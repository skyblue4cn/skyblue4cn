package cn.insurance.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cn.insurance.dao.BaseDao;
import cn.insurance.dao.ITbTravelerInfoDao;
import cn.insurance.model.TbTravelerInfo;

public class TbTravelerInfoDao extends BaseDao implements ITbTravelerInfoDao {

	@Override
	protected Object mapObj(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		TbTravelerInfo tbTravelerInfo = new TbTravelerInfo() ;
		tbTravelerInfo.setId(rs.getInt("id")) ;
		tbTravelerInfo.setIntBillId(rs.getInt("intBillId")) ;
		tbTravelerInfo.setStrCountry(rs.getString("strCountry")) ;
		tbTravelerInfo.setStrIndentyNumber(rs.getString("strIndentyNumber")) ;
		tbTravelerInfo.setStrTravelerName(rs.getString("strTravelerName")) ;
		tbTravelerInfo.setStrSex(rs.getString("strSex")) ;
		tbTravelerInfo.setStrBirthday(rs.getString("strBirthday")) ;
		return tbTravelerInfo;
	}

	public int create(TbTravelerInfo tbTravelerInfo) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO tbTravelerInfo(intBillId,strTravelerName,strSex,strBirthday,strCountry,strIndentyNumber) VALUES(?,?,?,?,?,?)" ;
		Object[] objs = {
				tbTravelerInfo.getIntBillId(),
				tbTravelerInfo.getStrTravelerName(),
				tbTravelerInfo.getStrSex(),
				tbTravelerInfo.getStrBirthday(),
				tbTravelerInfo.getStrCountry(),
				tbTravelerInfo.getStrIndentyNumber()
		} ;
		return super.jdbcTemplate.update(sql, objs);
	}
	
	/*
	 * 批量添加游客
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbTravelerInfoDao#create(java.util.List)
	 */
	public int create(List<TbTravelerInfo> travelerList){
		for(TbTravelerInfo t : travelerList){
			create(t) ;
		}
		return 1 ;
	}
	
	
	/*
	 * 更新
	 * (non-Javadoc)
	 * @see cn.insurance.dao.IDataDao#update(java.lang.Object)
	 */
	public int update(TbTravelerInfo tbTravelerInfo) {
		// TODO Auto-generated method stub
		String sql = "UPDATE tbTravelerInfo SET intBillId=?,strTravelerName=?,strSex=?,strBirthday=?,strCountry=?,strIndentyNumber=? WHERE id=?" ; 
		Object[] objs = {
			tbTravelerInfo.getIntBillId(),
			tbTravelerInfo.getStrTravelerName() ,
			tbTravelerInfo.getStrSex(),
			tbTravelerInfo.getStrBirthday(),
			tbTravelerInfo.getStrCountry(),
			tbTravelerInfo.getStrIndentyNumber(),
			tbTravelerInfo.getId()
		} ;
		return super.jdbcTemplate.update(sql,objs);
	}


	public List getAllObjectListByBillId(Integer billId) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tbTravelerInfo WHERE intBillId=" + billId ;
		return super.query(sql);
	}

	public TbTravelerInfo getObjectInfoById(Integer id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * from tbTravelerInfo WHERE id=" + id ;
		return  (TbTravelerInfo)super.queryByObj(sql);
	}

	
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM tbTravelerInfo WHERE id=" + id ;
		return super.update(sql);
	}
	
	/*
	 * 
	 * 按保单ID删除所有的游客
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbTravelerInfoDao#deleteByBillId(java.lang.Integer)
	 */
	public int deleteByBillId(Integer billId){
		String sql = "DELETE FROM tbTravelerInfo WHERE intBillId=" + billId ;
		return super.update(sql) ;
	}
	
	
	/** 修改于2010-6-8*/
	//根据用户的名称和身份证获得所有的保单
	public List getBillIdByTravelerInfo(String strName,String strNum){
		Set r = new HashSet();
		List rl = new ArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from TbTravelerInfo u where ");
		if(!strName.equals("")){
			sql.append("u.strTravelerName like '%" + strName + "' ");
			if(!strNum.equals(""))
				sql.append(" and ");
		}
		if(!strNum.equals(""))
			sql.append("u.strIndentyNumber = '" + strNum + "'");
			
		rl = super.query(sql.toString());
//		List l = super.query(" where u.strTravelerName like '%" 
//				+ strName + "%' or u.strIndentyNumber = '" + strNum + "'");
//		for (Iterator iterator = l.iterator(); iterator.hasNext();) {
//			TbTravelerInfo o = (TbTravelerInfo) iterator.next();
//			int billid = o.getIntBillId();
//			if(!r.contains(r)){
//				rl.add(o);
//				r.add(billid);
//			}
//		}
		return rl;
	}
	
	
}
