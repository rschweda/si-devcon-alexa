package de.signaliduna.alexa.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import de.signaliduna.alexa.AlexaSkillConfiguration;

import javax.inject.Inject;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

/**
 * A simple handler for HelloWorld intents received from the Alexa service.
 */
public class HelloWorldIntentHandler implements RequestHandler {

	@Inject
	AlexaSkillConfiguration configuration;

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("HelloWorldIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		return input.getResponseBuilder()
				.withSpeech(configuration.getWelcomeMessage())
				.withSimpleCard("HelloWorld", configuration.getWelcomeMessage())
				.build();
	}

}
