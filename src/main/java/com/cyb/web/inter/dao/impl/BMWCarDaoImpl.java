package com.cyb.web.inter.dao.impl;

import org.springframework.stereotype.Repository;

import com.cyb.web.inter.dao.ICarDao;
import com.cyb.web.inter.po.Car;

/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月22日
 */
@Repository("bmwCarDaoImpl")
public class BMWCarDaoImpl implements ICarDao<Car>{

	@Override
	public void run() {
		System.out.println("BMWCar run....");
	}
	
}
