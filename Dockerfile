FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} quote-java2.jar
ENTRYPOINT ["java","-jar","/quote-java2.jar"]