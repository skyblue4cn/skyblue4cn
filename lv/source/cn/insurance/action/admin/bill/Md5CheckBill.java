package cn.insurance.action.admin.bill;

import com.opensymphony.xwork.ModelDriven;

import cn.insurance.action.SupportAction;
import cn.insurance.model.TbBill;
import cn.insurance.service.admin.IAdminBillServ;
import cn.insurance.utils.BillCheckMd5Code;
/**
 * 保单的确定验证
 * @author 何青松
 * 修改时间 2012/11/20 14:32
 */
public class Md5CheckBill extends SupportAction implements ModelDriven{
	
	private static final long serialVersionUID = 1L;
	public TbBill tbBill = new TbBill() ;
	private String checkResult ;
	private IAdminBillServ adminBillServ ;
	public Object getModel() {
		return tbBill;
	}
	
	/**
	 * 对保单的确认码进行验证
	 * @return
	 */
	public String checkBillMd5Code(){
		String result = BillCheckMd5Code.getTheMd5Code(tbBill) ;
		boolean md5check = false ;
		//验证所填信息加密是否匹配
		if(result.equals(tbBill.getMd5CheckCode().trim())){
			md5check = true ;
		}
		if(!md5check){
			checkResult = "该保单信息没有通过系统验证，错误原因：确认码未能通过验证！" ;
			return INPUT ;
		}else{
			//查询该保单信息
			TbBill bill = adminBillServ.getBillByNumber(tbBill.getStrBillNumber().trim()) ;
			if(bill != null){
				checkResult = "该保单已通过系统验证。" ;
				tbBill = bill ;
				return SUCCESS ;
			}else{
				checkResult = "该保单信息没有通过系统验证，错误原因：确认码验证通过,但该保单在系统中不存在,无法查询该保单信息！" ;
				return INPUT ;
			}
		}
	}


	public String getCheckResult() {
		return checkResult;
	}


	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}


	public IAdminBillServ getAdminBillServ() {
		return adminBillServ;
	}


	public void setAdminBillServ(IAdminBillServ adminBillServ) {
		this.adminBillServ = adminBillServ;
	}

	
}
