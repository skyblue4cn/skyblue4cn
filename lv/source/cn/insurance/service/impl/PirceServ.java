package cn.insurance.service.impl;


import java.util.List;

import cn.insurance.dao.ITbAccidentalFeeDao;
import cn.insurance.dao.ITbAccidentalFee_CarDao;
import cn.insurance.dao.ITbAccidentalFee_CarDaoS;
import cn.insurance.dao.ITbAdjustFeeDao;
import cn.insurance.dao.ITbPartmentDao;
import cn.insurance.model.TbAccidentalFee;
import cn.insurance.model.TbAdjustFee;
import cn.insurance.model.TbBill;
import cn.insurance.model.TbPartment;
import cn.insurance.service.IPriceServ;
import cn.insurance.utils.DateUtil;

/**
 * 将所有的关于费用的全部集合写在这里
 * @author zhuyan
 *
 */
public class PirceServ implements IPriceServ {
	
	private ITbAccidentalFeeDao tbAccidentalFeeDao ;
	
	private ITbAdjustFeeDao tbAdjustFeeDao ;
	
	private ITbPartmentDao tbPartmentDao ;

	private ITbAccidentalFee_CarDao tbAccidentalFee_CarDao;
	/*
	 * 取得通用的意外先价格
	 * (non-Javadoc)
	 * @see cn.insurance.service.IPriceServ#getNormalAccidentalFee()
	 */
	public TbAccidentalFee getNormalAccidentalFee() {
		/*默认设定意外险通用价格的ID为1*/
		return tbAccidentalFeeDao.getObjectInfoById(1);
	}
	
	/*
	 * 查询通用的调节费
	 * (non-Javadoc)
	 * @see cn.insurance.service.IPriceServ#getNormalAdjustFee()
	 */
	public TbAdjustFee getNormalAdjustFee(){
		return (TbAdjustFee)tbAdjustFeeDao.getObjectInfoById(1) ;
	}
	
	
	/**
	 * 按旅行社ID查询该旅行社的意外险价格
	 * (non-Javadoc)
	 * @see cn.insurance.service.IPriceServ#getCompanyAccdFeeById(java.lang.Integer)
	 */
	public TbAccidentalFee getCompanyAccdFeeById(Integer companyId) {

		/*因为在添加旅行社的时候就给旅行社添加了通用费用*/
		return tbAccidentalFeeDao.getAccidentFeeByPartmentId(companyId) ;
	}
	
	
	/**
	 * 按旅行社ID查询该旅行社的调节费价格
	 * (non-Javadoc)
	 * @see cn.insurance.service.IPriceServ#getCompanyAdjustFee(java.lang.Integer)
	 */
	public TbAdjustFee getCompanyAdjustFee(Integer companyId){
		return tbAdjustFeeDao.getAdjustFeeByPartmentId(companyId);
	}
	
	
	/**
	 * 更新通用的意外险价格费率
	 * 所有都更新
	 * (non-Javadoc)
	 * @see cn.insurance.service.IPriceServ#updateNormalAccidentalFee(cn.insurance.model.TbAccidentalFee)
	 */
	public int updateNormalAccidentalFee(TbAccidentalFee tbAccidentalFee){
		tbAccidentalFeeDao.updateNormalAccidentalFee(tbAccidentalFee) ;
		return 1 ;
	}
	
	/*
	 * 更新通用的调节费价格
	 * 所有都更新
	 * (non-Javadoc)
	 * @see cn.insurance.service.IPriceServ#updateNormalAdjustFee(cn.insurance.model.TbAdjustFee)
	 */
	public int updateNormalAdjustFee(TbAdjustFee tbAdjustFee){
		tbAdjustFeeDao.updateNormalAdjustFee(tbAdjustFee);
		return 1 ;
	}
	
	
	/*
	 * 更新旅行社的意外险价格
	 * (non-Javadoc)
	 * @see cn.insurance.service.IPriceServ#updateLxsAccidentalFee(cn.insurance.model.TbAccidentalFee)
	 */
	public int updateLxsAccidentalFee(TbAccidentalFee tbAccidentalFee){
		tbAccidentalFeeDao.updateNormalAccidentalFee(tbAccidentalFee) ;
		return 1 ;
	}
	
	/*
	 * 更新旅行社责任险价格
	 * (non-Javadoc)
	 * @see cn.insurance.service.IPriceServ#updateLxsAdjustFee(cn.insurance.model.TbAdjustFee)
	 */
	public int updateLxsAdjustFee(TbAdjustFee tbAdjustFee){
		tbAdjustFeeDao.update(tbAdjustFee) ;
		return 1 ;
	}
	
	
	/*
	 * 计算出一个保单的价格
	 * (non-Javadoc)
	 * @see cn.insurance.service.IPriceServ#getBillPrice(cn.insurance.model.TbBill)
	 */
	public TbBill getBillPrice(TbBill tbBill){
		
		/*先找出旅行社的id，以便用来查询该旅行社的价格*/
		Integer companyId = ((TbPartment)tbPartmentDao.getObjectInfoById(tbBill.getIntPartmentId())).getCompany().getId() ;
		if(tbBill.getIntKindId() == 1){
			/*算出责任险调节费,调节费不限天数*/
			setAdjustPrice(tbBill, companyId) ;
		}else if(tbBill.getIntKindId() == 2){
			/*算出意外险保费,不包括调节费*/
			setAccidentalPrice(tbBill, companyId) ;
		}
		return tbBill ;
	}
	
	
	/**
	 * 计算责任险责任险价格
	 * @param tbBill
	 */
	private TbBill setAdjustPrice(TbBill tbBill ,Integer companyId){
		/*先找出这个保单所属旅行社的价格设定*/
		TbAdjustFee tbAdjustFee = getCompanyAdjustFee(companyId);
		tbBill.setDbeChinaFee(tbAdjustFee.getDbeChinaStandard()) ;
		tbBill.setDbeOtherFee(tbAdjustFee.getDbeOtherStandard()) ;
		/*总价=（内宾*内宾人数 + 外宾*外宾人数） */
		double dbeAllFee =tbBill.getIntChinaTravelerNumber()*tbBill.getDbeChinaFee() + tbBill.getIntOtherTravelerNumber()*tbBill.getDbeOtherFee();
		
		tbBill.setDbeAllFee(dbeAllFee) ;
		return tbBill ;
	}
	
	/**
	 * 计算意外险价格
	 * @param tbBill
	 */
	private TbBill setAccidentalPrice(TbBill tbBill ,Integer companyId){
		/*查询意外先价格设定*/
		TbAccidentalFee tbAccidentalFee = tbAccidentalFeeDao.getAccidentFeeByPartmentId(companyId) ;
		
		/*算出有几天*/
		Integer date_number = DateUtil.getNumberFormDate(tbBill.getDteStartTime(), tbBill.getDteEndTime());
		/*判断是境内游还是境外游*/
			if(tbBill.getIntTravelProperty() == 0){
				/*境内*/
				/*根据天数设定每个人需要多少钱*/
				setPricePerManInChina(tbBill, date_number,tbAccidentalFee) ;
			}else{
				/*根据天数设定每个人需要多少钱*/
				setPricePerManInOther(tbBill, tbAccidentalFee, date_number) ;
			}
			/*特殊旅游项目价格设定*/
			setSpecialFee(tbBill,tbAccidentalFee , date_number , companyId);
		/*优惠*/
		tbBill.setDbeAllFee(tbBill.getDbeAllFee()) ;
		return tbBill ;
	}
	
	/**
	 * 根据天数设定意外险国内旅游每个人需要多少钱
	 * @param tbBill
	 * @param date_number
	 * @param tbAccidentalFee
	 * @return
	 */
	private TbBill setPricePerManInChina(TbBill tbBill , Integer date_number , TbAccidentalFee tbAccidentalFee){
		if(date_number == 1){
			/*1天*/
			tbBill.setDbeChinaFee(tbAccidentalFee.getDbeOneDayFee()) ;
		}
		if(date_number == 2){
			/*2天*/
			tbBill.setDbeChinaFee(tbAccidentalFee.getDbeTwoDayFee()) ;
		}
		if(date_number == 3){
			/*3天*/
			tbBill.setDbeChinaFee(tbAccidentalFee.getDbeThreeDayFee()) ;
		}
		if(date_number == 4){
			/*4天*/
			tbBill.setDbeChinaFee(tbAccidentalFee.getDbeFourDayFee()) ;
		}
		if(date_number==5){
			tbBill.setDbeChinaFee(tbAccidentalFee.getDbeFiveDayFee());
		}
		if(date_number==6){
			tbBill.setDbeChinaFee(tbAccidentalFee.getDbeSixDayFee());
		}
		if(date_number==7){
			tbBill.setDbeChinaFee(tbAccidentalFee.getDbeSevenDayFee());
		}
		if(date_number==8){
			tbBill.setDbeChinaFee(tbAccidentalFee.getDbeEightDayFee());
		}
		if(date_number==9){
			tbBill.setDbeChinaFee(tbAccidentalFee.getDbeNineDayFee());
		}
		if(date_number==10){
			tbBill.setDbeChinaFee(tbAccidentalFee.getDbeTenDayFee());
		}
		if(date_number==11){
			tbBill.setDbeChinaFee(tbAccidentalFee.getDbeElevenDayFee());
		}
		if(date_number==12){
			tbBill.setDbeChinaFee(tbAccidentalFee.getDbeTwelveDayFee());
		}
		if(date_number >12){
			/*12天以上*/
			tbBill.setDbeChinaFee(tbAccidentalFee.getDbeTwelveDayFee() + (date_number-12)*tbAccidentalFee.getDbeAboveTwelveDayFee()) ;
		}
		//如果有外宾，按照出入境的收费来收费
		if(date_number <=30){
			tbBill.setDbeOtherFee(tbAccidentalFee.getDbeThirtyDayFee());
		}else{
			tbBill.setDbeOtherFee(tbAccidentalFee.getDbeAboveThirtyDayFee());
		}
		return tbBill ;
	}
	
	/**
	 * 根据天数设定意外险国外旅游每个人的价格
	 * @param tbBill
	 * @param tbAccidentalFee
	 * @param date_number
	 * @return
	 */
	private TbBill setPricePerManInOther(TbBill tbBill ,TbAccidentalFee tbAccidentalFee , Integer date_number){
		if(date_number <=30){
			/*30天以内*/
			tbBill.setDbeChinaFee(tbAccidentalFee.getDbeThirtyDayFee()) ;
			tbBill.setDbeOtherFee(tbAccidentalFee.getDbeThirtyDayFee()) ;
		}else{
			/*30天以上*/
			tbBill.setDbeChinaFee(tbAccidentalFee.getDbeAboveThirtyDayFee()) ;
			tbBill.setDbeOtherFee(tbAccidentalFee.getDbeAboveThirtyDayFee()) ;
		}
		return tbBill ;
	}
	
	/**
	 * 特别旅游项目收费
	 * @param tbBill
	 * @param tbAccidentalFee
	 * @return
	 */
	private TbBill setSpecialFee(TbBill tbBill , TbAccidentalFee tbAccidentalFee , int days, int companyId){
//		if(tbBill.getIntTravelType() == 1){
//			/*如果是自驾车旅游，则要加钱*/
//			
//			/**2010-10-10*/
//			if(companyId == 27){
//				if(days<=3){
//					tbBill.setDbeChinaFee(12.0);
//					tbBill.setDbeOtherFee(12.0);
//				}
//				else if(days<=7){
//					tbBill.setDbeChinaFee(17.0);
//					tbBill.setDbeOtherFee(17.0);
//				}
//				else if(days<=15){
//					tbBill.setDbeChinaFee(22.0);
//					tbBill.setDbeOtherFee(22.0);
//				}else{
//					tbBill.setDbeChinaFee(tbBill.getDbeChinaFee() + tbAccidentalFee.getDbeSelfDriveFee()*days) ;
//					tbBill.setDbeOtherFee(tbBill.getDbeOtherFee() + tbAccidentalFee.getDbeSelfDriveFee()*days) ;
//				}
//			}
//			else if(companyId == 250){
//				if(days<=3){
//					tbBill.setDbeChinaFee(10.0);
//					tbBill.setDbeOtherFee(10.0);
//				}
//				else if(days<=7){
//					tbBill.setDbeChinaFee(17.0);
//					tbBill.setDbeOtherFee(17.0);
//				}
//				else if(days<=15){
//					tbBill.setDbeChinaFee(22.0);
//					tbBill.setDbeOtherFee(22.0);
//				}else{
//					tbBill.setDbeChinaFee(tbBill.getDbeChinaFee() + tbAccidentalFee.getDbeSelfDriveFee()*days) ;
//					tbBill.setDbeOtherFee(tbBill.getDbeOtherFee() + tbAccidentalFee.getDbeSelfDriveFee()*days) ;
//				}
//			}
//			else{
//				if(days<=3){
//					tbBill.setDbeChinaFee(10.0);
//					tbBill.setDbeOtherFee(10.0);
//				}
//				else if(days<=7){
//					tbBill.setDbeChinaFee(15.0);
//					tbBill.setDbeOtherFee(15.0);
//				}
//				else if(days<=15){
//					tbBill.setDbeChinaFee(20.0);
//					tbBill.setDbeOtherFee(20.0);
//				}else{
//					tbBill.setDbeChinaFee(tbBill.getDbeChinaFee() + tbAccidentalFee.getDbeSelfDriveFee()*days) ;
//					tbBill.setDbeOtherFee(tbBill.getDbeOtherFee() + tbAccidentalFee.getDbeSelfDriveFee()*days) ;
//				}
//			}
//		}
		//double allFee = (tbBill.getDbeChinaFee()*tbBill.getIntChinaTravelerNumber() + tbBill.getDbeOtherFee()*tbBill.getIntOtherTravelerNumber()) ;
		//tbBill.setDbeAllFee(allFee) ;
		if(tbBill.getIntTravelType() ==2){
			/*如果是自由人，则在国内和出境旅游的基础上加收百分比*/
			tbBill.setDbeAllFee(tbBill.getDbeAllFee()*(1+(double)tbAccidentalFee.getDbeFreeManFeeRate()/100)) ;
		}
		if(tbBill.getIntIsHasHighDanger() ==1){
			/*如果有高风险，则在国内和出境旅游的基础上加收百分比*/
			tbBill.setDbeAllFee(tbBill.getDbeAllFee()*(1+(double)tbAccidentalFee.getDbeSpecialItemFeeRate()/100)) ;
		}
		return tbBill ;
	}
	
	public ITbPartmentDao getTbPartmentDao() {
		return tbPartmentDao;
	}

	public void setTbPartmentDao(ITbPartmentDao tbPartmentDao) {
		this.tbPartmentDao = tbPartmentDao;
	}

	public ITbAccidentalFeeDao getTbAccidentalFeeDao() {
		return tbAccidentalFeeDao;
	}

	public void setTbAccidentalFeeDao(ITbAccidentalFeeDao tbAccidentalFeeDao) {
		this.tbAccidentalFeeDao = tbAccidentalFeeDao;
	}

	public ITbAdjustFeeDao getTbAdjustFeeDao() {
		return tbAdjustFeeDao;
	}

	public void setTbAdjustFeeDao(ITbAdjustFeeDao tbAdjustFeeDao) {
		this.tbAdjustFeeDao = tbAdjustFeeDao;
	}

	public ITbAccidentalFee_CarDao getTbAccidentalFee_CarDao() {
		return tbAccidentalFee_CarDao;
	}

	public void setTbAccidentalFee_CarDao(
			ITbAccidentalFee_CarDao tbAccidentalFeeCarDao) {
		tbAccidentalFee_CarDao = tbAccidentalFeeCarDao;
	}
}
