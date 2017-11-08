package com.cyb.redis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.cyb.CalUtils;
import com.cyb.base.SpringJunitBase;
import com.cyb.date.DateUtil;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年7月24日
 */
import com.cyb.web.redis.dao.RedisDao;
import com.cyb.web.redis.dao.UserRedisDao;

import net.sf.json.JSONObject;
/**
 * 大赛批量计算模拟测试
 * @author DHUser
 *
 */
public class LhugACalBatch extends SpringJunitBase {
	TB_A po = new TB_A(0, 0);
	long personNum=2000;
	Log log = LogFactory.getLog(LhugACalBatch.class);
	String keyBase = "Lhug:rolling:dhqh:" + po.getAccount() + ":2:a";
	String keyBasePerson = "Lhug:rolling:dhqh:persons";
	String keyBaseInit = "Lhug:rolling:dhqh:init";//+account:2|3|5|10
	String keyBaseSuccessed = "Lhug:rolling:dhqh:2";
	String keyBaseFailed = "Lhug:rolling:dhqh:1";
	String keyBase2Cal = "Lhug:rolling:dhqh:0";
	// 某个选手第二赛季时间区间列表
	String keyBaseDates = "Lhug:rolling:dhqh:" + po.getAccount() + ":2" + ":days";
	
	@Autowired
	RedisDao dao;
	
	@Autowired
	UserRedisDao dao2;
	
	public int threadhold = 500;//切分粒度,切分太小可能会导致栈溢出
	//并发初始化选手成绩信息
	class CalLjdwjzBatchTask extends RecursiveAction {
		private static final long serialVersionUID = 1L;
		private long first;
		private long last;
		public CalLjdwjzBatchTask(long first, long last) {
			this.first = first;
			this.last = last;
		}
		@Override
		protected  void compute() {
			Thread.currentThread().setName("[+first"+"-"+last+")");
			if (last - first < threadhold) {
			    calTask();//调用计算逻辑
			} else {
				long middle = (last + first) / 2;
				CalLjdwjzBatchTask t1 = new CalLjdwjzBatchTask(first, middle + 1);
				CalLjdwjzBatchTask t2 = new CalLjdwjzBatchTask(middle + 1, last);
				/*t1.fork();
				t2.fork();//异步-不可窃取
				t1.join();
				t2.join();*/
				invokeAll(t1, t2);//同步-可以窃取
			}
		}
		/*@SuppressWarnings("unused")
		private void calTask() {
			StringBuffer sb = new StringBuffer();
			for (long i = first; i < last; i++) {
				sb.append(dao.lIndex(keyBasePerson, i));//获取某个人
				calLjdwjzBatch(sb.toString());
				dao.lpush(keyBaseSuccessed,sb.toString());
				sb.delete(0, sb.length());
			}
		}*/
		
		private synchronized void calTask() {
			System.out.println("子任务计算区间：["+first+"-"+last+")");
			StringBuffer sb = new StringBuffer();
			for (long i = first; i < last; i++) {
				sb.append(dao.lIndex(keyBasePerson, i));//获取某个人的资金账号
				clearAKeysBatch(sb.toString(),2);//清除自己的旧数据
				initTaskBatch(sb.toString(),i);//初始化成绩和计算日期
				calLjdwjzBatch(sb.toString());//计算单位净值
				dao.lpush(keyBaseSuccessed,sb.toString());//记录成功初始化
				sb.delete(0, sb.length());//清除当前临时资金账号
			}
		}
	}
	
	//初始化成绩和计算日期
	public void  initTaskBatch(String account,long i){
		initABySeason(2,account,i);
		/*initABySeason(3,account,i);
		initABySeason(5,account,i);
		initABySeason(10,account,i);*/
	}
	//清除所有人所有赛季成绩
	@Test
	public void clearAKeys(){
		for(int i=0;i<dao.lLen(keyBasePerson);i++){//插入当日参赛人员信息
			clearAKeysBatch(dao.lIndex(keyBasePerson, i),2);
			clearAKeysBatch(dao.lIndex(keyBasePerson, i),3);
			clearAKeysBatch(dao.lIndex(keyBasePerson, i),5);
			clearAKeysBatch(dao.lIndex(keyBasePerson, i),10);
		}	
	}
	
	public void clearAKeysBatch(String account,int season){
		String keyBase = "Lhug:rolling:dhqh:competitor:" + account ;//+ ":"+season+":a";
		//执行赛季计算日key
		String keyBaseDates = "Lhug:rolling:dhqh:competitor:" + account; //+ ":"+season+ ":days";
		dao.delete(keyBaseDates);// 清除当前人员计算的最新日期区间值
		dao.delete(keyBase);
		/*String keyBase = "Lhug:rolling:dhqh:competitor";
		dao.delete(keyBase);*/
	}
	
	@Test
	public void clearAKeysBatch(){
		String keyBase = "Lhug:rolling:dhqh:competitor";
		dao.delete(keyBase);
	}
	
	@Test
	public void testForkJoin(){
		//clearAKeys();//清除keys,每个成员自己清除数据
		dao.delete(keyBaseSuccessed);//删除临时记录列表
		initRqList(2);//初始化日期数据
		InitPersons();//初始化人员数据 计算多少人
		CalLjdwjzBatchTask task = new CalLjdwjzBatchTask(0,dao.lLen(keyBasePerson));
		// 通过无参的类构造器创建一个ForkJoinPool
		ForkJoinPool pool = new ForkJoinPool();
		// 调用execute()方法执行任务
		pool.execute(task);
		// 显示关于线程池演变的信息
		long s = System.currentTimeMillis();
		try {
			try {
				task.get();
			} catch (ExecutionException e1) {
				e1.printStackTrace();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long e = System.currentTimeMillis();
		// 关闭线程池
		pool.shutdown();
		if (task.isCompletedNormally()) {
			System.out.println("并发任务执行完成！");
		}
		System.out.println("Main: End of the program."+"共耗时：" + (e - s) / 1000+"."+(e - s) % 1000);
	}
	// 第一天，插入所有的a表值
	/**
	 * 每个人的每个赛季都定义一个时间区间 
	 * 插入10年的成绩数据
	 * 作者 : iechenyb<br>
	 * 方法描述: 说点啥<br>
	 * 创建时间: 2017年7月15日
	 */
	@Test
	public void initAAndPersonsStatics() {
		StringBuffer sb = new StringBuffer();
		long s = System.currentTimeMillis();
		for(int i=0;i<dao.lLen(keyBasePerson);i++){//插入当日参赛人员信息
			sb.append(dao.lIndex(keyBasePerson, i));//获取某个人
			initABySeason(2,sb.toString(),i);
			/*initABySeason(3,sb.toString(),i);
			initABySeason(5,sb.toString(),i);
			initABySeason(10,sb.toString(),i);*/
			sb.delete(0, sb.length());
		}
		long e = System.currentTimeMillis();
		System.out.println("初始化"+personNum+"个选手的四个赛季成绩用时："+(e-s)/1000+"s."+(e-s)%1000+"ms");
	}
	/**
	 * 
	 *作者 : 批量生成成绩 ，每个人生成四个赛季的数据<br>
	 *方法描述: 该方法如果不同步，则生成的日期数据list会乱掉<br>
	 *创建时间: 2017年7月15日
	 *@param num synchronized
	 */
	public  void  initABySeason(long num,String account,long index) {
		long s = System.currentTimeMillis();
		// 某个选手第二赛季成绩hash列表
		Date d = new Date();
		//指定赛季成绩keycompetitor:
		String keyBase = "Lhug:rolling:dhqh:competitor:" + account + ":"+num+":a";
		//执行赛季计算日key
		String keyBaseDates = "Lhug:rolling:dhqh:competitor:" + account + ":"+num+ ":days";
		dao.delete(keyBaseDates);// 清除当前人员计算的最新日期区间值
		dao.delete(keyBase);
		long days = 240*num;
		for (int i = 0; i < days; i++) {// 第一次将所有值加载到redis中
			po = new TB_A(this.days.get(i));
			dao.hSet(keyBase, String.valueOf(po.getYwrq()), JSONObject.fromObject(po).toString());
			// 每天计算的日期需要用dates存储起来，四个赛季共用一个时间区间。
			dao.lpush(keyBaseDates, String.valueOf(po.getYwrq()));
		}
		long e = System.currentTimeMillis();
		System.out.println("["+index+"]初始化选手["+account+"]赛季["+num+"]成绩信息和日期信息用时："+(e-s)/1000+"s."+(e-s)%1000+"ms");
	}
	List<Long> days;
	public void initRqList(int season){
		days = new ArrayList<Long>();
		Date d = new Date();
		int daysT = 240*season;
		for (int i = 0; i < daysT; i++) {// 第一次将所有值加载到redis中
			days.add(DateUtil.date2long8(DateUtil.nextSomeDay(d, i)));
		}
	}
	/**
	 * 
	 *作者 : iechenyb<br>
	 *方法描述: 模拟计算单位净值<br>
	 *创建时间: 2017年7月15日
	 */
	public void calLjdwjzBatch(String account){
		calLjdwjz(account,2);//计算当前资金账号第2滚动赛季成绩
		//calLjdwjz(account,3);//计算当前资金账号第2滚动赛季成绩
		//calLjdwjz(account,5);//计算当前资金账号第2滚动赛季成绩
		//calLjdwjz(account,10);//计算当前资金账号第2滚动赛季成绩
	}
	//计算某个人的指定赛季的成绩
	public synchronized void calLjdwjz(String account,long season){
		long s = System.currentTimeMillis();
		String  daysKey= "Lhug:rolling:dhqh:competitor:" + account + ":"+season+ ":days";
		String  keyBaseA = "Lhug:rolling:dhqh:competitor:" + account + ":"+season+":a";
		try{
			long days = dao.lLen(daysKey)-1;//计算总天数
			String firstDay = dao.lIndex(daysKey, days);
			StringBuffer sb = new StringBuffer();//当日日期临时变量
			StringBuffer preDay = new StringBuffer();//上日日期临时变量
			for(long i=days;i>=0;i--){//计算前3条
				sb.append(dao.lIndex(daysKey, i));//记录当前日期
				if(firstDay.equals(sb.toString())){
					//System.out.println("计算的第一天:"+sb.toString());
					// 获取当日的成绩基础数据
					TB_A a = JSON.parseObject(new String(dao.hGet(keyBaseA, sb.toString())),TB_A.class);
					//第一天参赛的成绩进行默认的初始化
					a.setLjdwjz(1);//默认累计单位净值1
					a.setDrdwjz(1);//默认当日单位净值1
					a.setDrdwfs(a.getDrqy());//默认单位出金
					a.setDrljdwcj(0);//默认单位出金
					a.setDrdwfs(a.drqy);
					a.setSrqy(0);//待定，暂时复制为0
					a.setDrjyk(a.getDrqy()-a.getSrqy()-a.getDrjrj());//计算当日净盈亏
					//截至当日累计净入金
					a.setDrljjrj(0+a.getDrzdjrj());//计算截至当日累计净入金
					a.setZdljjrj(a.getDrljjrj()<0?0:a.getDrljjrj());//计算当日累计净入金
					dao.hSet(keyBaseA, sb.toString(), JSONObject.fromObject(a).toString());
				}else{
					//获取上日日期
					preDay.append(dao.lIndex(daysKey, i+1));
					//System.out.println("计算日期："+sb.toString()+"，上日日期："+preDay.toString());
					TB_A preA = JSON.parseObject(new String(dao.hGet(keyBaseA, preDay.toString())),TB_A.class);
					// 获取当日的成绩基础数据
					TB_A curA = JSON.parseObject(new String(dao.hGet(keyBaseA, sb.toString())),TB_A.class);
					curA = CalUtils.calLjdwjz(curA, preA);//计算累计单位净值并将当日成绩重新赋值
					///dao.hSet(keyBaseA, sb.toString(),JSONObject.fromObject(curA).toString());
					curA = null;
					preA = null;
				}
				sb.delete(0, 8);//清除日期数据
				preDay.delete(0, 8);//清除日期数据
			}
			
			long e = System.currentTimeMillis();
			//显示第一天的成绩信息
			//System.out.println(new String(dao.hGet(keyBaseA, "20171107")));
			System.out.println("["+account+"]"+"滚动赛季["+season+"]"+"计算单位净值用时："+(e-s)/1000+"s."+(e-s)%1000+"ms");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	// 查询当前人员计算的成绩信息和日期列表
	@Test
	public void queryDateInex() {
		System.out.println(dao.hLen(keyBase));
		System.out.println(dao.lLen(keyBaseDates));
		// 找到指定一天的索引
		System.out.println(dao.lIndex(keyBaseDates, 0));
	}
	
	@Test
	public void queryAFromSomeDate() {
		TB_A po = new TB_A();
		String  keyBaseA = "Lhug:rolling:dhqh:competitor:" + po.getAccount() + ":2:a";
		System.out.println(new String(dao.hGet(keyBaseA, "20171108")));
		System.out.println(new String(dao.hGet(keyBaseA, "20171107")));
	}

	// 删除小于起始点成绩
	@Test
	public void removeBig() {
		//删除日期大于第3大的所有数据
		long start = Long.valueOf(dao.lIndex(keyBaseDates, 2));
		for (long i = 0; i < dao.lLen(keyBaseDates); ) {
			String value = dao.lIndex(keyBaseDates, 0);
			long cur = Long.valueOf(value);
			if (cur > start) {
				dao.lrem(keyBaseDates, 0, value);//移除日期列表中的数据,索引会变化
				dao.hDel(keyBase, String.valueOf(cur));//移除对应的成绩数据
				i=0;
				System.out.println(start+" < 被删除日期"+cur);
			} else {
				break;
			}

		}
	}
	
	@Test
	public void  removeLess(){
		//删除小于第三小的日期数据
		long start = Long.valueOf(dao.lIndex(keyBaseDates,dao.lLen(keyBaseDates)-10));
		for(long i=dao.lLen(keyBaseDates)-1;i>=0;){
			String value = dao.lIndex(keyBaseDates, dao.lLen(keyBaseDates)-1);
			long cur = Long.valueOf(value);
			if(cur < start){
				dao.lrem(keyBaseDates, 1, value);
				dao.hDel(keyBase, String.valueOf(cur));//移除对应的成绩数据
				System.out.println(start+" > 删除尾端日期："+cur);
			}else{
				break;
			}
		}
	}
	
	@Test
	public void calIndex() {
		// 某个选手第二赛季成绩hash列表
		for (long i = dao.lLen(keyBaseDates)-1; i >0; i--) {// 第一次将所有值加载到redis中
			System.out.println("-------------------------------------");
			String cur = dao.lIndex(keyBaseDates,i);
			System.out.println("开始计算指标数据，当前日期为"+cur);
			if(i==(dao.lLen(keyBaseDates)-1)){
				System.out.println("计算第一天数据！！！");
			}else{
				String aCur = new String(dao.hGet(keyBase, String.valueOf(cur)));
				String aPre = new String(dao.hGet(keyBase,dao.lIndex(keyBaseDates,i+1)));
				System.out.println("上一个交易日为："+dao.lIndex(keyBaseDates,i+1)
				+"\n,当前交易数据为："+JSONObject.fromObject(aCur)+
				",\n上个交易日数据为："+aPre);
			}
		}
	}
	/**
	 * 
	 *作者 : iechenyb<br>
	 *方法描述: 初始化1万个才赛选手<br>
	 *创建时间: 2017年7月15日
	 */
	@Test
	public void InitPersons(){
		int account=888660;
		dao.delete(keyBase2Cal);//删除待计算人员
		dao.delete(keyBasePerson);//清除历史人员数据
		for(int i=0;i<personNum;i++){//插入当日参赛人员信息
			String account_tmmp  = String.valueOf(account++);
			dao.lpush(keyBasePerson, String.valueOf(account_tmmp));
			dao.hSet(keyBase2Cal, account_tmmp, JSONObject.fromObject(new PersonCalStatus(account_tmmp)).toString());
		}
	}
	//初始化人员参赛日期等信息
	@Test
	public void TB_INIT(){
		dao.delete(keyBaseInit);
		for(int i=0;i<dao.lLen(keyBasePerson);i++){//插入当日参赛人员信息
			String account = dao.lIndex(keyBasePerson, i);
			dao.hSet(keyBaseInit+":"+account,String.valueOf(2) , JSONObject.fromObject(new TB_INIT(account,0,0)).toString());
			dao.hSet(keyBaseInit+":"+account,String.valueOf(3) , JSONObject.fromObject(new TB_INIT(account,0,0)).toString());
			dao.hSet(keyBaseInit+":"+account,String.valueOf(5) , JSONObject.fromObject(new TB_INIT(account,0,0)).toString());
			dao.hSet(keyBaseInit+":"+account,String.valueOf(10) , JSONObject.fromObject(new TB_INIT(account,0,0)).toString());
		}
	}
	
	/**
	 * 获取hset的所有key值，然后遍历hset内容
	 *作者 : iechenyb<br>
	 *方法描述: 说点啥<br>
	 *创建时间: 2017年7月15日
	 */
	@Test
	public void getSetKeys(){
		System.out.println(dao.hGetAll("Lhug:rolling:dhqh:888668:2:a").size());	
		Map<byte[],byte[]> maps = dao.hGetAll("Lhug:rolling:dhqh:888668:2:a");
		for(byte[] tmp:maps.keySet()){
			System.out.println("key="+new String(tmp)+",value="+new String(maps.get(tmp)));
		}
	}
	//初始化人员参赛日期等信息
	@Test
	public void ReadTB_INIT(){
		for(int i=0;i<dao.lLen(keyBasePerson);i++){//插入当日参赛人员信息
			String account = dao.lIndex(keyBasePerson, i);
			JSONObject obj = JSONObject.fromObject(new String(dao.hGet(keyBaseInit,account)));
			System.out.println(obj.get("big"));
			TB_INIT init = JSON.parseObject(new String(dao.hGet(keyBaseInit,account)), TB_INIT.class);
			System.out.println("json to bean ，bigdecimal is "+init.getBig());
		}
	}

}
