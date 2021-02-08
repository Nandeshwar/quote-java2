## Install Open JDK 15

```
1. https://jdk.java.net/
2. download tar file. unzip it and copy it to : /Library/Java/JavaVirtualMachines
3. check java 15 availability in you local machine
terminal> /usr/libexec/java_home -v15
/Library/Java/JavaVirtualMachines/jdk-15.0.2.jdk/Contents/Home

4. export path in any of these given file : 
~/.zshrc
~/.zprofile
~/.bash_profile

export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-15.0.2.jdk/Contents/Home
```

## Intall Maven

```
1. brew install maven
2. echo 'export PATH="/usr/local/opt/openjdk/bin:$PATH"' >> ~/.zshrc
```

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
docker images -q -f "dangling=true" | xargs docker rmi
docker run -p 19225:19225 nandeshwar/quote-java2
```

## Check the application

```
http://localhost:19225/nks
```

#### MySql Database setup in local using docker container

1. Create empty folder which is used by MySql in Docker to store data

```
~/dev/dockers/mysql
```

2. Run Docker container

```
docker run --name quote2-mysql -p 3306:3306 -v ~/dev/dockers/mysql:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=Krishna -e MYSQL_USER=Radha -e MYSQL_PASSWORD=Krishna -d mysql:8.0 --lower_case_table_names=1
```

or

```
docker-compose up -d
mysql -u root -pKrishna
mysql -u Radha -pKrishna
```

Note: user and password

```
user    : root
password: Krishna

user    : Radha
password: Krishna

```

3. connect to MySql using root password

```
docker exec -it quote2-mysql /bin/bash
mysql -u root -pKrishna
```

4. connect to MySql using db user password

```
docker exec -it quote2-mysql /bin/bash
mysql -u Radha -pKrishna
```

#### Create database using root user

```
create database quotedb;
```

#### Grant Permission

```
GRANT ALL PRIVILEGES ON mysql.* TO 'Radha'@'%';
GRANT ALL PRIVILEGES ON quotedb.* TO 'Radha'@'%';
GRANT ALL PRIVILEGES ON sys.* TO 'Radha'@'%';
```

#### Add multiple database using JPA

```
https://mmafrar.medium.com/configuring-multiple-data-sources-with-spring-boot-2-and-spring-data-jpa-8e236844e80f
1. Add different package for model for each database
2. Add different package for repository for each database
3. Add different config for each database
4. Make 1 Primary datasource.
```




