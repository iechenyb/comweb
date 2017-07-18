var app = angular.module('app',[]);
app.controller('editfile',main);
var page ;
var path ;
var ue;
var ue1;
function main($scope,$q,$http,$filter){
	 page = $scope;
	 path = $('#path').val();
	 page.openEditView=openEditView;
	 page.submit = submit;
	 page.closeView=closeView;
	 page.selected = selected;
	 page.del = delFile;
	 ue = UE.getEditor('container');
	 page.ff={};
	 page.needLogo=false;
	 //分页栏刷新
	 page.reshPage=function(){
		 $scope.results=$filter("filter")($scope.files,{realName:page.nameq,fileType1:page.tp1,fileType:page.tp});
		 initPageSplit(page.results,page);//总长度
	 }
	 $('#file1').on('change', function() {
	      var fileNames = '';
	      $.each(this.files, function() {
	        fileNames += '<span class="am-badge">' + this.name + '</span> ';
	      });
	      $('#file-list').html(fileNames);
	 });
	 $('#file2').on('change', function() {
	      var fileNames = '';
	      $.each(this.files, function() {
	        fileNames += '<span class="am-badge">' + this.name + '</span> ';
	      });
	      $('#file-list1').html(fileNames);
	 });
	 resetList(); 
}
function resetList(){
    $.ajax({
		 url:path+"xzzx/list.do",
		 contentType: "application/json",
		 async:false,
		 data:'',
		 dataType:'json',
		 success:function (data){
			page.files = data;
			page.results = data;
			initPageSplit(data,page);
		 },
		 complete:function(data){
			 checkAjaxSessionTimeOut(data);
		 }
	 });
}
function selected(key,cur){
	try{
		return key.indexOf(cur)==-1?false:true;
	}catch(e){
		//console.log(e);
		return false;
	}
}
function checkForm(){
	if(!validate($('#realName').val(),"text",200,null,'文件名称')){ return false;}
	if(!validate(ue.getContent(),"text",600,null,'文件描述')){ return false;}
	return true;
}
function submit(){
	if(!checkForm()){return false;}
	$('#bjbtn').attr("disabled",true);
	$('#bjbtn').attr("value","处理中,请稍后...");
	url = path+"xzzx/upload.do";
	page.f.fileType = $('#fileType').val();
	if(page.cmd=='mod'){
		url = path+"xzzx/updFile.do";
	}
	var form = new FormData($('#dataForm')[0]);
	$.ajax({
		 url:url,
		 processData:false,
         contentType:false,
		 async:true,
		 type: "post",
		 data:form,
		 dataType:'json',
		 success:function (data){
			 alert(data.msg);
			 if(data.zt=='1'){
				 document.getElementById("toList").click();
				 page.files.push(data.t);
				 initPageSplit(page.files,page);
				 page.f = {};
				 $('#bjbtn').attr("disabled",false);
				 $('#bjbtn').attr("value","提交");
			 }
			 $('#eidtView').modal("close");
		 },
		 complete:function(data){
			 checkAjaxSessionTimeOut(data);
			 $('#bjbtn').attr("disabled",false);
			 $('#bjbtn').attr("value","提交");
		 }
	 }); 
}
function closeView(){
	$('#preView').modal("close");
}
var pubFile;
function delFile(record){
	pubFile = record;
	$('#tip').modal({
        relatedTarget: this,
        onConfirm: function(options) {
        	$.ajax({
       		 url:path+"xzzx/delFile.do?id="+pubFile.id,
       		 contentType: "application/json",
       		 async:false,
       		 type: "POST",
       		 data:'',
       		 dataType:'json',
       		 success:function (data){
       			 if(data.zt=='1'){
       				 page.files.splice(page.files.indexOf(record),1);
       				 page.results=page.files;
       				 initPageSplit(page.results,page);
       			 }
       			 alert(data.msg);
       			 page.$apply();
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
	$('#bjbtn').attr("disabled",false);
	$('#bjbtn').attr("value","提交");
	page.cmd = cmd;
	if(cmd=='preview'){
		/*ue1 = UE.getEditor('container1');
	    ue1.ready(function() {
			 ue1.setContent(record.content);
		});*/
		page.pre = record;
		$("#con").html(page.pre.content);
		$('#preView').modal({
		    width : $(window).width() * 0.9,
			height : Math.min($(window).height() * 1, 800),
	});
	}else{
		if(cmd!='add'){
	      	 /*ue.ready(function() {
				ue.setContent(record.description);
			  });*/
		}else{//add 
			page.f = {};
			$('#file-list').html("");
			$('#file-list1').html("");
			ue.setContent("");
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
			    width : $(window).width() * 0.9,
				height : Math.min($(window).height() * 0.8, 600),
		});
	}
}

function orderEvent(){
	  var up = page.direct;
	  page.files.forEach(
		function(item){
			return;
		}	  
	  );
	  if(up=="0"){
		  page.files.sort(
			function(a,b){
				return a[page.order]<b[page.order]?1:-1;
			});
	  }else{
		  page.files.sort(
			function(a,b){
				return a[page.order]>b[page.order]?1:-1;
			});
	  }
}
