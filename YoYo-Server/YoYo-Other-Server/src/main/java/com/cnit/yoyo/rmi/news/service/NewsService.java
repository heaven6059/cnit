package com.cnit.yoyo.rmi.news.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.dao.news.NewsContentMapper;
import com.cnit.yoyo.dao.news.NewsMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.news.News;
import com.cnit.yoyo.model.news.NewsContent;
import com.cnit.yoyo.model.news.dto.NewsQueryDto;
import com.cnit.yoyo.util.StringUtil;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

@Service
@RequestMapping("/newsService")
public class NewsService {
	
	public static final Logger logger = LoggerFactory.getLogger(NewsService.class);
	
	@Autowired
	private NewsMapper newsMapper;
	
	@Autowired
	private NewsContentMapper newsContentMapper;
	
	/**
	 * @Title:  findNewsList  
	 * @Description:  查询新闻列表  
	 * @author <a href="zjcai@chinacnit.com">蔡志杰</a> 
	 * @date 2015-8-17 下午3:57:03  
	 * @version 1.0.0 
	 * @param @param data
	 * @param @return
	 * @return Object  返回类型 
	 * @throws
	 */
	public Object findNewsList(Object data){
		logger.info("NewsService.findNewsList------->start");
		HeadObject head = new HeadObject();
		NewsQueryDto dto = (NewsQueryDto)data;
		ResultPage<News> result=null;
		int pageNum = (null == dto.getPageNum() || dto.getPageNum() < 1)? GlobalStatic.DEFAULT_PAGE_INDEX : dto.getPageNum();
		int pageSize = (null == dto.getPageSize() || dto.getPageSize() < 1)? GlobalStatic.DEFAULT_PAGE_SIZE_10: dto.getPageSize();
		PageHelper.startPage(pageNum,pageSize);
		try{
			result = new ResultPage<News>(newsMapper.selectByQuery(dto));
			head.setRetCode(ErrorCode.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
			logger.error(e.toString());
		}
		logger.info("NewsService.findNewsList------->end");
		return new ResultObject(head,JSON.toJSONString(result));
	}
	
	/**
	 * @Title:  removeNews  
	 * @Description:  批量删除 
	 * @author <a href="zjcai@chinacnit.com">蔡志杰</a> 
	 * @date 2015-8-17 下午5:31:48  
	 * @version 1.0.0 
	 * @param @param data
	 * @param @return
	 * @return Object  返回类型 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public Object removeNews(Object data){
		logger.info("NewsService.removeNews------->start");
		HeadObject head = new HeadObject();
		List<Integer> ids = (List<Integer>)data;
		if(null != ids && ids.size() > 0){
			try{
				newsMapper.deleteBatch(ids);
				head.setRetCode(ErrorCode.SUCCESS);
			}catch (Exception e) {
				e.printStackTrace();
				logger.error(e.toString());
				head.setRetCode(ErrorCode.FAILURE);
			}
		}
		logger.info("NewsService.removeNews------->end");
		return new ResultObject(head);
	}
	
	/**
	 * @Title:  addNews  
	 * @Description:  新增新闻  
	 * @author <a href="zjcai@chinacnit.com">蔡志杰</a> 
	 * @date 2015-8-18 下午3:22:12  
	 * @version 1.0.0 
	 * @param @param data
	 * @param @return
	 * @return Object  返回类型 
	 * @throws
	 */
	public Object addNews(Object data){
		logger.info("NewsService.removeNews------->start");
		HeadObject head = new HeadObject();
		News news = (News)data;
		try{
			newsMapper.insertSelective(news);
			NewsContent content = news.getNewsContent();
			if(null != content){
				content.setNewsId(news.getNewsId());
				if(StringUtils.isNotBlank(content.getContent())){
					content.setLength(content.getContent().length());
				}
				newsContentMapper.insertSelective(content);
			}
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
			head.setRetCode(ErrorCode.FAILURE);
		}
		logger.info("NewsService.removeNews------->end");
		return new ResultObject(head);
	}
	
	/**
	 * @Title:  editNews  
	 * @Description:  编辑新闻 
	 * @author <a href="zjcai@chinacnit.com">蔡志杰</a> 
	 * @date 2015-8-18 下午4:32:42  
	 * @version 1.0.0 
	 * @param @param data
	 * @param @return
	 * @return Object  返回类型 
	 * @throws
	 */
	public Object editNews(Object data){
		logger.info("NewsService.editNews------->start");
		HeadObject head = new HeadObject();
		News news = (News)data;
		try{
			newsMapper.updateByPrimaryKeySelective(news);
			NewsContent content = news.getNewsContent();
			if(null == content){
				newsContentMapper.deleteByNewsId(news.getNewsId());
			}else{
				NewsContent newsContent = newsContentMapper.selectByNewsId(news.getNewsId());
				 Date now = new Date();
				if(null != newsContent){
					newsContent.setContent(content.getContent());
					newsContent.setLastModify(now);
					newsContentMapper.updateByPrimaryKeyWithBLOBs(newsContent);
				}else{
					content.setCreateTime(now);
					content.setLastModify(now);
					newsContentMapper.insertSelective(content);
				}
			}
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
			head.setRetCode(ErrorCode.FAILURE);
		}
		logger.info("NewsService.editNews------->end");
		return new ResultObject(head);
	}

	/**
	 * @Title:  findNewsById  
	 * @Description:  查询新闻
	 * @author <a href="zjcai@chinacnit.com">蔡志杰</a> 
	 * @date 2015-8-18 下午4:48:01  
	 * @version 1.0.0 
	 * @param @param data
	 * @param @return
	 * @return Object  返回类型 
	 * @throws
	 */
	public Object findNewsById(Object data){
		logger.info("NewsService.findNewsById------->start");
		HeadObject head = new HeadObject();
		Integer newsId = (Integer)data;
		News news = new News();
		try{
			news = newsMapper.selectByPrimaryKey(newsId);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
			head.setRetCode(ErrorCode.FAILURE);
		}
		logger.info("NewsService.findNewsById------->end");
		return new ResultObject(head,news);
	}
	
	public Object findIndexNews(Object data){
		logger.info("NewsService.findIndexNews------->start");
		HeadObject head = new HeadObject();
		Integer number = (Integer)data;
		List<News> newsList = new ArrayList<News>();
		try{
			newsList = newsMapper.selectIndexList(number);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
			e.printStackTrace();
			logger.error(e.toString());
		}
		logger.info("NewsService.findIndexNews------->start");
		return new ResultObject(head,JSON.toJSONString(newsList));
	}
}
