package com.cnit.yoyo.util.aes;

/**
 * 测试类
 * @Author:yangyi  
 * @Date:2015年7月21日  
 * @Version:1.0.0
 */
public class Test {

	public static void main(String[] args) {

		String content = "1";
		String skey = "123456789";
		try {
			//加密
			byte[] encryptResultStr = BackAES.encrypt(content, skey, 1);
			System.out.println("方法-加密后："+new String(encryptResultStr));
			String decryptString=BackAES.decrypt(new String("pcy10zBPTzIDt6RlwTuO90XFj+452Fu0LXz0lbWzs5+1GA7AHzRsHUWx4CHKss8t+WLoaKCl+n5mWxKFkgorC5VrTf7T+NFiqIyq8Z2uFU9+67iYy89xyxLYO2HO/W688d3TsbPhS8L4jWy8ltatr9+B6Xaha/sXJZROTkMs/xiVyCeGCRvdmTTXIbb9JPFfc5N/Yf2feFLHgyFaGp8joPAAIu+rs9pfWCGDbceRAl+YeD73W/613+cETWnSnXFAY2vwo1dz0XTEAgyqP4GMM7YDZZSjWqepTfUZqkRfB+Lgu89Q1U6X9LR7k6V68t5WP2sN2PKN0Bst7BHWs7e2FsntxFXp/Craqxn6QDnCm+cKP3PjrCZAvPa1Uo7SAyRjVrynO06FYhkQ9TfOBdAS9CeyTn0vZwL7Q8SHoOOLY0ZBwJQCCBORPFlL4BaCFTpQNgaff/D3AH4HEGJJqJoZu35yFrJGVmTP9Yd5C1OsFmk=="),
					skey, 1);
			System.out.println("方法-解密后："+decryptString);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
