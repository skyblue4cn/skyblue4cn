package cn.insurance.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.insurance.dao.BaseDao;
import cn.insurance.dao.ITbBillDao;
import cn.insurance.dao.ITbPeiKuanDao;
import cn.insurance.model.PageBean;
import cn.insurance.model.TbPeiKuan;

public class TbPeiKuanDao extends BaseDao implements ITbPeiKuanDao {

	private ITbBillDao tbBillDao ;
	
	@Override
	protected Object mapObj(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		TbPeiKuan tbPeiKuan = new TbPeiKuan() ;
		tbPeiKuan.setId(rs.getInt("id")) ;
		tbPeiKuan.setIntBillId(rs.getInt("intBillId")) ;
		tbPeiKuan.setDbePeiKuanFee(rs.getDouble("dbePeiKuanFee")) ;
		tbPeiKuan.setStrDesc(rs.getString("strDesc")) ;
		
		/*保单详情*/
//		tbPeiKuan.setTbBill(tbBillDao.getObjectInfoById(tbPeiKuan.getIntBillId())) ;
		return tbPeiKuan;
	}

	public int addPeiKuanLog(TbPeiKuan tbPeiKuan) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO tbPeiKuan (intBillId,dbePeiKuanFee,strDesc) VALUES(?,?,?)" ;
		Object[] objs = {
				tbPeiKuan.getIntBillId() ,
				tbPeiKuan.getDbePeiKuanFee(),
				tbPeiKuan.getStrDesc()
		} ;
		return super.jdbcTemplate.update(sql, objs);
	}

	public TbPeiKuan getPeiKuanLogByBillId(Integer billId) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tbPeiKuan WHERE intBillId=" + billId + " ORDER by id DESC";
		try{
			return (TbPeiKuan)super.queryByObj(sql) ;
		}catch(Exception e){
			return null;
		}
	}

	
	public PageBean getAllPeiKuanLog(PageBean pagebean) {
		return super.getPageBean(pagebean, "", "tbPeiKuan") ;
	}

	public ITbBillDao getTbBillDao() {
		return tbBillDao;
	}

	public void setTbBillDao(ITbBillDao tbBillDao) {
		this.tbBillDao = tbBillDao;
	}
	
}
