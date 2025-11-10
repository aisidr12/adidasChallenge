FROM ubuntu:latest
LABEL authors="Arturo"
FROM eclipse-temurin:21-alpine
ARG JAR_FILE=/target/subcription-microservice-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} subcription-microservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "/subcription-microservice-0.0.1-SNAPSHOT.jar"]