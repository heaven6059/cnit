package com.cnit.yoyo.image.impl;

import java.io.*;

import magick.ImageInfo;
import magick.MagickApiException;
import magick.MagickException;
import magick.MagickImage;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.dto.ImagesDTO;
import com.cnit.yoyo.image.HessianInerface;
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
@Service("hessianInerface")
public class HessianInerfaceImp implements HessianInerface {

	private static final Log logger = LogFactory.getLog(HessianInerfaceImp.class);
	public static final String UploadImage = "UploadImage";
	public static final String UploadTempImage = "UploadTempImage";
	public static final String UploadResizeImage = "UploadResizeImage";
	public static final String WriteImage = "WriteImage";
	@Autowired
	private ImService imService;

	@Override
	public ImagesDTO uploadSingleFile(byte[] buffer, String filePath) {
		String fileName=System.nanoTime()+".jpg";
		File file = uploadSingleBigFile(buffer, fileName, false);
		Result result = new ResultSupport();
		try {
			logger.info("-----------图片服务器正在处理图片---------------");
//			if (temp.equals(UploadImage)) {
//				result = imService.UploadImage(file,filePath);
//			} else if (temp.equals(UploadTempImage)) {
//				result = imService.UploadTempImage(file,filePath);
//			} else if (temp.equals(WriteImage)) {
				result = imService.writeImage(file,filePath);
//			}
			logger.info("-----------图片服务器正在处理图片完毕------------------");
		} catch (IOException e) {
			logger.error("图片上传到图片服务器失败,原因：" + e.getMessage());
			result.setSuccess(false);
			result.setError(e.getMessage());
		}
		
		ImagesDTO imagesDTO = new ImagesDTO();
		imagesDTO.setFileName(result.getDefaultModel().toString());
		imagesDTO.setSuffix(result.getModels().get("suffix").toString());
		return imagesDTO;
	}

	@Override
	public String uploadStream(String filename, InputStream data) {
		String filePath = Configuration.getInstance().getConfigValue(ConstantURL.TEMPIMAGEPATH, "tempImagePath");
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(filePath + "/" + filename);
			IOUtils.copyLarge(data, out);
			out.flush();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(data);
		}
		return "";
	}

	@Override
	public File uploadSingleBigFile(byte[] buffer, String fileName, boolean isAppand) {
		String filePath = Configuration.getInstance().getConfigValue(ConstantURL.TEMPIMAGEPATH, "tempImagePath");
		FileOutputStream fos = null;
		File file = null;
		try {
			File myPath1 = new File(filePath);
			if (!myPath1.exists()) {
				myPath1.mkdir();
			} 
			file = new File(filePath + "/" + fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			fos = new FileOutputStream(file);

			fos.write(buffer);
			fos.flush();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			IOUtils.closeQuietly(fos);

		}
		return file;
	}

	/*
	 * @Override public byte[] downloadSingleFile(Long attachID) { if (attachID
	 * == null) throw new RuntimeException("下载文件为空"); File_commonDomain
	 * attachDomain = new File_commonDomain(); attachDomain.setId(attachID);
	 * attachDomain = (File_commonDomain) attachDomain.load();
	 * 
	 * File temp = new File(WebContext.getRealPath("/") +
	 * attachDomain.getFilepath());
	 * 
	 * FileInputStream fis = null; byte[] result; try { fis = new
	 * FileInputStream(temp); result = new byte[fis.available()];
	 * fis.read(result);
	 * 
	 * } catch (FileNotFoundException e) { throw new RuntimeException(e); }
	 * catch (IOException e) { throw new RuntimeException(e); } finally {
	 * IOUtils.closeQuietly(fis);
	 * 
	 * } return result;
	 * 
	 * }
	 * 
	 * @Override public byte[] downloadSingleFileForLen(Long attachID, long
	 * startIndex, long length) { if (attachID == null) throw new
	 * RuntimeException("下载文件为空");
	 * 
	 * File_commonDomain attachDomain = new File_commonDomain();
	 * attachDomain.setId(attachID); attachDomain = (File_commonDomain)
	 * attachDomain.load();
	 * 
	 * File temp = new File(WebContext.getRealPath("/") +
	 * attachDomain.getFilepath());
	 * 
	 * RandomAccessFile raf = null; byte[] result; try { // fis = new
	 * FileInputStream(temp); result = new byte[(int) length]; //
	 * fis.read(result, (int) startIndex, (int) length);
	 * 
	 * raf = new RandomAccessFile(temp, "r"); raf.seek(startIndex);
	 * raf.read(result);
	 * 
	 * } catch (FileNotFoundException e) { throw new RuntimeException(e); }
	 * catch (IOException e) { throw new RuntimeException(e); } finally { if
	 * (raf != null) { try { raf.close(); } catch (IOException e) { throw new
	 * RuntimeException(e); } }
	 * 
	 * } return result;
	 * 
	 * }
	 * 
	 * @Override public long getDownloadFileSize(Long attachID) { if (attachID
	 * == null) throw new RuntimeException("下载文件为空");
	 * 
	 * File_commonDomain attachDomain = new File_commonDomain();
	 * attachDomain.setId(attachID); attachDomain = (File_commonDomain)
	 * attachDomain.load();
	 * 
	 * File temp = new File(WebContext.getRealPath("/") +
	 * attachDomain.getFilepath()); if (!temp.exists()) { return 0; }
	 * 
	 * return temp.length();
	 * 
	 * }
	 */
	@SuppressWarnings("unused")
	private String getRealPath(String rootpath, String year, String month, String day) {
		File yearFile = new File(rootpath + "/" + year);
		if (!yearFile.exists()) {
			yearFile.mkdir();
		}
		File monthFile = new File(rootpath + "/" + year + "/" + month);
		if (!monthFile.exists()) {
			monthFile.mkdir();
		}
		File dayFile = new File(rootpath + "/" + year + "/" + month + "/" + day);
		if (!dayFile.exists()) {
			dayFile.mkdir();
		}
		File upFile = new File(rootpath + "/" + year + "/" + month + "/" + day + "/webupload");
		if (!upFile.exists()) {
			upFile.mkdir();
		}
		return rootpath + "/" + year + "/" + month + "/" + day + "/webupload";
	}
}
