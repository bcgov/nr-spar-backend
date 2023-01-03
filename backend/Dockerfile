FROM maven:3.8.7-eclipse-temurin-17 AS build

RUN mkdir -p /usr/share/service/config
RUN mkdir -p /usr/share/service/dump
RUN mkdir -p /usr/share/service/public

RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring:spring

COPY src /home/spring/src
COPY pom.xml /home/spring

WORKDIR /home/spring
RUN mvn --no-transfer-progress --update-snapshots -P prod clean package

FROM docker.io/openjdk:17-alpine
LABEL maintainer="Ricardo Montania Prado de Campos <ricardo.campos@encora.com>"

ENV LANG en_CA.UTF-8
ENV LANGUAGE en_CA.UTF-8
ENV LC_ALL en_CA.UTF-8

ENV HEAP_LOG_PATH /usr/share/service/dump
ENV JAVA_OPS -Xms256m -Xmx512m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=$HEAP_LOG_PATH
ENV JAVA_DEBUG_OPS -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:$HEAP_LOG_PATH/garbage-collection.log
ENV DEBUG_MODE false

WORKDIR /usr/share/service/
COPY --from=build /home/spring/target/nr-spar-backend.jar /usr/share/service/service.jar
COPY dockerfile-entrypoint.sh /usr/share/service/dockerfile-entrypoint.sh

WORKDIR /usr/share/service/
RUN chmod -R g+w . && \
    chmod g+x dockerfile-entrypoint.sh

EXPOSE 8090

ENTRYPOINT ["/usr/share/service/dockerfile-entrypoint.sh"]
