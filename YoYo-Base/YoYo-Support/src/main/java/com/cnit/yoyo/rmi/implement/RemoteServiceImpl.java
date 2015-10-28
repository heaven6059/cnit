/**
 * 文 件 名   :  RemoteServiceImpl.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-2-5 上午9:48:53
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.rmi.implement;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Locale;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.exception.BusinessException;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.ApplicationContextUtil;
import com.cnit.yoyo.util.ErrorCodeHolder;
import com.cnit.yoyo.util.ServiceMapHolder;
import com.cnit.yoyo.util.StringUtil;

/**
 * @description <一句话功能简述>
 * @detail <功能详细描述>
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
public class RemoteServiceImpl implements RemoteService {
    private static final Logger log = LoggerFactory.getLogger(RemoteServiceImpl.class);

    @Autowired  
    private MongoTemplate mongoTemplate;
    
    /**
     * @description 服务之前（暂时弃用）
     * @detail 把请求头部信息存数据库
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-2-5
     * @param requestObject
     */
    private void preService(RequestObject requestObject) {
        log.info("请求报文：【"+requestObject.toString()+"】");
    }

    /**
     * @see com.cnit.yoyo.rmi.interfaces.RemoteService#doService(com.cnit.yoyo.domain.RequestObject)
     */
    public ResultObject doService(RequestObject requestObject) {
        preService(requestObject);
        HeadObject reqhead = requestObject.getHead();
        Object content = null;
        // 根据请求头部信息中的服务标志选择服务提供者
        String serviceCode = reqhead.getServiceCode();
        try {
            if (!StringUtil.isEmpty(serviceCode)) {
                String serviceProvider = ServiceMapHolder.getServiceProvider(serviceCode);
                if (!StringUtil.isEmpty(serviceProvider)) {
                    String serviceClass = serviceProvider.split("\\.")[0];
                    String serviceMethod = serviceProvider.split("\\.")[1];
                    if (!StringUtil.isEmpty(serviceClass) && !StringUtil.isEmpty(serviceMethod)) {
                        // 从spring容器中获得服务提供者
                        Object provider = ApplicationContextUtil.getBeanByName(serviceClass);
                        // 根据方法名及参数类型获得方法对象
                        Method method = provider.getClass().getMethod(serviceMethod, Object.class);
                        // 调用方法
                        content = method.invoke(provider, requestObject.getContent());
                        
                    }
                } else {
                    throw new BusinessException(ErrorCode.SPE001);
                }
            } else {
                throw new BusinessException(ErrorCode.SPE001);
            }
        } catch (Throwable e) {
            log.error("执行服务异常.服务执行前信息:{},异常信息:{}",requestObject.toString(),e);
            reqhead = doException(e, reqhead);
        }
        if(null!=content && content!=""){
        	mongoTemplate.save(content);
        }
        return postService(reqhead, content);
    }
    public Object doServiceByServer(RequestObject requestObject){
    	preService(requestObject);
        HeadObject reqhead = requestObject.getHead();
        Object content = null;
        // 根据请求头部信息中的服务标志选择服务提供者
        String serviceCode = reqhead.getServiceCode();
        //参数类型
        try {
            if (!StringUtil.isEmpty(serviceCode)) {
                if (!StringUtil.isEmpty(serviceCode)) {
                    String serviceClass = serviceCode.split("\\.")[0];
                    String serviceMethod = serviceCode.split("\\.")[1];
                    if (!StringUtil.isEmpty(serviceClass) && !StringUtil.isEmpty(serviceMethod)) {
                        // 从spring容器中获得服务提供者
                        Object provider = ApplicationContextUtil.getBeanByName(serviceClass);
                        // 根据方法名及参数类型获得方法对象
                        Method method = provider.getClass().getMethod(serviceMethod, Object.class);
                        // 调用方法
                        content = method.invoke(provider, requestObject.getContent());
                    }
                } else {
                    throw new BusinessException(ErrorCode.SPE001);
                }
            } else {
                throw new BusinessException(ErrorCode.SPE001);
            }
        } catch (Throwable e) {
            log.error(e.getMessage());
            reqhead = doException(e, reqhead);
        }
        if(null!=content && content!=""){
        	mongoTemplate.save(content);
        }
        return content;
    }
    
    public static void main(String[] args) {
    	String s="[{'children':[{'children':[{'children':[{'children':[{'children':[],'id':'98','name':'更新雨刮器','pId':'97'}],'id':'97','name':'外部维修','pId':'33'}],'id':'33','name':'小保养','pId':'31'}],'id':'31','name':'常规保养','pId':'28'},{'children':[],'id':'32','name':'常规维修','pId':'28'}],'id':'28','name':'保养','pId':'0'}]";
    	System.out.println(buidlHTML(JSONArray.fromObject(s),""));
	}
    private static String buidlHTML(JSONArray array,String html){
    	for (Object object : array) {
			JSONObject obj=(JSONObject) object;
			if(obj.getJSONArray("children").size()>0){
				html+=obj.getString("name")+">";
				System.out.println(html);
				return buidlHTML(obj.getJSONArray("children"),html);
			}
		}
    	return html;
    }
    
    private HeadObject doException(Throwable ex, HeadObject head) {
    	ex.printStackTrace();
//    	if (ex.getCause().getClass().toString().equals(NullPointerException.class)) {
//            head.setRetCode(ErrorCode.SPE002);
//        } else if (ex.getCause().getClass().equals(IOException.class)) {
//            head.setRetCode(ErrorCode.SPE003);
//        } else if (ex.getCause().getClass().equals(ClassNotFoundException.class)) {
//            head.setRetCode(ErrorCode.SPE004);
//        } else if (ex.getCause().getClass().equals(ArithmeticException.class)) {
//            head.setRetCode(ErrorCode.SPE005);
//        } else if (ex.getCause().getClass().equals(ArrayIndexOutOfBoundsException.class)) {
//            head.setRetCode(ErrorCode.SPE006);
//        } else if (ex.getCause().getClass().equals(IllegalArgumentException.class)) {
//            head.setRetCode(ErrorCode.SPE007);
//        } else if (ex.getCause().getClass().equals(ClassCastException.class)) {
//            head.setRetCode(ErrorCode.SPE008);
//        } else if (ex.getCause().getClass().equals(SecurityException.class)) {
//            head.setRetCode(ErrorCode.SPE009);
//        } else if (ex.getCause().getClass().equals(NoSuchMethodError.class)) {
//            head.setRetCode(ErrorCode.SPE010);
//        } else if (ex.getCause().getClass().equals(InternalError.class)) {
//            head.setRetCode(ErrorCode.SEE002);
//        } else if (ex.getCause().getClass().equals(BusinessException.class)) {
////            BusinessException be = (BusinessException) ex;
////            head.setRetCode(ErrorCodeHolder.getErrorCode(be.getErrorType()));
////            if (GlobalStatic.RET_TYPE_OTHER.equalsIgnoreCase(be.getErrorSource())) {
////                head.setRetType(GlobalStatic.RET_TYPE_OTHER);
////                head.setRetMsg(be.getErrorMsg());
////            }
//            head.setRetCode(ErrorCode.SEE001);
//        } else {
//            String errorType = ex.getCause().getClass().getSimpleName();
//            head.setRetCode(ErrorCodeHolder.getErrorCode(errorType));
//        }
    	head.setRetCode(ErrorCode.FAILURE);
        return head;
    }

    /**
     * @description 服务之后（暂时弃用）
     * @detail 把返回头部信息存数据库
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-2-5
     * @param requestObject
     * @return
     */
    private ResultObject postService(HeadObject head, Object content) {
        ResultObject resultObject = null;
        if (content instanceof HeadObject) {
            resultObject = new ResultObject();
            head = (HeadObject) content;
        } else if (content instanceof ResultObject) {
            resultObject = (ResultObject) content;
            head.setRetCode(resultObject.getHead().getRetCode());
            head.setRetType(resultObject.getHead().getRetType());
            head.setRetMsg(resultObject.getHead().getRetMsg());
        }else{ //抛出BusinessException 则content==null
            head.setRetCode(ErrorCode.FAILURE);
            resultObject = new ResultObject(head);
        }
        // 服务端客户端标志交换
        String temp = head.getProviderCode();
        head.setProviderCode(head.getConsumerCode());
        head.setConsumerCode(temp);
        head.setDateTime(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
        if (!GlobalStatic.RET_TYPE_OTHER.equalsIgnoreCase(head.getRetType())) {
//            head.setRetMsg(ApplicationContextUtil.getMessage(head.getRetCode(), null, head.getLocale()));
        }
        resultObject.setHead(head);
        log.info("响应报文：【"+resultObject.toString()+"】");
        return resultObject;
    }
}
