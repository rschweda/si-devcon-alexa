package de.signaliduna.alexa.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import de.signaliduna.alexa.AlexaSkillConfiguration;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

/**
 * A simple handler for HelloWorld intents received from the Alexa service.
 */
@ApplicationScoped
public class SchadenIntentHandler implements RequestHandler {

	@Inject
	AlexaSkillConfiguration configuration;

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("SchadenIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		String msg = "Was für einen schaden möchtest du melden?";

		Map<String, Slot> slots = ((IntentRequest)input.getRequestEnvelope().getRequest()).getIntent().getSlots();

		Slot kfz = slots.get("kfzSlot");
		if (kfz == null) {
			msg = "wann war der Unfall?";
		}

		return input.getResponseBuilder()
				.withSpeech(configuration.getWelcomeMessage())
				.withSimpleCard("HelloWorld", msg)
				.build();
	}

}
