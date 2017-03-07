angular.module('app', []).controller('login', function($scope,$http) {
    var tdata ;
    var tree;
    var url = $("#path").val()+"menu/getMenuTree1.do";
    $http.get(url).success(function(data) {
	    tdata=data;
	    tree = $('#firstTree').tree({
	        dataSource: function(options, callback) {
	            setTimeout(function() {
	                callback({data: options.products || tdata});
	                tree.tree('discloseAll');
	            }, 400);
	        },
	        multiSelect: false,
	        cacheItems: true,
	        folderSelect: true
	    });
	   
	    tree.on('selected.tree.amui', function (event, data) {
	         node =data.selected[0];
	         if(node.isleaf==0){	
			   	 $('#page').attr("href",$("#path").val()+"project/xtgl/menu/editMenu.jsp?pid="+node.id+"&pname="+node.title);
			   	 $('#page').attr("target","rightFramemenucyb");
			   	 document.getElementById("page").click();
		   	 }else{
		   		 alert('叶子节点不能添加子节点，请点击上级菜单进行修改！');
		   	 }
	    });
    });
});