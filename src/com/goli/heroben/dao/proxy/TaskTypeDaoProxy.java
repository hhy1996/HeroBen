package com.goli.heroben.dao.proxy;

import java.util.Date;
import java.util.List;

//import com.goli.heroben.dao.IPaymentDao;
import com.goli.heroben.dao.ITaskTypeDao;
import com.goli.heroben.dao.impl.TaskTypeDaoImp;
import com.goli.heroben.dbc.DataBaseConnection;
import com.goli.heroben.vo.TaskTypeBean;

public class TaskTypeDaoProxy implements ITaskTypeDao {
	private DataBaseConnection dbc = null;
	private ITaskTypeDao dao = null;

	public TaskTypeDaoProxy() throws Exception {
		this.dbc = new DataBaseConnection();
		this.dao = new TaskTypeDaoImp(this.dbc.getConnection());
	}
	
	@Override
	public boolean doCreate(TaskTypeBean taskTypeBean) throws Exception {
		boolean flag = true;
		try {
			if (this.dao.findTaskTypeById(taskTypeBean.getId()) == null)
		    	flag = this.dao.doCreate(taskTypeBean);
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
		return flag;
		
	}

	@Override
	public boolean doRemoveById(int id) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.doRemoveById(id);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doRemoveByName(String typeName) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.doRemoveByName(typeName);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doRemoveByUserid(String userId) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.doRemoveByUserid(userId);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doUpdateId(int oId, int nId) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.doUpdateId(oId, nId);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doUpdateName(int id, String nName) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.doUpdateName( id,  nName);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doUpdateUser(String oUser, String nUser) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.doUpdateUser(oUser,nUser);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doUpdateTime(int id, Date nTime) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.doUpdateTime(id,nTime);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public TaskTypeBean findTaskTypeById(int id) throws Exception {
		TaskTypeBean taskTypeBean = null;
		try {
			taskTypeBean = this.dao.findTaskTypeById(id);
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		} 
		return taskTypeBean;
	}

	@Override
	public boolean doUpdateAll(TaskTypeBean oType, TaskTypeBean nType) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.doUpdateAll(oType, nType);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public List<TaskTypeBean> findTaskTypeByUser(String userid) throws Exception {
		List<TaskTypeBean> all = null;
		try {
			all = this.dao.findTaskTypeByUser(userid);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return all;
	}

	@Override
	public List<String> findAllTypeName() throws Exception {
		List<String> all = null;
		try {
			all = this.dao.findAllTypeName();
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return all;
	}

}
