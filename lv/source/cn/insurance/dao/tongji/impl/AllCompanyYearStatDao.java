package cn.insurance.dao.tongji.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.insurance.commonwords.BillKey;
import cn.insurance.dao.ITbPartmentDao;
import cn.insurance.dao.conn.ConnectionManager;
import cn.insurance.dao.tongji.IAllCompanyYearStatDao;
import cn.insurance.model.TbPartment;
import cn.insurance.model.tongji2.AllCompanyYearStat;
import cn.insurance.utils.PrintLog;

public class AllCompanyYearStatDao implements IAllCompanyYearStatDao {
	
	
	private Connection conn ;
	
	private PreparedStatement st ;
	
	private ResultSet rs ;
	
	private ITbPartmentDao tbPartmentDao ;
	
	
	/*
	 * 按年统计旅行社及下属部门的所有保单
	 * (non-Javadoc)
	 * @see cn.insurance.dao.tongji.ICompanyYearStatDao#getCompanyYearStatList(int)
	 */
	public List<AllCompanyYearStat> getAllCompanyYearStatList(int year) {
		List<AllCompanyYearStat> list = new ArrayList<AllCompanyYearStat>();
		List<TbPartment> companyList = tbPartmentDao.getAllCompanyList();
		for(TbPartment company : companyList){
			list.add(getCompanyYearStatByYear(year, company));
		}
		return list ;
	}

	
	
	/**
	 * 按年统计指定旅行社及下属部门的所有保单
	 * @param year
	 * @param companyId
	 * @return
	 */
	private AllCompanyYearStat getCompanyYearStatByYear(int year , TbPartment company){
		AllCompanyYearStat cys = new AllCompanyYearStat();
		cys.setCompany(company);
		cys.setYear(year);
		//查询这一年内所有的核得通过的保单数目
		cys.setHasSureBillCount(getTheSureBillCountByYear(year , company.getId())) ;
		//查询这一年内所有的被保险公司退回的保单数目
		cys.setReturnBillCount(getTheReturnBillCountByYear(year, company.getId())) ;
		//总数应该是通过和被退回的和
		cys.setAllBillCount(cys.getHasSureBillCount() + cys.getReturnBillCount()) ;
		//查询已付费的保单费用及金额
		getTheHasPayBillCountAndFee(cys) ;
		//查询未付费的保单费用及金额
		getTheNotPayBillCountAndFee(cys) ;
		//查询赔款的保单数量及金额
		getThePeiKuanBillAccount(cys) ;
		return cys ;
	}

	
	/**
	 * 查询这一年内这个部门所有的核得通过的保单
	 * @param year
	 * @return
	 */
	private int getTheSureBillCountByYear( int year , int companyId){
		String sql = "SELECT COUNT(*) FROM tbbill WHERE YEAR(dteApplyDate)=? AND intBillStateId=? AND (intPartmentID=? OR intPartmentID in(SELECT id FROM tbPartment WHERE intParentId=?)) " ;
		try{
			conn = ConnectionManager.getConnection() ;
			st = conn.prepareStatement(sql) ;
			st.setInt(1, year) ;
			st.setInt(2, BillKey.billState4) ; //已生效的保单
			st.setInt(3, companyId);
			st.setInt(4, companyId) ;
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
	private int getTheReturnBillCountByYear(int year , int companyId){
		String sql = "SELECT COUNT(*) FROM tbbill WHERE YEAR(dteApplyDate)=? AND intBillStateId=? AND (intPartmentID=? OR intPartmentID in(SELECT id FROM tbPartment WHERE intParentId=?)) " ;
		try{
			conn = ConnectionManager.getConnection() ;
			st = conn.prepareStatement(sql) ;
			st.setInt(1, year) ;
			st.setInt(2, BillKey.billState6) ; //被退回的保单
			st.setInt(3, companyId);
			st.setInt(4, companyId) ;
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
	private void getTheHasPayBillCountAndFee(AllCompanyYearStat cys){
		String sql = "SELECT COUNT(*) , SUM(dbeAllFee) FROM tbbill WHERE YEAR(dteApplyDate)=? AND intBillStateId=? AND intIsPay=? AND (intPartmentID=? OR intPartmentID in(SELECT id FROM tbPartment WHERE intParentId=?)) " ;
		try{
			conn = ConnectionManager.getConnection() ;
			st = conn.prepareStatement(sql) ;
			st.setInt(1, cys.getYear()) ;
			st.setInt(2, BillKey.billState4) ; //被确认的保单
			st.setInt(3, BillKey.HAS_PAY_BILL) ;
			st.setInt(4, cys.getCompany().getId());
			st.setInt(5, cys.getCompany().getId()) ;
			rs = st.executeQuery() ;
			if(rs.next()){
				int hasPayBillCount = rs.getInt(1) ; //已付费的保单数量
				double hasPayBillFee = rs.getDouble(2) ; //已付费的金额
				cys.setHasPayBillCount(hasPayBillCount) ;
				cys.setHasPayBillFee(hasPayBillFee) ;
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
	private void getTheNotPayBillCountAndFee(AllCompanyYearStat cys){
		String sql = "SELECT COUNT(*) , SUM(dbeAllFee) FROM tbbill WHERE YEAR(dteApplyDate)=? AND intBillStateId=? AND intIsPay=? AND (intPartmentID=? OR intPartmentID in(SELECT id FROM tbPartment WHERE intParentId=?))" ;
		try{
			conn = ConnectionManager.getConnection() ;
			st = conn.prepareStatement(sql) ;
			st.setInt(1, cys.getYear()) ;
			st.setInt(2, BillKey.billState4) ; //被确认的保单
			st.setInt(3, BillKey.NOT_PAY_BILL) ; //未付费
			st.setInt(4, cys.getCompany().getId());
			st.setInt(5, cys.getCompany().getId()) ;
			rs = st.executeQuery() ;
			if(rs.next()){
				int notPayBillCount = rs.getInt(1) ; //未付费的保单数量
				double notPayBillFee = rs.getDouble(2) ; //未付费的金额
				cys.setNotPayBillCount(notPayBillCount);
				cys.setNotPayBillFee(notPayBillFee) ;
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
	private void getThePeiKuanBillAccount(AllCompanyYearStat cys){
		String sql = "SELECT COUNT(*),SUM(dbePeiKuanFee) FROM tbPeiKuan WHERE intBillId IN(SELECT id FROM tbbill WHERE YEAR(dteApplyDate)=? AND (intPartmentID=? OR intPartmentID in(SELECT id FROM tbPartment WHERE intParentId=?))) " ;
		try{
			conn = ConnectionManager.getConnection() ;
			st = conn.prepareStatement(sql) ;
			st.setInt(1, cys.getYear()) ;
			st.setInt(2, cys.getCompany().getId());
			st.setInt(3, cys.getCompany().getId()) ;
			rs = st.executeQuery() ;
			if(rs.next()){
				int peiKuaiBillCount = rs.getInt(1) ; //赔款的保单数量
				double peiKuanNumber = rs.getDouble(2) ; //赔款的金额
				cys.setPeiKuaiBillCount(peiKuaiBillCount) ;
				cys.setPeiKuanNumber(peiKuanNumber) ;
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
