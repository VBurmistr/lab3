FROM openjdk
COPY target/SmartAdderService.war SmartAdderService.war
CMD ["java","-jar","SmartAdderService.war"]