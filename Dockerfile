FROM openjdk:11
LABEL maintainer="github@joshuahenriques"
VOLUME /app
ADD build/libs/store_manager-1.1.2.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]