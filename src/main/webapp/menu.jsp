<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%  
String path=application.getRealPath(request.getRequestURI());  
String basePath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";  
%> 
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link rel="stylesheet" href="<%=basePath%>amazeui/css/amazeui.min.css">
<link rel="stylesheet" href="<%=basePath%>amazeui/css/app.css">
</head>
<body>
参数：<%=request.getParameter("pid")%>
<form class="am-form" action='../cdgl/add.cs' method='post'>
<input type="hidden" value="<%=request.getParameter("pid")%>" name="pid"/>
<table  class="am-table am-table-bordered">
<tr>
	<td width="10%" align='right'>上级菜单:</td><td width="40%"><input type="text" class="" id=""  style='width:400px;float:left;' disabled placeholder="上级菜单名称，不可编辑"></td>
	<td width="10%" align='right'>菜单名称:</td><td  width="50%"><input type="text" style='width:400px' class="" id="menuName" name="menuName" placeholder="菜单名称"/><font color='red' ></font></td>
</tr>
<tr>
	<td width="10%" align='right'>菜单路径:</td>
	<td>
     <input type="text"  class="" id='url' name="url" placeholder="http://ip:port/webName/?">
     <font color='red' ></font>
	</td>
	<td width="10%" align='right'>菜单标志:</td>
	<td>
     <input type="text"  class="" id='menubs' name="menuBs" placeholder="菜单首字母"  style="width:400px;"/>
     <font color='red' ></font>
	</td>
</tr>   
<tr>
	<td width="10%" align='right'>是否叶子:</td><td width="40%"> 
	  <label class="am-radio-inline">
        <input type="radio"  value="1" name="isLeaf"> 是
      </label>
      <label class="am-radio-inline">
        <input type="radio" value='0' name="isLeaf"> 否
      </label><font color='red' size=1>&nbsp;&nbsp;设置为叶子节点则不能再添加子节点！</font></td>
	<td width="10%" align='right'>菜单级别:</td>
	<td  width="50%"> 
      <select id="level" name='level' style='width:400px'>
        <option value="1">一级</option>
        <option value="2">二级</option>
        <option value="3">三级</option>
      </select>
    </td>
</tr> 
<tr>
	<td width="10%" align='right'>备注:</td>
	<td colspan=3>
      <textarea class="" rows="5" name="bz" style='width:700'></textarea>
	</td>
</tr>   
<tr>
	<td colspan=4 align='center'>
	<button type="submit" class="am-btn am-btn-primary">提交</button>
	</td>
</tr>   
</table>
</form>
</body>
<!--[if (gte IE 9)|!(IE)]><!-->
<script src="<%=basePath%>amazeui/js/jquery.min.js"></script>
<!--<![endif]-->
<!--[if lte IE 8 ]>
<script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="assets/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->
<script src="<%=basePath%>amazeui/js/amazeui.min.js"></script>
</html>
