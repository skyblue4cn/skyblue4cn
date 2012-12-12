package cn.insurance.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.web.context.WebApplicationContext;

import com.opensymphony.xwork.ActionContext;

public class FileUpload {
	 /**
     * 上传文件。如果文件存在，则将文件删除。
     * @param file
     * @param fileSet �ļ���
     * @param fileName
     * @return
     * @throws IOException
     */
    public static File  uploadFile(File file,String fileSet,String fileName) throws IOException
    {
        //File f=getContextRootResource();
        String path=ReadConfig.getString("UPLOAD_PATH").trim();
        
        String newPath=path+"\\"+fileSet;
        String newFilePath=newPath+"\\"+fileName;
        File fs=new File(newPath);
        //System.out.println( fs.getPath());
        fs.mkdirs();
        File newF=new File(newFilePath);
        if (newF.exists())
        {
            newF.delete();
        }
        file.renameTo(newF);
    
        return newF;
    }
    
    /**
     * 删除一个文件。
     * @param realPaht 文件的相对路径，在方法中，需要对相对路径转化成绝对路径后使用。
     * @return boolean
     * @throws IOException
     */
    public boolean delFile(String realPath) throws IOException
    {
        boolean b=false;
        File file=getContextRootResource();
        String rootPath=file.getPath();
        String filePath=rootPath+"\\"+realPath;
        File delFile=new File(filePath);
        if(delFile.exists())
        {
            b=delFile.delete();
        }
        return b;
    }
    
    private  static File getContextRootResource()throws IOException
    {
        WebApplicationContext context = (WebApplicationContext)
        
        ActionContext.getContext().getApplication().get( 
              WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        Resource loader = context.getResource("");      
        File file = loader.getFile();
        return file;
    }
	
}
