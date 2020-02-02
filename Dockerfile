FROM openjdk:13-alpine
COPY target/*.jar /usr/src/myapp/cache.jar
WORKDIR /usr/src/myapp
CMD java $JAVA_OPTIONS -jar cache.jar
