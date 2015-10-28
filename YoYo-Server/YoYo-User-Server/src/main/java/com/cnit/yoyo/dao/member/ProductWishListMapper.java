package com.cnit.yoyo.dao.member;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cnit.yoyo.model.member.ProductWishList;
import com.cnit.yoyo.model.member.dto.ProSimpleDTO;
import com.cnit.yoyo.model.member.dto.ProductWishListDTO;

public interface ProductWishListMapper {

	int deleteByPrimaryKey(Integer id);
	 
	int insert(ProductWishList record);
	 
	int insertSelective(ProductWishList record);

	ProductWishList selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ProductWishList record);
	 
	int updateByPrimaryKey(ProductWishList record);
	
	List<ProductWishListDTO> selectWishListByMemberId(Long memberId);
	
	int deleteWishList(Integer [] id);
	
	/**
	 * @Title:  selectProductByMemberId  
	 * @Description:  TODO(根据用户id倒序查询关注的商品)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-5-27 下午2:23:52  
	 * @version 1.0.0 
	 * @param @param memberId
	 * @param @return
	 * @return List<ProWishDTO>  返回类型 
	 * @throws
	 */
	List<ProSimpleDTO> selectProductByMemberId(Long memberId);
	
	/**
	 * @Title:  selectByMemberIdAndProId  
	 * @Description:  TODO(根据用户id和货品id查询对象)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-5-27 下午7:52:34  
	 * @version 1.0.0 
	 * @param @param memberId
	 * @param @param proIdList
	 * @param @return
	 * @return List<ProductWishList>  返回类型 
	 * @throws
	 */
	List<ProductWishList> selectByMemberIdAndProId(@Param("memberId")Integer memberId, @Param("list")List<Integer> proIdList);
	
}