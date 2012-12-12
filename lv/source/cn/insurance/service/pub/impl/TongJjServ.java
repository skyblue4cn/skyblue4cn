package cn.insurance.service.pub.impl;

import java.util.ArrayList;
import java.util.List;

import cn.insurance.dao.impl.TbPartmentDao;
import cn.insurance.dao.tongji.IAllCompanyYearStatDao;
import cn.insurance.dao.tongji.ITongJIbyMonthDao;
import cn.insurance.dao.tongji.ITongJiByDayDao;
import cn.insurance.dao.tongji.ITongJiByTimeSpaceDao;
import cn.insurance.dao.tongji.ITongJiByYearDao;
import cn.insurance.model.TbPartment;
import cn.insurance.model.tongji2.AllCompanyYearStat;
import cn.insurance.model.tongji2.CompanyYearStat;
import cn.insurance.model.tongji2.PartmentTimeStat;
import cn.insurance.model.tongji2.PartmentDayStat;
import cn.insurance.model.tongji2.PartmentMonthStat;
import cn.insurance.model.tongji2.PartmentYearStat;
import cn.insurance.model.tongji2.abstractmodel.AbstractPartmentDayStat;
import cn.insurance.model.tongji2.abstractmodel.AbstractPartmentMonthStat;
import cn.insurance.service.pub.ITongJiServ;

public class TongJjServ implements ITongJiServ {
	
	
	private IAllCompanyYearStatDao allCompanyYearStatDao ;
	
	private ITongJiByYearDao tongJiByYearDao ;
	
	private ITongJIbyMonthDao tongJIbyMonthDao ;
	
	private ITongJiByDayDao tongJiByDayDao ;
	
	private ITongJiByTimeSpaceDao tongJiByTimeSpaceDao ;
	
	private TbPartmentDao tbPartmentDao ;
	

	/*
	 * 对所有旅行社及部门的所有保单按年进行统计
	 * (non-Javadoc)
	 * @see cn.insurance.service.pub.ITongJiServ#getAllCompanyYearStat()
	 */
	public List<AllCompanyYearStat> getAllCompanyYearStat(int year){
		return allCompanyYearStatDao.getAllCompanyYearStatList(year);
	}
	
	/**
	 * 根据旅行社ID和年统计这个旅行社的所有部门的数据
	 * @param companyId
	 * @return
	 */
	public List<PartmentYearStat> getPartmentYearStatByCompanyId(int companyId , int year){
		List<PartmentYearStat> list = new ArrayList<PartmentYearStat>();
		List<TbPartment> partmentList = tbPartmentDao.getAllPartmentListByCompanyId(companyId) ;
		/*先统计总社的*/
		list.add(tongJiByYearDao.getPartmentYearStatByYear(companyId, year)) ;
		/*再统计部门的*/
		if(partmentList != null && partmentList.size() > 0){
			for(TbPartment p : partmentList){
				list.add(tongJiByYearDao.getPartmentYearStatByYear(p.getId(), year)) ;
			}
		}
		return list ;
	}
	
	
	/*
	 * 根据按年统计
	 * (non-Javadoc)
	 * @see cn.insurance.service.pub.ITongJiServ#getPartmentYearStat(int)
	 */
	public List<PartmentYearStat> getPartmentYearStat(int partmentId){
		return tongJiByYearDao.getPartmentYearStat(partmentId) ;
	}
	
	/*
	 * 对部门按年进行统计(只统计指定的一年)
	 * (non-Javadoc)
	 * @see cn.insurance.service.pub.ITongJiServ#getPartmentYearStat(int, int)
	 */
	public PartmentYearStat getPartmentYearStat(int partmentId , int year){
		return tongJiByYearDao.getPartmentYearStatByYear(partmentId, year) ;
	}
	
	/*
	 * 按月统计
	 * (non-Javadoc)
	 * @see cn.insurance.service.pub.ITongJiServ#getPartmentMonthStat(int, int)
	 */
	public AbstractPartmentMonthStat getPartmentMonthStat(int partmentId , int year){
		AbstractPartmentMonthStat am = new AbstractPartmentMonthStat();
		am.setYear(year);
		am.setTbPartment(tbPartmentDao.getObjectInfoById(partmentId)) ;
		am.setPartmentMonthStatList(tongJIbyMonthDao.getPartmentMonthStatByYear(partmentId, year)) ;
		am.setPartmentYearStat(tongJiByYearDao.getPartmentYearStatByYear(partmentId, year)) ;
		return  am;
	}
	
	/*
	 * 按天统计
	 * (non-Javadoc)
	 * @see cn.insurance.service.pub.ITongJiServ#getPartmentDayStat(int, int, int)
	 */
	public AbstractPartmentDayStat getPartmentDayStat(int partmentId , int year , int month){
		AbstractPartmentDayStat ad = new AbstractPartmentDayStat();
		ad.setYear(year);
		ad.setMonth(month);
		ad.setPartmentDayStatList(tongJiByDayDao.getPartmentDayStatList(partmentId, year, month)) ;
		ad.setPartmentMonthStat(tongJIbyMonthDao.getPartmentMonthStatByYearAndMonth(partmentId, year, month)) ;
		return  ad;
	}
	
	/*
	 * 时间段统计
	 * (non-Javadoc)
	 * @see cn.insurance.service.pub.ITongJiServ#getPartmentTimeStat(int, java.lang.String, java.lang.String)
	 */
	public PartmentTimeStat getPartmentTimeStat(int partmentId,String startTime, String endTime) {
		return tongJiByTimeSpaceDao.getPartmentTimeStatList(partmentId, startTime, endTime);
	}
	
	
	
	

	public ITongJiByYearDao getTongJiByYearDao() {
		return tongJiByYearDao;
	}

	public void setTongJiByYearDao(ITongJiByYearDao tongJiByYearDao) {
		this.tongJiByYearDao = tongJiByYearDao;
	}







	public ITongJIbyMonthDao getTongJIbyMonthDao() {
		return tongJIbyMonthDao;
	}







	public void setTongJIbyMonthDao(ITongJIbyMonthDao tongJIbyMonthDao) {
		this.tongJIbyMonthDao = tongJIbyMonthDao;
	}

	public ITongJiByDayDao getTongJiByDayDao() {
		return tongJiByDayDao;
	}

	public void setTongJiByDayDao(ITongJiByDayDao tongJiByDayDao) {
		this.tongJiByDayDao = tongJiByDayDao;
	}

	public ITongJiByTimeSpaceDao getTongJiByTimeSpaceDao() {
		return tongJiByTimeSpaceDao;
	}

	public void setTongJiByTimeSpaceDao(ITongJiByTimeSpaceDao tongJiByTimeSpaceDao) {
		this.tongJiByTimeSpaceDao = tongJiByTimeSpaceDao;
	}

	public TbPartmentDao getTbPartmentDao() {
		return tbPartmentDao;
	}

	public void setTbPartmentDao(TbPartmentDao tbPartmentDao) {
		this.tbPartmentDao = tbPartmentDao;
	}


	public IAllCompanyYearStatDao getAllCompanyYearStatDao() {
		return allCompanyYearStatDao;
	}

	public void setAllCompanyYearStatDao(
			IAllCompanyYearStatDao allCompanyYearStatDao) {
		this.allCompanyYearStatDao = allCompanyYearStatDao;
	}
	
	
	
	
}
