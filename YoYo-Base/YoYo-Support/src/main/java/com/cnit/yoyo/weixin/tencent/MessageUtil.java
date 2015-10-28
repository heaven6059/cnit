package com.cnit.yoyo.weixin.tencent;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.cnit.yoyo.weixin.tencent.pojo.Article;
import com.cnit.yoyo.weixin.tencent.pojo.Media;
import com.cnit.yoyo.weixin.tencent.pojo.NewsMsg;
import com.cnit.yoyo.weixin.tencent.pojo.RespImageMsg;
import com.cnit.yoyo.weixin.tencent.pojo.RespMusicMsg;
import com.cnit.yoyo.weixin.tencent.pojo.TextMsg;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;


public class MessageUtil {
	
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";
	public static final String RESP_MESSAGE_TYPE_IMAGE = "image";
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
	
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
	/**
	 * 事件类型：subscribe(订阅)
	 */
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
	public static final String EVENT_TYPE_CLICK = "CLICK";
	public static final String EVENT_TYPE_VIEW = "VIEW";
	public static final String EVENT_TYPE_SCANCODE = "scancode_push";
	public static final String EVENT_TYPE_LOCATION = "location_select";
	
	
	
	/**
	 * 将微信发来的xml数据包转换成map
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static Map<String,String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException{
		Map<String,String> map = new HashMap<String,String>();
		// 从request中取得输入流
		InputStream inputStream = request.getInputStream();
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		System.out.println("接收参数："+document.asXML());
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();
		// 遍历所有子节点
		for(Element e : elementList){
			map.put(e.getName(), e.getText());
		}
		// 释放资源
		inputStream.close();
		inputStream = null;
		return map;
	}
	
	/**
	 * 将文本消息转换成xml
	 * @param textMsg
	 * @return
	 */
	public static String textMsgToXml(TextMsg textMsg){
		xstream.alias("xml", textMsg.getClass());
		return xstream.toXML(textMsg);
	}
	
	/**
	 * 扩展xstream，使其支持CDATA块
	 */
	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out){
				//对那些xml节点的转换增加CDATA标记   true增加  false反之
                boolean cdata = false;
                
                public void startNode(String name, Class clazz) {  
                	if(!name.equals("xml") && !name.equals("item")){
                		char[] arr = name.toCharArray();        
                        if (arr[0] >= 'a' && arr[0] <= 'z') {
                        	//arr[0] -= 'a' - 'A';
                        	//ASCII码，大写字母和小写字符之间数值上差32
                        	arr[0] = (char) ((int) arr[0] - 32);
                        }
                        name = new String(arr);
                	}
                	super.startNode(name, clazz);  
                }
                
                @Override
                public void setValue(String text) {
                    if(text!=null && !"".equals(text)){
                        //浮点型判断
                        Pattern patternInt = Pattern.compile("[0-9]*(\\.?)[0-9]*");
                        //整型判断
                        Pattern patternFloat = Pattern.compile("[0-9]+");
                        //如果是整数或浮点数 就不要加[CDATA[]了
                        if(patternInt.matcher(text).matches() || patternFloat.matcher(text).matches()){
                        	cdata = false;
                        }else{
                        	cdata = true;
                        }
                    }
                    super.setValue(text);  
                }
                
                protected void writeText(QuickWriter writer, String text) {  
                     if (cdata) {  
                        writer.write("<![CDATA[");  
                        writer.write(text);  
                        writer.write("]]>");  
                    } else {  
                        writer.write(text);
                    } 
               }  
			};
		}
	});

	/**
	 * 将图文消息对象转换成xml
	 * @param newsMsg
	 * @return
	 */
	public static String newsMsgToXml(NewsMsg newsMsg) {
		xstream.alias("xml", newsMsg.getClass());
		xstream.alias("item", new Article().getClass());
		return xstream.toXML(newsMsg);
	}

	/**
	 * 将回复图片消息对象转换成XML
	 * @param imageMsg
	 * @return
	 */
	public static String respImageMsgToXml(RespImageMsg imageMsg) {
		xstream.alias("xml", imageMsg.getClass());
		xstream.alias("Image", new Media().getClass());
		return xstream.toXML(imageMsg);
	}

	/**
	 * 将回复音乐消息对象转换成XML
	 * @param musicMsg
	 * @return
	 */
	public static String respMusicMsgToXml(RespMusicMsg musicMsg) {
		xstream.alias("xml", musicMsg.getClass());
		return xstream.toXML(musicMsg);
	}
	
}
