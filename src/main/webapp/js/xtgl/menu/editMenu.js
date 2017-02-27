var app = angular.module('app',[]);
app.controller('editmenu',main);
var page ;
var path ;
function main($scope,$q,$http){
	 page = $scope;
	 path = $('#path').val();
	 page.openEditView=openEditView;
	 page.submit = submit;
	 page.delMenu = delMenu;
	 resetList();
}
function resetList(){
	 $.ajax({
		 url:path+"menu/list.do?pid="+$('#pid').val(),
		 contentType: "application/json",
		 async:false,
		 data:'',
		 dataType:'json',
		 success:function (data){
			page.menus = data;
		 },
		 complete:function(data){
			 checkAjaxSessionTimeOut(data);
		 }
	 }); 
}
function submit(menu){
   if(isEmpty(page.menu.menuName,"菜单名称")){return false;}
   if(menu.isLeaf==1){
	   if(isEmpty(page.menu.url,"当前菜单为叶子节点时，菜单路径")){return false;}
   }
   if(!validate(page.menu.ordor,'number',12,null,"排序")){return false;}
	delete menu['$$hashKey'];
	url = path+"menu/addMenu.do";
	if(page.cmd=='mod'){
		url = path+"menu/updMenu.do";
	}
	$.ajax({
		 url:url,
		 contentType: "application/json",
		 async:false,
		 type: "POST",
		 data:JSON.stringify(page.menu),
		 dataType:'json',
		 success:function (data){
			 alert(data.msg);
			 if(data.zt=='1'){
				 if(page.cmd=='add'){
					 page.menus.push(data.t);
				 }
				$('#eidtView').modal("close");
				window.parent.leftFramemenu.location.reload();
			 }
		 },
		 complete:function(data){
			 checkAjaxSessionTimeOut(data);
		 }
	 }); 
	page.menu = {};
}
var pubMenu;
function delMenu(record){
	pubMenu = record;
	$('#tip').modal({
        relatedTarget: this,
        onConfirm: function(options) {
        	$.ajax({
       		 url:path+"menu/delMenu.do?id="+pubMenu.id,
       		 contentType: "application/json",
       		 async:false,
       		 type: "POST",
       		 data:'',
       		 dataType:'json',
       		 success:function (data){
       			 if(data.zt=='1'){
       				 page.menus.splice(page.menus.indexOf(pubMenu),1);
       				 page.$apply();
       				 window.parent.leftFramemenu.location.reload();
       			 }
       			 alert(data.msg);
       		 },
    		 complete:function(data){
    			 checkAjaxSessionTimeOut(data);
    		 }
       	 });
       },
      onCancel: function() {
    }
  });	
}
function openEditView(cmd,record){
	page.cmd = cmd;
	if(cmd!='add'){
		page.menu = record;
	}else{
		page.menu = {isLeaf:0,parentId:$('#pid').val()};
	}
	$('#eidtView').modal({
		onConfirm:function(){
			 if(window.confirm('你确定要取消编辑吗？')){
                resetList();
	            $('#eidtView').modal("close");
                return true;
             }else{
                return false;
            }
		},
		closeOnConfirm:true,
        closeViaDimmer:false,
	    width : $(window).width() * 0.8,
	    height : Math.min($(window).height() * 0.8, 800),
	});
}

function orderEvent(){
	  var up = page.direct;
	  page.menus.forEach(
		function(item){
			return;
		}	  
	  );
	  if(up=="0"){
		  page.menus.sort(
			function(a,b){
				return a[page.order]<b[page.order]?1:-1;
			});
	  }else{
		  page.menus.sort(
			function(a,b){
				return a[page.order]>b[page.order]?1:-1;
			});
	  }
}
