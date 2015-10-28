package com.cnit.yoyo.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.base.controller.BaseController;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.car.CarMaintain;
import com.cnit.yoyo.model.car.dto.CarMaintainQryDTO;
import com.cnit.yoyo.model.car.dto.MaintainAccessoryItemsDTO;
import com.cnit.yoyo.model.car.dto.MaintainProductDTO;
import com.cnit.yoyo.model.cart.Cart;
import com.cnit.yoyo.model.goods.GoodsTimePrice;
import com.cnit.yoyo.model.member.Member;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.APICommonUtil;
import com.cnit.yoyo.util.ApplicationContextUtil;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.Configuration;
import com.cnit.yoyo.util.DateUtils;

@Controller("carMaintainController")
@RequestMapping("/carMaintain")
public class CarMaintainController extends BaseController {

    @Autowired
    public RemoteService memberService;
    @Autowired
    public RemoteService itemService;
    @Autowired
    public RemoteService orderService;
    
    /**
     * 查询查找用户保养项目列表
     */
    @RequestMapping("/maintainList")
    @ResponseBody
	public Object maintainList(String data,HttpServletRequest request){
    	log.info("###########carMaintainController-->start");
    	log.info("----------------------data:"+data+"-------------------------");
    	ResultObject resultObject = null;
    	try{
			//获取请求参数
			JSONObject jsonData =  JSONObject.fromObject(data);
			String carId = (String) CommonUtil.getJsonValue(jsonData, "carId"); 
			String useMeter = (String) CommonUtil.getJsonValue(jsonData, "useMeter");
			String useYear = (String) CommonUtil.getJsonValue(jsonData, "useYear");
			String useMonth = (String) CommonUtil.getJsonValue(jsonData, "useMonth");

			//根据车型信息查询和使用信息查询保养项目
			CarMaintainQryDTO qDTO = new CarMaintainQryDTO();
			Calendar calendar=Calendar.getInstance();
			calendar.set(Calendar.YEAR, Integer.valueOf(useYear));
			calendar.set(Calendar.MONTH, Integer.valueOf(useMonth)-1);
			qDTO.setMaintainMonth(DateUtils.monthSpace(calendar.getTime()));
			qDTO.setMaintainKm(Double.valueOf(Integer.valueOf(useMeter)*0.001).longValue());
			qDTO.setCarId(Long.valueOf(carId));
			HeadObject head= CommonHeadUtil.geneHeadObject("carMaintainService.findCarMaintainItems");
		    resultObject = (ResultObject) itemService.doServiceByServer(new RequestObject(head,qDTO));
			Map<String, Object> resultMap = (Map<String, Object>) resultObject.getContent();
			
		    Map<String,Object> result = new HashMap<String,Object>();
//		    result.put("carInfo", memberCarDTO); //车型信息
		    List<MaintainAccessoryItemsDTO> carMaintainAccessoryItems = (List<MaintainAccessoryItemsDTO>) resultMap.get("carMaintainAccessoryItems");
		    if(carMaintainAccessoryItems!=null&&!carMaintainAccessoryItems.isEmpty()){
		    	List<MaintainAccessoryItemsDTO> list = new ArrayList<MaintainAccessoryItemsDTO>();
		    	for(MaintainAccessoryItemsDTO dto:carMaintainAccessoryItems){
		    		list.addAll(dto.getMaintainAccessorys());
		    	}
		    	result.put("items", list);
		    }
		    
		    
		    CarMaintain carMaintain = (CarMaintain) resultMap.get("carMaintain");
		    if(carMaintain!=null){
		    	result.put("maintainId", carMaintain.getMaintainId());
		    }
		    
		    resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS), result);
    		log.info("###########carMaintainController-->end");
    		return resultObject;
    	}catch(Exception e){
    		log.error(e.getMessage(),e);
    		return processExpction(e.getMessage());
    	}
	}
    
	/**
	  * @description <b>查询可做当前保养的店铺</b>
	  * @param @param request
	  * @param @param qryDTO
	  * @param @return
	  * @return Object
	*/
	@ResponseBody
	@RequestMapping("/companyList")
	public Object companyList(String data,HttpServletRequest request){
		HeadObject head= CommonHeadUtil.geneHeadObject("carMaintainService.findMaintainCompany");
		try{
			//获取请求参数
			JSONObject jsonData =  JSONObject.fromObject(data);
			String maintainIdStr = (String) CommonUtil.getJsonValue(jsonData,"maintainId");
			String area = (String) CommonUtil.getJsonValue(jsonData,"area");

			Object[] objs = jsonData.getJSONArray("ids").toArray();
			
			//校验参数
			if(StringUtils.isEmpty(maintainIdStr)){
				return  processExpction("maintainId不能为空！");
			}
			
			if(objs==null||objs.length==0){
				return processExpction("商品Id不能为空!");
			}
			Long[]ids = new Long[objs.length];
			for(int i=0;i<objs.length;i++){
				ids[i]=((Integer) objs[i]).longValue();
			}
			
			CarMaintainQryDTO qryDTO = new CarMaintainQryDTO();
			qryDTO.setMaintainId(Long.valueOf(maintainIdStr));
			qryDTO.setIds(ids);
			qryDTO.setArea(area);
			
			ResultObject resultObject = (ResultObject) itemService.doServiceByServer(new RequestObject(head,qryDTO));
			
			//增加商户详情
			ShopController shopController = (ShopController) ApplicationContextUtil.getBeanByName("shopController");
			JSONArray resultJarray = JSONArray.fromObject(resultObject.getContent());
            if(resultJarray!=null){
            	for(Iterator<JSONObject> it = resultJarray.iterator();it.hasNext();){
            		JSONObject sub = it.next();
            	    Integer companyId = (Integer) CommonUtil.getJsonValue(sub, "companyId");
            	    JSONObject params = new JSONObject();
        			params.put("companyId", companyId.toString());
        			ResultObject result=(ResultObject) shopController.shopDetail(params.toString(), request);
        			sub.put("companyDetail", result.getContent());
            	}
            }
            resultObject.setContent(resultJarray);
			return resultObject;
		}catch (Exception e) {
			log.error(e.getMessage(),e);
			return  processExpction(e.getMessage());
		}
	}
	/**
	 * <b>查询保养项默认商品</b>
	 * @param data
	 * @param request
	 * @return
	 */
	@RequestMapping("/maintainGoodsList")
	@ResponseBody
	public Object maintainGoodsList(String data,HttpServletRequest request){
		try{
			//获取请求参数
			JSONObject jsonData =  JSONObject.fromObject(data);
			String maintainId = (String) CommonUtil.getJsonValue(jsonData, "maintainId");
			String companyId = (String) CommonUtil.getJsonValue(jsonData, "companyId");
			String timeStr= (String)CommonUtil.getJsonValue(jsonData, "conTime");  //消费时间
			Object[] idsObjs = jsonData.getJSONArray("ids").toArray();
			Object[] caObjs = jsonData.getJSONArray("categoryIds").toArray();
			
			//检查参数
			if(StringUtils.isEmpty(maintainId)){
				return processExpction("maintainId不能为空");
			}
			if(StringUtils.isEmpty(companyId)){
				return processExpction("companyId不能为空！");
			}
			if(StringUtils.isEmpty(timeStr)){
				return processExpction("conTime不能为空！");
			}
			
            if(idsObjs==null||idsObjs.length==0){
            	return processExpction("ids不能为空！");
            }
			if(caObjs==null||caObjs.length==0){
				return processExpction("categoryIds不能为空！");
			}
			
			
			//组装参数
			CarMaintainQryDTO qryDTO = new CarMaintainQryDTO();
			qryDTO.setMaintainId(Long.valueOf(maintainId));
			qryDTO.setCompanyId(Long.valueOf(companyId));
			Long[] ids = new Long[idsObjs.length];
			for(int i=0;i<ids.length;i++){
				ids[i] = ((Integer) idsObjs[i]).longValue();
			}
			qryDTO.setIds(ids);
			Long[]categoryIds = new Long[caObjs.length];
			for(int i=0;i<caObjs.length;i++){
				categoryIds[i] = ((Integer)caObjs[i]).longValue();
			}
			qryDTO.setAccessoryCategoryIds(categoryIds);
			qryDTO.setAppDate(timeStr);
			
			HeadObject head= CommonHeadUtil.geneHeadObject("carMaintainService.findDefautlGoods");
			ResultObject resultObject = (ResultObject) itemService.doServiceByServer(new RequestObject(head,qryDTO));
			
			List<MaintainProductDTO> maintainProductDTOs=(List<MaintainProductDTO>) resultObject.getContent();
			for (MaintainProductDTO maintainProductDTO : maintainProductDTOs) {
				//图片url转换
				String imgUrl = Configuration.getInstance().getConfigValue("images.url");
				maintainProductDTO.setPicturePath(imgUrl+maintainProductDTO.getPicturePath());
				head = CommonHeadUtil.geneHeadObject("goodsTimePriceService.findGoodsTimePrice");
				Map<String, Object> param=new HashMap<String,Object>();
    			param.put("priceDate", DateUtils.getDate(qryDTO.getAppDate(), "yyyy-MM-dd"));
    			param.put("goodsId", maintainProductDTO.getGoodsId());
    			ResultObject resultObject1 = (ResultObject) itemService.doServiceByServer(new RequestObject(head, param));
    			if(null!=resultObject1.getContent()){
    				GoodsTimePrice goodsTimePrice=(GoodsTimePrice)resultObject1.getContent();
    				maintainProductDTO.setPrice(goodsTimePrice.getPrice());
    			}
			}
			
			return resultObject;
		}catch (Exception e) {
			log.error(e.getMessage(),e);
			return  processExpction(e.getMessage());
		}
	}
    /**
     * @description <b>加入购物车(保养育商品用)</b>
     * @return
     * String
    */
    @ResponseBody
    @RequestMapping("/maintainCart")
    public Object maintainCart(String data,HttpServletRequest request){
    	HeadObject headObject = CommonHeadUtil.geneHeadObject("cartService.addToCart");
    	try{
			//获取请求参数
			JSONObject jsonData =  JSONObject.fromObject(data);
			MemberListDo memberListDo = APICommonUtil.getMemberListDo((String)request.getAttribute("sessionid"));
    		
	    	List<Cart> memberCarts=new ArrayList<Cart>();
	    	JSONArray carArray=JSONArray.fromObject(jsonData.get("carts"));
	    	for (Object object : carArray) {
				JSONObject cartJSON=(JSONObject) object;
				Cart cart=new Cart();
				cart.setMemberId(Integer.parseInt(memberListDo.getMemberId()));
				cart.setTime(Long.valueOf(System.currentTimeMillis()/1000).intValue());
				cart.setGoodsId(Integer.parseInt(cartJSON.getString("goodsId")));
				cart.setProductId(Integer.parseInt(cartJSON.getString("productId")));
				cart.setQuantity(Integer.parseInt(cartJSON.getString("quantity")));
				cart.setCompanyId(Integer.parseInt(cartJSON.getString("companyId")));
				cart.setStoreId(Integer.parseInt(cartJSON.getString("storeId")));
				cart.setAppointment((String)CommonUtil.getJsonValue(cartJSON, "appointment"));
				memberCarts.add(cart);
			}
	    	headObject = (HeadObject) orderService.doServiceByServer(new RequestObject(headObject, memberCarts));
			return new ResultObject(headObject,null);
    	}catch(Exception e){
    		e.printStackTrace();
    		return CommonUtil.processExpction(headObject);
    	}
    }
	
    /**
     * 	保养服务接口
     * @param data
     * @param request
     * @return
     */
    @RequestMapping("/maintainServer")
    @ResponseBody
	public Object maintainServer(String data,HttpServletRequest request){
    	log.info("###########carMaintainController（maintainServer）-->start");
    	log.info("----------------------data:"+data+"-------------------------");
    	try{
			//获取请求参数
			JSONObject jsonData =  JSONObject.fromObject(data);
			
			//测试数据
//			jsonData = new JSONObject();
//			jsonData.put("maintainId", "152877");
//			jsonData.put("proIds", "[134,142]");
			////////////////
			String maintainIdStr = jsonData.getString("maintainId");
			Object[] objs = jsonData.getJSONArray("proIds").toArray();
			
			//校验参数
			if(maintainIdStr==null||"".equals(maintainIdStr)){
				return  processExpction("maintainId不能为空！");
			}
			
			Long[]ids = new Long[objs.length];
			if(ids==null||ids.length==0){
				return processExpction("商品Id不能为空!");
			}
			
			for(int i=0;i<objs.length;i++){
				ids[i]=((Integer) objs[i]).longValue();
			}
			
			CarMaintainQryDTO qryDTO = new CarMaintainQryDTO();
			qryDTO.setMaintainId(Long.valueOf(maintainIdStr));
			qryDTO.setIds(ids);
			
			HeadObject head= CommonHeadUtil.geneHeadObject("carMaintainService.findDefautlGoods");
			ResultObject resultObject = (ResultObject) itemService.doServiceByServer(new RequestObject(head,qryDTO));
			Map<String,Object> map = new HashMap<String,Object>();
			List<MaintainProductDTO> mpDtoList = (List<MaintainProductDTO>) resultObject.getContent();
			//图片url转换
			String imgUrl = Configuration.getInstance().getConfigValue("images.url");
			//按商家号分类
			Map<Long,List<MaintainProductDTO>> subMap = new HashMap<Long,List<MaintainProductDTO>>();
			if(mpDtoList!=null&&!mpDtoList.isEmpty()){
				for(MaintainProductDTO mainDTO:mpDtoList){
					mainDTO.setPicturePath(imgUrl+mainDTO.getPicturePath());
					List<MaintainProductDTO> subList = subMap.get(mainDTO.getCompanyId());
					if(subList==null){
						subList = new ArrayList<MaintainProductDTO>();
						subMap.put(mainDTO.getCompanyId(), subList);
					}
					subList.add(mainDTO);
				}
			}
			
			//查询商户信息与商品信息
			ShopController shopController = (ShopController) ApplicationContextUtil.getBeanByName("shopController");
			GoodsController goodsController = (GoodsController) ApplicationContextUtil.getBeanByName("goodsController");
			List<JSONObject> list = new ArrayList<JSONObject>();
			if(!subMap.isEmpty()){
				for(Iterator<Long> it=subMap.keySet().iterator();it.hasNext();){
					JSONObject infos = new JSONObject();
					list.add(infos);
					//增加商户信息
					Long companyId = it.next();
					JSONObject params = new JSONObject();
					params.put("companyId", companyId.toString());
					ResultObject result=(ResultObject) shopController.shopDetail(params.toString(), request);
					infos.put("companyInfos",result.getContent() );
					
					//增加评价信息
					 List<MaintainProductDTO> dtoList = subMap.get(companyId);
					 if(dtoList!=null&&!dtoList.isEmpty()){
						 for(MaintainProductDTO dto:dtoList){
							 Long goodsId = dto.getGoodsId();
							 params.clear();
							 params.put("goodsId", goodsId.toString());
							 params.put("pageIndex", "1");
							 params.put("pageSize", "1");
							 result = (ResultObject) goodsController.goodsComment(params.toString(), request);
							 if(result!=null){
								  dto.setCommentInfo(result.getContent());
							 }
						 }
					 }
					 infos.put("goodsInfo", dtoList);
					
				}
			}
			map.put("carMaintain", list);
			map.put("qryDTO", qryDTO);
			
    		log.info("###########carMaintainController（maintainServer）-->end");
    		return resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS), map);
    	}catch(Exception e){
    		log.error(e.getMessage(),e);
    		return  processExpction(e.getMessage());
    	}
    }
    
    /**
     * 查询更多配件
     * @param request
     * @param qryDTO
     * @return
     */
	@ResponseBody
	@RequestMapping("/moreGoods")
	public Object moreGoods(String data,HttpServletRequest request){
		HeadObject head= CommonHeadUtil.geneHeadObject("carMaintainService.findOptionalAccessories");
		try{
			
			//获取请求参数
			JSONObject jsonData =  JSONObject.fromObject(data);
			
			//测试数据
//			jsonData = new JSONObject();
//			jsonData.put("categoryId", "54");
//			jsonData.put("companyId", "14");
			////////////////
			
			String companyIdStr = (String)CommonUtil.getJsonValue(jsonData,"companyId");
			String categoryIdStr = (String)CommonUtil.getJsonValue(jsonData,"categoryId");
			//检查参数
			if(companyIdStr==null||"".equals(companyIdStr)){
				return processExpction("companyId	不能为空！");
			}
			if(categoryIdStr==null||"".equals(categoryIdStr)){
				return processExpction("categoryId 不能为空！");
			}
		
			CarMaintainQryDTO qryDTO = new CarMaintainQryDTO();
			qryDTO.setCompanyId(Long.valueOf(companyIdStr));
			qryDTO.setCategoryId(Integer.valueOf(categoryIdStr));
			
			ResultObject resultObject = (ResultObject) itemService.doServiceByServer(new RequestObject(head,qryDTO));
			if(resultObject!=null){
				//图片url转换
				String imgUrl = Configuration.getInstance().getConfigValue("images.url");
				
				Map<String, Object> map = (Map<String, Object>) resultObject.getContent();
				if(map!=null){
					List<MaintainProductDTO> dtoList = (List<MaintainProductDTO>) map.get("product");
					if(dtoList!=null&&!dtoList.isEmpty()){
						JSONObject params = new JSONObject();
						GoodsController goodsController = (GoodsController) ApplicationContextUtil.getBeanByName("goodsController");
						for(MaintainProductDTO mDto:dtoList){
							mDto.setPicturePath(imgUrl+mDto.getPicturePath());
							//增加评价信息
							 Long goodsId = mDto.getGoodsId();
							 params.clear();
							 params.put("goodsId", goodsId.toString());
							 params.put("pageIndex", "1");
							 params.put("pageSize", "1");
							 ResultObject result = (ResultObject) goodsController.goodsComment(params.toString(), request);
							 if(result!=null){
								 mDto.setCommentInfo(result.getContent());
							 }
						}
					}
					
				}
			}
			return resultObject;
		}catch (Exception e) {
			e.printStackTrace();
			return processExpction(e.getMessage());
		}
	}

    private Member selectMember(Integer accountId) throws Exception 
	{
		if (accountId != null && accountId != 0) 
		{
			HeadObject headObject = CommonHeadUtil.geneHeadObject("memberService.selectMemberByAccountId");
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("accountId", accountId);
			ResultObject resultObject = (ResultObject) memberService.doServiceByServer(new RequestObject(headObject, jsonObject));
			return (Member) JSONObject.toBean(JSONObject.fromObject(resultObject.getContent()),Member.class);
		}
		return null;
	}
}
