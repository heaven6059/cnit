/**
 * 文 件 名   :  PictureService.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnitcloud.com">李明</a>
 * 创建时间  :  2015年4月3日 下午5:56:20
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.rmi.good.service;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.goods.PictureMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.goods.Picture;
import com.cnit.yoyo.model.member.dto.MemberCommentDetailDTO;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @ClassName: PictureService 
 * @Description:图片service
 * @author xiaox
 * @date 2015-4-13 下午1:38:13
 */
@Service("pictureService")
public class PictureService {
    public static final Logger log = LoggerFactory.getLogger(PictureService.class);
    @Autowired
    private PictureMapper pictureMapper;

    
    /**
     * 
     * @Description: 保存图片  
     * @param @param data
     * @param @return
     * @author xiaox
     * @date 2015-4-13 下午1:43:02
     */
    public Object insertPicture(Object data){
        HeadObject head = new HeadObject();
        String content = null;
           try{
               Picture pic = new Picture();
               pic.setPicturePath((String)data);
               pic.setModifyTime(new Date());
               //TODO pic.setCompanyId(companyId);
               pictureMapper.insertSelective(pic);
               head.setRetCode(ErrorCode.SUCCESS);
               content = pic.getPictureId()+";"+data;  //以分号分割id与路径
           }catch(Exception e){    
            e.printStackTrace();
               head.setRetCode(ErrorCode.FAILURE);
           }       
           return new ResultObject(head,content);
      }
    
    /**
     * @Title:  selectByCommentId  
     * @Description:  TODO(根据评论id查询图片)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-15 下午4:28:55  
     * @version 1.0.0 
     * @param @param data
     * @param @return
     * @return Object  返回类型 
     * @throws
     */
	public Object selectByCommentId(Object data) {
		JSONObject jsonObject = JSONObject.fromObject(data);
//    	Integer pageIndex = jsonObject.getInt("pageIndex");
//		Integer pageSize = jsonObject.getInt("pageSize");
		Integer commentId = jsonObject.getInt("commentId");
//		PageHelper.startPage(pageIndex, pageSize);
//		ResultPage<Picture> picPage = new ResultPage<Picture>(pictureMapper.selectByCommentId(commentId));
		List<Picture> picList = pictureMapper.selectByCommentId(commentId);
//		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONObject.fromObject(picPage));
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), picList);
	}
	
	/**
	 * @Title:  selectByProductId  
	 * @Description:  TODO(根据货品id查询货品图片)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-5-18 下午5:08:12  
	 * @version 1.0.0 
	 * @param @param data
	 * @param @return
	 * @return Object  返回类型 
	 * @throws
	 */
	public Object selectByProductId(Object data) {
		JSONObject jsonObject = JSONObject.fromObject(data);
    	List<Picture> pictureList = pictureMapper.selectByProductId((Integer)data);
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), com.alibaba.fastjson.JSONObject.toJSON(pictureList));
	}

}