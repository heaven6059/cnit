/*
 * @fileName：Node.java    2014-7-24 下午06:16:34
 *
 * Copyright (c) 2014 tCloud Technologies, Inc. All rights reserved.
 * <P>Title：<P>
 * <P>Description：<P>
 * <P>Copyright: Copyright (c) 2014 <P>
 * <P>Company: tCloud <P>
 * @author tcloud
 * @version 1.0.0
 *
 */
package com.cnit.yoyo.spider.common.base.vo;

import java.io.Serializable;

public class Node implements Serializable
{
    private static final long serialVersionUID = 5143701347772766283L;

    private String sn;

    private String mobile;

    private String id;

    private String pId;

    private String name;

    private Integer level;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getpId()
    {
        return pId;
    }

    public void setpId(String pId)
    {
        this.pId = pId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getLevel()
    {
        return level;
    }

    public void setLevel(Integer level)
    {
        this.level = level;
    }

    public void setSn(String sn)
    {
        this.sn = sn;
    }

    public String getSn()
    {
        return sn;
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

}
