package com.cnit.yoyo.rmi.member.service;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.member.TagsMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.dto.MemberCommentDetailDTO;
import com.cnit.yoyo.model.member.dto.TagsDTO;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 
 * @ClassName: TagsService  
 * @Description: TODO(标签对象的增删改查)  
 * @detail <详细说明>
 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
 * @date 2015-5-22 下午12:03:20  
 * @version 1.0.0
 */
@Service("tagsService")
public class TagsService {
	
	private static final Log log = LogFactory.getLog(TagsService.class);

	@Autowired
    private TagsMapper tagsMapper;
   

	/**
	 * @Title:  selectTagsByCommentGoodsId  
	 * @Description:  TODO(根据商品id查询热评评论标签)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-5-22 下午12:05:24  
	 * @version 1.0.0 
	 * @param @param data
	 * @param @return
	 * @return Object  返回类型 
	 * @throws
	 */
	public Object selectTopCommentTagsByGoodsId(Object data){
    	log.info("start[tagsService.selectTopCommentTagsByGoodsId]");
    	JSONObject jsonObject = JSONObject.fromObject(data);
    	Integer pageIndex = jsonObject.getInt("pageIndex");
		Integer pageSize = jsonObject.getInt("pageSize");
		Integer goodsId = jsonObject.getInt("goodsId");
		PageHelper.startPage(pageIndex, pageSize);
		ResultPage<TagsDTO> tagsPage = new ResultPage(tagsMapper.selectTopCommentTagsByGoodsId(goodsId));
    	log.info("end[tagsService.selectTopCommentTagsByGoodsId]");
    	return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject(tagsPage));
    }
}
