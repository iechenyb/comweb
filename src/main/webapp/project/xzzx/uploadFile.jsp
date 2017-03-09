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
<body ng-controller="editfile">
<input type="hidden" id='id' value="<%=bh%>"></input>
<input type="hidden" id='path' value="<%=basePath%>"></input>
    <form id='dataForm' action="<%=basePath%>xzzx/upload.do" enctype="multipart/form-data" method='post'>
            <input type="hidden"  id='id' name='id' value='{{f.id}}'/>
			<table  class="am-table am-table-bordered">
				<tr>
					<td width="10%" align='right'>新闻标题<font color='red' >*</font>:</td>
					<td width="40%" colspan=3>
					<input type="text" style='width:80%;float:left;' id='realName' 
					name="title" ng-model='f.title' placeholder=""/>
					</td>
				</tr> 
				<tr>
					<td width="10%" align='right'>附件1<font color='red' >*</font>:</td>
					<td width="40%" align='left'>
						<div class="am-form-group am-form-file">
						  <button type="button" class="am-btn am-btn-danger am-btn-sm">
						    <i class="am-icon-cloud-upload"></i> 选择要上传的文件</button>
						    <input type="file" name='file1' id='file1'>
						    <span id="file-list"></span>
						</div>
					</td>
					<td width="10%" align='right'>附件2<span ng-show="needLogo"><font color='red'>*</font></span>:</td>
					<td width="40%" align='left'>
					<div class="am-form-group">
				      <div class="am-form-group am-form-file">
						  <button type="button" class="am-btn am-btn-danger am-btn-sm">
						    <i class="am-icon-cloud-upload"></i> 选择要上传的文件</button>
						    <input  type="file" name='file2' id='file2' >
						    <span id="file-list1"></span>
						    <img src="" ng-show="cmd=='mod'&&(f.logoSaveName!=null&&f.logoSaveName!='')" width="100" height="65"></img>
						</div>
				    </div>
					</td>
				</tr> 
				<tr>
					<td width="10%" align='right'>文件描述<font color='red' >*</font>:</td>
					<td width="40%" colspan=3 align="left">  
					<!-- <textarea class="" rows="5" id='description' name='description' ng-model='f.description' style='width:83%'></textarea> -->
					<script id="container" name="desc"  type="text/plain" style="width:960px;height:300px;"></script>
					</td>
				</tr>
				<tr>
					<td colspan=4 align='center'>
					<input id="bjbtn" type="button"  ng-click="submit()" class="am-btn am-btn-primary" value="提交"></input>
					</td>
				</tr>   
			</table>
		</form>
 <div class="am-popup" style="top:0;left:0;width:100%;height:100%;margin:0;" id="preView">
  <div class="am-popup-inner">
    <div class="am-popup-hd">
      <h4 class="am-popup-title">文件信息预览</h4>
      <span data-am-modal-confirm
            class="am-close"><!-- <b>&times;</b> --></span>
    </div>
    <div class="am-popup-bd" >
       
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
<script src="<%=basePath%>js/xzzx/uploadFile.js"></script>
</body>
</html>
