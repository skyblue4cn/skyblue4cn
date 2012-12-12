package cn.insurance.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cn.insurance.dao.BaseDao;
import cn.insurance.dao.ITbAdjustFeeDao;
import cn.insurance.model.TbAdjustFee;

public class TbAdjustFeeDao extends BaseDao implements ITbAdjustFeeDao {

	@Override
	protected Object mapObj(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		TbAdjustFee tbAdjustFee = new TbAdjustFee() ;
		tbAdjustFee.setId(rs.getInt("id")) ;
		if(tbAdjustFee.getId() != 1){
			tbAdjustFee.setIntPartmentId(rs.getInt("intPartmentId")) ;
		}
		tbAdjustFee.setDbeChinaStandard(rs.getDouble("dbeChinaStandard")) ;
		tbAdjustFee.setDbeOtherStandard(rs.getDouble("dbeOtherStandard")) ;
		return tbAdjustFee;
	}
	
	/*
	 * 添加
	 * (non-Javadoc)
	 * @see cn.insurance.dao.IDataDao#create(java.lang.Object)
	 */
	public int create(TbAdjustFee tbAdjustFee ) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO tbAdjustFee(intPartmentId,dbeChinaStandard,dbeOtherStandard) VALUES(?,?,?)" ;
		Object[] objs = {
				tbAdjustFee.getIntPartmentId(),
				tbAdjustFee.getDbeChinaStandard(),
				tbAdjustFee.getDbeOtherStandard()
		} ;
		return super.jdbcTemplate.update(sql, objs);
	}
	
	/*
	 * 更新调节费
	 * (non-Javadoc)
	 * @see cn.insurance.dao.IDataDao#update(java.lang.Object)
	 */
	public int update(TbAdjustFee tbAdjustFee) {
		// TODO Auto-generated method stub
		String sql = "UPDATE tbAdjustFee SET dbeChinaStandard=?,dbeOtherStandard=? WHERE id=?" ;
		Object[] objs = {
					tbAdjustFee.getDbeChinaStandard(),
					tbAdjustFee.getDbeOtherStandard(),
					tbAdjustFee.getId()
		} ;
		return super.jdbcTemplate.update(sql, objs);
	}
	
	/*
	 * 更新通用的调节费价格
	 * 所有旅行社一起更新
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbAdjustFeeDao#updateNormalAdjustFee(cn.insurance.model.TbAdjustFee)
	 */
	public int updateNormalAdjustFee(TbAdjustFee tbAdjustFee) {
		String sql = "UPDATE tbAdjustFee SET dbeChinaStandard=?,dbeOtherStandard=?" ;
		Object[] objs = {
					tbAdjustFee.getDbeChinaStandard(),
					tbAdjustFee.getDbeOtherStandard(),
		} ;
		return super.jdbcTemplate.update(sql, objs);
	
		
	}
	
	
	
	/*
	 * 根据旅行社ID查询该旅行社调节费
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbAdjustFeeDao#getAdjustFeeByPartmentId(java.lang.Integer)
	 */
	public TbAdjustFee getAdjustFeeByPartmentId(Integer partmentId){
		String sql = "SELECT * FROM tbAdjustFee WHERE intPartmentId=" + partmentId;
		return (TbAdjustFee)super.queryByObj(sql) ;
	}


	/*
	 * 根据ID查询
	 * (non-Javadoc)
	 * @see cn.insurance.dao.IDataDao#getObjectInfoById(java.lang.Integer)
	 */
	public TbAdjustFee getObjectInfoById(Integer id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tbAdjustFee WHERE id=" + id ;
		return (TbAdjustFee)super.queryByObj(sql);
	}
	
	/*
	 * 按旅行社ID删除调节费
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbAdjustFeeDao#deleteByPartmentId(java.lang.Integer)
	 */
	public int deleteByPartmentId(Integer partmentId){
		String sql = "DELETE FROM tbAdjustFee WHERE intPartmentId=" + partmentId;
		return super.update(sql) ;
	}
}
