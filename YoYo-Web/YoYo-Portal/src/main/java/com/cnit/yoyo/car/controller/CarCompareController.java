package com.cnit.yoyo.car.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.car.Car;
import com.cnit.yoyo.model.car.dto.CarCompareDTO;
import com.cnit.yoyo.model.car.dto.CarDataCatalogDTO;
import com.cnit.yoyo.model.goods.dto.GoodsAccessoryDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;

/**
 * @ClassName: GoodsController
 * @Description: TODO(商品详情页)
 * @detail <详细说明>
 * @author <a href="cmlai@chinacnit.com">赖彩妙</a>
 * @date 2015-4-27 下午4:08:08
 * @version 1.0.0
 */
@Controller
@RequestMapping("/carCompare")
@SuppressWarnings("unchecked")
public class CarCompareController {

	public static final Logger log = LoggerFactory.getLogger(CarCompareController.class);
	@Autowired
	private RemoteService itemService;

	/**
	 * @description <b>进入车型对比页</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-5-19
	 * @param @param request
	 * @param @param carIds
	 * @param @return
	 * @return String
	 */
	@RequestMapping("/goodsCompare")
	public Object toGoodsCompare(HttpServletRequest request,@RequestParam(value="ids",required=true)Integer [] ids) {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request,"010203-01");
		ModelAndView model=new ModelAndView();
		try {
			ResultObject resultObj=itemService.doService(new RequestObject(headObject,ids));
			Map<String, Object> map = (Map<String, Object>) resultObj.getContent();
			List<GoodsAccessoryDTO> list=(List<GoodsAccessoryDTO>) map.get("goods");
			SimplePropertyPreFilter filter = new SimplePropertyPreFilter(GoodsAccessoryDTO.class, "goodsId","goodsImg","price","goodsName");			
			request.setAttribute("goods",JSONObject.toJSONString(list,filter));
			request.setAttribute("compareResult", JSON.toJSON(map.get("compareResult")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.setViewName("pages/biz/parameter/goodsCompare");
		return model;
	}
	
	
	/**
	 * @description <b>进入车型对比页</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-5-19
	 * @param @param request
	 * @param @param carIds
	 * @param @return
	 * @return String
	 */
	@RequestMapping("/carCompare")
	public Object toCarCompare(HttpServletRequest request,@RequestParam(value="ids",required=true)Long [] ids) {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request,"010804-01");
		ModelAndView model=new ModelAndView();
		try {
			ResultObject resultObj=itemService.doService(new RequestObject(headObject,ids));
			Map<String, Object> map = (Map<String, Object>) resultObj.getContent();
			List<Car> cars = (List<Car>) map.get("cars");
			List<CarCompareDTO> compares = (List<CarCompareDTO>) map.get("compares");
			List<CarDataCatalogDTO> dtos = (List<CarDataCatalogDTO>) map.get("carDataCatalogDTOs");
			request.setAttribute("cars", cars);
			request.setAttribute("dtos", dtos);
			request.setAttribute("compares", compares);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.setViewName("pages/biz/parameter/parameter");
		return model;
	}
	
	
	/**
	 * @description <b>进入对比页</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-5-19
	 * @param @param request
	 * @param @param carIds
	 * @param @return
	 * @return String
	 */
	@RequestMapping("/carDeptCompare")
	public Object toCarCompare(HttpServletRequest request,@RequestParam(value="deptId",required=true)Integer deptId) {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request,"010804-02");
		ModelAndView model=new ModelAndView();
		try {
			ResultObject resultObj=itemService.doService(new RequestObject(headObject,deptId));
			Map<String, Object> result=new TreeMap<String, Object>();
			result.put("returncode",0);
			result.put("message", null);
			result.put("result", resultObj.getContent());
			request.setAttribute("result",com.alibaba.fastjson.JSONObject.toJSONString(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.setViewName("pages/biz/parameter/carSeriesParam");
		return model;
	}
	
	
	
    /**
     * @description <b>查询汽车品牌</b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015-5-11
     * @param @param request
     * @param @return
     * @return Object
   */
   @ResponseBody
   @RequestMapping("/findCarBrand")
   public Object findCarBrand(HttpServletRequest request,@RequestParam(value="identifier" ,required=true)String identifier){
   	 	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010701-01");
        try {
        	Map<String, Object> params=new HashMap<String,Object>();
        	params.put("identifier", identifier);
       	 	ResultObject resultObject = itemService.doService(new RequestObject(headObject, params));
       	 	return resultObject;
		} catch (Exception e) {
			e.printStackTrace();
			headObject.setRetCode(ErrorCode.FAILURE);
			return headObject;
		}
   }
   
   
	   /**
	    * @description <b>查询汽车厂商以及车系</b>
	    * @author 王鹏
	    * @version 1.0.0
	    * @data 2015-5-11
	    * @param @param request
	    * @param @return
	    * @return Object
	  */
	  @ResponseBody
	  @RequestMapping("/findCarDept")
	  public Object findCarDept(HttpServletRequest request,@RequestParam(value="brandId" ,required=true)Integer brandId){
	  	 	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010801-08");
	  	 	try {
	  	 		ResultObject resultObject = itemService.doService(new RequestObject(headObject, brandId));
	  	 		return resultObject;
	  	 	} catch (Exception e) {
	  	 		e.printStackTrace();
	  	 		headObject.setRetCode(ErrorCode.FAILURE);
	  	 		return headObject;
	  	 	}
	  }
	  
	   /**
	    * @description <b>查询车型</b>
	    * @author 王鹏
	    * @version 1.0.0
	    * @data 2015-5-11
	    * @param @param request
	    * @param @return
	    * @return Object
	  */
	  @ResponseBody
	  @RequestMapping("/findCar")
	  public Object findCar(HttpServletRequest request,@RequestParam(value="deptId" ,required=true)Integer deptId){
	  	 	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010301-11");
	  	 	try {
	  	 		ResultObject resultObject = itemService.doService(new RequestObject(headObject, deptId));
	  	 		return resultObject;
	  	 	} catch (Exception e) {
	  	 		e.printStackTrace();
	  	 		headObject.setRetCode(ErrorCode.FAILURE);
	  	 		return headObject;
	  	 	}
	  }
}
