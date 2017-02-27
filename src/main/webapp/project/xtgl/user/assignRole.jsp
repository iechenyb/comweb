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
</head>
<body ng-controller="editUserRole">
<input type="hidden" id='path' value="<%=basePath%>"></input>
<table border=0 class="am-table am-table-bordered imgtable">
<tr>
	<td align='right' style="vertical-align:middle;">职工号:</td>
	<td align='left'>
	    <input ng-model="zghq" ng-init="zghq=''" class="am-form-field" style="width:200px;" ng-change="refreshPage()"/>        
	</td>
	<td align='right' style="vertical-align:middle;"> 姓名:</td>
	<td align='left'>
	   <input ng-model="nameq" ng-init="nameq=''" class="am-form-field" style="width:200px;" ng-change="refreshPage()"/>        
	</td>
</tr>   
</table>

<table  class="am-table am-table-bordered imgtable">
<thead>
<tr align=center>
<th ng-click="order='empno';direct=!direct"><center>职工号
<span ng-class="{false:'am-icon-caret-down',true:'am-icon-caret-up'}[direct==true]" calss="ng-hide am-icon-caret-down" ></span>
</center></th>
<th ng-click="order='username';direct=!direct"><center>姓名</center></th>
<th ng-click="order='sex';direct=!direct"><center>性别</center></th>
<th ng-click="order='idcard';direct=!direct"><center>身份证</center></th>
<th ng-click="order='phone';direct=!direct"><center>手机号</center></th>
<th ng-click="order='email';direct=!direct"><center>邮件</center></th>
<th ng-click="order='isEffect';direct=!direct"><center>是否可用</center></th>
<th><center>操作</center></th>
</tr>
</thead>
<tbody>
<tr ng-repeat="vo in results | filter | orderBy:order:direct" align=center ng-show="$index+1>=start&&$index+1<=end">
<td >{{vo.empno}}</td>
<td >{{vo.username}}</td>
<td ><div ng-show="vo.sex=='1'">男</div><div ng-show="vo.sex=='0'">女</div></td>
<td ><div ng-show="vo.idcard!=''&&vo.idcard!=null">{{vo.idcard.substring(0,6)+"********"+vo.idcard.substring(vo.idcard.length-4,vo.idcard.length)}}</div></td>
<td >{{vo.phone.substring(0,3)+"****"+vo.phone.substring(vo.phone.length-4,vo.phone.length)}}</td>
<td >{{vo.email}}</td>
<td ><div ng-show="vo.isEffect=='1'">启用</div><div ng-show="vo.isEffect=='0'">禁用</div></td>
<td align='center'>
<img  class="click"  src="../../../images/t02.png" ng-click="openEditView(vo)"/></a>&nbsp;&nbsp;&nbsp; 
</tr>
</tbody>
</table>
<jsp:include page="../../pub/page.jsp"></jsp:include>
<div class="am-popup" style="width:40%;" id="my-popup1">
  <div class="am-popup-inner">
    <div class="am-popup-hd">
      <h4 class="am-popup-title">修改用户[{{mname}}]的角色</h4>
      <span data-am-modal-close
            class="am-close">&times;</span>
    </div>
    <div class="am-popup-bd">
    <table  class="am-table am-table-bordered imgtable">
	<thead>
	<tr align=center>
	<th align=center>&nbsp;</th>
	<th ng-click="order='title';direct=!direct"><center>角色名称
	<span ng-class="{false:'am-icon-caret-down',true:'am-icon-caret-up'}[direct==true]" calss="ng-hide am-icon-caret-down" ></span>
	</center></th>
	<th ng-click="order='type';direct=!direct"><center>角色描述</center></th>
	</tr>
	</thead>
	<tbody>
	<tr ng-repeat="vo in roles | filter:query | orderBy:order:direct" align=center>
	<td> 
	  <label class="am-checkbox-inline">
        <input type="checkbox" value="{{vo.id}}"  name="sroles" ng-checked="selected(vo.id)"/>
      </label>
    </td>
	<td >{{vo.rolename}}</td>
	<td >{{vo.description}}</td>
	</tr>
	</tbody>
	</table>
	<center><button  type="button"  id='' ng-click="save()" class="am-btn am-btn-danger" >保存</button></center>
    </div>
  </div>
</div>

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
<script src="<%=basePath%>amazeui/js/amazeui.page.min.js"></script>
<%--  <script src="<%=basePath%>js/jquery.min.js"></script> --%>
 <%-- <script src="<%=basePath%>js/jquery.js"></script> --%>
 <script src="<%=basePath%>angular/angular-1.0.1.min.js"></script>
 <script src="<%=basePath%>amazeui/js/handlebars.min.js"></script>
<script src="<%=basePath%>amazeui/js/amazeui.widgets.helper.js"></script>
<script src="<%=basePath%>amazeui/js/amazeui.widgets.helper.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/json2.js"></script>
<script src="<%=basePath%>js/pub/ajax.js"></script>
<script src="<%=basePath%>js/pub/page.js"></script>
<script src="<%=basePath%>js/xtgl/user/assignRole.js"></script>
</body>
</html>
