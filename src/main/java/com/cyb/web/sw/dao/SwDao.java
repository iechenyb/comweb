package com.cyb.web.sw.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cyb.web.base.dao.HibernateBaseDao;
import com.cyb.web.sw.po.Sw;

@Repository("swDao")
public class SwDao extends HibernateBaseDao<Sw> {
  public List<Sw> getList(){
	return this.list();
  }
}
