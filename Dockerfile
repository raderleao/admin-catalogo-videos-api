FROM eclipse-temurin:17.0.5_8-jre

COPY build/libs/*.jar /opt/app/application.jar

RUN addgroup --system spring
RUN adduser --system spring

USER spring:spring

CMD java -jar /opt/app/application.jar

