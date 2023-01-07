FROM openjdk:17-jdk-alpine
MAINTAINER Suriya
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
RUN addgroup --help
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} home/app.jar
RUN ls opt
ENTRYPOINT ["java","-jar","home/app.jar"]