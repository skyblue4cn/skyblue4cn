package cn.insurance.dao;

import java.util.List;

import cn.insurance.model.PageBean;
import cn.insurance.model.TbMonthPayInfo;
import cn.insurance.model.TbUser;

public interface ITbMonthPayInfoDao {
	
	

	/**
	 * 添加
	 * @param obj
	 * @return
	 */
	public int create(TbMonthPayInfo tbMonthPayInfo) ;
	
	/**
	 * 添加结算记录并返回相应的ID
	 * @param tbMonthPayInfo
	 * @return
	 */
	public Integer addMonthPayInfoReturnId(TbMonthPayInfo tbMonthPayInfo) ;

	
	/**
	 * 更新
	 * @param obj
	 * @return
	 */
	public TbMonthPayInfo update(TbMonthPayInfo tbMonthPayInfo) ;
	
	/**
	 * 根据年月来查询该月记录
	 * @param year
	 * @param month
	 * @return
	 */
	public List<TbMonthPayInfo> getMonthPayByYearAndMonth(String year , String month , Integer accountId) ;
	
	
	/**
	 * 根据ID查找对象
	 * @param id
	 * @return
	 */
	public TbMonthPayInfo getObjectInfoById(Integer id) ;
	
	/**
	 * 根据ID删除
	 * @param id
	 * @return
	 */
	public int delete(Integer id) ;
	
	/**
	 * 根据帐户ID查询该帐户所有月计算记录并分页
	 * @param pageBean
	 * @param accountId
	 * @return
	 */
	public PageBean getMonthPayByAccontId(PageBean pageBean , Integer accountId ) ;
	
	/**
	 * 
	 * 查询该年月所以未交费的月费
	 * @param year
	 * @param month
	 * @return
	 */
	public List<TbMonthPayInfo> getAllNotPayMonthFee(String year, String month) ;
	
	/**
	 * 查询该年月所有未交费的预存账户月费
	 * 用来对预存账户自动收费
	 * @param year
	 * @param month
	 * @return
	 */
	public List<TbMonthPayInfo> getAllNotPayYuCunMonthFee(String year, String month) ;
	
	
	/**
	 * 查询用户所在部门所有未交费
	 * @param tbUser
	 * @return
	 */
	public List<TbMonthPayInfo> getAllNotPayMonthLog(Integer accountId) ;
	
	/**
	 * 根据月费ID数组查询所有月费记录
	 * @param monthPayInfoIdList
	 * @return
	 */
	public List<TbMonthPayInfo> getPartmentFeeByIdList(int[] monthPayInfoIdList) ;
	
	/**
	 * 检查用户在规定时间之前的（1个月）是否有月费没有交费
	 * @param tbUser
	 * @return
	 */
	public boolean checkTheUserPartmentMonthFee(int accountId) ;
	
	

	public List<String> getMessage_2010(Integer partmentId);
	
	
}
