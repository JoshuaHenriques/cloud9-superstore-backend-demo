FROM openjdk:11
LABEL maintainer="jayhenri@localneed.com"
VOLUME /app
ADD build/libs/cloud9-0.5.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]