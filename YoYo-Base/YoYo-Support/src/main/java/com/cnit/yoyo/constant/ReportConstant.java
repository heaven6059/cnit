package com.cnit.yoyo.constant;

import java.util.ArrayList;
import java.util.List;

public class ReportConstant {
			
		/**  
		* @Title: ComplainConstant.java
		* @Package com.cnit.yoyo.constant
		* @Description: 投诉状态
		* @Author 王鹏
		* @date 2015-5-28 下午4:51:52
		* @version V1.0  
		*/ 
		public static class ReportStatus{
			public static Constant<String, String> INTERVENE = new Constant<String, String>("intervene", "平台介入");
			public static Constant<String, String> SUCCESS = new Constant<String, String>("success", "投诉成立");
			public static Constant<String, String> ERROR = new Constant<String, String>("error", "投诉不成立");
			public static Constant<String, String> CANCEL = new Constant<String, String>("cancel", "撤销投诉");
			public static Constant<String, String> FINISH = new Constant<String, String>("finish", "完成");
			
			public static List<Constant<String, String>> getReportStatus(){
				List<Constant<String, String>> reports=new ArrayList<Constant<String,String>>();
				reports.add(INTERVENE);
				reports.add(SUCCESS);
				reports.add(ERROR);
				reports.add(CANCEL);
				reports.add(FINISH);
				return reports;
			}
		}
}
