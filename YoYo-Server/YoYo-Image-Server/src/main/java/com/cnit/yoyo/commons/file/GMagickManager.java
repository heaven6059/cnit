package com.cnit.yoyo.commons.file; 

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.cnit.yoyo.image.Result;
/** 
 * @author  wanghb
 * @version V1.0 
 * @createDateTime：2015-05-12
 * @Company: cnit
 * @Copyright: Copyright (c) 2015
 **/
public interface GMagickManager {
	/**
	 * @param file
	 * file为文件上传后的在服务端的临时文件,struts2适用
	 */
	public Result writeImage(File file,String filePath) throws IOException;

	/**
	 * 判断文件是否符合规矩 <br>
	 * 图片的格式为:gif,jpg,png
	 * @param file
	 * @return
	 */
	public boolean checkImageTypeVailable(File file);

	/**
	 * 上传图片，该方法不对图片进行压缩
	 * 
	 * @param file
	 * @return
	 */
	public Result UploadImage(File file,String filePath) throws IOException;

	/**
	 * 组合图片名称 例如：根据A.jpg 获得小图 A.100x100.jpg
	 */
	public String imageCombine(String size, String imageName) throws Exception;

	/**
	 * 找到图片物理地址进行删除
	 * 
	 * @param imageurl 为图片的物理地址
	 */
	public void deleteFile(String imageurl);

	/**
	 * 删除图片文件
	 * 
	 * 改方法适用于商品图片
	 */
	public boolean delPhysicalImage(List<String> listdel, int sellerId);

	/**
	 * 检查文件大小
	 * 
	 * @param file
	 * @return
	 */
	public boolean checkFileSize(File file);
	/**
	 * 创建图片
	 * @param imageurl 图片的URL
	 * @return
	 */
	public Result createImage(String imageurl,String path);
	/**
	 * 获取图片类型
	 * @param file 图片
	 * @return
	 */
	public String getImageType(File file);
	public  void addImgText(String srcPath);
	/**
	 * 上传图片,用户自定义长及宽
	 * @param imgHeight 
	 * @param imgWidth
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public Result UploadResizeImage(int imgHeight,int imgWidth,File file,String filePath) throws IOException;
	

}
 