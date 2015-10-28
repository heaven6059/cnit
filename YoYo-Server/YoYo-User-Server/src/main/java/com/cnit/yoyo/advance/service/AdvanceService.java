package com.cnit.yoyo.advance.service;
/**   
 * @Description: 买家 预存款
 * @author  余平 yuping@cnit.com 
 * @date 2015-5-5 下午2:13:50 
 * @Copyright 2015 cnit
 * @version V1.0.0 		
 */
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.advance.dao.AdvanceHistoryMapper;
import com.cnit.yoyo.advance.model.Advance;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

@Service("advanceService")
public class AdvanceService {
	
	@Autowired
	private AdvanceHistoryMapper  advanceHistoryMapper;
	
	public Object getAdvanceList(Object object){
		HeadObject  head =  new  HeadObject();
		int pageNum = JSONObject.fromObject(object).getInt("pageNum");
		int pageSize = JSONObject.fromObject(object).getInt("pageSize");
		Map<String,Object> paraData = (Map<String,Object>)object;
		PageHelper.startPage(pageNum, pageSize);
		ResultPage<Advance> dataList = new ResultPage<Advance>(advanceHistoryMapper.getAdvanceList(paraData));
		if(dataList != null && dataList.getRows().size() > 0){
			head.setRetCode(ErrorCode.SUCCESS);
		}else{
			head.setRetCode(ErrorCode.FAILURE);
		}
		ResultObject  result = new ResultObject(head, dataList);
		return result;
	}

}

