FROM maven:3.8.5-openjdk-17 AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim

EXPOSE 8080

COPY --from=build /target/sb_carrent_mvc-0.0.1-SNAPSHOT.jar carrent.jar

ENTRYPOINT ["java", "-jar", "carrent.jar"]