package cn.insurance.action.admin.peikuan;

import com.opensymphony.xwork.ModelDriven;
import cn.insurance.action.SupportAction;
import cn.insurance.model.PageBean;
import cn.insurance.model.TbBill;
import cn.insurance.model.TbPeiKuan;
import cn.insurance.service.ITbPeiKuanServ;
import cn.insurance.service.admin.IAdminBillServ;
import cn.insurance.utils.CommonWords;
/**
 * 添加和查询赔款
 * @author 何青松
 * 修改时间 2012/11/20 14:41
 */
public class PeiKuanAction extends SupportAction implements ModelDriven{

	private static final long serialVersionUID = 1L;
	public TbPeiKuan tbPeiKuan = new TbPeiKuan();
	private ITbPeiKuanServ tbPeiKuanServ ;
	private PageBean pageBean ;
	private IAdminBillServ adminBillServ ;
	
	public Object getModel() {
		return tbPeiKuan;
	}
	
	/**
	 * 添加赔款记录
	 * @return
	 */
	public String addPeiKuanLog(){
		//先根据保单号找出该保单ID
		String billNumber = tbPeiKuan.getTbBill().getStrBillNumber().trim() ;
		if(billNumber == null || "".equals(billNumber)){
			addActionError("对不起，没有找到该保单,请查看保单号是否输入正确!") ;
			return INPUT ;
		}
		TbBill bill = null ;
		try{
			bill = adminBillServ.getBillByNumber(billNumber) ;
			if(bill == null){
				addActionError("对不起，没有找到该保单,请查看保单号是否输入正确!") ;
				return INPUT ;
			}
		}catch(Exception e){
			addActionError("对不起，没有找到该保单,请查看保单号是否输入正确!") ;
			return INPUT ;
		}
		tbPeiKuan.setIntBillId(bill.getId());
		if(bill.getIntIsPeiKuan() == CommonWords.peiKuan1){
			addActionError("该保单已添加过赔款记录，不能重复添加！");
			return INPUT ;
		}
		addActionMessage("添加成功！");
		return SUCCESS ;
	}

	
	/**
	 * 查询所有的赔款记录
	 * @return
	 */
	public String getAllPeiKuanLog(){
		pageBean = tbPeiKuanServ.getAllPeiKuanLog(pageBean) ;
		return SUCCESS ;
	}
	
	public ITbPeiKuanServ getTbPeiKuanServ() {
		return tbPeiKuanServ;
	}

	public void setTbPeiKuanServ(ITbPeiKuanServ tbPeiKuanServ) {
		this.tbPeiKuanServ = tbPeiKuanServ;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public IAdminBillServ getAdminBillServ() {
		return adminBillServ;
	}

	public void setAdminBillServ(IAdminBillServ adminBillServ) {
		this.adminBillServ = adminBillServ;
	}
}
