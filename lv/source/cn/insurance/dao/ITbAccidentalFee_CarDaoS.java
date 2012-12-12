package cn.insurance.dao;

import cn.insurance.model.TbAccidentalFee;
/**
 * 自驾车30万
 * @author 
 * 2012-12-4
 */
public interface ITbAccidentalFee_CarDaoS{
	/**
	 * 添加
	 * @param obj
	 * @return
	 */
	public int create(TbAccidentalFee tbAccidentalFee) ;
	
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
	public TbAccidentalFee getAccidentFeeByPartmentId(Integer companyId) ;
	
	
	/**
	 * 根据ID查找对象
	 * @param id
	 * @return
	 */
	public TbAccidentalFee getObjectInfoById(Integer id) ;

	/**
	 * 按旅行社ID删除意外险费率
	 * @param partmentId
	 * @return
	 */
	public int deleteByPartmentId(Integer partmentId) ;
}
