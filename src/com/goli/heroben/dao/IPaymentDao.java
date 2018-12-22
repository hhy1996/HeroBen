package com.goli.heroben.dao;

import java.util.List;

import com.goli.heroben.vo.PaymentBean;

public interface IPaymentDao {
	/**
	 * 通过订单id寻找
	 * 
	 * @param id
	 *            id表示的是数据表里面的支付编号id
	 * @return 返回值是一个payment的bean对象,如果没找到返回一个null
	 * @throws Exception
	 */
	public PaymentBean findPaymentById(String id) throws Exception;

	/**
	 * 通过用户名寻找
	 * 
	 * @param userid
	 *            userid表示的是用户的用户名
	 * @return 返回值是一个List<Payment>类型的数据,表示搜索得到的信息,找不到返回长度为0的List
	 * @throws Exception
	 */

	public List<PaymentBean> findPaymentByUserid(String userid) throws Exception;

	/**
	 * 通过第三方平台的支付id寻找我们这边的支付单
	 * 
	 * @param id
	 *            这里的id表示的是第三方支付平台的支付id号
	 * @return 返回值是一个Payment类型的bean对象,如果没找到,返回一个null
	 * @throws Exception
	 */
	public PaymentBean findPaymentByPlatformId(String id) throws Exception;

	/**
	 * 
	 * @param name
	 *            平台名称
	 * @return 找到的该平台的支付订单集合,如果没找到返回一个长度为0的List
	 * @throws Exception
	 */
	public List<PaymentBean> findPaymentByPlatformName(String name) throws Exception;

	/**
	 * 增加一个新的支付订单信息
	 * 
	 * @param paymentBean
	 *            传入的是一个payment的bean数据类型
	 * @return 返回值是是否增加成功
	 * @throws Exception
	 */

	public boolean doCreate(PaymentBean paymentBean) throws Exception;

	/**
	 * 通过支付ID移除一个支付单
	 * 
	 * @param id
	 *            表示的是要移除的那一行的支付id号
	 * @return 返回值是是否移除成功
	 * @throws Exception
	 */
	public boolean doRemoveById(String id) throws Exception;

	/**
	 * 
	 * @param userid
	 *            userid表示的是用户名
	 * @return 返回值是是否移除成功
	 * @throws Exception
	 */
	public boolean doRemoveByUserid(String userid) throws Exception;

	/**
	 * 
	 * @param id
	 *            id表示的是第三方支付平台的订单号
	 * @return 返回值是是否移除成功
	 * @throws Exception
	 */
	public boolean doRemoveByPlatformId(String id) throws Exception;

	/**
	 * 
	 * @param oId
	 *            oId表示的是要更新字段的旧ID值
	 * @param nId
	 *            nId表示的是要更新的字段的新ID值
	 * @return 返回值表示是否更新成功
	 * @throws Exception
	 */
	public boolean doUpdateId(String oId, String nId) throws Exception;

	/**
	 * 
	 * @param id
	 *            id表示要更新的那一行的ID
	 * @param nUserid
	 *            nUserid表示的是新的用户名
	 * @return 返回值是是否更新成功
	 * @throws Exception
	 */
	public boolean doUpdateUserid(String id, String nUserid) throws Exception;

	/**
	 * 
	 * @param id
	 *            id表示的是要更新的那一行的ID
	 * @param nMoney
	 *            nMoney表示的新的价值
	 * @return 返回值表示的是是否更新成功
	 * @throws Exception
	 */
	public boolean doUpdateMoney(String id, String nMoney) throws Exception;

	/**
	 * 
	 * @param id
	 *            id表示的是要更新的那一行数据的ID
	 * @param nTime
	 *            nTime表示的是新的时间
	 * @return 返回值表示的是否更新成功
	 * @throws Exception
	 */
	public boolean doUpdatePayTime(String id, String nTime) throws Exception;

	/**
	 * 
	 * @param id
	 *            id表示的是要更新的那一行的数据ID
	 * @param nid
	 *            nid表示的是新的第三方平台的ID
	 * @return 返回值表示是否更新成功
	 * @throws Exception
	 */
	public boolean doUpdatePlatformId(String id, String nid) throws Exception;

	/**
	 * 
	 * @param id
	 *            id表示的是要更新的那一行的数据ID
	 * @param nName
	 *            nName表示的是新的第三方支付平台的名称
	 * @return 返回值表示是否更新成功
	 * @throws Exception
	 */
	public boolean doUpdatePlatName(String id, String nName) throws Exception;

	/**
	 * 
	 * @param oPayment
	 *            旧的payment对象
	 * @param nPayment
	 *            新的payment对象
	 * @return 返回值是是否修改成功
	 * @throws Exception
	 */
	public boolean doUpdateAll(PaymentBean oPayment, PaymentBean nPayment) throws Exception;
}
