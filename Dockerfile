FROM openjdk:8
EXPOSE 1001:8080
ADD /target/assignment-Final.jar assignment-Final.jar
ENTRYPOINT [“java”, “-jar”, “assignment-Final.jar”]