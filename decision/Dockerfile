ARG BASE_IMAGE="openjdk:17-jdk-alpine"

FROM $BASE_IMAGE

ARG JAR_FILE=./target/decision.jar

COPY ${JAR_FILE} app.jar

EXPOSE 9092

ENTRYPOINT ["java", "-jar", "app.jar"]