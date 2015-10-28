package com.cnit.yoyo.shop.enter.controller;

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
import com.cnit.yoyo.util.RedisClientUtil;

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
	@Autowired
    private RedisClientUtil redisService;
    /**
     * @Title: shopRegister
     * @Description: 商家入驻
     * @param @param request
     * @param @return
     * @param @throws Exception 设定文件
     * @return String 返回类型
     * @throws
     */
    @RequestMapping("/shopRegister")
    public String shopRegister(HttpServletRequest request) throws Exception {
        return "pages/shopEnter/shopRegister";
    }

    /**
     * @Title: shopRegisterData
     * @Description: 用户跳转到开店类型页
     * @param @param request
     * @param @return
     * @param @throws Exception 设定文件
     * @return String 返回类型
     * @throws
     */
    @RequestMapping("/shopRegisterData")
    public String shopRegisterData(HttpServletRequest request) throws Exception {
        return "pages/shopEnter/shopRegisterData";
    }

    /**
     * @Title: shopRegisterData
     * @Description: 用户跳转到资料填写页面
     * @param @param type:开店类型
     * @param @return
     * @param @throws Exception 设定文件
     * @return String 返回类型
     * @throws
     */
    @RequestMapping("/shopRegisterSubmit")
    public String shopRegisterSubmit(@RequestParam(value = "type", required = true) String type, Map<String, Object> map) throws Exception {
        map.put("type", type);
        return "pages/shopEnter/shopRegisterSubmit";
    }

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
        return "pages/shopEnter/shopRegisterImg";
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
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020103-20", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        String loginName = (String) CommonUtil.getSession(request).getAttribute("loginName");
        Integer accountId = (Integer) CommonUtil.getSession(request).getAttribute("accountId");
        company.setShopName(loginName);
        company.setAccountId(accountId);
        if (company != null && StringUtils.isEmpty(company.getCompanyArea())) { // 只有2级地区的
            company.setCompanyArea(company.getCompanyArea_2());
        }
        if (company != null && StringUtils.isEmpty(company.getCompanyCarea())) {
            company.setCompanyCarea(company.getCompanyCarea_2());
        }
        // 快修店下的集团类型  TODO    ,//issuetype为店铺类型，如果页面上面选择快修店，则类型为2，如果选择4s店，则类型为1，对应店铺表中的级别为2：快修店，1：4s店
        if(company.getIssueType()!="" && "1".equals(company.getIssueType())){
            company.setGradeId(2l);// 设为默认1 :单店
        }else{
            company.setGradeId(1l);// 设为默认1 :单店
        }
        ResultObject resultObject = null;

        try {
            //判断账号是否已经入驻了
            resultObject = memberService.doService(new RequestObject(headObject,accountId));
            if (resultObject.getRetCode().equals(ErrorCode.IS_EXIST) || resultObject.getRetCode().equals(ErrorCode.FAILURE)) {
                return resultObject;
            }
            // 判断该法人是否已经入驻
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("idcard", company.getCompanyIdcard());
            dataMap.put("companyId", company.getCompanyId());
            headObject = CommonHeadUtil.geneHeadObject(request, "1000020103-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
            resultObject = memberService.doService(new RequestObject(headObject,dataMap));
            if (resultObject.getRetCode().equals(ErrorCode.IS_EXIST) || resultObject.getRetCode().equals(ErrorCode.FAILURE)) {
                return resultObject;
            }
            headObject = CommonHeadUtil.geneHeadObject(request, "1000020103-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
            Map<String,Object> data = new HashMap<String, Object>();
  		    data.put("company", company);
  		    data.put("goodCateIds", goodCategory);
            resultObject =memberService.doService(new RequestObject(headObject,data));
            //修改session中的信息
            String sessionId = request.getSession().getId();
            redisService.set(sessionId + "companyId", resultObject.getContent().toString());
            request.getSession().setAttribute("companyId", Integer.parseInt(resultObject.getContent().toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultObject;
    }

    /**
     * 上传图片
     */
    @RequestMapping("/uploadImg")
    @ResponseBody
    public ResultObject uploadImg(@RequestParam(value = "imageFile", required = false) MultipartFile file, HttpServletRequest request, ModelMap model) {
        System.out.println("开始");
        HeadObject head = new HeadObject();
        String path = request.getSession().getServletContext().getRealPath("/resources/upload");
        String pathABS = "/store/" + DateUtils.dateToString(new Date(), "yyyyMMdd");
        path = path + pathABS;

        if (file.getSize() > 2 * 1024 * 1024) { // 文件过大
            head.setRetCode(ErrorCode.OVER_LIMIT_SIZE);
            head.setRetMsg("文件大小不能超过2M");
        } else if (file.getSize() > 0 && file.getSize() < 2 * 1024 * 1024) {

            long datetime = Calendar.getInstance().getTimeInMillis();
            String fileName = file.getOriginalFilename();
            fileName = datetime + fileName.substring(fileName.indexOf("."));
            File targetFile = new File(path, fileName);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            // 保存
            try {
                file.transferTo(targetFile);
            } catch (Exception e) {
                e.printStackTrace();
                head.setRetCode(ErrorCode.FAILURE);
                head.setRetMsg("上传失败");
            }
            head.setRetCode(ErrorCode.SUCCESS);
            head.setRetMsg(pathABS + "/" + fileName);
        } else {
            head.setRetCode(ErrorCode.FAILURE);
            head.setRetMsg("上传失败");
        }

        return new ResultObject(head);
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

   
}
