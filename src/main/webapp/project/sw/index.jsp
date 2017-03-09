<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%  
String path=application.getRealPath(request.getRequestURI());  
String basePath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";  
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
<body ng-controller="zhs">
<input type="hidden" id='path' value="<%=basePath%>"></input>
<table  class="am-table am-table-bordered imgtable">
<thead>
<tr align=center>
<th colspan=3>
<center>中国工商银行上海分行(mysql-A表)</center>
</th>
</tr>
<tr align=center>
<th><center>序号</center></th>
<th><center>账号</center></th>
<th><center>余额</center></th>
</tr>
</thead>
<tbody>
<tr ng-repeat="vo in zhs" align=center>
<td ng-bind="$index+1">1</td>
<td ><a href="">{{vo.cardNo}}</a></td>
<td ><a href="">{{vo.money}}</a></td>
</tr>
<tr align=center>
<td colspan=3>默认转账操作：账户1先转出100，账户2后转入100</td>
</tr>
<tr align=center>
<td colspan=3>  
<input type="button" class="am-btn-primary" value="正常转账"    ng-click="query111('')"></input>
<input type="button" class="am-btn-primary" value="转入时异常" ng-click="query111('throw')"></input> 
 </td>
</tr>
</tbody>
</table>
<hr>
<table  class="am-table am-table-bordered imgtable">
<thead>
<tr align=center>
<th colspan=3>
<center>中国工商银行郑州分行(mysql-B表)</center>
</th>
</tr>
<tr align=center>
<th><center>序号</center></th>
<th><center>账号</center></th>
<th><center>余额</center></th>
</tr>
</thead>
<tbody>
<tr ng-repeat="vo in zhs" align=center>
<td ng-bind="$index+1">1</td>
<td ><a href="">{{vo.cardNo}}</a></td>
<td ><a href="">{{vo.money}}</a></td>
</tr>
<tr align=center>
<td colspan=3>默认转账操作：账户1先转出100，账户2后转入100</td>
</tr>
<tr align=center>
<td colspan=3>  
<input type="button" class="am-btn-primary" value="正常转账"    ng-click="query111('')"></input>
<input type="button" class="am-btn-primary" value="转入时异常" ng-click="query111('throw')"></input> 
 </td>
</tr>
</tbody>
</table>
<hr>
<table  class="am-table am-table-bordered imgtable">
<thead>
<tr align=center>
<th colspan=3>
<center>中国建设银行上海分行(oracle-A表)</center>
</th>
</tr>
<tr align=center>
<th><center>序号</center></th>
<th><center>账号</center></th>
<th><center>余额</center></th>
</tr>
</thead>
<tbody>
<tr ng-repeat="vo in zhs" align=center>
<td ng-bind="$index+1">1</td>
<td ><a href="">{{vo.cardNo}}</a></td>
<td ><a href="">{{vo.money}}</a></td>
</tr>
<tr align=center>
<td colspan=3>默认转账操作：账户1先转出100，账户2后转入100</td>
</tr>
<tr align=center>
<td colspan=3>  
<input type="button" class="am-btn-primary" value="正常转账"    ng-click="query111('')"></input>
<input type="button" class="am-btn-primary" value="转入时异常" ng-click="query111('throw')"></input> 
 </td>
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
<script src="<%=basePath%>js/sw/index.js"></script>
</body>
</html>
