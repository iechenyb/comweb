<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>东航期货</title>
<link rel="icon" type="image/png" href="<%=basePath%>image/favicon.ico">
</head>
<body>
    <script id="container" name="content" type="text/plain" style="width:100%;height:500px;">
    </script>
    <script src="<%=basePath%>cdn/ueditor.config.js"></script>
    <script src="<%=basePath%>cdn/ueditor.all.min.js"></script>
    <script type="text/javascript">
        var ue = UE.getEditor('container');
    </script>
    <p>
</p>
</body>
</html>