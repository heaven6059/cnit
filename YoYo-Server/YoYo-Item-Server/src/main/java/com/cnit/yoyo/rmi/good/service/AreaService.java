
package com.cnit.yoyo.rmi.good.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.goods.AreaMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.goods.Area;

/**
 * @description 店铺service
 * @version 1.0.0
 */
@Service("areaService")
public class AreaService {
	private static final Log log = LogFactory.getLog(AreaService.class);
	 @Autowired
	 private AreaMapper areaMapper;
	 
	 public Object findByDeepAndPid(Object data){
	    	HeadObject head = new HeadObject();
	    	List<Area> list = null;
	    	try{
		    	JSONObject content = JSONObject.fromObject(data);
		    	Area area = (Area) JSONObject.toBean(content, Area.class);
		    	list=areaMapper.findByDeepAndPid(area);
		    	head.setRetCode(ErrorCode.SUCCESS);
	    	}catch(Exception e){
	    		log.error(e.getMessage());
	    		head.setRetCode(ErrorCode.FAILURE);
	    	}
	    	
	    	return new ResultObject(head, list);
	    }
	 
	 /**
	  * @Title:  findByPrimaryKey  
	  * @Description:  TODO(根据主键查询area对象)  
	  * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	  * @date 2015-5-11 下午6:20:13  
	  * @version 1.0.0 
	  * @param @param data
	  * @param @return
	  * @return Object  返回类型 
	  * @throws
	  */
	public Object findByPrimaryKey(Object data) {
		HeadObject head = new HeadObject();
		Area area = null;
		try {
			area = areaMapper.selectByPrimaryKey((Integer) data);
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e.getMessage());
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head, area);
	}
}
