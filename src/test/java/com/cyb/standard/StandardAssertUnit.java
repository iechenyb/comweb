package com.cyb.standard;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年7月19日
 */
public class StandardAssertUnit {
	Log log = LogFactory.getLog(StandardAssertUnit.class);
	@Before//在 VM arguments 文本框中输入:-ea注意中间没有空格,如果输入-da表示禁止断言。
	public void checkIsOpenEclipseAssert(){
		boolean isOpen = false;  
		assert isOpen=true; //如果开启了断言，会将isOpen的值改为true
		if(!isOpen)
			System.out.println("是否开启断言"+isOpen);//打印是否开启了断言   
	}
	@Test
	public void test(){
		int i=0;int j=1;
		boolean isOk = 1>2;
		assert("")!=null;
		assert("")=="";
		assert !(6==5);//其后的sysout可以打印断言正常  
	    System.out.println("如果断言正常，我就被打印.");
	    System.out.println("如果断言正常，我就被打印!");
	    System.out.println("如果断言正常，我就被打印.");
	    System.out.println("如果断言正常，我就被打印!");
		//如果冒号前为false，则抛出AssertionError ，错误信息内容为冒号后面的内容
		assert isOk : "程序错误"+isOk;
		assert(i)==j;
		assert(j)==i;
		assert isOk;
		assert true;
		assert false;
		assert i==1;
		assert 1==i;
		assert i==j;
		assert(null)!=null;
	}
	public boolean able(){
		return true;
	}
}
