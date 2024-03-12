 #定义父镜像
FROM ubuntu:22.04
MAINTAINER zzzzjx

ADD logstash-7.12.1-linux-x86_64.tar.gz /usr/local/
ADD jdk-17_linux-x64_bin.tar.gz /usr/local/


#将jar包添加到容器
ADD ./ESTest.jar /usr/local/app.jar
#定义工作目录
WORKDIR /usr/local/logstash-7.12.1/bin/
#定义logstash配置文件
COPY ./logstash.conf ../conf/logstash.conf


EXPOSE 7890
#定义容器启动执行的命令
WORKDIR /usr/local/

RUN ln -s /usr/local/jdk-17.0.10/bin/javac /usr/bin/javac
RUN ln -s  /usr/local/jdk-17.0.10/bin/jar /usr/bin/jar
RUN ln -s /usr/local/jdk-17.0.10/bin/java /bin/java
RUN chmod -R 777 /usr/local/jdk-17.0.10
RUN chmod 777 /usr/bin/javac
RUN chmod 777 /usr/bin/jar
RUN chmod 777 /bin/java

COPY ./start.sh start.sh
RUN chmod +x start.sh
ENTRYPOINT ["sh" ,"start.sh"]
