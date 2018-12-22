package com.goli.heroben.dao.proxy;

import java.util.List;

import com.goli.heroben.dao.IPaymentDao;
import com.goli.heroben.dao.impl.PaymentDaoImp;
import com.goli.heroben.dbc.DataBaseConnection;
import com.goli.heroben.vo.PaymentBean;


public class PaymentDaoProxy implements IPaymentDao {
	private DataBaseConnection dbc = null;
	private IPaymentDao dao = null;

	public PaymentDaoProxy() throws Exception{
		this.dbc = new DataBaseConnection();
		this.dao = new PaymentDaoImp(dbc.getConnection());
	}
	
	@Override
	public PaymentBean findPaymentById(String id) throws Exception {
		PaymentBean payment = null;
		try{
			payment = this.dao.findPaymentById(id);
		}catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
		return payment;
	}

	@Override
	public List<PaymentBean> findPaymentByUserid(String userid) throws Exception {
		List<PaymentBean> all = null;
		try {
			all = this.dao.findPaymentByUserid(userid);
		} catch (Exception e) {
			throw e;
		}finally {
			this.dbc.close();
		}
		return all;
	}

	@Override
	public PaymentBean findPaymentByPlatformId(String id) throws Exception {
		PaymentBean payment = null;
		try{
			payment = this.dao.findPaymentByPlatformId(id);
		}catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
		return payment;
	}

	@Override
	public boolean doCreate(PaymentBean paymentBean) throws Exception {
		boolean flag = false;
		try{
			if (this.dao.findPaymentById(paymentBean.getId()) == null){
				flag = this.dao.doCreate(paymentBean);
			}
		}catch (Exception e) {
			throw e;
		}finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doRemoveById(String id) throws Exception {
		boolean flag = false;
		try{
			if (this.dao.findPaymentById(id) != null){
				flag = this.dao.doRemoveById(id);
			}
		}catch (Exception e) {
			throw e;
		}finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doRemoveByUserid(String userid) throws Exception {
		boolean flag = false;
		try{
			if (this.dao.findPaymentByUserid(userid) != null){
				flag = this.dao.doRemoveByUserid(userid);
			}
		}catch (Exception e) {
			throw e;
		}finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doRemoveByPlatformId(String id) throws Exception {
		boolean flag = false;
		try{
			if (this.dao.findPaymentByPlatformId(id) != null){
				flag = this.dao.doRemoveByPlatformId(id);
			}
		}catch (Exception e) {
			throw e;
		}finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doUpdateId(String oId, String nId) throws Exception {
		boolean flag = false;
		try {
			if(this.dao.findPaymentById(oId) != null){
				flag = this.dao.doUpdateId(oId, nId);
			}
		}catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doUpdateUserid(String id, String nUserid) throws Exception {
		boolean flag = false;
		try {
			if(this.dao.findPaymentById(id) != null){
				flag = this.dao.doUpdateUserid(id, nUserid);
			}
		}catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doUpdateMoney(String id, String nMoney) throws Exception {
		boolean flag = false;
		try {
			if(this.dao.findPaymentById(id) != null){
				flag = this.dao.doUpdateMoney(id, nMoney);
			}
		}catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doUpdatePayTime(String id, String nTime) throws Exception {
		boolean flag = false;
		try {
			if(this.dao.findPaymentById(id) != null){
				flag = this.dao.doUpdatePayTime(id, nTime);
			}
		}catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doUpdatePlatformId(String id, String nid) throws Exception {
		boolean flag = false;
		try {
			if(this.dao.findPaymentById(id) != null){
				flag = this.dao.doUpdatePlatformId(id, nid);
			}
		}catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doUpdatePlatName(String id, String nName) throws Exception {
		boolean flag = false;
		try {
			if(this.dao.findPaymentById(id) != null){
				flag = this.dao.doUpdatePlatName(id, nName);
			}
		}catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doUpdateAll(PaymentBean oPayment, PaymentBean nPayment) throws Exception {
		boolean flag = false;
		try {
			if(this.dao.findPaymentById(oPayment.getId()) != null){
				flag = this.dao.doUpdateAll(oPayment, nPayment);
			}
		}catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public List<PaymentBean> findPaymentByPlatformName(String name) throws Exception {
		List<PaymentBean> all = null;
		try {
			all = this.dao.findPaymentByPlatformName(name);
		} catch (Exception e) {
			throw e;
		}finally {
			this.dbc.close();
		}
		return all;
	}

}
