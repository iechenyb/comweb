<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%  
String basePath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";  
%> 
<!doctype html>
<html>
<head>
    <title></title>
    <script src="<%=basePath%>liger/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
    <link href="<%=basePath%>liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <script src="<%=basePath%>liger/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="<%=basePath%>liger/lib/ligerUI/js/plugins/ligerTree.js" type="text/javascript"></script>
    <script type="text/javascript">
    var manager = null;

    $(".l-tree .l-body span").live('mouseover', function (e) {
        if (!this.title) 
            this.title = $(this).text();
    });

    $(function ()
    {
        $("#tree1").ligerTree(
        {   checkbox:false,
        	onSelect: function(node){
                 toPage(node.data);
            },
            url: '<%=basePath%>menu.json',//menu/getMenuTree.do
            ajaxType: 'get',
            nodeWidth : 100,
            onBeforeExpand: onBeforeExpand,
            onExpand: onExpand,
            parentIcon: 'folder', 
            childIcon: 'leaf' 
        });
        manager = $("#tree1").ligerGetTreeManager();
    });
 
    function onBeforeExpand(note)
    {
        if (note.data.children && note.data.children.length == 0)
        {
            //这里模拟一个加载节点的方法，append方法也用loadData(target,url)代替
            manager.append(note.target, []);
        }
    }
    function onExpand(note)
    { 
    	
    }
    var path='<%=basePath%>';
    function toPage(node){
     console.log(node);
     if(node.isleaf==0){	
	   	 $('#page').attr("href",path+"project/xtgl/menu/editMenu.jsp?pid="+node.id+"&pname="+node.text);
	   	 document.getElementById("page").click();
   	 }else{
   		 alert('叶子节点不能添加子节点，请点击上级菜单进行修改！');
   	 }
   }
    </script>
</head>
<body style="padding:10px">  
<div>
 <a href='#' id='page' target='rightFramemenu'></a>
</div>
    <div style="width:100%; height:800px; margin:1px; float:left; clear:both; border:0px solid #ccc; overflow:auto; ">
    <ul id="tree1" style="width:300px"></ul>
    </div> 
        <div style="display:none">
                     不用复选框： checkbox: false,
    </div>
</body>
</html>
