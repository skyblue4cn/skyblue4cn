package cn.insurance.action.client.partment;

import java.util.List;

import cn.insurance.action.SupportAction;
import cn.insurance.model.PageBean;
import cn.insurance.model.TbPartment;
import cn.insurance.service.ITbPartmentServ;
import cn.insurance.service.client.IClientBillServ;
import cn.insurance.service.client.IClientPartmentServ;

/**
 * 总社查看下级部门的保单
 * @author 系统管理员
 *
 */
public class PartmentBillAction extends SupportAction{
	
	
	private IClientPartmentServ clientPartmentServ ;
	
	private IClientBillServ clientBillServ ;
	
	private List<TbPartment> partmentList ;
	
	private PageBean pageBean ;
	
	private Integer partmentId ;
	
	
	
	/**
	 * 查看各部门的保单,查询该旅行社下的所有部门
	 * @return
	 */
	public String toShowPartmentBill(){
		Integer companyId = super.getUser().getIntPartmentId() ;
		partmentList = clientPartmentServ.getAllPartmentByCompanyIdWithCompany(companyId);
		if(partmentId == null || partmentId.intValue() == 0){
			/*如果没有选择部门，则查询所有这个总社下的所有部门的保单*/
			pageBean = clientBillServ.getAllPartmentEffectedBillByCompanyId(pageBean, companyId) ;
		}else{
			TbPartment p = clientPartmentServ.getTbPartmentById(partmentId) ;
			/*如果不是用户登录的总社或下属部门，则不能查看*/
			if(p.getId().intValue() != companyId.intValue() && p.getIntParentId().intValue() != companyId.intValue()){
				return SUCCESS ;
			}
			/*选择了部门，则查询这个部门的保单*/
			pageBean = clientBillServ.getEffectedBillByPartmentId(pageBean, partmentId);
		}
		return SUCCESS ;
	}






	public List<TbPartment> getPartmentList() {
		return partmentList;
	}




	public void setPartmentList(List<TbPartment> partmentList) {
		this.partmentList = partmentList;
	}






	public IClientPartmentServ getClientPartmentServ() {
		return clientPartmentServ;
	}






	public void setClientPartmentServ(IClientPartmentServ clientPartmentServ) {
		this.clientPartmentServ = clientPartmentServ;
	}






	public PageBean getPageBean() {
		return pageBean;
	}






	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}






	public IClientBillServ getClientBillServ() {
		return clientBillServ;
	}






	public void setClientBillServ(IClientBillServ clientBillServ) {
		this.clientBillServ = clientBillServ;
	}






	public Integer getPartmentId() {
		return partmentId;
	}






	public void setPartmentId(Integer partmentId) {
		this.partmentId = partmentId;
	}
	
	
	
}
