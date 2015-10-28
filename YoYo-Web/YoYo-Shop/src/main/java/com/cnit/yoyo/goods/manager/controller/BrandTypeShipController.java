
package com.cnit.yoyo.goods.manager.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;

/**
 * 
 * @ClassName: BrandTypeShipController 
 * @Description: 商品品牌与分类关系的管理
 * @author xiaox
 * @date 2015-3-21 下午2:19:49
 */
@Controller
@RequestMapping("/brandTypeShip")
public class BrandTypeShipController {
    public static final Logger log = LoggerFactory.getLogger(BrandTypeShipController.class);
    
    @Autowired
    private RemoteService itemService;

    
    
    /**
     * 
     * @Description: 通过分类id,查找该品牌与分类的关系 
     * @param @param request
     * @param @param categoryId
     * @param @return
     * @author xiaox
     * @date 2015-3-28 下午3:07:41
     */
    @RequestMapping("/findBrandCateShip")
    @ResponseBody
    public Object findBrandCateShip(HttpServletRequest request,@RequestParam(value="categoryId",required=true)Integer categoryId){
         HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020110-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
         ResultObject resultObject = null;
         try {
               resultObject = itemService.doService(new RequestObject(headObject, categoryId));
               
        } catch (Exception e) {
            e.printStackTrace();
        }
         return  resultObject ;
    }
    
   
    /**
     * 
     * @Description: 通过分类类型,查找该品牌与分类的关系 
     * @param @param request
     * @param @param categoryId
     * @param @return
     * @author xiaox
     * @date 2015-3-28 下午3:07:41
     */
    @RequestMapping("/findShip")
    @ResponseBody
    public Object findShip(HttpServletRequest request,@RequestParam(value="identifier",required=false)String identifier){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020110-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
        	   resultObject = itemService.doService(new RequestObject(headObject, identifier));
        	   
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    
  
}
