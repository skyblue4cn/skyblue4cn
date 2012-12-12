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
import cn.insurance.dao.tongji.ITongJIbyMonthDao;
import cn.insurance.model.TbPartment;
import cn.insurance.model.tongji2.PartmentMonthStat;
import cn.insurance.utils.PrintLog;

public class TongJIbyMonthDao implements ITongJIbyMonthDao {
	
	private ITbPartmentDao tbPartmentDao ;
	
	private Connection conn ;
	
	private PreparedStatement st ;
	
	private ResultSet rs ;
	
	/*
	 * 统计一年内所有月分的数据
	 * (non-Javadoc)
	 * @see cn.insurance.dao.tongji.ITongJIbyMonthDao#getPartmentMonthStatByYear(int)
	 */
	public List<PartmentMonthStat> getPartmentMonthStatByYear(int partmentId ,int year) {
//		TbPartment tbPartment = tbPartmentDao.getObjectInfoById(partmentId) ; 
		List<PartmentMonthStat> list = new ArrayList<PartmentMonthStat>();
		Calendar calendar = new GregorianCalendar();
		for(int month = 1; month <=12 ; month++){
			if(year == calendar.get(Calendar.YEAR) && month > calendar.get(Calendar.MONTH)+1){
				//如果统计时间超过了当前时间，则停止统计
				break ;
			}
			list.add(getPartmentMonthStatByYearAndMonth(partmentId, year, month)) ;
		}
		return list;
	}



	/*
	 * 查询该月的统计
	 * (non-Javadoc)
	 * @see cn.insurance.dao.tongji.ITongJIbyMonthDao#getPartmentMonthStatByYearAndMonth(int, int, int)
	 */
	public PartmentMonthStat getPartmentMonthStatByYearAndMonth(int partmentId,int year, int month) {
		TbPartment tbPartment = tbPartmentDao.getObjectInfoById(partmentId) ; 
		PartmentMonthStat pms = new PartmentMonthStat();
		pms.setTbPartment(tbPartment) ;
		pms.setYear(year) ;
		pms.setMonth(month) ;
		//查询所有的核得通过的保单数目
		pms.setHasSureBillCount(getTheSureBillCountByYear(partmentId , year , month)) ;
		//查询所有的被保险公司退回的保单数目
		pms.setReturnBillCount(getTheReturnBillCountByYear(partmentId , year,month)) ;
		//总数应该是通过和被退回的和
		pms.setAllBillCount(pms.getHasSureBillCount() + pms.getReturnBillCount()) ;
		//查询已付费的保单费用及金额
		getTheHasPayBillCountAndFee(pms) ;
		//查询未付费的保单费用及金额
		getTheNotPayBillCountAndFee(pms) ;
		//查询赔款的保单数量及金额
		getThePeiKuanBillAccount(pms) ;
		return pms;
	}
	

	/**
	 * 查询所有的核得通过的保单
	 * @param year
	 * @return
	 */
	private int getTheSureBillCountByYear(int partmentId , int year,int month){
		String sql = "SELECT COUNT(*) FROM tbbill WHERE intPartmentID=? AND YEAR(dteApplyDate)=? AND MONTH(dteApplyDate)=? AND intBillStateId=? " ;
		try{
			conn = ConnectionManager.getConnection() ;
			st = conn.prepareStatement(sql) ;
			st.setInt(1, partmentId) ;
			st.setInt(2, year) ;
			st.setInt(3, month) ;
			st.setInt(4, BillKey.billState4) ; //已生效的保单
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
	private int getTheReturnBillCountByYear(int partmentId , int year , int month){
		String sql = "SELECT COUNT(*) FROM tbbill WHERE intPartmentID=? AND YEAR(dteApplyDate)=? AND MONTH(dteApplyDate)=? AND intBillStateId=?" ;
		try{
			conn = ConnectionManager.getConnection() ;
			st = conn.prepareStatement(sql) ;
			st.setInt(1, partmentId) ;
			st.setInt(2, year) ;
			st.setInt(3, month);
			st.setInt(4, BillKey.billState6) ; //被退回的保单
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
	private void getTheHasPayBillCountAndFee(PartmentMonthStat pms){
		String sql = "SELECT COUNT(*) , SUM(dbeAllFee) FROM tbbill WHERE intPartmentID=? AND YEAR(dteApplyDate)=? AND MONTH(dteApplyDate)=? AND intBillStateId=? AND intIsPay=?" ;
		try{
			conn = ConnectionManager.getConnection() ;
			st = conn.prepareStatement(sql) ;
			st.setInt(1, pms.getTbPartment().getId()) ;
			st.setInt(2, pms.getYear()) ;
			st.setInt(3, pms.getMonth()) ;
			st.setInt(4, BillKey.billState4) ; //被确认的保单
			st.setInt(5, BillKey.HAS_PAY_BILL) ;
			rs = st.executeQuery() ;
			if(rs.next()){
				int hasPayBillCount = rs.getInt(1) ; //已付费的保单数量
				double hasPayBillFee = rs.getDouble(2) ; //已付费的金额
				pms.setHasPayBillCount(hasPayBillCount) ;
				pms.setHasPayBillFee(hasPayBillFee) ;
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
	private void getTheNotPayBillCountAndFee(PartmentMonthStat pms){
		String sql = "SELECT COUNT(*) , SUM(dbeAllFee) FROM tbbill WHERE intPartmentID=? AND YEAR(dteApplyDate)=? AND MONTH(dteApplyDate)=? AND intBillStateId=? AND intIsPay=?" ;
		try{
			conn = ConnectionManager.getConnection() ;
			st = conn.prepareStatement(sql) ;
			st.setInt(1, pms.getTbPartment().getId()) ;
			st.setInt(2, pms.getYear()) ;
			st.setInt(3, pms.getMonth());
			st.setInt(4, BillKey.billState4) ; //被确认的保单
			st.setInt(5, BillKey.NOT_PAY_BILL) ; //未付费
			rs = st.executeQuery() ;
			if(rs.next()){
				int notPayBillCount = rs.getInt(1) ; //未付费的保单数量
				double notPayBillFee = rs.getDouble(2) ; //未付费的金额
				pms.setNotPayBillCount(notPayBillCount);
				pms.setNotPayBillFee(notPayBillFee) ;
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
	private void getThePeiKuanBillAccount(PartmentMonthStat pms){
		String sql = "SELECT COUNT(*),SUM(dbePeiKuanFee) FROM tbPeiKuan WHERE intBillId IN(SELECT id FROM tbbill WHERE intPartmentID=? AND YEAR(dteApplyDate)=? AND MONTH(dteApplyDate)=?)" ;
		try{
			conn = ConnectionManager.getConnection() ;
			st = conn.prepareStatement(sql) ;
			st.setInt(1, pms.getTbPartment().getId()) ;
			st.setInt(2, pms.getYear()) ;
			st.setInt(3, pms.getMonth()) ;
			rs = st.executeQuery() ;
			if(rs.next()){
				int peiKuaiBillCount = rs.getInt(1) ; //赔款的保单数量
				double peiKuanNumber = rs.getDouble(2) ; //赔款的金额
				pms.setPeiKuaiBillCount(peiKuaiBillCount) ;
				pms.setPeiKuanNumber(peiKuanNumber) ;
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
