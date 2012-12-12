package cn.insurance.service.impl;

import cn.insurance.dao.ITbMessageDao;
import cn.insurance.model.PageBean;
import cn.insurance.model.TbMessage;
import cn.insurance.service.ITbMessageServ;

public class TbMessageServ implements ITbMessageServ {
	
	private ITbMessageDao tbMessageDao ;

	/*
	 * 按id查询消息
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbMessageServ#getMessageById(java.lang.Integer)
	 */
	public TbMessage getMessageById(Integer id) {
		// TODO Auto-generated method stub
		return tbMessageDao.getMessageById(id);
	}

	/*
	 * 按部门ID查询该部门的消息
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbMessageServ#getMessageByPartmentId(cn.insurance.model.PageBean, java.lang.Integer, java.lang.Integer)
	 */
	public PageBean getMessageByPartmentId(PageBean pageBean,
			Integer partmentId, Integer messageType) {
		// TODO Auto-generated method stub
		return tbMessageDao.getMessageByPartmentId(pageBean, partmentId, messageType);
	}

	/*
	 * 查询保险公司的消息
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbMessageServ#getMessageForBx(cn.insurance.model.PageBean, java.lang.Integer)
	 */
	public PageBean getMessageForBx(PageBean pageBean, Integer messageType) {
		// TODO Auto-generated method stub
		return tbMessageDao.getMessageForBx(pageBean, messageType);
	}
	
	/*
	 * 删除消息
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbMessageServ#deleteMessageById(java.lang.Integer)
	 */
	public int deleteMessageById(Integer id) {
		// TODO Auto-generated method stub
		return tbMessageDao.deleteMessageById(id);
	}

	
	
	
	public ITbMessageDao getTbMessageDao() {
		return tbMessageDao;
	}

	public void setTbMessageDao(ITbMessageDao tbMessageDao) {
		this.tbMessageDao = tbMessageDao;
	}
}
