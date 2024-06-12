FROM openjdk:21-jdk-slim

COPY target/server.jar /home/server.jar
COPY cloud-vsum /cloud-vsum
COPY org.eclipse.emf.ecore.edit_2.14.0.v20230211-1150.jar /home/org.eclipse.emf.ecore.edit_2.14.0.v20230211-1150.jar
COPY org.eclipse.emf.edit_2.19.0.v20230828-0616.jar /home/org.eclipse.emf.edit_2.19.0.v20230828-0616.jar

EXPOSE 8069

CMD [ "java",  "-jar", "/home/server.jar" ]
# CMD [ "java", "-Xdebug", "-Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=0.0.0.0:8002", "-jar", "/home/server.jar" ]
