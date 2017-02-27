<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <!-- 设置360浏览器使用webkit引擎 -->
    <meta name="renderer" content="webkit">
    <!-- 取消百度移动页面调整-->
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="stylesheet" href="<%=basePath %>assets/css/amazeui.min.css" />
	<link rel="stylesheet" href="<%=basePath %>assets/css/admin.css">
	<link rel="stylesheet" href="<%=basePath %>css/base.css">
	<script src="<%=basePath%>assets/js/jquery.min.js"></script>
	<script src="<%=basePath%>assets/js/amazeui.min.js"></script> 