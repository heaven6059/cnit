package com.cnit.yoyo.util;

/**
 * 
 * @ClassName: BNGenerator 
 * @Description: 商品编码生成器 
 * @author xiaox
 * @date 2015-4-15 下午2:21:35
 */
public class BNGenerator {

    /**
     * 
     * @Description: 获得整车的系统编码  
     * @param @return
     * @author xiaox
     * @date 2015-4-15 下午2:22:38
     */
    public static String getCarBn(){
        return "C-"+DateUtils.getCurrentDate(null);
    }
    
    /**
     * 
     * @Description:获得配件的系统编码    
     * @param @return
     * @author xiaox
     * @date 2015-4-15 下午2:25:40
     */
    public static String getAccessoryBn(){
        return "A-"+DateUtils.getCurrentDate(null);
    }
    
    /**
     * 
     * @Description: 精品  
     * @param @return
     * @author xiaox
     * @date 2015-4-15 下午2:28:31
     */
    public static String getBoutiqueBn(){
        return "B-"+DateUtils.getCurrentDate(null);
    }
    
    /**
     * 
     * @Description: 保养  
     * @param @return
     * @author xiaox
     * @date 2015-4-15 下午2:28:45
     */
    public static String getMaintainBn(){
        return "M-"+DateUtils.getCurrentDate(null);
    }
    
    /**
     * 
     * @Description: 其他  
     * @param @return
     * @author xiaox
     * @date 2015-4-15 下午2:28:56
     */
    public static String getOtherBn(){
        return "O-"+DateUtils.getCurrentDate(null);
    }
    
}
