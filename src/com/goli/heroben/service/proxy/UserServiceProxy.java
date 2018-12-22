package com.goli.heroben.service.proxy;

import java.util.List;

import com.goli.heroben.service.IUserService;
import com.goli.heroben.service.impl.UserServiceImp;
import com.goli.heroben.vo.UserBean;

public class UserServiceProxy implements IUserService {

	private IUserService us;

	public UserServiceProxy() {
		us = new UserServiceImp();
	}

	@Override
	public UserBean login(String id, String pwd) {
		if (id == null || pwd == null)
			return null;
		UserBean userBean = null;
		try {
			userBean = this.us.login(id, pwd);
		} catch (Exception e) {
			throw e;
		}
		return userBean;
	}

	@Override
	public int regeist(UserBean user) {
		if (user == null)
			return -1;
		int flag;
		flag = this.us.regeist(user);
		return flag;
	}

	@Override
	public String sendCheckCode(UserBean user, int type) {
		if (user == null || type == 0)
			return null;
		String iden = null;
		iden = this.us.sendCheckCode(user, type);
		return iden;
	}

	@Override
	public boolean resetPassword(UserBean user, String nPass) {
		if (user == null || nPass == null)
			return false;
		boolean flag = false;
		flag = this.us.resetPassword(user, nPass);
		return flag;
	}

	@Override
	public boolean withdraw(UserBean user, double money) {
		if (user == null)
			return false;
		boolean flag = false;
		flag = this.us.withdraw(user, money);
		return flag;
	}

	@Override
	public boolean checkUser(UserBean user, String nuaaid) {
		if (user == null || nuaaid == null)
			return false;
		boolean flag = false;
		flag = this.us.checkUser(user, nuaaid);
		return flag;
	}

	@Override
	public boolean uploadHeadPic(UserBean user, String src) {
		if (user == null || src == null)
			return false;
		boolean flag = false;
		flag = this.us.uploadHeadPic(user, src);
		return flag;
	}

	@Override
	public boolean changeTel(UserBean user, String nTel) {
		if (user == null || nTel == null)
			return false;
		boolean flag = false;
		flag = this.us.changeTel(user, nTel);
		return flag;
	}

	@Override
	public boolean changeEmail(UserBean user, String nEmail) {
		if (user == null || nEmail == null)
			return false;
		boolean flag = false;
		flag = this.us.changeEmail(user, nEmail);
		return flag;
	}

	@Override
	public boolean changeSex(UserBean user, String sex) {
		if (user == null || sex == null)
			return false;
		boolean flag = false;
		flag = this.us.changeSex(user, sex);
		return flag;
	}

	@Override
	public boolean changeInfo(UserBean ouser, UserBean nuser) {
		if (ouser == null || nuser == null)
			return false;
		boolean flag = false;
		flag = this.us.changeInfo(ouser, nuser);
		return flag;
	}

	@Override
	public List<String> getSixHighScoreUser() {
		return this.us.getSixHighScoreUser();
	}

}
