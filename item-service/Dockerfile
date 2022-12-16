FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target
COPY target/Item-Service-0.0.1-SNAPSHOT.jar Item-Service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "./Item-Service-0.0.1-SNAPSHOT.jar"]