
package com.cnit.yoyo.car.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.car.CarDataCatalog;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.StringUtil;

/**
 * 
 * @ClassName: CarDataCategoryController 
 * @Description: 数据项类别
 * @author xiaox
 * @date 2015-3-31 下午2:11:10
 */
@Controller
@RequestMapping("/carDataCate")
public class CarDataCategoryController {
    public static final Logger log = LoggerFactory.getLogger(CarDataCategoryController.class);
    
    @Autowired
    private RemoteService itemService;

    /**
     * 
     *@description 车型数据项类别
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-5-14
     *@return
     */
    @RequestMapping("/index")
    public String indexCate() {
        return "pages/biz/car/carDataTypeIndex";
    }
  
    /**
     * 
     * @Description: 获取数据项类别列表 
     * @author xiaox
     * @date 2015-3-31 上午11:06:19
     */
    @RequestMapping("/carDataCateList")
    @ResponseBody
    public Object getCarDataCateList(HttpServletRequest request,CarDataCatalog catalog,String sort, String order) throws Exception {
         Map<String, Object> data = new HashMap<String, Object>();
         HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020116-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
         String pageIndex = request.getParameter("page");
         String pageSize = request.getParameter("rows");
         if (!StringUtils.isNotBlank(sort)) {
             sort = "t_car_data_catalog.catalogId";
             order = "desc";
         }else{
             if(sort.split(",").length>1){
                 sort=sort.split(",")[sort.split(",").length-1];
             }
         }
         if(StringUtils.isNotBlank(order)){
             if(order.split(",").length>1){
                 order=order.split(",")[order.split(",").length-1];
             }
         }
         data.put("sort", sort);
         data.put("order", order);
         data.put("cateName", catalog.getCatalogName());
         data.put("pageIndex", StringUtil.isEmpty(pageIndex) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
         data.put("pageSize", StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize));
         ResultObject resultObject = itemService.doService(new RequestObject(headObject,data));
         return resultObject;
    }
    
  /**
   * 
   * @Description: 保存数据项类别  
   * @param @param request
   * @param @return
   * @author xiaox
   * @date 2015-3-31 下午2:42:32
   */
    @RequestMapping("/insertCarDataCate")
    @ResponseBody
    public Object insertCarDataCate(HttpServletRequest request,CarDataCatalog catalog){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020116-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
        	   resultObject = itemService.doService(new RequestObject(headObject, catalog));
        	 
        	   if(resultObject.getHead().getRetCode().equals(ErrorCode.SUCCESS)){  //同一车型的周期名称不存在，可以添加
        		  
	        	   headObject = CommonHeadUtil.geneHeadObject(request, "2000020116-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	        	   
	        	   resultObject = itemService.doService(new RequestObject(headObject, catalog));
        	   }
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    
    
    
    /**
     * 
     * @Description: 逻辑删除 
     * @param @param request
     * @param @param id
     * @param @return
     * @author xiaox
     * @date 2015-3-30 上午11:02:26
     */
    @RequestMapping("/deleteCarDataCate")
    @ResponseBody
    public Object deleteCarDataCate(HttpServletRequest request,@RequestParam(value="id[]",required=true)Integer[] id){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020116-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
        	   resultObject = itemService.doService(new RequestObject(headObject, id));
        	   
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    
    /**
     * 
     * @Description: 更新 
     * @param @param request
     * @param @param carScope
     * @param @return
     * @author xiaox
     * @date 2015-3-30 上午11:36:08
     */
    @RequestMapping("/updateCarDataCate")
    @ResponseBody
    public Object updateCarDataCate(HttpServletRequest request,CarDataCatalog catalog){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020116-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
	       	   resultObject = itemService.doService(new RequestObject(headObject, catalog));
	       	   if(resultObject.getHead().getRetCode().equals(ErrorCode.SUCCESS)){  //修改后的名称不同名，可以修改
	        	   headObject = CommonHeadUtil.geneHeadObject(request, "2000020116-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	        	   resultObject = itemService.doService(new RequestObject(headObject, catalog));
	       	   }
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    
    /**
     * 跳转到汽车配置信息页面
     * @return
     */
    @RequestMapping("/configinfoindex")
    public String index(Model model,@RequestParam(value="carId",required=true)Integer carId) {
    	model.addAttribute("carId", carId);
        return "pages/biz/car/carConfiginfoIndex";
    }
    
    
    /**
     * @description 保存配件参数类型信息
     * @detail <方法详细描述>
     * @version 1.0.0
     * @param request
     * @return
     * @author ssd
     * @throws Exception
     */
    @RequestMapping("/saveConfiginfo")
    @ResponseBody
    public Object saveConfiginfo(HttpServletRequest request) throws Exception {
    	
         JSONObject content = new JSONObject();
         String spec = request.getParameter("carId");
         
         if (!StringUtil.isEmpty(spec)) {
        	 spec = "{carId:"+spec+"}";
             JSONObject specJSON = JSONObject.fromObject(spec);
             content.put("carId", specJSON);
         }
         String temp = request.getParameter("inserted");
         if (!StringUtil.isEmpty(temp)) {
             JSONArray valuesJSON = JSONArray.fromObject(temp);
             content.put("inserted", valuesJSON);
         }
         /*temp = request.getParameter("updated");
         if (!StringUtil.isEmpty(temp)) {
             JSONArray valuesJSON = JSONArray.fromObject(temp);
             content.put("updated", valuesJSON);
         }
         temp = request.getParameter("deleted");
         if (!StringUtil.isEmpty(temp)) {
             JSONArray valuesJSON = JSONArray.fromObject(temp);
             content.put("deleted", valuesJSON);
         }*/
         HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020115-10", GlobalStatic.SYSTEM_CODE_DATA,
                 GlobalStatic.SYSTEM_CODE_BACK);
         ResultObject resultObject = itemService.doService(new RequestObject(headObject, content));
         return resultObject;
    	
    	
    	
        /*log.info("start[CarDataCategoryController.saveConfiginfo]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020104-03", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        JSONObject content = new JSONObject();
        String carId = request.getParameter("carId");
        int carNum = Integer.parseInt(carId);
        int dataNum = 0;
        String dataType = "";
        String configValue = "";
       
        List<CarDataString> stingList = new ArrayList<CarDataString>();
        List<CarDataInt> intList = new ArrayList<CarDataInt>();
        List<CarDataBool> boolList = new ArrayList<CarDataBool>();
        List<CarDataDecimal> deciList = new ArrayList<CarDataDecimal>();
        JSONArray valuesJSON = null;
        JSONArray valuesJSON1 = null;
        
        String temp = request.getParameter("inserted");
        if (!StringUtil.isEmpty(temp)) {
            valuesJSON = JSONArray.fromObject(temp);
            if(!valuesJSON.isEmpty()) {
            	valuesJSON1 = valuesJSON;
            }
            //content.put("inserted", valuesJSON);
        }
        temp = request.getParameter("updated");
        if (!StringUtil.isEmpty(temp)) {
            valuesJSON = JSONArray.fromObject(temp);
            //content.put("updated", valuesJSON);
            if(!valuesJSON.isEmpty()) {
            	valuesJSON1 = valuesJSON;
            }
        }
        temp = request.getParameter("deleted");
        if (!StringUtil.isEmpty(temp)) {
            valuesJSON = JSONArray.fromObject(temp);
            //content.put("deleted", valuesJSON);
            if(!valuesJSON.isEmpty()) {
            	valuesJSON1 = valuesJSON;
            }
        }
        
        HeadObject stringHead = CommonHeadUtil.geneHeadObject(request, "2000020115-06", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        HeadObject intHead = CommonHeadUtil.geneHeadObject(request, "2000020115-07", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        HeadObject boolHead = CommonHeadUtil.geneHeadObject(request, "2000020115-08", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        HeadObject decmiHead = CommonHeadUtil.geneHeadObject(request, "2000020115-09", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        
        
        List<CarConfiginfoDTO> list = (List<CarConfiginfoDTO>)JSONArray.toCollection(valuesJSON1, CarConfiginfoDTO.class);
        for(int i=0;i<list.size();i++) {
        	CarConfiginfoDTO dto = list.get(i);
        	dataType = dto.getDataType();
        	dataNum = Integer.parseInt(dto.getDataId());
        	configValue = dto.getConfigValue();
        	if("STR".equals(dataType)) {
        		CarDataString data = new CarDataString();
        		data.setCarId(carNum);
        		data.setDataId(dataNum);
        		data.setValue(configValue);
        		stingList.add(data);
        	}else if("INT".equals(dataType)) {
        		CarDataInt data = new CarDataInt();
        		data.setCarId(carNum);
        		data.setDataId(dataNum);
        		data.setValue(Integer.parseInt(configValue));
        		intList.add(data);
        	}else if("BOL".equals(dataType)) {
        		CarDataBool data = new CarDataBool();
        		data.setCarId(carNum);
        		data.setDataId(dataNum);
        		data.setValue(configValue);
        		boolList.add(data);
        	}else if("DEC".equals(dataType)) {
        		CarDataDecimal data = new CarDataDecimal();
        		data.setCarId(carNum);
        		data.setDataId(dataNum);
        		data.setValue(BigDecimal.valueOf(Long.parseLong(configValue)));
        		deciList.add(data);
        	}
        }
        ResultObject stringObject = null;
        ResultObject intObject = null;
        ResultObject boolObject = null;
        ResultObject deciObject = null;
        if(stingList.size()>0) {
        	stringObject = itemService.doService(new RequestObject(stringHead, stingList));
        }
        if(intList.size()>0) {
        	intObject = itemService.doService(new RequestObject(intHead, intList));
        }
        if(boolList.size()>0) {
        	boolObject = itemService.doService(new RequestObject(boolHead, boolList));
        }
        if(deciList.size()>0) {
        	deciObject = itemService.doService(new RequestObject(decmiHead, deciList));
        }
        headObject.setRetCode(ErrorCode.SUCCESS);
        if(stringObject.getRetCode().equals(ErrorCode.SUCCESS))
        //ResultObject resultObject = itemService.doService(new RequestObject(headObject, content));
        log.info("end[AccessoryController.saveAccessoryCatalog]");
        return stringObject;*/
    }
    
    /**
     * 
    *
    * @Description: 更新车型数据项值 
    * @param @param request
    * @param @return
    * @param @throws Exception    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:29:42 
    * @return Object    返回类型 
    * @throws
     */
    @RequestMapping("/updateConfiginfo")
    @ResponseBody
    public Object updateConfiginfo(HttpServletRequest request) throws Exception {
    	
         JSONObject content = new JSONObject();
         String spec = request.getParameter("carId");
         
         if (!StringUtil.isEmpty(spec)) {
        	 spec = "{carId:"+spec+"}";
             JSONObject specJSON = JSONObject.fromObject(spec);
             content.put("carId", specJSON);
         }
         String temp = request.getParameter("inserted");
         if (!StringUtil.isEmpty(temp)) {
             JSONArray valuesJSON = JSONArray.fromObject(temp);
             content.put("inserted", valuesJSON);
         }
         
        /* temp = request.getParameter("updated");
         if (!StringUtil.isEmpty(temp)) {
             JSONArray valuesJSON = JSONArray.fromObject(temp);
             content.put("updated", valuesJSON);
         }
         temp = request.getParameter("deleted");
         if (!StringUtil.isEmpty(temp)) {
             JSONArray valuesJSON = JSONArray.fromObject(temp);
             content.put("deleted", valuesJSON);
         }*/
         HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020115-11", GlobalStatic.SYSTEM_CODE_DATA,
                 GlobalStatic.SYSTEM_CODE_BACK);
         ResultObject resultObject = itemService.doService(new RequestObject(headObject, content));
         return resultObject;
    }
    
   
}
