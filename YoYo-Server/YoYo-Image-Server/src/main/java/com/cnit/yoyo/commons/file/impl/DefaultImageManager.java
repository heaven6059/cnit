package com.cnit.yoyo.commons.file.impl;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;

import org.apache.commons.io.FileUtils;

import com.cnit.yoyo.commons.file.ImageManager;
import com.cnit.yoyo.image.Result;
import com.cnit.yoyo.image.ResultSupport;

public class DefaultImageManager implements ImageManager {
	private String imagePath;
	private int maxSize;
	private List<String> styles;

	public List<String> getStyles() {
		return this.styles;
	}

	public void setStyles(List<String> styles) {
		this.styles = styles;
	}

	public int getMaxSize() {
		return this.maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public String getImagePath() {
		return this.imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public void cutCenterImage(InputStream in, OutputStream os, int w, int h, String suffix) throws IOException {
		BufferedImage prevImage = ImageIO.read(in);
		double width = prevImage.getWidth();
		double height = prevImage.getHeight();
		double percent_w = w / width;
		double percent_h = h / width;
		int newWidth = (int) (width * percent_w);
		int newHeight = (int) (height * percent_h);
		BufferedImage image = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
		image.getGraphics().drawImage(prevImage, 0, 0, newWidth, newHeight, null);
		ImageIO.write(image, suffix, os);
		os.flush();
		in.close();
		os.close();
	}

	public BufferedImage resize(BufferedImage source, int targetW, int targetH) throws IOException {
		int type = source.getType();
		BufferedImage target = null;
		double sx = (double) targetW / source.getWidth();
		double sy = (double) targetH / source.getHeight();
		// 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放
		// 则将下面的if else语句注释即可
		if (sx > sy) {
			sx = sy;
			targetW = (int) (sx * source.getWidth());
		}
		// else {
		// sy = sx;
		// targetH = (int) (sy * source.getHeight());
		// }
		if (type == BufferedImage.TYPE_CUSTOM) { // handmade
			ColorModel cm = source.getColorModel();
			WritableRaster raster = cm.createCompatibleWritableRaster(targetW, targetH);
			boolean alphaPremultiplied = cm.isAlphaPremultiplied();
			target = new BufferedImage(cm, raster, alphaPremultiplied, null);
		} else {
			// 固定宽高，宽高一定要比原图片大
			// target = new BufferedImage(targetW, targetH, type);
			target = new BufferedImage(800, 600, type);
		}

		Graphics2D g = target.createGraphics();

		// 写入背景
		// g.drawImage(ImageIO.read(new File("ok/blank.png")), 0, 0, null);

		// smoother than exlax:
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
		g.dispose();
		return target;
	}

	private void scaleImage(File file, String fileName, String suffix, String path) throws MagickException {
		InputStream in = null;
		OutputStream out = null;
		Date date = new Date();
		SimpleDateFormat dateFm = new SimpleDateFormat("yyyyMM");
		String imagesPath = getImagePath() + "/" + path + "/" + dateFm.format(date);
		File myPath = new File(imagesPath);
		if (!myPath.exists()) {
			myPath.mkdir();
		}
		if ((this.styles != null) && (this.styles.size() > 0)) {
			for (String item : this.styles) {
				int width = Integer.parseInt(item.substring(0, item.indexOf("*")));
				int height = Integer.parseInt(item.substring(item.indexOf("*") + 1, item.length()));
				BufferedImage sourceImg = null;
				try {
					sourceImg = ImageIO.read(new FileInputStream(file));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				double fileWidth = sourceImg.getWidth();
				double fileHeight = sourceImg.getHeight();
				double n = width / fileWidth;
				double m = height / fileHeight;
				if ((n >= 1.0D) && (m >= 1.0D)) {
					try {
						in = new BufferedInputStream(new FileInputStream(file), 16384);
						String filePath = imagesPath + "/" + fileName + "." + width + "x" + height + "." + suffix;
						out = new BufferedOutputStream(new FileOutputStream(filePath), 16384);
						sourceImg = this.resize(sourceImg, width, height);
						this.cutCenterImage(in, out, width, height, suffix);
					} catch (Exception e) {
						if (null != in) {
							try {
								in.close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
						if (null != out)
							try {
								out.close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
					} finally {
						if (null != in) {
							try {
								in.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						if (null != out) {
							try {
								out.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				} else {
					String filePath = "";
					if (n > m) {
						filePath = imagesPath + "/" + fileName + "." + width + "x" + height + "." + suffix;
					} else {
						filePath = imagesPath + "/" + fileName + "." + width + "x" + height + "." + suffix;
					}
					try {
						in = new BufferedInputStream(new FileInputStream(file), 16384);
						out = new BufferedOutputStream(new FileOutputStream(filePath), 16384);
						this.cutCenterImage(in, out, width, height, suffix);
					} catch (Exception e) {
						if (null != in) {
							try {
								in.close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
						if (null != out)
							try {
								out.close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
					} finally {
						if (null != in) {
							try {
								in.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						if (null != out) {
							try {
								out.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}

	public Result writeImage(File file, String filePath) throws IOException {
		Result result = UploadImage(file, filePath);
		if ((getStyles() != null) && (getStyles().size() > 0) && (result.isSuccess())) {
			try {
				scaleImage(file, result.getModels().get("fileName").toString(), result.getModels().get("suffix").toString(), filePath);
			} catch (MagickException e) {
				result.setSuccess(false);
				result.setError(e);
			}
		}
		return result;
	}

	public boolean checkImageTypeVailable(File file) {
		return getImageType(file) != null;
	}

	public Result UploadImage(File file, String filePath) throws IOException {
		Result result = new ResultSupport();
		InputStream in = null;
		OutputStream out = null;
		try {
			String suffix = getImageType(file);
			if (suffix == null) {
				result.setSuccess(false);
				result.setError("文件格式错误");
				Result localResult1 = result;
				return localResult1;
			}
			in = new BufferedInputStream(new FileInputStream(file), 16384);
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
			out = new BufferedOutputStream(new FileOutputStream(imagesPath + "/" + fileName + "." + suffix), 16384);

			byte[] buffer = new byte[16384];

			int len = -1;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
			result.setSuccess(true);
			result.setDefaultModel("/" + filePath + "/" + dateFm.format(date) + "/" + fileName + "." + suffix);
			result.setDefaultModel("suffix", suffix);
			result.setDefaultModel("fileName", fileName);

			if (null != in) {
				in.close();
			}
			if (null != out)
				out.close();
		} catch (Exception e) {
			System.out.println(e);
			result.setSuccess(false);
			if (null != in) {
				in.close();
			}
			if (null != out)
				out.close();
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

	public String imageCombine(String size, String imageName) throws Exception {
		size = size.replace("*", "x");
		String format = imageName.substring(imageName.lastIndexOf("."), imageName.length());
		String name = imageName.substring(0, imageName.lastIndexOf("."));
		String url = name + "." + size + format;
		return url;
	}

	public boolean delPhysicalImage(List<String> listdel, int sellerId) {
		String imgName;
		for (int m = 0; m < listdel.size(); m++) {
			imgName = (String) listdel.get(m);
			deleteFile(imgName);
			if (null != this.styles) {
				for (String imgsize : this.styles)
					try {
						String subimgName = imageCombine(imgsize, imgName);
						deleteFile(subimgName);
					} catch (Exception e) {
					}
			}
		}
		return true;
	}

	public void deleteFile(String imageurl) {
		imageurl = this.imagePath + "/" + imageurl;
		try {
			File imgfile = new File(imageurl);
			if ((imgfile.exists()) && (imgfile.isFile())) {
				imgfile.delete();
			}
			imgfile = null;
		} catch (Exception e) {
		}
	}

	public boolean checkFileSize(File file) {
		long filesize = file.length();

		return filesize <= this.maxSize;
	}

	public Result createImage(String imageurl, String filePath) {
		Result result = new ResultSupport();
		String picUrl = imageurl.substring(0, imageurl.lastIndexOf("."));
		imageurl = this.imagePath + "/" + imageurl;
		File file = new File(imageurl);
		try {
			String suffix = getImageType(file);
			if (suffix == null) {
				result.setSuccess(false);
				result.setError("文件格式错误");
			} else {
				try {
					scaleImage(file, picUrl, suffix, filePath);
				} catch (MagickException e) {
					result.setSuccess(false);
					result.setError(e);
				}
			}
		} catch (Exception e) {
			result.setSuccess(false);
		}
		return result;
	}

	public Result UploadResizeImage(int imgHeight, int imgWidth, File file, String filePath) throws IOException {
		Result result = UploadImage(file, filePath);
		try {
			String fileName = resizeImage(imgHeight, imgWidth, file, result.getModels().get("fileName").toString(), result.getModels().get("suffix").toString(), filePath);
			result.setDefaultModel("fileName", fileName);
			result.setDefaultModel(fileName);
		} catch (MagickException e) {
			result.setSuccess(false);
			result.setError(e);
		}
		return result;
	}

	public String resizeImage(int imgHeight, int imgWidth, File file, String fileName, String suffix, String path) throws MagickException {
		String filePath = "";
		ImageInfo info = new ImageInfo(file.getPath());
		MagickImage image = new MagickImage(new ImageInfo(file.getPath()));
		Dimension imageDim = image.getDimension();
		double fileWidth = imageDim.getWidth();
		double fileHeight = imageDim.getHeight();
		double n = imgWidth / fileWidth;
		double m = imgHeight / fileHeight;

		if ((n >= 1.0D) && (m >= 1.0D)) {
			MagickImage scaled = image.scaleImage((int) fileWidth, (int) fileHeight);
			scaled.setFileName(getImagePath() + "/" + path + "/" + fileName + "." + imgWidth + "x" + imgHeight + "." + suffix);

			scaled.writeImage(info);
		} else {
			// MagickImage scaled;
			// MagickImage scaled;
			// if (n > m)
			// scaled = image.scaleImage((int)(fileWidth * m), imgHeight);
			// else {
			// scaled = image.scaleImage(imgWidth, (int)(fileHeight * n));
			// }
			// filePath = fileName + "." + imgWidth + "x" + imgHeight + "." +
			// suffix;
			// scaled.setFileName(getImagePath() + "/" + fileName + "." +
			// imgWidth + "x" + imgHeight + "." + suffix);
			//
			// scaled.writeImage(info);
		}
		return filePath;
	}

	public String getImageType(File file) {
		if ((file == null) || (!file.exists()))
			return null;
		try {
			byte[] imgContent = FileUtils.readFileToByteArray(file);
			int len = imgContent.length;
			byte n1 = imgContent[(len - 2)];
			byte n2 = imgContent[(len - 1)];
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

			if ((b0 == 71) && (b1 == 73) && (b2 == 70) && (b3 == 56) && (b4 == 55) && (b5 == 97)) {
				return "gif";
			}
			if ((b0 == 71) && (b1 == 73) && (b2 == 70) && (b3 == 56) && (b4 == 57) && (b5 == 97)) {
				return "gif";
			}
			if ((b0 == -119) && (b1 == 80) && (b2 == 78) && (b3 == 71) && (b4 == 13) && (b5 == 10) && (b6 == 26)) {
				return "png";
			}
			if ((b0 == -1) && (b1 == -40) && (n1 == -1) && (n2 == -39))
				return "jpg";
			if ((b6 == 74) && (b7 == 70) && (b8 == 73) && (b9 == 70))
				return "jpg";
			if ((b6 == 69) && (b7 == 120) && (b8 == 105) && (b9 == 102)) {
				return "jpg";
			}

			return null;
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		} catch (IOException e) {
		}
		return null;
	}
}
