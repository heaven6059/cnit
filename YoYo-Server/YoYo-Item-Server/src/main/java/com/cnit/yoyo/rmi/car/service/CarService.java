package com.cnit.yoyo.rmi.car.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.autohome.model.AutohomeCarInfoDTO;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.car.AccessoryCarIndexMapper;
import com.cnit.yoyo.dao.car.CarConfigMapper;
import com.cnit.yoyo.dao.car.CarDataBoolMapper;
import com.cnit.yoyo.dao.car.CarDataDecimalMapper;
import com.cnit.yoyo.dao.car.CarDataIntMapper;
import com.cnit.yoyo.dao.car.CarMaintainMapper;
import com.cnit.yoyo.dao.car.CarMapper;
import com.cnit.yoyo.dao.car.CarSpiderCompareMapper;
import com.cnit.yoyo.dao.goods.GoodsMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.QueryParamObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.car.Car;
import com.cnit.yoyo.model.car.CarSpiderCompare;
import com.cnit.yoyo.model.car.dto.CarTypeConfigDTO;
import com.cnit.yoyo.model.car.dto.CarTypeDTO;
import com.cnit.yoyo.model.car.dto.CarTypeDeptDTO;
import com.cnit.yoyo.model.car.dto.CarTypeDetailDTO;
import com.cnit.yoyo.model.goods.Brand;
import com.cnit.yoyo.util.StringUtil;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @ClassName: CarService 
 * @Description: 车型service
 * @author xiaox
 * @date 2015-3-26 下午7:03:07
 */
@Service("carService")
public class CarService {
    @Autowired 
    private CarMapper  carMapper;
    
    @Autowired 
    private AccessoryCarIndexMapper  accessoryCarIndexMapper;
    
    @Autowired 
    private CarMaintainMapper  carMaintainMapper;
    @Autowired 
    private CarDataIntMapper  carDataIntMapper;
    @Autowired 
    private CarDataBoolMapper  carDataBoolMapper;
    @Autowired 
    private CarDataDecimalMapper  carDataDecimalMapper;
    @Autowired 
    private CarConfigMapper  carConfigMapper;
    @Autowired 
    private GoodsMapper  goodsMapper;
    @Autowired
    private CarSpiderCompareMapper carSpiderCompareMapper;
    
    
    /**
     * @Description:查询爬虫关联车型数据
     * @param data
     * @return
     */
    public Object selectCarSpiderData(Object data){
    	HeadObject head = new HeadObject();
		Car retObj = null;
		try{
			retObj =  carMapper.selectCarSpiderData((AutohomeCarInfoDTO)data);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch(Exception e){    
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}       
		return new ResultObject(head, retObj);
    }
    
    
    /**
     * @Description:批量更新爬虫数据
     * @param data
     * @return
     */
    public Object updateSpiderCompareDataList(Object data){
    	HeadObject head = new HeadObject();
    	List<CarSpiderCompare> list=(List<CarSpiderCompare>) data;
    	int num=0;
    	try{
    		carSpiderCompareMapper.batchDeleteByParams(list);
    		num=carSpiderCompareMapper.batchInsertByParams(list);
    		head.setRetCode(ErrorCode.SUCCESS);
    	}catch(Exception e){
       	 e.printStackTrace();
         head.setRetCode(ErrorCode.FAILURE);
    	}
    	return new ResultObject(head, num);
    }
    
    /**
     * 
     * @Title: findList 
     * @Description: 获取车型 
     * @param @param data
     * @param @return    设定文件 
     * @author xiaox
     * @date 2015-3-26 下午7:03:31
     */
	public Object findList(Object data){
    	 HeadObject head = new HeadObject();
    	 List<Car> cars = null;
         try{
			 cars = carMapper.findList((Integer)data);
             head.setRetCode(ErrorCode.SUCCESS);
         }catch(Exception e){    
        	 e.printStackTrace();
             head.setRetCode(ErrorCode.FAILURE);
         }       
         return new ResultObject(head, cars);
    }
	
	  /**
     * 
     * @Description: 根据车系ID获取车型 
     */
	public Object findCarByDeptId(Object data){
    	 HeadObject head = new HeadObject();
    	 List<CarTypeDeptDTO> cars = null;
         try{
			 cars = carMapper.findCarByDeptId((Integer)data);
             head.setRetCode(ErrorCode.SUCCESS);
         }catch(Exception e){    
        	 e.printStackTrace();
             head.setRetCode(ErrorCode.FAILURE);
         }       
         return new ResultObject(head, cars);
    }
	
	/**
	  * @description <b>根据车系查找车型</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-11
	  * @param @param data
	  * @param @return
	  * @return Object
	*/
	public Object findCarByDept(Object data){
    	 HeadObject head = new HeadObject();
    	 List<Car> cars = null;
         try{
			 cars = carMapper.selectCarByDept((Integer)data);
             head.setRetCode(ErrorCode.SUCCESS);
         }catch(Exception e){    
        	 e.printStackTrace();
             head.setRetCode(ErrorCode.FAILURE);
         }       
         return new ResultObject(head, cars);
    }
	
	/**
	 * @description <b>查找车型</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015年7月30日
	 * @param data
	 * @return
	 * Object
	*/
	public Object findCar(Object data){
   	 HeadObject head = new HeadObject();
   	 List<Car> cars = null;
        try{
        	cars = carMapper.selectCar((Map<String, Object>) data);
            head.setRetCode(ErrorCode.SUCCESS);
        }catch(Exception e){    
        	e.printStackTrace();
            head.setRetCode(ErrorCode.FAILURE);
        }       
        return new ResultObject(head, cars);
   }
	
	/**
     * 
     * @Description: 获取车型 
     * @param @param data
     * @param @return    设定文件 
     */
	public Object findCarTypeList(Object data){
		HeadObject head = new HeadObject();
   	 	ResultPage<CarTypeDTO> page = null;
         try{
             head.setRetCode(ErrorCode.SUCCESS);
             JSONObject content = JSONObject.fromObject(data);
             PageHelper.startPage(content.getInt("pageIndex"), content.getInt("pageSize"));
             CarTypeDTO carTypeDTO = (CarTypeDTO)JSONObject.toBean(content.getJSONObject("carTypeDTO"), CarTypeDTO.class);
			 page = new ResultPage( carMapper.findCarTypeList(carTypeDTO.getBrandId(),carTypeDTO.getFactoryId(),
					 carTypeDTO.getCarDeptId(),carTypeDTO.getCarYearId(),carTypeDTO.getGradeId(),carTypeDTO.getCarName(),
					 carTypeDTO.getKeyword(),QueryParamObject.getOrderByCause(content)));
             head.setRetCode(ErrorCode.SUCCESS);
         }catch(Exception e){    
        	 e.printStackTrace();
             head.setRetCode(ErrorCode.FAILURE);
         }       
         return new ResultObject(head, JSONObject.fromObject(page));
    }
	
	
	/**
	 * 
	* @Title: findCarTypeDetail
	* @Description: 查询车型详情
	* @param @param data
	* @param @return    设定文件 
	* @author ssd
	* @date 2015-4-7 下午3:34:05 
	* @return Object    返回类型 
	* @throws
	 */
	public Object findCarTypeDetail(Object data){
		HeadObject head = new HeadObject();
		//Car car = null;
		List<CarTypeDetailDTO> dtoList = new ArrayList<CarTypeDetailDTO>();
         try{
             Integer carId = (Integer) data;
             CarTypeConfigDTO car = carMapper.findCarDetail(carId);
             if(null != car) {
            	 String config = car.getConfigs();
            	 if(StringUtil.isEmpty(config)) {
            		 head.setRetCode(ErrorCode.NO_DATA);
            	 }else {
            		 JSONArray ja = JSONArray.fromObject(config);
            		 Object[] objs = ja.toArray();
            		 for(int i=0;i<objs.length;i++) {
            			 JSONObject json = (JSONObject) objs[i];
            			 String displayName = json.getString("displayName");
                    	 String displayValue = json.getString("configValue");
                    	 CarTypeDetailDTO dto = new CarTypeDetailDTO();
                    	 dto.setCarDeptId(String.valueOf(car.getCarDeptId()));
                    	 dto.setCarId(String.valueOf(car.getCarId()));
                    	 dto.setCarName(car.getCarName());
                    	 dto.setDisplayName(displayName);
                    	 dto.setDisplayValue(displayValue);
                    	 dto.setIconFile(car.getIconFile());
                    	 dto.setKeyword(car.getKeyword());
                    	 dto.setPrice(car.getPrice());
                    	 dto.setStatus(car.getStatus());
                    	 dto.setViewCount(String.valueOf(car.getViewCount()));
                    	 dtoList.add(dto);
            		 }
            		 
            		 head.setRetCode(ErrorCode.SUCCESS);
            	 }
            	 
             }else {
            	 head.setRetCode(ErrorCode.NO_DATA);
             }
             
             /*if(null != car) {
            	 List<CarConfigWithBLOBs> list = car.getCarConfigs();
                 if(null != list) {
                	 int len = list.size();
                	 if(len > 0) {
                		 for(int i=0;i<len;i++) {
                        	 String config = list.get(i).getConfigs();
                        	 if(StringUtil.isEmpty(config)) {
                        		 continue;
                        	 }
                        	 JSONArray ja = JSONArray.fromObject(config);
                        	
                        	 String displayName = jsonConfig.getString("displayName");
                        	 String displayValue = jsonConfig.getString("configValue");
                        	 CarTypeDetailDTO dto = new CarTypeDetailDTO();
                        	 dto.setCarDeptId(String.valueOf(car.getCarDeptId()));
                        	 dto.setCarId(String.valueOf(car.getCarId()));
                        	 dto.setCarName(car.getCarName());
                        	 dto.setDisplayName(displayName);
                        	 dto.setDisplayValue(displayValue);
                        	 dto.setIconFile(car.getIconFile());
                        	 dto.setKeyword(car.getKeyword());
                        	 dto.setPrice(car.getPrice());
                        	 dto.setStatus(car.getStatus());
                        	 dto.setViewCount(String.valueOf(car.getViewCount()));
                        	 dtoList.add(dto);
                         }
                	 } else {
                    	 head.setRetCode(ErrorCode.NO_DATA);
                     }
                 } else {
                	 head.setRetCode(ErrorCode.NO_DATA);
                 }
                 
                 
                 head.setRetCode(ErrorCode.SUCCESS);
             }else {
            	 head.setRetCode(ErrorCode.NO_DATA);
             }*/
             
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
	 * @author ssd
	 */
	 public Object findCarTypeByName(Object data){
     	 HeadObject head = new HeadObject();
         try{
       	      JSONObject content = JSONObject.fromObject(data);
       	      CarTypeDTO carTypeDTO = (CarTypeDTO)JSONObject.toBean(content, CarTypeDTO.class);
              int count=carMapper.findCarTypeByName(carTypeDTO);
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
	 * @Title: insertCarType 
	 * @Description:  插入车型信息，插入之前会根据车型名称进行判断重复
	 * @param @param data
	 * @param @return    设定文件 
	 * @author ssd
	 * @date 2015-4-7 上午11:15:32 
	 * @return Object    返回类型 
	 * @throws
	  */
	   public Object insertCarType(Object data){
	    	 HeadObject head = new HeadObject();
	    	 int id = 0;
	         try{
	      	   JSONObject content = JSONObject.fromObject(data);
	      	 CarTypeDTO carTypeDTO = (CarTypeDTO)JSONObject.toBean(content, CarTypeDTO.class);
	      	 String carGearBox = carTypeDTO.getCargearbox();
	      	 StringBuffer sb = new StringBuffer();
	      	sb.append(carTypeDTO.getCarDeptName());
	      	sb.append(" ");
	      	sb.append(carTypeDTO.getCarYearValue());
	      	sb.append(" ");
	      	sb.append(carTypeDTO.getCarName());
	      	sb.append(" ");
	      	sb.append(carGearBox);
	      	sb.append(" ");
	      	sb.append(carTypeDTO.getGradeName());
	      	
	      	String carName = sb.toString();
	      	 Car record = new Car();
	      	record.setCarName(carName);
	      	record.setCarDeptId(carTypeDTO.getCarDeptId());
	      	record.setIconFile(carTypeDTO.getIconFile());
	      	record.setKeyword(carTypeDTO.getKeyword());
	      	record.setLastUpdate(new Date());
	      	record.setPrice(carTypeDTO.getPrice());
	      	record.setStatus(carTypeDTO.getStatus());
	      	record.setFilterId(carTypeDTO.getCarYearId());
	      	 int count=carMapper.findCarByName(record);
	           if(count == 0){
	        	   carMapper.insertSelective(record);
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
	    * @Description: 删除车型数据，需要把关联的车型数据配置项的表数据都删除掉 
	    * @param @param data
	    * @param @return
	    * @author ssd
	    */
	   public Object deleteCarType(Object data){
		   	/* HeadObject head = new HeadObject();
		        try{
		      	  Integer carId = (Integer) data;
		      	  // 先判断是否存在下级车系，有，则不能删除，要先删除下级数据
		      	  int count = carTypeAccessoryMapper.selectByCarId(carId);
		      	  if(count == 0 ){ 
		      		carConfigMapper.deleteByCarId(carId);
		      		carMapper.deleteByPrimaryKey(carId);
		      		//把关联的车型数据配置项的表数据都删除掉
		      		carDataStringMapper.deleteByCarId(carId);
		      		carDataIntMapper.deleteByCarId(carId);
		      		carDataBoolMapper.deleteByCarId(carId);
		      		carDataDecimalMapper.deleteByCarId(carId);
		      		
		      		  head.setRetCode(ErrorCode.SUCCESS);
		      	  } else {
		      		  head.setRetCode(ErrorCode.IS_EXIST_CASCADE);
		      	  }
				 
		        }catch(Exception e){    
		     	   e.printStackTrace();
		           head.setRetCode(ErrorCode.FAILURE);
		        }       
		        return new ResultObject(head);*/
		        
		        HeadObject head = new HeadObject();
		        try{
		      	  Integer[]  id =  (Integer[]) data;
		      	
		      	  // 先判断是否存在下级车系，有，则不能删除，要先删除下级数据
		      	  if(null != id && id.length > 0) {
		      		List<Integer> carIds = accessoryCarIndexMapper.selectByCarId(id);
		      		List<Integer> goodsCarIds = goodsMapper.findGoodsByCarId(id);
		      		List<Integer> maintainCarIds = carMaintainMapper.selectByCarId(id);//保养周期关联车型
		      		Map<String, Object> params = new HashMap<String, Object>();
		        	params.put("isdel", 1);
		      		if((null != carIds && carIds.size() > 0) || 
		      				(null != maintainCarIds && maintainCarIds.size() > 0) || 
		      				(null != goodsCarIds && goodsCarIds.size() > 0)) {
		      			List<Integer> ids = new ArrayList<Integer>();
		      			List<Integer> goodIds = new ArrayList<Integer>();
		      			List<Integer> finalIds = new ArrayList<Integer>();
		      			if(null != carIds) {
		      				for (int i = 0; i < id.length; i++) {
								if (!carIds.contains(id[i])) {
									ids.add(id[i]);
								}
							}
		      			}
		      			
		      			if(null != goodsCarIds) {
		      				for (int i = 0; i < ids.size(); i++) {
								if (!goodsCarIds.contains(ids.get(i))) {
									goodIds.add(ids.get(i));
								}
							}
		      			}
		      			
		      			if(null != maintainCarIds) {
		      				for (int i = 0; i < goodIds.size(); i++) {
								if (!maintainCarIds.contains(goodIds.get(i))) {
									finalIds.add(goodIds.get(i));
								}
							}
		      			}
						
						if (finalIds.size() > 0) {
				        	params.put("ids", finalIds);
				        	carMapper.updateIsdelByIds(params);
						}
						head.setRetCode(ErrorCode.IS_EXIST_CASCADE);
						carIds.clear();
						carIds = null;
		      		}else {
			        	params.put("ids", id);
			        	carMapper.updateIsdelByIds(params);
						head.setRetCode(ErrorCode.SUCCESS);
					}
		      		params.clear();
		      		params = null;
		      	  }else {
						head.setRetCode(ErrorCode.PDE0001);
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
	    * @author ssd
	    */
	   public Object updateCarType(Object data){
		  	 HeadObject head = new HeadObject();
		       try{
		            JSONObject content = JSONObject.fromObject(data);
		            CarTypeDTO carTypeDTO = (CarTypeDTO)JSONObject.toBean(content, CarTypeDTO.class);
		            StringBuffer sb = new StringBuffer();
		            sb.append(carTypeDTO.getCarDeptName());
			      	sb.append(" ");
			      	sb.append(carTypeDTO.getCarYearValue());
			      	sb.append(" ");
			      	sb.append(carTypeDTO.getCarName());
			      	sb.append(" ");
			      	sb.append(carTypeDTO.getCargearbox());
			      	sb.append(" ");
			      	sb.append(carTypeDTO.getGradeName());
			      	 Car record = new Car();
			      	record.setCarId(Integer.parseInt(carTypeDTO.getCarId()));
			      	record.setCarName(sb.toString());
			      	record.setCarDeptId(carTypeDTO.getCarDeptId());
			      	record.setIconFile(carTypeDTO.getIconFile());
			      	record.setKeyword(carTypeDTO.getKeyword());
			      	record.setLastUpdate(new Date());
			      	record.setPrice(carTypeDTO.getPrice());
			      	record.setStatus(carTypeDTO.getStatus());
			      	record.setFilterId(carTypeDTO.getCarYearId());
			      	 //int count=carMapper.findCarByName(record);
			           //if(count > 0){
			        	   carMapper.updateByPrimaryKeySelective(record);
				           head.setRetCode(ErrorCode.SUCCESS);
			           //}else{
						//   head.setRetCode(ErrorCode.NO_DATA);
					   //}
		       }catch(Exception e){    
		    	   e.printStackTrace();
		           head.setRetCode(ErrorCode.FAILURE);
		       }       
		       return new ResultObject(head);
	   }
    
	   
	   /**
	    * 
	    *@description 通过carId获取车系，年款，厂商等的信息
	    *@detail <方法详细描述>
	    *@author <a href="xiaoxiang@cnit.com">肖湘</a>
	    *@version 1.0.0
	    *@data 2015-4-28
	    *@param data 车型id
	    *@return
	    */
	   public Object findCarInfoById(Object data){
	         HeadObject head = new HeadObject();
	         List<CarTypeDTO> cars = null;
	         CarTypeDTO car=null;
	         try{
	        	 Integer [] carId={(Integer) data};
	             cars = carMapper.findCarInfo(carId);
	             if(cars.size()>0){
	            	 car=cars.get(0);
	             }
	             head.setRetCode(ErrorCode.SUCCESS);
	         }catch(Exception e){    
	             e.printStackTrace();
	             head.setRetCode(ErrorCode.FAILURE);
	         }       
	         return new ResultObject(head, car);
	    }
    
	/**
	  * @description <b>通过车型Id(数组)获取车型信息</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-21
	  * @param @param data
	  * @param @return
	  * @return Object
	*/
	public Object findCarInfoByCarIds(Object carId){
		   HeadObject head = new HeadObject();
	         List<CarTypeDTO> cars = null;
	         try{
	             cars = carMapper.findCarInfo((Integer[]) carId);
	             head.setRetCode(ErrorCode.SUCCESS);
	         }catch(Exception e){    
	             e.printStackTrace();
	             head.setRetCode(ErrorCode.FAILURE);
	         }       
	         return new ResultObject(head, cars);
	   }
	   
	   /**
	    * @Title:  selectByPrimaryKey  
	    * @Description:  TODO(根据车型id查询车型对象)  
	    * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	    * @date 2015-5-11 下午1:40:48  
	    * @version 1.0.0 
	    * @param @param data
	    * @param @return
	    * @return Object  返回类型 
	    * @throws
	    */
	   public Object selectByPrimaryKey(Object data){
		     HeadObject head = new HeadObject();
	    	 Car car = null;
	         try{
				 car = carMapper.selectByPrimaryKey((Integer)data);
	             head.setRetCode(ErrorCode.SUCCESS);
	         }catch(Exception e){    
	        	 e.printStackTrace();
	             head.setRetCode(ErrorCode.FAILURE);
	         }       
	         return new ResultObject(head, car);
	   }
	   
	   /**
	    * @Title:  selectByCarDeptId  
	    * @Description:  TODO(根据车系查询车型列表)  
	    * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	    * @date 2015-5-11 下午1:41:42  
	    * @version 1.0.0 
	    * @param @param data
	    * @param @return
	    * @return Object  返回类型 
	    * @throws
	    */
	   public Object selectByCarDeptId(Object data){
		     HeadObject head = new HeadObject();
	    	 List<Car> carList = null;
	         try{
				 carList = carMapper.selectByCarDeptId((Integer)data);
	             head.setRetCode(ErrorCode.SUCCESS);
	         }catch(Exception e){    
	        	 e.printStackTrace();
	             head.setRetCode(ErrorCode.FAILURE);
	         }       
	         return new ResultObject(head, carList);
	   }
	   
	   /**
	    * @Title:  selectByBrandId  
	    * @Description:  TODO(根据品牌选择车型)  
	    * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	    * @date 2015-6-18 下午4:10:54  
	    * @version 1.0.0 
	    * @param @param data
	    * @param @return
	    * @return Object  返回类型 
	    * @throws
	    */
	   public Object selectByBrandId(Object data){
		     HeadObject head = new HeadObject();
	    	 List<Car> carList = null;
	         try{
				 carList = carMapper.selectByBrandId((Integer)data);
	             head.setRetCode(ErrorCode.SUCCESS);
	         }catch(Exception e){    
	        	 e.printStackTrace();
	             head.setRetCode(ErrorCode.FAILURE);
	         }       
	         return new ResultObject(head, carList);
	   }
	   
	   /**
	 * @description <b>年款条件查询车型,过滤掉没有商品的车型</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015年7月30日
	 * @param data
	 * @return
	 * Object
	*/
	public Object selectCarByYearHasGoods(Object data){
	     HeadObject head = new HeadObject();
    	 List<Car> carList = null;
         try{
			 carList = carMapper.selectCarByYearHasGoods((Map<String, Object>)data);
             head.setRetCode(ErrorCode.SUCCESS);
         }catch(Exception e){    
        	 e.printStackTrace();
             head.setRetCode(ErrorCode.FAILURE);
         }       
         return new ResultObject(head, carList);
   }
	 
}
