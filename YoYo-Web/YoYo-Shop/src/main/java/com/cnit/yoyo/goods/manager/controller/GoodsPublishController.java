package com.cnit.yoyo.goods.manager.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.goods.dto.GoodsProductDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;

/**
 * @ClassName: GoodsPublishController
 * @Description: 商品发布管理
 * @author xiaox
 * @date 2015-4-3 上午9:36:24
 */
@Controller
@RequestMapping("/goodsPublish")
public class GoodsPublishController {

    @Autowired
    private RemoteService itemService;
    
    @Autowired
    private RemoteService memberService;

    // 商品发布类型选择页
    @RequestMapping("/index")
    public String index(HttpServletRequest request,Map<String, Object> map) {
        //需要先判断该店铺是否禁止发布商品
        Integer companyId = (Integer) CommonUtil.getSession(request).getAttribute("companyId");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020103-16", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        try {
            ResultObject resultObject = memberService.doService(new RequestObject(headObject, companyId));
            if((Integer)resultObject.getContent()==0){ //店铺禁止发布商品
                map.put("msg", ErrorCode.IS_LIMIT_GOODS_MSG);
                return "pages/goodsMng/forbidden";
            }
            //判断店铺是否超出有效期
            headObject = CommonHeadUtil.geneHeadObject(request, "1000020103-19", GlobalStatic.SYSTEM_CODE_DATA,
                    GlobalStatic.SYSTEM_CODE_BACK);
            
            resultObject = memberService.doService(new RequestObject(headObject, companyId));
            if((Integer)resultObject.getContent()>0){ //超出有效期，店铺禁止发布商品
                map.put("msg", ErrorCode.IS_ENBALED_MSG);
                return "pages/goodsMng/forbidden";
            }
            
            //根据经营范围来确定发布商品的权限
            headObject = CommonHeadUtil.geneHeadObject(request, "030801-05", GlobalStatic.SYSTEM_CODE_DATA,
                    GlobalStatic.SYSTEM_CODE_BACK);
            resultObject = memberService.doService(new RequestObject(headObject, companyId));
            map.put("region", resultObject.getContent());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pages/goodsMng/goodsPublishIndex";
    }

    // 商品发布主页
    @RequestMapping("/goods_go")
    public String goodsGo(HttpServletRequest request,String type) {
        if(type!=null && type.equals("5")){
            return "pages/goodsMng/goodsPublishVirtual"; 
        }
        //查询发布的分类id
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020103-11", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        try {
            ResultObject resultObject = itemService.doService(new RequestObject(headObject, type));
            if(resultObject!=null){ 
                JSONArray catObj =  JSONArray.fromObject(resultObject.getContent());
                request.setAttribute("parentCatId", catObj.getJSONObject(0).get("catId"));
                request.setAttribute("catType", type);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "pages/goodsMng/goodsPublishMain";
    }

    /**
     * @Description: 保存商品货品信息
     * @param @param request
     * @param @throws Exception
     * @author xiaox
     * @date 2015-4-10 下午5:04:48
     */
    @RequestMapping("/saveGoods")
    @ResponseBody
    public Object saveGoods(HttpServletRequest request, GoodsProductDTO goodsDto) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020105-28", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
            public boolean apply(Object source, String name, Object value) {
                if (value == null) {
                    return true;
                } else {
                    return false;
                }
            }
        });
        //TODO 当前登陆者店铺id
        Integer companyId = (Integer) CommonUtil.getSession(request).getAttribute("companyId");
        Integer storeId = (Integer) CommonUtil.getSession(request).getAttribute("storeId");
        goodsDto.setCompanyId(companyId);
        goodsDto.setStoreId(storeId);
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, goodsDto));
        
        return resultObject;
    }
    
    /**
     * 
     *@description 卖家中心更新商品
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-4-30
     *@param request
     *@param goodsDto
     *@return
     *@throws Exception
     */
    @RequestMapping("/updateGoods")
    @ResponseBody
    public Object updateGoods(HttpServletRequest request, GoodsProductDTO goodsDto) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010201-03", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
            public boolean apply(Object source, String name, Object value) {
                if (value == null) {
                    return true;
                } else {
                    return false;
                }
            }
        });
        //TODO 当前登陆者店铺id
        Integer companyId = (Integer) CommonUtil.getSession(request).getAttribute("companyId");
        Integer storeId = (Integer) CommonUtil.getSession(request).getAttribute("storeId");
        goodsDto.setCompanyId(companyId);
        goodsDto.setStoreId(storeId);
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, goodsDto));
        
        return resultObject;
    }
    
    
    
    
    
    

}
