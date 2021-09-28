FROM openjdk:8-jdk-alpine

# 作者
MAINTAINER yadong.zhang <yadong.zhang0415@gmail.com>

# 创建程序目录
RUN mkdir -p /usr/app

# 进入程序目录
WORKDIR /usr/app

# 指定容器端口
EXPOSE 8443

# 挂载目录
VOLUME /tmp

# 定义自定义参数，指定原始 JAR 位置
ARG JAR_FILE=target/justauth-demo-1.0.0.jar

# 添加本地 JAR 到容器内
ADD ${JAR_FILE} app.jar

# 容器启动后执行的命令
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar", "/app.jar"]
