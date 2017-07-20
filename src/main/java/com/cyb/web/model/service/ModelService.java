package com.cyb.web.model.service;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cyb.web.base.service.HibernateBaseService;
import com.cyb.web.model.dao.ModelDao;
import com.cyb.web.model.po.Model;
/**
 * 作者 : iechenyb
 * 功能描述: 说点啥
 * 创建时间: 2017年07月16日 13时03分26秒 implements GenericService<Model>
 */
@Service("modelService")
public class ModelService extends HibernateBaseService<Model> {
   @Resource
   private ModelDao model;
   /**
   * 作者 : iechenyb
   * 功能描述: 说点啥
   * 创建时间: 2017年07月16日 13时03分26秒
   */
   public List<Model> getList(){
   	 return model.getList();
   }
}