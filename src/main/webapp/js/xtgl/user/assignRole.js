var app = angular.module('app',[]);
app.controller('editUserRole',main);
var page ;
var path ;
function main($scope,$q,$http,$filter){
	 page = $scope;
	 path = $('#path').val();
	 page.openEditView=openEditView;
	 page.submit = submit;
	 page.selected = selected;
	 page.save = save;
	 $.ajax({
		 url:path+"user/list.do",
		 contentType: "application/json",
		 async:false,
		 data:'',
		 dataType:'json',
		 success:function (data){
			page.users = data;
			page.results = data;
			initPageSpilt(data,page);
		 },
		 complete:function(data){
			 checkAjaxSessionTimeOut(data);
		 }
	 }); 
	 page.refreshPage=function(){
		 $scope.results=$filter("filter")(page.users,{empno:page.zghq,username:page.nameq});
		 initPageSplit($scope.results,page);
	 };
}

function selected(id){
	var checked = false;
	if(page.uRoles!=null){
		for(var i=0;i<page.uRoles.length;i++){
			if(id==page.uRoles[i].roleId){
				checked = true;
				break;
			}
		}
	}
	return checked;
}
function save(){
	var chk_value = [];
	var sids = $("input:checkbox[name='sroles']:checked");
	sids.each(function(){ 
		chk_value.push($(this).val()); 
	}); 
	console.log(chk_value+","+page.userId);
	$.ajax({
		 url:path+"user/saveUserRole.do",
		 contentType: "application/json",
		 type: "get",
		 async:false,
		 data:{roleIds:chk_value,userId:page.userId},
		 dataType:'json',
		 success:function (data){
		     alert(data.msg);
		 },
		 complete:function(data){
			 checkAjaxSessionTimeOut(data);
		 }
	 }); 
	$('#my-popup1').modal("close");
}
function submit(user){
	delete user['$$hashKey'];
	if(page.cmd=='add'){
		$.ajax({
			 url:path+"user/adduser.do",
			 contentType: "application/json",
			 type: "POST",
			 async:false,
			 data:JSON.stringify(page.user),
			 dataType:'json',
			 success:function (data){
				 if(data.zt=='1'){
					page.users.push(data.t);
					initPageSpilt(page.users,page);
				 }
			     alert(data.msg);
			 },
			 complete:function(data){
				 checkAjaxSessionTimeOut(data);
			 }
		 }); 
	}else if(page.cmd=='mod'){
		$.ajax({
			 url:path+"user/upduser.do",
			 contentType: "application/json",
			 async:false,
			 type: "POST",
			 data:JSON.stringify(page.user),
			 dataType:'json',
			 success:function (data){
				 if(data.zt=='1'){
					page.user = {};
				 }
				 alert(data.msg);
			 },
			 complete:function(data){
				 checkAjaxSessionTimeOut(data);
			 }
		 }); 
	}
	page.user = {};
	$('#my-popup1').modal("close");
}
function openEditView(record){
    page.mname = record.username;
	$.ajax({
		 url:path+"user/getUserRole.do?userId="+record.id,
		 contentType: "application/json",
		 async:false,
		 data:'',
		 dataType:'json',
		 success:function (data){
			page.uRoles = data;
		 },
		 complete:function(data){
			 checkAjaxSessionTimeOut(data);
		 }
	 }); 
	$.ajax({
		 url:path+"role/list.do",
		 contentType: "application/json",
		 async:false,
		 data:'',
		 dataType:'json',
		 success:function (data){
			page.roles = data;
		 }
	 }); 
	
	page.userId = record.id; 
	$('#my-popup1').modal({
	    width : $(window).width() * 0.5,
		height : Math.min($(window).height() * 0.8, 480),
	});
}

function orderEvent(){
	  var up = page.direct;
	  page.users.forEach(
		function(item){
			return;
		}	  
	  );
	  if(up=="0"){
		  page.users.sort(
			function(a,b){
				return a[page.order]<b[page.order]?1:-1;
			});
	  }else{
		  page.users.sort(
			function(a,b){
				return a[page.order]>b[page.order]?1:-1;
			});
	  }
}
