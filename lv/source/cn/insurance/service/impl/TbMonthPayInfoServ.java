//package cn.insurance.service.impl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import cn.insurance.dao.ITbAccountDao;
//import cn.insurance.dao.ITbBillDao;
//import cn.insurance.dao.ITbListForMonthPayDao;
//import cn.insurance.dao.ITbMonthPayInfoDao;
//import cn.insurance.dao.ITbMonthPayOutLogDao;
//import cn.insurance.dao.ITbPartmentDao;
//import cn.insurance.model.PageBean;
//import cn.insurance.model.TbMonthPayInfo;
//import cn.insurance.model.TbMonthPayOutLog;
//import cn.insurance.model.TbPartment;
//import cn.insurance.service.ITbMonthPayInfoServ;
//import cn.insurance.utils.CommonWords;
//import cn.insurance.utils.DateUtil;
//
//public class TbMonthPayInfoServ implements ITbMonthPayInfoServ {
//	
//	private ITbMonthPayInfoDao tbMonthPayInfoDao ;
//	
//	private ITbPartmentDao tbPartmentDao ;
//	
//	private ITbBillDao tbBillDao ;
//	
//	private ITbListForMonthPayDao tbListForMonthPayDao ;
//	
//	private ITbAccountDao tbAccountDao ;
//	
//	private ITbMonthPayOutLogDao tbMonthPayOutLogDao ;
//	
//		
//	/*
//	 * 按ID查询月费记录
//	 * (non-Javadoc)
//	 * @see cn.insurance.service.ITbMonthPayInfoServ#getMonthPayInfoById(java.lang.Integer)
//	 */
//	public TbMonthPayInfo getMonthPayInfoById(Integer id){
//		return tbMonthPayInfoDao.getObjectInfoById(id) ;
//	}
//	
//	
////	/*
////	 * 查询该帐户所有以前有缓付但没有付费的月费记录
////	 * (non-Javadoc)
////	 * @see cn.insurance.service.ITbMonthPayInfoServ#getHfMonthPayByAccountId()
////	 */
////	public List<TbListForMonthPay> getAllHfMonthPayBillListByAccountId(Integer accountId , Integer monthPayId){
////		return tbListForMonthPayDao.getAllQianFeiListByAccountId(accountId , monthPayId) ;
////	}
////	
//	
//	
////	/*
////	 * 用户申请缓交清单
////	 * (non-Javadoc)
////	 * @see cn.insurance.service.ITbMonthPayInfoServ#applyHjMonthByUser(java.lang.Integer, int[])
////	 */
////	public int applyHjMonthByUser(Integer monthId , int[] ids) {
////		TbMonthPayInfo tbMonthPayInfo = getMonthPayInfoById(monthId) ;
////		/*检查是否付过费*/
////		if(tbMonthPayInfo.getIntIsPay() == CommonWords.monthPayState2){
////			return 0 ;
////		}
////		/*将所有由这个月交费的记录都改为缓付*/
////		tbListForMonthPayDao.updateListByMonthPayIdToJiesuan(tbMonthPayInfo.getId(), 0) ;
////		/*将选择的保单清单都更新为有这个月费付费*/
////		tbListForMonthPayDao.updateListByMonthPayIdToJiesuan(ids, tbMonthPayInfo.getId()) ;
////		/*查出这些提交过来的保单的信息*/
////		List<TbBill> billList = tbBillDao.getBillListByIdList(ids) ;
////		if(billList == null || billList.size() ==0){
////			tbMonthPayInfo.setDbeNeedToPay(0.0) ;
////		}else{
////			/*算出总的费用*/
////			double allFee = 0.0 ;
////			for(TbBill bill : billList){
////				allFee += bill.getDbeAllFee() ;
////			}
////			tbMonthPayInfo.setDbeNeedToPay(allFee) ;
////		}
////		tbMonthPayInfoDao.update(tbMonthPayInfo) ;
////		return  2 ;
////	}
//	
//	
//	/*
//	 * 根据旅行社ID查询该旅行社下所有的月费统计
//	 * (non-Javadoc)
//	 * @see cn.insurance.service.ITbMonthPayInfoServ#getPartmentMonthStatByCompanyId(java.lang.Integer)
//	 */
//	public List<TbMonthPayInfo> getPartmentMonthStatByCompanyId(Integer companyId , String year,String month) {
//		List<TbMonthPayInfo> monthPayList = new ArrayList<TbMonthPayInfo>() ;
//		List<TbPartment> partmentList = tbPartmentDao.getAllPartmentListByCompanyId(companyId);
//		/*先查出总社的*/
//		TbPartment company = tbPartmentDao.getObjectInfoById(companyId) ;
//		monthPayList.add(getPartmentMonthStat(company , year , month))  ;
//		/*再查部门的*/
//		for(TbPartment partment : partmentList){
//			monthPayList.add(getPartmentMonthStat(partment,year,month))  ;
//		}
//		return monthPayList;
//	}
//
//	/*
//	 * 查询部门的月费统计
//	 * (non-Javadoc)
//	 * @see cn.insurance.service.ITbMonthPayInfoServ#getPartmentMonthStatByPartmentId(java.lang.Integer)
//	 */
//	public TbMonthPayInfo getPartmentMonthStatByPartmentId(Integer partmentId,String year ,String month){
//		TbPartment tbPartment = tbPartmentDao.getObjectInfoById(partmentId) ;
//		return getPartmentMonthStat(tbPartment,year ,month) ;
//		
//	}
//	
//	/**
//	 * 按部门查询该部门的月费统计
//	 * @param tbPartment
//	 * @return
//	 */
//	private TbMonthPayInfo getPartmentMonthStat(TbPartment tbPartment,  String year ,String month ) {
//		List all  = tbMonthPayInfoDao.getMonthPayByYearAndMonth(year, month, tbPartment.getTbAccount().getId()) ;
//		if(all != null && all.size()>0){
//			return (TbMonthPayInfo)all.get(0) ;
//		}else{
//			TbMonthPayInfo tbMonthPayInfo = new TbMonthPayInfo() ;
//			tbMonthPayInfo.setId(-1) ;
//			tbMonthPayInfo.setTbPartment(tbPartment) ;
//			tbMonthPayInfo.setDbeNeedToPay(0.0) ;
//			tbMonthPayInfo.setDbeHfFee(0.0) ;
//			tbMonthPayInfo.setIntIsPay(-1) ;
//			tbMonthPayInfo.setDteStartTime(DateUtil.getDteTimeForMonthFeeLog(year, month, 1)) ;
//			tbMonthPayInfo.setDteEndTime(DateUtil.getDteTimeForMonthFeeLog(year, month, 2)) ;
//			return tbMonthPayInfo ;
//		}
//	}
//	
//	
//	/*
//	 * 按部门ID查询部门的所有月费结算记录
//	 * (non-Javadoc)
//	 * @see cn.insurance.service.ITbMonthPayInfoServ#getAllMonthFeeLogByPartmentId(cn.insurance.model.PageBean, java.lang.Integer)
//	 */
//	public PageBean getAllMonthFeeLogByPartmentId(PageBean pageBean , Integer partmentId){
//		TbPartment tbPartment = tbPartmentDao.getObjectInfoById(partmentId) ;
//		return getMonthPayByAccountId(pageBean ,tbPartment.getTbAccount().getId() );
//	}
//	
//	
//	
//	/*
//	 * 按帐户ID查询某个帐户的所有月结算记录并分页
//	 * (non-Javadoc)
//	 * @see cn.insurance.service.ITbAccountServ#getMonthPayByAccountId(cn.insurance.model.PageBean, java.lang.Integer)
//	 */
//	public PageBean getMonthPayByAccountId(PageBean pageBean , Integer accountId){
//		return tbMonthPayInfoDao.getMonthPayByAccontId(pageBean, accountId) ;
//	}
//	
//
//	
//	
//	/*
//	 * 月费结算收费
//	 * (non-Javadoc)
//	 * @see cn.insurance.service.ITbAccountServ#shouMonthFee(cn.insurance.model.TbMonthPayInfo)
//	 */
//	public int shouMonthFee(TbMonthPayOutLog tbMonthPayOutLog ){
//		TbMonthPayInfo tbMonthPayInfo = getMonthPayInfoById(tbMonthPayOutLog.getIntMonthPayId()) ;
//		/*检查收费了没有*/
//		if(tbMonthPayInfo.getIntIsPay() == CommonWords.monthPayState2){
//			return 0 ;
//		}
//		/*将清单里的单子更新为收费状态*/
//		tbListForMonthPayDao.updateBillStateByMonthPayIdForShouFei(tbMonthPayInfo.getId()) ;
//		/*将月费记录更新为收费状态*/
//		tbMonthPayInfo.setIntIsPay(CommonWords.monthPayState2) ;
//		tbMonthPayInfoDao.update(tbMonthPayInfo) ;
//		/*添加新的记录*/
//		tbMonthPayOutLog.setDbePayNumber(tbMonthPayInfo.getDbeNeedToPay()) ;
//		tbMonthPayOutLogDao.addTbMonthPayOutLog(tbMonthPayOutLog) ;
//		return 2 ;
//	}
//	
//	
//	
//	/*
//	 * 根据月费ID数组查询所有月费
//	 * (non-Javadoc)
//	 * @see cn.insurance.service.ITbMonthPayInfoServ#getPartmentFeeByIdList(int)
//	 */
//	public List<TbMonthPayInfo> getPartmentFeeByIdList(int[] monthPayInfoIdList) {
//		return tbMonthPayInfoDao.getPartmentFeeByIdList(monthPayInfoIdList) ;
//	}
//	
//	/*
//	 * 对多个部门一起收费
//	 * (non-Javadoc)
//	 * @see cn.insurance.service.ITbMonthPayInfoServ#showPartmentFeeByIdList(int[], cn.insurance.model.TbMonthPayInfo)
//	 */
//	public int showPartmentFeeByIdList(int[] monthPayInfoId ,TbMonthPayOutLog tbMonthPayOutLog){
//		for(int i=0 ; i<monthPayInfoId.length ; i++){
//			tbMonthPayOutLog.setIntMonthPayId(monthPayInfoId[i]) ;
//			shouMonthFee(tbMonthPayOutLog) ;
//		}
//		return 1 ;
//	}
//	
//	/*
//	 * 验证该用户所在帐户在规定时间前的月费是否已交费
//	 * (non-Javadoc)
//	 * @see cn.insurance.service.ITbMonthPayInfoServ#checkTheUserPartmentMonthFee(cn.insurance.model.TbUser)
//	 */
//	public boolean checkTheUserPartmentMonthFee(int accountId) {
//		return tbMonthPayInfoDao.checkTheUserPartmentMonthFee(accountId) ;
//	}
//	
//	
//
//	public ITbPartmentDao getTbPartmentDao() {
//		return tbPartmentDao;
//	}
//
//
//
//	public void setTbPartmentDao(ITbPartmentDao tbPartmentDao) {
//		this.tbPartmentDao = tbPartmentDao;
//	}
//
//
//
//	public ITbMonthPayInfoDao getTbMonthPayInfoDao() {
//		return tbMonthPayInfoDao;
//	}
//
//
//
//	public void setTbMonthPayInfoDao(ITbMonthPayInfoDao tbMonthPayInfoDao) {
//		this.tbMonthPayInfoDao = tbMonthPayInfoDao;
//	}
//
//
//
//	public ITbBillDao getTbBillDao() {
//		return tbBillDao;
//	}
//
//
//
//	public void setTbBillDao(ITbBillDao tbBillDao) {
//		this.tbBillDao = tbBillDao;
//	}
//
//
//	public ITbListForMonthPayDao getTbListForMonthPayDao() {
//		return tbListForMonthPayDao;
//	}
//
//
//	public void setTbListForMonthPayDao(ITbListForMonthPayDao tbListForMonthPayDao) {
//		this.tbListForMonthPayDao = tbListForMonthPayDao;
//	}
//
//	public ITbAccountDao getTbAccountDao() {
//		return tbAccountDao;
//	}
//
//	public void setTbAccountDao(ITbAccountDao tbAccountDao) {
//		this.tbAccountDao = tbAccountDao;
//	}
//
//	public ITbMonthPayOutLogDao getTbMonthPayOutLogDao() {
//		return tbMonthPayOutLogDao;
//	}
//
//	public void setTbMonthPayOutLogDao(ITbMonthPayOutLogDao tbMonthPayOutLogDao) {
//		this.tbMonthPayOutLogDao = tbMonthPayOutLogDao;
//	}
//
//}
