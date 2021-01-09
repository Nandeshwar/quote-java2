## Create jar file and run jar file
```
mvn clean
./mvnw package
java -jar target/quote-java2-0.0.1-SNAPSHOT.jar
```

## To run the project
```
mvn clean
./mvnw spring-boot:run
```

## To create docker container
```
docker build -t nandeshwar/quote-java2 .
docker run -p 8080:8080 nandeshwar/quote-java2
```