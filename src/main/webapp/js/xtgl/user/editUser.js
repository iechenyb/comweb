var app = angular.module('app',[]);
app.controller('edituser',main);
var page ;
var path ;
function main($scope,$q,$http,$filter){
	 page = $scope;
	 path = $('#path').val();
	 page.openEditView=openEditView;
	 page.submit = submit;
	 page.resetPwd = resetPwd;
	 page.del = delUser;
	 resetList();
	 page.refreshPage=function(){
		 $scope.results=$filter("filter")(page.users,{empno:page.zghq,username:page.nameq});
		 initPageSplit($scope.results,page);
	 }
}
function resetList(){
	$.ajax({
		 url:path+"user/list.do",
		 contentType: "application/json",
		 async:false,
		 data:'',
		 dataType:'json',
		 success:function (data){
			page.users = data;
			page.results=data;
			initPageSpilt(data,page);
		 },
		 complete:function(data){
			 checkAjaxSessionTimeOut(data);
		 }
	 }); 
}
function submit(user){
	if(isEmpty(page.user.empno,"职工编号")){return false;}
	if(isEmpty(page.user.username,"姓名")){return false;}
	if(page.user.idcard!=''&&page.user.idcard!=undefined){
		if(!validate(page.user.idcard,'idcard',18,null,"身份证")){return false;}
	}
	if(!validate(page.user.phone,'mobile',11,null,"手机号")){return false;}
	if(!validate(page.user.email,'email',100,null,"邮箱")){return false;}
	delete user['$$hashKey'];
	url = path+"user/addUser.do";
	if(page.cmd=='mod'){
		url = path+"user/updUser.do";
	}
	$.ajax({
		 url:url,
		 contentType: "application/json",
		 async:false,
		 type: "POST",
		 data:JSON.stringify(page.user),
		 dataType:'json',
		 success:function (data){
			 if(data.zt=='1'){
				 if(page.cmd=='add'){
					 page.users.push(data.t);
					 initPageSpilt(page.users,page);
				 }
				page.user = {};
				$('#eidtView').modal("close");
			 }
			 alert(data.msg);
		 },
		 complete:function(data){
			 checkAjaxSessionTimeOut(data);
		 }
	 }); 
}
var pubUser;
function delUser(record){
	pubUser = record;
	page.dname = record.username;
	$('#tip').modal({
        relatedTarget: this,
        onConfirm: function(options) {
        	$.ajax({
       		 url:path+"user/delUser.do?id="+pubUser.id,
       		 contentType: "application/json",
       		 async:false,
       		 type: "POST",
       		 data:'',
       		 dataType:'json',
       		 success:function (data){
       			 if(data.zt=='1'){
       				 page.users.splice(page.users.indexOf(record),1);
       				 page.results=page.users;
       				 initPageSplit($scope.results,page);
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
		page.user = record;
	}else{
		page.user = {isEffect:0};
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
			height : Math.min($(window).height() * 0.8, 480),
	});
}
function resetPwd(r){
page.rname = r.username;
$('#dtip').modal({
        relatedTarget: this,
        onConfirm: function(options) {
		 	$.ajax({
				 url:path+"user/updPwd.do",
				 contentType: "application/json",
				 async:false,
				 type: "POST",
				 data:angular.toJson(r),
				 dataType:'json',
				 success:function (data){
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
