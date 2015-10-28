package com.cnit.yoyo.rmi.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.dao.shop.CompanyCheckCatMapper;
import com.cnit.yoyo.dao.shop.CompanyMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.shop.CompanyCheckCat;

/**
 * 
 *@description 店铺商品需要审核service
 *@detail <功能详细描述>
 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
 *@version 1.0.0
 */
@Service("companyCheckCatService")
public class CompanyCheckCatService {
	private static final Log log = LogFactory.getLog(CompanyCheckCatService.class);
	
    @Autowired
    private CompanyCheckCatMapper companyCheckCatMapper;
    @Autowired
    private CompanyMapper companyMapper;
    
    /**
     * 
     *@description 更新店铺商品是否需要审核开关
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-6-10
     *@param data
     *@return
     */
    public Object updateShopGoodCheck(Object data) {
        HeadObject head = new HeadObject();
        Map<String,Object> shopMap = new HashMap<String,Object>();
        @SuppressWarnings("unchecked")
        Map<String,Object> map = (Map<String,Object>)data;
        Integer[] goodCategory = (Integer[]) map.get("goodCategory");
        Integer companyId = (Integer)map.get("companyId");
        //1.先删除，再添加
        companyCheckCatMapper.deleteByCompanyId(companyId);
        shopMap.put("companyId",companyId);
        if(goodCategory!=null){
            Map<String,Object> cmap = new HashMap<String,Object>();
            cmap.put("companyId",companyId);
            cmap.put("goodCategory",goodCategory);
            companyCheckCatMapper.save(cmap);
            shopMap.put("isCheck",GlobalStatic.SHOP_CHECK_IN_GOODS);
        }else{
            shopMap.put("isCheck",GlobalStatic.SHOP_CHECK_OUT_GOODS);
        }
        companyMapper.updateShopGoodCheck(shopMap);  //更新店铺的状态
        head.setRetCode(ErrorCode.SUCCESS);
        return new ResultObject(head);
    }
    
    /**
     * 
     *@description 获得店铺需要审核的商品分类
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-6-10
     *@param data
     *@return
     */
    public Object getShopGoodsCat(Object data){
        HeadObject head = new HeadObject();
        List<CompanyCheckCat> list = null;
        list =  companyCheckCatMapper.getShopGoodsCat((Integer)data);
        head.setRetCode(ErrorCode.SUCCESS);
        return new ResultObject(head, list);
    }

   
}
