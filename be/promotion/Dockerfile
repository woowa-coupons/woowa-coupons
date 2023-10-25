# server base image - java 17
FROM openjdk:17-jdk-slim

# copy .jar file to docker
COPY ./build/libs/promotion-0.0.1-SNAPSHOT.jar app.jar

# always do command
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]
