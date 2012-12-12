package cn.insurance.dao;

import java.util.List;

import cn.insurance.model.PageBean;
import cn.insurance.model.TbBill;
import cn.insurance.model.tongji.PartmentDayStat;
import cn.insurance.model.tongji.PartmentMonthStat;
import cn.insurance.model.tongji.PartmentTimeStat;

public interface ITbBillDao {
	
	/**
	 * 检查是否存在相同的保单
	 * 同一个部门  同一个时间  同一个团队号
	 * 三者中只要满足两个条件，则为存在相同的保单
	 * @param tbBill
	 * @return
	 */
	public int checkBillIsExist(TbBill tbBill) ;
	
	
	/**
	 * 添加新的保单
	 * @param obj
	 * @return
	 */
	public int create(TbBill tbBill) ;
	
	/**
	 * 查询部门投保的最后一个保单
	 * 用此保单的最后几位数字+1来最为新的保单的编号
	 * @param partmentId
	 * @return
	 */
	public TbBill getThePartmentMaxIdBill(int partmentId) ;
	
	/**
	 * 更新保单
	 * 更新所有的信息
	 * @param obj
	 * @return
	 */
	public int update(TbBill tbBill) ;
	
	
	/**
	 * 保险公司对保单进行更新(只更新保单的信息，其他字段不更新)
	 * @param tbBill
	 * @return
	 */
	public int updateBillByBx(TbBill tbBill) ;
	
	
	/**
	 * 根据ID查找对象
	 * @param id
	 * @return
	 */
	public TbBill getObjectInfoById(Integer id) ;
	
	/**
	 * 
	 * 根据保单号查询保单
	 * @param billNumber
	 * @return
	 */
	public TbBill getBillByNumber(String billNumber) ;
	
	
	
	
	/**
	 * 根据ID删除
	 * @param id
	 * @return
	 */
	public int delete(Integer id) ;

	
	/**
	 * 根据id数组查询所有的保单
	 * @param ids
	 * @return
	 */
	public List<TbBill> getBillListByIdList(int[] ids) ;


	
	/**
	 * 查询部门的已确认保单
	 * @param pageBean
	 * @param partmentId
	 * @return
	 */
	public PageBean getEffectedBillByPartmentId(PageBean pageBean ,Integer partmentId)  ;
	
	/**
	 * 查询部门的待审核保单
	 * @param pageBean
	 * @param partmentID
	 * @return
	 */
	public PageBean getReferedBillByPartmentId(PageBean pageBean , Integer partmentID) ;
	
	/**
	 * 查询部门的已备案保单
	 * @param pageBean
	 * @param partmentId
	 * @return
	 */
	public PageBean getBeiAnBillByPartmentId(PageBean pageBean , Integer partmentId) ;
	
	
	/**
	 * 查询部门的已退回保单
	 * @param pageBean
	 * @param partmentId
	 * @return
	 */
	public PageBean getReturnBillByPartmentId(PageBean pageBean , Integer partmentId) ;
	
	
	/**
	 * 总社查询需要总社审核的新保单
	 * @param pageBean
	 * @param companyId
	 * @return
	 */
	public PageBean getBillForCompanyToSure(PageBean pageBean , Integer companyId)  ;
	
	/**
	 * 总社查询总社已确认的保单
	 * @param bean
	 * @param companyId
	 * @return
	 */
	public PageBean getBillForCompanyHasSure(PageBean bean , Integer companyId) ;
	
	/**
	 * 查询总社退回的保单
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
	 * 查询保险公司已备案的保单
	 * @param pageBean
	 * @return
	 */
	public PageBean getBillForBxBeiAn(PageBean pageBean) ;
	
	/**
	 * 查询保险公司已确认的保单
	 * @param pageBean
	 * @return
	 */
	public PageBean getBillForBxHasSure(PageBean pageBean) ;
	
	/**
	 * 查询保险公司已退回的保单
	 * @param pageBean
	 * @return
	 */
	public PageBean getBillForBxHasReturn(PageBean pageBean) ;
	
	/**
	 * 部门综合各种条件查询保单
	 * @param pageBean
	 * @param tbBill
	 * @param state
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public PageBean getBillByComditionForUser(PageBean pageBean , TbBill tbBill , int[] state , String startTime , String endTime) ;
	
	
	public PageBean getBillByComditionForUser_2010(PageBean pageBean ,String strTravelerName,String strIndentyNumber, TbBill tbBill , int[] state , String startTime , String endTime) ;
	/**
	 * 保险公司综合各种条件查询保单
	 * @param pageBean
	 * @param tbBill
	 * @param companyId
	 * @param partmentId
	 * @param state
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public PageBean getBillByComditionForBx(PageBean pageBean , TbBill tbBill ,Integer companyId, Integer partmentId,int[] state,String startTime ,String endTime) ;
	
	
	
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
	 * 定时清理作废的保单和将没有更新的备案保单退回
	 * @return
	 */
	public int updateBillAtTheTimeByQuartz() ;
	
	
	/**
	 * 根据部门和年月信息算出该部门的所有保单月份统计
	 * @param pm
	 * @return
	 */
	public PartmentMonthStat getPartmentMonthStatByYearAndMonth(PartmentMonthStat pm) ; 
	
	
	/**
	 * 根据年月按天统计每天的保单
	 * @param psd
	 * @return
	 */
	public PartmentDayStat getPartmentDayStatByDay(PartmentDayStat psd) ;
	
	
	/**
	 * 按时间段查询部门统计
	 * @param pts
	 * @return
	 */
	public PartmentTimeStat getPartmentTimeStatByTime(PartmentTimeStat pts);
	
	/**
	 * 查询所有的已赔款的保单
	 * @param pageBean
	 * @return
	 */
	public PageBean getAllHasPeiKuanBillList(PageBean pageBean) ;
	
	/**
	 * 通过旅行社总社的ID查询该旅行社总社及下属部门的所有已确认保单
	 * @param pageBean
	 * @param companyId
	 * @return
	 */
	public PageBean getAllPartmentEffectedBillByCompanyId(PageBean pageBean ,Integer companyId)  ;
	
	
	//public List getBillIdByTravelerInfo(String strName,String strNum);
	/** 修改于2010-6-8 该方法根据条件返回所有的LIST 不分页*/
	public PageBean getBillByComditionForBx_2010(PageBean pageBean ,String strIndentyNumber, String strTravelerName,TbBill tbBill ,Integer companyId, Integer partmentId,int[] state,String startTime ,String endTime);
	
}
