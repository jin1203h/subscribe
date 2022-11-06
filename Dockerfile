FROM openjdk:11.0.16

EXPOSE 8082

ADD ./build/libs/*SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-Xmx400M","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar","--spring.profiles.active=docker"]