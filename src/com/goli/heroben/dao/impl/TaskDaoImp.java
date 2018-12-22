package com.goli.heroben.dao.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.goli.heroben.dao.ITaskDao;
import com.goli.heroben.vo.PaymentBean;
import com.goli.heroben.vo.TaskBean;

public class TaskDaoImp implements ITaskDao {
	private Connection conn = null;// 数据库连接对象
	private PreparedStatement ps = null;// 数据库操作对象

	public TaskDaoImp(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean doCreate(TaskBean taskBean) throws Exception {
		boolean flag = false;
		String sql = "insert into task (id,pubid,pubtime,deadtime,paytype,title,content,"
				+ "imgsrc,cost,tasktype,state) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, taskBean.getId());
		this.ps.setString(2, taskBean.getPubid());
		this.ps.setTimestamp(3, new java.sql.Timestamp(taskBean.getPubTime().getTime()));
		this.ps.setTimestamp(4, new java.sql.Timestamp(taskBean.getDeadTime().getTime()));
		this.ps.setString(5, taskBean.getPayType());
		this.ps.setString(6, taskBean.getTitle());
		this.ps.setString(7, taskBean.getContent());
		this.ps.setString(8, taskBean.getImgSrc());
		this.ps.setString(9, taskBean.getCost());
		this.ps.setString(10, taskBean.getTaskType());
		this.ps.setString(11, taskBean.getState());
		if (this.ps.executeUpdate() > 0)
			flag = true;
		this.ps.close();
		return flag;
	}

	@Override
	public boolean doRemoveById(String id) throws Exception {
		boolean flag = false;
		String sql = "delete from task where id = ?";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, id);
		if (this.ps.executeUpdate() > 0)
			flag = true;
		this.ps.close();
		return flag;
	}

	@Override
	public boolean doRemoveByPubId(String id) throws Exception {
		boolean flag = false;
		String sql = "delete from task where pubid = ?";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, id);
		if (this.ps.executeUpdate() > 0)
			flag = true;
		this.ps.close();
		return flag;
	}

	public boolean doUpdateOneLine(String findKey, String findValue, String updateKey, String updateValue)
			throws Exception {
		boolean flag = false;
		String sql = "update task set %UPDATEKEY% = ? where %FINDKEY% = ?";
		sql = sql.replace("%UPDATEKEY%", updateKey);
		sql = sql.replace("%FINDKEY%", findKey);
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, updateValue);
		this.ps.setString(2, findValue);

		if (this.ps.executeUpdate() > 0)
			flag = true;
		this.ps.close();
		return flag;
	}

	@Override
	public boolean doUpdateId(String oId, String nId) throws Exception {
		return this.doUpdateOneLine("id", oId, "id", nId);
	}

	@Override
	public boolean doUpdatePubId(String oId, String nId) throws Exception {
		return this.doUpdateOneLine("pubid", oId, "pubid", nId);
	}

	@Override
	public boolean doUpdateRevId(String oId, String nId) throws Exception {
		return this.doUpdateOneLine("revid", oId, "revid", nId);
	}

	@Override
	public boolean doUpdatePubTime(String id, Date nTime) throws Exception {
		String time;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		time = format.format(nTime);
		return this.doUpdateOneLine("id", id, "pubtime", time);
	}

	@Override
	public boolean doUpdateDeadTime(String id, Date nTime) throws Exception {
		String time;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		time = format.format(nTime);
		return this.doUpdateOneLine("id", id, "deadtime", time);
	}

	@Override
	public boolean doUpdateRevTime(String id, Date nTime) throws Exception {
		String time;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		time = format.format(nTime);
		return this.doUpdateOneLine("id", id, "revtime", time);
	}

	@Override
	public boolean doUpdateFinTime(String id, Date nTime) throws Exception {
		String time;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		time = format.format(nTime);
		return this.doUpdateOneLine("id", id, "fintime", time);
	}

	@Override
	public boolean doUpdataPayType(String id, String nType) throws Exception {
		return this.doUpdateOneLine("id", id, "paytype", nType);
	}

	@Override
	public boolean doUpdateTitle(String id, String nTitle) throws Exception {
		return this.doUpdateOneLine("id", id, "title", nTitle);
	}

	@Override
	public boolean doUpdateContent(String id, String nContent) throws Exception {
		return this.doUpdateOneLine("id", id, "content", nContent);
	}

	@Override
	public boolean doUpdateImgSrc(String id, String nSrc) throws Exception {
		return this.doUpdateOneLine("id", id, "imgsrc", nSrc);
	}

	@Override
	public boolean doUpdateCost(String id, String nCost) throws Exception {
		return this.doUpdateOneLine("id", id, "cost", nCost);
	}

	@Override
	public boolean doUpdateTaskType(String id, String nType) throws Exception {
		return this.doUpdateOneLine("id", id, "tasktype", nType);
	}

	@Override
	public boolean doUpdateScore(String id, double nScore) throws Exception {
		return this.doUpdateOneLine("id", id, "score", String.valueOf(nScore));
	}

	@Override
	public boolean doUpdateState(String id, String nState) throws Exception {
		return this.doUpdateOneLine("id", id, "state", nState);
	}

	@Override
	public boolean doUpdatePayId(String id, String nPayId) throws Exception {
		return this.doUpdateOneLine("id", id, "payid", nPayId);
	}

	@Override
	public TaskBean findTaskById(String id) throws Exception {
		TaskBean taskBean = null;
		String sql = "select id,pubid,revid,pubtime,deadtime,revtime,fintime,paytype,title,content,imgsrc,cost,tasktype,score,state,payid from task where id = ?";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, id);
		ResultSet rs = this.ps.executeQuery();
		while (rs.next()) {
			taskBean = new TaskBean();
			taskBean.setId(rs.getString(1));
			taskBean.setPubid(rs.getString(2));
			taskBean.setRevid(rs.getString(3));
			taskBean.setPubTime(rs.getTimestamp(4));
			taskBean.setDeadTime(rs.getTimestamp(5));
			taskBean.setRevTime(rs.getTimestamp(6));
			taskBean.setFinTime(rs.getTimestamp(7));
			taskBean.setPayType(rs.getString(8));
			taskBean.setTitle(rs.getString(9));
			taskBean.setContent(rs.getString(10));
			taskBean.setImgSrc(rs.getString(11));
			taskBean.setCost(rs.getString(12));
			taskBean.setTaskType(rs.getString(13));
			taskBean.setScore(rs.getDouble(14));
			taskBean.setState(rs.getString(15));
			taskBean.setPayId(rs.getString(16));
		}
		rs.close();
		this.ps.close();
		return taskBean;
	}

	@Override
	public TaskBean findTaskByPayId(String payId) throws Exception {
		TaskBean taskBean = null;
		String sql = "select id,pubid,revid,pubtime,deadtime,revtime,fintime,paytype,title,content,imgsrc,cost,tasktype,score,state,payid from task where payid = ?";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, payId);
		ResultSet rs = this.ps.executeQuery();
		while (rs.next()) {
			taskBean = new TaskBean();
			taskBean.setId(rs.getString(1));
			taskBean.setPubid(rs.getString(2));
			taskBean.setRevid(rs.getString(3));
			taskBean.setPubTime(rs.getTimestamp(4));
			taskBean.setDeadTime(rs.getTimestamp(5));
			taskBean.setRevTime(rs.getTimestamp(6));
			taskBean.setFinTime(rs.getTimestamp(7));
			taskBean.setPayType(rs.getString(8));
			taskBean.setTitle(rs.getString(9));
			taskBean.setContent(rs.getString(10));
			taskBean.setImgSrc(rs.getString(11));
			taskBean.setCost(rs.getString(12));
			taskBean.setTaskType(rs.getString(13));
			taskBean.setScore(rs.getDouble(14));
			taskBean.setState(rs.getString(15));
			taskBean.setPayId(rs.getString(16));
		}
		rs.close();
		this.ps.close();
		return taskBean;
	}

	@Override
	public List<TaskBean> findTaskByPubId(String pubId) throws Exception {
		List<TaskBean> taskList = new ArrayList<TaskBean>();
		String sql = "select id,pubid,revid,pubtime,deadtime,revtime,fintime,paytype,title,content,imgsrc,cost,tasktype,score,state,payid from task where pubid = ?";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, pubId);
		ResultSet rs = this.ps.executeQuery();
		while (rs.next()) {
			TaskBean taskBean = new TaskBean();
			taskBean.setId(rs.getString(1));
			taskBean.setPubid(rs.getString(2));
			taskBean.setRevid(rs.getString(3));
			taskBean.setPubTime(rs.getTimestamp(4));
			taskBean.setDeadTime(rs.getTimestamp(5));
			taskBean.setRevTime(rs.getTimestamp(6));
			taskBean.setFinTime(rs.getTimestamp(7));
			taskBean.setPayType(rs.getString(8));
			taskBean.setTitle(rs.getString(9));
			taskBean.setContent(rs.getString(10));
			taskBean.setImgSrc(rs.getString(11));
			taskBean.setCost(rs.getString(12));
			taskBean.setTaskType(rs.getString(13));
			taskBean.setScore(rs.getDouble(14));
			taskBean.setState(rs.getString(15));
			taskBean.setPayId(rs.getString(16));
			taskList.add(taskBean);
		}
		rs.close();
		this.ps.close();
		return taskList;
	}

	@Override
	public List<TaskBean> findTaskByRevId(String revId) throws Exception {
		List<TaskBean> taskList = new ArrayList<TaskBean>();
		String sql = "select id,pubid,revid,pubtime,deadtime,revtime,fintime,paytype,title,content,imgsrc,cost,tasktype,score,state,payid from task where revid = ?";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, revId);
		ResultSet rs = this.ps.executeQuery();
		while (rs.next()) {
			TaskBean taskBean = new TaskBean();
			taskBean.setId(rs.getString(1));
			taskBean.setPubid(rs.getString(2));
			taskBean.setRevid(rs.getString(3));
			taskBean.setPubTime(rs.getTimestamp(4));
			taskBean.setDeadTime(rs.getTimestamp(5));
			taskBean.setRevTime(rs.getTimestamp(6));
			taskBean.setFinTime(rs.getTimestamp(7));
			taskBean.setPayType(rs.getString(8));
			taskBean.setTitle(rs.getString(9));
			taskBean.setContent(rs.getString(10));
			taskBean.setImgSrc(rs.getString(11));
			taskBean.setCost(rs.getString(12));
			taskBean.setTaskType(rs.getString(13));
			taskBean.setScore(rs.getDouble(14));
			taskBean.setState(rs.getString(15));
			taskBean.setPayId(rs.getString(16));
			taskList.add(taskBean);
		}
		rs.close();
		this.ps.close();
		return taskList;
	}

	@Override
	public List<TaskBean> findTaskByState(String state) throws Exception {
		List<TaskBean> taskList = new ArrayList<TaskBean>();
		String sql = "select id,pubid,revid,pubtime,deadtime,revtime,fintime,paytype,title,content,imgsrc,cost,tasktype,score,state,payid from task where state = ?";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, state);
		ResultSet rs = this.ps.executeQuery();
		while (rs.next()) {
			TaskBean taskBean = new TaskBean();
			taskBean.setId(rs.getString(1));
			taskBean.setPubid(rs.getString(2));
			taskBean.setRevid(rs.getString(3));
			taskBean.setPubTime(rs.getTimestamp(4));
			taskBean.setDeadTime(rs.getTimestamp(5));
			taskBean.setRevTime(rs.getTimestamp(6));
			taskBean.setFinTime(rs.getTimestamp(7));
			taskBean.setPayType(rs.getString(8));
			taskBean.setTitle(rs.getString(9));
			taskBean.setContent(rs.getString(10));
			taskBean.setImgSrc(rs.getString(11));
			taskBean.setCost(rs.getString(12));
			taskBean.setTaskType(rs.getString(13));
			taskBean.setScore(rs.getDouble(14));
			taskBean.setState(rs.getString(15));
			taskBean.setPayId(rs.getString(16));
			taskList.add(taskBean);
		}
		rs.close();
		this.ps.close();
		return taskList;
	}

	@Override
	public boolean doUpdateAll(TaskBean oTask, TaskBean nTask) throws Exception {
		boolean flag = false;
		Field[] field = PaymentBean.class.getDeclaredFields();
		String sql = "update task set ";
		try {
			for (Field f : field) {
				f.setAccessible(true);
				Object v1 = f.get(oTask);
				Object v2 = f.get(nTask);
				if (v1 != null && !v1.equals(v2) && v2 != null) {
					sql += f.getName() + " = '" + v2.toString() + "'  ,";
				}
			}
			sql = sql.substring(0, sql.length() - 1);
			sql += "where id = ? ";
			this.ps = this.conn.prepareStatement(sql);
			this.ps.setString(1, oTask.getId());
			if (this.ps.executeUpdate() > 0)
				flag = true;
		} catch (Exception e) {
			throw e;
		}
		return flag;
	}

	@Override
	public List<TaskBean> findTaskByStateLimit(String state, int start, int end) throws Exception {
		List<TaskBean> taskList = new ArrayList<TaskBean>();
		String sql = "select id,pubid,revid,pubtime,deadtime,revtime,fintime,paytype,title,content,imgsrc,cost,tasktype,score,state,payid from task where state = ? limit ?,?;";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, state);
		this.ps.setInt(2, start);
		this.ps.setInt(3, end);
		ResultSet rs = this.ps.executeQuery();
		while (rs.next()) {
			TaskBean taskBean = new TaskBean();
			taskBean.setId(rs.getString(1));
			taskBean.setPubid(rs.getString(2));
			taskBean.setRevid(rs.getString(3));
			taskBean.setPubTime(rs.getTimestamp(4));
			taskBean.setDeadTime(rs.getTimestamp(5));
			taskBean.setRevTime(rs.getTimestamp(6));
			taskBean.setFinTime(rs.getTimestamp(7));
			taskBean.setPayType(rs.getString(8));
			taskBean.setTitle(rs.getString(9));
			taskBean.setContent(rs.getString(10));
			taskBean.setImgSrc(rs.getString(11));
			taskBean.setCost(rs.getString(12));
			taskBean.setTaskType(rs.getString(13));
			taskBean.setScore(rs.getDouble(14));
			taskBean.setState(rs.getString(15));
			taskBean.setPayId(rs.getString(16));
			taskList.add(taskBean);
		}
		rs.close();
		this.ps.close();
		return taskList;
	}

	@Override
	public int getTaskCount(String state) throws Exception {
		int result = -1;
		String sql = "select count(id) from task where state = ?;";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, state);
		ResultSet rs = this.ps.executeQuery();
		if (rs.next()) {
			result = rs.getInt(0);
		}
		rs.close();
		this.ps.close();
		return result;
	}

	@Override
	public boolean doUpdateRevIdById(String oId, String nId) throws Exception {
		return this.doUpdateOneLine("id", oId, "revid", nId);
	}

	@Override
	public List<TaskBean> findTaskAll() throws Exception {
		List<TaskBean> taskList = new ArrayList<TaskBean>();
		String sql = "select id,pubid,revid,pubtime,deadtime,revtime,fintime,paytype,title,content,imgsrc,cost,tasktype,score,state,payid from task";
		this.ps = this.conn.prepareStatement(sql);
		ResultSet rs = this.ps.executeQuery();
		while (rs.next()) {
			TaskBean taskBean = new TaskBean();
			taskBean.setId(rs.getString(1));
			taskBean.setPubid(rs.getString(2));
			taskBean.setRevid(rs.getString(3));
			taskBean.setPubTime(rs.getTimestamp(4));
			taskBean.setDeadTime(rs.getTimestamp(5));
			taskBean.setRevTime(rs.getTimestamp(6));
			taskBean.setFinTime(rs.getTimestamp(7));
			taskBean.setPayType(rs.getString(8));
			taskBean.setTitle(rs.getString(9));
			taskBean.setContent(rs.getString(10));
			taskBean.setImgSrc(rs.getString(11));
			taskBean.setCost(rs.getString(12));
			taskBean.setTaskType(rs.getString(13));
			taskBean.setScore(rs.getDouble(14));
			taskBean.setState(rs.getString(15));
			taskBean.setPayId(rs.getString(16));
			taskList.add(taskBean);
		}
		rs.close();
		this.ps.close();
		return taskList;
	}
}