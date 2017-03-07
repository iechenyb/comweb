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
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>amazeui/css/amazeui.min.css">
<link rel="stylesheet" href="<%=basePath%>cdn/amazeui.tree.css">
<script language="JavaScript" src="js/jquery.js"></script>
<script type="text/javascript">
$(function(){	
	//顶部导航切换
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	
</script>
<script type="text/javascript">  
    //获取系统时间，将时间以指定格式显示到页面。  
    function systemTime()  
    {  
        //获取系统时间。  
        var dateTime=new Date();  
        var hh=dateTime.getHours();  
        var mm=dateTime.getMinutes();  
        var ss=dateTime.getSeconds();  
          
        //分秒时间是一位数字，在数字前补0。  
        mm = extra(mm);  
        ss = extra(ss);  
          
        //将时间显示到ID为time的位置，时间格式形如：19:18:02  
        document.getElementById("time").innerHTML=hh+":"+mm+":"+ss;  
          
        //每隔1000ms执行方法systemTime()。  
        setTimeout("systemTime()",1000);  
    }  
      
    //补位函数。  
    function extra(x)  
    {  
        //如果传入数字小于10，数字前补一位0。  
        if(x < 10)  
        {  
            return "0" + x;  
        }  
        else  
        {  
            return x;  
        }  
    }  
</script>

</head>

<body style="background: url(images/topbg.gif) repeat-x;background-size:cover;overflow: hidden;">
<div class="am-g">
	<div class="topleft">
		<!-- <a href="index.jsp" target="_parent"></a> -->
		<!-- <img src="images/logo.png" title="系统首页" />  -->
		后台管理系统
	</div>
	<div class="topright">
	<div>
		<ul>
			<li><span><img src="images/help.png" title="帮助"
					class="helpimg" /></span><a href="#">帮助</a></li>
			<li><a href="#">关于</a></li>
			<li><a href="#" onclick='top.location.replace("user/exit.do")'>退出</a></li>
		</ul>
		</div>
		<div class="user">
			<span><%=username%></span>
		</div>
	</div>
<div class="am-g">
<script type="text/javascript">
  function exit(obj){
	 obj.href= '<%=basePath%>user/exit.do';
			obj.target = window.top;
			obj.click();
  }
</script>
</body>
</html>
