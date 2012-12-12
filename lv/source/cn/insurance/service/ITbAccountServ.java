package cn.insurance.service;

import java.util.List;

import cn.insurance.model.PageBean;
import cn.insurance.model.TbAccount;
import cn.insurance.model.TbBill;
import cn.insurance.model.TbMonthPayInfo;
import cn.insurance.model.TbPayOutInfo;
import cn.insurance.model.TbYearPayInfo;

public interface ITbAccountServ {
	
	public TbAccount getAccountById(Integer id) ;
	
	
	/**
	 * 根据部门Id查询帐户
	 * @param intPartmentId
	 * @return
	 */
	public TbAccount getAccountByPartmentId(Integer intPartmentId) ;

	
	/**
	 * 更新帐户
	 * @param tbAccount
	 * @return
	 */
	public boolean updateAccount(TbAccount tbAccount) ;
	
	
	/**
	 * 更新帐户设置
	 * @param tbAccount
	 */
	public void updateAccountProperty(TbAccount tbAccount) ;
	
	
	
	/**
	 * 旅行社申请退保，将旅行社在这个保单上的钱退回去
	 * 旅行社退钱已扣钱记录为准(记录的数额和帐户ID )
	 * @param bill
	 * @param tbReturnBill
	 */
	public void returnAccount(TbBill bill) ;
	
	
	/**
	 * 查询某个保单的所有支出记录
	 * @param billId
	 * @return
	 */
	public List<TbPayOutInfo> getAllPayOutInfoByBillId(Integer billId) ;

	

	/**
	 * 查询某个帐户的预存记录信息信息
	 * @param pageBean
	 * @param accountId
	 * @return
	 */
	public PageBean getYuCunPayLogByAccountId(PageBean pageBean , Integer accountId , Integer payTypeId) ;
	
	
	/**
	 * 查询某个帐户年费包干记录信息
	 * @param bean
	 * @param accountId
	 * @return
	 */
	public PageBean getYearPayLogByAccountId(PageBean bean , Integer accountId) ;

	
	/**
	 * 根据ID查询年费记录
	 * @param yearPayInfoId
	 * @return
	 */
	public TbYearPayInfo getYearPayInfoById(Integer yearPayInfoId) ;
	
	/**
	 * 根据ID查询预存费记录
	 * @param payOutInfoId
	 * @return
	 */
	public TbPayOutInfo getPayOutInfoById(Integer payOutInfoId) ;
	
	
	
	
	
	
	
	/**
	 * 预存帐户收费
	 */
	public void  shouFeeForYuCun(TbPayOutInfo tbPayOutInfo) ;

	
	/**
	 * 年费用户交费
	 */
	public void shouYearFee(TbYearPayInfo tbYearPayInfo) ;

}	
