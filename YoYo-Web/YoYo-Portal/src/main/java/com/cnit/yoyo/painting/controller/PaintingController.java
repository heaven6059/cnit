package com.cnit.yoyo.painting.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.terracotta.context.query.Matcher;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.dto.ImagesDTO;
import com.cnit.yoyo.image.HessianInerface;
import com.cnit.yoyo.model.member.PamAccount;
import com.cnit.yoyo.model.painting.CarPart;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;


/**
 *@description 钣金喷漆功能
 *@detail <功能详细描述>
 *@author <a href="zjcai@chinacnit.com">蔡志杰</a>
 *@version 1.0.0
 */
@Controller
@RequestMapping("/painting")
public class PaintingController 
{
    public static final Logger logger = LoggerFactory.getLogger(PaintingController.class);
    
    @Autowired
    public RemoteService otherService;
    @Autowired
    public HessianInerface imagesService;
    @Autowired
    public RemoteService memberService;
    
    @RequestMapping("/index")
    public String toPaintPage(HttpServletRequest request)
    {
        logger.info(">>>>>>>>>>>>>>>>>>>PaintingController.toPaintPage start>>>>>>>>>>>>>>>>>>>");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990201-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> dataMap = new HashMap<String, Object>();
        ResultObject resultObject = null;
        try {
            resultObject = otherService.doService(new RequestObject(headObject,dataMap));
            Object content = resultObject.getContent();
            //车前脸、车左侧灯父部件
            List<CarPart> supNodes = new ArrayList<CarPart>();
            //父部件的子部件
            List<CarPart> subNodes = new ArrayList<CarPart>();
            if(content !=null ){
                @SuppressWarnings("unchecked")
                List<CarPart> list = (List<CarPart>)content;
                for (CarPart carPart : list) {
                    if(!carPart.getIsLeaf()){
                        supNodes.add(carPart);
                    }else{
                        subNodes.add(carPart);
                    }
                }
            }
            request.setAttribute("supParts", supNodes);
            request.setAttribute("subParts", subNodes);
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        logger.info(">>>>>>>>>>>>>>>>>>>PaintingController.toPaintPagestart end>>>>>>>>>>>>>>>>>>>");
        return "pages/biz/painting/painting";
    }
    
    @RequestMapping("/save")
    @ResponseBody
    public ResultObject saveOrder(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020109-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = new ResultObject();
        if(null != request.getSession().getAttribute("accountId")){
        	 int accountId = (Integer)request.getSession().getAttribute("accountId");
        	 resultObject = memberService.doService(new RequestObject(headObject, accountId));
     		 PamAccount pamAccount = null;
     		 if(resultObject != null && resultObject.getContent() != null){
     			 pamAccount = (PamAccount) resultObject.getContent();
     		 }
     		 if(null != pamAccount && pamAccount.getAccountType().startsWith("10")){
    			headObject = CommonHeadUtil.geneHeadObject(request, "990201-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    			JSONObject jsonObject = new JSONObject();
    			jsonObject.put("catParts", request.getParameter("catParts"));
    			jsonObject.put("memberId", request.getParameter("memberId"));
    			jsonObject.put("accountId",accountId);
    			
    	    	if(null != request.getParameter("source")){
    	    		jsonObject.put("source",request.getParameter("source"));
    	    	}
    	    	
    			resultObject = otherService.doService(new RequestObject(headObject,jsonObject));
     		 }else{
      			resultObject.getHead().setRetCode("000004");
     		 }
        }else{
 			resultObject.getHead().setRetCode("000004");
		}
        return resultObject;
    }
    
    /**
     * 上传图片
     * @throws IOException 
     */
    @RequestMapping("/uploadImg")
    @ResponseBody
    public ResultObject uploadImg(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, ModelMap model) throws IOException 
    {
        InputStream is = file.getInputStream();
        byte[] buffer = new byte[(int) file.getSize()];
        is.read(buffer);
        HeadObject head = CommonHeadUtil.geneHeadObject(request, "4000020101-01", GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = new ResultObject();
        ImagesDTO imagesDTO = null;
        // 保存
        try {
            imagesDTO = imagesService.uploadSingleFile(buffer,GlobalStatic.IMAGES_PATH_SHOP);
            head.setRetCode(ErrorCode.SUCCESS);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            head.setRetCode(ErrorCode.FAILURE);
        }
        resultObject.setHead(head);
        resultObject.setContent(imagesDTO);
        return resultObject;
    }
}
