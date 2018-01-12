package com.cyb.common;

/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年1月10日
 */
public class CheckException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public CheckException(){}
	public CheckException(String msg){super(msg);}
	public CheckException(Throwable cause){super(cause);}
	public CheckException(String msg,Throwable cause){super(msg,cause);}
	public CheckException(String msg,Throwable cause,
			boolean enableSuppression,boolean writableStackTrace)
	{super(msg,cause,enableSuppression,writableStackTrace);}
}
