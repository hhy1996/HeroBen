package com.goli.heroben.factory;

import com.goli.heroben.dao.IPaymentDao;
import com.goli.heroben.dao.ITaskDao;
import com.goli.heroben.dao.ITaskTypeDao;
import com.goli.heroben.dao.IUserDao;
import com.goli.heroben.dao.IWithdrawDao;
import com.goli.heroben.dao.proxy.PaymentDaoProxy;
import com.goli.heroben.dao.proxy.TaskDaoProxy;
import com.goli.heroben.dao.proxy.TaskTypeDaoProxy;
import com.goli.heroben.dao.proxy.UserDaoProxy;
import com.goli.heroben.dao.proxy.WithdrawDaoProxy;

public class DaoFactory {
	public static IPaymentDao getIPaymentDaoInstance() throws Exception {
		return new PaymentDaoProxy();
	}

	public static ITaskTypeDao getITaskTypeDaoInstance() throws Exception {
		return new TaskTypeDaoProxy();
	}

	public static IUserDao getIUserDaoInstance() throws Exception {
		return new UserDaoProxy();
	}

	public static IWithdrawDao getIWithdrawDaoInstance() throws Exception {
		return new WithdrawDaoProxy();
	}

	public static ITaskDao getITaskDaoInstance() throws Exception {
		return new TaskDaoProxy();
	}
}
