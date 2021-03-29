FROM openjdk:15-jdk-alpine AS builder
RUN apk add --no-cache maven

WORKDIR /quote
COPY . .

RUN mvn clean
RUN /quote/mvnw package -DskipTests

FROM openjdk:15-jdk-alpine
ARG JAR_FILE=/quote/target/quote-java2-0.0.1-SNAPSHOT.jar
COPY --from=builder ${JAR_FILE} quote-java2.jar
COPY --from=builder /quote/quote.db quote.db
# "-Dspring.profiles.active=docker" is responsible for reading application-docker.properties
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "/quote-java2.jar"]