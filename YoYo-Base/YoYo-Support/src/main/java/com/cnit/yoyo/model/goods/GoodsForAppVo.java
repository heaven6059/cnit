package com.cnit.yoyo.model.goods;

import java.io.Serializable;

public class GoodsForAppVo extends Goods implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Integer isChange; //是否可换，1:是 0:否
	
	public Integer isGroup;  //是否可团购，1:是，0：否
	
	public Integer isCoupon;  //是否可用券，1：是，0：否
	
	

}
