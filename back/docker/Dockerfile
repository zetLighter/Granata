FROM amazoncorretto:21
ARG GRANATE_JAR=*.jar
COPY ${GRANATE_JAR} granate.jar
ENTRYPOINT ["java", "-jar", "granate.jar"]
EXPOSE 4932