package com.cnit.yoyo.commons.file.impl;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import magick.CompositeOperator;
import magick.DrawInfo;
import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;
import magick.PixelPacket;

import com.cnit.yoyo.commons.file.CompositeImage;
import com.cnit.yoyo.image.Result;
import com.cnit.yoyo.image.ResultSupport;

public class DefaultCompositeImage implements CompositeImage {
	private String imagePath; // 图片路径
	private String fontPath;

	public String getFontPath() {
		return fontPath;
	}

	public void setFontPath(String fontPath) {
		this.fontPath = fontPath;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public boolean checkImageTypeVailable(File file) {
		return this.getImageType(file) != null;
	}

	public String getImageType(File file) {
		if (file == null) {
			return null;
		}
		try {
			byte[] imgContent = FileUtils.readFileToByteArray(file);
			
			int len = imgContent.length;
			byte n1 = imgContent[len - 2];
			byte n2 = imgContent[len - 1];
			byte b0 = imgContent[0];
			byte b1 = imgContent[1];
			byte b2 = imgContent[2];
			byte b3 = imgContent[3];
			byte b4 = imgContent[4];
			byte b5 = imgContent[5];
			byte b6 = imgContent[6];
			byte b7 = imgContent[7];
			byte b8 = imgContent[8];
			byte b9 = imgContent[9];

			// GIF(G I F 8 7 a)
			if (b0 == (byte) 'G' && b1 == (byte) 'I' && b2 == (byte) 'F' && b3 == (byte) '8'
					&& b4 == (byte) '7' && b5 == (byte) 'a') {
				return "gif";
				// GIF(G I F 8 9 a)
			} else if (b0 == (byte) 'G' && b1 == (byte) 'I' && b2 == (byte) 'F' && b3 == (byte) '8'
					&& b4 == (byte) '9' && b5 == (byte) 'a') {
				return "gif";
				// PNG(89 P N G 0D 0A 1A)
			} else if (b0 == -119 && b1 == (byte) 'P' && b2 == (byte) 'N' && b3 == (byte) 'G'
					&& b4 == 13 && b5 == 10 && b6 == 26) {
				return "png";
				// JPG JPEG(FF D8 --- FF D9)
			} else if (b0 == -1 && b1 == -40 && n1 == -1 && n2 == -39) {
				return "jpg";
			} else if (b6 == (byte) 'J' && b7 == (byte) 'F' && b8 == (byte) 'I' && b9 == (byte) 'F') {
				return "jpg";
			} else if (b6 == (byte) 'E' && b7 == (byte) 'x' && b8 == (byte) 'i' && b9 == (byte) 'f') {
				return "jpg";
			}
			// else if (b0 == (byte) 'B' && b1 == (byte) 'M') {
			// return true;
			// }
			else {
				return null;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	public Result annotateImage(String imageName, String text, int space, int opacity) {
		Result result = new ResultSupport();
		String filePath = this.imagePath + "/" + imageName;
		if (!this.checkImageTypeVailable(new File(filePath))) {
			result.setError("文件类型错误");
			result.setSuccess(false);
			return result;
		}
		try {
			ImageInfo info = new ImageInfo(filePath);

			// if (filePath.toUpperCase().endsWith("JPG") ||
			// filePath.toUpperCase().endsWith("JPEG")) {
			// info.setCompression(CompressionType.JPEGCompression); //
			// 压缩类别为JPEG格式
			// info.setPreviewType(PreviewType.JPEGPreview); // 预览格式为JPEG格式
			// info.setQuality(95);
			// }

			MagickImage aImage = new MagickImage(info);

			DrawInfo aInfo = new DrawInfo(info);

			aInfo.setFill(PixelPacket.queryColorDatabase("#000000"));
			aInfo.setUnderColor(PixelPacket.queryColorDatabase("yellow"));
			aInfo.setOpacity(0);
			aInfo.setPointsize(14);

			// aInfo.setFont("Arial"); // 英文使用此字体也可

			// 注意这里解决中文字体问题，有以下两行才可正常显示
			// String fontPath = "/home/simhei.ttf";

			aInfo.setFont(this.fontPath + "simhei.ttf");
			aInfo.setTextAntialias(true);

			aInfo.setText(text);
			aInfo.setGeometry("+5+20");
			aInfo.setOpacity(opacity);
			aImage.annotateImage(aInfo);
			// 覆盖原来图片
			aImage.setFileName(filePath);
			aImage.writeImage(info);
			aImage.destroyImages();
			aImage = null;
			result.setSuccess(true);
		} catch (MagickException e) {
			result.setSuccess(false);
			result.setError(e);
		}
		return result;
	}

	public Result CompositeLogoToImage(String imageName, String logName, int apace, int opacity) {
		try {
			ImageInfo info = new ImageInfo();
			MagickImage image = null;
			MagickImage mask = null;
			MagickImage dest = null;

			image = new MagickImage(new ImageInfo(this.imagePath + "/" + imageName));
			mask = new MagickImage(new ImageInfo(this.imagePath + "/" + logName));
			image.setFileName(this.imagePath + "/" + imageName);
			image.writeImage(info);
			dest = new MagickImage(new ImageInfo(this.imagePath + "/" + imageName));
			dest.compositeImage(CompositeOperator.AtopCompositeOp, mask, 20, 20);
			dest.setFileName(this.imagePath + "/" + imageName);
			dest.writeImage(info);
			dest.destroyImages();
		} catch (MagickException ex) {
			System.out.println(ex);
		}
		return null;
	}

	public Result CompositeLogoToImage(String imageName, File logfile, int space) {

		double x = 0, y = 0;
		try {
			ImageInfo info = new ImageInfo();
			MagickImage image = null;
			MagickImage mask = null;
			MagickImage dest = null;

			image = new MagickImage(new ImageInfo(this.imagePath + "/" + imageName));
			Dimension imageDim = image.getDimension();
			double width = imageDim.getWidth();
			double height = imageDim.getHeight();			       
	       if(space % 3==1){
	    	   x = width -width*3/3; 
	       }else if(space % 3==2){
	    	   x = width -width*2/3; 
	       }else if(space % 3==0){
	    	   x = width -width*1/3; 
	       }
	       if(space <=3){
	    	   y = height-height*3/3;
	       }else if(space <=6){
	    	   y = height-height*2/3;
	       }else if(space <=9){
	    	   y = height-height*1/3; 
	       }
	       
	        ImageInfo fLogo  = new ImageInfo(logfile.getPath());
	        mask = new MagickImage(fLogo);
			dest = new MagickImage(new ImageInfo(this.imagePath + "/" + imageName));
			dest.compositeImage(CompositeOperator.AtopCompositeOp, mask, (int)x, (int)y);
			dest.setFileName(this.imagePath + "/" + imageName);
			dest.writeImage(info);
			dest.destroyImages();
		} catch (MagickException ex) {
			System.out.println(ex);
		}
		return null;
	}

	@Override
	public Result annotateImage(String imageName, String text, int space,
			int opacity, String fontType,String fontColor,String bgColor,int fontSize) {
		Result result = new ResultSupport();
		String filePath = this.imagePath + "/" + imageName;
		if (!this.checkImageTypeVailable(new File(filePath))) {
			result.setError("文件类型错误");
			result.setSuccess(false);
			return result;
		}
		try {
			ImageInfo info = new ImageInfo(filePath);
			MagickImage aImage = new MagickImage(info);
			
			double x = 0, y = 0;
			Dimension imageDim = aImage.getDimension();
			double width = imageDim.getWidth();
			double height = imageDim.getHeight();
		       if(space % 3==1){
		    	   x = width -width*3/3; 
		       }else if(space % 3==2){
		    	   x = width -width*2/3; 
		       }else if(space % 3==0){
		    	   x = width -width*1/3; 
		       }
		       if(space <=3){
		    	   y = height-height*3/3;
		       }else if(space <=6){
		    	   y = height-height*2/3;
		       }else if(space <=9){
		    	   y = height-height*1/3; 
		       }
		    	   x=x+20;
		    	   y=y+20; 

	       DrawInfo aInfo = new DrawInfo(info);

			aInfo.setFill(PixelPacket.queryColorDatabase(fontColor));
			aInfo.setUnderColor(PixelPacket.queryColorDatabase(bgColor));
			aInfo.setPointsize(fontSize);

		    String fontUrl = this.fontPath +fontType;
			aInfo.setFont(fontUrl);
			aInfo.setTextAntialias(true);
			aInfo.setText(text);
			aInfo.setGeometry("+"+x+"+"+y+"");

			//aInfo.setGeometry("+50+20");
			aInfo.setOpacity(opacity);
			aImage.annotateImage(aInfo);
			// 覆盖原来图片
			aImage.setFileName(filePath);
			aImage.writeImage(info);
			aImage.destroyImages();
			aImage = null;
			result.setSuccess(true);
		} catch (MagickException e) {
			result.setSuccess(false);
			result.setError(e);
		}
		return result;
	}		
	
}
