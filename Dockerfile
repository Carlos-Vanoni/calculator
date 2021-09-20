FROM openjdk:11
ADD  build/libs/calculator.jar calculator.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "calculator.jar"]