package com.cnit.yoyo.util;

import java.math.BigDecimal;

import com.cnit.yoyo.domain.QueryParamObject;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * 将驼峰命名法命名的字段修改为对应数据库字段名
 */
public class JsonConvertToDBFieldProcessor implements JsonValueProcessor {
	private String paramNameSubfix;

	public JsonConvertToDBFieldProcessor() {
		super();
	}

	public JsonConvertToDBFieldProcessor(String paramNameSubfix) {
		super();
		this.paramNameSubfix = paramNameSubfix;
	}

	@Override
	public Object processArrayValue(Object paramObject,
			JsonConfig paramJsonConfig) {
		return process(paramObject);
	}

	@Override
	public Object processObjectValue(String propertyName, Object propertyValue,
			JsonConfig paramJsonConfig) {
		System.out.println(propertyName);
		if ("paramName".equalsIgnoreCase(propertyName)) {
			return process(propertyValue);
		} else {
			return propertyValue;
		}
	}

	private Object process(Object value) {
		if (value instanceof String) {
			String paramName = (String) value;
			int subfixIndex = paramName.lastIndexOf(this.paramNameSubfix);
			if (subfixIndex == -1) {
				return paramName;
			} else {
				return QueryParamObject.convertToDBColumn(paramName.substring(0,
						subfixIndex));
			}
		}
		return value == null ? "" : value.toString();
	}

}