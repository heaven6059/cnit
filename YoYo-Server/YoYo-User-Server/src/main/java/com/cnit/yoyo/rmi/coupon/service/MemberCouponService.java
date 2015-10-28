package com.cnit.yoyo.rmi.coupon.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.coupon.dao.MemberCouponMapper;
import com.cnit.yoyo.coupon.model.MemberCoupon;
import com.cnit.yoyo.coupon.model.dto.MemberCouponDTO;
import com.cnit.yoyo.coupon.model.dto.MemberCouponQryDTO;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.activity.dto.CouponsDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

@Service("memberCouponService")
public class MemberCouponService {
	
	private MemberCouponMapper memberCouponMapper;
	
	@Autowired
	private RemoteService itemService;
	
	public Object selectMemberCouponsForPage(Object object){
		HeadObject headObject=new HeadObject();
		ResultPage<MemberCouponDTO> memberCoupons = null;
		try{
			MemberCouponQryDTO memberCouponQry=(MemberCouponQryDTO)object;
			PageHelper.startPage(memberCouponQry.getPage(),memberCouponQry.getRows());
			memberCoupons = new ResultPage<MemberCouponDTO>(memberCouponMapper.selectMemberStoreCoupons(memberCouponQry));
			headObject.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			headObject.setRetCode(ErrorCode.FAILURE);
			e.printStackTrace();
		}
		return new ResultObject(headObject, memberCoupons);
	}
	
	public Object selectMemberStoreCoupons(Object object){
		HeadObject headObject=new HeadObject();
		List<MemberCouponDTO> memberCoupons = null;
		try{
			memberCoupons = memberCouponMapper.selectMemberStoreCoupons((MemberCouponQryDTO) object);
			headObject.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			headObject.setRetCode(ErrorCode.FAILURE);
			e.printStackTrace();
		}
		return new ResultObject(headObject, memberCoupons);
	}
	
	public Object selectMemberCouponsByOrder(Object object){
		HeadObject headObject=new HeadObject();
		MemberCouponDTO memberCoupons = null;
		try{
			memberCoupons = memberCouponMapper.selectMemberCouponsByOrder((MemberCouponQryDTO) object);
			headObject.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			headObject.setRetCode(ErrorCode.FAILURE);
			e.printStackTrace();
		}
		return new ResultObject(headObject, memberCoupons);
	}
	
	public Object saveMemberCoupon(Object object){
		HeadObject headObject=new HeadObject();
		try{
			MemberCoupon memberCoupon=(MemberCoupon) object;
			memberCoupon.setCreateTime(new Date());
			memberCouponMapper.insertSelective(memberCoupon);
			HeadObject headObject1 = CommonHeadUtil.geneHeadObject("couponsService.updateCouponsNum");
			CouponsDTO couponsDTO=new CouponsDTO();
			couponsDTO.setCpnsId(memberCoupon.getCpnsId());
			couponsDTO.setOnlineQuantity(1);
			ResultObject resultObject1 = (ResultObject) itemService.doServiceByServer(new RequestObject(headObject1, JSON.toJSONString(couponsDTO)));
			if(!resultObject1.getHead().getRetCode().equals(ErrorCode.SUCCESS)){
				headObject.setRetCode(resultObject1.getHead().getRetCode());
			}else{
				headObject.setRetCode(ErrorCode.SUCCESS);
			}
		}catch (Exception e) {
			headObject.setRetCode(ErrorCode.FAILURE);
			e.printStackTrace();
		}
		return new ResultObject(headObject);
	}
	
	public Object updateMemberCoupon(Object object){
		HeadObject headObject=new HeadObject();
		try{
			List<MemberCouponDTO> memberCoupons = (List<MemberCouponDTO>) object;
			for (MemberCouponDTO memberCouponDTO : memberCoupons) {
				memberCouponMapper.updateByPrimaryKeySelective(memberCouponDTO);
			}
			headObject.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			headObject.setRetCode(ErrorCode.FAILURE);
			e.printStackTrace();
		}
		return new ResultObject(headObject);
	}
	
	public Object deleteMemberCoupon(Object object){
		HeadObject headObject=new HeadObject();
		try{
			MemberCouponDTO memberCoupon=(MemberCouponDTO)object;
			memberCouponMapper.updateByPrimaryKeySelective(memberCoupon);
			headObject.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			headObject.setRetCode(ErrorCode.FAILURE);
			e.printStackTrace();
		}
		return new ResultObject(headObject);
	}
	 
}
