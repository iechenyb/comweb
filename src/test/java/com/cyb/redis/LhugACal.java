package com.cyb.redis;

import java.util.Date;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
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

public class LhugACal extends SpringJunitBase {
	TB_A po = new TB_A(0, 0);
	Log log = LogFactory.getLog(LhugACal.class);
	String keyBase = "Lhug:rolling:dhqh:" + po.getAccount() + ":2:a";
	String keyBasePerson = "Lhug:rolling:dhqh:persons";
	String keyBaseInit = "Lhug:rolling:dhqh:init";
	// 某个选手第二赛季时间区间列表
	String keyBaseDates = "Lhug:rolling:dhqh:" + po.getAccount() + ":2" + ":days";
	@Autowired
	RedisDao dao;
	@Autowired
	UserRedisDao dao2;

	// 第一天，插入所有的a表值
	/**
	 * 每个人的每个赛季都定义一个时间区间 作者 : iechenyb<br>
	 * 方法描述: 说点啥<br>
	 * 创建时间: 2017年7月15日
	 */
	@Test
	public void firstdate() {
		// 某个选手第二赛季成绩hash列表
		Date d = new Date();
		Random random = new Random();
		dao.delete(keyBaseDates);// 清除当前人员计算的最新日期区间值
		for (int i = 0; i < 100; i++) {// 第一次将所有值加载到redis中
			po = new TB_A(DateUtil.date2long8(DateUtil.nextSomeDay(d, i)), random.nextDouble());
			// if(!dao.exists(keyBase)){}
			dao.hSet(keyBase, String.valueOf(po.getYwrq()), JSONObject.fromObject(po).toString());
			// 每天计算的日期需要用dates存储起来，四个赛季共用一个时间区间。
			dao.lpush(keyBaseDates, String.valueOf(po.getYwrq()));
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
	
	@Test
	public void persons(){
		int account=888669;
		dao.delete(keyBasePerson);//清除历史数据
		for(int i=0;i<10000;i++){//插入当日参赛人员信息
			dao.lpush(keyBasePerson, String.valueOf(account++));
		}
	}
	//初始化人员参赛日期等信息
	@Test
	public void TB_INIT(){
		for(int i=0;i<dao.lLen(keyBasePerson);i++){//插入当日参赛人员信息
			String account = dao.lIndex(keyBasePerson, i);
			dao.hSet(keyBaseInit,account , JSONObject.fromObject(new TB_INIT(account,0,0)).toString());
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
