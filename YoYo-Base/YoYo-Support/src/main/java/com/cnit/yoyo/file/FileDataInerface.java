package com.cnit.yoyo.file;  

/**
 * 分块上传文件
 * @Author:yangyi  
 * @Date:2015年8月21日  
 * @Version:1.0.0
 */
public interface FileDataInerface {
    /**
     * @Description:文件初始化调用
     * @param fileType 上传文件类型：例如ios，安卓
     * @param suffix上 传文件后缀名
     * @return
     * @throws RemoteException
     */
    public String start(String fileType,String suffix);
    /**
     * @Description:分块上传逻辑
     * @param uploadId 初始化后返回的随机值
     * @param part 文件块
     * @param offset 开始位置
     * @param len 长度
     * @throws RemoteException
     */
    public void upload(String uploadId,byte[] part,int offset,int len);
     /**
      * @Description:上传完成客户端调用
      * @param uploadId 初始化后返回的随机值
      * @return 
      * @throws RemoteException
      */
    public String finish(String uploadId);
 
}
