package cn.insurance.dao;

import java.util.List;

import cn.insurance.model.TbAccidentalFee;

/**
 * 责任险20万
 * @author 
 * 2012-12-3
 */
public interface ITbAccidentalFeeDaoT{
	
	/**
	 * 添加
	 * @param obj
	 * @return
	 */
	public int create(TbAccidentalFee tbAccidentalFee);
	
	/**
	 * 更新
	 * @param obj
	 * @return
	 */
	public int update(TbAccidentalFee tbAccidentalFee) ;
	
	
	/**
	 * 更新通用的意外险价格
	 * 所有旅行社一起更新
	 * @param tbAccidentalFee
	 * @return
	 */
	public int updateNormalAccidentalFee(TbAccidentalFee tbAccidentalFee) ;
	
	/**
	 * 根据旅行社ID查询该旅行社的意外险费用
	 * @param PartmentId
	 * @return
	 */
	public TbAccidentalFee getAccidentFeeByPartmentId(Integer companyId);
	
	
	/**
	 * 根据ID查找对象
	 * @param id
	 * @return 查询集合
	 */
	public TbAccidentalFee getObjectInfoById(Integer id);

	/**
	 * 按旅行社ID删除意外险费率TbAccidentalFeeDao.java
	 * @param partmentId
	 * @return
	 */
	public int deleteByPartmentId(Integer partmentId);
}
