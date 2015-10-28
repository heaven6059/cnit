package com.cnit.yoyo.report.dao;

import java.util.List;
import java.util.Map;

import com.cnit.yoyo.report.model.Report;
import com.cnit.yoyo.report.model.ReportComment;
import com.cnit.yoyo.report.model.ReportDTO;
import com.cnit.yoyo.report.model.ReportQryDTO;


public interface ReportMapper {
	
    int deleteByPrimaryKey(Long reportId);

    int insert(Report record);

    int insertSelective(Report record);

    Report selectByPrimaryKey(Long reportId);

    int updateByPrimaryKeySelective(Report record);

    int updateByPrimaryKeyWithBLOBs(Report record);

    int updateByPrimaryKey(Report record);
	/**
	 * 
	*@Description:
	*@param paraData
	*@return
	 */
	public List<ReportDTO> getReportList(ReportQryDTO reportQryDTO);

	/**
	 * 
	*@Description:
	*@param report
	*@return
	 */
	public int saveReport(Report report);

	/**
	 * 
	*@Description:
	*@param reportComment
	*@return
	 */
	public int saveReportComment(ReportComment reportComment);

	/**
	 * 
	*@Description:
	*@param map
	*@return
	 */
	public ReportDTO getReportDetailById(Map<String, Object> map);
	
	int updateReportStatus(Report report);
	
	public int selectProductIsReport(Long productId);
}