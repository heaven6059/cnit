package com.cnit.yoyo.spider.common.base.sql;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxSqlConfigHandler extends DefaultHandler {

	private String id;
	private StringBuilder value;
	private boolean content;

	private Map<String, String> sqlMap = new HashMap<String, String>();

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if ("sql".equals(qName)) {
			value=new StringBuilder();
			id = attributes.getValue("id");
			content=true;
		}

	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if ("sql".equals(qName)) {
			sqlMap.put(id, value.toString());
			content=false;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if(content){
			value  =value.append(new String(ch, start, length));
		}
	}
	
	public Map<String, String> getSqlMap(){
		return sqlMap;
	}
	
}
