package com.goli.heroben.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.goli.heroben.factory.DaoFactory;
import com.goli.heroben.service.ITaskService;
import com.goli.heroben.vo.TaskBean;
import com.goli.heroben.vo.TaskTypeBean;
import com.goli.heroben.vo.UserBean;

public class TaskServiceImp implements ITaskService {

	@Override
	public List<String> getTaskType() {
		List<String> all = new ArrayList<String>();
		try {
			all = DaoFactory.getITaskTypeDaoInstance().findAllTypeName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return all;
	}

	@Override
	public boolean addTaskType(UserBean user, String value) {
		boolean flag = false;
		TaskTypeBean taskTypeBean = new TaskTypeBean();

		// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// String str = df.format(new Date());
		// java.util.Date time = df.parse(str);
		// java.sql.Timestamp sqlDate = new java.sql.Timestamp(time.getTime());

		Date time = new Date();
		int id = Integer.parseInt(Long.toString(time.getTime()).substring(3));

		taskTypeBean.setId(id);
		taskTypeBean.setTypename(value);
		taskTypeBean.setCreatedby(user.getTelephone());
		taskTypeBean.setCreatetime(time);

		try {
			flag = DaoFactory.getITaskTypeDaoInstance().doCreate(taskTypeBean);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	@Override
	public boolean publishTask(UserBean user, TaskBean task) {
		boolean flag = false;
		TaskBean taskBean = new TaskBean();
		taskBean.setId(task.getId());
		taskBean.setPubTime(task.getPubTime());
		taskBean.setPubid(user.getTelephone());
		taskBean.setDeadTime(task.getDeadTime());//
		taskBean.setTitle(task.getTitle());//
		taskBean.setContent(task.getContent());//
		taskBean.setImgSrc(task.getImgSrc());//
		taskBean.setCost(task.getCost());//
		taskBean.setTaskType(task.getTaskType());
		taskBean.setPayType(task.getPayType());
		if (task.getPayType().equals("online")) {
			taskBean.setState("waitingpay");
		} else {
			taskBean.setState("waitingrev");
		}

		try {
			flag = DaoFactory.getITaskDaoInstance().doCreate(taskBean);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		return flag;
	}

	@Override
	public boolean changeTitle(TaskBean task, String title) {
		boolean flag = false;
		try {
			flag = DaoFactory.getITaskDaoInstance().doUpdateTitle(task.getId(), title);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean changeContent(TaskBean task, String content) {
		boolean flag = false;
		try {
			flag = DaoFactory.getITaskDaoInstance().doUpdateContent(task.getId(), content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean changeDeadTime(TaskBean task, Date nTime) {
		boolean flag = false;
		try {
			flag = DaoFactory.getITaskDaoInstance().doUpdateDeadTime(task.getId(), nTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean closeTask(UserBean user, TaskBean task) {
		boolean flag = false;
		try {
			flag = DaoFactory.getITaskDaoInstance().doUpdateState(task.getId(), "close");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean receiveTask(UserBean user, TaskBean task) {
		boolean flag = false;
		try {
			flag = DaoFactory.getITaskDaoInstance().doUpdateRevIdById(task.getId(), user.getTelephone());
		} catch (Exception e2) {
			// TODO 自动生成的 catch 块
			e2.printStackTrace();
		}
		try {
			DaoFactory.getITaskDaoInstance().doUpdateRevTime(task.getId(), new Date());
		} catch (Exception e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		if (flag) {
			try {
				flag = DaoFactory.getITaskDaoInstance().doUpdateState(task.getId(), "waitingfin");
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public boolean payTask(TaskBean task, String payid) {
		boolean flag = false;
		try {
			flag = DaoFactory.getITaskDaoInstance().doUpdatePayId(task.getId(), payid);
		} catch (Exception e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		if (flag) {
			try {
				flag = DaoFactory.getITaskDaoInstance().doUpdateState(task.getId(), "waitingrev");
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public boolean finTask(UserBean user, TaskBean task) {
		boolean flag = false;
		try {
			flag = DaoFactory.getITaskDaoInstance().doUpdateFinTime(task.getId(), new Date());
		} catch (Exception e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		if (flag) {
			try {
				flag = DaoFactory.getITaskDaoInstance().doUpdateState(task.getId(), "waitingcheck");
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public boolean checkTask(UserBean user, TaskBean task) {
		boolean flag = false;
		try {
			flag = DaoFactory.getITaskDaoInstance().doUpdateState(task.getId(), "success");
			if(task.getPayType().equals("online")){
				double cost=Double.parseDouble(task.getCost());
				double newmoney=user.getLeftmoney()+cost;
				DaoFactory.getIUserDaoInstance().doUpdateLeftMoney(task.getRevid(), String.valueOf(newmoney));
				user.setLeftmoney(newmoney);
			}
			DaoFactory.getITaskDaoInstance().doUpdateScore(task.getId(), task.getScore());
			int count=user.getTaskcount();
			user.setTaskcount(count+1);
			DaoFactory.getIUserDaoInstance().doUpdateTaskCount(task.getRevid(), (count+1));
			double newscore=(user.getAvescore()*count+task.getScore())/(count+1);
			DaoFactory.getIUserDaoInstance().doUpdateAveScore(task.getRevid(), newscore);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<TaskBean> showAllTask(UserBean user) {
		List<TaskBean> alltemp = new ArrayList<TaskBean>();
		List<TaskBean> all = new ArrayList<TaskBean>();
		try {
			alltemp = DaoFactory.getITaskDaoInstance().findTaskByState("waitingrev");
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		for (int i = 0; i < alltemp.size(); i++) {
			if (!alltemp.get(i).getPubid().equals(user.getTelephone())) {
				all.add(alltemp.get(i));
			}
		}
		return all;
	}

	@Override
	public List<TaskBean> searchTask(UserBean user, String key) {
		List<TaskBean> all = new ArrayList<TaskBean>();
		if (key.equals("pub")) {
			try {
				all = DaoFactory.getITaskDaoInstance().findTaskByPubId(user.getTelephone());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				all = DaoFactory.getITaskDaoInstance().findTaskByRevId(user.getTelephone());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return all;
	}

	@Override
	public List<TaskBean> searchTaskByKey(String key) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public List<TaskBean> getTwelveUnRevTasks() {
		List<TaskBean> result = new ArrayList<TaskBean>();
		try {
			result = DaoFactory.getITaskDaoInstance().findTaskByStateLimit("waitingrev", 0, 12);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<TaskBean> showRealAllTask(UserBean user) {
		List<TaskBean> alltemp = new ArrayList<TaskBean>();
		try {
			alltemp = DaoFactory.getITaskDaoInstance().findTaskAll();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return alltemp;
	}

}
