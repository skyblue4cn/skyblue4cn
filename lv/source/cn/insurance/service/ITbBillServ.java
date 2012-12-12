package cn.insurance.service;

import java.io.File;
import java.util.List;


import cn.insurance.model.TbBill;
import cn.insurance.model.TbBillBackup;
import cn.insurance.model.TbTravelerInfo;

public interface ITbBillServ {
	
	/**
	 * 预提交保单
	 * @param tbInsuranceBill
	 * @param tbTravelerInfos
	 * @throws Exception
	 */
	public TbBill addBill(TbBill  tbBill);
	
	/**
	 * 检查保单是否存在
	 * 同一个部门  同一个时间  同一个团队号
	 * 三者中只要满足两个条件，则为存在相同的保单
	 * @param tbBill
	 * @return
	 */	
	public int checkBillIsExist(TbBill tbBill) ;
	
	
	/**
	 * 正式提交保单
	 * 0 表示该保单之前已经提交过了，不能再次提交
	 * 1 表示该保单提交成功
	 * @param tbBill
	 * @return
	 */
	public int sureApplyBill(TbBill tbBill , File file , String fileName , List<TbTravelerInfo> travelerList) ;
	
	/**
	 * 设定一个保单的价格
	 * @param tbBill
	 * @return
	 */
	public TbBill getBillPrice(TbBill tbBill) ;
	
	/**
	 * 通用的更新保单信息
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
	 * 旅行社核查保单,并确认通过审查
	 * 返回结果1表示该保单之前已经被审查过了
	 * 返回结果2表示该保单通过审查，已更新数据
	 * @param tbBill
	 */
	public int sureBillByZsSuccess(TbBill tbBill) ;
	
	/**
	 * 旅行社审查不通过，退回保单
	 * @param tbBill
	 * @return
	 */
	public int returnBillByZs(TbBill tbBill) ;
	
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
	 * 按ID查询保单信息
	 * @return
	 */
	public TbBill getTbBillById(Integer id) ;

	/**
	 * 
	 * 根据保单号查询保单
	 * @param billNumber
	 * @return
	 */
	public TbBill getBillByNumber(String billNumber) ;
	
	
	/**
	 * 删除保单
	 * 将会删除跟保单相关联的数据（游客信息）
	 * @param id
	 */
	public void deleteBillById(Integer id) ;

	
	/**
	 * 根据保单IDs批量查询所有的保单
	 * @return
	 */
	public List<TbBill> getAllBillListByIds(int[] billIds) ;
	
	
	/**
	 * 保险公司对保单进行更新
	 * @param tbBill
	 * @return
	 */
	public int updateBillByBx(TbBill tbBill,File file, String fileName,List<TbTravelerInfo> travelerList , TbBillBackup tbBillBackup) ;
	
	
}
