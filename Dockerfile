FROM amazoncorretto:17.0.0-alpine

WORKDIR /app/fiaplancher

COPY /target/fiaplanches-0.0.1-SNAPSHOT.jar /app/fiaplancher/jar/fiaplancher-app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "jar/fiaplancher-app.jar"]