package com.cnit.yoyo.rmi.requirement.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.requirement.PostRequirementMapper;
import com.cnit.yoyo.dao.requirement.RequirementContentMapper;
import com.cnit.yoyo.dao.requirement.RequirementMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.requirement.PostRequirement;
import com.cnit.yoyo.model.requirement.Requirement;
import com.cnit.yoyo.model.requirement.RequirementContent;
import com.cnit.yoyo.model.requirement.dto.PostRequirementDTO;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;
import com.mysql.fabric.xmlrpc.base.Array;

@Service("postWantService")
public class PostWantService {

    public static final Logger logger = LoggerFactory.getLogger(PostWantService.class);
    
    @Autowired
    private RequirementMapper requirementMapper;
    @Autowired
    private PostRequirementMapper postRequirementMapper;
    @Autowired
    private RequirementContentMapper requirementContentMapper;
    
    /**
     * 查询所有需求类型
     * @param data
     * @return
     */
    public Object findRequirementType(Object data){
		logger.info("PostWantService.findRequrieType>>>>>>>>>start");
    	HeadObject head = new HeadObject();
    	List<Requirement> list = new ArrayList<Requirement>();
    	try{
    		list = requirementMapper.selectAll();
            head.setRetCode(ErrorCode.SUCCESS);
        }catch (Exception e) {
            head.setRetCode(ErrorCode.FAILURE);
        	logger.error(e.toString());
        	e.printStackTrace();
		}
		logger.info("PostWantService.findRequrieType>>>>>>>>>end");
    	return new ResultObject(head, list);
    }
    
    /**
     * 根据用户ID查询是否存在联级数据，add by ssd
     * @param data
     * @return
     */
    public Object findByAccoutId(Object data){
    	HeadObject head = new HeadObject();
    	Integer  accountId =  (Integer) data;
    	int count = 0;
    	try{
    		count = requirementContentMapper.findByAccountId(accountId);
            head.setRetCode(ErrorCode.SUCCESS);
        }catch (Exception e) {
            head.setRetCode(ErrorCode.FAILURE);
        	logger.error(e.toString());
        	e.printStackTrace();
		}
    	return new ResultObject(head, count);
    } 
    
    /**
     * 发布需求
     * @param data
     * @return
     */
    public Object submit(Object data){
    	logger.info("PostWantService.submit>>>>>>>>>start");
    	HeadObject head = new HeadObject();
    	PostRequirement record = (PostRequirement)data;
    	try{
    		postRequirementMapper.insertSelective(record);
    		Long id = record.getId();
    		RequirementContent content = new RequirementContent();
    		content.setIsRoot(true);
    		content.setPostRequirementId(id);
    		content.setDescription(record.getContent());
    		content.setCreateTime(record.getCreateTime());
    		content.setAccountId(record.getAccountId());
    		content.setAccountType(record.getAccountType());
    		requirementContentMapper.insertSelective(content);
            head.setRetCode(ErrorCode.SUCCESS);
        }catch (Exception e) {
            head.setRetCode(ErrorCode.FAILURE);
        	logger.error(e.toString());
        	e.printStackTrace();
		}
    	logger.info("PostWantService.submit>>>>>>>>>end");
    	return head;
    }
    
    /**
     * 获取需求列表
     * @param obj
     * @return
     */
	public Object findRequirmentList(Object data){
    	logger.info("PostWantService.findRequirmentList>>>>>>>>>start");
    	HeadObject head = new HeadObject();
    	PostRequirementDTO record = (PostRequirementDTO)data;
    	int pageNum = record.getPage();
    	int pageSize = record.getRows();
    	
    	ResultPage<PostRequirement> result = null;
		PageHelper.startPage(pageNum,pageSize);
    	try{
    		result = new ResultPage<PostRequirement>(postRequirementMapper.selectList(record));
            head.setRetCode(ErrorCode.SUCCESS);
        }catch (Exception e) {
            head.setRetCode(ErrorCode.FAILURE);
        	logger.error(e.toString());
        	e.printStackTrace();
		}
    	logger.info("PostWantService.findRequirmentList>>>>>>>>>end");
    	return new ResultObject(head, JSON.toJSONString(result));
    }
    
    /**
     * 获取评论列表
     * @param data
     * @return
     */
    public Object findContentList(Object data){
    	logger.info("PostWantService.findContentList>>>>>>>>>start");
    	HeadObject head = new HeadObject();
    	Long requirementId = (Long)data;
    	String jsonStr = "" ;
    	try{
    		List<RequirementContent> list = requirementContentMapper.selectList(requirementId);
    		list = reset(list);
    		jsonStr = JSON.toJSONString(list);
            head.setRetCode(ErrorCode.SUCCESS);
        }catch (Exception e) {
            head.setRetCode(ErrorCode.FAILURE);
        	logger.error(e.toString());
        	e.printStackTrace();
		}
    	logger.info("PostWantService.findContentList>>>>>>>>>end");
    	return new ResultObject(head, jsonStr);
    }
    
    /**
     * 重新排序
     * @param List
     * @return
     */
    private List<RequirementContent> reset(List<RequirementContent> list){
    	List<RequirementContent> _list = new ArrayList<RequirementContent>();
    	Long parentId = null;
    	
    	//先拿到根节点
		for (RequirementContent node : list) {
			if(node.getIsRoot()){
				_list.add(node);
 				parentId = node.getId();
 				break;
 			}
		};
			
		getChild(list, _list, parentId);
    	return _list;
    }
    
    @SuppressWarnings("rawtypes")
	private void getChild(List<RequirementContent> list, List<RequirementContent> _list, Long parentId){
    	for (Iterator iterator = list.iterator(); iterator.hasNext();) {
    		RequirementContent content = (RequirementContent) iterator.next();
    		if(null != content.getParentId() && content.getParentId().equals(parentId)){
    			_list.add(content);
    			getChild(list,_list, content.getId());
    		}
    	}
    }
    
    /**
     * 需求回复
     * @param data
     * @return
     */
    public Object addResponse(Object data){
    	logger.info("PostWantService.addResponse>>>>>>>>>start");
    	HeadObject head = new HeadObject();
    	RequirementContent response = (RequirementContent)data;
    	Long parentId = response.getParentId();
    	try{
    		//如果屏蔽了要修改父节点的状态
    		if(!response.isYesOrNot()){
    			RequirementContent parent = new RequirementContent();
    			parent.setId(parentId);
    			parent.setDisabled(true);
    			requirementContentMapper.updateByPrimaryKeySelective(parent);
    		}
    		response.setIsRoot(false);
    		response.setCreateTime(new Date());
    		requirementContentMapper.insertSelective(response);
            head.setRetCode(ErrorCode.SUCCESS);
        }catch (Exception e) {
            head.setRetCode(ErrorCode.FAILURE);
        	logger.error(e.toString());
        	e.printStackTrace();
		}
    	logger.info("PostWantService.addResponse>>>>>>>>>end");
    	return head;
    }
    
    /**
     * 修改需求
     * @param data
     * @return
     */
    public Object updateRequirement(Object data){
    	HeadObject head = new HeadObject();
    	PostRequirement record = (PostRequirement)data;
    	logger.info("PostWantService.updateRequirement>>>>>>>>>start");
    	try{
    		postRequirementMapper.updateByPrimaryKeySelective(record);
            head.setRetCode(ErrorCode.SUCCESS);
        }catch (Exception e) {
            head.setRetCode(ErrorCode.FAILURE);
        	logger.error(e.toString());
        	e.printStackTrace();
		}
    	logger.info("PostWantService.updateRequirement>>>>>>>>>end");
    	return head;
    }
    
    /**
     * 买家、卖家获取需求及内容列表
     * @param obj
     * @return
     */
	public Object findList(Object data){
    	logger.info("PostWantService.findList>>>>>>>>>start");
    	HeadObject head = new HeadObject();
    	PostRequirementDTO record = (PostRequirementDTO)data;
    	int pageNum = record.getPage();
    	int pageSize = record.getRows();
    	
    	ResultPage<Object> result = null;
		PageHelper.startPage(pageNum,pageSize);
    	try{
    		result = new ResultPage<Object>(postRequirementMapper.selectIdList(record));
			if(result.getRows().size()>0){
				record.setIds(result.getRows());
				result.setRows(postRequirementMapper.selectMyList(record));
			}else{
				result.setRows(null);
			}
            head.setRetCode(ErrorCode.SUCCESS);
        }catch (Exception e) {
            head.setRetCode(ErrorCode.FAILURE);
        	logger.error(e.toString());
        	e.printStackTrace();
		}
    	logger.info("PostWantService.findList>>>>>>>>>end");
    	return new ResultObject(head, JSON.toJSONString(result));
    }
}
