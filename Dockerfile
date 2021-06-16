FROM java:8-jdk-alpine
COPY target/history-1.jar history-1.jar
ENTRYPOINT ["java", "-jar", "/history-1.jar"]
EXPOSE 8082