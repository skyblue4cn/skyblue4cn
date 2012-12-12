package cn.insurance.dao;

import java.util.List;

import cn.insurance.model.PageBean;
import cn.insurance.model.TbPayOutInfo;

public interface ITbPayoutInfoDao {
	/**
	 * 添加
	 * @param obj
	 * @return
	 */
	public int create(TbPayOutInfo tbPayOutInfo) ;
	
	/**
	 * 更新
	 * @param obj
	 * @return
	 */
	public TbPayOutInfo update(TbPayOutInfo tbPayOutInfo) ;
	
	/**
	 * 查询全部
	 * @return
	 */
	public List<TbPayOutInfo> getAllObjectList() ;
	
	/**
	 * 根据ID查找对象
	 * @param id
	 * @return
	 */
	public TbPayOutInfo getObjectInfoById(Integer id) ;
	
	/**
	 * 根据ID删除
	 * @param id
	 * @return
	 */
	public int delete(Integer id) ;
	
	/**
	 * 查询帐户的所有预存的支付信息
	 * @param pageBean
	 * @param accountId
	 * @return
	 */
	public PageBean getYuCunPayLogByAccountId(PageBean pageBean , Integer accountId , Integer payTypeId) ;
}
