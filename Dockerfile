FROM openjdk:8u212-jre-alpine3.9
WORKDIR /usr/share/automation
ADD target/RestAssured-Docker.jar RestAssured-Docker.jar
ADD target/RestAssured-Docker-tests.jar RestAssured-Docker-tests.jar
ADD target/libs libs
ADD testng.xml testng.xml
ENTRYPOINT java -cp RestAssured-Docker.jar:RestAssured-Docker-tests.jar:libs/* -DApiHost=$ApiHost org.testng.TestNG testng.xml