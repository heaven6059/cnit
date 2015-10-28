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
import com.cnit.yoyo.dao.member.StoreWishListMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.StoreWishList;
import com.cnit.yoyo.model.member.dto.FocusStoreQryDTO;
import com.cnit.yoyo.model.member.dto.StoreWishListDTO;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

/**  
* @Title: StoreWishListService.java
* @Package com.cnit.yoyo.rmi.member.service.wishlist
* @Description: 店铺收藏service
* @Author 王鹏
* @date 2015-4-30 上午10:01:11
* @version V1.0  
*/ 
@Service("storeWishListService")
public class StoreWishListService {
	 private static final Log log = LogFactory.getLog(StoreWishListService.class);
		
		@Autowired
		private StoreWishListMapper wishListMapper;
		
		/**
		  * @description <b>查询会员收藏的店铺</b>
		  * @author 王鹏
		  * @version 1.0.0
		  * @data 2015-4-21
		  * @param @param data
		  * @param @return
		  * @return Object
		*/
		public Object qryStoreWishList(Object data) {
			HeadObject head = new HeadObject();
			ResultPage<StoreWishListDTO> result=null;
			try {
				JSONObject paramJSON=JSON.parseObject((String) data);
				Integer page = paramJSON.containsKey("page")&&paramJSON.getInteger("page")>0?paramJSON.getIntValue("page"):GlobalStatic.DEFAULT_PAGE_INDEX;
				PageHelper.startPage(page, GlobalStatic.DEFAULT_PAGE_SIZE_10);
				result = new ResultPage<StoreWishListDTO>(this.wishListMapper.selectWishListByMemberId(paramJSON.getLong("memberId")));
				head.setRetCode(ErrorCode.SUCCESS);
			} catch (Exception e) {
				log.error(e);
				e.printStackTrace();
				head.setRetCode(ErrorCode.FAILURE);
			}
			return new ResultObject(head,result);
		}
		
		
		
		/**
		 * @description <b>查询卖家查询店铺收藏记录</b>
		 * @author 王鹏
		 * @version 1.0.0
		 * @data 2015-8-13
		 * @param data
		 * @return
		 * Object
		*/
		public Object qryShopFocusStoreList(Object data) {
			HeadObject head = new HeadObject();
			ResultPage<StoreWishListDTO> result=null;
			try {
				FocusStoreQryDTO qryDTO=(FocusStoreQryDTO) data;
				PageHelper.startPage(qryDTO.getPage(), qryDTO.getRows());
				result = new ResultPage<StoreWishListDTO>(this.wishListMapper.selectShopStoreWishList(qryDTO));
				head.setRetCode(ErrorCode.SUCCESS);
			} catch (Exception e) {
				log.error(e);
				e.printStackTrace();
				head.setRetCode(ErrorCode.FAILURE);
			}
			return new ResultObject(head,JSON.toJSON(result));
		} 
		
		/**
		  * @description <b>删除店铺信息</b>
		  * @author 王鹏
		  * @version 1.0.0
		  * @data 2015-4-30
		  * @param @param data
		  * @param @return
		  * @return Object
		*/
		public Object deleteStoreWishList(Object data){
			log.info("start[StoreWishListService.deleteWishList]");
			HeadObject head = new HeadObject();
			try {
				Map<String, Object> map=(Map<String, Object>) data;
				this.wishListMapper.deleteStoreWishList(map);
				this.wishListMapper.subtractFavCount(map);
				head.setRetCode(ErrorCode.SUCCESS);
			} catch (Exception e) {
				log.error("删除收店铺信息失败",e);
				e.printStackTrace();
				head.setRetCode(ErrorCode.FAILURE);
			}
			log.info("end[StoreWishListService.deleteWishList]");
			return head;
		}
		
		/**
		  * @description <b>添加店铺关注</b>
		  * @author 王鹏
		  * @version 1.0.0
		  * @data 2015-6-10
		  * @param @param object
		  * @param @return
		  * @return Object
		*/
		public Object addStoreWishList(Object object){
			log.info("start[StoreWishListService.addStoreWishList]");
			HeadObject head = new HeadObject();
			JSONObject result=new JSONObject();
			try {
				StoreWishList record= (StoreWishList) object;
				Integer count = this.wishListMapper.checkMemberFocus(record);
				if(count>0){
					head.setRetCode(ErrorCode.FAILURE);
					result.put("msg", "您已关注该店铺,无需重复关注");
				}else{
					this.wishListMapper.insertSelective(record);
					this.wishListMapper.addFavCount(record.getCompanyId());
					head.setRetCode(ErrorCode.SUCCESS);
				}
			} catch (Exception e) {
				log.error("添加店铺关注失败",e);
				e.printStackTrace();
				head.setRetCode(ErrorCode.FAILURE);
			}
			log.info("end[StoreWishListService.addStoreWishList]");
			return new ResultObject(head, result);
		}	
}
