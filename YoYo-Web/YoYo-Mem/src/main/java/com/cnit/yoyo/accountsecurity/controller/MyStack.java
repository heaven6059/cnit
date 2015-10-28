package com.cnit.yoyo.accountsecurity.controller;

import java.util.ArrayList;
import java.util.List;

/**   
 * @Description: 
 * @author wanghb
 * @date 2015年10月15日
 * @Copyright 2015 cnit
 * @version V1.0.0 		
*/
public class MyStack {  
    private List<String> list = new ArrayList<String>();  
  
    public synchronized void push(String value) {  
        synchronized (this) {  
            list.add(value);  
            notify();  
        }  
    }  
  
    public synchronized String pop() throws InterruptedException {  
        synchronized (this) {  
            if (list.size() <= 0) {  
                wait();  
            }  
            return list.remove(list.size() - 1);  
        }  
    }  
} 

