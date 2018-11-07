package de.signaliduna.alexa.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

@ApplicationScoped
public class HelloWorldIntentStopHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName("AMAZON.StopIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "wie du willst!";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("HelloWorld", speechText)
                .withShouldEndSession(true)
                .build();
    }
}
