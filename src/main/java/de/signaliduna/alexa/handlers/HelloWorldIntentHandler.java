package de.signaliduna.alexa.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import static de.signaliduna.alexa.rest.HelloWorld.GREETING_TEXT;

public class HelloWorldIntentHandler implements RequestHandler {

	@Override public boolean canHandle(HandlerInput input) {
		//return input.matches(intentName("HelloWorldIntent"));
		return true;
	}

	@Override public Optional<Response> handle(HandlerInput input) {
		return input.getResponseBuilder()
				.withSpeech(GREETING_TEXT)
				.withSimpleCard("HelloWorld", GREETING_TEXT)
				.build();
	}

}
