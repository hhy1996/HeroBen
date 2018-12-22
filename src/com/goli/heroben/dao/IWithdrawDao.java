package com.goli.heroben.dao;

import java.util.Date;
import java.util.List;

import com.goli.heroben.vo.WithdrawBean;

public interface IWithdrawDao {
	/**
	 * 
	 * @param withdrawBean
	 *            取款的Bean对象
	 * @return 返回值是是否创建成功
	 * @throws Exception
	 */
	public boolean doCreate(WithdrawBean withdrawBean) throws Exception;

	/**
	 * 
	 * @param id
	 *            取款编号
	 * @return 返回值表示是否移除成功
	 * @throws Exception
	 */
	public boolean doRemoveById(String id) throws Exception;

	/**
	 * 删掉所有该用户的取款信息
	 * 
	 * @param userId
	 *            用户编号
	 * @return 返回值表示是否移除成功
	 * @throws Exception
	 */
	public boolean doRemoveByUserId(String userId) throws Exception;

	/**
	 * 
	 * @param id
	 *            第三方平台给的支付订单号
	 * @return 表示是否移除成功
	 * @throws Exception
	 */
	public boolean doRemoveByPlatformId(String id) throws Exception;

	/**
	 * 
	 * @param oId
	 *            旧的取款编号ID
	 * @param nId
	 *            新的取款编号ID
	 * @return 返回值表示是否修改成功
	 * @throws Exception
	 */
	public boolean doUpdateId(String oId, String nId) throws Exception;

	/**
	 * 
	 * @param oId
	 *            旧的用户ID
	 * @param nId
	 *            新的用户ID
	 * @return 返回值表示是否修改成功
	 * @throws Exception
	 */
	public boolean doUpdateUserId(String oId, String nId) throws Exception;

	/**
	 * 
	 * @param id
	 *            取款订单ID
	 * @param nMoney
	 *            新的取款金额
	 * @return 返回值表示是否修改成功
	 * @throws Exception
	 */
	public boolean doUpdateMoney(String id, double nMoney) throws Exception;

	/**
	 * 
	 * @param id
	 *            取款订单ID
	 * @param platid
	 *            用户取款的编号(例如用户的支付宝账户)
	 * @return 返回值表示是否修改成功
	 * @throws Exception
	 */
	public boolean doUpdateUserPlatId(String id, String platid) throws Exception;

	/**
	 * 
	 * @param id
	 *            取款订单ID
	 * @param nTime
	 *            新的支付时间
	 * @return 返回值表示是否修改成功
	 * @throws Exception
	 */
	public boolean doUpdatePayTime(String id, Date nTime) throws Exception;

	/**
	 * 
	 * @param id
	 *            取款订单ID
	 * @param nPlatId
	 *            新的第三方平台给的支付编号
	 * @return
	 * @throws Exception
	 */
	public boolean doUpdatePlatFormId(String id, String nPlatId) throws Exception;

	/**
	 * 
	 * @param id
	 *            取款订单ID
	 * @param nName
	 *            第三方平台的名称
	 * @return 是否更新成功
	 * @throws Exception
	 */
	public boolean doUpdatePlatName(String id, String nName) throws Exception;

	/**
	 * 
	 * @param oWithdraw
	 *            旧的取款订单对象
	 * @param nWithdraw
	 *            新的取款订单对象
	 * @return 是否更新成功
	 * @throws Exception
	 */
	public boolean doUpdateAll(WithdrawBean oWithdraw, WithdrawBean nWithdraw) throws Exception;

	/**
	 * 
	 * @param id
	 *            取款订单编号
	 * @return 找到的Bean对象,如果没找到返回null
	 * @throws Exception
	 */
	public WithdrawBean findWithdrawById(String id) throws Exception;

	/**
	 * 
	 * @param userid
	 *            用户id
	 * @return 找到的数据组成的List,如果没找到返回一个长度为0的List
	 * @throws Exception
	 */
	public List<WithdrawBean> findWithdrawByUserId(String userid) throws Exception;

	/**
	 * 
	 * @param id
	 *            第三方平给的ID
	 * @return 找到的Bean对象,如果没找到返回null
	 * @throws Exception
	 */
	public WithdrawBean findWithdrawByPlatId(String id) throws Exception;

	/**
	 * 
	 * @param name
	 *            第三方平台的名字
	 * @return 找到的数据组成的List集合,如果没找到返回一个长度为0的List
	 * @throws Exception
	 */
	public List<WithdrawBean> findWithdrawByPlatName(String name) throws Exception;
}
