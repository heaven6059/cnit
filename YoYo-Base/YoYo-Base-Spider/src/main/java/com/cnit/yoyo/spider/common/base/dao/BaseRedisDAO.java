package com.cnit.yoyo.spider.common.base.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.io.Serializable;

public class BaseRedisDAO
{
    @Autowired
    protected RedisTemplate<Serializable, Serializable> redisTemplate;

    public RedisTemplate<Serializable, Serializable> getRedisTemplate()
    {
        return redisTemplate;
    }

    public RedisSerializer<String> getRedisSerializer()
    {
        return redisTemplate.getStringSerializer();
    }
}
