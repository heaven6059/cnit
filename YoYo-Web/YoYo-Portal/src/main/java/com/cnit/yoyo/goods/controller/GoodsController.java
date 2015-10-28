package com.cnit.yoyo.goods.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.car.Car;
import com.cnit.yoyo.model.car.CarDept;
import com.cnit.yoyo.model.car.dto.AccessoryDTO;
import com.cnit.yoyo.model.car.dto.CarConfiginfoDTO;
import com.cnit.yoyo.model.car.dto.CarTypeDTO;
import com.cnit.yoyo.model.goods.Area;
import com.cnit.yoyo.model.goods.Brand;
import com.cnit.yoyo.model.goods.ConsultReplace;
import com.cnit.yoyo.model.goods.DriveOrConsult;
import com.cnit.yoyo.model.goods.Goods;
import com.cnit.yoyo.model.goods.GoodsTimePrice;
import com.cnit.yoyo.model.goods.GoodsViewHistory;
import com.cnit.yoyo.model.goods.GoodsWithBLOBs;
import com.cnit.yoyo.model.goods.Picture;
import com.cnit.yoyo.model.goods.ProductWithBLOBs;
import com.cnit.yoyo.model.goods.dto.CompanyDTO;
import com.cnit.yoyo.model.goods.dto.GoodsCarDTO;
import com.cnit.yoyo.model.goods.dto.PictureDTO;
import com.cnit.yoyo.model.goods.dto.ProductImgDTO;
import com.cnit.yoyo.model.member.CommentPraise;
import com.cnit.yoyo.model.member.Member;
import com.cnit.yoyo.model.member.MemberCommentWithBLOBs;
import com.cnit.yoyo.model.member.PamAccount;
import com.cnit.yoyo.model.member.dto.MemberCommentQryDTO;
import com.cnit.yoyo.model.member.dto.OrderCommentDetailDTO;
import com.cnit.yoyo.model.member.dto.ProSimpleDTO;
import com.cnit.yoyo.model.member.dto.TagsDTO;
import com.cnit.yoyo.model.order.OrderComment;
import com.cnit.yoyo.model.sales.activity.dto.ScoreBuyApplyDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.DateUtils;
import com.cnit.yoyo.util.StringUtil;
import com.cnit.yoyo.util.domain.ResultPage;

/**
 * @ClassName: GoodsController  
 * @Description: (商品详情页)  
 * @detail <详细说明>
 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
 * @date 2015-4-27 下午4:08:08  
 * @version 1.0.0
 */
@SuppressWarnings("unchecked")
@Controller
@RequestMapping("/goodsManager")
public class GoodsController {

	public static final Logger log = LoggerFactory.getLogger(GoodsController.class);
	@Autowired
	private RemoteService itemService;
	@Autowired
	private RemoteService orderService;
	@Autowired
	private RemoteService memberService;
	@Autowired
	public RemoteService searchClientService;
	@Autowired
	private RemoteService salesService;
	

	private Integer pageSize =10;
	
	//浏览历史记录cookie的key值
	private static final String HISTORY_COOKIE_NAME = "YOYO_History";
	//浏览历史记录cookie的寿命
	private static final int HISTORY_COOKIE_MAX_AGE_HOUR=7*24;
	//浏览历史记录cookie对象
	private Cookie historyCookie;
	//浏览历史记录保存的商品最大数量
	private static final int MAX_HISTORY_SIZE = 20;
	/**
	 * @throws Exception 
	 * @Title:  goodsDetailMain  
	 * @Description:  (商品详情页面)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-4-27 下午4:08:28  
	 * @version 1.0.0 
	 * @param @return
	 * @return String  返回类型 
	 * @throws
	 */
    @RequestMapping("/goodsIndex")
    public Object goodsMain(HttpServletRequest request,@RequestParam(value="goodsId",required=true)Integer goodsId) throws Exception 
    {
    	ModelAndView mv = new ModelAndView();
		ResultObject resultObject = null;
		HeadObject headObject = null;
		//查询商品详情
		GoodsWithBLOBs goods = this.selectGoodsBrandById(request, headObject, resultObject, goodsId);
		if(null!=goods){
			mv.addObject("src", goods.getMidPic());
			mv.addObject("brandId", goods.getBrandId());
			if(!"1".equals(goods.getDisabled())){//商品没有被删除
				goodsIsReport(request, goods.getGoodsId().longValue());
				selectCompanyInfo(request, goods.getCompanyId());
				findTopSales(request, goods.getCompanyId());
				findAccessoryAndRelated(request, goods.getGoodsId().longValue());
				findGoodsTimePrice(request, goods);
				if(goods.getCarId()>0){
					selectCarInfo(request, goods.getCarId(),goods.getCompanyId());
				}else if(goods.getAccId()>0){
					selectGoodsAccessoryParams(request, goodsId);
				}
				this.getGoodsScoreActivity(request, goodsId);
				this.buildAppointmentDate(request,goods.getGoodsId().longValue());
				mv.addObject("goods", goods);
			}
		}
		mv.setViewName("pages/biz/goods/goodsDetail");
		return mv;
	}
    
    private void getGoodsScoreActivity(HttpServletRequest request,Integer goodsId){
    	try{
			HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "060102-05");
			ResultObject resultObject = salesService.doService(new RequestObject(headObject, goodsId));			
			if(null!=resultObject.getContent()){
				request.setAttribute("scoreActivity", resultObject.getContent());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
    }
    
	public void buildAppointmentDate(HttpServletRequest request,Long goodsId){
		try{
			HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "011501-01");
			ResultObject resultObject = itemService.doService(new RequestObject(headObject, goodsId));
			request.setAttribute("dateList", resultObject.getContent());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void findGoodsTimePrice(HttpServletRequest request,Goods goods){
		try{
			HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "011601-01");
			Map<String, Object> param=new HashMap<String,Object>();
			param.put("priceDate", new Date());
			param.put("goodsId", goods.getGoodsId());
			ResultObject resultObject = itemService.doService(new RequestObject(headObject, param));
			if(null!=resultObject.getContent()){
				GoodsTimePrice goodsTimePrice=(GoodsTimePrice)resultObject.getContent();
				goods.setPrice(goodsTimePrice.getPrice());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    /**
     * @Title:  selectSaleCountByGoodsAndStauts 
     * @Description:  (这里用一句话描述这个方法的作用)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-6-25 下午4:12:57  
     * @version 1.0.0 
     * @param @param request
     * @param @param headObject
     * @param @param resultObject
     * @param @param goodsId
     * @param @return
     * @param @throws Exception
     * @return Integer  返回类型 
     * @throws
     */
    private Integer selectSaleCountByGoodsAndStauts(HttpServletRequest request, HeadObject headObject, ResultObject resultObject, Integer goodsId) throws Exception{
    	headObject = CommonHeadUtil.geneHeadObject(request, "2000020107-10", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	JSONObject jsonObject = new JSONObject();
    	jsonObject.put("goodsId", goodsId);
    	jsonObject.put("status", "finish");
        resultObject = orderService.doService(new RequestObject(headObject, jsonObject));
        if(resultObject!=null&&resultObject.getContent()!=null){
        	return (Integer)resultObject.getContent();
        }else{
        	return 0;
        }
    }
    
    /**
     * @Title:  selectGoodsById  
     * @Description:  (根据商品id查询商品对象)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-11 下午1:31:07  
     * @version 1.0.0 
     * @param @param request
     * @param @param headObject
     * @param @param resultObject
     * @param @param goodsId
     * @param @return
     * @param @throws Exception
     * @return GoodsWithBLOBs  返回类型 
     * @throws
     */
    private GoodsWithBLOBs selectGoodsById(HttpServletRequest request, HeadObject headObject, ResultObject resultObject, Integer goodsId) throws Exception{
    	headObject = CommonHeadUtil.geneHeadObject(request, "2000020105-07", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        resultObject = itemService.doService(new RequestObject(headObject, goodsId));
        return (GoodsWithBLOBs) JSONObject.toBean(((JSONObject)resultObject.getContent()),GoodsWithBLOBs.class);
    }
    
   
    /**
     * @Title:  selectGoodsBrandById  
     * @Description:  (根据商品id查询商品对象，含品牌名称)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-7-1 下午3:24:32  
     * @version 1.0.0 
     * @param @param request
     * @param @param headObject
     * @param @param resultObject
     * @param @param goodsId
     * @param @return
     * @param @throws Exception
     * @return GoodsWithBLOBs  返回类型 
     * @throws
     */
    private GoodsWithBLOBs selectGoodsBrandById(HttpServletRequest request, HeadObject headObject, ResultObject resultObject, Integer goodsId){
    	try{
	    	headObject = CommonHeadUtil.geneHeadObject(request, "010201-22", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	        resultObject = itemService.doService(new RequestObject(headObject, goodsId));
	        return (GoodsWithBLOBs) resultObject.getContent();
    	}catch(Exception e){
    		e.printStackTrace();
    		return null;
    	}
    }
    
    private void selectCompanyInfo(HttpServletRequest request,Integer compayId){
    	try{
	    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020103-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	    	JSONObject content = new JSONObject();
	    	content.put("companyId", compayId);
	    	ResultObject resultObject = memberService.doService(new RequestObject(headObject, content));
	    	request.setAttribute("company", JSONObject.toBean(((JSONObject)resultObject.getContent()),CompanyDTO .class));
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    private void selectCarInfo(HttpServletRequest request,Integer carId,Integer companyId){
    	try{
	    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010301-01");
	    	ResultObject resultObject = itemService.doService(new RequestObject(headObject, carId));
	    	CarTypeDTO carTypeDTO=(CarTypeDTO) resultObject.getContent();
	    	request.setAttribute("carInfo", carTypeDTO);
	    	
	    	Map<String, Object> params=new HashMap<String,Object>();
	    	headObject = CommonHeadUtil.geneHeadObject(request, "010201-26");
	    	params.put("deptId", carTypeDTO.getDeptId());
	    	params.put("companyId", companyId);
	    	resultObject = itemService.doService(new RequestObject(headObject, params));
	    	request.setAttribute("otherCarInfos", resultObject.getContent());
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    /**
     * @description <b>获取商品配件参数</b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015年7月28日
     * @param request
     * @param goodsId
     * void
    */
    private void selectGoodsAccessoryParams(HttpServletRequest request,Integer goodsId){
    	try{
	    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010203-01");
	    	Integer [] data={goodsId};
	    	ResultObject resultObject = itemService.doService(new RequestObject(headObject, data));
			Map<String, Object> compareResult=(Map<String, Object>) resultObject.getContent();
	    	request.setAttribute("accessoryParams", JSON.toJSON(compareResult.get("compareResult")));
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    /**
     * @description <b>商品是否举报</b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015年7月28日
     * @param request
     * @param goodsId
     * void
    */
    private void goodsIsReport(HttpServletRequest request,Long goodsId){
    	try{
	    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030112-06", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	    	ResultObject resultObject = memberService.doService(new RequestObject(headObject, goodsId));
	    	if(resultObject.getRetCode().equals(ErrorCode.SUCCESS)){
	    		Map<String, Integer> resultMap = (Map<String, Integer>) resultObject.getContent();
	   			request.setAttribute("hasReport", resultMap.get("hasReport"));
	    	}
    	}catch (Exception e) {
    		e.printStackTrace();
		}
    }
    
    /**
     * @Title:  findGoodsDetail  
     * @Description:  (获取商品详情页的数据)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-27 下午4:11:01  
     * @version 1.0.0 
     * @param request
     * @param response
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/findGoodsDetail")
    @ResponseBody
	public Object findGoodsDetail(HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		log.info("start[GoodsController.findGoodsDetail]");
		ResultObject resultObject = null;
		HeadObject headObject = null;
		String goodsIdString = request.getParameter("goodsId");
		if(goodsIdString!=null&&!"".equals(goodsIdString.trim())){
			Integer goodsId = Integer.parseInt(goodsIdString);
			//查询商品详情
			headObject = CommonHeadUtil.geneHeadObject(request, "2000020105-07");
	        resultObject = itemService.doService(new RequestObject(headObject, goodsId));
	        GoodsWithBLOBs goods = (GoodsWithBLOBs) JSONObject.toBean(((JSONObject)resultObject.getContent()),GoodsWithBLOBs.class);
	        if(goods!=null&&goods.getGoodsId()!=null&&!"1".equals(goods.getDisabled())){
	        	if(!"0".equals(goods.getMarketable())){
	        		//查询货品详情
		        	headObject = CommonHeadUtil.geneHeadObject(request, "2000020121-04");
			        resultObject = itemService.doService(new RequestObject(headObject, goodsId));
			        List<ProductImgDTO> productDTOList = (List<ProductImgDTO>) resultObject.getContent();
			        if(productDTOList!=null&&productDTOList.size()>=1){
			        	List<Picture> picList = new ArrayList<Picture>();
			        	headObject = CommonHeadUtil.geneHeadObject(request, "011601-01");
			        	for (ProductImgDTO productImgDTO : productDTOList) {
			        		if(null==productImgDTO.getPicList()||productImgDTO.getPicList().size()<=0){
				        		Picture pic = new Picture();
			        			pic.setPicturePath(StringUtil.isEmpty(goods.getBigPic())?goods.getMidPic():goods.getBigPic());
			        			picList.add(pic);
			        			productImgDTO.setPicList(picList);
			        		}
		        			
		        			Map<String, Object> param=new HashMap<String,Object>();
		        			param.put("priceDate", new Date());
		        			param.put("goodsId", goods.getGoodsId());
		        			resultObject = itemService.doService(new RequestObject(headObject, param));
		        			if(null!=resultObject.getContent()){
		        				GoodsTimePrice goodsTimePrice=(GoodsTimePrice)resultObject.getContent();
		        				productImgDTO.setPrice(goodsTimePrice.getPrice());
		        				productImgDTO.setPriceDouble(goodsTimePrice.getPrice().doubleValue());
		        			}
		        			
						}
			        	for(int i=productDTOList.size()-1 ; i>=0 ; i--){
			        		if(!"1".equals(productDTOList.get(i).getDisabled()) && !"1".equals(productDTOList.get(i).getLimitStore()) &&!"1".equals(productDTOList.get(i).getLimitStoredown())){
			        			if(!(productDTOList.get(i).getPicList()!=null&&productDTOList.get(i).getPicList().size()>=1) ){
				        			picList = new ArrayList<Picture>();
				        			Picture pic = new Picture();
				        			pic.setPicturePath(goods.getBigPic()!=null&&!"".equals(goods.getBigPic())?goods.getBigPic():goods.getMidPic());
				        			picList.add(pic);
				        			productDTOList.get(i).setPicList(picList);
				        		}
			        		}else{
			        			productDTOList.remove(i);
			        		}
			        	}
			        }
			        resultObject.setContent(productDTOList);
	        	}else{
	        		resultObject.setContent(null);
	        	}
	        }else{
	        	resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,isGoodsExist:false}"));
	        }
		}else{
			resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,isGoodsExist:false}"));
		}
		log.info("end[GoodsController.findGoodsDetail]");
		return resultObject;
	}
    
    /**
     * @Title:  findCouponsAndAttribute  
     * @Description:  (查询店铺优惠券)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-5 下午1:35:09  
     * @version 1.0.0 
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/findCouponsAndAttribute")
    @ResponseBody
	public Object findCouponsAndAttribute(HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		log.info("start[GoodsController.findCoupons]");
		ResultObject resultObject = null;
		HeadObject headObject = null;
		JSONObject jsonObject = new JSONObject();
		String goodsIdString = request.getParameter("goodsId");
		if(goodsIdString!=null&&!"".equals(goodsIdString.trim())){
			Integer goodsId = Integer.parseInt(goodsIdString);
			//查询商品详情
			headObject = CommonHeadUtil.geneHeadObject(request, "2000020105-07", GlobalStatic.SYSTEM_CODE_DATA,
	                GlobalStatic.SYSTEM_CODE_BACK);
	        resultObject = itemService.doService(new RequestObject(headObject, goodsId));
	        GoodsWithBLOBs goods = (GoodsWithBLOBs) JSONObject.toBean(((JSONObject)resultObject.getContent()),GoodsWithBLOBs.class);
	        if(goods!=null&&goods.getGoodsId()!=null){
	        	//查询优惠券列表
	        	jsonObject.clear();
	        	AccessoryDTO attributeList = null;
	        	if(goods.getAccId()!=null && goods.getAccId() > 0){
	        		headObject = CommonHeadUtil.geneHeadObject(request, "2000020104-12", GlobalStatic.SYSTEM_CODE_DATA,
			                GlobalStatic.SYSTEM_CODE_BACK);
			        resultObject = itemService.doService(new RequestObject(headObject, goods.getAccId()));
			       attributeList = (AccessoryDTO) resultObject.getContent();
			       
			       headObject = CommonHeadUtil.geneHeadObject(request, "2000020104-09", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		    		JSONObject content = new JSONObject();
		    		if(attributeList!=null){
		    			content.put("catalogId", attributeList.getCatId());
		    			content.put("accId", attributeList.getAccId());
		    			resultObject = itemService.doService(new RequestObject(headObject, content));
		    			jsonObject.put("accParams", resultObject.getContent());
		    		}
	        	}
//		        jsonObject.put("couponList", couponList);
		        jsonObject.put("attributeList", attributeList);
		        resultObject.setContent(jsonObject);
	        }else{
	        	resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,isGoodsExist:false}"));
	        }
		}else{
			resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,isGoodsExist:false}"));
		}
		log.info("end[GoodsController.findCoupons]");
		return resultObject;
	}

    /**
     * @Title:  findAccessoryAndRelated  
     * @Description:  (查询优惠套餐)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-5 下午5:00:44  
     * @version 1.0.0 
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/findAccessoryAndRelated")
    @ResponseBody
	public Object findAccessoryAndRelated(HttpServletRequest request,Long goodsId) throws Exception 
	{
		log.info("start[GoodsController.findAccessory]");
		ResultObject resultObject = null;
		HeadObject headObject = null;
		JSONObject jsonObject = new JSONObject();
		//查询相关商品
		headObject = CommonHeadUtil.geneHeadObject(request, "010201-06");
		jsonObject.put("goodsId", goodsId);
		jsonObject.put("pageSize", 3);
		jsonObject.put("pageIndex", 1);
        resultObject = itemService.doService(new RequestObject(headObject, jsonObject));
        request.setAttribute("realtedGoods", resultObject.getContent());
		
		//查询商品优惠套餐
		headObject = CommonHeadUtil.geneHeadObject(request, "010201-08");
		jsonObject.put("goodsId", goodsId);
		jsonObject.put("pageSize", 10);
		jsonObject.put("pageIndex", 1);
        resultObject = itemService.doService(new RequestObject(headObject, jsonObject));
        request.setAttribute("accessoryGoods", resultObject.getContent());
        log.info("end[GoodsController.findAccessory]");
		return resultObject;
	}
    
    /**
     * @Title:  findRelatedGoods  
     * @Description:  (分页查询相关商品)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-8 上午11:04:40  
     * @version 1.0.0 
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/findRelatedGoods")
    @ResponseBody
	public Object findRelatedGoods(HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		log.info("start[GoodsController.findRelatedGoods]");
		ResultObject resultObject = null;
		HeadObject headObject = null;
		JSONObject jsonObject = new JSONObject();
		String goodsIdString = request.getParameter("goodsId");
		String pageIndexString = request.getParameter("pageIndex");
		Integer pageIndex;
		if(pageIndexString!=null&&!"".equals(pageIndexString)){
			pageIndex = Integer.parseInt(pageIndexString);
		}else{
			pageIndex=1;
		}
		if(goodsIdString!=null&&!"".equals(goodsIdString.trim())){
			Integer goodsId = Integer.parseInt(goodsIdString);
			//查询相关商品
			headObject = CommonHeadUtil.geneHeadObject(request, "010201-06", GlobalStatic.SYSTEM_CODE_DATA,
	                GlobalStatic.SYSTEM_CODE_BACK);
			jsonObject.put("goodsId", goodsId);
			jsonObject.put("pageSize", 3);
			jsonObject.put("pageIndex", pageIndex);
	        resultObject = itemService.doService(new RequestObject(headObject, jsonObject));
		}else{
			resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,isGoodsExist:false}"));
		}
		log.info("end[GoodsController.findRelatedGoods]");
		return resultObject;
	}
    
    /**
     * @Title:  findAccessory  
     * @Description:  (分页查询优惠套装)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-9 上午10:02:37  
     * @version 1.0.0 
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/findAccessory")
    @ResponseBody
	public Object findAccessory(HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		log.info("start[GoodsController.findAccessory]");
		ResultObject resultObject = null;
		HeadObject headObject = null;
		JSONObject jsonObject = new JSONObject();
		String goodsIdString = request.getParameter("goodsId");
		String pageIndexString = request.getParameter("pageIndex");
		Integer pageIndex;
		if(pageIndexString!=null&&!"".equals(pageIndexString)){
			pageIndex = Integer.parseInt(pageIndexString);
		}else{
			pageIndex=1;
		}
		if(goodsIdString!=null&&!"".equals(goodsIdString.trim())){
			Integer goodsId = Integer.parseInt(goodsIdString);
	        //查询商品优惠套餐
			headObject = CommonHeadUtil.geneHeadObject(request, "010201-08", GlobalStatic.SYSTEM_CODE_DATA,
	                GlobalStatic.SYSTEM_CODE_BACK);
			jsonObject.put("goodsId", goodsId);
			jsonObject.put("pageSize", 10);
			jsonObject.put("pageIndex", pageIndex);
	        resultObject = itemService.doService(new RequestObject(headObject, jsonObject));
		}else{
			resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,isGoodsExist:false}"));
		}
		log.info("end[GoodsController.findAccessory]");
		return resultObject;
	}
    
    /**
     * @Title:  findAccessoryGoods  
     * @Description:  (分页查询优惠套餐中的商品)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-9 上午11:34:37  
     * @version 1.0.0 
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/findAccessoryGoods")
    @ResponseBody
	public Object findAccessoryGoods(HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		log.info("start[GoodsController.findAccessoryGoods]");
		ResultObject resultObject = null;
		HeadObject headObject = null;
		JSONObject jsonObject = new JSONObject();
		String accessoryIdString = request.getParameter("accessoryId");
		String pageIndexString = request.getParameter("pageIndex");
		Integer pageIndex;
		if(pageIndexString!=null&&!"".equals(pageIndexString)){
			pageIndex = Integer.parseInt(pageIndexString);
		}else{
			pageIndex=1;
		}
		if(pageIndex<1){
			pageIndex = 1;
		}
		if(accessoryIdString!=null&&!"".equals(accessoryIdString.trim())){
			Integer accessoryId = Integer.parseInt(accessoryIdString);
	        //查询商品优惠套餐
			headObject = CommonHeadUtil.geneHeadObject(request, "010201-05", GlobalStatic.SYSTEM_CODE_DATA,
	                GlobalStatic.SYSTEM_CODE_BACK);
			jsonObject.put("accessoryId", accessoryId);
			jsonObject.put("isPager", "1");
//			if(pageIndex==1){
//				jsonObject.put("pageSize", 4);
//			}else{
//				jsonObject.put("pageSize", 5);
//			}
			jsonObject.put("pageSize", 4);
			jsonObject.put("pageIndex", pageIndex);
	        resultObject = itemService.doService(new RequestObject(headObject, jsonObject));
		}else{
			resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,isAccessoryExist:false}"));
		}
		log.info("end[GoodsController.findAccessoryGoods]");
		return resultObject;
	}
    
    private PamAccount findAccountId(HttpServletRequest request) throws Exception{
//		return (Integer) CommonUtil.getSession(request).getAttribute("accountId");
    	Integer accountId = (Integer) CommonUtil.getSession(request).getAttribute("accountId");
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020109-02", 
				GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = memberService.doService(new RequestObject(headObject, accountId));
		PamAccount pamAccount = null;
		if(resultObject!=null&&resultObject.getContent()!=null){
			pamAccount = (PamAccount) resultObject.getContent();
		}
		return pamAccount;
    }
    
    /**
     * @Title:  driveAndConsult  
     * @Description:  (预约试驾或询问最低价)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-11 上午9:28:51  
     * @version 1.0.0 
     * @param @param request
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/enquiry")
    public Object enquiry(HttpServletRequest request,Long goodsId) throws Exception{
    	ModelAndView mv = new ModelAndView();
    	PamAccount pamAccount = findAccountId(request);
		if(pamAccount!=null&&!"100".equals(pamAccount.getAccountType())){
			mv.addObject("isBuyer",false);
			mv.setViewName("pages/biz/goods/enquiry");
			return mv;
		}
    	String type = request.getParameter("type");
    	mv.addObject("type", type);
		ResultObject resultObject = null;
		HeadObject headObject = null;
		headObject = CommonHeadUtil.geneHeadObject(request, "010201-27");
		resultObject = itemService.doService(new RequestObject(headObject, goodsId));
		GoodsCarDTO goodsCarDTO=(GoodsCarDTO) resultObject.getContent();
		request.setAttribute("goodsCar", goodsCarDTO);
		//查询所有车系
		headObject = CommonHeadUtil.geneHeadObject(request, "2000020117-06");
        resultObject = itemService.doService(new RequestObject(headObject, null));
        List<CarDept> carDeptList = (List<CarDept>) resultObject.getContent();
        mv.addObject("carDeptList",carDeptList);
        //查询省份
        headObject = CommonHeadUtil.geneHeadObject(request, "1000020104-01");
        Area area = new Area();
        area.setAreaDeep(1);
        area.setAreaParentId(0);
        resultObject = itemService.doService(new RequestObject(headObject, area));
        List<Area> areaList = (List<Area>) resultObject.getContent();
        mv.addObject("areaList", areaList);
        
        headObject = CommonHeadUtil.geneHeadObject(request, "010701-01");
        Map<String, Object> params=new HashMap<String,Object>();
		params.put("identifier", 999);	
   	 	resultObject = itemService.doService(new RequestObject(headObject, params));
   	 	mv.addObject("allBrandList", resultObject.getContent());
        
        //查询包含车型的品牌
		headObject = CommonHeadUtil.geneHeadObject(request, "2000020109-21");
		resultObject = itemService.doService(new RequestObject(headObject, null));
		List<Brand> brandList = (List<Brand>) resultObject.getContent();
		mv.addObject("brandList", brandList);
		//查询当前年份
		mv.addObject("thisYear", Calendar.getInstance().get(Calendar.YEAR));
		mv.addObject("orderType",request.getParameter("orderType"));
		mv.setViewName("pages/biz/goods/enquiry");
		return mv;
	}
    
    /**
     * @Title:  getApplyList  
     * @Description:  (这里用一句话描述这个方法的作用)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-12 下午4:56:03  
     * @version 1.0.0 
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/getApplyList")
    @ResponseBody
	public Object getApplyList(HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		log.info("start[GoodsController.getApplyList]");
		ResultObject resultObject = null;
		HeadObject headObject = null;
		//查询车型列表
        headObject = CommonHeadUtil.geneHeadObject(request, "010901-03", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageIndex", 1);
        jsonObject.put("pageSize", 10);
        jsonObject.put("type", request.getParameter("type"));
        resultObject = itemService.doService(new RequestObject(headObject, jsonObject));
		log.info("end[GoodsController.getApplyList]");
		return resultObject;
	}
    
    /**
     * @Title:  getCarList  
     * @Description:  (根据车系id查询车型列表)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-12 上午10:09:01  
     * @version 1.0.0 
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/getCarList")
    @ResponseBody
	public Object getCarList(HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		log.info("start[GoodsController.getCarList]");
		ResultObject resultObject = null;
		HeadObject headObject = null;
		String carDeptIdString = request.getParameter("carDeptId");
		if(carDeptIdString!=null&&!"".equals(carDeptIdString)){
			Integer carDeptId = Integer.parseInt(carDeptIdString);
			if(carDeptId!=null&&carDeptId!=0){
				//查询车型列表
		        headObject = CommonHeadUtil.geneHeadObject(request, "010301-13", GlobalStatic.SYSTEM_CODE_DATA,
		                GlobalStatic.SYSTEM_CODE_BACK);
		        resultObject = itemService.doService(new RequestObject(headObject, carDeptId));
			}else{
				resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,isCarDeptExist:false}"));
			}
		}else{
			resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,isCarDeptExist:false}"));
		}
		log.info("end[GoodsController.getCarList]");
		return resultObject;
	}
    
    /**
     * @Title:  getCarDeptList  
     * @Description:  (根据用户旧车品牌id获取所属的车型列表)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-12 下午1:23:27  
     * @version 1.0.0 
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/getUserCarDeptList")
    @ResponseBody
	public Object getUserCarDeptList(HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		log.info("start[GoodsController.getUserCarDeptList]");
		ResultObject resultObject = null;
		HeadObject headObject = null;
		String carIdString = request.getParameter("carId");
		if(carIdString!=null&&!"".equals(carIdString)){
			Integer carId = Integer.parseInt(carIdString);
			if(carId!=null&&carId!=0){
		        headObject = CommonHeadUtil.geneHeadObject(request, "010802-07", GlobalStatic.SYSTEM_CODE_DATA,
		                GlobalStatic.SYSTEM_CODE_BACK);
		        resultObject = itemService.doService(new RequestObject(headObject, carId));
			}else{
				resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,isCarExist:false}"));
			}
		}else{
			resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,isCarExist:false}"));
		}
		log.info("end[GoodsController.getUserCarDeptList]");
		return resultObject;
	}
    
    /**
     * @Title:  getUserCarList  
     * @Description:  (根据用户旧车的车型查找车系)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-12 下午1:47:27  
     * @version 1.0.0 
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/getUserCarList")
    @ResponseBody
	public Object getUserCarList(HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		log.info("start[GoodsController.getUserCarList]");
		ResultObject resultObject = null;
		HeadObject headObject = null;
		String brandIdString = request.getParameter("brandId");
		if(brandIdString!=null&&!"".equals(brandIdString)){
			Integer brandId = Integer.parseInt(brandIdString);
			if(brandId!=null&&brandId!=0){
				//查询车型列表
		        headObject = CommonHeadUtil.geneHeadObject(request, "010301-16", GlobalStatic.SYSTEM_CODE_DATA,
		                GlobalStatic.SYSTEM_CODE_BACK);
		        resultObject = itemService.doService(new RequestObject(headObject, brandId));
			}else{
				resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,isBrandExist:false}"));
			}
		}else{
			resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,isBrandExist:false}"));
		}
		log.info("end[GoodsController.getUserCarList]");
		return resultObject;
	}
    
    /**
     * @Title:  getCityInfo  
     * @Description:  (根据省份id获取城市列表)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-11 下午3:44:28  
     * @version 1.0.0 
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/getCityInfo")
    @ResponseBody
	public Object getCityInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		log.info("start[GoodsController.getCityInfo]");
		ResultObject resultObject = null;
		HeadObject headObject = null;
		String provinceIdString = request.getParameter("provinceId");
		if(provinceIdString!=null&&!"".equals(provinceIdString)){
			Integer provinceId = Integer.parseInt(provinceIdString);
			if(provinceId!=null&&provinceId!=0){
				//查询城市
		        headObject = CommonHeadUtil.geneHeadObject(request, "1000020104-01", GlobalStatic.SYSTEM_CODE_DATA,
		                GlobalStatic.SYSTEM_CODE_BACK);
		        Area area = new Area();
		        area.setAreaDeep(2);
		        area.setAreaParentId(provinceId);
		        resultObject = itemService.doService(new RequestObject(headObject, area));
			}else{
				resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,isProvinceExist:false}"));
			}
		}else{
			resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,isProvinceExist:false}"));
		}
		log.info("end[GoodsController.getCityInfo]");
		return resultObject;
	}
    
    /**
     * @Title:  getCountyInfo  
     * @Description:  (根据城市id获取地区列表)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-11 下午4:04:05  
     * @version 1.0.0 
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/getCountyInfo")
    @ResponseBody
	public Object getCountyInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		log.info("start[GoodsController.getCountyInfo]");
		ResultObject resultObject = null;
		HeadObject headObject = null;
		String cityIdString = request.getParameter("cityId");
		if(cityIdString!=null&&!"".equals(cityIdString)){
			Integer cityId = Integer.parseInt(cityIdString);
			if(cityId!=null&&cityId!=0){
				//查询地区
		        headObject = CommonHeadUtil.geneHeadObject(request, "1000020104-01", GlobalStatic.SYSTEM_CODE_DATA,
		                GlobalStatic.SYSTEM_CODE_BACK);
		        Area area = new Area();
		        area.setAreaDeep(3);
		        area.setAreaParentId(cityId);
		        resultObject = itemService.doService(new RequestObject(headObject, area));
			}else{
				resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,isCityExist:false}"));
			}
		}else{
			resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,isCityExist:false}"));
		}
		log.info("end[GoodsController.getCountyInfo]");
		return resultObject;
	}
    
    /**
     * @Title:  createOrder  
     * @Description:  (预约试驾)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-11 下午4:58:15  
     * @version 1.0.0 
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/createOrder")
    @ResponseBody
	public Object createOrder(HttpServletRequest request,HttpServletResponse response,DriveOrConsult driveOrConsult,ConsultReplace consultReplace) throws Exception 
	{
		log.info("start[GoodsController.createOrder]");
		ResultObject resultObject = null;
		HeadObject headObject = null;
		//查询是否已存在相同的对象
		headObject = CommonHeadUtil.geneHeadObject(request, "010901-02");
        resultObject = itemService.doService(new RequestObject(headObject, driveOrConsult));
        List<DriveOrConsult> driveOrConsultList = (List<DriveOrConsult>) resultObject.getContent();
        if(driveOrConsultList!=null&&driveOrConsultList.size()>=1){
        	return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,isObjectExist:true}"));
        }else{
        	if(null!=driveOrConsult.getIsReplace()&&driveOrConsult.getIsReplace()==1){
        		consultReplace.setCarId(Integer.valueOf(request.getParameter("replaceCarId")));
        	}
        	driveOrConsult.setAddTime(System.currentTimeMillis());
        	//保存对象
        	headObject = CommonHeadUtil.geneHeadObject(request, "010901-01");
        	JSONObject jsonObject = new JSONObject();
        	jsonObject.put("driveOrConsult", driveOrConsult);
        	jsonObject.put("consultReplace", consultReplace);
            resultObject = itemService.doService(new RequestObject(headObject, jsonObject));
            if(resultObject.getRetCode().equals(ErrorCode.SUCCESS)){
            	resultObject =  new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:true}"));
            }
        }
		log.info("end[GoodsController.createOrder]");
		return resultObject;
	}
    
    
    /**
     * @Title:  updateGoodsInfo  
     * @Description:  (更新商品信息(浏览次数等))  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-13 上午11:28:16  
     * @version 1.0.0 
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/updateGoodsInfo")
    @ResponseBody
	public Object updateGoodsInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		log.info("start[GoodsController.updateGoodsInfo]");
		ResultObject resultObject = null;
		HeadObject headObject = null;
		JSONObject jsonObject = new JSONObject();
		String goodsIdString = request.getParameter("goodsId");
		if(goodsIdString!=null&&!"".equals(goodsIdString.trim())){
			Integer goodsId = Integer.parseInt(goodsIdString);
			//保存商品浏览历史
	        Integer accountId = (Integer) request.getSession().getAttribute("accountId");
			String loginStatus = (String) request.getSession().getAttribute("loginStatus");
	        if(accountId!=null&&loginStatus!=null&&loginStatus.equals(GlobalStatic.ACCOUNT_STATUS_ON)){//用户已登录
	        	headObject = CommonHeadUtil.geneHeadObject(request,"1000020102-17",GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
				jsonObject.put("accountId", accountId);
				resultObject = memberService.doService(new RequestObject(headObject, jsonObject));
				Member member = (Member) JSONObject.toBean(JSONObject.fromObject(resultObject.getContent()),Member.class);
				if(member!=null){
					GoodsViewHistory history = new GoodsViewHistory();
					history.setGoodsId(goodsId.longValue());
					history.setMemberId(member.getMemberId());
					history.setLastModify(System.currentTimeMillis());
					headObject = CommonHeadUtil.geneHeadObject(request,"010201-15",GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
					resultObject = itemService.doService(new RequestObject(headObject, history));
				}
	        }
	        //保存商品浏览历史
	        ProSimpleDTO proSimpleDTO = new ProSimpleDTO("", "", goodsId, null, null, null, null);
	        addProToCookie(request, response, proSimpleDTO);
	        
	        //增加商品浏览次数
	        headObject = CommonHeadUtil.geneHeadObject(request,"010201-16", 
					GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
			resultObject = itemService.doService(new RequestObject(headObject, goodsId));
		}
		log.info("end[GoodsController.updateGoodsInfo]");
		return resultObject;
	}
    
    /**
	 * @Title: addProToCookie 
	 * @Description: (添加商品至浏览历史cookie) 
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-4-11 
	 * @version 1.0.0 
	 * @param request
	 * @param response
	 * @param newCart
	 * @param @return
	 * @param @throws UnsupportedEncodingException    
	 * @return String    返回类型 
	 * @throws
	 */
	private String addProToCookie(HttpServletRequest request,
			HttpServletResponse response, ProSimpleDTO proSimpleDTO)
			throws UnsupportedEncodingException 
	{
		JSONArray array = this.selectCookie(request);
		List<ProSimpleDTO> proSimpleDTOList = null;
		if (array != null && array.size() >= 1) 
		{
			proSimpleDTOList = (List<ProSimpleDTO>) JSONArray.toCollection(array, ProSimpleDTO.class);
		}
		if (proSimpleDTOList == null) // cookie没有浏览历史数据
		{	
			proSimpleDTOList = new ArrayList<ProSimpleDTO>();
		}

		// 判断cookie中是否已存在该货品，存在则删除
		for (int i = proSimpleDTOList.size()-1; i>=0 ; i--) 
		{
			if (proSimpleDTOList.get(i).getGoodsId().equals(proSimpleDTO.getGoodsId())) // 该商品已存在
			{
				proSimpleDTOList.remove(i);
			}
		}
		int size = proSimpleDTOList.size();
		for (int i = size - MAX_HISTORY_SIZE; i>=0 ; i--) 
		{
			proSimpleDTOList.remove(i);
		}
		proSimpleDTOList.add(proSimpleDTO);
		
		this.saveCookie(proSimpleDTOList, response);
		return null;
	}
    
	/**
	 * @Title: selectCookie 
	 * @Description: (从cookie中获取数据) 
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-4-11 
	 * @version 1.0.0 
	 * @param request
	 * @param @return
	 * @param @throws UnsupportedEncodingException    
	 * @return JSONArray    返回类型 
	 * @throws
	 */
	private JSONArray selectCookie(HttpServletRequest request)
			throws UnsupportedEncodingException 
	{
		Cookie cookies[] = request.getCookies();
		if (cookies != null && cookies.length >= 1) 
		{
			for (Cookie c : cookies) 
			{
				if (c.getName().equals(HISTORY_COOKIE_NAME)) 
				{
					String jsonString = URLDecoder.decode(c.getValue(), "utf-8");
					historyCookie = c;
					if (jsonString != null && !"".equals(jsonString.trim())) 
					{
						JSONArray array = JSONArray.fromObject(jsonString);
						return array;
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * @Title: saveCookie 
	 * @Description: (将购物车数据保存到cookie) 
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-4-11 
	 * @version 1.0.0 
	 * @param cartList
	 * @param response
	 * @param @throws UnsupportedEncodingException    
	 * @return void    返回类型 
	 * @throws
	 */
	private void saveCookie(List<ProSimpleDTO> ProSimpleDTOList, HttpServletResponse response)
			throws UnsupportedEncodingException 
	{
		if (ProSimpleDTOList != null && ProSimpleDTOList.size() >= 1) 
		{
			JSONArray array = JSONArray.fromObject(ProSimpleDTOList);
			if (historyCookie != null) 
			{
				historyCookie.setValue(URLEncoder.encode(array.toString(), "utf-8"));
			} else {
				historyCookie = new Cookie(HISTORY_COOKIE_NAME, URLEncoder.encode(array.toString(), "utf-8"));
			}
			historyCookie.setMaxAge(HISTORY_COOKIE_MAX_AGE_HOUR * 60 * 60);
			historyCookie.setPath("/");
			response.addCookie(historyCookie);
		} else {
			if (historyCookie != null) 
			{
				historyCookie.setMaxAge(0);
				historyCookie.setPath("/");
				response.addCookie(historyCookie);
			}
		}
	}
    
    /**
     * @Title:  findCarData  
     * @Description:  (加载商品车型信息)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-13 上午11:28:49  
     * @version 1.0.0 
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/findCarData")
    @ResponseBody
    public Object findCarData(HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		log.info("start[GoodsController.findCarData]");
		ResultObject resultObject = null;
		HeadObject headObject = null;
		JSONObject jsonObject = new JSONObject();
		com.alibaba.fastjson.JSONObject jsonObject2 = new com.alibaba.fastjson.JSONObject();
//		String goodsIdString = request.getParameter("goodsId");
		String carIdString = request.getParameter("carId");
//		if(goodsIdString!=null&&!"".equals(goodsIdString.trim())){
//			Integer goodsId = Integer.parseInt(goodsIdString);
//			
//		}
		if(carIdString!=null&&!"".equals(carIdString)){
			Integer carId = Integer.parseInt(carIdString);
			//查询车型信息
		    headObject = CommonHeadUtil.geneHeadObject(request, "010301-14", GlobalStatic.SYSTEM_CODE_DATA,
		            GlobalStatic.SYSTEM_CODE_BACK);
		    resultObject = itemService.doService(new RequestObject(headObject,carId));
		    if(resultObject.getContent()!=null){
		    	CarTypeDTO carType = (CarTypeDTO) resultObject.getContent();
		    	jsonObject.put("carType", carType);
		    	jsonObject2.put("carType", carType);
		    	if(carType!=null && carType.getCarId()!=null){
		    		//查询同车系的车型对象
			        headObject = CommonHeadUtil.geneHeadObject(request, "010301-13", GlobalStatic.SYSTEM_CODE_DATA,
			                GlobalStatic.SYSTEM_CODE_BACK);
			        resultObject = itemService.doService(new RequestObject(headObject, carType.getDeptId()));
			        List<Car> carList = (List<Car>) resultObject.getContent();
			        jsonObject.put("carList", carList);
			        jsonObject2.put("carList", carList);
		    	}
		    }
		}
		resultObject.setContent(jsonObject2);
		log.info("end[GoodsController.findCarData]");
		return resultObject;
	}

    /**
     * @Title:  findGoodsComment  
     * @Description:  (获取商品评论)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-13 下午3:34:37  
     * @version 1.0.0 
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/findGoodsComment")
    @ResponseBody
    public Object findGoodsComment(HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
    	log.info("start[GoodsController.findGoodsComment]");
		ResultObject resultObject = null;
		HeadObject headObject = null;
		JSONObject jsonObject = new JSONObject();
		String goodsIdString = request.getParameter("goodsId");
		String pageIndexString = request.getParameter("pageIndex");
		if(goodsIdString!=null&&!"".equals(goodsIdString.trim())){
			Integer pageIndex = 1;
			if(pageIndexString!=null&&!"".equals(pageIndexString)){
				pageIndex = Integer.parseInt(pageIndexString);
			}
			if(pageIndex < 1){
				pageIndex = 1;
			}
			Integer goodsId = Integer.parseInt(goodsIdString);
			headObject = CommonHeadUtil.geneHeadObject(request, "020105-08", GlobalStatic.SYSTEM_CODE_DATA,
		            GlobalStatic.SYSTEM_CODE_BACK);
			jsonObject.put("pageIndex", pageIndex);
			jsonObject.put("pageSize", pageSize);
			jsonObject.put("goodsId", goodsId); 
		    resultObject = orderService.doService(new RequestObject(headObject,jsonObject));
		    Map<String ,Class> map = new HashMap<String,Class>();
		    map.put("rows", OrderCommentDetailDTO.class);
		    //查询评论列表
		    ResultPage<OrderCommentDetailDTO> commentPage = (ResultPage<OrderCommentDetailDTO>) JSONObject.toBean(JSONObject.fromObject(resultObject.getContent()),ResultPage.class, map);
		    if(commentPage!=null&&commentPage.getRows().size()>=1){
		    	ResultPage<PictureDTO> picDtoPage = null;
		    	PictureDTO picDto = null;
		    	List<PictureDTO> picDtoList = null;
		    	for(int i=0;i<commentPage.getRows().size();i++){
		    		picDtoPage = new ResultPage<PictureDTO>();
		    		picDtoList = null;
		    		//评论照片
		    		headObject = CommonHeadUtil.geneHeadObject(request, "4000020101-02", GlobalStatic.SYSTEM_CODE_DATA,
				            GlobalStatic.SYSTEM_CODE_BACK);
					jsonObject.put("commentId", commentPage.getRows().get(i).getCommentId());
				    resultObject = itemService.doService(new RequestObject(headObject,jsonObject));
			    	List<Picture> picLst = (List<Picture>)resultObject.getContent(); 
			    	if(picLst.size()>=1){
			    		picDtoList = new ArrayList<PictureDTO>();
				    	for(int j=0;j<picLst.size();j++){
				    		picDto = new PictureDTO();
				    		BeanUtils.copyProperties(picLst.get(j), picDto);
				    		picDtoList.add(picDto);
				    	}
			    	}
				    commentPage.getRows().get(i).setPicPageList(picDtoList);
		    	}
		    	
		    	//查询好评度 positive (comment) 中评 moderate/neutral (comment) 差评 negative (comment)
			    headObject = CommonHeadUtil.geneHeadObject(request, "020105-09", GlobalStatic.SYSTEM_CODE_DATA,
			            GlobalStatic.SYSTEM_CODE_BACK);
			    resultObject = orderService.doService(new RequestObject(headObject,goodsId));
			    
		    	commentPage.getRows().get(0).setPositiveRate((Double)(resultObject.getContent()!=null?JSONObject.fromObject(resultObject.getContent()).get("positiveRate"):(double)0));
		    	commentPage.getRows().get(0).setNeutralRate((Double)(resultObject.getContent()!=null?JSONObject.fromObject(resultObject.getContent()).get("neutralRate"):(double)0));
		    	commentPage.getRows().get(0).setNegativeRate((Double)(resultObject.getContent()!=null?JSONObject.fromObject(resultObject.getContent()).get("negativeRate"):(double)0));
			    
			    //查询标签
			    headObject = CommonHeadUtil.geneHeadObject(request, "030501-01", GlobalStatic.SYSTEM_CODE_DATA,
			            GlobalStatic.SYSTEM_CODE_BACK);
			    jsonObject.put("pageIndex", 1);
				jsonObject.put("pageSize", 10);
				jsonObject.put("goodsId", goodsId);
			    resultObject = memberService.doService(new RequestObject(headObject,jsonObject));
			    map.put("rows", TagsDTO.class);
			    ResultPage<TagsDTO> tagsPage = (ResultPage<TagsDTO>) JSONObject.toBean(JSONObject.fromObject(resultObject.getContent()),ResultPage.class,map);
			    if(tagsPage!=null){
			    	commentPage.getRows().get(0).setTagsList(tagsPage.getRows());
			    }
		    }
		    
		    
		    resultObject.setHead(new HeadObject(ErrorCode.SUCCESS));
		    resultObject.setContent(commentPage);
		}
		log.info("end[GoodsController.findGoodsComment]");
		return resultObject;
	}
    
    
    /**
     * @Title:  findGoodsDiscuss  
     * @Description:  (获取商品咨询)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-13 下午3:34:59  
     * @version 1.0.0 
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @ResponseBody
    @RequestMapping("/findGoodsDiscuss")
    public Object findGoodsDiscuss(HttpServletRequest request,HttpServletResponse response,MemberCommentQryDTO memberCommentQryDTO) throws Exception{
		log.info("start[GoodsController.findGoodsDiscuss]");
		ResultObject resultObject = null;
		HeadObject headObject = null;
		if(null!=memberCommentQryDTO.getGoodsId()){
			headObject = CommonHeadUtil.geneHeadObject(request, "030101-09", GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
		    resultObject = memberService.doService(new RequestObject(headObject,memberCommentQryDTO));
		}
		log.info("end[GoodsController.findGoodsDiscuss]");
		return resultObject;
	}
    
    /**
     * @Title:  findTopSales  
     * @Description:  (查询热销排行榜)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-16 下午2:26:44  
     * @version 1.0.0 
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    private void findTopSales(HttpServletRequest request,Integer companyId){
    	try{
			HeadObject headObject = null;
			headObject = CommonHeadUtil.geneHeadObject(request, "010201-18");
			Map<String, Object> param=new HashMap<String, Object>();
			param.put("companyId", companyId);
			ResultObject resultObject = itemService.doService(new RequestObject(headObject,param));
		    request.setAttribute("topSales", resultObject.getContent());
    	}catch(Exception e){
    		e.printStackTrace();
    	}
	}
    
    /**
     * @Title:  consult  
     * @Description:  (查看全部咨询消息)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-18 上午10:02:17  
     * @version 1.0.0 
     * @param @param request
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/consult")
    public Object consult(HttpServletRequest request,MemberCommentQryDTO memberCommentQryDTO) throws Exception 
    {
    	ModelAndView mv = new ModelAndView();
    	String goodsIdString = request.getParameter("goodsId");
		if(goodsIdString!=null&&!"".equals(goodsIdString.trim())){
			Integer goodsId = Integer.parseInt(goodsIdString);
			mv.addObject("goodsId", goodsId);
		}
		String productIdString = request.getParameter("productId");
		if(productIdString!=null&&!"".equals(productIdString.trim())){
			Integer productId = Integer.parseInt(productIdString);
			mv.addObject("productId", productId);
		}
		mv.setViewName("pages/biz/goods/consult");
		return mv;
	}
    
    /**
     * @Title:  findProductInfo  
     * @Description:  (获取货品数据)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-18 下午4:57:30  
     * @version 1.0.0 
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/findProductInfo")
    @ResponseBody
    public Object findProductInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		log.info("start[GoodsController.findProductInfo]");
    	ResultObject resultObject = null;
    	HeadObject headObject = null;
    	JSONObject jsonObject = new JSONObject();
    	//根据productId查询Product对象
    	String productIdString = request.getParameter("productId");
		Integer productId = null;
		ProductWithBLOBs product = null;
		if(productIdString!=null&&!"".equals(productIdString.trim())){
			productId = Integer.parseInt(productIdString);
			headObject = CommonHeadUtil.geneHeadObject(request,"2000020121-05");
			resultObject = itemService.doService(new RequestObject(headObject, productId));
			product = (ProductWithBLOBs)resultObject.getContent();
		}
		Integer goodsId = null;
		if(productId==null){
			//当productId为空时，根据goodsId查询product对象
			String goodsIdString = request.getParameter("goodsId");	
			goodsId = Integer.parseInt(goodsIdString);
			List<Integer> goodsIdList = new ArrayList<Integer>();
			goodsIdList.add(goodsId);
			headObject = CommonHeadUtil.geneHeadObject(request,"2000020121-08");
			jsonObject.clear();
			jsonObject.put("goodsIdList", goodsIdList);
			resultObject = itemService.doService(new RequestObject(headObject, jsonObject));
			List<ProductWithBLOBs> productList = (List<ProductWithBLOBs>)resultObject.getContent();
			product = productList.get(0);
		}
		if(product!=null&&product.getProductId()>=1){
			//查询货品图片
			ProductImgDTO productDto = new ProductImgDTO();
			BeanUtils.copyProperties(product, productDto);
			headObject = CommonHeadUtil.geneHeadObject(request,"4000020101-03");
			resultObject = itemService.doService(new RequestObject(headObject, product.getProductId()));
			List<Picture> picList = null;
			if(resultObject.getContent()!=null){
				picList = com.alibaba.fastjson.JSONObject.parseArray(resultObject.getContent().toString(), Picture.class);
			}
			if(picList!=null&&picList.size()>=1){
				productDto.setPicList((List<Picture>) resultObject.getContent());
			}else{
				GoodsWithBLOBs goods = this.selectGoodsById(request, headObject, resultObject, goodsId);
				picList = new ArrayList<Picture>();
    			Picture pic = new Picture();
    			pic.setPicturePath(goods.getBigPic()!=null&&!"".equals(goods.getBigPic())?goods.getBigPic():goods.getMidPic());
    			picList.add(pic);
    			productDto.setPicList(picList);
			}
			//查询货品评论数及评分
			headObject = CommonHeadUtil.geneHeadObject(request,"020105-15");
			resultObject = orderService.doService(new RequestObject(headObject, product.getProductId().longValue()));
			jsonObject = JSONObject.fromObject(resultObject.getContent());
			productDto.setCommentCount(jsonObject.getLong("commentCount"));
			productDto.setSumPoint(jsonObject.getLong("sumPoint"));
			resultObject.setContent(productDto);
		}else{
			//货品不存在
			return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,msg:\"该货品不存在\"}"));
		}
		log.info("end[GoodsController.findProductInfo]");
		return resultObject;
	}
    
    private PamAccount findPamAccount(HttpServletRequest request) throws Exception{
    	Integer accountId = (Integer) CommonUtil.getSession(request).getAttribute("accountId");
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020109-02", 
				GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = memberService.doService(new RequestObject(headObject, accountId));
		PamAccount pamAccount = null;
		if(resultObject!=null&&resultObject.getContent()!=null){
			pamAccount = (PamAccount) resultObject.getContent();
		}
		return pamAccount;
    }
    
    /**
     * @Title:  saveConsultation  
     * @Description:  (保存咨询)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-18 下午2:21:17  
     * @version 1.0.0 
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/saveConsultation")
    @ResponseBody
    public Object saveConsultation(HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		log.info("start[GoodsController.saveConsultation]");
		ResultObject resultObject = null;
		Integer accountId = (Integer) request.getSession().getAttribute("accountId");
		String loginStatus = (String) request.getSession().getAttribute("loginStatus");
        if(accountId!=null&&loginStatus!=null&&loginStatus.equals(GlobalStatic.ACCOUNT_STATUS_ON)){
        	HeadObject headObject = null;
    		JSONObject jsonObject = new JSONObject();
    		
    		//获取当前登陆对象
        	headObject = CommonHeadUtil.geneHeadObject(request,"1000020102-17", 
					GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
			jsonObject.put("accountId", accountId);
			resultObject = memberService.doService(new RequestObject(headObject, jsonObject));
			Member member = (Member) JSONObject.toBean(JSONObject.fromObject(resultObject.getContent()),Member.class);
			PamAccount pamAccount = findPamAccount(request);
//			PamAccount pamAccount = findAccountId(request);
			if(pamAccount!=null&&!"100".equals(pamAccount.getAccountType())){
				return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,isBuyer:false}"));
			}
			if(member!=null){
	    		String consultationContent = request.getParameter("consultationContent");//咨询内容
	    		//查询product对象
	    		String productIdString = request.getParameter("productId");
	    		Integer productId = null;
	    		ProductWithBLOBs product = null;
	    		if(productIdString!=null&&!"".equals(productIdString.trim())){
	    			productId = Integer.parseInt(productIdString);
//	    			headObject = CommonHeadUtil.geneHeadObject(request,"2000020121-05", 
//	    					GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
	    			headObject = CommonHeadUtil.geneHeadObject(request,"2000020121-11", 
	    					GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
	    			resultObject = itemService.doService(new RequestObject(headObject, productId));
	    			product = (ProductWithBLOBs)resultObject.getContent();
	    		}
	    		String goodsIdString = request.getParameter("goodsId");	
  	    		Integer goodsId = Integer.parseInt(goodsIdString);
	    		if(product==null){
	    			List<Integer> goodsIdList = new ArrayList<Integer>();
	    			goodsIdList.add(goodsId);
	    			headObject = CommonHeadUtil.geneHeadObject(request,"2000020121-08", 
	    					GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
	    			jsonObject.clear();
	    			jsonObject.put("goodsIdList", goodsIdList);
	    			resultObject = itemService.doService(new RequestObject(headObject, jsonObject));
	    			List<ProductWithBLOBs> productList = (List<ProductWithBLOBs>)resultObject.getContent();
	    			product = productList.get(0);
	    		}
	    		if(product != null &&product.getProductId() != 0){
	    			//组装MemberComment对象
//	    			headObject = CommonHeadUtil.geneHeadObject(request, "030101-02", GlobalStatic.SYSTEM_CODE_DATA,
//	    		            GlobalStatic.SYSTEM_CODE_BACK);
	    			MemberCommentWithBLOBs comment = new MemberCommentWithBLOBs();
	    			comment.setMemberId(member.getMemberId());
	    			comment.setForCommentId(0);
	    			comment.setTypeId(0l);
	    			comment.setProductId(product.getProductId());
	    			comment.setObjectType("ask");
	    			comment.setAuthorId(member.getMemberId());
	    			comment.setAuthor(pamAccount.getLoginName());
	    			comment.setMemReadStatus(0);
	    			comment.setAdmReadStatus(0);
//	    			comment.setTime(new Date(System.currentTimeMillis()));
	    			comment.setToId(0);
	    			comment.setComment(consultationContent);
	    			comment.setIp(request.getRemoteAddr());
	    			comment.setDisplay(1);
	    			comment.setpIndex((byte)0);
	    			comment.setDisabled(0);
	    			comment.setCommentsType("0");
	    			String specDesc = product.getSpecDesc();
	    			Integer storeId = null;
	    			if(specDesc!=null&&!"".equals(specDesc.trim())){
	    				String[] specArray = specDesc.split(",");
	    				if(specArray!=null&&specArray.length>=1){
	    					for(int i=0;i<specArray.length;i++){
			    				if(specArray[i].split(":")[0].split("\\|")[1].equals("分店")){
			    					storeId = Integer.parseInt(specArray[i].split(":")[1].split("\\|")[0]);
			    					break;
			    				}
			    			}
	    				}
	    			}
	    			if(storeId==null||storeId==0){
	    				GoodsWithBLOBs goods = this.selectGoodsById(request, headObject, resultObject, goodsId);
	    				storeId = goods.getStoreId();
	    			}
	    			comment.setStoreId(storeId);
	    			comment.setCompanyId(product.getCompanyId().longValue());
	    			comment.setPraise(0);
	    			headObject = CommonHeadUtil.geneHeadObject(request, "030101-20", GlobalStatic.SYSTEM_CODE_DATA,
	    		            GlobalStatic.SYSTEM_CODE_BACK);
	    			resultObject = memberService.doService(new RequestObject(headObject,comment));
	    		}else{
	    			return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,msg:\"该货品不存在\"}"));
	    		}
			}else{
				return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,msg:\"请先登录\"}"));
			}
        }else{
        	return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,msg:\"请先登录\"}"));
        }
		log.info("end[GoodsController.saveConsultation]");
		return resultObject;
	}
    
    /**
     * @Title:  addPraise  
     * @Description:  (评论点赞)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-19 上午9:40:34  
     * @version 1.0.0 
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/addPraise")
    @ResponseBody
    public Object addPraise(HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		log.info("start[GoodsController.addPraise]");
		ResultObject resultObject = null;
		Integer accountId = (Integer) request.getSession().getAttribute("accountId");
		String loginStatus = (String) request.getSession().getAttribute("loginStatus");
        if(accountId!=null&&loginStatus!=null&&loginStatus.equals(GlobalStatic.ACCOUNT_STATUS_ON)){
        	HeadObject headObject = null;
    		JSONObject jsonObject = new JSONObject();
    		//获取当前登陆对象
        	headObject = CommonHeadUtil.geneHeadObject(request,"1000020102-17", 
					GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
			jsonObject.put("accountId", accountId);
			resultObject = memberService.doService(new RequestObject(headObject, jsonObject));
			Member member = (Member) JSONObject.toBean(JSONObject.fromObject(resultObject.getContent()),Member.class);
			PamAccount pamAccount = findPamAccount(request);
//			PamAccount pamAccount = findAccountId(request);
			if(pamAccount!=null&&!"100".equals(pamAccount.getAccountType())){
				return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,isBuyer:false}"));
			}
			if(member!=null){
	    		String commentIdString = request.getParameter("commentId");
	    		if(commentIdString!=null&&!"".equals(commentIdString)){
	    			Integer commentId = Integer.parseInt(commentIdString);
	    			headObject = CommonHeadUtil.geneHeadObject(request,"020105-10", 
	    					GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
	    			resultObject = orderService.doService(new RequestObject(headObject, commentId));
	    			OrderCommentDetailDTO comment = (OrderCommentDetailDTO) resultObject.getContent();
	    			if(comment!=null){
	    				//查询点赞对象
	    				headObject = CommonHeadUtil.geneHeadObject(request,"020201-01", 
		    					GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
		    			jsonObject.put("memberId", member.getMemberId());
		    			jsonObject.put("commentId", comment.getCommentId());
		    			resultObject = orderService.doService(new RequestObject(headObject, jsonObject));
		    			List<CommentPraise> commentPraiseList = null;
	    				if(resultObject!=null&&resultObject.getContent()!=null){
	    					commentPraiseList = (List<CommentPraise>) resultObject.getContent();
	    				}
	    				if(commentPraiseList!=null&&commentPraiseList.size()>=1){
	    					return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,msg:\"一个评价只能点一次哟\"}"));
    					}else{
    						//保存点赞对象
    						CommentPraise commentPraise = new CommentPraise();
    						commentPraise.setCommentId(comment.getCommentId().intValue());
    						commentPraise.setMemberId(member.getMemberId());
    						commentPraise.setAddTime((int)(System.currentTimeMillis()/1000));
    						
    						headObject = CommonHeadUtil.geneHeadObject(request,"020201-02", 
    		    					GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
    		    			resultObject = orderService.doService(new RequestObject(headObject, commentPraise));
    		    			
    		    			if(resultObject.getRetCode().equals(ErrorCode.SUCCESS)){
    		    				//查询该评论点赞次数
    		    				headObject = CommonHeadUtil.geneHeadObject(request,"020201-03", 
        		    					GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
        		    			resultObject = orderService.doService(new RequestObject(headObject, comment.getCommentId().intValue()));
    		    			}else{
    		    				return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,msg:\"系统繁忙，请稍后再试\"}"));
    		    			}
    					}
	    			}else{
	    				return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,msg:\"该评论不存在\"}"));
	    			}
	    		}else{
	    			return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,msg:\"该评论不存在\"}"));
	    		}
	    		
			}else{
				return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,msg:\"请先登录\"}"));
			}
        }else{
        	return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,msg:\"请先登录\"}"));
        }
		log.info("end[GoodsController.addPraise]");
		return resultObject;
	}
    
    /**
     * @Title:  addReply  
     * @Description:  (提交回复)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-19 下午8:36:58  
     * @version 1.0.0 
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/addReply")
    @ResponseBody
    public Object addReply(HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		log.info("start[GoodsController.addReply]");
		ResultObject resultObject = null;
		Integer accountId = (Integer) request.getSession().getAttribute("accountId");
		String loginStatus = (String) request.getSession().getAttribute("loginStatus");
        if(accountId!=null&&loginStatus!=null&&loginStatus.equals(GlobalStatic.ACCOUNT_STATUS_ON)){
        	HeadObject headObject = null;
    		JSONObject jsonObject = new JSONObject();
    		//获取当前登陆对象
        	headObject = CommonHeadUtil.geneHeadObject(request,"1000020102-17", 
					GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
			jsonObject.put("accountId", accountId);
			resultObject = memberService.doService(new RequestObject(headObject, jsonObject));
			Member member = (Member) JSONObject.toBean(JSONObject.fromObject(resultObject.getContent()),Member.class);
			PamAccount pamAccount = findPamAccount(request);
//			PamAccount pamAccount = findAccountId(request);
			if(pamAccount!=null&&!"100".equals(pamAccount.getAccountType())){
				return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,isBuyer:false}"));
			}
			if(member!=null){
				String commentText = request.getParameter("commentText");
				if(commentText!=null&&!"".equals(commentText.trim())){
					String commentIdString = request.getParameter("commentId");
		    		if(commentIdString!=null&&!"".equals(commentIdString)){
		    			Integer commentId = Integer.parseInt(commentIdString);
		    			headObject = CommonHeadUtil.geneHeadObject(request,"020105-10", 
		    					GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
		    			resultObject = orderService.doService(new RequestObject(headObject, commentId));
		    			OrderCommentDetailDTO comment = (OrderCommentDetailDTO) resultObject.getContent();
		    			if(comment==null){
		    				headObject = CommonHeadUtil.geneHeadObject(request,"020105-11", 
			    					GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
			    			resultObject = orderService.doService(new RequestObject(headObject, commentId));
			    			comment = (OrderCommentDetailDTO) resultObject.getContent();
		    			}
		    			if(comment!=null){
		    				if(comment.getMemberId().equals(member.getMemberId().longValue())){
		    					return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,msg:\"不能回复自己\"}"));
		    				}
		    				
		    				//保存回复对象
		    				OrderComment newComment = new OrderComment();
		    				newComment.setMemberId(member.getMemberId().longValue());
		    				if(comment.getForCommentId()==0){
		    					newComment.setForCommentId(comment.getCommentId().longValue());
		    				}else{
		    					newComment.setForCommentId(comment.getForCommentId().longValue());
		    				}
		    				newComment.setProductId(comment.getProductId().longValue());
		    				newComment.setOrderId(comment.getOrderId());
		    				newComment.setOrderItemId(comment.getOrderItemId());
		    				newComment.setComment(commentText);
		    				newComment.setCommentsType("2");
		    				newComment.setDisplay("1");
		    				newComment.setToId(comment.getMemberId());
		    				newComment.setToName(comment.getLoginName());
//		    				newComment.setCreateTime(new Date(System.currentTimeMillis()));
		    				headObject = CommonHeadUtil.geneHeadObject(request,"020105-13", 
		    						GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
		    				resultObject = orderService.doService(new RequestObject(headObject, newComment));
		    				if(resultObject.getHead().getRetCode().equals(ErrorCode.SUCCESS)){
		    					headObject = CommonHeadUtil.geneHeadObject(request,"020105-14", 
			    						GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
			    				resultObject = orderService.doService(new RequestObject(headObject, newComment));
		    				}else{
		    					return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,msg:\"系统繁忙，请稍后再试\"}"));
		    				}
		    			}else{
		    				return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,msg:\"该评论不存在\"}"));
		    			}
		    		}else{
		    			return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,msg:\"该评论不存在\"}"));
		    		}
				}else{
					return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,msg:\"请输入回复内容\"}"));
				}
			}else{
				return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,msg:\"请先登录\"}"));
			}
        }else{
        	return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,msg:\"请先登录\"}"));
        }
		log.info("end[GoodsController.addReply]");
		return resultObject;
	}
    
    /**
     * @Title:  reply  
     * @Description:  (查看评论回复列表)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-20 下午1:40:21  
     * @version 1.0.0 
     * @param @param request
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/reply")
    public Object reply(HttpServletRequest request) throws Exception 
    {
    	ModelAndView mv = new ModelAndView();
    	String commentIdString = request.getParameter("commentId");
		if(commentIdString!=null&&!"".equals(commentIdString.trim())){
			Integer commentId = Integer.parseInt(commentIdString);
			mv.addObject("commentId", commentId);
		}
		mv.setViewName("pages/biz/goods/reply");
		return mv;
	}
    
    /**
     * @Title:  findReplyList  
     * @Description:  (查询评论回复列表)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-20 下午2:24:58  
     * @version 1.0.0 
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/findReplyList")
    @ResponseBody
    public Object findReplyList(HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		log.info("start[GoodsController.findReplyList]");
		ResultObject resultObject = null;
		HeadObject headObject = null;
		JSONObject jsonObject = new JSONObject();
		String commentIdString = request.getParameter("commentId");
		if(commentIdString!=null&&!"".equals(commentIdString)){
			Integer commentId = Integer.parseInt(commentIdString);
			headObject = CommonHeadUtil.geneHeadObject(request,"020105-10");
			resultObject = orderService.doService(new RequestObject(headObject, commentId));
			OrderCommentDetailDTO comment = (OrderCommentDetailDTO) resultObject.getContent();
			if(comment!=null){
				String pageIndexString = request.getParameter("pageIndex");
				Integer pageIndex = 1;
				if(pageIndexString!=null&&!"".equals(pageIndexString)){
					pageIndex = Integer.parseInt(pageIndexString);
				}
				if(pageIndex < 1){
					pageIndex = 1;
				}
				headObject = CommonHeadUtil.geneHeadObject(request,"020105-12");
				jsonObject.put("pageIndex", pageIndex);
				jsonObject.put("pageSize", pageSize);
				jsonObject.put("commentId", commentId);
				resultObject = orderService.doService(new RequestObject(headObject, jsonObject));
			}else{
				return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,msg:\"该评论不存在\"}"));
			}
		}else{
			return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,msg:\"该评论不存在\"}"));
		}
		log.info("end[GoodsController.findReplyList]");
		return resultObject;
	}
    
    /**
     * @Title:  findCommentInfo  
     * @Description:  (查询评论对象)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-20 下午5:24:16  
     * @version 1.0.0 
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/findCommentInfo")
    @ResponseBody
    public Object findCommentInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		log.info("start[GoodsController.findCommentInfo]");
		ResultObject resultObject = null;
		HeadObject headObject = null;
		JSONObject jsonObject = new JSONObject();
		String commentIdString = request.getParameter("commentId");
		if(commentIdString!=null&&!"".equals(commentIdString)){
			Integer commentId = Integer.parseInt(commentIdString);
			headObject = CommonHeadUtil.geneHeadObject(request,"020105-10", 
					GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
			resultObject = orderService.doService(new RequestObject(headObject, commentId));
			OrderCommentDetailDTO comment = (OrderCommentDetailDTO) resultObject.getContent();
			if(comment!=null){
	    		//查询评论图片
	    		headObject = CommonHeadUtil.geneHeadObject(request, "4000020101-02", GlobalStatic.SYSTEM_CODE_DATA,
			            GlobalStatic.SYSTEM_CODE_BACK);
				jsonObject.put("commentId", comment.getCommentId());
			    resultObject = itemService.doService(new RequestObject(headObject,jsonObject));
		    	List<Picture> picList = (List<Picture>)resultObject.getContent(); 
		    	List<PictureDTO> picDtoList = null;
		    	if(picList.size()>=1){
		    		picDtoList = new ArrayList<PictureDTO>();
		    		PictureDTO picDto = null;
			    	for(int j=0;j<picList.size();j++){
			    		picDto = new PictureDTO();
			    		BeanUtils.copyProperties(picList.get(j), picDto);
			    		picDtoList.add(picDto);
			    	}
		    	}
			    comment.setPicPageList(picDtoList);
				resultObject.setContent(comment);
			}else{
				return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,msg:\"该评论不存在\"}"));
			}
		}else{
			return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,msg:\"该评论不存在\"}"));
		}
		log.info("end[GoodsController.findCommentInfo]");
		return resultObject;
	}
    
    /**
     * @Title:  findCarParam  
     * @Description:  (查询车型参数配置)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-22 下午3:38:31  
     * @version 1.0.0 
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @ResponseBody
    @RequestMapping("/findCarParam")
    public Object findCarParam(HttpServletRequest request,@RequestParam(required=true,value="carId")Integer carId) throws Exception 
	{
		log.info("start[GoodsController.findCarParam]");//010804-01
		ResultObject resultObject = null;
		HeadObject headObject = null;
		com.alibaba.fastjson.JSONObject jsonObject2 = new com.alibaba.fastjson.JSONObject();
		//查询车型信息
	    headObject = CommonHeadUtil.geneHeadObject(request, "010804-04");
	    resultObject = itemService.doService(new RequestObject(headObject,new Integer[]{carId}));
	    jsonObject2 = (com.alibaba.fastjson.JSONObject)resultObject.getContent();
	    if(jsonObject2!=null&&jsonObject2.size()>=1){
	    	headObject = CommonHeadUtil.geneHeadObject(request, "010804-03");
		    resultObject = itemService.doService(new RequestObject(headObject,carId));
		    List<CarConfiginfoDTO> carConfigList = (List<CarConfiginfoDTO>) resultObject.getContent();
		    jsonObject2.put("carConfig", carConfigList);
	    }
	    resultObject.setContent(jsonObject2);
		log.info("end[GoodsController.findCarData]");
		return resultObject;
	}
    
    
    @ResponseBody
    @RequestMapping("/getProductByGoodsId")
	public Object getProductByGoodsId(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Integer> goodsIdList = new ArrayList<Integer>();
		JSONObject jsonObject = new JSONObject();
		goodsIdList.add(Integer.parseInt(request.getParameter("goodsId")));
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020121-08", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		jsonObject.put("goodsIdList", goodsIdList);
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, jsonObject));
		List<ProductWithBLOBs> productList = (List<ProductWithBLOBs>) resultObject.getContent();
		if(null!=productList && productList.size()>0){
			return  productList.get(0).getProductId();
		}
		return null;
	}
    
    /**
     * @Title:  searchByBrandId  
     * @Description:  TODO(根据品牌搜索推荐商品)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-7-10 上午10:49:15  
     * @version 1.0.0 
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
//    @RequestMapping("/searchByBrandId")
//    @ResponseBody
//	public Object searchByBrandId(HttpServletRequest request, HttpServletResponse response) throws Exception {
//    	log.info("start[GoodsController.searchByBrandId]");
//		ResultObject resultObject = null;
//		HeadObject headObject = null;
//		String brandIdString = request.getParameter("brandId");
//		if(brandIdString!=null&&!"".equals(brandIdString)){
//			Integer brandId = Integer.parseInt(brandIdString);
////			headObject = CommonHeadUtil.geneHeadObject(request,"050101-07", 
////					GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
////			resultObject = searchClientService.doService(new RequestObject(headObject, brandId));
//		}
//		log.info("end[GoodsController.searchByBrandId]");
//		return resultObject;
//	}
   
}
