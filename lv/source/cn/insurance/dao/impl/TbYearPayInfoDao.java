package cn.insurance.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cn.insurance.dao.BaseDao;
import cn.insurance.dao.ITbYearPayInfoDao;
import cn.insurance.model.PageBean;
import cn.insurance.model.TbYearPayInfo;

public class TbYearPayInfoDao extends BaseDao implements ITbYearPayInfoDao {

	@Override
	protected Object mapObj(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		TbYearPayInfo tbYearPayInfo = new TbYearPayInfo() ;
		tbYearPayInfo.setId(rs.getInt("id")) ;
		tbYearPayInfo.setIntAccountId(rs.getInt("intAccountId")) ;
		tbYearPayInfo.setIntUser(rs.getInt("intUser")) ;
		tbYearPayInfo.setDbeNeedFeeByYear(rs.getDouble("dbeNeedFeeByYear")) ;
		tbYearPayInfo.setDbePayFee(rs.getDouble("dbePayFee")) ;
		tbYearPayInfo.setDbeCurResidual(rs.getDouble("dbeCurResidual")) ;
		tbYearPayInfo.setDbeAftResidual(rs.getDouble("dbeAftResidual")) ;
		tbYearPayInfo.setDteEndTime(rs.getDate("dteEndTime")) ;
		tbYearPayInfo.setDtePayDate(rs.getDate("dtePayDate")) ;
		tbYearPayInfo.setDteStartTime(rs.getDate("dteStartTime")) ;
		tbYearPayInfo.setStrUserName(rs.getString("strUserName")) ;
		tbYearPayInfo.setStrDesc(rs.getString("strDesc")) ;
		return tbYearPayInfo;
	}
	
	/*
	 * 添加年结算用户的支付记录
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbYearPayInfoDao#create(cn.insurance.model.TbYearPayInfo)
	 */
	public int create(TbYearPayInfo tbYearPayInfo) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO tbYearPayInfo(intAccountId,dbeNeedFeeByYear,dbePayFee,dbeCurResidual,dbeAftResidual,dtePayDate,dteStartTime,dteEndTime,intUser,strUserName,strDesc)VALUES(?,?,?,?,?,?,?,?,?,?,?)" ;
		Object[] objs = {
			tbYearPayInfo.getIntAccountId(),
			tbYearPayInfo.getDbeNeedFeeByYear(),
			tbYearPayInfo.getDbePayFee(),
			tbYearPayInfo.getDbeCurResidual(),
			tbYearPayInfo.getDbeAftResidual(),
			tbYearPayInfo.getDtePayDate(),
			tbYearPayInfo.getDteStartTime(),
			tbYearPayInfo.getDteEndTime(),
			tbYearPayInfo.getIntUser(),
			tbYearPayInfo.getStrUserName(),
			tbYearPayInfo.getStrDesc()
		} ;
		return super.jdbcTemplate.update(sql, objs);
	}
	
	/*
	 * 更新年费记录
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbYearPayInfoDao#update(cn.insurance.model.TbYearPayInfo)
	 */
	public TbYearPayInfo update(TbYearPayInfo tbYearPayInfo) {
		// TODO Auto-generated method stub
		String sql = "UPDATE tbYearPayInfo SET intAccountId=?,dbeNeedFeeByYear=?,dbePayFee=?,dtePayDate=?,dteStartTime=?,dteEndTime=?,intUser=?,strUserName=?,strDesc=? WHERE id=?" ;
		Object[] objs = {
				tbYearPayInfo.getIntAccountId(),
				tbYearPayInfo.getDbeNeedFeeByYear(),
				tbYearPayInfo.getDbePayFee(),
				tbYearPayInfo.getDtePayDate(),
				tbYearPayInfo.getDteStartTime(),
				tbYearPayInfo.getDteEndTime(),
				tbYearPayInfo.getIntUser(),
				tbYearPayInfo.getStrUserName(),
				tbYearPayInfo.getStrDesc(),
				tbYearPayInfo.getId()
			} ;
		super.jdbcTemplate.update(sql, objs) ;
		return tbYearPayInfo;
	}
	
	/*
	 * 查询全部记录
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbYearPayInfoDao#getAllObjectList()
	 */
	public List<TbYearPayInfo> getAllObjectList() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tbYearPayInfo" ;
		return super.query(sql);
	}
	
	/*
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbYearPayInfoDao#getAllObjectListByCondition(java.lang.Object)
	 */
	public List<TbYearPayInfo> getAllObjectListByCondition(Object obj) {
		// TODO Auto-generated method stub
		
		return null;
	}
	
	/*
	 * 按ID查询
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbYearPayInfoDao#getObjectInfoById(java.lang.Integer)
	 */
	public TbYearPayInfo getObjectInfoById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * 按ID删除
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbYearPayInfoDao#delete(java.lang.Integer)
	 */
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM tbYearPayInfo WHERE id=" +id ;
		return super.update(sql);
	}
	
	/*
	 * 查询所有帐户年费包干记录
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbYearPayInfoDao#getYearPayByAccountId(cn.insurance.model.PageBean, java.lang.Integer)
	 */
	public PageBean getYearPayByAccountId(PageBean pageBean , Integer accountId) {
		StringBuffer conditionSql = new StringBuffer() ;
		conditionSql.append(" AND intAccountId=").append(accountId) ;
		conditionSql.append(" ORDER BY id DESC") ;
		return super.getPageBean(pageBean, conditionSql.toString(), "tbYearPayInfo") ;
		
		
	}
}
