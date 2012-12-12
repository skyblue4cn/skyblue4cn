package cn.insurance.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import cn.insurance.dao.BaseDao;
import cn.insurance.dao.ITbBillDao;
import cn.insurance.dao.ITbListForMonthPayDao;
import cn.insurance.model.TbListForMonthPay;
import cn.insurance.utils.CommonWords;

public class TbListForMonthPayDao extends BaseDao implements ITbListForMonthPayDao {
	
	private ITbBillDao tbBillDao ;
	
	@Override
	protected Object mapObj(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		TbListForMonthPay tbListForMonthPay = new TbListForMonthPay() ;
		tbListForMonthPay.setId(rs.getInt("id")) ;
		tbListForMonthPay.setIntBillId(rs.getInt("intBillId")) ;
		tbListForMonthPay.setIntMonthPayId(rs.getInt("intMonthPayId")) ;
		
		tbListForMonthPay.setIntPayMonthFeeId(rs.getInt("intPayMonthFeeId")) ;
		tbListForMonthPay.setIntBillState(rs.getInt("intBillState")) ;
		
		tbListForMonthPay.setTbBill(tbBillDao.getObjectInfoById(tbListForMonthPay.getIntBillId())) ;
		
		return tbListForMonthPay;
	}

	public int create(TbListForMonthPay tbListForMonthPay) {
		String sql =" INSERT INTO tbListForMonthPay(intMonthPayId,intBillId,intPayMonthFeeId,intBillState)VALUES(?,?,?,?)" ;
		Object[] objs = {
				tbListForMonthPay.getIntMonthPayId() ,
				tbListForMonthPay.getIntBillId(),
				tbListForMonthPay.getIntPayMonthFeeId(),
				tbListForMonthPay.getIntBillState()
		} ;
		return super.jdbcTemplate.update(sql, objs);
	}
	/*
	 * 批量添加记录
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbListForMonthPayDao#createTbListForMonthPay(java.lang.Integer, java.lang.Integer[])
	 */
	public int createTbListForMonthPay(Integer monthPayId, Integer[] billIds) {
		// TODO Auto-generated method stub
		for(Integer billId:billIds){
			StringBuffer sql = new StringBuffer() ;
			sql.append(" INSERT INTO tbListForMonthPay(intMonthPayId,intBillId)VALUES(") ;
			sql.append(monthPayId).append(",") ;
			sql.append(billId).append(")") ;
			super.update(sql.toString()) ;
		}
		return 1 ;
	}
	
	/*
	 * 收费时月费清单里的应付费用的单子全部更新为已交费状态
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbListForMonthPayDao#updateBillStateByMonthPayIdForShouFei(int)
	 */
	 
	public int updateBillStateByMonthPayIdForShouFei(int monthPayId){
		/*更新清单里的状态*/
		StringBuffer sql = new StringBuffer() ;
		sql.append(" UPDATE tbListForMonthPay SET intBillState=").append(CommonWords.billMonthState2) ;
		sql.append(" WHERE intPayMonthFeeId=").append(monthPayId) ;
		super.update(sql.toString()) ;
		/*更新保单里的状态为已交费*/
		sql = new StringBuffer() ;
		sql.append(" UPDATE tbBill SET intIsPay=1 WHERE id IN (SELECT intBillId FROM tbListForMonthPay WHERE intPayMonthFeeId=").append(monthPayId).append(")") ;
		super.update(sql.toString()) ;
		return 1 ;
	}
	
	

	
	/*
	 * 查询月费的应付清单
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbListForMonthPayDao#getYingFuListForMonthPay(java.lang.Integer)
	 */
	public List<TbListForMonthPay> getYingFuListForMonthPay(Integer monthPayId){
		StringBuffer sql = new StringBuffer() ;
		/*清单属于该月并且由该月付费*/
		sql.append("SELECT * FROM tbListForMonthPay WHERE intMonthPayId=intPayMonthFeeId AND intMonthPayId=").append(monthPayId) ;
		return super.query(sql.toString()) ;
	}
	
	/*
	 * 通过月费的清单查询所以清单要付费的总费用
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbListForMonthPayDao#getYingFuMonthFeeByMonthId(int)
	 */
	public double getYingFuMonthFeeByMonthId(int monthPayId) {
		String sql = "SELECT SUM(dbeAllFee) FROM tbbill WHERE id IN(SELECT intBillId FROM tbListForMonthPay WHERE intMonthPayId=?)" ;
		Object[] objs = {monthPayId} ;
		return (Double) super.jdbcTemplate.queryForObject(sql, objs ,new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getDouble(1) ;
			}
		}) ;
	}


	
	
	
	
	public List<TbListForMonthPay> getLogListByMonthPayId(Integer montPayId) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tbListForMonthPay WHERE intMonthPayId=" + montPayId;
		return super.query(sql);
	}

	/*
	 * 删除清单中的保单
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbListForMonthPayDao#deleteBillListByBillId(int)
	 */
	public int deleteBillListByBillId(int billId){
		String sql = "DELETE FROM tbListForMonthPay WHERE intBillId=" + billId ;
		return super.update(sql) ;
	}
	
	
	public ITbBillDao getTbBillDao() {
		return tbBillDao;
	}

	public void setTbBillDao(ITbBillDao tbBillDao) {
		this.tbBillDao = tbBillDao;
	}




}
