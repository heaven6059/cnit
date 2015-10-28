/**
 * 文 件 名   :  CommonHeadUtil.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-2-5 下午1:59:24
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.util;

import javax.servlet.http.HttpServletRequest;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;

/**
 * @description <一句话功能简述>
 * @detail <功能详细描述>
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
public class CommonHeadUtil {
	
	 /**
	  * 服务层调用
	  * @param serviceCode
	  * @return
	  */
	 public static HeadObject geneHeadObject(String serviceCode) {
		 return geneHeadObject(null, serviceCode, GlobalStatic.SYSTEM_CODE_DATA,
                 GlobalStatic.SYSTEM_CODE_BACK);
		 
	 }
	 
	 /**
	  * 服务层调用
	  * @param serviceCode
	  * @return
	  */
	public static HeadObject geneHeadObject(HttpServletRequest request,String serviceCode) {
		 return geneHeadObject(request, serviceCode, GlobalStatic.SYSTEM_CODE_DATA,
                 GlobalStatic.SYSTEM_CODE_BACK);
		
	} 

    public static HeadObject geneHeadObject(HttpServletRequest request, String serviceCode, String providerCode, String consumerCode) {
        HeadObject headObject = new HeadObject();
        headObject.setSeqno(StringUtil.getUUID());
        headObject.setProviderCode(providerCode);
        headObject.setConsumerCode(consumerCode);
        headObject.setServiceCode(serviceCode);
        if(null != request) {
        	headObject.setIp(request.getRemoteHost());
            headObject.setLocale(request.getLocale());
            headObject.setReferUrl(request.getRequestURI());
        }
        headObject.setDateTime(DateUtils.getCurrentDate(null));
        headObject.setRetCode(ErrorCode.SUCCESS);
        return headObject;
    }
}
