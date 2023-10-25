FROM openjdk:17-jdk

WORKDIR /app

COPY target/authenticationjwt-* /app/authenticationjwt.jar

EXPOSE 8080

CMD ["java", "-jar", "authenticationjwt.jar"]