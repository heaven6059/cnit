package com.cnit.yoyo.model.car;

import java.io.Serializable;
import java.util.List;

public class AccessoryAndBindingCars extends Accessory implements Serializable {

	private static final long serialVersionUID = -2651278239657756046L;
	private List<AccessoryCarIndex> cars;
	public List<AccessoryCarIndex> getCars() {
		return cars;
	}
	public void setCars(List<AccessoryCarIndex> cars) {
		this.cars = cars;
	}
	
}
