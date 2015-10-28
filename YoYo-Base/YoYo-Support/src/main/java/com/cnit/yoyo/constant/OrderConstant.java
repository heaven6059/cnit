
package com.cnit.yoyo.constant;

public class OrderConstant {
	/**  
	* @Title: OrderConstant.java
	* @Package com.cnit.yoyo.constant
	* @Description: 支付类型
	* @Author 王鹏
	* @date 2015-5-29 上午9:39:32
	* @version V1.0  
	*/ 
	public static class PaymentType{
		public static Constant<Integer, String> ON_LINE_PAY = new Constant<Integer, String>(0, "在线支付");
		public static Constant<Integer, String> LOCALE_PAY = new Constant<Integer, String>(1, "到店支付");
	}
	
	/**  
	* @Title: OrderConstant.java
	* @Package com.cnit.yoyo.constant
	* @Description: 订单类型
	* @Author 王鹏
	* @date 2015-5-29 上午9:39:20
	* @version V1.0  
	*/ 
	public static class OrderType{
		public static Constant<String, String> SCORE_BUY=new Constant<String, String>("SCORE_BUY", "积分换购");
	}
	
	/**  
	* @Title: OrderConstant.java
	* @Package com.cnit.yoyo.constant
	* @Description: 订单状态
	* @Author 王鹏
	* @date 2015-5-29 上午9:39:20
	* @version V1.0  
	*/ 
	public static class OrderStatus{
		public static Constant<String, String> CREATE = new Constant<String, String>("create", "订单创建");
		public static Constant<String, String> UNCONFIRM = new Constant<String, String>("unconfirm", "待确认");
		public static Constant<String, String> CONFIRM = new Constant<String, String>("confirm", "已确认");
		public static Constant<String, String> UNINSTALL = new Constant<String, String>("uninstall", "未安装");
		public static Constant<String, String> INSTALL = new Constant<String, String>("install", "安装中");
		public static Constant<String, String> INSTALLEND = new Constant<String, String>("installend", "安装完成");
		public static Constant<String, String> REFUNDS = new Constant<String, String>("refunds", "退款中");
		public static Constant<String, String> FINISH = new Constant<String, String>("finish", "已完成");
		public static Constant<String, String> DEAD = new Constant<String, String>("dead", "已作废");
	}
	
	/**  
	* @Title: OrderConstant.java
	* @Package com.cnit.yoyo.constant
	* @Description: 订单日志行为
	* @Author 王鹏
	* @date 2015-5-29 上午9:39:20
	* @version V1.0  
	*/ 
	public static class OrderLogBehavior{
		public static Constant<String, String> CREATE = new Constant<String, String>("create", "创建");
		public static Constant<String, String> PAYMENTS = new Constant<String, String>("payments", "支付");
		public static Constant<String, String> REFUNDS = new Constant<String, String>("refunds", "退款");
		public static Constant<String, String> FINISH = new Constant<String, String>("finish", "完成");
		public static Constant<String, String> RESHIP = new Constant<String, String>("reship", "退货");		
		public static Constant<String, String> CANCEL = new Constant<String, String>("cancel", "取消");
	}	
}

