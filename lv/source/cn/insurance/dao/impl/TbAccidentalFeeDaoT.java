package cn.insurance.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import cn.insurance.dao.BaseDao;
import cn.insurance.dao.ITbAccidentalFeeDaoT;
import cn.insurance.model.TbAccidentalFee;

public class TbAccidentalFeeDaoT extends BaseDao implements ITbAccidentalFeeDaoT {

	/**
	 * 返回一个对象结果集
	 */
	@Override
	protected Object mapObj(ResultSet rs) throws SQLException {
		TbAccidentalFee tbAccidentalFee = new TbAccidentalFee() ;
		/*ID为1表示通用的费率*/
		tbAccidentalFee.setId(rs.getInt("id")) ;
		/*通用价格intpartmentId没有值，rs.getInt会自动赋值为0，所以这里将值赋值为null*/
		if(tbAccidentalFee.getId() != 1){
			tbAccidentalFee.setIntPartmentId(rs.getInt("intPartmentId")) ;
		}
		/*国内旅游*/
		tbAccidentalFee.setDbeOneDayFee(rs.getDouble("dbeOneDayFee")) ;
		tbAccidentalFee.setDbeTwoDayFee(rs.getDouble("dbeTwoDayFee")) ;
		tbAccidentalFee.setDbeThreeDayFee(rs.getDouble("dbeThreeDayFee")) ;
		tbAccidentalFee.setDbeFourDayFee(rs.getDouble("dbeFourDayFee")) ;
		tbAccidentalFee.setDbeFiveDayFee(rs.getDouble("dbeFiveDayFee"));
		tbAccidentalFee.setDbeSixDayFee(rs.getDouble("dbeSixDayFee")) ;
		tbAccidentalFee.setDbeSevenDayFee(rs.getDouble("dbeSevenDayFee")) ;
		tbAccidentalFee.setDbeEightDayFee(rs.getDouble("dbeEightDayFee")) ;
		tbAccidentalFee.setDbeNineDayFee(rs.getDouble("dbeNineDayFee")) ;
		tbAccidentalFee.setDbeTenDayFee(rs.getDouble("dbeTenDayFee"));
		tbAccidentalFee.setDbeElevenDayFee(rs.getDouble("dbeElevenDayFee"));
		tbAccidentalFee.setDbeTwelveDayFee(rs.getDouble("dbeTwelveDayFee"));
		tbAccidentalFee.setDbeAboveTwelveDayFee(rs.getDouble("dbeAboveTwelveDayFee"));
		/*出入境旅游*/
		tbAccidentalFee.setDbeThirtyDayFee(rs.getDouble("dbeThirtyDayFee")) ;
		tbAccidentalFee.setDbeAboveThirtyDayFee(rs.getDouble("dbeAboveThirtyDayFee")) ;
		/*其他特别旅游项目*/
		tbAccidentalFee.setDbeFreeManFeeRate(rs.getDouble("dbeFreeManFeeRate")) ;
		tbAccidentalFee.setDbeSpecialItemFeeRate(rs.getDouble("dbeSpecialItemFeeRate"));
		tbAccidentalFee.setDbeMoneyTypeID(rs.getInt("dbeMoneyTypeID"));
		return tbAccidentalFee;
	}

	/*
	 * 对某个部门设定特定的价格时使用添加
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbAccidentalFeeDao#create(cn.insurance.model.TbAccidentalFee)
	 */
	public int create(TbAccidentalFee tbAccidentalFee ) {
		String sql = "INSERT into tbAccidentalFee(intPartmentId,dbeOneDayFee,dbeTwoDayFee,dbeThreeDayFee,dbeFourDayFee,dbeFiveDayFee,dbeSixDayFee,dbeSevenDayFee,dbeEightDayFee,dbeNineDayFee,dbeTenDayFee,dbeElevenDayFee,dbeTwelveDayFee,dbeAboveTwelveDayFee,dbeThirtyDayFee,dbeAboveThirtyDayFee,dbeFreeManFeeRate,dbeSpecialItemFeeRate,20) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] objs = {
				tbAccidentalFee.getIntPartmentId(),
				tbAccidentalFee.getDbeOneDayFee(),
				tbAccidentalFee.getDbeTwoDayFee(),
				tbAccidentalFee.getDbeThreeDayFee(),
				tbAccidentalFee.getDbeFourDayFee(),
				tbAccidentalFee.getDbeFiveDayFee(),
				tbAccidentalFee.getDbeSixDayFee(),
				tbAccidentalFee.getDbeSevenDayFee(),
				tbAccidentalFee.getDbeEightDayFee(),
				tbAccidentalFee.getDbeNineDayFee(),
				tbAccidentalFee.getDbeTenDayFee(),
				tbAccidentalFee.getDbeElevenDayFee(),
				tbAccidentalFee.getDbeTwelveDayFee(),
				tbAccidentalFee.getDbeAboveTwelveDayFee(),
				/*出入境旅游*/
				tbAccidentalFee.getDbeThirtyDayFee(),
				tbAccidentalFee.getDbeAboveThirtyDayFee(),
				/*其他特别旅游项目*/
				tbAccidentalFee.getDbeFreeManFeeRate() ,
				tbAccidentalFee.getDbeSpecialItemFeeRate(),
				tbAccidentalFee.getDbeMoneyTypeID()
			} ;

		return super.jdbcTemplate.update(sql, objs);
	}
	
	/*
	 * 更新意外险费用
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbAccidentalFeeDao#update(cn.insurance.model.TbAccidentalFee)
	 */
	public int update(TbAccidentalFee tbAccidentalFee ) {
		// TODO Auto-generated method stub
		/*这里没有更新intPartmentId这个字段是因为这个数值和ID是对应的，不会改变，而通用价格更新没有IntPartmentId,所以除去更新这个字段*/
		String sql = "UPDATE tbAccidentalFee SET dbeOneDayFee=?,dbeTwoDayFee=?,dbeThreeDayFee=?,dbeFourDayFee=?,dbeFiveDayFee=?,dbeSixDayFee=?,dbeSevenDayFee=?,dbeEightDayFee=?,dbeNineDayFee=?,dbeTenDayFee=?,dbeElevenDayFee=?,dbeTwelveDayFee=?,dbeAboveTwelveDayFee=?,dbeThirtyDayFee=?,dbeAboveThirtyDayFee=?,dbeFreeManFeeRate=?,dbeSpecialItemFeeRate=?,dbeMoneyTypeID=20 WHERE id=?";
		Object[] objs = {
				tbAccidentalFee.getDbeOneDayFee(),
				tbAccidentalFee.getDbeTwoDayFee(),
				tbAccidentalFee.getDbeThreeDayFee(),
				tbAccidentalFee.getDbeFourDayFee(),
				tbAccidentalFee.getDbeFiveDayFee(),
				tbAccidentalFee.getDbeSixDayFee(),
				tbAccidentalFee.getDbeSevenDayFee(),
				tbAccidentalFee.getDbeEightDayFee(),
				tbAccidentalFee.getDbeNineDayFee(),
				tbAccidentalFee.getDbeTenDayFee(),
				tbAccidentalFee.getDbeElevenDayFee(),
				tbAccidentalFee.getDbeTwelveDayFee(),
				tbAccidentalFee.getDbeAboveTwelveDayFee(),
				/*出入境旅游*/
				tbAccidentalFee.getDbeThirtyDayFee(),
				tbAccidentalFee.getDbeAboveThirtyDayFee(),
				/*其他特别旅游项目*/
				tbAccidentalFee.getDbeFreeManFeeRate() ,
				tbAccidentalFee.getDbeSpecialItemFeeRate(),
				tbAccidentalFee.getDbeMoneyTypeID(),
				tbAccidentalFee.getId()
			} ;
		return super.jdbcTemplate.update(sql, objs);
	}
	
	/**
	 * 更新
	 */
	public int updateNormalAccidentalFee(TbAccidentalFee tbAccidentalFee) {
		String sql = "UPDATE tbAccidentalFee SET dbeOneDayFee=?,dbeTwoDayFee=?,dbeThreeDayFee=?,dbeFourDayFee=?,dbeFiveDayFee=?,dbeSixDayFee=?,dbeSevenDayFee=?,dbeEightDayFee=?,dbeNineDayFee=?,dbeTenDayFee=?,dbeElevenDayFee=?,dbeTwelveDayFee=?,dbeAboveTwelveDayFee=?,dbeThirtyDayFee=?,dbeAboveThirtyDayFee=?,dbeFreeManFeeRate=?,dbeSpecialItemFeeRate=?,dbeMoneyTypeID=20 WHERE id=?";
		Object[] objs = {
				tbAccidentalFee.getDbeOneDayFee(),
				tbAccidentalFee.getDbeTwoDayFee(),
				tbAccidentalFee.getDbeThreeDayFee(),
				tbAccidentalFee.getDbeFourDayFee(),
				tbAccidentalFee.getDbeFiveDayFee(),
				tbAccidentalFee.getDbeSixDayFee(),
				tbAccidentalFee.getDbeSevenDayFee(),
				tbAccidentalFee.getDbeEightDayFee(),
				tbAccidentalFee.getDbeNineDayFee(),
				tbAccidentalFee.getDbeTenDayFee(),
				tbAccidentalFee.getDbeElevenDayFee(),
				tbAccidentalFee.getDbeTwelveDayFee(),
				tbAccidentalFee.getDbeAboveTwelveDayFee(),
				/*出入境旅游*/
				tbAccidentalFee.getDbeThirtyDayFee(),
				tbAccidentalFee.getDbeAboveThirtyDayFee(),
				/*其他特别旅游项目*/
				tbAccidentalFee.getDbeFreeManFeeRate() ,
				tbAccidentalFee.getDbeSpecialItemFeeRate(),
				tbAccidentalFee.getDbeMoneyTypeID(),
				tbAccidentalFee.getId()
			} ;
		return super.jdbcTemplate.update(sql, objs);
		
	}
	
	/**
	 * 根据旅行社ID查询该旅行社意外险价格
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbAccidentalFeeDao#getAccidentFeeByPartmentId(java.lang.Integer)
	 */
	public TbAccidentalFee getAccidentFeeByPartmentId(Integer partmentId) {
		String sql = "SELECT * FROM tbAccidentalFee WHERE intPartmentId="  + partmentId+" AND dbeMoneyTypeID=20";
		return (TbAccidentalFee)super.queryByObj(sql);
	}
	
	/**
	 * 根据ID查询该意外险价格
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbAccidentalFeeDao#getObjectInfoById(java.lang.Integer)
	 */
	public TbAccidentalFee getObjectInfoById(Integer id) {
		String sql = "SELECT * FROM tbAccidentalFee WHERE id=" + id+" AND dbeMoneyTypeID=20";
		return (TbAccidentalFee)super.query(sql);
	}

	/**
	 * 按旅行社ID删除意外险费率
	 * (non-Javadoc)
	 * @see cn.insurance.dao.ITbAccidentalFeeDao#deleteByPartmentId(java.lang.Integer)
	 */
	public int deleteByPartmentId(Integer partmentId){
		String sql = "DELETE FROM tbAccidentalFee intPartmentId="+partmentId+" AND dbeMondyTypeID=20";
		return  super.update(sql);
	}
}
