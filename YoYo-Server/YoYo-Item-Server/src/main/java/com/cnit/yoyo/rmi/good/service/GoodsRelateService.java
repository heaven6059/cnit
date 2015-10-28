/**
 * 文 件 名   :  SpecService.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-3-18 下午2:05:46
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.rmi.good.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.goods.GoodsRelatedMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;

/**
 * 
 *@description 相关商品的service
 *@detail <功能详细描述>
 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
 *@version 1.0.0
 */
@Service("goodsRelateService")
public class GoodsRelateService {
    public static final Logger log = LoggerFactory.getLogger(GoodsRelateService.class);
    @Autowired
    private GoodsRelatedMapper goodsRelateMapper;
 

   /**
    * 
    *@description 根据商品id获取相关商品
    *@detail <方法详细描述>
    *@author <a href="xiaoxiang@cnit.com">肖湘</a>
    *@version 1.0.0
    *@data 2015-5-12
    *@param data
    *@return
    *@throws Exception
    */
    public Object getSpecAndValuesById(Object data) throws Exception {
        
        return new ResultObject(new HeadObject(ErrorCode.SUCCESS), goodsRelateMapper.selectByGoodsId((Integer)data));
    }

  
}
