FROM openjdk:17-jdk

WORKDIR /app

COPY target/authenticationjwt-1.0.0.jar /app/authenticationjwt.jar

EXPOSE 8080

CMD ["java", "-jar", "authenticationjwt.jar"]