package com.cnit.yoyo.service;  

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cnit.yoyo.base.xml.AppLoginsHolder;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.app.Tapplog;
import com.cnit.yoyo.model.app.Tclientlog;
import com.cnit.yoyo.model.app.Ttoken;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.APICommonUtil;
import com.cnit.yoyo.util.AppServiceMapHolder;
import com.cnit.yoyo.util.ApplicationContextUtil;
import com.cnit.yoyo.util.Base64;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.RedisClientUtil;
import com.cnit.yoyo.util.ServiceMapHolder;
import com.cnit.yoyo.util.aes.BackAES;

/**
 * 手机api总控制处理类
 * @Author:yangyi  
 * @Date:2015年7月17日  
 * @Version:1.0.0
 */
public class IMhpService {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	private final Integer SEEEION_DEF_TIME=1000*60*24*3;//默认保存3天
	@Autowired
	private RemoteService  otherService;
	@Autowired
	private RedisClientUtil redisService;
	@Autowired
	private AppLoginsHolder appLoginsHolder;

	@SuppressWarnings("finally")
	public String mhpServiceTest(String json,HttpServletRequest request) {
		String resultJson = "";
		HeadObject headObject =CommonHeadUtil.geneHeadObject(request, "1000020102-20");
		long s = System.currentTimeMillis();
		System.out.println("原始密文："+json);
		String token = "";
		String method ="";
		String[] LastReqNote = null;
		//是否需要登录
		boolean needLogin=false;
		try {
			System.out.println("报文解密时间："+(System.currentTimeMillis()-s));
			logger.info("报文解密时间："+(System.currentTimeMillis()-s));
			System.out.println("输入参数："+json);
			logger.info("输入参数："+json);
			if (json!=null) {
	            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
	            Matcher m = p.matcher(json);
	            json = m.replaceAll("");
	        }
			json = json.replace("&quot;", "'").replace("(", "['").replace(")", "']");
			JSONObject jsonObject = JSONObject.fromObject(json);
			method = (String) jsonObject.get("method");
			String data = jsonObject.get("data").toString();
			String sessionid = (String)jsonObject.get("sessionid");
			if(StringUtils.isNotBlank(sessionid)){
				request.setAttribute("sessionid", sessionid);
			}
			token = (String)jsonObject.get("token");
			
			//记录每次调方法所花时间
			//方法名,网络模式(0:NO 1:Wwan 2:Wi-Fi),重连标记(0:未重连 1:有重连),数据请求时间(时间差毫秒),请求时间yyyymmddhhmmss,请求返回状态100
			//"LastReqNote":["getSimpleHospitalList","1","0","4.129217","20140901123045","100"]
			try {
				if(jsonObject.get("lastReqNote") != null){
					JSONArray array = (JSONArray) jsonObject.get("lastReqNote");
					if(array != null && array.size() == 6){
						HeadObject headObject1 = CommonHeadUtil.geneHeadObject("appBaseService.saveTclientlog");
						Tclientlog log = new Tclientlog();
						log.setFmethod(array.get(0).toString());
						log.setFnetmode(array.get(1).toString());
						log.setFisrelink(array.get(2).toString());
						log.setFtime(array.get(3).toString());
						log.setFbegintime(array.get(4).toString());
						log.setFresult(array.get(5).toString());
						log.setFresultinfo("");
						HeadObject logResultObject=(HeadObject) otherService.doServiceByServer(new RequestObject(headObject1, log));
						if (!ErrorCode.SUCCESS.equalsIgnoreCase(logResultObject.getRetCode())) {
							JsonConfig jsonConfig=new JsonConfig();
							jsonConfig.setExcludes(new String[]{ "head"});
							resultJson =JSONObject.fromObject(new ResultObject(logResultObject),jsonConfig).toString();
							return resultJson;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//检查该方法是否需要登录
			if(appLoginsHolder.getNeedLoginMethod(method)){
				if(StringUtils.isNotBlank(sessionid)){
					MemberListDo memberDo=APICommonUtil.getMemberListDo(sessionid);
					if(memberDo == null){
						String errorCode = ErrorCode.FAILURE;
						JsonConfig jsonConfig=new JsonConfig();
						jsonConfig.setExcludes(new String[]{ "head"});
						headObject.setRetCode(errorCode);
						headObject.setRetMsg("检查sessionid是否正确或者重新登录");
						ResultObject resultObject=new ResultObject(headObject);
						resultJson =JSONObject.fromObject(resultObject,jsonConfig).toString();
						return resultJson;
					}else{
						needLogin=true;
					}
				}else{
					String errorCode = ErrorCode.FAILURE;
					JsonConfig jsonConfig=new JsonConfig();
					jsonConfig.setExcludes(new String[]{ "head"});
					headObject.setRetCode(errorCode);
					headObject.setRetMsg("此方法需要登录后操作，请输入sessionid");
					ResultObject resultObject=new ResultObject(headObject);
					resultJson =JSONObject.fromObject(resultObject,jsonConfig).toString();
					return resultJson;
				}
			}
			/**
			 * 设置检测方法令牌
			 */
			if(StringUtils.isNotBlank(token)){
				logger.info("#################token:{}####################",token);
				//查询token
				HeadObject headObject1 = CommonHeadUtil.geneHeadObject("appBaseService.findToken");
				ResultObject tokenResultObject = (ResultObject) otherService.doServiceByServer(new RequestObject(headObject1,token));
				if (ErrorCode.SUCCESS.equalsIgnoreCase(tokenResultObject.getRetCode())) {
					Ttoken token1=(Ttoken) tokenResultObject.getContent();
					//可以查询到值，并且状态为依据处理完，则返回值，否则新增token记录
					if(token1 != null){
						if("1".equals(token1.getFstate())){
							resultJson = token1.getFresult();
							logger.info("############VALUE::"+resultJson);
						}else{
							JsonConfig jsonConfig=new JsonConfig();
							jsonConfig.setExcludes(new String[]{ "head"});
							headObject.setRetCode(ErrorCode.DOING);
							headObject.setRetMsg("上次请求还未处理完");
							ResultObject resultObject=new ResultObject(headObject);
							resultJson =JSONObject.fromObject(resultObject,jsonConfig).toString();
						}
						return resultJson;
					}else{
						//未查询到值则新增记录
						HeadObject headObject2 =CommonHeadUtil.geneHeadObject("appBaseService.saveToken");
						Ttoken objToken = new Ttoken();
						objToken.setFtokenid(token);
						objToken.setFcreatetime(new Date());
						objToken.setFstate("0");//令牌方法执行情况 0 正在执行 1执行完成
						objToken.setFresult("");//执行结果
						HeadObject tokenResultObject2=(HeadObject) otherService.doServiceByServer(new RequestObject(headObject2, objToken));
						//系统报错
						if (!ErrorCode.SUCCESS.equalsIgnoreCase(tokenResultObject2.getRetCode())) {
							JsonConfig jsonConfig=new JsonConfig();
							jsonConfig.setExcludes(new String[]{ "head"});
							headObject.setRetCode(ErrorCode.FAILURE);
							headObject.setRetMsg("系统异常");
							ResultObject resultObject=new ResultObject(headObject);
							resultJson =JSONObject.fromObject(resultObject,jsonConfig).toString();
							return resultJson;
						}
					}
				}
			}
			
			//更新会话最后访问时间
			if(needLogin){
				logger.info("sessionid>>>{}",sessionid);
				String sessionVal=redisService.get(sessionid);
				if(sessionVal != null){
					redisService.del(sessionid);
					redisService.set(sessionid, sessionVal, SEEEION_DEF_TIME);
				}
			}
			// 从spring容器中获得服务提供者appApplicationContextUtil
			String serviceClassName = AppServiceMapHolder.getServiceProvider(method);
			if(StringUtils.isNotBlank(serviceClassName)){
				Object provider = ApplicationContextUtil.getBeanByName(serviceClassName);
				// 根据方法名及参数类型获得方法对象
				Method mth = provider.getClass().getMethod(method, String.class,HttpServletRequest.class);
				resultJson =  mth.invoke(provider, data,request).toString();
				//记录客户端日志
				HeadObject headObject1 =CommonHeadUtil.geneHeadObject("appBaseService.saveTappLog");
				Tapplog applog=new Tapplog();
				MemberListDo memberDo=APICommonUtil.getMemberListDo(sessionid);
				JSONObject dataJson=JSONObject.fromObject(jsonObject.get("data"));
				if(memberDo != null){
					applog.setFuserid(new Long(memberDo.getAccountId()));
					applog.setFusername(memberDo.getLoginName());
				}
				applog.setFinterfacecode(dataJson.get("finterfacecode").toString());
				applog.setFinterfacename(dataJson.get("finterfacename").toString());
				applog.setFphonemodel(dataJson.get("fphonemodel").toString());
				applog.setFphoneostype(dataJson.get("fphoneostype").toString());
				applog.setFphoneos(dataJson.get("fphoneos").toString());
				applog.setFusetime(new Date());
				otherService.doServiceByServer(new RequestObject(headObject1, applog));
			}else{
				//没找到服务
				JsonConfig jsonConfig=new JsonConfig();
				jsonConfig.setExcludes(new String[]{ "head"});
				headObject.setRetCode(ErrorCode.NO_METHOD);
				headObject.setRetMsg("对应的method不存在");
				ResultObject resultObject=new ResultObject(headObject);
				resultJson =JSONObject.fromObject(resultObject,jsonConfig).toString();
			}
		} catch (Exception e) {
			//设置token执行状态，异常则置为-1;
			logger.error("系统异常>>>{}",e.toString());
			String errorCode = ErrorCode.FAILURE;
			JsonConfig jsonConfig=new JsonConfig();
			jsonConfig.setExcludes(new String[]{ "head"});
			headObject.setRetCode(errorCode);
			headObject.setRetMsg("系统异常");
			ResultObject resultObject=new ResultObject(headObject);
			resultJson =JSONObject.fromObject(resultObject,jsonConfig).toString();
			e.printStackTrace();
		}finally{
			s = System.currentTimeMillis();
		   if(StringUtils.isNotBlank(token)){
			 //查询token
				HeadObject headObject1 = CommonHeadUtil.geneHeadObject("appBaseService.findToken");
				ResultObject tokenResultObject;
				try {
					tokenResultObject = (ResultObject) otherService.doServiceByServer(new RequestObject(headObject1,token));
					if (ErrorCode.SUCCESS.equalsIgnoreCase(tokenResultObject.getRetCode())) {
						Ttoken token1=(Ttoken) tokenResultObject.getContent();
						if(token1 != null){
							//查询到值则更新记录
							HeadObject headObject2 =CommonHeadUtil.geneHeadObject("appBaseService.updateToken");
							token1.setFtokenid(token);
							token1.setFendtime(new Date());
							token1.setFstate("1");//令牌方法执行情况 0 正在执行 1执行完成
							token1.setFresult(resultJson);//执行结果
							otherService.doServiceByServer(new RequestObject(headObject2,token1));
						}
					}
				} catch (Exception e) {
					logger.error("系统异常{}",e.toString());
					e.printStackTrace();
				}
			}
			try {
				JsonConfig jsonConfig=new JsonConfig();
				jsonConfig.setExcludes(new String[]{ "head"});
				resultJson =JSONObject.fromObject(resultJson,jsonConfig).toString();
				logger.info("返回结果resultJson>>>{}",resultJson);
				resultJson = Base64.encode(resultJson.replaceAll("content", "data").getBytes("utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			logger.info("报文加密时间："+(System.currentTimeMillis()-s));
			return resultJson;
		}
	}
	public static void main(String[] args){
		String str="{data={fappcode=1;fappname=\"\\U4f18\\U4f18\";finterfacecode=\"\";finterfacename=\"\";fphonemodel=\"\";fphoneos=ios;fphoneostype=2;identifier=999;testflag=1};lastReqNote=['memberCarList,0,0,\"4.099021\",20150802115724,0'];method=findCarBrand;sessionid=2C76D4369FE66EAD19360EBD20D2398E;token=\"49A3B520-14FB-4689-A729-E6F8E63AFF4F\"}";
		JSONObject jsonObject = JSONObject.fromObject(str);
		System.out.println(111);
	}
}
