#!/bin/sh
./gradlew installDist
docker-compose up --build