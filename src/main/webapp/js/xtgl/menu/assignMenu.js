var app = angular.module('app',[]);
app.controller('editRole',main);
var page ;
var path ;
function onBeforeExpand(note)
{
    if (note.data.children && note.data.children.length == 0)
    {
        manager.append(note.target, []);
    }
}
function onExpand(note){ }
var manager ;
var tree;
function main($scope,$q,$http,$filter){
	 page = $scope;
	 path = $('#path').val();
	 page.openEditView=openEditView;
	 page.save=save;
	 $.ajax({
		 url:path+"role/list.do",
		 contentType: "application/json",
		 async:false,
		 data:'',
		 dataType:'json',
		 success:function (data){
			page.roles = data;
			page.results=data;
			initPageSpilt(data,page);
		 },
		 complete:function(data){
			 checkAjaxSessionTimeOut(data);
		 }
	 });
	  page.refreshPage=function(){
		 $scope.results=$filter("filter")(page.roles,{rolename:page.name_});
		 initPageSplit($scope.results,page);
	 } 
}
function f_selectNode0() {
    var parm0 = function(data) {
        return false;
    };
    tree.selectNode(parm0);
}
function f_selectNode(arrIds) {
    var parm = function(data) {
    	var selected = false;
    	for(var i=0;i<arrIds.length;i++){
	        if (data.id==arrIds[i])
	        { 
	        	selected =  true; 
	        } 
        }
    	return selected;
    };
    tree.selectNode(parm);
}
function save(){
	var selectedIds = [];
	var menus = manager.getChecked();
	for(var i=0;i<menus.length;i++){
		//选择叶子节点
		if(menus[i].data.isleaf=="1"||(menus[i].data.isleaf=="0"&&menus[i].data.children.length==0)){
		  selectedIds.push(menus[i].data.id);
		}
	}  
	$.ajax({
		 url:path+"menu/saveRoleMenu.do",
		 contentType: "application/json",
		 type: "get",
		 async:false,
		 data:{leafIds:selectedIds,roleId:page.roleId},
		 dataType:'json',
		 success:function (data){
		     alert(data.msg);
		 },
		 complete:function(data){
			 checkAjaxSessionTimeOut(data);
		 }
	 }); 
	page.roleId = '';
	window.location.reload();
	//$('#my-popup1').modal('close');
}

function openEditView(record){
	var selected = [];
	page.name = record.rolename;
	$.ajax({
		 url:path+"menu/getRoleMenu.do?roleId="+record.id,
		 contentType: "application/json",
		 async:true,
		 type: "POST",
		 data:'',
		 dataType:'json',
		 success:function (data){
			 selected = data;
		 },
		 complete:function(data){
			 checkAjaxSessionTimeOut(data);
		 }
	 }); 
	page.roleId = record.id ; 
	var times = 0;
	tree = $("#tree1").ligerTree(
	        {   checkbox:true,
	        	onSelect: function(node){},
	            url: path+'menu/getMenuTree.do',
	            ajaxType: 'get',
	            nodeWidth : 320,
	            onBeforeExpand: onBeforeExpand,
	            onExpand: onExpand,
	            parentIcon: 'folder', 
	            childIcon: 'leaf' ,
	            onSuccess: function(data) {
	            	if(times==0){
		            	f_selectNode0();
		            	f_selectNode(selected);
	            	}
	            	times ++;
	            },
	   		 complete:function(data){
				 checkAjaxSessionTimeOut(data);
			 }
	      });
	manager = $("#tree1").ligerGetTreeManager();
	/*$('#my-popup1').modal({
	    width : $(window).width() * 0.5,
		height : Math.min($(window).height() * 0.8, 480),
	});*/
	$('#showTree').click();	
}

function orderEvent(){
	  var up = page.direct;
	  page.roles.forEach(
		function(item){
			return;
		}	  
	  );
	  if(up=="0"){
		  page.roles.sort(
			function(a,b){
				return a[page.order]<b[page.order]?1:-1;
			});
	  }else{
		  page.roles.sort(
			function(a,b){
				return a[page.order]>b[page.order]?1:-1;
			});
	  }
}
