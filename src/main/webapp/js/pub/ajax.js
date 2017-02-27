function checkAjaxSessionTimeOut(data){
	try{
     if(data.status==403){
				var msg = JSON.parse(data.responseText);
				if(msg.zt==999){alert(msg.msg);}
	 }else if(data.status==400||data.status==415){
		 alert('请求操作失败！code='+data.status);
	 }
	}catch(e){
	  console.log(e);
	}
}