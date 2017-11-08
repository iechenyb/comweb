package com.cyb.redis;

/**
 *作者 : iechenyb<br>
 *类描述:选手计算状态，全量信息移动到失败或者成功的列表中<br>
 *创建时间: 2017年11月2日
 */
public class PersonCalStatus {
	public String account;//资金账号
	public String seasonType;//赛季类型
	public String lastDay;//计算出错的日期
	public String index;//计算的那个指标
	public int status=0;//计算状态 0待计算 1 失败 2成功
	public String time;//记录时间20171103123002
	public String exInfor;//异常信息 如填写ex.toString()
	public PersonCalStatus(){}
	public PersonCalStatus(String account){
		this.account = account;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getSeasonType() {
		return seasonType;
	}
	public void setSeasonType(String seasonType) {
		this.seasonType = seasonType;
	}
	public String getLastDay() {
		return lastDay;
	}
	public void setLastDay(String lastDay) {
		this.lastDay = lastDay;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getExInfor() {
		return exInfor;
	}
	public void setExInfor(String exInfor) {
		this.exInfor = exInfor;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
