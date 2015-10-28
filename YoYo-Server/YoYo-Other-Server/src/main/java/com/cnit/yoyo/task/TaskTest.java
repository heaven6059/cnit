package com.cnit.yoyo.task;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class TaskTest {
 
	public void run(Object test) {
		for (int i = 0; i < 1; i++) {
 			System.out.println(i+" run......................................" + (new Date()));
		}

	}

	public void run1(Object test) {
		for (int i = 0; i < 1; i++) {
			System.out.println(i+" run1......................................" + (new Date()));
		}
	}
	
	public static void main(String[] args) {
		String c=null;
	    Map<String, Charset> charsets = Charset.availableCharsets();
	    for (Map.Entry<String, Charset> entry : charsets.entrySet()) {
	       System.out.println(entry.getKey());
	    }

	}
}
