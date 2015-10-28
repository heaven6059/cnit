package com.cnit.yoyo.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.cnit.yoyo.dto.spec.SpecQryDTO;
import com.cnit.yoyo.model.cart.Cart;
import com.cnit.yoyo.model.cart.dto.CartListDTO;
import com.cnit.yoyo.model.goods.CatSpecShip;
import com.cnit.yoyo.model.goods.GoodsTimePrice;
import com.cnit.yoyo.model.goods.GoodsWithBLOBs;
import com.cnit.yoyo.model.goods.Picture;
import com.cnit.yoyo.model.goods.Store;
import com.cnit.yoyo.model.goods.dto.ProductImgDTO;
import com.cnit.yoyo.model.member.Member;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.model.member.PamAccount;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.APICommonUtil;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.Configuration;
import com.cnit.yoyo.util.RedisClientUtil;
import com.cnit.yoyo.util.StringUtil;

@Controller("cartController")
@RequestMapping("/cart")
public class CartController extends BaseController {
	
    @Autowired
    public RemoteService memberService;
    @Autowired
    public RemoteService orderService;
    @Autowired
    public RemoteService itemService;
    @Autowired
    private RedisClientUtil redisService;
    
    private Cart newCart;
	//购物车最大能容纳的商品种类数量
	private static final int MAX_CART_SIZE = 50;
	private String errorMsg="系统繁忙，请稍后再试！";
	
    @RequestMapping("/addCart")
    @ResponseBody
	public Object addCart(String data,HttpServletRequest request){
		log.info("start[CartController.addCart]");
		try{
			//获取请求参数
			JSONObject jsonData =  JSONObject.fromObject(data);
			MemberListDo memberListDo = APICommonUtil.getMemberListDo((String)request.getAttribute("sessionid"));
			Integer accountId = memberListDo.getAccountId();
			
			//测试数据
//			Integer accountId = 152;
//			jsonData = new JSONObject();
//			jsonData.put("quantity", "10");
//			jsonData.put("productId", "438");
			
			// 获取数据
			String goodsIdString = (String) CommonUtil.getJsonValue(jsonData, "goodsId");
			String quantityString = (String)CommonUtil.getJsonValue(jsonData, "quantity");
			String specValueId = (String) CommonUtil.getJsonValue(jsonData,"specValueId");
			String productIdString = (String) CommonUtil.getJsonValue(jsonData,"productId");
			
			//检查参数
			if(StringUtils.isEmpty(quantityString)){
				return processExpction("数量不能为空");
			}
	        if(StringUtils.isEmpty(productIdString)){
	        	return processExpction("货品id不能为空");
	        }
			PamAccount pamAccount = findAccountId(accountId);
			if(pamAccount!=null&&!"100".equals(pamAccount.getAccountType())){
				return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,isBuyer:false}"));
			}
			if(pamAccount!=null){
				accountId = pamAccount.getAccountId();
			}
			// 判断数据是否合法
			String addCartDataResult = this.addCartdata(goodsIdString, quantityString, specValueId, productIdString, accountId,null);
			ResultObject resultObject = null;
			if ("".equals(addCartDataResult))// 数据合法
			{
				// 判断登陆状态
				Member member = this.selectMember(accountId);
				String addCartResult = null;
				if (member != null)// 用户已登录
				{
					newCart.setMemberId(member.getMemberId());
					addCartResult = this.addCartToDb( newCart, accountId);
				} else {// 用户未登录 
					
				}
				resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),
						addCartResult != null ? JSONObject.fromObject("{result:false,msg:'"
						+ addCartResult + "'}") : JSONObject.fromObject("{result:true}"));
			} else {// 加入购物车的数据不合法
				resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),
						JSONObject.fromObject("{result:false,msg:'" + addCartDataResult + "'}"));
			}
			log.info("end[CartController.addCart]");
			return resultObject;
		}catch(Exception e){
    		log.error(e.getMessage(),e);
    		return processExpction(e.getMessage());
		}
	}
    
    /**
     * 获取购物车列表
     * @param data
     * @param request
     * @return
     */
    @RequestMapping("/cartList")
    @ResponseBody
	public Object cartList(String data,HttpServletRequest request) 
	{
    	log.info("###########cartList-->start");
    	log.info("----------------------data:"+data+"-------------------------");
    	try{
				//获取请求参数
				JSONObject jsonData =  JSONObject.fromObject(data);
				MemberListDo memberListDo = APICommonUtil.getMemberListDo((String)request.getAttribute("sessionid"));
				Integer accountId = memberListDo.getAccountId();
//				Integer accountId = 152;
				HeadObject headObject = CommonHeadUtil.geneHeadObject("accountService.selectByAccountId");
				ResultObject resultObject = (ResultObject) memberService.doServiceByServer(new RequestObject(headObject, accountId));
				PamAccount pamAccount = null;
				if(resultObject!=null&&resultObject.getContent()!=null){
					pamAccount = (PamAccount) resultObject.getContent();
				}
				
				if(pamAccount!=null&&!"100".equals(pamAccount.getAccountType())){
					return new ResultObject(new HeadObject(ErrorCode.FAILURE),JSONObject.fromObject("{result:false,isBuyer:false}"));
				}
				if(pamAccount!=null){
					accountId = pamAccount.getAccountId();
				}
				Member member = null;
				if (accountId != null && accountId !=0) 
				{
					member = this.selectMember(accountId);
				} 
				List<CartListDTO> cartList = null;
				if(member!=null&&member.getMemberId()!=0){
					// 用户已登录
					// 查询数据库中的购物车商品
				   headObject = CommonHeadUtil.geneHeadObject("cartService.findCartListByAccountId");
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("accountId", accountId);
					resultObject = (ResultObject) orderService.doServiceByServer(new RequestObject(headObject, jsonObject));
					cartList = (List<CartListDTO>)resultObject.getContent();
				}
				
				//查询货品图片
				cartList = this.findProImage(cartList);
				
				//查询分店
				cartList = this.findStoresByCartList(cartList);
				
				//按商店号分组
				List<List<CartListDTO>> list = new ArrayList();
				Map<Integer,List<CartListDTO>> map =new HashMap<Integer,List<CartListDTO>>();
				
				//图片地址转成httpURL
				String imgUrl = Configuration.getInstance().getConfigValue("images.url");
				if(cartList!=null&&!cartList.isEmpty()){
					for(CartListDTO cto:cartList){
						cto.setGoodsImage(imgUrl+cto.getGoodsImage());
						
						Integer storeId = cto.getStoreId();
						List<CartListDTO> cList = map.get(storeId);
						if(cList==null){
							cList = new ArrayList<CartListDTO>();
							map.put(storeId, cList);
						}
						cList.add(cto);
					}
					for(List<CartListDTO> cList:map.values()){
						list.add(cList);
					}
					
				}
				
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("cartList", list);
				resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS), jsonObject);
				log.info("###########cartList-->end");
				return resultObject;
    	}catch(Exception e){
    		log.error(e.getMessage(),e);
    		return processExpction(e.getMessage());
    	}
	}
    
    /**
     * @Description: (获取确认订单页面的货品和分店数据) 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/findOrderInfo")
    @ResponseBody
    public Object findOrderInfo(String data,HttpServletRequest request) throws Exception 
	{
		log.info("start[CartController.findOrderInfo]");

		//获取请求参数
		JSONObject jsonData =  JSONObject.fromObject(data);
		MemberListDo memberListDo = APICommonUtil.getMemberListDo((String)request.getAttribute("sessionid"));
		Integer accountId = memberListDo.getAccountId();
		
		/////////测试代码 start //////
//		Integer  accountId=152;
//		jsonData = new JSONObject();
//		jsonData.put("proIdListString", "[399,445,438]");
//		jsonData.put("quantityListString", "[3,2,4]");
       /////////测试代码 end //////
		String proIdListString = (String) CommonUtil.getJsonValue(jsonData, "proIdListString");
		String quantityListString = (String) CommonUtil.getJsonValue(jsonData, "quantityListString");
		
		//检查参数
		if(StringUtils.isEmpty(proIdListString)){
			return processExpction("货品id不能为空！");
		}
		if(StringUtils.isEmpty(quantityListString)){
			return processExpction("数量不能为空！");
		}
		
		PamAccount pamAccount = findAccountId(accountId);
		if(pamAccount!=null&&!"100".equals(pamAccount.getAccountType())){
			return new ResultObject(new HeadObject(ErrorCode.FAILURE),JSONObject.fromObject("{result:false,isBuyer:false}"));
		}
		if(pamAccount!=null){
			accountId = pamAccount.getAccountId();
		}
		ResultObject resultObject = null;
		HeadObject headObject;
		JSONObject jsonObject = new JSONObject();

		if (accountId != null && accountId != 0) 
		{
					List<Integer> proIdList = (List<Integer>) JSONArray.toCollection(JSONArray.fromObject(proIdListString), Integer.class);
					if (proIdList != null && proIdList.size() >= 1) 
					{
						// 查询member对象
						Member member = this.selectMember(accountId);
						// 根据proIdList查询购物车对象
						List<CartListDTO> cartList = null;
						List<Integer> quantityList = (List<Integer>) JSONArray.toCollection(JSONArray.fromObject(quantityListString), Integer.class);
						if(quantityList.size()==proIdList.size()){
							headObject = CommonHeadUtil.geneHeadObject("cartService.findCartListByProIdList");
							resultObject = (ResultObject) orderService.doServiceByServer(new RequestObject(headObject, JSONArray.fromObject(proIdList)));
							cartList = (List<CartListDTO>) JSONArray.toCollection(JSONArray.fromObject(resultObject.getContent()),CartListDTO.class);
							if (cartList != null && cartList.size() > 0) 
							{
								for (int j = cartList.size()-1; j >=0; j--) 
								{
									if("1".equals(cartList.get(j).getLimitGoodsdown()) || "1".equals(cartList.get(j).getLimitStore()) || "1".equals(cartList.get(j).getLimitStoredown())
											|| "1".equals(cartList.get(j).getShopStatus()) || "1".equals(cartList.get(j).getStatus()) || "1".equals(cartList.get(j).getDisabled())){
										return new ResultObject(new HeadObject(ErrorCode.FAILURE), JSONObject.fromObject("{ msg:\"很抱歉，该订单中存在失效货品\"}"));
									}
									for (int i = 0; i < proIdList.size(); i++) 
									{
										if (proIdList.get(i).equals(cartList.get(j).getProductId())) 
										{
											if(quantityList.get(i) > cartList.get(j).getStore()){
												return new ResultObject(new HeadObject(ErrorCode.FAILURE), JSONObject.fromObject("{ msg:\"很抱歉，该订单中存在货品库存不足\"}"));
											}else{
												cartList.get(j).setQuantity(quantityList.get(i));
											}
										}
									}
									
								}
							}
						}
						if(!(cartList!=null&&cartList.size()>=1)){
							headObject = CommonHeadUtil.geneHeadObject("cartService.findCartListByProIdAndMem");
							jsonObject.put("memberId", member.getMemberId());
							jsonObject.put("proIdList", proIdList);
							resultObject = (ResultObject) orderService.doServiceByServer(new RequestObject(headObject, jsonObject));
							cartList = (List<CartListDTO>) JSONArray.toCollection(JSONArray.fromObject(resultObject.getContent()), CartListDTO.class);
						}
						List<Store> storeList = null;
						if(cartList!=null&&cartList.size()>=1){
							//查询店铺
							List<Integer> storeIdList = new ArrayList<Integer>();
				    		for(int i =0 ; i<cartList.size() ; i++){
				    			storeIdList.add(cartList.get(i).getStoreId());
				    		}
							headObject = CommonHeadUtil.geneHeadObject("storeService.selectByStoreIdList3");
							resultObject = (ResultObject) memberService.doServiceByServer(new RequestObject(headObject, storeIdList));
							
							if(resultObject.getContent()!=null){
								storeList = com.alibaba.fastjson.JSONArray.parseArray(resultObject.getContent().toString(), Store.class);
							}
							if(storeList!=null){
								for(int i=0;i<storeList.size();i++){
									if("1".equals(storeList.get(i).getLimitGoodsdown()) || "1".equals(storeList.get(i).getLimitStore()) || "1".equals(storeList.get(i).getLimitStoredown())
											|| "1".equals(storeList.get(i).getShopstatus()) || "1".equals(storeList.get(i).getStatus()) || "1".equals(storeList.get(i).getDisabled())){
										return new ResultObject(new HeadObject(ErrorCode.FAILURE),
												JSONObject.fromObject("{isLogin:true,msg:\"很抱歉，该订单中存在失效货品\"}"));
									}
								}
							}
							
							//查询货品图片
							this.findProImage(cartList);
							for (int j = cartList.size()-1; j >=0; j--) 
							{
								if(cartList.get(j).getQuantity() <= 0 || "0".equals(cartList.get(j).getgMarketable()) || "1".equals(cartList.get(j).getgDisabled()) 
										|| "0".equals(cartList.get(j).getpMarketable()) || "1".equals(cartList.get(j).getpDisabled())){
									return new ResultObject(new HeadObject(ErrorCode.FAILURE),
											JSONObject.fromObject("{isLogin:true,msg:\"很抱歉，该订单中存在失效货品\"}"));
								}
							}
						}
						
						Double sumPrice = this.totalPrice(cartList);
					
						com.alibaba.fastjson.JSONObject returnJson = new com.alibaba.fastjson.JSONObject();
						DecimalFormat df2 = new DecimalFormat("#.00");//四舍五入保留两位小数   
						returnJson.put("sumPrice", df2.format(Double.parseDouble(sumPrice.toString())));
						//按商店号分组
						List<List<CartListDTO>> list = new ArrayList();
						Map<Integer,List<CartListDTO>> map =new HashMap<Integer,List<CartListDTO>>();
						
						//图片地址转成httpURL
						String imgUrl = Configuration.getInstance().getConfigValue("images.url");
						if(cartList!=null&&!cartList.isEmpty()){
							for(CartListDTO cto:cartList){
								cto.setGoodsImage(imgUrl+cto.getGoodsImage());
								
								Integer storeId = cto.getStoreId();
								List<CartListDTO> cList = map.get(storeId);
								if(cList==null){
									cList = new ArrayList<CartListDTO>();
									map.put(storeId, cList);
								}
								cList.add(cto);
							}
							for(List<CartListDTO> cList:map.values()){
								list.add(cList);
							}
							
						}
						returnJson.put("content", list);
						returnJson.put("storeList", storeList);
						returnJson.put("isLogin", true);
						resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS), returnJson);
					} else {
						resultObject = new ResultObject(new HeadObject(ErrorCode.FAILURE),JSONObject.fromObject("{empty:true}"));
					}
		} else {
			resultObject = new ResultObject(new HeadObject(ErrorCode.FAILURE),JSONObject.fromObject("{}"));
		}

		log.info("end[CartController.findOrderInfo]");
		return resultObject;
	}
    
    /**
  	 * @Title: orderAdd 
  	 * @Description: (提交订单) 
  	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
  	 * @date 2015-4-18
  	 * @version 1.0.0 
  	 * @param request
  	 * @param response
  	 * @param @return
  	 * @param @throws Exception    
  	 * @return Object    返回类型 
  	 * @throws
  	 */
      @ResponseBody
      @RequestMapping("/submitOrder")
      public Object submitOrder(String data,HttpServletRequest request) throws Exception{
  		log.info("start[CartController.submitOrder]");
  		//获取请求参数
  		JSONObject jsonData =  JSONObject.fromObject(data);
  		MemberListDo memberListDo = APICommonUtil.getMemberListDo((String)request.getAttribute("sessionid"));
  		
//  		//测试
//  		memberListDo = new MemberListDo();
//  		memberListDo.setAccountType("100");
//  		memberListDo.setMemberId("62");
//  		memberListDo.setAccountId(152);
//  		memberListDo.setLoginName("YOYO");
  		
  		
  		//订单号
  		ResultObject resultObject = null;
  		HeadObject headObject = null;
  		JSONObject jsonObject = new JSONObject();
  		// 查询当前登陆的member对象
  		if (memberListDo==null||!memberListDo.getAccountType().equals("100")) 
  		{
  			return new ResultObject(new HeadObject(ErrorCode.FAILURE),JSONObject.fromObject("{result:false,isBuyer:false}"));
  		}
  			// 用户已登录
  		String storeIdString = jsonData.getJSONArray("storeIds").toString();
  		String payment = (String) CommonUtil.getJsonValue(jsonData,"payment");

  		String proIdsString = jsonData.getJSONArray("proIdListString").toString();
  		String remark = jsonData.getString("remark");
  		String quantityListString = jsonData.getJSONArray("quantityListString").toString();
  		Integer point=StringUtil.isNotEmpty((String) CommonUtil.getJsonValue(jsonData,"point"))?Integer.parseInt((String) CommonUtil.getJsonValue(jsonData,"point")):0;
  		String appointmentStr = (String) CommonUtil.getJsonValue(jsonData, "appointmens");
  		String goodsIdsStr = (String) CommonUtil.getJsonValue(jsonData, "goodsIds");
  		
  			List<Integer> storeIdList = null;
  			payment = (payment != null && ("到店支付".equals(payment) || "在线支付".equals(payment))) ? payment : "在线支付";
  			if (StringUtil.isEmpty(storeIdString)) 
  			{
  				return new ResultObject(new HeadObject(ErrorCode.FAILURE),JSONObject.fromObject("{result:false,msg:'请选择门店'}"));
  			}
  			storeIdList = (List<Integer>) JSONArray.toCollection(JSONArray.fromObject(storeIdString), Integer.class);
  			if (null==storeIdList||storeIdList.size()<=0) 
  			{
  				return new ResultObject(new HeadObject(ErrorCode.FAILURE),JSONObject.fromObject("{result:false,msg:'请选择门店'}"));
  			}
  			List<Integer> proIdList = null;
  			if (StringUtil.isEmpty(proIdsString)) 
  			{
  				return new ResultObject(new HeadObject(ErrorCode.FAILURE),JSONObject.fromObject("{result:false,msg:'该货品不存在'}"));
  			}
  			proIdList = (List<Integer>) JSONArray.toCollection(JSONArray.fromObject(proIdsString),Integer.class);
  			if (null==proIdList||proIdList.size()<=0) 
  			{
  				return new ResultObject(new HeadObject(ErrorCode.FAILURE),JSONObject.fromObject("{result:false,msg:'该货品不存在'}"));
  			}
  			Member member = this.selectMember(memberListDo.getAccountId());
  			if(point>member.getPointUseable().intValue()){
  				return new ResultObject(new HeadObject(ErrorCode.FAILURE),JSONObject.fromObject("{result:false,msg:'可用积分小于兑换积分'}"));
  			}
  			List<Integer> quantityList = (List<Integer>) JSONArray.toCollection(JSONArray.fromObject(quantityListString), Integer.class);
  			//根据proIdList查询购物车对象
  			headObject = CommonHeadUtil.geneHeadObject("cartService.findMemberCartListByProIdList");
  			Map<String, Object> cartParam=new HashMap<String,Object>();
  			cartParam.put("proIdList", proIdList);
  			cartParam.put("memberId", Integer.valueOf(memberListDo.getMemberId()));
  			resultObject = (ResultObject) orderService.doServiceByServer(new RequestObject(headObject,cartParam));
  			List<CartListDTO> cartList = (List<CartListDTO>) JSONArray.toCollection(JSONArray.fromObject(resultObject.getContent()),CartListDTO.class);
  			if(null!=cartList&&cartList.size()>0){
  				for (CartListDTO cartListDTO : cartList) {
  					if("1".equals(cartListDTO.getLimitGoodsdown()) || "1".equals(cartListDTO.getLimitStore()) || "1".equals(cartListDTO.getLimitStoredown())
  							|| "1".equals(cartListDTO.getShopStatus()) || "1".equals(cartListDTO.getStatus()) || "1".equals(cartListDTO.getDisabled())){
  						return new ResultObject(new HeadObject(ErrorCode.FAILURE), JSONObject.fromObject("{result:false,isLogin:true,msg:\"很抱歉，该订单中存在失效货品\"}"));
  					}
  				}
  			}
  			if(cartList!=null&&cartList.size()>=1){
  				//查询店铺
  				storeIdList.clear();
  	    		for(int i =0 ; i<cartList.size() ; i++){
  	    			storeIdList.add(cartList.get(i).getStoreId());
  	    		}
  				headObject = CommonHeadUtil.geneHeadObject("storeService.selectByStoreIdList3");
  				resultObject = (ResultObject) memberService.doServiceByServer(new RequestObject(headObject, storeIdList));
  				List<Store> storeList = null;
  				if(resultObject.getContent()!=null){
  					storeList = com.alibaba.fastjson.JSONArray.parseArray(resultObject.getContent().toString(), Store.class);
  				}
  				if(storeList!=null){
  					for(int i=0;i<storeList.size();i++){
  						if("1".equals(storeList.get(i).getLimitGoodsdown()) || "1".equals(storeList.get(i).getLimitStore()) || "1".equals(storeList.get(i).getLimitStoredown())
  								|| "1".equals(storeList.get(i).getShopstatus()) || "1".equals(storeList.get(i).getStatus()) || "1".equals(storeList.get(i).getDisabled())){
  							return new ResultObject(new HeadObject(ErrorCode.FAILURE), JSONObject.fromObject("{result:false,isLogin:true,msg:'很抱歉，该订单中存在失效货品'}"));
  						}
  					}
  				}
  				
  				for (int i = 0; i < cartList.size(); i++) 
  				{
  					if("0".equals(cartList.get(i).getgMarketable()) || "1".equals(cartList.get(i).getgDisabled()) 
  							|| "0".equals(cartList.get(i).getpMarketable()) || "1".equals(cartList.get(i).getpDisabled())){
  						return new ResultObject(new HeadObject(ErrorCode.FAILURE), JSONObject.fromObject("{result:false,isLogin:true,msg:'很抱歉，该订单中存在失效货品'}"));
  					}
  					for (int j = 0; j < proIdList.size(); j++) 
  					{
  						if (proIdList.get(j).equals(cartList.get(i).getProductId())) 
  						{
  							if(quantityList.get(j) > cartList.get(i).getStore()){
  								return new ResultObject(new HeadObject(ErrorCode.FAILURE), JSONObject.fromObject("{result:false,isLogin:true,msg:'很抱歉，该订单中存在货品库存不足'}"));
  							}else{
  								cartList.get(i).setQuantity(quantityList.get(j));
  							}
  						}
  					}
  					
  					String specDesc = cartList.get(i).getSpecDesc();
  					if(specDesc!=null&&!"".equals(specDesc)){
  						String[] specItems = specDesc.split(",");
  						if(specItems!=null&&specItems.length>=1){
  							for(int j = specItems.length-1 ; j>=0 ; j--){
  								if(specItems[j].split(":")[0].split("\\|")[1].equals("分店")){
  									cartList.get(i).setStoreId(Integer.parseInt(specItems[j].split(":")[1].split("\\|")[0]));
  									cartList.get(i).setStoreName(specItems[j].split(":")[1].split("\\|")[1]);
  									break;
  								}
  							}
  						}
  					}
  					cartList.get(i).setMemberId(Integer.valueOf(memberListDo.getMemberId()));
  				}
  			}
  			
  			
  			// 判断请求来源是否来自移动端
  			String checkFrom = jsonData.getString("fphoneostype")!=null&& jsonData.getString("fphoneostype").equals("5000")?"appAndroid":"appIOS";
  			// 存入数据库
  			if (null==cartList||cartList.size()<=0){
  				// 删除购物车数据
  				headObject = CommonHeadUtil.geneHeadObject("cartService.deleteCartByProIdAndMemberId");
  				jsonObject.clear();
  				jsonObject.put("memberId", memberListDo.getMemberId());
  				jsonObject.put("proIdList", proIdList);
  				orderService.doServiceByServer(new RequestObject(headObject,jsonObject));
  				resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:true}"));
  				resultObject = new ResultObject(new HeadObject(ErrorCode.FAILURE),JSONObject.fromObject("{result:false,msg:'订单提交失败，该订单中的货品不存在'}"));
  			}
  				
  			for (CartListDTO cartListDTO : cartList) {
  					headObject = CommonHeadUtil.geneHeadObject( "goodsTimePriceService.findGoodsTimePrice");
  					Map<String, Object> param=new HashMap<String,Object>();
  					if(StringUtil.isNotEmpty(cartListDTO.getAppointment())){
  						int index=cartListDTO.getAppointment().indexOf("|")==-1?cartListDTO.getAppointment().length():cartListDTO.getAppointment().indexOf("|");
  						param.put("priceDate", cartListDTO.getAppointment().substring(0, index));
  					}else{
  						param.put("priceDate", new Date());
  					}
          			param.put("goodsId", cartListDTO.getGoodsId());
          			resultObject = (ResultObject) itemService.doServiceByServer(new RequestObject(headObject, param));
          			if(null!=resultObject.getContent()){
          				GoodsTimePrice goodsTimePrice=(GoodsTimePrice)resultObject.getContent();
          				cartListDTO.setGoodsPrice(goodsTimePrice.getPrice().doubleValue());
          			}
  			}
  			jsonObject.clear();
  			jsonObject.put("member", member);
  			jsonObject.put("cartList", cartList);
  			jsonObject.put("payment", payment);
  			jsonObject.put("checkFrom", checkFrom);
  			jsonObject.put("ip", request.getRemoteAddr());
  			jsonObject.put("remark",remark);
  			jsonObject.put("memberName",memberListDo.getLoginName());
  			jsonObject.put("point", point);
  			synchronized (this) {
  				headObject = CommonHeadUtil.geneHeadObject("cartService.saveOrder");
  				resultObject = (ResultObject) orderService.doServiceByServer(new RequestObject(headObject, jsonObject));
  				if(resultObject.getHead().getRetCode().equals(ErrorCode.FAILURE)){
  					return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,msg:'订单提交失败，"+resultObject.getHead().getRetMsg()+"'}"));
  				}else{
  					for(CartListDTO c:cartList){
  						headObject = CommonHeadUtil.geneHeadObject("productService.decreStore");
  						jsonObject.clear();
  						jsonObject.put("productId", c.getProductId());
  						jsonObject.put("quantity", c.getQuantity());
  						itemService.doServiceByServer(new RequestObject(headObject, jsonObject));
  					}
  					
  					headObject = CommonHeadUtil.geneHeadObject("cartService.deleteCartByProIdAndMemberId");
  					jsonObject.clear();
  					jsonObject.put("memberId", memberListDo.getMemberId());
  					jsonObject.put("proIdList", proIdList);
  					orderService.doServiceByServer(new RequestObject(headObject,jsonObject));
  					
  					try{
  						String [] appointments = appointmentStr.split(",");
  						String [] goodsIds=goodsIdsStr.split(",");
  						for (int j = 0; j < goodsIds.length; j++) {
  							String key = goodsIds[j]+"_"+appointments[j].replace("-", "").replace(":", "").replace("|", "_");
  							String count = redisService.get(key.toString());
  							if(StringUtil.isEmpty(count)){
  								count="1";
  							}else{
  								count = String.valueOf(Integer.valueOf(count)+1);
  							}
  							redisService.set(key.toString(), count, 60*60*24);
  						}
  					}catch(Exception e){
  						e.printStackTrace();
  					}
  				}
  				return new ResultObject(new HeadObject(ErrorCode.SUCCESS),null);
  			}
  	}
      
    
    
   
    
    @RequestMapping("/deleteCart")
    @ResponseBody
	public Object deleteCart(String data,HttpServletRequest request) throws Exception 
	{
		log.info("start[CartController.deleteCart]");
		
		//获取请求参数
		JSONObject jsonData =  JSONObject.fromObject(data);
		MemberListDo memberListDo = APICommonUtil.getMemberListDo((String)request.getAttribute("sessionid"));
		Integer accountId = memberListDo.getAccountId();
		
		/////////测试代码 start //////
//		Integer  accountId=152;
//		jsonData = new JSONObject();
//		jsonData.put("proIdListString", "[445,399,438]");
       /////////测试代码 end //////
		
		// 获取数据
		String proIdString = jsonData.getString("proIdListString");
		
		//检查参数
		if(StringUtils.isEmpty(proIdString)){
			return processExpction("货品ID不能为空");
		}
		
		PamAccount pamAccount = findAccountId(accountId);
		if(pamAccount!=null&&!"100".equals(pamAccount.getAccountType())){
			return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,isBuyer:false}"));
		}
		if(pamAccount!=null){
			accountId = pamAccount.getAccountId();
		}
		List<Integer> proIdList = null;
		String resultMsg = null;
		if (proIdString != null && !"".equals(proIdString)) 
		{
			proIdList = (List<Integer>) JSONArray.toCollection(JSONArray.fromObject(proIdString), Integer.class);
		} else {
			resultMsg = "没有选中任何商品";
			return new ResultObject(new HeadObject(ErrorCode.FAILURE),JSONObject.fromObject("{result:false,msg:'" + resultMsg + "'}"));
		}
		// 判断登陆状态
		Member member = null;
		ResultObject resultObject = null;
		member = this.selectMember(accountId);
		if (member != null) // 用户已登录
		{
			boolean deleteResult = this.deleteCartFromDb( proIdList,member.getMemberId());
			resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),
					JSONObject.fromObject("{result:" + deleteResult + "}"));
		} else {// 用户未登录
			resultObject = new ResultObject(new HeadObject(ErrorCode.FAILURE),JSONObject.fromObject("{msg:未登录}"));
		}
		log.info("end[CartController.deleteCart]");
		return resultObject;
	}
    
    /**
     * @Title: deleteCartFromDb 
     * @Description: (根据货品id列表从购物车数据库删除指定商品) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-13 
     * @version 1.0.0 
     * @param request
     * @param proIdList
     * @param memberId
     * @param @return
     * @param @throws Exception    
     * @return boolean    返回类型 
     * @throws
     */
    private boolean deleteCartFromDb(List<Integer> proIdList, Integer memberId) throws Exception 
	{
		HeadObject headObject = CommonHeadUtil.geneHeadObject("cartService.deleteCartByProIdAndMemberId");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("memberId", memberId);
		jsonObject.put("proIdList", proIdList);
	    headObject = (HeadObject) orderService.doServiceByServer(new RequestObject(headObject, jsonObject));
		String retCode = headObject.getRetCode();
		if (ErrorCode.SUCCESS.equals(retCode)) 
		{
			return true;
		}
		return false;
	}
    
    private  Object getObjFromJson(String jsonString,String orderId) { 
    	JSONObject jsonObject = JSONObject.fromObject(jsonString); 
        Map map = new HashMap(); 
        for(Iterator iter = jsonObject.keys(); iter.hasNext();){ 
            String key = (String)iter.next(); 
            map.put(key, jsonObject.get(key)); 
        }
        map.put("orderId", orderId);
        return JSONObject.fromObject(map);
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
    
    private List<CartListDTO> findProImage(List<CartListDTO> list) throws Exception
	{
		if (list != null && list.size() >= 1) 
		{
			HeadObject headObject = null;
			ResultObject resultObject = null;
			List<Picture> pictureList = null;
			for (int i =0 ; i<list.size() ; i++) 
			{
				//查询图片
				headObject = CommonHeadUtil.geneHeadObject("pictureService.selectByProductId");
				resultObject = (ResultObject) itemService.doServiceByServer(new RequestObject(headObject, list.get(i).getProductId()));
//				pictureList = (List<Picture>) resultObject.getContent();
				if(resultObject.getContent()!=null){
					pictureList = com.alibaba.fastjson.JSONObject.parseArray(resultObject.getContent().toString(), Picture.class);
					if(pictureList!=null&&pictureList.size()>=1){
						list.get(i).setGoodsImage(pictureList.get(0).getPicturePath());
					}
				}
			}
		}
		return list;
	}
    
    private List<CartListDTO> findStoresByCartList( List<CartListDTO> list) throws Exception{
    	if (list != null && list.size() >= 1) 
		{
    		List<Integer> storeIdList = new ArrayList<Integer>();
    		for(int i =0 ; i<list.size() ; i++){
    			storeIdList.add(list.get(i).getStoreId());
    		}
			HeadObject headObject = null;
			ResultObject resultObject = null;
			headObject = CommonHeadUtil.geneHeadObject("storeService.selectByStoreIdList3");
			resultObject = (ResultObject) memberService.doServiceByServer(new RequestObject(headObject, storeIdList));
			List<Store> storeList = null;
			if(resultObject.getContent()!=null){
				storeList = com.alibaba.fastjson.JSONArray.parseArray(resultObject.getContent().toString(), Store.class);
			}
			if(storeList!=null){
				for(int i=0;i<storeList.size();i++){
					for(int j =0; j<list.size();j++){
						if(list.get(j).getStoreId().equals(storeList.get(i).getStoreId().intValue())){
							list.get(j).setLimitGoodsdown(storeList.get(i).getLimitGoodsdown());
							list.get(j).setLimitStore(storeList.get(i).getLimitStore());
							list.get(j).setLimitStoredown(storeList.get(i).getLimitStoredown());
							list.get(j).setShopStatus(storeList.get(i).getShopstatus());
							list.get(j).setStatus(storeList.get(i).getStatus());
							list.get(j).setDisabled(storeList.get(i).getDisabled());
						}
					}
				}
			}
		}
		return list;
    }
    
    private PamAccount findAccountId(Integer accountId) throws Exception{
    	HeadObject headObject = CommonHeadUtil.geneHeadObject("accountService.selectByAccountId");
		ResultObject resultObject = (ResultObject) memberService.doServiceByServer(new RequestObject(headObject, accountId));
		PamAccount pamAccount = null;
		if(resultObject!=null&&resultObject.getContent()!=null){
			pamAccount = (PamAccount) resultObject.getContent();
		}
		return pamAccount;
    }
    /**
     * @Title: totalPrice 
     * @Description: (计算购物车列表中的商品总价) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-16
     * @version 1.0.0 
     * @param cartList
     * @param @return    
     * @return Double    返回类型 
     * @throws
     */
	private Double totalPrice(List<CartListDTO> cartList) 
	{
		Double totalPrice = 0.0;
		if (cartList != null && cartList.size() >= 1) 
		{
			for (int i = 0; i < cartList.size(); i++) 
			{
				totalPrice += cartList.get(i).getGoodsPrice() * cartList.get(i).getQuantity();
			}
		}
		return totalPrice;
	}
	/**
	 * @Title: addCartdata 
	 * @Description: (判断加入购物车的数据是否合法) 
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-4-11 
	 * @version 1.0.0 
	 * @param request
	 * @param goodsId
	 * @param quantity
	 * @param specValueId
	 * @param productIdString
	 * @param accountId
	 * @param @return
	 * @param @throws Exception    
	 * @return String    返回类型 
	 * @throws
	 */
	private String addCartdata(String goodsIdString,
			String quantityString, String specValueId, String productIdString, Integer accountId,String appointment) throws Exception 
	{
		Integer quantity = 1;
		if (quantityString != null && !"".equals(quantityString.trim())) 
		{
			quantity = Integer.parseInt(quantityString);
		}
		if (quantity == null || quantity < 1) 
		{
			quantity = 1;
		}
		HeadObject headObject;
		ResultObject resultObject;
		JSONObject jsonObject;
		Integer productId;
		ProductImgDTO product;
		GoodsWithBLOBs goodsWithBLOBs = null;
		Integer goodsId;
		if (productIdString != null && !"".equals(productIdString.trim())) 
		{
			productId = Integer.parseInt(productIdString);
			//查询商品对象
			headObject = CommonHeadUtil.geneHeadObject("goodsService.selectByProductId");
			resultObject = (ResultObject) itemService.doServiceByServer(new RequestObject(headObject, productId));
			goodsWithBLOBs = (GoodsWithBLOBs)resultObject.getContent();
			
			//查询货品对象
			headObject = CommonHeadUtil.geneHeadObject("productService.findByProductId");
			resultObject = (ResultObject) itemService.doServiceByServer(new RequestObject(headObject, productId));
			
		} else {
			if (goodsIdString != null && !"".equals(goodsIdString.trim())) 
			{
				goodsId = Integer.parseInt(goodsIdString.trim());
				// 根据商品id查询商品对象
				headObject = CommonHeadUtil.geneHeadObject("goodsService.selectByGoodsId");
				jsonObject = new JSONObject();
				jsonObject.put("goodsId", goodsId);
				resultObject = (ResultObject) itemService.doServiceByServer(new RequestObject(headObject, jsonObject));
				goodsWithBLOBs = (GoodsWithBLOBs) JSONObject.toBean(
						(JSONObject) resultObject.getContent(), GoodsWithBLOBs.class);
				if (goodsWithBLOBs != null && goodsWithBLOBs.getGoodsId() != null)// 商品存在，状态
				{
					// 根据分类id查询该商品是否有规格
					headObject = CommonHeadUtil.geneHeadObject("categoryService.getCatSpecByCatId");
					jsonObject.clear();
					jsonObject.put("catId", goodsWithBLOBs.getCatId());
					resultObject = (ResultObject) itemService.doServiceByServer(new RequestObject(headObject,jsonObject));
					List<CatSpecShip> catSpecShipList = (List<CatSpecShip>) JSONArray.toCollection(JSONArray.fromObject(resultObject.getContent()), CatSpecShip.class);
					String specInfo = null;
					if (catSpecShipList != null && catSpecShipList.size() >= 1) 
					{
						// 根据用户选择的商品规格查询规格对象
						List<Integer> specValueIdList = (List<Integer>) JSONArray.toCollection(JSONArray.fromObject(specValueId), Integer.class);
						if (specValueIdList != null && specValueIdList.size() >= 1) 
						{
							headObject = CommonHeadUtil.geneHeadObject("specService.selectSpecAndValueById");
							resultObject = (ResultObject) itemService.doServiceByServer(new RequestObject(headObject, JSONArray.fromObject(specValueIdList)));
							List<SpecQryDTO> specQryDTOList = (List<SpecQryDTO>) JSONArray.toCollection(JSONArray.fromObject(resultObject.getContent()), SpecQryDTO.class);
							if (specQryDTOList != null && specQryDTOList.size() >= 1) 
							{
								StringBuffer sb = new StringBuffer();
								for (int i = 0; i < specQryDTOList.size(); i++) 
								{
									sb.append(specQryDTOList.get(i).getSpecName());
									sb.append(":");
									sb.append(specQryDTOList.get(i).getSpecValueName());
									sb.append(",");
								}
								specInfo = sb.toString();
							} else {
								return "商品规格不存在";
							}
						} else {
							return "请选择商品规格";
						}
					} else {
						// 该商品没有规格 查询product对象
					}
					// 查询product对象
					headObject = CommonHeadUtil.geneHeadObject("productService.selectBySpecInfoAndGoodsId");
					resultObject = (ResultObject) itemService.doServiceByServer(new RequestObject(headObject,specInfo 
							!= null ? JSONObject.fromObject("{goodsId:\"" + goodsId + "\",specInfo:\"" 
							+ specInfo + "\"}") : JSONObject.fromObject("{goodsId:\"" + goodsId + "\"}")));
				} else {
					return "该商品不存在";
				}
			} else {
				return "该商品不存在";
			}
		}
		Integer storeId = null;
		if (resultObject != null && resultObject.getContent() != null) 
		{
			product = (ProductImgDTO) resultObject.getContent();
//			System.out.println("controller...productId.."+product.getProductId());
			if (product != null && product.getProductId() != null && product.getProductId() != 0) 
			{
				if("1".equals(product.getDisabled()) || "1".equals(goodsWithBLOBs.getDisabled())){
					return "很抱歉，该货品不存在";
				}
				
				if("0".equals(product.getMarketable()) || "0".equals(goodsWithBLOBs.getMarketable())){
					return "很抱歉，该货品已下架";
				}
				
				if (product.getStoreInt() <= 0) 
				{
					return "该货品库存不足";
				} else {
					quantity = product.getStoreInt() < quantity ? product
							.getStoreInt() : quantity;
				}
				String specDesc = product.getSpecDesc();
				if(specDesc!=null&&!"".equals(specDesc)){
					String[] specItems = specDesc.split(",");
					if(specItems!=null&&specItems.length>=1){
						for(int i = specItems.length-1 ; i>=0 ; i--){
							if(specItems[i].split(":")[0].split("\\|")[1].equals("分店")){
								storeId = Integer.parseInt(specItems[i].split(":")[1].split("\\|")[0]);
								break;
							}
						}
					}
				}
			} else {
				return "该货品不存在";
			}
		} else {
			return "该货品不存在";
		}
		if(!(storeId!=null&&storeId!=0)){
			storeId=goodsWithBLOBs.getStoreId();
		}
		//查询分店
		if(storeId!=null&&storeId!=0){
			List<Integer> storeIdList = new ArrayList<Integer>();
			storeIdList.add(storeId);
			headObject = CommonHeadUtil.geneHeadObject("storeService.selectByStoreIdList3");
			resultObject = (ResultObject) memberService.doServiceByServer(new RequestObject(headObject, storeIdList));
			List<Store> storeList = null;
			if(resultObject.getContent()!=null){
				storeList = com.alibaba.fastjson.JSONArray.parseArray(resultObject.getContent().toString(), Store.class);
			}
			if(storeList!=null){
				if("1".equals(storeList.get(0).getLimitGoodsdown()) || "1".equals(storeList.get(0).getLimitStore()) 
						|| "1".equals(storeList.get(0).getLimitStoredown()) || "1".equals(storeList.get(0).getDisabled()) 
						|| "1".equals(storeList.get(0).getStatus()) || "1".equals(storeList.get(0).getShopstatus())){
					return "很抱歉，该货品已下架";
				}
			}
		}
		newCart = new Cart(null, product.getGoodsId(), product.getProductId(), goodsWithBLOBs.getCompanyId(), storeId,
				specValueId != null && !"".equals(specValueId) ? specValueId.substring(1, specValueId.length() - 1) : "",
				quantity, (int) (System.currentTimeMillis() / 1000),appointment);
		return "";
	}
	
	/**
	 * @Title: addCartToDb 
	 * @Description: (添加商品至购物车数据库) 
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-4-12 
	 * @version 1.0.0 
	 * @param request
	 * @param newCart
	 * @param accountId
	 * @param @return
	 * @param @throws Exception    
	 * @return String    返回类型 
	 * @throws
	 */
	private String addCartToDb( Cart newCart,Integer accountId) throws Exception 
	{
		// 根据用户id查询购物车商品
		HeadObject headObject = CommonHeadUtil.geneHeadObject("cartService.findCartListByAccountId");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("accountId", accountId);
		ResultObject resultObject = (ResultObject) orderService.doServiceByServer(new RequestObject(headObject, jsonObject));// 判断失效商品?
		List<Cart> cartList = (List<Cart>) JSONArray.toCollection((JSONArray) resultObject.getContent(), Cart.class);
		// 判断购物车中是否已存在该商品，存在则加数量，不存在则加入购物车
		boolean isExist = false;
		for (Cart c : cartList) 
		{
			if (c.getProductId().equals(newCart.getProductId())) // 该商品已存在
			{
				// 修改购物车中商品购买数量
				headObject = CommonHeadUtil.geneHeadObject("cartService.updateQuantityByProAndMem");
				newCart.setQuantity(c.getQuantity() + newCart.getQuantity());
				headObject = (HeadObject) orderService.doServiceByServer(new RequestObject(
						headObject, JSONObject.fromObject(newCart)));
				if (headObject.getRetCode().equals(ErrorCode.FAILURE)) 
				{
					return errorMsg;
				}
				isExist = true;
			}
		}
		if (!isExist) 
		{
			if (cartList.size() >= MAX_CART_SIZE) 
			{
				return "购物车已满，请清理后再加入购物车";
			} else {
				// 将购物车数据存入数据库
				headObject = CommonHeadUtil.geneHeadObject("cartService.saveCart");
				jsonObject.clear();
				jsonObject.put("specValueId", newCart.getSpecValueId());
				newCart.setSpecValueId(null);
				newCart.setCartId(null);
				jsonObject.put("newCart", newCart);
				headObject = (HeadObject) orderService.doServiceByServer(new RequestObject(headObject, jsonObject));
				if (resultObject.getHead().getRetCode().equals(ErrorCode.FAILURE)) 
				{
					return errorMsg;
				}
			}
		}
		return null;
	}
}
