package com.cnit.yoyo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.util.StringUtil;

public class QueryParamObject implements Serializable {
	private static final long serialVersionUID = 3152903384696644062L;
	// 拼接SQL用的操作符
	public static final String BLACK_SPACE = " ";
	public static final String WILD_CARD = "%";
	public static final String COMMAM = " ,  ";
	public static final String QUATE = "'";
	public static final String EQUAL = " = ";
	public static final String BIG_OR_EQUAL = " >= ";
	public static final String SMALL_OR_EQUAL = " <= ";
	public static final String LIKE = " like ";
	// 参数名称常数
	public static final String PARAM_NAME_SUBFIX = "Q";
	public static final String LIKE_PARAM = "likes";
	public static final String RANGE_PARAM = "ranges";
	public static final String EQUAL_PARAM = "equals";
	public static final String INS_PARAM = "ins";
	public static final String MQL_LIST = "mql";
	public static final String ORDER_STR = "order";
	public static final String SORT_STR = "sort";
	public static final String PAGE = "page";
	public static final String ROWS = "rows";
	public static final String PARAM_NAME = "paramName";
	public static final String PARAM_VALUE = "paramValue";
	public static final String WILD_TYPE = "wildType";
	public static final String LEFT_LIKE = "left";
	public static final String RIGHT_LIKE = "right";
	public static final String BOTH_LIKE = "both";
	public static final String PARAM_MIN_VALUE = "paramMinValue";
	public static final String PARAM_MAX_VALUE = "paramMaxValue";
	private Integer page;
	private Integer rows;
	private String sort;
	private String order;
	private List<EqualParam> equals;
	private List<LikeParam> likes;
	private List<RangeParam> ranges;
	private List<InParam> ins;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public List<EqualParam> getEquals() {
		return equals;
	}

	public void setEquals(List<EqualParam> equals) {
		this.equals = equals;
	}

	public List<LikeParam> getLikes() {
		return likes;
	}

	public void setLikes(List<LikeParam> likes) {
		this.likes = likes;
	}

	public List<RangeParam> getRanges() {
		return ranges;
	}

	public void setRanges(List<RangeParam> ranges) {
		this.ranges = ranges;
	}

	public List<InParam> getIns() {
		return ins;
	}

	public void setIns(List<InParam> ins) {
		this.ins = ins;
	}

	public static String trimParamName(String paramName) {
		int subfixIndex = paramName.lastIndexOf(PARAM_NAME_SUBFIX);
		if (subfixIndex == -1) {
			return paramName;
		}
		return paramName.substring(0, paramName.lastIndexOf(PARAM_NAME_SUBFIX));
	}

	/**
	 * 将驼峰命名法命名的字符串转换成数据库字段名
	 * 
	 * @param name
	 * @return
	 */
	public static String convertToDBColumn(String name) {
		StringBuilder result = new StringBuilder();
		if (name != null && name.length() > 0) {
			// 保留前缀
			int prefixIndex = name.indexOf(".");
			if (prefixIndex != -1) {
				result.append(name.substring(0, prefixIndex + 1));
				name = name.substring(prefixIndex + 1);
			}
			// 将第一个字符处理成大写
			result.append(name.substring(0, 1).toUpperCase());
			// 循环处理其余字符
			for (int i = 1; i < name.length(); i++) {
				String s = name.substring(i, i + 1);
				// 在大写字母前添加下划线
				if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
					result.append("_");
				}
				// 其他字符直接转成大写
				result.append(s.toUpperCase());
			}
		}
		return result.toString();
	}

	/**
	 * 将数据库字段名转换成以驼峰命名法命名的字符串
	 * 
	 * @param name
	 * @return
	 */
	public static String convertToCamelName(String name) {
		StringBuilder result = new StringBuilder();
		// 快速检查
		if (name == null || name.isEmpty()) {
			// 没必要转换
			return "";
		} else if (!name.contains("_")) {
			// 不含下划线，仅将首字母小写
			return name.substring(0, 1).toLowerCase() + name.substring(1);
		}
		// 用下划线将原始字符串分割
		String camels[] = name.split("_");
		for (String camel : camels) {
			// 跳过原始字符串中开头、结尾的下换线或双重下划线
			if (camel.isEmpty()) {
				continue;
			}
			// 处理真正的驼峰片段
			if (result.length() == 0) {
				// 第一个驼峰片段，全部字母都小写
				result.append(camel.toLowerCase());
			} else {
				// 其他的驼峰片段，首字母大写
				result.append(camel.substring(0, 1).toUpperCase());
				result.append(camel.substring(1).toLowerCase());
			}
		}
		return result.toString();
	}

	public static String getOrderCause(String name, String type) {
		StringBuffer sb = new StringBuffer();
		String[] names = name.split(COMMAM);
		String[] types = type.split(COMMAM);
		for (int i = 0; i < names.length; i++) {
			sb.append(convertToDBColumn(names[i])).append(BLACK_SPACE).append(types[i]).append(COMMAM);
		}
		return sb.substring(0, sb.lastIndexOf(COMMAM));
	}

	/**
	 * 根据前台传的条件拼装MQL
	 * 
	 * @param content
	 * @return
	 */
	public static List<String> getMqls(JSONObject content) {
		List<String> mqlString = new ArrayList<String>();
		StringBuffer mql = new StringBuffer();
		// 模糊查询
		JSONArray likes = content.optJSONArray(QueryParamObject.LIKE_PARAM);
		if (likes != null) {
			for (Iterator iterator = likes.iterator(); iterator.hasNext();) {
				JSONObject likeParam = (JSONObject) iterator.next();
				String paramName = convertToDBColumn(trimParamName(likeParam.optString(QueryParamObject.PARAM_NAME)));
				String wideType = likeParam.optString(QueryParamObject.WILD_TYPE);
				mql.append(paramName).append(QueryParamObject.LIKE).append(QueryParamObject.QUATE);
				if (wideType == null || QueryParamObject.LEFT_LIKE.equalsIgnoreCase(wideType) || QueryParamObject.BOTH_LIKE.equalsIgnoreCase(wideType)) {
					mql.append(QueryParamObject.WILD_CARD);
				}
				mql.append(likeParam.opt(QueryParamObject.PARAM_VALUE));
				if (wideType == null || QueryParamObject.RIGHT_LIKE.equalsIgnoreCase(wideType) || QueryParamObject.BOTH_LIKE.equalsIgnoreCase(wideType)) {
					mql.append(QueryParamObject.WILD_CARD);
				}
				mql.append(QueryParamObject.QUATE);
				mqlString.add(mql.toString());
				mql.delete(0, mql.length());
			}
		}
		// 精确查询
		JSONArray equals = content.optJSONArray(QueryParamObject.EQUAL_PARAM);
		if (equals != null) {
			for (Iterator iterator = equals.iterator(); iterator.hasNext();) {
				JSONObject equalParam = (JSONObject) iterator.next();
				String paramName = convertToDBColumn(trimParamName(equalParam.optString(QueryParamObject.PARAM_NAME)));
				mql.append(paramName).append(QueryParamObject.EQUAL).append(QueryParamObject.QUATE)
						.append(equalParam.opt(QueryParamObject.PARAM_VALUE)).append(QueryParamObject.QUATE);
				mqlString.add(mql.toString());
				mql.delete(0, mql.length());
			}
		}
		// 范围查询
		JSONArray ranges = content.optJSONArray(QueryParamObject.RANGE_PARAM);
		if (ranges != null) {
			for (Iterator iterator = ranges.iterator(); iterator.hasNext();) {
				JSONObject rangeParam = (JSONObject) iterator.next();
				String paramName = convertToDBColumn(trimParamName(rangeParam.optString(QueryParamObject.PARAM_NAME)));
				if (!StringUtil.isEmpty(rangeParam.opt(QueryParamObject.PARAM_MIN_VALUE))) {
					mql.append(paramName).append(QueryParamObject.BIG_OR_EQUAL).append(rangeParam.opt(QueryParamObject.PARAM_MIN_VALUE));
					System.out.println(mql);
					mqlString.add(mql.toString());
					mql.delete(0, mql.length());
				}
				if (!StringUtil.isEmpty(rangeParam.opt(QueryParamObject.PARAM_MAX_VALUE))) {
					mql.append(paramName).append(QueryParamObject.SMALL_OR_EQUAL).append(rangeParam.opt(QueryParamObject.PARAM_MAX_VALUE));
					System.out.println(mql);
					mqlString.add(mql.toString());
					mql.delete(0, mql.length());
				}
			}
		}
		return mqlString;
	}

	/**
	 * 获取排序语句
	 * 
	 * @param content
	 * @return
	 */
	public static String getOrderByCause(JSONObject content) {
		// 排序字段
		String orderBy = null;
		if (!StringUtil.isEmpty(content.optString(QueryParamObject.SORT_STR))) {
			orderBy = QueryParamObject.getOrderCause(content.optString(QueryParamObject.SORT_STR),
					content.optString(QueryParamObject.ORDER_STR));
		} else {
			orderBy = QueryParamObject.getOrderCause(GlobalStatic.SORT_FIELD_DEFAULT, GlobalStatic.SORT_TYPE_DEFAULT);
		}
		return orderBy;
	}

	/**
	 * 获取当前页
	 * 
	 * @param content
	 * @return
	 */
	public static int getPage(JSONObject content) {
		if (!StringUtil.isEmpty(content.get(QueryParamObject.PAGE))) {
			return content.getInt(QueryParamObject.PAGE);
		} else {
			return 1;
		}
	}

	/**
	 * 获取当前页
	 * 
	 * @param content
	 * @return
	 */
	public static int getRows(JSONObject content) {
		if (!StringUtil.isEmpty(content.get(QueryParamObject.ROWS))) {
			return content.getInt(QueryParamObject.ROWS);
		} else {
			return 20;
		}
	}

	/**
	 * 精确查询条件
	 */
	public class EqualParam implements Serializable {
		private static final long serialVersionUID = 8579758233197114325L;
		private String paramName;
		private Object paramValue;

		public EqualParam() {
		}

		public EqualParam(String paramName, Object paramValue) {
			this.paramName = paramName;
			this.paramValue = paramValue;
		}

		public String getParamName() {
			return paramName;
		}

		public void setParamName(String paramName) {
			this.paramName = paramName;
		}

		public Object getParamValue() {
			return paramValue;
		}

		public void setParamValue(Object paramValue) {
			this.paramValue = paramValue;
		}
	}

	/**
	 * 模糊查询条件
	 */
	public class LikeParam implements Serializable {
		private static final long serialVersionUID = -4873242623999139141L;
		private String paramName;
		private Object paramValue;
		private String wildType;

		public String getParamName() {
			return paramName;
		}

		public void setParamName(String paramName) {
			this.paramName = paramName;
		}

		public Object getParamValue() {
			return paramValue;
		}

		public void setParamValue(Object paramValue) {
			this.paramValue = paramValue;
		}

		public String getWildType() {
			return wildType;
		}

		public void setWildType(String wildType) {
			this.wildType = wildType;
		}
	}

	/**
	 * 范围查询条件
	 */
	public class RangeParam implements Serializable {
		private static final long serialVersionUID = 3094571028246474631L;
		private String paramName;
		private Object paramMinValue;
		private Object paramMaxValue;

		public String getParamName() {
			return paramName;
		}

		public void setParamName(String paramName) {
			this.paramName = paramName;
		}

		public Object getParamMinValue() {
			return paramMinValue;
		}

		public void setParamMinValue(Object paramMinValue) {
			this.paramMinValue = paramMinValue;
		}

		public Object getParamMaxValue() {
			return paramMaxValue;
		}

		public void setParamMaxValue(Object paramMaxValue) {
			this.paramMaxValue = paramMaxValue;
		}

	}

	/**
	 * 限定查询条件
	 */
	public class InParam implements Serializable {
		private static final long serialVersionUID = -8124016424213308258L;
		private String paramName;
		private List paramValue;

		public String getParamName() {
			return paramName;
		}

		public void setParamName(String paramName) {
			this.paramName = paramName;
		}

		public List getParamValue() {
			return paramValue;
		}

		public void setParamValue(List paramValue) {
			this.paramValue = paramValue;
		}
	}
}
