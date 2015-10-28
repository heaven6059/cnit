package com.cnit.yoyo.spider.common.base.resourcewatcher;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 目录守护，监听目录的变化，包括增加文件、删除文件、修改文件
 * @Author:yangyi  
 * @Date:2015年6月8日  
 * @Version:1.0.0
 */
public class DirectoryWatcher extends AbstractResourceWatcher {

    /**
     * The current map of files and their timestamps (String fileName => Long
     * lastMod)
     */
    private Map currentFiles = new HashMap();

    /**
     * The directory to watch.
     */
    private String directory;

    /**
     * The map of last recorded files and their timestamps (String fileName =>
     * Long lastMod)
     */
    private Map prevFiles = new HashMap();

    /**
     * Constructor that takes the directory to watch.
     *
     * @param directoryPath   the directory to watch
     * @param intervalSeconds The interval to use when monitoring this
     *                        directory.  I.e., ever x seconds, check this directory to see
     *                        what has changed.
     * @throws IllegalArgumentException if the argument does not map to a
     *                                  valid directory
     */
    public DirectoryWatcher(String directoryPath, int intervalSeconds)
            throws IllegalArgumentException {

        //Get the common thread interval stuff set up.
        super(intervalSeconds, directoryPath + " interval watcher.");

        //Check that it is indeed a directory.
        File theDirectory = new File(directoryPath);

        if (theDirectory != null && !theDirectory.isDirectory()) {

            //This is bad, so let the caller know
            String message = "The path " + theDirectory +
                    " does not represent a valid directory.";
            throw new IllegalArgumentException(message);

        }

        //Else all is well so set this directory and the interval
        this.directory = directoryPath;

    }


    /**
     * Start the monitoring of this directory.
     */
    public void start() {

        //Since we're going to start monitoring, we want to take a snapshot of the
        //current directory to we have something to refer to when stuff changes.
        takeSnapshot();

        //And start the thread on the given interval
        super.start();

        //And notify the listeners that monitoring has started
        File theDirectory = new File(directory);
        monitoringStarted(theDirectory);
    }

    /**
     * Stop the monitoring of this directory.
     */
    public void stop() {

        //And start the thread on the given interval
        super.stop();

        //And notify the listeners that monitoring has started
        File theDirectory = new File(directory);
        monitoringStopped(theDirectory);
    }

    /**
     * Store the file names and the last modified timestamps of all the files
     * and directories that exist in the directory at this moment.
     */
    private void takeSnapshot() {

        //Set the last recorded snap shot to be the current list
        prevFiles.clear();
        prevFiles.putAll(currentFiles);

        //And get a new current state with all the files and directories
        currentFiles.clear();

        File theDirectory = new File(directory);
        File[] children = theDirectory.listFiles();

        //Store all the current files and their timestamps
        for (int i = 0; i < children.length; i++) {

            File file = children[i];
            currentFiles.put(file.getAbsolutePath(),
                    new Long(file.lastModified()));

        }

    }

    /**
     * Check this directory for any changes and fire the proper events.
     */
    protected void doInterval() {

        //Take a snapshot of the current state of the dir for comparisons
        takeSnapshot();

        //Iterate through the map of current files and compare
        //them for differences etc...
        Iterator currentIt = currentFiles.keySet().iterator();

        while (currentIt.hasNext()) {

            String fileName = (String) currentIt.next();
            Long lastModified = (Long) currentFiles.get(fileName);

            //If this file did not exist before, but it does now, then
            //it's been added
            if (!prevFiles.containsKey(fileName)) {
                //DirectorySnapshot.addFile(fileName);
                resourceAdded(new File(fileName));
            }
            //If this file did exist before
            else if (prevFiles.containsKey(fileName)) {

                Long prevModified = (Long) prevFiles.get(fileName);

                //If this file existed before and has been modified
                if (prevModified.compareTo(lastModified) != 0) {
                    // 27 June 2006
                    // Need to check if the file are removed and added
                    // during the interval
                   /* if (!DirectorySnapshot.containsFile(fileName)) {
                        resourceAdded(new File(fileName));
                    } else {*/
                        resourceChanged(new File(fileName));
                    //}
                }
            }
        }

        //Now we need to iterate through the list of previous files and
        //see if any that existed before don't exist anymore
        Iterator prevIt = prevFiles.keySet().iterator();

        while (prevIt.hasNext()) {

            String fileName = (String) prevIt.next();

            //If this file did exist before, but it does not now, then
            //it's been deleted
            if (!currentFiles.containsKey(fileName)) {
               //DirectorySnapshot.removeFile(fileName);
                resourceDeleted(fileName);
            }
        }
    }
}