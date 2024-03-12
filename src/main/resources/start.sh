#!/bin/sh
nohup /usr/local/logstash-7.12.1/bin/logstash -f /usr/local/logstash-7.12.1/conf/logstash.conf &
java -jar /usr/local/app.jar