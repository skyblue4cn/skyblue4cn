package cn.insurance.service.admin;

import java.util.List;

import cn.insurance.model.PageBean;
import cn.insurance.model.TbMonthPayInfo;
import cn.insurance.model.TbMonthPayOutLog;

public interface IAdminMonthFeeServ {
	

	/**
	 * 按ID查询月费记录(包含保单清单)
	 * @param id
	 * @return
	 */
	public TbMonthPayInfo getMonthPayInfoById(Integer id) ;
	
	
	/**
	 * 根据旅行社ID查询该旅行社所有部门的月费统计
	 * @return
	 */
	public List<TbMonthPayInfo> getPartmentMonthStatByCompanyId(Integer companyId , String year , String month);
	
	/**
	 * 查询部门的月费统计
	 * @param partmentId
	 * @return
	 */
	public TbMonthPayInfo getPartmentMonthStatByPartmentId(Integer partmentId, String year , String month) ;
	
	/**
	 * 根据accountId查询该帐户下的所有月费结算记录
	 * @param pageBean
	 * @param accountId
	 * @return
	 */
	public PageBean getMonthPayByAccountId(PageBean pageBean , Integer accountId) ;
	
	/**
	 * 对月费进行结算
	 * 由于保单在修改的过程中月费可能会变化，
	 * 所以在每月的要收费之前系统会自动对月费进行一次清算
	 * 看是否保单中的费用与月费匹配
	 * 如果不匹配，则修改月费的应交费用
	 * @param year
	 * @param month
	 */
	public void jieSuan(String year, String month) ;
	
	
	/**
	 * 查询该年月所有未交费的预存账户月费
	 * @param year
	 * @param month
	 * @return
	 */
	public List<TbMonthPayInfo> getAllNotPayYuCunMonthFee(String year, String month) ;

	/**
	 * 收费：
	 * 月费和预存账户扣费都是使用这个方法收费
	 * 返回结果0 表示之前已收过费了 不能重复收费
	 * 返回结果1 表示收费成功
	 * 由于将月费结算和预存都是放在月费中，所以这里所有收费都是收取月费
	 * 1.对于月结算收费，是实际中保险公司人员去旅行社收费后，回来确认收费，所以账户余额保持不变
	 * 2.对于预存账户收费，是由系统在每月21凌晨3点进行自动收费，从账户扣除，并记录
	 * 以前的预存账户每张单子都收费不好是因为收费后要改保单就要退回会加收，这样会增加系统的复杂性
	 * @param monthPayId
	 * @param payType
	 * @param tbMonthPayOutLog
	 * 应该包含收费人的信息 包括收费人ID 姓名和 备注等信息  
	 * @return
	 */
	public int shouFei(int monthPayId ,int payType , TbMonthPayOutLog tbMonthPayOutLog) ;
	
	/**
	 * 月结帐户收费
	 * 返回结果0 表示之前已收过费了 不能重复收费
	 * 返回结果1 表示数据错误（有可能是钱不够，也可能是其他原因）
	 * 返回结果2 表示成功！
	 * @param tbMonthPayInfo
	 * @return
	 */
//	public int shouMonthFee(TbMonthPayOutLog tbMonthPayOutLog) ;
	
	/**
	 * 根据月费ID list 查询所有的月费记录
	 * 为了集中收费
	 * @param monthPayInfoIdList
	 * @return
	 */
	public List<TbMonthPayInfo> getPartmentFeeByIdList(int[] monthPayInfoIdList) ;
	
	/**
	 * 对多个部门一起收费
	 * @param monthPayInfoId
	 * @param tbMonthPayInfo
	 * @return
	 */
	public int showPartmentFeeByIdList(int[] monthPayInfoId ,TbMonthPayOutLog tbMonthPayOutLog) ;
	
	
	
	
	/**
	 * 检测用户以前的月费记录是否交费
	 * 用来抑制没有交费的用户继续投保
	 * @param tbUser
	 * @return
	 */
//	public boolean checkTheUserPartmentMonthFee(int accountId) ;
}
