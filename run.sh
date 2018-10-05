#!/bin/sh
set -e
./gradlew clean installDist
docker-compose up --build