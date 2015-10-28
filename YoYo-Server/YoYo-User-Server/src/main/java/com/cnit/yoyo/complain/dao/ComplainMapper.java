package com.cnit.yoyo.complain.dao;
/**   
 * @Package com.cnit.yoyo.complain.dao 
 * @Description: 
 * @author  余平 yuping@cnit.com 
 * @date 2015-4-29 下午2:58:31 
 * @Copyright 2015 cnit
 * @version V1.0.0 		
 */
import java.util.List;
import java.util.Map;

import com.cnit.yoyo.complain.model.Complain;
import com.cnit.yoyo.complain.model.ComplainComments;
import com.cnit.yoyo.complain.model.dto.ComplainDetailDTO;
import com.cnit.yoyo.complain.model.dto.ComplainQryDTO;

public interface ComplainMapper {

    int deleteByPrimaryKey(Long complainId);

    int insert(Complain record);

    int insertSelective(Complain record);

    int updateByPrimaryKeySelective(Complain record);

    int updateByPrimaryKeyWithBLOBs(Complain record);

    int updateByPrimaryKey(Complain record);
	
	/** 
	 * @Description: 获取投诉列表信息
	 * @param paraData
	 * @return			
	 */
	public List<Complain> getComplainList(ComplainQryDTO qryDTO);

	/** 
	 * @Description: 保存投诉卖家
	 * @param complain
	 * @return			
	*/
	public int saveComplain(Complain complain);


	/** 
	 * @Description: 保存投诉卖家内容
	 * @param complainComments
	 * @return			
	*/
	public int saveComplainComment(ComplainComments complainComments); 
	

	/** 
	 * @Description: 修改状态
	 * @param complainComments
	 * @return			
	*/
	public int updateComplainStatus(Map<String, Object> params);

	Complain selectByPrimaryKey(Long complainId);
	
	/** 
	 * @Description: 查询订单详投诉信息
	 * @param map
	 * @return			
	*/
	public ComplainDetailDTO getComplainDetailById(Long complainId);
}