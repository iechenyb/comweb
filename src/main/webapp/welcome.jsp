<%@ page language="java" import="java.util.*"
	contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Object name = request.getSession().getAttribute("username");
%>
<!DOCTYPE html>
<html ng-app="app">
<head>
<title>用户登录</title>
<link rel="icon" type="image/png" href="<%=basePath%>image/favicon.ico">
<meta name="keywords"
	content="Flat Dark Web Login Form Responsive Templates, Iphone Widget Template, Smartphone login forms,Login form, Widget Template, Responsive Templates, a Ipad 404 Templates, Flat Responsive Templates" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<meta name="mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-title" content="东航期货" />
<link rel="stylesheet" href="<%=basePath%>amazeui/css/amazeui.min.css">
<link rel="stylesheet" href="<%=basePath%>amazeui/css/app.css">
<link href="css/dh.css" rel="stylesheet">
<link href="css/loading.css" rel="stylesheet">
</head>
<body ng-controller="login" style="background-color:#F5F5F5;">
	<form class="am-form am-text-center ng-pristine ng-valid">
		<div ui-view
			style="position: absolute; top: 10rem; left: 35%; width: 30%;">
			<center>
				<img src="image/logo.png" class="dh-bg" style="z-index: 5"/>
				<div style="padding-top:100px;"><font size=25><b>后台管理系统</b></font></div>
				<div style="padding-top:30px;"><font size=20>欢迎你，<%=name%>!</font></div>
			</center>
		</div>
	</form>
	<div class="login-form"></div>
	<script src="<%=basePath%>amazeui/js/jquery.min.js"></script>
	<script src="<%=basePath%>angular/angular-1.0.1.min.js"></script>
	<script src="<%=basePath%>js/login/login.js"></script>
</body>

</html>
