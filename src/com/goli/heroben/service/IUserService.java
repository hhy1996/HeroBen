package com.goli.heroben.service;

import java.util.List;

import com.goli.heroben.vo.UserBean;

public interface IUserService {
	/**
	 * 
	 * @param id
	 *            要登录的id(注意判断是Email还是电话号码)
	 * @param pwd
	 *            密码,在代理层记得进行加密
	 * @return 返回该ID的UserBean对象,如果密码错误则返回null
	 */
	public UserBean login(String id, String pwd);

	/**
	 * 这个函数用于注册用户
	 * 
	 * @param user
	 *            要注册用户对象,注意在里面加上一些用户没有填写的"隐藏信息",并将密码加密
	 * @return -1表示对象为空,0表示成功,1表示邮箱格式有误,2表示电话号码格式有误,3表示电话号码或邮箱已使用,5表示性别有误
	 */
	public int regeist(UserBean user);

	/**
	 * 
	 * @param user
	 *            要发送验证码的用户对象
	 * @param type
	 *            要发送的类型,1为短信验证,2为邮箱验证
	 * @return 返回发送的验证码,如果发送失败返回null
	 */
	public String sendCheckCode(UserBean user, int type);

	/**
	 * 
	 * @param user
	 *            要重置密码的用户对象
	 * @param nPass
	 *            新的密码,注意加密问题
	 * @return 返回是否操作成功
	 */
	public boolean resetPassword(UserBean user, String nPass);

	/**
	 * 
	 * @param user
	 *            要取款的用户对象
	 * @param money
	 *            要取款的金额
	 * @return 返回值表示是否成功
	 */
	public boolean withdraw(UserBean user, double money);

	/**
	 * 
	 * @param user
	 *            要验证的用户对象
	 * @param nuaaid
	 *            用户的校园编号
	 * @return 返回是否操作成功
	 */
	public boolean checkUser(UserBean user, String nuaaid);

	/**
	 * 上传用户头像函数
	 * 
	 * @param user
	 *            用户对象
	 * @param src
	 *            头像图片路径
	 * @return 返回值表示是否上传成功
	 */
	public boolean uploadHeadPic(UserBean user, String src);

	/**
	 * 修改电话号码
	 * 
	 * @param user
	 *            用户对象
	 * @param nTel
	 *            新的电话号码
	 * @return 返回值表示是否修改成功
	 */
	public boolean changeTel(UserBean user, String nTel);

	/**
	 * 修改邮箱
	 * 
	 * @param user
	 *            用户对象
	 * @param nEmail
	 *            新的Email值
	 * @return
	 */
	public boolean changeEmail(UserBean user, String nEmail);

	/**
	 * 
	 * @param user
	 *            用户对象
	 * @param sex
	 *            新的性别值
	 * @return 返回值表示是否修改成功
	 */
	public boolean changeSex(UserBean user, String sex);

	/**
	 * 
	 * @param ouser
	 *            旧的用户对象
	 * @param nuser
	 *            新的用户对象
	 * @return
	 */
	public boolean changeInfo(UserBean ouser, UserBean nuser);
	/**
	 * 
	 * @return 返回值为获取到的六个用户的名字
	 */
	public List<String> getSixHighScoreUser();
}
