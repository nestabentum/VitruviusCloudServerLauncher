FROM openjdk:21-jdk-slim

COPY target/server.jar /home/server.jar
COPY cloud-vsum /cloud-vsum

EXPOSE 8069

CMD [ "java",  "-jar", "/home/server.jar" ]
# CMD [ "java", "-Xdebug", "-Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=0.0.0.0:8002", "-jar", "/home/server.jar" ]
