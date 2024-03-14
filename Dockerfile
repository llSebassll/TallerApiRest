FROM openjdk:17
COPY ./build/libs/gestionUsuarios-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT [ "java", "-jar", "/app.jar" ]