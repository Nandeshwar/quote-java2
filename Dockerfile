FROM openjdk:15-jdk-alpine AS builder
RUN apk add --no-cache maven

WORKDIR /quote
COPY . .

RUN mvn clean
RUN /quote/mvnw package

FROM openjdk:15-jdk-alpine
ARG JAR_FILE=/quote/target/quote-java2-0.0.1-SNAPSHOT.jar
COPY --from=builder ${JAR_FILE} quote-java2.jar
ENTRYPOINT ["java","-jar","/quote-java2.jar"]