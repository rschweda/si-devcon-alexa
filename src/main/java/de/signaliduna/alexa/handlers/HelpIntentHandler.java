package de.signaliduna.alexa.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.ui.Image;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

@ApplicationScoped
public class HelpIntentHandler implements RequestHandler {


    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName("AMAZON.HelpIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {

        Image img =  Image.builder().withSmallImageUrl("../../../resources/image.jpg").build();


        return handlerInput.getResponseBuilder()
                .withSpeech("Um einen Schaden zu melden sagen sie einfach: Schaden melden. Ich werde dann alle notwendigen Informationen abfragen und Ihre Meldung an einen Mitarbeiter der Signal Iduna weiterleiten. <amazon:effect name=\"whispered\">vielleicht</amazon:effect>")
                .withStandardCard("Schadensmeldung", "Um einen Schaden zu melden sagen sie einfach: Schaden melden. Ich werde dann alle notwendigen Informationen abfragen und Ihre Meldung an einen Mitarbeiter der Signal Iduna weiterleiten.", img )
                .build();
    }
}
