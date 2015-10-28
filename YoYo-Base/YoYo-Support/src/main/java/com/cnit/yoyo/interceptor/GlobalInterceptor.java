/**
 * 文 件 名   :  GlobalInterceptor.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-2-3 上午10:25:21
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.interceptor;

import java.io.IOException;
import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.exception.BusinessException;

/**
 * @description <一句话功能简述>
 * @detail <功能详细描述>
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
public class GlobalInterceptor implements MethodInterceptor {

    private Object doInvoke(MethodInvocation methodInvocation) {
        Class targetClass = methodInvocation.getThis().getClass();
        Logger log = LogManager.getLogger(targetClass);
        Method method = methodInvocation.getMethod();
        Object result = null;
        try {
            log.info(targetClass.getName() + "." + methodInvocation.getMethod().getName() + ":BEGIN!");// 方法前的操作
            result = (ResultObject) methodInvocation.proceed();
            log.info(targetClass.getName() + "." + methodInvocation.getMethod().getName() + ":END!");// 方法后的操作
        } catch (Throwable e) {
            log.error(e.getMessage());
            // 方法执行出异常
            result = doException(e);
            log.info(targetClass.getName() + "." + methodInvocation.getMethod().getName() + ":EXCEPTION END!");// 方法后的操作
        }
        return result;
    }

    /**
     * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
     */
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        return doInvoke(methodInvocation);
    }

    private Object doException(Throwable ex) {
        HeadObject head = new HeadObject();
        // 在这里判断异常，根据不同的异常返回错误。
        if (ex.getClass().toString().equals(NullPointerException.class.toString())) {
            head.setRetCode(ErrorCode.SPE002);
        } else if (ex.getClass().equals(IOException.class)) {
            head.setRetCode(ErrorCode.SPE003);
        } else if (ex.getClass().equals(ClassNotFoundException.class)) {
            head.setRetCode(ErrorCode.SPE004);
        } else if (ex.getClass().equals(ArithmeticException.class)) {
            head.setRetCode(ErrorCode.SPE005);
        } else if (ex.getClass().equals(ArrayIndexOutOfBoundsException.class)) {
            head.setRetCode(ErrorCode.SPE006);
        } else if (ex.getClass().equals(IllegalArgumentException.class)) {
            head.setRetCode(ErrorCode.SPE007);
        } else if (ex.getClass().equals(ClassCastException.class)) {
            head.setRetCode(ErrorCode.SPE008);
        } else if (ex.getClass().equals(SecurityException.class)) {
            head.setRetCode(ErrorCode.SPE009);
        } else if (ex.getClass().equals(NoSuchMethodError.class)) {
            head.setRetCode(ErrorCode.SPE010);
        } else if (ex.getClass().equals(InternalError.class)) {
            head.setRetCode(ErrorCode.SEE002);
        } else if(ex.getClass().equals(BusinessException.class)){
            BusinessException be = (BusinessException)ex;
            head.setRetCode(be.getErrorCode());
            if(GlobalStatic.RET_TYPE_OTHER.equalsIgnoreCase(be.getErrorSource())){
                head.setRetType(GlobalStatic.RET_TYPE_OTHER);
                head.setRetMsg(be.getErrorMsg());
            }
        }
        return head;
    }
}
