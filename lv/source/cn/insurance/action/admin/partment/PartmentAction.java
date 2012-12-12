package cn.insurance.action.admin.partment;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import cn.insurance.action.SupportAction;
import cn.insurance.model.PageBean;
import cn.insurance.model.TbPartment;
import cn.insurance.service.admin.IAdminPartmentServ;
import com.opensymphony.xwork.ModelDriven;

/**
 * 旅行社树形节点
 * @author 何青松
 * 修改时间 2012/11/20 14:36
 */
@SuppressWarnings("serial")
public class PartmentAction extends SupportAction implements ModelDriven{

	public TbPartment tbPartment = new TbPartment();
	private IAdminPartmentServ adminPartmentServ;
	private List<TbPartment> partmentList = new ArrayList<TbPartment>();
	private PageBean pageBean;
	private int theYear ;
	private int theMonth ;
	private File partmentImg ;
	private String partmentImgContentType ;
	private String partmentImgFileName ;
	private int nodeid ;
	
	
	/* modelDriven */
	public Object getModel() {
		return tbPartment;
	}
	
	/**
	 * 通过节点ID查询部门信息
	 * @return
	 */
	public String getPartmentInfo(){
		tbPartment = adminPartmentServ.getPartmentInfoById(nodeid);
		return SUCCESS ;
	}
	
	/**
	 * 添加旅行社的时候对参数进行检查
	 * @return
	 */
	@SuppressWarnings("unused")
	private boolean validate_addCompany(){
		if(tbPartment.getStrPartmentName() == null || "".equals(tbPartment.getStrPartmentName().trim())){
			addActionError("请填写旅行社名称!") ;
			return false ;
		}
		if(tbPartment.getDteResStartDate()==null){
			addActionError("请填写责任险起始时间！") ;
			return false ;
		}
		if(tbPartment.getDteResEndDate()==null){
			addActionError("请填写责任险结束时间！") ;
			return false ;
		}
		return true ;
	}
		
	/**
	 * 添加旅行社或部门
	 */
	public String addCompany() throws Exception {
		if (adminPartmentServ.addTbPartment(tbPartment , partmentImg , partmentImgFileName)) {
			addActionMessage("添加成功！");
			return SUCCESS;
		}
		addActionError("添加失败，可能是名称存在冲突!");
		return INPUT;
	}
	
	/**
	 * 添加部门初始化数据
	 * @return
	 */
	public String toAddPartment(){
		return SUCCESS ;
	}

	/**
	 * 添加旅行社或部门
	 */
	public String addPartment() throws Exception {
		if(tbPartment.getStrPartmentName() == null || "".equals(tbPartment.getStrPartmentName().trim())){
			addActionError("请填写部门名称！") ;
			return INPUT ;
		}
		if (adminPartmentServ.addTbPartment(tbPartment , partmentImg , partmentImgFileName)) {
			addActionMessage("添加成功！");
			return SUCCESS;
		}
		addActionError("添加失败，可能是名称存在冲突!");
		return INPUT;
	}

	/**
	 * 修改旅行社或部门信息
	 * 
	 * @return
	 */
	public String updatePartment() {
		adminPartmentServ.updateTbPartment(tbPartment , partmentImg , partmentImgFileName);
		addActionMessage("更新成功！") ;
		return SUCCESS;
	}

	/**
	 * 查询全部旅行社
	 * 
	 * @return
	 */
	public String queryAllCompany() {
		/*月费统计的时候需要一个年月的初始值*/
		Calendar calendar = new GregorianCalendar() ;
		theYear = calendar.get(Calendar.YEAR) ;
		theMonth = calendar.get(Calendar.MONTH) +1 ;
		partmentList = adminPartmentServ.getAllCompanyList();
		return SUCCESS;
	}
	
	
	
	
	/**
	 * 按ID查询旅行社或部门信息
	 * 
	 * @return
	 */
	public String queryPartmentInfoById() {
		tbPartment = adminPartmentServ.getPartmentInfoById(tbPartment.getId());
		return SUCCESS;
	}
	

	/**
	 * 按旅行社ID查询该旅行社下的全部部门
	 * 分页
	 * @return
	 */
	public String getAllPartmentByNodeid() {
		tbPartment = adminPartmentServ.getPartmentInfoById(nodeid) ;
		pageBean = adminPartmentServ.getAllPartmentByCompanyId(pageBean, tbPartment.getId()) ;
		return SUCCESS;
	}

	/**
	 * 按旅行社ID查询该旅行社下的全部部门
	 * 分页
	 * @return
	 */
	public String getAllPartmentByCompanyId() {
		tbPartment = adminPartmentServ.getPartmentInfoById(tbPartment.getId()) ;
		pageBean = adminPartmentServ.getAllPartmentByCompanyId(pageBean, tbPartment.getId()) ;
		return SUCCESS;
	}
	
	/**
	 * 根绝旅行社ID查询所有的部门以供选择
	 */
	public String getAllPartmentBylxsId(){
		/*如果不选旅行社，则没有部门*/
		if(tbPartment.getId() == -1){
			return SUCCESS ;
		}
		partmentList = adminPartmentServ.getAllPartmentByParentId(tbPartment.getId()) ;
		return SUCCESS ;
	}
	
	public String getAllPartmentToSelect(){
		/*如果不选旅行社，则没有部门*/
		if(tbPartment.getId() == -1){
			return SUCCESS ;
		}
		/*先将旅行社总社加进去*/
		partmentList.add(adminPartmentServ.getPartmentInfoById(tbPartment.getId())) ;
		partmentList.addAll(adminPartmentServ.getAllPartmentByParentId(tbPartment.getId())) ;
		return SUCCESS ;
	}
	
	public String deletePartmentById(){
		try{
			adminPartmentServ.deletePartmentById(tbPartment.getId()) ;
		}catch(Exception e){
			addActionMessage("删除失败，可能有关联数据存在！请先删除关联数据!") ;
			return INPUT ;
		}
		addActionMessage("操作成功！");
		return SUCCESS ;
	}
	
	
	/* setter and getter */


	
	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}


	public List<TbPartment> getPartmentList() {
		return partmentList;
	}

	public void setPartmentList(List<TbPartment> partmentList) {
		this.partmentList = partmentList;
	}

	public int getTheMonth() {
		return theMonth;
	}

	public void setTheMonth(int theMonth) {
		this.theMonth = theMonth;
	}

	public int getTheYear() {
		return theYear;
	}

	public void setTheYear(int theYear) {
		this.theYear = theYear;
	}

	public File getPartmentImg() {
		return partmentImg;
	}

	public void setPartmentImg(File partmentImg) {
		this.partmentImg = partmentImg;
	}

	public String getPartmentImgContentType() {
		return partmentImgContentType;
	}

	public void setPartmentImgContentType(String partmentImgContentType) {
		this.partmentImgContentType = partmentImgContentType;
	}

	public String getPartmentImgFileName() {
		return partmentImgFileName;
	}

	public void setPartmentImgFileName(String partmentImgFileName) {
		this.partmentImgFileName = partmentImgFileName;
	}

	public IAdminPartmentServ getAdminPartmentServ() {
		return adminPartmentServ;
	}

	public void setAdminPartmentServ(IAdminPartmentServ adminPartmentServ) {
		this.adminPartmentServ = adminPartmentServ;
	}

	public int getNodeid() {
		return nodeid;
	}

	public void setNodeid(int nodeid) {
		this.nodeid = nodeid;
	}

}

