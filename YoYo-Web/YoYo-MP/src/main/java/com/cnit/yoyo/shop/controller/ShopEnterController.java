package com.cnit.yoyo.shop.controller;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.goods.dto.CompanyDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.DateUtils;

/**
 * @description 商家入驻
 * @detail
 * @author  xiaox
 * @version 1.0.0
 */
@Controller
@RequestMapping("/shopEnter")
public class ShopEnterController {


	@Autowired
	private RemoteService memberService;

 

   

    /**
     * @Title: shopRegisterImg
     * @Description: 跳转到上传图片页面
     * @param @param request
     * @param @return
     * @param @throws Exception 设定文件
     * @return String 返回类型
     * @throws
     */
    @RequestMapping("/shopRegisterImg")
    public String shopRegisterImg(@RequestParam(value = "companyId", required = true) String companyId, Map<String, Object> map) throws Exception {
    	map.put("companyId", Long.valueOf(companyId));
        return "pages/biz/shop/shopRegisterImg";
    }

    /**
     * @Title: shopRegisterSave
     * @Description: 提交商家的入驻信息资料
     * @param @param request
     * @param @param company
     * @param @return
     * @param @throws Exception 设定文件
     * @return Object 返回类型
     * @throws
     */
    @RequestMapping("/shopRegisterSave")
    @ResponseBody
    public Object shopRegisterSave(HttpServletRequest request, CompanyDTO company,@RequestParam(value="goodCategory[]",required=false)Integer[] goodCategory) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020103-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    /*    String loginName = (String) CommonUtil.getSession(request).getAttribute("loginName");
        Integer accountId = (Integer) CommonUtil.getSession(request).getAttribute("accountId");
        company.setShopName(loginName);
        company.setAccountId(accountId);*/
        if (company != null && StringUtils.isEmpty(company.getCompanyArea())) { // 只有2级地区的
            company.setCompanyArea(company.getCompanyArea_2());
        }
        if (company != null && StringUtils.isEmpty(company.getCompanyCarea())) {
            company.setCompanyCarea(company.getCompanyCarea_2());
        }
        // 单店类型
        if (company != null && company.getGradeId() == null) {
            company.setGradeId(1l);// 设为默认1 :单店
        }
        ResultObject resultObject = null;

        try {
            // 判断该法人是否已经入驻
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("idcard", company.getCompanyIdcard());
            dataMap.put("companyId", company.getCompanyId());
            resultObject = memberService.doService(new RequestObject(headObject,dataMap));
            if (resultObject.getRetCode().equals(ErrorCode.IS_EXIST) || resultObject.getRetCode().equals(ErrorCode.FAILURE)) {
                return resultObject;
            }
            headObject = CommonHeadUtil.geneHeadObject(request, "1000020103-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
            Map<String,Object> data = new HashMap<String, Object>();
  		    data.put("company", company);
  		    data.put("goodCateIds", goodCategory);
            resultObject =memberService.doService(new RequestObject(headObject,data));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultObject;
    }

  

    /**
     * @Title: saveShopImagePath
     * @Description: 保存图片的路径
     * @param @param request
     * @param @param company
     * @param @return 设定文件
     * @return ResultObject 返回类型
     * @throws
     */

    @RequestMapping("/saveShopImagePath")
    @ResponseBody
    public ResultObject saveShopImagePath(HttpServletRequest request, CompanyDTO company) {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020103-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        try {
            resultObject = memberService.doService(new RequestObject(headObject,company));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultObject;
    }

    /**
     * @Title: shopRegisterCheck
     * @Description:跳转到审核等待页面
     * @param @param request
     * @param @return
     * @param @throws Exception 设定文件
     * @return String 返回类型
     * @throws
     */
    @RequestMapping("/shopRegisterCheck")
    public String shopRegisterCheck(HttpServletRequest request) throws Exception {
        return "pages/shopEnter/shopRegisterCheck";
    }
    
    
    /**
     * 
     *@description 更新店铺信息
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-6-15
     *@return
     *@throws Exception
     */
    @RequestMapping("/updateShopAllInfo")
    @ResponseBody
    public Object updateShopAllInfo(HttpServletRequest request, CompanyDTO company,@RequestParam(value="goodCategory[]",required=false)Integer[] goodCategory) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020103-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        if (company != null && StringUtils.isEmpty(company.getCompanyArea())) { // 只有2级地区的
            company.setCompanyArea(company.getCompanyArea_2());
        }
        if (company != null && StringUtils.isEmpty(company.getCompanyCarea())) {
            company.setCompanyCarea(company.getCompanyCarea_2());
        }
        // 单店类型
        if (company != null && company.getGradeId() == null) {
            company.setGradeId(1l);// 设为默认1 :单店
        }
        ResultObject resultObject = null;

        try {
            // 判断该法人是否已经入驻
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("idcard", company.getCompanyIdcard());
            dataMap.put("companyId", company.getCompanyId());
            resultObject = memberService.doService(new RequestObject(headObject,dataMap));
            if (resultObject.getRetCode().equals(ErrorCode.IS_EXIST) || resultObject.getRetCode().equals(ErrorCode.FAILURE)) {
                return resultObject;
            }
            headObject = CommonHeadUtil.geneHeadObject(request, "1000020103-17", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
            Map<String,Object> data = new HashMap<String, Object>();
            data.put("company", company);
            data.put("goodCateIds", goodCategory);
            resultObject =memberService.doService(new RequestObject(headObject,data));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultObject;
    }


   
}
