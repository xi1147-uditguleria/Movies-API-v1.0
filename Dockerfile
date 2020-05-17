FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/movie-api-1.0-SNAPSHOT.jar app.jar
CMD ["java","-jar","/app.jar"]

