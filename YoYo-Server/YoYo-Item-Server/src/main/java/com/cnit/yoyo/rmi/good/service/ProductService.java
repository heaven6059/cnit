/**
 * 文 件 名   :  GoodsService.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnitcloud.com">李明</a>
 * 创建时间  :  2015年4月3日 下午5:56:20
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.rmi.good.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.goods.GoodsMapper;
import com.cnit.yoyo.dao.goods.PictureMapper;
import com.cnit.yoyo.dao.goods.ProductMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.goods.Picture;
import com.cnit.yoyo.model.goods.Product;
import com.cnit.yoyo.model.goods.ProductWithBLOBs;
import com.cnit.yoyo.model.goods.dto.GoodsProductSpecDTO;
import com.cnit.yoyo.model.goods.dto.ProductImgDTO;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

/**
 * @description 货品service
 * @detail <功能详细描述>
 * @version 1.0.0
 */
@Service("productService")
public class ProductService {
    public static final Logger log = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductMapper productMapper;
    
    @Autowired
    private PictureMapper pictureMapper;
    
    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 
     * @Description: 通过货品编号进行判断是否存在  
     * @param @param data
     * @param @return
     * @author xiaox
     * @date 2015-4-14 下午5:30:45
     */
    public int findProductByBn(String[] data){
         return productMapper.findProductByBn(data,null);
   }
   
    /**
     * 
    * @Title: selectBySpecInfoAndGoods 
    * @Description: 根据商品id和商品规格查询货品
    * @author <a href="cmlai@chinacnit.com">赖彩妙</a>
    * @param @param data
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws
     */
    public Object selectBySpecInfoAndGoodsId(Object data){
    	log.info("start[ProductService.selectBySpecInfoAndGoodsId]");
    	JSONObject jsonObject = JSONObject.fromObject(data);
    	Integer goodsId = jsonObject.getInt("goodsId");
    	String specInfo = (String) jsonObject.get("specInfo");
    	Product p = productMapper.selectBySpecInfoAndGoodsId(goodsId, specInfo);
    	ProductImgDTO productDTO = null;
//    	jsonObject.clear();
    	if(p!=null){
//    		jsonObject.put("productId", p.getProductId());
//    		jsonObject.put("store", p.getStore().intValue());
    		productDTO = new ProductImgDTO();
    		BeanUtils.copyProperties(p, productDTO);
    		productDTO.setPriceDouble(p.getPrice().doubleValue());
    		productDTO.setStoreInt(p.getStore().intValue());
    	}
        log.info("end[ProductService.selectBySpecInfoAndGoodsId]");
        return new ResultObject(new HeadObject(ErrorCode.SUCCESS), productDTO);
    }
    
    /**
     * 减少货品库存
     * @param data
     * @return
     */
    public Object decreStore(Object data){
    	log.info("start[ProductService.decreStore]");
    	JSONObject jsonObject = JSONObject.fromObject(data);
    	Integer quantity = jsonObject.getInt("quantity");
    	Integer productId = jsonObject.getInt("productId");
    	productMapper.decreStore(productId, quantity);
//    	ProductWithBLOBs product = productMapper.selectByPrimaryKey(productId);
//    	if(product.getStore().intValue()<=0){
//    		productMapper.updateMarketable(product.getProductId(), "0");
//    	}
    	goodsMapper.updateStore(productId);
        log.info("end[ProductService.decreStore]");
        return new HeadObject(ErrorCode.SUCCESS);
    }
    
    /**
     * 增加货品库存
     * @param data
     * @return
     */
    public Object addStock(Object data){
    	log.info("start[ProductService.addStore]");
    	JSONObject stockJson=JSONObject.fromObject(data);
    	productMapper.addStock(stockJson.getInt("productId"), stockJson.getInt("stockNum"));
    	goodsMapper.updateStore(stockJson.getInt("productId"));
        log.info("end[ProductService.addStore]");
        return new HeadObject(ErrorCode.SUCCESS);
    }

    public Object getProductSpecByGoodsId(Object data){
    	List<GoodsProductSpecDTO> productSpecs = productMapper.getProductSpecByGoodsId((Integer)data);
    	return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONArray.fromObject(productSpecs));
    }
    
    /**
     * @Title:  findByGoodsId  
     * @Description:  TODO(根据goodsId查询货品列表)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-29 下午1:14:14  
     * @version 1.0.0 
     * @param @param data
     * @param @return
     * @return Object  返回类型 
     * @throws
     */
    public Object findByGoodsId(Object data){
    	log.info("start[ProductService.findByGoodsId]");
    	List<ProductImgDTO> productDTOList = productMapper.selectByGoodsId((Integer)data);
    	if(productDTOList!=null&&productDTOList.size()>=1){
    		List<Picture> pictureList = null;
    		for(int i=0;i<productDTOList.size();i++){
    			pictureList = pictureMapper.selectByProductId(productDTOList.get(i).getProductId());
    			productDTOList.get(i).setPicList(pictureList);
    			productDTOList.get(i).setPriceDouble(productDTOList.get(i).getPrice().doubleValue());
    			productDTOList.get(i).setStoreInt(productDTOList.get(i).getStore().intValue());
    		}
    	}
        log.info("end[ProductService.findByGoodsId]");
        return new ResultObject(new HeadObject(ErrorCode.SUCCESS),productDTOList);
    }
    
    /**
     * @Title:  findByProductId  
     * @Description:  TODO(根据productId查询货品对象)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-4 上午11:05:36  
     * @version 1.0.0 
     * @param @param data
     * @param @return
     * @return Object  返回类型 
     * @throws
     */
    public Object findByProductId(Object data){
    	log.info("start[ProductService.findByProductId]");
    	ProductWithBLOBs product = productMapper.selectByPrimaryKey((Integer)data);
    	ProductImgDTO productDTO = null;
    	if(product!=null){
    		productDTO = new ProductImgDTO();
    		BeanUtils.copyProperties(product, productDTO);
    		productDTO.setPriceDouble(product.getPrice().doubleValue());
    		productDTO.setStoreInt(product.getStore().intValue());
    	}
        log.info("end[ProductService.findByProductId]");
        return new ResultObject(new HeadObject(ErrorCode.SUCCESS),productDTO);
    }
    

    /**
     * @Title:  selectByGoodsIdList  
     * @Description:  TODO(根据商品id列表查询默认的货品列表)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-13 下午7:37:31  
     * @version 1.0.0 
     * @param @param data
     * @param @return
     * @return Object  返回类型 
     * @throws
     */
    public Object selectByGoodsIdList(Object data){
    	log.info("start[ProductService.selectByGoodsIdList]");
    	JSONObject jsonObject = JSONObject.fromObject(data);
    	List<Integer> goodsIdList = (List<Integer>) jsonObject.get("goodsIdList");
//    	Integer productId = (Integer) jsonObject.get("productId");
    	List<ProductWithBLOBs> productList = null;
//    	if(productId!=null&&productId!=0){
//    		ProductWithBLOBs product = productMapper.selectByPrimaryKey(productId);
//    		String specDesc = product.getSpecDesc();
//    		String[] specItems = specDesc.split(",");
//    		String specItem = null;
//    		if(specItems!=null&&specItems.length>=1){
//    			for(int i = specItems.length-1 ; i>=0 ; i--){
//					if(specItems[i].split(":")[0].split("\\|")[1].equals("分店")){
//						specItem = specItems[i];
//						break;
//					}
//				}
//    		}
//    		if(specItem!=null){
//    			productList = productMapper.selectByGoodsIdListAndSpecItem(goodsIdList, specItem);
//    		}
//    	}
    	if(productList==null){
    		productList = productMapper.selectByGoodsIdList(goodsIdList);
    	}
        log.info("end[ProductService.selectByGoodsIdList]");
        return new ResultObject(new HeadObject(ErrorCode.SUCCESS),productList);
    }
    

    
    /**
     * 
     *@description 根据店铺id查找所有货品
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-5-13
     *@param data
     *@return
     */
    public Object getProductByCompanyId(Object data){
        ResultPage<GoodsProductSpecDTO> page = null;
        Integer cateId = null;
        JSONObject content = JSONObject.fromObject(data);
        PageHelper.startPage(content.getInt("pageIndex"), content.getInt("pageSize"));
        if(!StringUtils.isBlank(content.getString("cateId")) && !content.getString("cateId").equals("null")){
            cateId = Integer.valueOf(content.getString("cateId"));
        }
        page = new ResultPage<GoodsProductSpecDTO>(productMapper.getProductByCompanyId(content.getInt("companyId"),cateId));
        return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONObject.fromObject(page));
    }
    
    /**
     * 
     *@description 根据id集查货品
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-5-13
     *@param data
     *@return
     */
    public Object findProductByIds(Object data){
        List<Product> list = null;
        list =productMapper.findProductByIds((Map)data);
        return new ResultObject(new HeadObject(ErrorCode.SUCCESS), list);
    }
    
    /**
     * @Title:  selectByProductIdList  
     * @Description:  TODO(根据货品id集合查询货品)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-27 下午7:27:46  
     * @version 1.0.0 
     * @param @param data
     * @param @return
     * @return Object  返回类型 
     * @throws
     */
    public Object selectByProductIdList(Object data){
    	log.info("start[ProductService.selectByProductIdList]");
    	List<ProductWithBLOBs> productList = productMapper.selectByProductIdList(((List<Integer>)data));
        log.info("end[ProductService.selectByProductIdList]");
        return new ResultObject(new HeadObject(ErrorCode.SUCCESS),productList);
    }
    
    /**
     * @Title:  selectByProductIdList  
     * @Description:  TODO(根据货品id集合查询货品)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-27 下午7:27:46  
     * @version 1.0.0 
     * @param @param data
     * @param @return
     * @return Object  返回类型 
     * @throws
     */
    public Object selectByProductIds(Object data){
    	log.info("start[ProductService.selectByProductIdList]");
    	List<ProductWithBLOBs> productList = productMapper.selectByProductIds(((List<Integer>)data));
        log.info("end[ProductService.selectByProductIdList]");
        return new ResultObject(new HeadObject(ErrorCode.SUCCESS),productList);
    }
    
    /**
     * @Title:  findProductCompanyById  
     * @Description:  TODO(根据货品id查询货品及集团)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-7-2 下午3:31:42  
     * @version 1.0.0 
     * @param @param data
     * @param @return
     * @return Object  返回类型 
     * @throws
     */
    public Object findProductCompanyById(Object data){
    	log.info("start[ProductService.findProductCompanyById]");
    	ProductWithBLOBs product = productMapper.selectProductCompanyById((Integer)data);
    	ProductImgDTO productDTO = null;
    	if(product!=null){
    		productDTO = new ProductImgDTO();
    		BeanUtils.copyProperties(product, productDTO);
    		productDTO.setPriceDouble(product.getPrice().doubleValue());
    		productDTO.setStoreInt(product.getStore().intValue());
    	}
        log.info("end[ProductService.findProductCompanyById]");
        return new ResultObject(new HeadObject(ErrorCode.SUCCESS),productDTO);
    }
    
    
}