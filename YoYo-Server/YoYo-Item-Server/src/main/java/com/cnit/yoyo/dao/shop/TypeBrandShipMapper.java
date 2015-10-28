package com.cnit.yoyo.dao.shop;

import java.util.List;

import com.cnit.yoyo.model.goods.dto.TypeBrandShipDTO;
import com.cnit.yoyo.model.shop.TypeBrandShip;
import com.cnit.yoyo.model.shop.TypeBrandShipKey;
/**
 * 商品分类与品牌的关系对象
 * @author Administrator
 *
 */
public interface TypeBrandShipMapper {

    int deleteByPrimaryKey(Integer brandId);

    int insert(TypeBrandShip record);

    int insertSelective(TypeBrandShip record);

    TypeBrandShip selectByPrimaryKey(TypeBrandShipKey key);

    int updateByPrimaryKeySelective(TypeBrandShip record);

    int updateByPrimaryKey(TypeBrandShip record);

    /**
	 * 
	 * @Title: findTypeBrandList 
	 * @Description: 通过品牌id查找品牌与分类的关系
	 * @param @param brandId
	 * @param @return    设定文件 
	 * @author xiaox
	 * @date 2015-3-25 下午7:47:49
	 */
	List<TypeBrandShipDTO> findTypeBrandList(Integer brandId);
	
	/**
	 * 
	 * @Title: deleteByBrandIds 
	 * @Description:删除品牌与分类的关系 
	 * @param @param brandIds
	 * @param @return    设定文件 
	 * @author xiaox
	 * @date 2015-3-26 上午9:18:25
	 */
	int deleteByBrandIds(Integer [] brandIds);
	
	/**
	 * 
	 * @Description: 通过分类id查找品牌 
	 * @param @param brandId
	 * @param @return
	 * @author xiaox
	 * @date 2015-3-28 下午2:58:20
	 */
	List<TypeBrandShipDTO> findTypeBrandByCateId(List<Integer> brandId);
	List<TypeBrandShipDTO> findShip(TypeBrandShip identifier);
}