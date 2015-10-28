

package com.cnit.yoyo.rmi.activity.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.activity.ActivityFullReduceGoodsShipMapper;
import com.cnit.yoyo.dao.activity.ActivityFullReduceMapper;
import com.cnit.yoyo.dao.activity.ActivityRulesMapper;
import com.cnit.yoyo.dao.activity.CouponsApplyBrandMapper;
import com.cnit.yoyo.dao.activity.CouponsMapper;
import com.cnit.yoyo.dao.activity.CouponsPicShipMapper;
import com.cnit.yoyo.dao.activity.SalesRuleGoodsMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.exception.BusinessException;
import com.cnit.yoyo.model.activity.ActivityFullReduce;
import com.cnit.yoyo.model.activity.ActivityFullReduceGoodsShip;
import com.cnit.yoyo.model.activity.ActivityRules;
import com.cnit.yoyo.model.activity.Coupons;
import com.cnit.yoyo.model.activity.CouponsApplyBrand;
import com.cnit.yoyo.model.activity.CouponsExample;
import com.cnit.yoyo.model.activity.CouponsPicShip;
import com.cnit.yoyo.model.activity.SalesRuleGoodsWithBLOBs;
import com.cnit.yoyo.model.activity.dto.CouponsApplyBrandDTO;
import com.cnit.yoyo.model.activity.dto.CouponsDTO;
import com.cnit.yoyo.model.activity.dto.FullReduceDTO;
import com.cnit.yoyo.model.member.dto.RolesDTO;
import com.cnit.yoyo.util.JsonDateValueProcessor;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @Description: 满减活动
 */
@Service("activityRulesService")
public class ActivityRulesService {
	public static final Logger log = LoggerFactory.getLogger(ActivityRulesService.class);
	
	@Autowired
	private ActivityRulesMapper activityRulesMapper;
	@Autowired
	private ActivityFullReduceMapper activityFullReduceMapper;
	@Autowired
	private ActivityFullReduceGoodsShipMapper activityFullReduceGoodsShipMapper;
	
	/**
	 * 
	 *@description 获取活动数据，带分页
	 *@detail <方法详细描述>
	 *@param data
	 *@return
	 */
	public Object findFullReduceListPage(Object data){
        HeadObject head = new HeadObject();
        ResultPage<FullReduceDTO> page = null;
        JSONObject json = null;
        try{
            JSONObject content = JSONObject.fromObject(data);
            FullReduceDTO dto = (FullReduceDTO) JSONObject.toBean(content,FullReduceDTO.class);
            PageHelper.startPage(content.optInt("pageIndex"), content.optInt("pageSize"));
            
            page = new ResultPage<FullReduceDTO>(this.activityFullReduceMapper.selectFullReduceList(dto));
            JsonConfig jsonConfig = new JsonConfig();  
            jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
            json = JSONObject.fromObject(page,jsonConfig);
            head.setRetCode(ErrorCode.SUCCESS);
        }catch(Exception e){    
            e.printStackTrace();
            head.setRetCode(ErrorCode.FAILURE);
        }       
        return new ResultObject(head, json);
   }
	
	/**
	 * 
	 *@description 获取活动数据，带分页
	 *@detail <方法详细描述>
	 *@param data
	 *@return
	 */
	public Object findFullDiscountListPage(Object data){
        HeadObject head = new HeadObject();
        ResultPage<FullReduceDTO> page = null;
        JSONObject json = null;
        try{
            JSONObject content = JSONObject.fromObject(data);
            PageHelper.startPage(content.optInt("pageIndex"), content.optInt("pageSize"));
            
            page = new ResultPage<FullReduceDTO>(this.activityFullReduceMapper.selectFullDiscountList());
            JsonConfig jsonConfig = new JsonConfig();  
            jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
            json = JSONObject.fromObject(page,jsonConfig);
            head.setRetCode(ErrorCode.SUCCESS);
        }catch(Exception e){    
            e.printStackTrace();
            head.setRetCode(ErrorCode.FAILURE);
        }       
        return new ResultObject(head, json);
   }
	
	/**
	 * 获取编辑的数据
	 * @param data
	 * @return
	 */
	public Object editFullReduce(Object data){
        HeadObject head = new HeadObject();
        FullReduceDTO fullReduce = null;
        JSONObject json = null;
        try{
        	long actId = (Long)data;
        	fullReduce = this.activityFullReduceMapper.selectFullReduceListById(actId);
        	JsonConfig jsonConfig = new JsonConfig();  
            jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
            json = JSONObject.fromObject(fullReduce,jsonConfig);
            head.setRetCode(ErrorCode.SUCCESS);
        }catch(Exception e){    
            e.printStackTrace();
            head.setRetCode(ErrorCode.FAILURE);
        }       
        return new ResultObject(head, json);
   }
	
	/**
	 * 根据活动ID获取关联的商品ID
	 * @param data
	 * @return
	 */
	public Object getGoodsIdsByActId(Object data) {
		HeadObject head = new HeadObject();
		List<Integer> goodsIds = null;
        try{
        	long actId = (Long)data;
        	goodsIds = this.activityFullReduceGoodsShipMapper.selectByActId(actId);
            head.setRetCode(ErrorCode.SUCCESS);
        }catch(Exception e){    
            e.printStackTrace();
            head.setRetCode(ErrorCode.FAILURE);
        }       
        return new ResultObject(head, goodsIds);
	}
   

	/**
	 * 
	 * @Description:保存活动
	 */
	public Object saveActivity(Object data) throws BusinessException {
		HeadObject head = new HeadObject();
		try {
			JSONObject content = JSONObject.fromObject(data);
			FullReduceDTO dto = (FullReduceDTO) JSONObject.toBean(content,FullReduceDTO.class);
			
			//1.先添加商品规则
			ActivityRules rule = new ActivityRules();
			rule.setRuleName(dto.getName());
			rule.setConditions(dto.getConditions());
			rule.setCreateTime(new Date());
			activityRulesMapper.insertSelective(rule);
			
			ActivityFullReduce activity = new ActivityFullReduce();
			activity.setName(dto.getName());
			activity.setRuleId(rule.getRuleId());
			activity.setCompanyId(dto.getCompanyId());     
			activity.setStoreId(dto.getStoreId());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		    Date fromDate = sdf.parse(dto.getFromTimeStr()); 
		    Date toDate = sdf.parse(dto.getToTimeStr());
			activity.setStartTime(fromDate);
			activity.setEndTime(toDate);
			activity.setIssueType("1");
			activity.setJoinTimes(dto.getJoinTimes());
			activity.setUsePlatform(dto.getUsePlatform());
			activity.setMemberLvIds(dto.getMemberLvIds());
			activity.setActivityType(dto.getActivityType());
			activityFullReduceMapper.insertSelective(activity);
			
			int[] goodsIds = dto.getGoodsId();
			for(int i=0;i<goodsIds.length;i++) {
				int goodsId = goodsIds[i];
				ActivityFullReduceGoodsShip ship = new ActivityFullReduceGoodsShip();
				ship.setActId(activity.getActId());
				ship.setGoodsId(goodsId);
				activityFullReduceGoodsShipMapper.insertSelective(ship);
			}
			
			head.setRetCode(ErrorCode.SUCCESS);
			
		} catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
			throw new BusinessException(ErrorCode.SPE001);
		}
		return new ResultObject(head);
	}
	
	/**
	 * 
	 * @Description:更新活动
	 */
	public Object updateActivity(Object data) throws BusinessException {
		HeadObject head = new HeadObject();
		try {
			JSONObject content = JSONObject.fromObject(data);
			FullReduceDTO dto = (FullReduceDTO) JSONObject.toBean(content,FullReduceDTO.class);
			
			//1.先添加商品规则
			ActivityRules rule = new ActivityRules();
			rule.setRuleId(dto.getRuleId());
			rule.setRuleName(dto.getName());
			rule.setConditions(dto.getConditions());
			activityRulesMapper.updateByPrimaryKeySelective(rule);
			
			ActivityFullReduce activity = new ActivityFullReduce();
			activity.setActId(dto.getActId());
			activity.setName(dto.getName());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		    Date fromDate = sdf.parse(dto.getFromTimeStr()); 
		    Date toDate = sdf.parse(dto.getToTimeStr());
			activity.setStartTime(fromDate);
			activity.setEndTime(toDate);
			activity.setJoinTimes(dto.getJoinTimes());
			activity.setUsePlatform(dto.getUsePlatform());
			activity.setMemberLvIds(dto.getMemberLvIds());
			activity.setActivityType(dto.getActivityType());
			activityFullReduceMapper.updateByPrimaryKeySelective(activity);
			
			activityFullReduceGoodsShipMapper.deleteByActId(activity.getActId());
			int[] goodsIds = dto.getGoodsId();
			for(int i=0;i<goodsIds.length;i++) {
				int goodsId = goodsIds[i];
				ActivityFullReduceGoodsShip ship = new ActivityFullReduceGoodsShip();
				ship.setActId(activity.getActId());
				ship.setGoodsId(goodsId);
				activityFullReduceGoodsShipMapper.insertSelective(ship);
			}
			
			head.setRetCode(ErrorCode.SUCCESS);
			
		} catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
			throw new BusinessException(ErrorCode.SPE001);
		}
		return new ResultObject(head);
	}
	
	/**
	 * 
	 * @Description:删除活动
	 */
	public Object deleteActivity(Object data) throws BusinessException {
		HeadObject head = new HeadObject();
		try {
			JSONObject content = JSONObject.fromObject(data);
			int ruleId = content.optInt("ruleId");
			long actId = content.optLong("actId");
			//1.先删除商品规则
			activityRulesMapper.deleteByPrimaryKey(ruleId);
			
			activityFullReduceGoodsShipMapper.deleteByActId(actId);
			
			activityFullReduceMapper.deleteByPrimaryKey(actId);
			
			head.setRetCode(ErrorCode.SUCCESS);
			
		} catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
			throw new BusinessException(ErrorCode.SPE001);
		}
		return new ResultObject(head);
	}
	
}