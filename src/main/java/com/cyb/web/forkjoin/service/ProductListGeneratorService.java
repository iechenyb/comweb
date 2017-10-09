package com.cyb.web.forkjoin.service;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.cyb.web.base.service.HibernateBaseService;
import com.cyb.web.forkjoin.po.Product;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年10月9日
 */
@Service
public class ProductListGeneratorService extends HibernateBaseService<Product> {
	Log log = LogFactory.getLog(ProductListGeneratorService.class);
	public List<Product> saveProBatch(int size){
        List<Product> ret = new ArrayList<Product>();
        for(int i=0;i<size;i++){
            Product product = new Product();
            product.setId(String.valueOf(i));
            product.setName("Product" + i);
            product.setPrice(10);
            delete(product);
            save(product);
            ret.add(product);
        }
        return ret;
    }
	
	public List<Product> getList(){
		return getAll();
	}
	
	public void updateProduct( List<Product> ps){
		int count=ps.size();
		for(int i=0;i<count;i++){
			update(ps.get(i));
		}
	}
}
