/**
 * 文 件 名   :  RemoteService.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-2-5 上午9:36:47
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.rmi.interfaces;

import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;

/**
 *@description 远程服务统一入口
 *@detail <功能详细描述>
 *@author <a href="liming@cnit.com">李明</a>
 *@version 1.0.0
 */
public interface RemoteService {
	/**
	 * Web调用远程服务
	 * @param requestObject
	 * @return
	 * @throws Exception
	 */
    ResultObject doService(RequestObject requestObject) throws Exception;
    
    /**
     * Server调用远程服务
     * @param params
     * @return
     */
    Object doServiceByServer(RequestObject requestObject);
    
}
