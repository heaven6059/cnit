/**
 * 文 件 名   :  RequestObject.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-2-2 下午5:46:29
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.domain;

import java.io.Serializable;

import net.sf.json.JSONObject;

/**
 * @description 页面请求对象
 * @detail <功能详细描述>
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
public class RequestObject implements Serializable {
    private static final long serialVersionUID = -6898881470958029717L;
    /** 请求头部信息 */
    private HeadObject head;
    /** 请求数据体 */
    private Object content;

    public RequestObject() {
    }

    public RequestObject(HeadObject head) {
        this.head = head;
    }

    public RequestObject(HeadObject head, Object content) {
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

    @Override
    public String toString() {
        JSONObject jsonObject = JSONObject.fromObject(this);
        return jsonObject.toString();
    }
}
