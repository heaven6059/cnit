package com.cnit.yoyo.rmi.car.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.dao.car.CarMaintainMapper;
import com.cnit.yoyo.dao.car.CarMaintainShipMapper;
import com.cnit.yoyo.dao.car.MaintainDefaultGoodsMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.car.CarMaintain;
import com.cnit.yoyo.model.car.CarMaintainShip;
import com.cnit.yoyo.model.car.MaintainDefaultGoods;
import com.cnit.yoyo.model.car.dto.CarMaintainDTO;
import com.cnit.yoyo.model.car.dto.CarMaintainGoodsDTO;
import com.cnit.yoyo.model.car.dto.CarMaintainItemDTO;
import com.cnit.yoyo.model.car.dto.CarMaintainQryDTO;
import com.cnit.yoyo.model.car.dto.CarMaintainReferenceDTO;
import com.cnit.yoyo.model.car.dto.CarMaintainShipDTO;
import com.cnit.yoyo.model.car.dto.MaintainAccessoryCatalogDTO;
import com.cnit.yoyo.model.car.dto.MaintainAccessoryItemsDTO;
import com.cnit.yoyo.model.car.dto.MaintainCompanyDTO;
import com.cnit.yoyo.model.car.dto.MaintainProductDTO;
import com.cnit.yoyo.util.StringUtil;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

/**
 * @ClassName: CarMaintainService
 * @Description: 保养周期service
 * @author xiaox
 * @date 2015-3-26 下午4:20:04
 */
@Service("carMaintainService")
public class CarMaintainService {
    @Autowired
    private CarMaintainMapper carMaintainMapper;

    @Autowired
    private CarMaintainShipMapper carMaintainShipMapper;
    @Autowired
    private MaintainDefaultGoodsMapper maintainDefaultGoodsMapper;

    /**
     * @Title: findBrandList
     * @Description: 查找保养周期
     * @author xiaox
     * @date 2015-3-26 下午4:20:57
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Object findList(Object data) {
        HeadObject head = new HeadObject();
        ResultPage<CarMaintain> page = null;
        try {
            JSONObject content = JSONObject.fromObject(data);
            PageHelper.startPage(content.getInt("pageIndex"), content.getInt("pageSize"));
            CarMaintainDTO carMaintain = (CarMaintainDTO) JSONObject.toBean(content.getJSONObject("carMaintainDto"), CarMaintainDTO.class);
            page = new ResultPage(carMaintainMapper.findList(carMaintain));
            head.setRetCode(ErrorCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            head.setRetCode(ErrorCode.FAILURE);
        }
        return new ResultObject(head, JSONObject.fromObject(page));
    }

    /**
     * @description <b>查询保养对比数据</b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015-5-13
     * @param @param data
     * @param @return
     * @return Object
     */
    public Object selectCarMaintainReference(Object data) {
        HeadObject head = new HeadObject();
        ResultPage<CarMaintainReferenceDTO> page = null;
        try {
            CarMaintainReferenceDTO dto = (CarMaintainReferenceDTO) data;
            HashMap<String, BigDecimal> map = carMaintainMapper.selectCarMaintainCount();
            if (map.get("cycle").compareTo(map.get("crawler")) == 1) {
                dto.setInvert(true);
            }
            PageHelper.startPage(dto.getPage(), dto.getRows());
            page = new ResultPage<CarMaintainReferenceDTO>(carMaintainMapper.selectCarMaintainReference(dto));
            head.setRetCode(ErrorCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            head.setRetCode(ErrorCode.FAILURE);
        }
        return new ResultObject(head, JSONObject.fromObject(page));
    }

    /**
     * @Title: insertCarMaintain
     * @Description:修改或者保养周期
     * @param @param data
     * @param @return 设定文件
     * @author xiaox
     * @date 2015-3-26 下午7:47:02
     */
    public Object insertCarMaintain(Object data) {
        HeadObject head = new HeadObject();
        try {
            JSONObject content = JSONObject.fromObject(data);
            CarMaintainDTO carMaintainDto = (CarMaintainDTO) JSONObject.toBean(content, CarMaintainDTO.class);
            if (null != carMaintainDto.getMaintainId() && carMaintainDto.getMaintainId() > 0) {
                carMaintainMapper.updateByPrimaryKeySelective(carMaintainDto);
            } else {
                carMaintainMapper.insertSelective(carMaintainDto);
            }
            head.setRetCode(ErrorCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            head.setRetCode(ErrorCode.FAILURE);
        }
        return new ResultObject(head);
    }

    /**
     * @Title: findByCarIdandName
     * @Description: 查询当前车型是否存在相同保养周期
     * @param @param data
     * @param @return 设定文件
     * @author xiaox
     * @date 2015-3-27 下午1:44:20
     */
    public Object findByCarIdandName(Object data) {
        HeadObject head = new HeadObject();
        try {
            JSONObject content = JSONObject.fromObject(data);
            CarMaintainDTO carMaintainDto = (CarMaintainDTO) JSONObject.toBean(content, CarMaintainDTO.class);
            int count = carMaintainMapper.findByCarIdandName(carMaintainDto);
            if (count == 0) {
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
     * @Title: deleteBrand
     * @Description: 逻辑删除周期以及周期与保养类别的关系
     * @param @param data
     * @param @return 设定文件
     * @author xiaox
     * @date 2015-3-27 下午2:06:49
     */
    public Object deleteByCarMaintainIds(Object data) {
        HeadObject head = new HeadObject();
        try {
            Integer[] carMaintainIds = (Integer[]) data;
            carMaintainMapper.deleteByPrimaryKey(carMaintainIds);
            // 删除对应与分类的关系表
            carMaintainShipMapper.deleteByCarMaintainIds(carMaintainIds);
            head.setRetCode(ErrorCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            head.setRetCode(ErrorCode.FAILURE);
        }
        return new ResultObject(head);
    }

    /**
     * @Title: updateCarMaintain
     * @Description: 只更新基础数据，此处不更新关系表
     * @param @param data
     * @param @return 设定文件
     * @author xiaox
     * @date 2015-3-27 下午3:29:26
     */
    public Object updateCarMaintain(Object data) {
        HeadObject head = new HeadObject();
        try {
            JSONObject content = JSONObject.fromObject(data);
            CarMaintainDTO carMaintainDto = (CarMaintainDTO) JSONObject.toBean(content, CarMaintainDTO.class);
            carMaintainMapper.updateByPrimaryKeySelective(carMaintainDto);
            head.setRetCode(ErrorCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            head.setRetCode(ErrorCode.FAILURE);
        }
        return new ResultObject(head);
    }

    /**
     * @Title: updateMaintainCate
     * @Description: 只更新关系表
     * @param @param data
     * @param @return 设定文件
     * @author xiaox
     * @date 2015-3-27 下午5:29:26
     */
    public Object updateMaintainCate(Object data) {
        HeadObject head = new HeadObject();
        try {
            JSONObject content = JSONObject.fromObject(data);
            // 先删除所有的，然后添加
            int id = content.getInt("maintainId");
            carMaintainShipMapper.deleteByMaintainId(id);
            JSONArray arr = content.getJSONArray("categoryIds");
            CarMaintainShip ship = new CarMaintainShip();
            ship.setMaintainId(id);
            ship.setDisabled(GlobalStatic.DISABLED_DEFAULT_ON);
            for (int i = 0; i < arr.size(); i++) {
                ship.setCatId(arr.getInt(i));
                carMaintainShipMapper.insertSelective(ship);
            }
            head.setRetCode(ErrorCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            head.setRetCode(ErrorCode.FAILURE);
        }
        return new ResultObject(head);
    }

    /**
     * @Title: findMaintainShip
     * @Description: 查找保养周期与分类的关系
     * @param @param data
     * @param @return 设定文件
     * @author xiaox
     * @date 2015-3-28 上午9:43:13
     */
    public Object findMaintainShip(Object data) {
        HeadObject head = new HeadObject();
        List<CarMaintainShipDTO> list = null;
        try {
            Integer maintainId = (Integer) data;
            list = carMaintainShipMapper.selectShip(maintainId);
            head.setRetCode(ErrorCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            head.setRetCode(ErrorCode.FAILURE);
        }
        return new ResultObject(head, list);
    }

    /**
     * @description <b>>查具体车型的保养信息</b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015-5-12
     * @param @param data
     * @param @return
     * @return Object
     */
    public Object findCarMaintainByCarId(Object data) {
        HeadObject head = new HeadObject();
        List<CarMaintain> list = null;
        try {
            Integer carId = (Integer) data;
            list = carMaintainMapper.selectCarmaintainByCarId(carId);
            head.setRetCode(ErrorCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            head.setRetCode(ErrorCode.FAILURE);
        }
        return new ResultObject(head, list);
    }

    /**
     * @description <b>主键查询保养信息</b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015-5-12
     * @param @param data
     * @param @return
     * @return Object
     */
    public Object findCarMaintainById(Object data) {
        HeadObject head = new HeadObject();
        CarMaintain carMaintain = null;
        try {
            Integer carId = (Integer) data;
            carMaintain = carMaintainMapper.selectByPrimaryKey(carId);
            head.setRetCode(ErrorCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            head.setRetCode(ErrorCode.FAILURE);
        }
        return new ResultObject(head, carMaintain);
    }

    /**
     * @description 卖家中心--》设置保养商品
     * @detail 获取所有保养周期
     * @author <a href="xiaoxiang@cnit.com">肖湘</a>
     * @version 1.0.0
     * @data 2015-5-18
     * @param data
     * @return
     */
    public Object selectCarMaintainByParam(Object data) {
        HeadObject head = new HeadObject();
        ResultPage<CarMaintainReferenceDTO> page = null;
        CarMaintainReferenceDTO dto = (CarMaintainReferenceDTO) data;
        PageHelper.startPage(dto.getPage(), dto.getRows());
        page = new ResultPage<CarMaintainReferenceDTO>(carMaintainMapper.selectCarMaintainByParam(dto));

        head.setRetCode(ErrorCode.SUCCESS);

        return new ResultObject(head, JSONObject.fromObject(page));
    }

    /**
     * @description 卖家中心--》设置保养商品
     * @description 通过分类id集合获得指定的保养项目的保养配件类别
     * @author <a href="xiaoxiang@cnit.com">肖湘</a>
     * @version 1.0.0
     * @data 2015-5-21
     * @param data
     * @return
     */
    public Object findCarMaintainItemByIds(Object data) {
        HeadObject head = new HeadObject();
        List<CarMaintainGoodsDTO> carMaintainGoods = null;
        JSONObject content = JSONObject.fromObject(data);
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("companyId", Integer.valueOf(content.getString("companyId")));
            map.put("ids", content.getJSONArray("ids").toArray());
            map.put("maintianId", Integer.valueOf(content.getString("maintianId")));
            carMaintainGoods = carMaintainMapper.findCarMaintainItemByIds(map);
            head.setRetCode(ErrorCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            head.setRetCode(ErrorCode.FAILURE);
        }
        return new ResultObject(head, carMaintainGoods);
    }

    
    /**
     * 
     *@description 保存或更新保养默认商品
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-5-22
     *@param data
     *@return
     */
    public Object saveDefaultGoods(Object data) {
        HeadObject head = new HeadObject();
        CarMaintainGoodsDTO defaultGoods= (CarMaintainGoodsDTO)data;
        try {
            //先删除后添加
            maintainDefaultGoodsMapper.deleteByParam(defaultGoods.getCompanyId(),defaultGoods.getMaintainId(),defaultGoods.getMaintainItemId());
            //添加
            MaintainDefaultGoods defaultProduct = new MaintainDefaultGoods();
            defaultProduct.setAccId(defaultGoods.getMaintainItemId());
            defaultProduct.setCompanyId(Long.valueOf(defaultGoods.getCompanyId()));
            defaultProduct.setGoodsId(Long.valueOf(defaultGoods.getProductId()));
            defaultProduct.setMaintainId(defaultGoods.getMaintainId());
            defaultProduct.setModifyTime(new Date());
            
            maintainDefaultGoodsMapper.insertSelective(defaultProduct);
            head.setRetCode(ErrorCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            head.setRetCode(ErrorCode.FAILURE);
        }
        return new ResultObject(head);
    }
    
    public Object findCarMaintain(Object data){
    	 HeadObject head = new HeadObject();
    	 CarMaintain carMaintain = null;
         try {
        	 carMaintain = carMaintainMapper.selectCarMaintain((CarMaintainQryDTO) data);
        	 carMaintain.setOptionalMaintains(com.alibaba.fastjson.JSONArray.parseArray(carMaintain.getOptionalMaintain(), CarMaintainItemDTO.class));
        	 carMaintain.setOfficialMaintains(com.alibaba.fastjson.JSONArray.parseArray(carMaintain.getOfficialMaintain(), CarMaintainItemDTO.class));
        	 
             head.setRetCode(ErrorCode.SUCCESS);
         } catch (Exception e) {
             e.printStackTrace();
             head.setRetCode(ErrorCode.FAILURE);
         }
         return new ResultObject(head,carMaintain);
    }
    
    public Object findMaintainCompany(Object data){
    	HeadObject head = new HeadObject();
    	List<MaintainCompanyDTO> maintainCompanys=null;
    	try{
    		CarMaintainQryDTO qryDTO=(CarMaintainQryDTO)data;
    		if(qryDTO.getIds().length>0){
    		qryDTO.setAccCount(qryDTO.getIds().length);
    		maintainCompanys = carMaintainMapper.selectMaintainCompany(qryDTO);
    		}
    		head.setRetCode(ErrorCode.SUCCESS);
    	}catch (Exception e) {
    		head.setRetCode(ErrorCode.FAILURE);
    		e.printStackTrace();
		}
    	return new ResultObject(head,maintainCompanys);
    }

    public Object findDefautlGoods(Object data){
    	HeadObject head = new HeadObject();
    	List<MaintainProductDTO> carMaintainCompany=null;
    	try{
    		carMaintainCompany = carMaintainMapper.selectDefautlGoods((CarMaintainQryDTO) data);
    		head.setRetCode(ErrorCode.SUCCESS);
    	}catch (Exception e) {
    		head.setRetCode(ErrorCode.FAILURE);
    		e.printStackTrace();
		}
    	return new ResultObject(head,carMaintainCompany);
    }
    
    public Object findOptionalAccessories(Object data){
    	HeadObject head = new HeadObject();
    	Map<String, Object> result=new HashMap();
    	try{
    		result.put("product", carMaintainMapper.selectOptionalAccessoriesProduct((CarMaintainQryDTO) data));
    		result.put("brand", carMaintainMapper.selectOptionalAccessoriesBrand((CarMaintainQryDTO) data));
    		head.setRetCode(ErrorCode.SUCCESS);
    	}catch (Exception e) {
    		head.setRetCode(ErrorCode.FAILURE);
    		e.printStackTrace();
		}
    	return new ResultObject(head,result);
    }
    
    public Object findCarMaintainItems(Object data){
    	HeadObject head = new HeadObject();
    	Map<String, Object> resultMap=new HashMap<String,Object>();
    	try{
    		 CarMaintain carMaintain = carMaintainMapper.selectCarMaintain((CarMaintainQryDTO) data);
    		 List<CarMaintainItemDTO> carMaintainItemDTOs=new ArrayList<CarMaintainItemDTO>();
    		 if(!StringUtil.isEmpty(carMaintain.getOptionalMaintain())){
    			 try{
    				 carMaintain.setOptionalMaintains(com.alibaba.fastjson.JSONArray.parseArray(carMaintain.getOptionalMaintain(), CarMaintainItemDTO.class));
    				 carMaintainItemDTOs.addAll(carMaintain.getOptionalMaintains());
    			 }catch (Exception e) {
    				 e.printStackTrace();
    			 }
    		 }
    		 if(!StringUtil.isEmpty(carMaintain.getOfficialMaintain())){
    			 try{
    				 carMaintain.setOfficialMaintains(com.alibaba.fastjson.JSONArray.parseArray(carMaintain.getOfficialMaintain(), CarMaintainItemDTO.class));
    				 carMaintainItemDTOs.addAll(carMaintain.getOfficialMaintains());
    			 }catch (Exception e) {
    				 e.printStackTrace();
    			 }
    		 }
    		 List<MaintainAccessoryItemsDTO> carMaintainAccessoryItems = carMaintainMapper.selectMaintainAccessoryItems(carMaintainItemDTOs);
        	 resultMap.put("carMaintainAccessoryItems", carMaintainAccessoryItems);
        	 resultMap.put("carMaintain", carMaintain);
    		head.setRetCode(ErrorCode.SUCCESS);
    	}catch (Exception e) {
    		head.setRetCode(ErrorCode.FAILURE);
    		e.printStackTrace();
		}
    	return new ResultObject(head,resultMap);
    }
    
    
    public Object findMaintainAccessoryCatalog(Object data){
    	HeadObject head = new HeadObject();
    	List<MaintainAccessoryCatalogDTO> maintainAccessoryCatalogs = null;
    	try{
    		maintainAccessoryCatalogs = carMaintainMapper.selectMaintainAccessoryCatalog((CarMaintainQryDTO) data);
    		head.setRetCode(ErrorCode.SUCCESS);
    	}catch (Exception e) {
    		head.setRetCode(ErrorCode.FAILURE);
    		e.printStackTrace();
		}
    	return new ResultObject(head,maintainAccessoryCatalogs);
    }
    
}
