package com.cnit.yoyo.dao.goods;

import com.cnit.yoyo.dto.TreeNode;
import com.cnit.yoyo.model.goods.GoodsCat;
import com.cnit.yoyo.model.goods.GoodsCatExample;
import com.cnit.yoyo.model.goods.GoodsCatWithBLOBs;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsCatMapper {
	int countByExample(GoodsCatExample example);

	int deleteByExample(GoodsCatExample example);

	int deleteByPrimaryKey(Integer catId);

	int insert(GoodsCatWithBLOBs record);

	int insertSelective(GoodsCatWithBLOBs record);

	List<GoodsCatWithBLOBs> selectByExampleWithBLOBs(GoodsCatExample example);

	List<GoodsCat> selectByExample(GoodsCatExample example);

	GoodsCatWithBLOBs selectByPrimaryKey(Integer catId);

	int updateByExampleSelective(@Param("record") GoodsCatWithBLOBs record, @Param("example") GoodsCatExample example);

	int updateByExampleWithBLOBs(@Param("record") GoodsCatWithBLOBs record, @Param("example") GoodsCatExample example);

	int updateByExample(@Param("record") GoodsCat record, @Param("example") GoodsCatExample example);

	int updateByPrimaryKeySelective(GoodsCatWithBLOBs record);

	int updateByPrimaryKeyWithBLOBs(GoodsCatWithBLOBs record);

	int updateByPrimaryKey(GoodsCat record);

	/**
	 * @description <b>获取所有主分类</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-5-6
	 * @param @return
	 * @return List<GoodsCatWithBLOBs>
	 */
	List<GoodsCatWithBLOBs> selectAllMainCategory();

	/**
	 * @description <b>根据分类Id集合查询分类信息</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-5-7
	 * @param @param list
	 * @param @return
	 * @return List<GoodsCatWithBLOBs>
	 */
	List<GoodsCatWithBLOBs> selectCategoryByIds(Long [] ids);

	/**
	 * 
	 * @description 获取指定分类id的所有上级分类id
	 * @detail <方法详细描述>
	 * @author <a href="xiaoxiang@cnit.com">肖湘</a>
	 * @version 1.0.0
	 * @data 2015-5-9
	 * @param id
	 * @return
	 */
	List<Integer> getParentIds(Integer id);

	/**
	 * @description <b>查询保养分类信息</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-5-7
	 * @param @param list
	 * @param @return
	 * @return List<GoodsCatWithBLOBs>
	 */
	List<GoodsCatWithBLOBs> selectMaintainCategory(String identifier);
	
	/**
	 * @description <b>查询官方保养分类信息</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-5-7
	 * @param @param list
	 * @param @return
	 * @return List<GoodsCatWithBLOBs>
	 */
	List<GoodsCatWithBLOBs> selectOfficialMaintainCategory(String identifier);
	
	/**
	 * @description <b>查询自选保养信息</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-5-7
	 * @param @param list
	 * @param @return
	 * @return List<GoodsCatWithBLOBs>
	 */
	List<GoodsCatWithBLOBs> selectOptionalMaintainCategory();
	
	

	/**
	 * @description <b>根据identifier查信息</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-5-11
	 * @param @param identifier
	 * @param @return
	 * @return List<GoodsCatWithBLOBs>
	 */
	List<TreeNode> selectMaintainCategoryTree(String identifier);
	
	
	/**
	 * 查找自选保养分类树
	 * @param identifier
	 * @return
	 */
	List<TreeNode> selectOptionalMaintainCategoryTree(String identifier);

	List<TreeNode> selectAllCategoryTree();

	int increaseSubcatCount(@Param("catId") Integer parentCatId);

	int decreaseSubcatCount(@Param("catId") Integer parentCatId);
	
	List<GoodsCat> findCategoryTree(@Param("identifier")String identifier);
	
	List<GoodsCat> selectByPid(@Param("pid") Integer pid);
	List<GoodsCat> selectNoHiddenByPid(@Param("pid") Integer pid);
	
	List<GoodsCat> selectCategory(GoodsCat goodsCat);
	//通过店铺id查找店铺经营范围，然后根据经营范围查找商品分类
	List<GoodsCat> findGoodsCatByCompanyId(Integer companyId);
}