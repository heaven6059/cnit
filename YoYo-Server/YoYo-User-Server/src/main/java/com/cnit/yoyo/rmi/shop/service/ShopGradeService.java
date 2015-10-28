package com.cnit.yoyo.rmi.shop.service;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.shop.CompanyMapper;
import com.cnit.yoyo.dao.shop.ShopGradeMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.goods.CompanyWithBLOBs;
import com.cnit.yoyo.model.shop.ShopGrade;
import com.cnit.yoyo.model.shop.dto.ShopGradeDTO;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

/**
 * @description 店铺等级service
 * @version 1.0.0
 */
@Service("shopGradeService")
public class ShopGradeService {
    private static final Log log = LogFactory.getLog(ShopGradeService.class);
    @Autowired
    private ShopGradeMapper shopGradeMapper;
    @Autowired
    private CompanyMapper companyMapper;

    /**
     * @Title: findByType
     * @Description: 通过店铺类型，查找店铺等级
     * @param @param data
     * @param @return 设定文件
     * @author xiaox
     * @date 2015-3-19 下午6:25:11
     */
    public Object findByType(Object data) {
        HeadObject head = new HeadObject();
        List<ShopGrade> list = null;
        try {
            JSONObject content = JSONObject.fromObject(data);
            list = shopGradeMapper.findByType(content.getString("type"));
            head.setRetCode(ErrorCode.SUCCESS);
        } catch (Exception e) {
            log.error(e.getMessage());
            head.setRetCode(ErrorCode.FAILURE);
        }

        return new ResultObject(head, list);
    }

    /**
     * @Title: findByCompanyId
     * @Description: 通过公司id，查找公司等级信息
     * @param @param data
     * @param @return 设定文件
     * @author xiaox
     * @date 2015-3-19 下午6:22:48
     */
    public Object findByCompanyId(Object data) {
        HeadObject head = new HeadObject();
        ShopGrade shopGrade = null;
        try {
            JSONObject content = JSONObject.fromObject(data);
            shopGrade = shopGradeMapper.findByCompanyId(content.getLong("companyId"));
            head.setRetCode(ErrorCode.SUCCESS);
        } catch (Exception e) {
            log.error(e.getMessage());
            head.setRetCode(ErrorCode.FAILURE);
        }

        return new ResultObject(head, shopGrade);
    }

    /**
     * @description 按条件查找所有店铺等级
     * @detail <方法详细描述>
     * @author <a href="xiaoxiang@cnit.com">肖湘</a>
     * @version 1.0.0
     * @data 2015-5-25
     * @param data
     * @return
     */
    public Object findShopGradeByParam(Object data) {
        HeadObject head = new HeadObject();
        ResultPage<ShopGrade> page = null;
        JSONObject content = JSONObject.fromObject(data);
        ShopGradeDTO shopGradeDto = (ShopGradeDTO) JSONObject.toBean(content, ShopGradeDTO.class);
        PageHelper.startPage(shopGradeDto.getPage(), shopGradeDto.getRows());
        page = new ResultPage<ShopGrade>(shopGradeMapper.findShopGradeByParam(shopGradeDto));
        head.setRetCode(ErrorCode.SUCCESS);
        return new ResultObject(head, JSONObject.fromObject(page));
    }

    /**
     * @description 通过名称查找等级是否存在
     * @detail <方法详细描述>
     * @author <a href="xiaoxiang@cnit.com">肖湘</a>
     * @version 1.0.0
     * @data 2015-5-25
     * @param data
     * @return
     */
    public Object findByName(Object data) {
        HeadObject head = new HeadObject();
        try {
            JSONObject content = JSONObject.fromObject(data);
            ShopGrade dto = (ShopGrade) JSONObject.toBean(content, ShopGrade.class);
            int count = shopGradeMapper.findByName(dto);
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
     * @description 插入店铺等级
     * @detail <方法详细描述>
     * @author <a href="xiaoxiang@cnit.com">肖湘</a>
     * @version 1.0.0
     * @data 2015-5-25
     * @param data
     * @return
     */
    public Object insertShopGrade(Object data) {
        HeadObject head = new HeadObject();
        JSONObject content = JSONObject.fromObject(data);
        ShopGrade dto = (ShopGrade) JSONObject.toBean(content, ShopGrade.class);
        dto.setLastModify(new Date());
        dto.setDisabled("0");
        shopGradeMapper.insertSelective(dto);
        head.setRetCode(ErrorCode.SUCCESS);
        return new ResultObject(head);
    }

    /**
     * 
     *@description 更新
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-5-26
     *@param data
     *@return
     */
    public Object updateShopGrade(Object data) {
        HeadObject head = new HeadObject();
        JSONObject content = JSONObject.fromObject(data);
        ShopGrade dto = (ShopGrade) JSONObject.toBean(content, ShopGrade.class);
        dto.setLastModify(new Date());
        shopGradeMapper.updateByPrimaryKeySelective(dto);
        head.setRetCode(ErrorCode.SUCCESS);
        return new ResultObject(head);
    }
    
    /**
     * 
     *@description 批量删除
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-5-26
     *@param data
     *@return
     */
    public Object deleteShopGrade(Object data){
        HeadObject head = new HeadObject();
         try{
             Integer [] shopGradeIds =  (Integer[]) data;
             //先判断是否存在店铺使用该等级
             boolean flag = false; //批量删除时，是否有部分有关联数据
             List<CompanyWithBLOBs> company = null;
             for(int i=0;i<shopGradeIds.length;i++){
                 company = companyMapper.findCompanyByGradeId(new Integer[]{shopGradeIds[i]});
                 if((company==null || company.size()==0)){ 
                     shopGradeMapper.deleteByPrimaryKey(Long.valueOf(shopGradeIds[i]));
                 } else {
                    flag=true;
                 }
             }
             if(flag){
               head.setRetCode(ErrorCode.IS_EXIST_CASCADE);
             }else{
                 head.setRetCode(ErrorCode.SUCCESS);
             }
             
         }catch(Exception e){    
          e.printStackTrace();
             head.setRetCode(ErrorCode.FAILURE);
         }       
         return new ResultObject(head);
  }

}
