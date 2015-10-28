package com.cnit.yoyo.spider.common.utils;  

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.caucho.hessian.client.HessianProxyFactory;
import com.cnit.yoyo.image.HessianInerface;
  
/**
 * 创建Hessian客户端工具类
 * @author yangyi
 * @date 2015年6月23日
 */
public class HessianHelper {
	private static final Logger logger=LoggerFactory.getLogger(HessianHelper.class);
	
	private HessianHelper(){};
	
	private static final HessianHelper _instance = new HessianHelper();
	
	//静态工厂方法   
    public static HessianHelper getInstance() {  
        return _instance;  
    } 
    
	@SuppressWarnings("unchecked")
	private static <T> T createClient(String hessianURL,Class<T> interfaceClass){
		T client = null;
		try{
			HessianProxyFactory factory = new HessianProxyFactory();
			client = (T)factory.create(interfaceClass, hessianURL);
		}catch(MalformedURLException e){
			logger.error("创建Hessian客户端出错!", e);
		}
		return client;
	}
	
	//创建客户端
	public HessianInerface createClient(String hessianURL){
		return HessianHelper.createClient(hessianURL, HessianInerface.class);
	}
	
	public byte[] getRemoteBuffer(String remotePath)throws Exception{
	  	URL remoteUrl = new URL(remotePath);
		URLConnection conn = remoteUrl.openConnection();
		InputStream is = conn.getInputStream();
		byte[] buffer = new byte[conn.getContentLength()];
		is.read(buffer);
		return buffer;
  }
}
