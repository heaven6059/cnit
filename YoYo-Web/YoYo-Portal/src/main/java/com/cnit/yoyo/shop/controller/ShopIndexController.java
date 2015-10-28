package com.cnit.yoyo.shop.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.index.controller.IndexController;
import com.cnit.yoyo.model.goods.GoodsCat;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.model.member.StoreWishList;
import com.cnit.yoyo.model.shop.ShopCarCoupon;
import com.cnit.yoyo.model.shop.ShopFocusAd;
import com.cnit.yoyo.model.shop.dto.ShopIndexQryDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;


/**
 * 店铺首页
 * @author wanghb
 * @date 2015-05-26
 * @version 1.0.0
 */
@Controller
public class ShopIndexController {
	
	@Autowired
	private RemoteService memberService;
	
	@Autowired
	public RemoteService itemService;
	
	@Autowired
	public RemoteService searchClientService;
	
	@Autowired
	private RemoteService otherService;
	
	public static final Logger log = LoggerFactory.getLogger(IndexController.class);
	
	@RequestMapping("/shop/index")
    public String shopIndex(HttpServletRequest request,@RequestParam("companyId")Long companyId) throws Exception{
		HeadObject headObject=CommonHeadUtil.geneHeadObject(request,"1000020103-14");
		ResultObject resultObject = memberService.doService(new RequestObject(headObject, companyId));
		request.setAttribute("company", resultObject.getContent());
		this.findFocuseAd(request, companyId);
		this.searchByCateName(request, companyId);
//		this.findShopHotSell(request, companyId);
//		this.findShopIndexHotCommentGoods(request, companyId);
//		this.findShopIndexCategoryGoods(request, companyId);
		this.findShopIndexPush(request, companyId);
        return "pages/shop/shopIndex";
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
			shopFocusAd.setFocusEnabled("1");
			ResultObject resultObject = otherService.doService(new RequestObject(headObject, shopFocusAd));
			request.setAttribute("shopFocusAd", resultObject.getContent());
		}catch (Exception e) {
			log.error("查询店铺焦点图过程失败", e);
		}
	}
	
	private void searchByCateName(HttpServletRequest request,Long companyId){
		try{
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("currrentPage", 1);
			dataMap.put("pageSize", 8);
			dataMap.put("orderFile", "lastModify");
			dataMap.put("companyId",companyId);
	
			JSONObject params = new JSONObject();
			params.put("catName", "新车");
			params.put("disabled", "0");
			HeadObject headObject1 = CommonHeadUtil.geneHeadObject(request, "2000020103-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
			ResultObject resultObject1 = itemService.doService(new RequestObject(headObject1, params));
			List<GoodsCat> list = com.alibaba.fastjson.JSONArray.parseArray(resultObject1.getContent().toString(), GoodsCat.class);
			Integer cateId = 0;
			if (null != list && list.size() > 0) {
				cateId = list.get(0).getCatId();
				dataMap.put("cateId", cateId);
				HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "050101-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
				ResultObject resultObject = searchClientService.doService(new RequestObject(headObject, dataMap));
				request.setAttribute("shopCarCoupon", resultObject.getContent());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	  * @description <b>查询店铺热销</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-11
	  * @param 
	  * @return void
	*/
	private void findShopHotSell(HttpServletRequest request,Long companyId){
		HeadObject headObject=CommonHeadUtil.geneHeadObject(request,"990503-01");
		try{
			ShopIndexQryDTO qryDTO=new ShopIndexQryDTO(companyId);
			ResultObject resultObject = otherService.doService(new RequestObject(headObject, qryDTO));
			request.setAttribute("hotSell", resultObject.getContent());
		}catch (Exception e) {
			log.error("查询店铺焦点图过程失败", e);
		}
	}
	
	/**
	  * @description <b>查询店铺热销</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-11
	  * @param 
	  * @return void
	*/
	private void findShopIndexHotCommentGoods(HttpServletRequest request,Long companyId){
		HeadObject headObject=CommonHeadUtil.geneHeadObject(request,"990503-02");
		ShopIndexQryDTO qryDTO=new ShopIndexQryDTO(companyId,30,888);
		try{
			ResultObject resultObject = otherService.doService(new RequestObject(headObject, qryDTO));
			request.setAttribute("accessoriesHotComment",resultObject.getContent());
		}catch (Exception e) {
			log.error("查询配件热门评论商品失败", e);
		}
		try{
			qryDTO.setIdentifier(666);			
			ResultObject resultObject = otherService.doService(new RequestObject(headObject, qryDTO));
			request.setAttribute("boutiqueHotComment", resultObject.getContent());
		}catch (Exception e) {
			log.error("查询精品热门评论商品失败", e);
		}
	}
	
	/**
	  * @description <b>查询店铺热销</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-11
	  * @param 
	  * @return void
	*/
	private void findShopIndexCategoryGoods(HttpServletRequest request,Long companyId){
		HeadObject headObject=CommonHeadUtil.geneHeadObject(request,"990503-03");
		ShopIndexQryDTO qryDTO=new ShopIndexQryDTO(companyId,7,888);
		try{
			ResultObject resultObject = otherService.doService(new RequestObject(headObject, qryDTO));
			request.setAttribute("accessoriesGoods", resultObject.getContent());
		}catch (Exception e) {
			log.error("查询热销配件商品失败", e);
		}
	
		try{
			qryDTO.setIdentifier(666);
			ResultObject resultObject = otherService.doService(new RequestObject(headObject, qryDTO));
			request.setAttribute("boutiqueGoods", resultObject.getContent());
		}catch (Exception e) {
			log.error("查询热销精品商品失败", e);
		}
	}
	
	/**
	  * @description <b>店铺推荐</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-11
	  * @param 
	  * @return void
	*/
	private void findShopIndexPush(HttpServletRequest request,Long companyId){
		HeadObject headObject=CommonHeadUtil.geneHeadObject(request,"990503-04");
		ShopIndexQryDTO qryDTO=new ShopIndexQryDTO(companyId);
		qryDTO.setEnabled(1);
		try{
			ResultObject resultObject = otherService.doService(new RequestObject(headObject, qryDTO));
			request.setAttribute("shopIndexPush", resultObject.getContent());
		}catch (Exception e) {
			log.error("查询店铺推荐失败", e);
		}
	}
	
	
	/**
	  * @description <b>店铺关注</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-10
	  * @param @param request
	  * @param @param companyId
	  * @param @return
	  * @return Object
	*/
	@ResponseBody
	@RequestMapping("/shop/focusStore")
	public Object focusStore(HttpServletRequest request,@RequestParam("companyId")Long companyId){
		HeadObject headObject=CommonHeadUtil.geneHeadObject(request, "030202-03");
		try{
			MemberListDo memberListDo=CommonUtil.getMemberListDo(request);
			if(null!=memberListDo){
				StoreWishList storeWishList=new StoreWishList();
				storeWishList.setCompanyId(companyId);
				storeWishList.setMemberId(Long.parseLong(memberListDo.getMemberId()));
				storeWishList.setWishlistDate(new Date());
				return memberService.doService(new RequestObject(headObject, storeWishList));
			}else{
				return CommonUtil.notLoign(headObject);
			}
		}catch (Exception e) {
			return CommonUtil.processExpction(headObject);
		}
	}
    
}