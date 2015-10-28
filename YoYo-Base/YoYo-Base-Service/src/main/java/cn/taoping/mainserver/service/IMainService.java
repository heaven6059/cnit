/*
 * 文 件 名 : IMainService.java 版 权 : Ltd. Copyright (c) 2013 深圳市商巢互联网软件有限公司,All rights reserved 描 述 : &lt;描述&gt; 创建人 : 彭彩云 创建时间: 2015-4-21 下午1:43:33
 */
package cn.taoping.mainserver.service;

import cn.taoping.jsonrpc.core.dto.ReqParamsObj;
import cn.taoping.jsonrpc.core.dto.ResponseObj;

/**
 * web端提供接口能力
 * 
 * @author 彭彩云
 * @version [版本号, 2015-4-21 下午1:43:33]
 */
public interface IMainService {

	/**
	 * 别名+邮箱注册用户(邮箱注册开通业务逻辑在邮件回复)
	 * 
	 * @param reqObj
	 *  ReqParamsObj reqObj = new ReqParamsObj();
		Map<String, Object> map = new HashMap<>();
		map.put("userAlias", String);//别名 String类型
		map.put("userEmail", String);//邮箱 String类型
		map.put("password", String);//密码 String类型
		map.put("language", String);//语言  zh_CN/en_US String类型 默认为zh_CN 允许为空 
	 * @return [参数说明]
	 * @return ResponseObj responseObj.isSuccess() true:成功 false:失败(错误信息见responseObj.getMsg())
	 * 成功返回userID=responseObj.getResult()
	 * @exception throws [违例类型] [违例说明]
	 */
	public ResponseObj regUserForAliasAndEmail(ReqParamsObj reqObj);

	/**
	 * 手机注册用户
	 * 
	 * @param reqObj
	 *  ReqParamsObj reqObj = new ReqParamsObj();
		Map<String, Object> map = new HashMap<>();
		map.put("userMobile", Long);//手机号码
		map.put("verificationCode", String);//短信验证码
		map.put("password", String);//密码 String类型
		map.put("taskID", String);//短信ID 
	 * @return [参数说明]
	 * @return ResponseObj responseObj.isSuccess() true:成功 false:失败(错误信息见responseObj.getMsg())
	 * 成功返回userID=responseObj.getResult()
	 * @exception throws [违例类型] [违例说明]
	 */
	public ResponseObj regUserForMobile(ReqParamsObj reqObj);
	
	/**
	 * 别名注册用户
	 * 
	 * @param reqObj
	  * ReqParamsObj reqObj = new ReqParamsObj();
		Map<String, Object> map = new HashMap<>();
		map.put("userAlias", String);//用户名(别名) String类型
		map.put("realName", String);//真实姓名 String类型
		map.put("password", String);//密码 String类型
	 * @return [参数说明]
	 * @return ResponseObj responseObj.isSuccess() true:成功 false:失败(错误信息见responseObj.getMsg())
	 * 成功返回userID=responseObj.getResult()
	 * @exception throws [违例类型] [违例说明]
	 */
	public ResponseObj regUserForAlias(ReqParamsObj reqObj);

	/**
	 * 发送验证短信(根据短信模板发送验证短信)
	 * 
	 * @param reqObj
	 * ReqParamsObj reqObj = new ReqParamsObj();
		Map<String, Object> map = new HashMap<>();
		map.put("userMobile", String);//发送手机号码 
	 * @return [参数说明]
	 * @return ResponseObj responseObj.isSuccess() true:成功 false:失败(错误信息见responseObj.getMsg())
	 * 成功返回taskID=responseObj.getResult()
	 * @exception throws [违例类型] [违例说明]
	 */
	public ResponseObj sendVerifySMS(ReqParamsObj reqObj);

	/**
	 * 验证验证码是否正确
	 * 
	 *  @param reqObj
	 *  ReqParamsObj reqObj = new ReqParamsObj();
		Map<String, Object> map = new HashMap<>();
		map.put("userMobile", Long);//手机号码
		map.put("verificationCode", String);//短信验证码
		map.put("taskID", String);//短信ID 
	 * @return [参数说明]
	 * @return ResponseObj responseObj.isSuccess() true:成功 false:失败(错误信息见responseObj.getMsg())
	 * 成功返回verificationCode=responseObj.getResult()
	 * @exception throws [违例类型] [违例说明]
	 */
	public ResponseObj verifySMS(ReqParamsObj reqObj);

	/**
	 * 修改用户基础信息(姓名、性别、出生年月、头像 头像修改需要调用云存储的图片更新接口 数据库存MD5值)
	 * 
	 * @param reqObj
	 *  ReqParamsObj reqObj = new ReqParamsObj();
		Map<String, Object> map = new HashMap<>();
		map.put("userID", Long);//用户ID
		map.put("realName", String);//真实姓名  允许为空
		map.put("userGender", String);//性别 M 男 G 女  允许为空
		map.put("birthday", String);//出生年月  允许为空
		map.put("profilePhoto", String);//头像地址(云存储MD5地址)  允许为空
	 * @return ResponseObj responseObj.isSuccess() true:成功 false:失败(错误信息见responseObj.getMsg())
	 * @return ResponseObj
	 * @exception throws [违例类型] [违例说明]
	 */
	public ResponseObj modifyUserBaseInfo(ReqParamsObj reqObj);

	/**
	 * 更新用户头像图片上传云存储
	 * 
	 * @param reqObj
	 * ReqParamsObj reqObj = new ReqParamsObj();
		Map<String, Object> map = new HashMap<>();
		map.put("userID", Long);//用户ID
		map.put("profilePhoto", String);//头像地址(云存储MD5地址)  允许为空
	 * @return ResponseObj responseObj.isSuccess() true:成功 false:失败(错误信息见responseObj.getMsg())
	 * @return ResponseObj
	 * @exception throws [违例类型] [违例说明]
	 */
	public ResponseObj modifyUserHeadIcon(ReqParamsObj reqObj);

	/**
	 * 登录信息修改 别名登录绑定/修改 邮箱登录绑定/修改 手机登录绑定/修改
	 * 
	 * @param reqObj
	 *  ReqParamsObj reqObj = new ReqParamsObj();
		Map<String, Object> map = new HashMap<>();
		map.put("userID", Long);//用户ID
		map.put("userMobile", Long);//手机号码 允许为空
		map.put("isMobileBound", Integer);//是否绑定手机 0 未绑定 1 绑定 允许为空
		map.put("userEmail", String);//邮箱 String类型 允许为空
		map.put("isEmailBound", Integer);//是否绑定邮箱  0 未绑定 1 绑定 允许为空
		map.put("userAlias", String);//别名 String类型
		map.put("isAliasBound", Integer);//是否绑定别名  0 未绑定 1 绑定 允许为空
	 * @return [参数说明]
	 * @return ResponseObj responseObj.isSuccess() true:成功 false:失败(错误信息见responseObj.getMsg())
	 * 成功返回userID=responseObj.getResult()
	 * @exception throws [违例类型] [违例说明]
	 */
	public ResponseObj modifyUserLoginInfo(ReqParamsObj reqObj);

	/**
	 * 修改密码
	 * 
	 * @param reqObj
	 * ReqParamsObj reqObj = new ReqParamsObj();
		Map<String, Object> map = new HashMap<>();
		map.put("userID", Long);//用户ID
		map.put("oldPassword", String);//原始密码
		map.put("password", String);//新密码 String类型
	 * @return [参数说明]
	 * @return ResponseObj responseObj.isSuccess() true:成功 false:失败(错误信息见responseObj.getMsg())
	 * @exception throws [违例类型] [违例说明]
	 */
	public ResponseObj modifyUserLoginPassword(ReqParamsObj reqObj);
	
	/**
	 * 修改密码(手机修改密码)
	 * 
	 * @param reqObj
	 * ReqParamsObj reqObj = new ReqParamsObj();
		Map<String, Object> map = new HashMap<>();
		map.put("userMobile", Long);//手机号码 允许为空
		map.put("key", String);//加密key值
		map.put("password", String);//新密码 String类型
	 * @return [参数说明]
	 * @return ResponseObj responseObj.isSuccess() true:成功 false:失败(错误信息见responseObj.getMsg())
	 * @exception throws [违例类型] [违例说明]
	 */
	public ResponseObj modifyUserLoginPasswordForMobile(ReqParamsObj reqObj);
	
	/**
	 * 忘记密码手机重置(需要验证码)
	 * 
	 * @param reqObj
	 * ReqParamsObj reqObj = new ReqParamsObj();
		Map<String, Object> map = new HashMap<>();
		map.put("userID", Long);//用户ID
		map.put("verificationCode", String);//短信验证码
		map.put("taskID", String);//短信ID 
		map.put("password", String);//新密码 String类型
	 * @return [参数说明]
	 * @return ResponseObj responseObj.isSuccess() true:成功 false:失败(错误信息见responseObj.getMsg())
	 * @exception throws [违例类型] [违例说明]
	 */
	public ResponseObj resetUserLoginPasswordForMobile(ReqParamsObj reqObj);
	
	/**
	 * 忘记密码发送重置密码邮件
	 * 
	 * @param reqObj
	 * ReqParamsObj reqObj = new ReqParamsObj();
		Map<String, Object> map = new HashMap<>();
		map.put("userEmail", String);//邮箱 String类型 允许为空
		map.put("language", String);//语言  zh_CN/en_US String类型 默认为zh_CN 允许为空 
	 * @return [参数说明]
	 * @return ResponseObj responseObj.isSuccess() true:成功 false:失败(错误信息见responseObj.getMsg())
	 * 成功返回userID=responseObj.getResult()
	 * @exception throws [违例类型] [违例说明]
	 */
	public ResponseObj sendUserResetPasswordForEmail(ReqParamsObj reqObj);
	
	/**
	 * 忘记密码邮件重置
	 * 
	 * @param reqObj
	 *  ReqParamsObj reqObj = new ReqParamsObj();
		Map<String, Object> map = new HashMap<>();
		map.put("userID", Long);//用户ID
		map.put("userEmail", String);//邮箱 String类型 允许为空
		map.put("password", String);//新密码 String类型
	 * @return [参数说明]
	 * @return ResponseObj responseObj.isSuccess() true:成功 false:失败(错误信息见responseObj.getMsg())
	 * @exception throws [违例类型] [违例说明]
	 */
	public ResponseObj resetUserLoginPasswordForEmail(ReqParamsObj reqObj);
	
	/**
	 * 验证用户有效性
	 * 
	 * @param reqObj
	 *  ReqParamsObj reqObj = new ReqParamsObj();
		Map<String, Object> map = new HashMap<>();
		map.put("userName", String);//别名/邮箱/手机号码 String类型
		map.put("password", String);//密码 String类型
	 * @return [参数说明]
	 * @return ResponseObj responseObj.isSuccess() true:成功 false:失败(错误信息见responseObj.getMsg())
	 * 成功返回userID=responseObj.getResult()
	 * @exception throws [违例类型] [违例说明]
	 */
	public ResponseObj verifyUser(ReqParamsObj reqObj);
	
	/**
	 * 移动端用户登录
	 * 
	 * @param reqObj
	 *  ReqParamsObj reqObj = new ReqParamsObj();
		Map<String, Object> map = new HashMap<>();
		map.put("userName", String);//别名/邮箱/手机号码 String类型
		map.put("password", String);//密码 String类型
		map.put("mac", String);//终端mac地址 String类型
	 * @return [参数说明]
	 * @return ResponseObj responseObj.isSuccess() true:成功 false:失败(错误信息见responseObj.getMsg())
	 * 成功返回userID=responseObj.getResult()
	 * @exception throws [违例类型] [违例说明]
	 */
	public ResponseObj setAppLogin(ReqParamsObj reqObj);
	
	/**
	 * 查询使用统一权限的某系统的权限
	 * 
	 * @param reqObj
	 * ReqParamsObj reqObj = new ReqParamsObj();
		Map<String, Object> map = new HashMap<>();
		map.put("userID", Long);//用户ID
		map.put("systemID", Integer);//系统ID
	 * @return [参数说明]
	 * @return ResponseObj responseObj.isSuccess() true:成功 false:失败(错误信息见responseObj.getMsg())
	 * 成功返回List<Map<String, String>> mapList=(List<Map<String, String>>)responseObj.getResult()
	 * @exception throws [违例类型] [违例说明]
	 */
	public ResponseObj getSysPermission(ReqParamsObj reqObj);
	
	/**
	 * 激活用户
	 * @param reqObj
	 *  ReqParamsObj reqObj = new ReqParamsObj();
		Map<String, Object> map = new HashMap<>();
		map.put("userID", Long);//用户ID
	 * @return [参数说明]
	 * @return ResponseObj responseObj.isSuccess() true:成功 false:失败(错误信息见responseObj.getMsg())
	 * 成功返回userID=responseObj.getResult()
	 * @exception throws [违例类型] [违例说明]
	 */
	public ResponseObj activateRegUser(ReqParamsObj reqObj);
	
	/**
	 * 获取用户基本信息
	 * @param reqObj
	    ReqParamsObj reqObj = new ReqParamsObj();
		Map<String, Object> map = new HashMap<>();
		map.put("userID", Long);//用户ID
	 * @return [参数说明]
	 * @return ResponseObj responseObj.isSuccess() true:成功 false:失败(错误信息见responseObj.getMsg())
	 * 成功返回userobject=responseObj.getResult()
	 * 
	 * @exception throws [违例类型] [违例说明]
	 */
	public ResponseObj getUserInfo(ReqParamsObj reqObj);
	
	
	/**
	 * 用户开通系统角色
	 * @param reqObj
	 *  ReqParamsObj reqObj = new ReqParamsObj();
		Map<String, Object> map = new HashMap<>();
		map.put("btype", Integer);//开通业务  0:个人业务 1:公司业务
		map.put("userID", Long);//用户ID
		map.put("companyID", Long);//公司ID 个人业务可为空(btype=0)
		map.put("systemID", Integer);//系统ID
		map.put("roleType", Integer);//角色类型 默认为0
	 * @return ResponseObj 
	 * @exception throws [违例类型] [违例说明]
	 */
	public ResponseObj setUserSystemRole(ReqParamsObj reqObj);

}
