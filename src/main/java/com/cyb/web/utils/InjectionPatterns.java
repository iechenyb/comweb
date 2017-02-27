package com.cyb.web.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InjectionPatterns {
	static Log log = LogFactory.getLog(InjectionPatterns.class);
	public static Map<String,String> xssMap = new LinkedHashMap<String,String>();
	
	static {
		//脚本注入
		// 含有脚本 javascript
		xssMap.put("javascript","<[j|J][a|A][v|V][a|A][s|S][c|C][r|R][i|I][p|P][t|T]>");
		// 含有脚本： script
		xssMap.put("script","<[s|S][c|C][r|R][i|C][p|P][t|T]>");
		// 含有函数： eval
		xssMap.put("eval","[e|E][v|V][a|A][l|L]");
		// 含有函数： eval
//		xssMap.put("cookie","[C|c][o|O][O|o][k|K][I|i][e|E]");
//		xssMap.put("alert","$[a|A][L|l][e|E][R|r][T|t]");
//		xssMap.put("prompt","$[P|p][R|r][O|o][M|m][P|p][t|T]");
		//sql注入
//		xssMap.put("delete","$[D|d][E|e][l|L][E|e][t|T][e|E]");
//		xssMap.put("update","$[u|U][P|p][d|D][A|a][t|T][e|E]");
//		xssMap.put("drop","$[d|D][R|r][o|O][p|P]");
//		xssMap.put("insert","$([I|i][N|n][S|s][e|E][r|R][T|t])[\\s\\S]*$");
	    //、; ，[ ，] ，| ，< ，> ，\
//		xssMap.put("os", "[;|\\\\|\\|']");//操作系统特殊符号
//		xssMap.put("java", "(<%)+|(%>)+");//过滤<%或者%>
//		xssMap.put("java", "(%%3E)+|(%3C%)+");//过滤<%或者%>
//		xssMap.put("java", "[(%3E)+|(%3C)+]");
		//xssMap.put("java", "[<|>]");
	}
	
	//匹配所有的表达式并显示
	public static String getMatcher(String regEx,String value)
	{
		StringBuffer buf = new StringBuffer("");
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(value);
		while(mat.find()){
			buf.append(mat.group());
			break;
		}
		log.info("本次http请求被过滤的参数值["+value+"]中包含的注入脚本关键字：["+buf.toString()+"]");
		return buf.toString();
	}
	public static boolean checkMatcher(String regEx,String value){
		try{
			Pattern pat = Pattern.compile(regEx);
			Matcher matcher = pat.matcher(value);
			return matcher.find();
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public static Map<String,String> checkJsMap(String value){
		String has = "0";
		String key1 = "";
		if(StringUtils.isNotEmpty(value)){
			Set<String> keySet = xssMap.keySet();
			for(String key:keySet){
				String regex = xssMap.get(key);
				if(checkMatcher(regex,value)){
					key1=getMatcher(regex,value);
					has = "1";
					break;
				}
			}
		}
		Map<String,String> ret = new HashMap<String, String>();
		ret.put("has", has);
		ret.put("key",key1);
		return ret;
	}
	public static void main(String[] args) {
		//单斜杠需要替换成双斜杠才能匹配
		 log.info(isValidRex("inserT table xxx;"));
	}
	
	public static  boolean isValid(String p) {  
	    p = p.toUpperCase();  
	    if (p.indexOf("DELETE") >= 0 || p.indexOf("ASCII") >= 0  
	        || p.indexOf("UPDATE") >= 0 || p.indexOf("SELECT") >= 0  
	        || p.indexOf("'") >= 0 || p.indexOf("SUBSTR(") >= 0  
	        || p.indexOf("COUNT(") >= 0 || p.indexOf(" OR ") >= 0  
	        || p.indexOf(" AND ") >= 0 || p.indexOf("DROP") >= 0  
	        || p.indexOf("EXECUTE") >= 0 || p.indexOf("EXEC") >= 0  
	        || p.indexOf("TRUNCATE") >= 0 || p.indexOf("INTO") >= 0  
	        || p.indexOf("DECLARE") >= 0 || p.indexOf("MASTER") >= 0  
	        ) {  
	        log.error("未能通过过滤器：p=" + p);  
	        return false;  
	    }  
	    return true;  
	}  
	//过滤 ‘  
	//ORACLE 注解 --  /**/  
	//关键字过滤 update ,delete   
	static String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"  
	            + "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";  
	  
	static Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);  
	  
	/*************************************************************************** 
	 * 参数校验 
	 *  
	 * @param str 
	 */  
	public static Map<String,String> isValidRex(String str) {
		Map<String,String> ret = new HashMap<String, String>();
		ret.put("has", "0");
		Matcher matcher = sqlPattern.matcher(str);
	    if (matcher.find()) {  
	    	String key = matcher.group();
	        log.error("未能通过过滤器：关键字=" + key);  
	        ret.put("has", "1");
	        ret.put("key",key);
	    }else{
	    	ret = checkJsMap(str);
	    }  
	    return ret;  
	}  
	
	
//	conditions：%3C <
//	utf8QueryStr:%3C
//	本次过滤的http请求参数值%3C中包含的注入脚本：%
//	httpuri:/VASAnalystCenter/contract/us.html
//	conditions：%E6%98%AF%E4%BA%86  是了
//	utf8QueryStr:%E6%98%AF%E4%BA%86
//	本次过滤的http请求参数值%E6%98%AF%E4%BA%86中包含的注入脚本：%%%%%%
}
