package com.goli.heroben.factory;

import com.goli.heroben.service.IMoneyService;
import com.goli.heroben.service.ITaskService;
import com.goli.heroben.service.IUserService;
import com.goli.heroben.service.proxy.MoneyServiceProxy;
import com.goli.heroben.service.proxy.TaskServiceProxy;
import com.goli.heroben.service.proxy.UserServiceProxy;

public class ServiceFactory {
	static private ITaskService taskservice = null;
	static private IUserService userservice = null;
	static private IMoneyService moneyservice = null;

	public static ITaskService getTaskServiceInstance() {
		if (taskservice == null)
			taskservice = new TaskServiceProxy();
		return taskservice;
	}

	public static IUserService getUserServiceInstance() {
		if (userservice == null)
			userservice = new UserServiceProxy();
		return userservice;
	}
	
	public static IMoneyService getMoneyServiceInstance(){
		if(moneyservice==null)
			moneyservice=new MoneyServiceProxy();
		return moneyservice;
	}
}
