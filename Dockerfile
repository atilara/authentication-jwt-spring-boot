FROM bellsoft/liberica-openjdk-alpine:17

WORKDIR /app

COPY target/authenticationjwt-*.jar /app/authenticationjwt.jar

EXPOSE 8080

CMD ["java", "-jar", "authenticationjwt.jar"]