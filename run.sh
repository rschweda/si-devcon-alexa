#!/bin/sh
set -e
./gradlew installDist
docker-compose up --build