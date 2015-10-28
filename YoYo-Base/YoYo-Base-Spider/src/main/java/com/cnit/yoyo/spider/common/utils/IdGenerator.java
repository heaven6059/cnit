package com.cnit.yoyo.spider.common.utils;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 主建id生成器
 * @Author:yangyi  
 * @Date:2015年6月8日  
 * @Version:1.0.0
 */
public abstract class IdGenerator {

	// 记录在内存
	public static final Map<String, AtomicInteger>	RECORD_INT_MAP	= new ConcurrentHashMap<String, AtomicInteger>();
	public static final Map<String, AtomicLong>		RECORD_LONG_MAP	= new ConcurrentHashMap<String, AtomicLong>();

	/**
	 * 获取下一个int
	 * @param key
	 * @param startVal
	 * @return
	 */
	public static Integer getNI(String key, Integer... startVal) {
		Set<String> keySet = RECORD_INT_MAP.keySet();
		for (String temp : keySet) {
			if (StringUtils.equalsIgnoreCase(temp, key)) { return RECORD_INT_MAP.get(key).incrementAndGet(); }
		}

		if (ArrayUtils.isNotEmpty(startVal)) {
			AtomicInteger temp = new AtomicInteger(startVal[0]);
			RECORD_INT_MAP.put(key, temp);
			return temp.intValue();
		} else {
			AtomicInteger temp = new AtomicInteger(100000);
			RECORD_INT_MAP.put(key, temp);
			return temp.intValue();
		}
	}
	
	
	/**
	 * 获取下一个Long
	 * @param key
	 * @param startVal
	 * @return
	 */
	public static Long getNL(String key, Long... startVal) {
		Set<String> keySet = RECORD_LONG_MAP.keySet();
		for (String temp : keySet) {
			if (StringUtils.equalsIgnoreCase(temp, key)) { return RECORD_LONG_MAP.get(key).incrementAndGet(); }
		}
		
		if (ArrayUtils.isNotEmpty(startVal)) {
			AtomicLong temp = new AtomicLong(startVal[0]);
			RECORD_LONG_MAP.put(key, temp);
			return temp.longValue();
		} else {
			AtomicLong temp = new AtomicLong(100000);
			RECORD_LONG_MAP.put(key, temp);
			return temp.longValue();
		}
	}

}
