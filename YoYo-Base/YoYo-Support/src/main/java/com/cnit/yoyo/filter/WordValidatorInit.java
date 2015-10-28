package com.cnit.yoyo.filter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.cnit.yoyo.util.RedisClientUtil;

/**
 * @Description:
 * @author wanghb
 * @date 2015年8月20日
 * @Copyright 2015 cnit
 * @version V1.0.0
 */
// 敏感词库初始化
public class WordValidatorInit  {

	public static final Logger logger = LoggerFactory.getLogger(WordValidatorInit.class);

	private String ENCODING = "gb2312"; // 字符编码
	public HashMap sensitiveWordMap;

	@Autowired
	private RedisClientUtil redisService;
	
	public Map<?, ?> initKeyWord() {
		try {
			logger.info("开始读取敏感词库文件。。。。。");
			// 读取敏感词库
			Set<String> keyWordSet = readSensitiveWordFile();
			logger.info("读取敏感词库文件完毕！");
			// 将敏感词库加入到HashMap中
			addSensitiveWordToHashMap(keyWordSet);
			logger.info("将敏感词库加入到内存完毕！");
//			String json = JSON.toJSONString(sensitiveWordMap,true); 
//			redisService.set("sensitiveWordMap",json);
			// spring获取application，然后application.setAttribute("sensitiveWordMap",sensitiveWordMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sensitiveWordMap;
	}

	/**
	 * @param keyWordSet 敏感词库
	 * @version 1.0
	 */
	private void addSensitiveWordToHashMap(Set<String> keyWordSet) {
		sensitiveWordMap = new HashMap(keyWordSet.size()); // 初始化敏感词容器，减少扩容操作
		String key = null;
		Map nowMap = null;
		Map<String, String> newWorMap = null;
		// 迭代keyWordSet
		Iterator<String> iterator = keyWordSet.iterator();
		while (iterator.hasNext()) {
			key = iterator.next(); // 关键字
			nowMap = sensitiveWordMap;
			for (int i = 0; i < key.length(); i++) {
				char keyChar = key.charAt(i); // 转换成char型
				Object wordMap = nowMap.get(keyChar); // 获取
				if (wordMap != null) { // 如果存在该key，直接赋值
					nowMap = (Map) wordMap;
				} else { // 不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
					newWorMap = new HashMap<String, String>();
					newWorMap.put("isEnd", "0"); // 不是最后一个
					nowMap.put(keyChar, newWorMap);
					nowMap = newWorMap;
				}
				if (i == key.length() - 1) {
					nowMap.put("isEnd", "1"); // 最后一个
				}
			}
		}
	}

	/**
	 * 
	 * 读取敏感词库中的内容，将内容添加到set集合中
	 * 
	 * @return
	 * @version 1.0
	 * @throws Exception
	 */
	private Set<String> readSensitiveWordFile() throws Exception {
		Set<String> set = null;
		File file = new File(getClass().getClassLoader().getResource("SensitiveWord.txt").getPath()); // 读取文件
		InputStreamReader read = new InputStreamReader(new FileInputStream(file), ENCODING);
		try {
			if (file.isFile() && file.exists()) { // 文件流是否存在
				set = new HashSet<String>();
				BufferedReader bufferedReader = new BufferedReader(read);
				String txt = null;
				while ((txt = bufferedReader.readLine()) != null) { // 读取文件，将文件内容放入到set中
					set.add(txt);
				}
			} else { // 不存在抛出异常信息
				throw new Exception("敏感词库文件不存在");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			read.close(); // 关闭文件流
		}
		return set;
	}

//	@Override
//	public void onApplicationEvent(ContextRefreshedEvent event) {
//		if (event.getApplicationContext().getParent() == null) {
//			this.initKeyWord();
//			logger.info("初始化数据到内存中..................");
//		}
//	}
}
