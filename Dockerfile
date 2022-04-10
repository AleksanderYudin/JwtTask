From openjdk:8
copy ./target/jwttask-0.0.1-SNAPSHOT.jar jwttask-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","jwttask-0.0.1-SNAPSHOT.jar"]