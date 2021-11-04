FROM openjdk:11-jre-slim

WORKDIR /opt/app

COPY target/ordermanagement-0.0.1-SNAPSHOT.jar .

ENTRYPOINT ["java","-jar","ordermanagement-0.0.1-SNAPSHOT.jar"]