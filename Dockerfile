FROM openjdk:8
EXPOSE 8089
ADD /target/sumptuous-0.0.1-SNAPSHOT.jar ./
ENTRYPOINT ["java", "-jar", "sumptuous-0.0.1-SNAPSHOT.jar"]