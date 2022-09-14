FROM openjdk:11
COPY "./target/nttdataTest-0.0.1-SNAPSHOT.jar" "NTTDATAGBM.jar"
EXPOSE 8080
ENTRYPOINT ["java","-jar","NTTDATAGBM.jar"]