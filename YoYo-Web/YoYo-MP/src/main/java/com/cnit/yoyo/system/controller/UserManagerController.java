
package com.cnit.yoyo.system.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.system.dto.UserAddDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.StringUtil;

/**
 * 用户管理类
* @author ssd
* @date 2015-4-22 下午3:14:08
 */
@Controller
@RequestMapping("/userManager")
public class UserManagerController {
    public static final Logger log = LoggerFactory.getLogger(UserManagerController.class);
    
    @Autowired
    private RemoteService memberService;

    /**
     * 
    *
    * @Description: 跳转到用户管理首页
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-22 下午3:15:00 
    * @return String    返回类型 
    * @throws
     */
    @RequestMapping("/index")
    public String index() {
        return "pages/biz/system/userManager";
    }
    
    /**
     * 
    *
    * @Description:获取用户列表，显示账号和角色列表
    * @param @param request
    * @param @return
    * @param @throws Exception    设定文件 
    * @author ssd
    * @date 2015-4-22 下午3:27:06 
    * @return Object    返回类型 
    * @throws
     */
    @RequestMapping("/userManagerList")
    @ResponseBody
    public Object getUserManagerList(HttpServletRequest request) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "3000020103-06", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> data = new HashMap<String, Object>();
        String pageIndex = request.getParameter("page");
        String pageSize = request.getParameter("rows");
        data.put("pageIndex", StringUtil.isEmpty(pageIndex) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
        data.put("pageSize", StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize));
        ResultObject resultObject = memberService.doService(new RequestObject(headObject,data));
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
    public Object findSelect(HttpServletRequest request){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "3000020103-08", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
        	   resultObject = memberService.doService(new RequestObject(headObject));
        	   
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    

    
    
    /**
     * 
    *
    * @Description: 添加用户
    * @param @param request
    * @param @param carYear
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-22 下午4:59:55 
    * @return Object    返回类型 
    * @throws
     */
    @RequestMapping("/insertUser")
    @ResponseBody
    public Object insertUser(HttpServletRequest request,UserAddDTO userAddDTO){
    	 ResultObject resultObject = null;
         try {
        	 HeadObject  headObject = CommonHeadUtil.geneHeadObject(request, "3000020103-07", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        	 resultObject = memberService.doService(new RequestObject(headObject, userAddDTO));
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    

    /**
     * 
    *
    * @Description: 删除用户
    * @param @param request
    * @param @param id
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-29 下午5:44:11 
    * @return Object    返回类型 
    * @throws
     */
    @RequestMapping("/deleteUser")
    @ResponseBody
    public Object deleteUser(HttpServletRequest request,@RequestParam(value="id",required=true)Integer id){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990104-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
        	   resultObject = memberService.doService(new RequestObject(headObject, id));
        	   
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    
    /**
     * 
    *
    * @Description: 更新用户 
    * @param @param request
    * @param @param carYear
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-29 下午7:22:48 
    * @return Object    返回类型 
    * @throws
     */
    @RequestMapping("/updateUser")
    @ResponseBody
	public Object updateUser(HttpServletRequest request,UserAddDTO userAddDTO) {
		ResultObject resultObject = null;
		try {
			HeadObject headObject = CommonHeadUtil.geneHeadObject(request,"990104-02", GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
			resultObject = memberService.doService(new RequestObject(headObject, userAddDTO));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultObject;
	}
  
}
