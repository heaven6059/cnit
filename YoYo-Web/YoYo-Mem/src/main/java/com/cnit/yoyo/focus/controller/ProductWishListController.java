package com.cnit.yoyo.focus.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.goods.ProductWithBLOBs;
import com.cnit.yoyo.model.member.Member;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.model.member.PamAccount;
import com.cnit.yoyo.model.member.ProductWishList;
import com.cnit.yoyo.model.order.dto.OrderQryDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;

/**  
* @Title: ProductWishListController.java
* @Package com.cnit.yoyo.wishlist.controller
* @Description: 商品收藏 Controller
* @Author 王鹏
* @date 2015-4-30 上午10:40:50
* @version V1.0  
*/ 
@Controller
@RequestMapping("/productWishList")
public class ProductWishListController {
	 @Autowired
	 private RemoteService  memberService ;

	 @Autowired
	 public RemoteService itemService;
	 @RequestMapping("/toWishList")
	 public String toWishList(){
		 return "pages/biz/wishlist/productWishList";
	 }
	 
   /**
     * @description <b>获取关注的商品信息</b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015-4-21
     * @param @param request
     * @param @return
     * @return Object
   */
   @RequestMapping("/loadProductWishList")
   public Object loadProductWishList(HttpServletRequest request,OrderQryDTO dto){
   	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030201-01");
       //TODO 从session中获取 店铺id
       JSONObject paramJSON=new JSONObject();
       paramJSON.put("page", request.getParameter("page"));
       paramJSON.put("rows", request.getParameter("rows"));
       try {
    	   MemberListDo memberDo=CommonUtil.getMemberListDo(request);
    	   if(null!=memberDo){
    		   int memberId = Integer.parseInt(memberDo.getMemberId());
	    	   paramJSON.put("memberId", memberId);
	    	   ResultObject resultObject = memberService.doService(new RequestObject(headObject, paramJSON.toJSONString()));
	           return resultObject;
    	   }else{
    		   headObject.setRetCode(ErrorCode.FAILURE);
    		   headObject.setRetMsg("未登录");
    	   }
       } catch (Exception e) {
    	   headObject.setRetCode(ErrorCode.FAILURE);
           e.printStackTrace();
       }
       return headObject;
   }
   
   /**
    * @description <b>删除关注的商品信息</b>
    * @author 王鹏
    * @version 1.0.0
    * @data 2015-4-21
    * @param @param request
    * @param @return
    * @return Object
  */
  @RequestMapping("/deleteWishList")
  public Object deleteWishList(HttpServletRequest request,@RequestParam(value="id",required=true)Integer [] id){
  	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030201-02");
      ResultObject resultObject = null;
      try {
    	  resultObject = memberService.doService(new RequestObject(headObject, id));
    	  return resultObject.getHead();
      } catch (Exception e) {
          e.printStackTrace();
          headObject.setRetCode(ErrorCode.FAILURE);
          return headObject;
      }
  }
  
  
    /**
     * @Title:  selectMember  
     * @Description:  TODO(根据accountId查询member对象)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-27 下午6:50:52  
     * @version 1.0.0 
     * @param @param accountId
     * @param @param request
     * @param @return
     * @param @throws Exception
     * @return Member  返回类型 
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
			return (Member) net.sf.json.JSONObject.toBean(net.sf.json.JSONObject.fromObject(resultObject.getContent()),Member.class);
		}
		return null;
	}
  	
  	//获取当前用户对象
  	private PamAccount findAccountId(HttpServletRequest request) throws Exception{
    	Integer accountId = (Integer) CommonUtil.getSession(request).getAttribute("accountId");
    	PamAccount pamAccount = null;
    	if (accountId != null && accountId != 0) 
		{
    		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020109-02", 
    				GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    		ResultObject resultObject = memberService.doService(new RequestObject(headObject, accountId));
    		if(resultObject!=null&&resultObject.getContent()!=null){
    			pamAccount = (PamAccount) resultObject.getContent();
    		}
		}
		return pamAccount;
    }
  
  	/**
  	 * 
  	 * @Title:  addWishList  
  	 * @Description:  TODO(这里用一句话描述这个方法的作用)  
  	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
  	 * @date 2015-5-27 下午6:50:36  
  	 * @version 1.0.0 
  	 * @param @param request
  	 * @param @param response
  	 * @param @return
  	 * @param @throws Exception
  	 * @return Object  返回类型 
  	 * @throws
  	 */
  	@RequestMapping("/addWishList")
  	@ResponseBody
	public Object addWishList(HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		Integer accountId = (Integer) CommonUtil.getSession(request).getAttribute("accountId");
		String loginStatus = (String) CommonUtil.getSession(request).getAttribute("loginStatus");
		ResultObject resultObject = null;
		HeadObject headObject = null;
		JSONObject jsonObject = new JSONObject();
		Member member = null;
		if (accountId != null && loginStatus != null && "1".equals(loginStatus)) 
		{
			PamAccount pamAccount = findAccountId(request);
			if(pamAccount!=null&&!"100".equals(pamAccount.getAccountType())){
				return new ResultObject(new HeadObject(ErrorCode.SUCCESS),net.sf.json.JSONObject.fromObject("{result:false,isBuyer:false}"));
			}
			
			// 查询当前用户对象
			member = this.selectMember(accountId, request);
		}
		if (member != null) 
		{
			String productIdString = request.getParameter("productId");
			List<ProductWithBLOBs> productList = null;
			List<Integer> productIdList = null;
			if(productIdString!=null&&!"".equals(productIdString)){
				//查询货品是否存在
				productIdList = (List<Integer>) net.sf.json.JSONArray.toCollection(net.sf.json.JSONArray.fromObject(productIdString), Integer.class);
				if(productIdList!=null && productIdList.size()>=1){
					headObject = CommonHeadUtil.geneHeadObject(request, "2000020121-09", 
							GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
					resultObject = itemService.doService(new RequestObject(headObject, productIdList));
					productList = (List<ProductWithBLOBs>) resultObject.getContent();
				}
			}
			if(!(productList!=null&&productList.size()>=1)){
				String goodsIdString = request.getParameter("goodsId");
				if(goodsIdString!=null && !"".equals(goodsIdString)){
					Integer goodsId = Integer.parseInt(goodsIdString);
					headObject = CommonHeadUtil.geneHeadObject(request, "2000020121-08", 
							GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
					jsonObject.clear();
					List<Integer> goodsIdList = new ArrayList<Integer>();
					goodsIdList.add(goodsId);
					jsonObject.put("goodsIdList", goodsIdList);
					resultObject = itemService.doService(new RequestObject(headObject, jsonObject));
					if(resultObject.getContent()!=null&&((List<ProductWithBLOBs>) resultObject.getContent()).size()>=1){
						productList = new ArrayList<ProductWithBLOBs>();
						productList.add(((List<ProductWithBLOBs>) resultObject.getContent()).get(0));
					}
				}
			}
			if(productList!=null&&productList.size()>=1){
//				ProductWishList productWishList = new ProductWishList();
//				productWishList.setMemberId(member.getMemberId().longValue());
//				productWishList.setWishlistDate(new Date(System.currentTimeMillis()));
				
				List<Integer> productIdList2 = new ArrayList<Integer>();
				for(int i =0;i <productList.size();i++){
					productIdList2.add(productList.get(i).getProductId());
				}
				//查询数据库中是否已存在该关注货品
				headObject = CommonHeadUtil.geneHeadObject(request, "030201-05", 
						GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
				jsonObject = new JSONObject();
				jsonObject.put("memberId", member.getMemberId());
				jsonObject.put("productIdList", productIdList2);
				resultObject = memberService.doService(new RequestObject(headObject, jsonObject));
				List<ProductWishList> list = null;
				if(resultObject.getContent()!=null){
					list = com.alibaba.fastjson.JSONArray.parseArray(resultObject.getContent().toString(), ProductWishList.class);
				}
				List<Integer> productIdListFromDB = new ArrayList<Integer>();
				if(list!=null&&list.size()>=1){
					for(int i =0;i<list.size();i++){
						productIdListFromDB.add(list.get(i).getProductId().intValue());
					}
				}
				productIdList2.clear();
				for(int i =0;i <productList.size();i++){
					if(!productIdListFromDB.contains(productList.get(i).getProductId())){
						productIdList2.add(productList.get(i).getProductId());
//						productWishList.setProductId(productList.get(i).getProductId().longValue());
//						wishListMapper.insertSelective(productWishList);
					}
				}
				if(productIdList2!=null&&productIdList2.size()>=1){
					//存入数据库
					headObject = CommonHeadUtil.geneHeadObject(request, "030201-04", 
							GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
					jsonObject = new JSONObject();
					jsonObject.put("memberId", member.getMemberId());
					jsonObject.put("productIdList", productIdList2);
					resultObject = memberService.doService(new RequestObject(headObject, jsonObject));
					//更新商品被收藏数量
					headObject = CommonHeadUtil.geneHeadObject(request, "010201-19", 
							GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
					resultObject = itemService.doService(new RequestObject(headObject, productIdList2));
				}else{
					if(productIdList != null && productIdList.size() == 1 
							&& productList != null && productList.size() == 1 
							&& productList.get(0).getProductId().equals(productIdList.get(0))){
						return new ResultObject(new HeadObject(ErrorCode.IS_EXIST));
					}
				}
				
				
				
//				//存入数据库
//				headObject = CommonHeadUtil.geneHeadObject(request, "030201-04", 
//						GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
//				jsonObject = new JSONObject();
//				jsonObject.put("memberId", member.getMemberId());
//				jsonObject.put("productList", productList);
//				resultObject = memberService.doService(new RequestObject(headObject, jsonObject));
			}else{
				resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),
						net.sf.json.JSONObject.fromObject("{result:false,msg:'该货品不存在'}"));
			}
		} else {
			// 用户未登录
			resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),
					net.sf.json.JSONObject.fromObject("{result:false,isLogin:false}"));
		}
		return resultObject;
	}
}
