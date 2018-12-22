package com.goli.heroben.dao.proxy;

import java.util.Date;
import java.util.List;

import com.goli.heroben.dao.IUserDao;
import com.goli.heroben.dao.impl.UserDaoImp;
import com.goli.heroben.dbc.DataBaseConnection;
import com.goli.heroben.vo.UserBean;

public class UserDaoProxy implements IUserDao {
	private DataBaseConnection dbc = null;
	private IUserDao dao = null;

	public UserDaoProxy() throws Exception {
		this.dbc = new DataBaseConnection();
		this.dao = new UserDaoImp(this.dbc.getConnection());
	}

	public boolean doCreate(UserBean userBean) throws Exception {
		boolean flag = false;
		try {
			if (this.dao.findUserByTel(userBean.getTelephone()) == null) {
				flag = this.dao.doCreate(userBean);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	public boolean doRemoveByTel(String telephone) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.doRemoveByTel(telephone);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	public boolean doRemoveByEmail(String email) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.doRemoveByEmail(email);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	public boolean doRemoveByChecked(int checked) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.doRemoveByChecked(checked);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	public boolean doUpdateTelephone(String oTel, String nTel) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.doUpdateTelephone(oTel, nTel);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	public boolean doUpdateEmail(String tel, String nEmail) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.doUpdateEmail(tel, nEmail);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	public boolean doUpdatePassword(String tel, String nPass) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.doUpdatePassword(tel, nPass);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	public boolean doUpdateName(String tel, String nName) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.doUpdateName(tel, nName);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	public boolean doUpdateSex(String tel, String nSex) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.doUpdateSex(tel, nSex);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	public boolean doUpdateNuaaid(String tel, String nId) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.doUpdateNuaaid(tel, nId);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	public boolean doUpdateChecked(String tel, int nChecked) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.doUpdateChecked(tel, nChecked);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	public boolean doUpdateTaskCount(String tel, int count) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.doUpdateTaskCount(tel, count);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	public boolean doUpdateAveScore(String tel, double nScore) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.doUpdateAveScore(tel, nScore);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	public boolean doUpdateDpSrc(String tel, String nSrc) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.doUpdateDpSrc(tel, nSrc);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	public boolean doUpdateLeftMoney(String tel, String nMoney) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.doUpdateLeftMoney(tel, nMoney);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	public boolean doUpdateRegeisteTime(String tel, Date nTime) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.doUpdateRegeisteTime(tel, nTime);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	public boolean doUpdateLastLoginTime(String tel, Date nTime) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.doUpdateLastLoginTime(tel, nTime);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	public UserBean findUserByTel(String tel) throws Exception {
		UserBean userBean;
		try {
			userBean = this.dao.findUserByTel(tel);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return userBean;
	}

	public UserBean findUserByEmail(String email) throws Exception {
		UserBean userBean;
		try {
			userBean = this.dao.findUserByEmail(email);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return userBean;
	}

	public List<UserBean> findUserByName(String name) throws Exception {
		List<UserBean> all = null;
		try {
			all = this.dao.findUserByName(name);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return all;
	}

	public boolean doUpdateAll(UserBean oUser, UserBean nUser) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.doUpdateAll(oUser, nUser);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public List<UserBean> findUserOrderByLimit(String order, int type, int limitsize) throws Exception {
		List<UserBean> all = null;
		try {
			all = this.dao.findUserOrderByLimit(order, type, limitsize);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return all;
	}
}
