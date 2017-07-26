/**
 * Created by DHUser on 2016/10/24.
 */

/**
 * @author  iechenyb
 * @param type 校验类型
 * @param length  文本长度
 * @param express 正则表达式
 */
function  validate(value,type,length,express,name){
    if(type=='text'||type==0){
        return text(value,length,express,name);
    }
    if(type=='mobile'||type==1){
        return mobile(value,11,express,name);
    }
    if(type=='telphone'||type==2){
        return telphone(value,length,express,name);
    }
    if(type=='email'||type==3){
        return email(value,length,express,name);
    }
    if(type=='hanzi'||type==4){
        return hanzi(value,length,express,name);
    }
    if(type=='number'||type==5){
        return num(value,length,express,name);
    }
    if(type=='idcard'||type==6){
        return idcard(value,length,express,name);
    }
}

function idcard(value,length,express,name){
    var express = /(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)/;
    return len(value,length,name)&&exp(value,express,name);
}

function hanzi(value,length,express,name){
    var express = /^[\\u4e00-\\u9fa5]+$/;
    return commValidate(value,length,express,name);
}
function num(value,length,express,name){
	if(isEmpty(value,name)){
		return false;
	}else{
		if(!isNumber(value,name)){
			return false;
		}
	}
    return true;
}
function text(value,length,express,name){
    return commValidate(value,length,express,name);
}
function mobile(value,length,express,name){
    var express = /^1(3|4|5|7|8)\d{9}$/;
    if(isEmpty(value,name)){
		return false;
	}else{
		if(!express.test(value)){
			alert(name+"必须为长度为11的有效数字！");
			return false;
		}
	}
    return true;
}
function telphone(value,length,express,name){
    return true;
}
function email(value,length,express,name){
    var express = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
    return commValidate(value,length,express,name);
}

function commValidate(value,length,express,name){
    if(isEmpty(value,name)){
        return false;
    }else{
        if(!len(value,length,name)){
            return false;
        }else if(!exp(value,express,name)){
            return false;
        }
    }
    return true;
}
function isEmpty(value,name){
    if(typeof value=="undefined"||value==null||value=='null'||value==''){
        alert(name+"不能为空！");
        return true;
    }
    return false;
}
function len(value,length,name){
    if(value.length>length){
        alert(name+'超出最大长度'+length+"!");
        return false;
    }
    return true;
}
function exp(value,express,name){
    if(express!=null&&express!=''){
        var check = express.test(value);
        if(!check){
            alert(name+"格式有误！");
            return false;
        }
    }
    return true;
}
function isNumber(_str,name){
    var tmp_str = _str;
    var pattern = /^[0-9]+$/;
    if(!pattern.test(tmp_str)){
	   alert(name+"必须为整数！")
	   return false;
    }
    return true ;
}
/*
 * 判断是否浮点数
 * @param oNum
 * @return true-符合要求,false-不符合
 */
function isFloat(oNum,name){
	 var temp = true;
	 if(!oNum) temp = false;
	 var strP=/^\d+(\.\d+)?$/;
	 if(!strP.test(oNum)) temp = false;
	 try{
	  if(parseFloat(oNum)!=oNum) temp = false;
	 }catch(ex){
	   temp = false;
	 }
	 if(!temp){
		 alert(name+"为小数或者整数！");
	 }
	 return temp;
}
/*
 * 判断图片类型
 * 
 * @param ths 
 * type="file"的javascript对象
 * @return true-符合要求,false-不符合
 */
function isPicture(value,name){
	if (value == "") {
		alert("请上传图片");
		return false;
	} else {
		if (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(value)) {
			alert(name+"类型必须是.gif,jpeg,jpg,png中的一种!");
			return false;
		}
   }
	return true;
}




/*
 * 判断图片大小
 * 
 * @param ths 
 * type="file"的JavaScript对象
 * @param width
 * 需要符合的宽 
 * @param height
 * 需要符合的高
 * @return true-符合要求,false-不符合
 */
function checkImgPX(ths, width, height) {
	var img = null;
	img = document.createElement("img");
	document.body.insertAdjacentElement("beforeEnd", img); // firefox不行
	img.style.visibility = "hidden"; 
	img.src = ths.value;
	var imgwidth = img.offsetWidth;
	var imgheight = img.offsetHeight;
	alert(imgwidth + "," + imgheight);
	if(imgwidth != width || imgheight != height) {
		alert("图的尺寸应该是" + width + "x"+ height);
		ths.value = "";
		return false;
	}
	return true;
}
function isEmptyValue(value){
	if(value!=null&&value!=''&&value!='undefined'){
		return false;
	}else{
		return true;
	}
}
//检验文件格式type=pdf|rar|zip|jpg|
function checkFileType(value,type,name){
	var suffix = value.substr(value.lastIndexOf(".")).toLowerCase().trim();
	if(type.indexOf(suffix+"|")!=-1){ return true;}else{ alert(name+"格式必须是"+type);return false;}
}
function isEmptyValue(value){
	if(value!=null&&value!=''&&value!='undefined'&value!=undefined){
		return false;
	}else{
		return true;
	}
}
/**
*验证营业执照是否合法：营业执照长度须为15位数字，前14位为顺序码，
*最后一位为根据GB/T 17710 1999(ISO 7064:1993)的混合系统校验位生成算法
*计算得出。此方法即是根据此算法来验证最后一位校验位是否政正确。
*二、顺序码是7-14位，顺序码指工商行政管理机关在其管辖范围内按照先
* 后次序为申请登记注册的市场主体所分配的顺序号。为了便于管理和
* 赋码，8位顺序码中的第1位（自左至右）采用以下分配规则：
*　　 1）内资各类企业使用“0”、“1”、“2”、“3”；
*　　 2）外资企业使用“4”、“5”；
*　　 3）个体工商户使用“6”、“7”、“8”、“9”。
**/
function isValidBusCode(busCode,name){
	var ret=false;
	if(busCode.length==15){
		var sum=0;
		var s=[];
		var p=[];
		var a=[];
		var m=10;
		p[0]=m;
		for(var i=0;i<busCode.length;i++){
			a[i]=parseInt(busCode.substring(i,i+1),m);
			s[i]=(p[i]%(m+1))+a[i];
			if(0==s[i]%m){
				p[i+1]=10*2;
			}else{
				p[i+1]=(s[i]%m)*2;
			}
		}
		if(1==(s[14]%m)){
			//营业执照编号正确!
			ret=true;
		}else{
			//营业执照编号错误!
			ret=false;
			alert(name+"格式不正确!");
		}
	}else{
		alert(name+"必须是15位数字！");
		ret = false;
	}
	return ret;
}
function checkorgcode(orgcode,name){
	if(orgcode.trim().length!=9){
		alert(name+"长度必须为9位.");
		return false;
    }
	var patrn=/^[0-9A-Z]+$/;
	if(patrn.test(orgcode)==false){
		alert(name+"只可为数字或大写拉丁字母.");
		return false;
	}
	var lastpatrn=/^[0-9X]+$/;
	var checkcode=orgcode.substring(8,9);
	if(lastpatrn.test(checkcode)==false){
		alert(name+"最后一位只可为数字或大写拉丁字母:X");
		return false;
	}
	var ancode;
	var ancodevalue;
	var total=0;
	for(var i=0;i<orgcode.length-1;i++){
		ancode=orgcode.substring(i,i+1);
		ancodevalue=findvalueinorgcodemapping(ancode);
		total=total+ancodevalue*weightedfactors[i];
	}
	var logiccheckcode=11-total%11;
	if(logiccheckcode==10) logiccheckcode='X';
	if(logiccheckcode==11) logiccheckcode='0';
	if(checkcode != logiccheckcode){
		alert(name+"格式错误.最后一位校验码应为:"+logiccheckcode);
		return false;
	}else{
		return true;
	}
}
function CheckSocialCreditCode(Code,name) {
	var patrn = /^[0-9A-Z]+$/;
	// 18位校验及大写校验
	if ((Code.length != 18) || (patrn.test(Code) == false)) {
		alert(name+"长度必须是18位，由数字和大写字母构成！") ;
		return false;
	} else {
		var Ancode;// 统一社会信用代码的每一个值
		var Ancodevalue;// 统一社会信用代码每一个值的权重
		var total = 0;
		var weightedfactors = [ 1, 3, 9, 27, 19, 26, 16, 17, 20, 29, 25, 13, 8,
				24, 10, 30, 28 ];// 加权因子
		var str = '0123456789ABCDEFGHJKLMNPQRTUWXY';
		// 不用I、O、S、V、Z
		for (var i = 0; i < Code.length - 1; i++) {
			Ancode = Code.substring(i, i + 1);
			Ancodevalue = str.indexOf(Ancode);
			total = total + Ancodevalue * weightedfactors[i];
			// 权重与加权因子相乘之和
		}
		var logiccheckcode = 31 - total % 31;
		if (logiccheckcode == 31) {
			logiccheckcode = 0;
		}
		var Str = "0,1,2,3,4,5,6,7,8,9,A,B,C,D,E,F,G,H,J,K,L,M,N,P,Q,R,T,U,W,X,Y";
		var Array_Str = Str.split(',');
		logiccheckcode = Array_Str[logiccheckcode];
		var checkcode = Code.substring(17, 18);
		if (logiccheckcode != checkcode) {
			alert(name+"验证无效！");
			return false;
		}
		return true;
	}
}