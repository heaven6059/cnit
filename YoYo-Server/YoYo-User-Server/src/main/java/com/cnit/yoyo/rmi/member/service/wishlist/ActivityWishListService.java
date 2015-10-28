package com.cnit.yoyo.rmi.member.service.wishlist;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.dao.member.ActivityWishListMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.dto.ActivityWishListDTO;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

/**  
* @Title: ActivityWishListService.java
* @Package com.cnit.yoyo.rmi.member.service.wishlist
* @Description: 会员关注活动Service
* @Author 王鹏
* @date 2015-5-4 上午10:45:31
* @version V1.0  
*/ 
@SuppressWarnings("unchecked")
@Service("activityWishListService")
public class ActivityWishListService {
	  private static final Log log = LogFactory.getLog(ProductWishListService.class);
	  
	  @Autowired
	  private ActivityWishListMapper activityWishListMapper;
	  
	  /**
		  * @description <b>查询会员收藏的活动信息</b>
		  * @author 王鹏
		  * @version 1.0.0
		  * @data 2015-4-21
		  * @param @param data
		  * @param @return
		  * @return Object
		*/
		public Object qryActivityWishList(Object data) {
			HeadObject head = new HeadObject();
			ResultPage<ActivityWishListDTO> result=null;
			try {
				JSONObject paramJSON=JSON.parseObject((String) data);
				Integer page = paramJSON.containsKey("page")&&paramJSON.getInteger("page")>0?paramJSON.getIntValue("page"):GlobalStatic.DEFAULT_PAGE_INDEX;
				PageHelper.startPage(page, GlobalStatic.DEFAULT_PAGE_SIZE_5);
				result = new ResultPage<ActivityWishListDTO>(this.activityWishListMapper.selectWishListByMemberId(paramJSON.getLong("memberId")));
				head.setRetCode(ErrorCode.SUCCESS);
			} catch (Exception e) {
				log.error(e);
				e.printStackTrace();
				head.setRetCode(ErrorCode.FAILURE);
			}
			return new ResultObject(head,result);
		}
		
		
		/**
		  * @description <b>删除关注的活动</b>
		  * @author 王鹏
		  * @version 1.0.0
		  * @data 2015-5-4
		  * @param @param data
		  * @param @return
		  * @return Object
		*/
		public Object deleteActivityWishList(Object data){
			log.info("start[ActivityWishListService.deleteActivityWishList]");
			HeadObject head = new HeadObject();
			try {
				
				this.activityWishListMapper.deleteWishList((Map<String, Object>) data);
				head.setRetCode(ErrorCode.SUCCESS);
			} catch (Exception e) {
				log.error("删除收藏的活动信息失败",e);
				e.printStackTrace();
				head.setRetCode(ErrorCode.FAILURE);
			}
			log.info("end[ActivityWishListService.deleteActivityWishList]");
			return head;
		}
}
