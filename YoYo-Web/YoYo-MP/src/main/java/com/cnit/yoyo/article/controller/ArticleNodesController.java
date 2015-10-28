package com.cnit.yoyo.article.controller;

import javax.servlet.http.HttpServletRequest;

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
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.StringUtil;

/**
 * @ClassName: ArticleNodesController  
 * @Description: TODO(文章栏目管理)  
 * @detail <详细说明>
 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
 * @date 2015-6-17 下午2:38:13  
 * @version 1.0.0
 */
@Controller
@RequestMapping("/articleNodes")
public class ArticleNodesController {
	
	public static final Logger log = LoggerFactory.getLogger(ArticleNodesController.class);
    
	@Autowired
    public RemoteService otherService;
//    @Autowired
//    public HessianInerface imagesService;

    /**
     * @Title:  index  
     * @Description:  TODO(文章栏目管理页面)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-6-2 下午1:53:02  
     * @version 1.0.0 
     * @param @return
     * @return String  返回类型 
     * @throws
     */
    @RequestMapping("/index")
    public String index() {
        return "pages/biz/article/articleNodes";
    }
    
    /**
     * @Title:  findViolationCatList  
     * @Description:  TODO(根据条件获取违规类型列表)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-6-2 下午2:09:57  
     * @version 1.0.0 
     * @param @param request
     * @param @param company
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/findArticleNodesList")
    @ResponseBody
    public Object findArticleNodesList(HttpServletRequest request) throws Exception {
        log.info("start[ArticleNodesController.findArticleNodesList]");
        String parentIdString = request.getParameter("parentId");
        Integer parentId = null;
		if (StringUtil.isEmpty(parentIdString)) {
			parentId = 0;
		} else {
			parentId = Integer.parseInt(parentIdString);
		}
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990601-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = otherService.doService(new RequestObject(headObject, parentId));
        log.info("end[ArticleNodesController.findArticleNodesList]");
        return resultObject.getContent();
    }
    
    /**
     * @Title:  saveOrUpdate 
     * @Description:  TODO(保存或更新对象)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-6-18 下午7:05:29  
     * @version 1.0.0 
     * @param @param request
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/saveOrUpdate")
	@ResponseBody
	public Object saveOrUpdate(HttpServletRequest request) throws Exception {
		log.info("start[ArticleNodesController.saveOrUpdate]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990601-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		String articleNodes = request.getParameter("articleNodes");
		ResultObject resultObject = null;
		if (!StringUtil.isEmpty(articleNodes)) {
			resultObject = otherService.doService(new RequestObject(headObject, articleNodes));
		}
		log.info("end[ArticleNodesController.saveOrUpdate]");
		return resultObject;
	}
    	
    /**
     * @Title:  getArticleNodesDetail  
     * @Description:  TODO(获取指定对象)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-6-18 下午2:20:49  
     * @version 1.0.0 
     * @param @param request
     * @param @param catId
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
	@RequestMapping("/getArticleNodesDetail")
	@ResponseBody
	public Object getArticleNodesDetail(HttpServletRequest request, Integer nodeId) throws Exception {
		log.info("start[ArticleNodesController.getArticleNodesDetail]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990601-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = otherService.doService(new RequestObject(headObject, nodeId));
		log.info("end[ArticleNodesController.getArticleNodesDetail]");
		return resultObject;
	}
	
	/**
	 * @Title:  ifPub  
	 * @Description:  TODO(修改发布状态)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-6-24 下午6:28:49  
	 * @version 1.0.0 
	 * @param @param request
	 * @param @param catId
	 * @param @return
	 * @param @throws Exception
	 * @return Object  返回类型 
	 * @throws
	 */
	@RequestMapping("/ifPub")
	@ResponseBody
	public Object ifPub(HttpServletRequest request, Integer nodeId, Integer ifPub) throws Exception {
		log.info("start[ArticleNodesController.ifPub]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990601-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = null;
		if(nodeId!=null&&nodeId!=0 && ifPub!=null){
			if(ifPub!=0 && ifPub!=1){
				ifPub = 1;
			}
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("nodeId", nodeId);
			jsonObject.put("ifPub", ifPub);
			resultObject = otherService.doService(new RequestObject(headObject, jsonObject));
		}else{
			resultObject = new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject("{result:false}"));
		}
		log.info("end[ArticleNodesController.ifPub]");
		return resultObject;
	}
	
	/**
	 * @Title:  delete  
	 * @Description:  TODO(删除文章栏目)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-7-3 上午11:33:56  
	 * @version 1.0.0 
	 * @param @param request
	 * @param @param nodeId
	 * @param @return
	 * @param @throws Exception
	 * @return Object  返回类型 
	 * @throws
	 */
	@RequestMapping("/deleteCate")
	@ResponseBody
	public Object deleteCate(HttpServletRequest request, Integer nodeId) throws Exception {
		log.info("start[ArticleNodesController.deleteCate]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990601-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = otherService.doService(new RequestObject(headObject, nodeId));
		log.info("end[ArticleNodesController.deleteCate]");
		return resultObject;
	}
//    
//	/**
//	 * @Title:  deleteCate  
//	 * @Description:  TODO(删除违规类型)  
//	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
//	 * @date 2015-6-3 下午2:23:10  
//	 * @version 1.0.0 
//	 * @param @param request
//	 * @param @param catId
//	 * @param @return
//	 * @param @throws Exception
//	 * @return Object  返回类型 
//	 * @throws
//	 */
//	@RequestMapping("/deleteCate")
//	@ResponseBody
//	public Object deleteCate(HttpServletRequest request, Integer catId) throws Exception {
//		log.info("start[StoreViolationCatController.deleteCate]");
//		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030601-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
//		ResultObject resultObject = memberService.doService(new RequestObject(headObject, catId));
//		log.info("end[StoreViolationCatController.deleteCate]");
//		return resultObject;
//	}
//	
//	/**
//	 * @Title:  findChildViolationCatList  
//	 * @Description:  TODO(查询除第一级分类外的违规类型)  
//	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
//	 * @date 2015-6-9 下午4:49:03  
//	 * @version 1.0.0 
//	 * @param @param request
//	 * @param @return
//	 * @param @throws Exception
//	 * @return Object  返回类型 
//	 * @throws
//	 */
//	@RequestMapping("/findChildViolationCatList")
//    @ResponseBody
//    public Object findChildViolationCatList(HttpServletRequest request) throws Exception {
//        log.info("start[StoreViolationCatController.findChildViolationCatList]");
//		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030601-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
//		ResultObject resultObject = memberService.doService(new RequestObject(headObject, null));
//        log.info("end[StoreViolationCatController.findChildViolationCatList]");
//        return resultObject.getContent();
//    }
}
