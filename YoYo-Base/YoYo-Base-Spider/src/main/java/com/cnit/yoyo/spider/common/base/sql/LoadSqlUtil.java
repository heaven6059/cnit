package com.cnit.yoyo.spider.common.base.sql;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class LoadSqlUtil {

	private static final Map<Class<?>, Map<String, String>> sqlTmp = new HashMap<Class<?>, Map<String, String>>();

	private static Lock lock = new ReentrantLock();

	public static void main(String[] args) {
		
		System.out.println(LoadSqlUtil.class.getSimpleName());
		
	}
	
	
	public static  void reLoadSql(){
		sqlTmp.clear();
	}
	
	
	public static String loadSql(Class<?> clazz, String id) {
		Map<String, String> sqls = sqlTmp.get(clazz);
		if (null == sqls) {
			lock.lock();
			try {

				Map<String, String> map = loadXml(clazz);
				if (null == map) {
					return null;
				}

				sqlTmp.put(clazz, map);

				return map.get(id);
			} finally {
				lock.unlock();
			}
		}
		return sqls.get(id);
	}

	private static  Map<String, String> loadXml(Class<?> clazz) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			InputStream is = clazz.getClassLoader().getResourceAsStream(
					"sql/"+clazz.getSimpleName()+ ".xml");
			SaxSqlConfigHandler handle = new SaxSqlConfigHandler();
			saxParser.parse(is, handle);
			is.close();
			return handle.getSqlMap();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
