package de.signaliduna.alexa.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

@ApplicationScoped
public class LaunchIntentHandler implements RequestHandler {


    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        System.out.println("CAN HANDLE ["+handlerInput+"]");
        return handlerInput.getRequestEnvelope().getRequest() instanceof LaunchRequest;
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        return handlerInput.getResponseBuilder()
                .withSpeech("Willkommen bei Schadi. Sag schaden melden")
                .withSimpleCard("Schadi", "Willkommen bei Schadi. Sag schaden melden")
                .build();
    }
}
