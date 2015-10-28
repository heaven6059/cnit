package com.cnit.yoyo.rmi.cart.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.OrderConstant;
import com.cnit.yoyo.constant.OrderConstant.OrderLogBehavior;
import com.cnit.yoyo.coupon.model.dto.MemberCouponDTO;
import com.cnit.yoyo.coupon.model.dto.MemberCouponQryDTO;
import com.cnit.yoyo.dao.cart.CartMapper;
import com.cnit.yoyo.dao.order.OrderItemsMapper;
import com.cnit.yoyo.dao.order.OrderMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.cart.Cart;
import com.cnit.yoyo.model.cart.CartExample;
import com.cnit.yoyo.model.cart.dto.CartDTO;
import com.cnit.yoyo.model.cart.dto.CartListDTO;
import com.cnit.yoyo.model.goods.dto.AccessoryGoodsDTO;
import com.cnit.yoyo.model.member.Member;
import com.cnit.yoyo.model.order.Order;
import com.cnit.yoyo.model.order.OrderItems;
import com.cnit.yoyo.model.order.OrderLog;
import com.cnit.yoyo.model.sales.activity.dto.ScoreBuyApplyDTO;
import com.cnit.yoyo.point.model.MemberPoint;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.rmi.order.service.OrderLogService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.DateUtils;

/**
 * @description 购物车业务层
 * @detail <功能详细描述>
 * @author <a href="cmlai@chinacnit.com">赖彩妙</a>
 * @version 1.0.0
 */
@Service("cartService")
@SuppressWarnings("unchecked")
public class CartService 
{

	public static final Logger log = LoggerFactory.getLogger(CartService.class);

    @Autowired
    private CartMapper cartMapper;
    
    @Autowired
    private OrderMapper orderMapper;
    
    @Autowired
    private OrderItemsMapper orderItemsMapper;

    @Autowired
    public RemoteService memberService;
    
    @Autowired
    private OrderLogService orderLogService;

	private Map<String, Object> object;
    
    /**
     * @Title: findCartListByAccountId 
     * @Description: (根据accountId查询购物车数据) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-10
     * @version 1.0.0 
     * @param data
     * @param @return    
     * @return Object    返回类型 
     * @throws
     */
	public Object findCartListByAccountId(Object data) 
	{
		log.info("start[CartService.findCartListByAccountId]");
		JSONObject jsonObject = JSONObject.fromObject(data);
		Integer accountId = jsonObject.getInt("accountId");
		List<CartListDTO> cartListDTOList = cartMapper.selectCartListByAccountId(accountId);
		if (cartListDTOList != null && cartListDTOList.size() >= 1) 
		{
			List<Integer> productIdList = new ArrayList<Integer>();
			for (int i = cartListDTOList.size()-1; i >= 0; i--) 
			{
				if(productIdList.contains(cartListDTOList.get(i).getProductId())){
					List<Integer> tempProductIdList = new ArrayList<Integer>();
					tempProductIdList.add(cartListDTOList.get(i).getProductId());
					cartMapper.deleteByPrimaryKey(cartListDTOList.get(i).getCartId());
					cartListDTOList.remove(i);
				}else{
					productIdList.add(cartListDTOList.get(i).getProductId());
					Integer store=null==cartListDTOList.get(i).getStore()?0:cartListDTOList.get(i).getStore();
					if (store < cartListDTOList.get(i).getQuantity()) 
					{
						cartListDTOList.get(i).setQuantity(store);
						cartMapper.updateQuantityByProAndMem(cartListDTOList.get(i));
					}
					String specDesc = cartListDTOList.get(i).getSpecDesc();
	    			if(specDesc!=null&&!"".equals(specDesc.trim())){
	    				String[] specArray = specDesc.split(",");
	    				if(specArray!=null&&specArray.length>=1){
	    					for(int j=specArray.length-1; j>=0 ;j--){
			    				if(specArray[j].split(":")[0].split("\\|")[1].equals("分店")){
			    					cartListDTOList.get(i).setStoreId(Integer.parseInt(specArray[j].split(":")[1].split("\\|")[0]));
			    					cartListDTOList.get(i).setStoreName(specArray[j].split(":")[1].split("\\|")[1]);
			    					break;
			    				}
			    			}
	    				}
	    			}
				}
			}
		}
		log.info("end[CartService.findCartListByAccountId]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), cartListDTOList);
	}
    
	/**
	 * @Title: updateQuantityByProAndMem 
	 * @Description: (根据货品id和memberId更新购物车商品的购买数量) 
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-4-14
	 * @version 1.0.0 
	 * @param data
	 * @param @return    
	 * @return Object    返回类型 
	 * @throws
	 */
	public Object updateQuantityByProAndMem(Object data) 
	{
		log.info("start[CartService.updateQuantityByCartId]");
		JSONObject jsonObject = JSONObject.fromObject(data);
		Cart cart = (Cart) JSONObject.toBean(jsonObject, Cart.class);
		int result = cartMapper.updateQuantityByProAndMem(cart);
		log.info("end[CartService.updateQuantityByCartId]");
		return result == 1 ? new HeadObject(ErrorCode.SUCCESS) : new HeadObject(ErrorCode.FAILURE);
	}
    
	/**
	 * @Title: saveCart 
	 * @Description: (保存购物车数据) 
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-4-11
	 * @version 1.0.0 
	 * @param data
	 * @param @return    
	 * @return Object    返回类型 
	 * @throws
	 */
	public Object saveCart(Object data) 
	{
		log.info("start[CartService.insertCart]");
		JSONObject jsonObject = JSONObject.fromObject(data);
		String specValueId = jsonObject.get("specValueId").toString();
		Cart cart = (Cart) JSONObject.toBean(JSONObject.fromObject(jsonObject.get("newCart")), Cart.class);
		cart.setSpecValueId(specValueId);
		int result = cartMapper.insertSelective(cart);
		log.info("end[CartService.insertCart]");
		return result == 1 ? new HeadObject(ErrorCode.SUCCESS) : new HeadObject(ErrorCode.FAILURE);
	}
    
	/**
	 * @Title: findCartListByProIdList 
	 * @Description: (根据货品id查询购物车商品列表) 
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-4-10
	 * @version 1.0.0 
	 * @param data
	 * @param @return    
	 * @return Object    返回类型 
	 * @throws
	 */
	public Object findCartListByProIdList(Object data) 
	{
		log.info("start[CartService.selectCartListByProIdList]");
		List<Integer> proIdList = (List<Integer>) JSONArray.toCollection(JSONArray.fromObject(data), Integer.class);
		Map<String, Object> param=new HashMap<String,Object>();
		param.put("proIdList", proIdList);
		List<CartListDTO> cartListDTOList = cartMapper.selectCartListByProIdList(param);
		log.info("end[CartService.selectCartListByProIdList]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONArray.fromObject(cartListDTOList));
	}
    
	/**
	 * @Title: findCartListByProIdList 
	 * @Description: (根据货品id查询购物车商品列表) 
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-4-10
	 * @version 1.0.0 
	 * @param data
	 * @param @return    
	 * @return Object    返回类型 
	 * @throws
	 */
	public Object findMemberCartListByProIdList(Object data) 
	{
		log.info("start[CartService.selectCartListByProIdList]");
		Map<String, Object> param=(Map<String, Object>) data;
		List<CartListDTO> cartListDTOList = cartMapper.selectCartListByProIdList(param);
		log.info("end[CartService.selectCartListByProIdList]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONArray.fromObject(cartListDTOList));
	}
	
	/**
	 * @Title: findCartListByProIdAndMem 
	 * @Description: (根据货品id和memberId查询购物车商品列表) 
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-4-9
	 * @version 1.0.0 
	 * @param data
	 * @param @return    
	 * @return Object    返回类型 
	 * @throws
	 */
	public Object findCartListByProIdAndMem(Object data) 
	{
		log.info("start[CartService.selectCartListByProIdAndMem]");
		JSONObject jsonObject = JSONObject.fromObject(data);
		Integer memberId = jsonObject.getInt("memberId");
		List<Integer> selectProIdList = (List<Integer>) JSONArray.toCollection(JSONArray.fromObject(jsonObject.get("proIdList")), Integer.class);
		List<CartListDTO> cartListDTOList = cartMapper.selectCartListByProIdAndMem(selectProIdList, memberId);
		if(cartListDTOList!=null&&cartListDTOList.size()>=1){
			for(int i =0;i<cartListDTOList.size();i++){
				String specDesc = cartListDTOList.get(i).getSpecDesc();
    			if(specDesc!=null&&!"".equals(specDesc.trim())){
    				String[] specArray = specDesc.split(",");
    				if(specArray!=null&&specArray.length>=1){
    					for(int j=specArray.length-1; j>=0 ;j--){
		    				if(specArray[j].split(":")[0].split("\\|")[1].equals("分店")){
		    					cartListDTOList.get(i).setStoreId(Integer.parseInt(specArray[j].split(":")[1].split("\\|")[0]));
		    					cartListDTOList.get(i).setStoreName(specArray[j].split(":")[1].split("\\|")[1]);
		    					break;
		    				}
		    			}
    				}
    			}
			}
		}
		log.info("end[CartService.selectCartListByProIdAndMem]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONArray.fromObject(cartListDTOList));
	}
    
	/**
	 * @Title: deleteCartByProIdAndMemberId 
	 * @Description: (根据买家id和货品id列表删除购物车商品数据) 
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-4-13
	 * @version 1.0.0 
	 * @param data
	 * @param @return    
	 * @return Object    返回类型 
	 * @throws
	 */
	public Object deleteCartByProIdAndMemberId(Object data) 
	{
		log.info("start[CartService.deleteCartByProIdAndMemberId]");
		JSONObject jsonObject = JSONObject.fromObject(data);
		Integer memberId = jsonObject.getInt("memberId");
		List<Integer> proIdList = (List<Integer>) jsonObject.get("proIdList");
		cartMapper.delectCartByProIdAndMemberId(memberId, proIdList);
		log.info("end[CartService.deleteCartByProIdAndMemberId]");
		return new HeadObject(ErrorCode.SUCCESS);
	}
    
	/**
	 * @Title: saveCartList 
	 * @Description: (批量保存购物车数据) 
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-4-16
	 * @version 1.0.0 
	 * @param data
	 * @param @return    
	 * @return Object    返回类型 
	 * @throws
	 */
	public Object saveCartList(Object data) 
	{
		log.info("start[CartService.saveCartList]");
		JSONObject jsonObject = JSONObject.fromObject(data);
		Integer memberId = jsonObject.getInt("memberId");
		List<CartListDTO> cartList = (List<CartListDTO>) JSONArray .toCollection(JSONArray.fromObject(jsonObject.get("cartList")), CartListDTO.class);
		// 查询数据库购物车中是否存在同一货品
		if (cartList != null && cartList.size() >= 1) 
		{
			List<Integer> proIdList = new ArrayList<Integer>();
			for (int i = 0; i < cartList.size(); i++) 
			{
				cartList.get(i).setMemberId(memberId);
				proIdList.add(cartList.get(i).getProductId());
			}
			if (proIdList != null && proIdList.size() >= 1) 
			{
				List<CartListDTO> cartListFromDB = cartMapper.selectCartListByProIdAndMem(proIdList, memberId);
				if (cartListFromDB != null && cartListFromDB.size() >= 1) 
				{
					int quantity = 0;
					// 更新已存在货品的购买数量
					for (int i = 0; i < cartListFromDB.size(); i++) 
					{
						for (int j = cartList.size() - 1; j >= 0; j--) 
						{
							if (cartListFromDB.get(i).getProductId().equals(cartList.get(j).getProductId())) 
							{
								quantity = cartListFromDB.get(i).getQuantity() + cartList.get(j).getQuantity();
								cartListFromDB.get(i).setQuantity(quantity > cartListFromDB.
										get(i).getStore() ? cartListFromDB.get(i).getStore() : quantity);
								cartMapper.updateQuantityByProAndMem(cartListFromDB.get(i));
								cartList.remove(j);
							}
						}
					}
				}
			}
			// 插入数据库购物车中不存在的货品
			if (cartList != null && cartList.size() >= 1) 
			{
				Integer maxCartSize = jsonObject.getInt("maxCartSize");
				// 根据买家id查询购物车中的货品数量
				CartExample cartExample = new CartExample();
				cartExample.createCriteria().andMemberIdEqualTo(memberId);
				int cartCount = cartMapper.countByExample(cartExample);
				if (maxCartSize > cartCount) 
				{
					for (int i = 0; i < cartList.size() && i < maxCartSize - cartCount; i++) 
					{
						cartMapper.insertSelective(cartList.get(i));
					}
				}
			}
		}
		log.info("end[CartService.saveCartList]");
		return new HeadObject(ErrorCode.SUCCESS);
	}
    
	/**
	 * @Title: saveOrder 
	 * @Description: (保存订单数据) 
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-4-18
	 * @version 1.0.0 
	 * @param data
	 * @param @return    
	 * @return Object    返回类型 
	 * @throws
	 */
	public Object saveOrder(Object data) 
	{
		log.info("start[CartService.saveOrder]");
		String tempOrderId=null;
		JSONObject jsonObject = JSONObject.fromObject(data);
		List<Long> coupons=(List<Long>)jsonObject.get("coupons");
		Member member = (Member) JSONObject.toBean(jsonObject.getJSONObject("member"), Member.class);
		ScoreBuyApplyDTO scoreBuyApply = null;
		if(jsonObject.containsKey("scoreBuyApply")){
			scoreBuyApply = (ScoreBuyApplyDTO)JSONObject.toBean(jsonObject.getJSONObject("scoreBuyApply"), ScoreBuyApplyDTO.class);
		}
		List<MemberCouponDTO> memberCoupons = null;
		if(null!=coupons&&coupons.size()>0){
			HeadObject couponHead=CommonHeadUtil.geneHeadObject("memberCouponService.selectMemberStoreCoupons");
			MemberCouponQryDTO memberCouponQry=new MemberCouponQryDTO();
			memberCouponQry.setMemcCodes(coupons);
			memberCouponQry.setMemberId(Long.valueOf(member.getMemberId()));
			ResultObject resultObject = (ResultObject) memberService.doServiceByServer(new RequestObject(couponHead, memberCouponQry));
			memberCoupons = (List<MemberCouponDTO>) resultObject.getContent();
		}
		// 将商品按店铺分装
		List<CartListDTO> cartList = (List<CartListDTO>) JSONArray.toCollection(JSONArray.fromObject(jsonObject.get("cartList")), CartListDTO.class);
		Map<Integer, List<CartListDTO>> cartMap = new HashMap<Integer, List<CartListDTO>>();
		List<CartListDTO> tempCartList = null;
		for (int j = 0; j < cartList.size(); j++) 
		{
			tempCartList = cartMap.get(cartList.get(j).getStoreId());
			if(tempCartList==null){
				tempCartList = new ArrayList<CartListDTO>();
			}
			tempCartList.add(cartList.get(j));
			cartMap.put(cartList.get(j).getStoreId(), tempCartList);
		}
		String payment = jsonObject.getString("payment");
		String checkFrom = jsonObject.getString("checkFrom");
		String ip = jsonObject.getString("ip");
		Object agObject = jsonObject.get("accessoryGoods");
		String remark = jsonObject.getString("remark");
		BigDecimal point = new BigDecimal(jsonObject.getInt("point"));
		AccessoryGoodsDTO ag = null;
		if(agObject!=null){
			ag = (AccessoryGoodsDTO)JSONObject.toBean(JSONObject.fromObject(agObject), AccessoryGoodsDTO.class);
		}
		Random random = new Random();
		// 保存订单
		if (cartMap != null && cartMap.size() >= 1) 
		{
			BigDecimal amount;
			Integer weight = 0;
			Double cost = (double)0;
			Date now = new Date(System.currentTimeMillis());
			List<OrderItems> orderItemList = null;
			String orderId = null ;
			int randomInt = 0;
			String dateString = DateUtils.dateToString(new Date(), "yyMMdd");
			String checkFromString;
			if ("appAndroid".equals(checkFrom)) {
				checkFromString = "20";
			} else if ("appIOS".equals(checkFrom)) {
				checkFromString = "21";
			} else {
				checkFromString = "10";
			}
			orderId = cartList.get(0).getIdentifier()+ checkFromString + dateString;
			Order orderFromDB;
			String parentOrderId = cartList.get(0).getIdentifier() + checkFromString + dateString;
			int i = 0;
			do {
				randomInt = random.nextInt(9000) + 1000;
				orderFromDB = orderMapper.selectByPrimaryKey(Long.parseLong(parentOrderId + randomInt));
				i++;
			}while(orderFromDB != null && i<15000);
			if(orderFromDB != null){
				return new HeadObject(ErrorCode.SUCCESS,"今日订单量已达上限");
			}
			Order parentOrder = new Order();
			parentOrder.setOrderId(Long.parseLong(parentOrderId + randomInt));
			parentOrder.setMemberId(cartList.get(0).getMemberId());
			parentOrder.setPayStatus("0");
			parentOrder.setCreatetime(now);
			parentOrder.setLastModified(now);
			parentOrder.setPayment(payment);
			if(OrderConstant.PaymentType.LOCALE_PAY.getValue().equals(payment)){
				parentOrder.setPaymentId(OrderConstant.PaymentType.LOCALE_PAY.getKey());
				parentOrder.setIsDisplay(0);
			}else{
				parentOrder.setPaymentId(OrderConstant.PaymentType.ON_LINE_PAY.getKey());
				parentOrder.setIsDisplay(1);
			}
//			parentOrder.setStatus("active");
			parentOrder.setStatus(OrderConstant.OrderStatus.CREATE.getKey());
			parentOrder.setIp(ip);
			parentOrder.setOrderRefer("web".equals(checkFrom)?"pc":"wap");
			parentOrder.setSource("web".equals(checkFrom)?"pc":"wap");
			parentOrder.setCostTax(BigDecimal.valueOf(0));
			parentOrder.setCostFreight(BigDecimal.valueOf(0));
			parentOrder.setExtend("");
			parentOrder.setActId(0);
			parentOrder.setTotalAmount(new BigDecimal(0));
			parentOrder.setFinalAmount(new BigDecimal(0));			
			parentOrder.setCostItem(new BigDecimal(0));
			parentOrder.setDiscountValue(new BigDecimal(0));
			parentOrder.setItemnum(0);
			parentOrder.setWeight(new BigDecimal(0));
			parentOrder.setCostItem(new BigDecimal(0));
			parentOrder.setMemo(remark);
			tempOrderId=parentOrder.getOrderId().toString();
			for (Entry<Integer, List<CartListDTO>> entry : cartMap.entrySet()) 
			{
				amount = BigDecimal.ZERO;
				weight = 0;
				cost = (double)0;
				Order order = new Order();
				i = 0;
				do {
					randomInt = random.nextInt(9000) + 1000;
					orderFromDB = orderMapper.selectByPrimaryKey(Long.parseLong(orderId + randomInt));
					i++;
				}while(orderFromDB != null && !orderFromDB.getOrderId().equals(parentOrder.getOrderId()) && i<15000);
				if(orderFromDB != null){
					return new HeadObject(ErrorCode.FAILURE,"今日订单量已达上限");
				}
//				System.out.println("service...orderId..."+(orderId + randomInt));
				BeanUtils.copyProperties(parentOrder, order);
				order.setOrderId(Long.parseLong(orderId + randomInt));
				order.setCompanyId(entry.getValue().get(0).getCompanyId().longValue());
				order.setStoreId(entry.getValue().get(0).getStoreId().longValue());
				order.setIsDisplay(1-parentOrder.getIsDisplay());
				orderItemList = new ArrayList<OrderItems>();
				for (CartListDTO c : entry.getValue()) 
				{
					OrderItems orderItems = new OrderItems();
					orderItems.setProductId(c.getProductId());
					orderItems.setBn(c.getBn());
					orderItems.setName(c.getProductName());
					orderItems.setPrice(new BigDecimal(c.getGoodsPrice()));
					orderItems.setgPric(new BigDecimal(c.getGoodsPrice()));
					orderItems.setCost(c.getCost());
					orderItems.setAmount(new BigDecimal(c.getQuantity()* c.getGoodsPrice()));
					orderItems.setWeight(c.getWeight() != null ? c.getWeight().intValue() : null);
					orderItems.setNums(c.getQuantity());
					orderItems.setSendnum(c.getQuantity());
					orderItems.setAddon(c.getSpecInfo());
					if(null==scoreBuyApply){
						orderItems.setItemType(c.getGoodsType());
					}else{
						orderItems.setItemType(OrderConstant.OrderType.SCORE_BUY.getKey());
					}
					orderItems.setAppointment(c.getAppointment());
					orderItemList.add(orderItems);
					amount = new BigDecimal(c.getQuantity() * c.getGoodsPrice()).add(amount);
					if(c.getWeight()!=null&&weight!=null){
						weight += c.getWeight().intValue();
					}else{
						weight = null;
					}
					if(c.getCost()!=null&&cost!=null){
						cost += c.getCost().doubleValue();
					}else{
						cost = null;
					}
				}
				BigDecimal discount = BigDecimal.ZERO;
				if(null!=ag){
					if("minus".equals(ag.getDiscType())){
						discount = ag.getCredit();
					}else if("discount".equals(ag.getDiscType())){
						discount = amount.multiply(BigDecimal.ONE.subtract(ag.getCredit()));
					}
					if(discount.compareTo(BigDecimal.ZERO)==-1){
						discount = BigDecimal.ZERO;
					}
					if(discount.compareTo(amount)>0){
						discount = amount;
					}
				}
				order.setTotalAmount(amount);
				order.setFinalAmount(amount.subtract(discount).subtract((point.divide(new BigDecimal(10)))));
				order.setCostItem(amount);
				order.setDiscountValue(discount.add(point.divide(new BigDecimal(10))));
				order.setPayed(amount.subtract(discount).subtract(point.divide(new BigDecimal(10))));
				order.setItemnum(orderItemList.size());
				order.setWeight(weight!=null?new BigDecimal(weight):null);
				order.setScoreU(point);				
				
				order.setScoreG(order.getFinalAmount().divide(new BigDecimal(10)));
				order.setCostItem(cost!=null?new BigDecimal(cost):null);
				order.setParentOrderId(parentOrder.getOrderId());
				order.setItemnum(orderItemList.size());
				//计算使用优惠卷价格开始
				if(null!=memberCoupons&&memberCoupons.size()>0){
					for (MemberCouponDTO memberCoupon : memberCoupons) {
						BigDecimal couponPrice=BigDecimal.ZERO;
						if(memberCoupon.getCoupons().getStoreId().equals(order.getStoreId())){
							if("promotion_conditions_order_subtotalallgoods".equals(memberCoupon.getSalesRuleGoods().getcTemplate())&&new BigDecimal(memberCoupon.getSalesRuleGoods().getConditions()).compareTo(order.getTotalAmount())==1){
								//总价
								return new ResultObject(new HeadObject(ErrorCode.FAILURE,"商品总价未达到该优惠卷的使用条件！"));
							}
							if("promotion_conditions_order_itemsquanityallgoods".equals(memberCoupon.getSalesRuleGoods().getsTemplate())&&Integer.parseInt(memberCoupon.getSalesRuleGoods().getConditions())>order.getItemnum()){
								//总数量
								return new ResultObject(new HeadObject(ErrorCode.FAILURE,"商品总数未达到该优惠卷的使用条件！"));
							}
							if("promotion_solutions_topercent".equals(memberCoupon.getSalesRuleGoods().getsTemplate())){
								//价格乘以X%折扣出售
								couponPrice=order.getFinalAmount().multiply(new BigDecimal(memberCoupon.getSalesRuleGoods().getActionSolution()).divide(new BigDecimal(100)));
								order.setDiscountValue(order.getDiscountValue().add(order.getFinalAmount().subtract(couponPrice)));
								order.setFinalAmount(couponPrice);
							}else if("promotion_solutions_bypercent".equals(memberCoupon.getSalesRuleGoods().getsTemplate())){
								//价格减去X%折扣出售
								couponPrice=order.getFinalAmount().subtract(order.getFinalAmount().multiply(new BigDecimal(memberCoupon.getSalesRuleGoods().getActionSolution()).divide(new BigDecimal(100))));
								order.setDiscountValue(order.getDiscountValue().add(order.getFinalAmount().subtract(couponPrice)));
								order.setFinalAmount(couponPrice);
							}else if("promotion_solutions_byfixed".equals(memberCoupon.getSalesRuleGoods().getsTemplate())){
								//价格优惠X出售
								couponPrice=order.getFinalAmount().subtract(new BigDecimal(memberCoupon.getSalesRuleGoods().getActionSolution()));
								order.setDiscountValue(order.getDiscountValue().add(order.getFinalAmount().subtract(couponPrice)));
								order.setFinalAmount(couponPrice);
							}
							order.setIsCoupons(1);
							memberCoupon.setDisabled(1);
							memberCoupon.setMemcGenOrderid(order.getOrderId());
						}
					}
				}
				//计算使用优惠卷价格结束
				//判断最终价格是否大于零，小于零则默认为0
				if(order.getFinalAmount().compareTo(BigDecimal.ZERO)==-1){
					order.setFinalAmount(BigDecimal.ZERO);
				}
				
				parentOrder.setItemnum(orderItemList.size());
				parentOrder.setTotalAmount(parentOrder.getTotalAmount().add(order.getTotalAmount()));
				parentOrder.setFinalAmount(parentOrder.getFinalAmount().add(order.getFinalAmount()));
				parentOrder.setScoreU(point);
				parentOrder.setScoreG(parentOrder.getFinalAmount().divide(new BigDecimal(10)));
				parentOrder.setCostItem(parentOrder.getCostItem().add(order.getCostItem()));
				parentOrder.setDiscountValue(parentOrder.getDiscountValue().add(order.getDiscountValue()));
				parentOrder.setItemnum(parentOrder.getItemnum()+orderItemList.size());
				parentOrder.setWeight(order.getWeight()!=null?new BigDecimal(parentOrder.getWeight().doubleValue()+order.getWeight().doubleValue()):null);
				parentOrder.setCostItem(order.getCostItem()!=null?new BigDecimal(parentOrder.getCostItem().doubleValue()+order.getCostItem().doubleValue()):null);
				parentOrder.setPayed(parentOrder.getFinalAmount().add(order.getFinalAmount()));
				
				if(cartMap.size()<2){
					order.setIsDisplay(1);
					order.setParentOrderId(order.getOrderId());
					order.setOrderKind("1");
					order.setAddon(CommonUtil.getRandomChar());
					orderMapper.insertSelective(order);
					tempOrderId=order.getOrderId().toString();
				}else{
					order.setOrderKind("2");
					order.setAddon(CommonUtil.getRandomChar());
					orderMapper.insertSelective(order);
				}
				
				this.addOrderLog(order.getOrderId(), order.getMemberId(), jsonObject.getString("memberName"), "买家提交订单,等待付款");
				for (OrderItems orderItems : orderItemList) 
				{
					orderItems.setOrderId(order.getOrderId());
					orderItemsMapper.insertSelective(orderItems);
				}
			}
			if(cartMap.size()>=2){
				parentOrder.setOrderKind("1");
				parentOrder.setAddon(CommonUtil.getRandomChar());
				orderMapper.insertSelective(parentOrder);
			}
			if(null!=memberCoupons&&memberCoupons.size()>0){
				HeadObject couponsHead=CommonHeadUtil.geneHeadObject("memberCouponService.updateMemberCoupon");
				memberService.doServiceByServer(new RequestObject(couponsHead, memberCoupons));				
			}
			//使用了积分修改用户积分以及写入积分使用日志
			if(null!=scoreBuyApply){
				parentOrder.setScoreU(parentOrder.getScoreU().add(new BigDecimal(scoreBuyApply.getScore())));
			}
			if(parentOrder.getScoreU().compareTo(BigDecimal.ZERO)==1){				
				HeadObject memberHead=CommonHeadUtil.geneHeadObject("memberService.modifyMember");
				member.setPointSummation(member.getPointSummation().add(parentOrder.getScoreG()));
				member.setPointUseable(member.getPointUseable().add(parentOrder.getScoreG()).subtract(parentOrder.getScoreU()));
				member.setPointUsed(member.getPointUsed().add(parentOrder.getScoreU()));
				memberService.doServiceByServer(new RequestObject(memberHead, JSONObject.fromObject(member)));
				
				MemberPoint memberPoint=new MemberPoint();
				memberPoint.setMemberId(member.getMemberId());
				memberPoint.setOutPoint(parentOrder.getScoreU().intValue());
				memberPoint.setRemainPoint(member.getPointUseable().intValue());			
				memberPoint.setCreatetime(new Date());
				memberPoint.setRemark("使用积分抵扣支付订单："+parentOrder.getOrderId());
				//写入支付积分
				HeadObject pointInHead=CommonHeadUtil.geneHeadObject("pointService.saveMemberPoint");
				memberService.doServiceByServer(new RequestObject(pointInHead, memberPoint));
			}
		}else{
			return new ResultObject(new HeadObject(ErrorCode.FAILURE));
		}
		log.info("end[CartService.saveOrder]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), tempOrderId);
	}
	
	public void addOrderLog(Long orderId,Integer opId,String opName,String logText){
		OrderLog orderLog=new OrderLog();
		orderLog.setOrderId(orderId);
		orderLog.setBillType("order");
		orderLog.setOpId(opId);
		orderLog.setOpName(opName);
		orderLog.setLogText(logText);
		orderLog.setBehavior(OrderLogBehavior.CREATE.getKey());
		orderLogService.addOrderLog(orderLog);	   
	}
	
	/**
	 * @description <b>加入购物车</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015年8月2日
	 * @param object
	 * @return
	 * Object
	*/
	public Object addToCart(Object object){
		HeadObject headObject=new HeadObject();
		try{
			List<Cart> carts=(List<Cart>) object;		
			for (Cart cart : carts) {
				Cart oldCart=cartMapper.selectHasAddCart(cart);
				if(null!=oldCart){
					oldCart.setQuantity(oldCart.getQuantity()+cart.getQuantity());
					oldCart.setAppointment(cart.getAppointment());
					oldCart.setAppointmentIndex(cart.getAppointmentIndex());
					cartMapper.updateByPrimaryKeySelective(oldCart);
				}else{
					cartMapper.insert(cart);
				}
			}
			headObject.setRetCode(ErrorCode.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			headObject.setRetCode(ErrorCode.FAILURE);
		}
		return headObject;
	}
	
	public Object selectGoodsByProductIds(Object object){
		HeadObject headObject=new HeadObject();
		List<CartDTO> list = null;
		try{
			list = cartMapper.selectGoodsByProductIds((Map<String, Object>) object);
			headObject.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
			headObject.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(headObject, list);
	}

	public Object selectMemberCart(Object object){
		HeadObject headObject=new HeadObject();
		List<CartDTO> list = null;
		try{
			list = cartMapper.selectMemberCart((Map<String, Object>) object);
			headObject.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
			headObject.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(headObject, list);
	}
}
