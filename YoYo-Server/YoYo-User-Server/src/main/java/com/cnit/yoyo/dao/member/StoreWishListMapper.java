package com.cnit.yoyo.dao.member;

import java.util.List;
import java.util.Map;

import com.cnit.yoyo.model.member.StoreWishList;
import com.cnit.yoyo.model.member.dto.FocusStoreQryDTO;
import com.cnit.yoyo.model.member.dto.StoreWishListDTO;

public interface StoreWishListMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_store_wishlist
	 * @mbggenerated  Thu Apr 30 09:41:43 CST 2015
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_store_wishlist
	 * @mbggenerated  Thu Apr 30 09:41:43 CST 2015
	 */
	int insert(StoreWishList record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_store_wishlist
	 * @mbggenerated  Thu Apr 30 09:41:43 CST 2015
	 */
	int insertSelective(StoreWishList record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_store_wishlist
	 * @mbggenerated  Thu Apr 30 09:41:43 CST 2015
	 */
	StoreWishList selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_store_wishlist
	 * @mbggenerated  Thu Apr 30 09:41:43 CST 2015
	 */
	int updateByPrimaryKeySelective(StoreWishList record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_store_wishlist
	 * @mbggenerated  Thu Apr 30 09:41:43 CST 2015
	 */
	int updateByPrimaryKey(StoreWishList record);
	
	List<StoreWishListDTO> selectWishListByMemberId(Long memberId);
	
	List<StoreWishListDTO> selectShopStoreWishList(FocusStoreQryDTO qryDTO);
	
	int deleteStoreWishList(Map<String, Object> map);
	
	Integer checkMemberFocus(StoreWishList wishList);
	
	/**
	  * @description <b>修改收藏次数</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-7-1
	  * @param @param companyId
	  * @param @return
	  * @return int
	*/
	int addFavCount(Long companyId);
	
	/**
	 * @description <b>消减收藏次数</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015年8月5日
	 * @param companyId
	 * @return
	 * int
	*/
	int subtractFavCount(Map<String, Object> map);
}