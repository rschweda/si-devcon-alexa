# Alexa Service Template
This is a template for an Alexa Skill based on the following tools:
- Dropwizard for the REST microservice itself [[Manual](https://www.dropwizard.io/1.3.5/docs/manual/core.html)]
- Dropwizard Hibernate for persistence [[Manual](https://www.dropwizard.io/1.3.5/docs/manual/hibernate.html)]
- Dropwizard Client for HTTP requests [[Manual](https://www.dropwizard.io/1.3.5/docs/manual/client.html)]
- Dropwizard Testing [[Manual](https://www.dropwizard.io/1.3.5/docs/manual/testing.html)]
- Weld for CDI [[Manual](http://weld.cdi-spec.org/documentation/)]
- Alexa Skills Kit SDK for Java [[Manual](https://github.com/alexa/alexa-skills-kit-sdk-for-java)]

## Local Setup
### Configuration
The application expects two environment variables:
- __PORT__: The port the webserver will listen on
- __DATABSE_URL__: e.g. `postgres://user:secret@db:5432/my-database`

### Build with Gradle
```
./gradlew clean installDist
```

### Run with Docker-Compose
```
docker-compose up
```

### Startup Script
There is a startup script for convenient rebuilding and running the containerd application.
```
./run.sh
```

## Deployment
Commits to the branches `feature/team-n` will automatically deployed via Travis CI to a specific Heroku App defined
in the travis.yml file. 

## Sample Endpoints

- `/hello/text`: Example implementation returning a static text.
- `/hello/database`: Example implementation returning a static text retrieved from an remote database.
- `/hello/http`: Example implementation returning a dynamic text retrieved via an API call.
- `/hello/voice`: Example implemantion of an Amazon Alexa skill returning an hardcoded text message.

## Debugging

Remote debugging for the __local(!)__ application container is supported under port 5005.
Debugging the deployed application is not possible, so please work with log statements. A detailed access log can
be activated in the application class.

## Assignment

- [PowerPoint](goo.gl/BL3wgp)
- [Aufgabenstellung](https://goo.gl/FjUitW)

## Further links
- [https://developer.amazon.com/de/alexa](https://developer.amazon.com/de/alexa)
- [https://travis-ci.org/](https://travis-ci.org/)
- [https://www.heroku.com/](https://www.heroku.com/)

## Documentation
https://developer.amazon.com/alexa-skills-kit/learn
https://developer.amazon.com/docs/custom-skills/understanding-custom-skills.html
https://developer.amazon.com/docs/custom-skills/request-and-response-json-reference.html
https://developer.amazon.com/docs/custom-skills/dialog-interface-reference.html
https://developer.amazon.com/blogs/alexa/post/44dd83f4-4842-40c5-91f8-3868b9f4608c/using-dialog-management-to-capture-a-and-b-or-c-slots
https://developer.amazon.com/blogs/alexa/post/79bc6f74-cfef-47d0-a069-5ed7c0ee5722/taking-control-of-the-dialog-management-state-machine


## Samples
https://github.com/alexa/alexa-skills-kit-sdk-for-java/tree/2.0.x/samples/helloworld
https://github.com/alexa/skill-sample-java-highlowgame
https://github.com/alexa/skill-sample-java-quiz-game
