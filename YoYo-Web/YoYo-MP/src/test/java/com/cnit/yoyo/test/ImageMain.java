package com.cnit.yoyo.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.dto.ImagesDTO;
import com.cnit.yoyo.image.HessianInerface;

/**
 * @author wanghb
 * @version V1.0
 * @createDateTime：2015-05-12
 * @Company: cnit
 * @Copyright: Copyright (c) 2015
 **/
public class ImageMain {

	private HessianInerface imagesService;

	@SuppressWarnings("resource")
//	@Before
	public void init() {
		ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext-HessianClient.xml", "springmvc-servlet.xml", "ApplicationContext-shiro.xml");
		imagesService = (HessianInerface) context.getBean("imagesService");
	}

//	@After
	public void destory() {
	}

	@SuppressWarnings("resource")
//	@Test
	public void ImageTest() throws IOException {
		File file = new File("e:\\shenzhen.jpg");
		InputStream is = new FileInputStream(file);
		byte[] buffer = new byte[(int) file.length()];
		is.read(buffer);
		ImagesDTO imagesDTO = imagesService.uploadSingleFile(buffer,GlobalStatic.IMAGES_PATH_PRODUCT);
		System.out.println("===============success================");
		System.out.println(imagesDTO.getFileName());
	}

}
