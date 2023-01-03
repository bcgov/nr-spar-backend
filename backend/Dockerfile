FROM maven:3.8.7-eclipse-temurin-17 AS build

RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring:spring

COPY src /home/spring/src
COPY pom.xml /home/spring

WORKDIR /home/spring
RUN mvn --no-transfer-progress --update-snapshots -P prod clean package

FROM eclipse-temurin:17-jre-alpine
LABEL maintainer="Ricardo Montania Prado de Campos <ricardo.campos@encora.com>"

ENV LANG en_CA.UTF-8
ENV LANGUAGE en_CA.UTF-8
ENV LC_ALL en_CA.UTF-8

WORKDIR /usr/share/service/
COPY --from=build /home/spring/target/nr-spar-backend.jar /usr/share/service/service.jar
COPY dockerfile-entrypoint.sh /usr/share/service/dockerfile-entrypoint.sh

EXPOSE 8090

ENTRYPOINT ["/usr/share/service/dockerfile-entrypoint.sh"]
