angular.module('app', []).controller('login', function($scope,$http) {
    var tdata ;
    var tree;
    staticMenu = false;//是否读取数据库菜单数据信息，根节点不用保存
    var url ="";
    if(!staticMenu){}else{}
    url1 = $("#path").val()+"menu/getUserMenusTree1.do";
    url2 = $("#path").val()+"restfull/user/userMenu.json";
    $http.get(url1).success(function(data) {
    	
	    tdata=data;
	    if(data.zt==0){
	    	console.log("系统尚未初始化菜单！");
	    	 $http.get(url2).success(function(data) {
	    		    tdata = data; 
	    		    tree = $('#firstTree').tree({
	    		        dataSource: function(options, callback) {
	    		            setTimeout(function() {
	    		                callback({data: options.products || tdata});
	    		            }, 400);
	    		        },
	    		        multiSelect: false,
	    		        cacheItems: true,
	    		        folderSelect: false
	    		    });
	    		    tree.on('selected.tree.amui', function (event, data) {
	    		        console.log("treenodename="+data.selected[0].url);
	    		        node =data.selected[0];
	    		        if(node.isleaf==1){	
	    			   	    $('#page').attr("href",$("#path").val()+node.url);
	    			   	    document.getElementById("page").click();
	    		   	    }
	    		    });
	    	 });
	    }else{
		    tree = $('#firstTree').tree({
		        dataSource: function(options, callback) {
		            setTimeout(function() {
		                callback({data: options.products || tdata});
		            }, 400);
		        },
		        multiSelect: false,
		        cacheItems: true,
		        folderSelect: false
		    });
		    tree.on('selected.tree.amui', function (event, data) {
		        console.log("treenodename="+data.selected[0].url);
		        node =data.selected[0];
		        if(node.isleaf==1){	
			   	    $('#page').attr("href",$("#path").val()+node.url);
			   	    document.getElementById("page").click();
		   	    }
		    });
	    }	    
    }).error(function(data,header,config,status){
    	//处理响应失败
    	var obj = {};
    	obj.status = header;
    	checkAjaxSessionTimeOut(obj);
    });;
});