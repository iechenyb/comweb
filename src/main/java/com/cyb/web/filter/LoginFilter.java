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

import net.sf.json.JSONObject;

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
import com.cyb.web.utils.BaseConfiguration;
import com.cyb.web.utils.Configuration;
import com.cyb.web.utils.EncodeUtils;
import com.cyb.web.utils.InjectionPatterns;


public class LoginFilter implements Filter {
	Log log = LogFactory.getLog(LoginFilter.class);
    public LoginFilter() {
    }
	public void destroy() {
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
			if(Boolean.valueOf(BaseConfiguration.get("useLoginFileter"))){
					request.setCharacterEncoding("utf-8");
					MyRequestWrapper req = new MyRequestWrapper((HttpServletRequest) request);
			        HttpServletResponse resp = (HttpServletResponse) response;  
			        String path = req.getContextPath();  
			        String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path;  
			        Object user = req.getSession(true).getAttribute(Contants.SSEIONUSERKEY);
			        if(req.getRequestURI().contains("job")
			        		||req.getRequestURI().contains("login/login.jsp")
			        		||req.getRequestURI().contains("user/login.do")
			        		||req.getRequestURI().contains("user/exit.do")
			        		||req.getRequestURI().contains("jsp/controller.jsp")//ueditor的请求不进行过滤
			        		){
			        	chain.doFilter(req, response);
			        }else{
				        if (user == null || "".equals(user)) {  
				        	if (req.getHeader("x-requested-with") != null 
			                        && "XMLHttpRequest".equalsIgnoreCase(req.getHeader("x-requested-with"))) {   
				        		resp.setHeader("sessionstatus","timeout");
				        		resp.setStatus(403);
				        		Map<String,Object> res = new HashMap<String, Object>();
				        		res.put("zt", 999);
				        		res.put("msg", "会话已经过期，请重新登录！");
			                    resp.getWriter().print(JSONObject.fromObject(res));
			                    return ;
			                }else{
					            resp.setHeader("Cache-Control", "no-store");  
					            resp.setDateHeader("Expires", 0);  
					            resp.setHeader("Prama", "no-cache");  
					            resp.sendRedirect(basePath+"/login/login.jsp?cmd=timeout");
			                }
				        } else{
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
						}
			        }
			}else{
				chain.doFilter(request, response);
			}
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

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
