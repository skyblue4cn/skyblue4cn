package cn.insurance.service.admin.impl;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import cn.insurance.dao.ITbAccountDao;
import cn.insurance.dao.ITbBillDao;
import cn.insurance.dao.ITbBillSureInfoDao;
import cn.insurance.dao.ITbListForMonthPayDao;
import cn.insurance.dao.ITbMessageDao;
import cn.insurance.dao.ITbMonthPayInfoDao;
import cn.insurance.dao.ITbPartmentDao;
import cn.insurance.dao.ITbPayoutInfoDao;
import cn.insurance.dao.ITbTravelerInfoDao;
import cn.insurance.model.PageBean;
import cn.insurance.model.TbAccount;
import cn.insurance.model.TbBill;
import cn.insurance.model.TbBillBackup;
import cn.insurance.model.TbListForMonthPay;
import cn.insurance.model.TbMonthPayInfo;
import cn.insurance.model.TbPartment;
import cn.insurance.model.TbPayOutInfo;
import cn.insurance.model.TbTravelerInfo;
import cn.insurance.service.IPriceServ;
import cn.insurance.service.admin.IAdminBillServ;
import cn.insurance.utils.CommonWords;
import cn.insurance.utils.DateUtil;
import cn.insurance.utils.FileUpload;

public class AdminBillServ implements IAdminBillServ{
	
	private ITbBillDao tbBillDao ;
	
	private ITbPartmentDao tbPartmentDao ;
	
	private ITbBillSureInfoDao tbBillSureInfoDao ;
	
	private ITbMessageDao tbMessageDao ;
	
	private ITbTravelerInfoDao tbTravelerInfoDao ;
	
	private IPriceServ priceServ ;
	
	private ITbPayoutInfoDao tbPayOutInfoDao ;
	
	private ITbMonthPayInfoDao tbMonthPayInfoDao ;
	
	private ITbAccountDao tbAccountDao ;
	
	private ITbListForMonthPayDao tbListForMonthPayDao ;
	
	/*
	 * 保险公司审查保单合格，保单通过审查,收费
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbBillServ#sureBillByBxSuccess(cn.insurance.model.TbBill)
	 */
	public int sureBillByBxSuccess(TbBill tbBill){
		TbBill oldBill = getTbBillById(tbBill.getId()) ;
		/*先查看这个保单能否被审核*/
		if(!checkIsCanSureByBx(oldBill.getIntBillStateId())){
			return 0 ;
		}
//		/*查出这个用户所在部门的信息（包括帐户信息，所在旅行社信息等）*/
		TbPartment tbPartment = tbPartmentDao.getObjectInfoById(oldBill.getIntPartmentId()) ;
//		if(tbPartment.getCompany().getTbAccount().getIntPayTypeId()==CommonWords.payType3){
//			/*如果该部门所在旅行社的帐户是年费包干用户，则交费方式为3*/
//			oldBill.setIntPayType(CommonWords.payType3) ;
//			/*将保单设置为收费状态*/
//			oldBill.setIntIsPay(CommonWords.billIsPay3) ;
//		}else{
//			/*月结和预存都根据该用户部门所在帐户的情况来决定*/
//			oldBill.setIntPayType(tbPartment.getTbAccount().getIntPayTypeId()) ;
//		}
//		
//		/*如果是预付费用户，则要从帐户扣钱*/
//		if(oldBill.getIntPayType() == CommonWords.payType1){
//			payBillFeeByYuCunAccount(oldBill , tbPartment.getTbAccount()) ;
//			/*将保单设置为已收费状态*/
//			oldBill.setIntIsPay(CommonWords.billIsPay3) ;
//		}
//		/*如果是月结算用户，则将费用计入该月*/
//		if(oldBill.getIntPayType() == CommonWords.payType2) {
//			payBillFeeByMonthAccount(oldBill, tbPartment.getTbAccount()) ;
//			/*保单为未付费状态*/
//			oldBill.setIntIsPay(CommonWords.billIsPay2) ;
//		}
		
		/*以前是分为月结、预存、年费等，这里不再做区分，全部设置为月结，等到收费的时候让管理员选择收费方式*/
		payBillFeeByMonthAccount(oldBill, tbPartment.getTbAccount()) ;
		oldBill.setIntIsPay(CommonWords.billIsPay2) ;
		/*将保单设置为生效状态*/
		oldBill.setIntBillStateId(CommonWords.billState4) ;
		oldBill.setStrSureUserName(tbBill.getTbBillSureInfo().getStrSureUserName()) ;
		oldBill.setDteSureTime(tbBill.getTbBillSureInfo().getDteSureTime()) ;
		/*更新保单信息*/
		tbBillDao.update(oldBill) ;
		/*添加确认记录*/
		addSureLog(CommonWords.sureType2, tbBill, oldBill ,null, "确认") ;
		/*发送信息到部门*/
		StringBuffer message = new StringBuffer() ;
		message.append("保单:").append(oldBill.getStrBillNumber())
			.append("已通过保险公司确认！") ;
		message.append("保单费用已计入当月费用当中.") ;
//		int result = 0 ;
//		/*将帐户收费根据不同的类型返回结果*/
//		if(oldBill.getIntPayType() == CommonWords.payType1){
//			/*保单费用是从预存帐户里扣除*/
//			message.append("保单费用已从预存账户里扣除.") ;
//			result = 2;
//		}
//		if(oldBill.getIntPayType() == CommonWords.payType2){
//			/*保单费用计入当月结算*/
//			message.append("保单费用已计入当月费用当中.") ;
//			result =  3 ;
//		}
//		if(oldBill.getIntPayType() == CommonWords.payType3){
//			/*保单费用将从年费结算*/
//			message.append("保单费用已从总社年费里扣除.") ;
//			result = 4 ;
//		}
	
		tbMessageDao.sendMessage(CommonWords.messageType2, oldBill.getIntPartmentId(), message.toString()) ;
		return 3 ;
		
	}
	
	/*
	 * 保单未通过保险公司审查，退回保单
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbBillServ#returnBillByBx(cn.insurance.model.TbBill)
	 */
	public int returnBillByBx(TbBill tbBill ){
		TbBill oldBill = getTbBillById(tbBill.getId()) ;

		/*先查看这个保单能否被审核*/
		if(!checkIsCanSureByBx(oldBill.getIntBillStateId())){
			return 0 ;
		}
		/*将保单设置为退回状态*/
		oldBill.setIntBillStateId(CommonWords.billState6) ;
		/*记录退回原因*/
		oldBill.setStrReturnReason(tbBill.getStrReturnReason()) ;
		/*更新保单信息*/
		tbBillDao.update(oldBill) ;
		/*添加确认记录*/
		addSureLog(CommonWords.sureType2, tbBill, oldBill,tbBill.getStrReturnReason(),"退单") ;
		/*记录确认信息*/
		tbBillSureInfoDao.addTbBillSureInfoDao(tbBill.getTbBillSureInfo()) ;
		
		/*发送消息到部门*/
		StringBuffer message = new StringBuffer() ;
		message.append("保单：").append(tbBill.getStrBillNumber())
			.append(" 未通过保险公司确认，已被退回！退回原因：").append(tbBill.getStrReturnReason()) ;
		tbMessageDao.sendMessage(CommonWords.messageType2, tbBill.getIntPartmentId(), message.toString()) ;
		return 2 ;
	}
	
	/*
	 * 保险公司将保单做备案处理
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbBillServ#beiAnBillByBx(cn.insurance.model.TbBill)
	 */
	public int beiAnBillByBx(TbBill tbBill){
		TbBill oldBill = getTbBillById(tbBill.getId()) ;
		/*先查看这个保单能否被审核*/
		if(!checkIsCanSureByBx(oldBill.getIntBillStateId())){
			return 0 ;
		}
		/*将保单设置为备案状态*/
		oldBill.setIntBillStateId(CommonWords.billState7) ;
		/*更新备案原因*/
		oldBill.setIntBeiAnReason(tbBill.getIntBeiAnReason()) ;
		/*更新保单信息*/
		tbBillDao.update(oldBill) ;
		/*添加确认记录*/
		addSureLog(CommonWords.sureType2, tbBill, oldBill,CommonWords.getBeiAnReason(tbBill.getIntBeiAnReason()),"备案") ;
		/*发送信息给部门*/
		StringBuffer message = new StringBuffer() ;
		message.append("保单:").append(tbBill.getStrBillNumber()).append(" 被保险公司备案处理，请在规定时间内及时填写保单所需要的信息，否则保单将无效并被退回！") ;
		return 1;
	}
	/*
	 * 保险公司更新保单
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbBillServ#updateBillByBx(cn.insurance.model.TbBill)
	 */
	public int updateBillByBx(TbBill tbBill, File file ,String fileName, List<TbTravelerInfo> travelerList , TbBillBackup tbBillBackup){
		/*检查该保单是否是已确认保单*/
		TbBill oldBill = tbBillDao.getObjectInfoById(tbBill.getId()) ;
		if(oldBill.getIntBillStateId() != CommonWords.billState4 ){
			return 0 ;
		}
		
		/*先看是否有附件需要上传*/
		if(file != null) {
			try{
				uploadTravelFile(oldBill, file, fileName);
			}catch(Exception e){
				return 1 ;
			}
		}
		tbBill.setStrFileUrl(oldBill.getStrFileUrl()) ;
		/*删除以前的旅客信息*/
		tbTravelerInfoDao.deleteByBillId(tbBill.getId()) ;
		/*添加新的游客信息*/
		if(travelerList != null && travelerList.size() >0){
			tbTravelerInfoDao.create(travelerList);
		}
		tbBill.setStrBillName(CommonWords.getBillName(tbBill.getIntKindId())) ;
		/*计算该保单需要的费用*/
		priceServ.getBillPrice(tbBill) ;
		/*更新新的保单*/
		tbBillDao.updateBillByBx(tbBill) ;
		return 2 ;
	}
	
	
	/**
	 * 上传保单的旅客文件
	 *
	 */
	private void uploadTravelFile(TbBill tbBill , File file , String fileName) throws Exception{
		Calendar calendar = new GregorianCalendar() ;
		String date = DateUtil.getFormatDate(calendar, "-yy-MM-dd-hh-mm-ss") ;
		//保存路径是 truavelerFile/年/旅行社ID/部门ID/保单号/
		StringBuffer filePath = new StringBuffer() ;
		filePath.append("travelerFile").append("/").append(calendar.get(Calendar.YEAR)).append("/") ;
		filePath.append(tbBill.getTbPartment().getCompany().getId()).append("/").append(tbBill.getTbPartment().getId()).append("/");
		filePath.append(tbBill.getStrBillNumber());
		/*保单号+后缀名给文件重新命名（中文名称的文件下载不太好处理）*/
		String reName = tbBill.getStrBillNumber()+ date + fileName.substring(fileName.lastIndexOf("."),fileName.length()) ;
		FileUpload.uploadFile(file, filePath.toString(), reName) ;
		tbBill.setStrFileUrl(filePath + "/" + reName) ;
	}
	
	/*
	 * 保险公司将已确认的保单又作废
	 * (non-Javadoc)
	 * @see cn.insurance.service.admin.IAdminBillServ#returnSureBillByBx(cn.insurance.model.TbBill)
	 */
	public int returnSureBillByBx(TbBill tbBill){
		/*检查该保单是否是已确认保单*/
		TbBill oldBill = tbBillDao.getObjectInfoById(tbBill.getId()) ;
		if(oldBill.getIntBillStateId() != CommonWords.billState4 ){
			return 0 ;
		}
		/*将保单设置为退回状态*/
		oldBill.setIntBillStateId(CommonWords.billState6) ;
		/*记录退回原因*/
		oldBill.setStrReturnReason(tbBill.getStrReturnReason()) ;
		/*更新保单信息*/
		tbBillDao.update(oldBill) ;
		//从月费记录清单中删除保单
		tbListForMonthPayDao.deleteBillListByBillId(oldBill.getId()) ;
		/*添加确认记录*/
		addSureLog(CommonWords.sureType2, tbBill, oldBill,tbBill.getStrReturnReason(),"退单") ;
		/*记录确认信息*/
		tbBillSureInfoDao.addTbBillSureInfoDao(tbBill.getTbBillSureInfo()) ;
		/*发送消息到部门*/
		StringBuffer message = new StringBuffer() ;
		message.append("保单：").append(tbBill.getStrBillNumber())
			.append("已被退回！退回原因：").append(tbBill.getStrReturnReason()) ;
		tbMessageDao.sendMessage(CommonWords.messageType2, tbBill.getIntPartmentId(), message.toString()) ;
		return 1 ;
	}
	
	
	/**
	 * 检查该保单是否可以让保险公司审核确认
	 * 防止某些保单多确认而被收费多次
	 * @param billState
	 * @return
	 */
	private boolean checkIsCanSureByBx(int billState){
		if(billState == CommonWords.billState3){
			return true ;
		}
		if(billState == CommonWords.billState7){
			return true ;
		}
		if(billState == CommonWords.billState8){
			return true ;
		}
		
		return false ;
	}
	
	
//	预存也全部给位一个月一结算  统一
	
//	/**
//	 * 预付费帐户支付保单费用
//	 * @param tbBill
//	 * @param tbAccount
//	 */
//	private void payBillFeeByYuCunAccount(TbBill tbBill , TbAccount tbAccount){
//		/*新增一条预存帐户收费记录*/
//		TbPayOutInfo tbPayOutInfo = new TbPayOutInfo() ;
//		tbPayOutInfo.setIntType(CommonWords.payOutKind2) ;
//		tbPayOutInfo.setIntAccountId(tbAccount.getId()) ;
//		tbPayOutInfo.setDtePayoutTime(new Date()) ;
//		tbPayOutInfo.setIntBillId(tbBill.getId()) ;
//		tbPayOutInfo.setIntFromUserId(tbBill.getTbBillSureInfo().getIntSureUserId()) ;
//		tbPayOutInfo.setStrSaveUserName(tbBill.getTbBillSureInfo().getStrSureUserName()) ;
//		tbPayOutInfo.setDbeCurResidual(tbAccount.getDbeResidual()) ;
//		tbPayOutInfo.setDbePayoutNumber(tbBill.getDbeAllFee()) ;
//		tbPayOutInfo.setDbeAftResidual(tbPayOutInfo.getDbeCurResidual()-tbPayOutInfo.getDbePayoutNumber()) ;
//		/*将费用从帐户扣除*/
//		tbAccount.setDbeResidual(tbAccount.getDbeResidual()-tbBill.getDbeAllFee()) ;
//		tbAccountDao.update(tbAccount) ;
//		
//		/*添加帐户付费记录*/
//		tbPayOutInfoDao.create(tbPayOutInfo) ;
//	}
	
	/**
	 * 月结帐户保单费用记录
	 * @param tbBill
	 * @param tbAccount
	 */
	private void payBillFeeByMonthAccount(TbBill tbBill, TbAccount tbAccount){
		TbMonthPayInfo tbMonthPayInfo = new TbMonthPayInfo() ;
		tbMonthPayInfo.setIntAccountId(tbAccount.getId()) ;
		/*设置年、月*/
		setYearAndMonth(tbMonthPayInfo) ;
		tbMonthPayInfo.setDbeNeedToPay(tbBill.getDbeAllFee()) ;
		/*查询该年和该月是否有记录*/
		List<TbMonthPayInfo> list = tbMonthPayInfoDao.getMonthPayByYearAndMonth(tbMonthPayInfo.getStrYear(), tbMonthPayInfo.getStrMonth(),tbAccount.getId()) ;
		Integer monthPayId = null ;
		if(list==null || list.size()==0){
			/*没有记录则添加记录,并去出这个id，然后添加月费的清单记录*/
			tbMonthPayInfo.setIntIsPay(0) ;
			tbMonthPayInfo.setDbeNeedToPay(tbMonthPayInfo.getDbeNeedToPay()) ;
			monthPayId = tbMonthPayInfoDao.addMonthPayInfoReturnId(tbMonthPayInfo);
		}else{
		
//			TbMonthPayInfo oldMonthPayInfo = null ;
//			for(TbMonthPayInfo mp:list){
//				if(mp.getIntIsPay() == 0){
//					/*一定要是没有支付过的记录，否则会有问题*/
//					oldMonthPayInfo = mp ;
//				}
//			}
//			if(oldMonthPayInfo != null){
//				/*将费用添加上去*/
//				oldMonthPayInfo.setDbeNeedToPay(oldMonthPayInfo.getDbeNeedToPay() + tbMonthPayInfo.getDbeNeedToPay()) ;
//				tbMonthPayInfoDao.update(oldMonthPayInfo) ;
//			}else{
//				/*如果之前的重复的本月费的帐已结算了，则重新添加记录*/
//				tbMonthPayInfoDao.create(tbMonthPayInfo) ;
//			}
			
			/*有记录则更新记录(这里不看是否已交费，规定每个月21号之后才能交上个月的费用，所以这里不检查是否已交费)*/
			TbMonthPayInfo oldLog = list.get(0) ;
			/*费用*/
			oldLog.setDbeNeedToPay(oldLog.getDbeNeedToPay() + tbMonthPayInfo.getDbeNeedToPay()) ;
			tbMonthPayInfoDao.update(oldLog) ;
			monthPayId = oldLog.getId() ;
		}
		/*添加清单记录*/
		addMonthPayBillList(tbBill.getId() , monthPayId) ;

	}
	
	
	
	/**
	 * 保单确认收费，月费结算时，需要根绝年月进行结算，这里就是根绝日期来设置年月
	 * (记录上个月的21号到本月的20号，如果本月属于21号之前，则计入这个月，否则计入下个月)
	 * 为了好对照统计报表，保险公司要求改为每月1号之该月最后一天作为一个月的月费记录
	 * 这里就更改了日期
	 * 另外，由于修改保单时保单费用会更改，但是并没有更改月费，所以本系统设定每天晚上3点会自动整理月费应交费
	 * update @yqg 20090725
	 * @param tbMonthPayInfo
	 */
	private void setYearAndMonth(TbMonthPayInfo tbMonthPayInfo){
		/*日期*/
		Calendar calendar = new GregorianCalendar() ;
		/*计费用上一个月的21号到这个月的20号,如果是21号以前，则计入这个月，否则计入下个月*/
//		if(calendar.get(Calendar.DAY_OF_MONTH)>20){
//			/*表示这个月的21-月底之间，费用将计入下个月*/
//			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+1) ;
//		}
		tbMonthPayInfo.setStrYear(String.valueOf(calendar.get(Calendar.YEAR))) ;
		tbMonthPayInfo.setStrMonth(String.valueOf(calendar.get(Calendar.MONTH)+1)) ;
		//设置这个月的开始时间
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1, 0, 0, 0) ;
		tbMonthPayInfo.setDteStartTime(calendar.getTime()) ;
		//设置这个月的结束时间（下个月的开始时间）
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, 1, 0, 0, 0) ;
		tbMonthPayInfo.setDteEndTime(calendar.getTime()) ;
		
	}
	
	
	/**
	 * 添加月费结算的保单清单记录
	 *
	 */
	private void addMonthPayBillList(Integer billId , Integer monthPayId){
		TbListForMonthPay tbListForMonthPay = new TbListForMonthPay() ;
		tbListForMonthPay.setIntBillId(billId) ;
		tbListForMonthPay.setIntMonthPayId(monthPayId) ;
		/*默认为当月支付*/
		tbListForMonthPay.setIntPayMonthFeeId(monthPayId) ;
		/*默认状态为未交费*/
		tbListForMonthPay.setIntBillState(CommonWords.billMonthState1) ;
		tbListForMonthPayDao.create(tbListForMonthPay) ;
	}
	
	/**
	 * 添加确认记录
	 * @param sureType
	 * @param tbBill
	 * @param oldBill
	 * @return
	 */
	private int addSureLog(int sureType  , TbBill tbBill , TbBill oldBill , String reason , String desc){
		tbBill.getTbBillSureInfo().setIntBillId(tbBill.getId()) ;
		tbBill.getTbBillSureInfo().setIntCurBillState(tbBill.getIntBillStateId()) ;
		tbBill.getTbBillSureInfo().setIntAftBillState(oldBill.getIntBillStateId()) ;
		tbBill.getTbBillSureInfo().setIntSureType(CommonWords.sureType2) ;
		tbBill.getTbBillSureInfo().setStrDesc(desc) ;
		tbBillSureInfoDao.addTbBillSureInfoDao(tbBill.getTbBillSureInfo()) ;
		return 1 ;
	}
	
	
	
	/*
	 * 根据保单ID查询保单
	 * (non-Javadoc)
	 * @see cn.insurance.service.IBillQueryServ#getTbBillById(java.lang.Integer)
	 */
	public TbBill getTbBillById(Integer id) {
		// TODO Auto-generated method stub
		return (TbBill)tbBillDao.getObjectInfoById(id);
	}
	
	/*
	 * 根据保单号查询保单
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITbBillServ#getBillByNumber(java.lang.String)
	 */
	public TbBill getBillByNumber(String billNumber){
		return (TbBill)tbBillDao.getBillByNumber(billNumber) ;
	}
	
	/*
	 * 查询需要保险公司审核的新保单
	 * (non-Javadoc)
	 * @see cn.insurance.service.IBillQueryServ#getBillsNeedBxSure(cn.insurance.model.PageBean)
	 */
	public PageBean getBillNeedBxSure(PageBean pageBean){
		return tbBillDao.getBillNeedBxSure(pageBean) ;
		
	}
	
	/*
	 * 查询已备案的保单
	 * (non-Javadoc)
	 * @see cn.insurance.service.IBillQueryServ#getBillForBxBeiAn(cn.insurance.model.PageBean)
	 */
	public PageBean getBillForBxBeiAn(PageBean pageBean){
		return tbBillDao.getBillForBxBeiAn(pageBean) ;
	}
	
	
	/*
	 * 查询保险公司已确认生效的保单
	 * (non-Javadoc)
	 * @see cn.insurance.service.IBillQueryServ#getBillForBxHasSure(cn.insurance.model.PageBean)
	 */
	public PageBean getBillForBxHasSure(PageBean pageBean){
		return tbBillDao.getBillForBxHasSure(pageBean) ;
	}
	
	/*
	 * 查询被保险公司退回的保单
	 * (non-Javadoc)
	 * @see cn.insurance.service.IBillQueryServ#getBillForBxHasReturn(cn.insurance.model.PageBean)
	 */
	public PageBean getBillForBxHasReturn(PageBean pageBean){
		return tbBillDao.getBillForBxHasReturn(pageBean) ;
	}

	
	
	/*
	 * 保险公司根据各种条件查询保单
	 * (non-Javadoc)
	 * @see cn.insurance.service.IBillQueryServ#getBillByCondition(cn.insurance.model.PageBean, cn.insurance.model.TbBill, cn.insurance.model.TbPartment, cn.insurance.model.TbReturnBill)
	 */
	public PageBean getBillByComditionForBx(PageBean pageBean , TbBill tbBill ,Integer companyId, Integer partmentId,int[] state,String startTime ,String endTime){
		return tbBillDao.getBillByComditionForBx(pageBean, tbBill, companyId, partmentId, state, startTime, endTime) ;
	}
	
	/** 修改于2010-6-8*/
	public List getBillIdByTravelerInfo(String strName,String strNum){
		return this.tbTravelerInfoDao.getBillIdByTravelerInfo(strName, strNum);
	}
	
	public PageBean getBillByComditionForBxNew(PageBean pageBean ,String strIndentyNumber,String strTravelerName,TbBill tbBill ,Integer companyId, Integer partmentId,int[] state,String startTime ,String endTime){
		return this.tbBillDao.getBillByComditionForBx_2010(pageBean ,strIndentyNumber,strTravelerName, tbBill, companyId, partmentId, state, startTime, endTime);
	}
	
	
	
	public ITbBillDao getTbBillDao() {
		return tbBillDao;
	}

	public void setTbBillDao(ITbBillDao tbBillDao) {
		this.tbBillDao = tbBillDao;
	}

	public ITbPartmentDao getTbPartmentDao() {
		return tbPartmentDao;
	}

	public void setTbPartmentDao(ITbPartmentDao tbPartmentDao) {
		this.tbPartmentDao = tbPartmentDao;
	}

	public ITbBillSureInfoDao getTbBillSureInfoDao() {
		return tbBillSureInfoDao;
	}

	public void setTbBillSureInfoDao(ITbBillSureInfoDao tbBillSureInfoDao) {
		this.tbBillSureInfoDao = tbBillSureInfoDao;
	}

	public ITbMessageDao getTbMessageDao() {
		return tbMessageDao;
	}

	public void setTbMessageDao(ITbMessageDao tbMessageDao) {
		this.tbMessageDao = tbMessageDao;
	}

	public ITbTravelerInfoDao getTbTravelerInfoDao() {
		return tbTravelerInfoDao;
	}

	public void setTbTravelerInfoDao(ITbTravelerInfoDao tbTravelerInfoDao) {
		this.tbTravelerInfoDao = tbTravelerInfoDao;
	}

	public IPriceServ getPriceServ() {
		return priceServ;
	}

	public void setPriceServ(IPriceServ priceServ) {
		this.priceServ = priceServ;
	}

	public ITbPayoutInfoDao getTbPayOutInfoDao() {
		return tbPayOutInfoDao;
	}

	public void setTbPayOutInfoDao(ITbPayoutInfoDao tbPayOutInfoDao) {
		this.tbPayOutInfoDao = tbPayOutInfoDao;
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

	public ITbListForMonthPayDao getTbListForMonthPayDao() {
		return tbListForMonthPayDao;
	}

	public void setTbListForMonthPayDao(ITbListForMonthPayDao tbListForMonthPayDao) {
		this.tbListForMonthPayDao = tbListForMonthPayDao;
	}
}
