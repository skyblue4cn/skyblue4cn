package cn.insurance.service.impl;



import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import cn.insurance.dao.ITbBillDao;
import cn.insurance.dao.ITbPartmentDao;
import cn.insurance.model.TbBill;
import cn.insurance.model.TbPartment;
import cn.insurance.model.tongji.PartmentDayStat;
import cn.insurance.model.tongji.PartmentMonthStat;
import cn.insurance.model.tongji.PartmentTimeStat;
import cn.insurance.model.tongji.PartmentYearStat;
import cn.insurance.service.ITongJiServ;
import cn.insurance.utils.DateUtil;


public class TongjiServ implements ITongJiServ {
	
	private ITbBillDao tbBillDao ;
	
	private ITbPartmentDao tbPartmentDao ;
	
	
	/*
	 * 按年查询部门的统计
	 * 从本年1月1日到当前时间的统计
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITongJiServ#getDefaultPartmentMonthStatList(java.lang.Integer)
	 */
	public PartmentYearStat getPartmentYearStat(Integer partmentId , int year){
		PartmentYearStat py = new PartmentYearStat() ;
		py.setYear(year) ;
		py.setTbPartment(tbPartmentDao.getObjectInfoById(partmentId)) ;
		/*对比当前的时间*/
		Calendar calendar = new GregorianCalendar() ;
		if(year == calendar.get(Calendar.YEAR)){
			/*如果要查询的是本年，那么只需要查询到当月即可*/
			for(int i =1 ;i<=(calendar.get(Calendar.MONTH)+1) ; i++){
				PartmentMonthStat pm = new PartmentMonthStat() ;
				pm.setYear(year) ;
				pm.setMonth(i);
				pm.setTbPartment(py.getTbPartment()) ;
				pm = getPartmentMonthStatByYearAndMonth(pm) ;
				py.getPmList().add(pm) ;
			}
		}
		if(year < calendar.get(Calendar.YEAR)){
			/*如果是以前的年费，则1-12月都要查询*/
			for(int i =1 ;i<=12; i++){
				PartmentMonthStat pm = new PartmentMonthStat() ;
				pm.setYear(year) ;
				pm.setMonth(i);
				pm.setTbPartment(py.getTbPartment()) ;
				pm = getPartmentMonthStatByYearAndMonth(pm) ;
				py.getPmList().add(pm) ;
			}
		}
		/*这里将所有的数字综合一下，算出总量*/
		if(py.getPmList() != null && py.getPmList().size() !=0){
			for(PartmentMonthStat pmm : py.getPmList()){
				py.setAllBillCount(py.getAllBillCount() + pmm.getAllBillCount()) ;
				py.setHasPayBillCount(py.getHasPayBillCount() + pmm.getHasPayBillCount()) ;
				py.setNotPayBillCount(py.getNotPayBillCount() + pmm.getNotPayBillCount()) ;
				py.setPeiKuaiBillCount(py.getPeiKuaiBillCount() + pmm.getPeiKuaiBillCount()) ;
				py.setPeiKuanNumber(py.getPeiKuanNumber() + pmm.getPeiKuanNumber()) ;
				py.getHasSureBillList().addAll(pmm.getHasSureBillList()) ;
				py.getReturnBillList().addAll(pmm.getReturnBillList()) ;
				py.getHasPayBillList().addAll(pmm.getHasPayBillList()) ;
				py.getNotPayBillList().addAll(pmm.getNotPayBillList()) ;
				/*算出已付费和未付费的金额*/
				py.setHasPayBillFee(py.getHasPayBillFee() + pmm.getHasPayBillFee()) ;
				py.setNotPayBillFee(py.getNotPayBillFee() + pmm.getNotPayBillFee()) ;
				py.getHasPeiKuanBillList().addAll(pmm.getHasPeiKuanBillList()) ;
			}
		}
		return py ;
	}
	
	
	
	
	/*
	 * 按部门和年月查询该部门的统计(按月统计，但不包含每天的统计)
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITongJiServ#getPartmentMonthStat()
	 */
	public PartmentMonthStat getPartmentMonthStatByYearAndMonth(PartmentMonthStat pm) {
		pm.setTbPartment(tbPartmentDao.getObjectInfoById(pm.getTbPartment().getId())) ;
		pm = tbBillDao.getPartmentMonthStatByYearAndMonth(pm) ;
		/*对数据进行统计*/
		pm.setAllBillCount(pm.getHasSureBillList().size() + pm.getReturnBillList().size()) ;
		pm.setHasPayBillCount(pm.getHasPayBillList().size()) ;
		pm.setNotPayBillCount(pm.getNotPayBillList().size()) ;
		/*算出已付费和未付费的金额*/
		pm.setHasPayBillFee(0.0) ;
		pm.setNotPayBillFee(0.0) ;
		if(pm.getHasPayBillList()!= null && pm.getHasPayBillList().size()>0){
			for(TbBill bill : pm.getHasPayBillList()){
				pm.setHasPayBillFee(pm.getHasPayBillFee() + bill.getDbeAllFee()) ;
			}
		}
		if(pm.getNotPayBillList()!= null && pm.getNotPayBillList().size()>0){
			for(TbBill bill : pm.getNotPayBillList()){
				pm.setNotPayBillFee(pm.getNotPayBillFee() + bill.getDbeAllFee()) ;
			}
		}
		pm.setPeiKuaiBillCount(pm.getHasPeiKuanBillList().size()) ;
		if(pm.getHasPeiKuanBillList() != null){
			for(TbBill bill : pm.getHasPeiKuanBillList()){
				pm.setPeiKuanNumber(pm.getPeiKuanNumber() + bill.getTbPeiKuan().getDbePeiKuanFee()) ;
			}
		}
		return pm;
	}

	/*
	 * 根据月份统计保单（包含每天的统计）
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITongJiServ#getPartmentMonthStatByDay(cn.insurance.model.tongji.PartmentMonthStat)
	 */
	public PartmentMonthStat getPartmentMonthStatByDay(PartmentMonthStat pm){
		TbPartment tbPartment = tbPartmentDao.getObjectInfoById(pm.getTbPartment().getId()) ;
		pm = getPartmentMonthStatByYearAndMonth(pm) ;
		Calendar calendar = new GregorianCalendar() ;
		calendar.set(Calendar.YEAR, pm.getYear()) ;
		calendar.set(Calendar.MONTH, pm.getMonth()-1) ;
		/*从第一天开始循环*/
		calendar.set(Calendar.DAY_OF_MONTH, 1) ;
		/*循环是根据月的变化来的，如果月份改变，则证明该月已完*/
		for(int i=calendar.get(calendar.MONTH) ;i==calendar.get(calendar.MONTH) ;){
			PartmentDayStat pds = new PartmentDayStat() ;
			pds.setYear(pm.getYear()) ;
			pds.setMonth(pm.getMonth()) ;
			pds.setDay(calendar.get(Calendar.DATE)) ;
			pds.setTbPartment(tbPartment) ;
			pm.getPdsList().add(getPartmentDayStatByDay(pds)) ;
			/*查询一天的数据后设定时间为后一天*/
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+1) ;
		}
		/*对数据进行统计*/
		pm.setAllBillCount(pm.getHasSureBillList().size() + pm.getReturnBillList().size()) ;
		pm.setHasPayBillCount(pm.getHasPayBillList().size()) ;
		pm.setNotPayBillCount(pm.getNotPayBillList().size()) ;
		pm.setHasPayBillFee(0.0) ;
		pm.setNotPayBillFee(0.0) ;
		if(pm.getHasPayBillList()!= null && pm.getHasPayBillList().size()>0){
			for(TbBill bill : pm.getHasPayBillList()){
				pm.setHasPayBillFee(pm.getHasPayBillFee() + bill.getDbeAllFee()) ;
			}
		}
		if(pm.getNotPayBillList()!= null && pm.getNotPayBillList().size()>0){
			for(TbBill bill : pm.getNotPayBillList()){
				pm.setNotPayBillFee(pm.getNotPayBillFee() + bill.getDbeAllFee()) ;
			}
		}
		pm.setPeiKuaiBillCount(pm.getHasPeiKuanBillList().size()) ;
		if(pm.getHasPeiKuanBillList() != null){
			for(TbBill bill : pm.getHasPeiKuanBillList()){
				pm.setPeiKuanNumber(pm.getPeiKuanNumber() + bill.getTbPeiKuan().getDbePeiKuanFee()) ;
			}
		}
		return pm ;
	}
	
	/*
	 * 按天统计当天的保单
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITongJiServ#getPartmentDayStatByDay(cn.insurance.model.tongji.PartmentDayStat)
	 */
	public PartmentDayStat getPartmentDayStatByDay(PartmentDayStat pds){
		/*查询改天天的所有保单统计*/
		pds = tbBillDao.getPartmentDayStatByDay(pds) ;
		/*对数据进行统计*/
		pds.setAllBillCount(pds.getHasSureBillList().size() + pds.getReturnBillList().size()) ;
		pds.setHasPayBillCount(pds.getHasPayBillList().size()) ;
		pds.setNotPayBillCount(pds.getNotPayBillList().size()) ;
		pds.setHasPayBillFee(0.0) ;
		pds.setNotPayBillFee(0.0) ;
		if(pds.getHasPayBillList()!= null && pds.getHasPayBillList().size()>0){
			for(TbBill bill : pds.getHasPayBillList()){
				pds.setHasPayBillFee(pds.getHasPayBillFee() + bill.getDbeAllFee()) ;
			}
		}
		if(pds.getNotPayBillList()!= null && pds.getNotPayBillList().size()>0){
			for(TbBill bill : pds.getNotPayBillList()){
				pds.setNotPayBillFee(pds.getNotPayBillFee() + bill.getDbeAllFee()) ;
			}
		}
		pds.setPeiKuaiBillCount(pds.getHasPeiKuanBillList().size()) ;
		if(pds.getHasPeiKuanBillList() != null){
			for(TbBill bill : pds.getHasPeiKuanBillList()){
				pds.setPeiKuanNumber(pds.getPeiKuanNumber() + bill.getTbPeiKuan().getDbePeiKuanFee()) ;
			}
		}
		return pds ;
	}
	
	/*
	 * 按时间段查询部门统计
	 * (non-Javadoc)
	 * @see cn.insurance.service.ITongJiServ#getPartmentMonthStatByTime(cn.insurance.model.tongji.PartmentTimeStat)
	 */
	public PartmentTimeStat getPartmentTimeStatByTime(PartmentTimeStat pts){
		/*查询时间段内的所有保单统计*/
		if(pts.getEndTime()==null || "".equals(pts.getEndTime())){
			/*如果没有限定结束时间，则结束时间定为当前时间*/
			pts.setEndTime(DateUtil.getFormatDate(new Date(), "yyyy-MM-dd")) ;
		}
		pts = tbBillDao.getPartmentTimeStatByTime(pts);
		/*对数据进行统计*/
		pts.setAllBillCount(pts.getHasSureBillList().size() + pts.getReturnBillList().size()) ;
		pts.setHasPayBillCount(pts.getHasPayBillList().size()) ;
		pts.setNotPayBillCount(pts.getNotPayBillList().size()) ;
		pts.setHasPayBillFee(0.0) ;
		pts.setNotPayBillFee(0.0) ;
		if(pts.getHasPayBillList()!= null && pts.getHasPayBillList().size()>0){
			for(TbBill bill : pts.getHasPayBillList()){
				pts.setHasPayBillFee(pts.getHasPayBillFee() + bill.getDbeAllFee()) ;
			}
		}
		if(pts.getNotPayBillList()!= null && pts.getNotPayBillList().size()>0){
			for(TbBill bill : pts.getNotPayBillList()){
				pts.setNotPayBillFee(pts.getNotPayBillFee() + bill.getDbeAllFee()) ;
			}
		}
		pts.setPeiKuaiBillCount(pts.getHasPeiKuanBillList().size()) ;
		if(pts.getHasPeiKuanBillList() != null){
			for(TbBill bill : pts.getHasPeiKuanBillList()){
				pts.setPeiKuanNumber(pts.getPeiKuanNumber() + bill.getTbPeiKuan().getDbePeiKuanFee()) ;
			}
		}
		return pts ;
		
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

}
