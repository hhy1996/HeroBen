package com.goli.heroben.service.proxy;

import java.util.Date;
import java.util.List;

import com.goli.heroben.factory.DaoFactory;
import com.goli.heroben.service.ITaskService;
import com.goli.heroben.service.impl.TaskServiceImp;
import com.goli.heroben.vo.TaskBean;
import com.goli.heroben.vo.UserBean;

public class TaskServiceProxy implements ITaskService {

	@Override
	public List<String> getTaskType() {
		List<String> all = null;
		all = new TaskServiceImp().getTaskType();
		return all;
	}

	@Override
	public boolean addTaskType(UserBean user, String value) {
		boolean flag = false;
		flag = new TaskServiceImp().addTaskType(user, value);
		return flag;
	}

	@Override
	public boolean publishTask(UserBean user, TaskBean task) {
		boolean flag = false;
		flag = new TaskServiceImp().publishTask(user, task);
		return flag;
	}

	@Override
	public boolean changeTitle(TaskBean task, String title) {
		boolean flag = false;
		try {
			if (DaoFactory.getITaskDaoInstance().findTaskById(task.getId()) != null)
				flag = new TaskServiceImp().changeTitle(task, title);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean changeContent(TaskBean task, String content) {
		boolean flag = false;
		try {
			if (DaoFactory.getITaskDaoInstance().findTaskById(task.getId()) != null)
				flag = new TaskServiceImp().changeContent(task, content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean changeDeadTime(TaskBean task, Date nTime) {
		boolean flag = false;
		try {
			if (DaoFactory.getITaskDaoInstance().findTaskById(task.getId()) != null
					&& nTime.getTime() >= task.getPubTime().getTime())
				flag = new TaskServiceImp().changeDeadTime(task, nTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean closeTask(UserBean user, TaskBean task) {
		boolean flag = false;
		try {
			if (task.getPubid().equals(user.getTelephone())
					&& DaoFactory.getITaskDaoInstance().findTaskById(task.getId()) != null)
				flag = new TaskServiceImp().closeTask(user, task);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean receiveTask(UserBean user, TaskBean task) {
		boolean flag = false;
		try {
			if (/* task.getPubid() != user.getTelephone() */(!task.getPubid().equals(user.getTelephone()))
					&& DaoFactory.getITaskDaoInstance().findTaskById(task.getId()) != null)
				flag = new TaskServiceImp().receiveTask(user, task);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean payTask(TaskBean task, String payid) {
		boolean flag = false;
		try {
			if (DaoFactory.getITaskDaoInstance().findTaskById(task.getId()) != null)
				flag = new TaskServiceImp().payTask(task, payid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean finTask(UserBean user, TaskBean task) {
		boolean flag = false;
		try {
			if (user.getTelephone().equals(task.getRevid())
					&& DaoFactory.getITaskDaoInstance().findTaskById(task.getId()) != null)
				flag = new TaskServiceImp().finTask(user, task);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean checkTask(UserBean user, TaskBean task) {
		boolean flag = false;
		try {
			if (user.getTelephone().equals(task.getPubid())
					&& DaoFactory.getITaskDaoInstance().findTaskById(task.getId()) != null)
				flag = new TaskServiceImp().checkTask(user, task);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<TaskBean> showAllTask(UserBean user) {
		List<TaskBean> all = null;
		all = new TaskServiceImp().showAllTask(user);
		return all;
	}

	@Override
	public List<TaskBean> searchTask(UserBean user, String key) {
		List<TaskBean> all = null;
		if (key.equals("pub") || key.equals("rev")) {
			all = new TaskServiceImp().searchTask(user, key);
		}
		return all;
	}

	@Override
	public List<TaskBean> searchTaskByKey(String key) {
		List<TaskBean> all = null;
		all = new TaskServiceImp().searchTaskByKey(key);
		return all;
	}

	@Override
	public List<TaskBean> getTwelveUnRevTasks() {
		List<TaskBean> all = null;
		all = new TaskServiceImp().getTwelveUnRevTasks();
		return all;
	}

	@Override
	public List<TaskBean> showRealAllTask(UserBean user) {
		List<TaskBean> all = null;
		all = new TaskServiceImp().showRealAllTask(user);
		return all;
	}

}
