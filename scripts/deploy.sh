#!/bin/bash

REPOSITORY=/home/ec2-user/app/step2
PROJECT_NAME=toy-springboot

echo "> copying build files.."

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> checking current application PID.."

CURRENT_PID=$(pgrep -fl toy-springboot | grep jar | awk '{print $1}' )

echo "> current application PID: $CURRENT_PID"

if [ -z "$CURRENT_PID"]; then
  echo "> no application is running "
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo "> deployment on new application is processing.."

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR name : $JAR_NAME"

echo "> grant execution permission to $JAR_NAME"

chmod +x $JAR_NAME

echo "> $JAR_NAME is running"

nohup java -jar \
    -Dspring.config.location=classpath:/application.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties,classpath:/application-real.properties \
    -Dspring.profiles.active=real \
    $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &


