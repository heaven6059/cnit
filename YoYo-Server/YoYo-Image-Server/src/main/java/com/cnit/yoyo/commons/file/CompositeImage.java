package com.cnit.yoyo.commons.file;

import java.io.File;

import com.cnit.yoyo.image.Result;
/** 
 * @author  wanghb
 * @version V1.0 
 * @createDateTime：2015-05-12
 * @Company: cnit
 * @Copyright: Copyright (c) 2015
 **/
public interface CompositeImage {
	/**
	 * 
	 * 设置文字水印
	 * 
	 * @param imageName
	 *            图片名字
	 * @param text
	 *            水印文字
	 * @param space
	 *            位置 1 顶部居左，2 顶部居中，3 顶部居右，4左部居中，5 图片中心，6右部居中 7底部居左 ，8 底部居中，9
	 *            底部居右
	 *@param opacity  透明度 0 - 100, 0 为不透明            
	 * @return
	 */
	Result annotateImage(String imageName, String text, int space,int opacity);

	/**
	 * 设置图片水印
	 * 
	 * @param imageName
	 * @param logName
	 * @param apace
	 *            位置 1 顶部居左，2 顶部居中，3 顶部居右，4左部居中，5 图片中心，6右部居中 7底部居左 ，8 底部居中，9
	 *            底部居右
	 * @return
	 */
	Result CompositeLogoToImage(String imageName, String logName, int apace,int opacity);
	
	
	Result CompositeLogoToImage(String imageName, File logfile, int apace);
	
	Result annotateImage(String imageName, String text, int space,int opacity,String fontType,String fontColor,String bgColor,int fontSize);
}
