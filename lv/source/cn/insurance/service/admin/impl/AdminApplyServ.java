package cn.insurance.service.admin.impl;

import cn.insurance.dao.ITbApplyDao;
import cn.insurance.model.PageBean;
import cn.insurance.model.TbApply;
import cn.insurance.service.admin.IAdminApplyServ;

public class AdminApplyServ implements IAdminApplyServ{
	
	private ITbApplyDao tbApplyDao ;
	
	/*
	 * 按id查询
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbApplyServ#getApplyById(java.lang.Integer)
	 */
	public TbApply getApplyById(Integer id){
		return tbApplyDao.getApplyById(id) ;
	}
	
	
	/*
	 * 
	 * 查询所有已经回复的申请
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbApplyServ#getAllHasApplyPageBean(cn.insurance.model.PageBean)
	 */
	public PageBean getAllHasApplyPageBean(PageBean pageBean) {
		// TODO Auto-generated method stub
		return tbApplyDao.getAllHasApplyPageBean(pageBean);
	}
	
	/*
	 * 查询所有还没有回复的申请
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbApplyServ#getAllNeedApplyPageBean(cn.insurance.model.PageBean)
	 */
	public PageBean getAllNeedApplyPageBean(PageBean pageBean) {
		// TODO Auto-generated method stub
		return tbApplyDao.getAllNeedApplyPageBean(pageBean);
	}
	
	
	/*
	 * 保险公司回复申请
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbApplyServ#updateTbApplyByBx(cn.insurance.model.TbApply)
	 */
	public int addReplyByBx(TbApply tbApply) {
		tbApply.setIntIsReply(1) ;
		return tbApplyDao.addReplyByBx(tbApply);
	}


	public ITbApplyDao getTbApplyDao() {
		return tbApplyDao;
	}


	public void setTbApplyDao(ITbApplyDao tbApplyDao) {
		this.tbApplyDao = tbApplyDao;
	}
	
	
}
