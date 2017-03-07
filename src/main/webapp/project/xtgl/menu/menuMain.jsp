
<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%  
String basePath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";  
%> 
<!doctype html>
<html  ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
</head>
  <frameset cols="18%,*" frameborder="no" border="0" framespacing="0">
    <frame src="SysMenu.jsp" name="leftFramemenu" scrolling="yes" noresize="noresize" id="leftFramemenu" title="leftFramemenu" />
    <frame src="../../../project/xtgl/menu/editMenu.jsp?pid=menuroot&pname=系统菜单" name="rightFramemenucyb" id="rightFramemenucyb" title="rightFramemenucyb" />
  </frameset>
<noframes>
<body>
</body></noframes>
</html>
