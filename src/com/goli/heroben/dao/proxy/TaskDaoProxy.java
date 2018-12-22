package com.goli.heroben.dao.proxy;

import java.util.Date;
import java.util.List;

import com.goli.heroben.dao.ITaskDao;
import com.goli.heroben.dao.impl.TaskDaoImp;
import com.goli.heroben.dbc.DataBaseConnection;
import com.goli.heroben.vo.TaskBean;

public class TaskDaoProxy implements ITaskDao {
	private DataBaseConnection dbc = null;
	private ITaskDao dao = null;

	public TaskDaoProxy() throws Exception {
		this.dbc = new DataBaseConnection();
		this.dao = new TaskDaoImp(dbc.getConnection());
	}

	@Override
	public boolean doCreate(TaskBean taskBean) throws Exception {
		boolean flag = false;
		try {
			if (this.dao.findTaskById(taskBean.getId()) == null) {
				flag = this.dao.doCreate(taskBean);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doRemoveById(String id) throws Exception {
		boolean flag = false;
		try {
			if (this.dao.findTaskById(id) != null) {
				flag = this.dao.doRemoveById(id);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doRemoveByPubId(String id) throws Exception {
		boolean flag = false;
		try {
			if (this.dao.findTaskByPubId(id) != null) {
				flag = this.dao.doRemoveByPubId(id);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doUpdateId(String oId, String nId) throws Exception {
		boolean flag = false;
		try {
			if (this.dao.findTaskById(oId) != null) {
				flag = this.dao.doUpdateId(oId, nId);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doUpdatePubId(String oId, String nId) throws Exception {
		boolean flag = false;
		try {
			if (this.dao.findTaskByPubId(oId) != null) {
				flag = this.dao.doUpdatePubId(oId, nId);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doUpdateRevId(String oId, String nId) throws Exception {
		boolean flag = false;
		try {
			if (this.dao.findTaskByRevId(oId) != null) {
				flag = this.dao.doUpdateRevId(oId, nId);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doUpdatePubTime(String id, Date nTime) throws Exception {
		boolean flag = false;
		try {
			if (this.dao.findTaskById(id) != null) {
				flag = this.dao.doUpdatePubTime(id, nTime);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doUpdateDeadTime(String id, Date nTime) throws Exception {
		boolean flag = false;
		try {
			if (this.dao.findTaskById(id) != null) {
				flag = this.dao.doUpdateDeadTime(id, nTime);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doUpdateRevTime(String id, Date nTime) throws Exception {
		boolean flag = false;
		try {
			if (this.dao.findTaskById(id) != null) {
				flag = this.dao.doUpdateRevTime(id, nTime);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doUpdateFinTime(String id, Date nTime) throws Exception {
		boolean flag = false;
		try {
			if (this.dao.findTaskById(id) != null) {
				flag = this.dao.doUpdateFinTime(id, nTime);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doUpdataPayType(String id, String nType) throws Exception {
		boolean flag = false;
		try {
			if (this.dao.findTaskById(id) != null) {
				flag = this.dao.doUpdataPayType(id, nType);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doUpdateTitle(String id, String nTitle) throws Exception {
		boolean flag = false;
		try {
			if (this.dao.findTaskById(id) != null) {
				flag = this.dao.doUpdateTitle(id, nTitle);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doUpdateContent(String id, String nContent) throws Exception {
		boolean flag = false;
		try {
			if (this.dao.findTaskById(id) != null) {
				flag = this.dao.doUpdateContent(id, nContent);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doUpdateImgSrc(String id, String nSrc) throws Exception {
		boolean flag = false;
		try {
			if (this.dao.findTaskById(id) != null) {
				flag = this.dao.doUpdateImgSrc(id, nSrc);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doUpdateCost(String id, String nCost) throws Exception {
		boolean flag = false;
		try {
			if (this.dao.findTaskById(id) != null) {
				flag = this.dao.doUpdateCost(id, nCost);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doUpdateTaskType(String id, String nType) throws Exception {
		boolean flag = false;
		try {
			if (this.dao.findTaskById(id) != null) {
				flag = this.dao.doUpdateTaskType(id, nType);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doUpdateScore(String id, double nScore) throws Exception {
		boolean flag = false;
		try {
			if (this.dao.findTaskById(id) != null) {
				flag = this.dao.doUpdateScore(id, nScore);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doUpdateState(String id, String nState) throws Exception {
		boolean flag = false;
		try {
			if (this.dao.findTaskById(id) != null) {
				flag = this.dao.doUpdateState(id, nState);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doUpdatePayId(String id, String nPayId) throws Exception {
		boolean flag = false;
		try {
			if (this.dao.findTaskById(id) != null) {
				flag = this.dao.doUpdatePayId(id, nPayId);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public TaskBean findTaskById(String id) throws Exception {
		TaskBean taskBean = null;
		try {
			taskBean = this.dao.findTaskById(id);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return taskBean;
	}

	@Override
	public TaskBean findTaskByPayId(String payId) throws Exception {
		TaskBean taskBean = null;
		try {
			taskBean = this.dao.findTaskByPayId(payId);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return taskBean;
	}

	@Override
	public List<TaskBean> findTaskByPubId(String pubId) throws Exception {
		List<TaskBean> taskList = null;
		try {
			taskList = this.dao.findTaskByPubId(pubId);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return taskList;
	}

	@Override
	public List<TaskBean> findTaskByRevId(String revId) throws Exception {
		List<TaskBean> taskList = null;
		try {
			taskList = this.dao.findTaskByRevId(revId);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return taskList;
	}

	@Override
	public List<TaskBean> findTaskByState(String state) throws Exception {
		List<TaskBean> taskList = null;
		try {
			taskList = this.dao.findTaskByState(state);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return taskList;
	}

	@Override
	public boolean doUpdateAll(TaskBean oTask, TaskBean nTask) throws Exception {
		boolean flag = false;
		try {
			if (this.dao.findTaskById(oTask.getId()) != null) {
				flag = this.dao.doUpdateAll(oTask, nTask);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public List<TaskBean> findTaskByStateLimit(String state, int start, int end) throws Exception {
		List<TaskBean> taskList = null;
		try {
			if (start < end)
				taskList = this.dao.findTaskByStateLimit(state, start, end);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return taskList;
	}

	@Override
	public int getTaskCount(String state) throws Exception {
		int result;
		try {
			result = this.dao.getTaskCount(state);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return result;
	}

	@Override
	public boolean doUpdateRevIdById(String oId, String nId) throws Exception {
		boolean flag = false;
		try {
			if (this.dao.findTaskByRevId(oId) != null) {
				flag = this.dao.doUpdateRevIdById(oId, nId);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public List<TaskBean> findTaskAll() throws Exception {
		List<TaskBean> taskList = null;
		try {
			taskList = this.dao.findTaskAll();
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return taskList;
	}
}
