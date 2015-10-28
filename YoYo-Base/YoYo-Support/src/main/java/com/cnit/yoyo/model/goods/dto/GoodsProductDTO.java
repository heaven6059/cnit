package com.cnit.yoyo.model.goods.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: GoodsProductDTO
 * @Description: 商品 货品
 * @author xiaox
 * @date 2015-4-11 上午10:32:31
 */
public class GoodsProductDTO extends GoodsInfoListDTO implements Serializable {

    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 1L;
    private String bn;                          // 单个货号
    private String attrJson;                    // 扩展属性json，格式如：[{"value":3,"id":35},{"value":5,"id":38}]
    private String[] picIds;                    // 货品图片id集合
    private String keywords;                    // 商品关键字
    private String intro;                       // 商品详细介绍
    private BigDecimal[] relatedGoods;          // 相关商品
    private String accessoryJson;               // 配件json
    private String rootCategory;             // 顶级分类标志
    private String tagsJson;                    // 标签
    private String productJson;                 // 货品json
    private String defaultPic;                  // 默认图片地址
    private Long defaultPicId;                  //默认图片id
    private String productId;                   //货品id
    
    private String relateGoodsJson; // 关联商品
    private String goodsSpecJson;   //商品表中的规格描述
//    private Integer companyId;
    private Integer storeId;
    private String picturePath;  //没有规格的货品的默认图片
    private String isOpenSpec;  //是否开启规格
    
    private int timenum1;
    private int timenum2;
    private int timenum3;
    private int timenum4;
    private int timenum5;
    private int timenum6;
    private int timenum7;
    private int timenum8;
    private int appointType;
    
    private Date fromTime;
    private Date toTime;
    
    private String priceStartTime;
    private String priceEndTime;
    private BigDecimal timePrice;// 区间价格
    
    
    public String getPicturePath() {
        return picturePath;
    }
    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
//    public Integer getCompanyId() {
//        return companyId;
//    }
//    public void setCompanyId(Integer companyId) {
//        this.companyId = companyId;
//    }
    public Integer getStoreId() {
        return storeId;
    }
    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
    public String getBn() {
        return bn;
    }
    public void setBn(String bn) {
        this.bn = bn;
    }
    public String getAttrJson() {
        return attrJson;
    }
    public void setAttrJson(String attrJson) {
        this.attrJson = attrJson;
    }
    public String[] getPicIds() {
        return picIds;
    }
    public void setPicIds(String[] picIds) {
        this.picIds = picIds;
    }
    public String getKeywords() {
        return keywords;
    }
    
    public String getRootCategory() {
        return rootCategory;
    }
    public void setRootCategory(String rootCategory) {
        this.rootCategory = rootCategory;
    }
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    public String getIntro() {
        return intro;
    }
    public void setIntro(String intro) {
        this.intro = intro;
    }
    public BigDecimal[] getRelatedGoods() {
        return relatedGoods;
    }
    public void setRelatedGoods(BigDecimal[] relatedGoods) {
        this.relatedGoods = relatedGoods;
    }
    public String getAccessoryJson() {
        return accessoryJson;
    }
    public void setAccessoryJson(String accessoryJson) {
        this.accessoryJson = accessoryJson;
    }
  
    public String getTagsJson() {
        return tagsJson;
    }
    public void setTagsJson(String tagsJson) {
        this.tagsJson = tagsJson;
    }
    public String getProductJson() {
        return productJson;
    }
    public void setProductJson(String productJson) {
        this.productJson = productJson;
    }
    public String getDefaultPic() {
        return defaultPic;
    }
    public void setDefaultPic(String defaultPic) {
        this.defaultPic = defaultPic;
    }
    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public String getRelateGoodsJson() {
        return relateGoodsJson;
    }
    public void setRelateGoodsJson(String relateGoodsJson) {
        this.relateGoodsJson = relateGoodsJson;
    }
    public String getGoodsSpecJson() {
        return goodsSpecJson;
    }
    public void setGoodsSpecJson(String goodsSpecJson) {
        this.goodsSpecJson = goodsSpecJson;
    }
    public Long getDefaultPicId() {
        return defaultPicId;
    }
    public void setDefaultPicId(Long defaultPicId) {
        this.defaultPicId = defaultPicId;
    }
    public String getIsOpenSpec() {
        return isOpenSpec;
    }
    public void setIsOpenSpec(String isOpenSpec) {
        this.isOpenSpec = isOpenSpec;
    }
	public int getTimenum1() {
		return timenum1;
	}
	public void setTimenum1(int timenum1) {
		this.timenum1 = timenum1;
	}
	public int getTimenum2() {
		return timenum2;
	}
	public void setTimenum2(int timenum2) {
		this.timenum2 = timenum2;
	}
	public int getTimenum3() {
		return timenum3;
	}
	public void setTimenum3(int timenum3) {
		this.timenum3 = timenum3;
	}
	public int getTimenum4() {
		return timenum4;
	}
	public void setTimenum4(int timenum4) {
		this.timenum4 = timenum4;
	}
	public int getTimenum5() {
		return timenum5;
	}
	public void setTimenum5(int timenum5) {
		this.timenum5 = timenum5;
	}
	public int getTimenum6() {
		return timenum6;
	}
	public void setTimenum6(int timenum6) {
		this.timenum6 = timenum6;
	}
	public int getTimenum7() {
		return timenum7;
	}
	public void setTimenum7(int timenum7) {
		this.timenum7 = timenum7;
	}
	public int getTimenum8() {
		return timenum8;
	}
	public void setTimenum8(int timenum8) {
		this.timenum8 = timenum8;
	}
	public int getAppointType() {
		return appointType;
	}
	public void setAppointType(int appointType) {
		this.appointType = appointType;
	}
	public Date getFromTime() {
		return fromTime;
	}
	public void setFromTime(Date fromTime) {
		this.fromTime = fromTime;
	}
	public Date getToTime() {
		return toTime;
	}
	public void setToTime(Date toTime) {
		this.toTime = toTime;
	}
	public String getPriceStartTime() {
		return priceStartTime;
	}
	public void setPriceStartTime(String priceStartTime) {
		this.priceStartTime = priceStartTime;
	}
	public String getPriceEndTime() {
		return priceEndTime;
	}
	public void setPriceEndTime(String priceEndTime) {
		this.priceEndTime = priceEndTime;
	}
	public BigDecimal getTimePrice() {
		return timePrice;
	}
	public void setTimePrice(BigDecimal timePrice) {
		this.timePrice = timePrice;
	}

    
    
 
}