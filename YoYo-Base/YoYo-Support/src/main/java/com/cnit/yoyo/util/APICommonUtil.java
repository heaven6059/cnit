package com.cnit.yoyo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import redis.clients.jedis.Jedis;
import net.sf.json.JSONObject;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.MemberListDo;

/**
 * api项目公共工具类
 * @author yangyi
 * @version 1.0.0
 * @copyright CNIT
 */
public class APICommonUtil {

	private Jedis jedis;

	public APICommonUtil() {
		jedis = new Jedis("10.255.8.17", 6379);
	}
	/**
	 * 获取当前项目session
	 * 
	 * @param request
	 * @return
	 */
	public static Object getSession(String sessionId) {
		APICommonUtil APICommonUtil = new APICommonUtil();
		return APICommonUtil.readRedisSession(sessionId);
	}

	private Object readRedisSession(String sessionId) {
		try {
			String accountInfoStr = jedis.get(sessionId);
			String password = jedis.get(sessionId + "password");
			if (StringUtils.isNotEmpty(accountInfoStr)) {
				return JSONObject.fromObject(accountInfoStr);
			} else {
				return "";
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return "";
	}

	public static MemberListDo getMemberListDo(String sessionId) {
		try {
			String accountInfo = APICommonUtil.getSession(sessionId).toString();// 获取当前用户信息
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
	
	public static void main(String[] args){
		String sessionid="145AC2F42F46EFBB3065BA4BF46053C8";
		Object obj=new APICommonUtil().getSession(sessionid);
		System.out.println(obj);
	}
}
