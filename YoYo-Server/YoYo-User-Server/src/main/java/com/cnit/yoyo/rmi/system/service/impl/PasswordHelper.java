package com.cnit.yoyo.rmi.system.service.impl;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.model.member.PamAccount;

/**
 * 密碼管理類
* @author ssd
* @date 2015-4-30 下午5:02:52
 */
@Service("passwordHelper")
public class PasswordHelper {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    @Value("${password.algorithmName}")
    private String algorithmName="md5";
    @Value("${password.hashIterations}")
    private String hashIterations="2";

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setHashIterations(String hashIterations) {
        this.hashIterations = hashIterations;
    }

    /**
     * 
    *
    * @Description: 加密密碼,我们使用MD5算法，“密码+盐（用户名+随机数）”的方式生成散列值，加密方法是：md5(md5(密码+username+salt2))，其中salt2是一串随机的散列值
    * @param @param user    设定文件 
    * @author ssd
    * @date 2015-4-30 下午5:03:15 
    * @return void    返回类型 
    * @throws
     */
    public void encryptPassword(PamAccount user) {

        user.setSalt(randomNumberGenerator.nextBytes().toHex());

        String newPassword = new SimpleHash(
                algorithmName,
                user.getLoginPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                Integer.parseInt(hashIterations)).toHex();

        user.setLoginPassword(newPassword);
    }
    
    /**
     * 
    *
    * @Description: 字符串加密为密码
    * @author yuping
    * @date 2015-4-30 下午5:03:15 
    * @return String    返回类型 
     */
    public String getEncryptPassword(PamAccount user) {
        String newPassword = new SimpleHash(
                algorithmName,
                user.getLoginPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                Integer.parseInt(hashIterations)).toHex();
        return newPassword;
    }    
    
    
    public static void main(String[] args) {
    	PamAccount pa = new PamAccount();
    	pa.setLoginName("demo");
    	pa.setLoginPassword("yoyo123456");
    	PasswordHelper ph = new PasswordHelper();
    	ph.encryptPassword(pa);
    	
    	System.out.println("pw:"+pa.getLoginPassword()+",salt:"+pa.getSalt());
    }
}
