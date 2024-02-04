FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY prospects.txt prospects.txt
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

