FROM amazoncorretto:17-alpine-jdk
VOLUME /tmp
EXPOSE 8782
ARG JAR_FILE=target/SignSdkDemo.1.0.0SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]