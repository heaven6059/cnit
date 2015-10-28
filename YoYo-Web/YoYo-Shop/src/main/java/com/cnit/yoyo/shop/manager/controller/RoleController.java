package com.cnit.yoyo.shop.manager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.goods.Store;
import com.cnit.yoyo.model.member.Roles;
import com.cnit.yoyo.model.member.StoreRolesResource;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;

/**
 * @ClassName: RoleController 
 * @Description: TODO(店铺管理中的角色管理功能) 
 * @detail <店铺角色的增删改查>
 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
 * @date 2015-4-21 下午7:31:34 
 * @version 1.0.0
 */
@Controller
@RequestMapping("/roleManager")
public class RoleController 
{
    
	public static final Logger log = LoggerFactory.getLogger(RoleController.class);
	@Autowired
	private RemoteService memberService;
	//默认的每页显示数
	private Integer pageSize = 10;
	//异常提示语
	private String errorMsg="系统繁忙，请稍后再试！";

    /**
     * @Title: roleMain 
     * @Description: TODO(角色管理页面) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-21 下午7:43:15 
     * @version 1.0.0 
     * @param @return    
     * @return String    返回类型 
     * @throws
     */
    @RequestMapping("/roleIndex")
    public String roleMain() 
    {
		return "pages/shopMng/roleMain";
	}
    
    /**
     * @Title: findRolePage 
     * @Description: TODO(分页查询店铺角色) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-22 下午2:36:44 
     * @version 1.0.0 
     * @param request
     * @param response
     * @param @return
     * @param @throws Exception    
     * @return Object    返回类型 
     * @throws
     */
    @RequestMapping("/rolePage")
    @ResponseBody
	public Object findRolePage(HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		log.info("start[RoleController.findRolePage]");
//		Integer accountId = (Integer) request.getSession().getAttribute("accountId");
//		accountId = 151;
//		String loginStatus = (String) request.getSession().getAttribute("loginStatus");
//		loginStatus = "1";
		Integer accountId = (Integer) CommonUtil.getSession(request).getAttribute("accountId");
		String loginStatus = (String) CommonUtil.getSession(request).getAttribute("loginStatus");
		ResultObject resultObject = null;
		HeadObject headObject = null;
		JSONObject jsonObject = null;
		if (accountId != null && loginStatus != null
				&& "1".equals(loginStatus.trim())) 
		{
			// 用户已登录
			// 查询当前用户的店铺对象
			Store store = this.findStoreByAccountId(request, accountId, headObject, resultObject);
			if (store != null) {
				// 分页查询当前店铺的角色列表
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
				headObject = CommonHeadUtil.geneHeadObject(request, "1000020108-02");
				jsonObject = new JSONObject();
				jsonObject.put("storeId", store.getStoreId());
				jsonObject.put("pageSize", pageSize);
				jsonObject.put("pageIndex", pageIndex);
				resultObject = memberService.doService(new RequestObject(headObject, jsonObject));
			} else {
				resultObject = new ResultObject( new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,isLogin:true,isStoreExist:false}"));
			}
		} else {
			// 用户未登录
			resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,isLogin:false}"));
		}
		log.info("end[RoleController.findRolePage]");
		return resultObject;
	}
    
    /**
     * 
    *
    * @Description: 获取角色的ID和名称作为添加页面的下拉框数据
    * @param @param request
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-29 下午5:42:56 
    * @return Object    返回类型 
    * @throws
     */
    @RequestMapping("/findSelect")
    @ResponseBody
    public Object findSelect(HttpServletRequest request,@RequestParam(value="companyId",required=true)Long companyId){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990302-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
        	   resultObject = memberService.doService(new RequestObject(headObject,companyId));
        	   
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    
    /**
     * @Title: findStoreByAccountId 
     * @Description: TODO(根据accountId查询店铺对象) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-23 上午11:40:29 
     * @version 1.0.0 
     * @param request
     * @param accountId
     * @param headObject
     * @param resultObject
     * @param @return
     * @param @throws Exception    
     * @return StoreDTO    返回类型 
     * @throws
     */
	private Store findStoreByAccountId(HttpServletRequest request, Integer accountId, 
			HeadObject headObject, ResultObject resultObject) throws Exception 
	{
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
    
    /**
     * @Title: rolesDel 
     * @Description: TODO(删除指定角色) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-23 上午11:42:23 
     * @version 1.0.0 
     * @param request
     * @param response
     * @param @return
     * @param @throws Exception    
     * @return Object    返回类型 
     * @throws
     */
    @RequestMapping("/rolesDel")
    @ResponseBody
	public Object rolesDel(HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		log.info("start[RoleController.rolesDel]");
//		Integer accountId = (Integer) request.getSession().getAttribute("accountId");
//		accountId = 151;
//		String loginStatus = (String) request.getSession().getAttribute("loginStatus");
//		loginStatus = "1";
		Integer accountId = (Integer) CommonUtil.getSession(request).getAttribute("accountId");
		String loginStatus = (String) CommonUtil.getSession(request).getAttribute("loginStatus");
		ResultObject resultObject = null;
		HeadObject headObject = null;
		JSONObject jsonObject = null;
		if (accountId != null && loginStatus != null && "1".equals(loginStatus)) 
		{
			// 用户已登录
			// 查询当前用户的店铺对象
			Store store = this.findStoreByAccountId(request, accountId, headObject, resultObject);
			if (store != null) 
			{
				String rolesIdString = request.getParameter("rolesId");
				Integer rolesId;
				if (rolesIdString != null & !"".equals(rolesIdString)) 
				{
					rolesId = Integer.parseInt(rolesIdString);
					headObject = CommonHeadUtil.geneHeadObject(request, "1000020108-03", 
							GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
					jsonObject = new JSONObject();
					jsonObject.put("rolesId", rolesId);
					jsonObject.put("storeId", store.getStoreId());
					resultObject = memberService.doService(new RequestObject(headObject, jsonObject));
					if (resultObject.getHead().getRetCode().equals(ErrorCode.SUCCESS)) 
					{
						resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),
								JSONObject.fromObject("{result:true}"));
					} else {
						resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),
								JSONObject.fromObject("{result:false,isLogin:true,msg:\""
												+ errorMsg + "\"}"));
					}
				} else {
					resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),
							JSONObject.fromObject("{result:false,isLogin:true,msg:\"该角色不存在！\"}"));
				}
			} else {
				resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),
						JSONObject.fromObject("{result:false,isLogin:true,isStoreExist:false}"));
			}
		} else {
			// 用户未登录
			resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),
					JSONObject.fromObject("{result:false,isLogin:false}"));
		}
		log.info("end[RoleController.rolesDel]");
		return resultObject;
	}
    
    
    /**
     * @Title: findRolesResource 
     * @Description: TODO(查询所有权限) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-24 上午10:25:36 
     * @version 1.0.0 
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception    
     * @return Object    返回类型 
     * @throws
     */
    @RequestMapping("/findRolesResource")
    @ResponseBody
	public Object findRolesResource(HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		log.info("start[RoleController.findRolesResource]");
//		Integer accountId = (Integer) request.getSession().getAttribute("accountId");
//		accountId = 151;
//		String loginStatus = (String) request.getSession().getAttribute("loginStatus");
//		loginStatus = "1";
		Integer accountId = (Integer) CommonUtil.getSession(request).getAttribute("accountId");
		String loginStatus = (String) CommonUtil.getSession(request).getAttribute("loginStatus");
		ResultObject resultObject = null;
		HeadObject headObject = null;
		JSONObject jsonObject = null;
		if (accountId != null && loginStatus != null && "1".equals(loginStatus)) 
		{
			// 用户已登录
			// 查询当前用户的店铺对象
			Store store = this.findStoreByAccountId(request, accountId,
					headObject, resultObject);
			if (store != null) 
			{
				// 查询所有权限
				headObject = CommonHeadUtil.geneHeadObject(request, "3000020110-03", 
						GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
				jsonObject = new JSONObject();
				jsonObject.put("orderColumn", "parent_id");
				jsonObject.put("companyId", store.getCompanyId());
				resultObject = memberService.doService(new RequestObject(headObject, jsonObject));
			} else {
				resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),
						JSONObject.fromObject("{result:false,isLogin:true,isStoreExist:false}"));
			}
		} else {
			// 用户未登录
			resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),
					JSONObject.fromObject("{result:false,isLogin:false}"));
		}
		log.info("end[RoleController.findRolesResource]");
		return resultObject;
	}
    
    /**
     * @Title: rolesAdd 
     * @Description: TODO(保存新角色) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-24 上午10:27:34 
     * @version 1.0.0 
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception    
     * @return Object    返回类型 
     * @throws
     */
    @RequestMapping("/rolesAdd")
    @ResponseBody
	public Object rolesAdd(HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		log.info("start[RoleController.rolesAdd]");
//		Integer accountId = (Integer) request.getSession().getAttribute("accountId");
//		accountId = 151;
//		String loginStatus = (String) request.getSession().getAttribute("loginStatus");
//		loginStatus = "1";
		Integer accountId = (Integer) CommonUtil.getSession(request).getAttribute("accountId");
		String loginStatus = (String) CommonUtil.getSession(request).getAttribute("loginStatus");
		ResultObject resultObject = null;
		HeadObject headObject = null;
		JSONObject jsonObject = new JSONObject();
		if (accountId != null && loginStatus != null && "1".equals(loginStatus)) 
		{
			// 用户已登录
			// 查询当前用户的店铺对象
			Store store = this.findStoreByAccountId(request, accountId,headObject, resultObject);
			if (store != null) 
			{
				String rolesName = request.getParameter("rolesName");
				String resourceIdsString = request.getParameter("resourceIds");
				System.out.println(rolesName + "..." + resourceIdsString);
				if (rolesName != null && !"".equals(rolesName.trim())) 
				{
					// 判断该店铺中是否已存在该角色名
					List<Roles> rolesList = this.findRolesByStoreAndName(request, headObject, 
							resultObject, store.getStoreId(), rolesName);
					if (!(rolesList != null && rolesList.size() >= 1)) 
					{
						List<Long> resourceIdList = null;
						if (resourceIdsString != null && !"".equals(resourceIdsString)) 
						{
							resourceIdList = (List<Long>) JSONArray.toCollection(
									JSONArray.fromObject(resourceIdsString), Long.class);
							if (resourceIdList != null && resourceIdList.size() >= 1) 
							{
								Long now = System.currentTimeMillis();
								// 保存Roles对象
								headObject = CommonHeadUtil.geneHeadObject(request, "1000020108-04",
										GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
								Roles roles = new Roles();
								roles.setCompanyId(store.getCompanyId());
								roles.setStoreId(store.getStoreId());
								roles.setRolesName(rolesName);
								roles.setRegtime(now);
								roles.setLastModifyTime(now);
								jsonObject.put("roles", roles);
								jsonObject.put("storeId", store.getStoreId());
								jsonObject.put("resourceIdList", resourceIdList);
								resultObject = memberService.doService(new RequestObject(
												headObject, jsonObject));
								if (resultObject.getHead().getRetCode().equals(ErrorCode.SUCCESS)) 
								{
									resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),
											JSONObject.fromObject("{result:true}"));
								} else {
									resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),
											JSONObject.fromObject("{result:false,isLogin:true,msg:\""
															+ errorMsg + "\"}"));
								}
							} else {
								resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),
										JSONObject.fromObject("{result:false,isLogin:true,msg:\"请选择权限！\"}"));
							}
						} else {
							resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),
									JSONObject.fromObject("{result:false,isLogin:true,msg:\"请选择权限！\"}"));
						}
					} else {
						resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),
								JSONObject.fromObject("{result:false,isLogin:true,msg:\"该角色名称已存在！\"}"));
					}
				} else {
					resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),
							JSONObject.fromObject("{result:false,isLogin:true,msg:\"请填写角色名称！\"}"));
				}
			} else {
				resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),
						JSONObject.fromObject("{result:false,isLogin:true,isStoreExist:false}"));
			}
		} else {
			// 用户未登录
			resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),
					JSONObject.fromObject("{result:false,isLogin:false}"));
		}
		log.info("end[RoleController.rolesAdd]");
		return resultObject;
	}
    
    /**
     * @Title: findRolesByStoreAndName 
     * @Description: TODO(根据店铺id和角色名称查找角色对象) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-24 下午5:16:46 
     * @version 1.0.0 
     * @param @param request
     * @param @param headObject
     * @param @param resultObject
     * @param @param storeId
     * @param @param rolesName
     * @param @return
     * @param @throws Exception    
     * @return List<Roles>    返回类型 
     * @throws
     */
	private List<Roles> findRolesByStoreAndName(HttpServletRequest request,
			HeadObject headObject, ResultObject resultObject, Long storeId,
			String rolesName) throws Exception 
	{
		headObject = CommonHeadUtil.geneHeadObject(request, "1000020108-05",
				GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		resultObject = memberService.doService(new RequestObject(headObject,
				JSONObject.fromObject("{storeId:" + storeId + ",rolesName:\""
						+ rolesName + "\"}")));
		List<Roles> rolesList = (List<Roles>) JSONArray.toCollection(
				JSONArray.fromObject(resultObject.getContent()), Roles.class);
		return rolesList;
	}
    
    /**
     * @Title: toRolesUpdate 
     * @Description: TODO(修改角色数据) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-24 下午5:11:58 
     * @version 1.0.0 
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception    
     * @return Object    返回类型 
     * @throws
     */
    @RequestMapping("/toRolesUpdate")
    @ResponseBody
	public Object toRolesUpdate(HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		log.info("start[RoleController.toRolesUpdate]");
		Integer accountId = (Integer) CommonUtil.getSession(request).getAttribute("accountId");
		String loginStatus = (String) CommonUtil.getSession(request).getAttribute("loginStatus");
		ResultObject resultObject = null;
		HeadObject headObject = null;
		JSONObject jsonObject = null;
		if (accountId != null && loginStatus != null && "1".equals(loginStatus)) 
		{
			// 用户已登录
			// 查询当前用户的店铺对象
			Store store = this.findStoreByAccountId(request, accountId, headObject, resultObject);
			if (store != null) 
			{
				String rolesIdString = request.getParameter("rolesId");
				Integer rolesId;
				if (rolesIdString != null & !"".equals(rolesIdString)) 
				{
					rolesId = Integer.parseInt(rolesIdString);
					// 查询Roles对象
					headObject = CommonHeadUtil.geneHeadObject(request, "1000020108-06");
					resultObject = memberService.doService(new RequestObject(headObject,JSONObject.fromObject("{rolesId:" + rolesId + "}")));
					Roles roles = (Roles) JSONObject.toBean(((JSONObject) ((JSONObject) resultObject.getContent()).get("roles")), Roles.class);
					if (roles != null) 
					{
						List<StoreRolesResource> rList = (List<StoreRolesResource>) JSONArray.toCollection(((JSONArray) ((JSONObject) resultObject.getContent()).get("resourceList")),StoreRolesResource.class);
						// 查询所有权限
						headObject = CommonHeadUtil.geneHeadObject(request, "3000020110-03");
						jsonObject = new JSONObject();
						jsonObject.put("orderColumn", "parent_id");
						jsonObject.put("companyId", store.getCompanyId());
						resultObject = memberService.doService(new RequestObject(headObject, jsonObject));

						jsonObject = new JSONObject();
						jsonObject.put("result", true);
						jsonObject.put("roles", roles);
						jsonObject.put("rList", rList);
						jsonObject.put("resourceList", resultObject.getContent());
						resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS), jsonObject);
					} else {
						resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,isLogin:true,msg:\"该角色不存在！\"}"));
					}
				} else {
					resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,isLogin:true,msg:\"该角色不存在！\"}"));
				}
			} else {
				resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,isLogin:true,isStoreExist:false}"));
			}
		} else {
			// 用户未登录
			resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false,isLogin:false}"));
		}
		log.info("end[RoleController.toRolesUpdate]");
		return resultObject;
	}
    
    /**
     * @Title: rolesUpdate 
     * @Description: TODO(更新修改后的角色对象) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-24 下午5:13:09 
     * @version 1.0.0 
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception    
     * @return Object    返回类型 
     * @throws
     */
    @RequestMapping("/rolesUpdate")
    @ResponseBody
	public Object rolesUpdate(HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		log.info("start[RoleController.rolesUpdate]");
//		Integer accountId = (Integer) request.getSession().getAttribute("accountId");
//		accountId = 151;
//		String loginStatus = (String) request.getSession().getAttribute("loginStatus");
//		loginStatus = "1";
		Integer accountId = (Integer) CommonUtil.getSession(request).getAttribute("accountId");
		String loginStatus = (String) CommonUtil.getSession(request).getAttribute("loginStatus");
		ResultObject resultObject = null;
		HeadObject headObject = null;
		JSONObject jsonObject = null;
		if (accountId != null && loginStatus != null && "1".equals(loginStatus)) 
		{
			// 用户已登录
			// 查询当前用户的店铺对象
			Store store = this.findStoreByAccountId(request, accountId, headObject, resultObject);
			if (store != null) 
			{
				String rolesName = request.getParameter("rolesName");
				String resourceIdsString = request.getParameter("resourceIds");
				String rolesIdString = request.getParameter("rolesId");
				Long rolesId;
				if (rolesIdString != null & !"".equals(rolesIdString)) 
				{
					rolesId = Long.parseLong(rolesIdString);
					if (rolesName != null && !"".equals(rolesName.trim()))
					{
						// 判断该店铺中是否已存在该角色名
						List<Roles> rolesList = this.findRolesByStoreAndName(request, headObject, 
								resultObject, store.getStoreId(), rolesName);
						boolean isExistName = this.isExistName(rolesList, rolesId);
						if (!isExistName) 
						{
							List<Long> resourceIdList = null;
							if (resourceIdsString != null && !"".equals(resourceIdsString)) 
							{
								resourceIdList = (List<Long>) JSONArray.toCollection(JSONArray
										.fromObject(resourceIdsString), Long.class);
								if (resourceIdList != null && resourceIdList.size() >= 1) 
								{
									Long now = System.currentTimeMillis();
									// 保存Roles对象
									headObject = CommonHeadUtil.geneHeadObject(request, "1000020108-07",
											GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
									Roles roles = new Roles();
									roles.setRolesId(rolesId);
									roles.setRolesName(rolesName);
									roles.setLastModifyTime(now);
									jsonObject = new JSONObject();
									jsonObject.put("roles", roles);
									jsonObject.put("storeId", store.getStoreId());
									jsonObject.put("resourceIdList", resourceIdList);
									resultObject = memberService.doService(new RequestObject(
													headObject, jsonObject));
									if (resultObject.getHead().getRetCode().equals(ErrorCode.SUCCESS)) 
									{
										resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),
												JSONObject.fromObject("{result:true}"));
									} else {
										resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),
												JSONObject.fromObject("{result:false,isLogin:true,msg:\""
														+ errorMsg + "\"}"));
									}
								} else {
									resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),
											JSONObject.fromObject("{result:false,isLogin:true,msg:\"请选择权限！\"}"));
								}
							} else {
								resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),
										JSONObject.fromObject("{result:false,isLogin:true,msg:\"请选择权限！\"}"));
							}
						} else {
							resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),
									JSONObject.fromObject("{result:false,isLogin:true,msg:\"该角色名称已存在！\"}"));
						}
					} else {
						resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),
								JSONObject.fromObject("{result:false,isLogin:true,msg:\"请填写角色名称！\"}"));
					}
				} else {
					resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),
							JSONObject.fromObject("{result:false,isLogin:true,msg:\"该角色不存在！\"}"));
				}
			} else {
				resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),
						JSONObject.fromObject("{result:false,isLogin:true,isStoreExist:false}"));
			}
		} else {
			// 用户未登录
			resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),
					JSONObject.fromObject("{result:false,isLogin:false}"));
		}
		log.info("end[RoleController.rolesUpdate]");
		return resultObject;

	}
    
    /**
     * @Title: isExistName 
     * @Description: TODO(判断是否存在同名角色) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-24 下午5:26:45 
     * @version 1.0.0 
     * @param @param rolesList
     * @param @param rolesId
     * @param @return    
     * @return boolean    返回类型 
     * @throws
     */
	private boolean isExistName(List<Roles> rolesList, Long rolesId) 
	{
		if (rolesList != null && rolesList.size() >= 1) 
		{
			for (int i = 0; i < rolesList.size(); i++) 
			{
				if (!(rolesList.get(i).getRolesId().equals(rolesId))) 
				{
					return true;
				}
			}
		}
		return false;
	}
    
    
}
