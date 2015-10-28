package com.cnit.yoyo.util;

import net.sf.json.JsonConfig;


public class JSONUtils {
	
	public static JsonConfig getJsonConfig(){
		return new JsonConfig();
	}
	
	public static JsonConfig getDBFieldConfig(){
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(String.class,new JsonConvertToDBFieldProcessor("Q"));
		return jsonConfig;
	}
}
