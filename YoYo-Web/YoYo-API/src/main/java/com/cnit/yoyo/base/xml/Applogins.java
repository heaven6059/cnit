package com.cnit.yoyo.base.xml;

import java.io.FileReader;
import java.util.List;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.io.xml.DomDriver;

@XStreamAlias("applogins")
public class Applogins {
		@XStreamImplicit(itemFieldName = "method")
		private List<String> method;

		public List<String> getMethod() {
			return method;
		}

		public void setMethod(List<String> method) {
			this.method = method;
		}

		@XStreamAlias("method")
		public static class Id {
			@XStreamAlias("method")
			private List<String> method;

			public List<String> getMethod() {
				return method;
			}

			public void setMethod(List<String> method) {
				this.method = method;
			}
		} 
		// xml转对象
		public Applogins getResponse(String xml) {
			try {
				XStream xstream = new XStream(new DomDriver());
				xstream.processAnnotations(Applogins.class);
				xstream.autodetectAnnotations(true);
//				this.getClass().getResource("/AppLoginMethodConfig.xml").getFile()
				return (Applogins) xstream.fromXML(new FileReader(xml));
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
		public static String getResponse(Applogins rs) {
			try {
				XStream xstream = new XStream();
				xstream.processAnnotations(Applogins.class);
				xstream.autodetectAnnotations(true);
				return (String) xstream.toXML(rs);
			} catch (Exception e) {
				// TODO Auto-generated catch block //失败
				e.printStackTrace();
				return null;
			}
		}
	}
