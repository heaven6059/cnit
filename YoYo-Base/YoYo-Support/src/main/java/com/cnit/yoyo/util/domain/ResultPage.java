/**
 * 文 件 名   :  ResultPage.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-3-2 上午11:36:10
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.util.domain;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.Page;

/**
 * @description <一句话功能简述>
 * @detail <功能详细描述>
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class ResultPage<T> implements Serializable {
    private static final long serialVersionUID = 8862292416016162086L;
    private int pageIndex;//页码
    private int pageSize;//页面大小
    private int currPageSize;//当前页数量
    private long total;//总数
    private int pages;//总页数
    private List<T> rows;

	public ResultPage(List<T> rows) {
        if (rows instanceof Page) {
            Page page = (Page) rows;
            this.pageIndex = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.total = page.getTotal();
            this.pages = page.getPages();
            this.rows = page;
            this.currPageSize = page.size();
        }
    }


	public ResultPage() {
		
		// TODO Auto-generated constructor stub
		
	}

	public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrPageSize() {
        return currPageSize;
    }

    public void setCurrPageSize(int currPageSize) {
        this.currPageSize = currPageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
