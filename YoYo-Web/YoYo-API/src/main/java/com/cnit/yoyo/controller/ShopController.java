package com.cnit.yoyo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.base.controller.BaseController;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.shop.dto.ShopDetailInfoDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.Configuration;
@Controller("shopController")
@RequestMapping("/shop")
public class ShopController extends BaseController {
	  @Autowired
	    private RemoteService memberService;
	  
	  @RequestMapping("/shopDetail")
	    @ResponseBody
	    public Object shopDetail(String data,HttpServletRequest request)
	    {

	        log.info("###########shopDetail-->start-------------------");
	        log.info("----------------------data:"+data+"-------------------------");

			HeadObject headObject = null;
			ResultObject resultObject = null;
			try {
				//获取请求参数
				JSONObject jsonData =  JSONObject.fromObject(data);
				String companyId = jsonData.getString("companyId"); 
//				String companyIdtest = request.getParameter("companyId");
			    headObject = CommonHeadUtil.geneHeadObject("companyService.findShopDetail");
				resultObject = (ResultObject) memberService.doServiceByServer(new RequestObject(headObject,Long.valueOf(companyId)));
				ShopDetailInfoDTO dto = (ShopDetailInfoDTO) resultObject.getContent();
				List<String> images = new ArrayList<String>();
				dto.setImages(images);
				if(dto!=null&&StringUtils.isNotEmpty(dto.getImage())){
					String imgUrl = Configuration.getInstance().getConfigValue("images.url");
					dto.setImage(imgUrl+dto.getImage());
					images.add(dto.getImage());
					
					//////////////没有的数据暂时先模拟///////
					dto.setIsFocus(0);
					dto.setGoodsPoints(100d);
					dto.setServicePoints(100d);
					dto.setTimePoints(100d);
					dto.setStoreBrief("这是测试商铺");
					///////////////////////////////////////////
					
				}
				
				log.info("###########shopDetail-->end-------------------");
				return resultObject;
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return processExpction(e.getMessage());
			}
	    }
}
