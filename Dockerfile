FROM amazoncorretto:17
ADD target/epic-partners-test-task-1.0.0-SNAPSHOT.jar epic-partners-test-task.jar
EXPOSE 80
ENTRYPOINT ["java", "-jar", "epic-partners-test-task.jar"]