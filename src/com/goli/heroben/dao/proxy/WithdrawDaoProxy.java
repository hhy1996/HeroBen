package com.goli.heroben.dao.proxy;

import java.util.Date;
import java.util.List;

//import com.goli.heroben.dao.IPaymentDao;
import com.goli.heroben.dao.IWithdrawDao;
//import com.goli.heroben.dao.impl.UserDaoImp;
import com.goli.heroben.dao.impl.WithdrawDaoImp;
import com.goli.heroben.dbc.DataBaseConnection;

import com.goli.heroben.vo.WithdrawBean;

public class WithdrawDaoProxy implements IWithdrawDao {
	private DataBaseConnection dbc = null;
	private WithdrawDaoImp dao = null;
	
	public WithdrawDaoProxy() throws Exception {
		this.dbc = new DataBaseConnection();
		this.dao = new WithdrawDaoImp(this.dbc.getConnection());
	}
	
	
	@Override
	public boolean doCreate(WithdrawBean withdrawBean) throws Exception {
		boolean flag = false;
		try {
			if (this.dao.findWithdrawById(withdrawBean.getId()) == null) {
				flag = this.dao.doCreate(withdrawBean);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doRemoveById(String id) throws Exception {
		boolean flag = false;
		try {
			if (this.dao.findWithdrawById(id) != null){
			flag = this.dao.doRemoveById(id);}
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
		
	}

	@Override
	public boolean doRemoveByUserId(String userId) throws Exception {
		boolean flag = false;
		try {
			if (this.dao.findWithdrawByUserId(userId) != null){
			flag = this.dao.doRemoveByUserId(userId);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doRemoveByPlatformId(String platid) throws Exception {
		boolean flag = false;
		try {
			if (this.dao.findWithdrawByPlatId(platid)!= null){
			flag = this.dao.doRemoveByPlatformId(platid);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doUpdateId(String oId, String nId) throws Exception {
		boolean flag = false;
		try {
			if(this.dao.findWithdrawById(oId) != null){
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
	public boolean doUpdateUserId(String oId, String nId) throws Exception {
		boolean flag = false;
		try {
			if(this.dao.findWithdrawByUserId(oId) != null){
				flag = this.dao.doUpdateUserId(oId, nId);
			}
		}catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doUpdateMoney(String id, double nMoney) throws Exception {
		boolean flag = false;
		try {
			if(this.dao.findWithdrawById(id) != null){
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
	public boolean doUpdateUserPlatId(String id, String platid) throws Exception {
		boolean flag = false;
		try {
			if(this.dao.findWithdrawById(id) != null){
				flag = this.dao.doUpdateUserPlatId(id, platid);
			}
		}catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean doUpdatePayTime(String id, Date nTime) throws Exception {
		boolean flag = false;
		try {
			if(this.dao.findWithdrawById(id) != null){
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
	public boolean doUpdatePlatFormId(String id, String nPlatId) throws Exception {
		boolean flag = false;
		try {
			if(this.dao.findWithdrawById(id) != null){
				flag = this.dao.doUpdatePlatFormId(id, nPlatId);
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
			if(this.dao.findWithdrawById(id) != null){
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
	public boolean doUpdateAll(WithdrawBean oWithdraw, WithdrawBean nWithdraw) throws Exception {//
		boolean flag = false;
		try {
			if(this.dao.findWithdrawById(oWithdraw.getId()) != null){
				flag = this.dao.doUpdateAll(oWithdraw, nWithdraw);
			}
		}catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public WithdrawBean findWithdrawById(String id) throws Exception {
		WithdrawBean withdrawBean = null;
		try {
			withdrawBean = this.dao.findWithdrawById(id);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return withdrawBean;
	}

	@Override
	public List<WithdrawBean> findWithdrawByUserId(String userid) throws Exception {
		List<WithdrawBean> all = null;
		try {
			all = this.dao.findWithdrawByUserId(userid);
		} catch (Exception e) {
			throw e;
		}finally {
			this.dbc.close();
		}
		return all;
	}

	@Override
	public WithdrawBean findWithdrawByPlatId(String platid) throws Exception {
		WithdrawBean withdrawBean = null;
		try {
			withdrawBean = this.dao.findWithdrawByPlatId(platid);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return withdrawBean;
	}

	@Override
	public List<WithdrawBean> findWithdrawByPlatName(String name) throws Exception {
		List<WithdrawBean> all = null;
		try {
			all = this.dao.findWithdrawByPlatName(name);
		} catch (Exception e) {
			throw e;
		}finally {
			this.dbc.close();
		}
		return all;
	}
}
