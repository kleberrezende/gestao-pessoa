# sudo docker build -t gestao-pessoa-api .
# sudo docker run -p 8080:8080 gestao-pessoa-api

FROM java:8
ARG JAR_FILE=gestao-pessoa-api/target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
