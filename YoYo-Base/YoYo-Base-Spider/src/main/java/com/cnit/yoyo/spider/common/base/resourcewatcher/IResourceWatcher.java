package com.cnit.yoyo.spider.common.base.resourcewatcher;

/**
 * 
 * <P>Description：Representation of an object that watches a resource (directory, database
 * etc...) for any changes and notifies its list of listeners when an event
 * occurs. </P>
 * 
 * @author tcloud
 * @version 1.0
 */
public interface IResourceWatcher {

    /**
     * Start watching the resource.
     */
    public void start();

    /**
     * Add a listener to this watcher.
     *
     * @param listener the listener to add
     */
    public void addListener(IResourceListener listener);

    /**
     * Remove a listener from this watcher.
     *
     * @param listener the listener to remove
     */
    public void removeListener(IResourceListener listener);

    /**
     * Stop the monitoring of the particular resource.
     */
    public void stop();
}

/*
 * $Log: IResourceWatcher.java,v $
 * Revision 1.1.1.1  2006/04/17 09:53:44  mengwang
 * ODS/CUG backend
 *
 */
