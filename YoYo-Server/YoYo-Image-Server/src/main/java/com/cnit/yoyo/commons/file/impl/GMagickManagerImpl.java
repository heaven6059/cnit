package com.cnit.yoyo.commons.file.impl;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.process.ProcessStarter;
import org.im4java.process.StandardStream;

import com.cnit.yoyo.commons.file.GMagickManager;
import com.cnit.yoyo.image.Result;
import com.cnit.yoyo.image.ResultSupport;

/**
 * @author wanghb
 * @version V1.0
 * @createDateTime：2015-05-12
 * @Company: cnit
 * @Copyright: Copyright (c) 2015
 **/
public class GMagickManagerImpl implements GMagickManager {
	private static ConvertCmd convert = new ConvertCmd(true);
	private String globalPath;
	private String imagePath; // 图片路径
	private static final int BUFFER_SIZE = 16 * 1024;
	private int maxSize; // 图片最大值
	private List<String> styles; // 压缩图片类型

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public String getGlobalPath() {
		return globalPath;
	}

	public void setGlobalPath(String globalPath) {
		System.out.println(globalPath);
//		ProcessStarter.setGlobalSearchPath(globalPath);
		this.globalPath = globalPath;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public List<String> getStyles() {
		return styles;
	}

	public void setStyles(List<String> styles) {
		this.styles = styles;
	}

	public static int getBufferSize() {
		return BUFFER_SIZE;
	}

	@Override
	public Result writeImage(File file,String filePath) throws IOException {
		Result result = this.UploadImage(file,filePath);
		if (this.getStyles() != null && this.getStyles().size() > 0 && result.isSuccess()) {
			try {
				this.scaleImage(file, result.getModels().get("fileName").toString(), result.getModels().get("suffix").toString(),filePath);
			} catch (IM4JavaException e) {
				result.setSuccess(false);
				result.setError(e);
			} catch (InterruptedException e) {
				result.setSuccess(false);
				result.setError(e);
			}
		}
		return result;
	}

	@Override
	public boolean checkImageTypeVailable(File file) {
		return this.getImageType(file) != null;
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
    public  void drawImg(String picPath,String drawPicPath,int width, int height) throws IOException, InterruptedException, IM4JavaException{  
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
    
	private void scaleImage(File file, String fileName, String suffix,String path) throws IM4JavaException, IOException, InterruptedException {
		BufferedImage img = ImageIO.read(file);
		IMOperation op = null;
		String temp = "";
		Date date = new Date();
		SimpleDateFormat dateFm = new SimpleDateFormat("yyyyMM");
		String imagesPath = getImagePath() + "/" + path + "/" + dateFm.format(date);
		File myPath = new File(imagesPath);
		if (!myPath.exists()) {
			myPath.mkdir();
		}
		if (styles != null && styles.size() > 0) {
			for (String item : styles) {
				op = new IMOperation();
				// 获取宽度
				int width = Integer.parseInt(item.substring(0, item.indexOf("*")));
				// 获取高度
				int height = Integer.parseInt(item.substring(item.indexOf("*") + 1, item.length()));
				System.out.println("获取宽度:"+width+"获取高度:"+height);
				// 获取图片的宽度
				double fileWidth = img.getWidth();
				// 获取图片的高度
				double fileHeight = img.getHeight();
				double n = (width/1.00) / (height/1.00) ;
				double m = (fileWidth/1.00) / (fileHeight/1.00);
				// 如果需要压缩的图片尺寸比需要压缩的小,则不压缩,尺寸和原来一样
				op.addImage();
				if (n >= 1 && m >= 1) {
					// op.sample((int)fileWidth, (int)fileHeight);
					temp = (int) fileWidth + "x" + (int) fileHeight;
				} else {
					// 最优比例压缩
					if (n > m) {
						temp = (int) (fileWidth * m) + "x" + height;
						// op.sample((int)(fileWidth * m), height);
					} else {
						// op.sample(width,(int) (fileHeight * n));
						temp = width + "x" + (int) (fileHeight * n);
					}
				}
				double dValue=Math.abs(n-m);
//				if(dValue>0.5){
					op.addRawArgs("-thumbnail",width + "x" + height+"^");
					op.addRawArgs("-gravity", "center");
					op.addRawArgs("-extent", width + "x" + height);
//				}else{
//					op.addRawArgs("-thumbnail", width + "x" + height);  
//				}
				op.addRawArgs("-quality", "100");
				op.addImage();
				String filePath = imagesPath + "/" + fileName + "." + width + "x" + height + "." + suffix;
				convert.run(op, img, filePath);
//				this.drawImg(file.getPath(), filePath, width, height);
			}
		}
	}

	@Override
	public Result UploadImage(File file,String filePath) throws IOException {
		Result result = new ResultSupport();
		InputStream in = null;
		OutputStream out = null;
		try {
			String suffix = this.getImageType(file);
			if (suffix == null) {
				result.setSuccess(false);
				result.setError("文件格式错误");
				return result;
			}
			in = new BufferedInputStream(new FileInputStream(file), BUFFER_SIZE);
			String fileName = System.currentTimeMillis() + "";
			Date date = new Date();
			SimpleDateFormat dateFm = new SimpleDateFormat("yyyyMM");
			File myPath1 = new File(getImagePath() + "/" + filePath);
			if (!myPath1.exists()) {
				myPath1.mkdir();
			}
			String imagesPath = getImagePath() + "/" + filePath + "/" + dateFm.format(date);
			File myPath = new File(imagesPath);
			if (!myPath.exists()) {
				myPath.mkdir();
			}
			
			out = new BufferedOutputStream(new FileOutputStream(imagesPath + "/" + fileName + "." + suffix), BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];
			// while (in.read(buffer) > 0) {
			// out.write(buffer);//改方法会导致文件变大,请其他同事注意
			// }
			int len = -1;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
			result.setSuccess(true);
			result.setDefaultModel("/" + filePath + "/" + dateFm.format(date) + "/" + fileName + "." + suffix);
			result.setDefaultModel("suffix", suffix);
			result.setDefaultModel("fileName", fileName);
		} catch (Exception e) {
			result.setSuccess(false);
		} finally {
			if (null != in) {
				in.close();
			}
			if (null != out) {
				out.close();
			}
		}
		return result;
	}

	@Override
	public String imageCombine(String size, String imageName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteFile(String imageurl) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean delPhysicalImage(List<String> listdel, int sellerId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkFileSize(File file) {
		long filesize = file.length();
		if (filesize > maxSize) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public Result createImage(String imageurl,String path) {
		Result result = new ResultSupport();
		String picUrl = imageurl.substring(0, imageurl.lastIndexOf("."));
		imageurl = imagePath + "/" + imageurl;
		File file = new File(imageurl);
		try {
			String suffix = this.getImageType(file);
			if (suffix == null) {
				result.setSuccess(false);
				result.setError("文件格式错误");
			} else {
				try {
					this.scaleImage(file, picUrl, suffix,path);
				} catch (IM4JavaException e) {
					result.setSuccess(false);
					result.setError(e);
				}
			}
		} catch (Exception e) {
			result.setSuccess(false);
		}
		return result;
	}

	@Override
	public void addImgText(String srcPath) {
		// TODO Auto-generated method stub
		IMOperation op = new IMOperation();
		op.font("宋体").gravity("southeast").pointsize(18).fill("#BCBFC8").draw("text 5,5 4sonline.com.cn");
		op.addImage();
		op.addImage();
		ConvertCmd convert = new ConvertCmd();
		// linux下不要设置此值，不然会报错
		// convert.setSearchPath(imageMagickPath);
		try {
			convert.run(op, srcPath, srcPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IM4JavaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Result UploadResizeImage(int imgHeight, int imgWidth, File file,String filePath) throws IOException {
		Result result = this.UploadImage(file,filePath);
		try {
			String fileName = this.resizeImage(imgHeight, imgWidth, file, result.getModels().get("fileName").toString(), result.getModels().get("suffix").toString());
			result.setDefaultModel("fileName", fileName);
			result.setDefaultModel(fileName);
		} catch (InterruptedException e) {

		} catch (IM4JavaException e) {
			result.setSuccess(false);
			result.setError(e);
		}
		return result;
	}

	public String resizeImage(int imgHeight, int imgWidth, File file, String fileName, String suffix) throws IOException, InterruptedException, IM4JavaException {
		String filePath = "";
		BufferedImage img = ImageIO.read(file);
		IMOperation op = null;
		System.out.println("1:" + 1);
		op = new IMOperation();
		// 获取宽度
		int width = imgWidth;
		// 获取高度
		int height = imgHeight;
		// 获取图片的宽度
		double fileWidth = img.getWidth();
		// 获取图片的高度
		double fileHeight = img.getHeight();
		double n = width / fileWidth;
		double m = height / fileHeight;
		// 如果需要压缩的图片尺寸比需要压缩的小,则不压缩,尺寸和原来一样
		op.addImage();
		if (n >= 1 && m >= 1) {
			op.sample((int) fileWidth, (int) fileHeight);
		} else {
			// 最优比例压缩
			if (n > m) {
				op.sample((int) (fileWidth * m), height);
			} else {
				op.sample(width, (int) (fileHeight * n));
			}
		}
		op.addImage();
		convert.run(op, img, this.getImagePath() + "/" + fileName + "." + width + "x" + height + "." + suffix);
		filePath = fileName + "." + imgWidth + "x" + imgHeight + "." + suffix;
		return filePath;
	}

	/**
	 * 居中切割图片(不支持gif图片切割) 1、如果源图宽高都小于目标宽高，则只压缩图片，不做切割
	 * 2、如果源图宽高都大于目标宽度，则根据宽度等比压缩后再居中切割 3、其它条件下，则压缩图片（不做缩放）后再居中切割
	 * 该方法在知道源图宽度（sw）和高度（sh）的情况下使用
	 * 
	 * @param srcPath
	 *            源图路径
	 * @param desPath
	 *            切割图片保存路径
	 * @param sw
	 *            源图宽度
	 * @param sh
	 *            源图高度
	 * @param dw
	 *            切割目标宽度
	 * @param dh
	 *            切割目标高度
	 * @throws Exception
	 */
	public void cropImage(String srcPath, String desPath, int sw, int sh, int dw, int dh) throws Exception {

		if (sw <= 0 || sh <= 0 || dw <= 0 || dh <= 0)
			return;
		IMOperation op = new IMOperation();
		op.addImage();
		if ((sw <= dw) && (sh <= dh)) // 如果源图宽度和高度都小于目标宽高，则仅仅压缩图片
		{
			op.resize(sw, sh);
		}
		if ((sw <= dw) && (sh > dh)) // 如果源图宽度小于目标宽度，并且源图高度大于目标高度
		{
			op.resize(sw, sh); // 压缩图片
			op.append().crop(sw, dh, 0, (sh - dh) / 2);// 切割图片
		}
		if ((sw > dw) && (sh <= dh)) // 如果源宽度大于目标宽度，并且源高度小于目标高度
		{
			op.resize(sw, sh);
			op.append().crop(dw, sh, (sw - dw) / 2, 0);
		}
		if (sw > dw && sh > dh) // 如果源图宽、高都大于目标宽高
		{
			float ratiow = (float) dw / sw; // 宽度压缩比
			float ratioh = (float) dh / sh; // 高度压缩比
			if (ratiow >= ratioh) // 宽度压缩比小（等）于高度压缩比（是宽小于高的图片）
			{
				int ch = (int) (ratiow * sh); // 压缩后的图片高度

				op.resize(dw, null); // 按目标宽度压缩图片
				op.append().crop(dw, dh, 0, (ch > dh) ? ((ch - dh) / 2) : 0); // 根据高度居中切割压缩后的图片
			} else // （宽大于高的图片）
			{
				int cw = (int) (ratioh * sw); // 压缩后的图片宽度
				op.resize(cw, null); // 按计算的宽度进行压缩
				op.append().crop(dw, dh, (cw > dw) ? ((cw - dw) / 2) : 0, 0); // 根据宽度居中切割压缩后的图片
			}
		}
		op.addImage();
		convert.run(op, srcPath, desPath);
	}

	@Override
	public String getImageType(File file) {
		// TODO Auto-generated method stub
		if (file == null || !file.exists()) {
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
			if (b0 == (byte) 'G' && b1 == (byte) 'I' && b2 == (byte) 'F' && b3 == (byte) '8' && b4 == (byte) '7' && b5 == (byte) 'a') {
				return "gif";
				// GIF(G I F 8 9 a)
			} else if (b0 == (byte) 'G' && b1 == (byte) 'I' && b2 == (byte) 'F' && b3 == (byte) '8' && b4 == (byte) '9' && b5 == (byte) 'a') {
				return "gif";
				// PNG(89 P N G 0D 0A 1A)
			} else if (b0 == -119 && b1 == (byte) 'P' && b2 == (byte) 'N' && b3 == (byte) 'G' && b4 == 13 && b5 == 10 && b6 == 26) {
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
	
}
