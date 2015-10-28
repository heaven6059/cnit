package com.cnit.yoyo.shop.enter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.rmi.interfaces.RemoteService;

/**
 * @description <一句话功能简述>
 * @detail <功能详细描述>
 * @author xiaox
 * @version 1.0.0
 */
@Service("baseService")
public class BaseService {
    @Autowired
    private RemoteService remoteService;
    
   



    /**
     * @description 远程方法通用调用方法
     * @detail 若对头部和数据体无特殊处理 可直接使用该方法
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-2-6
     * @param headObject
     * @param data
     * @return
     * @throws Exception
     */
    public ResultObject doCommon(HeadObject headObject, Object data) throws Exception {
        System.out.println("data==========="+data);
        return remoteService.doService(new RequestObject(headObject, data));
    }
    
   
}
