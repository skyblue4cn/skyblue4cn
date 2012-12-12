package cn.insurance.action.admin.account;

import jxl.Workbook;

import jxl.format.Alignment;
 
import jxl.format.UnderlineStyle;
 
import jxl.format.VerticalAlignment;
 
import jxl.write.Label;
import jxl.write.WriteException;
 
import jxl.write.WritableCellFormat;
 
import jxl.write.WritableFont;
 
import jxl.write.WritableSheet;
 
import jxl.write.WritableWorkbook;
import jxl.write.biff.RowsExceededException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.record.formula.functions.Request;

import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ModelDriven;
import cn.insurance.action.SupportAction;
import cn.insurance.model.PageBean;
import cn.insurance.model.TbMonthPayInfo;
import cn.insurance.model.TbPartment;
import cn.insurance.service.admin.IAdminMonthFeeServ;
import cn.insurance.service.admin.IAdminPartmentServ;
import cn.insurance.utils.CommonWords;
/**
 * 月费操作
 * @author 
 * 修改时间 2012/11/20 14:26
 */
public class MonthFeeAction extends SupportAction implements ModelDriven{
	
	private static final long serialVersionUID = 1L;
	public TbMonthPayInfo tbMonthPayInfo = new TbMonthPayInfo() ;
	private IAdminPartmentServ adminPartmentServ ;
	private IAdminMonthFeeServ adminMonthFeeServ ;
	private PageBean pageBean ;
	private int companyId ;
	private List<TbMonthPayInfo> monthPayInfoList ;
	private Integer partmentId ;
	private int isShowAll ;
	private int[] ids ;
	private double allFee = 0.0 ;
	private int nodeid ;

	public Object getModel() {
		return tbMonthPayInfo;
	}
	
	/**
	 * 根据帐户ID查询该帐户的所有月费结算记录
	 * @return
	 */
	public String getMonthPayLogByPartmentId(){
		TbPartment tbPartment = adminPartmentServ.getPartmentInfoById(nodeid) ;
		tbMonthPayInfo.setTbPartment(tbPartment) ;
		pageBean = adminMonthFeeServ.getMonthPayByAccountId(pageBean,tbPartment.getTbAccount().getId()) ;
		return SUCCESS ;
	}
	
	/**
	 * 根据帐户ID查询该帐户的所有月费结算记录
	 * @return
	 */
	public String getMonthPayLogByAccountId(){
		TbPartment tbPartment = adminPartmentServ.getPartmentInfoByAccountId(tbMonthPayInfo.getIntAccountId()) ;
		tbMonthPayInfo.setTbPartment(tbPartment) ;
		pageBean = adminMonthFeeServ.getMonthPayByAccountId(pageBean, tbMonthPayInfo.getIntAccountId()) ;
		return SUCCESS ;
	}
	
	
	/**
	 * 按ID查询月结算记录
	 * @return
	 */
	public String getMonthPayInfoById(){
		tbMonthPayInfo = adminMonthFeeServ.getMonthPayInfoById(tbMonthPayInfo.getId()) ;
		return SUCCESS ;
	}
	
	
	/**
	 * 月费结算收费
	 */
	public String shouMonthFee(){
		if(!checkMonthFee()){
			getMonthPayInfoById() ;
			return INPUT ;
		}
		
		int result = adminMonthFeeServ.shouFei(tbMonthPayInfo.getId() ,tbMonthPayInfo.getPayType(),tbMonthPayInfo.getTbMonthPayOutLog()) ;
		if(result ==0 ){
			addActionError("失败，该月费已收费！不能重复收费！") ;
		}
		else{
			addActionMessage("付费成功！，已更新！");
		}
		return SUCCESS ;
	}
	
	/**
	 * 对收取月费进行检查
	 * @return
	 */
	private boolean checkMonthFee(){
		TbMonthPayInfo m = adminMonthFeeServ.getMonthPayInfoById(tbMonthPayInfo.getId()) ;
		if(m.getIntIsPay().intValue() ==  CommonWords.monthPayState2){
			addActionError("该月已经收取过费用！不能重复收取！") ;
			return false ;
		}
		if(new Date().before(m.getDteEndTime())){
			addActionError("请在该月结束以后收费！") ;
			return false ;
		}
		if(tbMonthPayInfo.getPayType() == 0){
			addActionError("请选择收费方式！") ;
			return false ;
		}
		if(tbMonthPayInfo.getTbMonthPayOutLog().getStrDesc() == null || "".equals(tbMonthPayInfo.getTbMonthPayOutLog().getStrDesc())){
			addActionError("请填写备注说明！") ;
			return false ;
		}
		return true ;
	}
	

	/**
	 * 根据旅行社ID查询部门的月费统计
	 * @return
	 */
	public String getPartmentMonthStatByCompanyId(){
		if(companyId == -1){
			return SUCCESS ;
		}
		monthPayInfoList = adminMonthFeeServ.getPartmentMonthStatByCompanyId(companyId, tbMonthPayInfo.getStrYear(),tbMonthPayInfo.getStrMonth() ) ;
		/*这里为了根据旅行社统一结账的要求，这里将所有的费用做一个统计*/
		tbMonthPayInfo.setId(0) ;
		tbMonthPayInfo.setDbeNeedToPay(0.0) ;
		if(monthPayInfoList != null && monthPayInfoList.size() >0){
			for(TbMonthPayInfo m : monthPayInfoList){
				if(m.getIntIsPay() == CommonWords.monthPayState1){
					/*只要是没有交费的都统计*/
					tbMonthPayInfo.setDbeNeedToPay(tbMonthPayInfo.getDbeNeedToPay() + m.getDbeNeedToPay()) ;
				}
			}
		}
			try {
				int size = this.monthPayInfoList.size();
				   String fileName = "c://导出数据.xls";// 导出文件名
				   FileOutputStream fileOutputStream = new FileOutputStream(fileName);
				   // 创建新的Excel 工作簿
				   WritableWorkbook workbook = Workbook.createWorkbook(fileOutputStream);
				   // 生成 Exc 的Sheet
				   WritableSheet sheet = workbook.createSheet("导出数据", 0);
				   // 这里是对Cell里面的字体进行格式化
				   WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10,
				          WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
				          jxl.format.Colour.BLACK);
				   WritableCellFormat format = new WritableCellFormat(wfc);
				   WritableCellFormat wcfFC = new jxl.write.WritableCellFormat();
				   // 行居中
				   wcfFC.setAlignment(Alignment.CENTRE);
				   // 列居中
				   wcfFC.setVerticalAlignment(VerticalAlignment.CENTRE);
				   Label labelCk = null;
				   labelCk = new Label(0, 0, "编号", wcfFC);
				   sheet.addCell(labelCk);
				   labelCk = new Label(1, 0, "名称", wcfFC);
				   sheet.addCell(labelCk);
				   labelCk = new Label(2, 0, "应付", wcfFC);
				   sheet.addCell(labelCk);
				   labelCk = new Label(3, 0, "状态", wcfFC);
				   sheet.addCell(labelCk);
				   // 循环将输出内容加到sheet中
				   for (int i = 1; i < size + 1; i++){
					   TbMonthPayInfo c = (TbMonthPayInfo) this.monthPayInfoList.get(i - 1);
				       String cId = String.valueOf(i);
				       labelCk = new Label(0, i, cId, wcfFC);
				       sheet.addCell(labelCk);
				       
				       String cName =c.getTbPartment().getStrPartmentName();
				       labelCk = new Label(1, i, cName, wcfFC);
				       sheet.addCell(labelCk);
				       
				       String cWork = c.getDbeNeedToPay().toString();
				       labelCk = new Label(2, i, cWork, wcfFC);
				       sheet.addCell(labelCk);
 
				       int num =c.getIntIsPay();
				       String value="";
				       if(num==0)
				    	      value="未交费";
				       else if(num==-1)
				    	   value="无费用";
				       else
				    	   value="以交费";
				       String cTel = value;
				       labelCk = new Label(3, i, cTel, wcfFC);
				       sheet.addCell(labelCk);
				   }
				   workbook.write();
				   workbook.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return SUCCESS ;
	}
	
//	/**
//	 * 导出数据到Excel
//	 * @return
//	 * @throws Exception
//	 */
//	public String export() throws Exception {
//		ActionContext actionContext =ActionContext.getContext();
//		Map session = actionContext.getSession();
//	    List contactList = (List)session.get("list");
//	    
//	       try {
//			int size = contactList.size();
//			   String fileName = "c:\\导出记录.xls";// 导出文件名
//			   FileOutputStream fileOutputStream = new FileOutputStream(fileName);
//			   // 创建新的Excel 工作簿
//			   WritableWorkbook workbook = Workbook.createWorkbook(fileOutputStream);
//			   // 生成 Exc 的Sheet
//			   WritableSheet sheet = workbook.createSheet("导出数据", 0);
//			   // 这里是对Cell里面的字体进行格式化
//			   WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10,
//			          WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
//			          jxl.format.Colour.BLACK);
//			   WritableCellFormat format = new WritableCellFormat(wfc);
//			   WritableCellFormat wcfFC = new jxl.write.WritableCellFormat();
// 
//			   // 行居中
//			   wcfFC.setAlignment(Alignment.CENTRE);
//			   // 列居中
//			   wcfFC.setVerticalAlignment(VerticalAlignment.CENTRE);
//			   Label labelCk = null;
//			   labelCk = new Label(0, 0, "编号", wcfFC);
//			   sheet.addCell(labelCk);
//			   labelCk = new Label(1, 0, "名称", wcfFC);
//			   sheet.addCell(labelCk);
//			   labelCk = new Label(2, 0, "应付", wcfFC);
//			   sheet.addCell(labelCk);
//			   labelCk = new Label(3, 0, "状态", wcfFC);
//			   sheet.addCell(labelCk);
//			   //labelCk = new Label(4, 0, "清单", wcfFC);
//			   //sheet.addCell(labelCk);
////	       labelCk = new Label(5, 0, "交费", wcfFC);
////	       sheet.addCell(labelCk);
//			   // 循环将输出内容加到sheet中
//			   for (int i = 1; i < size + 1; i++){
//				   TbMonthPayInfo c = (TbMonthPayInfo) contactList.get(i - 1);
//			       String cId = String.valueOf(i);
//			       labelCk = new Label(0, i, cId, wcfFC);
//			       sheet.addCell(labelCk);
//			       
//			       String cName =c.getTbPartment().getStrPartmentName();
//			       labelCk = new Label(1, i, cName, wcfFC);
//			       sheet.addCell(labelCk);
//			       
//			       String cWork = c.getDbeNeedToPay().toString();
//			       labelCk = new Label(2, i, cWork, wcfFC);
//			       sheet.addCell(labelCk);
// 
//			       int num =c.getIntIsPay();
//			       String value="";
//			       if(num==0)
//			    	      value="未交费";
//			       else if(num==-1)
//			    	   value="无费用";
//			       else
//			    	   value="以交费";
//			       String cTel = value;
//			       labelCk = new Label(3, i, cTel, wcfFC);
//			       sheet.addCell(labelCk);
//			   }
//			   workbook.write();
//			   workbook.close();
//			   this.addFieldError("ok", "<center><h1 style='margin-top:40px'>导出成功</h1></center>");
//		} catch (Exception e) {
//			this.addFieldError("ok", "<center><h1 style='margin-top:40px'>导出错误，请重试</h1></center>");
//		}
//	       return "SUCCESS";
//	    }

	/**
	 * 根据部门ID查看该部门的月费统计
	 * @return
	 */
	public String getPartmentMonthStatByPartmentId(){
		tbMonthPayInfo = adminMonthFeeServ.getPartmentMonthStatByPartmentId(partmentId , tbMonthPayInfo.getStrYear(),tbMonthPayInfo.getStrMonth()) ;
		return SUCCESS ;
	}
	
	
	/**
	 * 根据选择的月费ID查询所有的月费（集中收费）
	 * @return
	 */
	public String getPartmentFeeByIdList(){
		/*查处所有选中的月费记录详情*/
		monthPayInfoList = adminMonthFeeServ.getPartmentFeeByIdList(ids) ;
		/*把第一个的数据提出来，为了方便显示旅行社及时间等信息*/
		tbMonthPayInfo = monthPayInfoList.get(0) ;
		/*统计一下总共需要多少钱*/
		allFee = 0.0 ;
		for(TbMonthPayInfo m : monthPayInfoList){
			if(m.getIntIsPay() == CommonWords.monthPayState1){
				allFee += m.getDbeNeedToPay() ;
			}
		}
		return SUCCESS ;
	}
	
	/**
	 * 集中收费
	 * @return
	 */
	public String shouPartmentFeeByIdList(){
		adminMonthFeeServ.showPartmentFeeByIdList(ids, tbMonthPayInfo.getTbMonthPayOutLog()) ;
		addActionMessage("付费成功！，已更新！");
		return SUCCESS ;
	}
	
	
	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public List<TbMonthPayInfo> getMonthPayInfoList() {
		return monthPayInfoList;
	}

	public void setMonthPayInfoList(List<TbMonthPayInfo> monthPayInfoList) {
		this.monthPayInfoList = monthPayInfoList;
	}

	public Integer getPartmentId() {
		return partmentId;
	}

	public void setPartmentId(Integer partmentId) {
		this.partmentId = partmentId;
	}

	public int getIsShowAll() {
		return isShowAll;
	}

	public void setIsShowAll(int isShowAll) {
		this.isShowAll = isShowAll;
	}

	public double getAllFee() {
		return allFee;
	}

	public void setAllFee(double allFee) {
		this.allFee = allFee;
	}

	public int[] getIds() {
		return ids;
	}

	public void setIds(int[] ids) {
		this.ids = ids;
	}

	public IAdminPartmentServ getAdminPartmentServ() {
		return adminPartmentServ;
	}

	public void setAdminPartmentServ(IAdminPartmentServ adminPartmentServ) {
		this.adminPartmentServ = adminPartmentServ;
	}

	public IAdminMonthFeeServ getAdminMonthFeeServ() {
		return adminMonthFeeServ;
	}

	public void setAdminMonthFeeServ(IAdminMonthFeeServ adminMonthFeeServ) {
		this.adminMonthFeeServ = adminMonthFeeServ;
	}

	public int getNodeid() {
		return nodeid;
	}

	public void setNodeid(int nodeid) {
		this.nodeid = nodeid;
	}
}
