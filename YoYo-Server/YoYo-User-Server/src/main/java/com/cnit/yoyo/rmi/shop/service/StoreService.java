package com.cnit.yoyo.rmi.shop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.shop.StoreMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.car.CarYear;
import com.cnit.yoyo.model.goods.Store;
import com.cnit.yoyo.model.goods.dto.StoreDTO;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

/**
 * 
 *@description 分店信息
 *@detail <功能详细描述>
 *@author <a href="liguanghua@chinacnit.com">李光华</a>
 *@version 1.0.0
 */
@Service("storeService")
public class StoreService {
	private static final Log log = LogFactory.getLog(RoleService.class);
    @Autowired 
    private StoreMapper  storeMapper;
    
    public Object updateStore(Object data){
        HeadObject head = new HeadObject();
        try{
            JSONObject content = JSONObject.fromObject(data);
            Store store = (Store) JSONObject.toBean(content, Store.class);
            storeMapper.updateByPrimaryKeySelective(store);
            head.setRetCode(ErrorCode.SUCCESS);
        }catch(Exception e){          
            head.setRetCode(ErrorCode.FAILURE);
        }       
        return head;
    }
    
    
    //保存分店
    public Object saveStore(Object data){
    	  HeadObject head = new HeadObject();
          try{
              JSONObject content = JSONObject.fromObject(data);
              Store store = (Store) JSONObject.toBean(content, Store.class);
              store.setLimitStore("0");
              if(store.getStoreId() != null && store.getStoreId() != 0 ){ //更新
            	   storeMapper.updateByPrimaryKeySelective(store);
              }else{
            	  storeMapper.insertSelective(store);
              }
              head.setRetCode(ErrorCode.SUCCESS);
          }catch(Exception e){          
              head.setRetCode(ErrorCode.FAILURE);
          }       
          return new ResultObject(head);
    }
    
    
    //删除分店
    public Object deleteStore(Object data){
    	  HeadObject head = new HeadObject();
          try{
              storeMapper.deleteByPrimaryKey((Long)data);
              head.setRetCode(ErrorCode.SUCCESS);
          }catch(Exception e){          
              head.setRetCode(ErrorCode.FAILURE);
          }       
          return new ResultObject(head);
    }
    
    /**
     * 
     * @Title: findShopByCompanyId 
     * @Description: 查找分店，带分页
     * @param @param data
     * @param @return    设定文件 
     * @author xiaox
     * @date 2015-3-18 下午2:30:50
     */
    @SuppressWarnings({ "unchecked" })
    public Object findShopByCompanyId(Object data){
    	 HeadObject head = new HeadObject();
    	 ResultPage<Store> page = null;
         try{
             JSONObject content = JSONObject.fromObject(data);
             PageHelper.startPage(content.getInt("pageIndex"), content.getInt("pageSize"));
			 page = new ResultPage( storeMapper.findShopByCompanyId(content.getLong("companyId")));
             head.setRetCode(ErrorCode.SUCCESS);
         }catch(Exception e){          
             head.setRetCode(ErrorCode.FAILURE);
         }       
         return new ResultObject(head, JSONObject.fromObject(page));
    }
    
    /**
     * 
     *@description 通过集团id查找分店，不带分页
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-5-15
     *@param data
     *@return
     */
    public Object findShopByCId(Object data){
        
        return storeMapper.findShopByCompanyId(Long.valueOf(data.toString()));
            
   }
    
    /**
     * 通过集团id查找分店，不带分页
     * @param @return    设定文件 
     * @author ssd
     */
	public Object findSelect(Object data){
    	 HeadObject head = new HeadObject();
    	 List<Store> list = null;
    	 long companyId = 0L;
    	 if(null != data) {
    		 companyId = (Long)data;
    	 }
    	 
         try{
             list =  storeMapper.findSelect(companyId);
             head.setRetCode(ErrorCode.SUCCESS);
         }catch(Exception e){    
        	 e.printStackTrace();
             head.setRetCode(ErrorCode.FAILURE);
         }       
         return new ResultObject(head, list);
    }
    
    /**
     * 
     * @Title: selectByStoreIdList 
     * @Description: 根据
     * @param @param data
     * @param @return    设定文件 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a>
     * @date 2015-4-18 
     */
    public Object selectByStoreIdList(Object data){
    	HeadObject head = new HeadObject();
    	JSONArray jsonObject = null;
        try{
            JSONObject content = JSONObject.fromObject(data);
            List<Integer> storeIdList = (List<Integer>) JSONArray.toCollection(JSONArray.fromObject(content.get("storeIdList")), Integer.class);
            List<StoreDTO> storeList = storeMapper.selectByStoreIdList(storeIdList);
            jsonObject = JSONArray.fromObject(storeList);
            head.setRetCode(ErrorCode.SUCCESS);
        }catch(Exception e){          
            head.setRetCode(ErrorCode.FAILURE);
        }       
        return new ResultObject(head, jsonObject);
    }
    public Object selectByStoreIdList2(Object data){
    	JSONObject content = JSONObject.fromObject(data);
        List<Integer> storeIdList = (List<Integer>) JSONArray.toCollection(JSONArray.fromObject(content.get("storeIdList")), Integer.class);
        List<Store> storeList = storeMapper.selectByStoreIdList2(storeIdList);
        List<StoreDTO> storeDTOList = null;
        if(storeList!=null&&storeList.size()>=1){
        	storeDTOList = new ArrayList<StoreDTO>();
        	StoreDTO storeDTO = null;
        	for(int i =0 ;i< storeList.size();i++){
        		storeDTO = new StoreDTO();
        		storeDTO.setStoreId(storeList.get(i).getStoreId());
        		storeDTO.setStoreName(storeList.get(i).getStoreName());
        		storeDTO.setCompanyId(storeList.get(i).getCompanyId());
        		storeDTO.setAddr(storeList.get(i).getAddr());
        		storeDTO.setArea(storeList.get(i).getArea());
        		storeDTOList.add(storeDTO);
        	}
        	
        }
        return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONArray.fromObject(storeDTOList));
    }
    public Object selectByStoreIdList3(Object data){
        List<Store> storeList = storeMapper.selectByStoreIdList2((List<Integer>)data);
        return new ResultObject(new HeadObject(ErrorCode.SUCCESS), com.alibaba.fastjson.JSONArray.toJSON(storeList));
    }
    
    /**
     * @Title: findByAccountId 
     * @Description: TODO(根据accountId查询店铺对象) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-22 下午4:41:02 
     * @version 1.0.0 
     * @param @param data
     * @param @return    
     * @return Object    返回类型 
     * @throws
     */
    public Object findByAccountId(Object data){
        try{
            JSONObject jsonObject = JSONObject.fromObject(data);
            Integer accountId = jsonObject.getInt("accountId");
            List<Store> store = storeMapper.selectByAccountId(accountId);
            if(store!=null&&store.size()>=1){
            	jsonObject = JSONObject.fromObject(store.get(0));
            	return new ResultObject(new HeadObject(ErrorCode.SUCCESS), jsonObject);
            }else{
            	return new ResultObject(new HeadObject(ErrorCode.SUCCESS));
            }
            
        }catch(Exception e){          
            return new ResultObject(new HeadObject(ErrorCode.FAILURE));
        }       
    }
    
    public Object findByAccountId2(Object data){
    	List<Store> storeList = storeMapper.selectByAccountId((Integer)data);
    	Store store = null;
        if(storeList!=null&&storeList.size()>=1){
        	store = storeList.get(0);
        }
        return new ResultObject(new HeadObject(ErrorCode.SUCCESS),com.alibaba.fastjson.JSONObject.toJSON(store));
    }
    
    
    /**
     * 
     *@description 通过店铺id与分店id查找预警库存值
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-4-29
     *@param data
     *@return
     */
    public Object findStoreNumById(Object data){
        HeadObject head = new HeadObject();
        Integer num = null;
        try{
           JSONObject content = JSONObject.fromObject(data);
           num = storeMapper.findStoreNumById(content.getLong("companyId"),content.getLong("storeId"));
           head.setRetCode(ErrorCode.SUCCESS);
           
        }catch(Exception e){
           log.error(e.getMessage());
           head.setRetCode(ErrorCode.FAILURE);
        }
           
       return new ResultObject(head,num);
    }
    
    /**
     * 
     *@description 更新分店预警库存
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-4-29
     *@param data
     *@return
     */
    public Object updateStoreNum(Object data){
        HeadObject head = new HeadObject();
           try{
               JSONObject content = JSONObject.fromObject(data);
               storeMapper.updateStoreNum(content.getLong("companyId"),content.getLong("storeId"),content.getInt("storeNum"));
               head.setRetCode(ErrorCode.SUCCESS);
               
           }catch(Exception e){
               log.error(e.getMessage());
               head.setRetCode(ErrorCode.FAILURE);
           }
           
           return new ResultObject(head);
    }
    
    
    /**
     * @description <b>查询店铺以及其集团信息</b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015-8-11
     * @param data
     * @return
     * Object
    */
    public Object findShopAndCompany(Object data){
        HeadObject head = new HeadObject();
        Store store = null;
        try{
        	store = storeMapper.selectShopAndCompany((Map<String, Object>) data);
	        head.setRetCode(ErrorCode.SUCCESS);
        }catch(Exception e){
        	log.error(e.getMessage());
        	head.setRetCode(ErrorCode.FAILURE);
        }
        return new ResultObject(head,store);
    }
}
