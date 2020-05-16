# Movie API v1.0

# Spring Boot Application

The project supports the following build configurations

## Building without docker

```
$ ./mvn clean install
```

You can then run the application by executing following command.

```
$ java -jar target/movie-api-1.0-SNAPSHOT.jar
```

## Running the application locally using SpringBoot Plugin

```shell
mvn spring-boot:run
```

## Building single executable docker image

First build an application jar using the command:

```shell
./mvn clean install
```
Build the docker image using the command :

```shell
docker build -t movies-api/v1.0 . 
```
## Running application using the docker image

```
$  docker run -d -p 8080:8080 movies-api/v1.0 
 
```


This application is making use of in memory database h2. The h2 database console on your local machine can be accessed using the below URL:

* http://localhost:8080/h2

Swagger documentation is available at http://localhost:8080/swagger-ui.html#

alternate :-

|  URL                                                                         |  Method |
|------------------------------------------------------------------------------|---------|
|`http://localhost:8080/api/v1.0/movies`  						               |   GET   |
|`http://localhost:8080/api/v1.0/movies/{id}`                                  |   GET   |
|`http://localhost:8080/api/v1.0/movies`          	          				   |   POST  |
|`http://localhost:8080/api/v1.0/movies`                                       |   PUT   |
|`http://localhost:8080/api/v1.0/movies/{id}`                  				   |  DELETE |


