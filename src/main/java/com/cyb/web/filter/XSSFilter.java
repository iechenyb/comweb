package com.cyb.web.filter;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.web.utils.Configuration;
import com.cyb.web.utils.EncodeUtils;
import com.cyb.web.utils.InjectionPatterns;

import net.sf.json.JSONObject;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2017年7月31日
 */
public class XSSFilter implements Filter {
	Log log = LogFactory.getLog(XSSFilter.class);

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse resp = (HttpServletResponse) response;  
		MyRequestWrapper req = new MyRequestWrapper((HttpServletRequest) request);
		String path = req.getContextPath();
		String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path;  
		if(Boolean.valueOf(Configuration.get("enableXSS")))
    	{   
        	Map<String,String> ret = check(req);
        	if("1".equals(ret.get("has"))){//存在脚本注入风险
        		if (req.getHeader("x-requested-with") != null 
                        && "XMLHttpRequest".equalsIgnoreCase(req.getHeader("x-requested-with"))) {   
	        		resp.setHeader("sessionstatus","timeout");
	        		resp.setStatus(403);
	        		Map<String,Object> res = new HashMap<String, Object>();
	        		res.put("zt", 999);
	        		res.put("msg", "请求存在风险，被系统拦截,存在敏感关键字["+ret.get("key")+"]！");
                    resp.getWriter().print(JSONObject.fromObject(res));
                    return ;
                }else{
		            resp.setHeader("Cache-Control", "no-store");  
		            resp.setDateHeader("Expires", 0);  
		            resp.setHeader("Prama", "no-cache");  
		            resp.sendRedirect(basePath+"/exception/xsserror.jsp");
                }
        	}else{
        		chain.doFilter(req, response);
        	}
    	}else{
    		chain.doFilter(req, response);
    	}
		/*Map<String, String[]> parameterMap = request.getParameterMap();

		for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
			String[] value = entry.getValue();
			if (value != null) {
				int strLength = value.length;
				for (int i = 0; i < strLength; i++) {
					boolean result = stripXSS(value[i]);
					if (result)
						response.sendRedirect("error.jsp");
				}
			}
		}*/
		chain.doFilter(req, resp);

	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public Map<String,String> check(MyRequestWrapper request) throws IOException{
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
				ret = check1(new MyRequestWrapper(request));
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
			return ret;
		}
		return ret;
	}
	public Map<String,String> check1(MyRequestWrapper request) throws IOException{
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


}
