package com.cnit.yoyo.dao.member;

import java.util.List;
import java.util.Map;

import com.cnit.yoyo.model.member.ProductViewHistory;
import com.cnit.yoyo.model.member.dto.ProductViewHistoryDTO;
import com.cnit.yoyo.model.member.dto.ProductViewHistoryQryDTO;

public interface ProductViewHistoryMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(ProductViewHistory record);

	int insertSelective(ProductViewHistory record);

	ProductViewHistory selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ProductViewHistory record);

	int updateByPrimaryKey(ProductViewHistory record);
	
	int deleteProductViewHistory(Map<String, Object> params);
	
	/**
	  * @description <b>查询商品浏览历史</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-18
	  * @param @param memberId
	  * @param @return
	  * @return List<ProductViewHistoryDTO>
	*/
	List<ProductViewHistoryDTO> selectProductViewHistory(ProductViewHistoryQryDTO qryDTO);
	
	/**
	 * @description <b>查询当前卖家被浏览的商品信息</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-8-13
	 * @param qryDTO
	 * @return
	 * List<ProductViewHistoryDTO>
	*/
	List<ProductViewHistoryDTO> selectShopProductViewHistory(ProductViewHistoryQryDTO qryDTO);
	
}