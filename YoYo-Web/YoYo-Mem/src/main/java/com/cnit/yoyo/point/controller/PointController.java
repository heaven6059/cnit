package com.cnit.yoyo.point.controller;
/**   
 * @Description: 积分
 * @author  余平 yuping@cnit.com 
 * @date 2015-5-5 下午2:13:50 
 * @Copyright 2015 cnit
 * @version V1.0.0 		
 */
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.Member;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.RedisClientUtil;
import com.cnit.yoyo.util.StringUtil;

@Controller
@RequestMapping("/point")
public class PointController {
	
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
	 @RequestMapping("/getPointListPage")
	 public Object getComplainListPage(HttpServletRequest request) throws Exception
	 { 
		 MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
		 request.setAttribute("member", selectMember(memberListDo.getAccountId(), request));
		 return "/pages/biz/point/pointList";
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
    private Member selectMember(Integer accountId,HttpServletRequest request) throws Exception{
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
	  * 
	  * @Description: 积分记录
	  * @param request
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("/getPointList")
	 @ResponseBody
	 public Object getComplainList(HttpServletRequest request) throws Exception{
		 HeadObject headObject =  CommonHeadUtil.geneHeadObject(request, "030107-01");
		 MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
		 ResultObject resultObject = null;
		 if(null!=memberListDo){
			 Integer pageNum =  StringUtil.isEmpty(request.getParameter("pageNum"))?GlobalStatic.DEFAULT_PAGE_INDEX:Integer.parseInt(request.getParameter("pageNum"));
			 Integer pageSize = StringUtil.isEmpty(request.getParameter("pageSize"))?GlobalStatic.DEFAULT_PAGE_SIZE_10:Integer.parseInt(request.getParameter("pageSize"));
			 Map<String,Object> paraData = new HashMap<String, Object>();
			 paraData.put("memberId", memberListDo.getMemberId());
			 paraData.put("pageNum", pageNum);
			 paraData.put("pageSize", pageSize);
			 RequestObject  requestObject  =  new RequestObject(headObject, paraData);
			 resultObject =(ResultObject) memberService.doService(requestObject);
		 }else{
			 headObject.setRetCode(ErrorCode.FAILURE);
			 headObject.setRetMsg("未登录");
			 resultObject=new ResultObject(headObject);			 
		 }
		 return resultObject;
	 }
}

