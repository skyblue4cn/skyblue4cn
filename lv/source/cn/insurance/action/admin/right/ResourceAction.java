package cn.insurance.action.admin.right;

import java.util.List;

import com.opensymphony.xwork.ModelDriven;

import cn.insurance.action.SupportAction;
import cn.insurance.model.right.TbResource;
import cn.insurance.model.right.TbRight;
import cn.insurance.service.right.ITbResourceServ;
import cn.insurance.service.right.ITbRightServ;
/**
 * 权限资源管理
 * @author 何青松
 * 修改时间 2012/11/20 14:45
 */
public class ResourceAction extends SupportAction implements ModelDriven{

	private static final long serialVersionUID = 1L;
	public TbResource tbResource = new TbResource();
	private ITbResourceServ tbResourceServ ;
	private ITbRightServ tbRightServ ;
	private List<TbResource> bxResourceList ;
	private List<TbResource> lxsResourceList ;
	private List<TbRight> bxRightList ;
	private List<TbRight> lxsRightList ;
	private int type ; //bx=1和lxs=2 

	public Object getModel() {
		return tbResource;
	}
	
	/**
	 * 所有的权限资源
	 * @return
	 */
	public String resourceList(){
		bxResourceList = tbResourceServ.getBxResourceList() ;
		lxsResourceList = tbResourceServ.getLxsResourceList() ;
		return SUCCESS ;
	}
	
	/**
	 * 
	 * 添加权限资源
	 * @return
	 */
	public String toAddResource(){
		if(type==1){
			bxRightList = tbRightServ.getAllBxRightList() ;
		}
		if(type==2){
			lxsRightList = tbRightServ.getAllLxsRightList() ;
		}
		return SUCCESS ;
	}
	
	
	/**
	 * 添加资源
	 * @return
	 */
	public String addResource(){
		tbResourceServ.addResource(tbResource) ;
		return SUCCESS ;
	}
	
	/**
	 * 更新资源
	 * @return
	 */
	public String updateResource(){
		tbResourceServ.updateResource(tbResource) ;
		return SUCCESS ;
	}

	public ITbResourceServ getTbResourceServ() {
		return tbResourceServ;
	}

	public void setTbResourceServ(ITbResourceServ tbResourceServ) {
		this.tbResourceServ = tbResourceServ;
	}

	public List<TbRight> getBxRightList() {
		return bxRightList;
	}

	public void setBxRightList(List<TbRight> bxRightList) {
		this.bxRightList = bxRightList;
	}

	public List<TbRight> getLxsRightList() {
		return lxsRightList;
	}

	public void setLxsRightList(List<TbRight> lxsRightList) {
		this.lxsRightList = lxsRightList;
	}

	public List<TbResource> getBxResourceList() {
		return bxResourceList;
	}

	public void setBxResourceList(List<TbResource> bxResourceList) {
		this.bxResourceList = bxResourceList;
	}

	public List<TbResource> getLxsResourceList() {
		return lxsResourceList;
	}

	public void setLxsResourceList(List<TbResource> lxsResourceList) {
		this.lxsResourceList = lxsResourceList;
	}

	public ITbRightServ getTbRightServ() {
		return tbRightServ;
	}

	public void setTbRightServ(ITbRightServ tbRightServ) {
		this.tbRightServ = tbRightServ;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}




	
}
