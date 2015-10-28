package com.cnit.yoyo.image;

import java.io.File;
import java.io.InputStream;

import com.cnit.yoyo.dto.ImagesDTO;

/**
 * @author wanghb
 * @version V1.0
 * @createDateTime：2015-05-12
 * @Company: cnit
 * @Copyright: Copyright (c) 2015
 **/
public interface HessianInerface {
	/**
	 * 
	 * @param buffer 图片流
	 * @param filePath 图片存放路径
	 * @param suffix 类型（jpg、png等）
	 * @return 返回逻辑路径
	 */
	public ImagesDTO uploadSingleFile(byte[] buffer,String filePath);

	public String uploadStream(String filename, InputStream data);

	/**
	 * 
	 * @param buffer
	 * @param fileName
	 * @param isAppand
	 *            是否追加到原文件
	 * @return
	 */
	public File uploadSingleBigFile(byte[] buffer, String fileName, boolean isAppand);

}
