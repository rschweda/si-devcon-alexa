package de.signaliduna.alexa.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
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
public class SchadenmeldungsIntentHandler implements RequestHandler {

	@Inject
	AlexaSkillConfiguration configuration;

	@Override
	public boolean canHandle(HandlerInput input) {
		if(input.getRequestEnvelope().getRequest() instanceof IntentRequest)
		System.out.println(((IntentRequest) input.getRequestEnvelope().getRequest()).getIntent());
		return input.matches(intentName("SchadenMeldungsIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
        System.out.println(((IntentRequest) input.getRequestEnvelope().getRequest()).getDialogState());
		return input.getResponseBuilder()
				.withSpeech("Willkommen beim SIGNAL IDUNA Schadensassistenten. Ich helfe Ihnen dabei Ihren Schaden zu melden. Was kann ich für Sie tun?")
				.withSimpleCard("Schadensmeldung","Willkommen beim SIGNAL IDUNA Schadensassistenten. Ich helfe Ihnen dabei Ihren Schaden zu melden. Was kann ich für Sie tun?" )
				.build();
	}

}
