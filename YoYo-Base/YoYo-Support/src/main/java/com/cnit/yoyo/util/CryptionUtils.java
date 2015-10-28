/**
 * 文 件 名   :  CryptionUtils.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  &lt;描述&gt;
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-1-22 下午4:16:14
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * @description <一句话功能简述>
 * @detail <功能详细描述>
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
public class CryptionUtils {

    public final static String DES           = "DES";
    public final static String DESEDE        = "DESede";
    public final static String IDEA          = "IDEA";
    public final static String RC2           = "RC2";
    public final static String RC4           = "RC4";
    public final static String RC5           = "RC5";
    public final static String SKIPJACK      = "SKIPJACK";
    public final static String AES           = "AES";
    public final static String MD5           = "MD5";
    public final static String SHA           = "SHA";
    public final static String RSA           = "RSA";
    public final static String DSA           = "DSA";

    private final static int   UNKOWN        = 0;
    private final static int   SYMMETRICAL   = 1;
    private final static int   UNSYMMETRICAL = 2;
    private final static int   OTHER         = 3;

    /**
     * @description 对字符串加密
     * @detail <方法详细描述>
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-1-22
     * @param str
     * @return
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws UnsupportedEncodingException
     */
    public static byte[] Encrytor(String algorithm, String str, String charset, RSAPublicKey publicKey) throws InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        int algorithmType = algorithmType(algorithm);
        if (algorithmType == CryptionUtils.SYMMETRICAL || algorithmType == CryptionUtils.UNSYMMETRICAL) {
            // Cipher负责完成加密或解密工作
            Cipher c = Cipher.getInstance(algorithm);
            if (algorithmType == CryptionUtils.SYMMETRICAL) {
                // KeyGenerator 提供对称密钥生成器的功能，支持各种算法
                KeyGenerator keygen = KeyGenerator.getInstance(algorithm);
                // SecretKey 负责保存对称密钥
                SecretKey deskey = keygen.generateKey();
                // 根据密钥，对Cipher对象进行初始化，DECRYPT_MODE表示加密模式
                c.init(Cipher.ENCRYPT_MODE, deskey);
            } else {
                if (publicKey != null) {
                    c.init(Cipher.ENCRYPT_MODE, publicKey);
                } else {
                    return null;
                }
            }
            return c.doFinal(str.getBytes(charset));
        } else if (algorithmType == CryptionUtils.OTHER) {
            // 根据MD5算法生成MessageDigest对象
            MessageDigest md5 = MessageDigest.getInstance(algorithm);
            byte[] srcBytes = str.getBytes(charset);
            // 使用srcBytes更新摘要
            md5.update(srcBytes);
            // 完成哈希计算，得到result
            return md5.digest();
        } else {
            return null;
        }
    }

    /**
     * @description 对字符串解密
     * @detail <方法详细描述>
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-1-22
     * @param buff
     * @return
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     */
    public static byte[] Decryptor(String algorithm, byte[] buff, RSAPrivateKey privateKey) throws InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());

        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        int algorithmType = algorithmType(algorithm);
        if (algorithmType == CryptionUtils.SYMMETRICAL || algorithmType == CryptionUtils.UNSYMMETRICAL) {
            // Cipher负责完成加密或解密工作
            Cipher c = Cipher.getInstance(algorithm);
            if (algorithmType == CryptionUtils.SYMMETRICAL) {
                // KeyGenerator 提供对称密钥生成器的功能，支持各种算法
                KeyGenerator keygen = KeyGenerator.getInstance(algorithm);
                // SecretKey 负责保存对称密钥
                SecretKey deskey = keygen.generateKey();
                // 根据密钥，对Cipher对象进行初始化，DECRYPT_MODE表示加密模式
                c.init(Cipher.DECRYPT_MODE, deskey);
            } else {
                if (privateKey != null) {
                    c.init(Cipher.DECRYPT_MODE, privateKey);
                } else {
                    return null;
                }
            }
            return c.doFinal(buff);
        } else if (algorithmType == CryptionUtils.UNSYMMETRICAL) {
            return null;
        } else if (algorithmType == CryptionUtils.OTHER) {
            return null;
        } else {
            return null;
        }
    }

    /**
     * @description 获得算法类型
     * @detail <方法详细描述>
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-1-22
     * @param algorithm
     * @return
     */
    public static int algorithmType(String algorithm) {
        // 常用对称加密算法DES、IDEA、RC2、RC4、SKIPJACK、RC5、AES
        if (CryptionUtils.DES.equalsIgnoreCase(algorithm) || CryptionUtils.DESEDE.equalsIgnoreCase(algorithm)
                || CryptionUtils.IDEA.equalsIgnoreCase(algorithm) || CryptionUtils.RC2.equalsIgnoreCase(algorithm)
                || CryptionUtils.RC4.equalsIgnoreCase(algorithm) || CryptionUtils.RC5.equalsIgnoreCase(algorithm)
                || CryptionUtils.SKIPJACK.equalsIgnoreCase(algorithm) || CryptionUtils.AES.equalsIgnoreCase(algorithm)) {
            return CryptionUtils.SYMMETRICAL;
        }
        // 常用非对称加密算法RSA
        if (CryptionUtils.RSA.equalsIgnoreCase(algorithm) || CryptionUtils.DSA.equalsIgnoreCase(algorithm)) {
            return CryptionUtils.UNSYMMETRICAL;
        }
        if (CryptionUtils.MD5.equalsIgnoreCase(algorithm) || CryptionUtils.SHA.equalsIgnoreCase(algorithm)) {
            return CryptionUtils.OTHER;
        }
        return CryptionUtils.UNKOWN;
    }

    public static void main(String[] args) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException,
            NoSuchPaddingException, UnsupportedEncodingException {
        String msg = "郭XX-精品相声技术";
        byte[] resultBytes = CryptionUtils.Encrytor(CryptionUtils.MD5, msg, "gbk", null);
        System.out.println("明文是：" + msg);
        System.out.println("密文是：" + new String(resultBytes, "gbk"));
    }
}
