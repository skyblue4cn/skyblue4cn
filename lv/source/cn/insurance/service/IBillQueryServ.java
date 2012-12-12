package cn.insurance.service;


import cn.insurance.model.PageBean;
import cn.insurance.model.TbBill;



/** 这些方法暂时没有用*/

/**专门定义一个接口用来查询各种各样的保单*/
public interface IBillQueryServ {

	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public TbBill getTbBillById(Integer id) ;
	
	/**
	 * 查询某个部门已审核的保单
	 * @param tbBill
	 * @return
	 */
	public PageBean getEffectedBillByPartmentId(PageBean pageBean ,Integer partmentId) ;
	
	
	/**
	 * 查询该部门所有的待审核的保单
	 * 已提交的保单有2中情况
	 * 旅行社未审查的
	 * 保险公司未审查的
	 * @param pageBean
	 * @param tbBill
	 * @return
	 */
	public PageBean getReferedBillByPartmentId(PageBean pageBean , Integer partmentId) ;
	
	
	/**
	 * 根据部门ID查询备案的保单
	 * @param pageBean
	 * @param partmentId
	 * @return
	 */
	public PageBean getBeiAnBillByPartmentId(PageBean pageBean , Integer partmentId) ;
	
	/**
	 * 查询所有被退回的保单
	 * 被退回的保单有两种状态
	 * 总社退回来的 
	 * 分社退回来的
	 * @param pageBean
	 * @param tbBill
	 * @return
	 */
	public PageBean getReturnBillByPartmentId(PageBean pageBean , Integer partmentId) ;
	
	
	/**
	 * 根据各种条件查询保单
	 * @param pageBean
	 * @param tbBill
	 * @param tbReturnBill
	 * @return
	 */
	public PageBean getBillByComditionForBx(PageBean pageBean , TbBill tbBill ,Integer companyId, Integer partmentId,int[] state,String startTime ,String endTime) ;
	
	
	/**
	 * 旅行社根据条件查找保单
	 * tbbill里可以存 保单ID  保单的签发人
	 * @param pageBean
	 * @param tbBill
	 * @param partmentId
	 * @param state
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public PageBean getBillByComditionForUser(PageBean pageBean , TbBill tbBill ,int[] state , String startTime , String endTime) ;

	/**
	 * 某些旅行社总社需要审核分社的保单，查询出这些保单
	 * @param pageBean
	 * @param companyId
	 * @return
	 */
	public PageBean getBillForCompanyToSure(PageBean pageBean , Integer companyId) ;
	
	/**
	 * 
	 * 查询旅行社已审核的保单
	 * @param bean
	 * @param companyId
	 * @return
	 */
	public PageBean getBillForCompanyHasSure(PageBean bean , Integer companyId) ;
	
	/**
	 * 旅行社退回的保单
	 * @param pageBean
	 * @param companyId
	 * @return
	 */
	public PageBean getBillForCompanyHasReturn(PageBean pageBean , Integer companyId) ;
	
	
	/**
	 * 查询需要保险公司审核的新保单
	 * @param pageBean
	 * @return
	 */
	public PageBean getBillNeedBxSure(PageBean pageBean) ;
	
	/**
	 * 查询已备案的保单
	 * @return
	 */
	public PageBean getBillForBxBeiAn(PageBean pageBean) ;
	
	
	
	/**
	 * 查询保险公司已确认生效的保单
	 * @param pageBean
	 * @return
	 */
	public PageBean getBillForBxHasSure(PageBean pageBean) ;
	
	/**
	 * 查询旅行社退回的保单
	 * @param pageBean
	 * @return
	 */
	public PageBean getBillForBxHasReturn(PageBean pageBean) ;
	
	/**
	 * 统计有多少个新的保单需要确认
	 * @return
	 */
	public int getNewBillNumberNeedSureByBx() ;
	
	/**
	 * 统计更新后的备案保单数
	 * @return
	 */
	public int getNewBeiAnBillNumberNeedSureByBx() ;
	
	
	/**
	 * 查询所有的已赔款的保单
	 * @param pageBean
	 * @return
	 */
	public PageBean getAllHasPeiKuanBillList(PageBean pageBean) ;
	
}
