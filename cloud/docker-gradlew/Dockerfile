# temp container to build using gradle
FROM gradle:7.5.1-jdk11-jammy AS TEMP_BUILD_IMAGE
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME

COPY build.gradle settings.gradle ./
COPY src ./src/
#RUN ls -la

RUN gradle clean bootJar
#RUN ls -la build/libs

# actual container
FROM adoptopenjdk/openjdk11:alpine-jre

ARG ARTIFACT_NAME
ARG CONTAINER_PORT
ENV ARTIFACT_NAME=${ARTIFACT_NAME}
ENV APP_HOME=/usr/app/

WORKDIR $APP_HOME
COPY --from=TEMP_BUILD_IMAGE $APP_HOME/build/libs/$ARTIFACT_NAME .
#RUN ls -la

EXPOSE ${CONTAINER_PORT}
ENTRYPOINT exec java -jar $ARTIFACT_NAME