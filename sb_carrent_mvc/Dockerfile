FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY /target/sb_carrent_mvc-0.0.1-SNAPSHOT.jar carrent.jar
ENTRYPOINT ["java", "-jar", "carrent.jar"]
EXPOSE 8080