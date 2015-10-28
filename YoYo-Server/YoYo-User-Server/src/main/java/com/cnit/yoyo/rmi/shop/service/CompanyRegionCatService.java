package com.cnit.yoyo.rmi.shop.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.shop.CompanyRegionCatMapper;
import com.cnit.yoyo.dao.shop.CompanyRegionShipMapper;
import com.cnit.yoyo.dao.shop.ShopScopeMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.goods.GoodsCat;
import com.cnit.yoyo.model.shop.CompanyRegionCat;
import com.cnit.yoyo.model.shop.CompanyRegionShip;
import com.cnit.yoyo.model.shop.ShopScope;
import com.cnit.yoyo.model.shop.dto.CompanyRegionCatDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;

/**
 * @description 店铺经营范围service
 * @version 1.0.0
 */
@Service("companyRegionCatService")
public class CompanyRegionCatService {
    private static final Log log = LogFactory.getLog(CompanyRegionCatService.class);
    @Autowired
    private CompanyRegionCatMapper companyRegionCatMapper;
    @Autowired
    private CompanyRegionShipMapper companyRegionShipMapper;
    @Autowired
    private ShopScopeMapper shopScopeMapper;
    
    @Autowired
    private RemoteService itemService;
    /**
     * 
     *@description 通过父节点获取子节点
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-6-23
     *@param data
     *@return
     *@throws Exception
     */
    public Object getRegionByPid(Object data) throws Exception {
        log.info("start[CompanyRegionCatService.getRegionByPid]");
        JSONObject content = JSONObject.fromObject(data);
        int pid = (Integer) content.get("parentCatId");
        List<CompanyRegionCatDTO> list = companyRegionCatMapper.getRegionByPid(pid);
        log.info("end[CompanyRegionCatService.getRegionByPid]");
        return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONArray.fromObject(list));
    }

    /**
     * 
     *@description 添加经营范围
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-6-23
     *@param data
     *@return
     *@throws Exception
     */
    public Object insert(Object data) throws Exception {
        log.info("start[CompanyRegionCatService.saveCategory]");
        JSONObject content = JSONObject.fromObject(data);
        @SuppressWarnings("unchecked")
        Map<String,Object> map = (Map<String, Object>) data;
        CompanyRegionCat regionCat = (CompanyRegionCat) JSONObject.toBean(content.getJSONObject("cate"), CompanyRegionCat.class);
        Integer [] goodCategory = (Integer[])  map.get("goodCategory");
        int count = 0;
        //判断是否重名
        count = companyRegionCatMapper.findByRegionName(regionCat);
        if (count != 0) {
            return new HeadObject(ErrorCode.IS_EXIST);
        }
        if (regionCat.getRegionId() == null) {// 新增分类
            regionCat.setDisabled("0");
            regionCat.setCreateTime(new Date());
            regionCat.setLastMofify(new Date());
          //更改父级的子类数目
            if(regionCat.getParentRegionId()!=null && regionCat.getParentRegionId()!=0){
                CompanyRegionCatDTO temp = new CompanyRegionCatDTO();
                temp.setRegionId(regionCat.getParentRegionId());
                temp.setChildCount(1); //增加1
                companyRegionCatMapper.updateParentById(temp);
            }else{ //添加一级目录
                regionCat.setParentRegionId(0);
            }
            companyRegionCatMapper.insertSelective(regionCat);
            
        } else {
            regionCat.setLastMofify(new Date());
            companyRegionCatMapper.updateByPrimaryKeySelective(regionCat);
        }
        
      //先删除，再插入经营范围与分类的关系
        companyRegionShipMapper.delete(regionCat.getRegionId());
        CompanyRegionShip ship = new CompanyRegionShip();
        ship.setRegionId(regionCat.getRegionId());
        for(int i=0 ; i < goodCategory.length ; i++ ){
            ship.setCatId(goodCategory[i]);
            companyRegionShipMapper.insertSelective(ship);
        }
        
        log.info("end[CompanyRegionCatService.saveCategory]");
        return new HeadObject(ErrorCode.SUCCESS);
    }
    
    
    
    public Object delete(Object data) {
        HeadObject head = new HeadObject();
        JSONObject content = JSONObject.fromObject(data);
        CompanyRegionCatDTO regionCat = (CompanyRegionCatDTO) JSONObject.toBean(content, CompanyRegionCatDTO.class);
        companyRegionCatMapper.deleteById(regionCat.getRegionId());
        CompanyRegionCatDTO temp = new CompanyRegionCatDTO();
        temp.setRegionId(regionCat.getParentRegionId());
        temp.setChildCount(-1); //父级子类减1
        companyRegionCatMapper.updateParentById(temp);
        head.setRetCode(ErrorCode.SUCCESS);
        return new ResultObject(head);
    }
    
    /**
     * 
     *@description 查找指定经营范围的商品分类关系
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-6-24
     *@param data
     *@return
     */
    public Object findRegionShip(Object data){
        HeadObject head = new HeadObject();
        List<CompanyRegionShip> list = null;
        list =  companyRegionShipMapper.findRegionShip((Integer)data);
        head.setRetCode(ErrorCode.SUCCESS);
        return new ResultObject(head, list);
   }
    
    /**
     * 
     *@description 查找店铺经营范围
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-6-25
     *@param data
     *@return
     */
    public Object findRegionByCompanyId(Object data){
        HeadObject head = new HeadObject();
        List<ShopScope> list = null;
        list =  shopScopeMapper.findRegionByCompanyId((Integer)data);
        head.setRetCode(ErrorCode.SUCCESS);
        return new ResultObject(head, list);
   }
    
    
    /**
     * 
     *@description 根据经营范围找分类
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-7-7
     *@param data
     *@return
     */
    @SuppressWarnings("unchecked")
    public Object findGoodsCatByCompanyId(Object data){
        HeadObject head = new HeadObject();
        List<GoodsCat> list = null;
        HeadObject headObject = CommonHeadUtil.geneHeadObject("categoryService.findGoodsCatByCompanyId");
        ResultObject result = (ResultObject)itemService.doServiceByServer(new RequestObject(headObject,data));
        if(result!=null && result.getHead().getRetCode().equals(ErrorCode.SUCCESS)){
            list = (List<GoodsCat>) result.getContent();
            head.setRetCode(ErrorCode.SUCCESS);
        }else{
            head.setRetCode(ErrorCode.FAILURE);
        }
        return new ResultObject(head, list);
   }
}
