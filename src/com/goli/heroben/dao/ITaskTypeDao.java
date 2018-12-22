package com.goli.heroben.dao;

import java.util.Date;
import java.util.List;

import com.goli.heroben.vo.TaskTypeBean;

public interface ITaskTypeDao {
	/**
	 * 
	 * @param taskTypeBean
	 *            taskType是一个TaskType类型Bean对象
	 * @return 返回值表示是否添加成功
	 */
	public boolean doCreate(TaskTypeBean taskTypeBean) throws Exception;

	/**
	 * 
	 * @param id
	 *            id值表示type的唯一编号
	 * @return 返回值表示是否移除成功
	 */
	public boolean doRemoveById(int id) throws Exception;

	/**
	 * 
	 * @param typeName
	 *            typename表示类型名称
	 * @return 返回值表示是否移除成功
	 */
	public boolean doRemoveByName(String typeName) throws Exception;

	/**
	 * 
	 * @param userId
	 *            userid表示的是创建这个类型的用户编号
	 * @return 返回值表示是否移除成功
	 */
	public boolean doRemoveByUserid(String userId) throws Exception;

	/**
	 * 
	 * @param oId
	 *            oId表示的是旧的ID值
	 * @param nId
	 *            nId表示的是新的ID值
	 * @return 返回值表示的是否更新成功
	 */
	public boolean doUpdateId(int oId, int nId) throws Exception;

	/**
	 * 
	 * @param id
	 *            id表示的是类型的编号
	 * @param nName
	 *            nName表示的是这个类型的内容
	 * @return 返回值表示的是是否更新成功
	 */
	public boolean doUpdateName(int id, String nName) throws Exception;

	/**
	 * @param oUser
	 *            oUser表示的是创建这个类型的用户旧ID
	 * @param nUser
	 *            nUser表示的是创建这个类型的用户新ID
	 * @return 返回值表示的是是否更新成功
	 */
	public boolean doUpdateUser(String oUser, String nUser) throws Exception;

	/**
	 * 
	 * @param id
	 *            id表示的是类型的唯一编号ID
	 * @param nTime
	 *            nTime表示的是更新后的新时间
	 * @return 返回值表示的是是否更新成功
	 */
	public boolean doUpdateTime(int id, Date nTime) throws Exception;

	/**
	 * 
	 * @param oType
	 *            oType表示的是旧的TaskType对象
	 * @param nType
	 *            nType表示的是新的TaskType对象
	 * @return 返回值表示的是是否更新成功
	 */
	public boolean doUpdateAll(TaskTypeBean oType, TaskTypeBean nType) throws Exception;

	/**
	 * 
	 * @param id
	 *            id表示的是要寻找的类型的唯一编号ID
	 * @return 返回值表示找到的TaskType类型的bean对象,如果没找到返回一个null
	 */
	public TaskTypeBean findTaskTypeById(int id) throws Exception;

	/**
	 * @param userid
	 *            userid表示的是创建这个类型的用户编号
	 * @return 返回值表示的是所有找到的集合,如果没找到返回一个长度为0的List
	 */
	public List<TaskTypeBean> findTaskTypeByUser(String userid) throws Exception;

	/**
	 * 这个函数用于找到所有的类型内容,并将他们打包后传出来
	 * 
	 * @return 返回值表示的是所有找到的类型集合,如果没找到返回一个长度为0的List
	 */
	public List<String> findAllTypeName() throws Exception;
}
