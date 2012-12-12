package cn.insurance.service;

import java.util.List;

import cn.insurance.model.PageBean;
import cn.insurance.model.TbListForMonthPay;
import cn.insurance.model.TbMonthPayInfo;
import cn.insurance.model.TbMonthPayOutLog;
import cn.insurance.model.TbUser;


/**
 * 月费管理
 * @author zhuyan
 *
 */
public interface ITbMonthPayInfoServ {
	
	/**
	 * 按ID查询月费记录(包含保单清单)
	 * @param id
	 * @return
	 */
	public TbMonthPayInfo getMonthPayInfoById(Integer id) ;
	
//	/**
//	 * 用户对月费清单进行结算时
//	 * 查询帐户所有以前申请了缓付但还没有结算的清单(或者结算到当月的清单)
//	 * 不包含当月的清单
//	 * 给用户结算
//	 * @return
//	 */
//	public List<TbListForMonthPay> getAllHfMonthPayBillListByAccountId(Integer accountId , Integer monthPayId) ;
	
	
	
//	/**
//	 * 用户申请缓交部分清单
//	 * 先对缓交的月费清单进行检查
//	 * 返回0表示时间已过，不能缓交
//	 * 返回1表示申请的缓交清单数额过大，不允许
//	 * 返回2表示申请成功
//	 * @param monthId
//	 * @param ids
//	 * @return
//	 */
//	public int applyHjMonthByUser(Integer monthId , int[] ids) ;
//	
	
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
	 * 查询某个部门的所有月费结算记录
	 * @param pageBean
	 * @param partmentId
	 * @return
	 */
	public PageBean getAllMonthFeeLogByPartmentId(PageBean pageBean , Integer partmentId) ;
	
	/**
	 * 根据accountId查询该帐户下的所有月费结算记录
	 * @param pageBean
	 * @param accountId
	 * @return
	 */
	public PageBean getMonthPayByAccountId(PageBean pageBean , Integer accountId) ;

	
	/**
	 * 月结帐户收费
	 * 返回结果0 表示之前已收过费了 不能重复收费
	 * 返回结果1 表示数据错误（有可能是钱不够，也可能是其他原因）
	 * 返回结果2 表示成功！
	 * @param tbMonthPayInfo
	 * @return
	 */
	public int shouMonthFee(TbMonthPayOutLog tbMonthPayOutLog) ;
	
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
	public boolean checkTheUserPartmentMonthFee(int accountId) ;
	
}
