package com.cnit.yoyo.constant;

import java.util.ArrayList;
import java.util.List;

public class AfterSalesConstant {
	/**  
	* @Title: AfterSalesConstant.java
	* @Package com.cnit.yoyo.constant
	* @Description: 服务类型
	* @Author 王鹏
	* @date 2015-5-29 下午2:43:30
	* @version V1.0  
	*/ 
	public static class AfterSalesRequire{
//		public static Constant<Integer, String> ONLEY_REFUND = new Constant<Integer, String>(1, "不退货部分退款");
//		public static Constant<Integer, String> REFUND_ALL = new Constant<Integer, String>(2, "需要退货退款");
		public static Constant<Integer, String> NEED_CHAGNE = new Constant<Integer, String>(3, "要求换货");
		public static Constant<Integer, String> NEED_MAINTAIN = new Constant<Integer, String>(4, "要求维修");		
		public static Constant<Integer, String> NEED_REFUND = new Constant<Integer, String>(5, "要求退款");
		
		public static String getAfterSalesRequire(Integer status){
			String title="";
			for (Constant<Integer, String> constant : getAfterSalesRequires()) {
				if(status==constant.getKey()){
					title=constant.getValue();
					break;
				}
			}
			return title;
		}
		
		public static List<Constant<Integer, String>> getAfterSalesRequires(){
			List<Constant<Integer, String>> list=new ArrayList<Constant<Integer,String>>();
//			list.add(ONLEY_REFUND);
//			list.add(REFUND_ALL);
			list.add(NEED_CHAGNE);
			list.add(NEED_MAINTAIN);
			list.add(NEED_REFUND);
			return list;
		}
	}
	
	/**  
	* @Title: AfterSalesConstant.java
	* @Package com.cnit.yoyo.constant
	* @Description: 申请售后原因
	* @Author 王鹏
	* @date 2015-5-29 下午5:17:54
	* @version V1.0  
	*/ 
	public static class AfterSalesReason{
		public static Constant<Integer, String> GOODS_PROBLEM = new Constant<Integer, String>(1, "商品问题");
		public static Constant<Integer, String> WITHOUT_ANY_REASON = new Constant<Integer, String>(2, "七天无理由退换货");
		public static Constant<Integer, String> APPEAL = new Constant<Integer, String>(3, "退换货申诉");
		public static Constant<Integer, String> UNINSTALL = new Constant<Integer, String>(4, "钣金未到店安装");
		
		public static String getAfterSalesReason(Integer status){
			String title="";
			for (Constant<Integer, String> constant : getAfterSalesReasons()) {
				if(status==constant.getKey()){
					title=constant.getValue();
					break;
				}
			}
			return title;
		}
		
		public static List<Constant<Integer, String>> getAfterSalesReasons(){
			List<Constant<Integer, String>> list=new ArrayList<Constant<Integer,String>>();
			list.add(UNINSTALL);
			list.add(GOODS_PROBLEM);
			list.add(WITHOUT_ANY_REASON);
			list.add(APPEAL);
			return list;
		}
	}
	
	
	/**
	 * @author Administrator
	 * 售后服务申请状态
	 */
	public static class AfterSalesStatus{
		public static Constant<Integer, String> STATUS_1 = new Constant<Integer, String>(1, "等待卖家确认");
		public static Constant<Integer, String> STATUS_2 = new Constant<Integer, String>(2, "同意售后服务申请");
		public static Constant<Integer, String> STATUS_3 = new Constant<Integer, String>(3, "拒绝售后服务申请");
		public static Constant<Integer, String> STATUS_4 = new Constant<Integer, String>(4, "平台介入,等待卖家举证");
		public static Constant<Integer, String> STATUS_5 = new Constant<Integer, String>(5, "平台介入,卖家已举证");
		public static Constant<Integer, String> STATUS_6 = new Constant<Integer, String>(6, "平台介入,平台裁定拒绝申请");
		public static Constant<Integer, String> STATUS_7 = new Constant<Integer, String>(7, "平台介入,平台裁定同意申请");
		public static Constant<Integer, String> STATUS_8 = new Constant<Integer, String>(8, "卖家已退款，等待系统结算");
		public static Constant<Integer, String> STATUS_9 = new Constant<Integer, String>(9, "卖家未处理，系统自动同意申请");
		public static Constant<Integer, String> STATUS_10 = new Constant<Integer, String>(10, "退款审核通过,系统退款至收款账户");
		public static Constant<Integer, String> STATUS_11 = new Constant<Integer, String>(11, "退款审核不通过");
		public static Constant<Integer, String> STATUS_12 = new Constant<Integer, String>(12, "完成");
		
		public static String getAfterSalesStausText(Integer status){
			String title="";
			for (Constant<Integer, String> constant : getAfterSalesStatus()) {
				if(status==constant.getKey()){
					title=constant.getValue();
					break;
				}
			}
			return title;
		}
	
		public static List<Constant<Integer, String>> getAfterSalesStatus(){
			List<Constant<Integer, String>> list=new ArrayList<Constant<Integer,String>>();
			list.add(STATUS_1);
			list.add(STATUS_2);
			list.add(STATUS_3);
			list.add(STATUS_4);
			list.add(STATUS_5);
			list.add(STATUS_6);
			list.add(STATUS_7);
			list.add(STATUS_8);
			list.add(STATUS_9);
			list.add(STATUS_10);
			list.add(STATUS_11);
			list.add(STATUS_12);
			
			return list;
		}
		
	}
	
	public static class AfterSalesRole{
		public static Constant<Integer, String> STATUS_MEMBER = new Constant<Integer, String>(1, "买家");
		public static Constant<Integer, String> STATUS_SELLER = new Constant<Integer, String>(2, "卖家");
		public static Constant<Integer, String> STATUS_ADMIN = new Constant<Integer, String>(3, "管理员");
		public static Constant<Integer, String> STATUS_SYSTEM = new Constant<Integer, String>(4, "系统");
		
		public static String getAfterSalesRoleText(Integer status){
			String title="";
			for (Constant<Integer, String> constant : getAfterSalesRoles()) {
				if(status==constant.getKey()){
					title=constant.getValue();
					break;
				}
			}
			return title;
		}
	
		public static List<Constant<Integer, String>> getAfterSalesRoles(){
			List<Constant<Integer, String>> list=new ArrayList<Constant<Integer,String>>();
			list.add(STATUS_MEMBER);
			list.add(STATUS_SELLER);
			list.add(STATUS_ADMIN);
			list.add(STATUS_SYSTEM);
			return list;
		}
	}
}
