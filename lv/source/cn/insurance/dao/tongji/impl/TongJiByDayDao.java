package cn.insurance.dao.tongji.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import cn.insurance.commonwords.BillKey;
import cn.insurance.dao.ITbPartmentDao;
import cn.insurance.dao.conn.ConnectionManager;
import cn.insurance.dao.tongji.ITongJiByDayDao;
import cn.insurance.model.TbPartment;
import cn.insurance.model.tongji2.PartmentDayStat;
import cn.insurance.utils.PrintLog;

public class TongJiByDayDao implements ITongJiByDayDao {
	
	private ITbPartmentDao tbPartmentDao ;
	
	private Connection conn ;
	
	private PreparedStatement st ;
	
	private ResultSet rs ;
	
	/*
	 * 按天统计
	 * (non-Javadoc)
	 * @see cn.insurance.dao.tongji.ITongJiByDay#getPartmentDayStatList(int, int, int)
	 */
	public List<PartmentDayStat> getPartmentDayStatList(int partmentId,int year, int month) {
		TbPartment tbPartment = tbPartmentDao.getObjectInfoById(partmentId) ; 
		List<PartmentDayStat> list = new ArrayList<PartmentDayStat>();
		Calendar calendar1 = new GregorianCalendar();
		calendar1.set(Calendar.YEAR, year) ;
		calendar1.set(Calendar.MONTH, (month-1)) ; //月在日历中是从0开始
		Calendar calendar2 = new GregorianCalendar();
		//这里将日期没遍历一次就设定加1  如果日历的月份改变，则说明这个月已经全部查询完成
		for(int day = 1; month == calendar1.get(Calendar.MONTH)+1; calendar1.set(Calendar.DATE, ++day)){
			if(year == calendar2.get(Calendar.YEAR) && month == (calendar2.get(Calendar.MONTH)+1) && day>calendar2.get(Calendar.DATE)){
				//如果统计时间超过了当前时间，则停止统计
				break ;
			}
			PartmentDayStat pds = new PartmentDayStat();
			pds.setTbPartment(tbPartment) ;
			pds.setYear(year) ;
			pds.setMonth(month) ;
			pds.setDay(day) ;
			//查询所有的核得通过的保单数目
			pds.setHasSureBillCount(getTheSureBillCountByYear(partmentId , year , month,day)) ;
			//查询所有的被保险公司退回的保单数目
			pds.setReturnBillCount(getTheReturnBillCountByYear(partmentId , year,month,day)) ;
			//总数应该是通过和被退回的和
			pds.setAllBillCount(pds.getHasSureBillCount() + pds.getReturnBillCount()) ;
			//查询已付费的保单费用及金额
			getTheHasPayBillCountAndFee(pds) ;
			//查询未付费的保单费用及金额
			getTheNotPayBillCountAndFee(pds) ;
			//查询赔款的保单数量及金额
			getThePeiKuanBillAccount(pds) ;
			list.add(pds) ;
		}
		return list;
	}
	

	/**
	 * 查询所有的核得通过的保单
	 * @param year
	 * @return
	 */
	private int getTheSureBillCountByYear(int partmentId , int year,int month , int day){
		String sql = "SELECT COUNT(*) FROM tbbill WHERE intPartmentID=? AND YEAR(dteApplyDate)=? AND MONTH(dteApplyDate)=? AND DAY(dteApplyDate)=? AND intBillStateId=? " ;
		try{
			conn = ConnectionManager.getConnection() ;
			st = conn.prepareStatement(sql) ;
			st.setInt(1, partmentId) ;
			st.setInt(2, year) ;
			st.setInt(3, month) ;
			st.setInt(4, day);
			st.setInt(5, BillKey.billState4) ; //已生效的保单
			rs = st.executeQuery() ;
			if(rs.next()){
				return rs.getInt(1) ;
			}
		}catch(Exception e){
			PrintLog.getLog().error(this, e) ;
		}finally{
			ConnectionManager.close(rs, st, conn) ;
		}
		return 0 ;
	}
	
	
	
	/**
	 * 查询所有被退回的保单
	 * @param year
	 * @return
	 */
	private int getTheReturnBillCountByYear(int partmentId , int year , int month , int day){
		String sql = "SELECT COUNT(*) FROM tbbill WHERE intPartmentID=? AND YEAR(dteApplyDate)=? AND MONTH(dteApplyDate)=? AND DAY(dteApplyDate)=? AND intBillStateId=?" ;
		try{
			conn = ConnectionManager.getConnection() ;
			st = conn.prepareStatement(sql) ;
			st.setInt(1, partmentId) ;
			st.setInt(2, year) ;
			st.setInt(3, month);
			st.setInt(4, day) ;
			st.setInt(5, BillKey.billState6) ; //被退回的保单
			rs = st.executeQuery() ;
			if(rs.next()){
				return rs.getInt(1) ;
			}
		}catch(Exception e){
			PrintLog.getLog().error(this, e) ;
		}finally{
			ConnectionManager.close(rs, st, conn) ;
		}
		return 0 ;
	}
	
	/**
	 * 查询已付费的保单数量及金额
	 * @param pys
	 * @return
	 */
	private void getTheHasPayBillCountAndFee(PartmentDayStat pds){
		String sql = "SELECT COUNT(*) , SUM(dbeAllFee) FROM tbbill WHERE intPartmentID=? AND YEAR(dteApplyDate)=? AND MONTH(dteApplyDate)=? AND DAY(dteApplyDate)=?  AND intBillStateId=? AND intIsPay=?" ;
		try{
			conn = ConnectionManager.getConnection() ;
			st = conn.prepareStatement(sql) ;
			st.setInt(1, pds.getTbPartment().getId()) ;
			st.setInt(2, pds.getYear()) ;
			st.setInt(3, pds.getMonth()) ;
			st.setInt(4, pds.getDay()) ;
			st.setInt(5, BillKey.billState4) ; //被确认的保单
			st.setInt(6, BillKey.HAS_PAY_BILL) ;
			rs = st.executeQuery() ;
			if(rs.next()){
				int hasPayBillCount = rs.getInt(1) ; //已付费的保单数量
				double hasPayBillFee = rs.getDouble(2) ; //已付费的金额
				pds.setHasPayBillCount(hasPayBillCount) ;
				pds.setHasPayBillFee(hasPayBillFee) ;
			}
		}catch(Exception e){
			PrintLog.getLog().error(this, e) ;
		}finally{
			ConnectionManager.close(rs, st, conn) ;
		}
	}
	
	/**
	 * 查询未付费的保单数量及金额
	 * @param pys
	 * @return
	 */
	private void getTheNotPayBillCountAndFee(PartmentDayStat pds){
		String sql = "SELECT COUNT(*) , SUM(dbeAllFee) FROM tbbill WHERE intPartmentID=? AND YEAR(dteApplyDate)=? AND MONTH(dteApplyDate)=? AND DAY(dteApplyDate)=?  AND intBillStateId=? AND intIsPay=?" ;
		try{
			conn = ConnectionManager.getConnection() ;
			st = conn.prepareStatement(sql) ;
			st.setInt(1, pds.getTbPartment().getId()) ;
			st.setInt(2, pds.getYear()) ;
			st.setInt(3, pds.getMonth());
			st.setInt(4, pds.getDay());
			st.setInt(5, BillKey.billState4) ; //被确认的保单
			st.setInt(6, BillKey.NOT_PAY_BILL) ; //未付费
			rs = st.executeQuery() ;
			if(rs.next()){
				int notPayBillCount = rs.getInt(1) ; //未付费的保单数量
				double notPayBillFee = rs.getDouble(2) ; //未付费的金额
				pds.setNotPayBillCount(notPayBillCount);
				pds.setNotPayBillFee(notPayBillFee) ;
			}
		}catch(Exception e){
			PrintLog.getLog().error(this, e) ;
		}finally{
			ConnectionManager.close(rs, st, conn) ;
		}
	}
	
	/**
	 * 查询赔款的保单数量及金额
	 * @param pys
	 */
	private void getThePeiKuanBillAccount(PartmentDayStat pds){
		String sql = "SELECT COUNT(*),SUM(dbePeiKuanFee) FROM tbPeiKuan WHERE intBillId IN(SELECT id FROM tbbill WHERE intPartmentID=? AND YEAR(dteApplyDate)=? AND MONTH(dteApplyDate)=? AND DAY(dteApplyDate)=? )" ;
		try{
			conn = ConnectionManager.getConnection() ;
			st = conn.prepareStatement(sql) ;
			st.setInt(1, pds.getTbPartment().getId()) ;
			st.setInt(2, pds.getYear()) ;
			st.setInt(3, pds.getMonth()) ;
			st.setInt(4, pds.getDay()) ;
			rs = st.executeQuery() ;
			if(rs.next()){
				int peiKuaiBillCount = rs.getInt(1) ; //赔款的保单数量
				double peiKuanNumber = rs.getDouble(2) ; //赔款的金额
				pds.setPeiKuaiBillCount(peiKuaiBillCount) ;
				pds.setPeiKuanNumber(peiKuanNumber) ;
			}
		}catch(Exception e){
			PrintLog.getLog().error(this, e) ;
		}finally{
			ConnectionManager.close(rs, st, conn) ;
		}
	
	
	}
	
	public ITbPartmentDao getTbPartmentDao() {
		return tbPartmentDao;
	}

	public void setTbPartmentDao(ITbPartmentDao tbPartmentDao) {
		this.tbPartmentDao = tbPartmentDao;
	}

}
