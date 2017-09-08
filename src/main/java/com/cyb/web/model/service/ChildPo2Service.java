package com.cyb.web.model.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyb.web.base.service.HibernateBaseService;
import com.cyb.web.model.dao.ChildPo2Dao;
import com.cyb.web.model.po.ChildPoExtend;
/**
 * 作者 : iechenyb
 * 功能描述: 说点啥
 * 创建时间: 2017年07月16日 13时03分26秒
 */
@Service("childPo2Service")
public class ChildPo2Service extends HibernateBaseService<ChildPoExtend> {
   @Autowired
   private ChildPo2Dao model;
   /**
   * 作者 : iechenyb
   * 功能描述: 说点啥
   * 创建时间: 2017年07月16日 13时03分26秒
   */
   public List<ChildPoExtend> getList(){
   	 return model.getList();
   }
}