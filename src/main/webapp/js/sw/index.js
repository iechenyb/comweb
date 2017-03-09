var app = angular.module('app',[]);
app.controller('zhs',main);
var page ;
var path ;
function main($scope,$q,$http){
	 page = $scope;
	 path = $('#path').val();
	 $.ajax({
		 url:path+"sw/show.do?key=",
		 contentType: "application/json",
		 async:false,
		 data:'',
		 dataType:'json',
		 success:function (data){
			page.zhs = data;
		 },
		 complete:function(data){
			 checkAjaxSessionTimeOut(data);
		 }
	 }); 
	 $scope.query111=function(ex){
		 $.ajax({
			 url:path+"sw/testSw.do?ex="+ex,
			 contentType: "application/json",
			 async:false,
			 data:'',
			 dataType:'json',
			 success:function (data){
				  alert(data.msg);
				  page.zhs = data.data;
			 },
			 complete:function(data){
				 checkAjaxSessionTimeOut(data);
			 }
		 }); 
		 $.ajax({
			 url:path+"sw/show.do?key=",
			 contentType: "application/json",
			 async:false,
			 data:'',
			 dataType:'json',
			 success:function (data){
				page.zhs = data;
			 },
			 complete:function(data){
				 checkAjaxSessionTimeOut(data);
			 }
		 }); 
	 }
}
