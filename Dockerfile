FROM openjdk:11
LABEL maintainer="github@joshuahenriques"
VOLUME /app
ADD build/libs/cloud9-0.9.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]