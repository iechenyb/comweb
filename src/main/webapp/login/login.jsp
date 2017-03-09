<%@ page language="java" import="java.util.*"
	contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+  request.getContextPath()+ "/";
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
<meta name="apple-mobile-web-app-title" content="" />
<link rel="stylesheet" href="<%=basePath%>amazeui/css/amazeui.min.css">
<link href="<%=basePath%>css/dh.css" rel="stylesheet">
<link href="<%=basePath%>css/loading.css" rel="stylesheet">
</head>
<body ng-controller="login">
	<form class="am-form am-text-center ng-pristine ng-valid" style="overflow:hidden;">
	<input type="hidden" class="" value='<%=basePath%>' id="path" >
		<div
			style="position: absolute; top: 10rem; left: 35%; width: 30%;">
			<center>
			    <h1>管理员登录</h1>
				<fieldset>
					<div class="am-form-group">
						<input type="text" class="" id="name" ng-model="user.username"
							　 placeholder="请输入账号">
					</div>
					<div class="am-form-group">
						<input type="password" class="" id="pwd" ng-model="user.password"
							placeholder="请输入密码">
					</div>
					<button type="button"
						class="am-btn am-btn-primary am-btn am-btn-primary am-btn-block am-margin-top"
						ng-click="login()">登录</button>
						<div class="am-form-group" id="tip"></div>
				</fieldset>
			</center>
		</div>
	</form>
	<a id="to" href="#" onclick='top.location.replace("../index.jsp")'></a>
	<div class="login-form"></div>
	<script src="<%=basePath%>amazeui/js/jquery.min.js"></script>
	<script src="<%=basePath%>angular/angular-1.0.1.min.js"></script>
	<script src="<%=basePath%>js/login/login.js"></script>
</body>

</html>
