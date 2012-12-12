package cn.insurance.service.admin;

import java.io.File;
import java.util.List;

import cn.insurance.model.PageBean;
import cn.insurance.model.TbBill;
import cn.insurance.model.TbBillBackup;
import cn.insurance.model.TbTravelerInfo;

public interface IAdminBillServ {
	
	/**
	 * 保险公司审查保单通过，确认保单生效，收费
	 * @param tbBill
	 * @return
	 */
	public int sureBillByBxSuccess(TbBill tbBill ) ;
	
	/**
	 * 保险公司审查保单不合格，退回保单
	 * 1 表示保单之前已经被审查过了
	 * 2 表示操作成功，保单已退回
	 * @param tbBill
	 * @return
	 */
	public int returnBillByBx(TbBill tbBill ) ; 
	
	/**
	 * 将保单备案处理
	 * @return
	 */
	public int beiAnBillByBx(TbBill tbBill) ;
	
	
	/**
	 * 保险公司对保单进行更新
	 * @param tbBill
	 * @return
	 */
	public int updateBillByBx(TbBill tbBill,File file, String fileName,List<TbTravelerInfo> travelerList , TbBillBackup tbBillBackup) ;
	
	/**
	 * 保险公司退回之前确认过的保单
	 * @param tbBill
	 * @return
	 */
	public int returnSureBillByBx(TbBill tbBill) ;
	

	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public TbBill getTbBillById(Integer id) ;
	
	/**
	 * 根据保单号查询保单
	 */
	public TbBill getBillByNumber(String billNumber) ;
	
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
	 * 保险公司根据各种条件查询保单
	 * @param pageBean
	 * @param tbBill
	 * @param companyId
	 * @param partmentId
	 * @param state
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public PageBean getBillByComditionForBx(PageBean pageBean , TbBill tbBill ,Integer companyId, Integer partmentId,int[] state,String startTime ,String endTime);
	
	
	
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
	
	/** 2010-6-8 根据 游客姓名 或 身份证 获得 所有 TRAVELERINFO */
	public List getBillIdByTravelerInfo(String strName,String strNum);
	
	/** 2010-6-8 获得所有保单信息 不分页*/
	public PageBean getBillByComditionForBxNew(PageBean pageBean ,String strIndentyNumber, String strTravelerName,TbBill tbBill ,Integer companyId, Integer partmentId,int[] state,String startTime ,String endTime);
}
