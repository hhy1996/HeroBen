package com.goli.heroben.vo;

import java.util.Date;

public class PaymentBean {
	private String id;
	private String userid;
	private double money;
	private Date paytime;
	private String platformid;
	private String platname;
	private String url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
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
	public String toString(){
		return "\n编号 = " + id + "\n用户名 = " + userid + "\n金额 =" + money + "\n֧支付时间 ="
				+ paytime + "\n订单号 = " + platformid + "\n֧支付方式 = " + platname;
	}
}
