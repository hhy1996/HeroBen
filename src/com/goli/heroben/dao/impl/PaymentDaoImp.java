package com.goli.heroben.dao.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.goli.heroben.dao.IPaymentDao;
import com.goli.heroben.vo.PaymentBean;

public class PaymentDaoImp implements IPaymentDao {
	private Connection conn = null;// 数据库连接对象
	private PreparedStatement ps = null;// 数据库操作对象
	
	public PaymentDaoImp(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public PaymentBean findPaymentById(String id) throws Exception {
		PaymentBean pay = null;
		String sql = "select id, userid, money, paytime, platformid, "
				+ "platname,url from payment where id = ?";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, id);
		ResultSet rs = this.ps.executeQuery();
		while(rs.next()){
			pay = new PaymentBean();
			pay.setId(rs.getString(1));
			pay.setUserid(rs.getString(2));
			pay.setMoney(rs.getDouble(3));
			pay.setPaytime(rs.getTimestamp(4));
			pay.setPlatformid(rs.getString(5));
			pay.setPlatname(rs.getString(6));
			pay.setUrl(rs.getString(7));
		}
		rs.close();
		this.ps.close();
		return pay;
	}

	@Override
	public List<PaymentBean> findPaymentByUserid(String userid) throws Exception {
		PaymentBean pay = null;
		List<PaymentBean> all = new ArrayList<PaymentBean>();
		String sql = "select id, userid, money, paytime, platformid, "
				+ "platname,url from payment where userid = ?";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, userid);
		ResultSet rs = this.ps.executeQuery();
		while(rs.next()){
			pay = new PaymentBean();
			pay.setId(rs.getString(1));
			pay.setUserid(rs.getString(2));
			pay.setMoney(rs.getDouble(3));
			pay.setPaytime(rs.getTimestamp(4));
			pay.setPlatformid(rs.getString(5));
			pay.setPlatname(rs.getString(6));
			pay.setUrl(rs.getString(7));
			all.add(pay);
		}
		rs.close();
		this.ps.close();
		return all;
	}

	@Override
	public PaymentBean findPaymentByPlatformId(String id) throws Exception {
		PaymentBean pay = null;
		String sql = "select id, userid, money, paytime, platformid, "
				+ "platname,url from payment where platformid = ?";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, id);
		ResultSet rs = this.ps.executeQuery();
		while(rs.next()){
			pay = new PaymentBean();
			pay.setId(rs.getString(1));
			pay.setUserid(rs.getString(2));
			pay.setMoney(rs.getDouble(3));
			pay.setPaytime(rs.getTimestamp(4));
			pay.setPlatformid(rs.getString(5));
			pay.setPlatname(rs.getString(6));
			pay.setUrl(rs.getString(7));
		}
		rs.close();
		this.ps.close();
		return pay;
	}

	@Override
	public boolean doCreate(PaymentBean paymentBean) throws Exception {
		boolean flag = false;
		String sql = "insert into payment (id, userid, money, paytime,"
				+ "platformid, platname, url) values (?, ?, ?, ?, ?, ?, ?)";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, paymentBean.getId());
		this.ps.setString(2, paymentBean.getUserid());
		this.ps.setDouble(3, paymentBean.getMoney());
		this.ps.setTimestamp(4, new java.sql.Timestamp(paymentBean.getPaytime().getTime()));
		this.ps.setString(5, paymentBean.getPlatformid());
		this.ps.setString(6, paymentBean.getPlatname());
		this.ps.setString(7, paymentBean.getUrl());
		if(this.ps.executeUpdate() > 0)
			flag = true;
		this.ps.close();
		return flag;
	}

	@Override
	public boolean doRemoveById(String id) throws Exception {
		boolean flag = false;
		String sql = "delete from payment where id = ?";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, id);
		if(this.ps.executeUpdate() > 0)
			flag = true;
		this.ps.close();
		return flag;
	}

	@Override
	public boolean doRemoveByUserid(String userid) throws Exception {
		boolean flag = false;
		String sql = "delete from payment where userid = ?";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, userid);
		if(this.ps.executeUpdate() > 0)
			flag = true;
		this.ps.close();
		return flag;
	}

	@Override
	public boolean doRemoveByPlatformId(String id) throws Exception {
		boolean flag = false;
		String sql = "delete from payment where platformid = ?";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, id);
		if(this.ps.executeUpdate() > 0)
			flag = true;
		this.ps.close();
		return flag;
	}

	@Override
	public boolean doUpdateId(String oId, String nId) throws Exception {
		boolean flag = false;
		String sql = "update payment set id = ? where id = ?";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, nId);
		this.ps.setString(2, oId);
		if(this.ps.executeUpdate() > 0)
			flag = true;
		this.ps.close();
		return flag;
	}

	@Override
	public boolean doUpdateUserid(String id, String nUserid) throws Exception {
		boolean flag = false;
		String sql = "update payment set userid = ? where id = ?";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, nUserid);
		this.ps.setString(2, id);
		if(this.ps.executeUpdate() > 0)
			flag = true;
		this.ps.close();
		return flag;
	}

	@Override
	public boolean doUpdateMoney(String id, String nMoney) throws Exception {
		boolean flag = false;
		String sql = "update payment set money = ? where id = ?";
		this.ps = this.conn.prepareStatement(sql);
		double money = Double.parseDouble(nMoney);
		this.ps.setDouble(1, money);
		this.ps.setString(2, id);
		if(this.ps.executeUpdate() > 0)
			flag = true;
		this.ps.close();
		return flag;
	}

	@Override
	public boolean doUpdatePayTime(String id, String nTime) throws Exception {
		boolean flag = false;
		String sql = "update payment set paytime = ? where id = ?";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");  
	    Date time = sdf.parse(nTime); 
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setTimestamp(1, new java.sql.Timestamp(time.getTime()));
		this.ps.setString(2, id);
		if(this.ps.executeUpdate() > 0)
			flag = true;
		this.ps.close();
		return flag;
	}

	@Override
	public boolean doUpdatePlatformId(String id, String nid) throws Exception {
		boolean flag = false;
		String sql = "update payment set platformid = ? where id = ?";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, nid);
		this.ps.setString(2, id);
		if(this.ps.executeUpdate() > 0)
			flag = true;
		this.ps.close();
		return flag;
	}

	@Override
	public boolean doUpdatePlatName(String id, String nName) throws Exception {
		boolean flag = false;
		String sql = "update payment set platname = ? where id = ?";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, nName);
		this.ps.setString(2, id);
		if(this.ps.executeUpdate() > 0)
			flag = true;
		this.ps.close();
		return flag;
	}

	@Override
	public boolean doUpdateAll(PaymentBean oPayment, PaymentBean nPayment) throws Exception {
		boolean flag = false;
		Field[] field = PaymentBean.class.getDeclaredFields();
		String sql = "update payment set ";
		try{
			for(Field f:field){
				f.setAccessible(true);
				Object v1 = f.get(oPayment);
				Object v2 = f.get(nPayment);
				if (v1 != null && !v1.equals(v2) && v2 != null) {
					sql += f.getName() + " = '" + v2.toString() + "'  ,";
				}
			}
			sql = sql.substring(0, sql.length()-1);
			sql += "where id = ? ";
 			this.ps = this.conn.prepareStatement(sql);
			this.ps.setString(1, oPayment.getId());
			if(this.ps.executeUpdate() > 0)
				flag = true;
		}catch (Exception e) {
			throw e;
		}
		return flag;
	}

	@Override
	public List<PaymentBean> findPaymentByPlatformName(String name) throws Exception {
		PaymentBean pay = null;
		List<PaymentBean> all = new ArrayList<PaymentBean>();
		String sql = "select id, userid, money, paytime, platformid, "
				+ "platname from payment where platname = ?";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, name);
		ResultSet rs = this.ps.executeQuery();
		while(rs.next()){
			pay = new PaymentBean();
			pay.setId(rs.getString(1));
			pay.setUserid(rs.getString(2));
			pay.setMoney(rs.getDouble(3));
			pay.setPaytime(rs.getTimestamp(4));
			pay.setPlatformid(rs.getString(5));
			pay.setPlatname(rs.getString(6));
			all.add(pay);
		}
		rs.close();
		this.ps.close();
		return all;
	}
}
