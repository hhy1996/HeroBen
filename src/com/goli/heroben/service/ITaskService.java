package com.goli.heroben.service;

import java.util.Date;
import java.util.List;

import com.goli.heroben.vo.TaskBean;
import com.goli.heroben.vo.UserBean;

public interface ITaskService {
	/**
	 * 这个函数用于获取所有的任务类型
	 * 
	 * @return 返回值返回所有得到的任务类别的内容,请将TaskType的typename值组合起来传出来,如果没找到返回null
	 * @throws Exception
	 */
	public List<String> getTaskType();

	/**
	 * 这个函数用于用户添加一个任务类型
	 * 
	 * @param user
	 *            传入的用户信息对象,用于获取用户的id
	 * @param value
	 *            传入的类型值
	 * @return
	 */
	public boolean addTaskType(UserBean user, String value);

	/**
	 * 这个函数用于发布任务
	 * 
	 * @param user
	 *            传入用户信息
	 * @param task
	 *            传入的任务信息
	 * @return 返回值表示是否修改成功
	 */
	public boolean publishTask(UserBean user, TaskBean task);

	/**
	 * 这个函数用语修改任务标题
	 * 
	 * @param task
	 *            传入要修改的任务的对象
	 * @param title
	 *            传入的新的标题
	 * @return 返回值表示是否修改成功
	 */
	public boolean changeTitle(TaskBean task, String title);

	/**
	 * 这个函数用于修改任务内容
	 * 
	 * @param task
	 *            传入要修改的任务的对象
	 * @param content
	 *            传入新的内容值
	 * @return 表示是否修改成功
	 */
	public boolean changeContent(TaskBean task, String content);

	/**
	 * 这个函数用于修改任务的截止时间
	 * 
	 * @param task
	 *            要修改的任务对象
	 * @param nTime
	 *            要修改的新的时间
	 * @return 返回值表示是否成功
	 */
	public boolean changeDeadTime(TaskBean task, Date nTime);

	/**
	 * 这个函数用于关闭一个任务，注意使用user对象判断这个任务是否属于该用户
	 * 
	 * @param user
	 *            用户对象
	 * @param task
	 *            任务对象
	 * @return 返回值表示是否关闭成功
	 */
	public boolean closeTask(UserBean user, TaskBean task);

	/**
	 * 这个函数用于接受任务,注意使用user判断这个任务不是他自己发布的才可以接受
	 * 
	 * @param user
	 *            用户对象
	 * @param task
	 *            要接受的任务对象
	 * @return
	 */
	public boolean receiveTask(UserBean user, TaskBean task);

	/**
	 * 这个函数用于修改订单是否支付成功
	 * 
	 * @param task
	 *            要支付的任务对象
	 * @param payid
	 *            任务的支付编号
	 * @return 返回支付是否成功
	 */
	public boolean payTask(TaskBean task, String payid);

	/**
	 * 用于完成任务,注意判断要完成的任务是否属于该用户
	 * 
	 * @param user
	 *            要结束任务的人
	 * @param task
	 *            任务对象
	 * @return 返回值表示是否成功操作
	 */
	public boolean finTask(UserBean user, TaskBean task);

	/**
	 * 注意确认这个任务是不是这个用户发布的
	 * 
	 * @param user
	 *            要确认付款的用户
	 * @param task
	 *            任务对象
	 * @return 返回值表示是否成功
	 */
	public boolean checkTask(UserBean user, TaskBean task);

	/**
	 * 返回一个TaskBean的List,里面存储所有的任务,其中要根据user剔除掉该用户自身发布的任务
	 * 
	 * @param user
	 *            用户对象
	 * @return 返回值表示能找到的所有任务
	 */
	public List<TaskBean> showAllTask(UserBean user);

	/**
	 * 这个函数用于搜索关键字任务
	 * 
	 * @param user
	 *            用户对象
	 * @param key
	 *            key为pub时查询所有该用户发布出去的任务,为rev的时候返回所有接受的任务
	 * @return 返回值表示能找到的所有任务
	 */
	public List<TaskBean> searchTask(UserBean user, String key);

	/**
	 * 通过关键字查询任务
	 * 
	 * @param key
	 *            搜索关键字
	 * @return
	 */
	public List<TaskBean> searchTaskByKey(String key);

	/**
	 * 
	 * @return 返回值为获取到的任务
	 */
	public List<TaskBean> getTwelveUnRevTasks();

	/**
	 * 
	 * @param user
	 * @return
	 */
	public List<TaskBean> showRealAllTask(UserBean user);

}
