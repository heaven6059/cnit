
package com.cnit.yoyo.car.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.car.dto.CarMaintainReferenceDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;

/**
 * 
 * @ClassName: CarMaintainController 
 * @Description: 保养周期管理
 * @author xiaox
 * @date 2015-3-26 下午4:12:19
 */
@Controller
@RequestMapping("/carMaintainReference")
public class CarMaintainReferenceController {
    public static final Logger log = LoggerFactory.getLogger(CarMaintainReferenceController.class);
    
    @Autowired
    private RemoteService itemService;

    /**
     * @description 映射至保养周期管理首页
     * @detail <方法详细描述>
     * @version 1.0.0
     * @data 2015年3月21日
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "pages/biz/car/carMaintainReferenceIndex";
    }
    
    /**
     * @description 映射至保养周期管理首页
     * @detail <方法详细描述>
     * @version 1.0.0
     * @data 2015年3月21日
     * @return
     */
    @RequestMapping("/toCarMaintainEdit")
    public String toCarMaintainEdit() {
        return "pages/biz/car/carMaintainEdit";
    }
    
    /**
      * @description <b>获取保养周期对比表</b>
      * @author 王鹏
      * @version 1.0.0
      * @data 2015-5-13
      * @param @param request
      * @param @param carMaintainDto
      * @param @return
      * @param @throws Exception
      * @return Object
    */
    @RequestMapping("/carMaintainReferenceList")
    @ResponseBody
    public Object getCarMaintainReferenceList(HttpServletRequest request, CarMaintainReferenceDTO dto) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010803-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, dto));
        return resultObject;
    }
}
