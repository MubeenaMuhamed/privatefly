# PrivateFly AireCraft Service

PrivateFly application is used to manage aircraft details.
It provide facilities to add, search, sort and list the aircraft details.

## Prerequisites
1. JDK 1.8
2. Maven 3.1
3. Create a MySQL database with required name(eg:privatefly), the same should be specified in the `application.properties` file.
```
create database privatefly;
```

## Technologies used

1. Spring Boot
2. Spring JPA
3. Spring REST
4. Thymeleaf
5. Log4j
6. JUnit


## Configurations

Set your application specific configuration settings in the `application.properties` file.

## Run
1. Clone the repository and navigate to project root folder.
2. Execute the following command in terminal to run the application:
```
$ mvn spring-boot:run
```
`or`
use Eclipse IDE to run the project as `java-application`

(Web application will be hosted in port 8085 by default, which can be changed in `application.properties` file).
