/**
 * 文 件 名   :  ResultObject.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  &lt;描述&gt;
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-1-27 上午9:25:55
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.domain;

import java.io.Serializable;

import net.sf.json.JSONObject;

/**
 * @description 返回页面结果集
 * @detail <功能详细描述>
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
public class ResultObject implements Serializable {
    private static final long serialVersionUID = -8948747420144666605L;
    /** 返回数据头部信息 */
    private HeadObject head;
    /** 返回数据体 */
    private Object content;

    public ResultObject() {
    }

    public ResultObject(HeadObject head) {
        this.head = head;
    }

    public ResultObject(HeadObject head, Object content) {
        this.head = head;
        this.content = content;
    }

    public HeadObject getHead() {
        return head;
    }

    public void setHead(HeadObject head) {
        this.head = head;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public String getRetCode() {
        return this.head.getRetCode();
    }
    public String getRetMsg() {
        return this.head.getRetMsg();
    }
    
    @Override
    public String toString() {
        JSONObject jsonObject = JSONObject.fromObject(this);
        return jsonObject.toString();
    }
}
