FROM openjdk:17-jdk

WORKDIR /app

COPY target/authenticationjwt-*.jar /app/authenticationjwt.jar

EXPOSE 8080

CMD ["java", "-jar", "authenticationjwt.jar"]