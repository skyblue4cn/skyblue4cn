package cn.insurance.action.admin.price;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.insurance.action.SupportAction;
import cn.insurance.model.TbAccidentalFee;
import cn.insurance.model.TbAdjustFee;
import cn.insurance.model.TbPartment;
import cn.insurance.service.IPriceServ;
import cn.insurance.service.admin.IAdminPartmentServ;

/**
 * 旅行社意外保险操作
 * @author 
 * 修改时间 2012/11/20 14:44
 */
public class PriceActionS extends SupportAction {
	
	private static final long serialVersionUID = 1L;
	private Log log=LogFactory.getLog(PriceActionS.class);
	private TbAccidentalFee tbAccidentalFee = new TbAccidentalFee();
	private TbAdjustFee tbAdjustFee = new TbAdjustFee();
	private IPriceServ priceServS ;
	private IAdminPartmentServ adminPartmentServ;
	private TbPartment tbPartment;
	private List<TbPartment> partmentList;
	
	/**
	 * 查询所有的旅行社进入价格管理
	 * @return
	 */
	public String toManagerPrice(){
		//查询所有的旅行社，因为价格之和旅行社有关
		partmentList = adminPartmentServ.getAllCompanyList() ;
		return SUCCESS ;
	}
	

	/**
	 * 取得通用价格
	 */
	public String getNormalPrice(){
		tbAdjustFee = priceServS.getNormalAdjustFee() ;
		tbAccidentalFee = priceServS.getNormalAccidentalFee();
		return SUCCESS ;
	}
	
	/**
	 * 取得某个旅行社的价格设定,如果这个旅行社没有记录，则返回为通用价格
	 * @return
	 */
	public String getPartmentPrice(){
		tbPartment = adminPartmentServ.getPartmentInfoById(tbPartment.getId()) ;
		tbAccidentalFee = priceServS.getCompanyAccdFeeById(tbPartment.getId()) ;
		tbAdjustFee = priceServS.getCompanyAdjustFee(tbPartment.getId()) ;
		return SUCCESS ;
	}
	/**
	 * 更新通用意外险价格
	 * @return
	 */
	public String updateNormalAccidentalPrice(){
		priceServS.updateNormalAccidentalFee(tbAccidentalFee) ; 
		addActionMessage("更新成功！") ;
		return getNormalPrice() ;
	}
	
	/**
	 * 更新通用的责任险调结费
	 * @return
	 */
	public String updateNormalAdjustPrice(){
		tbAccidentalFee.setId(3) ;
		tbAccidentalFee.setIntPartmentId(null) ;
		priceServS.updateNormalAdjustFee(tbAdjustFee) ; 
		addActionMessage("更新成功！") ;
		return getNormalPrice() ;
	}
	
	
	
	/**
	 * 更新旅行社意外险价格价格
	 */
	public String updateCompanyAccidentalPrice(){
		log.debug(tbAccidentalFee.getId()+"---------------------------------------------------------");
		priceServS.updateLxsAccidentalFee(tbAccidentalFee) ;
		addActionMessage("更新成功！") ;
		return SUCCESS;

	}
	
	
	/**
	 * 更新旅行社责任调节费价格
	 */
	public String updateCompanyAdjustPrice(){
		priceServS.updateLxsAdjustFee(tbAdjustFee) ;
		addActionMessage("更新成功！") ;
		return SUCCESS;

	}
	
	
	public TbAccidentalFee getTbAccidentalFee() {
		return tbAccidentalFee;
	}

	public void setTbAccidentalFee(TbAccidentalFee tbAccidentalFee) {
		this.tbAccidentalFee = tbAccidentalFee;
	}


	public IPriceServ getPriceServS() {
		return priceServS;
	}


	public void setPriceServS(IPriceServ priceServS) {
		this.priceServS = priceServS;
	}


	public TbAdjustFee getTbAdjustFee() {
		return tbAdjustFee;
	}


	public void setTbAdjustFee(TbAdjustFee tbAdjustFee) {
		this.tbAdjustFee = tbAdjustFee;
	}

	public TbPartment getTbPartment() {
		return tbPartment;
	}

	public void setTbPartment(TbPartment tbPartment) {
		this.tbPartment = tbPartment;
	}


	public List<TbPartment> getPartmentList() {
		return partmentList;
	}


	public void setPartmentList(List<TbPartment> partmentList) {
		this.partmentList = partmentList;
	}


	public IAdminPartmentServ getAdminPartmentServ() {
		return adminPartmentServ;
	}


	public void setAdminPartmentServ(IAdminPartmentServ adminPartmentServ) {
		this.adminPartmentServ = adminPartmentServ;
	}
}
