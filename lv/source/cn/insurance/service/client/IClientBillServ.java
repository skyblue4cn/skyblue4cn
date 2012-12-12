package cn.insurance.service.client;

import java.io.File;
import java.util.List;

import cn.insurance.model.PageBean;
import cn.insurance.model.TbBill;
import cn.insurance.model.TbTravelerInfo;

/**
 * 客户段对保单的处理
 * @author yqg
 *
 */
public interface IClientBillServ {
	
	/**
	 * 添加新的保单
	 * @param tbBill
	 * @return
	 * @throws Exception
	 */
	public TbBill addNewBill(TbBill tbBill) throws Exception ;
	
	/**
	 * 设定一个保单的价格
	 * @param tbBill
	 * @return
	 */
	public TbBill getBillPrice(TbBill tbBill) ;
	
	/**
	 * 按ID查询保单信息
	 * @param id
	 * @return
	 */
	public TbBill getTbBillById(Integer id);
	
	/**
	 * 按保单号查询保单
	 * @param billNumber
	 * @return
	 */
	public TbBill getBillByNumber(String billNumber);
	
	
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
	 * 正式提交保单
	 * 0 表示该保单之前已经提交过了，不能再次提交
	 * 1 表示该保单提交成功
	 * @param tbBill
	 * @return
	 */
	public int sureApplyBill(TbBill tbBill , File file , String fileName , List<TbTravelerInfo> travelerList) ;
	
	/**
	 * 更新保单信息
	 * @param tbBill
	 * @return
	 */
	public int updateBillByUser(TbBill tbBill,File file ,String fileName ,List<TbTravelerInfo> travelerList) ;
	
	/**
	 * 旅行社更新备案保单的信息
	 * @param tbBill
	 * @return
	 */
	public int updateBeiAnBill(TbBill tbBill ,File travelerFile ,String travelerFileFileName ,List<TbTravelerInfo> tbTravelerInfoList) ;
	

	/**
	 * 删除保单
	 * 将会删除跟保单相关联的数据（游客信息）
	 * @param id
	 */
	public void deleteBillById(Integer id) ;
	
	
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
	public PageBean getBillByComditionForUser(PageBean pageBean,TbBill tbBill ,int[] state , String startTime , String endTime) ;

	
	public PageBean getBillByComditionForUser_2010(PageBean pageBean ,String strTravelerName,String strIndentyNumber ,TbBill tbBill ,int[] state , String startTime , String endTime) ;

	/**
	 * 通过旅行社总社的ID查询该旅行社总社及下属部门的所有已确认保单
	 * @param pageBean
	 * @param companyId
	 * @return
	 */
	public PageBean getAllPartmentEffectedBillByCompanyId(PageBean pageBean ,Integer companyId)  ;
	
}
