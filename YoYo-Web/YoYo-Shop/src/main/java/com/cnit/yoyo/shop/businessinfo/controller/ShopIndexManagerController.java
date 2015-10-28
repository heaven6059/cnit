package com.cnit.yoyo.shop.businessinfo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.model.shop.ShopFocusAd;
import com.cnit.yoyo.model.shop.ShopIndexPush;
import com.cnit.yoyo.model.shop.ShopIndexPushWithBLOBs;
import com.cnit.yoyo.model.shop.dto.ShopIndexPushDTO;
import com.cnit.yoyo.model.shop.dto.ShopIndexQryDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.StringUtil;

/**  
* @Title: ShopIndexManager.java
* @Package com.cnit.yoyo.shop.businessinfo.controller
* @Description: 店铺首页管理
* @Author 王鹏
* @date 2015-6-12 下午12:23:15
* @version V1.0  
*/ 
@Controller
@RequestMapping("/shopIndexManager")
public class ShopIndexManagerController {

	@Autowired
	private RemoteService otherService;
	
	@RequestMapping("/shopIndexMng")
	public String toShopIndexManager(HttpServletRequest request){
		MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
		try{
			if(null!=memberListDo){
				ShopIndexQryDTO indexQryDTO=new ShopIndexQryDTO(memberListDo.getCompanyId());
				HeadObject head=CommonHeadUtil.geneHeadObject(request, "990503-06");
				ResultObject resultObject = otherService.doService(new RequestObject(head, indexQryDTO));
				request.setAttribute("shopIndexPush", resultObject.getContent());
				request.setAttribute("memberListDo", memberListDo);
				findFocuseAd(request, memberListDo.getCompanyId());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "pages/shopIndexMng/shopIndexMng";
	}
	
	/**
	  * @description <b>查询店铺焦点图</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-11
	  * @param @param request
	  * @param @param companyId
	  * @return void
	*/
	private void findFocuseAd(HttpServletRequest request,Long companyId){
		HeadObject headObject=CommonHeadUtil.geneHeadObject(request,"990501-01");
		try{
			ShopFocusAd shopFocusAd=new ShopFocusAd(companyId);
			ResultObject resultObject = otherService.doService(new RequestObject(headObject, shopFocusAd));
			request.setAttribute("shopFocusAd", resultObject.getContent());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	  * @description <b>保存或编辑焦点图</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-26
	  * @param @param request
	  * @param @return
	  * @return String
	*/
	@RequestMapping("/saveFocusAd")
	public String saveFocusAd(HttpServletRequest request){
		try{
			MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
			if(null!=memberListDo){
				String [] focusId=request.getParameterValues("focusId");
				String [] focuskeyword=request.getParameterValues("focuskeyword");
				String [] focushref=request.getParameterValues("focushref");
				String [] focusImg=request.getParameterValues("focusImg");
				List<ShopFocusAd> shopFocusAds=new ArrayList<>();
				for (int i = 0; i < focusImg.length; i++) {
					ShopFocusAd focusAd=new ShopFocusAd();
					focusAd.setFocusUrl(focushref[i]);
					focusAd.setFocusImg(focusImg[i]);
					focusAd.setFocusTitle(focuskeyword[i]);
					focusAd.setId(StringUtil.isEmpty(focusId[i])?null:Long.parseLong(focusId[i]));
					focusAd.setCompanyId(memberListDo.getCompanyId());
					shopFocusAds.add(focusAd);
				}
				HeadObject headObject=CommonHeadUtil.geneHeadObject(request,"990501-02");
				if(shopFocusAds.size()>0){
					otherService.doService(new RequestObject(headObject, shopFocusAds));
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/shopIndexManager/shopIndexMng";
	}
	
	/**
	  * @description <b>修改焦点图启用状态</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-26
	  * @param @param request
	  * @param @param shopFocusAd
	  * @param @return
	  * @return String
	*/
	@RequestMapping("/eanbleFocusAd")
	public String eanbleFocusAd(HttpServletRequest request,ShopFocusAd shopFocusAd){
		try{
			HeadObject headObject=CommonHeadUtil.geneHeadObject(request,"990501-04");
			otherService.doService(new RequestObject(headObject, shopFocusAd));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/shopIndexManager/shopIndexMng";
	}
	
	/**
	  * @description <b>删除焦点图</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-26
	  * @param @param request
	  * @param @param shopFocusAd
	  * @param @return
	  * @return String
	*/
	@RequestMapping("/deleteFocusAd")
	public String deleteFocusAd(HttpServletRequest request,ShopFocusAd shopFocusAd){
		try{
			HeadObject headObject=CommonHeadUtil.geneHeadObject(request,"990501-03");
			otherService.doService(new RequestObject(headObject, shopFocusAd.getId()));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/shopIndexManager/shopIndexMng";
	}
	
	@RequestMapping("/toEditShopIndexPush")
	public String toEditShopIndexPush(HttpServletRequest request,ShopIndexQryDTO shopIndexQryDTO){
		String type=request.getParameter("type");
		try{
			MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
			if("Edit".equals(type)&&null!=memberListDo){
				shopIndexQryDTO.setCompanyId(memberListDo.getCompanyId());
				HeadObject head=CommonHeadUtil.geneHeadObject(request, "990503-07");
				ResultObject resultObject = otherService.doService(new RequestObject(head, shopIndexQryDTO));
				request.setAttribute("shopIndexPush", resultObject.getContent());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "pages/shopIndexMng/editShopIndex";
	}
	
	@RequestMapping("/toEditShopIndexTemplate")
	public String toEditShopIndexBanner(HttpServletRequest request,ShopIndexQryDTO shopIndexQryDTO){
		String type=request.getParameter("type");
		String page=request.getParameter("page");
		try{
			MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
			if("Edit".equals(type)&&null!=memberListDo){
				shopIndexQryDTO.setCompanyId(memberListDo.getCompanyId());
				HeadObject head=CommonHeadUtil.geneHeadObject(request, "990503-07");
				ResultObject resultObject = otherService.doService(new RequestObject(head, shopIndexQryDTO));
				request.setAttribute("shopIndexPush", resultObject.getContent());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		if(null!=page){
			return "pages/shopIndexMng/"+page;
		}else{
			return "redirect:/shopIndexManager/shopIndexMng";
		}
	}
	
	
	@RequestMapping("/updateShopIndexPush")
	public String updateShopIndexPush(HttpServletRequest request,ShopIndexPushWithBLOBs shopIndexPushWithBLOBs){
		String type=request.getParameter("type");
		try{
			MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
			if("Edit".equals(type)&&null!=memberListDo){
				shopIndexPushWithBLOBs.setCompanyId(memberListDo.getCompanyId());
				HeadObject head=CommonHeadUtil.geneHeadObject(request, "990503-08");
				otherService.doService(new RequestObject(head, shopIndexPushWithBLOBs));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/shopIndexManager/shopIndexMng";
	}
	
	@RequestMapping("/deleteShopIndexPush")
	public String deleteShopIndexPush(HttpServletRequest request,@RequestParam(defaultValue="id",required=true)Long id){
		try{
			HeadObject head=CommonHeadUtil.geneHeadObject(request, "990503-09");
			otherService.doService(new RequestObject(head, id));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/shopIndexManager/shopIndexMng";
	}
	
	@RequestMapping("/editShopIndex")
	public String editShopIndex(HttpServletRequest request,ShopIndexPushWithBLOBs shopIndexPush){
		MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
		try{
			if(null!=memberListDo){
				shopIndexPush.setCompanyId(memberListDo.getCompanyId());
				HeadObject head=CommonHeadUtil.geneHeadObject(request, "990503-05");
				ResultObject resultObject=otherService.doService(new RequestObject(head, shopIndexPush));
				shopIndexPush=(ShopIndexPushWithBLOBs)resultObject.getContent();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/shopIndexManager/shopIndexMng";
	}
	
	@RequestMapping("/editShopIndexKeyword")
	public String editShopIndexKeyword(HttpServletRequest request,ShopIndexPushWithBLOBs shopIndexPush){
		MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
		try{
			if(null!=memberListDo){
				shopIndexPush.setCompanyId(memberListDo.getCompanyId());
				String [] titles=request.getParameterValues("top-right-keyword");
				String [] hrefs=request.getParameterValues("top-right-href");
				JSONArray titleArray=new JSONArray();
				for (int i = 0; i < titles.length; i++) {
					JSONObject title=new JSONObject();
					title.put("title",titles[i]);
					title.put("href",hrefs[i]);
					titleArray.add(title);
				}
				shopIndexPush.setTitlePush(titleArray.toString());
				HeadObject head=CommonHeadUtil.geneHeadObject(request, "990503-05");
				ResultObject resultObject = otherService.doService(new RequestObject(head, shopIndexPush));
				shopIndexPush=(ShopIndexPushWithBLOBs)resultObject.getContent();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/shopIndexManager/toEditShopIndexPush?type=Edit&id="+shopIndexPush.getId();
	}
	
	@RequestMapping("/editShopIndexClassify")
	public String editShopIndexClassify(HttpServletRequest request,ShopIndexPushWithBLOBs shopIndexPush){
		MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
		try{
			if(null!=memberListDo){
				shopIndexPush.setCompanyId(memberListDo.getCompanyId());
				String [] bottomLeftTitles=request.getParameterValues("bottom-left-keyword");
				String [] bottomLeftHref=request.getParameterValues("bottom-left-href");
				JSONArray titleArray=new JSONArray();
				for (int i = 0; i < bottomLeftTitles.length; i++) {
					JSONObject title=new JSONObject();
					title.put("title",bottomLeftTitles[i]);
					title.put("href",bottomLeftHref[i]);
					titleArray.add(title);
				}
				shopIndexPush.setLeftPush(titleArray.toString());
				HeadObject head=CommonHeadUtil.geneHeadObject(request, "990503-05");
				ResultObject resultObject = otherService.doService(new RequestObject(head, shopIndexPush));
				shopIndexPush=(ShopIndexPushWithBLOBs)resultObject.getContent();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/shopIndexManager/toEditShopIndexPush?type=Edit&id="+shopIndexPush.getId();
	}
	
	@RequestMapping("/editShopIndexBanner")
	public String editShopIndexBanner(HttpServletRequest request,ShopIndexPushWithBLOBs shopIndexPush){
		MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
		try{
			if(null!=memberListDo){
				shopIndexPush.setCompanyId(memberListDo.getCompanyId());
				
				String [] bottomRightTitles=request.getParameterValues("bottom-right-keyword");
				String [] bottomRightHref=request.getParameterValues("bottom-right-href");
				String [] bottomRightImg=request.getParameterValues("bottom-right-Img");
				JSONArray titleArray=new JSONArray();
				for (int i = 0; i < bottomRightTitles.length; i++) {
					JSONObject title=new JSONObject();
					title.put("title",bottomRightTitles[i]);
					title.put("href",bottomRightHref[i]);
					title.put("img",bottomRightImg[i]);
					titleArray.add(title);
				}
				shopIndexPush.setRightPush(titleArray.toString());
				HeadObject head=CommonHeadUtil.geneHeadObject(request, "990503-05");
				ResultObject resultObject = otherService.doService(new RequestObject(head, shopIndexPush));
				shopIndexPush=(ShopIndexPushWithBLOBs)resultObject.getContent();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/shopIndexManager/toEditShopIndexPush?type=Edit&id="+shopIndexPush.getId();
	}
	

}
