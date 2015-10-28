/**
 * 文 件 名   :  SignService.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  &lt;描述&gt;
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-1-22 下午3:28:17
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.member.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.goods.CompanyWithBLOBs;
import com.cnit.yoyo.rmi.interfaces.RemoteService;

/**
 * @description <一句话功能简述>
 * @detail <功能详细描述>
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
@Service("signService")
public class SignService {
    @Autowired
    private RemoteService memberService;
    
   

    /**
     * @description 发送验证短信
     * @detail <方法详细描述>
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-2-6
     * @param headObject
     * @param data
     * @return
     * @throws Exception
     */
    public ResultObject sendSms(HeadObject headObject, Object data) throws Exception {
        String smsCodeTemp = String.valueOf(Math.random() * 9000 + 1000);
        String smsCode = smsCodeTemp.substring(0, smsCodeTemp.indexOf("."));
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("mobile", data);
        dataMap.put("smsCode", smsCode);
        dataMap.put("template", "sms.template.common");
        RequestObject requestObject = new RequestObject(headObject, dataMap);
        ResultObject ro = memberService.doService(requestObject);
        ro.setContent(smsCode);
        return ro;
    }

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
        return memberService.doService(new RequestObject(headObject, data));
    }
    
   
}
