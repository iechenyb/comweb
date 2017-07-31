package com.cyb.web.filter;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Pattern;

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

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2017年7月31日
 */
public class UrlFilter implements Filter {
	Log log = LogFactory.getLog(UrlFilter.class);

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;

		HttpServletResponse response = (HttpServletResponse) resp;

		Map<String, String[]> parameterMap = request.getParameterMap();

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
		}
		chain.doFilter(req, resp);

	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	/**
	 * 如果找到非法字符则返回true,如果没找到则返回false
	 * 
	 * @param value
	 * @return
	 */
	public static boolean stripXSS(String value) {
		boolean result = false;
		if (value != null) {

			// Avoid null characters
			value = value.replaceAll("", "");

			// Avoid anything between script tags
			Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
			result = scriptPattern.matcher(value).find();// .replaceAll("");
			// //如果找到则为true
			if (result)
				return result;

			// Avoid anything in a src='...' type of expression
			scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			result = scriptPattern.matcher(value).find();// .replaceAll("");
			if (result)
				return result;

			scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			result = scriptPattern.matcher(value).find();// .replaceAll("");
			if (result)
				return result;

			// Remove any lonesome </script> tag
			scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
			result = scriptPattern.matcher(value).find();// .replaceAll("");
			if (result)
				return result;

			// Remove any lonesome <script ...> tag
			scriptPattern = Pattern.compile("<script(.*?)>",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			result = scriptPattern.matcher(value).find();// .replaceAll("");
			if (result)
				return result;

			// Avoid eval(...) expressions
			scriptPattern = Pattern.compile("eval\\((.*?)\\)",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			result = scriptPattern.matcher(value).find();// .replaceAll("");
			if (result)
				return result;

			// Avoid expression(...) expressions
			scriptPattern = Pattern.compile("expression\\((.*?)\\)",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			result = scriptPattern.matcher(value).find();// .replaceAll("");
			if (result)
				return result;

			scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
			result = scriptPattern.matcher(value).find();// .replaceAll("");
			if (result)
				return result;

			// Avoid onload= expressions
			scriptPattern = Pattern.compile("onload(.*?)=",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			result = scriptPattern.matcher(value).find();// .replaceAll("");
			if (result)
				return result;

			// Avoid alert:... expressions
			scriptPattern = Pattern.compile("alert", Pattern.CASE_INSENSITIVE);
			result = scriptPattern.matcher(value).find();// .replaceAll("");
			if (result)
				return result;
			
			scriptPattern = Pattern.compile("iframe", Pattern.CASE_INSENSITIVE);
			result = scriptPattern.matcher(value).find();// .replaceAll("");
			if (result)
				return result;
		}
		return result;
	}

}
