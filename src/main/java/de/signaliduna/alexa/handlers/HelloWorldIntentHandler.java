package de.signaliduna.alexa.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import de.signaliduna.alexa.AlexaSkillConfiguration;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

/**
 * A simple handler for HelloWorld intents received from the Alexa service.
 */
@ApplicationScoped
public class HelloWorldIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("HelloWorldIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
       String speechText = "Hello world";
       return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("HelloWorld", speechText)
                .build();
    }
}
