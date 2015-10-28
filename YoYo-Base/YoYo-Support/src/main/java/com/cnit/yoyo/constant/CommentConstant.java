package com.cnit.yoyo.constant;

/**  
* @Title: CommentConstant.java
* @Package com.cnit.yoyo.constant
* @Description: 评论常量类
* @Author 王鹏
* @date 2015-5-16 下午5:58:11
* @version V1.0  
*/ 
public class CommentConstant {
	public static class CommentsType{
		public static Constant<String, String> EXPLAIN = new Constant<String, String>("0", "解释");
		public static Constant<String, String> COMMENT = new Constant<String, String>("1", "评论");
		public static Constant<String, String> REPLY = new Constant<String, String>("2", "回复");
		public static Constant<String, String> APPEND = new Constant<String, String>("3", "追加");
	}
	
	public static class ObjeType{
		public static Constant<String, String> ASK = new Constant<String, String>("ASK", "咨询");
		public static Constant<String, String> DISCUSS = new Constant<String, String>("DISCUSS", "讨论");
		public static Constant<String, String> MESSAGE = new Constant<String, String>("MESSAGE", "消息");
		public static Constant<String, String> MSG = new Constant<String, String>("MSG", "短消息");
		public static Constant<String, String> ORDER = new Constant<String, String>("MSG", "订单");
	}
}
