package com.cnit.yoyo.myshiro.system.service;

import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.NamedFilterList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cnit.yoyo.model.system.UrlFilter;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 过滤器管理器
* @author ssd
* @date 2015-4-30 下午4:13:19
 */
@Service("shiroFilerChainManager")
public class ShiroFilerChainManager {

    @Autowired
    private DefaultFilterChainManager filterChainManager;

    public DefaultFilterChainManager getFilterChainManager() {
		return filterChainManager;
	}

	public void setFilterChainManager(DefaultFilterChainManager filterChainManager) {
		this.filterChainManager = filterChainManager;
	}

	private Map<String, NamedFilterList> defaultFilterChains;

    @PostConstruct
    public void init() {
        defaultFilterChains = new HashMap<String, NamedFilterList>(filterChainManager.getFilterChains());
    }

    /**
     * 
    *
    * @Description: 容器启动的时候加载库表中配置路径过滤器
    * @param @param urlFilters    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:16:33 
    * @return void    返回类型 
    * @throws
     */
    public void initFilterChains(List<UrlFilter> urlFilters) {
        //1、首先删除以前老的filter chain并注册默认的
        filterChainManager.getFilterChains().clear();
        if(defaultFilterChains != null) {
            filterChainManager.getFilterChains().putAll(defaultFilterChains);
        }

        //2、循环URL Filter 注册filter chain
        for (UrlFilter urlFilter : urlFilters) {
            String url = urlFilter.getUrl();
            //注册roles filter
            if (!StringUtils.isEmpty(urlFilter.getRoles())) {
                filterChainManager.addToChain(url, "roles", urlFilter.getRoles());
            }
            //注册perms filter
            if (!StringUtils.isEmpty(urlFilter.getPermissions())) {
                filterChainManager.addToChain(url, "perms", urlFilter.getPermissions());
            }
        }


    }

}
