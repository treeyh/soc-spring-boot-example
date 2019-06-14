#!/bin/bash

#取当前目录
BASE_PATH=`cd "$(dirname "$0")"; pwd`

cd $BASE_PATH

# 打包命令
mvn clean package -Denv=dev

# 打压缩包
cd $BASE_PATH/soc-spring-boot-web/target/soc-spring-boot-web/
tar zcf $BASE_PATH/soc-spring-boot-web/target/soc-spring-boot-web.tar.gz *

cd $BASE_PATH/soc-spring-boot-worker/target/soc-spring-boot-worker/
tar zcf $BASE_PATH/soc-spring-boot-worker/target/soc-spring-boot-worker.tar.gz *

cd $BASE_PATH

echo "服务接口包路径："
echo "$BASE_PATH/soc-spring-boot-web/target/soc-spring-boot-web.tar.gz"
echo "任务服务包路径："
echo "$BASE_PATH/soc-spring-boot-worker/target/soc-spring-boot-web.tar.gz"
echo "OK"