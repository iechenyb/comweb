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
<img class="click" src="../../images/t01.png" ng-click="openEditView('add','')" ></img>

<table border=0 class="am-table am-table-bordered imgtable">
<tr align=right>
	<td align='right' style="vertical-align:middle;">文件名称:</td>
	<td align='left'>
	    <input ng-model="nameq" class="am-form-field" ng-init="nameq=''" ng-change="reshPage()">        
	</td>
	<td align='right' style="vertical-align:middle;">文件类型：</td>
	<td align='left'>
	 <select ng-model='tp1' ng-init="tp1=''" class="am-form-field" ng-change="reshPage()">
        <option value=""></option>
        <option value="0">资料</option>
        <option value="1">合同</option>
        <option value="2">软件</option>
	</select>
	</td>
	<td align='right' style="vertical-align:middle;">软件/合同类型：</td>
	<td align='left'>
	  <select ng-model="tp" ng-init="tp=''" class="am-form-field" ng-change="reshPage()">
  		   <option value=""></option> 
		   <option ng-repeat="xx in fileTypes" value="{{xx.id}}" >{{xx.typeName}}</option> 
	  </select>
	</td>
</tr>   
</table>

<table  class="am-table am-table-bordered imgtable">
<thead>
<tr align=center>
<th><center>序号</center></th>
<th ng-click="order='empno';direct=!direct"><center>文件名称
<span ng-class="{false:'am-icon-caret-down',true:'am-icon-caret-up'}[direct==true]" calss="ng-hide am-icon-caret-down" ></span>
</center></th>
<th ng-click="order='isEffect';direct=!direct"><center>文件大小</center></th>
<th ng-click="order='isEffect';direct=!direct"><center>排列顺序</center></th>
<th><center>操作</center></th>
</tr>
</thead>
<tbody>
<tr ng-repeat="vo in results | filter| orderBy:order:direct" 
ng-show="$index+1>=start&&$index+1<=end"
align=center>
<td >{{$index+1}}</td>
<td >{{vo.realName}}</td>
<td >{{vo.size/1000}}K</td>
<td> {{vo.ordor}}</td>
<td align='center'>
<img  class="click"  src="../../images/t02.png" ng-click="openEditView('mod',vo)"/></a>&nbsp;&nbsp;&nbsp; 
<img  class="click" ng-click="del(vo);" src="../../images/t03.png">&nbsp;&nbsp;&nbsp; 
<img  class="click" ng-click="openEditView('preview',vo)" src="../../images/ico06.png" alt="查看答案"/></td>
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
    <form id='dataForm' action="<%=basePath%>xzzx/upload.do" enctype="multipart/form-data" method='post'>
            <input type="hidden"  id='id' name='id' value='{{f.id}}'/>
			<table  class="am-table am-table-bordered">
				<tr>
					<td width="10%" align='right'>文件名称<font color='red' >*</font>:</td>
					<td width="40%">
					<input type="text" style='width:60%;float:left;' id='realName' name="realName" ng-model='f.realName' placeholder=""/>
					</td>
					<td width="10%" align='right'>文件类型<font color='red' >*</font>:</td>
					<td width="40%">
						<div class="am-form-group">
					      <select name='fileType1' id='fileType1' ng-model='f.fileType1' ng-change="type1Change()" style='width:60%;float:left;'>
					        <option value=""></option>
					        <option value="0">资料</option>
					        <option value="1">合同</option>
					        <option value="2">软件</option>
					      </select>
					      <span class="am-form-caret"></span>
					    </div>
					</td>
				</tr> 
				<tr>
					<td width="10%" align='right'>文件<font color='red' >*</font>:</td>
					<td width="40%" align='left'>
						<div class="am-form-group am-form-file">
						  <button type="button" class="am-btn am-btn-danger am-btn-sm">
						    <i class="am-icon-cloud-upload"></i> 选择要上传的文件</button>
						    <input type="file" name='file' id='file'>
						    <span id="file-list"></span>
						</div>
					</td>
					<td width="10%" align='right'>Logo<span ng-show="needLogo"><font color='red'>*</font></span>:</td>
					<td width="40%" align='left'>
					<div class="am-form-group">
				      <div class="am-form-group am-form-file">
						  <button type="button" class="am-btn am-btn-danger am-btn-sm">
						    <i class="am-icon-cloud-upload"></i> 选择要上传的文件</button>
						    <input  type="file" name='logoFile' id='logoFile' >
						    <span id="file-list1"></span>
						    <img src="{{ftpUrl+f.logoSaveName}}" ng-show="cmd=='mod'&&(f.logoSaveName!=null&&f.logoSaveName!='')" width="100" height="65"></img>
						</div>
				    </div>
					</td>
				</tr> 
				<tr>
					<td width="10%" align='right'>公司选择<font color='red' >*</font>:</td>
					 <td  width="40%"  align='left'  colspan=3>
					 <div style='width:80%;float:left;'>
						 <input type="checkbox"  value="1" name='ssgs' ng-checked="selected(f.ssgs,1)"> 香港网站
	        			 <input type="checkbox" value='2'  name='ssgs' ng-checked="selected(f.ssgs,2)"> 期货网站
	        			 <input type="checkbox"  value="3" name='ssgs' ng-checked="selected(f.ssgs,3)"> 金控网站
	        			 <input type="checkbox" value='4'  name='ssgs' ng-checked="selected(f.ssgs,4)"> 香港英文网站
        			 </div>
					</td>
				</tr>
				<tr>
					<td width="10%" align='right'>软件状态<font color='red' >*</font>:</td>
					<td width="40%">
					   <div class="am-form-group">
					      <select  name='softState' id='softState' ng-model='f.softState' style='width:60%;float:left;'>
					        <option value=""></option>
					        <option value="0">最新</option>
					        <option value="1">推荐</option>
					      </select>
					      <span class="am-form-caret"></span>
					    </div>
					</td>
					<td width="10%" align='right'>合同/软件类型<font color='red' >*</font>:</td>
					<td width="40%">
					 <div class="am-form-group">
					    <select style='width:50%;float:left;' name="fileType" id="fileType"  required>
					       <option value=""></option>
						   <option ng-repeat="var0 in fileTypes" value="{{var0.id}}" ng-selected="{{var0.id==f.fileType}}" >{{var0.typeName}}<div style="display:none;"></div></option> 
						</select>
				      </div>
					</td>
				</tr>
				<tr>
					<td width="10%" align='right'>文件种类<font color='red' >*</font>:</td>
					<td width="40%">
					   <div class="am-form-group">
					      <select  name='fileType2' id='fileType2' ng-model='f.fileType2' style='width:60%;float:left;'>
					        <option value=""></option>
					        <option value="0">pdf</option>
					        <option value="1">doc</option>
					        <option value="2">apk</option>
					        <option value="3">xls</option>
					      </select>
					      <span class="am-form-caret"></span>
					    </div>
					</td>
					<td width="10%" align='right'>文件下载名:</td>
					<td width="40%">
					<input type="text" style='width:60%;float:left;'  ng-model='f.downName' name="downName" placeholder="不填，则显示服务器保存的文件名称"/><font color=red>填写时必须附带后缀名</font>
					</td>
				</tr> 
			    <tr>
					<td width="10%" align='right'>MD5码:</td>
					<td width="40%">
					<input type="text" style='width:60%;float:left;'  name='md5'  ng-model='f.md5' placeholder="如不填写，则上传后自动生成MD5码"/>
					</td>
					<td width="10%" align='right'>排列顺序<font color='red' >*</font>:</td>
					<td width="40%">
					<input type="text" style='width:60%;float:left;' id='ordor' name='ordor' ng-model='f.ordor'  placeholder="数字越小，排序越靠前"/>
					</td>
				</tr> 
				<tr>
					<td width="10%" align='right'>文件描述<font color='red' >*</font>:</td>
					<td width="40%" colspan=3 align="left">  
					<!-- <textarea class="" rows="5" id='description' name='description' ng-model='f.description' style='width:83%'></textarea> -->
					<script id="container" name="description"  type="text/plain" style="width:960px;height:100px;"></script>
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
    <form id='dataForm' action="<%=basePath%>xzzx/upload.do" enctype="multipart/form-data" method='post'>
			<table  class="am-table am-table-bordered">
				<tr>
					<td width="10%" align='right'>文件名称:</td>
					<td width="40%">
					<input type="text" readonly style='width:60%;float:left;' id='realName_' name="realName_" ng-model="ff.realName" placeholder=""/>
					</td>
					<td width="10%" align='right'>文件类型:</td>
					<td width="40%">
						<div class="am-form-group">
					      <select disabled name='fileType1_' id='fileType1_' ng-model='ff.fileType1' style='width:60%;float:left;'>
					        <option value=""></option>
					        <option value="0">资料</option>
					        <option value="1">合同</option>
					        <option value="2">软件</option>
					      </select>
					      <span class="am-form-caret"></span>
					    </div>
					</td>
				</tr> 
				<tr>
					<td width="10%" align='right'>文件:</td>
					<td width="40%" align='left'>
						  <a href='<%=basePath%>xzzx/download.do?id={{ff.id}}'>{{ff.realName}}-下载</a>
					</td>
					<td width="10%" align='right'>Logo:</td>
					<td width="40%" align='left'>
					<div class="am-form-group">
				      <div class="am-form-group am-form-file">
						 <img src="{{ftpUrl+ff.logoSaveName}}" width="100" height="80" ng-show="ff.logoSaveName!=null&&ff.logoSaveName!=''"></img>
						 <div ng-show="ff.logoSaveName==null||ff.logoSaveName==''">无</div>
					  </div>
				    </div>
					</td>
				</tr> 
				<tr>
					<td width="10%" align='right'>公司选择:</td>
					 <td  width="40%"  align='left'  colspan=3>
					 <div style='width:80%;float:left;'>
						 <input type="checkbox"  value="1" name='ssgs_' disabled ng-checked="selected(ff.ssgs,1)"> 香港网站
	        			 <input type="checkbox" value='2'  name='ssgs_' disabled ng-checked="selected(ff.ssgs,2)"> 期货网站
	        			 <input type="checkbox"  value="3" name='ssgs_' disabled ng-checked="selected(ff.ssgs,3)"> 金控网站
	        			 <input type="checkbox" value='4'  name='ssgs_' disabled ng-checked="selected(ff.ssgs,1)"> 香港英文网站
        			 </div>
					</td>
				</tr>
				<tr>
					<td width="10%" align='right'>软件状态:</td>
					<td width="40%">
					   <div class="am-form-group">
					      <select  disabled name='softState_' id='softState_' ng-model="ff.softState" style='width:60%;float:left;'>
					        <option value=""></option>
					        <option value="0" ng-selected="ff.softState==0">最新</option>
					        <option value="1" ng-selected="ff.softState==1">推荐</option>
					      </select>
					      <span class="am-form-caret"></span>
					    </div>
					</td>
					<td width="10%" align='right'>合同/软件类型:</td>
					<td width="40%">
					 <div class="am-form-group">
					    <select disabled style='width:50%;float:left;' ng-model="ff.fileType" name="fileType_" id="fileType_"  required>
					       <option value=""></option>
						   <option ng-repeat="var in fileTypes" value="{{var.id}}" ng-selected="var.id==ff.fileType">{{var.typeName}}</option> 
						</select>
				      </div>
					</td>
				</tr>
				<tr>
					<td width="10%" align='right'>文件种类:</td>
					<td width="40%">
					   <div class="am-form-group">
					      <select  disabled name='fileType2_' id='fileType2_' ng-model="ff.fileType2" style='width:60%;float:left;'>
					        <option value=""></option>
					        <option value="0" ng-selected="ff.fileType2==0">pdf</option>
					        <option value="1" ng-selected="ff.fileType2==1">doc</option>
					        <option value="2" ng-selected="ff.fileType2==2">apk</option>
					        <option value="3" ng-selected="ff.fileType2==3">xls</option>
					      </select>
					      <span class="am-form-caret"></span>
					    </div>
					</td>
					<td width="10%" align='right'>文件下载名:</td>
					<td width="40%">
					<input type="text" readonly style='width:60%;float:left;'  name="downName_" ng-model='ff.downName' placeholder="如不填写，则显示服务器保存的文件名"/>
					</td>
				</tr> 
			    <tr>
					<td width="10%" align='right'>MD5码:</td>
					<td width="40%">
					<input type="text" readonly style='width:60%;float:left;'  name='md5_'  ng-model='ff.md5' placeholder="如不填写，则上传后自动生成MD5码"/>
					</td>
					<td width="10%" align='right'>排列顺序:</td>
					<td width="40%">
					<input type="text" readonly style='width:60%;float:left;' id='ordor_' name='ordor_'  ng-model='ff.ordor' placeholder="数字越小，排序越靠前"/>
					</td>
				</tr> 
				<tr>
					<td width="10%" align='right'>文件描述:</td>
					<td width="40%" colspan=3 align="left">  
					  <script id="container1" name="description"  readonly type="text/plain" style="width:960px;height:100px;"></script>
					</td>
				</tr>
				<tr>
					<td colspan=4 align='center'>
					 <button type="button" ng-click="closeView()" class="am-btn am-btn-primary">关闭</button>
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
