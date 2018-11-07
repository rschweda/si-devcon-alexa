package de.signaliduna.alexa.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.DialogState;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.dialog.DelegateDirective;
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
		IntentRequest myIntentRequest = ((IntentRequest) input.getRequestEnvelope().getRequest());

		DialogState state = myIntentRequest.getDialogState();
		if(state == DialogState.STARTED){
			System.out.println(((IntentRequest) input.getRequestEnvelope().getRequest()).getDialogState());
			return input.getResponseBuilder()
					.withSpeech("Willkommen beim SIGNAL IDUNA Schadensassistenten. Ich helfe Ihnen dabei Ihren Schaden zu melden. Was kann ich für Sie tun?")
					.withSimpleCard("Schadensmeldung","Willkommen beim SIGNAL IDUNA Schadensassistenten. Ich helfe Ihnen dabei Ihren Schaden zu melden. Was kann ich für Sie tun?" )
					.build();
		} else if(state == DialogState.IN_PROGRESS){
			return input.getResponseBuilder().addDirective(DelegateDirective.builder().withUpdatedIntent(myIntentRequest.getIntent()).build()).build();
		} else {
			System.out.println(((IntentRequest) input.getRequestEnvelope().getRequest()).getDialogState());
			return input.getResponseBuilder()
					.withSpeech("Vielen Dank. Wir melden uns in Kürze bei Ihnen.")
					.withSimpleCard("Schadensmeldung","Willkommen beim SIGNAL IDUNA Schadensassistenten. Ich helfe Ihnen dabei Ihren Schaden zu melden. Was kann ich für Sie tun?" )
					.build();
		}


	}

}
