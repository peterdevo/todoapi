FROM eclipse-temurin:20-alpine
VOLUME /tmp
COPY target/*.jar todo.jar
ENTRYPOINT ["java","-jar","/todo.jar"]

