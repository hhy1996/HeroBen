package com.goli.heroben.vo;

import java.util.Date;

public class UserBean {
	private String telephone;
    private String email;
    private String password;
    private String name;
    private String sex;
    private String nuaaid;
    private int checked;
    private int taskcount ;
    private double avescore;
    private String dpsrc;
    private double leftmoney;
    private Date regeiste_time;
    private Date last_login_time;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNuaaid() {
		return nuaaid;
	}

	public void setNuaaid(String nuaaid) {
		this.nuaaid = nuaaid;
	}

	public int getChecked() {
		return checked;
	}

	public void setChecked(int checked) {
		this.checked = checked;
	}

	public int getTaskcount() {
		return taskcount;
	}

	public void setTaskcount(int taskcount) {
		this.taskcount = taskcount;
	}

	public double getAvescore() {
		return avescore;
	}

	public void setAvescore(double avescore) {
		this.avescore = avescore;
	}

	public String getDpsrc() {
		return dpsrc;
	}

	public void setDpsrc(String dpsrc) {
		this.dpsrc = dpsrc;
	}

	public double getLeftmoney() {
		return leftmoney;
	}

	public void setLeftmoney(double leftmoney) {
		this.leftmoney = leftmoney;
	}

	public Date getRegeiste_time() {
		return regeiste_time;
	}

	public void setRegeiste_time(Date regeisteTime) {
		regeiste_time = regeisteTime;
	}

	public Date getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(Date lastLoginTime) {
		last_login_time = lastLoginTime;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
