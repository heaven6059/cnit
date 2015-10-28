package com.cnit.yoyo.goodsvirtualitems.controller;
/**   
 * @Description: 购买的虚拟商品
 * @author  余平 yuping@cnit.com 
 * @date 2015-5-5 下午2:13:50 
 * @Copyright 2015 cnit
 * @version V1.0.0 		
 */
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.RedisClientUtil;
import com.cnit.yoyo.util.StringUtil;

@Controller
@RequestMapping("/goodsVirtualItems")
public class GoodsVirtualItemsController {
	
	 @Autowired
	 private RemoteService  memberService ;
	 
	 @Autowired
	 private RedisClientUtil redisService;//redis缓存服务
	 
	 /**
	  * 
	  * @Description: 获取投诉管理列表
	  * @param request
	  * @param userId
	  * @return
	 * @throws Exception 
	  */
	 @RequestMapping("/getGoodsVirtualItemsListPage")
	 public Object getGoodsVirtualItemsListPage() throws Exception
	 { 
		
		 return "/pages/biz/goodsvirtualitems/goodsVirtualItemsList";
	 }
	 
	 /**
	  * 
	  * @Description: 积分记录
	  * @param request
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("/getGoodsVirtualItemsList")
	 @ResponseBody
	 public Object getGoodsVirtualItemsList(HttpServletRequest request  ) throws Exception
	 { 
		 HeadObject headObject =  CommonHeadUtil.geneHeadObject(request, "030110-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK) ;
		 //MemberListDo memberDo = (MemberListDo)(CommonUtil.getSession(request).getAttribute(GlobalStatic.USER_INFO));
		 //String memberId = memberDo.getMemberId();
		 String memberId = "62";
		 String num =  request.getParameter("pageNum");
		 String size = request.getParameter("pageSize");
		 int pageNum = StringUtil.isEmpty(num) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.valueOf(num);
		 int pageSize = StringUtil.isEmpty(size) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.valueOf(size); 
		 Map<String,Object> paraData = new HashMap<String, Object>();
		 paraData.put("memberId", memberId);
		 paraData.put("pageNum", pageNum);
		 paraData.put("pageSize", pageSize);
		 RequestObject  requestObject  =  new RequestObject(headObject, paraData);
		 ResultObject resultObject =(ResultObject) memberService.doService(requestObject);
		 return resultObject.getContent();
	 }

}

