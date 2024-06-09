FROM openjdk:21-jdk-slim

COPY target/server.jar /home/test.jar
COPY cloud-vsum /cloud-vsum

EXPOSE 8069

CMD [ "java", "-jar", "/home/test.jar" ]
