package com.cnit.yoyo.rmi.good.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.goods.GoodsCatMapper;
import com.cnit.yoyo.dao.shop.TypeBrandShipMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.goods.dto.TypeBrandShipDTO;
import com.cnit.yoyo.model.shop.TypeBrandShip;

/**
 * 
 *@description 商品品牌与商品分类的关系service
 *@detail <功能详细描述>
 *@version 1.0.0
 */
@Service("typeBrandShipService")
public class TypeBrandShipService {
  
    
    @Autowired 
    private TypeBrandShipMapper  typeBrandShipMapper;
    @Autowired
    GoodsCatMapper goodsCatMapper;
    
    /**
     * 
     * @Title: findBrandList 
     * @Description: 通过品牌id,查找商品品牌与分类的关系
     * @param @param data
     * @param @return    设定文件 
     * @author xiaox
     * @date 2015-3-18 下午2:30:50
     */
	public Object findTypeBrandList(Object data){
    	 HeadObject head = new HeadObject();
    	 List<TypeBrandShipDTO> list = null;
         try{
             list =  typeBrandShipMapper.findTypeBrandList((Integer)data);
             head.setRetCode(ErrorCode.SUCCESS);
         }catch(Exception e){    
        	 e.printStackTrace();
             head.setRetCode(ErrorCode.FAILURE);
         }       
         return new ResultObject(head, list);
    }
	
	/**
	 * 
	 * @Description: 通过分类id查找品牌 （品牌是继承的） 
	 * @param @param data
	 * @param @return
	 * @author xiaox
	 * @date 2015-3-28 下午2:56:08
	 */
	public Object findTypeBrandByCateId(Object data){
   	 HeadObject head = new HeadObject();
   	 List<TypeBrandShipDTO> list = null;
        try{
         // 获取指定分类id的所有上级分类id
            List<Integer> parentIds = goodsCatMapper.getParentIds((Integer) data);
            list =  typeBrandShipMapper.findTypeBrandByCateId(parentIds);
            head.setRetCode(ErrorCode.SUCCESS);
        }catch(Exception e){    
       	 e.printStackTrace();
            head.setRetCode(ErrorCode.FAILURE);
        }       
        return new ResultObject(head, list);
   }
	
	
	/**
     * 
     * @Description: 通过分类类型查找品牌  
     * @param @param data
     * @param @return
     * @author xiaox
     * @date 2015-3-28 下午2:56:08
     */
    public Object findShip(Object data){
     HeadObject head = new HeadObject();
     List<TypeBrandShipDTO> list = null;
        try{
            TypeBrandShip temp = new TypeBrandShip();
            if(data != null){
            	temp.setIdentifier(String.valueOf(data));
            }
            list =  typeBrandShipMapper.findShip(temp);
            head.setRetCode(ErrorCode.SUCCESS);
        }catch(Exception e){    
         e.printStackTrace();
            head.setRetCode(ErrorCode.FAILURE);
        }       
        return new ResultObject(head, list);
   }
    
   
}
