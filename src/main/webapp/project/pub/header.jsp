<%@ page language="java" import="com.cyb.web.utils.*" %>
<%  
String path=application.getRealPath(request.getRequestURI());  
String basePath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";  
request.setAttribute("basePath", basePath);
request.setAttribute("version", VersionUtils.getVersion());
%> 