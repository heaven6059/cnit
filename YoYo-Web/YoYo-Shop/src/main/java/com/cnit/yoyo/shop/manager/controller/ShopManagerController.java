
package com.cnit.yoyo.shop.manager.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
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
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.model.pageModel.Tree;
import com.cnit.yoyo.model.system.SiteResource;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.RedisClientUtil;
import com.cnit.yoyo.util.StringUtil;

/**
 * @description 店铺管理
 * @detail <功能详细描述>
 * @author xiaox
 * @version 1.0.0
 */
@Controller
@RequestMapping("/shopManager")
public class ShopManagerController {

    @Autowired 
    private RemoteService memberService;
    @Autowired
    private RedisClientUtil redisServer;
    
    
    	 /**
    	  * @description <b></b>
    	  * @author 王鹏
    	  * @version 1.0.0
    	  * @data 2015年7月23日
    	  * @param request
    	  * @return
    	  * String
    	*/
    public String shopIndex(HttpServletRequest request){
    	return "";
    }
    
    /**
     * 
     * @Title: findShopList 
     * @Description: 根据集团id查找所有分店
     * @param @param request
     * @param @return
     * @param @throws Exception    设定文件 
     * @author xiaox
     * @date 2015-3-18 下午7:34:03
     */
    @RequestMapping("/findShopList")
    @ResponseBody
    public Object findShopList(HttpServletRequest request) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020105-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> data = new HashMap<String, Object>();
        String pageIndex = request.getParameter("page");
        String pageSize = request.getParameter("rows");
        data.put("pageIndex", StringUtil.isEmpty(pageIndex) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
        data.put("pageSize", StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize));
        //TODO 从session中获取集团id
        //data.put("companyId", 67);
        Integer companyId = (Integer) CommonUtil.getSession(request).getAttribute("companyId");
        data.put("companyId",Long.valueOf(companyId));
        return memberService.doService(new RequestObject(headObject, data));
    }
    
    
    
    
    /**
     * 
     * @Title: saveStore 
     * @Description:保存分店 
     * @param @param request
     * @param @param store
     * @param @return
     * @param @throws Exception    设定文件 
     * @author xiaox
     * @date 2015-3-19 下午1:17:16
     */
    @RequestMapping("/saveStore")
    @ResponseBody
    public Object saveStore(HttpServletRequest request, Store store) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020106-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        store.setLastModify(new Date());
        
        //TODO 从session中获取集团id与集团名称，然后根据id判断能添加几家分店
        Integer companyId = (Integer) CommonUtil.getSession(request).getAttribute("companyId");
        String companyName = (String) CommonUtil.getSession(request).getAttribute("companyName");
        Integer accountId = (Integer) CommonUtil.getSession(request).getAttribute("accountId");
        store.setCompanyId(Long.valueOf(companyId));
        store.setCompanyName(companyName);
        store.setAccountId(accountId);
        store.setDisabled("0");
        store.setStatus("0");
        ResultObject resultObject = null;
        try {//判断是否可以添加分店
        	resultObject = memberService.doService(new RequestObject(headObject, store));
        	if(resultObject.getContent()!=null){
        		JSONObject shopGrade = JSONObject.fromObject(resultObject.getContent());
        		if(shopGrade.getString("issueType").equals(GlobalStatic.SHOP_TYPE_UNIT)){ //单店类型
        			resultObject.setHead(new HeadObject(ErrorCode.IS_NOT_COMPANY));
        		}else{
        			if(Integer.valueOf(shopGrade.getString("shopNums"))>Integer.valueOf(store.getLimitStore())){ //集团类型，判断是否超出开店个数
        				 store.setGradeId(1l);  //分店等级，默认最低级
    		        	 headObject = CommonHeadUtil.geneHeadObject(request, "1000020105-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    		             resultObject = memberService.doService(new RequestObject(headObject, store));
        			}else{
        				resultObject.setHead(new HeadObject(ErrorCode.IS_STORE_NUMS_OVER));
        			}
        		}
        	}
	       
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultObject;
    }
    
    
    @RequestMapping("/deleteStore")
    @ResponseBody
    public Object deleteStore(HttpServletRequest request, Long storeId) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020105-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        try {
            resultObject = memberService.doService(new RequestObject(headObject, storeId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultObject;
    }

    

    /**
     * 
     * @Title: shopRegister 
     * @Description: 店铺管理 
     * @param @param request
     * @param @return
     * @param @throws Exception    设定文件 
     * @author xiaox
     * @date 2015-3-18 下午7:34:36
     */
    @RequestMapping("/manager")
    public String shopRegister(HttpServletRequest request) throws Exception {
        return "pages/shopMng/shopManager";
    }
    
    @RequestMapping("/menuTree")
    @ResponseBody
    public Object getMenuTree(HttpServletRequest request,Map<String, Object> map) throws Exception {
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "3000020110-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	Map<String, Object> data = new HashMap<String, Object>();
    	String accountInfo = (String) CommonUtil.getSession(request).getAttribute(GlobalStatic.USER_INFO);
    	JSONObject account = JSONObject.fromObject(accountInfo);
    	MemberListDo memberDo=(MemberListDo) JSONObject.toBean(account,MemberListDo.class);
    	//String accountInfo = redisServer.get(sessionId);
    	//PamAccount user = (PamAccount) request.getAttribute("user");
        int accountId = memberDo.getAccountId();
        data.put("accountId", accountId);
        data.put("parentId", 0);
        ResultObject resultObject = memberService.doService(new RequestObject(headObject,data)); // pid 表示父菜单id
        @SuppressWarnings("unchecked")
		List<SiteResource> resources = (List<SiteResource>) resultObject.getContent();
        List<Tree> menuTree = getTreeList(resources,headObject,accountId);
        map.put("menuTree", menuTree);
        resultObject.setContent(map);
        return resultObject;
    }
    
    /**
     * 
    *
    * @Description: 获得树形的菜单，递归进行获取
    * @param @param resources
    * @param @param headObject
    * @param @return
    * @param @throws Exception    设定文件 
    * @author ssd
    * @date 2015-4-10 下午2:14:56 
    * @return List<Tree>    返回类型 
    * @throws
     */
    private List<Tree> getTreeList(List<SiteResource> resources, HeadObject headObject,int accountId ) throws  Exception{
		List<Tree> lt = null;
		List<Tree> children = new ArrayList<Tree>();
		if (resources != null && resources.size() > 0) {
			lt=new ArrayList<Tree>();
			for (SiteResource r : resources) {
				Tree tree = new Tree();
				tree.setId(String.valueOf(r.getId()));
				tree.setPid(String.valueOf(r.getParentId()));
				tree.setText(r.getResourceName());
				
				Map<String, Object> attr = new HashMap<String, Object>();
				attr.put("url", r.getUrl());
				tree.setAttributes(attr);
				if (StringUtils.isNotBlank(r.getIcon())){
					tree.setIconCls(r.getIcon());
				} else {
					tree.setIconCls("flag");  //设置图标为"flag"的css
				}
				lt.add(tree);
				children=getTreeData(String.valueOf(r.getId()),headObject,accountId);
				if(children != null){
					tree.setChildren(children);
				}
			}
		}
		return lt;
	}
	
    //查找子菜单
    @SuppressWarnings("unchecked")
	private List<Tree> getTreeData(String parentId, HeadObject headObject,int accountId) throws Exception{
		List<SiteResource> catalogls = new ArrayList<SiteResource>();
	    ResultObject resultObject = null;
		if (StringUtils.isNotBlank(parentId)) {
			Map<String, Object> data = new HashMap<String, Object>();
	        data.put("accountId", accountId);
	        data.put("parentId", parentId);
			//查找下一级菜单
			resultObject = memberService.doService(new RequestObject(headObject,data)); 
			if(resultObject!=null){
				catalogls =  (List<SiteResource>) resultObject.getContent();
			}
		}
		return getTreeList(catalogls,headObject,accountId);
		
	}
    
    /**
     * 
     * @Title: basesetup 
     * @Description: 店铺基本设置 
     * @param @return    设定文件 
     * @author xiaox
     * @date 2015-3-18 下午7:34:51
     */
    @RequestMapping("/shopBaseSetup")
    public String shopBaseSetup(HttpServletRequest request,Map<String, Object> map){
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020103-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        //TODO 从session中获取 店铺id
        Map<String, Long> dataMap = new HashMap<String, Long>();
        Integer companyId = (Integer) CommonUtil.getSession(request).getAttribute("companyId");
        dataMap.put("companyId",Long.valueOf(companyId));
        
        try {
            resultObject = memberService.doService(new RequestObject(headObject, dataMap));
            map.put("shop", resultObject.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pages/shopMng/shopBaseSetup";
    }
    
    
    
    /**
     * 
     *@description 后台卖家店铺信息的更新
     *@detail <方法详细描述>
     *@version 1.0.0
     *@data 2015-3-11
     *@param request
     *@param company
     *@return
     *@throws Exception
     */
    @RequestMapping("/shopInfoUpdate")
    @ResponseBody
    public Object shopInfoUpdate(HttpServletRequest request,CompanyWithBLOBs company) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020103-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
     
        return  memberService.doService(new RequestObject(headObject, company));
    }
    
 
    /**
     * 
     *@description 获取店铺是否需要审核商品
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-6-11
     *@param request
     *@param map
     *@return
     */
    @RequestMapping("/shopGoodCheck")
    @ResponseBody
    public Object shopGoodCheck(HttpServletRequest request){
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020103-15", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        //TODO 从session中获取 店铺id
        Map<String, Long> dataMap = new HashMap<String, Long>();
        Integer companyId = (Integer) CommonUtil.getSession(request).getAttribute("companyId");
        dataMap.put("companyId",Long.valueOf(companyId));
        
        try {
            resultObject = memberService.doService(new RequestObject(headObject, dataMap));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultObject;
    }
    
  
}
