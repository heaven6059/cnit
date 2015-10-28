package com.cnit.yoyo.rmi.car.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.autohome.model.AutohomeCarInfoDTO;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.car.CarDeptMapper;
import com.cnit.yoyo.dao.car.CarFactoryMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.QueryParamObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.app.Ttoken;
import com.cnit.yoyo.model.car.CarFactory;
import com.cnit.yoyo.model.car.dto.CarFactoryDTO;
import com.cnit.yoyo.util.StringUtil;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;
/**
 * 
 * @ClassName: CarFactoryService 
 * @Description: 汽车厂商service
 * @author xiaox
 * @date 2015-3-28 上午10:53:51
 */
@Service("carFactoryService")
public class CarFactoryService {
    @Autowired 
    private CarFactoryMapper  carFactoryMapper;
    
    @Autowired 
    private CarDeptMapper  carDeptMapper;
    
  
    
  /**
   * 
   * @Description: 获取厂商
   * @param @param data
   * @param @return
   * @author xiaox
   * @date 2015-3-28 上午11:15:10
   */
	public Object findList(Object data){
    	 HeadObject head = new HeadObject();
    	 ResultPage<CarFactory> page = null;
         try{
        	 JSONObject content = JSONObject.fromObject(data);
        	 PageHelper.startPage(content.getInt("pageIndex"), content.getInt("pageSize"));
        	 CarFactory dto = (CarFactory)JSONObject.toBean(content.getJSONObject("carFactory"), CarFactory.class);
        	 if(dto!=null && !StringUtil.isEmpty(content.optString(QueryParamObject.SORT_STR))){
        	     dto.setOrderStmt(QueryParamObject.getOrderByCause(content));
        	 }
        	 page = new ResultPage<CarFactory>(carFactoryMapper.findList(dto));
             head.setRetCode(ErrorCode.SUCCESS);
         }catch(Exception e){    
        	 e.printStackTrace();
             head.setRetCode(ErrorCode.FAILURE);
         }       
         return new ResultObject(head, JSONObject.fromObject(page));
    }
	
	/**
     * 查询汽车厂商的下拉框数据
     * @param @return    设定文件 
     * @author ssd
     */
	public Object findSelect(Object data){
    	 HeadObject head = new HeadObject();
    	 List<CarFactory> list = null;
         try{
             list =  carFactoryMapper.findSelect((Integer)data);
             head.setRetCode(ErrorCode.SUCCESS);
         }catch(Exception e){    
        	 e.printStackTrace();
             head.setRetCode(ErrorCode.FAILURE);
         }       
         return new ResultObject(head, list);
    }
    
	/**
	  * @description <b>根据品牌查汽车厂商</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-11
	  * @param @param data
	  * @param @return
	  * @return Object
	*/
	public Object findCarFactoryByBrand(Object data){
    	 HeadObject head = new HeadObject();
    	 List<CarFactory> list = null;
         try{
             list =  carFactoryMapper.selectCarFactoryByBrand((Integer)data);
             head.setRetCode(ErrorCode.SUCCESS);
         }catch(Exception e){    
        	 e.printStackTrace();
             head.setRetCode(ErrorCode.FAILURE);
         }       
         return new ResultObject(head, list);
    }
	
	/**
	 * 
	 * @Description:查找是否同名 
	 * @param @param data
	 * @param @return
	 * @author xiaox
	 * @date 2015-3-30 下午1:50:03
	 */
	 public Object findByName(Object data){
     	 HeadObject head = new HeadObject();
         try{
       	      JSONObject content = JSONObject.fromObject(data);
       	      CarFactory carfactory = (CarFactory)JSONObject.toBean(content, CarFactory.class);
              int count=carFactoryMapper.findByName(carfactory);
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
	    * @Description: 保存 
	    * @param @param data
	    * @param @return
	    * @author xiaox
	    * @date 2015-3-30 上午13:41:36
	    */
	   public Object insertCarFactory(Object data){
	    	 HeadObject head = new HeadObject();
	         try{
	      	   JSONObject content = JSONObject.fromObject(data);
	      	   CarFactory carfactory = (CarFactory)JSONObject.toBean(content, CarFactory.class);
	      	   carfactory.setCreatetime(new Date());
	      	   carFactoryMapper.insertSelective(carfactory);
	           head.setRetCode(ErrorCode.SUCCESS);
	         }catch(Exception e){    
	        	 e.printStackTrace();
	             head.setRetCode(ErrorCode.FAILURE);
	         }       
	         return new ResultObject(head);
	    }
	   
	   /**
	    * 
	    * @Description: 删除 
	    * @param @param data
	    * @param @return
	    * @author xiaox
	    * @date 2015-3-30 下午1:53:43
	    */
	   public Object deleteCarFactory(Object data){
		   	 HeadObject head = new HeadObject();
		        try{
		      	  Integer[]  id =  (Integer[]) data;
		      	  // 先判断是否存在下级车系，有，则不能删除，要先删除下级数据
		      	  int count = 0;
		      	  boolean flag = false; //批量删除时，是否有部分有关联数据
		      	  for(int i=0;i<id.length;i++){
		      	      count = carDeptMapper.selectByFactoryId(id[i]);
    		      	  if(count == 0 ){ 
    		      		  carFactoryMapper.deleteByPrimaryKey(new Integer[]{id[i]});
    		      	  } else {
    		      		 flag=true;
    		      	  }
		      	  }
		      	  if(flag){
		      	    head.setRetCode(ErrorCode.IS_EXIST_CASCADE);
		      	  }else{
		      	    head.setRetCode(ErrorCode.SUCCESS);
		      	  }
		        }catch(Exception e){    
		     	   e.printStackTrace();
		           head.setRetCode(ErrorCode.FAILURE);
		        }       
		        return new ResultObject(head);
	   }
	   
	   
	   /**
	    * 
	    * @Description: 更新
	    * @param @param data
	    * @param @return
	    * @author xiaox
	    * @date 2015-3-30 下午2:44:16
	    */
	   public Object updateCarFactory(Object data){
		  	 HeadObject head = new HeadObject();
		       try{
		            JSONObject content = JSONObject.fromObject(data);
		            CarFactory carfactory = (CarFactory)JSONObject.toBean(content, CarFactory.class);
		            carfactory.setCreatetime(new Date());
		       	    carFactoryMapper.updateByPrimaryKeySelective(carfactory);
					head.setRetCode(ErrorCode.SUCCESS);
		       }catch(Exception e){    
		    	   e.printStackTrace();
		           head.setRetCode(ErrorCode.FAILURE);
		       }       
		       return new ResultObject(head);
	   }
			    
	   /**
	  * @description <b>查询厂商以及其包含的车系</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-21
	  * @param @param data
	  * @param @return
	  * @return Object
	*/
	public Object findCarFactoryIncludeCarDept(Object data){
		   HeadObject head = new HeadObject();
	    	 List<CarFactoryDTO> list = null;
	         try{
	             list =  carFactoryMapper.selectCarFactoryIncludeCarDept((Integer)data);
	             head.setRetCode(ErrorCode.SUCCESS);
	         }catch(Exception e){    
	        	 e.printStackTrace();
	             head.setRetCode(ErrorCode.FAILURE);
	         }       
	         return new ResultObject(head, list);
	}
	
  /**
   * @Description:查询爬虫关联车厂商数据
   * @param data
   * @return
   */
	public Object selectCarFactorySpiderDataList(Object data){
		HeadObject head = new HeadObject();
		List<AutohomeCarInfoDTO> list=(List<AutohomeCarInfoDTO>) data;
		List<CarFactoryDTO> retlist = null;
		try{
			retlist =  carFactoryMapper.selectCarFactorySpiderDataList(list);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch(Exception e){    
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}       
		return new ResultObject(head, retlist);
	}
		
	/**
	   * @Description:查询爬虫关联车厂商数据
	   * @param data
	   * @return
	   */
		public Object selectCarFactorySpiderData(Object data){
			HeadObject head = new HeadObject();
			CarFactoryDTO retObj = null;
			try{
				retObj =  carFactoryMapper.selectCarFactorySpiderData((AutohomeCarInfoDTO)data);
				head.setRetCode(ErrorCode.SUCCESS);
			}catch(Exception e){    
				e.printStackTrace();
				head.setRetCode(ErrorCode.FAILURE);
			}       
			return new ResultObject(head, retObj);
		}
	
   /**
	  * @description <b>查询厂商以及其包含的车系，并且存在商品</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-21
	  * @param @param data
	  * @param @return
	  * @return Object
	*/
	public Object findCarFactoryIncludeCarDeptHasGoods(Object data){
		 HeadObject head = new HeadObject();
    	 List<CarFactoryDTO> list = null;
         try{
             list =  carFactoryMapper.selectCarFactoryIncludeCarDeptHasGoods((Integer)data);
             head.setRetCode(ErrorCode.SUCCESS);
         }catch(Exception e){    
        	 e.printStackTrace();
             head.setRetCode(ErrorCode.FAILURE);
         }       
         return new ResultObject(head, list);
	}
	
	
}
