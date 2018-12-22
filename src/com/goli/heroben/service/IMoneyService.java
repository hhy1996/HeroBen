package com.goli.heroben.service;

import com.goli.heroben.vo.PaymentBean;
import com.goli.heroben.vo.WithdrawBean;

public interface IMoneyService {

	public boolean putMoney(PaymentBean payment, String taskid);
	
	public boolean getMoney(String userTel, WithdrawBean withdrawBean);
}
