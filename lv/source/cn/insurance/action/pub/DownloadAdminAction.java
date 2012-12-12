package cn.insurance.action.pub;

import java.io.File;
import java.io.FileInputStream;
import cn.insurance.utils.ReadConfig;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionSupport;

/**
 * 管理文件下载
 * @author zhuyan
 *
 */
public class DownloadAdminAction extends ActionSupport{

	private static final long serialVersionUID = 1L;

	/*文件的地址（相对地址，也就是数据库中保存的地址）*/
	private String filePath ;
	
	private String fileName ;
	
	private FileInputStream travelerFileInputStream ;
	
	/**
	 * 因为直接在页面放文件链接下载会出现在页面直接打开的问题，
	 * 所以这里专门写一个文件下载的管理工具
	 * @return
	 */
	public String downloadFile(){
		try{
			String path = ReadConfig.getString("UPLOAD_PATH") ;
			String fileAbsPath = path + "/" + filePath ;
			File file = new File(fileAbsPath) ;
			fileName = file.getName() ;
			/*验证文件下载路径，只能下载travelerFile和public/file目录下的文件*/
			if(!filePath.startsWith("travelerFile")){
				/*如果不是这个路径，不准下载，防止下载web-inf下的文件*/
				addActionError("对不起，该文件不存在！") ;
				return INPUT ;
			}
			if(!file.exists()){
				addActionError("对不起，该文件不存在，可能已被删除！") ;
				return INPUT ;
			}
			travelerFileInputStream  = new FileInputStream(file) ;
		}catch(Exception e){
			return INPUT ;
		}
		return SUCCESS ;
	}
	
	
	/**
	 * 这个是直接写的路径下载
	 * 从file文件夹里下载文件，主要是实现约定好的文件
	 * @return
	 */
	public String downloadFileBypath(){
		try{
			//制定路径到容器的pub、file目录下
			String fileAbsPath = ServletActionContext.getServletContext().getRealPath("/pub/file")+"/"+ filePath ;
			System.out.println(fileAbsPath) ;
			File file = new File(fileAbsPath) ;
			fileName = file.getName() ;
			if(!file.exists()){
				addActionError("对不起，该文件不存在，可能已被删除！") ;
				return INPUT ;
			}
			travelerFileInputStream  = new FileInputStream(file) ;
		}catch(Exception e){
			return INPUT ;
		}
		return SUCCESS ;
	}
	
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public FileInputStream getTravelerFileInputStream() {
		return travelerFileInputStream;
	}

	public void setTravelerFileInputStream(FileInputStream travelerFileInputStream) {
		this.travelerFileInputStream = travelerFileInputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
