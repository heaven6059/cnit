package com.cnit.yoyo.model.thirdaccount.dto;

import java.io.Serializable;
import java.util.Date;

import com.cnit.yoyo.model.thirdaccount.ThirdAccount;

public class ThirdAccountDTO extends ThirdAccount implements Serializable {

    /**
     * 第三方登录账号
     */
    private String thirdAccountCode;

   /**
    * 第三方登录名称
    */
    private String thirdAccountName;

   /**
    * 第三方登陆类型
    */
    private String thirdAccountType;

   /**
    * 优优账号ID
    */
    private Integer pamAccountId;
}