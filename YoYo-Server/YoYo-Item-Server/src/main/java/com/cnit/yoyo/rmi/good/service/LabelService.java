package com.cnit.yoyo.rmi.good.service;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.goods.LabelMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.goods.Label;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

/**
 * @ClassName: LabelService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Harder-Zhao
 * @date 2015-3-30 上午11:17:57
 * 
 */
@Service("labelService")
public class LabelService {

	@Autowired
	private LabelMapper labelMapper;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object findLabelsByCamId(Object data) {
		HeadObject head = new HeadObject();
		ResultPage<Label> page = null;
		try {
			JSONObject content = JSONObject.fromObject(data);
			PageHelper.startPage(content.getInt("pageIndex"), content.getInt("pageSize"));
			page = new ResultPage(labelMapper.findLabelsByCamId());
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head, JSONObject.fromObject(page));
	}

	/**
	 * 
	 * @Description: 查找是否有同名
	 * @param @param data
	 * @param @return
	 * @author xiaox
	 * @date 2015-4-1 上午11:17:00
	 */
	 public Object findBrandByName(Object data){
      	 HeadObject head = new HeadObject();
           try{
               JSONObject content = JSONObject.fromObject(data);
               Label label = (Label)JSONObject.toBean(content, Label.class);
   			   int count=labelMapper.findBrandByName(label);
   			   if(count == 0){
   				   head.setRetCode(ErrorCode.SUCCESS);
   			   }else{
   				   head.setRetCode(ErrorCode.IS_EXIST);
   			   }
           }catch(Exception e){    
        	   e.printStackTrace();
               head.setRetCode(ErrorCode.FAILURE);
           }       
           return new ResultObject(head);
    }
	
	
	/**
	 * @Title: insertLabel
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author Harder-Zhao
	 * @param @param data
	 * @param @return 设定文件
	 * @return Object 返回类型
	 * @throws
	 */
	public Object insertLabel(Object data) {
		HeadObject head = new HeadObject();
		try {
			JSONObject content = JSONObject.fromObject(data);
			Label label = (Label) JSONObject.toBean(content, Label.class);
			labelMapper.insertSelective(label);
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head);
	}

	/**
	 * @Title: updateLabel
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author Harder-Zhao
	 * @param @param data
	 * @param @return 设定文件
	 * @return Object 返回类型
	 * @throws
	 */
	public Object updateLabel(Object data) {
		HeadObject head = new HeadObject();
		try {
			JSONObject content = JSONObject.fromObject(data);
			Label label = (Label) JSONObject.toBean(content, Label.class);
			labelMapper.updateByPrimaryKeySelective(label);
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head);
	}

	/**
	 * @Title: deleteLabel
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author Harder-Zhao
	 * @param @param data
	 * @param @return 设定文件
	 * @return Object 返回类型
	 * @throws
	 */
	public Object deleteLabel(Object data) {
		HeadObject head = new HeadObject();
		try {
			Long[] ids = (Long[]) data;
			labelMapper.deleteByPrimaryKeys(ids);
			// TODO 还需要去t_business_brand表中将与这个标签相关联的
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head);
	}
}