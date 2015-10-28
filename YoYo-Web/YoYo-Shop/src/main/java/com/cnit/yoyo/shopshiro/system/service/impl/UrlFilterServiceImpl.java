package com.cnit.yoyo.shopshiro.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.system.UrlFilter;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.shopshiro.system.service.ShiroFilerChainManager;
import com.cnit.yoyo.shopshiro.system.service.UrlFilterService;
import com.cnit.yoyo.util.CommonHeadUtil;

import javax.annotation.PostConstruct;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 路径过滤器管理类
* @author ssd
* @date 2015-4-30 下午4:22:27
 */
//@Service("urlFilterService")
public class UrlFilterServiceImpl implements UrlFilterService {

    //@Autowired
    //private ShiroFilerChainManager shiroFilerChainManager;
    
    @Autowired
    private RemoteService memberService;

    /**
     * 新增路径过滤器
     */
    public UrlFilter createUrlFilter(UrlFilter urlFilter) throws Exception {
        //urlFilterDao.insert(urlFilter);
        initFilterChain();
        return urlFilter;
    }



    /**
     * 更新路径过滤器
     */
    public UrlFilter updateUrlFilter(UrlFilter urlFilter) throws Exception {
        //urlFilterDao.updateByPrimaryKey(urlFilter);
        initFilterChain();
        return urlFilter;
    }

    /**
     * 删除路径过滤器
     */
    public void deleteUrlFilter(Long urlFilterId) throws Exception {
       // urlFilterDao.deleteByPrimaryKey(urlFilterId);
        initFilterChain();
    }

    /**
     * 查找路径过滤器
     */
    public UrlFilter findOne(Long urlFilterId) {
        return null;//urlFilterDao.selectByPrimaryKey(urlFilterId);
    }
    

    /**
     * 查找所有路径过滤器
     */
    @SuppressWarnings("unchecked")
	public List<UrlFilter> findAll() throws Exception {
    	HeadObject headObject = CommonHeadUtil
                .geneHeadObject(null, "3000020103-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_FRONT);
        Map<String,Object> data = new HashMap<String, Object>();
        ResultObject resultObject = memberService.doService(new RequestObject(headObject,data));
    	List<UrlFilter> lists = (List<UrlFilter>) resultObject.getContent();
    	/*UrlFilter urlFilter = new UrlFilter();
    	urlFilter.setId(1);
    	urlFilter.setName("admin");
    	urlFilter.setRoles("admin");
    	urlFilter.setUrl("/login/**");
    	List<UrlFilter> lists = new ArrayList<UrlFilter>();
    	lists.add(urlFilter);*/
        return lists;
    }

    /**
     * 
    *
    * @Description:生成新的路径过滤器，该方法会把shiro上面的路径过滤器给清除掉，然后再初始化这些
    * @param @throws Exception    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:23:55 
    * @return void    返回类型 
    * @throws
     */
    //@PostConstruct
    public void initFilterChain() throws Exception {
       // shiroFilerChainManager.initFilterChains(findAll());
    }


}
