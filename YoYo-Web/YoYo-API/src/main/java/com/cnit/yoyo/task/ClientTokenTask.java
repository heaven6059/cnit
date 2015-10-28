package com.cnit.yoyo.task;  

import java.util.List;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.app.Ttoken;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;

/**
 * 删除token线程
 * @Author:yangyi  
 * @Date:2015年7月20日  
 * @Version:1.0.0
 */
@Component("clientTokenTask")
public class ClientTokenTask {
	public static final Logger log = LoggerFactory.getLogger(ClientTokenTask.class);
	@Autowired
	private RemoteService  otherService;
	
	public void deleteToken(){
		log.info("删除token线程开始>>>>>");
		try {
			//查询所有的token记录
			HeadObject headObject = CommonHeadUtil.geneHeadObject(null, "99031310-07");
			ResultObject resultObject=otherService.doService(new RequestObject(headObject, "1"));
			//返回结果
			if (ErrorCode.SUCCESS.equalsIgnoreCase(resultObject.getRetCode())) {
				List<Ttoken> list=(List<Ttoken>) resultObject.getContent();
				if(list != null && list.size() > 0){
					log.info("删除已经处理完成的token记录:"+list.toString());
					//删除所有的token记录
					HeadObject headObject1 = CommonHeadUtil.geneHeadObject(null, "99031310-08");
					otherService.doService(new RequestObject(headObject1,list));
				}else{
					log.info("没有查询到已经处理完成的token记录");
				}
			}
		log.info("删除token线程结束<<<<<");
		} catch (Exception e) {
			// TODO Auto-generated catch block  
			e.printStackTrace();
		}
	}
}
