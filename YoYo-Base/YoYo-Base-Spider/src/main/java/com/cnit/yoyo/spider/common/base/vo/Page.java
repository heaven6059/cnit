package com.cnit.yoyo.spider.common.base.vo;

import java.io.Serializable;

/**
 * 列表分页显示控制类
 * 
 */
public class Page implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = -29269622478719207L;

    // 记录总数
    private int counts = -1;

    // 每页显示记录数
    private int pageSize=10;

    // 总页数
    private int totalPage = 1;

    // 当前页
    private int curPage = 1;

    // 页面显示开始记录数
    private int firstResult = 1;

    // 页面显示最后记录数
    private int lastResult = 1;

    /*
     * 排序名称
     */
    private String orderName;

    /*
     * 排序类型
     */
    private String orderType;

    private String orderBy;

    public Page(int counts, int pageSize)
    {
        // 计算所有的页面数
        this.counts = counts;
        // this.totalPage = (int)Math.ceil((this.counts + this.perPageSize - 1)
        // / this.perPageSize);
        if (counts % pageSize == 0)
        {
            this.totalPage = this.counts / this.pageSize;
        }
        else
        {
            this.totalPage = this.counts / this.pageSize + 1;
        }
    }

    public Page()
    {
    }

    /**
     * @return the counts
     */
    public int getCounts()
    {
        return counts;
    }

    /**
     * @param counts
     *            the counts to set
     */
    public void setCounts(int counts, int pageSize)
    {
        this.pageSize = pageSize;
        // 计算所有的页面数
        this.counts = counts;
        // this.totalPage = (int)Math.ceil((this.counts + this.perPageSize - 1)
        // / this.perPageSize);
        if (counts % this.pageSize == 0)
        {
            this.totalPage = this.counts / this.pageSize;
        }
        else
        {
            this.totalPage = this.counts / this.pageSize + 1;
        }
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    /**
     * @return the totalPage
     */
    public int getTotalPage()
    {
        if (totalPage < 1)
        {
            return 1;
        }
        return totalPage;
    }

    /**
     * @param totalPage
     *            the totalPage to set
     */
    public void setTotalPage(int totalPage)
    {
        this.totalPage = totalPage;
    }

    public int getCurPage()
    {
        return curPage;
    }

    public void setCurPage(int curPage)
    {
        this.curPage = curPage;
    }

    /**
     * @return the firstResult
     */
    public int getFirstResult()
    {
        int temp = this.curPage - 1;
        if (temp <= 0)
        {
            return 0;
        }
        return this.firstResult = (this.curPage - 1) * this.pageSize;
    }

    /**
     * @param firstResult
     *            the firstResult to set
     */
    public void setFirstResult(int firstResult)
    {
        this.firstResult = firstResult;
    }

    /**
     * @return the lastResult
     */
    public int getLastResult()
    {
        this.lastResult = this.firstResult + this.pageSize;
        return lastResult;
    }

    /**
     * @param lastResult
     *            the lastResult to set
     */
    public void setLastResult(int lastResult)
    {
        this.lastResult = lastResult;
    }

    /**
     * @return the orderName
     */
    public String getOrderName()
    {
        return orderName;
    }

    /**
     * @param orderName
     *            the orderName to set
     */
    public void setOrderName(String orderName)
    {
        this.orderName = orderName;
    }

    /**
     * @return the orderBy
     */
    public String getOrderType()
    {
        return orderType;
    }

    /**
     * @param orderBy
     *            the orderBy to set
     */
    public void setOrderType(String orderType)
    {
        this.orderType = orderType;
    }

    /**
     * @return the orderBy
     */
    public String getOrderBy()
    {
        if (this.getOrderName() == null || this.getOrderName().equals("")
                || this.getOrderType() == null
                || this.getOrderType().equals(""))
        {
            return "";
        }
        orderBy = " order by " + this.getOrderName() + " "
                + this.getOrderType();
        return orderBy;
    }

    /**
     * @param orderBy
     *            the orderBy to set
     */
    public void setOrderBy(String orderBy)
    {
        this.orderBy = orderBy;
    }

    public void setCounts(int counts)
    {
        this.counts = counts;
    }

}
