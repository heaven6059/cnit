package com.cnit.yoyo.membercar.service;
/**   
 * @Description: 我的车型service
 * @author  余平 yuping@chinacnit.com 
 * @date 2015-4-28 下午8:12:03 
 * @Copyright 2015 cnit
 * @version V1.0.0 		
 */
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.api.vo.MemberCarDefVO;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.membercar.dao.MemberCarMapper;
import com.cnit.yoyo.membercar.model.MemberCar;
import com.cnit.yoyo.membercar.model.MemberCarDTO;
import com.cnit.yoyo.membercar.model.MemberCarQryDTO;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;


@Service("memberCarService")
public class MemberCarService {
	
	@Autowired
	private MemberCarMapper  memberCarMapper;
	
	/**
	  * @description <b>查询用户车型信息列表</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-3
	  * @param @param object
	  * @param @return
	  * @return Object
	*/
	public Object memberCarList(Object object){
		HeadObject  head =  new  HeadObject();
		ResultPage<MemberCar> dataList=null;
		try{
			MemberCarQryDTO qryDTO=new MemberCarQryDTO();
			BeanUtils.copyProperties(qryDTO, object);
			PageHelper.startPage(qryDTO.getPage(), qryDTO.getRows());
			dataList= new ResultPage<MemberCar>(memberCarMapper.selectMemberCarList(qryDTO));
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
			e.printStackTrace();
		}
		return new ResultObject(head, dataList);
	}
	
	
	/**
	  * @description <b>保存用户的车型</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-3
	  * @param @param object
	  * @param @return
	  * @return Object
	*/
	public Object saveMemberCar(Object object){
		HeadObject  head =  new  HeadObject();
		try{
			MemberCar memberCar =new MemberCar();
			BeanUtils.copyProperties(memberCar, object);
			//查询当前用户已加入车型数量
			int count = memberCarMapper.selectMemberCarCount(memberCar);
			MemberCarQryDTO qryDTO = new MemberCarQryDTO();
			BeanUtils.copyProperties(qryDTO, object);
			//判断当前车型是否已加入
			int hasAddCount =memberCarMapper.selectMemberHasAddCar(qryDTO);
			if(count<6&&hasAddCount==0){
				memberCarMapper.insertSelective(memberCar);
				if(null!=memberCar.getIsDefault()&&memberCar.getIsDefault()==1){
					memberCarMapper.updateDefaultCarStatus(memberCar);
				}
				head.setRetCode(ErrorCode.SUCCESS);
			}else{
				head.setRetCode(ErrorCode.THE_NUMBER_OVER);	
			}
		}catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
			e.printStackTrace();
		}
		return new ResultObject(head);
	}
	
	/**
	  * @description <b>查询用户是否添加该款车型</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-3
	  * @param @param object
	  * @param @return
	  * @return Object
	*/
	public Object findMemberHasAddCar(Object object){
		HeadObject  head =  new  HeadObject();
		int count = 0;
		try{
			MemberCarQryDTO qryDTO = new MemberCarQryDTO();
			BeanUtils.copyProperties(qryDTO, object);
			count =memberCarMapper.selectMemberHasAddCar(qryDTO);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
			e.printStackTrace();
		}
		return new ResultObject(head,count);
	}
	
	/**
	  * @description <b>查询用户默认车型</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-3
	  * @param @param object
	  * @param @return
	  * @return Object
	*/
	public Object findMemberDefaultCar(Object object){
		HeadObject  head =  new  HeadObject();
		MemberCarDTO memberCarDTO = null;
		try{
			MemberCarQryDTO qryDTO = new MemberCarQryDTO();
			BeanUtils.copyProperties(qryDTO, object);
			memberCarDTO =memberCarMapper.selectMemberDefaultCar(qryDTO);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
			e.printStackTrace();
		}
		return new ResultObject(head,memberCarDTO);
	}
	
	/**
	  * @description <b>修改用户的车型</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-3
	  * @param @param object
	  * @param @return
	  * @return Object
	*/
	public Object updateMemberCar(Object object){
		HeadObject  head =  new  HeadObject();
		try{
			MemberCar memberCar = new MemberCar();
			ConvertUtils.register(new DateConverter(null), java.util.Date.class); 
			BeanUtils.copyProperties(memberCar, object);
			memberCarMapper.updateByPrimaryKeySelective(memberCar);
			if(memberCar.getIsDefault()==1){
				memberCarMapper.updateDefaultCarStatus(memberCar);
			}
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
			e.printStackTrace();
		}
		return new ResultObject(head);
	}
	
	/**
	  * @description <b>设置默认车型</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-3
	  * @param @param object
	  * @param @return
	  * @return Object
	*/
	public Object setDefaultCar(Object object){
		HeadObject  head =  new  HeadObject();
		try{
			MemberCar memberCar =(MemberCar)object;
			memberCarMapper.updateByPrimaryKeySelective(memberCar);
			memberCarMapper.updateDefaultCarStatus(memberCar);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
			e.printStackTrace();
		}
		return new ResultObject(head);
	}
	
	/**
	  * @description <b>设置默认车型(app)</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-3
	  * @param @param object
	  * @param @return
	  * @return Object
	*/
	public Object setDefaultCarForApp(Object object){
		HeadObject  head =  new  HeadObject();
		try{
			MemberCarDefVO memberCarDefVO =(MemberCarDefVO)object;
			MemberCar memberCar = new MemberCar();
			memberCar.setId(memberCarDefVO.getId());
			memberCar.setIsDefault(memberCarDefVO.getIsDefault());
			memberCar.setMemberId(memberCarDefVO.getMemberId());
			memberCarMapper.updateByPrimaryKeySelective(memberCar);
			memberCarMapper.updateDefaultCarStatus(memberCar);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
			e.printStackTrace();
		}
		return new ResultObject(head);
	}
	
	/**
	  * @description <b>查询用户的车型</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-3
	  * @param @param object
	  * @param @return
	  * @return Object
	*/
	public Object findMemberCarById(Object object){
		HeadObject  head =  new  HeadObject();
		MemberCarDTO memberCarDTO=null;
		try{
			MemberCarQryDTO qryDTO = new MemberCarQryDTO();
			BeanUtils.copyProperties(qryDTO, object);
			memberCarDTO=memberCarMapper.selectMemberCar(qryDTO);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
			e.printStackTrace();
		}
		return new ResultObject(head,memberCarDTO);
	}
	
	/**
	  * @description <b>删除用户车型(物理删除)</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-4
	  * @param @param object
	  * @param @return
	  * @return Object
	*/
	public Object deleteMemberCar(Object object){
		HeadObject  head =  new  HeadObject();
		try{
			MemberCarQryDTO qryDTO = new MemberCarQryDTO();
			BeanUtils.copyProperties(qryDTO, object);
			memberCarMapper.deleteMemberCar(qryDTO);
			//默认车型被删除，设置最后记录为默认车型
			if(qryDTO.getIsDefault()==1){
				memberCarMapper.updateLastCarToDefault(qryDTO);
			}
			head.setRetCode(ErrorCode.SUCCESS);			
		}catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
			e.printStackTrace();
		}
		return new ResultObject(head);
	}
	

}

