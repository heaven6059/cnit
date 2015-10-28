package com.cnit.yoyo.rmi.car.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.autohome.model.AutohomeCarInfoDTO;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.car.CarDeptMapper;
import com.cnit.yoyo.dao.car.CarFactoryMapper;
import com.cnit.yoyo.dao.car.CarMapper;
import com.cnit.yoyo.dao.car.CarYearMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.QueryParamObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.car.AccessoryCatalog;
import com.cnit.yoyo.model.car.CarDept;
import com.cnit.yoyo.model.car.CarFactory;
import com.cnit.yoyo.model.car.dto.CarDeptDTO;
import com.cnit.yoyo.model.car.dto.CarFactoryDTO;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;
/**
 * 汽车车系
* @author ssd
* @date 2015-4-8 下午3:54:04
 */
@Service("carDeptService")
public class CarDeptService {
    @Autowired 
    private CarMapper  carMapper;
    @Autowired 
    private CarYearMapper  carYearMapper;
    @Autowired 
    private CarDeptMapper  carDeptMapper;
    
    /**
     * @Description:查询爬虫关联车系数据
     * @param data
     * @return
     */
  	public Object selectCarDeptSpiderDataList(Object data){
  		HeadObject head = new HeadObject();
  		List<AutohomeCarInfoDTO> list=(List<AutohomeCarInfoDTO>) data;
  		List<CarDept> retlist = null;
  		try{
  			retlist =  carDeptMapper.selectCarDeptSpiderDataList(list);
  			head.setRetCode(ErrorCode.SUCCESS);
  		}catch(Exception e){    
  			e.printStackTrace();
  			head.setRetCode(ErrorCode.FAILURE);
  		}       
  		return new ResultObject(head, retlist);
  	}
  		
  	/**
  	   * @Description:查询爬虫关联车系数据
  	   * @param data
  	   * @return
  	   */
  		public Object selectCarDeptSpiderData(Object data){
  			HeadObject head = new HeadObject();
  			CarDept retObj = null;
  			try{
  				retObj =  carDeptMapper.selectCarDeptSpiderData((AutohomeCarInfoDTO)data);
  				head.setRetCode(ErrorCode.SUCCESS);
  			}catch(Exception e){    
  				e.printStackTrace();
  				head.setRetCode(ErrorCode.FAILURE);
  			}       
  			return new ResultObject(head, retObj);
  		}
  
    
  /**
   * 
  *
  * @Description: 获取车系信息
  * @param @param data
  * @param @return    设定文件 
  * @author ssd
  * @date 2015-4-8 下午4:23:46 
  * @return Object    返回类型 
  * @throws
   */
	public Object findList(Object data){
    	 HeadObject head = new HeadObject();
    	 ResultPage<CarDeptDTO> page = null;
         try{
        	 JSONObject content = JSONObject.fromObject(data);
        	 PageHelper.startPage(content.getInt("pageIndex"), content.getInt("pageSize"));
        	 CarDeptDTO dto = (CarDeptDTO)JSONObject.toBean(content.getJSONObject("carDept"), CarDeptDTO.class);
        	 page = new ResultPage<CarDeptDTO>(carDeptMapper.findList(dto.getCarDeptName(),dto.getKeyword(),dto.getFactoryid(),dto.getGradeId(),QueryParamObject.getOrderByCause(content)));
        	 
        	 
     		/*PageHelper.startPage(QueryParamObject.getPage(content), QueryParamObject.getRows(content));
     		page = new ResultPage(carDeptMapper.selectByQueryParams(
     				QueryParamObject.getMqls(content), QueryParamObject.getOrderByCause(content)));*/
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
    	 List<CarDept> list = null;
    	 JSONObject content = JSONObject.fromObject(data);
    	 CarDept dept = new CarDept();
    	 dept.setFactoryid(content.getInt("factoryId"));
    	 if(StringUtils.isNotBlank(content.getString("gradeId"))){
    	     dept.setGradeId(content.getInt("gradeId"));
    	 }
         try{
             list =  carDeptMapper.findSelect(dept);
             head.setRetCode(ErrorCode.SUCCESS);
         }catch(Exception e){    
        	 e.printStackTrace();
             head.setRetCode(ErrorCode.FAILURE);
         }       
         return new ResultObject(head, list);
    }
    
	
  	/**
 	  * @description <b>通过厂商查找车系</b>
 	  * @author 王鹏
 	  * @version 1.0.0
 	  * @data 2015-5-11
 	  * @param @param factoryId
 	  * @param @return
 	  * @return List<CarDept>
 	*/
	public Object findCarDeptByFactory(Object data){
   	 HeadObject head = new HeadObject();
   	 List<CarDept> list = null;
        try{
            list =  carDeptMapper.selectCarDeptByFactory((Integer)data);
            head.setRetCode(ErrorCode.SUCCESS);
        }catch(Exception e){    
       	 e.printStackTrace();
            head.setRetCode(ErrorCode.FAILURE);
        }       
        return new ResultObject(head, list);
   }
	
	
	/**
	 * 
	*
	* @Description: 保存车系信息 
	* @param @param data
	* @param @return    设定文件 
	* @author ssd
	* @date 2015-4-8 下午4:28:24 
	* @return Object    返回类型 
	* @throws
	 */
	public Object insertCarDept(Object data) {
		HeadObject head = new HeadObject();
		try {
			JSONObject content = JSONObject.fromObject(data);
			CarDept carDept = (CarDept) JSONObject.toBean(content,
					CarDept.class);

			int count = carDeptMapper.findByName(carDept);
			if (count == 0) {
				carDeptMapper.insertSelective(carDept);
				head.setRetCode(ErrorCode.SUCCESS);
			} else {
				head.setRetCode(ErrorCode.IS_EXIST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head);
	}
	   
	   /**
	    * 
	   *
	   * @Description:删除车系信息 
	   * @param @param data
	   * @param @return    设定文件 
	   * @author ssd
	   * @date 2015-4-8 下午4:48:43 
	   * @return Object    返回类型 
	   * @throws
	    */
	   public Object deleteCarDept(Object data){
		        HeadObject head = new HeadObject();
		        try{
		      	  Integer[]  id =  (Integer[]) data;
		      	  // 先判断是否存在下级车系，有，则不能删除，要先删除下级数据
		      	  if(null != id && id.length > 0) {
		      		List<Integer> carDeptIds = carMapper.findbyDeptId(id);
		      		List<Integer> yearDeptIds = carYearMapper.findbyDeptId(id);
		      		Map<String, Object> params = new HashMap<String, Object>();
		        	params.put("status", 1);
		      		if((null != carDeptIds && carDeptIds.size() > 0) || 
		      				(null != yearDeptIds && yearDeptIds.size() > 0)) {
		      			List<Integer> ids = new ArrayList<Integer>();
		      			if(null != carDeptIds && carDeptIds.size() > 0) {
		      				for (int i = 0; i < id.length; i++) {
								if (!carDeptIds.contains(id[i])) {
									ids.add(id[i]);
								}
							}
		      			} 
		      			
		      			if(null != yearDeptIds && yearDeptIds.size() > 0) {
		      				if (ids.size() > 0) {
		      					for (int i = 0; i < id.length; i++) {
									if (!yearDeptIds.contains(ids.get(i))) {
										ids.add(id[i]);
									}else {
										ids.remove(ids.get(i));
									}
								}
		      				}else {
		      					for (int i = 0; i < id.length; i++) {
									if (!yearDeptIds.contains(id[i])) {
										ids.add(id[i]);
									}
								}
		      				}
		      				
		      			}
		      			
						
						if (ids.size() > 0) {
				        	params.put("ids", ids);
				        	carDeptMapper.updateStatusByIds(params);
						}
						head.setRetCode(ErrorCode.IS_EXIST_CASCADE);
						carDeptIds.clear();
						carDeptIds = null;
						yearDeptIds.clear();
						yearDeptIds = null;
		      		}else {
			        	params.put("ids", id);
			        	carDeptMapper.updateStatusByIds(params);
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
	   *
	   * @Description: 更新车系信息
	   * @param @param data
	   * @param @return    设定文件 
	   * @author ssd
	   * @date 2015-4-8 下午4:55:29 
	   * @return Object    返回类型 
	   * @throws
	    */
	   public Object updateCarDept(Object data){
		  	 HeadObject head = new HeadObject();
		       try{
		            JSONObject content = JSONObject.fromObject(data);
		            CarDept carDept = (CarDept)JSONObject.toBean(content, CarDept.class);
		            
		            int count = carDeptMapper.findByName(carDept);
					if (count == 0) {
						carDeptMapper.updateByPrimaryKeySelective(carDept);
						head.setRetCode(ErrorCode.SUCCESS);
					} else {
						head.setRetCode(ErrorCode.IS_EXIST);
					}
		       }catch(Exception e){    
		    	   e.printStackTrace();
		           head.setRetCode(ErrorCode.FAILURE);
		       }       
		       return new ResultObject(head);
	   }
			    
	   /**
	    * @Title:  findAllCarDept  
	    * @Description:  TODO(查询所有车系)  
	    * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	    * @date 2015-5-12 上午9:31:15  
	    * @version 1.0.0 
	    * @param @param data
	    * @param @return
	    * @return Object  返回类型 
	    * @throws
	    */
	   public Object findAllCarDept(Object data){
	    	 HeadObject head = new HeadObject();
	    	 List<CarDept> carDeptList = null;
	         try{
	        	 carDeptList = carDeptMapper.selectAllCarDept();
	             head.setRetCode(ErrorCode.SUCCESS);
	         }catch(Exception e){    
	        	 e.printStackTrace();
	             head.setRetCode(ErrorCode.FAILURE);
	         }       
	         return new ResultObject(head, carDeptList);
	    }   
	   
	   /**
	    * @Title:  findCarDeptByBrandId  
	    * @Description:  TODO(根据品牌id查询所属的车系列表)  
	    * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	    * @date 2015-5-12 下午1:24:22  
	    * @version 1.0.0 
	    * @param @param data
	    * @param @return
	    * @return Object  返回类型 
	    * @throws
	    */
	   public Object findCarDeptByBrandId(Object data){
	    	 HeadObject head = new HeadObject();
	    	 List<CarDept> carDeptList = null;
	         try{
	        	 carDeptList = carDeptMapper.selectByBrandId((Integer)data);
	             head.setRetCode(ErrorCode.SUCCESS);
	         }catch(Exception e){    
	        	 e.printStackTrace();
	             head.setRetCode(ErrorCode.FAILURE);
	         }       
	         return new ResultObject(head, carDeptList);
	    } 
	   
	   /**
	    * @Title:  findCarDeptByCarId  
	    * @Description:  TODO(根据车型查询车系)  
	    * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	    * @date 2015-6-18 下午4:16:38  
	    * @version 1.0.0 
	    * @param @param data
	    * @param @return
	    * @return Object  返回类型 
	    * @throws
	    */
	   public Object findCarDeptByCarId(Object data){
	    	 HeadObject head = new HeadObject();
	    	 List<CarDept> carDeptList = null;
	         try{
	        	 carDeptList = carDeptMapper.selectByCarId((Integer)data);
	             head.setRetCode(ErrorCode.SUCCESS);
	         }catch(Exception e){    
	        	 e.printStackTrace();
	             head.setRetCode(ErrorCode.FAILURE);
	         }       
	         return new ResultObject(head, carDeptList);
	    }
	   
	   
    
}
