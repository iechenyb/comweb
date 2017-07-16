<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<jsp:include page="../pub/header.jsp"></jsp:include>
<% 
String bh = request.getParameter("bh");
String id = request.getParameter("id");
%>
<!doctype html>
<html ng-app="app">
<head>
<base href="<%=request.getAttribute("basePath")%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>编辑子菜单</title>
<link rel="stylesheet" href="amazeui/css/amazeui.min.css">
<link rel="stylesheet" href="amazeui/css/app.css">
<script src="cdn/ueditor.config.js"></script>
<script src="cdn/ueditor.all.min.js"></script>
<script src="cdn/ueditor.parse.min.js"></script>
</head>
<body ng-controller="editfile">
<input type="hidden" id='id' value="<%=bh%>"></input>
<input type="hidden" id='path' value=""></input>
<!-- <img class="click" src="../../images/t01.png" ng-click="openEditView('add','')" ></img> -->

<table border=0 class="am-table am-table-bordered imgtable">
<tr align=right>
	<td align='right' style="vertical-align:middle;">文件名称:</td>
	<td align='left'>
	    <input ng-model="nameq" class="am-form-field" ng-init="nameq=''" ng-change="reshPage()">        
	</td>
</tr>   
</table>

<table  class="am-table am-table-bordered imgtable">
<thead>
<tr align=center>
<th><center>序号</center></th>
<th ng-click="order='empno';direct=!direct"><center>新闻标题
<span ng-class="{false:'am-icon-caret-down',true:'am-icon-caret-up'}[direct==true]" calss="ng-hide am-icon-caret-down" ></span>
</center></th>
<th ng-click="order='isEffect';direct=!direct"><center>内容大小</center></th>
<th ng-click="order='isEffect';direct=!direct"><center>附件名称</center></th>
<th><center>操作</center></th>
</tr>
</thead>
<tbody>
<tr ng-repeat="vo in results | filter| orderBy:order:direct" 
ng-show="$index+1>=start&&$index+1<=end"
align=center>
<td >{{$index+1}}</td>
<td ><a href="xzzx/download.do?id={{vo.id}}">{{vo.title}}</a></td>
<td >{{vo.size/1000}}K</td>
<td> {{vo.fjname}}</td>
<td align='center'>
<!-- <img  class="click"  src="../../images/t02.png" ng-click="openEditView('mod',vo)"/></a>&nbsp;&nbsp;&nbsp; 
<img  class="click" ng-click="del(vo);" src="../../images/t03.png">&nbsp;&nbsp;&nbsp; --> 
<img  class="click" ng-click="openEditView('preview',vo)" src="images/ico06.png" alt="查看答案"/></td>
</tr>
</tbody>
</table>
<jsp:include page="../pub/page.jsp"></jsp:include>
 <div class="am-popup" style="top:0;left:0;width:100%;height:100%;margin:0;" id="eidtView">
  <div class="am-popup-inner">
    <div class="am-popup-hd">
      <h4 class="am-popup-title">上传文件</h4>
      <span data-am-modal-confirm
            class="am-close"><b>&times;</b></span>
    </div>
    <div class="am-popup-bd" >
    <form id='dataForm' action="xzzx/upload.do" enctype="multipart/form-data" method='post'>
            <input type="hidden"  id='id' name='id' value='{{f.id}}'/>
			<table  class="am-table am-table-bordered">
				<tr>
					<td width="10%" align='right'>新闻标题<font color='red' >*</font>:</td>
					<td width="40%" colspan=3>
					<input type="text" style='width:80%;float:left;' id='realName' 
					name="realName" ng-model='f.title' placeholder=""/>
					</td>
				</tr> 
				<tr>
					<td width="10%" align='right'>附件1<font color='red' >*</font>:</td>
					<td width="40%" align='left'>
						<div class="am-form-group am-form-file">
						  <button type="button" class="am-btn am-btn-danger am-btn-sm">
						    <i class="am-icon-cloud-upload"></i> 选择要上传的文件</button>
						    <input type="file" name='file' id='file'>
						    <span id="file-list"></span>
						</div>
					</td>
					<td width="10%" align='right'>附件2<span ng-show="needLogo"><font color='red'>*</font></span>:</td>
					<td width="40%" align='left'>
					<div class="am-form-group">
				      <div class="am-form-group am-form-file">
						  <button type="button" class="am-btn am-btn-danger am-btn-sm">
						    <i class="am-icon-cloud-upload"></i> 选择要上传的文件</button>
						    <input  type="file" name='logoFile' id='logoFile' >
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
    </div>
  </div>
</div> 

 <div class="am-popup" style="top:0;left:0;width:100%;height:100%;margin:0;" id="preView">
  <div class="am-popup-inner">
    <div class="am-popup-hd">
      <h4 class="am-popup-title">文件信息预览</h4>
      <span data-am-modal-confirm
            class="am-close"><!-- <b>&times;</b> --></span>
    </div>
    <div class="am-popup-bd" >
       <!-- <script id="container1" type="text/plain" style="width:960px;height:300px;"></script> -->
       <div id="con"></div>
       <span class="am-modal-btn" ng-click="closeView()">返回</span>
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
<script src="amazeui/js/jquery.min.js"></script>
<script src="amazeui/js/amazeui.min.js"></script>
<script src="amazeui/js/amazeui.page.min.js"></script>
<script src="angular/angular-1.0.1.min.js"></script>
<script src="amazeui/js/handlebars.min.js"></script>
<script src="amazeui/js/amazeui.widgets.helper.js"></script>
<script src="amazeui/js/amazeui.widgets.helper.min.js"></script>
<script type="text/javascript" src="js/json2.js"></script>
<script src="js/pub/ajax.js"></script>
<script src="js/pub/validate.js"></script>
<script src="js/pub/page.js"></script>
<script src="js/xzzx/uploadFile.js"></script>
</body>
</html>
