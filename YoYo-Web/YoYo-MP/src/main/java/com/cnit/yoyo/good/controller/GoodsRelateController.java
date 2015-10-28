/**
 * 文 件 名   :  SpecController.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-3-18 下午3:49:34
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.good.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;

/**
 * 
 *@description 相关商品的控制层
 *@detail <功能详细描述>
 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
 *@version 1.0.0
 */
@Controller
@RequestMapping("/goodsRelate")
public class GoodsRelateController {
    public static final Logger log = LoggerFactory.getLogger(GoodsRelateController.class);

    @Autowired
    private RemoteService itemService;

   

 /**
  * 
  *@description 根据商品id查找对应相关商品
  *@detail <方法详细描述>
  *@author <a href="xiaoxiang@cnit.com">肖湘</a>
  *@version 1.0.0
  *@data 2015-5-12
  *@param request
  *@param goodsId
  *@return
  *@throws Exception
  */
    @RequestMapping("/relateGoodsList")
    @ResponseBody
    public Object relateGoodsList(HttpServletRequest request,Integer goodsId) throws Exception {
        log.info("start[GoodsRelateController.relateGoodsList]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010202-01", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
      
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, goodsId));
        log.info("end[GoodsRelateController.relateGoodsList]");
        return resultObject;
    }

   
}
