package cn.insurance.action.admin.right;

import java.util.List;

import com.opensymphony.xwork.ModelDriven;

import cn.insurance.action.SupportAction;
import cn.insurance.model.right.TbRight;
import cn.insurance.service.right.ITbRightServ;
/**
 * 权限管理
 * @author 何青松
 * 修改时间 2012/11/20 14:47
 */
public class RightAction extends SupportAction implements ModelDriven{
	
	private static final long serialVersionUID = 1L;
	public TbRight tbRight = new TbRight();
	private ITbRightServ tbRightServ ;
	private List<TbRight> bxRightList ;
	private List<TbRight> lxsRightList ;
	public Object getModel() {
		return tbRight;
	}

	/**
	 * 获得所有权限
	 * @return
	 */
	public String rightList(){
		bxRightList = tbRightServ.getAllBxRightList() ;
		lxsRightList = tbRightServ.getAllLxsRightList() ;
		return SUCCESS ;
	}
	
	/**
	 * 添加权限
	 * @return
	 */
	public String toAddRight(){
		return SUCCESS ;
	}
	
	/**
	 * 添加权限
	 * @return
	 */
	public String addRight(){
		tbRightServ.addTbRight(tbRight) ;
		addActionMessage("添加成功！") ;
		return SUCCESS ;
	}

	public ITbRightServ getTbRightServ() {
		return tbRightServ;
	}

	public void setTbRightServ(ITbRightServ tbRightServ) {
		this.tbRightServ = tbRightServ;
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
}
