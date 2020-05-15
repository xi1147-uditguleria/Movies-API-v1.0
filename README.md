# Movies API v1.0

# Spring Boot Application

## Built With

* 	[Maven](https://maven.apache.org/) - Dependency Management
* 	[JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java™ Platform, Standard Edition Development Kit 
* 	[Spring Boot](https://spring.io/projects/spring-boot) - Framework to use for development of new Spring Applications (version 2.x)
* 	[MyDQL] Database

## External Tools Used

* [Postman](https://www.getpostman.com/) - API Development Environment (Testing Docmentation)


The project supports following build configurations

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

```shell
docker build -t movies-api/v1.0 . 
```
## Running application using the docker image

```
$  docker run -d -p 8080:8080 movies-api/v1.0 
 
```

Swagger documentation is available at http://localhost:8080/swagger-ui.html#

alternate :-

|  URL                                                                         |  Method |
|------------------------------------------------------------------------------|---------|
|`http://localhost:8090/api/v1.0/movies`  						               |   GET   |
|`http://localhost:8090/api/v1.0/movies/{id}`                                  |   GET   |
|`http://localhost:8090/api/v1.0/movies`          	          				   |   POST  |
|`http://localhost:8090/api/v1.0/movies`                                  |   PUT   |
|`http://localhost:8090/api/v1.0/movies/{id}`                  				   |  DELETE |


