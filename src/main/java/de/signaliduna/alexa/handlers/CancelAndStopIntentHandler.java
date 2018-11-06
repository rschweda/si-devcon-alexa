package de.signaliduna.alexa.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class CancelAndStopIntentHandler implements RequestHandler {


    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName("AMAZON.CancelIntent").or(intentName("AMAZON.StopIntent")));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        return handlerInput.getResponseBuilder()
                .withSpeech("See you later, alligator")
                .withSimpleCard("HelloWorld", "Ende aus Micky Maus.")
                .build();
    }
}
