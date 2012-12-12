package cn.insurance.service.impl;


import cn.insurance.dao.ITbBillDao;
import cn.insurance.model.PageBean;
import cn.insurance.model.TbBill;
import cn.insurance.service.IBillQueryServ;
import cn.insurance.service.ITbPartmentServ;

public class BillQueryServ implements  IBillQueryServ {
	
	private ITbBillDao tbBillDao ;
	
	
	private ITbPartmentServ tbPartmentServ ;
	
	/*
	 * 根据保单ID查询保单
	 * (non-Javadoc)
	 * @see cn.insurance.service.IBillQueryServ#getTbBillById(java.lang.Integer)
	 */
	public TbBill getTbBillById(Integer id) {
		// TODO Auto-generated method stub
		return (TbBill)tbBillDao.getObjectInfoById(id);
	}
	
	/*
	 * 根据部门ID查询某个部门已审核的确认的保单
	 * (non-Javadoc)
	 * @see cn.insurance.service.IBillQueryServ#getBillByPartmentAndState(cn.insurance.model.TbBill)
	 */
	public PageBean getEffectedBillByPartmentId(PageBean pageBean ,Integer partmentId) {
		// TODO Auto-generated method stub
		return tbBillDao.getEffectedBillByPartmentId(pageBean, partmentId) ;
	}

	/*
	 * 查询该部门所有的待审核的保单
	 * 已提交的保单有2中情况
	 * 旅行社未审查的
	 * 保险公司未审查的
	 * (non-Javadoc)
	 * @see cn.insurance.service.IBillQueryServ#getReferedBillByPartmentId(cn.insurance.model.PageBean, cn.insurance.model.TbBill)
	 */
	public PageBean getReferedBillByPartmentId(PageBean pageBean , Integer partmentID){
		return tbBillDao.getReferedBillByPartmentId(pageBean, partmentID) ;
	}
	
	
	public PageBean getBeiAnBillByPartmentId(PageBean pageBean , Integer partmentId){
		return tbBillDao.getBeiAnBillByPartmentId(pageBean, partmentId) ;
	}
	
	
	/*
	 * 查询该部门所有被退回来的保单
	 * (non-Javadoc)
	 * @see cn.insurance.service.IBillQueryServ#getReturnBillByPartmentId(cn.insurance.model.PageBean, cn.insurance.model.TbBill)
	 */
	public PageBean getReturnBillByPartmentId(PageBean pageBean , Integer partmentId){
		return tbBillDao.getReturnBillByPartmentId(pageBean, partmentId) ;
	}
	
	
	/**总社**/
	
	/*
	 * 查询需要总社审核的保单给总社
	 * (non-Javadoc)
	 * @see cn.insurance.service.IBillQueryServ#getBillForCompanyToSure(cn.insurance.model.PageBean, java.lang.Integer)
	 */
	public PageBean getBillForCompanyToSure(PageBean pageBean , Integer companyId) {
		return tbBillDao.getBillForCompanyToSure(pageBean, companyId);
	}
	
	


	
	/*
	 * 查询旅行社已审核的保单
	 * (non-Javadoc)
	 * @see cn.insurance.service.IBillQueryServ#getBillForCompanyHasSure(cn.insurance.model.PageBean, java.lang.Integer)
	 */
	public PageBean getBillForCompanyHasSure(PageBean bean , Integer companyId){
		return tbBillDao.getBillForCompanyHasSure(bean, companyId) ;
	}
	
	/*
	 * 查询旅行社退回的保单
	 * (non-Javadoc)
	 * @see cn.insurance.service.IBillQueryServ#getBillForCompanyHasCancel(cn.insurance.model.PageBean, java.lang.Integer)
	 */
	public PageBean getBillForCompanyHasReturn(PageBean pageBean , Integer companyId){
		return tbBillDao.getBillForCompanyHasReturn(pageBean, companyId) ;
	}
	
	/*
	 * 查询需要保险公司审核的新保单
	 * (non-Javadoc)
	 * @see cn.insurance.service.IBillQueryServ#getBillsNeedBxSure(cn.insurance.model.PageBean)
	 */
	public PageBean getBillNeedBxSure(PageBean pageBean){
		return tbBillDao.getBillNeedBxSure(pageBean) ;
		
	}
	
	/*
	 * 查询已备案的保单
	 * (non-Javadoc)
	 * @see cn.insurance.service.IBillQueryServ#getBillForBxBeiAn(cn.insurance.model.PageBean)
	 */
	public PageBean getBillForBxBeiAn(PageBean pageBean){
		return tbBillDao.getBillForBxBeiAn(pageBean) ;
	}
	
	
	/*
	 * 查询保险公司已确认生效的保单
	 * (non-Javadoc)
	 * @see cn.insurance.service.IBillQueryServ#getBillForBxHasSure(cn.insurance.model.PageBean)
	 */
	public PageBean getBillForBxHasSure(PageBean pageBean){
		return tbBillDao.getBillForBxHasSure(pageBean) ;
	}
	
	/*
	 * 查询被保险公司退回的保单
	 * (non-Javadoc)
	 * @see cn.insurance.service.IBillQueryServ#getBillForBxHasReturn(cn.insurance.model.PageBean)
	 */
	public PageBean getBillForBxHasReturn(PageBean pageBean){
		return tbBillDao.getBillForBxHasReturn(pageBean) ;
	}


	
	
	/*
	 * 保险公司根据各种条件查询保单
	 * (non-Javadoc)
	 * @see cn.insurance.service.IBillQueryServ#getBillByCondition(cn.insurance.model.PageBean, cn.insurance.model.TbBill, cn.insurance.model.TbPartment, cn.insurance.model.TbReturnBill)
	 */
	public PageBean getBillByComditionForBx(PageBean pageBean , TbBill tbBill ,Integer companyId, Integer partmentId,int[] state,String startTime ,String endTime){
		return tbBillDao.getBillByComditionForBx(pageBean, tbBill, companyId, partmentId, state, startTime, endTime) ;
	}

	/*
	 * 部门根据条件查找保单
	 * (non-Javadoc)
	 * @see cn.insurance.service.IBillQueryServ#getBillByComditionForUser(cn.insurance.model.PageBean, cn.insurance.model.TbBill, java.lang.Integer, int[], java.lang.String, java.lang.String)
	 */
	public PageBean getBillByComditionForUser(PageBean pageBean , TbBill tbBill , int[] state , String startTime , String endTime){
		return tbBillDao.getBillByComditionForUser(pageBean, tbBill, state, startTime, endTime) ;
	}
	
	
	
	

	/*
	 * 统计有多少个新的保单需要确认
	 * 主要是为了给保险公司的业务人员提示信息(代办事务)
	 * (non-Javadoc)
	 * @see cn.insurance.service.IBillQueryServ#getNewBillNumberNeedSureByBx()
	 */
	public int getNewBillNumberNeedSureByBx(){
		return tbBillDao.getNewBillNumberNeedSureByBx() ;
	}
	
	/*
	 * 统计更新后的备案保单数
	 * 主要是为了给保险公司的业务人员提示信息（代办事务）
	 * (non-Javadoc)
	 * @see cn.insurance.service.IBillQueryServ#getNewBeiAnBillNumberNeedSureByBx()
	 */
	public int getNewBeiAnBillNumberNeedSureByBx(){
		return tbBillDao.getNewBeiAnBillNumberNeedSureByBx() ;
	}
	
	/*
	 * 查询所有已赔款的保单
	 * (non-Javadoc)
	 * @see cn.insurance.service.IBillQueryServ#getAllHasPeiKuanBillList(cn.insurance.model.PageBean)
	 */
	public PageBean getAllHasPeiKuanBillList(PageBean pageBean) {
		return tbBillDao.getAllHasPeiKuanBillList(pageBean) ;
	}
	
	/*setter and getter*/

	public ITbBillDao getTbBillDao() {
		return tbBillDao;
	}

	public void setTbBillDao(ITbBillDao tbBillDao) {
		this.tbBillDao = tbBillDao;
	}

	public ITbPartmentServ getTbPartmentServ() {
		return tbPartmentServ;
	}

	public void setTbPartmentServ(ITbPartmentServ tbPartmentServ) {
		this.tbPartmentServ = tbPartmentServ;
	}


}
