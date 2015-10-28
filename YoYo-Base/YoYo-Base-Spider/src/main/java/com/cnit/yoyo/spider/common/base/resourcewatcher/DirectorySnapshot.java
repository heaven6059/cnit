package com.cnit.yoyo.spider.common.base.resourcewatcher;

import java.util.Map;
import java.util.HashMap;


public class DirectorySnapshot {

    private static Map files = new HashMap();

    public static void addFile(String fileName){
        files.put(fileName, fileName);
    }

    public static void removeFile(String fileName){
        files.remove(fileName);
    }

    public static boolean containsFile(String fileName){
        return files.containsKey(fileName);
    }
}
