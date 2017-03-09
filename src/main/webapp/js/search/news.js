var app = angular.module('app',[]);
app.controller('news',main);
var page ;
var path ;
function main($scope,$q,$http){
	 page = $scope;
	 path = $('#path').val();
	 $.ajax({
		 url:path+"search/news.do?key=",
		 contentType: "application/json",
		 async:false,
		 data:'',
		 dataType:'json',
		 success:function (data){
			page.news = data;
			console.log(page.news);
		 },
		 complete:function(data){
			 checkAjaxSessionTimeOut(data);
		 }
	 }); 
	 $scope.query111=function(){
		 console.log("search!");
		 var title=$("#title").val();
		 $.ajax({
			 url:path+"search/words.do?key="+title,
			 contentType: "application/json",
			 async:false,
			 data:'',
			 dataType:'json',
			 success:function (data){
				page.words = data;
				console.log(page.news);
			 },
			 complete:function(data){
				 checkAjaxSessionTimeOut(data);
			 }
		 }); 
		 $.ajax({
			 url:path+"search/news.do?key="+title,
			 contentType: "application/json",
			 async:false,
			 data:'',
			 dataType:'json',
			 success:function (data){
				page.news = data;
				console.log(page.news);
			 },
			 complete:function(data){
				 checkAjaxSessionTimeOut(data);
			 }
		 }); 
	 }
}
