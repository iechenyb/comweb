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
	 ue1 = UE.getEditor('container1');
	 page.ff={};
	 page.needLogo=false;
	 page.type1Change = function(){
		 if($('#fileType1').val()==2){
		     page.needLogo=true;
		 }else{
		     page.needLogo=false;
		 }
		page.$apply();
	 }
	 //分页栏刷新
	 page.reshPage=function(){
		 $scope.results=$filter("filter")($scope.files,{realName:page.nameq,fileType1:page.tp1,fileType:page.tp});
		 initPageSplit(page.results,page);//总长度
	 }
	 $('#file').on('change', function() {
	      var fileNames = '';
	      $.each(this.files, function() {
	        fileNames += '<span class="am-badge">' + this.name + '</span> ';
	      });
	      $('#file-list').html(fileNames);
	 });
	 $('#logoFile').on('change', function() {
	      var fileNames = '';
	      $.each(this.files, function() {
	        fileNames += '<span class="am-badge">' + this.name + '</span> ';
	      });
	      $('#file-list1').html(fileNames);
	 });
	 $.ajax({
		 url:path+"xzzx/typeList.do",
		 contentType: "application/json",
		 async:false,
		 data:'',
		 dataType:'json',
		 success:function (data){
			page.fileTypes = data;
		 },
		 complete:function(data){
			 checkAjaxSessionTimeOut(data);
		 }
	 }); 
	 resetList(); 
	 $.ajax({
		 url:path+"xzzx/ftp.do",
		 contentType: "application/json",
		 async:false,
		 data:'',
		 dataType:'json',
		 success:function (data){
			page.ftpUrl = data;
		 },
		 complete:function(data){
			 checkAjaxSessionTimeOut(data);
		 }
	 }); 
}
function resetList(){
    $.ajax({
		 url:path+"xzzx/fileList.do",
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
	if(isEmpty($('#fileType1').val(),'文件类型')){return false;}
	if(page.cmd=='add'){
		if(isEmpty($('#file').val(),'文件')){return false;}
		if($('#fileType1').val()==2){
			if(isEmpty($('#logoFile').val(),'Logo')){return false;}
			if(!checkImgType($('#logoFile').val(),'Logo')){return false;}; 
		} 
	}
	var selected = 0;
	$("input[name='ssgs']").each(
		function(){
		 if($(this).is(':checked')){selected ++;}
		}
	); 
	if(selected==0){alert('请选择公司选择项！');return false;}
	if(isEmpty($('#softState').val(),'软件状态')){return false;}
	if(isEmpty($('#fileType').val(),'合同/文件类型')){return false;}
	if(isEmpty($('#fileType2').val(),'文件种类')){return false;}
	if(!validate($('#ordor').val(),"number",200,null,'排列顺序')){ return false;}
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
			 if(data.zt=='1'&&page.cmd=='add'){
				 //window.location.reload();
				 page.files.push(data.t);
				 initPageSplit(page.files,page);
				 page.f = {};
				 $('#bjbtn').attr("disabled",false);
				 $('#bjbtn').attr("value","提交");
			 }else{
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
	    ue.ready(function() {
			 page.ff=record;
			 ue1.setContent(record.description);
			 $("#fileType option[value='"+record.fileType+"']").attr("selected",true);
		});
		/*$.ajax({
      		 url:path+"xzzx/getFile.do?id="+record.id,
      		 contentType: "application/json",
      		 async:false,
      		 type: "POST",
      		 data:'',
      		 dataType:'json',
      		 success:function (data){
      			 page.ff=data;
      			$("input[name='ssgs']").each(
  					function(){
      					if($(this).val()==data.ssgs){
      						$(this).attr("checked","checked");
      					}
  					}
      			); 
      		 },
			 complete:function(data){
				 checkAjaxSessionTimeOut(data);
			 }
      	 });*/
		$('#preView').modal({
		    width : $(window).width() * 0.9,
			height : Math.min($(window).height() * 1, 800),
	});
	}else{
		if(cmd!='add'){
			/*$.ajax({
	      		 url:path+"xzzx/getFile.do?id="+record.id,
	      		 contentType: "application/json",
	      		 async:false,
	      		 type: "POST",
	      		 data:'',
	      		 dataType:'json',
	      		 success:function (data){
	      			 page.f = {};
	      			 page.f=data;
	      			 ue.setContent(data.description);
	      			 $("#fileType option[value='"+data.fileType+"']").attr("selected",true);
	      		 },
				 complete:function(data){
					 checkAjaxSessionTimeOut(data);
				 }
	      	 });*/
	      	 ue.ready(function() {
				page.f = record;
				ue.setContent(record.description);
				$("#fileType option[value='"+record.fileType+"']").attr("selected",true);
			});
		}else{//add 
			page.f = {};
			$('#file-list').html("");
			$('#file-list1').html("");
			ue.setContent("");
			ue1.setContent("");
			$("#fileType option[value='"+record.fileType+"']").attr("selected",false);
			page.f.ssgs="";
			page.$apply();
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
