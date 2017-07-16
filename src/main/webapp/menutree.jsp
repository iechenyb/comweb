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
    	try{
        $("#tree1").ligerTree(
        {   checkbox:false,
        	onSelect: function(node){
                 toPage(node.data);
            },
            url: '<%=basePath%>menu/getUserMenusTree.do', // menu/getUserMenusTree.do
            ajaxType: 'get',
            nodeWidth : 100,
            onBeforeExpand: onBeforeExpand,
            onExpand: onExpand,
            onAfterAppend:collapseAll
        });
    	}catch(e){
    		console.log("err load tree!");
    	}
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
    function onExpand(node)
    { 
    	node.collapseAll();
    }
    function collapseAll(){
    	//manager.collapseAll();
    }
    var path='<%=basePath%>';
    function toPage(node){
    	
     if(node.isleaf==1){	
	   	 $('#page').attr("href",path+node.url);
	   	 document.getElementById("page").click();
   	 }
   }
    </script>
</head>
<body style="padding:10px">  
<div>
<!-- <a class="l-button" style="width:120px; margin-left:10px; float:left;" onclick="f_selectNode()">选择行</a>
 <a class="l-button" style="width:120px; margin-left:10px;float:left;" onclick="f_cancelSelect()">反选择行</a>
  <a class="l-button" style="width:120px; margin-left:10px;float:left;" onclick="showData()">获取选中的值</a> -->
 <a href='#' id='page' target='rightFrame'></a>
</div>
    <div style="width:90%; height:800px; margin:1px; float:left; clear:both; border:0px solid #ccc; overflow:auto;  ">
    <ul id="tree1"></ul>
    </div> 
        <div style="display:none">
                     不用复选框： checkbox: false,
    </div>
</body>
</html>
