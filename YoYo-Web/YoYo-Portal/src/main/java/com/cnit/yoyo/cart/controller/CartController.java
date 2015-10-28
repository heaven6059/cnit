package com.cnit.yoyo.cart.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.aspectj.weaver.IUnwovenClassFile;
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
import com.cnit.yoyo.dto.spec.SpecQryDTO;
import com.cnit.yoyo.model.cart.Cart;
import com.cnit.yoyo.model.cart.dto.CartDTO;
import com.cnit.yoyo.model.cart.dto.CartListDTO;
import com.cnit.yoyo.model.cart.dto.CartProductDTO;
import com.cnit.yoyo.model.goods.CatSpecShip;
import com.cnit.yoyo.model.goods.GoodsAppointment;
import com.cnit.yoyo.model.goods.GoodsTimePrice;
import com.cnit.yoyo.model.goods.GoodsWithBLOBs;
import com.cnit.yoyo.model.goods.Picture;
import com.cnit.yoyo.model.goods.ProductWithBLOBs;
import com.cnit.yoyo.model.goods.Store;
import com.cnit.yoyo.model.goods.dto.ProductImgDTO;
import com.cnit.yoyo.model.member.Member;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.model.member.PamAccount;
import com.cnit.yoyo.model.member.dto.ProSimpleDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.RedisClientUtil;
import com.cnit.yoyo.util.StringUtil;
import com.cnit.yoyo.util.domain.ResultPage;
import com.cnit.yoyo.util.servlet.CookieUtil;

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
public class CartController
{
	public static final Logger log = LoggerFactory.getLogger(CartController.class);
	//购物车cookie的key值
	private static final String CART_COOKIE_NAME = "YOYO_cart";
	//购物车最大能容纳的商品种类数量
	private static final int MAX_CART_SIZE = 50;
	//购物车cookie的寿命
	private static final int CART_COOKIE_MAX_AGE_HOUR=7*24;
	//购物车cookie对象
	private Cookie cartCookie;
	
	private Cart newCart;
	
	private String errorMsg="系统繁忙，请稍后再试！";
    @Autowired
    public RemoteService orderService;
    @Autowired
    public RemoteService memberService;
    @Autowired
    public RemoteService itemService;
    //浏览历史记录cookie的key值
  	private static final String HISTORY_COOKIE_NAME = "YOYO_History";
  	//浏览历史记录cookie的寿命
  	private static final int HISTORY_COOKIE_MAX_AGE_HOUR=7*24;
  	//浏览历史记录cookie对象
  	private Cookie historyCookie;
  	
  	@Autowired
    private RedisClientUtil redisService;
    
    /**
     * @Title: toCartMain 
     * @Description: (购物车主页) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-9
     * @version 1.0.0 
     * @param @return    
     * @return String    返回类型 
     * @throws
     */
    @RequestMapping("/index")
	public String toCartMain(HttpServletRequest request,HttpServletResponse response) 
    {
    	this.findCartList(request, response);
		return "pages/biz/cart/cartMain";
	}
    
    @ResponseBody
    @RequestMapping("/checkGoodsAppointment")
    public Object checkGoodsAppointment(HttpServletRequest request,String [] goodsId,String [] appointment,String [] index){
    	try{
    		Integer notPassCount=0;
    		if(goodsId.length>0&&appointment.length>0&&index.length>0){
	    		for (int i = 0; i < goodsId.length; i++) {
	    			HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "011501-02");
	    			ResultObject resultObject = itemService.doService(new RequestObject(headObject, Long.valueOf(goodsId[i])));
	    			GoodsAppointment goodsAppointment=(GoodsAppointment)resultObject.getContent();
	    			String app=appointment.length==goodsId.length?appointment[i]:appointment[0];
	    			String num = index.length==goodsId.length?index[i]:index[0];
	    			String count = redisService.get(goodsId[i]+"_"+app.replace("-", "").replace(":", "").replace("|", "_"));
	    			if(StringUtil.isEmpty(count)){
	    			}else{
	    				Integer timeCount=0;
	    				if("1".equals(num)){
	    					timeCount=goodsAppointment.getTimeNum1(); 
	    				}else if("2".equals(num)){
	    					timeCount=goodsAppointment.getTimeNum2();
	    				}else if("3".equals(num)){
	    					timeCount=goodsAppointment.getTimeNum3();
	    				}else if("4".equals(num)){
	    					timeCount=goodsAppointment.getTimeNum4();
	    				}else if("5".equals(num)){
	    					timeCount=goodsAppointment.getTimeNum5();
	    				}else if("6".equals(num)){
	    					timeCount=goodsAppointment.getTimeNum6();
	    				}else if("7".equals(num)){
	    					timeCount=goodsAppointment.getTimeNum7();
	    				}else if("8".equals(num)){
	    					timeCount=goodsAppointment.getTimeNum8();
	    				}
	    				if(Integer.valueOf(count)>=timeCount){
	    					notPassCount++;
	    				}
	    			}
				}
    		}
    		if(notPassCount>0){
    			return ErrorCode.FAILURE;
    		}else{
    			return ErrorCode.SUCCESS;
    		}
    	}catch (Exception e) {
    		e.printStackTrace();
    		return ErrorCode.FAILURE;
		}
    }
    
    
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
     * @description <b>加入购物车</b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015年8月2日
     * @param request
     * @return
     * String
    */
    @ResponseBody
    @RequestMapping("/addToCart")
    public Object addToCart(HttpServletRequest request,@RequestParam(value="carts",required=true)String carts){
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020122-09");
    	try{
	    	MemberListDo memberListDo=CommonUtil.getMemberListDo(request);
	    	if(null==memberListDo){
	    		return CommonUtil.notLoign(headObject);
	    	}
	    	List<Cart> memberCarts=new ArrayList<Cart>();
	    	JSONArray carArray=JSONArray.fromObject(carts);
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
				cart.setAppointment(cartJSON.getString("appointment"));
				memberCarts.add(cart);
			}
			ResultObject resultObject = orderService.doService(new RequestObject(headObject, memberCarts));
			return resultObject;
    	}catch(Exception e){
    		e.printStackTrace();
    		return CommonUtil.processExpction(headObject);
    	}
    	
    }
    
    /**
     * @Title: findCartList 
     * @Description: (获取购物车列表数据) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-9 
     * @version 1.0.0 
     * @param request
     * @param response
     * @param @return
     * @param @throws Exception    
     * @return Object    返回类型 
     * @throws
     */
	public void findCartList(HttpServletRequest request,HttpServletResponse response){
    	HeadObject headObject=null;
		try{
			MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
			List<CartDTO> carts=null;
			//已登录
			Map<String, Object> param=new HashMap<String,Object>();
			if(null!=memberListDo){
				this.saveCookieToDB(request, response);
				param.put("memberId", memberListDo.getMemberId());
				headObject= CommonHeadUtil.geneHeadObject(request,"2000020122-11");
				ResultObject resultObject = orderService.doService(new RequestObject(headObject, param));
				carts=(List<CartDTO>)resultObject.getContent();
			}else{
				JSONArray cartArray=selectCookie(request);
				List<Integer> productIds=new ArrayList<Integer>();
				for (Object object : cartArray) {
					JSONObject cartObject=(JSONObject)object;
					productIds.add(cartObject.getInt("productId"));
				}
				param.put("productIds", productIds);
				headObject= CommonHeadUtil.geneHeadObject(request,"2000020122-10");
				ResultObject resultObject = orderService.doService(new RequestObject(headObject, param));
				carts=(List<CartDTO>)resultObject.getContent();
				for (Object object : cartArray) {
					for (CartDTO cartDTO : carts) {
						for (CartProductDTO cartProduct : cartDTO.getCartProducts()) {
							JSONObject cartObject=(JSONObject)object;
							if(cartProduct.getProductId().equals(cartObject.getInt("productId"))){
								cartProduct.setQuantity(cartObject.getInt("quantity"));
								cartProduct.setAppointment(cartObject.getString("appointment"));
							}
						}
					}
				}
			}
			
			for (CartDTO cartDTO : carts) {
				for (CartProductDTO dto : cartDTO.getCartProducts()) {
					headObject = CommonHeadUtil.geneHeadObject(request, "011601-01");
					param=new HashMap<String,Object>();
					if(StringUtil.isNotEmpty(dto.getAppointment())){
						int index=dto.getAppointment().indexOf("|")==-1?dto.getAppointment().length():dto.getAppointment().indexOf("|");
						param.put("priceDate", dto.getAppointment().substring(0, index));
					}else{
						param.put("priceDate", new Date());
					}
        			param.put("goodsId", dto.getGoodsId());
        			ResultObject resultObject = itemService.doService(new RequestObject(headObject, param));
        			if(null!=resultObject.getContent()){
        				GoodsTimePrice goodsTimePrice=(GoodsTimePrice)resultObject.getContent();
        				dto.setPrice(goodsTimePrice.getPrice());
        			}
				}
			}
			
			request.setAttribute("cartList", carts);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

    /**
     * @throws Exception 
     * @Title:  findStoresByCartList  
     * @Description:  (店铺信息)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-6-12 下午4:52:09  
     * @version 1.0.0 
     * @param @param request
     * @param @param cartList
     * @param @return
     * @return List<CartListDTO>  返回类型 
     * @throws
     */
    private List<CartListDTO> findStoresByCartList(HttpServletRequest request, List<CartListDTO> list) throws Exception{
    	if (list != null && list.size() >= 1) 
		{
    		List<Integer> storeIdList = new ArrayList<Integer>();
    		for(int i =0 ; i<list.size() ; i++){
    			storeIdList.add(list.get(i).getStoreId());
    		}
			HeadObject headObject = null;
			ResultObject resultObject = null;
			headObject = CommonHeadUtil.geneHeadObject(request, "031001-06", 
					GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
			resultObject = memberService.doService(new RequestObject(headObject, storeIdList));
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
    
    /**
     * @Title:  findWishAndHistory  
     * @Description:  (加载我的关注，最近浏览)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-28 下午1:28:07  
     * @version 1.0.0 
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/findWishAndHistory")
    @ResponseBody
	public Object findWishAndHistory(HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		log.info("start[CartController.findWishAndHistory]");
		PamAccount pamAccount = findAccountId(request);
		if(pamAccount!=null&&!"100".equals(pamAccount.getAccountType())){
			return new ResultObject(new HeadObject(ErrorCode.SUCCESS), null);
		}
		Integer accountId = null;
		if(pamAccount!=null){
			accountId = pamAccount.getAccountId();
		}
		ResultObject resultObject = null;
		HeadObject headObject = null;
		Member member = null;
		List<ProSimpleDTO> proHistoryList = null;
		List<ProSimpleDTO> proWishList = null;
		if (accountId != null && accountId !=0) 
		{
			member = this.selectMember(accountId, request);
		} 
		if(member!=null&&member.getMemberId()!=0){
			//用户已登录
			//我的关注
			proWishList = this.selectProWishList(member, request, headObject, resultObject);
		}
		//最近浏览
		proHistoryList = null;
		JSONArray array = this.selectHistoryCookie(request);//获取cookie数据
		if (array != null && array.size() != 0) 
		{
			proHistoryList = (List<ProSimpleDTO>) JSONArray.toCollection(array, ProSimpleDTO.class);
			proHistoryList = this.findHistoryByGoodsIdList(request, proHistoryList);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("proWishList", proWishList);
		jsonObject.put("proHistoryList", proHistoryList);
		resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS), jsonObject);
		log.info("end[CartController.findWishAndHistory]");
		return resultObject;
	}
    
    /**
     * @Title:  findWishList  
     * @Description:  (加载我的关注)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-29 下午5:18:26  
     * @version 1.0.0 
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/findWishList")
    @ResponseBody
	public Object findWishList(HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		log.info("start[CartController.findWishList]");
		PamAccount pamAccount = findAccountId(request);
		if(pamAccount!=null&&!"100".equals(pamAccount.getAccountType())){
			return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,isBuyer:false}"));
		}
		Integer accountId = null;
		if(pamAccount!=null){
			accountId = pamAccount.getAccountId();
		}
		ResultObject resultObject = null;
		HeadObject headObject = null;
		Member member = null;
		List<ProSimpleDTO> proWishList = null;
		if (accountId != null && accountId !=0) 
		{
			member = this.selectMember(accountId, request);
		} 
		if(member!=null&&member.getMemberId()!=0){
			// 用户已登录
			//我的关注
			proWishList = this.selectProWishList(member, request, headObject, resultObject);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("proWishList", proWishList);
		resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS), jsonObject);
		log.info("end[CartController.findWishList]");
		return resultObject;
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
    private List<CartListDTO> findCartByProIdList(HttpServletRequest request,
			List<CartListDTO> list) throws Exception 
	{
		List<Integer> proIdList = new ArrayList<Integer>();
		List<CartListDTO> cartList = null;
		if (list != null && list.size() >= 1) 
		{
			for (Cart c : list) 
			{
				proIdList.add(c.getProductId());
			}
			HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020122-04",GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
			ResultObject resultObject = orderService.doService(new RequestObject(headObject,JSONArray.fromObject(proIdList)));
			cartList = (List<CartListDTO>) JSONArray.toCollection(JSONArray.fromObject(resultObject.getContent()),CartListDTO.class);
			if (cartList != null && cartList.size() > 0) 
			{
				List<Integer> productIdList = new ArrayList<Integer>();
				for (int j = cartList.size()-1; j >= 0; j--) 
				{
					if(productIdList!=null&&productIdList.size()>=1&&productIdList.contains(cartList.get(j).getProductId())){
						List<Integer> tempProductIdList = new ArrayList<Integer>();
						tempProductIdList.add(cartList.get(j).getProductId());
						cartList.remove(j);
					}else{
						productIdList.add(cartList.get(j).getProductId());
						for (int i = 0; i < list.size(); i++) 
						{
							if (list.get(i).getProductId().equals(cartList.get(j).getProductId())) 
							{
								cartList.get(j).setQuantity(list.get(i).getQuantity() > cartList.get(j).getStore() ? cartList.get(j).getStore() : list.get(i).getQuantity());
								cartList.get(j).setTime(list.get(i).getTime());
								cartList.get(j).setSpecValueId(list.get(i).getSpecValueId());
							}
						}
					}
				}
			}
		}
		return cartList;
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
    private List<CartListDTO> findProImage(HttpServletRequest request,List<CartListDTO> list) throws Exception
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
    
    
    /**
     * @Title:  selectProWishList  
     * @Description:  (查询我的关注)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-27 下午3:09:45  
     * @version 1.0.0 
     * @param @param member
     * @param @param request
     * @param @param headObject
     * @param @param resultObject
     * @param @return
     * @param @throws Exception
     * @return List<ProWishDTO>  返回类型 
     * @throws
     */
    private List<ProSimpleDTO> selectProWishList(Member member, HttpServletRequest request, HeadObject headObject, ResultObject resultObject) throws Exception{
    	headObject = CommonHeadUtil.geneHeadObject(request, "030201-03", 
				GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("memberId", member.getMemberId());
		jsonObject.put("pageIndex", 1);
		jsonObject.put("pageSize", 18);
		resultObject = memberService.doService(new RequestObject(headObject, jsonObject));
		Map<String,Class> map = new HashMap<String,Class>();
		map.put("rows", ProSimpleDTO.class);
		ResultPage<ProSimpleDTO> proWishListPage = (ResultPage<ProSimpleDTO>) JSONObject.toBean(JSONObject.fromObject(resultObject.getContent()), ResultPage.class,map);
		List<ProSimpleDTO> proWishList = null;
		if(proWishListPage!=null&&proWishListPage.getRows()!=null&&proWishListPage.getRows().size()>=1){
			proWishList = proWishListPage.getRows();
			//查询货品图片
			List<Picture> pictureList = null;
			GoodsWithBLOBs goods = null;
			for(int i =0 ; i<proWishList.size() ; i++){
				headObject = CommonHeadUtil.geneHeadObject(request, "4000020101-03", 
						GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
				resultObject = itemService.doService(new RequestObject(headObject, proWishList.get(i).getProductId().intValue()));
//				pictureList = (List<Picture>) resultObject.getContent();
				if(resultObject.getContent()!=null){
					pictureList = com.alibaba.fastjson.JSONObject.parseArray(resultObject.getContent().toString(), Picture.class);
				}
				if(pictureList!=null&&pictureList.size()>=1){
					proWishList.get(i).setPicturePath(pictureList.get(0).getPicturePath());
				}else{
					headObject = CommonHeadUtil.geneHeadObject(request, "010201-09", 
							GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
					resultObject = itemService.doService(new RequestObject(headObject, proWishList.get(i).getProductId()));
					goods = (GoodsWithBLOBs) resultObject.getContent();
					proWishList.get(i).setPicturePath(goods.getMidPic());
				}
			}
		}
		return proWishList;
    }
    
    /**
     * @Title:  findHistoryByProIdList  
     * @Description:  (查询最近浏览历史商品)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-27 下午3:54:33  
     * @version 1.0.0 
     * @param @param request
     * @param @param list
     * @param @return
     * @param @throws Exception
     * @return List<CartListDTO>  返回类型 
     * @throws
     */
    private List<ProSimpleDTO> findHistoryByGoodsIdList(HttpServletRequest request,List<ProSimpleDTO> list) throws Exception 
	{
		List<Integer> goodsIdList = new ArrayList<Integer>();
		List<ProSimpleDTO> proSimpleDTOList = null;
		if (list != null && list.size() >= 1) 
		{
			for (ProSimpleDTO p : list) 
			{
				goodsIdList.add(p.getGoodsId().intValue());
			}
			HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020121-08", 
					GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("goodsIdList", goodsIdList);
			ResultObject resultObject = itemService.doService(new RequestObject(headObject, 
					jsonObject));
			List<ProductWithBLOBs> productList = (List<ProductWithBLOBs>) resultObject.getContent();
			if(productList!=null&&productList.size()>=1){
				proSimpleDTOList = new ArrayList<ProSimpleDTO>();
				ProSimpleDTO proSimpleDTO = null;
				List<Picture> pictureList = null;
				GoodsWithBLOBs goods = null;
				Integer count ;
				for(int j =0;j<goodsIdList.size();j++){
					for(int i=0;i<productList.size();i++){
						if(productList.get(i).getGoodsId().equals(goodsIdList.get(j))){
							proSimpleDTO = new ProSimpleDTO();
							BeanUtils.copyProperties(productList.get(i), proSimpleDTO);
							proSimpleDTO.setProductId(productList.get(i).getProductId().longValue());
							proSimpleDTO.setProductName(productList.get(i).getName());
							//查询图片
							headObject = CommonHeadUtil.geneHeadObject(request, "4000020101-03", 
									GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
							resultObject = itemService.doService(new RequestObject(headObject, productList.get(i).getProductId()));
//							pictureList = (List<Picture>) resultObject.getContent();
							if(resultObject.getContent()!=null){
								pictureList = com.alibaba.fastjson.JSONObject.parseArray(resultObject.getContent().toString(), Picture.class);
							}
							if(pictureList!=null&&pictureList.size()>=1){
								proSimpleDTO.setPicturePath(pictureList.get(0).getPicturePath());
							}else{
								headObject = CommonHeadUtil.geneHeadObject(request, "010201-09", 
										GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
								resultObject = itemService.doService(new RequestObject(headObject, productList.get(i).getProductId()));
								goods = (GoodsWithBLOBs) resultObject.getContent();
								proSimpleDTO.setPicturePath(goods.getMidPic());
							}
							//查询评论数量
							headObject = CommonHeadUtil.geneHeadObject(request, "030101-19", 
									GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
							resultObject = memberService.doService(new RequestObject(headObject, productList.get(i).getGoodsId()));
							count = (Integer) resultObject.getContent();
							proSimpleDTO.setCommentCount(count!=null?count:0);
							proSimpleDTOList.add(proSimpleDTO);
						}
					}
				}
			}
		}
		return proSimpleDTOList;
	}
    
	/**
	 * @Title: cartAdd 
	 * @Description: (加入购物车) 
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-4-11 
	 * @version 1.0.0 
	 * @param request
	 * @param response
	 * @param @return
	 * @param @throws Exception    
	 * @return Object    返回类型 
	 * @throws
	 */
    @RequestMapping("/addCart")
	public String cartAdd(HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		log.info("start[CartController.addCart]");
		// 获取数据
		Integer quantity = Integer.valueOf(request.getParameter("quantity"));
		String specValueId = request.getParameter("specValueId");
		Integer productId = Integer.valueOf(request.getParameter("productId"));
		String appointment=request.getParameter("appointment");
		Integer appointmentIndex=Integer.valueOf(request.getParameter("index"));
			
		//查询商品对象
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010201-09");
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, productId));
		GoodsWithBLOBs goodsWithBLOBs = (GoodsWithBLOBs)resultObject.getContent();
		
		Cart cart = new Cart();
		cart.setGoodsId(goodsWithBLOBs.getGoodsId());
		cart.setProductId(productId);
		cart.setQuantity(quantity);
		cart.setCompanyId(goodsWithBLOBs.getCompanyId());
		cart.setStoreId(goodsWithBLOBs.getStoreId());
		cart.setAppointment(appointment);
		cart.setAppointmentIndex(appointmentIndex);

		// 判断登陆状态
		MemberListDo member = CommonUtil.getMemberListDo(request);
		if (null!=member){
			cart.setMemberId(Integer.valueOf(member.getMemberId()));
			this.buyNowAddToCart(request, response);
		}else{
			this.addCartToCookie(request, response, cart);
		}
		request.setAttribute("productId", productId);
		return "pages/biz/cart/addSuccess";
	}
    
    /**
     * @description <b>立即购买加入购物车</b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015-8-18
     * @param request
     * @param response
     * @return
     * @throws Exception
     * String
    */
    @ResponseBody
    @RequestMapping("/buyNowAddToCart")
	public Object buyNowAddToCart(HttpServletRequest request,HttpServletResponse response){
		// 获取数据
    	HeadObject headObject = null;
    	try{
			Integer quantity = Integer.valueOf(request.getParameter("quantity"));
			Integer productId = Integer.valueOf(request.getParameter("productId"));
			String appointment=request.getParameter("appointment");
			Integer appointmentIndex=Integer.valueOf(request.getParameter("index"));
				
			//查询商品对象
			headObject=CommonHeadUtil.geneHeadObject(request, "010201-09");
			ResultObject resultObject = itemService.doService(new RequestObject(headObject, productId));
			GoodsWithBLOBs goodsWithBLOBs = (GoodsWithBLOBs)resultObject.getContent();
			List<Cart> memberCarts=new ArrayList<Cart>();
			// 判断登陆状态
			MemberListDo member = CommonUtil.getMemberListDo(request);
			if (null!=member){
				Cart cart = new Cart();
				cart.setGoodsId(goodsWithBLOBs.getGoodsId());
				cart.setProductId(productId);
				cart.setQuantity(quantity);
				cart.setCompanyId(goodsWithBLOBs.getCompanyId());
				cart.setStoreId(goodsWithBLOBs.getStoreId());
				cart.setAppointment(appointment);
				cart.setMemberId(Integer.valueOf(member.getMemberId()));
				cart.setAppointmentIndex(appointmentIndex);
				memberCarts.add(cart);
				headObject = CommonHeadUtil.geneHeadObject(request, "2000020122-09");
				resultObject = orderService.doService(new RequestObject(headObject, memberCarts));
				return resultObject;
			}else{
				return CommonUtil.notLoign(headObject);
			}
    	}catch (Exception e) {
    		e.printStackTrace();
    		return CommonUtil.processExpction(headObject);
		}
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
	private String addCartdata(HttpServletRequest request, String goodsIdString,
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
			headObject = CommonHeadUtil.geneHeadObject(request, "010201-09");
			resultObject = itemService.doService(new RequestObject(headObject, productId));
			goodsWithBLOBs = (GoodsWithBLOBs)resultObject.getContent();
			
			//查询货品对象
			headObject = CommonHeadUtil.geneHeadObject(request, "2000020121-05");
			resultObject = itemService.doService(new RequestObject(headObject, productId));
			
		} else {
			if (goodsIdString != null && !"".equals(goodsIdString.trim())) 
			{
				goodsId = Integer.parseInt(goodsIdString.trim());
				// 根据商品id查询商品对象
				headObject = CommonHeadUtil.geneHeadObject(request, "2000020105-30");
				jsonObject = new JSONObject();
				jsonObject.put("goodsId", goodsId);
				resultObject = itemService.doService(new RequestObject(headObject, jsonObject));
				goodsWithBLOBs = (GoodsWithBLOBs) JSONObject.toBean(
						(JSONObject) resultObject.getContent(), GoodsWithBLOBs.class);
				if (goodsWithBLOBs != null && goodsWithBLOBs.getGoodsId() != null)// 商品存在，状态
				{
					// 根据分类id查询该商品是否有规格
					headObject = CommonHeadUtil.geneHeadObject(request,"2000020103-10");
					jsonObject.clear();
					jsonObject.put("catId", goodsWithBLOBs.getCatId());
					resultObject = itemService.doService(new RequestObject(headObject,
							jsonObject));
					List<CatSpecShip> catSpecShipList = (List<CatSpecShip>) JSONArray.toCollection(JSONArray.fromObject(resultObject.getContent()), CatSpecShip.class);
					String specInfo = null;
					if (catSpecShipList != null && catSpecShipList.size() >= 1) 
					{
						// 根据用户选择的商品规格查询规格对象
						List<Integer> specValueIdList = (List<Integer>) JSONArray.toCollection(JSONArray.fromObject(specValueId), Integer.class);
						if (specValueIdList != null && specValueIdList.size() >= 1) 
						{
							headObject = CommonHeadUtil.geneHeadObject(request,"2000020102-08");
							resultObject = itemService.doService(new RequestObject(headObject, JSONArray.fromObject(specValueIdList)));
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
					headObject = CommonHeadUtil.geneHeadObject(request,"2000020121-01");
					resultObject = itemService.doService(new RequestObject(headObject,specInfo 
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
			headObject = CommonHeadUtil.geneHeadObject(request, "031001-06",GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
			resultObject = memberService.doService(new RequestObject(headObject, storeIdList));
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
	 * @Title: addCartToCookie 
	 * @Description: (添加商品至购物车cookie) 
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
	private String addCartToCookie(HttpServletRequest request,
			HttpServletResponse response, Cart newCart)
			throws UnsupportedEncodingException 
	{
		JSONArray array = this.selectCookie(request);
		List<Cart> cartList = null;
		if (array != null && array.size() >= 1) 
		{
			if (array.size() >= this.MAX_CART_SIZE) 
			{
				return "购物车已满，请清理后再加入购物车";
			}
			cartList = (List<Cart>) JSONArray.toCollection(array, Cart.class);
		}
		if (cartList == null) // cookie没有购物车数据
		{	
			cartList = new ArrayList<Cart>();
		}

		// 判断购物车中是否已存在该货品，存在则加数量，不存在则加入购物车
		boolean isExist = false;
		for (Cart c : cartList) 
		{
			if (c.getProductId().equals(newCart.getProductId())) // 该货品已存在
			{
				c.setQuantity(c.getQuantity() + newCart.getQuantity());
				isExist = true;
			}
		}
		if (!isExist) 
		{
			cartList.add(newCart);
		}
		this.saveCookie(cartList, response);
		return null;
	}
	
	
	/**
	 * @Title: selectHistoryCookie 
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
	private JSONArray selectHistoryCookie(HttpServletRequest request)
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
	 * @Title: saveHistoryCookie 
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
	private void saveHistoryCookie(List<ProSimpleDTO> ProSimpleDTOList, HttpServletResponse response)throws UnsupportedEncodingException 
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
	private JSONArray selectCookie(HttpServletRequest request)throws UnsupportedEncodingException 
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
	private void saveCookie(List<Cart> cartList, HttpServletResponse response)throws UnsupportedEncodingException 
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
			cartCookie.setMaxAge(CART_COOKIE_MAX_AGE_HOUR * 60 * 60);
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
	private String addCartToDb(HttpServletRequest request, Cart newCart,Integer accountId) throws Exception 
	{
		// 根据用户id查询购物车商品
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020122-01",GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("accountId", accountId);
		ResultObject resultObject = orderService.doService(new RequestObject(headObject, jsonObject));// 判断失效商品?
		List<Cart> cartList = (List<Cart>) JSONArray.toCollection((JSONArray) resultObject.getContent(), Cart.class);
		// 判断购物车中是否已存在该商品，存在则加数量，不存在则加入购物车
		boolean isExist = false;
		for (Cart c : cartList) 
		{
			if (c.getProductId().equals(newCart.getProductId())) // 该商品已存在
			{
				// 修改购物车中商品购买数量
				headObject = CommonHeadUtil.geneHeadObject(request, "2000020122-03",GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
				newCart.setQuantity(c.getQuantity() + newCart.getQuantity());
				resultObject = orderService.doService(new RequestObject(headObject, JSONObject.fromObject(newCart)));
				if (resultObject.getHead().getRetCode().equals(ErrorCode.FAILURE)) 
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
				headObject = CommonHeadUtil.geneHeadObject(request, "2000020122-02", 
						GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
				jsonObject.clear();
				jsonObject.put("specValueId", newCart.getSpecValueId());
				newCart.setSpecValueId(null);
				newCart.setCartId(null);
				jsonObject.put("newCart", newCart);
				resultObject = orderService.doService(new RequestObject(headObject, jsonObject));
				if (resultObject.getHead().getRetCode().equals(ErrorCode.FAILURE)) 
				{
					return errorMsg;
				}
			}
		}
		return null;
	}

	/**
	 * @Title: cartDel 
	 * @Description: (删除购物车内的指定商品) 
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-4-13 
	 * @version 1.0.0 
	 * @param request
	 * @param response
	 * @param @return
	 * @param @throws Exception    
	 * @return Object    返回类型 
	 * @throws
	 */
    @RequestMapping("/deleteCart")
    @ResponseBody
	public Object cartDel(HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		log.info("start[CartController.deleteCart]");
		// 获取数据
		String proIdString = request.getParameter("proIdString");
//		Integer accountId = (Integer) request.getSession().getAttribute("accountId");
//		String loginStatus = (String) request.getSession().getAttribute("loginStatus");
//		Integer accountId = findLoginUserId(request);
//		Integer accountId = findAccountId(request);
		PamAccount pamAccount = findAccountId(request);
		if(pamAccount!=null&&!"100".equals(pamAccount.getAccountType())){
			return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,isBuyer:false}"));
		}
		Integer accountId = null;
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
			return new ResultObject(new HeadObject(ErrorCode.SUCCESS),
					JSONObject.fromObject("{result:false,msg:'" + resultMsg + "'}"));
		}

		// 判断登陆状态
		Member member = null;
		ResultObject resultObject = null;
		member = this.selectMember(accountId, request);
		if (member != null) // 用户已登录
		{
			boolean deleteResult = this.deleteCartFromDb(request, proIdList,
					member.getMemberId());
			resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),
					JSONObject.fromObject("{result:" + deleteResult + "}"));
		} else {// 用户未登录
			this.deleteCartFromCookie(request, response, proIdList);
			resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),
					JSONObject.fromObject("{result:true}"));
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
    private boolean deleteCartFromDb(HttpServletRequest request,List<Integer> proIdList, Integer memberId) throws Exception 
	{
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020122-05",
				GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("memberId", memberId);
		jsonObject.put("proIdList", proIdList);
		ResultObject resultObject = orderService.doService(new RequestObject(
				headObject, jsonObject));
		String retCode = resultObject.getHead().getRetCode();
		if (ErrorCode.SUCCESS.equals(retCode)) 
		{
			return true;
		}
		return false;
	}
    
	/**
	 * @Title: deleteCartFromCookie 
	 * @Description: (根据货品id列表从购物车cookie删除指定商品) 
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-4-13
	 * @version 1.0.0 
	 * @param request
	 * @param response
	 * @param proIdList
	 * @param @return
	 * @param @throws UnsupportedEncodingException    
	 * @return List<Cart>    返回类型 
	 * @throws
	 */
    private List<Cart> deleteCartFromCookie(HttpServletRequest request,HttpServletResponse response, List<Integer> proIdList)throws UnsupportedEncodingException 
	{
		// 获取cookie中的购物车数据
		JSONArray array = this.selectCookie(request);
		List<Cart> cartList = null;
		if (array != null && array.size() != 0) 
		{
			cartList = (List<Cart>) JSONArray.toCollection(array, Cart.class);
		}
		if (cartList == null) // cookie没有购物车数据
		{
			cartList = new ArrayList<Cart>();
		}

		// 根据货品id列表删除购物车中的数据
		for (int i = cartList.size() - 1; i >= 0; i--) 
		{
			for (Integer j : proIdList) 
			{
				if (cartList.get(i).getProductId().equals(j)) 
				{
					cartList.remove(i);
					break;
				}
			}
		}

		// 保存cookie
		this.saveCookie(cartList, response);
		return cartList;
	}

	/**
	 * @Title: cartUpdate 
	 * @Description: (修改购物车内的指定商品的购买数量) 
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-4-13
	 * @version 1.0.0 
	 * @param request
	 * @param response
	 * @param @return
	 * @param @throws Exception    
	 * @return Object    返回类型 
	 * @throws
	 */
    @RequestMapping("/updateCart")
    @ResponseBody
	public Object cartUpdate(HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		log.info("start[CartController.updateCart]");
		// 获取数据
		String quantityString = request.getParameter("quantity");
		String productIdString = request.getParameter("productId");
//		Integer accountId = (Integer) request.getSession().getAttribute("accountId");
//		String loginStatus = (String) request.getSession().getAttribute("loginStatus");
//		Integer accountId = findLoginUserId(request);
//		Integer accountId = findAccountId(request);
		PamAccount pamAccount = findAccountId(request);
		if(pamAccount!=null&&!"100".equals(pamAccount.getAccountType())){
			return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,isBuyer:false}"));
		}
		Integer accountId = null;
		if(pamAccount!=null){
			accountId = pamAccount.getAccountId();
		}
		Integer quantity = null;
		Integer productId = null;
		if (quantityString != null && !"".equals(quantityString)) 
		{
			quantity = Integer.parseInt(quantityString);
			quantity = quantity < 1 ? 1 : quantity;
		} else {
			return new ResultObject(new HeadObject(ErrorCode.SUCCESS),
					JSONObject.fromObject("{result:false,msg:'商品数量输入有误'}"));
		}
		if (productIdString != null && !"".equals(productIdString)) 
		{
			productId = Integer.parseInt(productIdString);
		} else {
			return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,msg:'该货品不存在'}"));
		}

		// 判断库存是否充足
		List<Integer> proIdList = new ArrayList<Integer>();
		proIdList.add(productId);
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020122-04",GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = orderService.doService(new RequestObject(headObject, JSONArray.fromObject(proIdList)));
		List<CartListDTO> cartListDTO = (List<CartListDTO>) JSONArray.toCollection(JSONArray.fromObject(resultObject.getContent()), CartListDTO.class);
		if (cartListDTO != null && cartListDTO.size() >= 1) 
		{
			if (quantity > cartListDTO.get(0).getStore()) 
			{
				quantity = cartListDTO.get(0).getStore();
			}
		}

		// 判断登陆状态
		Member member = this.selectMember(accountId, request);
		Cart newCart = new Cart();
		newCart.setMemberId(member != null ? member.getMemberId() : null);
		newCart.setProductId(productId);
		newCart.setQuantity(quantity);
		boolean updateResult;
		if (member != null) // 用户已登录
		{
			updateResult = this.updateCartFromDb(request, newCart);
			System.out.println(("controller..."+updateResult));
		} else {// 用户未登录
			this.updateCartFromCookie(request, response, newCart);
			updateResult = true;
		}
		resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),
				JSONObject.fromObject("{result:" + updateResult + ",quantity:" + quantity + "}"));
		log.info("end[CartController.updateCart]");
		return resultObject;
	}
    
    /**
     * @Title: updateCartFromDb 
     * @Description: (修改购物车数据库中指定商品的购买数量) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-14 
     * @version 1.0.0 
     * @param request
     * @param newCart
     * @param @return
     * @param @throws Exception    
     * @return boolean    返回类型 
     * @throws
     */
    private boolean updateCartFromDb(HttpServletRequest request, Cart newCart)throws Exception 
	{
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020122-03", 
				GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = orderService.doService(new RequestObject(
				headObject, JSONObject.fromObject(newCart)));
		System.out.println("controller....resultObject..."+resultObject);
		String retCode = resultObject.getHead().getRetCode();
		if (ErrorCode.SUCCESS.equals(retCode)) 
		{
			return true;
		}
		return false;
	}
    
	/**
	 * 
	 * @Title: updateCartFromCookie 
	 * @Description: (修改购物车cookie中指定商品的购买数量) 
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-4-14 
	 * @version 1.0.0 
	 * @param request
	 * @param response
	 * @param newCart
	 * @param @return
	 * @param @throws UnsupportedEncodingException    
	 * @return List<Cart>    返回类型 
	 * @throws
	 */
	private List<Cart> updateCartFromCookie(HttpServletRequest request,
			HttpServletResponse response, Cart newCart)
			throws UnsupportedEncodingException 
	{
		// 获取cookie中的购物车数据
		JSONArray array = this.selectCookie(request);
		List<Cart> cartList = null;
		if (array != null && array.size() != 0) 
		{
			cartList = (List<Cart>) JSONArray.toCollection(array, Cart.class);
		}
		if (cartList == null) // cookie没有购物车数据
		{
			cartList = new ArrayList<Cart>();
		}

		// 根据货品id列表更新购物车中的数据
		for (int i = 0; i < cartList.size(); i++) 
		{
			if (cartList.get(i).getProductId().equals(newCart.getProductId())) 
			{
				cartList.get(i).setQuantity(newCart.getQuantity());
				break;
			}
		}

		// 保存cookie
		this.saveCookie(cartList, response);
		return cartList;
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
	private void saveCookieToDB(HttpServletRequest request,HttpServletResponse response)throws Exception{
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020122-09");
    	try{
	    	MemberListDo memberListDo=CommonUtil.getMemberListDo(request);
	    	List<Cart> memberCarts=new ArrayList<Cart>();
	    	JSONArray carArray=this.selectCookie(request);
	    	if(null==carArray||carArray.size()==0)return;
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
				cart.setAppointment(cartJSON.getString("appointment"));
				cart.setAppointmentIndex(cartJSON.getInt("appointmentIndex"));
				memberCarts.add(cart);
			}
			ResultObject resultObject = orderService.doService(new RequestObject(headObject, memberCarts));
			if(resultObject.getRetCode().equals(ErrorCode.SUCCESS)){
				CookieUtil.addCookie(response, CART_COOKIE_NAME, null, 0, "/");
			}
    	}catch(Exception e){
    		e.printStackTrace();
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
     * 
     * @Title: findCartSize 
     * @Description: (查询购物车内的货品种类数量) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-24 上午9:16:59 
     * @version 1.0.0 
     * @param request
     * @param response
     * @param @return
     * @param @throws Exception    
     * @return Object    返回类型 
     * @throws
     */
    @RequestMapping("/findCartSize")
    @ResponseBody
	public Object findCartSize(HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		log.info("start[CartController.findCartSize]");
		PamAccount pamAccount = findAccountId(request);
		if(pamAccount!=null&&!"100".equals(pamAccount.getAccountType())){
			return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,isBuyer:false}"));
		}
		Integer accountId = null;
		if(pamAccount!=null){
			accountId = pamAccount.getAccountId();
		}
		ResultObject resultObject = null;
		List<CartListDTO> cartList = null;
		if (accountId != null && accountId != 0 ) 
		{
			// 用户已登录
			// 查看cookie中是否存在商品，存在则加入数据库
			this.saveCookieToDB(request, response);
			// 查询数据库中的购物车商品
			HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020122-01");
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("accountId", accountId);
			resultObject = orderService.doService(new RequestObject(headObject, jsonObject));
			cartList = (List<CartListDTO>) JSONArray.toCollection(JSONArray.fromObject(resultObject.getContent()), CartListDTO.class);
		} else {
			// 用户未登录
			JSONArray array = this.selectCookie(request);// 获取cookie数据
			if (array != null && array.size() != 0) 
			{
				cartList = (List<CartListDTO>) JSONArray.toCollection(array, CartListDTO.class);
				cartList = this.findCartByProIdList(request, cartList);
			}
		}
		cartList = this.findStoresByCartList(request, cartList);
		Integer cartSize = 0;
		if(cartList!=null&&cartList.size()>=1){
			for(int i = 0 ; i < cartList.size() ; i++ ){
				if(
					!"0".equals(cartList.get(i).getgMarketable())
					&& !"1".equals(cartList.get(i).getgDisabled()) 
					&& !"0".equals(cartList.get(i).getpMarketable()) 
					&& !"1".equals(cartList.get(i).getpDisabled())
					&& !"1".equals(cartList.get(i).getShopStatus())
					&& !"1".equals(cartList.get(i).getStatus()) 
					&& !"1".equals(cartList.get(i).getDisabled())
				){
					cartSize=cartSize+cartList.get(i).getQuantity();
				}
			}
		}
		resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONObject.fromObject("{cartSize:"+cartSize+"}"));
		log.info("end[CartController.findCartSize]");
		return resultObject;
	}

/**
     * @Title:  toAddSuccess  
     * @Description:  (成功加入购物车提示页面)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-13 下午6:25:28  
     * @version 1.0.0 
     * @param @param request
     * @param @return
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/toAddSuccess")
	public Object toAddSuccess(HttpServletRequest request) 
    {
    	ModelAndView mv = new ModelAndView();
    	mv.addObject("productId", request.getParameter("productId"));
 		mv.setViewName("pages/biz/cart/addSuccess");
 		return mv;
	}
    
    /**
     * @Title:  findProductFromSameOrder  
     * @Description:  (根据货品id查询同时购买的其他货品)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-29 上午10:53:43  
     * @version 1.0.0 
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/findProductFromSameOrder")
    @ResponseBody
	public Object findProductFromSameOrder(HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		log.info("start[CartController.findProductFromSameOrder]");
		ResultObject resultObject = null;
		String productIdString = request.getParameter("productId");
		if(productIdString != null && !"".equals(productIdString)){
			Integer productId = Integer.parseInt(productIdString);
			HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020107-09", 
					GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("productId", productId);
			jsonObject.put("pageIndex", 1);
			jsonObject.put("pageSize", 9);
			resultObject = orderService.doService(new RequestObject(headObject, jsonObject));
//			List<Integer> productIdList = (List<Integer>) JSONObject.toBean((JSONObject)resultObject.getContent(), ResultPage.class);
			ResultPage<Integer> page = (ResultPage<Integer>) JSONObject.toBean((JSONObject)resultObject.getContent(), ResultPage.class);
			List<ProSimpleDTO> proSimpleDTOList = null;
			if(page!=null&&page.getRows()!=null&&page.getRows().size()>=1){
				List<Integer> productIdList = page.getRows();
				
				headObject = CommonHeadUtil.geneHeadObject(request, "2000020121-09", 
						GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
				resultObject = itemService.doService(new RequestObject(headObject, productIdList));
				List<ProductWithBLOBs> productList = (List<ProductWithBLOBs>) resultObject.getContent();
				if(productList!=null&&productList.size()>=1){
					proSimpleDTOList = new ArrayList<ProSimpleDTO>();
					ProSimpleDTO proSimpleDTO = null;
					List<Picture> pictureList = null;
					GoodsWithBLOBs goods = null;
					Integer count ;
					for(int i=0;i<productList.size();i++){
						proSimpleDTO = new ProSimpleDTO();
						BeanUtils.copyProperties(productList.get(i), proSimpleDTO);
						proSimpleDTO.setProductId(productList.get(i).getProductId().longValue());
						proSimpleDTO.setProductName(productList.get(i).getName());
						//查询图片
						headObject = CommonHeadUtil.geneHeadObject(request, "4000020101-03", 
								GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
						resultObject = itemService.doService(new RequestObject(headObject, productList.get(i).getProductId()));
						if(resultObject.getContent()!=null){
							pictureList = com.alibaba.fastjson.JSONObject.parseArray(resultObject.getContent().toString(), Picture.class);
						}
						if(pictureList!=null&&pictureList.size()>=1){
							proSimpleDTO.setPicturePath(pictureList.get(0).getPicturePath());
						}else{
							headObject = CommonHeadUtil.geneHeadObject(request, "010201-09", 
									GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
							resultObject = itemService.doService(new RequestObject(headObject, productList.get(i).getProductId()));
							goods = (GoodsWithBLOBs) resultObject.getContent();
							proSimpleDTO.setPicturePath(goods.getMidPic());
						}
						//查询评论数量
						headObject = CommonHeadUtil.geneHeadObject(request, "030101-19", 
								GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
						resultObject = memberService.doService(new RequestObject(headObject, productList.get(i).getGoodsId()));
						count = (Integer) resultObject.getContent();
						proSimpleDTO.setCommentCount(count!=null?count:0);
						proSimpleDTOList.add(proSimpleDTO);
						
					}
				}
			}
			jsonObject.clear();
			jsonObject.put("sameOrderProduct", proSimpleDTOList);
			resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS), jsonObject);
		}else{
			resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS), null);
		}
		log.info("end[CartController.findProductFromSameOrder]");
		return resultObject;
	}
    
    /**
     * @description <b>未支付的商品Id</b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015-8-13
     * @return
     * Object
    */
    @ResponseBody
    @RequestMapping("/unPayProduct")
    public Object unPayProduct(HttpServletRequest request){
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020104-12");
    	try{
	    	MemberListDo memberListDo=CommonUtil.getMemberListDo(request);
	    	if(null!=memberListDo){
	    		ResultObject resultObject = orderService.doService(new RequestObject(headObject, Integer.valueOf(memberListDo.getMemberId())));
	    		return resultObject;
	    	}else{
	    		return CommonUtil.notLoign(headObject);
	    	}
    	}catch(Exception e){
    		e.printStackTrace();
    		return CommonUtil.processExpction(headObject);
    	}
    }
    
}
