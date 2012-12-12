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
import cn.insurance.dao.tongji.ITongJiByYearDao;
import cn.insurance.model.TbPartment;
import cn.insurance.model.tongji2.PartmentDayStat;
import cn.insurance.model.tongji2.PartmentMonthStat;
import cn.insurance.model.tongji2.PartmentTimeStat;
import cn.insurance.model.tongji2.PartmentYearStat;
import cn.insurance.utils.PrintLog;

/**
 * 对数据进行统计
 * @author yqg
 *
 */
public class TongJiByYearDao implements ITongJiByYearDao {
	
	private ITbPartmentDao tbPartmentDao ;
	
	private int firstYear = 2009 ;  //开始使用这个系统的年费是2008年,统计从这个年份开始
	
	private Connection conn ;
	
	private PreparedStatement st ;
	
	private ResultSet rs ;
	
	/*
	 * 按年进行统计
	 * (non-Javadoc)
	 * @see cn.insurance.dao.tongji.ITongJiDao#getPartmentYearStat(int)
	 */
	public List<PartmentYearStat> getPartmentYearStat(int partmentId) {
		//先查询出这个部门
		TbPartment tbPartment = tbPartmentDao.getObjectInfoById(partmentId) ; 
		Calendar calendar = new GregorianCalendar(); //当前的时间
		List<PartmentYearStat> list = new ArrayList<PartmentYearStat>() ;
		for(int year=firstYear ; year <= calendar.get(Calendar.YEAR) ; year++){
			list.add(getPartmentYearStatByYear(partmentId , year)) ;
		}
		return list;
	}
	
	/*
	 * 查询某年度统计
	 * (non-Javadoc)
	 * @see cn.insurance.dao.tongji.ITongJiByYearDao#getPartmentYearStatByYear(int)
	 */
	public PartmentYearStat getPartmentYearStatByYear(int partmentId , int year) {
		TbPartment tbPartment = tbPartmentDao.getObjectInfoById(partmentId) ; 
		PartmentYearStat partmentYearStat = new PartmentYearStat() ;
		partmentYearStat.setTbPartment(tbPartment) ;
		partmentYearStat.setYear(year) ;
		//查询这一年内所有的核得通过的保单数目
		partmentYearStat.setHasSureBillCount(getTheSureBillCountByYear(partmentId , year)) ;
		//查询这一年内所有的被保险公司退回的保单数目
		partmentYearStat.setReturnBillCount(getTheReturnBillCountByYear(partmentId , year)) ;
		//总数应该是通过和被退回的和
		partmentYearStat.setAllBillCount(partmentYearStat.getHasSureBillCount() + partmentYearStat.getReturnBillCount()) ;
		//查询已付费的保单费用及金额
		getTheHasPayBillCountAndFee(partmentYearStat) ;
		//查询未付费的保单费用及金额
		getTheNotPayBillCountAndFee(partmentYearStat) ;
		//查询赔款的保单数量及金额
		getThePeiKuanBillAccount(partmentYearStat) ;
		return partmentYearStat ;
	}

	
	
	
	
	/**
	 * 查询这一年内这个部门所有的核得通过的保单
	 * @param year
	 * @return
	 */
	private int getTheSureBillCountByYear(int partmentId , int year){
		String sql = "SELECT COUNT(*) FROM tbbill WHERE intPartmentID=? AND YEAR(dteApplyDate)=? AND intBillStateId=? " ;
		try{
			conn = ConnectionManager.getConnection() ;
			st = conn.prepareStatement(sql) ;
			st.setInt(1, partmentId) ;
			st.setInt(2, year) ;
			st.setInt(3, BillKey.billState4) ; //已生效的保单
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
	 * 查询这一年内这个部门所有被退回的保单
	 * @param year
	 * @return
	 */
	private int getTheReturnBillCountByYear(int partmentId , int year){
		String sql = "SELECT COUNT(*) FROM tbbill WHERE intPartmentID=? AND YEAR(dteApplyDate)=? AND intBillStateId=?" ;
		try{
			conn = ConnectionManager.getConnection() ;
			st = conn.prepareStatement(sql) ;
			st.setInt(1, partmentId) ;
			st.setInt(2, year) ;
			st.setInt(3, BillKey.billState6) ; //被退回的保单
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
	private void getTheHasPayBillCountAndFee(PartmentYearStat pys){
		String sql = "SELECT COUNT(*) , SUM(dbeAllFee) FROM tbbill WHERE intPartmentID=? AND YEAR(dteApplyDate)=? AND intBillStateId=? AND intIsPay=?" ;
		try{
			conn = ConnectionManager.getConnection() ;
			st = conn.prepareStatement(sql) ;
			st.setInt(1, pys.getTbPartment().getId()) ;
			st.setInt(2, pys.getYear()) ;
			st.setInt(3, BillKey.billState4) ; //被确认的保单
			st.setInt(4, BillKey.HAS_PAY_BILL) ;
			rs = st.executeQuery() ;
			if(rs.next()){
				int hasPayBillCount = rs.getInt(1) ; //已付费的保单数量
				double hasPayBillFee = rs.getDouble(2) ; //已付费的金额
				pys.setHasPayBillCount(hasPayBillCount) ;
				pys.setHasPayBillFee(hasPayBillFee) ;
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
	private void getTheNotPayBillCountAndFee(PartmentYearStat pys){
		String sql = "SELECT COUNT(*) , SUM(dbeAllFee) FROM tbbill WHERE intPartmentID=? AND YEAR(dteApplyDate)=? AND intBillStateId=? AND intIsPay=?" ;
		try{
			conn = ConnectionManager.getConnection() ;
			st = conn.prepareStatement(sql) ;
			st.setInt(1, pys.getTbPartment().getId()) ;
			st.setInt(2, pys.getYear()) ;
			st.setInt(3, BillKey.billState4) ; //被确认的保单
			st.setInt(4, BillKey.NOT_PAY_BILL) ; //未付费
			rs = st.executeQuery() ;
			if(rs.next()){
				int notPayBillCount = rs.getInt(1) ; //未付费的保单数量
				double notPayBillFee = rs.getDouble(2) ; //未付费的金额
				pys.setNotPayBillCount(notPayBillCount);
				pys.setNotPayBillFee(notPayBillFee) ;
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
	private void getThePeiKuanBillAccount(PartmentYearStat pys){
		String sql = "SELECT COUNT(*),SUM(dbePeiKuanFee) FROM tbPeiKuan WHERE intBillId IN(SELECT id FROM tbbill WHERE intPartmentID=? AND YEAR(dteApplyDate)=?)" ;
		try{
			conn = ConnectionManager.getConnection() ;
			st = conn.prepareStatement(sql) ;
			st.setInt(1, pys.getTbPartment().getId()) ;
			st.setInt(2, pys.getYear()) ;
			rs = st.executeQuery() ;
			if(rs.next()){
				int peiKuaiBillCount = rs.getInt(1) ; //赔款的保单数量
				double peiKuanNumber = rs.getDouble(2) ; //赔款的金额
				pys.setPeiKuaiBillCount(peiKuaiBillCount) ;
				pys.setPeiKuanNumber(peiKuanNumber) ;
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
