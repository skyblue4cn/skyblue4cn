package cn.insurance.dao.tongji.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.insurance.commonwords.BillKey;
import cn.insurance.dao.ITbPartmentDao;
import cn.insurance.dao.conn.ConnectionManager;
import cn.insurance.dao.tongji.ITongJiByTimeSpaceDao;
import cn.insurance.model.TbPartment;
import cn.insurance.model.tongji2.PartmentDayStat;
import cn.insurance.model.tongji2.PartmentTimeStat;
import cn.insurance.utils.PrintLog;

public class TongJiByTimeSpaceDao implements ITongJiByTimeSpaceDao {
	
	private ITbPartmentDao tbPartmentDao ;
	
	private Connection conn ;
	
	private PreparedStatement st ;
	
	private ResultSet rs ;
	
	/*
	 * 按时间段统计
	 * (non-Javadoc)
	 * @see cn.insurance.dao.right.ITongJiByTimeSpace#getPartmentTimeStatList(int, java.lang.String, java.lang.String)
	 */
	public PartmentTimeStat getPartmentTimeStatList(int partmentId, String startTime, String endTime) {
		String limitTimeSql = getLimimtTimeSql(startTime, endTime) ;
		TbPartment tbPartment = tbPartmentDao.getObjectInfoById(partmentId) ; 
		PartmentTimeStat pts = new PartmentTimeStat();
		pts.setTbPartment(tbPartment) ;
		pts.setStartTime(startTime) ;
		pts.setEndTime(endTime) ;
		//查询所有的核得通过的保单数目
		pts.setHasSureBillCount(getTheSureBillCountByYear(partmentId , limitTimeSql)) ;
		//查询所有的被保险公司退回的保单数目
		pts.setReturnBillCount(getTheReturnBillCountByYear(partmentId , limitTimeSql)) ;
		//总数应该是通过和被退回的和
		pts.setAllBillCount(pts.getHasSureBillCount() + pts.getReturnBillCount()) ;
		//查询已付费的保单费用及金额
		getTheHasPayBillCountAndFee(pts , limitTimeSql) ;
		//查询未付费的保单费用及金额
		getTheNotPayBillCountAndFee(pts , limitTimeSql) ;
		//查询赔款的保单数量及金额
		getThePeiKuanBillAccount(pts , limitTimeSql) ;
		return pts;
	}
	
	

	/**
	 * 查询所有的核得通过的保单
	 * @param year
	 * @return
	 */
	private int getTheSureBillCountByYear(int partmentId , String limitTimeSql){
		String sql = "SELECT COUNT(*) FROM tbbill WHERE intPartmentID=?  AND intBillStateId=? " +limitTimeSql ;
		try{
			conn = ConnectionManager.getConnection() ;
			st = conn.prepareStatement(sql) ;
			st.setInt(1, partmentId) ;
			st.setInt(2, BillKey.billState4) ; //已生效的保单
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
	private int getTheReturnBillCountByYear(int partmentId , String limitTimeSql){
		String sql = "SELECT COUNT(*) FROM tbbill WHERE intPartmentID=? AND intBillStateId=? " + limitTimeSql ;
		try{
			conn = ConnectionManager.getConnection() ;
			st = conn.prepareStatement(sql) ;
			st.setInt(1, partmentId) ;
			st.setInt(2, BillKey.billState6) ; //被退回的保单
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
	private void getTheHasPayBillCountAndFee(PartmentTimeStat pts , String limitTimeSql){
		String sql = "SELECT COUNT(*) , SUM(dbeAllFee) FROM tbbill WHERE intPartmentID=?  AND intBillStateId=? AND intIsPay=? " + limitTimeSql;
		try{
			conn = ConnectionManager.getConnection() ;
			st = conn.prepareStatement(sql) ;
			st.setInt(1, pts.getTbPartment().getId()) ;
			st.setInt(2, BillKey.billState4) ; //被确认的保单
			st.setInt(3, BillKey.HAS_PAY_BILL) ;
			rs = st.executeQuery() ;
			if(rs.next()){
				int hasPayBillCount = rs.getInt(1) ; //已付费的保单数量
				double hasPayBillFee = rs.getDouble(2) ; //已付费的金额
				pts.setHasPayBillCount(hasPayBillCount) ;
				pts.setHasPayBillFee(hasPayBillFee) ;
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
	private void getTheNotPayBillCountAndFee(PartmentTimeStat pts , String limitTimeSql){
		String sql = "SELECT COUNT(*) , SUM(dbeAllFee) FROM tbbill WHERE intPartmentID=?  AND intBillStateId=? AND intIsPay=? " + limitTimeSql ;
		try{
			conn = ConnectionManager.getConnection() ;
			st = conn.prepareStatement(sql) ;
			st.setInt(1, pts.getTbPartment().getId()) ;
			st.setInt(2, BillKey.billState4) ; //被确认的保单
			st.setInt(3, BillKey.NOT_PAY_BILL) ; //未付费
			rs = st.executeQuery() ;
			if(rs.next()){
				int notPayBillCount = rs.getInt(1) ; //未付费的保单数量
				double notPayBillFee = rs.getDouble(2) ; //未付费的金额
				pts.setNotPayBillCount(notPayBillCount);
				pts.setNotPayBillFee(notPayBillFee) ;
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
	private void getThePeiKuanBillAccount(PartmentTimeStat pts ,String limitTimeSql){
		String sql = "SELECT COUNT(*),SUM(dbePeiKuanFee) FROM tbPeiKuan WHERE intBillId IN(SELECT id FROM tbbill WHERE intPartmentID=? "+ limitTimeSql+")" ;
		try{
			conn = ConnectionManager.getConnection() ;
			st = conn.prepareStatement(sql) ;
			st.setInt(1, pts.getTbPartment().getId()) ;
			rs = st.executeQuery() ;
			if(rs.next()){
				int peiKuaiBillCount = rs.getInt(1) ; //赔款的保单数量
				double peiKuanNumber = rs.getDouble(2) ; //赔款的金额
				pts.setPeiKuaiBillCount(peiKuaiBillCount) ;
				pts.setPeiKuanNumber(peiKuanNumber) ;
			}
		}catch(Exception e){
			PrintLog.getLog().error(this, e) ;
		}finally{
			ConnectionManager.close(rs, st, conn) ;
		}
	
	
	}
	
	/**
	 * 取得时间段查询的限制语句
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	private String getLimimtTimeSql(String startTime , String endTime){
		StringBuffer sb = new StringBuffer();
		if(startTime != null &&! "".equals(startTime.trim())){
			startTime += " 00:00:00" ;
			sb.append(" AND dteApplyDate>='" + startTime +"' ") ;
		}
		if(endTime != null && !"".equals(endTime.trim()) ){
			endTime += " 59:59:59" ;
			sb.append(" AND dteApplyDate<'" + endTime+"' ") ;
		}
		return sb.toString() ;
	}



	public ITbPartmentDao getTbPartmentDao() {
		return tbPartmentDao;
	}



	public void setTbPartmentDao(ITbPartmentDao tbPartmentDao) {
		this.tbPartmentDao = tbPartmentDao;
	}

}
