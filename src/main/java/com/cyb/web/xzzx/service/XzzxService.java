package com.cyb.web.xzzx.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cyb.web.base.service.HibernateBaseService;
import com.cyb.web.xzzx.dao.XzzxDao;
@Service("xzzxService")
public class XzzxService extends HibernateBaseService<Object>{
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年11月8日下午2:20:44</br>
	 */
	@Resource(name="xzzxDao")
	XzzxDao dao ;
	
}
