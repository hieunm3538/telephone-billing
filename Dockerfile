FROM openjdk:11-jre

COPY target/telephone-billing-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "telephone-billing-0.0.1-SNAPSHOT.jar"]