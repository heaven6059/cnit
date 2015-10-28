package com.cnit.yoyo.spider.common.base.vo;

import java.util.List;

public class PageModel<T>
{
    private int page = 1;

    public int totalPages = 0;

    private int pageRecorders;

    private int totalRows = 0;

    private int pageStartRow = 0;

    private int pageEndRow = 0;

    private boolean hasNextPage = false;

    private boolean hasPreviousPage = false;

    private List<T> list;

    public PageModel(List<T> list, int pageRecorders)
    {
        init(list, pageRecorders);// 通过对象集，记录总数划分
    }

    public void init(List<T> list, int pageRecorders)
    {
        this.pageRecorders = pageRecorders;
        this.list = list;
        totalRows = list.size();
        // it = list.iterator();
        hasPreviousPage = false;
        if ((totalRows % pageRecorders) == 0)
        {
            totalPages = totalRows / pageRecorders;
        }
        else
        {
            totalPages = totalRows / pageRecorders + 1;
        }

        if (page >= totalPages)
        {
            hasNextPage = false;
        }
        else
        {
            hasNextPage = true;
        }

        if (totalRows < pageRecorders)
        {
            this.pageStartRow = 0;
            this.pageEndRow = totalRows;
        }
        else
        {
            this.pageStartRow = 0;
            this.pageEndRow = pageRecorders;
        }
    }

    public boolean isNext()
    {
        return list.size() > 5;
    }

    public void setHasPreviousPage(boolean hasPreviousPage)
    {
        this.hasPreviousPage = hasPreviousPage;
    }

    public String toString(int temp)
    {
        String str = Integer.toString(temp);
        return str;
    }

    public void description()
    {

        String description = "共有数据数:" + this.getTotalRows() +

        "共有页数: " + this.getTotalPages() +

        "当前页数为:" + this.getPage() +

        " 是否有前一页: " + this.isHasPreviousPage() +

        " 是否有下一页:" + this.isHasNextPage() +

        " 开始行数:" + this.getPageStartRow() +

        " 终止行数:" + this.getPageEndRow();

        System.out.println(description);
    }

    public List<T> getNextPage()
    {
        page = page + 1;

        disposePage();

        System.out.println("用户凋用的是第" + page + "页");
        this.description();
        return getObjects(page);
    }

    private void disposePage()
    {

        if (page == 0)
        {
            page = 1;
        }

        if ((page - 1) > 0)
        {
            hasPreviousPage = true;
        }
        else
        {
            hasPreviousPage = false;
        }

        if (page >= totalPages)
        {
            hasNextPage = false;
        }
        else
        {
            hasNextPage = true;
        }
    }

    public List<T> getPreviousPage()
    {

        page = page - 1;

        if ((page - 1) > 0)
        {
            hasPreviousPage = true;
        }
        else
        {
            hasPreviousPage = false;
        }
        if (page >= totalPages)
        {
            hasNextPage = false;
        }
        else
        {
            hasNextPage = true;
        }
        this.description();
        return getObjects(page);
    }

    /**
     * 
     * 方法描述：获取第几页的内容
     * 
     * @param page
     * @return
     */
    public List<T> getObjects(int page)
    {
        if (page == 0)
            this.setPage(1);
        else
            this.setPage(page);
        this.disposePage();
        if (page * pageRecorders < totalRows)
        {// 判断是否为最后一页
            pageEndRow = page * pageRecorders;
            pageStartRow = pageEndRow - pageRecorders;
        }
        else
        {
            pageEndRow = totalRows;
            pageStartRow = pageRecorders * (totalPages - 1);
        }

        List<T> objects = null;
        if (!list.isEmpty())
        {
            objects = list.subList(pageStartRow, pageEndRow);
        }
        // this.description();
        return objects;
    }

    public List<T> getFistPage()
    {
        if (this.isNext())
        {
            return list.subList(0, pageRecorders);
        }
        else
        {
            return list;
        }
    }

    public boolean isHasNextPage()
    {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage)
    {
        this.hasNextPage = hasNextPage;
    }

    public List<T> getList()
    {
        return list;
    }

    public void setList(List<T> list)
    {
        this.list = list;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public int getPageEndRow()
    {
        return pageEndRow;
    }

    public void setPageEndRow(int pageEndRow)
    {
        this.pageEndRow = pageEndRow;
    }

    public int getPageRecorders()
    {
        return pageRecorders;
    }

    public void setPageRecorders(int pageRecorders)
    {
        this.pageRecorders = pageRecorders;
    }

    public int getPageStartRow()
    {
        return pageStartRow;
    }

    public void setPageStartRow(int pageStartRow)
    {
        this.pageStartRow = pageStartRow;
    }

    public int getTotalPages()
    {
        return totalPages;
    }

    public void setTotalPages(int totalPages)
    {
        this.totalPages = totalPages;
    }

    public int getTotalRows()
    {
        return totalRows;
    }

    public void setTotalRows(int totalRows)
    {
        this.totalRows = totalRows;
    }

    public boolean isHasPreviousPage()
    {
        return hasPreviousPage;
    }

}
