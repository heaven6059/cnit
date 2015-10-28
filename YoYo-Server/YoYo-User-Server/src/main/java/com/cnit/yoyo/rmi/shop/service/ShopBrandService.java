package com.cnit.yoyo.rmi.shop.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.dao.shop.ShopBrandMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.goods.Brand;
import com.cnit.yoyo.model.goods.GoodsCatWithBLOBs;
import com.cnit.yoyo.model.goods.dto.ShopBrandBLOBsWithLabel;
import com.cnit.yoyo.model.shop.ShopBrandKey;
import com.cnit.yoyo.model.shop.ShopBrandWithBLOBs;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

/**
 * 店铺品牌关系表
 * 
 * @ClassName: ShopBrandService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author xiaox
 * @date 2015-4-18 下午4:25:43
 */
@Service("shopBrandService")
public class ShopBrandService {

	@Autowired
	private ShopBrandMapper shopBrandMapper;
	
	
    @Autowired
    private RemoteService itemService;

	public void deleteByPrimaryKeys(Object data) {

		Integer[] ids = (Integer[]) data;
		shopBrandMapper.deleteByPrimaryKeys(ids);

	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
    public Object findBrandBusinessList(Object data) {
	    ResultPage<Brand> page = null;
	    JSONObject content = JSONObject.fromObject(data);
        PageHelper.startPage(content.getInt("pageIndex"), content.getInt("pageSize"));
        ShopBrandWithBLOBs shopBrand = (ShopBrandWithBLOBs) JSONObject.toBean(content.getJSONObject("shopBrand"), ShopBrandWithBLOBs.class);
        page =new ResultPage(shopBrandMapper.findBrandBusinessList(shopBrand));
		return page;

	}
	
	public Object findBrandBusinessByStatus(Object data) {

		
		return  shopBrandMapper.findBrandBusinessByStatus((String)data);

	}
	
	
	/** 
	* @Title: insertBrandBusiness 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author Harder-Zhao
	* @param @param data
	* @param @return    设定文件 
	* @return Object    返回类型 
	* @throws 
	*/
	public Object insertBrandBusiness(Object data) {
		HeadObject head = new HeadObject();
		try {
			JSONObject content = JSONObject.fromObject(data);
			ShopBrandWithBLOBs shopBrand = (ShopBrandWithBLOBs) JSONObject.toBean(content, ShopBrandWithBLOBs.class);
			 ResultObject resultObject =( ResultObject)judgeCheck(shopBrand);
			 if(resultObject.getHead().getRetCode().equals(ErrorCode.IS_EXIST) || resultObject.getHead().getRetCode().equals(ErrorCode.IS_EXIST_MORE_APPLY)){
			     return resultObject;
			 }
			shopBrandMapper.insertSelective(shopBrand);
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head);
	}
	
	
	/** 
	* @Title: updateBrandBusiness 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author Harder-Zhao
	* @param @param data
	* @param @return    设定文件 
	* @return Object    返回类型 
	* @throws 
	*/
	public Object updateBrandBusiness(Object data) {
		HeadObject head = new HeadObject();
		try {
			JSONObject content = JSONObject.fromObject(data);
			ShopBrandWithBLOBs shopBrand = (ShopBrandWithBLOBs) JSONObject.toBean(content, ShopBrandWithBLOBs.class);
			 ResultObject resultObject =( ResultObject)judgeCheck(shopBrand);
             if(resultObject.getHead().getRetCode().equals(ErrorCode.IS_EXIST)|| resultObject.getHead().getRetCode().equals(ErrorCode.IS_EXIST_MORE_APPLY)){
                 return resultObject;
             }
			shopBrandMapper.updateByPrimaryKeySelective(shopBrand);
			//判断是否是新品牌申请，如果是，则添加到商品品牌表
			if("1".equals(shopBrand.getStatus()) && "0".equals(shopBrand.getType())){
			    HeadObject headObject =  CommonHeadUtil.geneHeadObject("categoryService.findCateById");
			    Brand brand = new Brand();
			    Map<String,Object> dataMap = new HashMap<String, Object>();
			    BeanUtils.copyProperties(shopBrand, brand);
			    dataMap.put("brand", brand);
			    dataMap.put("goodCateIds", new Integer[]{shopBrand.getStoreCat()});
			    //根据分类查identifiers
			    ResultObject result =  (ResultObject) itemService.doServiceByServer(new RequestObject(headObject,shopBrand.getStoreCat()));
			    JSONObject goodsCate=JSONObject.fromObject(result.getContent());
			    dataMap.put("identifiers", new String[]{goodsCate.getString("identifier")});
			    if(goodsCate.getString("identifier").equals("999")){ //整车
			        brand.setBrandType(1);
			    }else{
			        brand.setBrandType(0);
			    }
			    headObject = CommonHeadUtil.geneHeadObject("brandService.insertBrand");
		        itemService.doServiceByServer(new RequestObject(headObject,dataMap));
			}
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head);
	}
	
	
	/** 
	* @Title: deleteBrandBusiness 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author Harder-Zhao
	* @param @param data
	* @param @return    设定文件 
	* @return Object    返回类型 
	* @throws 
	*/
	public Object deleteBrandBusiness(Object data) {
		HeadObject head = new HeadObject();
		try {
			ShopBrandKey shopBrandKey = (ShopBrandKey) data;
			shopBrandMapper.deleteByPrimaryKey(shopBrandKey);
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head);
	}
	
	
	/** 
	* @Title: markLabelForBrandByIds 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author Harder-Zhao
	* @param @param data
	* @param @return    设定文件 
	* @return Object    返回类型 
	* @throws 
	*/
	public Object markLabelForBrandById(Object data) {
		HeadObject head = new HeadObject();
		try {
			JSONObject content = JSONObject.fromObject(data);
			Map<String, Object> dataMap = new HashMap<String, Object>();
			//content.getString("xxx")取出来的是[x,x,x]类型的数据，需要去掉中括号[]
			if("null".equals(content.getString("labelIds"))){
				dataMap.put("labelIds", null);
			}else{
				dataMap.put("labelIds", content.getString("labelIds").substring(1, content.getString("labelIds").length()-1));
			}
			String[] str = content.getString("brandIds").substring(1, content.getString("brandIds").length()-1).split(",");
			for(String id : str){
				dataMap.put("brandIds", id);
				shopBrandMapper.updateLabelBrandById(dataMap);
			}
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head);
	}
	
	
	/**
	 * 
	 *@description 插入已有品牌的申请
	 *@detail <方法详细描述>
	 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
	 *@version 1.0.0
	 *@data 2015-6-3
	 *@param data
	 *@return
	 */
	@SuppressWarnings("unchecked")
    public Object insertOldBrandBusiness(Object data) {
        HeadObject head = new HeadObject();
        Map dataMap = (Map) data;
        boolean flag = false;
        Integer[] brandIds=(Integer[]) dataMap.get("brandIds");
        List<Integer> tempIds = new ArrayList<Integer>();  //存放已经存在的品牌
        List<Integer> newIds = new ArrayList<Integer>(); 
        int count= shopBrandMapper.judgeOldCheck(brandIds);  //是否重复申请
        if(count!=0){
            head.setRetCode(ErrorCode.IS_EXIST);
            return new ResultObject(head);
        }
        
        //1.第一步，获取店铺已有品牌，需要判断该品牌是否已经被该店铺进行使用
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("companyId", dataMap.get("companyId"));
        map.put("status", "1"); //已经通过审核的
        List<ShopBrandWithBLOBs> shopBrand=(List<ShopBrandWithBLOBs>) findBrandStatus(map);
        if(shopBrand!=null && shopBrand.size()>0){
            for(int i=0;i<brandIds.length;i++){
                newIds.add(brandIds[i]);
                for(int j=0;j<shopBrand.size();j++){
                    if(brandIds[i]==shopBrand.get(j).getBrandId()){   //申请的品牌已经在使用
                        tempIds.add(brandIds[i]);
                        flag = true;
                    }
                }
            }
        }
        if(flag){ //存在已经在使用的
            newIds.removeAll(tempIds);
            brandIds=(Integer[]) newIds.toArray();
            head.setRetCode(ErrorCode.IS_EXIST_CASCADE);
        }else{
            head.setRetCode(ErrorCode.SUCCESS);
        }
        //2.查找品牌存放到店铺品牌中
        HeadObject headObject = CommonHeadUtil.geneHeadObject("brandService.selectByIds");
        List<Brand> brandList=(List<Brand>) itemService.doServiceByServer(new RequestObject(headObject,brandIds));
        //3.批量插入到申请表
        List<ShopBrandWithBLOBs> result = new ArrayList<ShopBrandWithBLOBs>();
        ShopBrandWithBLOBs temp = null;
        Brand brand = null;
        for(int i=0;i<brandList.size();i++){
            brand = brandList.get(i);
            temp = new ShopBrandWithBLOBs();
            temp.setBrandAptitude(brand.getBrandAptitude());
            temp.setBrandDesc(brand.getBrandDesc());
            temp.setBrandId(brand.getBrandId());
            temp.setBrandKeywords(brand.getBrandKeywords());
            temp.setBrandLogo(brand.getBrandLogo());
            temp.setBrandName(brand.getBrandName());
            temp.setBrandUrl(brand.getBrandUrl());
            temp.setCompanyId(Long.valueOf(dataMap.get("companyId").toString()));
            result.add(temp);
        }
        shopBrandMapper.insertBatchSelective(result);
        return new ResultObject(head);
    }
	
	/**
	 * 
	 *@description 通过店铺id与状态查询店铺的品牌
	 *@detail <方法详细描述>
	 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
	 *@version 1.0.0
	 *@data 2015-6-3
	 *@param data
	 *@return
	 */
	public Object findBrandStatus(Object data){
	    Map map = (Map) data;
	    return shopBrandMapper.findBrandStatus(map);
	}
	
	
	/**
	 * 
	 *@description 撤回申请
	 *@detail <方法详细描述>
	 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
	 *@version 1.0.0
	 *@data 2015-6-20
	 *@param data
	 *@return
	 */
	public Object cancelBrandBusiness(Object data){
	    JSONObject content = JSONObject.fromObject(data);
        ShopBrandWithBLOBs shopBrand = (ShopBrandWithBLOBs) JSONObject.toBean(content, ShopBrandWithBLOBs.class);
        shopBrandMapper.cancelBrandBusiness(shopBrand.getCompanyBrandId());
        HeadObject head = new HeadObject();
        head.setRetCode(ErrorCode.SUCCESS);
        return new ResultObject(head);
    }
	
	/**
	 * 
	 *@description 判断店铺是否是重复申请
	 *@detail <方法详细描述>
	 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
	 *@version 1.0.0
	 *@data 2015-6-20
	 *@param data
	 *@return
	 */
	public Object judgeCheck(Object data){
	    HeadObject head = new HeadObject();
        JSONObject content = JSONObject.fromObject(data);
        ShopBrandWithBLOBs shopBrand = (ShopBrandWithBLOBs) JSONObject.toBean(content, ShopBrandWithBLOBs.class);
        int count = 0;
        count=shopBrandMapper.judgeCheck(shopBrand);
        //2.查找品牌名称是否在品牌中
        HeadObject headObject = CommonHeadUtil.geneHeadObject("brandService.findBrandApplyByName");
        Brand brand = new Brand();
        brand.setBrandName(shopBrand.getBrandName());
        if(shopBrand.getBrandId()!=null ){
            brand.setBrandId(shopBrand.getBrandId());
        }
        ResultObject result =  (ResultObject) itemService.doServiceByServer(new RequestObject(headObject,brand));
        if(result!=null && result.getHead().getRetCode().equals(ErrorCode.IS_EXIST)){
            head.setRetCode(ErrorCode.IS_EXIST_MORE_APPLY);
        }else{
            if(count!=0){
                head.setRetCode(ErrorCode.IS_EXIST);
            }else{
                head.setRetCode(ErrorCode.SUCCESS);
            }
        }
        
        return new ResultObject(head);
    }
	
    
	
}
