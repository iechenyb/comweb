<%@ page language="java" contentType="text/html; charset=gbk"
	pageEncoding="gbk"%>
<%
	String basePath = "http://" + request.getServerName() + ":"
			+ request.getServerPort() + request.getContextPath() + "/";
	Object username = request.getSession().getAttribute("username");
%>
<!doctype html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<style>
    .dh-footer {
        width: 100%;
        height: 100%;
        margin:0.2rem;
        border:0px red solid;
    }

    .dh-footer p {
        text-align: center;
        margin: 0;
        padding: .1rem;
        font-family : 微软雅黑,宋体;
		font-size : 1em;
		color : '';
    }

	.dh-footer p a:hover {
        color: #f08080;
    }
    .dh-footer a {
        color: white;
        font-size : 1.5rem;
    }
</style>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>amazeui/css/amazeui.min.css">
</head>
<body style="background: url(images/topbg.gif) repeat-x;overflow: hidden;">
	<div class="am-g">
	<div class="dh-footer">
       <p><a>上海市吴中路686号东航金融中心 4008-889-889</a> &nbsp;&nbsp;
        <a href="http://www.miibeian.gov.cn/" target="_blank">沪ICP备14006107号</a>&nbsp;&nbsp;
        <a href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=31011202001150" target="_blank">沪公网安备
            31011202001150号</a><br>
         <a>友情连接：</a><a href="http://localhost/" target="_blank">东航期货网站新版</a>
       </p>
	</div>
	</div>
</body>
</html>
