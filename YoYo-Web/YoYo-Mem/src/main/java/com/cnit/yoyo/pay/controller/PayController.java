package com.cnit.yoyo.pay.controller;
/**   
 * @Description: 支付
 * @author  余平 yuping@cnit.com 
 * @date 2015-4-24 下午5:41:07 
 * @Copyright 2015 
 * @version V1.0.0 		
 */
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.coupon.model.MemberCoupon;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.activity.dto.CouponsDTO;
import com.cnit.yoyo.model.painting.PaintingOrders;
import com.cnit.yoyo.order.model.Order;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.StringUtil;

@Controller
@RequestMapping("/pay")
public class PayController {

    public static final Logger logger = LoggerFactory.getLogger(PayController.class);

	@Autowired
    private RemoteService orderService;
    @Autowired
    private RemoteService otherService;
    @Autowired
	private RemoteService itemService;
    
    /**
     * 
     * @Description: 立即支付
     * @param request
     * @param orderid
     * @return
     */
    @RequestMapping("/payOrder")
    public String payOrder(HttpServletRequest request,@RequestParam(value="orderId",required=true)Long orderId,String addon){
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020104-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        try {
        	Map<String, Object> dataMap=new HashMap<String, Object>();
        	dataMap.put("orderId", orderId);
            resultObject = orderService.doService(new RequestObject(headObject, dataMap));
            Order order=(Order) resultObject.getContent();
            request.setAttribute("order", resultObject.getContent());
            if(StringUtil.isNotEmpty(addon)&&!order.getAddon().equals(addon)){
            	request.setAttribute("href", request.getHeader("Referer"));
                request.setAttribute("errorMsg", "订单信息已改变请刷新订单信息后再试");
            	return "pages/memError";
            }
        } catch (Exception e) {
            e.printStackTrace();            
        }
        return "pages/biz/pay/payOrder";
    }
    /**
     * 
     * @Description: 立即支付
     * @param request
     * @param orderid
     * @return
     */
    @RequestMapping("/payOrderCoupon")
    public String payOrderCoupon(HttpServletRequest request,Integer cpnsId){
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030115-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		CouponsDTO coupon = null;
		try {
			HeadObject couponObject = CommonHeadUtil.geneHeadObject(request, "2000030101-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
			ResultObject couponResult = itemService.doService(new RequestObject(couponObject, cpnsId));
			coupon = (CouponsDTO) couponResult.getContent();
			MemberCoupon memberCoupon = new MemberCoupon();
			HttpSession session = CommonUtil.getSession(request);
			Integer memberId = Integer.parseInt(session.getAttribute("memberId").toString());
			memberCoupon.setMemberId(memberId);
			memberCoupon.setDisabled(0);
			memberCoupon.setCpnsId(coupon.getCpnsId());
			itemService.doService(new RequestObject(headObject, coupon));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("orderId", coupon.getCpnsId());
		return "pages/biz/pay/payOrderCoupon";
    }
   
    /**
     * 钣金在线支付
     * @param request
     * @return
     */
    @RequestMapping("/payPaintingOrder")
    public String payPaintingOrder(HttpServletRequest request, Long orderId){
        logger.info(">>>>>>>>>>>>>>>>>>>PayController.payPaintingOrder start>>>>>>>>>>>>>>>>>>>");
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990201-13", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        try {
            resultObject = otherService.doService(new RequestObject(headObject, orderId));
            PaintingOrders pOrder = (PaintingOrders)resultObject.getContent();
            Order order = new Order();
            order.setOrderId(pOrder.getId());
            order.setFinalAmount(pOrder.getPayed());
            request.setAttribute("order", order);
        } catch (Exception e) {
        	logger.equals(e.toString());
            e.printStackTrace();
        }
        logger.info(">>>>>>>>>>>>>>>>>>>PayController.payPaintingOrder end>>>>>>>>>>>>>>>>>>>");
        return "pages/biz/pay/payOrder";
    }
}
