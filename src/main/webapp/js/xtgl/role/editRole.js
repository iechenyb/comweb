var app = angular.module('app',[]);
app.controller('editRole',main);
var page ;
var path ;
function main($scope,$q,$http,$filter){
	 page = $scope;
	 path = $('#path').val();
	 page.openEditView=openEditView;
	 page.submit = submit;
	 resetList();
	 page.refreshPage=function(){
		 $scope.results=$filter("filter")(page.roles,{rolename:page.name_});
		 initPageSplit($scope.results,page);
	 }
}
function resetList(){
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
}
function submit(role){
	delete role['$$hashKey'];
	if(isEmpty(page.role.rolename,"角色名称")){return false;}
	if(isEmpty(page.role.description,"角色描述")){return false;}
	if(page.cmd=='add'){
		$.ajax({
			 url:path+"role/addRole.do",
			 contentType: "application/json",
			 type: "POST",
			 async:false,
			 data:JSON.stringify(page.role),
			 dataType:'json',
			 success:function (data){
				 if(data.zt=='1'){
					page.roles.push(data.t);
					initPageSpilt(data,page);
					$('#eidtView').modal("close");
				 }
			     alert(data.msg);
			 },
			 complete:function(data){
				 checkAjaxSessionTimeOut(data);
			 }
		 }); 
	}else if(page.cmd=='mod'){
		$.ajax({
			 url:path+"role/updRole.do",
			 contentType: "application/json",
			 async:false,
			 type: "POST",
			 data:JSON.stringify(page.role),
			 dataType:'json',
			 success:function (data){
				 if(data.zt=='1'){
					page.role = {};
					$('#eidtView').modal("close");
				 }
				 alert(data.msg);
			 },
			 complete:function(data){
				 checkAjaxSessionTimeOut(data);
			 }
		 }); 
	}
}
function openEditView(cmd,record){
	page.cmd = cmd;
	if(cmd!='add'){
		page.role = record;
	}else{
		page.role = {};
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
		    width : $(window).width() * 0.5,
			height : Math.min($(window).height() * 0.8, 280),
	});
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
