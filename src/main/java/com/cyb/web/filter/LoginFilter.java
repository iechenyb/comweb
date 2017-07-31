package com.cyb.web.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.web.constant.Contants;
import com.cyb.web.utils.BaseConfiguration;

import net.sf.json.JSONObject;

public class LoginFilter implements Filter {
	Log log = LogFactory.getLog(LoginFilter.class);

	public LoginFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (Boolean.valueOf(BaseConfiguration.get("useLoginFileter"))) {
			request.setCharacterEncoding("utf-8");
			MyRequestWrapper req = new MyRequestWrapper((HttpServletRequest) request);
			HttpServletResponse resp = (HttpServletResponse) response;
			String path = req.getContextPath();
			String basePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path;
			Object user = req.getSession(true).getAttribute(Contants.SSEIONUSERKEY);
			if (req.getRequestURI().contains("job") || req.getRequestURI().contains("login/login.jsp")
					|| req.getRequestURI().contains("user/login.do") || req.getRequestURI().contains("user/exit.do")
					|| req.getRequestURI().contains("jsp/controller.jsp")// ueditor的请求不进行过滤
			) {
				chain.doFilter(req, response);
			} else {
				if (user == null || "".equals(user)) {
					if (req.getHeader("x-requested-with") != null
							&& "XMLHttpRequest".equalsIgnoreCase(req.getHeader("x-requested-with")))
					{//ajax请求
						resp.setHeader("sessionstatus", "timeout");
						resp.setStatus(403);
						Map<String, Object> res = new HashMap<String, Object>();
						res.put("zt", 999);
						res.put("msg", "会话已经过期，请重新登录！");
						resp.getWriter().print(JSONObject.fromObject(res));
						return;
					} else {//非ajax请求
						resp.setHeader("Cache-Control", "no-store");
						resp.setDateHeader("Expires", 0);
						resp.setHeader("Prama", "no-cache");
						resp.sendRedirect(basePath + "/login/login.jsp?cmd=timeout");
					}
				} else {
					chain.doFilter(request, response);
				}
			}
		} else {
			chain.doFilter(request, response);
		}
	}
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
