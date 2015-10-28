package com.cnit.yoyo.rmi.oam.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.car.BrandAutohomeMapper;
import com.cnit.yoyo.dao.car.CarAutohomeMapper;
import com.cnit.yoyo.dao.car.CarConfigMapper;
import com.cnit.yoyo.dao.car.CarDataBoolMapper;
import com.cnit.yoyo.dao.car.CarDataDecimalMapper;
import com.cnit.yoyo.dao.car.CarDataIntMapper;
import com.cnit.yoyo.dao.car.CarDataMapper;
import com.cnit.yoyo.dao.car.CarDataStringMapper;
import com.cnit.yoyo.dao.car.CarDeptAutohomeMapper;
import com.cnit.yoyo.dao.car.CarDeptMapper;
import com.cnit.yoyo.dao.car.CarFactoryAutohomeMapper;
import com.cnit.yoyo.dao.car.CarFactoryMapper;
import com.cnit.yoyo.dao.car.CarMapper;
import com.cnit.yoyo.dao.car.CarSpiderAuditLogMapper;
import com.cnit.yoyo.dao.car.CarSpiderCompareMapper;
import com.cnit.yoyo.dao.car.CarYearMapper;
import com.cnit.yoyo.dao.goods.BrandMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.QueryParamObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.car.BrandAutohome;
import com.cnit.yoyo.model.car.Car;
import com.cnit.yoyo.model.car.CarAutohome;
import com.cnit.yoyo.model.car.CarConfigWithBLOBs;
import com.cnit.yoyo.model.car.CarData;
import com.cnit.yoyo.model.car.CarDataBool;
import com.cnit.yoyo.model.car.CarDataDecimal;
import com.cnit.yoyo.model.car.CarDataInt;
import com.cnit.yoyo.model.car.CarDataString;
import com.cnit.yoyo.model.car.CarDept;
import com.cnit.yoyo.model.car.CarDeptAutohome;
import com.cnit.yoyo.model.car.CarFactory;
import com.cnit.yoyo.model.car.CarFactoryAutohome;
import com.cnit.yoyo.model.car.CarSpiderAuditLogWithBLOBs;
import com.cnit.yoyo.model.car.CarSpiderCompare;
import com.cnit.yoyo.model.car.CarYear;
import com.cnit.yoyo.model.car.dto.SpiderConfigDataDTO;
import com.cnit.yoyo.model.car.dto.SpiderDataDTO;
import com.cnit.yoyo.model.goods.Brand;
import com.cnit.yoyo.util.StringUtil;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;
/**
 * 汽车年款
* @author ssd
* @date 2015-4-8 下午3:54:04
 */
@Service("oamGoodsService")
public class OamGoodsService {
    @Autowired 
    private CarSpiderCompareMapper  carSpiderCompareMapper;
    @Autowired 
    private CarSpiderAuditLogMapper  carSpiderAuditLogMapper;
    
    @Autowired 
    private BrandMapper  brandMapper;
    @Autowired 
    private CarFactoryMapper  carFactoryMapper;
    @Autowired 
    private CarDeptMapper  carDeptMapper;
    @Autowired 
    private CarMapper  carMapper;
    @Autowired 
    private CarYearMapper  carYearMapper;
    @Autowired 
    private BrandAutohomeMapper  brandAutohomeMapper;
    @Autowired 
    private CarAutohomeMapper  carAutohomeMapper;
    @Autowired 
    private CarDeptAutohomeMapper  carDeptAutohomeMapper;
    @Autowired 
    private CarFactoryAutohomeMapper  carFactoryAutohomeMapper;
    
    @Autowired 
    private CarDataMapper  carDataMapper;
    @Autowired 
    private CarDataIntMapper  carDataIntMapper;
    @Autowired 
    private CarDataDecimalMapper  carDataDecimalMapper;
    @Autowired 
    private CarDataBoolMapper  carDataBoolMapper;
    @Autowired 
    private CarDataStringMapper  carDataStringMapper;
    @Autowired 
    private CarConfigMapper  carConfigMapper;
    
    
  
    
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
	    	 ResultPage<CarSpiderCompare> page = null;
	         try{
	        	 JSONObject content = JSONObject.fromObject(data);
	        	 PageHelper.startPage(content.getInt("pageIndex"), content.getInt("pageSize"));
	        	 CarSpiderCompare dto = (CarSpiderCompare)JSONObject.toBean(content.getJSONObject("carSpiderCompare"), CarSpiderCompare.class);
	        	 page = new ResultPage<CarSpiderCompare>(carSpiderCompareMapper.findList(dto.getSpiderValId(),dto.getCompareType(),QueryParamObject.getOrderByCause(content)));
	        	 List<CarSpiderCompare> list = page.getRows();
	        	 Map<String, Class> classMap = new HashMap<String, Class>();
	        	 classMap.put("detailConfig", SpiderConfigDataDTO.class);
	        	 for(int i=0;i<list.size();i++) {
	        		 CarSpiderCompare csc = list.get(i);
	        		 String beforeValue = csc.getBeforeTips();
	        		 String afterValue = csc.getAfterTips();
	        		 if(!StringUtil.isEmpty(beforeValue)) {
	        			 JSONObject beforeJson = JSONObject.fromObject(beforeValue);
	        			 SpiderDataDTO spiderDto = (SpiderDataDTO)JSONObject.toBean(beforeJson, SpiderDataDTO.class,classMap);
		        		 StringBuffer beforesb = new StringBuffer();
		        		 if(!StringUtil.isEmpty(spiderDto.getName())){
		        			 beforesb.append("<名称>：");
		        			 beforesb.append(spiderDto.getName());
		        			 beforesb.append("、");
		        		 }
		        		 if(!StringUtil.isEmpty(spiderDto.getImgPath())){
		        			 beforesb.append("logo：");
		        			 beforesb.append(spiderDto.getImgPath());
		        			 beforesb.append("、");
		        		 }
		        		 if(!StringUtil.isEmpty(spiderDto.getUrl())){
		        			 beforesb.append("<网址>：");
		        			 beforesb.append(spiderDto.getUrl());
		        			 beforesb.append("、");
		        		 }
		        		 if(null != spiderDto.getDetailConfig()) {
		        			 List<SpiderConfigDataDTO> configList = spiderDto.getDetailConfig();
		        			 for(int j=0;j<configList.size();j++) {
		        				 SpiderConfigDataDTO configDto = configList.get(j);
		        				 beforesb.append("<数据项名称>：");
			        			 beforesb.append(configDto.getDisplayName());
			        			 beforesb.append("、");
			        			 beforesb.append("<数据项值>：");
			        			 beforesb.append(configDto.getConfigValue());
			        			 beforesb.append("、");
		        			 }
		        		 }
		        		 
		        		 csc.setBeforeTips(beforesb.toString());
	        		 }else {
	        			 csc.setBeforeTips(""); 
	        		 }
	        		 
	        		 if(!StringUtil.isEmpty(afterValue)) {
	        			 JSONObject afterJson = JSONObject.fromObject(afterValue);
	        			 SpiderDataDTO spiderDto = (SpiderDataDTO)JSONObject.toBean(afterJson, SpiderDataDTO.class,classMap);
		        		 StringBuffer aftersb = new StringBuffer();
		        		 if(!StringUtil.isEmpty(spiderDto.getName())){
		        			 aftersb.append("<名称>：");
		        			 aftersb.append(spiderDto.getName());
		        			 aftersb.append("、");
		        		 }
		        		 if(!StringUtil.isEmpty(spiderDto.getImgPath())){
		        			 aftersb.append("logo：");
		        			 aftersb.append(spiderDto.getImgPath());
		        			 aftersb.append("、");
		        		 }
		        		 if(!StringUtil.isEmpty(spiderDto.getUrl())){
		        			 aftersb.append("<网址>：");
		        			 aftersb.append(spiderDto.getUrl());
		        			 aftersb.append("、");
		        		 }
		        		 if(null != spiderDto.getDetailConfig()) {
		        			 List<SpiderConfigDataDTO> configList = spiderDto.getDetailConfig();
		        			 for(int j=0;j<configList.size();j++) {
		        				 SpiderConfigDataDTO configDto = configList.get(j);
		        				 aftersb.append("<数据项名称>：");
		        				 aftersb.append(configDto.getDisplayName());
		        				 aftersb.append("、");
		        				 aftersb.append("<数据项值>：");
		        				 aftersb.append(configDto.getConfigValue());
		        				 aftersb.append("、");
		        			 }
		        		 }
		        		 csc.setAfterTips(aftersb.toString());
	        		 }else {
	        			 csc.setAfterTips("");
	        		 }
	        		 
	        	 }
	        	 page.setRows(list);
	             head.setRetCode(ErrorCode.SUCCESS);
	         }catch(Exception e){    
	        	 e.printStackTrace();
	             head.setRetCode(ErrorCode.FAILURE);
	         }      
	         JsonConfig jsonConfig = new JsonConfig();  
	         jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
	         JSONObject json = JSONObject.fromObject(page,jsonConfig);
	         return new ResultObject(head, json);
	    }	
	
		/**
	     * 查询汽车厂商的下拉框数据
	     * @param @return    设定文件 
	     * @author ssd
	     */
		public Object batchCheck(Object data){
	    	 HeadObject head = new HeadObject();
	    	 JSONObject content = JSONObject.fromObject(data);
	    	 JSONArray goodsRows = content.getJSONArray("rows");
	    	 int status = content.optInt("status");
	    	 String cause = content.optString("cause"); 
	    	 String operator = content.getString("operator");
	    	 JSONObject json = null;
	    	 Date date = new Date();
	    	 
	    	 if(status == 0) {
	    		 for (Object object : goodsRows) {
	 				json = JSONObject.fromObject(object);
	 				int spiderId = json.optInt("id");
	 				String spiderValId = json.optString("spiderValId");
	 				String beforeValue = json.optString("beforeValue");
	 				String afterValue = json.optString("afterValue");
	 				String compareType = json.optString("compareType");//对比类型，add-新增，update-数据不一致
	 				String type = json.optString("type");//数据项类型CT车型，CB车品牌，CF厂商，CS车系，CBY保养，CP配件
	 				CarSpiderAuditLogWithBLOBs auditLog = new CarSpiderAuditLogWithBLOBs();
	 				auditLog.setSpiderCompareId(spiderValId);
	 				auditLog.setAfterValue(afterValue);
	 				auditLog.setBeforeValue(beforeValue);
	 				auditLog.setStatus(status);
	 				auditLog.setType(type);
	 				auditLog.setCause(cause);
	 				auditLog.setCreateId(operator);
	 				auditLog.setCreateTime(date);
	 				
	 				
	 				JSONObject afterJson = JSONObject.fromObject(afterValue);
	 				String autohomeId = afterJson.optString("id");
	 				String pId = afterJson.optString("pid");
	 				String name = afterJson.optString("name");
	 				String imgPath = afterJson.optString("imgPath");
	 				String url = afterJson.optString("url");
	 				String level = afterJson.optString("level");
	 				String year = afterJson.optString("year");
	 				int grade = 0;
	 				if(StringUtil.isEmpty(level)) {
	 					grade =99;
	 				}else {
	 					grade = Integer.parseInt(level);
	 				}
	 				int scopeId = afterJson.optInt("scopeId");
	 				if("CB".equals(type)) {
	 					Brand record = new Brand();
	 					record.setBrandName(name);
	 					if(!StringUtil.isEmpty(url)) {
	 						record.setBrandUrl(url);
	 					}
	 					
	 					if(!StringUtil.isEmpty(imgPath)){
	 						record.setBrandLogo(imgPath);
	 					}
	 					
	 					
	 					if("add".equals(compareType)) {
	 						record.setBrandType(1);
		 					record.setFavCount(0);
	 						brandMapper.insertSelective(record);
	 						int brandId = record.getBrandId();
	 						BrandAutohome autohome = new BrandAutohome();
	 						autohome.setAutohomeId(autohomeId);
	 						autohome.setBrandId(brandId);
	 						brandAutohomeMapper.insert(autohome);
	 					}else {
	 						BrandAutohome ba = brandAutohomeMapper.selectByAutohomeId(autohomeId);
	 						record.setBrandId(ba.getBrandId());
	 						brandMapper.updateByPrimaryKeySelective(record);
	 					}
	 				}else if("CF".equals(type)) {
	 					CarFactory record = new CarFactory();
	 					record.setFactoryName(name);
	 					
	 					if(!StringUtil.isEmpty(imgPath)){
	 						record.setIconFile(imgPath);
	 					}
	 					
	 					if(!StringUtil.isEmpty(url)){
	 						record.setLinkUrl(url);
	 					}
	 					if("add".equals(compareType)) {
	 						int brandId = brandAutohomeMapper.findBrandId(pId);
	 						record.setBrandId(brandId);
		 					record.setScopeId(scopeId);
		 					record.setGroupId(0);
	 						carFactoryMapper.insertSelective(record);
	 						int factoryId = record.getFactoryId();
	 						CarFactoryAutohome autohome =  new CarFactoryAutohome();
	 						autohome.setAutohomeId(autohomeId);
	 						autohome.setFactoryId(factoryId);
	 						carFactoryAutohomeMapper.insertSelective(autohome);
	 					}else {
	 						
	 						CarFactoryAutohome cfa = carFactoryAutohomeMapper.selectByAutohomeId(autohomeId);
	 						record.setFactoryId(cfa.getFactoryId());
	 						carFactoryMapper.updateByPrimaryKeySelective(record);
	 					}
	 					
	 				}else if("CS".equals(type)) {
	 					CarDept record = new CarDept();
	 					record.setCarDeptName(name);
	 					
	 					if("add".equals(compareType)) {
	 						CarFactoryAutohome factoryAuto = carFactoryAutohomeMapper.selectByAutohomeId(pId);
	 						record.setFactoryid(factoryAuto.getFactoryId());
		 					record.setGradeId(grade);
	 						carDeptMapper.insertSelective(record);
	 						int carDeptId = record.getCarDeptId();
	 						CarDeptAutohome autohome = new CarDeptAutohome();
	 						autohome.setAutohomeId(autohomeId);
	 						autohome.setCarDeptId(carDeptId);
	 						carDeptAutohomeMapper.insertSelective(autohome);
	 					}else {
	 						int deptId = carDeptAutohomeMapper.selectbyAutohomeId(autohomeId);
	 						record.setCarDeptId(deptId);
	 						carDeptMapper.updateByPrimaryKeySelective(record);
	 					}
	 				}else if("CT".equals(type)) {
	 					Car record = new Car();
	 					record.setCarName(name);
	 					
	 					if(!StringUtil.isEmpty(imgPath)){
	 						record.setIconFile(imgPath);
	 					}
	 					
	 					if("add".equals(compareType)) {
	 						int carDeptId = carDeptAutohomeMapper.selectbyAutohomeId(autohomeId);
	 						CarYear yearId = carYearMapper.selectByDeptId(carDeptId,year);
	 						record.setCarDeptId(carDeptId);
		 					record.setFilterId(yearId.getId());
		 					record.setIsconfig(0);
	 						carMapper.insertSelective(record);
	 						int carId = record.getCarId();
	 						CarAutohome autohome = new CarAutohome();
	 						autohome.setAutohomeId(autohomeId);
	 						autohome.setCarId(carId);
	 						carAutohomeMapper.insertSelective(autohome);
	 						
	 						String configStr = afterJson.optString("detailConfig");
	 						if(!StringUtil.isEmpty(configStr)) {
	 							JSONArray configs = JSONArray.fromObject(configStr);
		 						StringBuffer sb = new StringBuffer();
		 						sb.append("[");
		 						for(Object obj : configs) {
		 							JSONObject config = JSONObject.fromObject(obj);
		 							String displayName = config.optString("displayName");
		 							String value = config.optString("configValue");
		 							
		 							CarData carData = carDataMapper.findByDisplayName(displayName);
		 							int dataId = carData.getDataId();
		 							String dataType = carData.getDataType();
		 							if("INT".equals(dataType)) {
		 								int intValue = 0;
		 								if(!StringUtil.isEmpty(value)) {
		 									intValue = Integer.parseInt(value);
		 								}
		 								CarDataInt intData = new CarDataInt();
		 								intData.setCarId(carId);
		 								intData.setDataId(dataId);
		 								intData.setValue(intValue);
		 								carDataIntMapper.insertSelective(intData);
		 								sb.append("{\"dataId\":\""+dataId+"\",\"displayName\":\""+displayName+"\",\"dataType\":\"INT\",\"configValue\":\""+intValue+"\"},");
		 							}else if("STR".equals(dataType)) {
		 								CarDataString strData = new CarDataString();
		 								strData.setCarId(carId);
		 								strData.setDataId(dataId);
		 								strData.setValue(value);
		 								carDataStringMapper.insertSelective(strData);
		 								sb.append("{\"dataId\":\""+dataId+"\",\"displayName\":\""+displayName+"\",\"dataType\":\"STR\",\"configValue\":\""+value+"\"},");
		 							}else if("DEC".equals(dataType)) {
		 								BigDecimal bd = new BigDecimal(0);
		 								if(!StringUtil.isEmpty(value)) {
		 									double doubleValue = Double.parseDouble(value);
		 									bd = BigDecimal.valueOf(doubleValue);
		 								}
		 								CarDataDecimal decData = new CarDataDecimal();
		 								decData.setCarId(carId);
		 								decData.setDataId(dataId);
		 								decData.setValue(bd);
		 								carDataDecimalMapper.insertSelective(decData);
		 								sb.append("{\"dataId\":\""+dataId+"\",\"displayName\":\""+displayName+"\",\"dataType\":\"DEC\",\"configValue\":\""+bd+"\"},");
		 							}else if("BOL".equals(dataType)) {
		 								String bolValue = "0";
		 								if(StringUtil.isEmpty(value) || "-".equals(value)) {
		 									bolValue = "0";
		 								}else {
		 									bolValue = "1";
		 								}
		 								CarDataBool bolData = new CarDataBool();
		 								bolData.setCarId(carId);
		 								bolData.setDataId(dataId);
		 								bolData.setValue(bolValue);
		 								carDataBoolMapper.insertSelective(bolData);
		 								sb.append("{\"dataId\":\""+dataId+"\",\"displayName\":\""+displayName+"\",\"dataType\":\"BOL\",\"configValue\":\""+bolValue+"\"},");
		 							}
		 						}
		 						if(sb.length() > 2) {
		 							sb.substring(0, sb.length()-1);
		 						}
		 						sb.append("]");
		 						CarConfigWithBLOBs configData = new CarConfigWithBLOBs();
		 						configData.setCarId(carId);
		 						configData.setConfigs(sb.toString());
		 						carConfigMapper.insertSelective(configData);
	 						}
	 						
	 					}else {
	 						int carId = carAutohomeMapper.selectByAutohomeId(autohomeId);
	 						record.setCarId(carId);
	 						carMapper.updateByPrimaryKeySelective(record);
	 						
	 						String configStr = afterJson.optString("detailConfig");
	 						if(!StringUtil.isEmpty(configStr)) {
	 							JSONArray configs = JSONArray.fromObject(configStr);
		 						StringBuffer sb = new StringBuffer();
		 						sb.append("[");
		 						for(Object obj : configs) {
		 							JSONObject config = JSONObject.fromObject(obj);
		 							String displayName = config.optString("displayName");
		 							String value = config.optString("configValue");
		 							
		 							CarData carData = carDataMapper.findByDisplayName(displayName);
		 							int dataId = carData.getDataId();
		 							String dataType = carData.getDataType();
		 							if("INT".equals(dataType)) {
		 								int intValue = 0;
		 								if(!StringUtil.isEmpty(value) && !"-".equals(value)) {
		 									intValue = Integer.parseInt(value);
		 								}
		 								CarDataInt intData = new CarDataInt();
		 								intData.setCarId(carId);
		 								intData.setDataId(dataId);
		 								intData.setValue(intValue);
		 								carDataIntMapper.updateByPrimaryKeySelective(intData);
		 								sb.append("{\"dataId\":\""+dataId+"\",\"displayName\":\""+displayName+"\",\"dataType\":\"INT\",\"configValue\":\""+intValue+"\"},");
		 							}else if("STR".equals(dataType)) {
		 								CarDataString strData = new CarDataString();
		 								strData.setCarId(carId);
		 								strData.setDataId(dataId);
		 								strData.setValue(value);
		 								carDataStringMapper.updateByPrimaryKeySelective(strData);
		 								sb.append("{\"dataId\":\""+dataId+"\",\"displayName\":\""+displayName+"\",\"dataType\":\"STR\",\"configValue\":\""+value+"\"},");
		 							}else if("DEC".equals(dataType)) {
		 								BigDecimal bd = new BigDecimal(0);
		 								if(!StringUtil.isEmpty(value) && !"-".equals(value)) {
		 									double doubleValue = Double.parseDouble(value);
		 									bd = BigDecimal.valueOf(doubleValue);
		 								}
		 								CarDataDecimal decData = new CarDataDecimal();
		 								decData.setCarId(carId);
		 								decData.setDataId(dataId);
		 								decData.setValue(bd);
		 								carDataDecimalMapper.updateByPrimaryKeySelective(decData);
		 								sb.append("{\"dataId\":\""+dataId+"\",\"displayName\":\""+displayName+"\",\"dataType\":\"DEC\",\"configValue\":\""+bd+"\"},");
		 							}else if("BOL".equals(dataType)) {
		 								String bolValue = "0";
		 								if(StringUtil.isEmpty(value) || "-".equals(value)) {
		 									bolValue = "0";
		 								}else {
		 									bolValue = "1";
		 								}
		 								CarDataBool bolData = new CarDataBool();
		 								bolData.setCarId(carId);
		 								bolData.setDataId(dataId);
		 								bolData.setValue(bolValue);
		 								carDataBoolMapper.updateByPrimaryKeySelective(bolData);
		 								sb.append("{\"dataId\":\""+dataId+"\",\"displayName\":\""+displayName+"\",\"dataType\":\"BOL\",\"configValue\":\""+bolValue+"\"},");
		 							}
		 						}
		 						if(sb.length() > 2) {
		 							sb.substring(0, sb.length()-1);
		 						}
		 						sb.append("]");
		 						CarConfigWithBLOBs configData = new CarConfigWithBLOBs();
		 						configData.setCarId(carId);
		 						configData.setConfigs(sb.toString());
		 						carConfigMapper.updateByCarIdSelective(configData);
	 						}
	 						
	 					}
	 				}/*else if("CBY".equals(type)) {
	 					
	 				}else if("CP".equals(type)) {
	 					
	 				}*/
	 				carSpiderAuditLogMapper.insertSelective(auditLog);
 					CarSpiderCompare csc = new CarSpiderCompare();
 					csc.setId(spiderId);
 					csc.setStatus(1);
 					carSpiderCompareMapper.updateByPrimaryKeySelective(csc);
	 			} 
	    	 }else {
	    		 for (Object object : goodsRows) {
		 				json = JSONObject.fromObject(object);
		 				int spiderId = json.optInt("id");
		 				String spiderValId = json.optString("spiderValId");
		 				String beforeValue = json.optString("beforeValue");
		 				String afterValue = json.optString("afterValue");
		 				//String compareType = json.optString("compareType");//对比类型，add-新增，update-数据不一致
		 				String type = json.optString("type");//数据项类型CT车型，CB车品牌，CF厂商，CS车系，CBY保养，CP配件
		 				CarSpiderAuditLogWithBLOBs auditLog = new CarSpiderAuditLogWithBLOBs();
		 				auditLog.setSpiderCompareId(spiderValId);
		 				auditLog.setAfterValue(afterValue);
		 				auditLog.setBeforeValue(beforeValue);
		 				auditLog.setStatus(status);
		 				auditLog.setType(type);
		 				auditLog.setCause(cause);
		 				auditLog.setCreateId(operator);
		 				auditLog.setCreateTime(date);
		 				
		 				carSpiderAuditLogMapper.insertSelective(auditLog);
		 				CarSpiderCompare csc = new CarSpiderCompare();
	 					csc.setId(spiderId);
	 					csc.setStatus(1);
	 					carSpiderCompareMapper.updateByPrimaryKeySelective(csc);
	    		 }
	    		 
	    		 
	    	 }
	    	 
	    	 head.setRetCode(ErrorCode.SUCCESS);     
	         return new ResultObject(head);
	    }
    
	
}
