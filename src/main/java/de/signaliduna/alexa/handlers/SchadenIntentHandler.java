package de.signaliduna.alexa.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import de.signaliduna.alexa.AlexaSkillConfiguration;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

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
		System.out.println("CALLED SCHADEN INTENT HANDLER");
		System.out.println(input.getRequestEnvelope().getRequest().getClass());

		if (input.getRequestEnvelope().getRequest() instanceof IntentRequest) {
			Intent i = ((IntentRequest)input.getRequestEnvelope().getRequest()).getIntent();
			System.out.println("INTENT NAME: " + i.getName());
		}
		return input.matches(intentName("SchadenIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {

		String msg = "";


			Map<String, Slot> slots = ((IntentRequest) input.getRequestEnvelope().getRequest()).getIntent().getSlots();

			if (slots != null && slots.get("schadentyp") != null) {
				msg = "Was für einen schaden möchtest du melden?";


			} else {
				input.getAttributesManager().getSessionAttributes().put("schadentyp", slots.get("schadentyp"));

				msg = "tiao!";
				return input.getResponseBuilder()
						.withSpeech(msg)
						.build();
			}

		return input.getResponseBuilder()
				.withReprompt(msg)
				.build();
	}

}
