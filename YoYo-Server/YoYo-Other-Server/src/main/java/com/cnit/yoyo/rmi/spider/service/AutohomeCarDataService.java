package com.cnit.yoyo.rmi.spider.service;  

import java.util.List;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.autohome.model.AutohomeCarInfoDTO;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.spider.AutohomeCarInfoMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.QueryParamObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.app.Ttoken;
import com.cnit.yoyo.model.car.CarFactory;
import com.cnit.yoyo.model.spider.AutohomeCarInfo;
import com.cnit.yoyo.util.StringUtil;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;
/**
 * 汽车之家爬虫数据服务层
 * @Author:yangyi  
 * @Date:2015年8月6日  
 * @Version:1.0.0
 */
@Service("autohomeCarDataService")
public class AutohomeCarDataService {
	private static final Logger log = LoggerFactory.getLogger(AutohomeCarDataService.class);
	
	@Autowired
	private AutohomeCarInfoMapper autohomeCarInfoMapper;
	
	/**
	 * @Description:查询所有的爬虫相关数据
	 * @param data
	 * @return
	 */
	public Object queryAutohomeCarDataList(Object data){
		HeadObject head = new HeadObject();
		ResultPage<AutohomeCarInfoDTO> page = null;
        try{
        	JSONObject content = JSONObject.fromObject(data);
       	 	PageHelper.startPage(content.getInt("pageIndex"), content.getInt("pageSize"));
//       	 CarFactory dto = (CarFactory)JSONObject.toBean(content.getJSONObject("carFactory"), CarFactory.class);
       	 	page = new ResultPage<AutohomeCarInfoDTO>(autohomeCarInfoMapper.queryAutohomeCarDataList(null));
            head.setRetCode(ErrorCode.SUCCESS);
        }catch(Exception e){    
       	 e.printStackTrace();
            head.setRetCode(ErrorCode.FAILURE);
        }       
        return new ResultObject(head, page);
	}
	
	/**
	 * @Description:根据车型id查找车型对应的属性集合
	 * @param data
	 * @return
	 */
	public Object selectCarInfoIncludeCarAttr(Object data){
		HeadObject head = new HeadObject();
		AutohomeCarInfoDTO autohomeCarInfo=null;
		try{
			autohomeCarInfo=autohomeCarInfoMapper.selectCarInfoIncludeCarAttr((String)data);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch(Exception e){    
       	 	e.printStackTrace();
            head.setRetCode(ErrorCode.FAILURE);
        }
		return new ResultObject(head, autohomeCarInfo);
	}
	
}
