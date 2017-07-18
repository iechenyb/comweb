function checkAjaxSessionTimeOut(data){
	try{
     if(data.status==403){
    	 //window.location.href="err.jsp"; 
    	 window.location.replace("http://localhost:8080/comweb/denied.jsp");
		var msg = JSON.parse(data.responseText);
		if(msg.zt==999){
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
			retData=data;
		},
		complete : function(data) {
			checkAjaxSessionTimeOut(data);
		}
	});
	return retData;
}
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