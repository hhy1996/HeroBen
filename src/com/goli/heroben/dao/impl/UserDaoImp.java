package com.goli.heroben.dao.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.goli.heroben.dao.IUserDao;
import com.goli.heroben.vo.UserBean;

public class UserDaoImp implements IUserDao {
	private Connection conn = null;// 数据库连接对象
	private PreparedStatement ps = null;// 数据库操作对象

	public UserDaoImp(Connection conn) {// 通过构造方法来取得数据库连接
		this.conn = conn;
	}

	public boolean doCreate(UserBean userBean) throws Exception {
		boolean flag = false;
		String sql = "insert into user(telephone ,email, password, name, sex,nuaaid,checked,taskcount,avescore,dpsrc,leftmoney,regeiste_time,last_login_time) values(?,?,?,?,?,?,?,?,?,?,?,?,?);";

		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, userBean.getTelephone());
		this.ps.setString(2, userBean.getEmail());
		this.ps.setString(3, userBean.getPassword());
		this.ps.setString(4, userBean.getName());
		this.ps.setString(5, userBean.getSex());
		this.ps.setString(6, userBean.getNuaaid());
		this.ps.setInt(7, userBean.getChecked());
		this.ps.setInt(8, userBean.getTaskcount());
		this.ps.setDouble(9, userBean.getAvescore());
		this.ps.setString(10, userBean.getDpsrc());
		this.ps.setDouble(11, userBean.getLeftmoney());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = df.format(userBean.getLast_login_time());
		java.util.Date time = df.parse(str);
		java.sql.Timestamp sqlDate = new java.sql.Timestamp(time.getTime());
		this.ps.setTimestamp(12, sqlDate);
		this.ps.setTimestamp(13, sqlDate);
		if (this.ps.executeUpdate() > 0) {
			flag = true;
		}
		this.ps.close();
		return flag;
	}

	public boolean doRemoveByTel(String telephone) throws Exception {
		boolean flag = false;
		String sql = "delete from user where telephone = '" + telephone + "' ";
		this.ps = this.conn.prepareStatement(sql);

		if (ps.executeUpdate() > 0)
			flag = true;
		return flag;
	}

	public boolean doRemoveByEmail(String email) throws Exception {
		boolean flag = false;
		String sql = "delete from user where email = '" + email + "' ";
		this.ps = this.conn.prepareStatement(sql);

		if (ps.executeUpdate() > 0)
			flag = true;
		return flag;
	}

	public boolean doRemoveByChecked(int checked) throws Exception {
		boolean flag = false;
		String sql = "delete from user where checked = '" + checked + "' ";
		this.ps = this.conn.prepareStatement(sql);

		if (ps.executeUpdate() > 0)
			flag = true;
		return flag;
	}

	public boolean doUpdateTelephone(String oTel, String nTel) throws Exception {
		boolean flag = false;
		String sql = "update user set telephone = '" + nTel + "' where telephone ='" + oTel + "' ";
		this.ps = this.conn.prepareStatement(sql);

		if (ps.executeUpdate() > 0)
			flag = true;
		return flag;
	}

	public boolean doUpdateEmail(String tel, String nEmail) throws Exception {
		boolean flag = false;
		String sql = "update user set email = '" + nEmail + "' where telephone ='" + tel + "' ";
		this.ps = this.conn.prepareStatement(sql);

		if (ps.executeUpdate() > 0)
			flag = true;
		return flag;
	}

	public boolean doUpdatePassword(String tel, String nPass) throws Exception {
		boolean flag = false;
		String sql = "update user set password = '" + nPass + "' where telephone ='" + tel + "' ";
		this.ps = this.conn.prepareStatement(sql);

		if (ps.executeUpdate() > 0)
			flag = true;
		return flag;
	}

	public boolean doUpdateName(String tel, String nName) throws Exception {
		boolean flag = false;
		String sql = "update user set name = '" + nName + "' where telephone ='" + tel + "' ";
		this.ps = this.conn.prepareStatement(sql);

		if (ps.executeUpdate() > 0)
			flag = true;
		return flag;
	}

	public boolean doUpdateSex(String tel, String nSex) throws Exception {
		boolean flag = false;
		String sql = "update user set sex = '" + nSex + "' where telephone ='" + tel + "' ";
		this.ps = this.conn.prepareStatement(sql);

		if (ps.executeUpdate() > 0)
			flag = true;
		return flag;
	}

	public boolean doUpdateNuaaid(String tel, String nId) throws Exception {
		boolean flag = false;
		String sql = "update user set nuaaid = '" + nId + "' where telephone ='" + tel + "' ";
		this.ps = this.conn.prepareStatement(sql);

		if (ps.executeUpdate() > 0)
			flag = true;
		return flag;
	}

	public boolean doUpdateChecked(String tel, int nChecked) throws Exception {
		boolean flag = false;
		String sql = "update user set checked = '" + nChecked + "' where telephone ='" + tel + "' ";
		this.ps = this.conn.prepareStatement(sql);

		if (ps.executeUpdate() > 0)
			flag = true;
		return flag;
	}

	public boolean doUpdateTaskCount(String tel, int count) throws Exception {
		boolean flag = false;
		String sql = "update user set taskcount = '" + count + "' where telephone ='" + tel + "' ";
		this.ps = this.conn.prepareStatement(sql);

		if (ps.executeUpdate() > 0)
			flag = true;
		return flag;
	}

	public boolean doUpdateAveScore(String tel, double nScore) throws Exception {
		boolean flag = false;
		String sql = "update user set avescore = '" + nScore + "' where telephone ='" + tel + "' ";
		this.ps = this.conn.prepareStatement(sql);

		if (ps.executeUpdate() > 0)
			flag = true;
		return flag;
	}

	public boolean doUpdateDpSrc(String tel, String nSrc) throws Exception {
		boolean flag = false;
		String sql = "update user set dpsrc = '" + nSrc + "' where telephone ='" + tel + "' ";
		this.ps = this.conn.prepareStatement(sql);

		if (ps.executeUpdate() > 0)
			flag = true;
		return flag;
	}

	public boolean doUpdateLeftMoney(String tel, String nMoney) throws Exception {
		boolean flag = false;
		double Money = Double.parseDouble(nMoney);
		String sql = "update user set leftmoney = ? where telephone =?";

		this.ps = this.conn.prepareStatement(sql);
		this.ps.setDouble(1, Money);
		this.ps.setString(2, tel);
		if (ps.executeUpdate() > 0)
			flag = true;
		return flag;
	}

	public boolean doUpdateRegeisteTime(String tel, Date nTime) throws Exception {
		boolean flag = false;
		String sql = "update user set regeiste_time = ? where telephone ='" + tel + "' ";
		this.ps = this.conn.prepareStatement(sql);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = df.format(nTime);
		java.util.Date time = df.parse(str);
		java.sql.Timestamp sqlDate = new java.sql.Timestamp(time.getTime());
		ps.setTimestamp(1, sqlDate);
		if (ps.executeUpdate() > 0)
			flag = true;
		return flag;
	}

	public boolean doUpdateLastLoginTime(String tel, Date nTime) throws Exception {
		boolean flag = false;
		String sql = "update user set last_login_time = ? where telephone ='" + tel + "' ";
		this.ps = this.conn.prepareStatement(sql);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = df.format(nTime);
		java.util.Date time = df.parse(str);
		java.sql.Timestamp sqlDate = new java.sql.Timestamp(time.getTime());
		ps.setTimestamp(1, sqlDate);
		if (ps.executeUpdate() > 0)
			flag = true;
		return flag;
	}

	public UserBean findUserByTel(String tel) throws Exception {
		UserBean userBean = null;
		String sql = "select telephone ,email, password, name, sex,nuaaid,checked,taskcount,avescore,dpsrc,leftmoney,regeiste_time,last_login_time from user where telephone = ?";

		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, tel);

		ResultSet rs = this.ps.executeQuery();

		if (rs.next()) {
			userBean = new UserBean();
			userBean.setTelephone(rs.getString(1));
			userBean.setEmail(rs.getString(2));
			userBean.setPassword(rs.getString(3));
			userBean.setName(rs.getString(4));
			userBean.setSex(rs.getString(5));
			userBean.setNuaaid(rs.getString(6));
			userBean.setChecked(rs.getInt(7));
			userBean.setTaskcount(rs.getInt(8));
			userBean.setAvescore(rs.getInt(9));
			userBean.setDpsrc(rs.getString(10));
			userBean.setLeftmoney(rs.getDouble(11));
			userBean.setRegeiste_time(rs.getDate(12));
			userBean.setLast_login_time(rs.getDate(13));
		}
		rs.close();
		this.ps.close();
		return userBean;
	}

	public UserBean findUserByEmail(String email) throws Exception {
		UserBean userBean = null;
		String sql = "select telephone ,email, password, name, sex,nuaaid,checked,taskcount,avescore,dpsrc,leftmoney,regeiste_time,last_login_time from user where email = ?";

		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, email);

		ResultSet rs = this.ps.executeQuery();

		if (rs.next()) {
			userBean = new UserBean();
			userBean.setTelephone(rs.getString(1));
			userBean.setEmail(rs.getString(2));
			userBean.setPassword(rs.getString(3));
			userBean.setName(rs.getString(4));
			userBean.setSex(rs.getString(5));
			userBean.setNuaaid(rs.getString(6));
			userBean.setChecked(rs.getInt(7));
			userBean.setTaskcount(rs.getInt(8));
			userBean.setAvescore(rs.getInt(9));
			userBean.setDpsrc(rs.getString(10));
			userBean.setLeftmoney(rs.getDouble(11));
			userBean.setRegeiste_time(rs.getDate(12));
			userBean.setLast_login_time(rs.getDate(13));
		}
		rs.close();
		this.ps.close();
		return userBean;
	}

	public List<UserBean> findUserByName(String name) throws Exception {
		List<UserBean> all = new ArrayList<UserBean>();
		UserBean userBean = null;
		String sql = "select telephone ,email, password, name, sex,nuaaid,checked,taskcount,avescore,dpsrc,leftmoney,regeiste_time,last_login_time from user where name = ?";

		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, name);

		ResultSet rs = this.ps.executeQuery();
		while (rs.next()) {
			userBean = new UserBean();
			userBean.setTelephone(rs.getString(1));
			userBean.setEmail(rs.getString(2));
			userBean.setPassword(rs.getString(3));
			userBean.setName(rs.getString(4));
			userBean.setSex(rs.getString(5));
			userBean.setNuaaid(rs.getString(6));
			userBean.setChecked(rs.getInt(7));
			userBean.setTaskcount(rs.getInt(8));
			userBean.setAvescore(rs.getInt(9));
			userBean.setDpsrc(rs.getString(10));
			userBean.setLeftmoney(rs.getDouble(11));
			userBean.setRegeiste_time(rs.getDate(12));
			userBean.setLast_login_time(rs.getDate(13));
			all.add(userBean);
		}
		ps.close();
		rs.close();
		return all;
	}

	public boolean doUpdateAll(UserBean oUser, UserBean nUser) throws Exception {
		boolean flag = false;
		Field[] field = UserBean.class.getDeclaredFields();
		String sql = "update user set ";
		for (Field f : field) {
			f.setAccessible(true);
			Object v1 = f.get(oUser);
			Object v2 = f.get(nUser);
			if (v1 != null && !v1.equals(v2) && v2 != null) {
				sql += f.getName() + "='" + v2.toString() + "',";
			}
		}
		sql = sql.substring(0, sql.length() - 1);
		sql += " where telephone = ?";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, oUser.getTelephone());
		if (this.ps.executeUpdate() > 0)
			flag = true;
		return flag;
	}

	@Override
	public List<UserBean> findUserOrderByLimit(String order, int type, int limitsize) throws Exception {
		List<UserBean> all = new ArrayList<UserBean>();
		UserBean userBean = null;
		String sql = "select telephone,email,password,name,sex,nuaaid,checked,taskcount,avescore,dpsrc,leftmoney,regeiste_time,last_login_time from user order by ? %TYPE% limit ?";
		if(type==1){
			sql=sql.replace("%TYPE%", "ASC");
		}else{
			sql=sql.replace("%TYPE%", "DESC");
		}
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, order);
		this.ps.setInt(2, limitsize);
		ResultSet rs = this.ps.executeQuery();
		while (rs.next()) {
			userBean = new UserBean();
			userBean.setTelephone(rs.getString(1));
			userBean.setEmail(rs.getString(2));
			userBean.setPassword(rs.getString(3));
			userBean.setName(rs.getString(4));
			userBean.setSex(rs.getString(5));
			userBean.setNuaaid(rs.getString(6));
			userBean.setChecked(rs.getInt(7));
			userBean.setTaskcount(rs.getInt(8));
			userBean.setAvescore(rs.getInt(9));
			userBean.setDpsrc(rs.getString(10));
			userBean.setLeftmoney(rs.getDouble(11));
			userBean.setRegeiste_time(rs.getDate(12));
			userBean.setLast_login_time(rs.getDate(13));
			all.add(userBean);
		}
		ps.close();
		rs.close();
		return all;
	}

}
