package com.cnit.yoyo.spider.common.base.resourcewatcher;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * 资源守护抽象类，提供给不同的资源子类继承
 * @Author:yangyi  
 * @Date:2015年6月8日  
 * @Version:1.0.0
 */
public abstract class AbstractResourceWatcher extends IntervalThread implements
        IResourceWatcher
{

    /**
     * The list of listeners to notify when this directory changes.
     */
    private Collection listeners = new LinkedList();

    /**
     * Constructor that takes the directory to watch.
     * 
     * @param name
     *            the directory to watch
     * @param intervalSeconds
     *            The interval to use when monitoring this resource. I.e., ever
     *            x seconds, check this resource to see what has changed.
     */
    public AbstractResourceWatcher(int intervalSeconds, String name)
    {

        // Get the common thread interval stuff set up.
        super(intervalSeconds, name);
    }

    /**
     * Remove all listeners from this watcher.
     */
    public void removeAllListeners()
    {

        // Clear the list of all listeners
        listeners.clear();
    }

    /**
     * Add a listener to this directory.
     * 
     * @param listener
     *            the listener to add
     */
    public void addListener(IResourceListener listener)
    {

        // And add the listener
        listeners.add(listener);
    }

    /**
     * Remove a listener from this watcher.
     * 
     * @param listener
     *            the listener to remove
     */
    public void removeListener(IResourceListener listener)
    {

        // Iterate through the listeners and remove the this listener
        listeners.remove(listener);
    }

    /**
     * When an item is added to this resource, this method will be called. It
     * will fire the onAdd() method of all its listeners, passing in the item
     * that has been added.
     * 
     * @param newResource
     *            the item that has been added to this resource.
     */
    protected void resourceAdded(Object newResource)
    {

        // Iterate through the listeners and fire the onAdd method
        Iterator listIt = listeners.iterator();

        while (listIt.hasNext())
        {
            ((IResourceListener) listIt.next()).onAdd(newResource);
        }
    }

    /**
     * When an item is changed in this resource, this method will be called. It
     * will fire the onChange() method of all its listeners, passing in the item
     * that has been changed.
     * 
     * @param changedResource
     *            the item that has been added to this resource.
     */
    protected void resourceChanged(Object changedResource)
    {

        // Iterate through the listeners and fire the onChange method
        Iterator listIt = listeners.iterator();

        while (listIt.hasNext())
        {
            ((IResourceListener) listIt.next()).onChange(changedResource);
        }
    }

    /**
     * When an item is deleted in this resource, this method will be called. It
     * will fire the onChange() method of all its listeners, passing in the item
     * that has been deleted.
     * 
     * @param deletedResource
     *            the item that has been added to this resource.
     */
    protected void resourceDeleted(Object deletedResource)
    {

        // Iterate through the listeners and fire the onDelete method
        Iterator listIt = listeners.iterator();

        while (listIt.hasNext())
        {
            ((IResourceListener) listIt.next()).onDelete(deletedResource);
        }
    }

    /**
     * When monitoring starts on an item, this method will be called.
     * 
     * @param monitoredResource
     *            the resource that is now being monitored
     */
    protected void monitoringStarted(Object monitoredResource)
    {

        // Iterate through the listeners and fire the onStart method
        Iterator listIt = listeners.iterator();

        while (listIt.hasNext())
        {
            ((IResourceListener) listIt.next()).onStart(monitoredResource);
        }
    }

    /**
     * When monitoring stops on an item, this method will be called.
     * 
     * @param notMonitoredResource
     *            the resource that is not being monitored anymore.
     */
    protected void monitoringStopped(Object notMonitoredResource)
    {

        // Iterate through the listeners and fire the onStop method
        Iterator listIt = listeners.iterator();

        while (listIt.hasNext())
        {
            ((IResourceListener) listIt.next()).onStop(notMonitoredResource);
        }
    }

    /**
     * The interval has expired and now it's time to do something.
     */
    protected abstract void doInterval();
}