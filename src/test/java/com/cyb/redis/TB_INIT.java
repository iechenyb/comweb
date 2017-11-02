package com.cyb.redis;

import java.math.BigDecimal;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2017年11月2日
 */
public class TB_INIT {
	String account = "888668";
	long begin_date;
	double ljdwjz = 0.0;
	BigDecimal big = new BigDecimal("5.23");
    public TB_INIT(){}
	public TB_INIT(String account, long ywrq, double ljdwjz) {
		this.account = account;
		this.begin_date = ywrq;
		this.ljdwjz = ljdwjz;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public long getBegin_date() {
		return begin_date;
	}

	public void setBegin_date(long begin_date) {
		this.begin_date = begin_date;
	}

	public double getLjdwjz() {
		return ljdwjz;
	}

	public void setLjdwjz(double ljdwjz) {
		this.ljdwjz = ljdwjz;
	}

	public BigDecimal getBig() {
		return big;
	}

	public void setBig(BigDecimal big) {
		this.big = big;
	}

}
