package com.cnit.yoyo.article.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.article.dto.ArticleDto;
import com.cnit.yoyo.model.article.dto.ArticleQryDto;
import com.cnit.yoyo.model.shop.Violation;
import com.cnit.yoyo.model.shop.dto.ViolationQryDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.StringUtil;

@Controller
@RequestMapping("/article")
public class ArticleControllerMP {

public static final Logger log = LoggerFactory.getLogger(ArticleNodesController.class);
    
	@Autowired
    public RemoteService otherService;
	
	/**
	 * @Title:  index  
	 * @Description:  TODO(文章管理)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-7-6 下午5:04:28  
	 * @version 1.0.0 
	 * @param @return
	 * @return String  返回类型 
	 * @throws
	 */
	@RequestMapping("/index")
    public String index() {
        return "pages/biz/article/article";
    }
	
	/**
	 * @Title:  findArticleList  
	 * @Description:  TODO(查询文章列表)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-7-6 下午5:51:36  
	 * @version 1.0.0 
	 * @param @param request
	 * @param @param dto
	 * @param @return
	 * @param @throws Exception
	 * @return Object  返回类型 
	 * @throws
	 */
    @RequestMapping("/findArticleList")
    @ResponseBody
    public Object findArticleList(HttpServletRequest request, ArticleQryDto dto) throws Exception {
        log.info("start[ArticleController.findArticleList]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990602-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = otherService.doService(new RequestObject(headObject,dto));
        log.info("end[ArticleController.findArticleList]");
        return resultObject;
    }
    
    /**
	 * @Title:  addArticle  
	 * @Description:  TODO(添加文章)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-7-8 下午4:34:01  
	 * @version 1.0.0 
	 * @param @return
	 * @param @throws Exception
	 * @return Object  返回类型 
	 * @throws
	 */
    @RequestMapping("/addArticle")
    public String addArticle() {
        return "pages/biz/article/articleAdd";
    }
    
    /**
     * @Title:  editArticle  
     * @Description:  TODO(编辑文章)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-7-9 上午11:26:33  
     * @version 1.0.0 
     * @param @param request
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/editArticle")
    public Object editArticle(HttpServletRequest request) throws Exception {
        log.info("start[ArticleController.editArticle]");
    	ModelAndView mv = new ModelAndView();
    	String idString = request.getParameter("id");
    	if(idString!=null && !"".equals(idString)){
    		Integer id = Integer.parseInt(idString);
    		if(id != null && id >= 1){
    			HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990602-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
                ResultObject resultObject = otherService.doService(new RequestObject(headObject,id));
                if(resultObject!=null&& resultObject.getContent()!=null){
                	ArticleDto article = new ArticleDto();
                	try{
                		article = (ArticleDto)resultObject.getContent();
                	}catch (Exception e) {
                		e.printStackTrace();
                		log.error(e.toString());
					}
                	//判断该文章是否存在或被删除
                	if(article!=null&&article.getId()!=null&&article.getId()>=1&&article.getDisabled()!=1){
                		mv.addObject("article", resultObject.getContent());
                	}
                }
    		}
    	}
    	mv.setViewName("pages/biz/article/articleAdd");
        log.info("end[ArticleController.editArticle]");
        return mv;
    }
	
	/**
	 * @Title:  saveOrUpdateArticle  
	 * @Description:  TODO(保存或更新文章)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-7-8 下午4:34:01  
	 * @version 1.0.0 
	 * @param @param request
	 * @param @param violation
	 * @param @return
	 * @param @throws Exception
	 * @return Object  返回类型 
	 * @throws
	 */
	@RequestMapping("/saveOrUpdateArticle")
	@ResponseBody
	public Object saveOrUpdateArticle(HttpServletRequest request, ArticleDto article) throws Exception {
		log.info("start[ArticleController.saveOrUpdateArticle]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990602-02", 
				GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = otherService.doService(new RequestObject(headObject, article));
		log.info("start[ArticleController.saveOrUpdateArticle]");
		return resultObject;
	}

	/**
	 * @Title:  deleteArticle  
	 * @Description:  TODO(删除文章)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-7-9 上午11:26:46  
	 * @version 1.0.0 
	 * @param @param request
	 * @param @param id
	 * @param @return
	 * @param @throws Exception
	 * @return Object  返回类型 
	 * @throws
	 */
	@RequestMapping("/deleteArticle")
	@ResponseBody
	public Object deleteArticle(HttpServletRequest request, @RequestParam(value = "id", required = true) List<Integer> id) throws Exception {
		log.info("start[ArticleController.deleteArticle]");
		ResultObject resultObject = new ResultObject(new HeadObject(ErrorCode.NO_DATA));
		if(id!=null&&id.size()>=1){
			HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990602-04", 
					GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
			resultObject = otherService.doService(new RequestObject(headObject,id));
		}
		log.info("start[ArticleController.deleteArticle]");
		return resultObject;
	}
	
}
