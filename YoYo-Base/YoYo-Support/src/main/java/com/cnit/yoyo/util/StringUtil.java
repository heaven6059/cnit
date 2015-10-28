package com.cnit.yoyo.util;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.QueryParamObject;

public class StringUtil {
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D",
			"E", "F" };

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/**
	 * 转换字节数组为16进制字串
	 * 
	 * @param b
	 *            字节数组
	 * @return 16进制字串
	 */
	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	public static String MD5Encode(String origin) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
		}
		return resultString;
	}

	public static String replace(String src, String regex, String value) {
		if (!isEmpty(src))
			return src.replaceAll(regex, value);
		return src;
	}

	/**
	 * Example: subString("12345","1","4")=23
	 * 
	 * @param src
	 * @param start
	 * @param to
	 * @return
	 */
	public static Integer subStringToInteger(String src, String start, String to) {
		return stringToInteger(subString(src, start, to));
	}

	/**
	 * Example: subString("abcd","a","c")="b"
	 * 
	 * @param src
	 * @param start
	 *            null while start from index=0
	 * @param to
	 *            null while to index=src.length
	 * @return
	 */
	public static String subString(String src, String start, String to) {
		int indexFrom = start == null ? 0 : src.indexOf(start);
		int indexTo = to == null ? src.length() : src.indexOf(to);
		if (indexFrom < 0 || indexTo < 0 || indexFrom > indexTo) {
			return null;
		}

		if (null != start) {
			indexFrom += start.length();
		}

		return src.substring(indexFrom, indexTo);

	}

	/**
	 * @param in
	 * @return
	 */
	public static Integer stringToInteger(String in) {
		if (in == null) {
			return null;
		}
		in = in.trim();
		if (in.length() == 0) {
			return null;
		}

		try {
			return Integer.parseInt(in);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public static boolean equals(String a, String b) {
		if (a == null) {
			return b == null;
		}
		return a.equals(b);
	}

	public static boolean equalsIgnoreCase(String a, String b) {
		if (a == null) {
			return b == null;
		}
		return a.equalsIgnoreCase(b);
	}

	public static boolean isNotEmpty(Object value){
		return !isEmpty(value);
	}
	
	public static boolean isEmpty(Object value) {
		if (value != null) {
			if (value instanceof String) {
				if (((String) value).length() == 0) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	public static int lowerHashCode(String text) {
		if (text == null) {
			return 0;
		}
		// return text.toLowerCase().hashCode();
		int h = 0;
		for (int i = 0; i < text.length(); ++i) {
			char ch = text.charAt(i);
			if (ch >= 'A' && ch <= 'Z') {
				ch = (char) (ch + 32);
			}

			h = 31 * h + ch;
		}
		return h;
	}

	public static String getUUID() {
		return UUID.randomUUID().toString();
	}

	public static Object getValueIgnoreExecption(JSONObject content, String param) {
		Object obj = null;
		try {
			obj = content.get(param);
		} catch (Exception e) {
			System.out.println("字段[" + param + "]在[" + content + "]不存在！");
		}
		return obj;
	}
}
