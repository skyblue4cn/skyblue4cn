package cn.insurance.service.admin.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.insurance.dao.ITbAccountDao;
import cn.insurance.dao.ITbListForMonthPayDao;
import cn.insurance.dao.ITbMonthPayInfoDao;
import cn.insurance.dao.ITbMonthPayOutLogDao;
import cn.insurance.dao.ITbPartmentDao;
import cn.insurance.dao.ITbPayoutInfoDao;
import cn.insurance.model.PageBean;
import cn.insurance.model.TbAccount;
import cn.insurance.model.TbListForMonthPay;
import cn.insurance.model.TbMonthPayInfo;
import cn.insurance.model.TbMonthPayOutLog;
import cn.insurance.model.TbPartment;
import cn.insurance.model.TbPayOutInfo;
import cn.insurance.model.TbUser;
import cn.insurance.service.admin.IAdminMonthFeeServ;
import cn.insurance.utils.CommonWords;
import cn.insurance.utils.DateUtil;
import cn.insurance.utils.PrintLog;

public class AdminMonthFeeServ implements IAdminMonthFeeServ{
	
	private ITbMonthPayInfoDao tbMonthPayInfoDao ;
	
	private ITbPartmentDao tbPartmentDao ;
	
	private ITbListForMonthPayDao tbListForMonthPayDao ;
	
	private ITbMonthPayOutLogDao tbMonthPayOutLogDao ;
	
	private ITbAccountDao tbAccountDao ;
	
	private ITbPayoutInfoDao tbPayoutInfoDao ;
	
		
	/*
	 * 按ID查询月费记录
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbMonthPayInfoServ#getMonthPayInfoById(java.lang.Integer)
	 */
	public TbMonthPayInfo getMonthPayInfoById(Integer id){
		return tbMonthPayInfoDao.getObjectInfoById(id) ;
	}
	
	
	
	/*
	 * 根据旅行社ID查询该旅行社下所有的月费统计
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbMonthPayInfoServ#getPartmentMonthStatByCompanyId(java.lang.Integer)
	 */
	public List<TbMonthPayInfo> getPartmentMonthStatByCompanyId(Integer companyId , String year,String month) {
		List<TbMonthPayInfo> monthPayList = new ArrayList<TbMonthPayInfo>() ;
		List<TbPartment> partmentList = tbPartmentDao.getAllPartmentListByCompanyId(companyId);
		/*先查出总社的*/
		TbPartment company = tbPartmentDao.getObjectInfoById(companyId) ;
		monthPayList.add(getPartmentMonthStat(company , year , month))  ;
		/*再查部门的*/
		for(TbPartment partment : partmentList){
			monthPayList.add(getPartmentMonthStat(partment,year,month))  ;
		}
		return monthPayList;
	}

	/*
	 * 查询部门的月费统计
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbMonthPayInfoServ#getPartmentMonthStatByPartmentId(java.lang.Integer)
	 */
	public TbMonthPayInfo getPartmentMonthStatByPartmentId(Integer partmentId,String year ,String month){
		TbPartment tbPartment = tbPartmentDao.getObjectInfoById(partmentId) ;
		return getPartmentMonthStat(tbPartment,year ,month) ;
		
	}
	
	/**
	 * 按部门查询该部门的月费统计
	 * @param tbPartment
	 * @return
	 */
	private TbMonthPayInfo getPartmentMonthStat(TbPartment tbPartment,  String year ,String month ) {
		List all  = tbMonthPayInfoDao.getMonthPayByYearAndMonth(year, month, tbPartment.getTbAccount().getId()) ;
		if(all != null && all.size()>0){
			return (TbMonthPayInfo)all.get(0) ;
		}else{
			TbMonthPayInfo tbMonthPayInfo = new TbMonthPayInfo() ;
			tbMonthPayInfo.setId(-1) ;
			tbMonthPayInfo.setTbPartment(tbPartment) ;
			tbMonthPayInfo.setDbeNeedToPay(0.0) ;
			tbMonthPayInfo.setIntIsPay(-1) ;
//			tbMonthPayInfo.setDteStartTime(DateUtil.getDteTimeForMonthFeeLog(year, month, 1)) ;
//			tbMonthPayInfo.setDteEndTime(DateUtil.getDteTimeForMonthFeeLog(year, month, 2)) ;
			return tbMonthPayInfo ;
		}
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
	 * 对月费进行清算
	 * 
	 * (non-Javadoc)
	 * @see cn.insurance.service.admin.IAdminMonthFeeServ#jieSuan(java.lang.String, java.lang.String)
	 */
	public void jieSuan(String year, String month){
		//找出这个时间的所有月费(为防止有错误，这里还是查询未交费的)
		List<TbMonthPayInfo> list = tbMonthPayInfoDao.getAllNotPayMonthFee(year, month) ;
		if(list != null && list.size()>0){
			for(TbMonthPayInfo m : list){
				double allNeedFee = tbListForMonthPayDao.getYingFuMonthFeeByMonthId(m.getId()) ;
				if(m.getDbeNeedToPay() != allNeedFee){
					PrintLog.getLog().info("月费结算发现不一致：记录" +m.getDbeNeedToPay() +"实际:" + allNeedFee +",月费ID："+ m.getId()) ;
					m.setDbeNeedToPay(allNeedFee) ;
				}
				tbMonthPayInfoDao.update(m) ;
			}
		}
	}
	
	/*
	 * 查询该年月所有未交费的预存账户月费
	 * (non-Javadoc)
	 * @see cn.insurance.service.admin.IAdminMonthFeeServ#getAllNotPayYuCunMonthFee(int, int)
	 */
	public List<TbMonthPayInfo> getAllNotPayYuCunMonthFee(String year, String month) {
		return tbMonthPayInfoDao.getAllNotPayYuCunMonthFee(year, month) ;
	}
	
	/*
	 * 收费
	 * (non-Javadoc)
	 * @see cn.insurance.service.admin.IAdminMonthFeeServ#shouFei(int, int, cn.insurance.model.TbMonthPayOutLog)
	 */
	public int shouFei(int monthPayId ,int payType , TbMonthPayOutLog tbMonthPayOutLog){
		//查询出记录
		TbMonthPayInfo oldMonthPayInfo = getMonthPayInfoById(monthPayId) ;
		/*检查收费了没有*/
		if(oldMonthPayInfo.getIntIsPay() == CommonWords.monthPayState2){
			return 0 ;
		}
		/*将清单里的单子更新为收费状态,并将保单的状态更新为收费状态*/
		tbListForMonthPayDao.updateBillStateByMonthPayIdForShouFei(oldMonthPayInfo.getId()) ;
		/*将月费记录更新为收费状态*/
		oldMonthPayInfo.setIntIsPay(CommonWords.monthPayState2) ;
		oldMonthPayInfo.setDbePayNumber(oldMonthPayInfo.getDbeNeedToPay()) ;
		//现有的账户余额
		double curResidual = oldMonthPayInfo.getTbPartment().getTbAccount().getDbeResidual() ;
		//判断收费形式,看是月费是实际收取还是从账户里扣除
		//如果是从旅行社收费回来后确认收费
		if(payType == TbMonthPayInfo.JIAOFEI_PAY_TYPE){
			//收费前的余额
			oldMonthPayInfo.setDbeCurResidual(curResidual) ;
			//收费之后的余额不变
			oldMonthPayInfo.setDbeAftResidual(curResidual) ;
		}
		else if(payType == TbMonthPayInfo.ACCOUNT_PAY_TYPE){
			//收费前的余额
			oldMonthPayInfo.setDbeCurResidual(oldMonthPayInfo.getTbPartment().getTbAccount().getDbeResidual()) ;
			//从账户扣除该月的保费
			oldMonthPayInfo.getTbPartment().getTbAccount().setDbeResidual(curResidual - oldMonthPayInfo.getDbeNeedToPay()) ;
			//更新账户信息
			tbAccountDao.update(oldMonthPayInfo.getTbPartment().getTbAccount()) ;
			//收费后的账户余额
			oldMonthPayInfo.setDbeAftResidual(oldMonthPayInfo.getTbPartment().getTbAccount().getDbeResidual()) ;
			//添加账户支出记录
			TbPayOutInfo tbPayOutInfo = new TbPayOutInfo();
			tbPayOutInfo.setDbeCurResidual(oldMonthPayInfo.getDbeCurResidual()) ; //之前的账户余额
			tbPayOutInfo.setDbeAftResidual(oldMonthPayInfo.getDbeAftResidual()) ; //之后的账户余额
			tbPayOutInfo.setDtePayoutTime(new Date()) ;
			tbPayOutInfo.setIntFromUserId(tbMonthPayOutLog.getIntUserId()) ; 
			tbPayOutInfo.setStrSaveUserName(tbMonthPayOutLog.getStrUserName()) ;
			tbPayOutInfo.setDbePayoutNumber(oldMonthPayInfo.getDbeNeedToPay()) ;
			tbPayOutInfo.setIntAccountId(oldMonthPayInfo.getTbPartment().getTbAccount().getId()) ;
			tbPayOutInfo.setStrDesc(tbMonthPayOutLog.getStrDesc()) ;
			tbPayOutInfo.setIntType(CommonWords.payOutKind2) ; //系统记录为从账户扣钱交费
			tbPayoutInfoDao.create(tbPayOutInfo) ;
		
		}
		//更新月费为收费状态
		tbMonthPayInfoDao.update(oldMonthPayInfo) ;
		/*添加收费记录日志*/
		tbMonthPayOutLog.setDbePayNumber(oldMonthPayInfo.getDbeNeedToPay()) ;
		tbMonthPayOutLogDao.addTbMonthPayOutLog(tbMonthPayOutLog) ;
		return 1 ;
	}
	
	
	/*
	 * 月费结算收费
	 * 
	 * 早期的 已不使用
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbAccountServ#shouMonthFee(cn.insurance.model.TbMonthPayInfo)
	 */
	public int shouMonthFee(TbMonthPayOutLog tbMonthPayOutLog ){
		TbMonthPayInfo tbMonthPayInfo = getMonthPayInfoById(tbMonthPayOutLog.getIntMonthPayId()) ;
		/*检查收费了没有*/
		if(tbMonthPayInfo.getIntIsPay() == CommonWords.monthPayState2){
			return 0 ;
		}
		/*将清单里的单子更新为收费状态*/
		tbListForMonthPayDao.updateBillStateByMonthPayIdForShouFei(tbMonthPayInfo.getId()) ;
		/*将月费记录更新为收费状态*/
		tbMonthPayInfo.setIntIsPay(CommonWords.monthPayState2) ;
		tbMonthPayInfoDao.update(tbMonthPayInfo) ;
		/*添加新的记录*/
		tbMonthPayOutLog.setDbePayNumber(tbMonthPayInfo.getDbeNeedToPay()) ;
		tbMonthPayOutLogDao.addTbMonthPayOutLog(tbMonthPayOutLog) ;
		return 2 ;
	}
	
	
	
	/*
	 * 根据月费ID数组查询所有月费
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbMonthPayInfoServ#getPartmentFeeByIdList(int)
	 */
	public List<TbMonthPayInfo> getPartmentFeeByIdList(int[] monthPayInfoIdList) {
		return tbMonthPayInfoDao.getPartmentFeeByIdList(monthPayInfoIdList) ;
	}
	
	/*
	 * 对多个部门一起收费
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbMonthPayInfoServ#showPartmentFeeByIdList(int[], cn.insurance.model.TbMonthPayInfo)
	 */
	public int showPartmentFeeByIdList(int[] monthPayInfoId ,TbMonthPayOutLog tbMonthPayOutLog){
		for(int i=0 ; i<monthPayInfoId.length ; i++){
			tbMonthPayOutLog.setIntMonthPayId(monthPayInfoId[i]) ;
			shouMonthFee(tbMonthPayOutLog) ;
		}
		return 1 ;
	}

	public ITbPartmentDao getTbPartmentDao() {
		return tbPartmentDao;
	}

	public void setTbPartmentDao(ITbPartmentDao tbPartmentDao) {
		this.tbPartmentDao = tbPartmentDao;
	}



	public ITbMonthPayInfoDao getTbMonthPayInfoDao() {
		return tbMonthPayInfoDao;
	}



	public void setTbMonthPayInfoDao(ITbMonthPayInfoDao tbMonthPayInfoDao) {
		this.tbMonthPayInfoDao = tbMonthPayInfoDao;
	}



	public ITbListForMonthPayDao getTbListForMonthPayDao() {
		return tbListForMonthPayDao;
	}


	public void setTbListForMonthPayDao(ITbListForMonthPayDao tbListForMonthPayDao) {
		this.tbListForMonthPayDao = tbListForMonthPayDao;
	}

	public ITbMonthPayOutLogDao getTbMonthPayOutLogDao() {
		return tbMonthPayOutLogDao;
	}

	public void setTbMonthPayOutLogDao(ITbMonthPayOutLogDao tbMonthPayOutLogDao) {
		this.tbMonthPayOutLogDao = tbMonthPayOutLogDao;
	}



	public ITbAccountDao getTbAccountDao() {
		return tbAccountDao;
	}



	public void setTbAccountDao(ITbAccountDao tbAccountDao) {
		this.tbAccountDao = tbAccountDao;
	}



	public ITbPayoutInfoDao getTbPayoutInfoDao() {
		return tbPayoutInfoDao;
	}



	public void setTbPayoutInfoDao(ITbPayoutInfoDao tbPayoutInfoDao) {
		this.tbPayoutInfoDao = tbPayoutInfoDao;
	}

}
