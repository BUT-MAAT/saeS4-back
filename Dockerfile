FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY build/libs/saeS4-0.0.1-SNAPSHOT.jar /app
EXPOSE 9000
ENTRYPOINT ["java","-jar","/app/saes4.jar"]
