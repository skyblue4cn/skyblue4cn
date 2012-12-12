package cn.insurance.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cn.insurance.dao.BaseDao;
import cn.insurance.dao.ITbMonthPayOutLogDao;
import cn.insurance.model.TbMonthPayOutLog;

public class TbMonthPayOutLogDao extends BaseDao implements
		ITbMonthPayOutLogDao {
	
	@Override
	protected Object mapObj(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		TbMonthPayOutLog tbMonthPayOutLog = new TbMonthPayOutLog() ;
		tbMonthPayOutLog.setId(rs.getInt("id")) ;
		tbMonthPayOutLog.setDbePayNumber(rs.getDouble("dbePayNumber")) ;
		tbMonthPayOutLog.setDtePayDate(rs.getDate("dtePayDate")) ;
		tbMonthPayOutLog.setIntMonthPayId(rs.getInt("intMonthPayId")) ;
		tbMonthPayOutLog.setIntUserId(rs.getInt("intUserId")) ;
		tbMonthPayOutLog.setStrDesc(rs.getString("strDesc")) ;
		tbMonthPayOutLog.setStrUserName(rs.getString("strUserName")) ;
		return tbMonthPayOutLog;
	}

	public int addTbMonthPayOutLog(TbMonthPayOutLog tbMonthPayOutLog) {
		String sql = "INSERT INTO tbMonthPayOutLog(intMonthPayId,dbePayNumber,dtePayDate,intUserId,strUserName,strDesc) VALUES(?,?,?,?,?,?)" ;
		Object[] objs = {
			tbMonthPayOutLog.getIntMonthPayId() , 
			tbMonthPayOutLog.getDbePayNumber() ,
			tbMonthPayOutLog.getDtePayDate() ,
			tbMonthPayOutLog.getIntUserId(),
			tbMonthPayOutLog.getStrUserName() ,
			tbMonthPayOutLog.getStrDesc() , 
		} ;
		return super.jdbcTemplate.update(sql, objs) ;
	}

	public TbMonthPayOutLog getMonthPauOutLogByMonthPayId(
			Integer montPayId) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tbMonthPayOutLog WHERE intMonthPayId=" + montPayId ;
		List all = super.query(sql);
		if(all != null && all.size()>0){
			return (TbMonthPayOutLog)all.get(0) ;
		}
		return null ;
	}

}
