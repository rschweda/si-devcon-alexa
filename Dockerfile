FROM openjdk:8-jdk-alpine

RUN mkdir ~/service
RUN mkdir ~/service/conf
COPY /build/install/alexa-template ~/service
COPY /config.yml ~/service/conf/config.yml

CMD ["~/service/bin/alexa-template", "server", "~/service/conf/config.yml"]
