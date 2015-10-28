package com.cnit.yoyo.rmi.car.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.car.CarBrandGradeMapper;
import com.cnit.yoyo.dao.car.CarDeptMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.car.CarBrandGrade;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;
/**
 * 汽车车系
* @author ssd
* @date 2015-4-8 下午3:54:04
 */
@Service("carBrandGradeService")
public class CarBrandGradeService {
    @Autowired 
    private CarBrandGradeMapper  carBrandGradeMapper;
    @Autowired 
    private CarDeptMapper  carDeptMapper;
    
  
    
  /**
   * 
  *
  * @Description: 获取汽车级别信息
  * @param @param data
  * @param @return    设定文件 
  * @author ssd
  * @date 2015-4-8 下午4:23:46 
  * @return Object    返回类型 
  * @throws
   */
    public Object findList(Object data){
   	 HeadObject head = new HeadObject();
   	 ResultPage<CarBrandGrade> page = null;
        try{
       	 JSONObject content = JSONObject.fromObject(data);
       	 PageHelper.startPage(content.getInt("pageIndex"), content.getInt("pageSize"));
       	 page = new ResultPage<CarBrandGrade>(carBrandGradeMapper.findList());
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
    	 List<CarBrandGrade> list = null;
         try{
             list =  carBrandGradeMapper.findList();
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
	* @Description: 保存汽车级别
	* @param @param data
	* @param @return    设定文件 
	* @author ssd
	* @date 2015-4-8 下午4:28:24 
	* @return Object    返回类型 
	* @throws
	 */
	   public Object insertCarBrandGrade(Object data){
	    	 HeadObject head = new HeadObject();
	         try{
	      	   JSONObject content = JSONObject.fromObject(data);
	      	 CarBrandGrade carBrandGrade = (CarBrandGrade)JSONObject.toBean(content, CarBrandGrade.class);
	      	
	      	int count=carBrandGradeMapper.findByName(carBrandGrade);
			if (count == 0) {
				carBrandGradeMapper.insertSelective(carBrandGrade);
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
	    * 
	   *
	   * @Description:删除汽车级别信息 
	   * @param @param data
	   * @param @return    设定文件 
	   * @author ssd
	   * @date 2015-4-8 下午4:48:43 
	   * @return Object    返回类型 
	   * @throws
	    */
	   public Object deleteCarBrandGrade(Object data){
		        HeadObject head = new HeadObject();
		        try{
		      	  Integer[]  id =  (Integer[]) data;
		      	  // 先判断是否存在下级车系，有，则不能删除，要先删除下级数据
		      	  if(null != id && id.length > 0) {
		      		List<Integer> gradeIds = carDeptMapper.findByGradeId(id);
		      		Map<String, Object> params = new HashMap<String, Object>();
		        	params.put("status", 1);
		      		if(null != gradeIds && gradeIds.size() > 0) {
		      			List<Integer> ids = new ArrayList<Integer>();
						for (int i = 0; i < id.length; i++) {
							if (!gradeIds.contains(id[i])) {
								ids.add(id[i]);
							}
						}
						if (ids.size() > 0) {
				        	params.put("ids", ids);
				        	carBrandGradeMapper.updateStatusByIds(params);
						}
						head.setRetCode(ErrorCode.IS_EXIST_CASCADE);
						gradeIds.clear();
						gradeIds = null;
		      		}else {
			        	params.put("ids", id);
			        	carBrandGradeMapper.updateStatusByIds(params);
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
	   * @Description: 更新汽车级别信息
	   * @param @param data
	   * @param @return    设定文件 
	   * @author ssd
	   * @date 2015-4-8 下午4:55:29 
	   * @return Object    返回类型 
	   * @throws
	    */
	   public Object updateCarBrandGrade(Object data){
		  	 HeadObject head = new HeadObject();
		       try{
		            JSONObject content = JSONObject.fromObject(data);
		            CarBrandGrade carBrandGrade = (CarBrandGrade)JSONObject.toBean(content, CarBrandGrade.class);
		            
		            int count=carBrandGradeMapper.findByName(carBrandGrade);
					if (count == 0) {
						carBrandGradeMapper.updateByPrimaryKeySelective(carBrandGrade);
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
			    
	   
    
}
