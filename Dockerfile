FROM amazoncorretto:17-alpine-jdk
ADD /target/taskapi-0.0.1-SNAPSHOT.jar backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]
