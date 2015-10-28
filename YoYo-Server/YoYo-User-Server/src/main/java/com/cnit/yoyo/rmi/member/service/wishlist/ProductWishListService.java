package com.cnit.yoyo.rmi.member.service.wishlist;

import java.net.NetPermission;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.New;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.dao.member.MemberCommentMapper;
import com.cnit.yoyo.dao.member.ProductWishListMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.goods.GoodsWithBLOBs;
import com.cnit.yoyo.model.goods.ProductWithBLOBs;
import com.cnit.yoyo.model.member.ProductWishList;
import com.cnit.yoyo.model.member.dto.ProSimpleDTO;
import com.cnit.yoyo.model.member.dto.ProductWishListDTO;
import com.cnit.yoyo.model.order.Order;
import com.cnit.yoyo.model.order.dto.OrderQryDTO;
import com.cnit.yoyo.rmi.member.service.SmsService;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

/**  
* @Title: ProductWishListService.java
* @Package com.cnit.yoyo.rmi.member.service.wishlist
* @Description:商品收藏Serivce
* @Author 王鹏
* @date 2015-4-30 上午10:00:50
* @version V1.0  
*/
@Service("productWishListService")
public class ProductWishListService {
    private static final Log log = LogFactory.getLog(ProductWishListService.class);
	
	@Autowired
	private ProductWishListMapper wishListMapper;
	@Autowired
	private MemberCommentMapper memberCommentMapper;
	
	/**
	  * @description <b>查询会员收藏的商品</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-4-21
	  * @param @param data
	  * @param @return
	  * @return Object
	*/
	public Object qryProductWishList(Object data) {
		HeadObject head = new HeadObject();
		ResultPage<ProductWishListDTO> result=null;
		try {
			JSONObject paramJSON=JSON.parseObject((String) data);
			Integer page = paramJSON.containsKey("page")&&paramJSON.getInteger("page")>0?paramJSON.getIntValue("page"):GlobalStatic.DEFAULT_PAGE_INDEX;
			Integer rows = paramJSON.containsKey("rows")&&paramJSON.getInteger("rows")>0?paramJSON.getIntValue("rows"):GlobalStatic.DEFAULT_PAGE_SIZE_10;
			PageHelper.startPage(page, rows);
			result = new ResultPage<ProductWishListDTO>(this.wishListMapper.selectWishListByMemberId(paramJSON.getLong("memberId")));
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head,result);
	}
	
	/**
	  * @description <b>删除关注的商品</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-4
	  * @param @param data
	  * @param @return
	  * @return Object
	*/
	public Object deleteWishList(Object data){
		log.info("start[ProductWishListService.deleteWishList]");
		HeadObject head = new HeadObject();
		try {
			this.wishListMapper.deleteWishList((Integer[]) data);
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error("删除收藏商品信息失败",e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		log.info("end[ProductWishListService.deleteWishList]");
		return head;
	}
	
	/**
	 * @Title:  selectProductByMemberId  
	 * @Description:  TODO(分页查询关注的商品)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-5-27 下午2:03:18  
	 * @version 1.0.0 
	 * @param @param data
	 * @param @return
	 * @return Object  返回类型 
	 * @throws
	 */
	public Object selectProductByMemberId(Object data){
		log.info("start[ProductWishListService.selectProductByMemberId]");
		net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(data);
		Long memberId = jsonObject.getLong("memberId");
		Integer pageIndex = jsonObject.getInt("pageIndex");
		Integer pageSize = jsonObject.getInt("pageSize");
		PageHelper.startPage(pageIndex, pageSize);
		ResultPage<ProSimpleDTO> page = new ResultPage(wishListMapper.selectProductByMemberId(memberId));
		if(page.getRows()!=null&&page.getRows().size()>=1){
			for(int i = 0; i<page.getRows().size() ; i++){
				page.getRows().get(i).setCommentCount(memberCommentMapper.selectOrderCommentCountByProductId(page.getRows().get(i).getProductId().intValue()));
				page.getRows().get(i).setPriceDouble(page.getRows().get(i).getPrice().doubleValue());
			}
		}
		log.info("end[ProductWishListService.selectProductByMemberId]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.toJSON(page));
	}
	
	/**
	 * @Title:  saveProductWishList  
	 * @Description:  TODO(保存我的关注)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-5-27 下午7:08:45  
	 * @version 1.0.0 
	 * @param @param data
	 * @param @return
	 * @return Object  返回类型 
	 * @throws
	 */
	public Object saveProductWishList(Object data){
//		log.info("start[ProductWishListService.saveProductWishList]");
//		net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(data);
//		Integer memberId = jsonObject.getInt("memberId");
//		List<ProductWithBLOBs> productList = (List<ProductWithBLOBs>)net.sf.json.JSONArray.toCollection(net.sf.json.JSONArray.fromObject(jsonObject.get("productList")), ProductWithBLOBs.class) ;
//		if(productList!=null&&productList.size()>=1){
//			ProductWishList productWishList = new ProductWishList();
//			productWishList.setMemberId(memberId.longValue());
//			productWishList.setWishlistDate(new Date(System.currentTimeMillis()));
//			List<Integer> productIdList = new ArrayList<Integer>();
//			for(int i =0;i <productList.size();i++){
//				productIdList.add(productList.get(i).getProductId());
//			}
//			List<ProductWishList> list = wishListMapper.selectByMemberIdAndProId(memberId, productIdList);
//			List<Integer> productIdListFromDB = new ArrayList<Integer>();
//			if(list!=null&&list.size()>=1){
//				for(int i =0;i<list.size();i++){
//					productIdListFromDB.add(list.get(i).getProductId().intValue());
//				}
//			}
//			for(int i =0;i <productList.size();i++){
//				if(!productIdListFromDB.contains(productList.get(i).getProductId())){
//					productWishList.setProductId(productList.get(i).getProductId().longValue());
//					wishListMapper.insertSelective(productWishList);
//				}
//			}
//		}
//		log.info("end[ProductWishListService.saveProductWishList]");
//		return new ResultObject(new HeadObject(ErrorCode.SUCCESS));
		
		log.info("start[ProductWishListService.saveProductWishList]");
		net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(data);
		Integer memberId = jsonObject.getInt("memberId");
		List<Integer> productIdList = (List<Integer>)net.sf.json.JSONArray.toCollection(net.sf.json.JSONArray.fromObject(jsonObject.get("productIdList")), Integer.class) ;
		if(productIdList!=null&&productIdList.size()>=1){
			ProductWishList productWishList = new ProductWishList();
			productWishList.setMemberId(memberId.longValue());
			productWishList.setWishlistDate(new Date(System.currentTimeMillis()));
			for(int i =0;i <productIdList.size();i++){
				productWishList.setProductId(productIdList.get(i).longValue());
				wishListMapper.insertSelective(productWishList);
			}
		}
		log.info("end[ProductWishListService.saveProductWishList]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS));
	}
	
	/**
	 * @Title:  selectByMemberIdAndProId  
	 * @Description:  TODO(这里用一句话描述这个方法的作用)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-6-1 上午10:35:23  
	 * @version 1.0.0 
	 * @param @param data
	 * @param @return
	 * @return Object  返回类型 
	 * @throws
	 */
	public Object selectByMemberIdAndProId(Object data){
		log.info("start[ProductWishListService.selectByMemberIdAndProId]");
		net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(data);
		Integer memberId = jsonObject.getInt("memberId");
		List<Integer> productIdList = (List<Integer>)net.sf.json.JSONArray.toCollection(net.sf.json.JSONArray.fromObject(jsonObject.get("productIdList")), Integer.class) ;
		List<ProductWishList> list = null;
		if(productIdList!=null&&productIdList.size()>=1){
			list = wishListMapper.selectByMemberIdAndProId(memberId, productIdList);
		}
		log.info("end[ProductWishListService.selectByMemberIdAndProId]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONArray.toJSON(list));
	}
}
