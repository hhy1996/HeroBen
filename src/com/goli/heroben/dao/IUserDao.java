package com.goli.heroben.dao;

import java.util.Date;
import java.util.List;

import com.goli.heroben.vo.UserBean;

public interface IUserDao {
	/**
	 * 
	 * @param userBean
	 *            要添加的userBean对象
	 * @return 返回值是是否添加成功
	 * @throws Exception
	 */
	public boolean doCreate(UserBean userBean) throws Exception;

	/**
	 * 
	 * @param telephone
	 *            要删除的用户的电话号码
	 * @return 返回值是是否删除成功
	 * @throws Exception
	 */
	public boolean doRemoveByTel(String telephone) throws Exception;

	/**
	 * 
	 * @param email
	 *            要删除的用户的邮箱账号
	 * @return 返回值是是否删除成功
	 * @throws Exception
	 */
	public boolean doRemoveByEmail(String email) throws Exception;

	/**
	 * 这个函数是用来删除checked的值为参数值的账户
	 * 
	 * @param checked
	 *            表示要删除的用户的checked值
	 * @return 返回值是是否删除成功
	 * @throws Exception
	 */
	public boolean doRemoveByChecked(int checked) throws Exception;

	/**
	 * 
	 * @param oTel
	 *            要更新的用户旧电话号码
	 * @param nTel
	 *            要更新的用户新电话号码
	 * @return 返回值表示是否更新成功
	 * @throws Exception
	 */
	public boolean doUpdateTelephone(String oTel, String nTel) throws Exception;

	/**
	 * 
	 * @param tel
	 *            表示用户的电话号码
	 * @param nEmail
	 *            表示用户的新邮箱号
	 * @return 返回值表示是否更新成功
	 * @throws Exception
	 */
	public boolean doUpdateEmail(String tel, String nEmail) throws Exception;

	/**
	 * 
	 * @param tel
	 *            表示用户的电话号码
	 * @param nPass
	 *            表示用户的新密码
	 * @return 返回值表示是否更新成功
	 * @throws Exception
	 */
	public boolean doUpdatePassword(String tel, String nPass) throws Exception;

	/**
	 * 
	 * @param tel
	 *            表示用户的电话号码
	 * @param nName
	 *            表示用户的新昵称
	 * @return 返回值表示是否更新成功
	 * @throws Exception
	 */
	public boolean doUpdateName(String tel, String nName) throws Exception;

	/**
	 * 
	 * @param tel
	 *            表示用户的电话号码
	 * @param nSex
	 *            表示用户的新性别
	 * @return 返回值表示是否更新成功
	 * @throws Exception
	 */
	public boolean doUpdateSex(String tel, String nSex) throws Exception;

	/**
	 * 
	 * @param tel
	 *            表示用户的电话号码
	 * @param nId
	 *            表示用户的新身份验证编号
	 * @return 返回值表示是否更新成功
	 * @throws Exception
	 */
	public boolean doUpdateNuaaid(String tel, String nId) throws Exception;

	/**
	 * 
	 * @param tel
	 *            表示用户的电话号码
	 * @param nChecked
	 *            表示用户的是否已验证新值
	 * @return 返回值表示是否更新成功
	 * @throws Exception
	 */
	public boolean doUpdateChecked(String tel, int nChecked) throws Exception;

	/**
	 * 
	 * @param tel
	 *            表示用户的电话号码
	 * @param count
	 *            表示用户新的完成任务总数
	 * @return 返回值表示是否更新成功
	 * @throws Exception
	 */
	public boolean doUpdateTaskCount(String tel, int count) throws Exception;

	/**
	 * 
	 * @param tel
	 *            表示用户的电话号码
	 * @param nScore
	 *            表示用户的新评分
	 * @return 返回值表示是否更新成功
	 * @throws Exception
	 */
	public boolean doUpdateAveScore(String tel, double nScore) throws Exception;

	/**
	 * 
	 * @param tel
	 *            表示用户的电话号码
	 * @param nSrc
	 *            表示用户的新头像路径
	 * @return 返回值表示是否更新成功
	 * @throws Exception
	 */
	public boolean doUpdateDpSrc(String tel, String nSrc) throws Exception;

	/**
	 * 
	 * @param tel
	 *            表示用户的电话号码
	 * @param nMoney
	 *            表示用户的新余额数
	 * @return 返回值表示是否更新成功
	 * @throws Exception
	 */
	public boolean doUpdateLeftMoney(String tel, String nMoney) throws Exception;

	/**
	 * 
	 * @param tel
	 *            表示用户的电话号码
	 * @param nTime
	 *            表示用户的新注册时间
	 * @return 返回值表示是否更新成功
	 * @throws Exception
	 */
	public boolean doUpdateRegeisteTime(String tel, Date nTime) throws Exception;

	/**
	 * 
	 * @param tel
	 *            表示用户的电话号码
	 * @param nTime
	 *            表示用户的新的最后登录时间
	 * @return 返回值表示是否更新成功
	 * @throws Exception
	 */
	public boolean doUpdateLastLoginTime(String tel, Date nTime) throws Exception;

	/**
	 * 
	 * @param oUser
	 *            用户的旧对象
	 * @param nUser
	 *            用户的新对象
	 * @return 返回值表示是否更新成功
	 */
	public boolean doUpdateAll(UserBean oUser, UserBean nUser) throws Exception;

	/**
	 * 
	 * @param tel
	 *            用户的电话号码
	 * @return 返回值是找的用户的对象,如果没找到返回null
	 * @throws Exception
	 */
	public UserBean findUserByTel(String tel) throws Exception;

	/**
	 * 
	 * @param email
	 *            用户的邮箱
	 * @return 返回值是找的用户的对象,如果没找到返回null
	 * @throws Exception
	 */
	public UserBean findUserByEmail(String email) throws Exception;

	/**
	 * 
	 * @param name
	 *            用户的昵称
	 * @return 返回值是找的所有用户的对象,如果没找到返回一个长度为0的List
	 * @throws Exception
	 */
	public List<UserBean> findUserByName(String name) throws Exception;

	/**
	 * 
	 * @param order 要排序的字段
	 * @param type 排序类型，1为升序，2位降序
	 * @param limitsize 限制范围大小
	 * @return 返回值为获取到的符合要求的UserBean的List
	 * @throws Exception
	 */
	public List<UserBean> findUserOrderByLimit(String order,int type,int limitsize) throws Exception;
}
