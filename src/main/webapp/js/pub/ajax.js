/**
 * ajax请求完成校验
 * @param data
 */
function checkAjaxSessionTimeOut(data){
	try{
	     if(data.status==403){
	    	 //spring 安全框架检查无访问权限时，跳转到禁止访问页面。
	    	window.location.replace("http://localhost:8080/comweb/denied.jsp");
			var msg = JSON.parse(data.responseText);
			if(msg.zt==999){//会话过期
				alert(msg.msg);
			}
		 }else if(data.status==400||data.status==415){
			 alert('请求操作失败！code='+data.status);
		 }
	}catch(e){
	  console.log(e);
	}
}
/**
 * 
 * @param url=http://ip:port/projectname/uri?param1=1&param2=2
 */
function ajaxGet(url){
	var retData;
	$.ajax({
		url : url,
		contentType : "application/json",
		async : false,
		data : '',
		dataType : 'json',
		success : function(data) {
			console.log("ajax response:"+data)
			retData=data;
		},
		complete : function(data) {
			checkAjaxSessionTimeOut(data);
		},error:function (e){
			console.log(url+" ajax error!"+e);
		},beforeSend :function(xmlHttp){ 
			 xmlHttp.setRequestHeader("If-Modified-Since","0"); 
			 xmlHttp.setRequestHeader("Cache-Control","no-cache"); 
		}
	});
	return retData;
}
/**
 * 
 * @param url
 * @param jsonObj json对象参数
 * @returns
 */
function ajaxPostJson(url,jsonObj){
	var retData;
	$.ajax({
		url : url,
		contentType : "application/json",
		async : false,
		type : "POST",
		data : JSON.stringify(jsonObj),
		dataType : 'json',
		success : function(data) {
			retData = data;
		},
		complete : function(data) {
			checkAjaxSessionTimeOut(data);
		},error:function (e){
			console.log(url+" ajax error!"+e);
		},beforeSend :function(xmlHttp){ 
			 xmlHttp.setRequestHeader("If-Modified-Since","0"); 
			 xmlHttp.setRequestHeader("Cache-Control","no-cache"); 
		}
	});
	return retData;
}
/**
 * 
 * @param url 
 * @param formData = new FormData($('#dataForm')[0]);
 * @returns
 */
function ajaxPostFormData(url,formData){
	var retData;
	$.ajax({
		 url:url,
		 processData:false,
         contentType:false,
		 async:true,
		 type: "post",
		 data:form,
		 dataType:'json',
		 success : function(data) {
			retData = data;
		},
		complete : function(data) {
			checkAjaxSessionTimeOut(data);
		},error:function (e){
			console.log(url+" ajax error!"+e);
		},beforeSend :function(xmlHttp){ 
			 xmlHttp.setRequestHeader("If-Modified-Since","0"); 
			 xmlHttp.setRequestHeader("Cache-Control","no-cache"); 
		}
	});
	return retData;
}
//检验是否是合法请求
function isLawFullRet(data){
	alert(data.msg);
	if(data.zt=='1'){
		return true;
	}else{
		return false;
	}
}
