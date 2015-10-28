package com.cnit.yoyo.model.goods.dto;

import com.cnit.yoyo.model.goods.AccessoryGoods;

/**
 * @ClassName: AccessoryGoodsDTO  
 * @Description: TODO(这里用一句话描述这个类的作用)  
 * @detail <详细说明>
 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
 * @date 2015-5-25 下午7:05:17  
 * @version 1.0.0
 */
public class AccessoryGoodsDTO extends AccessoryGoods{

	
	private Double priceFromDouble;//最低价
	
	private Double priceToDouble;//最高价
	
	private Double creditDouble;//优惠额度

	public Double getPriceFromDouble() {
		return priceFromDouble;
	}

	public void setPriceFromDouble(Double priceFromDouble) {
		this.priceFromDouble = priceFromDouble;
	}

	public Double getPriceToDouble() {
		return priceToDouble;
	}

	public void setPriceToDouble(Double priceToDouble) {
		this.priceToDouble = priceToDouble;
	}

	public Double getCreditDouble() {
		return creditDouble;
	}

	public void setCreditDouble(Double creditDouble) {
		this.creditDouble = creditDouble;
	}

	
}
