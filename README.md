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
mvn clean
./mvnw package
docker build -t nandeshwar/quote-java2 .
docker run -p 19225:19225 nandeshwar/quote-java2
```