package com.cnit.yoyo.myshiro;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

/**
 * 自定义验证器，验证用户输入密码的错误次数，如果达到5次则锁住账号
 * 次数主要从缓存中获取，配置也在eh缓存的配置文件中配置
* @author ssd
* @date 2015-4-30 下午3:54:12
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

	private Cache<String, AtomicInteger> passwordRetryMPCache;

    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
    	passwordRetryMPCache = cacheManager.getCache("passwordRetryMPCache");
    }

    /**
     * 判断用户输入密码的次数是否超过5次，如果超过则锁住账号一定时间
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String)token.getPrincipal();
        //retry num + 1
        AtomicInteger retryCount = passwordRetryMPCache.get(username);
        if(retryCount == null) {
            retryCount = new AtomicInteger(0);
            passwordRetryMPCache.put(username, retryCount);
        }
        if(retryCount.incrementAndGet() > 5) {
            //if retry num > 5 throw
            throw new ExcessiveAttemptsException();
        }

        boolean matches = super.doCredentialsMatch(token, info);
        if(matches) {
            //clear retry num
        	passwordRetryMPCache.remove(username);
        }
        return matches;
    }
}
