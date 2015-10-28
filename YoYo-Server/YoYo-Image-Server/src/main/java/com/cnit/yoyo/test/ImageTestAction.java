package com.cnit.yoyo.test; 

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.cnit.yoyo.commons.file.GMagickManager;
import com.cnit.yoyo.commons.file.ImageManager;
import com.cnit.yoyo.image.Result;
import com.cnit.yoyo.util.Configuration;
import com.cnit.yoyo.util.ConstantURL;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


/** 
 * @author  wanghb
 * @version V1.0 
 * @createDateTime：2015-05-12
 * @Company: cnit
 * @Copyright: Copyright (c) 2015
 **/
public class ImageTestAction extends ActionSupport {
	private final static Log log=LogFactory.getLog(ImageTestAction.class);
	private File fileImage;
	private ImageManager imageManager ;
	private GMagickManager gmagickManager ;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String initImage(){
		return SUCCESS;
	}
	public String saveImage() throws IOException{
		HttpServletRequest request=ServletActionContext.getRequest();
		String sel=request.getParameter("imageType");
		Result result=null;
		if(sel.equals("0")){
			System.setProperty("jmagick.systemclassloader","no");
			log.info("java.library.path:"+System.getProperty("java.library.path"));
			result=	imageManager.writeImage(fileImage,"product");
		}else if(sel.equals("1")){
			result=gmagickManager.writeImage(fileImage,"product");
		}
		if(result.isSuccess()){
			String url= Configuration.getInstance().getConfigValue(ConstantURL.IMAGEURL)+result.getModels().get("fileName")+"."+result.getModels().get("suffix");
			log.info("URL:"+url);
			ActionContext.getContext().put("msg", "图片上传成功！");
			ActionContext.getContext().put("url",url);
		}else{
			ActionContext.getContext().put("msg", "图片上传失败！");
			log.info("错误原因:"+result.getError());
		}
		return SUCCESS;
	}
	public File getFileImage() {
		return fileImage;
	}
	public void setFileImage(File fileImage) {
		this.fileImage = fileImage;
	}
	public ImageManager getImageManager() {
		return imageManager;
	}
	public void setImageManager(ImageManager imageManager) {
		this.imageManager = imageManager;
	}
	public GMagickManager getGmagickManager() {
		return gmagickManager;
	}
	public void setGmagickManager(GMagickManager gmagickManager) {
		this.gmagickManager = gmagickManager;
	}

}
 