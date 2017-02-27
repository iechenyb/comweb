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
<body ng-controller="editmenu">
<input type="hidden" id='id' value="<%=pid%>"></input>
<input type="hidden" id='path' value="<%=basePath%>"></input>
<img class="click" src="../../../images/t01.png" ng-click="openEditView('add','')" ></img>
<table border=0 class="am-table am-table-bordered imgtable">
<tr>
    <td align='right' style="vertical-align:middle;width:50%">菜单名称:</td>
	<td align='left'>
	    <input ng-model="query" class="am-form-field" style="width:200px;">        
	</td>
</tr>   
</table>

<table  class="am-table am-table-bordered imgtable">
<thead>
<tr align=center>
<th><center>序号</center></th>
<th ng-click="order='menuName';direct=!direct"><center>菜单名称
<span ng-class="{false:'am-icon-caret-down',true:'am-icon-caret-up'}[direct==true]" calss="ng-hide am-icon-caret-down" ></span>
</center></th>
<th ng-click="order='url';direct=!direct"><center>功能链接</center></th>
<th ng-click="order='isLeaf';direct=!direct"><center>是否叶子</center></th>
<th ng-click="order='ordor';direct=!direct"><center>顺序号</center></th>
<th ng-click="order='menuDesc';direct=!direct"><center>描述</center></th>
<th><center>操作</center></th>
</tr>
</thead>
<tbody>
<tr ng-repeat="vo in menus | filter:query | orderBy:order:direct" align=center>
<td ng-bind="$index+1">1</td>
<td >{{vo.menuName}}</td>
<td >{{vo.url}}</td>
<td ><div ng-show="vo.isLeaf=='1'">叶子节点</div><div ng-show="vo.isLeaf=='0'">非叶子节点</div></td>
<td >{{vo.ordor}}</td>
<td >{{vo.menuDesc}}</td>
<td align='center'>
<img  class="click"  src="../../../images/t02.png" ng-click="openEditView('mod',vo)"/></a>&nbsp;&nbsp;&nbsp; 
<img  class="click" ng-click="delMenu(vo);" src="../../../images/t03.png"></td>
</tr>
</tbody>
</table>


 <div class="am-modal am-modal-no-btn" tabindex="-1" id="eidtView">
  <div class="am-modal-dialog">
    <div class="am-modal-hd">编辑菜单信息
      <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-confirm>&times;</a>
    </div>
    <div class="am-modal-bd">
			<form class="am-form" action='' method='post'>
					<input type="hidden" value="<%=pid%>" id="pid"/>
					<table  class="am-table am-table-bordered">
					<tr>
						<td width="10%" align='right'>上级菜单:</td><td width="40%"><input type="text" class="" value="<%=pname%>"  style='width:400px;float:left;' disabled placeholder="上级菜单名称"></td>
						<td width="10%" align='right'>菜单名称:</td><td  width="50%"><input type="text" style='width:400px' class="" ng-model="menu.menuName" id="menuName" name="menuName" placeholder="菜单名称"/><font color='red' ></font></td>
					</tr>
					<tr>
						<td width="10%" align='right'>菜单路径:</td>
						<td>
					     <input type="text"  class="" id='url' name="url" ng-model="menu.url"  placeholder="http://ip:port/webName/?,非叶子节点时为空!">
					     <font color='red' ></font>
						</td>
						<td width="10%" align='right'>是否叶子:</td>
						<td width="40%" align='left'> 
						  <label class="am-radio-inline">
					        <input type="radio"  value="1" ng-model="menu.isLeaf" name="isLeaf"> 是
					      </label>
					      <label class="am-radio-inline">
					        <input type="radio" value='0' name="isLeaf" ng-model="menu.isLeaf"> 否
					      </label><font color='red' size=1>&nbsp;&nbsp;设置为叶子节点则不能再添加子节点！</font>
					     </td>
					</tr> 
					<tr>
						<td width="10%" align='right'>排序:</td>
						<td colspan=3>
					      <input type="text"  class="" id='ordor' name="ordor" ng-model="menu.ordor"  placeholder="菜单顺序号，必须为数字">
						</td>
					</tr>    
					<tr>
						<td width="10%" align='right'>备注:</td>
						<td colspan=3>
					      <textarea class="" rows="5" name="bz" ng-model="menu.menuDesc" style='width:700'></textarea>
						</td>
					</tr>   
					<tr>
						<td colspan=4 align='center'>
						<button type="button" ng-click='submit(menu)' class="am-btn am-btn-primary">提交</button>
						</td>
					</tr>   
					</table>
					</form>
    </div>
  </div>
</div> 

<div class="am-modal am-modal-confirm" tabindex="-1" id="tip">
  <div class="am-modal-dialog">
    <div class="am-modal-hd">提示</div>
    <div class="am-modal-bd">
             	确定要删除这条记录吗？
    </div>
    <div class="am-modal-footer">
      <span class="am-modal-btn" data-am-modal-cancel>取消</span>
      <span class="am-modal-btn" data-am-modal-confirm>确定</span>
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
<%--  <script src="<%=basePath%>js/jquery.min.js"></script> --%>
 <%-- <script src="<%=basePath%>js/jquery.js"></script> --%>
 <script src="<%=basePath%>angular/angular-1.0.1.min.js"></script>
 <script src="<%=basePath%>amazeui/js/handlebars.min.js"></script>
<script src="<%=basePath%>amazeui/js/amazeui.widgets.helper.js"></script>
<script src="<%=basePath%>amazeui/js/amazeui.widgets.helper.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/json2.js"></script>
<script src="<%=basePath%>js/pub/ajax.js"></script>
<script src="<%=basePath%>js/pub/validate.js"></script>
<script src="<%=basePath%>js/xtgl/menu/editMenu.js"></script>
</body>
</html>
