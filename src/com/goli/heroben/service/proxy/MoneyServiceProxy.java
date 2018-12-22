package com.goli.heroben.service.proxy;

import com.goli.heroben.factory.DaoFactory;
import com.goli.heroben.service.IMoneyService;
import com.goli.heroben.service.IUserService;
import com.goli.heroben.service.impl.MoneyServiceImp;
import com.goli.heroben.service.impl.UserServiceImp;
import com.goli.heroben.vo.PaymentBean;
import com.goli.heroben.vo.WithdrawBean;

public class MoneyServiceProxy implements IMoneyService{
	private IMoneyService im;

	public MoneyServiceProxy() {
		im = new MoneyServiceImp();
	}

	@Override
	public boolean putMoney(PaymentBean payment, String taskid) {
		if(payment == null || taskid == null)
			return false;
		boolean flag = false;
		flag = this.im.putMoney(payment, taskid);
		return flag;
	}

	@Override
	public boolean getMoney(String userTel, WithdrawBean withdrawBean) {
		try {
			if(DaoFactory.getIUserDaoInstance().findUserByTel(userTel).getLeftmoney() < withdrawBean.getMoney())
				return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean flag = false;
		flag = this.im.getMoney(userTel, withdrawBean);
		return flag;
	}

}
