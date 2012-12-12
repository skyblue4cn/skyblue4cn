package cn.insurance.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cn.insurance.dao.BaseDao;
import cn.insurance.dao.ITbBillDao;
import cn.insurance.dao.ITbPayoutInfoDao;
import cn.insurance.dao.ITbUserDao;
import cn.insurance.model.PageBean;
import cn.insurance.model.TbPayOutInfo;
import cn.insurance.model.TbUser;

public class TbPayOutInfoDao extends BaseDao implements ITbPayoutInfoDao{
	
	private ITbUserDao tbUserDao ;
	
	private ITbBillDao tbBillDao ;
	
	@Override
	protected Object mapObj(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		TbPayOutInfo tbPayOutInfo = new TbPayOutInfo() ;
		tbPayOutInfo.setId(rs.getInt("id")) ;
		tbPayOutInfo.setIntAccountId(rs.getInt("intAccountId")) ;
		tbPayOutInfo.setIntType(rs.getInt("intType")) ;
		tbPayOutInfo.setDbePayoutNumber(rs.getDouble("dbePayoutNumber")) ;
		tbPayOutInfo.setDtePayoutTime(rs.getTimestamp("dtePayoutTime")) ;
		tbPayOutInfo.setStrDesc(rs.getString("strDesc")) ;
		tbPayOutInfo.setIntFromUserId(rs.getInt("intFromUserId")) ;
		tbPayOutInfo.setStrSaveUserName(rs.getString("strSaveUserName")) ;
		tbPayOutInfo.setDbeCurResidual(rs.getDouble("dbeCurResidual")) ;
		tbPayOutInfo.setDbeAftResidual(rs.getDouble("dbeAftResidual")) ;
		tbPayOutInfo.setFromUser(tbUserDao.getObjectInfoById(tbPayOutInfo.getIntFromUserId()));
		return tbPayOutInfo;
		
	}
	
	/*
	 * 添加预存费用支付记录
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbPayoutTypeDao#create(cn.insurance.model.TbPayOutInfo)
	 */
	public int create(TbPayOutInfo tbPayOutInfo) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO tbPayOutInfo(intAccountId,intType,dbePayoutNumber,dtePayoutTime,dbeCurResidual,dbeAftResidual,strDesc,intFromUserId,strSaveUserName) VALUES(?,?,?,?,?,?,?,?,?)" ; 
		Object[] objs = {
			tbPayOutInfo.getIntAccountId() ,
			tbPayOutInfo.getIntType() ,
			tbPayOutInfo.getDbePayoutNumber() ,
			tbPayOutInfo.getDtePayoutTime() ,
			tbPayOutInfo.getDbeCurResidual(),
			tbPayOutInfo.getDbeAftResidual(),
			tbPayOutInfo.getStrDesc() ,
			tbPayOutInfo.getIntFromUserId() ,
			tbPayOutInfo.getStrSaveUserName() ,
		} ;
		
		return super.jdbcTemplate.update(sql, objs);
	}

	/*
	 * 更新预存费用支付记录
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbPayoutTypeDao#update(cn.insurance.model.TbPayOutInfo)
	 */
	public TbPayOutInfo update(TbPayOutInfo tbPayOutInfo) {
		// TODO Auto-generated method stub
		String sql = "UPDATE tbPayOutInfo SET intAccountId=?,intType=?,dbePayoutNumber=?,dtePayoutTime=?,dbeCurResidual=?,dbeAftResidual=?,strDesc=?,intFromUserId=?,strSaveUserName=? WHERE id=?" ;
		Object[] objs = {
				tbPayOutInfo.getIntAccountId() ,
				tbPayOutInfo.getIntType() ,
				tbPayOutInfo.getDbePayoutNumber() ,
				tbPayOutInfo.getDtePayoutTime() ,
				tbPayOutInfo.getDbeCurResidual(),
				tbPayOutInfo.getDbeAftResidual(),
				tbPayOutInfo.getStrDesc() ,
				tbPayOutInfo.getIntFromUserId() ,
				tbPayOutInfo.getStrSaveUserName() ,
				tbPayOutInfo.getId()
			} ;
			
		super.jdbcTemplate.update(sql, objs);
		return tbPayOutInfo;
	}

	/*
	 * 查询所有预存费用支付记录
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbPayoutTypeDao#getAllObjectList()
	 */
	public List<TbPayOutInfo> getAllObjectList() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tbPayOutInfo" ;
		return super.query(sql);
	}

	/*
	 * 按ID查询预存费用支付记录
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbPayoutTypeDao#getObjectInfoById(java.lang.Integer)
	 */
	public TbPayOutInfo getObjectInfoById(Integer id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tbPayOutInfo WHERE id=" + id ;
		return (TbPayOutInfo)super.queryByObj(sql);
	}

	/*
	 * 按ID删除预存费用支付记录
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbPayoutTypeDao#delete(java.lang.Integer)
	 */
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM tbPayOutInfo WHERE id=" + id ;
		return super.update(sql);
	}
	
	/*
	 * 由于条件很多种类，为了统一，这里直接将条件都写到service里
	 * (non-Javadoc)
	 * @see cn.insurance.dao.IPageBeanDao#getPageBean(cn.insurance.model.PageBean, java.lang.Object)
	 */
	public PageBean getPageBean(PageBean pageBean, Object obj) {
		// TODO Auto-generated method stub 
		if(obj instanceof String){
			String sql = (String) obj ;
			sql = sql + " ORDER BY id DESC	" ;
			return super.getPageBean(pageBean, sql, "tbPayOutInfo");
		}
		return pageBean ;
	}
	
	/**
	 * 查询某个帐户的所有预存费用的存款信息
	 */
	public PageBean getYuCunPayLogByAccountId(PageBean pageBean , Integer accountId ,Integer payTypeId){
		StringBuffer sql = new StringBuffer() ;
		sql.append(" AND intAccountId=").append(accountId) ;
		//inttype=1 表示存钱类型
		sql.append(" AND intType=").append(payTypeId) ;
		sql.append(" ORDER BY id DESC") ;
		return super.getPageBean(pageBean, sql.toString(), "tbPayOutInfo") ;
	}

	public ITbUserDao getTbUserDao() {
		return tbUserDao;
	}

	public void setTbUserDao(ITbUserDao tbUserDao) {
		this.tbUserDao = tbUserDao;
	}

	public ITbBillDao getTbBillDao() {
		return tbBillDao;
	}

	public void setTbBillDao(ITbBillDao tbBillDao) {
		this.tbBillDao = tbBillDao;
	}
	

}
