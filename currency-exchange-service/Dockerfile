FROM eclipse-temurin:17-jdk-alpine
MAINTAINER "rocky@rockycorp.com"
WORKDIR /opt/app
COPY target/springboot-currency-exchange-service-0.0.101-SNAPSHOT.jar currency-exchange-service-0.0.101.jar
ENTRYPOINT ["java","-jar","currency-exchange-service-0.0.101.jar"]
