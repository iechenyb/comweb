
<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%  
String basePath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";  
%> 
<!doctype html>
<html  ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="icon" type="image/png" href="<%=basePath%>image/favicon.ico">
<title>信息管理系统界面</title>
</head>
<frameset rows="9%,*" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="top.jsp" name="topFrame" scrolling="yes" noresize="noresize" id="topFrame" title="topFrame" />
  <frameset cols="15%,*" frameborder="no" border="0" framespacing="0">
    <frame src="UserMenu.jsp" name="leftFrame" scrolling="yes" noresize="noresize" id="leftFrame" title="leftFrame" />
    <frame src="" name="rightFrame" id="rightFrame" title="rightFrame" />
  </frameset>
   <!-- <frame src="footer.jsp" name="topFrame" scrolling="yes" noresize="noresize" id="topFrame" title="topFrame" /> -->
</frameset>
<noframes>
<body>
</body></noframes>
</html>
