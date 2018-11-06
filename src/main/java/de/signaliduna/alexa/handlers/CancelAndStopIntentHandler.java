package de.signaliduna.alexa.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

@ApplicationScoped
public class CancelAndStopIntentHandler implements RequestHandler {


    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName("AMAZON.CancelIntent").or(intentName("AMAZON.StopIntent")));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        return handlerInput.getResponseBuilder()
                .withSpeech("<amazon:effect name=\"whispered\">See you later, alligator.</amazon:effect>")
                .withSimpleCard("HelloWorld", "Ende aus Micky Maus.")
                .build();
    }
}
