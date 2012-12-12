package cn.insurance.dao;

import java.util.List;

import cn.insurance.model.PageBean;
import cn.insurance.model.TbYearPayInfo;

public interface ITbYearPayInfoDao {

	/**
	 * 添加
	 * @param obj
	 * @return
	 */
	public int create(TbYearPayInfo tbYearPayInfo) ;
	
	/**
	 * 更新
	 * @param obj
	 * @return
	 */
	public TbYearPayInfo update(TbYearPayInfo tbYearPayInfo) ;
	
	/**
	 * 查询全部
	 * @return
	 */
	public List<TbYearPayInfo> getAllObjectList() ;
	
	/**
	 * 按条件查询,实现的时侯根据参数的类型来具体实现查询
	 * 特别说明：由于保单需要各种各样的查询，所以在tbBillDao里这个方法里查询传入的String，表示查询的sql语句的条件部分
	 * @param obj
	 * @return
	 */
	public List<TbYearPayInfo> getAllObjectListByCondition(Object obj) ;
	
	
	/**
	 * 根据ID查找对象
	 * @param id
	 * @return
	 */
	public TbYearPayInfo getObjectInfoById(Integer id) ;
	
	/**
	 * 根据ID删除
	 * @param id
	 * @return
	 */
	public int delete(Integer id) ;
	
	
	/**
	 * 查询所有帐户年费包干记录
	 */
	public PageBean getYearPayByAccountId(PageBean pageBean , Integer accountId) ;
}
