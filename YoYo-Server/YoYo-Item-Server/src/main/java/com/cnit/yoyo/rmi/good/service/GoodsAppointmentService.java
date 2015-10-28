package com.cnit.yoyo.rmi.good.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.goods.GoodsAppointmentMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.goods.GoodsAppointment;
import com.cnit.yoyo.model.goods.dto.GoodsAppointmentDTO;
import com.cnit.yoyo.model.goods.dto.GoodsAppointmentTimeDTO;
import com.cnit.yoyo.util.DateUtils;
import com.cnit.yoyo.util.RedisClientUtil;
import com.cnit.yoyo.util.StringUtil;


@Service("goodsAppointmentService")
public class GoodsAppointmentService {
	@Autowired
	private GoodsAppointmentMapper goodsAppointmentMapper;
	
	@Autowired
    private RedisClientUtil redisService;
	
	public Object getAppointmentByGoodsId(Object data){
		HeadObject headObject=new HeadObject();
		GoodsAppointment goodsAppointment=null;
		try{
			goodsAppointment = goodsAppointmentMapper.selectGoodsAppointmentByGoodsId((Long) data);
			headObject.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			headObject.setRetCode(ErrorCode.FAILURE);
			e.printStackTrace();
		}
		return new ResultObject(headObject, goodsAppointment);
	}
	
	public Object getGoodsAppointmentByGoodsId(Object data){
		HeadObject headObject=new HeadObject();
		GoodsAppointment goodsAppointment=null;
		List<GoodsAppointmentTimeDTO> dateList=new ArrayList<GoodsAppointmentTimeDTO>();
		try{
			String [] times={"9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00"};
			goodsAppointment = goodsAppointmentMapper.selectGoodsAppointmentByGoodsId((Long) data);
			if(null!=goodsAppointment){
				for(int x=0;x<times.length-1;x++){
					GoodsAppointmentTimeDTO appointmentTimeDTO=new GoodsAppointmentTimeDTO();
					dateList.add(appointmentTimeDTO);
					appointmentTimeDTO.setAppointmentTime(times[x]+"-"+times[x+1]);
					List<GoodsAppointmentDTO> goodsAppointmentDTOs=new ArrayList<GoodsAppointmentDTO>();
					for(int i=1;i<15;i++){
						GoodsAppointmentDTO appointmentDTO=new GoodsAppointmentDTO();
						goodsAppointmentDTOs.add(appointmentDTO);
						appointmentTimeDTO.setGoodsAppointmentDTOs(goodsAppointmentDTOs);
						PropertyUtils.copyProperties(appointmentDTO, goodsAppointment);
						appointmentDTO.setAppointmentDate(DateUtils.getOffsetDayDate(new Date(), -i));
						//类型是否为周六日或节假日
						String key=goodsAppointment.getGoodsId()+"_"+(DateUtils.dateToString(appointmentDTO.getAppointmentDate(),"yyyy-MM-dd")+"_"+appointmentTimeDTO.getAppointmentTime()).replace("-", "").replace(":", "");
						String count = redisService.get(key.toString());
						if(!StringUtil.isEmpty(count)){
							appointmentDTO.setAppointmentNum(Integer.valueOf(count));
						}
						//节假日时间段
						if(goodsAppointment.getType().equals(3)){
							//是否在设定的时间区间之内
							if(DateUtils.compare(DateUtils.getOffsetDayDate(DateUtils.getDate(DateUtils.getCurrentDate("yyyy-MM-dd")), -i), goodsAppointment.getAppointStart())>=0
									&&DateUtils.compare(DateUtils.getOffsetDayDate(DateUtils.getDate(DateUtils.getCurrentDate("yyyy-MM-dd")), -i), goodsAppointment.getAppointEnd())<=0){
								//当前日期可用
								appointmentDTO.setEnabled(true);
							}else{
								//当前日期不可用
								appointmentDTO.setEnabled(false);
							}
						}else if(goodsAppointment.getType().equals(2)){
							//周末时间段
							int week = DateUtils.getTimeDateOfWeek(appointmentDTO.getAppointmentDate());
							if(week>5){
								//当前日期可用
								appointmentDTO.setEnabled(true);
							}else{
								//当前日期不可用
								appointmentDTO.setEnabled(false);
							}
						}else{
							appointmentDTO.setEnabled(true);
						}
					}				
				}
			}
			headObject.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			headObject.setRetCode(ErrorCode.FAILURE);
			e.printStackTrace();
		}
		return new ResultObject(headObject, dateList);
	}
}
