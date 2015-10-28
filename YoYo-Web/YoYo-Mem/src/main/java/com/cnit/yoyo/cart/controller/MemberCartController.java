package com.cnit.yoyo.cart.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.constant.OrderConstant.OrderType;
import com.cnit.yoyo.coupon.model.dto.MemberCouponDTO;
import com.cnit.yoyo.coupon.model.dto.MemberCouponQryDTO;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.cart.Cart;
import com.cnit.yoyo.model.cart.dto.CartDTO;
import com.cnit.yoyo.model.cart.dto.CartListDTO;
import com.cnit.yoyo.model.cart.dto.CartProductDTO;
import com.cnit.yoyo.model.goods.AccessoryGoods;
import com.cnit.yoyo.model.goods.GoodsTimePrice;
import com.cnit.yoyo.model.goods.Picture;
import com.cnit.yoyo.model.goods.ProductWithBLOBs;
import com.cnit.yoyo.model.goods.Store;
import com.cnit.yoyo.model.member.Member;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.model.member.PamAccount;
import com.cnit.yoyo.model.sales.activity.dto.ScoreBuyApplyDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.DateUtils;
import com.cnit.yoyo.util.RedisClientUtil;
import com.cnit.yoyo.util.StringUtil;

/**
 * @ClassName: CartController 
 * @Description: (购物车管理功能) 
 * @detail <购物车页面的增删改查>
 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
 * @date 2015-4-9
 * @version 1.0.0
 */
@Controller
@RequestMapping("/cart")
@SuppressWarnings("unchecked")
public class MemberCartController
{
	public static final Logger log = LoggerFactory.getLogger(MemberCartController.class);
	//购物车cookie的key值
	private static final String CART_COOKIE_NAME = "YOYO_cart";
	//购物车最大能容纳的商品种类数量
	private static final int MAX_CART_SIZE = 50;
	//购物车cookie的寿命
	private static final int COOKIE_MAX_AGE_HOUR=7*24;
	//购物车cookie对象
	private Cookie cartCookie;
	
    @Autowired
    public RemoteService orderService;
    @Autowired
    public RemoteService memberService;
    @Autowired
    public RemoteService itemService;
    @Autowired
	private RemoteService salesService;
    @Autowired
    private RedisClientUtil redisService;
    

    /**
     * @throws Exception 
     * @Title:  findAccountId  
     * @Description:  (获取当前登陆用户的accountId)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-11 上午11:09:17  
     * @version 1.0.0 
     * @param @param request
     * @param @return
     * @return Integer  返回类型 
     * @throws
     */
    private PamAccount findAccountId(HttpServletRequest request) throws Exception{
    	Integer accountId = (Integer) CommonUtil.getSession(request).getAttribute("accountId");
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020109-02");
		ResultObject resultObject = memberService.doService(new RequestObject(headObject, accountId));
		PamAccount pamAccount = null;
		if(resultObject!=null&&resultObject.getContent()!=null){
			pamAccount = (PamAccount) resultObject.getContent();
		}
		return pamAccount;
    }

    /**
	 * @Title: findCartByProIdList 
	 * @Description: (根据货品id列表查询购物车商品列表数据) 
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-4-10 
	 * @version 1.0.0 
	 * @param request
	 * @param list
	 * @param @return
	 * @param @throws Exception    
	 * @return List<CartListDTO>    返回类型 
	 * @throws
	 */
    private List<CartListDTO> findCartByProIdList2(HttpServletRequest request,
			List<CartListDTO> list) throws Exception 
	{
		List<Integer> proIdList = new ArrayList<Integer>();
		List<CartListDTO> cartList = null;
		if (null != list && list.size() >= 1) 
		{
			for (Cart c : list) 
			{
				proIdList.add(c.getProductId());
			}
			HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020122-04");
			ResultObject resultObject = orderService.doService(new RequestObject(headObject, 
					JSONArray.fromObject(proIdList)));
			cartList = (List<CartListDTO>) JSONArray.toCollection(JSONArray.fromObject(resultObject.getContent()),CartListDTO.class);
			if (cartList != null && cartList.size() > 0) 
			{
				for (int j = 0; j < cartList.size(); j++) 
				{
					for (int i = 0; i < list.size(); i++) 
					{
						if (list.get(i).getProductId().equals(cartList.get(j).getProductId())) 
						{
							cartList.get(j).setQuantity(list.get(i).getQuantity() > cartList.get(j).
									getStore() ? cartList.get(j).getStore() : list.get(i).getQuantity());
							cartList.get(j).setTime(list.get(i).getTime());
							cartList.get(j).setSpecValueId(list.get(i).getSpecValueId());
						}
					}
				}
			}
		}
		return cartList;
	}

    /**
     * @Title: selectMember 
     * @Description: (根据accountId查询member对象) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-20 
     * @version 1.0.0 
     * @param accountId
     * @param loginStatus
     * @param request
     * @param @return
     * @param @throws Exception    
     * @return Member    返回类型 
     * @throws
     */
    private Member selectMember(Integer accountId, 
			HttpServletRequest request) throws Exception 
	{
		if (accountId != null && accountId != 0) 
		{
			HeadObject headObject = CommonHeadUtil.geneHeadObject(request,"1000020102-17", 
					GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("accountId", accountId);
			ResultObject resultObject = memberService.doService(new RequestObject(headObject, jsonObject));
			return (Member) JSONObject.toBean(JSONObject.fromObject(resultObject.getContent()),Member.class);
		}
		return null;
	}
	/**
	 * @Title: selectCookie 
	 * @Description: (从cookie中获取购物车数据) 
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
				if (c.getName().equals(CART_COOKIE_NAME)) 
				{
					String jsonString = URLDecoder.decode(c.getValue(), "utf-8");
					cartCookie = c;
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
	private void saveCookie(List<Cart> cartList, HttpServletResponse response)
			throws UnsupportedEncodingException 
	{
		if (cartList != null && cartList.size() >= 1) 
		{
			JSONArray array = JSONArray.fromObject(cartList);
			if (cartCookie != null) 
			{
				cartCookie.setValue(URLEncoder.encode(array.toString(), "utf-8"));
			} else {
				cartCookie = new Cookie(CART_COOKIE_NAME, URLEncoder.encode(array.toString(), "utf-8"));
			}
			cartCookie.setMaxAge(COOKIE_MAX_AGE_HOUR * 60 * 60);
			cartCookie.setPath("/");
			response.addCookie(cartCookie);
		} else {
			if (cartCookie != null) 
			{
				cartCookie.setMaxAge(0);
				cartCookie.setPath("/");
				response.addCookie(cartCookie);
			}
		}
	}

	/**
	 * @Title: putSelectPro 
	 * @Description: (将去结算的货品id存入session) 
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-4-15
	 * @version 1.0.0 
	 * @param request
	 * @param response
	 * @param @return
	 * @param @throws Exception    
	 * @return Object    返回类型 
	 * @throws
	 */
	@ResponseBody
    @RequestMapping("/putSelectPro")
	public Object putProId(HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
    	log.info("start[CartController.putSelectPro]");
		String result = "empty";
		boolean isLogin = false;
		String msg = "";
		String accessoryIdString = request.getParameter("accessoryId");
		if (accessoryIdString != null && !"".equals(accessoryIdString)) 
		{
			Long accessoryId = Long.parseLong(accessoryIdString);
			//查询优惠套装对象
			HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010201-17");
			ResultObject resultObject = itemService.doService(new RequestObject(headObject, accessoryId));
			AccessoryGoods ag = (AccessoryGoods) resultObject.getContent(); 
			if(ag!=null){
				if(ag.getMinBuy()<1){
					ag.setMinBuy(1);
				}
				if(ag.getMaxBuy()<1){
					ag.setMaxBuy(1);
				}
				if(ag.getMinBuy()>ag.getMaxBuy()){
					Integer temp = ag.getMinBuy();
					ag.setMinBuy(ag.getMaxBuy());
					ag.setMaxBuy(temp);
				}
				//根据套餐id查询商品列表
				String productIdString = request.getParameter("productId");
				Integer productId = null;
				if(productIdString!=null&&!"".equals(productIdString)){
					productId = Integer.parseInt(productIdString);
				}
				
				headObject = CommonHeadUtil.geneHeadObject(request, "010201-05"); 
						
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("accessoryId", accessoryId);
				jsonObject.put("isPager", "0");
				jsonObject.put("productId", productId);
				resultObject = itemService.doService(new RequestObject(headObject, jsonObject));
				List<ProductWithBLOBs> productList = (List<ProductWithBLOBs>) resultObject.getContent();
				
				if (productList != null && productList.size() >= 1) {
					List<Integer> productIdList = new ArrayList<Integer>();
					for (int i = 0; i < productList.size(); i++) {
						productIdList.add(productList.get(i).getProductId());
					}
					request.getSession().setAttribute("proIdListString", productIdList.toString());
					List<Integer> quantityList = new ArrayList<Integer>();
					for (int i = 0; i < productIdList.size(); i++) {
						quantityList.add(ag.getMinBuy());
					}
					request.getSession().setAttribute("quantityListString", quantityList.toString());
					request.getSession().setAttribute("accessoryId", accessoryId);
					result = "true";
				} else {
					msg = "该优惠套装的货品不存在";
//					isGoodsListExist = false;
				}
			}else{
				msg = "该优惠套装不存在";
			}
		}else{
			request.getSession().removeAttribute("accessoryId");
			String proIdListString = request.getParameter("proIdList");
			String appointment=request.getParameter("appointment");
			if (proIdListString != null && !"".equals(proIdListString)) 
			{
				List<Integer> proIdList = (List<Integer>) JSONArray.toCollection(JSONArray.fromObject(proIdListString), Integer.class);
				if (proIdList != null && proIdList.size() >= 1) 
				{
					request.getSession().setAttribute("proIdListString", proIdListString);
					request.getSession().setAttribute("appointment", appointment);
					result = "true";
				}
				
				String quantityListString = request.getParameter("quantityList");
				if (quantityListString != null && !"".equals(quantityListString)) 
				{
					List<Integer> quantityList = (List<Integer>) JSONArray.toCollection(JSONArray.fromObject(quantityListString), Integer.class);
					if (quantityList != null && quantityList.size() >= 1) 
					{
						request.getSession().setAttribute("quantityListString", quantityListString);
					}else{
						request.getSession().removeAttribute("quantityListString");
					}
				}else{
					request.getSession().removeAttribute("quantityListString");
				}
			}
		}
		MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
		if(null!=memberListDo&&("100").equals(memberListDo.getAccountType())){
			isLogin=true;
		}else{		
			isLogin = true;
		}
		log.info("end[CartController.putSelectPro]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS),
				JSONObject.fromObject("{result:\"" + result + "\",isLogin:\"" + isLogin + "\",msg:\""+msg+"\"}"));
	}
    
    /**
     * @Title: confirmOrder 
     * @Description: (确认订单页面) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-15
     * @version 1.0.0 
     * @param @return    
     * @return String    返回类型 
     * @throws
     */
    @RequestMapping("/confirmOrder")
	public String toConfirmOrder(HttpServletRequest request,HttpServletResponse response,String productIds,Long accessoryId,String appointment){	
    	HeadObject headObject = null;
    	List<CartDTO> cartList = null;
    	ResultObject resultObject = null;
    	AccessoryGoods ag = null;
    	List<Integer> proIdList = new ArrayList<Integer>();;
    	try{
    		MemberListDo memberListDo=CommonUtil.getMemberListDo(request);
    		if(null!=memberListDo){
    			Member member = this.selectMember(memberListDo.getAccountId(), request);
    			request.setAttribute("member", member);
	    		Map<String, Object> param=new HashMap<String,Object>();
	    		if(null!=accessoryId||StringUtil.isNotEmpty(request.getParameter("orderType"))){
	    			headObject = CommonHeadUtil.geneHeadObject(request, "2000020122-10");
	    		}else{
	    			headObject = CommonHeadUtil.geneHeadObject(request, "2000020122-11");
	    		}
	    		if(null!=accessoryId){
					//查询优惠套装对象
					headObject = CommonHeadUtil.geneHeadObject(request, "010201-17");
					resultObject = itemService.doService(new RequestObject(headObject, accessoryId));
					ag = (AccessoryGoods) resultObject.getContent(); 
					if(null!=ag){
						if(ag.getMinBuy()<1){
							ag.setMinBuy(1);
						}
						if(ag.getMaxBuy()<1){
							ag.setMaxBuy(1);
						}
						if(ag.getMinBuy()>ag.getMaxBuy()){
							Integer temp = ag.getMinBuy();
							ag.setMinBuy(ag.getMaxBuy());
							ag.setMaxBuy(temp);
						}
						//根据套餐id查询商品列表
						Integer productId = Integer.parseInt(request.getParameter("productId"));
						headObject = CommonHeadUtil.geneHeadObject(request, "010201-05"); 
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("accessoryId", accessoryId);
						jsonObject.put("isPager", "0");
						jsonObject.put("productId", productId);
						resultObject = itemService.doService(new RequestObject(headObject, jsonObject));
						List<ProductWithBLOBs> productList = (List<ProductWithBLOBs>) resultObject.getContent();
						if (productList != null && productList.size() >= 1) {
							for (int i = 0; i < productList.size(); i++) {
								proIdList.add(productList.get(i).getProductId());
							}
						}
						request.setAttribute("ag", ag);
						request.setAttribute("appointment", appointment);
					}
				}else{
					proIdList = (List<Integer>) JSONArray.toCollection(JSONArray.fromObject(productIds), Integer.class);
					param.put("memberId", memberListDo.getMemberId());					
				}
	    		param.put("productIds", proIdList);
	    		resultObject = orderService.doService(new RequestObject(headObject,param));
	    		cartList=(List<CartDTO>) resultObject.getContent();
	    		List<Long> storeIds=new ArrayList<Long>();
	    		for (CartDTO cartDTO : cartList) {
	    			storeIds.add(cartDTO.getStore().getStoreId());
					for (CartProductDTO dto : cartDTO.getCartProducts()) {
						headObject = CommonHeadUtil.geneHeadObject(request, "060102-05");
						resultObject = salesService.doService(new RequestObject(headObject, dto.getGoodsId()));
						if(null!=resultObject&&OrderType.SCORE_BUY.getKey().equals(request.getParameter("orderType"))){
							ScoreBuyApplyDTO scoreBuyApply = (ScoreBuyApplyDTO) resultObject.getContent();
							dto.setPrice(scoreBuyApply.getLastPrice());
							dto.setQuantity(Integer.valueOf(request.getParameter("quantity")));
							scoreBuyApply.setScore(scoreBuyApply.getScore()*dto.getQuantity());
							request.setAttribute("scoreBuy", scoreBuyApply);
							request.setAttribute("orderType", OrderType.SCORE_BUY.getKey());
							continue;
						}
						headObject = CommonHeadUtil.geneHeadObject(request, "011601-01");
						param=new HashMap<String,Object>();
						if(StringUtil.isNotEmpty(dto.getAppointment())){
							int index=dto.getAppointment().indexOf("|")==-1?dto.getAppointment().length():dto.getAppointment().indexOf("|");
							param.put("priceDate", dto.getAppointment().substring(0, index));
						}else{
							param.put("priceDate", new Date());
						}
	        			param.put("goodsId", dto.getGoodsId());
	        			resultObject = itemService.doService(new RequestObject(headObject, param));
	        			if(null!=resultObject.getContent()){
	        				GoodsTimePrice goodsTimePrice=(GoodsTimePrice)resultObject.getContent();
	        				dto.setPrice(goodsTimePrice.getPrice());
	        			}
					}
				}
	    		findMemberCoupon(request, storeIds);
	    		BigDecimal sumPrice = this.totalCartPrice(cartList);
	    		if(null!=accessoryId){
	    			BigDecimal discountPrice=BigDecimal.ZERO;
					if (ag.getDiscType().equals("minus")) {
						discountPrice = ag.getCredit();
					} else if (ag.getDiscType().equals("discount")) {
						discountPrice = sumPrice.multiply(BigDecimal.ONE.subtract(ag.getCredit()));
					} else {
						discountPrice = sumPrice;
					}
					request.setAttribute("discountPrice", discountPrice);
	    		}
				DecimalFormat df2 = new DecimalFormat("#.00");//四舍五入保留两位小数   
				request.setAttribute("sumPrice", df2.format(Double.parseDouble(sumPrice.toString())));
	    		request.setAttribute("cartList", cartList);
    		}
    	}catch (Exception e) {
    		e.printStackTrace();
		}
    	return "pages/biz/cart/confirmOrder";
    }
    
    /**
     * @description <b>查询用户优惠卷</b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015-8-26
     * @param request
     * @param storeIds
     * void
    */
    public void findMemberCoupon(HttpServletRequest request,List<Long> storeIds){
    	try{
	    	MemberListDo memberListDo=CommonUtil.getMemberListDo(request);
	    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030115-01");
	    	MemberCouponQryDTO memberCouponQry=new MemberCouponQryDTO();
	    	memberCouponQry.setMemberId(Long.valueOf(memberListDo.getMemberId()));
	    	memberCouponQry.setStoreIds(storeIds);
	    	ResultObject resultObject = memberService.doService(new RequestObject(headObject, memberCouponQry));
	    	List<MemberCouponDTO> memberCoupons = (List<MemberCouponDTO>) resultObject.getContent();
	    	request.setAttribute("memberCoupons", memberCoupons);
    	}catch (Exception e) {
    		e.printStackTrace();
		}
    }
    
    /**
     * @Title:  findProImage  
     * @Description:  (查询货品图片)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-28 下午2:19:02  
     * @version 1.0.0 
     * @param @param request
     * @param @param list
     * @param @return
     * @param @throws Exception
     * @return List<CartListDTO>  返回类型 
     * @throws
     */
    private List<CartListDTO> findProImage(HttpServletRequest request,
			List<CartListDTO> list) throws Exception 
	{
		if (list != null && list.size() >= 1) 
		{
			HeadObject headObject = null;
			ResultObject resultObject = null;
			List<Picture> pictureList = null;
			for (int i =0 ; i<list.size() ; i++) 
			{
				//查询图片
				headObject = CommonHeadUtil.geneHeadObject(request, "4000020101-03", 
						GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
				resultObject = itemService.doService(new RequestObject(headObject, list.get(i).getProductId()));
				if(resultObject.getContent()!=null){
					pictureList = com.alibaba.fastjson.JSONObject.parseArray(resultObject.getContent().toString(), Picture.class);
				}
				if(pictureList!=null&&pictureList.size()>=1){
					list.get(i).setGoodsImage(pictureList.get(0).getPicturePath());
				}
			}
		}
		return list;
	}
    
    
    /**
     * @Title: getOrderInfo 
     * @Description: (获取确认订单页面的货品和分店数据) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-15
     * @version 1.0.0 
     * @param request
     * @param response
     * @param @return
     * @param @throws Exception    
     * @return Object    返回类型 
     * @throws
     */
    @RequestMapping("/getOrderInfo")
    @ResponseBody
	public Object findOrderInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		log.info("start[CartController.getOrderInfo]");

		// 获取商品详情
		PamAccount pamAccount = findAccountId(request);
		if(pamAccount!=null&&!"100".equals(pamAccount.getAccountType())){
			return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,isBuyer:false}"));
		}
		Integer accountId = null;
		if(pamAccount!=null){
			accountId = pamAccount.getAccountId();
		}
		ResultObject resultObject = null;
		HeadObject headObject;
		JSONObject jsonObject = new JSONObject();
		boolean isLogin = false;

		if (accountId != null && accountId != 0) 
		{
			isLogin = true;
			this.saveCookieToDB(request, response, accountId);
			Object proIdListObject = request.getSession().getAttribute("proIdListString");
			if(proIdListObject!=null){
				String proIdListString = proIdListObject.toString();
				if (proIdListString!=null&&!"".equals(proIdListString.trim())) 
				{
					List<Integer> proIdList = (List<Integer>) JSONArray.toCollection(JSONArray.fromObject(proIdListString), Integer.class);
					if (proIdList != null && proIdList.size() >= 1) 
					{
						// 查询member对象
						Member member = this.selectMember(accountId, request);
						// 根据proIdList查询购物车对象
						List<CartListDTO> cartList = null;
						Object quantityListObject = request.getSession().getAttribute("quantityListString");
						
						if (quantityListObject != null) 
						{
							String quantityListString = quantityListObject.toString();
							if(quantityListString!=null&&!"".equals(quantityListObject)){
								List<Integer> quantityList = (List<Integer>) JSONArray.toCollection(JSONArray.fromObject(quantityListString), Integer.class);
								if(quantityList.size()==proIdList.size()){
									headObject = CommonHeadUtil.geneHeadObject(request, "2000020122-04", 
											GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
									resultObject = orderService.doService(new RequestObject(headObject, 
											JSONArray.fromObject(proIdList)));
									cartList = (List<CartListDTO>) JSONArray.toCollection(JSONArray.fromObject(resultObject.getContent()),CartListDTO.class);
									if (cartList != null && cartList.size() > 0) 
									{
										for (int j = cartList.size()-1; j >=0; j--) 
										{
											if("1".equals(cartList.get(j).getLimitGoodsdown()) || "1".equals(cartList.get(j).getLimitStore()) || "1".equals(cartList.get(j).getLimitStoredown())
													|| "1".equals(cartList.get(j).getShopStatus()) || "1".equals(cartList.get(j).getStatus()) || "1".equals(cartList.get(j).getDisabled())){
												return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONObject.fromObject("{isLogin:" + isLogin + ", msg:\"很抱歉，该订单中存在失效货品\"}"));
											}
											for (int i = 0; i < proIdList.size(); i++) 
											{
												if (proIdList.get(i).equals(cartList.get(j).getProductId())) 
												{
//													cartList.get(j).setQuantity(quantityList.get(i) > cartList.get(j).
//															getStore() ? cartList.get(j).getStore() : quantityList.get(i));
													if(quantityList.get(i) > cartList.get(j).getStore()){
														return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONObject.fromObject("{isLogin:" + isLogin + ", msg:\"很抱歉，该订单中存在货品库存不足\"}"));
													}else{
														cartList.get(j).setQuantity(quantityList.get(i));
													}
												}
											}
											
										}
									}
								}
							}
						}
						if(!(cartList!=null&&cartList.size()>=1)){
							headObject = CommonHeadUtil.geneHeadObject(request, "2000020122-06",
									GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
							jsonObject.put("memberId", member.getMemberId());
							jsonObject.put("proIdList", proIdList);
							resultObject = orderService.doService(new RequestObject(
									headObject, jsonObject));
							cartList = (List<CartListDTO>) JSONArray.toCollection(JSONArray.fromObject(resultObject.getContent()), CartListDTO.class);
						}
						List<Store> storeList = null;
						if(cartList!=null&&cartList.size()>=1){
							//查询店铺
							List<Integer> storeIdList = new ArrayList<Integer>();
				    		for(int i =0 ; i<cartList.size() ; i++){
				    			storeIdList.add(cartList.get(i).getStoreId());
				    		}
							headObject = CommonHeadUtil.geneHeadObject(request, "031001-06");
							resultObject = memberService.doService(new RequestObject(headObject, storeIdList));
							
							if(resultObject.getContent()!=null){
								storeList = com.alibaba.fastjson.JSONArray.parseArray(resultObject.getContent().toString(), Store.class);
							}
							if(storeList!=null){
								for(int i=0;i<storeList.size();i++){
									if("1".equals(storeList.get(i).getLimitGoodsdown()) || "1".equals(storeList.get(i).getLimitStore()) || "1".equals(storeList.get(i).getLimitStoredown())
											|| "1".equals(storeList.get(i).getShopstatus()) || "1".equals(storeList.get(i).getStatus()) || "1".equals(storeList.get(i).getDisabled())){
										return new ResultObject(new HeadObject(ErrorCode.SUCCESS),
												JSONObject.fromObject("{isLogin:true,msg:\"很抱歉，该订单中存在失效货品\"}"));
									}
								}
							}
							
							//查询货品图片
							this.findProImage(request, cartList);
							for (int j = cartList.size()-1; j >=0; j--) 
							{
								if(cartList.get(j).getQuantity() <= 0 || "0".equals(cartList.get(j).getgMarketable()) || "1".equals(cartList.get(j).getgDisabled()) 
										|| "0".equals(cartList.get(j).getpMarketable()) || "1".equals(cartList.get(j).getpDisabled())){
									return new ResultObject(new HeadObject(ErrorCode.SUCCESS),
											JSONObject.fromObject("{isLogin:true,msg:\"很抱歉，该订单中存在失效货品\"}"));
								}
							}
						}
						
						Double sumPrice = this.totalPrice(cartList);
						Object accessoryIdObject = request.getSession().getAttribute("accessoryId");
						AccessoryGoods ag = null;
						if(accessoryIdObject != null){
							Long accessoryId = Long.parseLong(accessoryIdObject.toString());
							//查询优惠套装对象
							headObject = CommonHeadUtil.geneHeadObject(request, "010201-17");
							resultObject = itemService.doService(new RequestObject(headObject, accessoryId));
							ag = (AccessoryGoods) resultObject.getContent(); 
						}
					
						com.alibaba.fastjson.JSONObject returnJson = new com.alibaba.fastjson.JSONObject();
//						returnJson.put("sumPrice", sumPrice);
						DecimalFormat df2 = new DecimalFormat("#.00");//四舍五入保留两位小数   
						returnJson.put("sumPrice", df2.format(Double.parseDouble(sumPrice.toString())));
						returnJson.put("ag", ag);
						returnJson.put("content", cartList);
						returnJson.put("storeList", storeList);
						returnJson.put("isLogin", true);
						resultObject = new ResultObject(new HeadObject(
								ErrorCode.SUCCESS), returnJson);
					} else {
						resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),
								JSONObject.fromObject("{isLogin:" + isLogin + ",empty:true}"));
					}
				} else {
					resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),
							JSONObject.fromObject("{isLogin:" + isLogin + ",empty:true}"));
				}
			}else{
				resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),
						JSONObject.fromObject("{isLogin:" + isLogin + ",empty:true}"));
			}
		} else {
			resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),
					JSONObject.fromObject("{isLogin:" + isLogin + "}"));
		}

		log.info("end[CartController.getOrderInfo]");
		return resultObject;
	}
    

    
    
    
    
    
    
    /**
     * @Title: saveCookieToDB 
     * @Description: (将cookie中的购物车商品存入数据库) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-16
     * @version 1.0.0 
     * @param request
     * @param response
     * @param accountId
     * @param loginStatus
     * @param @throws Exception    
     * @return void    返回类型 
     * @throws
     */
	private void saveCookieToDB(HttpServletRequest request,
			HttpServletResponse response, Integer accountId)
			throws Exception 
	{
		JSONArray array = this.selectCookie(request);
		List<CartListDTO> cartList = null;
		if (array != null && array.size() >= 1) 
		{
			cartList = (List<CartListDTO>) JSONArray.toCollection(array, CartListDTO.class);
			cartList = this.findCartByProIdList2(request, cartList);
			if (cartList != null && cartList.size() >= 1) 
			{
				// 根据accountId查询member对象
				Member member = this.selectMember(accountId, request);
				// 将购物车数据存入数据库
				if (member != null) 
				{
					HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020122-07");
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("memberId", member.getMemberId());
					jsonObject.put("cartList", cartList);
					jsonObject.put("maxCartSize", MAX_CART_SIZE);
					orderService.doService(new RequestObject(headObject, jsonObject));
					this.saveCookie(null, response);
				}
			}
		}
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
	 * @description <b>购物车商品总价</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-8-17
	 * @param cartList
	 * @return
	 * BigDecimal
	*/
	private BigDecimal totalCartPrice(List<CartDTO> cartList){
		BigDecimal totalPrice=BigDecimal.ZERO;
		for (CartDTO cartDTO : cartList) {
			for (CartProductDTO cartProduct : cartDTO.getCartProducts()) {
				if(null!=cartProduct.getQuantity()&&cartProduct.getQuantity()>0){
					totalPrice=cartProduct.getPrice().multiply(new BigDecimal(cartProduct.getQuantity())).add(totalPrice);
				}else{
					totalPrice=cartProduct.getPrice().multiply(BigDecimal.ONE).add(totalPrice);
				}
				
			}
		}
		return totalPrice;
	}
//    
	/**
	 * @Title: getDays 
	 * @Description: (获取从当前日期开始的一周内的日期数据) 
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-4-17
	 * @version 1.0.0 
	 * @param request
	 * @param response
	 * @param @return
	 * @param @throws Exception    
	 * @return Object    返回类型 
	 * @throws
	 */
    @RequestMapping("/getDays")
    @ResponseBody
	public Object getDays(HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		log.info("start[CartController.getDays]");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String today = format.format(System.currentTimeMillis());
		String[] weekDays = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
		Calendar cal = new GregorianCalendar();
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		w = w < 0 ? 0 : w;
		List<String> days = new ArrayList<String>();
		days.add("{date:\"" + today + "\",week:\"今天\"}");

		for (int i = 1; i < 8; i++) 
		{
			days.add("{date:\"" + getFormatDateAdd(i, format) + "\",week:\""
					+ weekDays[(w + i) % 7] + "\"}");
		}
		log.info("end[CartController.getDays]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS),
				JSONArray.fromObject(days));
	}
//
    /**
     * @Title: getFormatDateAdd 
     * @Description: (取得当前日期加上一定天数后的日期) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-17
     * @version 1.0.0 
     * @param amount
     * @param format
     * @param @return    
     * @return String    返回类型 
     * @throws
     */
    private String getFormatDateAdd(int amount, SimpleDateFormat format) 
	{
		Calendar cal = Calendar.getInstance();
		cal.add(GregorianCalendar.DATE, amount);
		return format.format(cal.getTime());
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
    public Object orderAdd(HttpServletRequest request,HttpServletResponse response,Long [] coupons) throws Exception{
		log.info("start[CartController.submitOrder]");
		//订单号
		ResultObject resultObject = null;
		HeadObject headObject = null;
		JSONObject jsonObject = new JSONObject();
		// 查询当前登陆的member对象
		MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
		if (memberListDo==null||!memberListDo.getAccountType().equals("100")) 
		{
			return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,isBuyer:false}"));
		}
			// 用户已登录
			String storeIdString = request.getParameter("storeId");
			String payment = request.getParameter("payment");
			String proIdsString = request.getParameter("proIds");
			String accessoryIdString = request.getParameter("accessoryId");
			String remark = request.getParameter("remark");
			String orderType=request.getParameter("orderType");
			Integer point=StringUtil.isNotEmpty(request.getParameter("point"))?Integer.parseInt(request.getParameter("point")):0;
			List<Integer> storeIdList = null;
			payment = (payment != null && ("到店支付".equals(payment) || "在线支付".equals(payment))) ? payment : "在线支付";
			if (StringUtil.isEmpty(storeIdString)) 
			{
				return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,msg:'请选择门店'}"));
			}
			storeIdList = (List<Integer>) JSONArray.toCollection(JSONArray.fromObject(storeIdString), Integer.class);
			if (null==storeIdList||storeIdList.size()<=0) 
			{
				return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,msg:'请选择门店'}"));
			}
			List<Integer> proIdList = null;
			if (StringUtil.isEmpty(proIdsString)) 
			{
				return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,msg:'该货品不存在'}"));
			}
			proIdList = (List<Integer>) JSONArray.toCollection(JSONArray.fromObject(proIdsString),Integer.class);
			if (null==proIdList||proIdList.size()<=0) 
			{
				return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,msg:'该货品不存在'}"));
			}
			Member member = this.selectMember(memberListDo.getAccountId(), request);
			if(point>member.getPointUseable().intValue()){
				return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,msg:'可用积分小于兑换积分'}"));
			}			
			String quantityListString = request.getParameter("quantitys");
			List<Integer> quantityList = (List<Integer>) JSONArray.toCollection(JSONArray.fromObject(quantityListString), Integer.class);
			//根据proIdList查询购物车对象
			headObject = CommonHeadUtil.geneHeadObject(request, "2000020122-12");
			Map<String, Object> cartParam=new HashMap<String,Object>();
			cartParam.put("proIdList", proIdList);
			if (StringUtil.isEmpty(accessoryIdString)&&StringUtil.isEmpty(orderType)){
			cartParam.put("memberId", Integer.valueOf(memberListDo.getMemberId()));
			}
			resultObject = orderService.doService(new RequestObject(headObject,cartParam));
			List<CartListDTO> cartList = (List<CartListDTO>) JSONArray.toCollection(JSONArray.fromObject(resultObject.getContent()),CartListDTO.class);
			if(null!=cartList&&cartList.size()>0){
				for (CartListDTO cartListDTO : cartList) {
					if("1".equals(cartListDTO.getLimitGoodsdown()) || "1".equals(cartListDTO.getLimitStore()) || "1".equals(cartListDTO.getLimitStoredown())
							|| "1".equals(cartListDTO.getShopStatus()) || "1".equals(cartListDTO.getStatus()) || "1".equals(cartListDTO.getDisabled())){
						request.getSession().removeAttribute("proIdListString");
						request.getSession().removeAttribute("quantityListString");
						request.getSession().removeAttribute("accessoryId");
						return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONObject.fromObject("{result:false,isLogin:true,msg:\"很抱歉，该订单中存在失效货品\"}"));
					}
				}
			}
			//套餐
			AccessoryGoods ag = null;
			if (accessoryIdString != null && !"".equals(accessoryIdString)) 
			{
				Long accessoryId = Long.parseLong(accessoryIdString);
				//查询优惠套装对象
				headObject = CommonHeadUtil.geneHeadObject(request, "010201-17");
				resultObject = itemService.doService(new RequestObject(headObject, accessoryId));
				ag = (AccessoryGoods) resultObject.getContent(); 
				if(ag!=null){
					if(ag.getMinBuy()<1){
						ag.setMinBuy(1);
					}
					if(ag.getMaxBuy()<1){
						ag.setMaxBuy(1);
					}
					if(ag.getMinBuy()>ag.getMaxBuy()){
						Integer temp = ag.getMinBuy();
						ag.setMinBuy(ag.getMaxBuy());
						ag.setMaxBuy(temp);
					}
					//根据套餐id查询商品列表
					headObject = CommonHeadUtil.geneHeadObject(request, "010201-05");
					jsonObject.put("accessoryId", accessoryId);
					jsonObject.put("isPager", "0");
					resultObject = itemService.doService(new RequestObject(headObject, jsonObject));
					List<ProductWithBLOBs> productList = (List<ProductWithBLOBs>) resultObject.getContent();
					
					if(productList!=null&&productList.size()>=1){
						List<Integer> productIdFromAg = new ArrayList<Integer>();
						for(int i=0;i<productList.size();i++){
							productIdFromAg.add(productList.get(i).getProductId());
						}
						List<Integer> productIdFromCart = new ArrayList<Integer>();
						for(int i=cartList.size()-1 ; i>=0 ; i--){
							if(!cartList.get(i).getGoodsId().equals(ag.getGoodsId().intValue())){
								productIdFromCart.add(cartList.get(i).getProductId());
							}
						}
						if(!(productIdFromAg.containsAll(productIdFromCart)&&productIdFromCart.containsAll(productIdFromAg))){
							ag = null;
						}
					}else{
						ag  =null;
					}
				}
			}
			
			if(cartList!=null&&cartList.size()>=1){
				//查询店铺
				storeIdList.clear();
	    		for(int i =0 ; i<cartList.size() ; i++){
	    			storeIdList.add(cartList.get(i).getStoreId());
	    		}
				headObject = CommonHeadUtil.geneHeadObject(request, "031001-06");
				resultObject = memberService.doService(new RequestObject(headObject, storeIdList));
				List<Store> storeList = null;
				if(resultObject.getContent()!=null){
					storeList = com.alibaba.fastjson.JSONArray.parseArray(resultObject.getContent().toString(), Store.class);
				}
				if(storeList!=null){
					for(int i=0;i<storeList.size();i++){
						if("1".equals(storeList.get(i).getLimitGoodsdown()) || "1".equals(storeList.get(i).getLimitStore()) || "1".equals(storeList.get(i).getLimitStoredown())
								|| "1".equals(storeList.get(i).getShopstatus()) || "1".equals(storeList.get(i).getStatus()) || "1".equals(storeList.get(i).getDisabled())){
							return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONObject.fromObject("{result:false,isLogin:true,msg:'很抱歉，该订单中存在失效货品'}"));
						}
					}
				}
				
				for (int i = 0; i < cartList.size(); i++) 
				{
					if("0".equals(cartList.get(i).getgMarketable()) || "1".equals(cartList.get(i).getgDisabled()) 
							|| "0".equals(cartList.get(i).getpMarketable()) || "1".equals(cartList.get(i).getpDisabled())){
						request.getSession().removeAttribute("proIdListString");
						request.getSession().removeAttribute("quantityListString");
						request.getSession().removeAttribute("accessoryId");
						return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONObject.fromObject("{result:false,isLogin:true,msg:'很抱歉，该订单中存在失效货品'}"));
					}
					for (int j = 0; j < proIdList.size(); j++) 
					{
						if (proIdList.get(j).equals(cartList.get(i).getProductId())) 
						{
							if(quantityList.get(j) > cartList.get(i).getStore()){
								request.getSession().removeAttribute("proIdListString");
								request.getSession().removeAttribute("quantityListString");
								request.getSession().removeAttribute("accessoryId");
								return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONObject.fromObject("{result:false,isLogin:true,msg:'很抱歉，该订单中存在货品库存不足'}"));
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
			String checkFrom = this.checkFrom(request);
			// 存入数据库
			if (null==cartList||cartList.size()<=0){
				// 删除购物车数据
				headObject = CommonHeadUtil.geneHeadObject(request, "2000020122-05");
				jsonObject.clear();
				jsonObject.put("memberId", memberListDo.getMemberId());
				jsonObject.put("proIdList", proIdList);

				request.getSession().removeAttribute("proIdListString");
				request.getSession().removeAttribute("quantityListString");
				request.getSession().removeAttribute("accessoryId");
				resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:true}"));
				resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,msg:'订单提交失败，该订单中的货品不存在'}"));
			}
			ScoreBuyApplyDTO scoreBuyApply = null;
			for (CartListDTO cartListDTO : cartList) {
					if(OrderType.SCORE_BUY.getKey().equals(request.getParameter("orderType"))){
						headObject = CommonHeadUtil.geneHeadObject(request, "060102-05");
						resultObject = salesService.doService(new RequestObject(headObject, cartListDTO.getGoodsId()));
						if(null==resultObject){
							return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,msg:'该商品所属的积分换购活动无效'}"));
						}
						scoreBuyApply = (ScoreBuyApplyDTO) resultObject.getContent();
						cartListDTO.setGoodsPrice(scoreBuyApply.getLastPrice().doubleValue());
						cartListDTO.setQuantity(Integer.valueOf(request.getParameter("quantity")));
						scoreBuyApply.setScore(scoreBuyApply.getScore()*Integer.valueOf(request.getParameter("quantity")));
						if(point>member.getPointUseable().intValue()){
							return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,msg:'可用积分小于兑换积分'}"));
						}
						headObject = CommonHeadUtil.geneHeadObject(request, "020104-15");
						cartParam.put("memberId", member.getMemberId());
						cartParam.put("productId", cartListDTO.getProductId());
						cartParam.put("itemType", OrderType.SCORE_BUY.getKey());
						resultObject = orderService.doService(new RequestObject(headObject, cartParam));
						Integer count = (Integer) resultObject.getContent();
						if(scoreBuyApply.getPersonlimit()<=count){							
							return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,msg:'每个账户最多只能购买"+scoreBuyApply.getPersonlimit()+"件'}"));
						}
						continue;
					}
				
					headObject = CommonHeadUtil.geneHeadObject(request, "011601-01");
					Map<String, Object> param=new HashMap<String,Object>();
					if(StringUtil.isNotEmpty(cartListDTO.getAppointment())){
						int index=cartListDTO.getAppointment().indexOf("|")==-1?cartListDTO.getAppointment().length():cartListDTO.getAppointment().indexOf("|");
						param.put("priceDate", cartListDTO.getAppointment().substring(0, index));
					}else{
						param.put("priceDate", new Date());
					}
        			param.put("goodsId", cartListDTO.getGoodsId());
        			resultObject = itemService.doService(new RequestObject(headObject, param));
        			if(null!=resultObject.getContent()){
        				GoodsTimePrice goodsTimePrice=(GoodsTimePrice)resultObject.getContent();
        				cartListDTO.setGoodsPrice(goodsTimePrice.getPrice().doubleValue());
        			}
			}
			jsonObject.clear();
			if(OrderType.SCORE_BUY.getKey().equals(request.getParameter("orderType"))){
				jsonObject.put("scoreBuyApply", scoreBuyApply);	
			}
			jsonObject.put("member", member);
			jsonObject.put("cartList", cartList);
			jsonObject.put("payment", payment);
			jsonObject.put("checkFrom", checkFrom);
			jsonObject.put("ip", request.getRemoteAddr());
			jsonObject.put("accessoryGoods", ag);
			jsonObject.put("credit", ag!=null?ag.getCredit():0);
			jsonObject.put("remark",remark);
			jsonObject.put("memberName",memberListDo.getLoginName());
			jsonObject.put("point", point);
			jsonObject.put("coupons", coupons);
			synchronized (this) {
				headObject = CommonHeadUtil.geneHeadObject(request, "2000020122-08");
				resultObject = orderService.doService(new RequestObject(headObject, jsonObject));
				if(resultObject.getHead().getRetCode().equals(ErrorCode.FAILURE)){
					return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,msg:'订单提交失败，"+resultObject.getHead().getRetMsg()+"'}"));
				}else{
					if(StringUtil.isEmpty(orderType)&&StringUtil.isEmpty(accessoryIdString)){
						try{
							for(CartListDTO c:cartList){
								headObject = CommonHeadUtil.geneHeadObject(request, "2000020121-02",GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
								jsonObject.clear();
								jsonObject.put("productId", c.getProductId());
								jsonObject.put("quantity", c.getQuantity());
								itemService.doService(new RequestObject(headObject, jsonObject));
							}
							headObject = CommonHeadUtil.geneHeadObject(request, "2000020122-05");
							jsonObject.clear();
							jsonObject.put("memberId", memberListDo.getMemberId());
							jsonObject.put("proIdList", proIdList);
							orderService.doService(new RequestObject(headObject,jsonObject));
							String [] appointments = request.getParameter("appointment").split(",");
							String [] goodsIds=request.getParameter("goodsIds").split(",");
							for (int j = 0; j < goodsIds.length; j++) {
								if(appointments.length>0&&appointments[j].indexOf("-")>0&&appointments[j].indexOf(":")>0&&appointments[j].indexOf("|")>0){
									String key = goodsIds[j]+"_"+appointments[j].replace("-", "").replace(":", "").replace("|", "_");
									String count = redisService.get(key.toString());
									if(StringUtil.isEmpty(count)){
										count="1";
									}else{
										count = String.valueOf(Integer.valueOf(count)+1);
									}
									redisService.set(key.toString(), count, 60*60*24);
								}
							}
						}catch(Exception e){
							e.printStackTrace();
							return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:true,orderId:"+resultObject.getContent()+"}"));
						}
					}
					return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:true,orderId:"+resultObject.getContent()+"}"));
				}
			}
	}
     
    
    /**
     * @Title: checkFromMobile 
     * @Description: (判断是否是移动设备访问) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-18
     * @version 1.0.0 
     * @param request
     * @param @return    
     * @return boolean    返回类型 
     * @throws
     */
    private String checkFrom(HttpServletRequest request) 
	{
		// \b 是单词边界(连着的两个(字母字符 与 非字母字符) 之间的逻辑上的间隔),
		// 字符串在编译时会被转码一次,所以是 "\\b"
		// \B 是单词内部逻辑间隔(连着的两个字母字符之间的逻辑上的间隔)
		String phoneReg = "\\b(ip(hone|od)|android|opera m(ob|in)i"
				+ "|windows (phone|ce)|blackberry"
				+ "|s(ymbian|eries60|amsung)|p(laybook|alm|rofile/midp"
				+ "|laystation portable)|nokia|fennec|htc[-_]"
				+ "|mobile|up.browser|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";
		String tableReg = "\\b(ipad|tablet|(Nexus 7)|up.browser"
				+ "|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";

		String userAgent = request.getHeader("USER-AGENT").toLowerCase();
		if (null == userAgent) 
		{
			userAgent = "";
		}

		// 移动设备正则匹配：手机端、平板
		Pattern phonePat = Pattern.compile(phoneReg, Pattern.CASE_INSENSITIVE);
		Pattern tablePat = Pattern.compile(tableReg, Pattern.CASE_INSENSITIVE);

		// 匹配
		Matcher matcherPhone = phonePat.matcher(userAgent);
		Matcher matcherTable = tablePat.matcher(userAgent);
		if (matcherPhone.find() || matcherTable.find()) 
		{
			if (userAgent.contains("android")) 
			{
				return "appAndroid";
			} else {
				return "appIOS";
			}
//			return true;
		} else {
			return "web";
//			return false;
		}
	}
    /**
     * @Title:  toPay  
     * @Description:  (转跳支付页面)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-13 下午7:05:11  
     * @version 1.0.0 
     * @param @param request
     * @param @return
     * @return String  返回类型 
     * @throws
     */
    @RequestMapping("/toPay")
	public String toPay(HttpServletRequest request) 
    {
		return "pages/biz/cart/pay";
	}
}
