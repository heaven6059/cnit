package com.cnit.yoyo.file.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;







import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.file.FileDataInerface;
import com.cnit.yoyo.image.impl.HessianInerfaceImp;
import com.cnit.yoyo.util.Configuration;
import com.cnit.yoyo.util.ConstantURL;
@Service("fileDataInerface")
public class FileDataIntergfaceImpl  implements FileDataInerface {
	
	private static final Log logger = LogFactory.getLog(HessianInerfaceImp.class);
    Map<String,String> fileNameMap = new HashMap<String, String>();
    Map<String, OutputStream> os = new HashMap<String, OutputStream>();
 
    public FileDataIntergfaceImpl() throws RemoteException {
        super();
    }
     
    private String getFilePath(String fileType){
    	return Configuration.getInstance().getConfigValue(ConstantURL.FILEPATH, "/root/YOYO/Files")+"/"+fileType;
    }
    public String start(String fileType,String suffix){
    	String baseUrl=Configuration.getInstance().getConfigValue(ConstantURL.FILEPATH, "/root/YOYO/Files");
    	String fileName = System.currentTimeMillis() + "."+suffix;
    	SimpleDateFormat dateFm = new SimpleDateFormat("yyyyMM");
		String filePath = getFilePath(fileType) + "/" + dateFm.format(new Date());
		String uploadId = UUID.randomUUID().toString();
    	File file = null;
		try {
			File myPath1 = new File(filePath);
			if (!myPath1.exists()) {
				myPath1.mkdirs();
			} 
			file = new File(filePath + "/" + fileName);
            if (!file.exists())
                file.createNewFile();
            OutputStream upOS = new BufferedOutputStream(new FileOutputStream(file));
            os.put(uploadId, upOS);
            fileNameMap.put(uploadId, filePath.replace(baseUrl, "")+ "/" + fileName);
        }catch (Exception e) {
        	logger.error("初始化文件上传失败：" + e.getMessage());
        	return null;
        }
		return uploadId;
    }
 
    public void upload(String uploadId,byte[] part,int offset,int len) {
        OutputStream upOS = os.get(uploadId);
        if(upOS == null){
        	logger.error("上传文件的id不存在:" + uploadId);
        }
        try {
            upOS.write(part,offset,len);
        } catch (Exception e) {
        	logger.error("File Up[" + uploadId+"]Exception:",e);
        }
    }
     
     
    public String finish(String uploadId){
        String fileName=fileNameMap.get(uploadId);
    	try {
            os.get(uploadId).close();
            os.remove(uploadId);
            fileNameMap.remove(uploadId);
        } catch (IOException e) {
        	logger.error("结束文件上传失败：",e);
        }
		return fileName;
    }
 
}