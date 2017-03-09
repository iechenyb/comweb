var app = angular.module('app',[]);
app.controller('login',main);
var page ;
var path ;
function main($scope,$q,$http){
	 page = $scope;
	 page.login=login;	 
	 page.user = {};
	 page.user.username="zhangsan";
	 page.user.password="111111";
	 path = $('#path').val();
	 document.onkeydown = function(e){ 
        var ev = document.all ? window.event : e;
        if(ev.keyCode==13) {
           //$('#FormId).submit();//处理事件
           login();
	     }
	};
}
var path;
function login(){
	$('#tip').html("<font>登录信息校验中,请稍候...</font>");
	$.ajax({
		 url:path+"user/login.do?username="+page.user.username+"&password="+page.user.password,
		 contentType: "application/json",
		 async:true,
		 dataType:'json',
		 data:'', 
		 success:function (data){
			if(data.zt=='1'){
				$('#tip').html("<font>登录信息校验成功，登录跳转中...</font>");
				document.getElementById('to').click();
			}else{
				$('#tip').html("<font color=red>"+data.msg+"</font>");
			}
		 }
	 }); 
}

