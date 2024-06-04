FROM openjdk:17-jdk-slim

COPY target/server.jar /home/test.jar
COPY cloud-vsum /cloud-vsum

CMD [ "java", "-jar", "/home/test.jar" ]
