angular.module('app', []).controller('login', function($scope,$http) {
    var tdata ;
    var tree;
    var url = $("#path").val()+"menu/getUserMenusTree1.do";
    $http.get(url).success(function(data) {
	    tdata=data;
	    console.log(tdata);
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
});