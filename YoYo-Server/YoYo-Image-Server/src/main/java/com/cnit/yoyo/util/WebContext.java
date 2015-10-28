package com.cnit.yoyo.util; 

/** 
 * @author  wanghb
 * @version V1.0 
 * @createDateTime：2015-05-12
 * @Company: cnit
 * @Copyright: Copyright (c) 2015
 **/
public class WebContext {
	//获取当前文件路径
	   public  String getWebClassesPath() {
	     String path = getClass().getProtectionDomain().getCodeSource()
	       .getLocation().getPath();
	     return path.substring(1);
	   }
	   //获取当前工程的web-inf路径
	   public  String getWebInfPath() throws IllegalAccessException{
	     String path = getWebClassesPath();
	     if (path.indexOf("WEB-INF") > 0) {
	      path = path.substring(0, path.indexOf("WEB-INF")+8);
	     } else {
	      throw new IllegalAccessException("路径获取错误");
	     }
	     return path;
	   }

	   //获取当前工程路径
	   public  String getWebRoot() throws IllegalAccessException{
	     String path = getWebClassesPath();
	     if (path.indexOf("WEB-INF") > 0) {
	      path = path.substring(0, path.indexOf("WEB-INF/classes"));
	     } else {
	      throw new IllegalAccessException("路径获取错误");
	     }
	     return path;
	   }
	   
	   public static void main(String[] args) throws IllegalAccessException {
		   WebContext pathUtil = new WebContext();
		   System.out.println(pathUtil.getWebClassesPath());
		   System.out.println(pathUtil.getWebInfPath());
	}
}
 