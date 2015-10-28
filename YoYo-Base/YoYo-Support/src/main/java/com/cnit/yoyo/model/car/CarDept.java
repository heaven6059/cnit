package com.cnit.yoyo.model.car;

import java.io.Serializable;

/**
 * @ClassName: CarDept
 * @Description: 车系
 * @author xiaox
 * @date 2015-3-26 下午4:10:20
 */

public class CarDept implements Serializable {
    private static final long serialVersionUID = -3391751972367924564L;
    private Integer carDeptId;
    private Integer factoryid;
    private String carDeptName;
    private String keyword;
    private Integer gradeId;
    private Integer status;

    public Integer getCarDeptId() {
        return carDeptId;
    }

    public void setCarDeptId(Integer carDeptId) {
        this.carDeptId = carDeptId;
    }

    public Integer getFactoryid() {
        return factoryid;
    }

    public void setFactoryid(Integer factoryid) {
        this.factoryid = factoryid;
    }

    public String getCarDeptName() {
        return carDeptName;
    }

    public void setCarDeptName(String carDeptName) {
        this.carDeptName = carDeptName;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

}