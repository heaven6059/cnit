package com.cnit.yoyo.util.sms;

import java.io.FileReader;
import java.io.StringReader;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.io.xml.DomDriver;

@XStreamAlias("returnMsg")
public class SmsReturnMsg {
		@XStreamAlias("request")
		private String request;
		@XStreamAlias("osid")
		private String osid;
		@XStreamAlias("numbers")
		private String numbers;
		@XStreamAlias("result")
		private String result;
		
		public String getRequest() {
			return request;
		}

		public void setRequest(String request) {
			this.request = request;
		}

		public String getOsid() {
			return osid;
		}

		public void setOsid(String osid) {
			this.osid = osid;
		}

		public String getNumbers() {
			return numbers;
		}

		public void setNumbers(String numbers) {
			this.numbers = numbers;
		}

		public String getResult() {
			return result;
		}

		public void setResult(String result) {
			this.result = result;
		}

		// xml转对象
		public static SmsReturnMsg getResponse(String str) {
			try {
				XStream xstream = new XStream(new DomDriver());
				xstream.processAnnotations(SmsReturnMsg.class);
				xstream.autodetectAnnotations(true);
//				this.getClass().getResource("/AppLoginMethodConfig.xml").getFile()
				return (SmsReturnMsg) xstream.fromXML(new StringReader(str));
				//
				// return (Modules) xstream.fromXML(xml);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// 失败
				e.printStackTrace();
				return null;
			}
		}

		// 对象转xml
		public static String getResponse(SmsReturnMsg rs) {
			try {
				XStream xstream = new XStream();
				xstream.processAnnotations(SmsReturnMsg.class);
				xstream.autodetectAnnotations(true);
				return (String) xstream.toXML(rs);
			} catch (Exception e) {
				// TODO Auto-generated catch block //失败
				e.printStackTrace();
				return null;
			}
		}

		@Override
		public String toString() {
			return "SmsReturnMsg [request=" + request + ", osid=" + osid
					+ ", numbers=" + numbers + ", result=" + result + "]";
		}
		
		
	}
