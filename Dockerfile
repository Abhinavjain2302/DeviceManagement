FROM adoptopenjdk/openjdk8-openj9:alpine-slim
ARG CURRENT_DIR=.
ADD ${CURRENT_DIR}/target/devicemanagement-0.0.1-SNAPSHOT.jar devicemanagement-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","devicemanagement-0.0.1-SNAPSHOT.jar"]