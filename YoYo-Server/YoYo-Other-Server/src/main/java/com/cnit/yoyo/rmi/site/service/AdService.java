package com.cnit.yoyo.rmi.site.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.site.AdMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.dto.BaseQryDTO;
import com.cnit.yoyo.model.site.Ad;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

/**
 * @description <商城页面广告设置>
 * @detail <商城首页各个广告的相关设置。每个设置有一个唯一标识（名称）。
 * 页面上需要对某个广告设置时，根据标识即可引用该设置。>
 * @author <a href="jpzhou@cnit.com">周加平</a>
 * @version 1.0.0
 */
@Service("adService")
public class AdService {
    private static final Logger logger = LoggerFactory.getLogger(AdService.class);
    @Autowired
    private AdMapper AdMapper;

    /**
     * @description <根据名称查询广告设置>
     * @detail <根据名称查询广告设置>
     * @author <a href="jpzhou@cnit.com">周加平</a>
     * @version 1.0.0
     * @data 2015年7月1日
     * @param data
     *        广告名称
     * @return
     */
    public Object selectByName(Object data) {
        logger.info("start[AdService.selectByName]");
        HeadObject head = new HeadObject(ErrorCode.SUCCESS);
        Object content = null;
        try {
            content = AdMapper.selectByName(String.valueOf(data));
        } catch (Exception e) {
            logger.error("查询广告异常", e);
            head.setRetCode(ErrorCode.FAILURE);
        }
        logger.info("end[AdService.selectByName]");
        return new ResultObject(head, content);
    }
    
    /**
     * 
     *@description <查询所有广告设置>
     *@detail <将广告设置查询出来以供首页对各个广告进行设置>
     *@author <a href="jpzhou@cnit.com">周加平</a>
     *@version 1.0.0
     *@data 2015年7月1日
     *@param data
     *@return resultObject.content是一个以name为key，Ad对象为value的Map
     */
    public Object selectAllToMap(Object data) {
        logger.info("start[AdService.selectAll]");
        HeadObject head = new HeadObject(ErrorCode.SUCCESS);
        Map<String,Object> content = new HashMap<String,Object>();
        List<Ad> AdList = new ArrayList<>();
        
        BaseQryDTO dto = (BaseQryDTO)data;
		
        ResultPage<Ad> result=null;
		PageHelper.startPage(dto.getPage(),dto.getRows());
        try {
        	result = new ResultPage<Ad>(AdMapper.selectAll());
        } catch (Exception e) {
            logger.error("查询所有广告异常", e);
            head.setRetCode(ErrorCode.FAILURE);
        }
        logger.info("end[AdService.selectAll]");
        return new ResultObject(head, JSON.toJSON(result));
    }

    /**
     * 
     *@description <更新一条广告设置>
     *@detail <方法详细描述>
     *@author <a href="jpzhou@cnit.com">周加平</a>
     *@version 1.0.0
     *@data 2015年7月1日
     *@param data
     *@return
     */
    public Object update(Object data) {
        logger.info("start[AdService.update]");
        HeadObject head = new HeadObject(ErrorCode.SUCCESS);
        Ad ad = (Ad)data;
        System.out.println(JSON.toJSONString(ad));
        try{
            this.AdMapper.updateByPrimaryKeySelective(ad);
        }catch(Exception e){
            head.setRetCode(ErrorCode.FAILURE);
            logger.error("更新广告异常",e);
        }
        logger.info("end[AdService.update]");
        return new ResultObject(head);
    }
    /**
     * 
     *@description <删除一条广告设置>
     *@detail <方法详细描述>
     *@author <a href="jpzhou@cnit.com">周加平</a>
     *@version 1.0.0
     *@data 2015年7月1日
     *@param data
     *@return
     */
    public Object deleteByName(Object data) {
        logger.info("start[AdService.deleteByName]");
        HeadObject head = new HeadObject(ErrorCode.SUCCESS);
        try{
            this.AdMapper.insert((Ad)data);
        }catch(Exception e){
            head.setRetCode(ErrorCode.FAILURE);
            logger.error("删除广告异常",e);
        }
        logger.info("end[AdService.deleteByName]");
        return new ResultObject(head);
    }
    
    /**
     * @Title:  findAdById  
     * @Description:  根据ID查询广告
     * @author <a href="zjcai@chinacnit.com">蔡志杰</a> 
     * @date 2015-8-6 上午11:03:00  
     * @version 1.0.0 
     * @param @param data
     * @param @return
     * @return Object  返回类型 
     * @throws
     */
    public Object findAdById(Object data){
        logger.info("start[AdService.findAdById]");
        HeadObject head = new HeadObject(ErrorCode.SUCCESS);
        Object content = null;
        try {
            content = AdMapper.selectByPrimaryKey((Integer)data);
        } catch (Exception e) {
            logger.error("查询广告异常", e);
            head.setRetCode(ErrorCode.FAILURE);
        }
        logger.info("end[AdService.findAdById]");
    	return new ResultObject(head, content);
    }
    
    /**
     * 
     *@description <新建一条广告设置>
     *@detail <方法详细描述>
     *@author <a href="jpzhou@cnit.com">周加平</a>
     *@version 1.0.0
     *@data 2015年7月1日
     *@param data
     *@return
     */
    public Object insert(Object data) {
        logger.info("start[AdService.insert]");
        HeadObject head = new HeadObject(ErrorCode.SUCCESS);
        try{
            this.AdMapper.insertSelective((Ad)data);
        }catch(Exception e){
            head.setRetCode(ErrorCode.FAILURE);
            logger.error("插入广告异常",e);
        }
        logger.info("end[AdService.insert]");
        return new ResultObject(head);
    }
    
    @SuppressWarnings("unchecked")
	public Object deleteById(Object data) {
        logger.info("start[AdService.deleteByName]");
        HeadObject head = new HeadObject(ErrorCode.SUCCESS);
        String idStr = (String)data;
        List<Integer> ids = new ArrayList<Integer>();
        if(StringUtils.isNotBlank(idStr)){
        	String[] arr = idStr.split(",");
        	for (String id : arr) {
        		ids.add(Integer.valueOf(id));
			}
        }
        try{
            this.AdMapper.deleteBatch(ids);
        }catch(Exception e){
            head.setRetCode(ErrorCode.FAILURE);
            logger.error("删除广告异常",e);
        }
        logger.info("end[AdService.deleteByName]");
        return new ResultObject(head);
    }
}
