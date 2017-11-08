package com.cyb.redis;

import java.util.Random;

import com.cyb.data.DataUtils;

/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年11月2日
 */
public class TB_A {
	String account="888668";
	long ywrq;//业务日期
	double ljdwjz=0.0;//累计单位净值
	double drjrj=0.0;//当日净入金
	double drzdjrj=0.0;//当日最大净入金 定值(1-1000)随机
	double drljjrj=0.0;//当日累计净入金 定值 累计 待计算
	double zdljjrj=0.0;//最大累计净入金
	double jzdrljjrj=0.0;//截至当日累计净入金
	double drdwfs=0.0;//当日单位份数
	double drdwcj=0.0;//当日单位出金
	double drljdwcj=0.0;//当日累计单位出金
	double drdwjz=0.0;//当日单位净值
	double drqy=0.0;//当日账户权益 定值（1-1000）随机
	double srqy=0.0;//上日账户权益 定值
	double drjyk=0.0;//当日净盈亏
	double ljsyl=0.0;//累计收益率
	public TB_A(long ywrq,double ljdwjz){
		this.ywrq = ywrq;
		this.ljdwjz = ljdwjz;
	}
	public TB_A(){}
	public TB_A(long ywrq){
		Random random = new Random();
		this.ywrq = ywrq;
		this.drqy = DataUtils.e2Double(random.nextDouble()*1000, 8).doubleValue();
		this.drzdjrj = DataUtils.e2Double(random.nextDouble()*1000-100,8).doubleValue();//小于100则为负数
		this.drjrj = DataUtils.e2Double(random.nextDouble()*1000-50,8).doubleValue();//小于50则为负数
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public long getYwrq() {
		return ywrq;
	}
	public void setYwrq(long ywrq) {
		this.ywrq = ywrq;
	}
	public double getLjdwjz() {
		return ljdwjz;
	}
	public void setLjdwjz(double ljdwjz) {
		this.ljdwjz = DataUtils.e2Double(ljdwjz,8).doubleValue();
	}

	public double getDrjrj() {
		return drjrj;
	}

	public void setDrjrj(double drjrj) {
		this.drjrj = DataUtils.e2Double(drjrj,8).doubleValue();
	}

	public double getDrzdjrj() {
		return drzdjrj;
	}

	public void setDrzdjrj(double drzdjrj) {
		this.drzdjrj = DataUtils.e2Double(drzdjrj,8).doubleValue();
	}

	public double getDrljjrj() {
		return drljjrj;
	}

	public void setDrljjrj(double drljjrj) {
		this.drljjrj = DataUtils.e2Double(drljjrj,8).doubleValue();
	}

	public double getZdljjrj() {
		return zdljjrj;
	}

	public void setZdljjrj(double zdljjrj) {
		this.zdljjrj = DataUtils.e2Double(zdljjrj,8).doubleValue();
	}

	public double getDrdwfs() {
		return drdwfs;
	}

	public void setDrdwfs(double drdwfs) {
		this.drdwfs = DataUtils.e2Double(drdwfs,8).doubleValue();
	}

	public double getDrdwcj() {
		return drdwcj;
	}

	public void setDrdwcj(double drdwcj) {
		this.drdwcj = DataUtils.e2Double(drdwcj,8).doubleValue();
	}

	public double getDrljdwcj() {
		return drljdwcj;
	}

	public void setDrljdwcj(double drljdwcj) {
		this.drljdwcj = DataUtils.e2Double(drljdwcj,8).doubleValue();
	}

	public double getDrdwjz() {
		return drdwjz;
	}

	public void setDrdwjz(double drdwjz) {
		this.drdwjz = DataUtils.e2Double(drdwjz,8).doubleValue();
	}

	public double getDrqy() {
		return drqy;
	}

	public void setDrqy(double drqy) {
		this.drqy = DataUtils.e2Double(drqy,8).doubleValue();
	}

	public double getSrqy() {
		return srqy;
	}

	public void setSrqy(double srqy) {
		this.srqy = DataUtils.e2Double(srqy,8).doubleValue();
	}
	public double getJzdrljjrj() {
		return jzdrljjrj;
	}
	public void setJzdrljjrj(double jzdrljjrj) {
		this.jzdrljjrj = DataUtils.e2Double(jzdrljjrj,8).doubleValue();
	}
	public double getDrjyk() {
		return drjyk;
	}
	public void setDrjyk(double drjyk) {
		this.drjyk = DataUtils.e2Double(drjyk,8).doubleValue();
	}
	public double getLjsyl() {
		return ljsyl;
	}
	public void setLjsyl(double ljsyl) {
		this.ljsyl = DataUtils.e2Double(ljsyl,8).doubleValue();
	}
	
}
