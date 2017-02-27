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
<body ng-controller="edituser" ng-init="pageSize=10">
<input type="hidden" id='id' value="<%=bh%>"></input>
<input type="hidden" id='path' value="<%=basePath%>"></input>
<img class="click" src="../../../images/t01.png" ng-click="openEditView('add','')" ></img>

<table border=0 class="am-table am-table-bordered imgtable">
<tr>
    <td align='right' style="vertical-align:middle;">职工号:</td>
	<td align='left'>
	    <input ng-model="zghq" ng-init="zghq=''" class="am-form-field" style="width:200px;" ng-change="refreshPage()"/>        
	</td>
	<td align='right' style="vertical-align:middle;">姓名:</td>
	<td align='left'>
	    <input ng-model="nameq" ng-init="nameq=''" class="am-form-field" style="width:200px;" ng-change="refreshPage()"/>        
	</td>
</tr>   
</table>

<table  class="am-table am-table-bordered imgtable">
<thead>
<tr align=center>
<th><center>序号</center></th>
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
<td >{{$index+1}}</td>
<td >{{vo.empno}}</td>
<td >{{vo.username}}</td>
<td ><div ng-show="vo.sex=='1'">男</div><div ng-show="vo.sex=='0'">女</div></td>
<td ><div ng-show="vo.idcard!=''&&vo.idcard!=null">{{vo.idcard.substring(0,6)+"********"+vo.idcard.substring(vo.idcard.length-4,vo.idcard.length)}}</div></td>
<td >{{vo.phone.substring(0,3)+"****"+vo.phone.substring(vo.phone.length-4,vo.phone.length)}}</td>
<td >{{vo.email}}</td>
<td ><div ng-show="vo.isEffect=='1'">启用</div><div ng-show="vo.isEffect=='0'">禁用</div></td>
<td align='center'>
<img  class="click"  src="../../../images/t02.png" ng-click="openEditView('mod',vo)"/></a>&nbsp;&nbsp;&nbsp; 
<img  class="click" ng-click="del(vo);" src="../../../images/t03.png">&nbsp;&nbsp;&nbsp;<a ng-click="resetPwd(vo);" href="javascript:void(0);">重置密码</a></td>
</tr>
</tbody>
</table>
<jsp:include page="../../pub/page.jsp"></jsp:include>
 <div class="am-modal am-modal-no-btn" tabindex="-1" id="eidtView">
  <div class="am-modal-dialog">
    <div class="am-modal-hd">编辑用户信息
      <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-confirm>&times;</a>
    </div>
    <div class="am-modal-bd">
			<table  class="am-table am-table-bordered">
				<tr>
					<td width="40%" align='right'>工号<font color='red' >*</font>:</td>
					<td width="40%">
					<input type="text" style='width:60%;float:left;'  ng-model='user.empno'  placeholder=""/>
					</td>
				</tr> 
				<tr>
					<td width="10%" align='right'>姓名<font color='red' >*</font>:</td>
					<td width="40%"><input type="text" style='width:60%;float:left;'  ng-model='user.username'  placeholder=""/>
					</td>
				</tr> 
				<tr>
					<td width="10%" align='right'>性别<font color='red' >*</font>:</td>
					 <td  width="50%"  align='left'>
					 <div style='width:60%;float:left;'>
					 <input type="radio"  value="1"  ng-model="user.sex" ng-checked="true" name="sex"> 男
        			 <input type="radio" value='0'  ng-model="user.sex" name="sex"> 女
        			 </div>
					</td>
				</tr>
				<tr>
					<td width="10%" align='right'>身份证:</td>
					<td width="40%"><input type="text" style='width:60%;float:left;'  ng-model='user.idcard'  placeholder=""/>
					</td>
				</tr> 
				<tr>
					<td width="10%" align='right'>手机号<font color='red' >*</font>:</td>
					<td width="40%"><input type="text" style='width:60%;float:left;'  ng-model='user.phone'  placeholder=""/>
					</td>
				</tr> 
				<tr>
					<td width="10%" align='right'>邮箱<font color='red' >*</font>:</td>
					<td width="40%"><input type="text" style='width:60%;float:left;'  ng-model='user.email'  placeholder=""/>
					</td>
				</tr> 
				<tr>
					<td width="10%" align='right'>是否可用<font color='red' >*</font>:</td>
					 <td  width="50%"  align='left'>
					 <div style='width:60%;float:left;'>
					 <input type="radio"  value="1"  ng-model="user.isEffect" name="isEffect"> 启用
        			 <input type="radio" value='0'  ng-model="user.isEffect" name="isEffect"> 禁用
        			 </div>
					</td>
				</tr>
				<tr>
					<td colspan=4 align='center'>
					<button type="button" class="am-btn am-btn-primary" ng-click='submit(user)'>提交</button>
					</td>
				</tr>   
			</table>
    </div>
  </div>
</div> 

<div class="am-modal am-modal-confirm" tabindex="-1" id="tip">
  <div class="am-modal-dialog">
    <div class="am-modal-hd">提示</div>
    <div class="am-modal-bd">
             	确定要删除[{{dname}}]的记录吗？
    </div>
    <div class="am-modal-footer">
      <span class="am-modal-btn" data-am-modal-cancel>取消</span>
      <span class="am-modal-btn" data-am-modal-confirm>确定</span>
    </div>
  </div>
</div>

<div class="am-modal am-modal-confirm" tabindex="-1" id="dtip">
  <div class="am-modal-dialog">
    <div class="am-modal-hd">提示</div>
    <div class="am-modal-bd">
             	确定要重置[{{rname}}]的密码吗？
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
<script src="<%=basePath%>js/pub/page.js"></script>
<script src="<%=basePath%>amazeui/js/amazeui.page.min.js"></script>
<script src="<%=basePath%>js/xtgl/user/editUser.js"></script>
</body>
</html>
