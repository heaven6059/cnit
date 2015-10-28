package com.cnit.yoyo.shop.manager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.goods.CompanyWithBLOBs;
import com.cnit.yoyo.model.goods.Store;
import com.cnit.yoyo.model.shop.ShopGrade;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;

/**
 * @ClassName: EarnestController  
 * @Description: TODO(卖家中心的保证金页面)  
 * @detail <详细说明>
 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
 * @date 2015-4-27 下午4:03:14  
 * @version 1.0.0
 */
@Controller
@RequestMapping("/earnestManager")
public class EarnestController {
	
	public static final Logger log = LoggerFactory.getLogger(EarnestController.class);
	@Autowired
	private RemoteService memberService;
	
	private Integer pageSize = 10;
	
	/**
	 * @Title:  earnestMain  
	 * @Description:  TODO(保证金主页面)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-4-27 下午4:04:12  
	 * @version 1.0.0 
	 * @param @return
	 * @return String  返回类型 
	 * @throws
	 */
	@RequestMapping("/earnestIndex")
    public String earnestMain() 
    {
		return "pages/shopMng/earnestMain";
	}
	
	/**
	 * @Title:  findEarnest  
	 * @Description:  TODO(获取保证金数据)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-4-27 下午4:04:35  
	 * @version 1.0.0 
	 * @param request
	 * @param response
	 * @param @return
	 * @param @throws Exception
	 * @return Object  返回类型 
	 * @throws
	 */
	@RequestMapping("/findEarnest")
    @ResponseBody
	public Object findEarnest(HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		log.info("start[EarnestController.findEarnest]");
//		Integer accountId = (Integer) request.getSession().getAttribute("accountId");
//		accountId = 155;
//		String loginStatus = (String) request.getSession().getAttribute("loginStatus");
//		loginStatus = "1";
		Integer accountId = (Integer) CommonUtil.getSession(request).getAttribute("accountId");
		String loginStatus = (String) CommonUtil.getSession(request).getAttribute("loginStatus");
		ResultObject resultObject = null;
		HeadObject headObject = null;
		JSONObject jsonObject = new JSONObject();
		if (accountId != null && loginStatus != null && "1".equals(loginStatus.trim())) 
		{
			// 用户已登录
			// 查询当前用户的店铺对象
			Store store = this.findStoreByAccountId(request, accountId, headObject, resultObject);
			if (store != null) {
//				//查询店铺等级
//				headObject = CommonHeadUtil.geneHeadObject(request, "1000020106-02",
//						GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
//				jsonObject = new JSONObject();
//				jsonObject.put("companyId", store.getCompanyId());
//				resultObject = memberService.doService(new RequestObject(headObject,
//						jsonObject));
//				ShopGrade shopGrade = (ShopGrade) resultObject.getContent();
				
				//查询集团
				headObject = CommonHeadUtil.geneHeadObject(request, "1000020103-18",
						GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
				resultObject = memberService.doService(new RequestObject(headObject,
						store.getCompanyId()));
				CompanyWithBLOBs company = (CompanyWithBLOBs) resultObject.getContent();
				if(company!=null){
					// 分页查询保证金变更历史
					String pageIndexString = request.getParameter("pageIndex");
					String pageSizeString = request.getParameter("pageSize");
					Integer pageIndex;
					if (pageIndexString != null && !"".equals(pageIndexString.trim())) 
					{
						pageIndex = Integer.parseInt(pageIndexString.trim());
					} else {
						pageIndex = 1;
					}
					if (pageSizeString != null && !"".equals(pageSizeString)) {
						pageSize = Integer.parseInt(pageSizeString);
					}
					headObject = CommonHeadUtil.geneHeadObject(request, "030103-01",
							GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
					jsonObject.put("pageIndex", pageIndex);
					jsonObject.put("pageSize", pageSize);
					jsonObject.put("storeId", store.getStoreId());
					jsonObject.put("companyId", store.getCompanyId());
					resultObject = memberService.doService(new RequestObject(headObject,
							jsonObject));
					Object object = resultObject.getContent();
					//查询保证金总数t_business_earnest_log
//					headObject = CommonHeadUtil.geneHeadObject(request, "030103-02",
//							GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
//					resultObject = memberService.doService(new RequestObject(headObject,
//							store.getCompanyId()));
//					Double sumEarnest = (Double) resultObject.getContent();
//					//查询违规记录扣除总数t_business_storeviolation
//					headObject = CommonHeadUtil.geneHeadObject(request, "030104-01",
//							GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
//					resultObject = memberService.doService(new RequestObject(headObject,
//							store.getCompanyId()));
//					jsonObject.clear();
//					jsonObject.put("sumEarnest", sumEarnest);
//					jsonObject.put("sumViolationEarnest", resultObject.getContent());
//					jsonObject.put("page", object);
//					if(shopGrade!=null){
//						jsonObject.put("issueMoney", shopGrade.getIssueMoney());
//					}
					//查询保证金余额
					headObject = CommonHeadUtil.geneHeadObject(request, "030103-05",
							GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
					resultObject = memberService.doService(new RequestObject(headObject,
							store.getCompanyId()));
					Double earnest = (Double) resultObject.getContent();
//					jsonObject.clear();
//					jsonObject.put("page", object);
//					jsonObject.put("earnest", earnest);
					com.alibaba.fastjson.JSONObject returnJson = new com.alibaba.fastjson.JSONObject();
					returnJson.put("page", object);
					returnJson.put("earnest", earnest);
					resultObject.setContent(returnJson);
				}else{
					resultObject = new ResultObject( new HeadObject(ErrorCode.SUCCESS),
							JSONObject.fromObject("{result:false,isLogin:true,isCompanyExist:false}"));
				}
			} else {
				resultObject = new ResultObject( new HeadObject(ErrorCode.SUCCESS),
						JSONObject.fromObject("{result:false,isLogin:true,isStoreExist:false}"));
			}
		} else {
			// 用户未登录
			resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),
					JSONObject.fromObject("{result:false,isLogin:false}"));
		}
		log.info("end[EarnestController.findEarnest]");
		return resultObject;
	}
	
	/**
	 * @Title:  findStoreByAccountId  
	 * @Description:  TODO(根据accountId查询店铺对象)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-4-27 下午4:05:10  
	 * @version 1.0.0 
	 * @param request
	 * @param accountId
	 * @param headObject
	 * @param resultObject
	 * @param @return
	 * @param @throws Exception
	 * @return StoreDTO  返回类型 
	 * @throws
	 */
	private Store findStoreByAccountId(HttpServletRequest request, Integer accountId, 
			HeadObject headObject, ResultObject resultObject) throws Exception 
	{
//		headObject = CommonHeadUtil.geneHeadObject(request, "1000020105-06",
//				GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("accountId", accountId);
//		resultObject = memberService.doService(new RequestObject(headObject,
//				jsonObject));
//		return (StoreDTO) JSONObject.toBean(
//				(JSONObject) resultObject.getContent(), StoreDTO.class);
		
		headObject = CommonHeadUtil.geneHeadObject(request, "031001-05",
				GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		resultObject = memberService.doService(new RequestObject(headObject,
				accountId));
		Store store = null;
		if(resultObject.getContent()!=null){
			store = com.alibaba.fastjson.JSONObject.parseObject(resultObject.getContent().toString(), Store.class);
		}
		return store;
	}
}
