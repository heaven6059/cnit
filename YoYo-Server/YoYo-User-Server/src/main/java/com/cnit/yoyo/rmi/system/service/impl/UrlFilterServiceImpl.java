package com.cnit.yoyo.rmi.system.service.impl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.dao.system.UrlFilterMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.PamAccount;
import com.cnit.yoyo.model.system.RoleUserLink;
import com.cnit.yoyo.model.system.UrlFilter;
import com.cnit.yoyo.model.system.dto.UserAddDTO;
import com.cnit.yoyo.model.system.dto.UserListDTO;
import com.cnit.yoyo.util.DateUtils;
import com.cnit.yoyo.util.StringUtil;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

import java.util.List;
import java.util.Map;

/**
 * 路径过滤器管理类
* @author ssd
* @date 2015-4-30 下午4:47:25
 */
@Service("urlFilterServiceImpl")
public class UrlFilterServiceImpl {

    @Autowired
    private UrlFilterMapper urlFilterMapper;

    /**
     * 
    *
    * @Description: 新增路径过滤器 
    * @param @param urlFilter
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:47:37 
    * @return UrlFilter    返回类型 
    * @throws
     */
    public UrlFilter createUrlFilter(UrlFilter urlFilter) {
        urlFilterMapper.insert(urlFilter);
        return urlFilter;
    }

    /**
     * 
    *
    * @Description: 更新路径过滤器
    * @param @param urlFilter
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:47:57 
    * @return UrlFilter    返回类型 
    * @throws
     */
    public UrlFilter updateUrlFilter(UrlFilter urlFilter) {
        urlFilterMapper.updateByPrimaryKey(urlFilter);
        return urlFilter;
    }

    /**
     * 
    *
    * @Description: 删除路径过滤器 
    * @param @param urlFilterId    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:48:16 
    * @return void    返回类型 
    * @throws
     */
    public void deleteUrlFilter(Integer urlFilterId) {
        urlFilterMapper.deleteByPrimaryKey(urlFilterId);
    }

    /**
     * 
    *
    * @Description: 查找路径过滤器 
    * @param @param urlFilterId
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:48:30 
    * @return UrlFilter    返回类型 
    * @throws
     */
    public UrlFilter findOne(Integer urlFilterId) {
        return urlFilterMapper.selectByPrimaryKey(urlFilterId);
    }

    /**
     * 
    *
    * @Description: 查找所有路径过滤器 
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:48:41 
    * @return List<UrlFilter>    返回类型 
    * @throws
     */
    public List<UrlFilter> findAll() {
        return urlFilterMapper.findAll();
    }
    
    /**
     * 
    *
    * @Description: 分页查找所有路径过滤器
    * @param @param data
    * @param @return
    * @param @throws Exception    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:49:03 
    * @return Object    返回类型 
    * @throws
     */
    public Object findFilterAll(Object data) throws Exception {
        HeadObject head = new HeadObject();
        List<UrlFilter> page = findAll();
        head.setRetCode(ErrorCode.SUCCESS);
        return new ResultObject(head, page);
    }
    
    /**
     * 
    *
    * @Description:获取页面路径信息列表
    * @param @param data
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:43:47 
    * @return Object    返回类型 
    * @throws
     */
    public Object getUrlManagerList(Object data) {
    	Map<String, Object> params = (Map<String, Object>) data;
		PageHelper.startPage(StringUtil.isEmpty(params.get("pageIndex"))?GlobalStatic.DEFAULT_PAGE_INDEX :(Integer)params.get("pageIndex"), StringUtil.isEmpty(params.get("pageSize"))?GlobalStatic.DEFAULT_PAGE_SIZE:(Integer)params.get("pageSize"));
        ResultPage<UrlFilter> page = new ResultPage<UrlFilter>(urlFilterMapper.findAll());
        JSONObject json = JSONObject.fromObject(page);
        return new ResultObject(new HeadObject(ErrorCode.SUCCESS), json);
    }
    
    /**
     * 
    *
    * @Description: 添加路径信息
    * @param @param data
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-22 下午5:40:15 
    * @return Object    返回类型 
    * @throws
     */
    public Object insertUrl(Object data) {
		HeadObject head = new HeadObject();
		try {
			JSONObject content = JSONObject.fromObject(data);
			UrlFilter urlFilter = (UrlFilter) JSONObject.toBean(content,UrlFilter.class);
			createUrlFilter(urlFilter);
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head);
	}
    
    /**
     * 
    *
    * @Description: 更新路径信息
    * @param @param data
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-29 下午7:46:11 
    * @return Object    返回类型 
    * @throws
     */
    public Object updateUrl(Object data) {
		HeadObject head = new HeadObject();
		try {
			JSONObject content = JSONObject.fromObject(data);
			UrlFilter urlFilter = (UrlFilter) JSONObject.toBean(content,UrlFilter.class);
			UrlFilter urlF = urlFilterMapper.selectByPrimaryKey(urlFilter.getId());
			if(null != urlF) {
				updateUrlFilter(urlFilter);
				
				head.setRetCode(ErrorCode.SUCCESS);
			}else {
				head.setRetCode(ErrorCode.NO_DATA);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head);
	}
    
    /**
     * 
    *
    * @Description: 删除路径信息
    * @param @param data
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-29 下午7:00:44 
    * @return Object    返回类型 
    * @throws
     */
    public Object deleteUrl(Object data){
    	HeadObject head = new HeadObject();
        	try{
	      	  	Integer  id =  (Integer) data;
	      	  deleteUrlFilter(id);
	      	head.setRetCode(ErrorCode.SUCCESS);
	        }catch(Exception e){    
	     	   e.printStackTrace();
	           head.setRetCode(ErrorCode.FAILURE);
	        }       
        return new ResultObject(head);
    }

}
