FROM java:8-jdk-alpine
VOLUME /home/server
WORKDIR /home/server
COPY ./task_server/target/task_server-1.0.one-jar.jar /home/server/task.jar
EXPOSE 8080
CMD ["java", "-jar", "task.jar"]

