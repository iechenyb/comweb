
package com.cyb.web.redis.dao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.cyb.collection.po.User;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年10月18日
 */
@Repository
public class UserRedisDao extends RedisCacheBase<User>{
	Log log = LogFactory.getLog(UserRedisDao.class);
}
