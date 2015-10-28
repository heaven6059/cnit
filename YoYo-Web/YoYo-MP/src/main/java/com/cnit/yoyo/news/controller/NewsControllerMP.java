package com.cnit.yoyo.news.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.news.News;
import com.cnit.yoyo.model.news.NewsContent;
import com.cnit.yoyo.model.news.dto.NewsQueryDto;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;

@Controller
@RequestMapping("/news")
public class NewsControllerMP {
	
	public static final Logger logger = LoggerFactory.getLogger(NewsControllerMP.class);
	
	@Autowired
    public RemoteService otherService;
	
	@RequestMapping("/index")
	public Object index(){
		return "pages/biz/news/newsIndex";
	}
	
	@RequestMapping("/newsList")
	@ResponseBody
	public Object getNewsList(HttpServletRequest request,NewsQueryDto dto){
		logger.info("newsControllerMP.getNewsList------>start");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "991001-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        try {
			resultObject = otherService.doService(new RequestObject(headObject,dto));
		} catch (Exception e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		logger.info("newsControllerMP.getNewsList------>end");
		System.out.println(resultObject.toString());
		return resultObject.getContent();
	}
	
	/**
	 * @Title:  removeNews  
	 * @Description:  批量删除
	 * @author <a href="zjcai@chinacnit.com">蔡志杰</a> 
	 * @date 2015-8-17 下午5:19:21  
	 * @version 1.0.0 
	 * @param @param request
	 * @param @param dto
	 * @param @return
	 * @return Object  返回类型 
	 * @throws
	 */
	@RequestMapping("/removeNews")
	public Object removeNews(HttpServletRequest request,@RequestBody NewsQueryDto dto){
		logger.info("newsControllerMP.removeNews------>start");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "991001-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        List<Integer> ids = dto.getIds();
        if(null != ids && ids.size()>0){
        	try {
    			resultObject = otherService.doService(new RequestObject(headObject,ids));
    		} catch (Exception e) {
    			logger.error(e.toString());
    			e.printStackTrace();
    		}
        }else{
        	return new ResultObject(new HeadObject(ErrorCode.NO_DATA));
        }
		logger.info("newsControllerMP.removeNews------>end");
		return resultObject;
	}
	
	@RequestMapping("/toEditNews")
	public Object toEditNews(HttpServletRequest request,String type,Integer newsId){
		logger.info("newsControllerMP.toEditNews------>start");
		if("edit".equals(type) && newsId != null){
			HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "991001-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	        ResultObject resultObject = null;
	        try {
				resultObject = otherService.doService(new RequestObject(headObject,newsId));
				request.setAttribute("news", resultObject.getContent());
			} catch (Exception e) {
				logger.error(e.toString());
				e.printStackTrace();
			}
		}
		logger.info("newsControllerMP.toEditNews------>end");
		return "pages/biz/news/editNews";
	}
	
	@RequestMapping("/addNews")
	@ResponseBody
	public Object addNews(HttpServletRequest request,News news,NewsContent newsContent){
		logger.info("newsControllerMP.addNews------>start");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "991001-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        Date now = new Date();
        news.setPubtime(now);

        if(news.getHasContent() && null != newsContent){
            newsContent.setCreateTime(now);
        	news.setNewsContent(newsContent);
        }
    	try {
			resultObject = otherService.doService(new RequestObject(headObject,news));
		} catch (Exception e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		logger.info("newsControllerMP.addNews------>end");
		return resultObject;
	}

	/**
	 * @Title:  editNews  
	 * @Description:  编辑新闻及内容  
	 * @author <a href="zjcai@chinacnit.com">蔡志杰</a> 
	 * @date 2015-8-18 下午4:24:57  
	 * @version 1.0.0 
	 * @param @param request
	 * @param @param news
	 * @param @param newsContent
	 * @param @return
	 * @return Object  返回类型 
	 * @throws
	 */
	@RequestMapping("/editNews")
	@ResponseBody
	public Object editNews(HttpServletRequest request,News news,NewsContent newsContent){
		logger.info("newsControllerMP.editNews------>start");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "991001-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        Date now = new Date();
        news.setPubtime(now);

        if(news.getHasContent() && null != newsContent){
        	news.setNewsContent(newsContent);
        }else{
        	news.setNewsContent(null);
        }
    	try {
			resultObject = otherService.doService(new RequestObject(headObject,news));
		} catch (Exception e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		logger.info("newsControllerMP.editNews------>end");
		return resultObject;
	}
}
