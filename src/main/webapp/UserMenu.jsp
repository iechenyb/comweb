<%@ page language="java" import="java.util.*"
	contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html ng-app="app">
<head>
<title></title>
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
<link rel="stylesheet" href="<%=basePath%>cdn/amazeui.tree.css">
</head>
<body ng-controller="login" class="dhtreebody">
<input type="hidden" id="path" value=<%=basePath%>/>
<div class="am-g">
        <div class="am-u-sm-12" style="margin-left:10px;border:0px gray solid;">
            <ul class="am-tree" id="firstTree">
                <li class="am-tree-branch am-hide" data-template="treebranch">
                    <div class="am-tree-branch-header">
                        <button class="am-tree-branch-name">
                            <span class="am-tree-icon am-tree-icon-folder"></span>
                            <span class="am-tree-label"></span>
                        </button>
                    </div>
                    <ul class="am-tree-branch-children"></ul>
                    <div class="am-tree-loader"><span class="am-icon-spin am-icon-spinner"></span></div>
                </li>
                <li class="am-tree-item am-hide" data-template="treeitem">
                    <button class="am-tree-item-name">
                        <span class="am-tree-icon am-tree-icon-item"></span>
                        <span class="am-tree-label"></span>
                    </button>
                </li>
            </ul>
        </div>
</div>
<a href='#' id='page' target='rightFrame'></a>
<script src="<%=basePath%>amazeui/js/jquery.min.js"></script>
<script src="<%=basePath%>angular/angular-1.0.1.min.js"></script>
<script src="<%=basePath%>cdn/amazeui.tree.js"></script>
<script src="<%=basePath%>js/pub/ajax.js"></script>
<script src="<%=basePath%>tree.js"></script>
</body>
</html>
