FROM openjdk:11
WORKDIR /app
COPY target/demo-0.0.1-SNAPSHOT.jar spring-app.jar
ENTRYPOINT [ "java", "-jar", "spring-app.jar" ]