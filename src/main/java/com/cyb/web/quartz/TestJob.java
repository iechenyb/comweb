package com.cyb.web.quartz;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RecursiveTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.app.stock.DrawCodesUtils;
import com.app.stock.RealQutoes;
import com.app.stock.Stock;
import com.cyb.page.Pagination;
import com.cyb.web.redis.dao.RedisDao;

import net.sf.json.JSONObject;

@Component
public class TestJob {
	String base = "com.cyb:";
	Log log = LogFactory.getLog(TestJob.class);

	@Autowired
	RedisDao dao;

	@Scheduled(cron = "0 * * * * ?")
	public void method1() throws IOException {
		try {
			exeTaskByPagSync();
			/* ForkJoinPool forkjoinPool = new ForkJoinPool();
			long totalcount = dao.lLen(base + "code:sh");
	        //生成一个计算任务，计算1+2+3+4
			PageTaskForkJoin task = new PageTaskForkJoin(0, totalcount,dao);
	        //执行一个任务
	        forkjoinPool.submit(task);*/
		} catch (InterruptedException e) {
			e.printStackTrace();
			
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 作者 : iechenyb<br>
	 * 方法描述: 一条一条抓取<br>
	 * 创建时间: 2017年7月15日hj12
	 */
	public void exeTaskOneByOne() {
		Callable<Integer> callable = new Callable<Integer>() {
			public Integer call() throws Exception {
				log.info("开始抓取分钟行情...");
				for (int i = 0; i < dao.lLen(base + "code:sh"); i++) {
					String codeStr = dao.lIndex(base + "code:sh", i);
					Stock s = JSON.parseObject(codeStr, Stock.class);
					String code = s.getExchange() + s.getCode();
					log.info(i + "," + code);
					RealQutoes rq = DrawCodesUtils.getQutoes(code);
					dao.lpush(base + "minuteQutoes:" + code, JSONObject.fromObject(rq).toString());
				}
				log.info("抓取分钟行情完成...");
				return 1;
			}
		};
		FutureTask<Integer> future = new FutureTask<Integer>(callable);
		new Thread(future).start();
	}

	/**
	 * 
	 * 作者 : 页面批量获取<br>
	 * 方法描述: 说点啥<br>
	 * 创建时间: 2017年7月15日hj12
	 */
	public void exeTaskByPage() {
		Callable<Integer> callable = new Callable<Integer>() {
			public Integer call() throws Exception {
				log.info("开始抓取分钟行情...");
				int pageSize = 850;
				long totalcount = dao.lLen(base + "code:sh");
				StringBuffer sb = new StringBuffer("");
				Pagination p = new Pagination(1, pageSize, totalcount);
				for (int i = 1; i <= p.getPageCount(); i++) {
					Pagination p_ = new Pagination(i, pageSize, totalcount);
					for (int j = p_.getOffset(); j <= (p_.getPageSize() * i - 1) - 1; j++) {
						String codeStr = dao.lIndex(base + "code:sh", j);
						Stock s = JSON.parseObject(codeStr, Stock.class);
						sb.append(s.getCode_() + ",");
					}
					log.info(p_.getOffset()+"->"+(p_.getPageSize() * i - 1)+",交易所：sh,总页数:"+p_.getPageCount()+",当前页："+i);
					List<RealQutoes> rqs = DrawCodesUtils.getRealQutoesBatch(sb.toString());
					for (RealQutoes rq : rqs) {
						dao.lpush(base + "minuteQutoes:" + rq.getCode(), JSONObject.fromObject(rq).toString());
					}
					sb.delete(0, sb.length());
				}
				log.info("抓取分钟行情完成...");
				return 1;
			}
		};
		FutureTask<Integer> future = new FutureTask<Integer>(callable);
		new Thread(future).start();
	}

	/**
	 * 
	 * 作者 : 页面批量获取<br>
	 * 方法描述: 说点啥<br>
	 * 创建时间: 2017年7月15日hj12
	 * 
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public void exeTaskByPagSync() throws InterruptedException, ExecutionException {
		log.info("开始抓取分钟行情...");
		int pageSize = 850;
		long totalcount = dao.lLen(base + "code:sh");
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10); 
		Pagination p = new Pagination(1, pageSize, totalcount);
		for (int i = 1; i <= p.getPageCount()+1; ++i) {
			log.info(i+","+p.getPageCount());
			Pagination p_ = new Pagination(i, pageSize, totalcount);
			FutureTask<Integer> future = new FutureTask<Integer>(new PageTask(p_, dao,"sh"));
			/*new Thread(future).start();
			future.get();*/
			fixedThreadPool.execute(future);
		}
		Thread.sleep(10);
		totalcount = dao.lLen(base + "code:sz");
		Pagination psz = new Pagination(1, pageSize, totalcount);
		for (int i = 1; i <= psz.getPageCount()+1; ++i) {
			log.info(i+","+psz.getPageCount());
			Pagination p_ = new Pagination(i, pageSize, totalcount);
			FutureTask<Integer> future = new FutureTask<Integer>(new PageTask(p_, dao,"sz"));
			fixedThreadPool.execute(future);
		}
		//fixedThreadPool.shutdown();
		log.info("抓取分钟行情完成...");
		Thread.sleep(10);
	}
}
class PageTaskForkJoin extends RecursiveTask<Integer>{
	RedisDao dao;
	String base = "com.cyb:";
	long start;
	long end;
	private static final long serialVersionUID = 1L;
    public PageTaskForkJoin(long start ,long end,RedisDao dao){
		this.dao = dao;
		this.start = start;
		this.end = end;
    }
	@Override
	protected Integer compute() {
		boolean canCompute = (end - start) <=850;
		if(canCompute){
			for (long i=start; i<=end; i++)
            {
                
            }
		}else{
			 long middle = (start + end)/2;
			 PageTaskForkJoin leftTask = new PageTaskForkJoin(start, middle,dao);
			 PageTaskForkJoin rightTask = new PageTaskForkJoin(middle+1, end,dao);
			// 执行子任务
            leftTask.fork();
            rightTask.fork();
            //等待任务执行结束合并其结果
            leftTask.join();
            rightTask.join();
		}
		return 1;
	}
}
class PageTask implements Callable<Integer> {
	Log log = LogFactory.getLog(PageTask.class);
	Pagination p_;
	RedisDao dao;
	String ex ;
	String base = "com.cyb:";

	public PageTask(Pagination p_, RedisDao dao,String ex) {
		this.p_ = p_;
		this.dao = dao;
		this.ex = ex;
	}

	public Integer call() throws Exception {
		StringBuffer sb = new StringBuffer("");
		for (int j = p_.getOffset(); j <= (p_.getPageSize() * p_.getCurrentPage() - 1) - 1; j++) {
			String codeStr = dao.lIndex(base + "code:"+ex, j);
			Stock s = JSON.parseObject(codeStr, Stock.class);
			String code = s.getExchange() + s.getCode();
			sb.append(code + ",");
		}
		log.info(p_.getRecordCount()+","+p_.getOffset()+"->"+(p_.getPageSize() * p_.getCurrentPage() - 1)+",交易所：sh,总页数:"+p_.getPageCount()+",当前页："+p_.getCurrentPage());
		List<RealQutoes> rqs = DrawCodesUtils.getRealQutoesBatch(sb.toString());
		for (RealQutoes rq : rqs) {
			dao.lpush(base + "minuteQutoes:" + rq.getCode(), JSONObject.fromObject(rq).toString());
			sb.delete(0, sb.length());
		}
		return 1;
	}
}
