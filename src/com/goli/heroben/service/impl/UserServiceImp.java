package com.goli.heroben.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.goli.heroben.factory.DaoFactory;
import com.goli.heroben.service.IUserService;
import com.goli.heroben.util.Encode.MD5Util;
import com.goli.heroben.util.Message.AliSendMessage;
import com.goli.heroben.util.Message.MailSendMessage;
import com.goli.heroben.util.Message.SendMessage;
import com.goli.heroben.vo.UserBean;

public class UserServiceImp implements IUserService {

	@Override
	public UserBean login(String id, String pwd) {
		pwd = MD5Util.MD5(pwd);

		UserBean userBean = null;
		if (id.indexOf('@') != -1) {
			try {
				userBean = DaoFactory.getIUserDaoInstance().findUserByEmail(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				userBean = DaoFactory.getIUserDaoInstance().findUserByTel(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (userBean!=null&&userBean.getPassword().equals(pwd)) {
			try {
				DaoFactory.getIUserDaoInstance().doUpdateLastLoginTime(userBean.getTelephone(),new Date());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return userBean;
		} else {
			return null;
		}
	}

	@Override
	public int regeist(UserBean user) {
		boolean flag1 = false;
		boolean flag3 = false;
		int result = 0;
		try {
			String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(user.getEmail());
			flag1 = matcher.matches();
		} catch (Exception e) {
			flag1 = false;
		}
		if (!flag1)
			result = 1;
		if (flag1) {
			try {
				if (!user.getSex().equals("男") && !user.getSex().equals("女") && !user.getSex().equals("保密"))
					result = 5;
				user.setRegeiste_time(new java.util.Date());
				user.setLast_login_time(new java.util.Date());
				user.setPassword(MD5Util.MD5(user.getPassword()));
				flag3 = DaoFactory.getIUserDaoInstance().doCreate(user);
				if (!flag3)
					result = 3;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public String sendCheckCode(UserBean user, int type) {
		SendMessage sm = null;
		String iden = null;
		if (type == 1) {
			sm = new AliSendMessage();
			iden = sm.sendCheckMsg(user.getTelephone(), 6);
		}
		if (type == 2) {
			sm = new MailSendMessage();
			iden = sm.sendCheckMsg(user.getEmail(), 6);
		}
		return iden;
	}

	@Override
	public boolean resetPassword(UserBean user, String nPass) {
		boolean flag = false;
		if (!nPass.equals(user.getPassword())) {
			try {
				nPass = MD5Util.MD5(nPass);
				flag = DaoFactory.getIUserDaoInstance().doUpdatePassword(user.getTelephone(), nPass);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public boolean withdraw(UserBean user, double money) {
		boolean flag = false;
		try {
			flag = DaoFactory.getIUserDaoInstance().doUpdateLeftMoney(user.getTelephone(),
					String.valueOf(user.getLeftmoney() - money));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean checkUser(UserBean user, String nuaaid) {
		boolean flag = false;
		try {
			flag = DaoFactory.getIUserDaoInstance().doUpdateNuaaid(user.getTelephone(), nuaaid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean uploadHeadPic(UserBean user, String src) {
		boolean flag = false;
		try {
			flag = DaoFactory.getIUserDaoInstance().doUpdateDpSrc(user.getTelephone(), src);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean changeTel(UserBean user, String nTel) {
		boolean flag = false;
		try {
			flag = DaoFactory.getIUserDaoInstance().doUpdateTelephone(user.getTelephone(), nTel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean changeEmail(UserBean user, String nEmail) {
		boolean flag = false;
		try {
			flag = DaoFactory.getIUserDaoInstance().doUpdateEmail(user.getTelephone(), nEmail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean changeSex(UserBean user, String sex) {
		boolean flag = false;
		try {
			flag = DaoFactory.getIUserDaoInstance().doUpdateSex(user.getTelephone(), sex);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean changeInfo(UserBean ouser, UserBean nuser) {
		boolean flag = false;
		try {
			flag = DaoFactory.getIUserDaoInstance().doUpdateAll(ouser, nuser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<String> getSixHighScoreUser() {
		List<String> nameList = new ArrayList<String>();
		try {
			List<UserBean> userlist = DaoFactory.getIUserDaoInstance().findUserOrderByLimit("avescore", 1, 6);
			if (userlist != null) {
				for (int i = 0; i < userlist.size(); i++) {
					nameList.add(userlist.get(i).getName());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nameList;
	}

}
