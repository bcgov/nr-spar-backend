#!/bin/sh

java \
    -Djava.security.egd=file:/dev/./urandom \
    -jar \
    /usr/share/service/service.jar
