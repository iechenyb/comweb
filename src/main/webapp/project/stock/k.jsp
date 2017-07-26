<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:include page="../pub/header.jsp"></jsp:include>
<% String version=request.getAttribute("version").toString(); %>
<!doctype html>
<html ng-app="app">
<head>
<base href="<%=request.getAttribute("basePath")%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>分钟K线</title>
<link rel="stylesheet" href="amazeui/css/amazeui.min.css">
<link rel="stylesheet" href="amazeui/css/app.css?v=<%=version%>">
</head>
<body ng-controller="controller">
	<input type="hidden" id='path' value="<%=request.getAttribute("basePath")%>"></input>
	   <div class="am-g am-g-fixed"></div> 
	  <div class="am-g am-g-fixed">
	      <div id="KChart" class="am-u-sm-6" style="height:500px;width:50%;"></div>
	 	<div id="tjChart" class="am-u-sm-6" style="height:500px;width:50%;"></div>
	  </div>
	<script src="amazeui/js/jquery.min.js"></script>
	<script src="amazeui/js/amazeui.min.js"></script>
	<script src="amazeui/js/amazeui.page.min.js"></script>
	<script src="angular/angular-1.0.1.min.js"></script>
	<script src="amazeui/js/handlebars.min.js"></script>
	<script src="amazeui/js/amazeui.widgets.helper.js"></script>
	<script src="amazeui/js/amazeui.widgets.helper.min.js"></script>
	<script type="text/javascript" src="js/json2.js"></script>
	<script src="echarts/echarts-all.js"></script>
	<script src="js/pub/ajax.js?v=<%=version%>"></script>
	<script src="js/pub/page.js?v=<%=version%>"></script>
	<script src="js/pub/validate.js?v=<%=version%>"></script>
	<script src="js/stock/k.js?v=<%=version%>"></script>
</body>
</html>
