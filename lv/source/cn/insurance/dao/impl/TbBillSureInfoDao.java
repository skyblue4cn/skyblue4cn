package cn.insurance.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cn.insurance.dao.BaseDao;
import cn.insurance.dao.ITbBillSureInfoDao;
import cn.insurance.model.TbBillSureInfo;

public class TbBillSureInfoDao extends BaseDao implements ITbBillSureInfoDao {

	@Override
	protected Object mapObj(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		TbBillSureInfo tbBillSureInfo = new TbBillSureInfo() ;
		tbBillSureInfo.setId(rs.getInt("id")) ;
		tbBillSureInfo.setIntBillId(rs.getInt("intBillId")) ;
		tbBillSureInfo.setIntSureType(rs.getInt("intSureType")) ;
		tbBillSureInfo.setIntSureUserId(rs.getInt("intSureUserId")) ;
		tbBillSureInfo.setDteSureTime(rs.getDate("dteSureTime")) ;
		tbBillSureInfo.setStrSureUserName(rs.getString("strSureUserName")) ;
		tbBillSureInfo.setStrDesc(rs.getString("strDesc")) ;
		tbBillSureInfo.setIntCurBillState(rs.getInt("intCurBillState")) ;
		tbBillSureInfo.setIntAftBillState(rs.getInt("intAftBillState")) ;
		return tbBillSureInfo;
	}
	
	/*
	 * 添加确认记录
	 * 
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbBillSureInfoDao#addTbBillSureInfoDao(cn.insurance.model.TbBillSureInfo)
	 */
	public int addTbBillSureInfoDao(TbBillSureInfo tbBillSureInfo) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO tbBillSureInfo(intBillId,intSureType,intSureUserId,strSureUserName,intCurBillState,intAftBillState,dteSureTime,strDesc)VALUES(?,?,?,?,?,?,?,?)" ;
		Object[] objs = {
				tbBillSureInfo.getIntBillId(),
				tbBillSureInfo.getIntSureType(),
				tbBillSureInfo.getIntSureUserId(),
				tbBillSureInfo.getStrSureUserName(),
				tbBillSureInfo.getIntCurBillState(),
				tbBillSureInfo.getIntAftBillState(),
				tbBillSureInfo.getDteSureTime(),
				tbBillSureInfo.getStrDesc()
		} ;
		return super.jdbcTemplate.update(sql, objs);
	}
	
	
	/*
	 * 
	 * 按保单ID查询确认记录
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbBillSureInfoDao#getBillSureInfoByBillId(java.lang.Integer, int)
	 */
	public List<TbBillSureInfo> getBillSureInfoByBillId(Integer billId, int type) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tbBillSureInfo WHERE intBillId=" + billId + " AND intSureType=" + type ;
		return super.query(sql);
	}

}
