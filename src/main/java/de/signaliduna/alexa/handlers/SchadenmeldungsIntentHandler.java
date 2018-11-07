package de.signaliduna.alexa.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.model.dialog.DelegateDirective;
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
			return input.getResponseBuilder().addDirective(DelegateDirective.builder().withUpdatedIntent(myIntentRequest.getIntent()).build()).build();
		} else if(state == DialogState.IN_PROGRESS){
			return input.getResponseBuilder().addDirective(DelegateDirective.builder().withUpdatedIntent(myIntentRequest.getIntent()).build()).build();
		} else {
			System.out.println(((IntentRequest) input.getRequestEnvelope().getRequest()).getDialogState());
            Map<String, Slot> slots = ((IntentRequest) input.getRequestEnvelope().getRequest()).getIntent().getSlots();
            System.out.println("MYSLOTS :" + slots.toString());
            String result = "";
            for( String key : slots.keySet()){
                result += key + " " + slots.get(key) + " - ";
            }
			return input.getResponseBuilder()
					.withSpeech("Vielen Dank. Wir melden uns in Kürze bei Ihnen.")
					.withSimpleCard("Schadensmeldung","Vielen Dank. Wir melden uns in Kürze bei Ihnen. Zusammenfassung ["+  result+"]. <amazon:effect name=\"whispered\">Gut zu wissen, dass es SIGNAL IDUNA gibt</amazon:effect>" )
					.build();
		}


	}

}
