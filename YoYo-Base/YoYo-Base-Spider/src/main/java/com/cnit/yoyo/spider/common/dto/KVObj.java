package com.cnit.yoyo.spider.common.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KVObj implements Serializable{
	private static final long serialVersionUID = 8315401506303372688L;
	
	private Object key;
	private Object value;

	public KVObj(Object key, Object value) {
		super();
		this.key = key;
		this.value = value;
	}

	public Object getKey() {
		return key;
	}

	public void setKey(Object key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public static <K,V> Map<K,V> toMap(List<KVObj> kvList){
		Map<K,V> kvMap = new HashMap<K, V>();
		for(KVObj kv : kvList){
			K k  = (K) kv.getKey();
			V v = (V) kv.getValue();
			kvMap.put(k, v);
		}
		return kvMap;
	}

}
