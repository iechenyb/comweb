package com.cyb.web.model.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyb.web.base.service.HibernateBaseService;
import com.cyb.web.model.dao.ChildPoDao;
import com.cyb.web.model.po.ChildPo;
/**
 * 作者 : iechenyb
 * 功能描述: 说点啥
 * 创建时间: 2017年07月16日 13时03分26秒
 */
@Service("childPoService")
public class ChildPoService extends HibernateBaseService<ChildPo> {
   @Autowired
   private ChildPoDao model;
   /**
   * 作者 : iechenyb
   * 功能描述: 说点啥
   * 创建时间: 2017年07月16日 13时03分26秒
   */
   public List<ChildPo> getList(){
   	 return model.getList();
   }
}