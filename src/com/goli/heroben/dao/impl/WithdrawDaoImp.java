package com.goli.heroben.dao.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.goli.heroben.dao.IWithdrawDao;
import com.goli.heroben.vo.PaymentBean;
import com.goli.heroben.vo.UserBean;
import com.goli.heroben.vo.WithdrawBean;

public class WithdrawDaoImp implements IWithdrawDao {
	private Connection conn = null;// 数据库连接对象
	private PreparedStatement ps = null;// 数据库操作对象

	public WithdrawDaoImp(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public boolean doCreate(WithdrawBean withdrawBean) throws Exception {
		boolean flag = false;
		String sql = "insert into withdraw (id,userid,money,userplatid,paytime,platformid,platname) values (?,?,?,?,?,?,?);";
		this.ps = this.conn.prepareStatement(sql);
		
		this.ps.setString(1, withdrawBean.getId());
		this.ps.setString(2, withdrawBean.getUserid());
		this.ps.setDouble(3, withdrawBean.getMoney());
		this.ps.setString(4, withdrawBean.getUserplatid());
		this.ps.setTimestamp(5, new java.sql.Timestamp(withdrawBean.getPaytime().getTime()));
		this.ps.setString(6, withdrawBean.getPlatformid());
		this.ps.setString(7, withdrawBean.getPlatname());
		
		if (this.ps.executeUpdate() > 0) {
			flag = true;
		}
		this.ps.close();
		
		return flag;
	}

	@Override
	public boolean doRemoveById(String id) throws Exception {
		boolean flag =false;
		String sql = "delete from withdraw where id = ? ";
	   	this.ps=this.conn.prepareStatement(sql);
	   	
	   	this.ps.setString(1, id);
	   	if(ps.executeUpdate()>0){
	   	     flag = true;
	   	}
	   	this.ps.close();
		return flag;
	}

	@Override
	public boolean doRemoveByUserId(String userId) throws Exception {
		boolean flag =false;
		String sql = "delete from withdraw where userid = ? ";
	   	this.ps=this.conn.prepareStatement(sql);
	   	
	   	this.ps.setString(1, userId);
	   	if(ps.executeUpdate()>0){
	   	     flag = true;
	   	}
	   	this.ps.close();
		return flag;
	}

	@Override
	public boolean doRemoveByPlatformId(String platid) throws Exception {
		boolean flag =false;
		String sql = "delete from withdraw where platformid = ? ";
	   	this.ps=this.conn.prepareStatement(sql);
	   	
	   	this.ps.setString(1, platid);
	   	if(ps.executeUpdate()>0){
	   	     flag = true;
	   	}
	   	this.ps.close();
		return flag;
	}

	@Override
	public boolean doUpdateId(String oId, String nId) throws Exception {
		boolean flag = false;
		String sql = "update withdraw set id = ? where id = ?";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, nId);
		this.ps.setString(2, oId);
		if(this.ps.executeUpdate() > 0)
			flag = true;
		this.ps.close();
		return flag;
	}

	@Override
	public boolean doUpdateUserId(String oId, String nId) throws Exception {
		boolean flag = false;
		String sql = "update withdraw set userid = ? where userid = ?";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, nId);
		this.ps.setString(2, oId);
		if(this.ps.executeUpdate() > 0)
			flag = true;
		this.ps.close();
		return flag;
	}

	@Override
	public boolean doUpdateMoney(String id, double nMoney) throws Exception {
		boolean flag = false;
		String sql = "update withdraw set money = ? where id = ?";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setDouble(1, nMoney);
		this.ps.setString(2, id);
		if(this.ps.executeUpdate() > 0)
			flag = true;
		this.ps.close();
		return flag;
	}

	@Override
	public boolean doUpdateUserPlatId(String id, String platid) throws Exception {
		boolean flag = false;
		String sql = "update withdraw set userplatid = ? where id = ?";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, platid);
		this.ps.setString(2, id);
		if(this.ps.executeUpdate() > 0)
			flag = true;
		this.ps.close();
		return flag;
	}

	@Override
	public boolean doUpdatePayTime(String id, Date nTime) throws Exception {		
		boolean flag = false;
		String sql = "update withdraw set paytime = ? where id = ?";
	   	this.ps=this.conn.prepareStatement(sql);
	   	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = df.format(nTime); 
		java.util.Date time = df.parse(str);
	   	java.sql.Timestamp sqlDate = new java.sql.Timestamp(time.getTime());
	   	this.ps.setTimestamp(1,sqlDate);
	   	this.ps.setString(2, id);
	   	if(ps.executeUpdate()>0)
	   	     flag = true;
		return flag;
		
	}

	@Override
	public boolean doUpdatePlatFormId(String id, String nPlatId) throws Exception {
		boolean flag = false;
		String sql = "update withdraw set platformid = ? where id = ?";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, nPlatId);
		this.ps.setString(2, id);
		if(this.ps.executeUpdate() > 0)
			flag = true;
		this.ps.close();
		return flag;
	}

	@Override
	public boolean doUpdatePlatName(String id, String nName) throws Exception {
		boolean flag = false;
		String sql = "update withdraw set platname = ? where id = ?";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, nName);
		this.ps.setString(2, id);
		if(this.ps.executeUpdate() > 0)
			flag = true;
		this.ps.close();
		return flag;
	}

	@Override
	public boolean doUpdateAll(WithdrawBean oWithdraw, WithdrawBean nWithdraw) throws Exception {
		boolean flag = false;
		Field[] field = WithdrawBean.class.getDeclaredFields();
		String sql = "update withdraw set ";
		try{
			for(Field f:field){
				f.setAccessible(true);
				Object v1 = f.get(oWithdraw);
				Object v2 = f.get(nWithdraw);
				if (v1 != null && !v1.equals(v2) && v2 != null) {
					sql += f.getName() + " = '" + v2.toString() + "'  ,";
				}
			}
			sql = sql.substring(0, sql.length()-1);
			sql += "where id = ? ";
 			this.ps = this.conn.prepareStatement(sql);
			this.ps.setString(1, oWithdraw.getId());
			if(this.ps.executeUpdate() > 0)
				flag = true;
		}catch (Exception e) {
			throw e;
		}
		return flag;
		
	}

	@Override
	public WithdrawBean findWithdrawById(String id) throws Exception {
		WithdrawBean withdrawBean = null;
		String sql = "select id,userid,money,userplatid,paytime,platformid,platname from withdraw where id = ?";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, id);
		ResultSet rs = this.ps.executeQuery();
		if (rs.next()) {
			withdrawBean = new WithdrawBean();
			withdrawBean.setId(rs.getString(1));
			withdrawBean.setUserid(rs.getString(2));
			withdrawBean.setMoney(rs.getDouble(3));
			withdrawBean.setUserplatid(rs.getString(4));
			withdrawBean.setPaytime(rs.getDate(5));
			withdrawBean.setPlatformid(rs.getString(6));
			withdrawBean.setPlatname(rs.getString(7));
		}
		rs.close();
		this.ps.close();
		return withdrawBean;
	}

	@Override
	public List<WithdrawBean> findWithdrawByUserId(String userid) throws Exception {
		WithdrawBean withdrawBean = null;
		List<WithdrawBean> all = new ArrayList<WithdrawBean>();
		String sql = "select id,userid,money,userplatid,paytime,platformid,platname from withdraw where userid = ?";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, userid);
		ResultSet rs = this.ps.executeQuery();
		while(rs.next()){
			withdrawBean = new WithdrawBean();
			withdrawBean.setId(rs.getString(1));
			withdrawBean.setUserid(rs.getString(2));
			withdrawBean.setMoney(rs.getDouble(3));
			withdrawBean.setUserplatid(rs.getString(4));
			withdrawBean.setPaytime(rs.getDate(5));
			withdrawBean.setPlatformid(rs.getString(6));
			withdrawBean.setPlatname(rs.getString(7));
			all.add(withdrawBean);
		}
		rs.close();
		this.ps.close();
		return all;
	}

	@Override
	public WithdrawBean findWithdrawByPlatId(String platid) throws Exception {
		WithdrawBean withdrawBean = null;
		String sql = "select id,userid,money,userplatid,paytime,platformid,platname from withdraw where platformid = ?";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, platid);
		ResultSet rs = this.ps.executeQuery();
		if (rs.next()) {
			withdrawBean = new WithdrawBean();
			withdrawBean.setId(rs.getString(1));
			withdrawBean.setUserid(rs.getString(2));
			withdrawBean.setMoney(rs.getDouble(3));
			withdrawBean.setUserplatid(rs.getString(4));
			withdrawBean.setPaytime(rs.getDate(5));
			withdrawBean.setPlatformid(rs.getString(6));
			withdrawBean.setPlatname(rs.getString(7));
		}
		rs.close();
		this.ps.close();
		return withdrawBean;
	}

	@Override
	public List<WithdrawBean> findWithdrawByPlatName(String name) throws Exception {
		WithdrawBean withdrawBean = null;
		List<WithdrawBean> all = new ArrayList<WithdrawBean>();
		String sql = "select id,userid,money,userplatid,paytime,platformid,platname from withdraw where platname = ?";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, name);
		ResultSet rs = this.ps.executeQuery();
		while(rs.next()){
			withdrawBean = new WithdrawBean();
			withdrawBean.setId(rs.getString(1));
			withdrawBean.setUserid(rs.getString(2));
			withdrawBean.setMoney(rs.getDouble(3));
			withdrawBean.setUserplatid(rs.getString(4));
			withdrawBean.setPaytime(rs.getDate(5));
			withdrawBean.setPlatformid(rs.getString(6));
			withdrawBean.setPlatname(rs.getString(7));
			all.add(withdrawBean);
		}
		rs.close();
		this.ps.close();
		return all;
	}
}
