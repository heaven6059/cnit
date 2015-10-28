package com.cnit.yoyo.report.service;
/**   
 * @Description: 举报管理service
 * @author  余平 yuping@chinacnit.com 
 * @date 2015-4-28 下午8:12:03 
 * @Copyright 2015 cnit
 * @version V1.0.0 		
 */
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.alibaba.fastjson.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.report.dao.ReportMapper;
import com.cnit.yoyo.report.model.Report;
import com.cnit.yoyo.report.model.ReportComment;
import com.cnit.yoyo.report.model.ReportDTO;
import com.cnit.yoyo.report.model.ReportQryDTO;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;


@Service("reportService")
public class ReportService {
	
	@Autowired
	private ReportMapper  reportMapper;
	
	/**
	 * 
	 * @param object 获取举报列表
	 * @return
	 */
	public Object getReportList(Object object){
		HeadObject  head =  new  HeadObject();
		ReportQryDTO reportQryDTO = (ReportQryDTO)object;
		PageHelper.startPage(reportQryDTO.getPage(), reportQryDTO.getRows());
		ResultPage<ReportDTO> dataList = new ResultPage<ReportDTO>(reportMapper.getReportList(reportQryDTO));
		if(dataList != null && dataList.getRows().size() > 0){
			head.setRetCode(ErrorCode.SUCCESS);
		}else{
			head.setRetCode(ErrorCode.FAILURE);
		}
		ResultObject  result = new ResultObject(head, JSONObject.toJSON(dataList));
		return result;
	}
	
	/**
	 * 
	 * @Description: 保存举报
	 * @param object
	 * @return
	 */
	public Object saveReport(Object object){
		HeadObject  head =  new  HeadObject();
		try{
			Report record = (Report) object;
			record.setCreatetime(new Date());
			reportMapper.insertSelective(record);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
			e.printStackTrace();
		}
		
		return new ResultObject(head);
	}
	
	/**
	 * 
	 * @Description: 增加举报留言
	 * @param object
	 * @return
	 */
	public Object saveReportComment(Object object){
		HeadObject  head =  new  HeadObject();
		ReportComment  reportComment = (ReportComment)object;
		String imagePath = reportComment.getImagePath();
		if(imagePath != null && imagePath.trim().length() >0 ){
			imagePath = imagePath.substring(0, imagePath.length()-1);
		}
		reportComment.setImagePath(imagePath); 		
		int result = reportMapper.saveReportComment(reportComment);
		if(result > 0){
			head.setRetCode(ErrorCode.SUCCESS);
		}else{
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head);
	}
	
	public Object updateReportStatus(Object object){
		HeadObject  head =  new  HeadObject();
		try{
			reportMapper.updateReportStatus((Report) object);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head);
	}
	
	
	/**
	 * 
	 * @Description: 查询举报详情
	 * @param data
	 * @return
	 */
	public Object getReportDetailById(Object data){
		HeadObject head = new HeadObject();
		ReportDTO  reportDTO =null;
		Map<String, Object> map=(Map<String, Object>) data;
		reportDTO=reportMapper.getReportDetailById(map);
		head.setRetCode(ErrorCode.SUCCESS);
		return new ResultObject(head, reportDTO);
	}

	/**
	  * @description <b>查询商品是否举报</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-18
	  * @param @param object
	  * @param @return
	  * @return Object
	*/
	public Object selectProductIsReport(Object object){
		HeadObject head = new HeadObject();
		Map<String, Integer> result=new HashMap<String, Integer>();
		try{
			int count = reportMapper.selectProductIsReport((Long) object);
			result.put("hasReport",count);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
			e.printStackTrace();
		}
		return new ResultObject(head, result);
	}
	
	/**
	  * @description <b>修改投诉</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015年7月8日
	  * @param @param object
	  * @param @return
	  * @return Object
	*/
	public Object updateReport(Object object){
		HeadObject headObject=new HeadObject();
		try{
			Report record=(Report)object;
			if(null==record.getReportId()){
				headObject.setRetCode(ErrorCode.FAILURE);
			}else{
				reportMapper.updateByPrimaryKeySelective(record);
				headObject.setRetCode(ErrorCode.SUCCESS);
			}
		}catch(Exception e){
			e.printStackTrace();
			headObject.setRetCode(ErrorCode.FAILURE);
		}
		return headObject;
	}
}

