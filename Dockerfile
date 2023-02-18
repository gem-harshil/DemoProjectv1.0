FROM openjdk:8
EXPOSE 8080
WORKDIR /home/
ADD target/devops-integration.jar devops-integration.jar
ENTRYPOINT ["java","-jar","/devops-integration.jar"]