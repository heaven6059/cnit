package com.cnit.yoyo.rmi.good.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.directory.DirContext;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.goods.ConsultReplaceMapper;
import com.cnit.yoyo.dao.goods.DriveOrConsultMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.goods.ConsultReplace;
import com.cnit.yoyo.model.goods.DriveOrConsult;
import com.cnit.yoyo.model.goods.DriveOrConsultExample;
import com.cnit.yoyo.model.goods.dto.DriveOrConsultDTO;
import com.cnit.yoyo.model.other.drive.ReservationDrive;
import com.cnit.yoyo.model.other.drive.dto.ReservationDriveQryDTO;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

/**
 * @ClassName: DriveOrConsultService  
 * @Description: TODO(询问最低价或预约试驾)  
 * @detail <详细说明>
 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
 * @date 2015-5-11 下午6:39:11  
 * @version 1.0.0
 */
/**  
* @Title: DriveOrConsultService.java
* @Package com.cnit.yoyo.rmi.good.service
* @Description: TODO(用一句话描述该文件做什么)
* @Author 王鹏
* @date 2015-5-27 下午8:16:13
* @version V1.0  
*/ 
@Service("driveOrConsultService")
public class DriveOrConsultService {

	public static final Logger log = LoggerFactory.getLogger(DriveOrConsultService.class);

    @Autowired
    private DriveOrConsultMapper driveOrConsultMapper;
    
    @Autowired
    private ConsultReplaceMapper consultReplaceMapper;
    
    /**
     * @Title:  saveDriveOrConsult  
     * @Description:  TODO(保存对象)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-11 下午6:41:58  
     * @version 1.0.0 
     * @param @param data
     * @param @return
     * @return Object  返回类型 
     * @throws
     */
    public Object saveDriveOrConsult(Object data) 
	{
    	HeadObject headObject = null;
		log.info("start[driveOrConsultService.saveDriveOrConsult]");
		JSONObject jsonObject = JSONObject.fromObject(data);
		DriveOrConsult  driveOrConsult = (DriveOrConsult) JSONObject.toBean(JSONObject.fromObject(jsonObject.get("driveOrConsult")),DriveOrConsult.class);
		ConsultReplace consultReplace = (ConsultReplace) JSONObject.toBean(JSONObject.fromObject(jsonObject.get("consultReplace")),ConsultReplace.class);
		int result = driveOrConsultMapper.insert(driveOrConsult);
		if(result==1){
			if(driveOrConsult.getIsReplace()==1&&consultReplace!=null){
				List<DriveOrConsult> list = driveOrConsultMapper.selectByParam(driveOrConsult.getCarId(), driveOrConsult.getCityId(), 
						driveOrConsult.getPhone(),driveOrConsult.getType(), driveOrConsult.getIsReplace(), driveOrConsult.getState());
				if(list!=null&&list.size()>=1){
					consultReplace.setConsultId(list.get(0).getId());
					consultReplaceMapper.insert(consultReplace);
				}
			}
			headObject = new HeadObject(ErrorCode.SUCCESS);
		}else{
			headObject = new HeadObject(ErrorCode.FAILURE);
		}
		log.info("end[driveOrConsultService.saveDriveOrConsult]");
		return headObject;
	}
    
    /**
     * @Title:  selectByExample  
     * @Description:  TODO(根据给定条件查询对象)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-11 下午7:01:19  
     * @version 1.0.0 
     * @param @param data
     * @param @return
     * @return Object  返回类型 
     * @throws
     */
    public Object selectByExample(Object data) 
	{
		log.info("start[driveOrConsultService.selectByExample]");
		DriveOrConsult driveOrConsult = (DriveOrConsult)data;
		List<DriveOrConsult> list = null;
		if(driveOrConsult!=null){
			list = driveOrConsultMapper.selectByParam(driveOrConsult.getCarId(), driveOrConsult.getCityId(), 
					driveOrConsult.getPhone(),driveOrConsult.getType(), driveOrConsult.getIsReplace(), driveOrConsult.getState());
		}
		log.info("end[driveOrConsultService.selectByExample]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS),list);
	}
    
    /**
     * @Title:  selectApplyList  
     * @Description:  TODO(分页查询申请列表)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-12 下午4:59:00  
     * @version 1.0.0 
     * @param @param data
     * @param @return
     * @return Object  返回类型 
     * @throws
     */
    public Object selectApplyList(Object data){
    	log.info("start[driveOrConsultService.selectApplyList]");
    	JSONObject jsonObject = JSONObject.fromObject(data);
    	Integer pageIndex = jsonObject.getInt("pageIndex");
		Integer pageSize = jsonObject.getInt("pageSize");
		Object typeObject = jsonObject.get("type");
		String type = null;
		if(typeObject!=null){
			type = typeObject.toString();
		}
		PageHelper.startPage(pageIndex, pageSize);
//		ResultPage<DriveOrConsultDTO> page = new ResultPage(driveOrConsultMapper.selectAll());
		if (!"1".equals(type) && !"2".equals(type)) {
			type = null;
		}
		ResultPage<DriveOrConsultDTO> page = new ResultPage(driveOrConsultMapper.selectAllByType(type));
		
		if(page!=null&&page.getRows()!=null&&page.getRows().size()>=1){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(int i=0;i<page.getRows().size();i++){
				if(page.getRows().get(i).getAddTime()!=null&&page.getRows().get(i).getAddTime()!=0){
					page.getRows().get(i).setAddDate(format.format(((long)page.getRows().get(i).getAddTime())*1000));
				}
				if(page.getRows().get(i).getType().equals("1")){
//					if(page.getRows().get(i).getIsReplace()==1){
//						page.getRows().get(i).setApplyType("置换");
//					}else if(page.getRows().get(i).getIsReplace()==0){
//						page.getRows().get(i).setApplyType("询问最低价");
//					}
					page.getRows().get(i).setApplyType("询问最低价");
				}else if(page.getRows().get(i).getType().equals("2")){
					page.getRows().get(i).setApplyType("预约试驾");
				}
			}
		}
		log.info("end[driveOrConsultService.selectApplyList]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONObject.fromObject(page));
    }
    
    /**
      * @description <b>查询试驾信息</b>
      * @author 王鹏
      * @version 1.0.0
      * @data 2015-5-27
      * @param @return
      * @return Object
    */
    public Object selectReservationDrive(Object data){
    	ReservationDriveQryDTO qryDTO=(ReservationDriveQryDTO)data;
    	PageHelper.startPage(qryDTO.getPage(), qryDTO.getRows());
    	ResultPage<ReservationDrive> resutlPage=null;
    	HeadObject headObject=new HeadObject();
    	try{
    		resutlPage=new ResultPage<ReservationDrive>(this.driveOrConsultMapper.selectReservationDrive(qryDTO));
    		headObject.setRetCode(ErrorCode.SUCCESS);
    	}catch (Exception e) {
    		headObject.setRetCode(ErrorCode.FAILURE);
    		e.printStackTrace();
		}
    	return new ResultObject(headObject, JSON.toJSON(resutlPage));
    }
    
    /**
     * @description <b>查询试驾信息</b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015-5-27
     * @param @return
     * @return Object
   */
   public Object selectShopReservationDrive(Object data){
   	ReservationDriveQryDTO qryDTO=(ReservationDriveQryDTO)data;
   	PageHelper.startPage(qryDTO.getPage(), qryDTO.getRows());
   	ResultPage<ReservationDrive> resutlPage=null;
   	HeadObject headObject=new HeadObject();
   	try{
   		resutlPage=new ResultPage<ReservationDrive>(this.driveOrConsultMapper.selectShopReservationDrive(qryDTO));
   		headObject.setRetCode(ErrorCode.SUCCESS);
   	}catch (Exception e) {
   		headObject.setRetCode(ErrorCode.FAILURE);
   		e.printStackTrace();
		}
   	return new ResultObject(headObject, JSON.toJSON(resutlPage));
   }
 
    
    /**
     * @description <b>查询试驾信息</b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015-5-27
     * @param @return
     * @return Object
   */
   public Object selectLowPriceConsult(Object data){
   	ReservationDriveQryDTO qryDTO=(ReservationDriveQryDTO)data;
   	PageHelper.startPage(qryDTO.getPage(), qryDTO.getRows());
   	ResultPage<ReservationDrive> resutlPage=null;
   	HeadObject headObject=new HeadObject();
   	try{
   		resutlPage=new ResultPage<ReservationDrive>(this.driveOrConsultMapper.selectLowPriceConsult(qryDTO));
   		headObject.setRetCode(ErrorCode.SUCCESS);
   	}catch (Exception e) {
   		headObject.setRetCode(ErrorCode.FAILURE);
   		e.printStackTrace();
		}
   	return new ResultObject(headObject, JSON.toJSON(resutlPage));
   }
   
   /**
    * @description <b>卖家中心询问最低价数据查询</b>
    * @author 王鹏
    * @version 1.0.0
    * @data 2015-5-27
    * @param @return
    * @return Object
  */
  public Object selectShopLowPriceConsult(Object data){
  	ReservationDriveQryDTO qryDTO=(ReservationDriveQryDTO)data;
  	PageHelper.startPage(qryDTO.getPage(), qryDTO.getRows());
  	ResultPage<ReservationDrive> resutlPage=null;
  	HeadObject headObject=new HeadObject();
  	try{
  		resutlPage=new ResultPage<ReservationDrive>(this.driveOrConsultMapper.selectShopLowPriceConsult(qryDTO));
  		headObject.setRetCode(ErrorCode.SUCCESS);
  	}catch (Exception e) {
  		headObject.setRetCode(ErrorCode.FAILURE);
  		e.printStackTrace();
		}
  	return new ResultObject(headObject, JSON.toJSON(resutlPage));
  }
   
   
   /**
  * @description <b>删除预约试驾或询问最低价</b>
  * @author 王鹏
  * @version 1.0.0
  * @data 2015-5-27
  * @param @param data
  * @param @return
  * @return Object
  */
   public Object deleteDriveOrConsult(Object data){
	   log.info("satart[driveOrConsultService.deleteDriveOrConsult]");
	   HeadObject headObject=new HeadObject();
	   try{
		   this.driveOrConsultMapper.deleteDriveOrConsult((Integer[]) data);
		   headObject.setRetCode(ErrorCode.SUCCESS);
	   }catch (Exception e) {
		   headObject.setRetCode(ErrorCode.FAILURE);
		   e.printStackTrace();
	   }
	   log.info("end[driveOrConsultService.deleteDriveOrConsult]");
	   return headObject;
   }
}
