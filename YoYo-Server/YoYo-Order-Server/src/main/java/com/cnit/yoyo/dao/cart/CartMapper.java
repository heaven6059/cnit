package com.cnit.yoyo.dao.cart;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cnit.yoyo.model.cart.Cart;
import com.cnit.yoyo.model.cart.CartExample;
import com.cnit.yoyo.model.cart.dto.CartDTO;
import com.cnit.yoyo.model.cart.dto.CartListDTO;

public interface CartMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cart_objects
     *
     * @mbggenerated Wed Apr 15 11:14:22 CST 2015
     */
    int countByExample(CartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cart_objects
     *
     * @mbggenerated Wed Apr 15 11:14:22 CST 2015
     */
    int deleteByExample(CartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cart_objects
     *
     * @mbggenerated Wed Apr 15 11:14:22 CST 2015
     */
    int deleteByPrimaryKey(Integer cartId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cart_objects
     *
     * @mbggenerated Wed Apr 15 11:14:22 CST 2015
     */
    int insert(Cart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cart_objects
     *
     * @mbggenerated Wed Apr 15 11:14:22 CST 2015
     */
    int insertSelective(Cart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cart_objects
     *
     * @mbggenerated Wed Apr 15 11:14:22 CST 2015
     */
    List<Cart> selectByExample(CartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cart_objects
     *
     * @mbggenerated Wed Apr 15 11:14:22 CST 2015
     */
    Cart selectByPrimaryKey(Integer cartId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cart_objects
     *
     * @mbggenerated Wed Apr 15 11:14:22 CST 2015
     */
    int updateByExampleSelective(@Param("record") Cart record, @Param("example") CartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cart_objects
     *
     * @mbggenerated Wed Apr 15 11:14:22 CST 2015
     */
    int updateByExample(@Param("record") Cart record, @Param("example") CartExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cart_objects
     *
     * @mbggenerated Wed Apr 15 11:14:22 CST 2015
     */
    int updateByPrimaryKeySelective(Cart record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cart_objects
     *
     * @mbggenerated Wed Apr 15 11:14:22 CST 2015
     */
    int updateByPrimaryKey(Cart record);
    
    /**
     * @Title: selectCartListByAccountId 
     * @Description: TODO(根据买家id查询购物车列表数据) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-9
     * @version 1.0.0 
     * @param accountId
     * @param @return    
     * @return List<CartListDTO>    返回类型 
     * @throws
     */
    List<CartListDTO> selectCartListByAccountId(Integer accountId);
    
    /**
     * @Title: selectCartListByProIdList 
     * @Description: TODO(根据货品id列表组装查询购物车商品列表) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-10
     * @version 1.0.0 
     * @param proIdList
     * @param @return    
     * @return List<CartListDTO>    返回类型 
     * @throws
     */
    List<CartListDTO> selectCartListByProIdList(Map<String, Object> param);
    
    /**
     * @Title: selectCartListByProIdAndMem 
     * @Description: TODO(根据货品id列表和买家id查询购物车商品列表) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-10
     * @version 1.0.0 
     * @param list
     * @param memberId
     * @param @return    
     * @return List<CartListDTO>    返回类型 
     * @throws
     */
    List<CartListDTO> selectCartListByProIdAndMem(@Param("list") List<Integer> list, @Param("memberId") Integer memberId);
    
    /**
     * @Title: delectCartByProIdAndMemberId 
     * @Description: TODO(根据买家id和货品id列表删除购物车商品) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-13
     * @version 1.0.0 
     * @param memberId
     * @param proIdList
     * @param @return    
     * @return int    返回类型 
     * @throws
     */
    int delectCartByProIdAndMemberId(@Param("memberId") Integer memberId, @Param("list") List<Integer> proIdList);
    
    /**
     * @Title: updateQuantityByProAndMem 
     * @Description: TODO(根据货品id和买家id更新购物车商品购买数量) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-14
     * @version 1.0.0 
     * @param cart
     * @param @return    
     * @return int    返回类型 
     * @throws
     */
    int updateQuantityByProAndMem(Cart cart);
    
    /**
     * @description <b>查询该商品是否已加入购物车</b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015年8月2日
     * @param cart
     * @return
     * Cart
    */
    Cart selectHasAddCart(Cart cart);
    
    List<CartDTO> selectGoodsByProductIds(Map<String, Object> param);
    
    List<CartDTO> selectMemberCart(Map<String, Object> param);
}