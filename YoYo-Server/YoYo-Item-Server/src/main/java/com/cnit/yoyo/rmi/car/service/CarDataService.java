package com.cnit.yoyo.rmi.car.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.car.CarConfigMapper;
import com.cnit.yoyo.dao.car.CarDataBoolMapper;
import com.cnit.yoyo.dao.car.CarDataCatalogMapper;
import com.cnit.yoyo.dao.car.CarDataDecimalMapper;
import com.cnit.yoyo.dao.car.CarDataIntMapper;
import com.cnit.yoyo.dao.car.CarDataListMapper;
import com.cnit.yoyo.dao.car.CarDataListValueMapper;
import com.cnit.yoyo.dao.car.CarDataMapper;
import com.cnit.yoyo.dao.car.CarDataStringMapper;
import com.cnit.yoyo.dao.car.CarMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.QueryParamObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.car.Car;
import com.cnit.yoyo.model.car.CarConfigWithBLOBs;
import com.cnit.yoyo.model.car.CarDataBool;
import com.cnit.yoyo.model.car.CarDataDecimal;
import com.cnit.yoyo.model.car.CarDataInt;
import com.cnit.yoyo.model.car.CarDataList;
import com.cnit.yoyo.model.car.CarDataListValue;
import com.cnit.yoyo.model.car.CarDataString;
import com.cnit.yoyo.model.car.dto.CarConfiginfoDTO;
import com.cnit.yoyo.model.car.dto.CarDataCatalogDTO;
import com.cnit.yoyo.model.car.dto.CarDataDTO;
import com.cnit.yoyo.model.car.dto.CarTypeDetailDTO;
import com.cnit.yoyo.model.goods.Spec;
import com.cnit.yoyo.model.goods.SpecValues;
import com.cnit.yoyo.util.StringUtil;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;
/**
 * 
 * @ClassName: CarDataService 
 * @Description: 车型数据项service 
 * @author xiaox
 * @date 2015-3-31 上午11:07:21
 */
@Service("carDataService")
public class CarDataService {
    @Autowired 
    private CarDataMapper  carDataMapper;
    @Autowired 
    private CarDataListMapper  carDataListMapper;
    
    @Autowired 
    private CarDataStringMapper  carDataStringMapper;
    @Autowired 
    private CarDataIntMapper  carDataIntMapper;
    @Autowired 
    private CarDataBoolMapper  carDataBoolMapper;
    @Autowired 
    private CarDataDecimalMapper  carDataDecimalMapper;
    @Autowired 
    private CarConfigMapper  carConfigMapper;
    @Autowired 
    private CarMapper  carMapper;
    @Autowired 
    private CarDataListValueMapper  carDataListValueMapper;
    @Autowired 
    private CarDataCatalogMapper  carDataCatalogMapper;
 
    
  
    
    /**
     * @Description: 获取车型数据项 
     * @param @param data
     * @param @return
     * @author xiaox
     * @date 2015-3-31 上午11:08:31
     */
	public Object findList(Object data){
    	 HeadObject head = new HeadObject();
    	 ResultPage<CarDataDTO> page = null;
         try{
        	 JSONObject content = JSONObject.fromObject(data);
        	 PageHelper.startPage(content.getInt("pageIndex"), content.getInt("pageSize"));
        	 CarDataDTO dto = (CarDataDTO)JSONObject.toBean(content.getJSONObject("carData"), CarDataDTO.class);
        	 if(dto!=null &&!StringUtil.isEmpty(content.optString(QueryParamObject.SORT_STR))){
        	     dto.setOrderStmt(QueryParamObject.getOrderByCause(content));
        	 }
        	 page = new ResultPage<CarDataDTO>(carDataMapper.findList(dto));
             head.setRetCode(ErrorCode.SUCCESS);
         }catch(Exception e){    
        	 e.printStackTrace();
             head.setRetCode(ErrorCode.FAILURE);
         }       
         return new ResultObject(head, JSONObject.fromObject(page));
    }
	
	/**
	 * 
	*
	* @Description: 查询所有车型数据项，用于编辑数据项的值
	* @param @param data
	* @param @return    设定文件 
	* @author ssd
	* @date 2015-5-8 下午4:10:30 
	* @return Object    返回类型 
	* @throws
	 */
	public Object findEditList(Object data){
		HeadObject head = new HeadObject();
   	 	//List<CarDataDTO> list = null;
   	 	List<CarDataCatalogDTO> dtoList = null;
   	 	
   	 	JSONObject content = JSONObject.fromObject(data);
   	 	int carId = content.getInt("carId");
   	 	int isconfig = content.getInt("isconfig");
        try{
        	dtoList = carDataCatalogMapper.findEditList();
        	//list = new ArrayList<CarDataDTO>(carDataMapper.findEditList());
        	if(isconfig == 1){//如果页面上面是完成配置的，则需要把配置项的值写入到输入框中
        		CarConfigWithBLOBs carConfig = carConfigMapper.selectByCarId(carId);
    	       	Map<Integer,String> configMap = new HashMap<Integer,String>();
    	       	if(null != carConfig) {
    	       		String config = carConfig.getConfigs();
   	           	 if(StringUtil.isEmpty(config)) {
   	           		 head.setRetCode(ErrorCode.NO_DATA);
   	           	 }else {
   	           		 JSONArray ja = JSONArray.fromObject(config);
   	           		 Object[] objs = ja.toArray();
   	           		 for(int i=0;i<objs.length;i++) {
   	           			 JSONObject json = (JSONObject) objs[i];
   	           			int dataId = json.getInt("dataId");
   	       				String value = json.getString("configValue");
   	       				configMap.put(dataId, value);
   	           		 }
    	       	}
   	           	 
   	           	 if(null != dtoList) {
   	           		 int len = dtoList.size();
   	           		 int size = 0;
   	           		 if(len > 0) {
   	           			 for(int i=0;i<len;i++) {
   	           				 CarDataCatalogDTO dto = dtoList.get(i);
   	           				 List<CarDataDTO> carDataList = dto.getCarDataDTOs();
   	           				 if(null != carDataList) {
   	           					 size = carDataList.size();
   	           					 for(int j=0;j<size;j++) {
	   	           					CarDataDTO dataDTO = carDataList.get(j);
		    	       				int dId = dataDTO.getDataId();
		    	       				if(configMap.containsKey(dId)) {
		    	       					dataDTO.setDataValue(configMap.get(dId));
		    	       				}
   	           					 }
   	           				 }
   	           			 }
   	           		 }
   	           	 }
    	       	
	           		 
	           		/*if(null != list) {
	    	       		int len = list.size();
	    	       		if(len > 0) {
	    	       			for(int i=0;i<len;i++) {
	    	       				CarDataDTO dto = list.get(i);
	    	       				int dId = dto.getDataId();
	    	       				if(configMap.containsKey(dId)) {
	    	       					dto.setDataValue(configMap.get(dId));
	    	       				}
	    	       			}
	    	       		}
	    	       	}*/
	           		 
	           		 head.setRetCode(ErrorCode.SUCCESS);
	           	 }
        	}
       	
	       	head.setRetCode(ErrorCode.SUCCESS);
        }catch(Exception e){    
       	 	e.printStackTrace();
            head.setRetCode(ErrorCode.FAILURE);
        }       
        return new ResultObject(head, dtoList);
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
       	      CarDataDTO carDataDto = (CarDataDTO)JSONObject.toBean(content, CarDataDTO.class);
              int count=carDataMapper.findByName(carDataDto);
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
	   public Object insertCarData(Object data){
	    	 HeadObject head = new HeadObject();
	    	 int id = 0;
	         try{
	      	   JSONObject content = JSONObject.fromObject(data);
	      	   CarDataDTO carDataDto = (CarDataDTO)JSONObject.toBean(content, CarDataDTO.class);
	      	   carDataDto.setCreatetime(new Date());
	      	   //有列表数据的，先插入到t_car_data_list表中，在插入到t_carDataIndex中
	      	   if(carDataDto.getDataType().equals("LIST")){
		      	   CarDataList list = new CarDataList() ;
		      	   list.setData(carDataDto.getListValue());
		      	   list.setListName(carDataDto.getListName());
		      	   carDataListMapper.insertSelective(list);
		      	   id= list.getListId();
	      	   }
	      	   carDataDto.setListId(id);
	      	   carDataMapper.insertSelective(carDataDto);
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
	   public Object deleteCarData(Object data){
	   	 	HeadObject head = new HeadObject();
	        try{
	        	 Integer[] id = (Integer[])data;
	        	 int str_count = 0;
                 int int_count = 0;
                 int dec_count = 0;
                 int bool_count = 0;
                 boolean flag = false;
	        	 for(int i=0;id!=null && i<id.length;i++){
    		      	 //先判断是否存在关联数据
    		      	 str_count = carDataStringMapper.selectByDataId(id[i]);
    		      	 int_count = carDataIntMapper.selectByDataId(id[i]);
    		      	 dec_count = carDataDecimalMapper.selectByDataId(id[i]);
    		      	 bool_count = carDataBoolMapper.selectByDataId(id[i]);
    		      	 if(str_count==0 && int_count==0 && dec_count==0 && bool_count==0){ //没有关联数据可以删除
    		      		carDataMapper.deleteByPrimaryKey(id[i]);
    		      	 }else {
    		      	   flag = true;
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
	    * @date 2015-3-31 下午2:44:16
	    */
	   public Object updateCarData(Object data){
		  	 HeadObject head = new HeadObject();
		       try{
		            JSONObject content = JSONObject.fromObject(data);
		            CarDataDTO dto = (CarDataDTO)JSONObject.toBean(content, CarDataDTO.class);
		            dto.setCreatetime(new Date());
		            if(dto.getDataType().equals("LIST")){ //更新了列表数据项
				      	   CarDataList list = new CarDataList() ;
				      	   list.setData(dto.getListValue());
				      	   list.setListName(dto.getListName());
				      	   if(dto.getListId()!=0){ 
					      	   list.setListId(dto.getListId());
					      	   carDataListMapper.updateByPrimaryKeySelective(list);
				      	   }else{ //新增一个列表数据项
				      		 carDataListMapper.insertSelective(list);
				      		 dto.setListId(list.getListId());
				      	   }
			      	}
		            carDataMapper.updateByPrimaryKeySelective(dto);
					head.setRetCode(ErrorCode.SUCCESS);
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
	    * @author ssd
	    */
	   public Object insertCarDataString(Object data){
	    	 HeadObject head = new HeadObject();
	    	 int id = 0;
	         try{
	      	   JSONObject content = JSONObject.fromObject(data);
	      	   CarDataString record = (CarDataString)JSONObject.toBean(content, CarDataString.class);
	      	   carDataStringMapper.insertSelective(record);
	           head.setRetCode(ErrorCode.SUCCESS);
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
	    * @author ssd
	    */
	   public Object insertCarDataInt(Object data){
	    	 HeadObject head = new HeadObject();
	    	 int id = 0;
	         try{
	      	   JSONObject content = JSONObject.fromObject(data);
	      	   CarDataInt record = (CarDataInt)JSONObject.toBean(content, CarDataInt.class);
	      	   carDataIntMapper.insertSelective(record);
	           head.setRetCode(ErrorCode.SUCCESS);
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
	    * @author ssd
	    */
	   public Object insertCarDataBool(Object data){
	    	 HeadObject head = new HeadObject();
	    	 int id = 0;
	         try{
	      	   JSONObject content = JSONObject.fromObject(data);
	      	   CarDataBool record = (CarDataBool)JSONObject.toBean(content, CarDataBool.class);
	      	   carDataBoolMapper.insertSelective(record);
	           head.setRetCode(ErrorCode.SUCCESS);
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
	    * @author ssd
	    */
	   public Object insertCarDataDecimal(Object data){
	    	 HeadObject head = new HeadObject();
	    	 int id = 0;
	         try{
	      	   JSONObject content = JSONObject.fromObject(data);
	      	   CarDataDecimal record = (CarDataDecimal)JSONObject.toBean(content, CarDataDecimal.class);
	      	   carDataDecimalMapper.insertSelective(record);
	           head.setRetCode(ErrorCode.SUCCESS);
	         }catch(Exception e){    
	        	 e.printStackTrace();
	             head.setRetCode(ErrorCode.FAILURE);
	         }       
	         return new ResultObject(head);
	    }
	   
	   /**
	     * @description 新增配置信息值
	     * @author ssd
	     * @param data
	     * @return
	     */
	    public Object saveCarDataValues(Object data) throws Exception {
	        JSONObject content = JSONObject.fromObject(data);
	        JSONObject carIdJson = content.getJSONObject("carId");
	        HeadObject head = new HeadObject();
	        if (null != carIdJson) {// 修改
	        	
	        	int dataNum = 0;
	            String dataType = "";
	            String configValue = "";
	            int carNum =  (Integer) carIdJson.get("carId");
	            JSONArray valuesJSON1 = null;
	           
	            JSONArray inserted = content.getJSONArray("inserted");// 新添加的规格值
	            if(!inserted.isEmpty()) {
	            	valuesJSON1 = inserted;
	            }
	            /*JSONArray updated = content.getJSONArray("updated");// 修改的规格值
	            if(!updated.isEmpty()) {
	            	valuesJSON1 = updated;
	            }
	            JSONArray deleted = content.getJSONArray("deleted");// 删除的规格值
	            if(!deleted.isEmpty()) {
	            	valuesJSON1 = deleted;
	            }*/
	            Iterator insertedIter = valuesJSON1.iterator();
	            try{
	            	JSONArray ja = new JSONArray();
	            	while (insertedIter.hasNext()) {
		                JSONObject object = (JSONObject) insertedIter.next();
		                CarConfiginfoDTO dto = (CarConfiginfoDTO) JSONObject.toBean(object, CarConfiginfoDTO.class);
		                dataType = dto.getDataType();
		            	dataNum = Integer.parseInt(dto.getDataId());
		            	configValue = dto.getConfigValue();
		            	if(StringUtil.isEmpty(configValue)) {
	            			continue;
	            		}
		            	int num = 0;
		            	if("STR".equals(dataType)) {
		            		CarDataString carData = new CarDataString();
		            		carData.setCarId(carNum);
		            		carData.setDataId(dataNum);
		            		carData.setValue(configValue);
		            		num = carDataStringMapper.selectCountByPrimaryKey(carData);
		            		if(num > 0) {
		            			carDataStringMapper.updateByPrimaryKeySelective(carData);
		            		}else {
		            			carDataStringMapper.insertSelective(carData);
		            		}
		            		
		            	}else if("INT".equals(dataType)) {
		            		CarDataInt carData = new CarDataInt();
		            		carData.setCarId(carNum);
		            		carData.setDataId(dataNum);
		            		carData.setValue(Integer.parseInt(configValue));
		            		num = carDataIntMapper.selectCountByPrimaryKey(carData);
		            		if(num > 0) {
		            			carDataIntMapper.updateByPrimaryKeySelective(carData);
		            		}else {
		            			carDataIntMapper.insertSelective(carData);
		            		}
		            		
		            	}else if("BOL".equals(dataType)) {
		            		CarDataBool carData = new CarDataBool();
		            		carData.setCarId(carNum);
		            		carData.setDataId(dataNum);
		            		carData.setValue(configValue);
		            		num = carDataBoolMapper.selectCountByPrimaryKey(carData);
		            		if(num > 0) {
		            			carDataBoolMapper.updateByPrimaryKeySelective(carData);
		            		}else {
		            			carDataBoolMapper.insertSelective(carData);
		            		}
		            		
		            	}else if("DEC".equals(dataType)) {
		            		CarDataDecimal carData = new CarDataDecimal();
		            		carData.setCarId(carNum);
		            		carData.setDataId(dataNum);
		            		carData.setValue(BigDecimal.valueOf(Double.parseDouble(configValue)));
		            		num = carDataDecimalMapper.selectCountByPrimaryKey(carData);
		            		if(num > 0) {
		            			carDataDecimalMapper.updateByPrimaryKeySelective(carData);
		            		}else {
		            			carDataDecimalMapper.insertSelective(carData);
		            		}
		            		
		            	}else if("LIST".equals(dataType)) {
		            		CarDataListValue carData = new CarDataListValue();
		            		carData.setCarId(carNum);
		            		carData.setDataId(dataNum);
		            		carData.setValue(configValue);
		            		num = carDataListValueMapper.selectCountByPrimaryKey(carData);
		            		if(num > 0) {
		            			carDataListValueMapper.updateByPrimaryKeySelective(carData);
		            		}else {
		            			carDataListValueMapper.insertSelective(carData);
		            		}
		            		
		            	}
		            	
		            	ja.add(object);
		            	
		            }
	            	CarConfigWithBLOBs carConfig = new CarConfigWithBLOBs();
	            	carConfig.setCarId(carNum);
	            	carConfig.setConfigs(ja.toString());
	            	int count = carConfigMapper.selectCountByCarId(carNum);
	            	if(count > 0) {
	            		carConfigMapper.updateByCarIdSelective(carConfig);
	            	}else {
	            		carConfigMapper.insertSelective(carConfig);
	            	}
	            	
	            	
	            	Car car = new Car();
	            	car.setCarId(carNum);
	            	car.setIsconfig(1);
	            	carMapper.updateByPrimaryKeySelective(car);
	            	head.setRetCode(ErrorCode.SUCCESS);
	            }catch(Exception e){    
		        	 e.printStackTrace();
		             head.setRetCode(ErrorCode.FAILURE);
		         }
	            
	        } 
	        
	        return new ResultObject(head);
	    }
	    
	    /**
	     * 更新配置值
	     * @param data
	     * @return
	     * @throws Exception
	     */
	    public Object updateCarDataValues(Object data) throws Exception {
	        JSONObject content = JSONObject.fromObject(data);
	        JSONObject carIdJson = content.getJSONObject("carId");
	        
	        if (null != carIdJson) {// 修改
	        	
	        	int dataNum = 0;
	            String dataType = "";
	            String configValue = "";
	            int carNum =  (Integer) carIdJson.get("carId");
	            JSONArray valuesJSON1 = null;
	           
	            JSONArray inserted = content.getJSONArray("inserted");// 新添加的规格值
	            if(!inserted.isEmpty()) {
	            	valuesJSON1 = inserted;
	            }
	            /*JSONArray updated = content.getJSONArray("updated");// 修改的规格值
	            if(!updated.isEmpty()) {
	            	valuesJSON1 = updated;
	            }
	            JSONArray deleted = content.getJSONArray("deleted");// 删除的规格值
	            if(!deleted.isEmpty()) {
	            	valuesJSON1 = deleted;
	            }*/
	            Iterator insertedIter = valuesJSON1.iterator();
	            while (insertedIter.hasNext()) {
	                JSONObject object = (JSONObject) insertedIter.next();
	                CarConfiginfoDTO dto = (CarConfiginfoDTO) JSONObject.toBean(object, CarConfiginfoDTO.class);
	                dataType = dto.getDataType();
	            	dataNum = Integer.parseInt(dto.getDataId());
	            	configValue = dto.getConfigValue();
	            	if(StringUtil.isEmpty(configValue)) {
            			continue;
            		}
	            	if("STR".equals(dataType)) {
	            		CarDataString carData = new CarDataString();
	            		carData.setCarId(carNum);
	            		carData.setDataId(dataNum);
	            		carData.setValue(configValue);
	            		carDataStringMapper.updateByPrimaryKeySelective(carData);
	            	}else if("INT".equals(dataType)) {
	            		CarDataInt carData = new CarDataInt();
	            		carData.setCarId(carNum);
	            		carData.setDataId(dataNum);
	            		carData.setValue(Integer.parseInt(configValue));
	            		carDataIntMapper.updateByPrimaryKeySelective(carData);
	            	}else if("BOL".equals(dataType)) {
	            		CarDataBool carData = new CarDataBool();
	            		carData.setCarId(carNum);
	            		carData.setDataId(dataNum);
	            		carData.setValue(configValue);
	            		carDataBoolMapper.updateByPrimaryKeySelective(carData);
	            	}else if("DEC".equals(dataType)) {
	            		CarDataDecimal carData = new CarDataDecimal();
	            		carData.setCarId(carNum);
	            		carData.setDataId(dataNum);
	            		carData.setValue(BigDecimal.valueOf(Long.parseLong(configValue)));
	            		carDataDecimalMapper.updateByPrimaryKeySelective(carData);
	            	}else if("LIST".equals(dataType)) {
	            		CarDataListValue carData = new CarDataListValue();
	            		carData.setCarId(carNum);
	            		carData.setDataId(dataNum);
	            		carData.setValue(configValue);
	            		carDataListValueMapper.updateByPrimaryKeySelective(carData);
	            	}
	            	
	            	CarConfigWithBLOBs carConfig = new CarConfigWithBLOBs();
	            	carConfig.setCarId(carNum);
	            	carConfig.setConfigs(object.toString());
	            	carConfigMapper.updateByPrimaryKeySelective(carConfig);
	            }
	        } 
	        return new HeadObject(ErrorCode.SUCCESS);
	    }
			    
	   
    
}
