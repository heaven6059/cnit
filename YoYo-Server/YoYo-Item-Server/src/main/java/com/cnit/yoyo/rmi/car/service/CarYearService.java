package com.cnit.yoyo.rmi.car.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.car.CarDeptMapper;
import com.cnit.yoyo.dao.car.CarFactoryMapper;
import com.cnit.yoyo.dao.car.CarMapper;
import com.cnit.yoyo.dao.car.CarYearMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.QueryParamObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.car.CarDept;
import com.cnit.yoyo.model.car.CarFactory;
import com.cnit.yoyo.model.car.CarYear;
import com.cnit.yoyo.model.car.dto.CarDeptDTO;
import com.cnit.yoyo.model.car.dto.CarYearDTO;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;
/**
 * 汽车年款
* @author ssd
* @date 2015-4-8 下午3:54:04
 */
@Service("carYearService")
public class CarYearService {
    @Autowired 
    private CarMapper  carMapper;
    @Autowired 
    private CarYearMapper  carYearMapper;
    @Autowired 
    private CarDeptMapper  carDeptMapper;
    
  
    
    /**
	   * 
	  *
	  * @Description: 获取年款信息
	  * @param @param data
	  * @param @return    设定文件 
	  * @author ssd
	  * @date 2015-4-8 下午4:23:46 
	  * @return Object    返回类型 
	  * @throws
	   */
		public Object findList(Object data){
	    	 HeadObject head = new HeadObject();
	    	 ResultPage<CarYearDTO> page = null;
	         try{
	        	 JSONObject content = JSONObject.fromObject(data);
	        	 PageHelper.startPage(content.getInt("pageIndex"), content.getInt("pageSize"));
	        	 CarYearDTO dto = (CarYearDTO)JSONObject.toBean(content.getJSONObject("carYearDTO"), CarYearDTO.class);
	        	 page = new ResultPage<CarYearDTO>(carYearMapper.findList(dto.getCarDeptId(),dto.getCarYearValue(),QueryParamObject.getOrderByCause(content)));
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
	    	 List<CarYear> list = null;
	         try{
	             list =  carYearMapper.findSelect((Integer)data);
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
		* @Description: 保存年款信息 
		* @param @param data
		* @param @return    设定文件 
		* @author ssd
		* @date 2015-4-8 下午4:28:24 
		* @return Object    返回类型 
		* @throws
		 */
		public Object insertCarYear(Object data) {
			HeadObject head = new HeadObject();
			try {
				JSONObject content = JSONObject.fromObject(data);
				CarYear carYear = (CarYear) JSONObject.toBean(content,
						CarYear.class);
				String carYearValue = carYear.getCarYearValue();
				int deptId = carYear.getCarDeptId();
				String[] years = carYearValue.split(",");
				int num = 0;
				int errorNum = 0;
				if(null != years && years.length > 0) {
					for(String year : years) {
						CarYear car = new CarYear();
						car.setCarDeptId(deptId);
						car.setCarYearValue(year);
						num  = carYearMapper.findbyYear(car);
						if(num == 0) {
							carYearMapper.insertSelective(car);
						}else {
							errorNum++;
						}
						
					}
				}
				
					
					if(errorNum > 0) {
						head.setRetCode(ErrorCode.IS_EXIST);
					}else {
						head.setRetCode(ErrorCode.SUCCESS);
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
	   * @Description:删除年款信息 
	   * @param @param data
	   * @param @return    设定文件 
	   * @author ssd
	   * @date 2015-4-8 下午4:48:43 
	   * @return Object    返回类型 
	   * @throws
	    */
	   public Object deleteCarYear(Object data){
		   	 HeadObject head = new HeadObject();
		        try{
		      	  Integer[]  id =  (Integer[]) data;
		      	  // 先判断是否存在下级车系，有，则不能删除，要先删除下级数据
		      	  if(null != id && id.length > 0) {
		      		List<Integer> yearIds = carMapper.findbyYearId(id);
		      		Map<String, Object> params = new HashMap<String, Object>();
		        	params.put("status", 1);
		      		if(null != yearIds && yearIds.size() > 0) {
		      			List<Integer> ids = new ArrayList<Integer>();
						for (int i = 0; i < id.length; i++) {
							if (!yearIds.contains(id[i])) {
								ids.add(id[i]);
							}
						}
						if (ids.size() > 0) {
				        	params.put("ids", ids);
							carYearMapper.updateStatusByIds(params);
						}
						head.setRetCode(ErrorCode.IS_EXIST_CASCADE);
						yearIds.clear();
						yearIds = null;
		      		}else {
			        	params.put("ids", id);
						carYearMapper.updateStatusByIds(params);
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
	   * @Description: 更新年款信息
	   * @param @param data
	   * @param @return    设定文件 
	   * @author ssd
	   * @date 2015-4-8 下午4:55:29 
	   * @return Object    返回类型 
	   * @throws
	    */
	   public Object updateCarYear(Object data){
		  	 HeadObject head = new HeadObject();
		       try{
		            JSONObject content = JSONObject.fromObject(data);
		            CarYear carYear = (CarYear)JSONObject.toBean(content, CarYear.class);
		            int num  = carYearMapper.findbyYear(carYear);
		            if(num == 0){
		            	carYearMapper.updateByPrimaryKeySelective(carYear);
						head.setRetCode(ErrorCode.SUCCESS);
		            }else {
		            	head.setRetCode(ErrorCode.IS_EXIST);
		            }
		            
		       }catch(Exception e){    
		    	   e.printStackTrace();
		           head.setRetCode(ErrorCode.FAILURE);
		       }       
		       return new ResultObject(head);
	   }
			    
	   
		/**
		 * @description <b>车系条件查询年款，并且过滤掉没有商品的年款</b>
		 * @author 王鹏
		 * @version 1.0.0
		 * @data 2015年7月30日
		 * @param data
		 * @return
		 * Object
		*/
		public Object selectCarYearByCarDeptHasGoods(Object data){
	    	 HeadObject head = new HeadObject();
	    	 List<CarYear> list = null;
	         try{
	             list =  carYearMapper.selectCarYearByCarDeptHasGoods((Integer)data);
	             head.setRetCode(ErrorCode.SUCCESS);
	         }catch(Exception e){    
	        	 e.printStackTrace();
	             head.setRetCode(ErrorCode.FAILURE);
	         } 
	         return new ResultObject(head, list);
	    }
}
