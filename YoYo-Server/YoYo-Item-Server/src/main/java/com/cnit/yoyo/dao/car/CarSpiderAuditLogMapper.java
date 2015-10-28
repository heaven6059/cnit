package com.cnit.yoyo.dao.car;

import com.cnit.yoyo.model.car.CarSpiderAuditLog;
import com.cnit.yoyo.model.car.CarSpiderAuditLogWithBLOBs;

public interface CarSpiderAuditLogMapper {
    int deleteByPrimaryKey(Integer id);

	int insert(CarSpiderAuditLogWithBLOBs record);

	int insertSelective(CarSpiderAuditLogWithBLOBs record);

	CarSpiderAuditLogWithBLOBs selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(CarSpiderAuditLogWithBLOBs record);

	int updateByPrimaryKeyWithBLOBs(CarSpiderAuditLogWithBLOBs record);

	int updateByPrimaryKey(CarSpiderAuditLog record);

}