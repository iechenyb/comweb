<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%  
String path=application.getRealPath(request.getRequestURI());  
String basePath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";  
String pname = request.getParameter("pname");
String pid = request.getParameter("pid");
%> 
<!doctype html>
<html ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>编辑子菜单</title>
<link rel="stylesheet" href="<%=basePath%>amazeui/css/amazeui.min.css">
<link rel="stylesheet" href="<%=basePath%>amazeui/css/app.css">
<script src="<%=basePath%>cdn/ueditor.config.js"></script>
<script src="<%=basePath%>cdn/ueditor.all.min.js"></script>
<script src="<%=basePath%>cdn/ueditor.parse.min.js"></script>
</head>
<body ng-controller="news">
<input type="hidden" id='id' value="<%=pid%>"></input>
<input type="hidden" id='path' value="<%=basePath%>"></input>
<img class="click" src="../../../images/t01.png" ng-click="openEditView('add','')" ></img>
<table border=0 class="am-table am-table-bordered imgtable">
<tr>
    <td align='right' style="vertical-align:middle;width:10%">新闻标题:</td>
	<td align='left'>
	    <input id="title" class="am-form-field" style="width:90%;"/>
	</td>
	<td align='left'>
	    <input type="button" class="am-btn-primary" value="查询" 
	    ng-click="query111()" ng-change="query111()"></input>        
	</td>
</tr>  
<tr>
    <td align='right' style="vertical-align:middle;width:10%">关键字:</td>
	<td align='left'>
	    <font color="red" size="3">{{words}}</font>
	</td>
</tr>  
</table>
<table  class="am-table am-table-bordered imgtable">
<thead>
<tr align=center>
<th><center>序号</center></th>
<th ng-click="order='menuName';direct=!direct"><center>新闻标题</center></th>
</tr>
</thead>
<tbody>
<tr ng-repeat="vo in news | filter:query | orderBy:order:direct" align=center>
<td ng-bind="$index+1">1</td>
<td ><a href="">{{vo.title}}</a></td>
</tr>
</tbody>
</table>
<!--[if (gte IE 9)|!(IE)]><!-->
<script src="<%=basePath%>amazeui/js/jquery.min.js"></script>
<!--<![endif]-->
<!--[if lte IE 8 ]>
<script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="assets/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->
<%-- <script src="<%=basePath%>amazeui/js/amazeui.js"></script> --%>
<script src="<%=basePath%>amazeui/js/amazeui.min.js"></script>
<%--  <script src="<%=basePath%>js/jquery.min.js"></script> --%>
 <%-- <script src="<%=basePath%>js/jquery.js"></script> --%>
 <script src="<%=basePath%>angular/angular-1.0.1.min.js"></script>
 <script src="<%=basePath%>amazeui/js/handlebars.min.js"></script>
<script src="<%=basePath%>amazeui/js/amazeui.widgets.helper.js"></script>
<script src="<%=basePath%>amazeui/js/amazeui.widgets.helper.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/json2.js"></script>
<script src="<%=basePath%>js/pub/ajax.js"></script>
<script src="<%=basePath%>js/pub/validate.js"></script>
<script src="<%=basePath%>js/search/news.js"></script>
</body>
</html>
