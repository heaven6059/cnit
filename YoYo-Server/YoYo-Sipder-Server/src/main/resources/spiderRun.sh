#!/bin/bash

if [ ! $JAVA_HOME ];then
        if [ -d "${javahome}" ]; then
                export JAVA_HOME=${javahome}
        else
                echo "error:no JAVA_HOME is not set,please set JAVA_HOME";
                exit;
        fi
fi

echo "JAVA_HOME=$JAVA_HOME"
#get current shell's parent path
parent_path=$(cd `dirname $0`;cd ../; pwd)
export CLIENT_HOME=$parent_path

export CLIENT_LIB_HOME=$CLIENT_HOME/lib

export CLASSPATH=.:$CLIENT_LIB_HOME/*

#kill 已有进程
pkill -9 -f SpiderRun
#删除锁文件
rm -f SpiderRun.lock

#CATALINA_OPTS="-server -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8899"

echo "java -version:"
$JAVA_HOME/bin/java -version
echo "SpiderRun start:"

rm -rf $CLIENT_HOME/spiderrun-yoyo-thread.log
#启动线程
$JAVA_HOME/bin/java -Xmx1048m $CATALINA_OPTS com.cnit.yoyo.spider.autohome.SpiderRun forever &
