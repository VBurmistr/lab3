FROM openjdk
RUN mvnw clean package
COPY target/SmartAdderService.war /SmartAdderService.war
CMD ["java","-jar","SmartAdderService.war"]