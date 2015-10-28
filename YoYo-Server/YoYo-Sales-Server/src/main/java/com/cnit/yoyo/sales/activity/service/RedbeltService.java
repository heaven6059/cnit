package com.cnit.yoyo.sales.activity.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.membermsg.model.MemberMsg;
import com.cnit.yoyo.model.goods.GoodsCatWithBLOBs;
import com.cnit.yoyo.model.member.PamAccount;
import com.cnit.yoyo.model.order.Redbelt;
import com.cnit.yoyo.model.order.RedbeltDetail;
import com.cnit.yoyo.model.order.RedbeltDetailExample;
import com.cnit.yoyo.model.order.RedbeltExample;
import com.cnit.yoyo.model.order.RedbeltVo;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.sales.activity.dao.RedbeltDetailMapper;
import com.cnit.yoyo.sales.activity.dao.RedbeltMapper;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.DateUtils;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

import net.sf.json.JSONArray;

@Service("redbeltService")
public class RedbeltService {
	private static final Log log = LogFactory.getLog(RedbeltService.class);

	@Autowired
	private RedbeltMapper redbeltMapper;
	@Autowired
	private RemoteService itemService;
	@Autowired
	private RedbeltDetailMapper redbeltDetailMapper;
	@Autowired
	private RemoteService userService;

	public Object findRedbelt(Object data) {
		HeadObject head = new HeadObject();
		ResultPage<Redbelt> resultPage = null;
		try {
			RedbeltExample record = (RedbeltExample) data;
			PageHelper.startPage(record.getPage(), record.getRows());
			resultPage = new ResultPage<Redbelt>(redbeltMapper.selectByExample(record));
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head, JSON.toJSON(resultPage));
	}

	public Object findRedbeltById(Object data) {
		HeadObject head = new HeadObject();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Redbelt redbelt = null;
		try {
			redbelt = redbeltMapper.selectByPrimaryKey((Long) data);
			String[] ids = redbelt.getCategorys().split(",");
			Long[] list = new Long[ids.length];
			for (int i = 0; i < ids.length; i++) {
				list[i] = Long.parseLong(ids[i]);
			}
			head = CommonHeadUtil.geneHeadObject("010401-03");
			ResultObject object = itemService.doService(new RequestObject(head, list));
			List<GoodsCatWithBLOBs> cats = (List<GoodsCatWithBLOBs>) object.getContent();
			resultMap.put("redbelt", redbelt);
			resultMap.put("categorys", cats);
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head, resultMap);
	}

	public Object saveRedbelt(Object data) {
		HeadObject head = new HeadObject();
		ResultPage<Redbelt> resultPage = null;
		try {
			RedbeltVo record = (RedbeltVo) data;
			Redbelt redbelt = new Redbelt();
			redbelt.setTarget(Integer.parseInt(record.getTarget()));
			redbelt.setNums(record.getNums());
			redbelt.setTotalAmount(record.getTotalAmount());
			redbelt.setRule(record.getRule());
			redbelt.setCategorys(record.getCategorys());
			redbelt.setStartTime(DateUtils.parser(record.getStartTime() + " 00:00:00"));
			redbelt.setEndTime(DateUtils.parser(record.getEndTime() + " 23:59:59"));
			redbelt.setMemberLvIds(record.getMemberLvIds());
			redbelt.setStatus((byte) record.getStatus().intValue());
			redbelt.setRemark(record.getRemark());
			int nums=0;
			if(record.getStatus()==1){//发放
				String acconutType="";
				if(redbelt.getTarget()==0){
					acconutType="1%";
				}else if(redbelt.getTarget()==1){
					acconutType="10%";
				}else if(redbelt.getTarget()==2){
					acconutType="11%";
				}
				PamAccount pamAccount=new PamAccount();
				pamAccount.setAccountType(acconutType);
				pamAccount.setAccountStatus("1");
				HeadObject headObject1 = CommonHeadUtil.geneHeadObject("userServiceImpl.findAll");
				ResultObject resultObject1 = (ResultObject) userService.doServiceByServer(new RequestObject(headObject1, pamAccount));
				if (null != resultObject1) {
					JSONArray jsonarray = JSONArray.fromObject(resultObject1.getContent());
					List<PamAccount> list1 = (List<PamAccount>) JSONArray.toCollection(jsonarray, PamAccount.class);
					nums=list1.size();
					redbelt.setNums(nums);
					if (record.getRedbeltId() != null) {
						redbelt.setRedbeltId(record.getRedbeltId());
						redbeltMapper.updateByPrimaryKeySelective(redbelt);
					} else {
						redbeltMapper.insert(redbelt);
					}
					if(null!=list1 && list1.size()>0){
						Double result=CommonUtil.div(Double.parseDouble(record.getTotalAmount().toString()), nums, 2);
						for(int i=0;i<list1.size();i++){
							PamAccount account=list1.get(i);
							RedbeltDetail detail=new RedbeltDetail();
							detail.setRedbeltId(redbelt.getRedbeltId());
							detail.setMemberId(Long.parseLong(account.getMemberId().toString()));
							detail.setUserName(account.getLoginName());
							detail.setAmount(new BigDecimal(result));
							detail.setCreateTime(new Date());
							redbeltDetailMapper.insertSelective(detail);
							HeadObject headObject2 = CommonHeadUtil.geneHeadObject("memberMsgService.saveMemberMsg");
							MemberMsg memberMsg=new MemberMsg();
							memberMsg.setCreateTime(new Date());
							memberMsg.setForId(93);
							memberMsg.setFromId(93);
							memberMsg.setFromType(1);
							memberMsg.setSubject("您已成功获得红包"+result+"元");
							memberMsg.setContent("您已成功获得红包"+result+"元");
							memberMsg.setToId(account.getMemberId());
							memberMsg.setToUname(account.getLoginName());
							userService.doServiceByServer(new RequestObject(headObject2, memberMsg));
						}
					}
				}
			}
			
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head, JSON.toJSON(resultPage));
	}

	public Object deleteRedbeltById(Object data) {
		HeadObject head = new HeadObject();
		ResultPage<Redbelt> resultPage = null;
		try {
			Redbelt redbelt = redbeltMapper.selectByPrimaryKey((Long) data);
			if (null != redbelt && redbelt.getStatus() == 1) {
				head.setRetCode(ErrorCode.IS_EXIST_CASCADE);
				return new ResultObject(head, JSON.toJSON(resultPage));
			}
			redbeltMapper.deleteByPrimaryKey((Long) data);
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head, JSON.toJSON(resultPage));
	}

	public Object findRedbeltDetailById(Object data) {
		HeadObject head = new HeadObject();
		ResultPage<RedbeltDetail> resultPage = null;
		try {
			RedbeltDetail redbeltDetail = (RedbeltDetail) data;
			RedbeltDetailExample example = new RedbeltDetailExample();
			example.setPage(redbeltDetail.getPage());
			example.setRows(redbeltDetail.getRows());
			example.createCriteria().andRedbeltIdEqualTo(redbeltDetail.getRedbeltId());
			PageHelper.startPage(example.getPage(), example.getRows());
			resultPage = new ResultPage<RedbeltDetail>(redbeltDetailMapper.selectByExample(example));
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head, JSON.toJSON(resultPage));
	}

}
