package com.goli.heroben.vo;

import java.util.Date;

public class WithdrawBean {
	@Override
	public String toString() {
		return "WithdrawBean [id=" + id + ", userid=" + userid + ", money=" + money + ", userplatid=" + userplatid
				+ ", paytime=" + paytime + ", platformid=" + platformid + ", platname=" + platname + "]";
	}
	private String id;
	private String userid;
	private double money;
	private String userplatid;
	private Date paytime;
	private String platformid;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getUserplatid() {
		return userplatid;
	}
	public void setUserplatid(String userplatid) {
		this.userplatid = userplatid;
	}
	public Date getPaytime() {
		return paytime;
	}
	public void setPaytime(Date paytime) {
		this.paytime = paytime;
	}
	public String getPlatformid() {
		return platformid;
	}
	public void setPlatformid(String platformid) {
		this.platformid = platformid;
	}
	public String getPlatname() {
		return platname;
	}
	public void setPlatname(String platname) {
		this.platname = platname;
	}
	private String platname;
}
