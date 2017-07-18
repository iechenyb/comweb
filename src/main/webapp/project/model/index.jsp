<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:include page="../pub/header.jsp"></jsp:include>
<% String version=request.getAttribute("version").toString(); %>
<!doctype html>
<html ng-app="app">
<head>
<base href="<%=request.getAttribute("basePath")%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>模板页面</title>
<link rel="stylesheet" href="amazeui/css/amazeui.min.css">
<link rel="stylesheet" href="amazeui/css/app.css?v=<%=version%>">
<script src="cdn/ueditor.config.js"></script>
<script src="cdn/ueditor.all.min.js"></script>
<script src="cdn/ueditor.parse.min.js"></script>
</head>
<body ng-controller="controller">
	<input type="hidden" id='id' value=""></input>
	<input type="hidden" id='path' value="<%=request.getAttribute("basePath")%>"></input>
	<img class="click" src="images/t01.png"
		ng-click="openEditView('add','')"></img>
	<table border=0 class="am-table am-table-bordered imgtable">
		<tr>
			<td align='right' style="vertical-align: middle; width: 50%">项目1:</td>
			<td align='left'><input ng-model="query" class="am-form-field"
				style="width: 200px;"></td>
		</tr>
	</table>

	<table class="am-table am-table-bordered imgtable">
		<thead align=center>
			<tr>
				<th>序号</th>
				<th ng-click="order='col1';direct=!direct">
						项目1 <span
							ng-class="{false:'am-icon-caret-down',true:'am-icon-caret-up'}[direct==true]"
							calss="ng-hide am-icon-caret-down"></span>
					</th>
				<th ng-click="order='col2';direct=!direct">项目2</th>
				<th ng-click="order='col3';direct=!direct">项目3</th>
				<th ng-click="order='col4';direct=!direct">项目4</th>
				<th ng-click="order='col5';direct=!direct">项目5</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<tr ng-repeat="vo in list | filter:query | orderBy:order:direct" ng-show="$index+1>=start&&$index+1<=end"
				align=center>
				<td ng-bind="$index+1">1</td>
				<td>{{vo.col1}}</td>
				<td>{{vo.col2}}</td>
				<td>{{vo.col3}}</td>
				<td>{{vo.col4}}</td>
				<td>{{vo.col5}}</td>
				<td align='center'><img class="click"
					src="images/t02.png" ng-click="openEditView('mod',vo)" /></a>&nbsp;&nbsp;&nbsp;
					<img class="click" ng-click="del(vo);"
					src="images/t03.png"></td>
			</tr>
		</tbody>
	</table>
	<jsp:include page="/project/pub/page.jsp"></jsp:include>
	<div class="am-modal am-modal-no-btn" tabindex="-1" id="eidtView">
		<div class="am-modal-dialog">
			<div class="am-modal-hd">
				编辑信息 <a href="javascript: void(0)" class="am-close am-close-spin"
					data-am-modal-confirm>&times;</a>
			</div>
			<div class="am-modal-bd">
				<form class="am-form" action='' method='post'>
					<input type="hidden" value="" id="pid" />
					<table class="am-table am-table-bordered">
						<tr>
							<td width="10%" align='right'>项目1:</td>
							<td width="40%"><input type="text" class="" value=""
								style='width: 400px; float: left;' ></td>
							<td width="10%" align='right'>项目2:</td>
							<td width="50%"><input type="text" style='width: 400px'
								class="" ng-model="menu.menuName" id="menuName" name="menuName"
								placeholder="输入提示" /><font color='red'></font></td>
						</tr>
						<tr>
							<td colspan=4 align='center'>
								<button type="button" ng-click='submit(menu)'
									class="am-btn am-btn-primary">提交</button>
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
			<div class="am-modal-bd">确定要删除这条记录吗？</div>
			<div class="am-modal-footer">
				<span class="am-modal-btn" data-am-modal-cancel>取消</span> <span
					class="am-modal-btn" data-am-modal-confirm>确定</span>
			</div>
		</div>
	</div>
	<script src="amazeui/js/jquery.min.js"></script>
	<script src="amazeui/js/amazeui.min.js"></script>
	<script src="amazeui/js/amazeui.page.min.js"></script>
	<script src="angular/angular-1.0.1.min.js"></script>
	<script src="amazeui/js/handlebars.min.js"></script>
	<script src="amazeui/js/amazeui.widgets.helper.js"></script>
	<script src="amazeui/js/amazeui.widgets.helper.min.js"></script>
	<script type="text/javascript" src="js/json2.js"></script>
	<script src="js/pub/ajax.js?v=<%=version%>"></script>
	<script src="js/pub/page.js?v=<%=version%>"></script>
	<script src="js/pub/validate.js?v=<%=version%>"></script>
	<script src="js/model/model.js?v=<%=version%>"></script>
</body>
</html>
