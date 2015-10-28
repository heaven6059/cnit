package com.cnit.yoyo.model.car.dto;

import java.io.Serializable;
import java.util.List;

import com.cnit.yoyo.model.car.CarData;
import com.cnit.yoyo.model.car.CarDataCatalog;

/**
 * @ClassName: CarDataCatalog
 * @Description: 车型数据项类别
 * @author xiaox
 * @date 2015-3-31 下午2:22:46
 */
public class CarDataCatalogDTO extends CarDataCatalog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2950085235903490847L;
	private List<CarData> carDatas;
	
	private List<CarDataDTO> carDataDTOs;

	public List<CarData> getCarDatas() {
		return carDatas;
	}

	public void setCarDatas(List<CarData> carDatas) {
		this.carDatas = carDatas;
	}

	public List<CarDataDTO> getCarDataDTOs() {
		return carDataDTOs;
	}

	public void setCarDataDTOs(List<CarDataDTO> carDataDTOs) {
		this.carDataDTOs = carDataDTOs;
	}

}