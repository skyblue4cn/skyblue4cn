package cn.insurance.action.admin.tongji;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionContext;

import cn.insurance.action.SupportAction;
import cn.insurance.model.TbMonthPayInfo;
import cn.insurance.model.TbPartment;
import cn.insurance.model.tongji2.AllCompanyYearStat;
import cn.insurance.model.tongji2.PartmentYearStat;
import cn.insurance.model.tongji2.abstractmodel.AbstractPartmentDayStat;
import cn.insurance.model.tongji2.abstractmodel.AbstractPartmentMonthStat;
import cn.insurance.service.admin.IAdminPartmentServ;
import cn.insurance.service.pub.ITongJiServ;
/**
 * 按天按月统计
 * @author 
 * 修改时间 2012/11/20 15:01
 */
public class TongJiAction extends SupportAction{
	
	private static final long serialVersionUID = 1L;
	private ITongJiServ tongJiServ ;
	private IAdminPartmentServ adminPartmentServ ;
	private List<AllCompanyYearStat> allCompanyYearStatList;
	private List<PartmentYearStat> partmentYearStatList ;
	private AbstractPartmentMonthStat ams ;
	private AbstractPartmentDayStat ads ;
	private int companyId ;
	private int partmentId ;
	private int year ;
	private int month ;
	private TbPartment company ;
	private TbPartment tbPartment ;
	private List<TbPartment> companyList ;
	
	/**
	 * 进入统计页面 查询数据
	 * @return
	 */
	public String toAdminStat(){
		Calendar c = new GregorianCalendar();
		year = c.get(Calendar.YEAR) ;
		companyList = adminPartmentServ.getAllCompanyList();
		return SUCCESS ;
	}
	
	/**
	 * 根据选择的条件进行统计
	 * @return
	 */
	public String adminStat(){
		ActionContext actionContext =ActionContext.getContext();
		Map session = actionContext.getSession();
		if(partmentId != -1 && partmentId != 0){
			/*按年统计该部门的数据*/
			tbPartment = adminPartmentServ.getPartmentInfoById(partmentId) ;
			PartmentYearStat py = tongJiServ.getPartmentYearStat(partmentId, year);
			partmentYearStatList = new ArrayList<PartmentYearStat>();
			partmentYearStatList.add(py);
			try {
				int size = this.partmentYearStatList.size();
				   String fileName = "c:\\按年导出的该部门的数据.xls";// 导出文件名
				   
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
				   List<Label> labellist = null;
				   Label labelCk = null;
				   labelCk = new Label(0, 0, "部门名称", wcfFC);
				   sheet.addCell(labelCk);
				   labelCk = new Label(1, 0, "时间", wcfFC);
				   sheet.addCell(labelCk);
				   labelCk = new Label(2, 0, "投保申请", wcfFC);
				   sheet.addCell(labelCk);
				   labelCk = new Label(3, 0, "审核通过", wcfFC);
				   sheet.addCell(labelCk);
				   labelCk = new Label(4, 0, "未通过", wcfFC);
				   sheet.addCell(labelCk);
				   labelCk = new Label(5, 0, "以付费份数", wcfFC);
				   sheet.addCell(labelCk);
				   labelCk = new Label(6, 0, "以付费金额", wcfFC);
				   sheet.addCell(labelCk);
				   labelCk = new Label(7, 0, "未付费份数", wcfFC);
				   sheet.addCell(labelCk);
				   labelCk = new Label(8, 0, "未付费金额", wcfFC);
				   sheet.addCell(labelCk);
				   labelCk = new Label(9, 0, "赔偿份数", wcfFC);
				   sheet.addCell(labelCk);
				   labelCk = new Label(10, 0, "赔偿金额", wcfFC);
				   sheet.addCell(labelCk);
				   // 循环将输出内容加到sheet中
				   for (int i = 1; i < size + 1; i++){
					   PartmentYearStat c = (PartmentYearStat) this.partmentYearStatList.get(i - 1);
					   TbPartment tb = (TbPartment)this.tbPartment;
					   
				       String cId = tb.getStrPartmentName();
				       labelCk = new Label(0, i, cId, wcfFC);
				       sheet.addCell(labelCk);
				       
				       String cName =String.valueOf(c.getYear());
				       labelCk = new Label(1, i, cName, wcfFC);
				       sheet.addCell(labelCk);
				       
				       String cWork = String.valueOf(c.getAllBillCount());
				       labelCk = new Label(2, i, cWork, wcfFC);
				       sheet.addCell(labelCk);
	 
				       String c_1 = String.valueOf(c.getHasSureBillCount());
				       labelCk = new Label(3, i, c_1, wcfFC);
				       sheet.addCell(labelCk);
				       
				       String c_2 = String.valueOf(c.getReturnBillCount());
				       labelCk = new Label(4, i, c_2, wcfFC);
				       sheet.addCell(labelCk);
				       
				       String c_3 = String.valueOf(c.getHasPayBillCount());
				       labelCk = new Label(5, i, c_3, wcfFC);
				       sheet.addCell(labelCk);
				       
				       String c_4 = String.valueOf(c.getHasPayBillFee());
				       labelCk = new Label(6, i, c_4, wcfFC);
				       sheet.addCell(labelCk);
				       
				       String c_5 = String.valueOf(c.getNotPayBillCount());
				       labelCk = new Label(7, i, c_5, wcfFC);
				       sheet.addCell(labelCk);
				       
				       String c_6 = String.valueOf(c.getNotPayBillFee());
				       labelCk = new Label(8, i, c_6, wcfFC);
				       sheet.addCell(labelCk);
				       
				       String c_7 = String.valueOf(c.getPeiKuaiBillCount());
				       labelCk = new Label(9, i, c_7, wcfFC);
				       sheet.addCell(labelCk);
				       
				       String c_8 = String.valueOf(c.getPeiKuanNumber());
				       labelCk = new Label(10, i, c_8, wcfFC);
				       sheet.addCell(labelCk);
				   }
				   workbook.write();
				   workbook.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "partment" ;
		}else{
			if(companyId !=-1 && companyId != 0){
				/*按年统计该旅行社的数据*/
				company = adminPartmentServ.getPartmentInfoById(companyId) ;
				partmentYearStatList = tongJiServ.getPartmentYearStatByCompanyId(companyId, year);
				
				/**START 修改于2010-6-7*/
				PartmentYearStat ps=null;
				if(partmentYearStatList != null){
					ps = new PartmentYearStat();
					for (Iterator<PartmentYearStat> iterator = partmentYearStatList.iterator(); iterator.hasNext();) {
						PartmentYearStat r = (PartmentYearStat) iterator.next();
						ps.setAllBillCount(ps.getAllBillCount()+r.getAllBillCount());
						ps.setHasSureBillCount(ps.getHasSureBillCount()+r.getHasSureBillCount());
						ps.setReturnBillCount(ps.getReturnBillCount()+r.getReturnBillCount());
						ps.setHasPayBillCount(ps.getHasPayBillCount()+r.getHasPayBillCount());
						ps.setHasPayBillFee(ps.getHasPayBillFee()+r.getHasPayBillFee());
						ps.setNotPayBillCount(ps.getNotPayBillCount()+r.getNotPayBillCount());
						ps.setNotPayBillFee(ps.getNotPayBillFee()+r.getNotPayBillFee());
						ps.setPeiKuaiBillCount(ps.getPeiKuaiBillCount()+r.getPeiKuaiBillCount());
						ps.setPeiKuanNumber(ps.getPeiKuanNumber()+r.getPeiKuanNumber());
					}
					ServletActionContext.getRequest().setAttribute("ps", ps);
				}
			       try {
			    	   int size = this.partmentYearStatList.size();
					   System.out.println("集合大小:"+size);
					   String fileName = "c:\\按年导出的该旅行社的数据.xls";
					   FileOutputStream fileOutputStream = new FileOutputStream(fileName);
					   // 创建新的Excel 工作簿
					   WritableWorkbook workbook = Workbook.createWorkbook(fileOutputStream);
					   // 生成 Excl 的Sheet
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
					   labelCk = new Label(0, 0, "部门名称", wcfFC);
					   sheet.addCell(labelCk);
					   labelCk = new Label(1, 0, "时间", wcfFC);
					   sheet.addCell(labelCk);
					   labelCk = new Label(2, 0, "投保申请", wcfFC);
					   sheet.addCell(labelCk);
					   labelCk = new Label(3, 0, "审核通过", wcfFC);
					   sheet.addCell(labelCk);
					   labelCk = new Label(4, 0, "未通过", wcfFC);
					   sheet.addCell(labelCk);
					   labelCk = new Label(5, 0, "以付费份数", wcfFC);
					   sheet.addCell(labelCk);
					   labelCk = new Label(6, 0, "以付费金额", wcfFC);
					   sheet.addCell(labelCk);
					   labelCk = new Label(7, 0, "未付费份数", wcfFC);
					   sheet.addCell(labelCk);
					   labelCk = new Label(8, 0, "未付费金额", wcfFC);
					   sheet.addCell(labelCk);
					   labelCk = new Label(9, 0, "赔偿份数", wcfFC);
					   sheet.addCell(labelCk);
					   labelCk = new Label(10, 0, "赔偿金额", wcfFC);
					   sheet.addCell(labelCk);
					   // 循环将输出内容加到sheet中
					   int i = 1;
					   PartmentYearStat c=null;
					   for (; i < size + 1;){
						   c= (PartmentYearStat) this.partmentYearStatList.get(i - 1);
						   TbPartment tb = (TbPartment)this.company;
					       String cId = c.getTbPartment().getStrPartmentName();
					       labelCk = new Label(0, i, cId, wcfFC);
					       sheet.addCell(labelCk);
					       
					       String cName =String.valueOf(c.getYear());
					       labelCk = new Label(1, i, cName, wcfFC);
					       sheet.addCell(labelCk);
					       
					       String cWork = String.valueOf(c.getAllBillCount());
					       labelCk = new Label(2, i, cWork, wcfFC);
					       sheet.addCell(labelCk);
		 
					       String c_1 = String.valueOf(c.getHasSureBillCount());
					       labelCk = new Label(3, i, c_1, wcfFC);
					       sheet.addCell(labelCk);
					       
					       String c_2 = String.valueOf(c.getReturnBillCount());
					       labelCk = new Label(4, i, c_2, wcfFC);
					       sheet.addCell(labelCk);
					       
					       String c_3 = String.valueOf(c.getHasPayBillCount());
					       labelCk = new Label(5, i, c_3, wcfFC);
					       sheet.addCell(labelCk);
					       
					       String c_4 = String.valueOf(c.getHasPayBillFee());
					       labelCk = new Label(6, i, c_4, wcfFC);
					       sheet.addCell(labelCk);
					       
					       String c_5 = String.valueOf(c.getNotPayBillCount());
					       labelCk = new Label(7, i, c_5, wcfFC);
					       sheet.addCell(labelCk);
					       
					       String c_6 = String.valueOf(c.getNotPayBillFee());
					       labelCk = new Label(8, i, c_6, wcfFC);
					       sheet.addCell(labelCk);
					       
					       String c_7 = String.valueOf(c.getPeiKuaiBillCount());
					       labelCk = new Label(9, i, c_7, wcfFC);
					       sheet.addCell(labelCk);
					       
					       String c_8 = String.valueOf(c.getPeiKuanNumber());
					       labelCk = new Label(10, i, c_8, wcfFC);
					       sheet.addCell(labelCk);
					       i++;
					       System.out.println("添加内容完毕");
					   }
					   String cId1 ="总计";
				       labelCk = new Label(0, i, cId1, wcfFC);
				       sheet.addCell(labelCk);
				       
				       String cName1 =String.valueOf(c.getYear());
				       labelCk = new Label(1, i, cName1, wcfFC);
				       sheet.addCell(labelCk);
				       
				       String cWork1 = String.valueOf(ps.getAllBillCount());
				       labelCk = new Label(2, i, cWork1, wcfFC);
				       sheet.addCell(labelCk);

				       String cv_1 = String.valueOf(ps.getHasSureBillCount());
				       labelCk = new Label(3, i, cv_1, wcfFC);
				       sheet.addCell(labelCk);
				       
				       String cv_2 = String.valueOf(ps.getReturnBillCount());
				       labelCk = new Label(4, i, cv_2, wcfFC);
				       sheet.addCell(labelCk);
				       
				       String cv_3 = String.valueOf(ps.getHasPayBillCount());
				       labelCk = new Label(5, i, cv_3, wcfFC);
				       sheet.addCell(labelCk);
				       
				       String cv_4 = String.valueOf(ps.getHasPayBillFee());
				       labelCk = new Label(6, i, cv_4, wcfFC);
				       sheet.addCell(labelCk);
				       
				       String cv_5 = String.valueOf(ps.getNotPayBillCount());
				       labelCk = new Label(7, i, cv_5, wcfFC);
				       sheet.addCell(labelCk);
				       
				       String cv_6 = String.valueOf(ps.getNotPayBillFee());
				       labelCk = new Label(8, i, cv_6, wcfFC);
				       sheet.addCell(labelCk);
				       
				       String cv_7 = String.valueOf(ps.getPeiKuaiBillCount());
				       labelCk = new Label(9, i, cv_7, wcfFC);
				       sheet.addCell(labelCk);
				       
				       String cv_8 = String.valueOf(ps.getPeiKuanNumber());
				       labelCk = new Label(10, i, cv_8, wcfFC);
				       sheet.addCell(labelCk);
				       
					   workbook.write();
					   workbook.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "company" ;
			}else{
				/*按年统计所有旅行社的数据*/
				allCompanyYearStatList=tongJiServ.getAllCompanyYearStat(year) ;
				 try {
						int size = this.allCompanyYearStatList.size();
						   String fileName = "c:\\按年导出的所有旅行社的数据.xls";// 导出文件名
						   
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
						   List<Label> labellist = null;
						   Label labelCk = null;
						   labelCk = new Label(0, 0, "部门名称", wcfFC);
						   sheet.addCell(labelCk);
						   labelCk = new Label(1, 0, "时间", wcfFC);
						   sheet.addCell(labelCk);
						   labelCk = new Label(2, 0, "投保申请", wcfFC);
						   sheet.addCell(labelCk);
						   labelCk = new Label(3, 0, "审核通过", wcfFC);
						   sheet.addCell(labelCk);
						   labelCk = new Label(4, 0, "未通过", wcfFC);
						   sheet.addCell(labelCk);
						   labelCk = new Label(5, 0, "以付费份数", wcfFC);
						   sheet.addCell(labelCk);
						   labelCk = new Label(6, 0, "以付费金额", wcfFC);
						   sheet.addCell(labelCk);
						   labelCk = new Label(7, 0, "未付费份数", wcfFC);
						   sheet.addCell(labelCk);
						   labelCk = new Label(8, 0, "未付费金额", wcfFC);
						   sheet.addCell(labelCk);
						   labelCk = new Label(9, 0, "赔偿份数", wcfFC);
						   sheet.addCell(labelCk);
						   labelCk = new Label(10, 0, "赔偿金额", wcfFC);
						   sheet.addCell(labelCk);
						   // 循环将输出内容加到sheet中
						   for (int i = 1; i < size + 1; i++){
							   AllCompanyYearStat c = (AllCompanyYearStat) this.allCompanyYearStatList.get(i - 1);

						       String cId = c.getCompany().getStrPartmentName();
						       labelCk = new Label(0, i, cId, wcfFC);
						       sheet.addCell(labelCk);
						       
						       String cName =String.valueOf(c.getYear());
						       labelCk = new Label(1, i, cName, wcfFC);
						       sheet.addCell(labelCk);
						       
						       String cWork = String.valueOf(c.getAllBillCount());
						       labelCk = new Label(2, i, cWork, wcfFC);
						       sheet.addCell(labelCk);
			 
						       String c_1 = String.valueOf(c.getHasSureBillCount());
						       labelCk = new Label(3, i, c_1, wcfFC);
						       sheet.addCell(labelCk);
						       
						       String c_2 = String.valueOf(c.getReturnBillCount());
						       labelCk = new Label(4, i, c_2, wcfFC);
						       sheet.addCell(labelCk);
						       
						       String c_3 = String.valueOf(c.getHasPayBillCount());
						       labelCk = new Label(5, i, c_3, wcfFC);
						       sheet.addCell(labelCk);
						       
						       String c_4 = String.valueOf(c.getHasPayBillFee());
						       labelCk = new Label(6, i, c_4, wcfFC);
						       sheet.addCell(labelCk);
						       
						       String c_5 = String.valueOf(c.getNotPayBillCount());
						       labelCk = new Label(7, i, c_5, wcfFC);
						       sheet.addCell(labelCk);
						       
						       String c_6 = String.valueOf(c.getNotPayBillFee());
						       labelCk = new Label(8, i, c_6, wcfFC);
						       sheet.addCell(labelCk);
						       
						       String c_7 = String.valueOf(c.getPeiKuaiBillCount());
						       labelCk = new Label(9, i, c_7, wcfFC);
						       sheet.addCell(labelCk);
						       
						       String c_8 = String.valueOf(c.getPeiKuanNumber());
						       labelCk = new Label(10, i, c_8, wcfFC);
						       sheet.addCell(labelCk);
						   }
						   workbook.write();
						   workbook.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				return "allCompany" ;
			}
		}
	}
	
//	/**
//	 * 按年导出所有旅行社的数据到Excel
//	 * @return
//	 * @throws Exception
//	 */
//	public String tongJiexport_all() throws Exception {
//		ActionContext actionContext =ActionContext.getContext();
//		Map session = actionContext.getSession();
//	    List contactList = (List)session.get("allC");
//	    
//	       try {
//			int size = contactList.size();
//			   String fileName = "c:\\按年导出的所有旅行社的数据.xls";// 导出文件名
//			   
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
//			   List<Label> labellist = null;
//			   Label labelCk = null;
//			   labelCk = new Label(0, 0, "部门名称", wcfFC);
//			   sheet.addCell(labelCk);
//			   labelCk = new Label(1, 0, "时间", wcfFC);
//			   sheet.addCell(labelCk);
//			   labelCk = new Label(2, 0, "投保申请", wcfFC);
//			   sheet.addCell(labelCk);
//			   labelCk = new Label(3, 0, "审核通过", wcfFC);
//			   sheet.addCell(labelCk);
//			   labelCk = new Label(4, 0, "未通过", wcfFC);
//			   sheet.addCell(labelCk);
//			   labelCk = new Label(5, 0, "以付费份数", wcfFC);
//			   sheet.addCell(labelCk);
//			   labelCk = new Label(6, 0, "以付费金额", wcfFC);
//			   sheet.addCell(labelCk);
//			   labelCk = new Label(7, 0, "未付费份数", wcfFC);
//			   sheet.addCell(labelCk);
//			   labelCk = new Label(8, 0, "未付费金额", wcfFC);
//			   sheet.addCell(labelCk);
//			   labelCk = new Label(9, 0, "赔偿份数", wcfFC);
//			   sheet.addCell(labelCk);
//			   labelCk = new Label(10, 0, "赔偿金额", wcfFC);
//			   sheet.addCell(labelCk);
//			   // 循环将输出内容加到sheet中
//			   for (int i = 1; i < size + 1; i++){
//				   AllCompanyYearStat c = (AllCompanyYearStat) contactList.get(i - 1);
//
//			       String cId = c.getCompany().getStrPartmentName();
//			       labelCk = new Label(0, i, cId, wcfFC);
//			       sheet.addCell(labelCk);
//			       
//			       String cName =String.valueOf(c.getYear());
//			       labelCk = new Label(1, i, cName, wcfFC);
//			       sheet.addCell(labelCk);
//			       
//			       String cWork = String.valueOf(c.getAllBillCount());
//			       labelCk = new Label(2, i, cWork, wcfFC);
//			       sheet.addCell(labelCk);
// 
//			       String c_1 = String.valueOf(c.getHasSureBillCount());
//			       labelCk = new Label(3, i, c_1, wcfFC);
//			       sheet.addCell(labelCk);
//			       
//			       String c_2 = String.valueOf(c.getReturnBillCount());
//			       labelCk = new Label(4, i, c_2, wcfFC);
//			       sheet.addCell(labelCk);
//			       
//			       String c_3 = String.valueOf(c.getHasPayBillCount());
//			       labelCk = new Label(5, i, c_3, wcfFC);
//			       sheet.addCell(labelCk);
//			       
//			       String c_4 = String.valueOf(c.getHasPayBillFee());
//			       labelCk = new Label(6, i, c_4, wcfFC);
//			       sheet.addCell(labelCk);
//			       
//			       String c_5 = String.valueOf(c.getNotPayBillCount());
//			       labelCk = new Label(7, i, c_5, wcfFC);
//			       sheet.addCell(labelCk);
//			       
//			       String c_6 = String.valueOf(c.getNotPayBillFee());
//			       labelCk = new Label(8, i, c_6, wcfFC);
//			       sheet.addCell(labelCk);
//			       
//			       String c_7 = String.valueOf(c.getPeiKuaiBillCount());
//			       labelCk = new Label(9, i, c_7, wcfFC);
//			       sheet.addCell(labelCk);
//			       
//			       String c_8 = String.valueOf(c.getPeiKuanNumber());
//			       labelCk = new Label(10, i, c_8, wcfFC);
//			       sheet.addCell(labelCk);
//			   }
//			   workbook.write();
//			   workbook.close();
//			   session.put("ok", "<center><h1 style='margin-top:40px'>导出成功</h1></center>");
//		} catch (Exception e) {
//			session.put("ok", "<center><h1 style='margin-top:40px'>导出错误，请重试</h1></center>");
//		}
//	       return "SUCCESS";
//	    }

//	/**
//	 * 按年导出该旅行社的数据到Excel
//	 * @return
//	 * @throws Exception
//	 */
//	public String tongJiexport_Year() throws Exception {
//		ActionContext actionContext =ActionContext.getContext();
//		Map session = actionContext.getSession();
//	    List contactList = (List)session.get("list");
//	       try {
//	    	   int size = contactList.size();
//			   System.out.println("集合大小:"+size);
//			   String fileName = "c:\\按年导出的该旅行社的数据.xls";
//			   FileOutputStream fileOutputStream = new FileOutputStream(fileName);
//			   // 创建新的Excel 工作簿
//			   WritableWorkbook workbook = Workbook.createWorkbook(fileOutputStream);
//			   // 生成 Excl 的Sheet
//			   WritableSheet sheet = workbook.createSheet("导出数据", 0);
//			   // 这里是对Cell里面的字体进行格式化
//			   WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10,
//			          WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
//			          jxl.format.Colour.BLACK);
//			   WritableCellFormat format = new WritableCellFormat(wfc);
//			   WritableCellFormat wcfFC = new jxl.write.WritableCellFormat();
//			   // 行居中
//			   wcfFC.setAlignment(Alignment.CENTRE);
//			   // 列居中
//			   wcfFC.setVerticalAlignment(VerticalAlignment.CENTRE);
//			   Label labelCk = null;
//			   labelCk = new Label(0, 0, "部门名称", wcfFC);
//			   sheet.addCell(labelCk);
//			   labelCk = new Label(1, 0, "时间", wcfFC);
//			   sheet.addCell(labelCk);
//			   labelCk = new Label(2, 0, "投保申请", wcfFC);
//			   sheet.addCell(labelCk);
//			   labelCk = new Label(3, 0, "审核通过", wcfFC);
//			   sheet.addCell(labelCk);
//			   labelCk = new Label(4, 0, "未通过", wcfFC);
//			   sheet.addCell(labelCk);
//			   labelCk = new Label(5, 0, "以付费份数", wcfFC);
//			   sheet.addCell(labelCk);
//			   labelCk = new Label(6, 0, "以付费金额", wcfFC);
//			   sheet.addCell(labelCk);
//			   labelCk = new Label(7, 0, "未付费份数", wcfFC);
//			   sheet.addCell(labelCk);
//			   labelCk = new Label(8, 0, "未付费金额", wcfFC);
//			   sheet.addCell(labelCk);
//			   labelCk = new Label(9, 0, "赔偿份数", wcfFC);
//			   sheet.addCell(labelCk);
//			   labelCk = new Label(10, 0, "赔偿金额", wcfFC);
//			   sheet.addCell(labelCk);
//			   // 循环将输出内容加到sheet中
//			   int i = 1;
//			   PartmentYearStat c=null;
//			   for (; i < size + 1;){
//				   c= (PartmentYearStat) contactList.get(i - 1);
//				   TbPartment tb = (TbPartment)session.get("tbPartment"); 
//			       String cId = tb.getStrPartmentName();
//			       labelCk = new Label(0, i, cId, wcfFC);
//			       sheet.addCell(labelCk);
//			       
//			       String cName =String.valueOf(c.getYear());
//			       labelCk = new Label(1, i, cName, wcfFC);
//			       sheet.addCell(labelCk);
//			       
//			       String cWork = String.valueOf(c.getAllBillCount());
//			       labelCk = new Label(2, i, cWork, wcfFC);
//			       sheet.addCell(labelCk);
// 
//			       String c_1 = String.valueOf(c.getHasSureBillCount());
//			       labelCk = new Label(3, i, c_1, wcfFC);
//			       sheet.addCell(labelCk);
//			       
//			       String c_2 = String.valueOf(c.getReturnBillCount());
//			       labelCk = new Label(4, i, c_2, wcfFC);
//			       sheet.addCell(labelCk);
//			       
//			       String c_3 = String.valueOf(c.getHasPayBillCount());
//			       labelCk = new Label(5, i, c_3, wcfFC);
//			       sheet.addCell(labelCk);
//			       
//			       String c_4 = String.valueOf(c.getHasPayBillFee());
//			       labelCk = new Label(6, i, c_4, wcfFC);
//			       sheet.addCell(labelCk);
//			       
//			       String c_5 = String.valueOf(c.getNotPayBillCount());
//			       labelCk = new Label(7, i, c_5, wcfFC);
//			       sheet.addCell(labelCk);
//			       
//			       String c_6 = String.valueOf(c.getNotPayBillFee());
//			       labelCk = new Label(8, i, c_6, wcfFC);
//			       sheet.addCell(labelCk);
//			       
//			       String c_7 = String.valueOf(c.getPeiKuaiBillCount());
//			       labelCk = new Label(9, i, c_7, wcfFC);
//			       sheet.addCell(labelCk);
//			       
//			       String c_8 = String.valueOf(c.getPeiKuanNumber());
//			       labelCk = new Label(10, i, c_8, wcfFC);
//			       sheet.addCell(labelCk);
//			       i++;
//			       System.out.println("添加内容完毕");
//			   }
//			   PartmentYearStat ps = (PartmentYearStat)session.get("ps");
//			   String cId1 ="总计";
//		       labelCk = new Label(0, i, cId1, wcfFC);
//		       sheet.addCell(labelCk);
//		       
//		       String cName1 =String.valueOf(c.getYear());
//		       labelCk = new Label(1, i, cName1, wcfFC);
//		       sheet.addCell(labelCk);
//		       
//		       String cWork1 = String.valueOf(ps.getAllBillCount());
//		       labelCk = new Label(2, i, cWork1, wcfFC);
//		       sheet.addCell(labelCk);
//
//		       String cv_1 = String.valueOf(ps.getHasSureBillCount());
//		       labelCk = new Label(3, i, cv_1, wcfFC);
//		       sheet.addCell(labelCk);
//		       
//		       String cv_2 = String.valueOf(ps.getReturnBillCount());
//		       labelCk = new Label(4, i, cv_2, wcfFC);
//		       sheet.addCell(labelCk);
//		       
//		       String cv_3 = String.valueOf(ps.getHasPayBillCount());
//		       labelCk = new Label(5, i, cv_3, wcfFC);
//		       sheet.addCell(labelCk);
//		       
//		       String cv_4 = String.valueOf(ps.getHasPayBillFee());
//		       labelCk = new Label(6, i, cv_4, wcfFC);
//		       sheet.addCell(labelCk);
//		       
//		       String cv_5 = String.valueOf(ps.getNotPayBillCount());
//		       labelCk = new Label(7, i, cv_5, wcfFC);
//		       sheet.addCell(labelCk);
//		       
//		       String cv_6 = String.valueOf(ps.getNotPayBillFee());
//		       labelCk = new Label(8, i, cv_6, wcfFC);
//		       sheet.addCell(labelCk);
//		       
//		       String cv_7 = String.valueOf(ps.getPeiKuaiBillCount());
//		       labelCk = new Label(9, i, cv_7, wcfFC);
//		       sheet.addCell(labelCk);
//		       
//		       String cv_8 = String.valueOf(ps.getPeiKuanNumber());
//		       labelCk = new Label(10, i, cv_8, wcfFC);
//		       sheet.addCell(labelCk);
//		       
//			   workbook.write();
//			   workbook.close();
//			   session.put("ok", "<center><h1 style='margin-top:40px'>导出成功</h1></center>");
//		} catch (Exception e) {
//			session.put("ok", "<center><h1 style='margin-top:40px'>导出错误，请重试</h1></center>");
//		}
//	       return "SUCCESS";
//	    }
	
//	/**
//	 * 按年导出该部门的数据Excel
//	 * @return
//	 * @throws Exception
//	 */
//	public String tongJiexport() throws Exception {
//		ActionContext actionContext =ActionContext.getContext();
//		Map session = actionContext.getSession();
//	    List contactList = (List)session.get("list");
//	    
//	       try {
//			int size = contactList.size();
//			   String fileName = "c:\\按年导出的该部门的数据.xls";// 导出文件名
//			   
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
//			   List<Label> labellist = null;
//			   Label labelCk = null;
//			   labelCk = new Label(0, 0, "部门名称", wcfFC);
//			   sheet.addCell(labelCk);
//			   labelCk = new Label(1, 0, "时间", wcfFC);
//			   sheet.addCell(labelCk);
//			   labelCk = new Label(2, 0, "投保申请", wcfFC);
//			   sheet.addCell(labelCk);
//			   labelCk = new Label(3, 0, "审核通过", wcfFC);
//			   sheet.addCell(labelCk);
//			   labelCk = new Label(4, 0, "未通过", wcfFC);
//			   sheet.addCell(labelCk);
//			   labelCk = new Label(5, 0, "以付费份数", wcfFC);
//			   sheet.addCell(labelCk);
//			   labelCk = new Label(6, 0, "以付费金额", wcfFC);
//			   sheet.addCell(labelCk);
//			   labelCk = new Label(7, 0, "未付费份数", wcfFC);
//			   sheet.addCell(labelCk);
//			   labelCk = new Label(8, 0, "未付费金额", wcfFC);
//			   sheet.addCell(labelCk);
//			   labelCk = new Label(9, 0, "赔偿份数", wcfFC);
//			   sheet.addCell(labelCk);
//			   labelCk = new Label(10, 0, "赔偿金额", wcfFC);
//			   sheet.addCell(labelCk);
//			   // 循环将输出内容加到sheet中
//			   for (int i = 1; i < size + 1; i++){
//				   PartmentYearStat c = (PartmentYearStat) contactList.get(i - 1);
//				   TbPartment tb = (TbPartment) session.get("tbPartment");
//				   
//			       String cId = tb.getStrPartmentName();
//			       labelCk = new Label(0, i, cId, wcfFC);
//			       sheet.addCell(labelCk);
//			       
//			       String cName =String.valueOf(c.getYear());
//			       labelCk = new Label(1, i, cName, wcfFC);
//			       sheet.addCell(labelCk);
//			       
//			       String cWork = String.valueOf(c.getAllBillCount());
//			       labelCk = new Label(2, i, cWork, wcfFC);
//			       sheet.addCell(labelCk);
// 
//			       String c_1 = String.valueOf(c.getHasSureBillCount());
//			       labelCk = new Label(3, i, c_1, wcfFC);
//			       sheet.addCell(labelCk);
//			       
//			       String c_2 = String.valueOf(c.getReturnBillCount());
//			       labelCk = new Label(4, i, c_2, wcfFC);
//			       sheet.addCell(labelCk);
//			       
//			       String c_3 = String.valueOf(c.getHasPayBillCount());
//			       labelCk = new Label(5, i, c_3, wcfFC);
//			       sheet.addCell(labelCk);
//			       
//			       String c_4 = String.valueOf(c.getHasPayBillFee());
//			       labelCk = new Label(6, i, c_4, wcfFC);
//			       sheet.addCell(labelCk);
//			       
//			       String c_5 = String.valueOf(c.getNotPayBillCount());
//			       labelCk = new Label(7, i, c_5, wcfFC);
//			       sheet.addCell(labelCk);
//			       
//			       String c_6 = String.valueOf(c.getNotPayBillFee());
//			       labelCk = new Label(8, i, c_6, wcfFC);
//			       sheet.addCell(labelCk);
//			       
//			       String c_7 = String.valueOf(c.getPeiKuaiBillCount());
//			       labelCk = new Label(9, i, c_7, wcfFC);
//			       sheet.addCell(labelCk);
//			       
//			       String c_8 = String.valueOf(c.getPeiKuanNumber());
//			       labelCk = new Label(10, i, c_8, wcfFC);
//			       sheet.addCell(labelCk);
//			   }
//			   workbook.write();
//			   workbook.close();
//			   session.put("ok", "<center><h1 style='margin-top:40px'>导出成功</h1></center>");
//		} catch (Exception e) {
//			session.put("ok", "<center><h1 style='margin-top:40px'>导出错误，请重试</h1></center>");
//		}
//	       return "SUCCESS";
//	    }
	
	/**
	 * 对部门按月进行统计
	 * @return
	 */
	public String adminTongJiByMonth(){
		ams = tongJiServ.getPartmentMonthStat(partmentId, year) ;
		return SUCCESS ;
	}
	
	
	
	/**
	 * 对部门按天进行统计
	 * @return
	 */
	public String adminTongJiByDay(){
		ads = tongJiServ.getPartmentDayStat(partmentId, year, month);
		return SUCCESS ;
	}
	
	public List<AllCompanyYearStat> getAllCompanyYearStatList() {
		return allCompanyYearStatList;
	}

	public void setAllCompanyYearStatList(
			List<AllCompanyYearStat> allCompanyYearStatList) {
		this.allCompanyYearStatList = allCompanyYearStatList;
	}

	public ITongJiServ getTongJiServ() {
		return tongJiServ;
	}

	public void setTongJiServ(ITongJiServ tongJiServ) {
		this.tongJiServ = tongJiServ;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public List<PartmentYearStat> getPartmentYearStatList() {
		return partmentYearStatList;
	}

	public void setPartmentYearStatList(List<PartmentYearStat> partmentYearStatList) {
		this.partmentYearStatList = partmentYearStatList;
	}

	public TbPartment getCompany() {
		return company;
	}

	public void setCompany(TbPartment company) {
		this.company = company;
	}

	public TbPartment getTbPartment() {
		return tbPartment;
	}

	public void setTbPartment(TbPartment tbPartment) {
		this.tbPartment = tbPartment;
	}

	public IAdminPartmentServ getAdminPartmentServ() {
		return adminPartmentServ;
	}

	public void setAdminPartmentServ(IAdminPartmentServ adminPartmentServ) {
		this.adminPartmentServ = adminPartmentServ;
	}



	public List<TbPartment> getCompanyList() {
		return companyList;
	}



	public void setCompanyList(List<TbPartment> companyList) {
		this.companyList = companyList;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getPartmentId() {
		return partmentId;
	}

	public void setPartmentId(int partmentId) {
		this.partmentId = partmentId;
	}

	public AbstractPartmentMonthStat getAms() {
		return ams;
	}

	public void setAms(AbstractPartmentMonthStat ams) {
		this.ams = ams;
	}

	public AbstractPartmentDayStat getAds() {
		return ads;
	}

	public void setAds(AbstractPartmentDayStat ads) {
		this.ads = ads;
	}
}
