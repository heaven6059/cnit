package com.cnit.yoyo.spider.common.base.resourcewatcher;

/**
 * 扫描线程
 * @Author:yangyi  
 * @Date:2015年6月8日  
 * @Version:1.0.0
 */
public abstract class IntervalThread implements Runnable
{

    /**
     * Whether or not this thread is active.
     */
    private boolean active = false;

    /**
     * The interval in seconds to run this thread
     */
    private int interval = -1;

    /**
     * The name of this pool (for loggin/display purposes).
     */
    private String name;

    /**
     * This instance's thread
     */
    private Thread runner;

    /**
     * Construct a new interval thread that will run on the given interval with
     * the given name.
     * 
     * @param intervalSeconds
     *            the number of seconds to run the thread on
     * @param name
     *            the name of the thread
     */
    public IntervalThread(int intervalSeconds, String name)
    {

        this.interval = intervalSeconds * 1000;
        this.name = name;
    }

    /**
     * Start the thread on the specified interval.
     */
    public void start()
    {

        active = true;

        // If we don't have a thread yet, make one and start it.
        if (runner == null && interval > 0)
        {
            runner = new Thread(this);
            runner.start();
        }
    }

    /**
     * Stop the interval thread.
     */
    public void stop()
    {
        active = false;
    }

    /**
     * Not for public use. This thread is automatically started when a new
     * instance is retrieved with the getInstance method. Use the start() and
     * stop() methods to control the thread.
     * 
     * @see Thread#run()
     */
    public void run()
    {

        // Make this a relatively low level thread
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

        // Pause this thread for the amount of the interval
        while (active)
        {
            try
            {
                doInterval();
                Thread.sleep(interval);

            }
            catch (InterruptedException e)
            {
                // Ignore
            }
        }
    }

    /**
     * String representation of this object. Just the name given to it an
     * instantiation.
     * 
     * @return the string name of this pool
     */
    public String toString()
    {
        return name;
    }

    /**
     * The interval has expired and now it's time to do something.
     */
    protected abstract void doInterval();
}
