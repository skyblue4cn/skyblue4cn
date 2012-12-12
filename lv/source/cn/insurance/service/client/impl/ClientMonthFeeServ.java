package cn.insurance.service.client.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.log.LogUtils;

import cn.insurance.dao.ITbAccountDao;
import cn.insurance.dao.ITbMonthPayInfoDao;
import cn.insurance.dao.ITbPartmentDao;
import cn.insurance.model.PageBean;
import cn.insurance.model.TbAccount;
import cn.insurance.model.TbMonthPayInfo;
import cn.insurance.model.TbPartment;
import cn.insurance.service.client.IClientMonthFeeServ;
import cn.insurance.service.client.IClientPartmentServ;
import cn.insurance.utils.DateUtil;
import cn.insurance.utils.PrintLog;

public class ClientMonthFeeServ implements IClientMonthFeeServ{
	
	private ITbMonthPayInfoDao tbMonthPayInfoDao ;
	
	private ITbAccountDao tbAccountDao ;
	
	private ITbPartmentDao tbPartmentDao;
	
	/*
	 * 按ID查询月费记录
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbMonthPayInfoServ#getMonthPayInfoById(java.lang.Integer)
	 */
	public TbMonthPayInfo getMonthPayInfoById(Integer id){
		return tbMonthPayInfoDao.getObjectInfoById(id) ;
	}
	
	
	/*
	 * 按帐户ID查询某个帐户的所有月结算记录并分页
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbAccountServ#getMonthPayByAccountId(cn.insurance.model.PageBean, java.lang.Integer)
	 */
	public PageBean getMonthPayByAccountId(PageBean pageBean , Integer accountId){
		return tbMonthPayInfoDao.getMonthPayByAccontId(pageBean, accountId) ;
	}

	/*
	 * 检查帐户是否可以投保
	 * (non-Javadoc)
	 * @see cn.insurance.service.client.IClientMonthFeeServ#checkTheUserPartmentMonthFee(int)
	 */
	public boolean checkTheUserPartmentMonthFee(int accountId){
		List<TbMonthPayInfo> notPayMonthInfoList = tbMonthPayInfoDao.getAllNotPayMonthLog(accountId);
		TbAccount account = tbAccountDao.getObjectInfoById(accountId) ;
		if(notPayMonthInfoList == null || notPayMonthInfoList.size() == 0){
			return true ;
		}
		Date today = new Date();
		for(TbMonthPayInfo tbMonthPayInfo : notPayMonthInfoList){
			int num = DateUtil.getNumberFormDate(tbMonthPayInfo.getDteEndTime(),today);
			PrintLog.Log("该旅行社欠费时间：" + num) ;
			if(num>account.getIntAcceptDays()){
				return false ;
			}
		}
		
		return true ;
	}
	
	/*
	 * 根据旅行社ID查询该旅行社总社及部门的所有月费
	 * (non-Javadoc)
	 * @see cn.insurance.service.client.IClientMonthFeeServ#getPartmentMonthPayInfoByCompanyId(int, int, java.lang.Integer)
	 */
	public List<TbMonthPayInfo> getPartmentMonthPayInfoByCompanyId(int year ,int month , Integer companyId){
		List<TbPartment> partmentList = new ArrayList<TbPartment>();
		TbPartment company = tbPartmentDao.getObjectInfoById(companyId) ;
		partmentList.add(company) ;
		partmentList.addAll(tbPartmentDao.getAllPartmentListByCompanyId(companyId));
		List<TbMonthPayInfo> monthPayList = new ArrayList<TbMonthPayInfo>();
		for(TbPartment p : partmentList){
			monthPayList.addAll(tbMonthPayInfoDao.getMonthPayByYearAndMonth(String.valueOf(year), String.valueOf(month), p.getTbAccount().getId())) ;
		}
		return monthPayList ;
	}

	
	
	/*
	 * 根据旅行社ID查询该旅行社总社及部门的所有月费
	 * (non-Javadoc)
	 * @see cn.insurance.service.client.IClientMonthFeeServ#getPartmentMonthPayInfoByPartmentId(int, int, java.lang.Integer)
	 */
	public List<TbMonthPayInfo> getPartmentMonthPayInfoByPartmentId(int year ,int month , Integer partmentId){
		TbPartment partment = tbPartmentDao.getObjectInfoById(partmentId) ;
		List<TbMonthPayInfo> monthPayList = tbMonthPayInfoDao.getMonthPayByYearAndMonth(String.valueOf(year), String.valueOf(month), partment.getTbAccount().getId());
		return monthPayList ;
	}
	
	public ITbMonthPayInfoDao getTbMonthPayInfoDao() {
		return tbMonthPayInfoDao;
	}


	public void setTbMonthPayInfoDao(ITbMonthPayInfoDao tbMonthPayInfoDao) {
		this.tbMonthPayInfoDao = tbMonthPayInfoDao;
	}


	public ITbAccountDao getTbAccountDao() {
		return tbAccountDao;
	}


	public void setTbAccountDao(ITbAccountDao tbAccountDao) {
		this.tbAccountDao = tbAccountDao;
	}


	public ITbPartmentDao getTbPartmentDao() {
		return tbPartmentDao;
	}


	public void setTbPartmentDao(ITbPartmentDao tbPartmentDao) {
		this.tbPartmentDao = tbPartmentDao;
	}


	
}
