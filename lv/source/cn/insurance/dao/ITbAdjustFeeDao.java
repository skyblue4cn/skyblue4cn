package cn.insurance.dao;


import cn.insurance.model.TbAdjustFee;

public interface ITbAdjustFeeDao {



		/**
		 * 添加
		 * @param obj
		 * @return
		 */
		public int create(TbAdjustFee tbAdjustFee) ;
		
		/**
		 * 更新
		 * @param obj
		 * @return
		 */
		public int update(TbAdjustFee tbAdjustFee) ;
		
		
		
		/**
		 * 更新通用的调节费价格
		 * 所有旅行社都更新
		 * @param tbAdjustFee
		 * @return
		 */
		public int updateNormalAdjustFee(TbAdjustFee tbAdjustFee) ;
		
		
		
		
		/**
		 * 根据旅行社ID得到该旅行社的调节费
		 * @param partmentId
		 * @return
		 */
		public TbAdjustFee getAdjustFeeByPartmentId(Integer companyId) ;
	
		/**
		 * 根据ID查找对象
		 * @param id
		 * @return
		 */
		public TbAdjustFee getObjectInfoById(Integer id) ;


		/**
		 * 根据旅行社ID删除调节费
		 * @param partmentId
		 * @return
		 */
		public int deleteByPartmentId(Integer partmentId) ;
}
