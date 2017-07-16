package com.cyb.web.utils;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.web.constant.Contants;
import com.cyb.web.filter.MyRequestWrapper;
import com.cyb.web.xtgl.po.User;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年7月16日
 */
public class RequestUtils {
	static Log log = LogFactory.getLog(RequestUtils.class);
	/**
	 * path:/WebDemo  
	 basePath:http://localhost:8683/WebDemo/  
	 remoteAddr:127.0.0.1  
	 servletPath:/index.jsp  
	 realPath:D:\apache-tomcat-6.0.13\webapps\WebDemo\  
	 remoteUser:null  
	 requestURI:/WebDemo/index.jsp  
	 *作者 : iechenyb<br>
	 *方法描述: 说点啥<br>
	 *创建时间: 2017年7月15日hj12
	 *@param request
	 *@return
	 */
	@SuppressWarnings("deprecation")
	public static Map<String,String> req2Map(HttpServletRequest request){
		String path = request.getContextPath();  
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
		String remoteAddress=request.getRemoteAddr();  
		String servletPath=request.getServletPath();  
		String realPath=request.getRealPath("/");  
		String remoteUser=request.getRemoteUser();  
		String requestURI=request.getRequestURI();
		Map<String,String> res = new HashMap<String,String>();
		res.put("ip", remoteAddress);
		res.put("basePath", basePath);
		res.put("path", path);
		res.put("servletPath", servletPath);
		res.put("realPath", realPath);
		res.put("remoteUser", remoteUser);
		res.put("uri", requestURI);
		return res;
	}
	public static boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		boolean isAjax = "XMLHttpRequest".equals(header) ? true : false;
		return isAjax;
	}

	public static String getRemoteHost(javax.servlet.http.HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}

	public static boolean isMoblie(HttpServletRequest request) {
		boolean isMoblie = false;
		String[] mobileAgents = { "iphone", "android", "phone", "mobile",
				"wap", "netfront", "java", "opera mobi", "opera mini", "ucweb",
				"windows ce", "symbian", "series", "webos", "sony",
				"blackberry", "dopod", "nokia", "samsung", "palmsource", "xda",
				"pieplus", "meizu", "midp", "cldc", "motorola", "foma",
				"docomo", "up.browser", "up.link", "blazer", "helio", "hosin",
				"huawei", "novarra", "coolpad", "webos", "techfaith",
				"palmsource", "alcatel", "amoi", "ktouch", "nexian",
				"ericsson", "philips", "sagem", "wellcom", "bunjalloo", "maui",
				"smartphone", "iemobile", "spice", "bird", "zte-", "longcos",
				"pantech", "gionee", "portalmmm", "jig browser", "hiptop",
				"benq", "haier", "^lct", "320x320", "240x320", "176x220",
				"w3c ", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq",
				"bird", "blac", "blaz", "brew", "cell", "cldc", "cmd-", "dang",
				"doco", "eric", "hipt", "inno", "ipaq", "java", "jigs", "kddi",
				"keji", "leno", "lg-c", "lg-d", "lg-g", "lge-", "maui", "maxo",
				"midp", "mits", "mmef", "mobi", "mot-", "moto", "mwbp", "nec-",
				"newt", "noki", "oper", "palm", "pana", "pant", "phil", "play",
				"port", "prox", "qwap", "sage", "sams", "sany", "sch-", "sec-",
				"send", "seri", "sgh-", "shar", "sie-", "siem", "smal", "smar",
				"sony", "sph-", "symb", "t-mo", "teli", "tim-", /* "tosh", */
				"tsm-", "upg1", "upsi", "vk-v", "voda", "wap-", "wapa", "wapi",
				"wapp", "wapr", "webc", "winw", "winw", "xda", "xda-",
				"Googlebot-Mobile" };
		if (request.getHeader("User-Agent") != null) {
			for (String mobileAgent : mobileAgents) {
				if (request.getHeader("User-Agent").toLowerCase()
						.indexOf(mobileAgent) >= 0) {
					isMoblie = true;
					break;
				}
			}
		}
		return isMoblie;
	}
	public static User getUser(HttpServletRequest request){
		User user = null ;
    	try{
    		user = (User) request.getSession().getAttribute(Contants.SSEIONUSERKEY);
    	}catch(Exception e){
    		log.info("获取登录人员回话记录失败！");
    	}
    	return user;
	}
	/**
	 * 
	 *作者 : iechenyb<br>
	 *方法描述: 文件流形式请求核查<br>
	 *创建时间: 2017年7月15日hj12
	 *@param request
	 *@return
	 *@throws IOException
	 */
	public Map<String,String> xssSQLStream(MyRequestWrapper request) throws IOException{
		Map<String,String> ret = new HashMap<String, String>();
		ret.put("has", "0");
		try {
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if(isMultipart){//带文件的上传
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				List<FileItem> items = upload.parseRequest(request);
				if(CollectionUtils.isNotEmpty(items)){
					for(FileItem it:items){
						if(it.isFormField()){
							ret = InjectionPatterns.isValidRex(EncodeUtils.iso88592UTF8(it.getString()));
							if("1".equals(ret.get("has"))){
								return ret;
							}
						}else{}
					}//end for
				}
			}else{//没有文件上传内容
				ret = xssSQL(new MyRequestWrapper(request));
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
			return ret;
		}
		return ret;
	}
	/**
	 * 
	 *作者 : iechenyb<br>
	 *方法描述: 普通的http请求，检查是否有sql注入<br>
	 *创建时间: 2017年7月15日hj12
	 *@param request
	 *@return
	 *@throws IOException
	 */
	public Map<String,String> xssSQL(MyRequestWrapper request) throws IOException{
		Map<String,String> ret = new HashMap<String, String>();
		ret.put("has", "0");
		List<String> keyVals =  request.getAllParameterNameAndValues();
		if(keyVals.size()>0){
			for(String s:keyVals){
				if(StringUtils.isNotEmpty(s)){
					String realVal = URLDecoder.decode(s.trim(),"UTF-8");
					ret = InjectionPatterns.isValidRex(realVal);
					if("1".equals(ret.get("has"))){//对http的queryString进行注入匹配
						log.info("请求参数存在安全风险"+"PostQueryStr:"+keyVals.toString());
						return ret;
				    }
				}
			}
		}
		String conditions = request.getQueryString();//x=1&y=2
		log.info("queryStr = "+conditions);
		if(conditions!=null&&!conditions.equals("")){		
			try{
				//当url解码出现异常时，直接跳转到错误页面，比如url里输入了<%或者%>
				conditions = URLDecoder.decode(conditions.trim(),"UTF-8");//get 请求是对url进行解码
				String[] strArr = conditions.split("&");
				for(int i=0;i<strArr.length;i++){
					ret = InjectionPatterns.isValidRex(strArr[i].split("=")[1]);
					if("1".equals(ret.get("has"))){//对http的queryString进行注入匹配
						log.info("请求参数存在安全风险"+"GetQueryStr:"+conditions);
						return ret;
					}
				}
			}catch(Exception e){
				return ret;
			}
		}
		
		String ajaxStr = request.getAjaxRequestParams();
		if(ajaxStr!=null&&!ajaxStr.equals("")){		
			try{
				//{"id":"fe4cbb65-1bd6-42f2-9816-3129fce0dd3b","username":"李四"}
				ajaxStr = URLDecoder.decode(ajaxStr.trim(),"UTF-8");//get 请求是对url进行解码
				String[] ajaxStrArr = ajaxStr.split(",");
				for(int i=0;i<ajaxStrArr.length;i++){
					ret = InjectionPatterns.isValidRex(ajaxStrArr[i].split(":")[1]);
					//当url解码出现异常时，直接跳转到错误页面，比如url里输入了<%或者%>
					if("1".equals(ret.get("has"))){//对http的queryString进行注入匹配
						log.info("请求参数存在安全风险"+"AjaxQueryStr:"+ajaxStr);
						return ret;
					}
				}
			}catch(Exception e){
				return ret;
			}
		}
	    return ret;
   }
	public static String systemLog(HttpServletRequest request){
		 //日志格式：张三[]访问地址uri
		User user = getUser(request);
		StringBuffer log = new StringBuffer("");
		if(user==null){
			log.append("[未知人员]");
		}else{
			log.append("["+user.getUsername()+"]");
		}
		String ip = getRemoteHost(request);
		log.append("通过IP["+ip+"]");
		String uri=request.getRequestURI();
		log.append("正在访问["+uri+"]。");
		return log.toString();
	}
}
