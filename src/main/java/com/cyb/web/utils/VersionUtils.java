package com.cyb.web.utils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.web.constant.Config;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年7月18日
 */
public class VersionUtils {
	Log log = LogFactory.getLog(VersionUtils.class);
	public static String getVersion(){
		if(BaseConfiguration.get("environmental").equals(Config.DEV.value)){
			return UUIDUtils.uuid();
		}else if(BaseConfiguration.get("environmental").equals(Config.TEST.value)){
			return UUIDUtils.uuid();
		}else if(BaseConfiguration.get("environmental").equals(Config.PRO.value)){
			return Configuration.get("version");
		}else{
			return "0.0.0";
		}
	}
}
