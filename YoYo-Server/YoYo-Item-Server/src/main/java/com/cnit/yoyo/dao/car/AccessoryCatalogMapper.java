package com.cnit.yoyo.dao.car;

import com.cnit.yoyo.model.car.Accessory;
import com.cnit.yoyo.model.car.AccessoryCatalog;
import com.cnit.yoyo.model.car.AccessoryCatalogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccessoryCatalogMapper {
    int countByExample(AccessoryCatalogExample example);

    int deleteByExample(AccessoryCatalogExample example);

    int deleteByPrimaryKey(Integer catalogId);

    int insert(AccessoryCatalog record);

    int insertSelective(AccessoryCatalog record);

    List<AccessoryCatalog> selectByExample(AccessoryCatalogExample example);

    AccessoryCatalog selectByPrimaryKey(Integer catalogId);

    int updateByExampleSelective(@Param("record") AccessoryCatalog record, @Param("example") AccessoryCatalogExample example);

    int updateByExample(@Param("record") AccessoryCatalog record, @Param("example") AccessoryCatalogExample example);

    int updateByPrimaryKeySelective(AccessoryCatalog record);

    int updateByPrimaryKey(AccessoryCatalog record);

	List<AccessoryCatalog> selectByQueryParams(@Param("whereStmt")List<String> mqls, @Param("orderStmt")String orderByCause);
	
	List<AccessoryCatalog> selectAccessoryCatalog();
	
	List<AccessoryCatalog> findMaintainCatList(); //查找保养配件类别
}