/**
 * 文 件 名   :  CommonHeadUtil.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-2-5 下午1:59:24
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.util;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import redis.clients.jedis.Jedis;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.MemberListDo;

/**
 * 公共工具类
 * 
 * @author wanghb
 * @version 1.0.0
 * @copyright CNIT
 */
public class CommonUtil {

	public Jedis jedis;

	public CommonUtil() {
		jedis = new Jedis("10.255.8.127", 6379);
	}

	/**
	 * 获取当前项目session
	 * 
	 * @param request
	 * @return
	 */
	public static HttpSession getSession(HttpServletRequest request) {
		CommonUtil CommonUtil = new CommonUtil();
		return CommonUtil.readSession(request);
	}

	private HttpSession readSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			String sessionId = request.getSession().getId();
			String accountInfoStr = jedis.get(sessionId);
			String password = jedis.get(sessionId + "password");
			if (StringUtils.isNotEmpty(accountInfoStr)) {
				request.getSession().setAttribute(GlobalStatic.USER_INFO, accountInfoStr.toString());
				request.getSession().setAttribute("password", password);
				JSONObject accountInfo = JSONObject.fromObject(accountInfoStr);
				request.getSession().setAttribute("loginName", accountInfo.getString("loginName"));
				request.getSession().setAttribute("loginStatus", GlobalStatic.ACCOUNT_STATUS_ON);
				request.getSession().setAttribute("accountId", accountInfo.getInt("accountId"));
				request.getSession().setAttribute("accountType", accountInfo.optInt("accountType"));
				request.getSession().setAttribute("storeId", accountInfo.optInt("storeId"));
				request.getSession().setAttribute("companyId", Integer.parseInt(jedis.get(sessionId + "companyId")));
				request.getSession().setAttribute("companyName", jedis.get(sessionId + "companyName"));
				request.getSession().setAttribute("gradeType", accountInfo.optString("gradeType")); // 店铺等级类型：单店，集团
				request.getSession().setAttribute("memberId", accountInfo.optString("memberId")); // 会员ID
				request.getSession().setAttribute("loginImg", accountInfo.optString("loginImg")); // 第三方图片url
				request.getSession().setAttribute("nickName", accountInfo.getString("nickName")); // 第三方账户别名
			} else {
				request.getSession().setAttribute(GlobalStatic.USER_INFO, "");
				request.getSession().setAttribute("password", "");
				request.getSession().setAttribute("loginName", "");
				request.getSession().setAttribute("loginStatus", GlobalStatic.ACCOUNT_STATUS_OFF);
				request.getSession().setAttribute("accountId", 0);
				request.getSession().setAttribute("accountType", 0);
				request.getSession().setAttribute("storeId", 0);
				request.getSession().setAttribute("companyId", 0);
				request.getSession().setAttribute("companyName", "");
				request.getSession().setAttribute("gradeType", ""); // 店铺等级类型：单店，集团
				request.getSession().setAttribute("memberId", ""); // 会员ID
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return session;
	}

	public static MemberListDo getMemberListDo(HttpServletRequest request) {
		try {
			String accountInfo = (String) CommonUtil.getSession(request).getAttribute(GlobalStatic.USER_INFO);// 获取当前用户信息
			if (StringUtil.isEmpty(accountInfo)) {
				return null;
			}
			JSONObject account = JSONObject.fromObject(accountInfo);
			MemberListDo memberDo = (MemberListDo) JSONObject.toBean(account, MemberListDo.class);
			return memberDo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static ResultObject notLoign(HeadObject head) {
		head.setRetCode(ErrorCode.NOT_LOGIN);
		head.setRetMsg("未获取到登录信息");
		return new ResultObject(head);
	}

	public static ResultObject processExpction(HeadObject headObject) {
		headObject.setRetCode(ErrorCode.FAILURE);
		headObject.setRetMsg("数据处理异常");
		return new ResultObject(headObject);
	}

	public static Object getJsonValue(JSONObject obj, Object key) {
		Object result = null;
		try {
			result = obj.get(key);
		} catch (Exception e) {
		}
		return result;
	}

	public static String getRandomChar() {
		return getRandomChar("");
	}

	public static String getRandomChar(String prefix) {
		StringBuffer sb = new StringBuffer(prefix);
		sb.append(System.currentTimeMillis());
		for (int i = 0; i <= 9; i++) {
			sb.append(Math.round(Math.random() * i));
		}
		return sb.toString();
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * @param v1 被除数
	 * @param v2 除数
	 * @param scale  表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
