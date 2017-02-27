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
<link href="<%=basePath%>liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>amazeui/css/amazeui.min.css">
<link rel="stylesheet" href="<%=basePath%>amazeui/css/app.css">
<script src="<%=basePath%>cdn/ueditor.config.js"></script>
<script src="<%=basePath%>cdn/ueditor.all.min.js"></script>
<script src="<%=basePath%>cdn/ueditor.parse.min.js"></script>
</head>
<body ng-controller="editRole">
<input type="hidden" id='path' value="<%=basePath%>"></input>
<table border=0 class="am-table am-table-bordered imgtable">
<tr >
   <td align='right' style="vertical-align:middle;width:50%">角色名称:</td>
	<td align='left'>
	    <input ng-model="name_" ng-init="name_=''" class="am-form-field" style="width:200px;" ng-change="refreshPage()"/>        
	</td>
</tr>   
</table>

<table  class="am-table am-table-bordered imgtable">
<thead>
<tr align=center>
<th><center>序号</center></th>
<th ng-click="order='title';direct=!direct"><center>角色名称
<span ng-class="{false:'am-icon-caret-down',true:'am-icon-caret-up'}[direct==true]" calss="ng-hide am-icon-caret-down" ></span>
</center></th>
<th ng-click="order='type';direct=!direct"><center>角色描述</center></th>
<th><center>操作</center></th>
</tr>
</thead>
<tbody>
<tr ng-repeat="vo in results | filter| orderBy:order:direct" ng-show="$index+1>=start&&$index+1<=end" align=center>
<td >{{$index+1}}</td>
<td >{{vo.rolename}}</td>
<td >{{vo.description}}</td>
<td align='center'>
<img  class="click"  src="../../../images/t02.png" ng-click="openEditView(vo)"/></a>&nbsp;&nbsp;&nbsp; 
</tr>
</tbody>
</table>
<jsp:include page="../../pub/page.jsp"></jsp:include>
<div class="am-popup" style="width:30%;" id="my-popup1">
  <div class="am-popup-inner">
    <div class="am-popup-hd">
      <h4 class="am-popup-title">修改[{{name}}]权限</h4>
      <span data-am-modal-close
            class="am-close">&times;</span>
    </div>
    <div class="am-popup-bd">
      <div style="width:100%;height:500px; margin:1px; float:left; clear:both; border:1px solid #ccc; overflow:auto;  ">
	 	<ul id="tree1" style="width:800px;"></ul>
	  </div> 
	  <center><button  type="button"  id='' ng-click="save()" class="am-btn am-btn-danger" >保存</button></center>
    </div>
  </div>
</div>
 <button
  style="display:none;"
  type="button"
  id='showTree'
  class="am-btn am-btn-danger"
  data-am-modal="{target: '#my-popup1'}">
</button>

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
<script src="<%=basePath%>liger/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
 <!--<link href="<%=basePath%>liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />-->
 <script src="<%=basePath%>liger/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
 <script src="<%=basePath%>liger/lib/ligerUI/js/plugins/ligerTree.js" type="text/javascript"></script>
 <script src="<%=basePath%>amazeui/js/amazeui.page.min.js"></script>
 <script src="<%=basePath%>js/pub/ajax.js"></script>
 <script src="<%=basePath%>js/pub/page.js"></script>
<script src="<%=basePath%>js/xtgl/menu/assignMenu.js"></script>
</body>
</html>
