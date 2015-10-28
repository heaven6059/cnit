package com.cnit.yoyo.article.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.article.dto.ArticleQryDto;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;

/**
 * @description <文章页面处理>
 * @detail <主要用于返回文章节点、内容>
 * @author <a href="jpzhou@cnit.com">周加平</a>
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/articleMem")
public class ArticleController {

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private RemoteService otherService;

    @RequestMapping(value = "/findNodesByParentId", method = RequestMethod.GET)
    @ResponseBody
    public ResultObject findNodesByParentId(HttpServletRequest request) {
        logger.info("start[ArticleController.findArticleNodesList]");
        HeadObject head = CommonHeadUtil.geneHeadObject(request, "990601-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_ANDROID);
        ResultObject result = null;
        try {
            String parentIdStr = request.getParameter("parentId");
            Integer parentId = 0;
            if (parentIdStr != null) {
                parentId = Integer.parseInt(parentIdStr);
            }
            result = otherService.doService(new RequestObject(head, parentId));
            result.getHead().setRetCode(ErrorCode.SUCCESS);
        } catch (Exception e) {
            result.getHead().setRetCode(ErrorCode.FAILURE);
            logger.error("获取文章节点异常", e);
        }
        return result;
    }
    @RequestMapping(value = "/findArticleNodesList", method = RequestMethod.GET)
    @ResponseBody
    public ResultObject findArticleNodesList(HttpServletRequest request) {
        logger.info("start[ArticleController.findArticleNodesList]");
        HeadObject head = CommonHeadUtil.geneHeadObject(request, "990601-06", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_ANDROID);
        ResultObject result = null;
        try {
            String parentIdStr = request.getParameter("parentId");
            Integer parentId = 0;
            if (parentIdStr != null) {
                parentId = Integer.parseInt(parentIdStr);
            }
            result = otherService.doService(new RequestObject(head, parentId));
            result.getHead().setRetCode(ErrorCode.SUCCESS);
        } catch (Exception e) {
            result.getHead().setRetCode(ErrorCode.FAILURE);
            logger.error("获取文章节点异常", e);
        }
        return result;
    }
    
    @RequestMapping(value = "/articleIndex", method = RequestMethod.GET)
    public ModelAndView articlePage(HttpServletRequest request, Integer nodeId,Integer parentNodeId) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("nodeId", nodeId);
        mav.addObject("parentNodeId",parentNodeId);
        mav.setViewName("pages/biz/article/articleIndex");
        return mav;
    }

    /**
     * @description <查询指定节点下的文章index列表>
     * @detail <方法详细描述>
     * @author <a href="jpzhou@cnit.com">周加平</a>
     * @version 1.0.0
     * @data 2015年7月16日
     * @param request
     * @param art
     * @return
     */
    @RequestMapping(value = "/articleIndexList", method = RequestMethod.GET)
    @ResponseBody
    public ResultObject articleIndexList(HttpServletRequest request, ArticleQryDto art) {
        logger.info("start[ArticleController.articleList]");
        HeadObject head = CommonHeadUtil.geneHeadObject(request, "990602-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_FRONT);
        Object content = null;
        try {
            ResultObject res = otherService.doService(new RequestObject(head, art));
            content = res.getContent();
            head = res.getHead();
        } catch (Exception e) {
            head.setRetCode(ErrorCode.FAILURE);
            logger.error("查询文章列表异常", e);
        }
        logger.info("end[ArticleController.articleList]");
        return new ResultObject(head, content);
    }

    /**
     * @description <查询文章内容>
     * @detail <根据articleId查询文章的内容>
     * @author <a href="jpzhou@cnit.com">周加平</a>
     * @version 1.0.0
     * @data 2015年7月16日
     * @param request
     * @param art
     * @return
     */
    @RequestMapping(value = "/articleBody", method = RequestMethod.GET)
    @ResponseBody
    public ResultObject article(HttpServletRequest request, Integer articleId) {
        logger.info("start[ArticleController.article]");
        HeadObject head = CommonHeadUtil.geneHeadObject(request, "990602-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_FRONT);
        Object content = null;
        try {
            ResultObject res = otherService.doService(new RequestObject(head, articleId));
            content = res.getContent();
            head = res.getHead();
        } catch (Exception e) {
            head.setRetCode(ErrorCode.FAILURE);
            logger.error("查询文章内容异常", e);
        }
        logger.info("end[ArticleController.article]");
        return new ResultObject(head, content);
    }
    
    
    /**
     * @Title:  index  
     * @Description:  文章首页
     * @author <a href="zjcai@chinacnit.com">蔡志杰</a> 
     * @date 2015-7-21 下午3:47:27  
     * @version 1.0.0 
     * @param @param request09
     * @param @param nodeId
     * @param @return
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/index")
    public Object findNodes(HttpServletRequest request, Integer nodeId){
        logger.info("start[ArticleController.findNodes]");
        HeadObject head = CommonHeadUtil.geneHeadObject(request, "990601-06", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_ANDROID);
        ResultObject result = null;
        try {
            result = otherService.doService(new RequestObject(head));
            System.out.println(JSON.toJSONString(result.getContent()));
            request.setAttribute("list", result.getContent());
            request.setAttribute("selectedId", nodeId);
        } catch (Exception e) {
        	e.printStackTrace();
            logger.error("获取文章节点异常", e);
        }
        logger.info("end[ArticleController.findNodes]");
        return "pages/biz/article/articleIndex";
    }
    
    @RequestMapping("/articleContent")
    @ResponseBody
    public Object getArticleContent(HttpServletRequest request, Integer articleId){
        logger.info("start[ArticleController.getArticleContent]");
        HeadObject head = CommonHeadUtil.geneHeadObject(request, "990602-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_ANDROID);
        ResultObject result = null;
        try {
            result = otherService.doService(new RequestObject(head,articleId));
        } catch (Exception e) {
        	e.printStackTrace();
            logger.error("获取文章异常", e);
        }
        logger.info("end[ArticleController.getArticleContent]");
        return result;
    }
    
}
