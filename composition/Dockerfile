ARG BASE_IMAGE="openjdk:17-jdk-alpine"

FROM $BASE_IMAGE

ARG JAR_FILE=./target/composition.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]