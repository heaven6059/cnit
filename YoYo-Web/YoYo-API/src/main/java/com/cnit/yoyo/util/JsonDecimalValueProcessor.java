package com.cnit.yoyo.util;

import java.math.BigDecimal;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * 把decimal类型数据格式化之后转成json数据
 * @author ssd
 *
 */
public class JsonDecimalValueProcessor implements JsonValueProcessor {  
      
    public JsonDecimalValueProcessor() {  
        super();  
    }  
      
    @Override  
    public Object processArrayValue(Object paramObject,  
            JsonConfig paramJsonConfig) {  
        return process(paramObject);  
    }  
  
    @Override  
    public Object processObjectValue(String paramString, Object paramObject,  
            JsonConfig paramJsonConfig) {  
        return process(paramObject);  
    }  
      
      
    private Object process(Object value){  
        if(value instanceof BigDecimal){    
            return String.valueOf(value);  
        } 
        return value == null ? "" : value.toString();    
    }  
  
} 