package com.cnit.yoyo.rmi.car.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.car.CarMaintainAccessoryMapper;
import com.cnit.yoyo.dao.car.MaintainDefaultGoodsMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.car.CarMaintainAccessory;
import com.cnit.yoyo.model.car.dto.CarMaintainAccessoryDTO;

/**
 * 
 * @ClassName: CarMaintainService 
 * @Description: 保养配件商品service
 * @author xiaox
 * @date 2015-3-26 下午4:20:04
 */
@Service("carMaintainAccessoryService")
public class CarMaintainAccessoryService {
    @Autowired 
    private CarMaintainAccessoryMapper  carMaintainAccessoryMapper;
    @Autowired 
    private MaintainDefaultGoodsMapper  maintainDefaultGoodsMapper;
    
    

    /**
     * 
     *@description 保存保养配件商品
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-5-19
     *@param data
     *@return
     */
    @SuppressWarnings("unused")
    public Object saveMaintainAccessory(Object data){
        HeadObject head = new HeadObject();
        List<CarMaintainAccessory> accList = null;
        List<CarMaintainAccessory> deleteList = new ArrayList<CarMaintainAccessory>();  //需要删除的
        List<Integer> deleteIds = new ArrayList<Integer>();  //需要删除的保养配件表的自增长的id
        JSONObject content = JSONObject.fromObject(data);
        CarMaintainAccessoryDTO dto = (CarMaintainAccessoryDTO) JSONObject.toBean(content,CarMaintainAccessoryDTO.class);
        List<Integer> accIds=new ArrayList<Integer>(Arrays.asList(dto.getAccIds())); //需要新增的
        List<Integer> retainAccIds = new ArrayList<Integer>();  //需要保留的
        List<Integer> oldAccIds = new ArrayList<Integer>();  //该分类已经存在的所有配件
       
        
        //先查询已经存在的，比较是否相同
        accList=carMaintainAccessoryMapper.findAccessoryByCatId(dto.getCategoryId());
        
        if(accList!=null && accList.size()>0){//更新
            for(CarMaintainAccessory acc : accList){
                oldAccIds.add(acc.getAccId());
                for(int i=0;i<accIds.size();i++){
                    if(acc.getAccId()==accIds.get(i)){
                        //accIds.remove(i);
                        retainAccIds.add(acc.getAccId());
                        break;
                    }else{ //需要删除的
                        deleteList.add(acc);
                    }
                }
            }
        }
        
        if(retainAccIds.size()>0){
            oldAccIds.removeAll(retainAccIds);   //去掉保留的，则剩下的就是需要删除的
            accIds.removeAll(retainAccIds);
        }
        
        //在删除前，需要先判断是否存在级联数据
        int count=0;
        if(deleteList.size()>0){
            for(CarMaintainAccessory acc : deleteList){ //先查到id
                deleteIds.add(acc.getId());
            }
            count=maintainDefaultGoodsMapper.findByAccIds(deleteIds);
        }
      
        if(count==0){
            //1.新增
            if(accIds!=null && accIds.size()>0){
                List<CarMaintainAccessory> list = new ArrayList<CarMaintainAccessory>();
                CarMaintainAccessory temp = null;
                for(Integer i:accIds){
                    temp = new CarMaintainAccessory();
                    temp.setCategoryId(dto.getCategoryId());
                    temp.setAccId(i);
                    temp.setModifyTime(new Date());
                    list.add(temp);
                }
                if(list.size()>0){
                    carMaintainAccessoryMapper.batchInsert(list); //批量插入
                }
            }
            
            //2.删除
            if(oldAccIds!=null && oldAccIds.size()>0){
                carMaintainAccessoryMapper.updateDisable(dto.getCategoryId(),oldAccIds);
            }
            head.setRetCode(ErrorCode.SUCCESS);
        }else{
            head.setRetCode(ErrorCode.IS_EXIST_CASCADE);
        }
        return new ResultObject(head);
   }
    
    
    
    
}
