package com.goli.heroben.dao;

import java.util.Date;
import java.util.List;

import com.goli.heroben.vo.TaskBean;

public interface ITaskDao {
	/**
	 * 
	 * @param taskBean
	 *            要创建的TaskBean对象
	 * @return 返回值表示是否添加成功
	 * @throws Exception
	 */
	public boolean doCreate(TaskBean taskBean) throws Exception;

	/**
	 * 
	 * @param id
	 *            要移除的对象ID
	 * @return 返回值表示是否移除成功
	 * @throws Exception
	 */
	public boolean doRemoveById(String id) throws Exception;

	/**
	 * 
	 * @param id
	 *            要移除的发布者ID信息
	 * @return 返回值表示是否移除成功
	 * @throws Exception
	 */
	public boolean doRemoveByPubId(String id) throws Exception;

	/**
	 * 
	 * @param oId
	 *            要更新的旧的任务编号
	 * @param nId
	 *            要更新的新的任务编号
	 * @return 返回值表示是否更新成功
	 * @throws Exception
	 */
	public boolean doUpdateId(String oId, String nId) throws Exception;

	/**
	 * 
	 * @param oId
	 *            旧的发布用户ID值
	 * @param nId
	 *            新的用户ID值
	 * @return 返回值表示是否更新成功
	 * @throws Exception
	 */
	public boolean doUpdatePubId(String oId, String nId) throws Exception;

	/**
	 * 
	 * @param oId
	 *            旧的任务接收者ID
	 * @param nId
	 *            新的任务接受者ID
	 * @return 返回值表示是否更新成功
	 * @throws Exception
	 */
	public boolean doUpdateRevId(String oId, String nId) throws Exception;

	/**
	 * 
	 * @param oId
	 *            旧的任务ID
	 * @param nId
	 *            新的任务接受者ID
	 * @return 返回值表示是否更新成功
	 * @throws Exception
	 */
	public boolean doUpdateRevIdById(String oId, String nId) throws Exception;

	/**
	 * 
	 * @param id
	 *            要修改的任务的ID编号
	 * @param nTime
	 *            新的发布时间
	 * @return 返回值表示是否更新成功
	 * @throws Exception
	 */
	public boolean doUpdatePubTime(String id, Date nTime) throws Exception;

	/**
	 * 
	 * @param id
	 *            要修改的任务的ID编号
	 * @param nTime
	 *            新的截止时间
	 * @return 返回值表示是否更新成功
	 * @throws Exception
	 */
	public boolean doUpdateDeadTime(String id, Date nTime) throws Exception;

	/**
	 * 
	 * @param id
	 *            要修改的任务的ID编号
	 * @param nTime
	 *            新的任务接受时间
	 * @return 返回值表示是否接收成功
	 * @throws Exception
	 */
	public boolean doUpdateRevTime(String id, Date nTime) throws Exception;

	/**
	 * 
	 * @param id
	 *            要修改的任务ID编号
	 * @param nTime
	 *            新的任务完成时间
	 * @return 返回值表示是否更新成功
	 * @throws Exception
	 */
	public boolean doUpdateFinTime(String id, Date nTime) throws Exception;

	/**
	 * 
	 * @param id
	 *            要修改的任务的ID编号
	 * @param nType
	 *            新的支付类型
	 * @return 返回值表示是否更新成功
	 * @throws Exception
	 */
	public boolean doUpdataPayType(String id, String nType) throws Exception;

	/**
	 * 
	 * @param id
	 *            要修改的任务的ID编号
	 * @param nTitle
	 *            新的任务标题
	 * @return 返回值表示是否更新成功
	 * @throws Exception
	 */
	public boolean doUpdateTitle(String id, String nTitle) throws Exception;

	/**
	 * 
	 * @param id
	 *            要修改的任务的ID编号
	 * @param nContent
	 *            新的任务内容
	 * @return 返回值表示是否更新成功
	 * @throws Exception
	 */
	public boolean doUpdateContent(String id, String nContent) throws Exception;

	/**
	 * 
	 * @param id
	 *            要修改的任务的ID编号
	 * @param nSrc
	 *            新的图片路径
	 * @return 返回值表示是否更新成功
	 * @throws Exception
	 */
	public boolean doUpdateImgSrc(String id, String nSrc) throws Exception;

	/**
	 * 
	 * @param id
	 *            要修改的任务的ID编号
	 * @param nCost
	 *            新的任务价值
	 * @return 返回值表示是否更新成功
	 * @throws Exception
	 */
	public boolean doUpdateCost(String id, String nCost) throws Exception;

	/**
	 * 
	 * @param id
	 *            要修改的任务的ID编号
	 * @param nType
	 *            新的任务类型
	 * @return 返回值表示是否更新成功
	 * @throws Exception
	 */
	public boolean doUpdateTaskType(String id, String nType) throws Exception;

	/**
	 * 
	 * @param id
	 *            要修改的任务的ID编号
	 * @param nScore
	 *            新的任务评分
	 * @return 返回值表示是否更新成功
	 * @throws Exception
	 */
	public boolean doUpdateScore(String id, double nScore) throws Exception;

	/**
	 * 
	 * @param id
	 *            要修改的任务的ID编号
	 * @param nState
	 *            新的任务状态
	 * @return 返回值表示是否更新成功
	 * @throws Exception
	 */
	public boolean doUpdateState(String id, String nState) throws Exception;

	/**
	 * 
	 * @param id
	 *            要修改的任务的ID编号
	 * @param nPayId
	 *            新的支付订单ID
	 * @return 返回值表示是否更新成功
	 * @throws Exception
	 */
	public boolean doUpdatePayId(String id, String nPayId) throws Exception;

	/**
	 * 
	 * @param oTask
	 *            旧的TaskBean对象
	 * @param nTask
	 *            新的TaskBean对象
	 * @return 返回值表示是否更新成功
	 * @throws Exception
	 */
	public boolean doUpdateAll(TaskBean oTask, TaskBean nTask) throws Exception;

	/**
	 * 
	 * @param id
	 *            要搜索的任务的ID编号
	 * @return 一个TaskBean对象,存储搜索到的task内容,如果没有搜索到返回null
	 * @throws Exception
	 */
	public TaskBean findTaskById(String id) throws Exception;

	/**
	 * 
	 * @param payId
	 *            要搜索的支付订单的ID编号
	 * @return 搜索到的Bean对象,如果没搜索到,返回null
	 * @throws Exception
	 */
	public TaskBean findTaskByPayId(String payId) throws Exception;

	/**
	 * 
	 * @param pubId
	 *            发布者的ID编号
	 * @return 一个List集合包含所有找到的对象,如果没找到返回一个长度为0的List
	 * @throws Exception
	 */
	public List<TaskBean> findTaskByPubId(String pubId) throws Exception;

	/**
	 * 
	 * @param revId
	 *            接受者的ID编号
	 * @return 一个List集合包含所有找到的对象,如果没找到返回一个长度为0的List
	 * @throws Exception
	 */
	public List<TaskBean> findTaskByRevId(String revId) throws Exception;

	/**
	 * 
	 * @param state
	 *            要搜索的任务的状态关键字
	 * @return 一个List集合包含所有找到的对象,如果没找到返回一个长度为0的List
	 * @throws Exception
	 */
	public List<TaskBean> findTaskByState(String state) throws Exception;

	/**
	 * 
	 * @param state
	 *            要搜索的任务的关键字
	 * @param start
	 *            要搜索的任务的开始位置
	 * @param end
	 *            要搜索的任务的结束位置
	 * @return 返回值表示此次搜索的结果
	 * @throws Exception
	 */
	public List<TaskBean> findTaskByStateLimit(String state, int start, int end) throws Exception;

	/**
	 * 
	 * @return 返回一个int表示某个状态的任务的总数
	 * @throws Exception
	 */
	public int getTaskCount(String state) throws Exception;

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<TaskBean> findTaskAll() throws Exception;
}
