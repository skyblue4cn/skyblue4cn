package cn.insurance.service.client;

import java.util.List;

import cn.insurance.model.PageBean;
import cn.insurance.model.TbMonthPayInfo;

/**
 * 客户端对月费的管理
 * @author yqg
 *
 */
public interface IClientMonthFeeServ {
	
	
	/**
	 * 按ID查询月费记录(包含保单清单)
	 * @param id
	 * @return
	 */
	public TbMonthPayInfo getMonthPayInfoById(Integer id) ;
	
	/**
	 * 根据accountId查询该帐户下的所有月费结算记录
	 * @param pageBean
	 * @param accountId
	 * @return
	 */
	public PageBean getMonthPayByAccountId(PageBean pageBean , Integer accountId) ;
	
	/**
	 * 检查帐户的欠费收费超过了预定的期限
	 * 如果超过了，不能投保，返回false
	 * 如果没有超过，则可以投保，返回true
	 * @param accountId
	 * @return
	 */
	public boolean checkTheUserPartmentMonthFee(int accountId) ;
	
	/**
	 * 根据旅行社ID查询该旅行社总社及部门的所有月费
	 * @param year
	 * @param month
	 * @param companyId
	 * @return
	 */
	public List<TbMonthPayInfo> getPartmentMonthPayInfoByCompanyId(int year ,int month , Integer companyId) ;

	
	/**
	 * 根据旅行社ID查询该旅行社总社及部门的所有月费
	 * @param year
	 * @param month
	 * @param companyId
	 * @return
	 */
	public List<TbMonthPayInfo> getPartmentMonthPayInfoByPartmentId(int year ,int month , Integer partmentId) ;
	
}
