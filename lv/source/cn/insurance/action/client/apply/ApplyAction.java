package cn.insurance.action.client.apply;

import java.util.Date;

import com.opensymphony.xwork.ModelDriven;

import cn.insurance.action.SupportAction;
import cn.insurance.commonwords.BillKey;
import cn.insurance.commonwords.TbApplyKey;
import cn.insurance.model.PageBean;
import cn.insurance.model.TbApply;
import cn.insurance.model.TbBill;
import cn.insurance.service.ITbApplyServ;
import cn.insurance.service.ITbBillServ;
import cn.insurance.service.client.IClientApplyServ;
import cn.insurance.service.client.IClientBillServ;

/**
 * 对批改申请的管理
 * @author yqg
 *
 */
public class ApplyAction extends SupportAction implements ModelDriven{
	
	public TbApply tbApply = new TbApply() ;
	
	private IClientApplyServ clientApplyServ ;
	
	private IClientBillServ clientBillServ ;
	
	private PageBean pageBean ;
	
	public Object getModel() {
		return  tbApply;
	}
	
	/**
	 * 通过保单号查询保单信息，对保单先进性验证是否可以填写
	 * @return
	 */
	public String toAddApplyUpdate(){
		TbBill tbBill = clientBillServ.getBillByNumber(tbApply.getStrBillNumber().trim()) ;
		//是否存在这个保单
		if(tbBill == null){
			addActionError("该保单不存在，请查看保单号是否填写正确！") ;
			return INPUT ;
		}
		//验证是不是这个部门的保单
		if(tbBill.getIntPartmentId().intValue() != getUser().getIntPartmentId().intValue()){
			addActionError("该保单不是您所在部门的保单，不能对该保单申请！") ;
			return INPUT ;
		}
		if(tbBill.getIntBillStateId().intValue() != BillKey.billState4){
			addActionError("该保单不是已确认的保单，不能对该保单申请！") ;
			return INPUT ;
		}
		//验证时间
		if(tbBill.getDteEndTime().before(new Date())){
			addActionError("该保单已过了保期，不能申请批改！") ;
			return INPUT ;
		}
		tbApply.setIntBillId(tbBill.getId()) ;
		tbApply.setStrBillNumber(tbBill.getStrBillNumber()) ;
		return SUCCESS ;
	}
	
	/**
	 * 用户修改申请 查询申请内容
	 * @return
	 */
	public String toUpdateApplyUpdate(){
		tbApply.setId(limitId) ;
		tbApply = clientApplyServ.getApplyById(tbApply.getId()) ;
		//如果是已回复的，则不准修改
		if(tbApply.getIntIsReply().intValue() == TbApplyKey.Has_Reply_State){
			addActionError("该申请保险公司已回复了，不能修改申请！") ;
			return INPUT ;
		}
		return SUCCESS ;
	}
	
	/**
	 * 修改申请
	 * @return
	 */
	public String updateApplyUpdate(){
		clientApplyServ.updateTbApply(tbApply) ;
		addActionMessage("操作成功,申请已修改！") ;
		return SUCCESS ;
	}
	
	/**
	 * 添加新的申请
	 * @return
	 */
	public String addTbApply(){
		int result = clientApplyServ.addTbApply(tbApply) ;
		if(result ==0){
			addActionMessage("申请失败，请查看保单号是否写正确，并且保证该保单是保险公司已确认的保单！") ;
			return INPUT ;
		}
		addActionMessage("操作成功，已将申请发往保险公司！请注意查看回复!") ;
		return SUCCESS ;
	}
	
	/**
	 * 查询所有未回复的申请
	 * @return
	 */
	public String getNotReplyApply(){
		pageBean = clientApplyServ.getNotReplyApplyByPartmentId(pageBean, getUser().getIntPartmentId()) ;
		return SUCCESS ;
	}
	
	/**
	 * 查询所有已回复的申请
	 * @return
	 */
	public String getHasReplyApply(){
		pageBean = clientApplyServ.getHasReplyApplyByPartmentId(pageBean, getUser().getIntPartmentId()) ;
		return SUCCESS ;
	}
	
	/**
	 * 查看申请详情
	 * @return
	 */
	public String showApplyUpdateInfo(){
		tbApply = clientApplyServ.getApplyById(limitId) ;
		return SUCCESS ;
	}


	public PageBean getPageBean() {
		return pageBean;
	}


	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public IClientApplyServ getClientApplyServ() {
		return clientApplyServ;
	}

	public void setClientApplyServ(IClientApplyServ clientApplyServ) {
		this.clientApplyServ = clientApplyServ;
	}

	public IClientBillServ getClientBillServ() {
		return clientBillServ;
	}

	public void setClientBillServ(IClientBillServ clientBillServ) {
		this.clientBillServ = clientBillServ;
	}


}
