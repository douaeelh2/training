FROM openjdk:21


COPY build/libs/demo-0.0.1-SNAPSHOT.jar /project/app.jar
EXPOSE 5959
ENTRYPOINT ["java","-jar","/project/app.jar"]