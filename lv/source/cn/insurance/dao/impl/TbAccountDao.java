package cn.insurance.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cn.insurance.dao.BaseDao;
import cn.insurance.dao.ITbAccountDao;
import cn.insurance.model.TbAccount;

public class TbAccountDao extends BaseDao implements ITbAccountDao {

	@Override
	protected Object mapObj(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		TbAccount tbAccount = new TbAccount() ;
		tbAccount.setId(rs.getInt("id")) ;
		tbAccount.setIntIsSonUse(rs.getInt("intIsSonUse")) ;
		tbAccount.setIntPartmentId(rs.getInt("intPartmentId")) ;
		tbAccount.setDbeResidual(rs.getDouble("dbeResidual")) ;
		tbAccount.setStrMoneyType(rs.getString("strMoneyType")) ;
		tbAccount.setDteStartTimeToNoPay(rs.getDate("dteStartTimeToNoPay")) ;
		tbAccount.setIntPayTypeId(rs.getInt("intPayTypeId")) ;
		tbAccount.setIntAccountState(rs.getInt("intAccountState")) ;
		
		tbAccount.setDbeAcceptMaxMoney(rs.getDouble("dbeAcceptMaxMoney")) ;
		tbAccount.setDbeNeedMessage(rs.getDouble("dbeNeedMessage")) ;
		tbAccount.setIntAcceptDays(rs.getInt("intAcceptDays")) ;
		return tbAccount;
	}
	
	/*
	 * 添加帐户
	 * (non-Javadoc)
	 * @see cn.insurance.dao.IDataDao#create(java.lang.Object)
	 */
	public int create(TbAccount tbAccount) {
		// TODO Auto-generated method stub
		String sql =  "INSERT INTO tbAccount(intPartmentId,dbeResidual,intIsSonUse,intPayTypeId)VALUES(?,?,?,?)" ;
 		Object[] objs = {
 				tbAccount.getIntPartmentId(),
				tbAccount.getDbeResidual(),
				tbAccount.getIntIsSonUse(),
				tbAccount.getIntPayTypeId()
 		} ;
		return super.jdbcTemplate.update(sql,objs);
		
	}

	/*
	 * 更新帐户信息
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbAccountDao#update(cn.insurance.model.TbAccount)
	 */
	public TbAccount update(TbAccount tbAccount) {
		// TODO Auto-generated method stub
		String sql = "UPDATE tbAccount SET intPartmentId=?,dbeResidual=?,strMoneyType=?,intIsSonUse=?,intPayTypeId=?,dteStartTimeToNoPay=?,intAccountState=?,dbeNeedMessage=?,intAcceptDays=?,dbeAcceptMaxMoney=? WHERE id=?" ;
		Object[] objs ={
				tbAccount.getIntPartmentId(),
				tbAccount.getDbeResidual(),
				tbAccount.getStrMoneyType(),
				tbAccount.getIntIsSonUse(),
				tbAccount.getIntPayTypeId(),
				tbAccount.getDteStartTimeToNoPay(),
				tbAccount.getIntAccountState(),
				tbAccount.getDbeNeedMessage(),
				tbAccount.getIntAcceptDays(),
				tbAccount.getDbeAcceptMaxMoney(),
				tbAccount.getId()
			} ;
		super.jdbcTemplate.update(sql, objs);
		return  tbAccount;
	}

	/*
	 * 查询所有帐户信息
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbAccountDao#getAllObjectList()
	 */
	public List<TbAccount> getAllObjectList() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tbAccount" ;
		return super.query(sql);
	}

	/*
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbAccountDao#getAllObjectListByCondition(java.lang.Object)
	 */
	public TbAccount getAccountByPartmentId(Integer partmentId){
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tbAccount WHERE intPartmentId="+ partmentId ;
		return (TbAccount)super.queryByObj(sql);
	}

	/*
	 * 按ID查询帐户信息
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbAccountDao#getObjectInfoById(java.lang.Integer)
	 */
	public TbAccount getObjectInfoById(Integer id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tbAccount WHERE id=" + id ;
		return (TbAccount)super.queryByObj(sql);
	}
	
	/*
	 * 按ID删除帐户信息
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbAccountDao#delete(java.lang.Integer)
	 */
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM tbAccount WHERE id=" + id ;
		return super.update(sql);
	}

	/*
	 * 根据部门ID删除帐户ID
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbAccountDao#deleteByPartmentId(java.lang.Integer)
	 */
	public int deleteByPartmentId(Integer partmentId) {
		String sql = "DELETE FROM tbAccount WHERE intPartmentId=" + partmentId ;
		return super.update(sql) ;
	}

}
