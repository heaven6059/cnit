/**
 * 文 件 名   :  SignUpDo.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-2-2 下午5:33:07
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.api.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.cnit.yoyo.api.base.vo.Parameter;
import com.cnit.yoyo.api.group.First;
import com.cnit.yoyo.api.group.Second;


/**
 * 第三方账号登录请求对象
 * @Author:yangyi  
 * @Date:2015年7月10日  
 * @Version:1.0.0
 */
public class ThirdSignUpVO extends Parameter implements Serializable {
    /* 登录名 */
	@NotBlank(message="{登录名不能为空}")
    private String loginName;
    /* 登录类型 手机号码 邮箱 用户名 */
    private String loginNameType;
    /* 邮箱 */
    private String email;
    /* 手机号码 */
    private String mobile;
    /* 登录密码 */
    @NotBlank(message="{密码不能为空}")
    private String loginPassword;
    /* 账户类型 QQ ,WX */
    @NotBlank(message="{账户类型不能为空}")
    private String accountType;
    /* 登录别名QQ网名 */
	@NotBlank(message="{登录别名不能为空}")
    private String accountName;
	/* 100-卖家，110卖家 */
	@NotBlank(message="{优优账户类型不能为空}")
    private String yyAccountType;
    /* 图片验证码 */
    private String imgValidation;
    /* 短信验证码 */
    private String smsValidation;
    /* 自动登录 */
    private boolean autoLogin;
    /* 注册来源 10：web前端  11：web管理平台  20：appAndroid 21:appIOS*/
    private String source;
    /* 注册类型 */
    private String regType;
    /* 注册IP */
    private String regIp;
    
    private String loginIp;
    
    
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginNameType() {
		return loginNameType;
	}
	public void setLoginNameType(String loginNameType) {
		this.loginNameType = loginNameType;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getYyAccountType() {
		return yyAccountType;
	}
	public void setYyAccountType(String yyAccountType) {
		this.yyAccountType = yyAccountType;
	}
	public String getImgValidation() {
		return imgValidation;
	}
	public void setImgValidation(String imgValidation) {
		this.imgValidation = imgValidation;
	}
	public String getSmsValidation() {
		return smsValidation;
	}
	public void setSmsValidation(String smsValidation) {
		this.smsValidation = smsValidation;
	}
	public boolean isAutoLogin() {
		return autoLogin;
	}
	public void setAutoLogin(boolean autoLogin) {
		this.autoLogin = autoLogin;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getRegType() {
		return regType;
	}
	public void setRegType(String regType) {
		this.regType = regType;
	}
	public String getRegIp() {
		return regIp;
	}
	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}
}
