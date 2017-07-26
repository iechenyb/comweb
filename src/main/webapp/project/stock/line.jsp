<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:include page="../pub/header.jsp"></jsp:include>
<% String version=request.getAttribute("version").toString(); %>
<!doctype html>
<html ng-app="app">
<head>
<base href="<%=request.getAttribute("basePath")%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>分钟行情</title>
<link rel="stylesheet" href="amazeui/css/amazeui.min.css">
<link rel="stylesheet" href="amazeui/css/app.css?v=<%=version%>">
<style>
</style>
</head>
<body ng-controller="controller">
	  <input type="hidden"  id='path' value="<%=request.getAttribute("basePath")%>"></input>
	  
	  <div class="am-g am-g-fixed" style="text-align:center">
	     <div class="am-u-sm-12">
		  	<input type="text" id='code' placeholder="输入股票代码" style="width:50%;float:left;" class="am-form-field am-radius" ng-model='codeName' value="sh600868"/>
		  	<button  type="button"  class="am-btn am-btn-primary"  style="width:20%;float:left;" data-am-modal="{target: '#my-popup'}">选择股票</button>
		  	<button ng-click='load()' class="am-form-field am-radius" style="width:30%" >查询</button>
	 	 </div>
	  </div> 
	  <div class="am-g am-g-fixed">
	      <div id="lineChart" class="am-u-sm-6" style="height:400px;width:50%;"></div>
	      <div id="lineChart1" class="am-u-sm-6" style="height:400px;width:50%;"></div>
	  </div>
	  
<div class="am-popup" id="my-popup" style="top:0;left:0;width:100%;margin:0;height:100%;">
  <div class="am-popup-inner">
    <div class="am-popup-hd">
      <h4 class="am-popup-title">选择股票</h4>
      <span data-am-modal-close
            class="am-close">&times;</span>
    </div>
    <div class="am-popup-bd">
       <div class="am-g">
        <div class="am-u-sm-12" style="text-align:center;"><b>上海证券交易所</b></div>
	      <div class="am-u-sm-2" ng-repeat="vo in shs">
		      <div class="am-radio" ng-click="setCode(vo);">
			      <label>
			        <input type="radio" name="doc-radio-1" >{{vo.name}}
			      </label>
	           </div>
	      </div>
	  </div>
	   <div class="am-g">
	     <div class="am-u-sm-12" style="text-align:center;"><b>深圳证券交易所</b></div>
	      <div class="am-u-sm-2" ng-repeat="vo in szs">
		      <div class="am-radio" ng-click="setCode(vo);">
			      <label>
			        <input type="radio" name="doc-radio-2" >{{vo.name}}
			      </label>
	           </div>
	      </div>
	  </div>
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
	<script src="echarts/echarts-all.js"></script>
	<script src="js/pub/ajax.js?v=<%=version%>"></script>
	<script src="js/pub/page.js?v=<%=version%>"></script>
	<script src="js/pub/validate.js?v=<%=version%>"></script>
	<script src="js/stock/line.js?v=<%=version%>"></script>
</body>
</html>
