package com.goli.heroben.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import java.lang.reflect.Field;

import com.goli.heroben.dao.ITaskTypeDao;
import com.goli.heroben.vo.TaskTypeBean;


public class TaskTypeDaoImp implements ITaskTypeDao {
	private Connection conn = null;// 数据库连接对象
	private PreparedStatement ps = null;// 数据库操作对象

	public TaskTypeDaoImp(Connection conn)
	{
		this.conn = conn;
	}
	
	@Override
	public boolean doCreate(TaskTypeBean taskTypeBean) throws Exception {
		boolean flag = false;
		String sql = "insert into tasktype (id,typename,createdby,createtime) values(?,?,?,?)";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, taskTypeBean.getId());
		this.ps.setString(2, taskTypeBean.getTypename());
		this.ps.setString(3, taskTypeBean.getCreatedby());
		this.ps.setDate(4, new java.sql.Date(taskTypeBean.getCreatetime().getTime()));
		if (this.ps.executeUpdate() > 0)
		{
			flag = true;
		}
		
		this.ps.close();
		return flag;
	}

	@Override
	public boolean doRemoveById(int id) throws Exception {
		boolean flag =false;
	    String sql = "delete from tasktype where id like ?";
	    this.ps = this.conn.prepareStatement(sql);
	    this.ps.setInt(1, id);
	    if (this.ps.executeUpdate() > 0) {
			flag = true;
		}
		this.ps.close();
		return flag;
	}

	@Override
	public boolean doRemoveByName(String typeName) throws Exception {
		boolean flag =false;
	    String sql = "delete from tasktype where typename like ?";
	    this.ps = this.conn.prepareStatement(sql);
	    this.ps.setString(1, "%" + typeName + "%");
	    if (this.ps.executeUpdate() > 0) {
			flag = true;
		}
		this.ps.close();
		return flag;
	}

	@Override
	public boolean doRemoveByUserid(String userId) throws Exception {
		boolean flag =false;
	    String sql = "delete from tasktype where createdby like ?";
	    this.ps = this.conn.prepareStatement(sql);
	    this.ps.setString(1, "%" + userId + "%");
	    if (this.ps.executeUpdate() > 0) {
			flag = true;
		}
		this.ps.close();
		return flag;
	}

	@Override
	public boolean doUpdateId(int oId, int nId) throws Exception {
		boolean flag =false;
		String sql = "update tasktype set id ='" + nId +"' where id = '" + oId+"' ";
	   	this.ps=this.conn.prepareStatement(sql);
	   	
	   	if(ps.executeUpdate()>0)
	   	{ flag = true;}
	   	this.ps.close();
		return flag;
	}

	@Override
	public boolean doUpdateName(int id, String nName) throws Exception {
		boolean flag =false;
		String sql = "update tasktype set typename ='" + nName +"' where  id = '" + id +"' ";
	   	this.ps=this.conn.prepareStatement(sql);
	   	
	   	if(ps.executeUpdate()>0)
	   	{ flag = true;}
	   	this.ps.close();
		return flag;
	}

	@Override
	public boolean doUpdateUser(String oUser, String nUser) throws Exception {
		boolean flag =false;
		String sql = "update tasktype set createdby ='" + nUser +"' where createdby = '" + oUser +"' ";
		this.ps=this.conn.prepareStatement(sql);
	   	
	   	if(ps.executeUpdate()>0)
	   	{ flag = true;}
	   	this.ps.close();
		return flag;
	
	}

	@Override
	public boolean doUpdateTime(int id, Date nTime) throws Exception {
		boolean flag =false;
		String sql = "update tasktype set createtime = ? where id ='" + id +"' ";
	   	this.ps=this.conn.prepareStatement(sql);
	   	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = df.format(nTime); 
		java.util.Date time = df.parse(str);
	   	java.sql.Timestamp sqlDate = new java.sql.Timestamp(time.getTime());
	   	ps.setTimestamp(1,sqlDate );
	   	if(ps.executeUpdate()>0)
	   	{ flag = true;}
	   	this.ps.close();
		return flag;
	}

	@Override
	public TaskTypeBean findTaskTypeById(int id) throws Exception {
		TaskTypeBean tasktypebean = null;
		String sql = "select id,typename,createdby,createtime from tasktype where id like ?";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, id);
		ResultSet rs = this.ps.executeQuery();
		if (rs.next()) {
			tasktypebean = new TaskTypeBean();
			tasktypebean.setId(rs.getInt(1));
			tasktypebean.setTypename(rs.getString(2));
			tasktypebean.setCreatedby(rs.getString(3));
			tasktypebean.setCreatetime(rs.getDate(4));	
		}
		rs.close();
		this.ps.close();
		
		return tasktypebean;
	}
 
	@Override
	public boolean doUpdateAll(TaskTypeBean oType, TaskTypeBean nType) throws Exception {
		boolean flag =false;
		int oid = oType.getId();
		int nid = nType.getId();
		String typename = nType.getTypename();
		String createdby = nType.getCreatedby();
		Date createtime = nType.getCreatetime();
		
		String sql = "update tasktype set id = '" + nid +"',typename = '" + typename +"',createdby = '" + createdby +"',createtime = ? where id ='" + oid +"' ";
		this.ps=this.conn.prepareStatement(sql);
	   	
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = df.format(createtime); 
		java.util.Date time = df.parse(str);
	   	java.sql.Timestamp sqlDate = new java.sql.Timestamp(time.getTime());
	   	ps.setTimestamp(1,sqlDate );
		
		/*Field[] field = TaskTypeBean.class.getDeclaredFields();
		String sql = "update tasktype set ";
		
		for(Field f:field)
		{
			f.setAccessible(true);
			Object v1 = f.get(oType);
			Object v2 = f.get(nType);
			if(v1 !=null && !v1.equals(v2)&&v2!=null)
			{
				sql+= f.getName()+"='"+v2.toString()+"',";
			}
		}
		sql = sql.substring(0, sql.length()-1);
		sql+=" where id = ?";
		
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setInt(1, oType.getId());*/
		
	   	if( this.ps.executeUpdate()>0)
		{  flag = true;}

		this.ps.close();
		return flag;
		
		
	
	}

	@Override
	public List<TaskTypeBean> findTaskTypeByUser(String userid) throws Exception {
		List<TaskTypeBean> all = new ArrayList<TaskTypeBean>();
		String sql = "select id,typename,createdby,createtime from tasktype where createdby like ?";
		this.ps = this.conn.prepareStatement(sql);
		this.ps.setString(1, "%" + userid + "%");
		ResultSet rs = this.ps.executeQuery();
		TaskTypeBean taskTypeBean = null;
		while (rs.next())
		{
			taskTypeBean = new TaskTypeBean();
			taskTypeBean.setId(rs.getInt(1));
			taskTypeBean.setTypename(rs.getString(2));
			taskTypeBean.setCreatedby(rs.getString(3));
			taskTypeBean.setCreatetime(rs.getDate(4));
			all.add(taskTypeBean);
		}
		rs.close();
		this.ps.close();
		return all;
	}

	@Override
	public List<String> findAllTypeName() throws Exception {
		List<String> all = new ArrayList<String>();
		String sql = "select typename from tasktype ";
		this.ps = this.conn.prepareStatement(sql);
		ResultSet rs = this.ps.executeQuery();
		String str = null;	
		while (rs.next())
		{
			str = rs.getString(1);
			all.add(str);
		}
		rs.close();
		this.ps.close();
		return all;
	}

}
