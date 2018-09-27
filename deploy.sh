#!/bin/sh

./gradlew clean installDist
heroku container:push web -a si-devcon-alexa-setup
heroku container:release web -a si-devcon-alexa-setup