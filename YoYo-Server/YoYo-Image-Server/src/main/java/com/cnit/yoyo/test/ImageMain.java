package com.cnit.yoyo.test;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.process.StandardStream;

import magick.CompressionType;
import magick.ImageInfo;
import magick.MagickApiException;
import magick.MagickException;
import magick.MagickImage;

import com.caucho.hessian.client.HessianProxyFactory;
import com.cnit.yoyo.dto.ImagesDTO;
import com.cnit.yoyo.image.HessianInerface;
import com.cnit.yoyo.image.ImService;
import com.cnit.yoyo.image.Result;

/**
 * @author wanghb
 * @version V1.0
 * @createDateTime：2015-05-12
 * @Company: cnit
 * @Copyright: Copyright (c) 2015
 **/
public class ImageMain {
	public static void main(String[] args) throws IOException {
		ImageTest();
		// resetsize("E:/shenzhen.jpg", "new.jpg");
//		try {  
//            drawImg("E://shenzhen.jpg", "D://shenzhenTwo.jpg", 300,400);  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        }  
	}

	@SuppressWarnings("resource")
	public static void ImageTest() throws IOException {
//		String url = "http://localhost:8082//YoYoImageServer/remoting/ImageTest";
//		String url = "http://10.255.8.92:8082/ImageService/remoting/ImageTest";
		String url = "http://10.255.8.17:8082/ImageService/remoting/ImageTest";
		HessianProxyFactory factory = new HessianProxyFactory();
		HessianInerface hInerface = (HessianInerface) factory.create(HessianInerface.class, url);
		File file = new File("e:\\3451dp2.jpg");
		byte[] buffer = new byte[(int) file.length()];
		ImagesDTO imagesDTO = hInerface.uploadSingleFile(buffer, "product");
		System.out.println("===============success================");
		System.out.println(imagesDTO.getFileName());
	}

	/** 
     *  
     * @param picPath 图片路径 
     * @param drawPicPath draw后的路径 
     * @param width draw后的宽度 
     * @param height draw后的高度 
     * @throws IOException 
     * @throws InterruptedException 
     * @throws IM4JavaException 
     */  
    public static void drawImg(String picPath,String drawPicPath,int width, int height) throws IOException, InterruptedException, IM4JavaException{  
        IMOperation op = new IMOperation();  
        op.addImage();  
        op.resize(width, height);  
        op.font("Arial").fill("red").draw("text 290,190 www.taobao.com");    
        op.quality(85d);  
        op.addImage();  
        //IM4JAVA是同时支持ImageMagick和GraphicsMagick的，如果为true则使用GM，如果为false支持IM。  
        ConvertCmd cmd = new ConvertCmd(true);  
        String osName = System.getProperty("os.name").toLowerCase();      
        if(osName.indexOf("win")>=0) {  //linux下不要设置此值，不然会报错  
            cmd.setSearchPath("D:/GraphicsMagick-1.3.21-Q8");   
           }  
        cmd.setErrorConsumer(StandardStream.STDERR);  
        cmd.run(op, picPath , drawPicPath);  
    } 
    
	public static void ImageService() throws IOException {
		String url = "http://localhost:8080/ImageServer/remoting/ImageService";
		HessianProxyFactory factory = new HessianProxyFactory();
		ImService testService = (ImService) factory.create(ImService.class, url);
		File file = new File("e:\\shenzhen.jpg");
		Result result = testService.writeImage(file, "product");
		if (result.isSuccess()) {
			System.out.println("===============success================");
			System.out.println(result.getDefaultModel());
		} else {
			System.out.println("===============false================");
			System.out.println(result.getError());
		}
	}

	public static void resetsize(String picFrom, String picTo) {
		try {
			ImageInfo info = new ImageInfo(picFrom);
			MagickImage image = new MagickImage(new ImageInfo(picFrom));
			MagickImage scaled = image.scaleImage(120, 97);
			scaled.setFileName(picTo);
			scaled.writeImage(info);
		} catch (MagickApiException ex) {
			ex.printStackTrace();
		} catch (MagickException ex) {
			ex.printStackTrace();
		}
	}
}
