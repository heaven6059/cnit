package com.cnit.yoyo.rmi.article.service;

import java.util.Date;
import java.util.List;
import java.util.jar.JarOutputStream;

import javax.enterprise.inject.New;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.article.ArticleBodyMapper;
import com.cnit.yoyo.dao.article.ArticleIndexMapper;
import com.cnit.yoyo.dao.article.ArticleNodesMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.dto.BaseQryDTO;
import com.cnit.yoyo.model.article.ArticleBody;
import com.cnit.yoyo.model.article.ArticleBodyWithBLOBs;
import com.cnit.yoyo.model.article.ArticleIndex;
import com.cnit.yoyo.model.article.ArticleIndexExample;
import com.cnit.yoyo.model.article.ArticleNodes;
import com.cnit.yoyo.model.article.dto.ArticleDto;
import com.cnit.yoyo.model.article.dto.ArticleQryDto;
import com.cnit.yoyo.model.car.CarFactoryScope;
import com.cnit.yoyo.model.car.dto.CarFactoryScopeQryDTO;
import com.cnit.yoyo.model.shop.StoreViolationCat;
import com.cnit.yoyo.model.shop.StoreViolationCatExample;
import com.cnit.yoyo.model.shop.StoreViolationCatWithBLOBs;
import com.cnit.yoyo.model.shop.Violation;
import com.cnit.yoyo.model.shop.ViolationExample;
import com.cnit.yoyo.model.shop.dto.ViolationDTO;
import com.cnit.yoyo.model.shop.dto.ViolationQryDTO;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

/**
 * @ClassName: ViolationService  
 * @Description: TODO(页面管理--文章列表)  
 * @detail <详细说明>
 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
 * @date 2015-6-3 下午5:08:19  
 * @version 1.0.0
 */
@Service("articleService")
public class ArticleService {

	private static final Log log = LogFactory.getLog(ArticleService.class);
	
    @Autowired 
    private ArticleBodyMapper articleBodyMapper;
    
    @Autowired 
    private ArticleIndexMapper articleIndexMapper;
    
    @Autowired 
    private ArticleNodesMapper articleNodesMapper;
	
    /**
     * @Title:  selectArticle  
     * @Description:  TODO(查询文章列表)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-7-6 下午5:15:04  
     * @version 1.0.0 
     * @param @param data
     * @param @return
     * @return Object  返回类型 
     * @throws
     */
    public Object selectArticleByQryDto(Object data) 
	{
		log.info("start[ArticleService.selectArticleByQryDto]");
		ArticleQryDto dto = (ArticleQryDto)data;
      	PageHelper.startPage(dto.getPage(), dto.getRows());
      	ResultPage<ArticleDto> page = new ResultPage<ArticleDto>(articleBodyMapper.selectByQryDto(dto));
		log.info("end[ArticleService.selectArticleByQryDto]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), com.alibaba.fastjson.JSONObject.toJSON(page));
	}
    
    /**
     * @Title:  saveOrUpdateArticle  
     * @Description:  TODO(保存或更新文章)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-7-8 下午4:35:54  
     * @version 1.0.0 
     * @param @param data
     * @param @return
     * @return Object  返回类型 
     * @throws
     */
    public Object saveOrUpdateArticle(Object data) 
	{
		log.info("start[ArticleService.saveOrUpdateArticle]");
		HeadObject headObject = null;
		ArticleDto article = (ArticleDto) JSONObject.toBean(JSONObject.fromObject(data), ArticleDto.class);
		//判断违规类型是否存在
		ArticleNodes articleNodes = articleNodesMapper.selectByPrimaryKey(article.getNodeId());
		if(articleNodes!=null && articleNodes.getDisabled() !=1){
			//查询同名文章
			List<ArticleDto> sameTitleList = articleBodyMapper.selectByTitle(article.getTitle());
			Date now = new Date(System.currentTimeMillis());
			
			if(article.getId()!=null&&article.getId()>0){//更新
				ArticleDto articleFromDB = articleBodyMapper.selectById(article.getId());
				if(articleFromDB!=null&&articleFromDB.getId()!=null&&articleFromDB.getId()>=1&&articleFromDB.getDisabled()!=1){
					if(sameTitleList!=null&&sameTitleList.size()>=1){
						for(int i = 0 ; i<sameTitleList.size() ; i++){
							if(!sameTitleList.get(i).getId().equals(article.getId())){
								headObject = new HeadObject(ErrorCode.IS_EXIST);
							}
						}
					}
					//更新
					if(headObject==null){
						ArticleIndex index = new ArticleIndex();
						index.setArticleId(articleFromDB.getArticleId());
						index.setTitle(article.getTitle());
//						index.setPlatform(platform)
						index.setNodeId(article.getNodeId());
						index.setAuthor(article.getAuthor());
						index.setUptime(now);
						index.setIfpub(article.getIfpub());
						if(index.getIfpub()!=null&&index.getIfpub()==1 && articleFromDB.getPubtime()==null){
							index.setPubtime(now);
						}
						articleIndexMapper.updateByPrimaryKeySelective(index);
						ArticleBodyWithBLOBs body = new ArticleBodyWithBLOBs();
						body.setId(articleFromDB.getId());
						body.setContent(article.getContent());
						body.setLength(body.getContent()!=null?body.getContent().length():0);
						body.setLastModify(now);
						articleBodyMapper.updateByPrimaryKeySelective(body);
						headObject = new HeadObject(ErrorCode.SUCCESS);
					}
				}else{
					//该文章不存在或已被删除
					headObject= new HeadObject(ErrorCode.SPE002);
				}
				
			}else{//新增
				if(sameTitleList!=null&&sameTitleList.size()>=1){
					headObject = new HeadObject(ErrorCode.IS_EXIST);
				}else{
					ArticleIndex index = new ArticleIndex();
					index.setTitle(article.getTitle());
//					index.setPlatform(platform)
					index.setType(1);
					index.setNodeId(article.getNodeId());
					index.setAuthor(article.getAuthor());
					index.setUptime(now);
					index.setLevel(1);
					index.setIfpub(article.getIfpub());
					if(index.getIfpub()!=null&&index.getIfpub()==1){
						index.setPubtime(now);
					}
					index.setDisabled(0);
					articleIndexMapper.insertSelective(index);
					ArticleBodyWithBLOBs body = new ArticleBodyWithBLOBs();
					body.setContent(article.getContent());
					body.setLength(body.getContent()!=null?body.getContent().length():0);
					body.setCreateTime(now);
					body.setLastModify(now);
					body.setArticleId(index.getArticleId());
					articleBodyMapper.insertSelective(body);
					headObject = new HeadObject(ErrorCode.SUCCESS);
				}
			}
		}else{
			//所属栏目不存在
			headObject = new HeadObject(ErrorCode.NO_DATA);
		}
		log.info("end[ArticleService.saveOrUpdateArticle]");
		return headObject;
	}
    
    /**
     * @Title:  selectById  
     * @Description:  TODO(根据id查询文章)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-7-9 上午10:02:21  
     * @version 1.0.0 
     * @param @param data
     * @param @return
     * @return Object  返回类型 
     * @throws
     */
    public Object selectById(Object data){
    	log.info("start[ArticleService.selectById]");
		ArticleDto article = articleBodyMapper.selectById((Integer)data);
		log.info("end[ArticleService.selectById]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS),article);
    }
    /**
     * 
     *@description <根据articleId查询文章内容>
     *@detail <方法详细描述>
     *@author <a href="jpzhou@cnit.com">周加平</a>
     *@version 1.0.0
     *@data 2015年7月17日
     *@param data
     *@return
     */
    public Object selectByArticleId(Object data){
        log.info("start[ArticleService.selectByArticleId]");
        ArticleDto article = articleBodyMapper.selectByArticleId((Integer)data);
        log.info("end[ArticleService.selectByArticleId]");
        return new ResultObject(new HeadObject(ErrorCode.SUCCESS), com.alibaba.fastjson.JSONObject.toJSON(article));
    }
    
    /**
     * @Title:  deleteByIdList  
     * @Description:  TODO(根据id列表删除文章)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-7-9 上午11:28:08  
     * @version 1.0.0 
     * @param @param data
     * @param @return
     * @return Object  返回类型 
     * @throws
     */
	public Object deleteByIdList(Object data) {
		log.info("start[ArticleService.delectByIdList]");
		articleIndexMapper.updateDisabledByPrimaryKey((List<Integer>)data, 1);
		log.info("end[ArticleService.delectByIdList]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS));
	}
    
}
