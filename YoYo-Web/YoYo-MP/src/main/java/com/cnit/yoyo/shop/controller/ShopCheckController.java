
package com.cnit.yoyo.shop.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.QueryParamObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.goods.CompanyWithBLOBs;
import com.cnit.yoyo.model.goods.dto.CompanyDTO;
import com.cnit.yoyo.model.shop.dto.CompanyExperienceDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;

/**
 * 
 *@description 店铺审核
 *@detail <功能详细描述>
 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
 *@version 1.0.0
 */
@Controller
@RequestMapping("/shopCheck")
public class ShopCheckController {
    public static final Logger log = LoggerFactory.getLogger(ShopCheckController.class);
    
    
    @Autowired
    private RemoteService memberService;

  
    @RequestMapping("/index")
    public String index() {
        return "pages/biz/shop/shopCheckIndex";
    }
    
    /**
     * 
     *@description 根据条件获取所有店铺列表
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-5-27
     *@param shopGrade
     */
    @RequestMapping("/findShopList")
    @ResponseBody
    public Object findShopList(HttpServletRequest request, CompanyDTO company,String sort, String order) throws Exception {
        log.info("start[ShopCheckController.findShopList]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020103-07", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        if(company!=null){ //增加排序
            JSONObject content = new JSONObject();
            if (!StringUtils.isNotBlank(sort)) {
                sort = "companyId";
                order = "desc";
            }else{
                if(sort.split(",").length>1){
                    sort=sort.split(",")[sort.split(",").length-1];
                }
            }
            if(StringUtils.isNotBlank(order)){
                if(order.split(",").length>1){
                    order=order.split(",")[order.split(",").length-1];
                }
            }
            content.put(QueryParamObject.SORT_STR, sort);
            content.put(QueryParamObject.ORDER_STR, order);
            company.setOrderStmt(QueryParamObject.getOrderByCause(content));
        }
        ResultObject resultObject = memberService.doService(new RequestObject(headObject, company));
        log.info("end[ShopCheckController.findShopList]");
        return resultObject;
    }
    
    /**
     * 
     *@description 获得店铺信息详情
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-5-29
     *@param request
     *@param companyId
     *@return
     *@throws Exception
     */
    @RequestMapping("/getDetail")
    public String getDetail(HttpServletRequest request,Integer companyId) throws Exception{
        log.info("start[ShopCheckController.getDetail]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020103-09", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = memberService.doService(new RequestObject(headObject, companyId));
        request.setAttribute("shopInfo", resultObject.getContent());
        log.info("end[ShopCheckController.getDetail]");
        return "pages/biz/shop/shopCheckDetail";
    }
    
    /**
     * 
     *@description 添加经验值记录
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-6-1
     *@param request
     *@param company
     *@return
     *@throws Exception
     */
    @RequestMapping("/saveExperience")
    @ResponseBody
    public Object saveExperience(HttpServletRequest request, CompanyExperienceDTO dto) throws Exception {
        log.info("start[ShopCheckController.saveExperience]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "031211-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Integer accountId = (Integer) request.getSession().getAttribute("accountId");
        dto.setOperator(accountId);
        ResultObject resultObject = memberService.doService(new RequestObject(headObject, dto));
        log.info("end[ShopCheckController.saveExperience]");
        return resultObject;
    }
    
    /**
     * 
     *@description 查找经验值记录,根据店铺id
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-6-1
     *@param request
     *@param company
     *@return
     *@throws Exception
     */
    @RequestMapping("/findExperience")
    @ResponseBody
    public Object findExperience(HttpServletRequest request, CompanyExperienceDTO dto) throws Exception {
        log.info("start[ShopCheckController.findExperience]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "031211-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = memberService.doService(new RequestObject(headObject, dto));
        log.info("end[ShopCheckController.findExperience]");
        return resultObject;
    }
    
    
    /**
     * 
     *@description 批量审核
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-6-1
     *@param goodCategory 需要商品审核的分类id
     *@param dto
     *@return
     *@throws Exception
     */
    @RequestMapping("/checking")
    @ResponseBody
    public Object checking(HttpServletRequest request, CompanyWithBLOBs company,@RequestParam(value="ids[]",required=true) Integer []ids,
            @RequestParam(value="accountIds[]",required=true) Integer []accountIds,@RequestParam(value="issueTypes[]",required=true) String []issueTypes,
            @RequestParam(value="goodCategory[]",required=false)Integer[] goodCategory) throws Exception {
        log.info("start[ShopCheckController.checking]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020103-10", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("ids", ids);
        data.put("approvedremark", company.getApprovedremark());
        data.put("approved", company.getApproved());
        data.put("accountIds", accountIds);
        data.put("issueTypes", issueTypes);
        data.put("goodCategory", goodCategory);
        if("1".equals(company.getApproved())){  //审核通过
            data.put("approvedTime", new Date());
        }
        ResultObject resultObject = memberService.doService(new RequestObject(headObject, data));
        log.info("end[ShopCheckController.checking]");
        return resultObject;
    }
    
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(HttpServletRequest request, @RequestParam(value="ids[]",required=true) Integer []ids) throws Exception {
        log.info("start[ShopCheckController.delete]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020103-11", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("ids", ids);
        ResultObject resultObject = memberService.doService(new RequestObject(headObject, data));
        log.info("end[ShopCheckController.delete]");
        return resultObject;
    }
    
    /**
     * @Title:  findStoreByCompany  
     * @Description:  TODO(查询分店)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-6-9 下午4:28:17  
     * @version 1.0.0 
     * @param @param request
     * @param @param companyId
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/findStoreByCompany")
    @ResponseBody
    public Object findStoreByCompany(HttpServletRequest request, Long companyId) throws Exception {
        log.info("start[ShopCheckController.findStoreByCompany]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "031001-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = memberService.doService(new RequestObject(headObject, companyId));
        log.info("end[ShopCheckController.findStoreByCompany]");
        return resultObject;
    }
    
    /**
     * 
     *@description 更新商品是否需要审核
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-6-10
     *@param request
     *@param dto
     *@return
     *@throws Exception
     */
    @RequestMapping("/updateShopGoodCheck")
    @ResponseBody
    public Object updateShopGoodCheck(HttpServletRequest request,Integer companyId,@RequestParam(value="goodCategory[]",required=false)Integer[] goodCategory ) throws Exception {
        log.info("start[ShopCheckController.updateShopGoodCheck]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "031310-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("goodCategory", goodCategory);
        data.put("companyId", companyId);
        ResultObject resultObject = memberService.doService(new RequestObject(headObject, data));
        log.info("end[ShopCheckController.updateShopGoodCheck]");
        return resultObject;
    }
    
    /**
     * 
     *@description 获得店铺需要审核的商品分类
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-6-10
     *@param request
     *@param companyId
     *@param goodCategory
     *@return
     *@throws Exception
     */
    @RequestMapping("/getShopGoodsCat")
    @ResponseBody
    public Object getShopGoodsCat(HttpServletRequest request,Integer companyId ) throws Exception {
        log.info("start[ShopCheckController.updateShopGoodCheck]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "031310-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = memberService.doService(new RequestObject(headObject, companyId));
        log.info("end[ShopCheckController.updateShopGoodCheck]");
        return resultObject;
    }
    
}