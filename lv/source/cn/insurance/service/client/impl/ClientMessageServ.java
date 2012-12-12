package cn.insurance.service.client.impl;

import java.util.List;

import cn.insurance.commonwords.MessageKey;
import cn.insurance.dao.ITbMessageDao;
import cn.insurance.dao.ITbMonthPayInfoDao;
import cn.insurance.model.PageBean;
import cn.insurance.service.client.IClientMessageServ;

public class ClientMessageServ implements IClientMessageServ {
	
	private ITbMessageDao tbMessageDao ;
	
	private ITbMonthPayInfoDao tbMonthPayInfoDao;
	
	public ITbMonthPayInfoDao getTbMonthPayInfoDao() {
		return tbMonthPayInfoDao;
	}

	public void setTbMonthPayInfoDao(ITbMonthPayInfoDao tbMonthPayInfoDao) {
		this.tbMonthPayInfoDao = tbMonthPayInfoDao;
	}

	/*
	 * 
	 * (non-Javadoc)
	 * @see cn.insurance.service.client.IClientMessageServ#getMessageByPartmentId(cn.insurance.model.PageBean, java.lang.Integer, java.lang.Integer)
	 */
	public PageBean getDYMessage(PageBean pageBean,Integer partmentId) {
		// TODO Auto-generated method stub
		return tbMessageDao.getMessageByPartmentId(pageBean, partmentId, MessageKey.messageType2 );
	}

	public ITbMessageDao getTbMessageDao() {
		return tbMessageDao;
	}

	public void setTbMessageDao(ITbMessageDao tbMessageDao) {
		this.tbMessageDao = tbMessageDao;
	}

	public List<String> getMessage_2010(Integer partmentId) {
		return this.tbMonthPayInfoDao.getMessage_2010(partmentId);
	}
	
}
