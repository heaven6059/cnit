package com.cnit.yoyo.spider.autohome.task;  

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSONObject;
import com.caucho.hessian.client.HessianProxyFactory;
import com.cnit.yoyo.dto.ImagesDTO;
import com.cnit.yoyo.image.HessianInerface;
import com.cnit.yoyo.spider.autohome.constants.Constant;
import com.cnit.yoyo.spider.common.dto.autohome.vo.FactoryConfig;
import com.cnit.yoyo.spider.common.utils.HessianHelper;
import com.cnit.yoyo.spider.common.utils.HttpUtils;
public class AutohomeSpiderTaskTest {
	@Autowired
	AutohomeSpiderTask autohomeSpiderTask;

	public static void main(String[] args){
    	new ClassPathXmlApplicationContext("classpath:conf/spring/applicationContext.xml");
    	new FactoryScopeTask().execute();
		
    }
	
  public void testGetFactoryConfig(){
	  String brandUrl1=Constant.CARFACTORYURL;
		Map<String,String> param=new HashedMap();
		param.put("type", "3");
		param.put("value", "34");
		try {
			String factoryId=null;
//			String str=HttpUtils.get(brandUrl1, param);
			String htmlStr=HttpUtils.get(brandUrl1, param);
			String config = JSONObject.parseObject(htmlStr).getString("result").replace("\"", "'");
			FactoryConfig factoryConfig = JSONObject.parseObject(config, FactoryConfig.class);
			if(factoryConfig != null){
				factoryId=factoryConfig.getFactoryitems().get(0).getId().toString();
			}
			System.out.println(factoryId);
		} catch (IOException e) {
			// TODO Auto-generated catch block  
			e.printStackTrace();
		}
  }
  
  public byte[] getRemoteBuffer(String remotePath)throws Exception{
	  	URL remoteUrl = new URL(remotePath);
		URLConnection conn = remoteUrl.openConnection();
		InputStream is = conn.getInputStream();
		byte[] buffer = new byte[conn.getContentLength()];
		is.read(buffer);
		return buffer;
  }
  
  @Test
  public void testUploadImage() throws Exception{
//	String url ="http://localhost:8082//YoYoImageServer/remoting/ImageTest";
	String url = "http://10.255.8.17:8082/ImageService/remoting/ImageTest";
	HessianInerface hInerface = HessianHelper.getInstance().createClient(url);
	String remotePath="http://car0.autoimg.cn/logo/brand/100/130278291464085825.jpg";
	byte[] buffer=getRemoteBuffer(remotePath);
	ImagesDTO imagesDTO=hInerface.uploadSingleFile(buffer,"mp");
	System.out.println("===============success================");
	System.out.println(imagesDTO.getFileName());
  }
}
