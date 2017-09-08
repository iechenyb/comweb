package com.cyb.web.model.service;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cyb.web.base.service.HibernateBaseService;
import com.cyb.web.model.dao.Model2Dao;
import com.cyb.web.model.po.ModelExtend;
/**
 * 作者 : iechenyb
 * 功能描述: 说点啥
 * 创建时间: 2017年07月16日 13时03分26秒
 */
@Service("model2Service")
public class Model2Service extends HibernateBaseService<ModelExtend> {
   @Resource
   private Model2Dao model;
   /**
   * 作者 : iechenyb
   * 功能描述: 说点啥
   * 创建时间: 2017年07月16日 13时03分26秒
   */
   public List<ModelExtend> getList(){
   	 return model.getList();
   }
}