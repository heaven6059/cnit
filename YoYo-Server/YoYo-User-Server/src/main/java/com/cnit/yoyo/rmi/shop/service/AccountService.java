package com.cnit.yoyo.rmi.shop.service;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.member.ClerkMapper;
import com.cnit.yoyo.dao.member.PamAccountMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.PamAccount;

@Service("accountService")
public class AccountService {
    
    @Autowired
    PamAccountMapper accountMapper;
    @Autowired
    ClerkMapper clearksMapper;
    
    /**
     * 
     * @Title: findLoginName 
     * @Description:查找是否是会员或店员
     * @param @param data
     * @param @return    设定文件 
     * @author xiaox
     * @date 2015-3-20 下午5:25:59
     */
    public Object findLoginName(Object data){
        HeadObject head = new HeadObject();
        Long count = 0l;
        try{
            JSONObject content = JSONObject.fromObject(data);
            count = accountMapper.selectLoginname(content.getString("loginName"));  //会员id
            System.out.println("count==="+count);
            if(count!=null && count>0){ //已经存在
               if( clearksMapper.findStoreMemByMemid(count)>0){ //是会员且是店员
                   head.setRetCode(ErrorCode.FAILURE);
               }else{
                   head.setRetCode(ErrorCode.SUCCESS);
               }
            }else{
                if(count==null){
                    count=0l;
                }
                head.setRetCode(ErrorCode.SUCCESS);
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResultObject(head,count);
    }
    
    /**
     * @Title:  selectByAccountId  
     * @Description:  TODO(根据主键查询对象)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-6-1 下午3:00:10  
     * @version 1.0.0 
     * @param @param data
     * @param @return
     * @return Object  返回类型 
     * @throws
     */
    public Object selectByAccountId(Object data){
    	PamAccount pamAccount = accountMapper.selectByPrimaryKey((Integer)data);
    	return new ResultObject(new HeadObject(ErrorCode.SUCCESS), pamAccount);
    }
}
