package com.cnit.yoyo.test;

import java.io.File;
import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;
import com.cnit.yoyo.dto.ImagesDTO;
import com.cnit.yoyo.image.HessianInerface;

/**   
 * @Description: 
 * @author wanghb
 * @date 2015年8月31日
 * @Copyright 2015 cnit
 * @version V1.0.0 		
*/
public class Test {

	public static void main(String[] args) throws Exception {
		System.out.println("===============success================");
//		String url = "http://localhost:8082//YoYoImageServer/remoting/ImageTest";
		String url = "http://10.255.8.92:8082/ImageService/remoting/ImageTest";
//		String url = "http://10.255.8.17:8082/ImageService/remoting/ImageTest";
		HessianProxyFactory factory = new HessianProxyFactory();
		HessianInerface hInerface = (HessianInerface) factory.create(HessianInerface.class, url);
		File file = new File("e:\\1.png");
		byte[] buffer = new byte[(int) file.length()];
		ImagesDTO imagesDTO = hInerface.uploadSingleFile(buffer, "product");
		System.out.println(imagesDTO.getFileName());
	}

}

