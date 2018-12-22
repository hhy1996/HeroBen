package com.goli.heroben.service.impl;

import com.goli.heroben.factory.DaoFactory;
import com.goli.heroben.service.IMoneyService;
import com.goli.heroben.vo.PaymentBean;
import com.goli.heroben.vo.WithdrawBean;

public class MoneyServiceImp implements IMoneyService{

	@Override
	public boolean putMoney(PaymentBean payment, String taskid) {
		boolean flag = false;
		try {
			boolean flag1 = DaoFactory.getIPaymentDaoInstance().doCreate(payment);
			boolean flag2 = DaoFactory.getITaskDaoInstance().doUpdatePayId(taskid, payment.getId());
			flag = flag1 && flag2;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean getMoney(String userTel, WithdrawBean withdrawBean) {
		boolean flag = false;
		try {
		double oMoney = DaoFactory.getIUserDaoInstance().findUserByTel(userTel).getLeftmoney();
		if (oMoney >= withdrawBean.getMoney()) {
			flag = true;
			double nMoney = oMoney - withdrawBean.getMoney();
			DaoFactory.getIUserDaoInstance().doUpdateLeftMoney(userTel, String.valueOf(nMoney));
			flag = flag && DaoFactory.getIWithdrawDaoInstance().doCreate(withdrawBean);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	
	
}
