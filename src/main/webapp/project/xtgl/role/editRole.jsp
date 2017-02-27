<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%  
String path=application.getRealPath(request.getRequestURI());  
String basePath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";  
String bh = request.getParameter("bh");
String id = request.getParameter("id");
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
<body ng-controller="editRole">
<input type="hidden" id='id' value="<%=bh%>"></input>
<input type="hidden" id='path' value="<%=basePath%>"></input>
<img class="click" src="../../../images/t01.png" ng-click="openEditView('add','')" ></img>

<table border=0 class="am-table am-table-bordered imgtable">
<tr>
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
<th ng-click="order='rolename';direct=!direct"><center>角色名称</center></th>
<th ng-click="order='description';direct=!direct"><center>角色描述</center></th>
<th><center>操作</center></th>
</tr>
</thead>
<tbody>
<tr ng-repeat="vo in results | filter| orderBy:order:direct" align=center ng-show="$index+1>=start&&$index+1<=end">
<td >{{$index+1}}</td>
<td >{{vo.rolename}}</td>
<td >{{vo.description}}</td>
<td align='center'>
<img  class="click"  src="../../../images/t02.png" ng-click="openEditView('mod',vo)"/></a>&nbsp;&nbsp;&nbsp; 
</td>
</tr>
</tbody>
</table>
<jsp:include page="../../pub/page.jsp"></jsp:include>
 <div class="am-modal am-modal-no-btn" tabindex="-1" id="eidtView">
  <div class="am-modal-dialog">
    <div class="am-modal-hd">编辑角色信息
      <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-confirm>&times;</a>
    </div>
    <div class="am-modal-bd">
			<table  class="am-table am-table-bordered">
				<tr>
					<td width="10%" align='right'>角色名称:</td>
					<td width="40%"><input type="text" style='width:60%;float:left;'  ng-model='role.rolename'  placeholder=""></td>
					</td>
				</tr> 
				<tr>
					<td width="10%" align='right'>角色描述:</td><td  width="50%">
					 <textarea style='width:60%;float:left;' ng-model="role.description" ></textarea>
					</td>
				</tr>
				<tr>
					<td colspan=4 align='center'>
					<button type="button" class="am-btn am-btn-primary" ng-click='submit(role)'>提交</button>
					</td>
				</tr>   
			</table>
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
<script src="<%=basePath%>js/pub/validate.js"></script>
<script src="<%=basePath%>js/pub/page.js"></script>
<script src="<%=basePath%>js/xtgl/role/editRole.js"></script>
</body>
</html>
