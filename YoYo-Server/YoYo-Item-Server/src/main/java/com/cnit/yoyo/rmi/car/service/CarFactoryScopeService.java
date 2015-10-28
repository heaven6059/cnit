package com.cnit.yoyo.rmi.car.service;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.dao.car.CarFactoryMapper;
import com.cnit.yoyo.dao.car.CarFactoryScopeMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.car.CarFactoryScope;
import com.cnit.yoyo.model.car.dto.CarFactoryScopeQryDTO;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;
/**
 * 
 * @ClassName: CarFactoryService 
 * @Description: 汽车厂商区域service
 * @author xiaox
 * @date 2015-3-28 上午10:53:51
 */
@Service("carFactoryScopeService")
public class CarFactoryScopeService {
    @Autowired 
    private CarFactoryScopeMapper  carFactoryScopeMapper;
    
    @Autowired 
    private CarFactoryMapper  carFactoryMapper;
    
  
    
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
    	 ResultPage<CarFactoryScope> page = null;
         try{
        	 CarFactoryScopeQryDTO dto = (CarFactoryScopeQryDTO)data;
        	 PageHelper.startPage(dto.getPage(), dto.getRows());
        	 page = new ResultPage<CarFactoryScope>(carFactoryScopeMapper.findList(dto));
             head.setRetCode(ErrorCode.SUCCESS);
         }catch(Exception e){    
        	 e.printStackTrace();
             head.setRetCode(ErrorCode.FAILURE);
         }       
         return new ResultObject(head, JSON.toJSON(page));
    }
    
  
	/**
	 * 
	 * @Description: 查找是否已有同名的  
	 * @param @param data
	 * @param @return
	 * @author xiaox
	 * @date 2015-3-30 上午10:38:49
	 */
   public Object findByName(Object data){
     	 HeadObject head = new HeadObject();
         try{
        	 JSONObject content = (JSONObject) JSONObject.toJSON(data);
       	      CarFactoryScope scope =JSON.toJavaObject(content, CarFactoryScope.class);
              int count=carFactoryScopeMapper.findByName(scope);
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
    * @date 2015-3-30 上午10:41:36
    */
   public Object insertCarFactoryScope(Object data){
    	 HeadObject head = new HeadObject();
         try{
        	 JSONObject content = (JSONObject) JSONObject.toJSON(data);
      	   CarFactoryScope scope = JSON.toJavaObject(content, CarFactoryScope.class);
      	   scope.setCreatetime(new Date());
      	   scope.setLastMidifity(new Date());
           carFactoryScopeMapper.insertSelective(scope);
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
   public Object deleteCarFactoryScope(Object data){
   	 HeadObject head = new HeadObject();
        try{
      	  //先判断是否存在下级汽车厂商，有，则不能删除，要先删除下级数据
      	  List<String>  count = carFactoryMapper.selectByScopeIds((Integer[]) data);
      	  if(count.size() == 0 ){
      		  carFactoryScopeMapper.deleteById((Integer[]) data);
      		  head.setRetCode(ErrorCode.SUCCESS);
      	  } else {      		  
      		  head.setRetMsg(JSONArray.fromObject(count).toString());
      		  head.setRetCode(ErrorCode.IS_EXIST_CASCADE);
      		  head.setRetType(GlobalStatic.RET_TYPE_OTHER);
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
    * @date 2015-3-30 上午11:30:24
    */
   public Object updateCarFactoryScope(Object data){
  	 HeadObject head = new HeadObject();
       try{
    	   JSONObject content = (JSONObject) JSONObject.toJSON(data);
            CarFactoryScope scope =JSON.toJavaObject(content, CarFactoryScope.class);
       	    scope.setLastMidifity(new Date());
       	    carFactoryScopeMapper.updateByPrimaryKeySelective(scope);
			head.setRetCode(ErrorCode.SUCCESS);
       }catch(Exception e){    
    	   e.printStackTrace();
           head.setRetCode(ErrorCode.FAILURE);
       }       
       return new ResultObject(head);
   }
	    
   /**
  * @description <b>根据ID获取汽车厂商区域</b>
  * @author 王鹏
  * @version 1.0.0
  * @data 2015-5-8
  * @param @param data
  * @param @return
  * @return Object
*/
public Object findCarFactoryScopeById(Object data){
  	 HeadObject head = new HeadObject();
  	CarFactoryScope carFactoryScope=null;
       try{
       	    carFactoryScope = carFactoryScopeMapper.selectByPrimaryKey((Integer) data);
			head.setRetCode(ErrorCode.SUCCESS);
       }catch(Exception e){    
    	   e.printStackTrace();
           head.setRetCode(ErrorCode.FAILURE);
       }       
       return new ResultObject(head,carFactoryScope);
   }
    
}
