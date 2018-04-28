package com.cyb.hibernate;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cyb.base.SpringJunitBase;
import com.cyb.web.hibernate.dao.ExtendDao;
import com.cyb.web.hibernate.po.Clss;
import com.cyb.web.hibernate.po.Student;
import com.cyb.web.hibernate.po.Student2;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年7月19日
 */
public class HQLQuery extends SpringJunitBase{
	Log log = LogFactory.getLog(HQLQuery.class);
	@Autowired
	ExtendDao dao;
	@Test
	public void initData(){
		Student s1 = new Student();
		s1.setSchool("xxx");
		s1.setSno("200");
		s1.setId("1");
		Student2 s2 = new Student2();
		s2.setId("1");
		s2.setSchool("xxx");
		s2.setSno("200");
		Clss cls = new Clss();
		cls.setClsName("zhongxue");
		s2.setCls(cls);
		cls.setId("1");
		dao.save(cls);
		dao.save(s1);
		dao.save(s2);
	}
	@SuppressWarnings("unchecked")
	@Test
	public void testHql(){
		String hql = "from Student";
		dao.testHql(hql);
		hql="from Student s ,Clss c where s.clsNo=c.id";
		dao.testHql(hql);
		hql="select s from Student s ,Clss c where s.clsNo=c.id";
		dao.testHql(hql);
		//hql="select s from Student s  inner join Clss c on s.clsNo=c.id";//不合法
		//join colunm测试
		hql="from Student2 s  where s.cls.id=1 and s.cls.clsName=2";
		dao.testHql(hql);
		hql="from Student2 s  where s.cls.clsName=2 and s.cls.id=1 ";
		dao.testHql(hql);
		hql="from Student2 as s inner join fetch s.cls";
		dao.testHql(hql);
		hql="from Student2 as s left join fetch s.cls";
		dao.testHql(hql);
		//查询1班人员记录
		hql="from Student2 as s right join fetch s.cls where s.cls.id=1";
		List<Student2> data = dao.testHql(hql);
		for(Student2 s2:data){
			log.info(s2.getSchool()+","+s2.getCls().getClsName());
		}
		//s和c以数组的形式存储。
		hql="select s,c from Student s ,Clss c where s.clsNo=c.id";
		List<Object[]> data2 = dao.testHql(hql);
		for(Object[] s2:data2){
			Student s = (Student) s2[0];
			Clss cls = (Clss)s2[1];
			log.info(s.getSchool()+","+cls.getClsName());
		}
		
	}
	@Test
	public void testPackagesToScanHql(){
		String hql="from Db2Bean";
		/*bean不存在，理论上后台应该出现报错日志，
		 * 但是因为log4j的日志级别为infor，
		 * 所以后台不能显示错误
		 * 将日志级别调整到debug则可以看到后台的dao底层错误。
		 * 生产上程序一摸一样，但是执行效果却不同，则可能出现表结构不一致的情况。
		 * */
		dao.testHql(hql);
		hql ="from DbBean";
		dao.testHql(hql);
	}
	
	
}
