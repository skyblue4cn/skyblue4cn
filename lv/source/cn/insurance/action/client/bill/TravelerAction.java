package cn.insurance.action.client.bill;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.opensymphony.xwork.ModelDriven;

import cn.insurance.action.SupportAction;
import cn.insurance.model.TbTravelerInfo;

/**
 * 管理保单上的游客信息
 * @author yqg
 *
 */
public class TravelerAction extends SupportAction implements ModelDriven{
	
	public TbTravelerInfo tbTravelerInfo = new TbTravelerInfo() ;
	
	private File travelerFile ;
	
	private String travelerFileFileName ;
	
	private String travelerFileContentType ;
	
	private List<TbTravelerInfo> tbTravelerInfoList ;
	
	

	public Object getModel() {
		return tbTravelerInfo;
	}
	
	
	/**
	 * 上传excel文件并导入
	 * @return
	 */
	public String uploadTravelerFile(){
		if(travelerFileFileName.lastIndexOf(".xls")<0){
			addActionError("文件格式不正确，只能导入excel文件！") ;
			return INPUT ;
		}
		tbTravelerInfoList = readExcelFile(travelerFile.getAbsolutePath()) ;
		if(tbTravelerInfoList == null){
			addActionError("导入错误，请查看文件格式是否符合要求！") ;
			return INPUT ;
		}
		return SUCCESS ;
	}

	/**
	 * 读取上传的excel文件
	 * @param filePath
	 * @return
	 */
	private List<TbTravelerInfo> readExcelFile(String filePath){
		List<TbTravelerInfo> all = new ArrayList<TbTravelerInfo>() ;
		try{
			FileInputStream fs = new FileInputStream(filePath) ;
			//创建一个HSSFWorkbook 对象
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			//通过名字来获取一个sheet 
			HSSFSheet sheet = wb.getSheetAt(0);
			//求出最大行
			int t = sheet.getLastRowNum();
			//从第二行开始读起
			for(int i=1 ;i<=t ;i++){
				TbTravelerInfo tt = new TbTravelerInfo() ;
				HSSFRow row = sheet.getRow(i) ;
				HSSFCell cell1 = row.getCell((short)1) ;
				HSSFCell cell2 = row.getCell((short)2) ;
				HSSFCell cell3 = row.getCell((short)3) ;
				HSSFCell cell4 = row.getCell((short)4) ;
				HSSFCell cell5 = row.getCell((short)5) ;
				tt.setStrTravelerName(getCellToString(cell1)) ;
				tt.setStrSex(getCellToString(cell2)) ;
				tt.setStrBirthday(getCellToString(cell3)) ;
				tt.setStrCountry(getCellToString(cell4)) ;	
				tt.setStrIndentyNumber(getCellToString(cell5)) ;
				all.add(tt) ;
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage()) ;
			return null ;
		}
		return all ;
	}
	
	/**
	 * 根据读取的cell得到值
	 * @param cell
	 * @return
	 */
	private String getCellToString(HSSFCell cell){
		if(cell == null) {
			return null ;
		}
		if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
			Double db = cell.getNumericCellValue() ;
			return String.valueOf(db.intValue()) ;
		}else{
			return cell.getStringCellValue() ;
		}
	}
	
	public File getTravelerFile() {
		return travelerFile;
	}

	public void setTravelerFile(File travelerFile) {
		this.travelerFile = travelerFile;
	}

	public String getTravelerFileFileName() {
		return travelerFileFileName;
	}

	public void setTravelerFileFileName(String travelerFileFileName) {
		this.travelerFileFileName = travelerFileFileName;
	}

	public String getTravelerFileContentType() {
		return travelerFileContentType;
	}

	public void setTravelerFileContentType(String travelerFileContentType) {
		this.travelerFileContentType = travelerFileContentType;
	}

	public List<TbTravelerInfo> getTbTravelerInfoList() {
		return tbTravelerInfoList;
	}

	public void setTbTravelerInfoList(List<TbTravelerInfo> tbTravelerInfoList) {
		this.tbTravelerInfoList = tbTravelerInfoList;
	}
	
}
