package com.cyb.web.quartz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RecursiveTask;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.app.stock.DrawCodesUtils;
import com.app.stock.RealQutoes;
import com.app.stock.Stock;
import com.cyb.date.DateUtil;
import com.cyb.page.Pagination;
import com.cyb.web.redis.dao.RedisDao;
import com.cyb.web.stock.utils.QutoesUtils;

import net.sf.json.JSONObject;

//@Component
public class StockJob {
	String base = "com.cyb:";
	Log log = LogFactory.getLog(StockJob.class);
	
	@PostConstruct //初始化方法的注解方式  等同与init-method=init  
    public void init(){  
        System.out.println("调用初始化方法....");  
    }  
	
    @PreDestroy //销毁方法的注解方式  等同于destory-method=destory222  
    public void destory(){  
        System.out.println("调用销毁化方法....");  
    }  
	@Autowired
	RedisDao dao;

	/**
	 * 
	 *作者 : iechenyb<br>
	 *方法描述: 中午盘<br>
	 *创建时间: 2017年7月15日
	 *@throws IOException
	 */
	@Scheduled(cron = "* 0-59 9-11 * * ?")
	public void mornig() throws IOException {
		calMin();
	}
	
	/**
	 * 
	 *作者 : iechenyb<br>
	 *方法描述: 下午盘<br>
	 *创建时间: 2017年7月15日hj12
	 *@throws IOException
	 */
	@Scheduled(cron = "* 0-59 13-15 * * ?")
	public void afternoon() throws IOException {
		calMin();
	}
	
	public void calMin(){
		try {
			if(QutoesUtils.isIntraday()){
				exeTaskByPagSync();
			}
		} catch (Exception e) {
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
			public Integer call()  {
				try{
					log.info("开始抓取分钟行情...");
					int pageSize = 850;
					long totalcount = dao.lLen(base + "code:sh");
					StringBuffer sb = new StringBuffer("");
					Pagination p = new Pagination(1, pageSize, totalcount);
					for (int i = 1; i <= p.getPageCount(); i++) {
						Pagination p_ = new Pagination(i, pageSize, totalcount);
						for (int j = p_.getOffset(); j <= (p_.getPageSize() * i - 1); j++) {
							try{
								String codeStr = dao.lIndex(base + "code:sh", j);
							    Stock s = JSON.parseObject(codeStr, Stock.class);
								sb.append(s.getCode_() + ",");
							}catch(Exception e){}
						}
						log.info(p_.getOffset()+"->"+(p_.getPageSize() * i - 1)+",交易所：sh,总页数:"+p.getPageCount()+",当前页："+i);
						List<RealQutoes> rqs = DrawCodesUtils.getRealQutoesBatch(sb.toString());
						for (RealQutoes rq : rqs) {
							dao.lpush(base + "minuteQutoes:" + rq.getCode(), JSONObject.fromObject(rq).toString());
						}
						sb.delete(0, sb.length());
					}
					log.info("抓取分钟行情完成...");
				}catch(Exception e){
					e.printStackTrace();
				}
				return 1;
			}
		};
		FutureTask<Integer> future = new FutureTask<Integer>(callable);
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2); 
		fixedThreadPool.submit(future);
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
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(20); 
		List<FutureTask<Integer>> lsTask = new ArrayList<FutureTask<Integer>>();
		Pagination p = new Pagination(1, pageSize, totalcount);
		for (int i = 1; i <= p.getPageCount(); ++i) {
			Pagination p_ = new Pagination(i, pageSize, totalcount);
			final FutureTask<Integer> future = new FutureTask<Integer>(new PageTaskCallable(p_, dao,"sh"));
			//new Thread(future).start();
			lsTask.add(future);
			fixedThreadPool.execute(future);
			//new Thread(new PageTask(p_, dao,"sh")).start();*/
		}
		totalcount = dao.lLen(base + "code:sz");
		Pagination psz = new Pagination(1, pageSize, totalcount);
		for (int i = 1; i <= psz.getPageCount(); ++i) {
			//log.info(i+","+psz.getPageCount());
			Pagination p_ = new Pagination(i, pageSize, totalcount);
			final FutureTask<Integer> future = new FutureTask<Integer>(new PageTaskCallable(p_, dao,"sz"));
			//new Thread(future).start();
			lsTask.add(future);
			fixedThreadPool.execute(future);
		}
		//fixedThreadPool.shutdown();
		for(FutureTask<Integer> t:lsTask){
			t.get();
		}
		log.info("抓取分钟行情完成...");
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
class PageTaskCallable implements Callable<Integer> {
	Log log = LogFactory.getLog(PageTaskCallable.class);
	Pagination p_;
	RedisDao dao;
	String ex ;
	String base = "com.cyb:";

	public PageTaskCallable(Pagination p_, RedisDao dao,String ex) {
		//log.info(p_.getClass()+","+p_.getPageCount());
		this.p_ = p_;
		this.dao = dao;
		this.ex = ex;
	}

	public Integer call() throws Exception {
		StringBuffer sb = new StringBuffer("");
		for (int j = p_.getOffset(); j <= (p_.getPageSize() * p_.getCurrentPage() - 1); j++) {
			try{
				String codeStr = dao.lIndex(base + "code:"+ex, j);
				Stock s = JSON.parseObject(codeStr, Stock.class);
				String code = s.getExchange() + s.getCode();
				sb.append(code + ",");
			}catch(Exception e){}
		}
		//log.info(p_.getRecordCount()+","+p_.getOffset()+"->"+(p_.getPageSize() * p_.getCurrentPage() - 1)+",交易所："+ex+",总页数:"+p_.getPageCount()+",当前页："+p_.getCurrentPage());
		try{
			List<RealQutoes> rqs = DrawCodesUtils.getRealQutoesBatch(sb.toString());
			for (RealQutoes rq : rqs) {
				//dao.lpush(base + "minuteQutoesList:" + rq.getCode(), JSONObject.fromObject(rq).toString());
				dao.hSet(base + "minuteQutoesHash:" + rq.getCode(), DateUtil.format(new Date(), "HH:mm"), JSONObject.fromObject(rq).toString());
				sb.delete(0, sb.length());
				
			}
		}catch(Exception e){
			log.info("网络数据获取异常！"+e.toString());
		}
		return 1;
	}
}

class PageTaskRunable implements Runnable {
	Log log = LogFactory.getLog(PageTaskRunable.class);
	Pagination p_;
	RedisDao dao;
	String ex ;
	String base = "com.cyb:";

	public PageTaskRunable(Pagination p_, RedisDao dao,String ex) {
		this.p_ = p_;
		this.dao = dao;
		this.ex = ex;
	}

	public void run() {
		StringBuffer sb = new StringBuffer("");
		for (int j = p_.getOffset(); j <= (p_.getPageSize() * p_.getCurrentPage() - 1) - 1; j++) {
			String codeStr = dao.lIndex(base + "code:"+ex, j);
			Stock s = JSON.parseObject(codeStr, Stock.class);
			String code = s.getExchange() + s.getCode();
			sb.append(code + ",");
		}
		log.info(p_.getRecordCount()+","+p_.getOffset()+"->"+(p_.getPageSize() * p_.getCurrentPage() - 1)+",交易所：sh,总页数:"+p_.getPageCount()+",当前页："+p_.getCurrentPage());
		List<RealQutoes> rqs;
		try {
			rqs = DrawCodesUtils.getRealQutoesBatch(sb.toString());
			for (RealQutoes rq : rqs) {
				dao.lpush(base + "minuteQutoes:" + rq.getCode(), JSONObject.fromObject(rq).toString());
				sb.delete(0, sb.length());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
