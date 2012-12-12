package cn.insurance.service.client.impl;

import cn.insurance.dao.ITbApplyDao;
import cn.insurance.model.PageBean;
import cn.insurance.model.TbApply;
import cn.insurance.service.client.IClientApplyServ;

public class ClientApplyServ implements IClientApplyServ{
	
	private ITbApplyDao tbApplyDao ;
	
	/*
	 * 添加申请
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbApplyServ#addTbApply(cn.insurance.model.TbApply)
	 */
	public int addTbApply(TbApply tbApply) {
		/*设置为未回复*/
		tbApply.setIntIsReply(0) ;
		tbApplyDao.addTbApply(tbApply);
		return 1 ;
	}
	
	/*
	 * 
	 * (non-Javadoc)
	 * @see cn.insurance.service.client.IClientApplyServ#updateTbApply(cn.insurance.model.TbApply)
	 */
	public int updateTbApply(TbApply tbApply){
		tbApplyDao.updateTbApply(tbApply) ;
		return 1 ;
	}
	
	/*
	 * 按id查询
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbApplyServ#getApplyById(java.lang.Integer)
	 */
	public TbApply getApplyById(Integer id){
		return tbApplyDao.getApplyById(id) ;
	}
	
	/*
	 *  按部门查询未回复的申请
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbApplyServ#getNotReplyApplyByPartmentId(cn.insurance.model.PageBean, java.lang.Integer)
	 */
	public PageBean getNotReplyApplyByPartmentId(PageBean pageBean , Integer partmentId){
		return tbApplyDao.getNotReplyApplyByPartmentId(pageBean, partmentId) ;
	}

	/*
	 * 按部门查询已回复的申请
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbApplyServ#getHasReplyApplyByPartmentId(cn.insurance.model.PageBean, java.lang.Integer)
	 */
	public PageBean getHasReplyApplyByPartmentId(PageBean pageBean , Integer partmentId){
		return tbApplyDao.getHasReplyApplyByPartmentId(pageBean, partmentId) ;
	}

	public ITbApplyDao getTbApplyDao() {
		return tbApplyDao;
	}

	public void setTbApplyDao(ITbApplyDao tbApplyDao) {
		this.tbApplyDao = tbApplyDao;
	}
	

	
}
