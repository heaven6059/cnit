
package com.cnit.yoyo.resource.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.pageModel.Tree;
import com.cnit.yoyo.model.system.Resource;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;

/**
 * 
 * @ClassName: ResourceController 
 * @Description:  菜单控制器
 * @author xiaox
 * @date 2015-4-2 上午10:24:33
 */
@Controller
@RequestMapping("/resource")
public class ResourceController {
    public static final Logger log = LoggerFactory.getLogger(ResourceController.class);
    
    @Autowired
    private RemoteService memberService;

  
    /**
     * 
     * @Description: 获取菜单列表 
     * @param @param request
     * @param @return
     * @param @throws Exception
     * @author xiaox
     * @date 2015-4-2 上午10:25:08
     */
    @RequestMapping("/resourceTree")
    public Object getResourceTree(HttpServletRequest request,Map<String,Object> map,@RequestParam(value="pid",required=true) int pid,
    		@RequestParam(value="layout",required=true) String layout) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "3000020110-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        
        int accountId = (Integer) request.getSession().getAttribute("accountId");
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("accountId", accountId);
        data.put("parentId", pid);
        ResultObject resultObject = memberService.doService(new RequestObject(headObject,data)); // pid 表示父菜单id
        @SuppressWarnings("unchecked")
		List<Resource> resources = (List<Resource>) resultObject.getContent();
        List<Tree> menuTree = getTreeList(resources,headObject,accountId);
        map.put("menuTree", menuTree);
        if(layout.equals(GlobalStatic.LAYOUT_NORTH)){
        	//String parentId = String.valueOf(resources.get(0).getId());
        	//request.getSession().setAttribute("parentId", parentId);
        	 return "pages/biz/layout/north";
        }else {
        	//request.getSession().removeAttribute("parentId");
        	return "pages/biz/layout/west";
        }
    }
    
    
    /**
     * 
     * @Description: 后台首页  
     * @param @return
     * @author xiaox
     * @date 2015-4-2 下午2:49:52
     */
    @RequestMapping("/home")
    public String home() {
        return "pages/biz/layout/home";
    }
    
    
    /**
     * 
     * @Description: 获得树形的菜单，递归进行获取
     * @author xiaox
     * @throws Exception 
     * @throws NumberFormatException 
     * @date 2015-4-2 上午11:00:10
     */
    private List<Tree> getTreeList(List<Resource> resources, HeadObject headObject,int accountId ) throws  Exception{
		List<Tree> lt = null;
		List<Tree> children = new ArrayList<Tree>();
		if (resources != null && resources.size() > 0) {
			lt=new ArrayList<Tree>();
			for (Resource r : resources) {
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
		List<Resource> catalogls = new ArrayList<Resource>();
	    ResultObject resultObject = null;
		if (StringUtils.isNotBlank(parentId)) {
			Map<String, Object> data = new HashMap<String, Object>();
	        data.put("accountId", accountId);
	        data.put("parentId", parentId);
			//查找下一级菜单
			resultObject = memberService.doService(new RequestObject(headObject,data)); 
			if(resultObject!=null){
				catalogls =  (List<Resource>) resultObject.getContent();
			}
		}
		return getTreeList(catalogls,headObject,accountId);
		
	}

   
  
}
