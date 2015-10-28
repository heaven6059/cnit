package com.cnit.yoyo.model.car.dto;

import java.io.Serializable;
/**
 * 
 * @Description: 车型列表
 * @author ssd
 */
public class CarConfiginfoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String dataId;
	
	private String displayName;//显示名称
	
    private String dataType;//车型名称

    private String configValue;//配置值

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}
    

    
    
   
}