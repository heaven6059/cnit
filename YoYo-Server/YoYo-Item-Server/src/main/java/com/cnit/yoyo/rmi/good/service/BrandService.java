package com.cnit.yoyo.rmi.good.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.autohome.model.AutohomeCarInfoDTO;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.car.CarFactoryMapper;
import com.cnit.yoyo.dao.goods.BrandMapper;
import com.cnit.yoyo.dao.goods.GoodsMapper;
import com.cnit.yoyo.dao.goods.LabelMapper;
import com.cnit.yoyo.dao.shop.TypeBrandShipMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.QueryParamObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.car.CarFactory;
import com.cnit.yoyo.model.car.dto.CarFactoryDTO;
import com.cnit.yoyo.model.goods.Brand;
import com.cnit.yoyo.model.goods.GoodsWithBLOBs;
import com.cnit.yoyo.model.goods.Label;
import com.cnit.yoyo.model.goods.dto.ShopBrandBLOBsWithLabel;
import com.cnit.yoyo.model.goods.dto.ShopBrandWithBLOBConvert;
import com.cnit.yoyo.model.shop.TypeBrandShip;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.StringUtil;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

/**
 * 
 *@description 商品品牌
 *@detail <功能详细描述>
 *@version 1.0.0
 */
@Service("brandService")
public class BrandService {
    @Autowired 
    private BrandMapper  brandMapper;
    
    @Autowired
    private RemoteService memberService;
    
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired 
    private CarFactoryMapper  carFactoryMapper;
    
    @Autowired 
    private TypeBrandShipMapper  typeBrandShipMapper;
    @Autowired
	private LabelMapper labelMapper;
    
    /**
     * @Description:查询爬虫关联车品牌数据
     * @param data
     * @return
     */
  	public Object selectCarBrandSpiderDataList(Object data){
  		HeadObject head = new HeadObject();
  		List<AutohomeCarInfoDTO> list=(List<AutohomeCarInfoDTO>) data;
  		List<Brand> retlist = null;
  		try{
  			retlist =  brandMapper.selectCarBrandSpiderDataList(list);
  			head.setRetCode(ErrorCode.SUCCESS);
  		}catch(Exception e){    
  			e.printStackTrace();
  			head.setRetCode(ErrorCode.FAILURE);
  		}       
  		return new ResultObject(head, retlist);
  	}
  		
  	/**
  	   * @Description:查询爬虫关联车品牌数据
  	   * @param data
  	   * @return
  	   */
  		public Object selectCarBrandSpiderData(Object data){
  			HeadObject head = new HeadObject();
  			Brand retObj = null;
  			try{
  				retObj =  brandMapper.selectCarBrandSpiderData((AutohomeCarInfoDTO)data);
  				head.setRetCode(ErrorCode.SUCCESS);
  			}catch(Exception e){    
  				e.printStackTrace();
  				head.setRetCode(ErrorCode.FAILURE);
  			}       
  			return new ResultObject(head, retObj);
  		}
  		
    
    /**
     * 
     * @Title: findBrandList 
     * @Description: 查找商品品牌
     * @param @param data
     * @param @return    设定文件 
     * @author xiaox
     * @date 2015-3-18 下午2:30:50
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Object findBrandList(Object data){
    	 HeadObject head = new HeadObject();
    	 ResultPage<Brand> page = null;
         try{
             JSONObject content = JSONObject.fromObject(data);
             PageHelper.startPage(content.getInt("pageIndex"), content.getInt("pageSize"));
             Brand brand = (Brand)JSONObject.toBean(content.getJSONObject("brand"), Brand.class);
             if(brand!=null && !StringUtil.isEmpty(content.optString(QueryParamObject.SORT_STR))){
                 brand.setOrderStmt(QueryParamObject.getOrderByCause(content));
             }
			 page = new ResultPage( brandMapper.findBrandList(brand));
             head.setRetCode(ErrorCode.SUCCESS);
         }catch(Exception e){    
        	 e.printStackTrace();
             head.setRetCode(ErrorCode.FAILURE);
         }  
         return new ResultObject(head, JSONObject.fromObject(page));
    }
    
    /**
     * 
     * @Title: insertBrand 
     * @Description: 新增品牌
     * @param @param data
     * @param @return    设定文件 
     * @author xiaox
     * @date 2015-3-23 上午9:55:23
     */
    public Object insertBrand(Object data){
   	    HeadObject head = new HeadObject();
        JSONObject content = JSONObject.fromObject(data);
        Brand brand = (Brand)JSONObject.toBean(content.getJSONObject("brand"), Brand.class);
		brandMapper.insertSelective(brand);
		//插入品牌与分类的关系
		TypeBrandShip typeBrand = new TypeBrandShip();
		typeBrand.setBrandId(brand.getBrandId());
		JSONArray arr = content.getJSONArray("goodCateIds");
		JSONArray arrIdentifier = content.getJSONArray("identifiers");   //分类区别字段
		for(int i=0 ; i < arr.size() ; i++ ){
			typeBrand.setCatId(arr.getInt(i));
			typeBrand.setIdentifier(arrIdentifier.getString(i));
			typeBrandShipMapper.insertSelective(typeBrand);
		}
		
        head.setRetCode(ErrorCode.SUCCESS);
        return new ResultObject(head);
   }
    
    /**
     * 
     * @Description: 根据分类ID查询品牌信息
     */
    public Object findBrandByCatId(Object data){
      	 HeadObject head = new HeadObject();
      	 List<Brand> brandList = null;
      	 ResultPage<Brand> page = null;
           try{
               Integer catId = (Integer)data;
               brandList=brandMapper.findBrandByCatId(catId);
               page = new ResultPage<Brand>();
               page.setRows(brandList);
			   head.setRetCode(ErrorCode.SUCCESS);
           }catch(Exception e){    
        	   e.printStackTrace();
               head.setRetCode(ErrorCode.FAILURE);
           }       
           return new ResultObject(head, JSONObject.fromObject(page));
    }
    
    /**
     * 
     * @Title: brandName 
     * @Description: 查找品牌是否存在
     * @param @param data
     * @param @return    设定文件 
     * @author xiaox
     * @date 2015-3-23 上午10:18:13
     */
    public Object findBrandByName(Object data){
      	 HeadObject head = new HeadObject();
           try{
               JSONObject content = JSONObject.fromObject(data);
               Brand brand = (Brand)JSONObject.toBean(content, Brand.class);
   			   int count=brandMapper.findBrandByName(brand);
   			   if(count == 0){
   				   head.setRetCode(ErrorCode.SUCCESS);
   			   }else{
   				   head.setRetCode(ErrorCode.IS_EXIST);
   			   }
           }catch(Exception e){    
        	   e.printStackTrace();
               head.setRetCode(ErrorCode.FAILURE);
           }       
           return new ResultObject(head);
    }
    
    /**
     * 
     * @Title: updateBrand 
     * @Description: 更新品牌
     * @param @param data
     * @param @return    设定文件 
     * @author xiaox
     * @date 2015-3-23 下午2:06:49
     */
    public Object updateBrand(Object data){
     	 HeadObject head = new HeadObject();
          try{
              JSONObject content = JSONObject.fromObject(data);
              Brand brand = (Brand)JSONObject.toBean(content.getJSONObject("brand"), Brand.class);
  			  brandMapper.updateByPrimaryKeySelective(brand);
  			  //更新品牌与分类的关系：先删除所有关系，然后再插入新的关系
  			  typeBrandShipMapper.deleteByPrimaryKey(brand.getBrandId());
  			  JSONArray arr = content.getJSONArray("goodCateIds");
  			  JSONArray arrIdentifier = content.getJSONArray("identifiers");   //分类区别字段
			  TypeBrandShip typeBrand = new TypeBrandShip();
			  typeBrand.setBrandId(brand.getBrandId());
			  for(int i=0 ; i < arr.size() ; i++ ){
				typeBrand.setCatId(arr.getInt(i));
				typeBrand.setIdentifier(arrIdentifier.getString(i));
				typeBrandShipMapper.insertSelective(typeBrand);
			  }
			
  			  head.setRetCode(ErrorCode.SUCCESS);
          }catch(Exception e){    
       	   e.printStackTrace();
              head.setRetCode(ErrorCode.FAILURE);
          }       
          return new ResultObject(head);
   }
    
    
    /**
     * 
     * @Title: deleteBrand 
     * @Description: 逻辑删除品牌以及物理删除品牌与分类的关系
     * @param @param data
     * @param @return    设定文件 
     * @author xiaox
     * @date 2015-3-23 下午2:06:49
     */
    public Object deleteBrand(Object data){
     	 HeadObject head = new HeadObject();
          try{
        	  Integer [] brandIds =  (Integer[]) data;
        	  Integer [] tempIds =  new Integer[brandIds.length];
        	  int j=0;
        	  //先判断是否存在厂商使用该品牌或在售的商品使用
              boolean flag = false; //批量删除时，是否有部分有关联数据
              List<GoodsWithBLOBs> goods = null;
              List<CarFactory> carfactory = null;
              for(int i=0;i<brandIds.length;i++){
                  goods = goodsMapper.findGoodsByBrandId(brandIds[i]);
                  carfactory = carFactoryMapper.selectCarFactoryByBrand(brandIds[i]);
                  if((goods==null && carfactory==null)||(goods.size()==0 && carfactory.size()==0)){ 
                      tempIds[j]=brandIds[i];
                      j++;
                  } else {
                     flag=true;
                  }
              }
              if(flag){
                head.setRetCode(ErrorCode.IS_EXIST_CASCADE);
              }else{
                  head.setRetCode(ErrorCode.SUCCESS);
              }
              if(tempIds.length>0){
      			  brandMapper.deleteByPrimaryKey(tempIds);
      			  //删除对应的品牌与分类的关系表
      			  typeBrandShipMapper.deleteByBrandIds(tempIds);
              }
          }catch(Exception e){    
       	   e.printStackTrace();
              head.setRetCode(ErrorCode.FAILURE);
          }       
          return new ResultObject(head);
   }
    
    /**
	 * @Title: findBrandBusinessList
	 * @Description: 查找申请品牌的列表 t_business_brand
	 * @author Harder-Zhao
	 * @param @param data
	 * @param @return 设定文件
	 * @return Object 返回类型
	 * @throws
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object findBrandBusinessList(Object data) {
		HeadObject head = new HeadObject();
		ResultPage<ShopBrandBLOBsWithLabel> page = null;
		ResultPage<ShopBrandBLOBsWithLabel> page2 = null;
		try {
			HeadObject headObject = CommonHeadUtil.geneHeadObject("shopBrandService.findBrandBusinessList");
			///List<ShopBrandWithBLOBs> brandList = (List<ShopBrandWithBLOBs>)memberService.doServiceByServer(new RequestObject(headObject,data));
			//List<ShopBrandWithBLOBs> brandList = shopBrandMapper.findBrandBusinessList(shopBrand);
			page2 =  (ResultPage<ShopBrandBLOBsWithLabel>) memberService.doServiceByServer(new RequestObject(headObject,data));
			List<ShopBrandBLOBsWithLabel> brandLabelList = new ArrayList<ShopBrandBLOBsWithLabel>();
			if(page2!=null && page2.getRows()!=null && page2.getRows().size()>0){
    			for(ShopBrandBLOBsWithLabel brand : page2.getRows()){
    				ShopBrandBLOBsWithLabel brandLabel = new ShopBrandBLOBsWithLabel();
    				List<Label> labels = null;
    				if(!StringUtils.isEmpty(brand.getLabel())){
    					String[] strIds = brand.getLabel().split(",");
    					int i=0;
    					for(String id : strIds){
    						strIds[i++] = id;
    					}
    					labels = labelMapper.selectByPrimaryKeys(strIds);
    					ShopBrandWithBLOBConvert.convertToBrandLabel(brandLabel, brand, labels);
    				}else{
    					ShopBrandWithBLOBConvert.convertToBrandLabel(brandLabel, brand, labels);
    				}
    				brandLabelList.add(brandLabel);
    			}
    			page = new ResultPage<ShopBrandBLOBsWithLabel>(brandLabelList);
    		
                page.setCurrPageSize(page2.getCurrPageSize());
                page.setPageIndex(page2.getPageIndex());
                page.setPages(page2.getPages());
                page.setPageSize(page2.getPageSize());
    			page.setRows(brandLabelList);
    			page.setTotal(page2.getTotal());
    			
			}else {
				page = page2;//为了使页面上的翻页数据为0；
			}
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head, JSONObject.fromObject(page));
	}

	
	
	

	
	/** 
	* @Title: findBrandBusinessByStatus 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author Harder-Zhao
	* @param @param data
	* @param @return    设定文件 
	* @return Object    返回类型 
	* @throws 
	*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object findBrandBusinessByStatus(Object data) {
		HeadObject head = new HeadObject();
		ResultPage<ShopBrandBLOBsWithLabel> page = new ResultPage<ShopBrandBLOBsWithLabel>();
		try {
			JSONObject content = JSONObject.fromObject(data);
			PageHelper.startPage(content.getInt("pageIndex"), content.getInt("pageSize"));
			String status = content.getString("status");
			HeadObject headObject = CommonHeadUtil.geneHeadObject("shopBrandService.findBrandBusinessByStatus");
			List<ShopBrandBLOBsWithLabel> brandList=(List<ShopBrandBLOBsWithLabel>) memberService.doServiceByServer(new RequestObject(headObject,status));
			
			//List<ShopBrandWithBLOBs> brandList = shopBrandMapper.findBrandBusinessByStatus(status);
			if(brandList!=null && brandList.size()>0){
    			page = new ResultPage(brandList);
    			List<ShopBrandBLOBsWithLabel> brandLabelList = new ArrayList<ShopBrandBLOBsWithLabel>();
    			for(ShopBrandBLOBsWithLabel brand : brandList){
    				ShopBrandBLOBsWithLabel brandLabel = new ShopBrandBLOBsWithLabel();
    				List<Label> labels = null;
    				if(!StringUtils.isEmpty(brand.getLabel())){
    					String[] strIds = brand.getLabel().split(",");
    					int i=0;
    					for(String id : strIds){
    						strIds[i++] = id;
    					}
    					labels = labelMapper.selectByPrimaryKeys(strIds);
    					ShopBrandWithBLOBConvert.convertToBrandLabel(brandLabel, brand, labels);
    				}else{
    					ShopBrandWithBLOBConvert.convertToBrandLabel(brandLabel, brand, labels);
    				}
    				brandLabelList.add(brandLabel);
    			}
    			page.setRows(brandLabelList);
			}
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head, JSONObject.fromObject(page));
	}
	
	/** 
	* @Title: deleteBrandApplyByIds 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author Harder-Zhao
	* @param @param data
	* @param @return    设定文件 
	* @return Object    返回类型 
	* @throws 
	*/
	public Object deleteBrandApplyByIds(Object data) {
		HeadObject head = new HeadObject();
		try {
			Integer[] ids = (Integer[]) data;
			HeadObject headObject = CommonHeadUtil.geneHeadObject("shopBrandService.deleteByPrimaryKeys");
			memberService.doServiceByServer(new RequestObject(headObject,ids));
			//shopBrandMapper.deleteByPrimaryKeys(ids);
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head);
	}
	
	/**
	 * @Title:  selectByBrandId  
	 * @Description:  TODO(根据品牌id查询品牌对象)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-5-5 下午3:58:00  
	 * @version 1.0.0 
	 * @param @param data
	 * @param @return
	 * @return Object  返回类型 
	 * @throws
	 */
	public Object selectByBrandId(Object data){
    	Brand brand = brandMapper.selectByPrimaryKey((Integer)data);
        return new ResultObject(new HeadObject(ErrorCode.SUCCESS), brand);
    }
	
	/**
	  * @description <b>查询汽车品牌</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-11
	  * @param @param data
	  * @param @return
	  * @return Object
	*/
	public Object findCarBrand(Object data){
		Map<String, Object> param=(Map<String, Object>) data;
		List<Brand> brands = brandMapper.selectCarBrand(param);
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), brands);
	}
	
	/**
	 * @Title:  selectAllBrand  
	 * @Description:  TODO(查询所有品牌)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-5-12 上午11:40:34  
	 * @version 1.0.0 
	 * @param @param data
	 * @param @return
	 * @return Object  返回类型 
	 * @throws
	 */
	public Object selectAllBrand(Object data) {
		HeadObject head = new HeadObject();
		List<Brand> brandList = null;
		try {
			brandList = brandMapper.selectAllBrand();
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head, brandList);
	}
	
	/**
	 * 
	 *@description 查询指定id集合的品牌
	 *@detail <方法详细描述>
	 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
	 *@version 1.0.0
	 *@data 2015-6-3
	 *@param data
	 *@return
	 */
	public Object selectByIds(Object data){
        List<Brand> brand = brandMapper.selectByIds((Integer[])data);
        return brand;
    }
	
	public Object selectBrandByIds(Object data){
		HeadObject head = new HeadObject();
		List<Integer> list=com.alibaba.fastjson.JSONArray.parseArray(data.toString(),Integer.class);
		Integer[] arr = (Integer[])list.toArray(new Integer[list.size()]);
        List<Brand> brandList = brandMapper.selectByIds(arr);
        head.setRetCode(ErrorCode.SUCCESS);
        return new ResultObject(head, brandList);
    }
	
	/**
	 * @description <b>查询包含商品的汽车品牌</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015年7月30日
	 * @param data
	 * @return
	 * Object
	*/
	public Object selectCarBrandHasGoods(Object data){
		HeadObject head = new HeadObject();
		List<Brand> brandList = null;
		try{
	        brandList = brandMapper.selectCarBrandHasGoods((Map<String, Object>) data);
	        head.setRetCode(ErrorCode.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head, brandList);
	}
	
	/**
	 * 
	 *@description 品牌申请查询是否存在
	 *@detail <方法详细描述>
	 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
	 *@version 1.0.0
	 *@data 2015-6-30
	 *@param data
	 *@return
	 */
	 public Object findBrandApplyByName(Object data){
	     HeadObject head = new HeadObject();
	     if(data!=null && StringUtils.isNotBlank(data.toString())){
	         Brand brand = (Brand)data;
	        // brand.setBrandName(data.toString());
	         if(brand.getBrandId()!=null && brand.getBrandId()!=0){//查找该品牌的类型BrandType
	             @SuppressWarnings("unchecked")
                List<Brand> temp = (List<Brand>)selectByIds(new Integer[]{brand.getBrandId()});
	             brand.setBrandType(temp.get(0).getBrandType());
	         }
	         int count=brandMapper.findBrandByName(brand);
	         if(count == 0){
	             head.setRetCode(ErrorCode.SUCCESS);
	         }else{
	             head.setRetCode(ErrorCode.IS_EXIST);
	         }
	         
	     }
         return new ResultObject(head);
    }
}