package com.cnit.yoyo.spider.autohome;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cnit.yoyo.spider.common.utils.DateUtil;

/**
 * 爬虫启动类
 * @Author:yangyi  
 * @Date:2015年6月8日  
 * @Version:1.0.0
 */
@SuppressWarnings("resource")
public class SpiderRun {
	/** 日志对象 */
	private static Logger LOG = LoggerFactory.getLogger(SpiderRun.class.getName());
	/**	
	 * 文件锁对象，防止程序重复启动
	 * linux环境启动时，通过shell脚本删除锁文件再启动程序
	 */
	private String fileUrl = "";
	
	/**
	 * 运行程序
	 * @throws Exception 
	 */
	public static void main(String args[]) throws Exception{
		new SpiderRun().run(args);
	}
	public void run(String[] args) throws Exception {
		// 生产环境添加文件锁
        if (this.validateFile()) {
            throw new Exception("SpiderRun程序启动失败。");
        }
        LOG.info(DateUtil.getCurZhCNDateTime() + " 爬虫线程启动....");
		new ClassPathXmlApplicationContext("classpath:conf/spring/applicationContext.xml");
	}

	 /**
     * 检查文件是否上锁
     * 
     * @return
     * @throws IOException
     */
    public boolean validateFile() throws IOException {
        fileUrl = System.getProperty("user.dir") + "/SpiderRun.lock";
        File myfilelock = new File(fileUrl);
        RandomAccessFile raf = null;
        FileChannel fc = null;
        if (!myfilelock.exists()) {
            //不存在，则新增文件，然后加锁
            raf = new RandomAccessFile(myfilelock, "rw");
            fc = raf.getChannel();
            FileLock fl = fc.tryLock();
            if (fl != null && fl.isValid()) {
//                System.err.println(fileUrl + "文件被创建且被锁！");
                return false;
            }
        } else {
            //存在，判断是否被锁
            raf = new RandomAccessFile(myfilelock, "rw");
            fc = raf.getChannel();
            FileLock fl = fc.tryLock();
            if (fl != null && fl.isValid()) {
                //被锁
                System.err.println(fileUrl + "文件已被锁，请删除后，再启动该进程！");
                return true;
            }
        }
        return false;
    }
}
