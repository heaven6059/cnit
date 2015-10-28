/**
 * 文 件 名   :  OauthService.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  &lt;描述&gt;
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-1-27 下午2:19:00
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.rmi.member.service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Properties;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.util.StringUtil;

/**
 * @description 第三方授权登录服务
 * @detail <功能详细描述>
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
public class OauthService {
    private static final Log log = LogFactory.getLog(OauthService.class);
    private static final String PARAM_SEPARATOR = "&";
    private static Properties properties;
    private static Resource configLocation;

    public Resource getConfigLocation() {
        return configLocation;
    }

    /**
     * @description 读取注入的配置文件并封装到Properties
     * @detail <方法详细描述>
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-2-2
     * @param configLocation
     */
    public void setConfigLocation(Resource configLocation) {
        this.configLocation = configLocation;
        if (this.configLocation != null) {
            properties = new Properties();
            try {
                properties.load(configLocation.getInputStream());
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }
    
    private Object getSinaAuthorizeURL(Object callback) throws Exception {
        HeadObject ho = new HeadObject();
        StringBuffer authorizeURL = new StringBuffer();
        String serverUrl = properties.getProperty("oauth.sina.authorizeURL");
        if (!StringUtil.isEmpty(serverUrl)) {
            authorizeURL.append(serverUrl);
            authorizeURL.append(properties.getProperty("oauth.sina.param.client_ID")).append(properties.getProperty("oauth.sina.client_ID"));
            authorizeURL.append(PARAM_SEPARATOR);
            authorizeURL.append(properties.getProperty("oauth.sina.param.redirect_URI")).append(properties.getProperty("oauth.sina.redirect_URI"));
            authorizeURL.append(PARAM_SEPARATOR);
            authorizeURL.append(properties.getProperty("oauth.sina.param.response_type")).append(properties.getProperty("oauth.sina.response_type"));
            authorizeURL.append(PARAM_SEPARATOR);
            authorizeURL.append("state=").append("").append("&scope=").append("all");
            ho.setRetCode(ErrorCode.SUCCESS);
        } else {
            ho.setRetCode(ErrorCode.SEE001);
            ho.setRetMsg("无有效第三方配置信息！");
        }
        return new ResultObject(ho,authorizeURL.toString());
    }
    

    private Object getQQAuthorizeURL(Object callback) throws Exception {
        HeadObject ho = new HeadObject();
        StringBuffer authorizeURL = new StringBuffer();
        String serverUrl = properties.getProperty("oauth.qq.authorizeURL");
        if (!StringUtil.isEmpty(serverUrl)) {
            authorizeURL.append(serverUrl);
            authorizeURL.append(properties.getProperty("oauth.qq.param.appId")).append(properties.getProperty("oauth.qq.appId"));
            authorizeURL.append(PARAM_SEPARATOR);
            authorizeURL.append(properties.getProperty("oauth.qq.param.responseType")).append("code");
            authorizeURL.append(PARAM_SEPARATOR);
            authorizeURL.append(properties.getProperty("oauth.qq.param.scope")).append(properties.getProperty("oauth.qq.scopelist"));
            authorizeURL.append(PARAM_SEPARATOR);
            authorizeURL.append(properties.getProperty("oauth.qq.param.redirectUri")).append(URLEncoder.encode((String) callback, "UTF-8"));
            ho.setRetCode(ErrorCode.SUCCESS);
        } else {
            ho.setRetCode(ErrorCode.SEE001);
            ho.setRetMsg("无有效第三方配置信息！");
        }
        return new ResultObject(ho,authorizeURL.toString());
    }

    private Object getWeiChatAuthorizeURL(Object callback) throws Exception {
        HeadObject ho = new HeadObject();
        StringBuffer authorizeURL = new StringBuffer();
        String serverUrl = properties.getProperty("oauth.weixin.authorizeURL");
        if (!StringUtil.isEmpty(serverUrl)) {
            authorizeURL.append(serverUrl);
            authorizeURL.append(properties.getProperty("oauth.weixin.param.appId")).append(properties.getProperty("oauth.weixin.appId"));
            authorizeURL.append(PARAM_SEPARATOR);
            authorizeURL.append(properties.getProperty("oauth.weixin.param.responseType")).append("code");
            authorizeURL.append(PARAM_SEPARATOR);
            authorizeURL.append(properties.getProperty("oauth.weixin.param.scope")).append(properties.getProperty("oauth.weixin.scopelist"));
            authorizeURL.append(PARAM_SEPARATOR);
            authorizeURL.append(properties.getProperty("oauth.weixin.param.redirectUri")).append(URLEncoder.encode((String) callback, "UTF-8"));
            ho.setRetCode(ErrorCode.SUCCESS);
        } else {
            ho.setRetCode(ErrorCode.SEE001);
            ho.setRetMsg("无有效第三方配置信息！");
        }
        return new ResultObject(ho,authorizeURL.toString());
    }

    public Object getAuthorizeURL(Object data) throws Exception {
        JSONObject jsonData = JSONObject.fromObject(data);
        String thirdPart = jsonData.getString("thirdPart");
        String callback = jsonData.getString("callback");
        if (GlobalStatic.THIRD_PART_QQ.equalsIgnoreCase(thirdPart)) {
            return getQQAuthorizeURL(callback);
        } else if (GlobalStatic.THIRD_PART_WX.equalsIgnoreCase(thirdPart)) {
            return getWeiChatAuthorizeURL(callback);
        } else if(GlobalStatic.THIRD_PART_SN.equalsIgnoreCase(thirdPart)){
        	return getSinaAuthorizeURL(callback);
        }
        else {
            return new ResultObject();
        }
    }
}
