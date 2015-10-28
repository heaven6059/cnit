package com.cnit.yoyo.rmi.car.service;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.car.CarDataCatalogMapper;
import com.cnit.yoyo.dao.car.CarDataMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.QueryParamObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.car.CarDataCatalog;
import com.cnit.yoyo.util.StringUtil;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;
/**
 * 
 * @ClassName: CarDataCategoryService 
 * @Description: 车型数据类别
 * @author xiaox
 * @date 2015-3-31 下午2:20:52
 */
@Service("carDataCateService")
public class CarDataCategoryService {
    @Autowired 
    private CarDataCatalogMapper  carDataCatalogMapper;
    
    @Autowired 
    private CarDataMapper  carDataMapper;
    
 
    
  
    
    /**
     * @Description: 获取车型数据项类别  
     * @param @param data
     * @param @return
     * @author xiaox
     * @date 2015-3-31 上午11:08:31
     */
    public Object findList(Object data){
   	 HeadObject head = new HeadObject();
   	 ResultPage<CarDataCatalog> page = null;
        try{
       	 JSONObject content = JSONObject.fromObject(data);
       	 PageHelper.startPage(content.getInt("pageIndex"), content.getInt("pageSize"));
       	 CarDataCatalog temp = new CarDataCatalog();
       	 if(StringUtils.isNotBlank(content.getString("cateName"))&& !content.getString("cateName").equals("null")){
       	     temp.setCatalogName(content.getString("cateName"));
       	 }
       	if(temp!=null && !StringUtil.isEmpty(content.optString(QueryParamObject.SORT_STR))){
       	    temp.setOrderStmt(QueryParamObject.getOrderByCause(content));
        }
       	 page = new ResultPage<CarDataCatalog>(carDataCatalogMapper.findList(temp));
         head.setRetCode(ErrorCode.SUCCESS);
        }catch(Exception e){    
       	 e.printStackTrace();
            head.setRetCode(ErrorCode.FAILURE);
        }       
        return new ResultObject(head, JSONObject.fromObject(page));
   }
    
	/**
	 * 
	 * @Description:查找是否同名 
	 * @param @param data
	 * @param @return
	 * @author xiaox
	 * @date 2015-3-31 下午2:50:03
	 */
	 public Object findByName(Object data){
     	 HeadObject head = new HeadObject();
         try{
       	      JSONObject content = JSONObject.fromObject(data);
       	      CarDataCatalog catalog = (CarDataCatalog)JSONObject.toBean(content, CarDataCatalog.class);
              int count=carDataCatalogMapper.findByName(catalog);
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
	  * @Description: 添加 
	  * @param @param data
	  * @param @return
	  * @author xiaox
	  * @date 2015-3-31 下午2:57:22
	  */
	 public Object insertCarDataCate(Object data){
    	 HeadObject head = new HeadObject();
         try{
      	   JSONObject content = JSONObject.fromObject(data);
      	   CarDataCatalog catalog = (CarDataCatalog)JSONObject.toBean(content, CarDataCatalog.class);
      	   carDataCatalogMapper.insertSelective(catalog);
           head.setRetCode(ErrorCode.SUCCESS);
         }catch(Exception e){    
        	 e.printStackTrace();
             head.setRetCode(ErrorCode.FAILURE);
         }       
         return new ResultObject(head);
    }
	 
	 
	 /**
	    * 
	    * @Description: 逻辑删除
	    * @param @param data
	    * @param @return
	    * @author xiaox
	    * @date 2015-3-30 上午10:46:56
	    */
	   public Object deleteCarDataCate(Object data){
	   	 HeadObject head = new HeadObject();
      	 Integer[]  id =  (Integer[]) data;
      	  //先判断是否存在下级车型数据，有，则不能删除，要先删除下级数据
      	 int count = 0;
         boolean flag = false; //批量删除时，是否有部分有关联数据
         for(int i=0;i<id.length;i++){
             count = carDataMapper.selectByCatalogId(id[i]);
             if(count == 0 ){ 
                 carDataCatalogMapper.deleteByPrimaryKey(id[i]);
             } else {
                 flag=true;
             }
         }
         if(flag){
           head.setRetCode(ErrorCode.IS_EXIST_CASCADE);
         }else{
           head.setRetCode(ErrorCode.SUCCESS);
         }
			 
	     return new ResultObject(head);
	   }
	   
	   /**
	    * 
	    * @Description: 更新  
	    * @param @param data
	    * @param @return
	    * @author xiaox
	    * @date 2015-3-30 上午11:30:24
	    */
	   public Object updateCarDataCate(Object data){
	  	 HeadObject head = new HeadObject();
	       try{
	            JSONObject content = JSONObject.fromObject(data);
	            CarDataCatalog catalog = (CarDataCatalog)JSONObject.toBean(content, CarDataCatalog.class);
	       	    carDataCatalogMapper.updateByPrimaryKeySelective(catalog);
				head.setRetCode(ErrorCode.SUCCESS);
	       }catch(Exception e){    
	    	   e.printStackTrace();
	           head.setRetCode(ErrorCode.FAILURE);
	       }       
	       return new ResultObject(head);
	   }
    
}
