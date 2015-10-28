package com.cnit.yoyo.domain.member;

import java.io.Serializable;

public class ClerkInfoDo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String loginName;  //店员名称
    private String tel;
    private String email;
    private String regTime;
    private String name;
    private String rolesName;
    private int attachId;
    private Long roleId;
    private String storeName;  //店铺名称
    private Long storeId;
    private Long companyId;
    
    private String shopkeeper; //店主
    private String shopName;  //分店名称
    private String lastModify;
    
    
    
    public int getAttachId() {
        return attachId;
    }
    public void setAttachId(int attachId) {
        this.attachId = attachId;
    }
    public String getLoginName() {
        return loginName;
    }
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getRegTime() {
        return regTime;
    }
    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRolesName() {
        return rolesName;
    }
    public void setRolesName(String rolesName) {
        this.rolesName = rolesName;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
    public Long getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
    public String getShopkeeper() {
        return shopkeeper;
    }
    public void setShopkeeper(String shopkeeper) {
        this.shopkeeper = shopkeeper;
    }
    public String getShopName() {
        return shopName;
    }
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
    public String getLastModify() {
        return lastModify;
    }
    public void setLastModify(String lastModify) {
        this.lastModify = lastModify;
    }
	
    
    
  
}
