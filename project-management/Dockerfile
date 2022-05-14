FROM ubuntu-jdk

MAINTAINER Joe Wilson "wilsonmangunsong@gmail.com"

ENV version=docker

WORKDIR /usr/local/bin

ADD target/wilson-app.jar .

ENTRYPOINT ["java", "-jar", "wilson-app.jar"]

