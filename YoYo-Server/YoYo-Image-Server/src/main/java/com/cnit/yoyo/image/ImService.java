package com.cnit.yoyo.image; 

import java.io.File;
import java.io.IOException;

/** 
 * @author  wanghb
 * @version V1.0 
 * @createDateTime：2015-05-12
 * @Company: cnit
 * @Copyright: Copyright (c) 2015
 **/
public interface ImService {
	/**
	 * 上传图片，该方法不对图片进行压缩
	 * 
	 * @param file
	 * @return
	 */
	public Result UploadImage(File file,String filePath) throws IOException;
	/**
	 * 上传图片临时文件
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public Result UploadTempImage(File file,String filePath) throws IOException;
	/**
	 * 上传图片,用户自定义长及宽
	 * @param imgHeight 
	 * @param imgWidth
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public Result UploadResizeImage(int imgHeight,int imgWidth,File file,String filePath) throws IOException;
	/**
	 * @param file
	 * file为文件上传后的在服务端的临时文件,struts2适用
	 */
	public Result writeImage(File file,String filePath) throws IOException;
	/**
	 * 检查文件大小
	 * 
	 * @param file
	 * @return
	 */
	public boolean checkFileSize(File file);
	/**
	 * 判断文件是否符合规矩 <br>
	 * 图片的格式为:gif,jpg,png
	 * @param file
	 * @return
	 */
	public boolean checkImageTypeVailable(File file);

}
 