package com.cyb.web.inter.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.cyb.web.inter.dao.ICarDao;
import com.cyb.web.inter.po.Car;
import com.cyb.web.inter.service.ICarService;

/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月22日
 */
@Repository("bmwCarServiceImpl")
public class BMWCarServiceImpl implements ICarService<Car>{
	
	@Resource(name="bmwCarDaoImpl")
	ICarDao<Car> dao;
	
	@Override
	public void run() {
		dao.run();
	}
	
}
