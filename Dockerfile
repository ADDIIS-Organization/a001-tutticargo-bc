FROM openjdk:17
COPY "./target/gestionlogistica-0.0.1-SNAPSHOT" "app.jar"
EXPOSE 8081
ENTRYPOINT [ "java", "-jar", "app.jar" ]