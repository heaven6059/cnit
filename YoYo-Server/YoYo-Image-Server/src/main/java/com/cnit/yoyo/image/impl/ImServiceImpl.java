package com.cnit.yoyo.image.impl;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.cnit.yoyo.commons.file.GMagickManager;
import com.cnit.yoyo.commons.file.ImageManager;
import com.cnit.yoyo.image.ImService;
import com.cnit.yoyo.image.Result;
import com.cnit.yoyo.image.ResultSupport;
import com.cnit.yoyo.util.Configuration;
import com.cnit.yoyo.util.ConstantURL;

/**
 * @author wanghb
 * @version V1.0
 * @createDateTime：2015-05-12
 * @Company: cnit
 * @Copyright: Copyright (c) 2015
 **/
@Service("imService")
public class ImServiceImpl implements ImService {

	private ImageManager imageManager;
	private GMagickManager gmagickManager;
	private ImageManager uploadJMagick;
	private GMagickManager uploadGMagick;
	// TRUE为JMagick False为GraphicsMagick
	private boolean magick = true;

	@Override
	public Result UploadImage(File file, String filePath) throws IOException {
		Result result = new ResultSupport();
		getMagick();
		if (magick) {
			result = imageManager.UploadImage(file, filePath);
		} else {
			result = gmagickManager.UploadImage(file, filePath);
		}
		return result;
	}

	@Override
	public Result UploadResizeImage(int imgHeight, int imgWidth, File file, String filePath) throws IOException {
		Result result = new ResultSupport();
		getMagick();
		if (magick) {
			result = imageManager.UploadResizeImage(imgHeight, imgWidth, file, filePath);
		} else {
			result = gmagickManager.UploadResizeImage(imgHeight, imgWidth, file,filePath);
		}
		return result;
	}

	@Override
	public Result writeImage(File file,String filePath) throws IOException {
		Result result = new ResultSupport();
		getMagick();
		if (magick) {
			result = imageManager.writeImage(file,filePath);
		} else {
			result = gmagickManager.writeImage(file,filePath);
		}
		file.delete();
		return result;
	}

	@Override
	public boolean checkFileSize(File file) {
		boolean val;
		getMagick();
		if (magick) {
			val = imageManager.checkFileSize(file);
		} else {
			val = gmagickManager.checkFileSize(file);
		}
		return val;
	}

	@Override
	public boolean checkImageTypeVailable(File file) {
		boolean val;
		getMagick();
		if (magick) {
			val = imageManager.checkImageTypeVailable(file);
		} else {
			val = gmagickManager.checkImageTypeVailable(file);
		}
		return val;
	}

	@Override
	public Result UploadTempImage(File file,String filePath) throws IOException {
		// TODO Auto-generated method stub
		Result result = new ResultSupport();
		getMagick();
		if (magick) {
			result = uploadJMagick.writeImage(file,filePath);
		} else {
			result = uploadGMagick.writeImage(file,filePath);
		}
		return result;
	}

	private void getMagick() {
		String flag = Configuration.getInstance().getConfigValue(ConstantURL.JORGMAGICK, "0");
		if ("1".equals(flag)) {
			magick = false;
		}
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

	public ImageManager getUploadJMagick() {
		return uploadJMagick;
	}

	public void setUploadJMagick(ImageManager uploadJMagick) {
		this.uploadJMagick = uploadJMagick;
	}

	public GMagickManager getUploadGMagick() {
		return uploadGMagick;
	}

	public void setUploadGMagick(GMagickManager uploadGMagick) {
		this.uploadGMagick = uploadGMagick;
	}
}
