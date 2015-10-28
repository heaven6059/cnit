package com.cnit.yoyo.spider.common.base.resourcewatcher;

/**
 * 资源监听接口
 * @Author:yangyi  
 * @Date:2015年6月8日  
 * @Version:1.0.0
 */
public interface IResourceListener
{

    /**
     * Monitoring has just started on this new resource.
     * 
     * @param monitoredResource
     *            the resource now being monitored.
     */
    public void onStart(Object monitoredResource);

    /**
     * Monitoring has just ended on this new resource.
     * 
     * @param notMonitoredResource
     *            the resource not being monitored anymore.
     */
    public void onStop(Object notMonitoredResource);

    /**
     * Something has been added to this resource, or the resource itself has
     * been added.
     * 
     * @param newResource
     *            the new resource
     */
    public void onAdd(Object newResource);

    /**
     * The resource has been changed.
     * 
     * @param changedResource
     *            the changed resource
     */
    public void onChange(Object changedResource);

    /**
     * Something has been added to this resource, or the resource itself has
     * been added.
     * 
     * @param deletedResource
     *            the deleted resource
     */
    public void onDelete(Object deletedResource);
}
