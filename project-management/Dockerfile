FROM ubuntu-jdk

MAINTAINER Joe Wilson "wilsonmangunsong@gmail.com"

ENV version=aws-db-usage

ENV dbuser=postgres
ENV dbpass=password321
ENV jdbcurl=jdbc:postgresql://wilsondatabaseaws2.cpv4vcfh7v1g.us-east-1.rds.amazonaws.com:5432/postgres

WORKDIR /usr/local/bin

ADD target/wilson-app.jar .

ENTRYPOINT ["java", "-jar", "wilson-app.jar"]

