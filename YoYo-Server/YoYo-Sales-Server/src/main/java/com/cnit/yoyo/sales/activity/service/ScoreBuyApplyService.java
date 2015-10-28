package com.cnit.yoyo.sales.activity.service;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.exception.BusinessException;
import com.cnit.yoyo.model.activity.Coupons;
import com.cnit.yoyo.model.activity.CouponsApplyBrand;
import com.cnit.yoyo.model.activity.CouponsPicShip;
import com.cnit.yoyo.model.activity.SalesRuleGoodsWithBLOBs;
import com.cnit.yoyo.model.activity.dto.CouponsDTO;
import com.cnit.yoyo.model.goods.GoodsCatWithBLOBs;
import com.cnit.yoyo.model.goods.GoodsRelated;
import com.cnit.yoyo.model.sales.activity.ScoreBuyActivity;
import com.cnit.yoyo.model.sales.activity.ScoreBuyApply;
import com.cnit.yoyo.model.sales.activity.ScoreBuyMemLvScore;
import com.cnit.yoyo.model.sales.activity.dto.ScoreBuyActivityQryDTO;
import com.cnit.yoyo.model.sales.activity.dto.ScoreBuyApplyDTO;
import com.cnit.yoyo.model.sales.activity.dto.ScoreBuyApplyQryDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.sales.activity.dao.ScoreBuyActivityMapper;
import com.cnit.yoyo.sales.activity.dao.ScoreBuyApplyMapper;
import com.cnit.yoyo.sales.activity.dao.ScoreBuyMemLvScoreMapper;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.domain.ResultPage;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.github.pagehelper.PageHelper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("scoreBuyApplyService")
public class ScoreBuyApplyService {
	private static final Log log = LogFactory.getLog(ScoreBuyApplyService.class);

	@Autowired
	private RemoteService itemService;
	
	@Autowired
	private ScoreBuyApplyMapper scoreBuyApplyMapper;
	
	@Autowired
	private ScoreBuyMemLvScoreMapper scoreBuyMemLvScoreMapper;
	
	
	
	/**
	  * @description <b>查询积分换购列表</b>
	*/
	public Object loadScoreBuyApplyList(Object data) {
		HeadObject head = new HeadObject();
		ResultPage<ScoreBuyApplyDTO> resultPage=null;
		try {
			ScoreBuyApplyQryDTO record=(ScoreBuyApplyQryDTO)data;
			PageHelper.startPage(record.getPage(), record.getRows());
			resultPage=new ResultPage<ScoreBuyApplyDTO>(scoreBuyApplyMapper.selectList(record));
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head,JSON.toJSON(resultPage));
	}
	public Object selectByPrimaryKey(Object data) {
		HeadObject head = new HeadObject();
		ScoreBuyApplyDTO dtl=null;
		try {
			dtl=scoreBuyApplyMapper.selectByPrimaryKey(Integer.parseInt(data.toString()));
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head,JSON.toJSON(dtl));
	}
	/**
	 * @Description:保存积分换购
	 */
	public Object saveScoreActivity(Object data) throws BusinessException {
		HeadObject head = new HeadObject();
		try {
			JSONObject content = JSONObject.fromObject(data);
			ScoreBuyApplyDTO dto = (ScoreBuyApplyDTO) JSONObject.toBean(content,ScoreBuyApplyDTO.class);
			
			//1.先添加商品规则
			ScoreBuyApply record = new ScoreBuyApply();
			BeanUtils.copyProperties(dto, record);
			record.setStatus(1);//1=>待审核,2=>审核通过,3=>审核不通过
			record.setLastMidifity(new Date());
			record.setIsdel("0");
			scoreBuyApplyMapper.insertSelective(record);
			
			//2.添加优惠券
			if (StringUtils.isNotBlank(dto.getMemberLvScore())) {
				JSONArray array = JSONArray.fromObject(dto.getMemberLvScore()); // 相关商品
				ScoreBuyMemLvScore memLv = null;
				List<ScoreBuyMemLvScore> list = new ArrayList<ScoreBuyMemLvScore>();
				if (array != null && array.size() > 0) {
					JSONObject relateJson = null;
					for (int i = 0; i < array.size(); i++) { // 多个相关商品
						relateJson = array.getJSONObject(i);
						memLv = new ScoreBuyMemLvScore();
						memLv.setAid(dto.getAid());
						memLv.setGid(dto.getGid());
						memLv.setMemberId(dto.getMemberId());
						memLv.setPrice(dto.getPrice());
						memLv.setLevelId(relateJson.optInt("levelId"));
						memLv.setScore(relateJson.optInt("memScore"));
						memLv.setLastPrice(new BigDecimal(relateJson.optInt("memPrice")));
						list.add(memLv);
						scoreBuyMemLvScoreMapper.insertSelective(memLv);
					}
					//scoreBuyMemLvScoreMapper.batchInsert(list);// 批量新增
				}
			}
			head.setRetCode(ErrorCode.SUCCESS);
			
		} catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
			throw new BusinessException(ErrorCode.SPE001);
		}
		return new ResultObject(head);
	}
	public Object updateScoreActivity(Object data) throws BusinessException {
		HeadObject head = new HeadObject();
		try {
			JSONObject content = JSONObject.fromObject(data);
			ScoreBuyApply dto = (ScoreBuyApply) JSONObject.toBean(content,ScoreBuyApply.class);
			scoreBuyApplyMapper.updateByPrimaryKeySelective(dto);
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
			throw new BusinessException(ErrorCode.SPE001);
		}
		return new ResultObject(head);
	}
	
	public Object selectGoodsScoreActivity(Object data){
		HeadObject head = new HeadObject();
		ScoreBuyApplyDTO scoreBuyApply = null;
		try{
			head.setRetCode(ErrorCode.SUCCESS);
			scoreBuyApply = scoreBuyApplyMapper.selectGoodsScoreActivity((Integer) data);
		}catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
			e.printStackTrace();
		}
		return new ResultObject(head, scoreBuyApply);
	}
}
