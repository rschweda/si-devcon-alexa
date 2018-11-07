/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.
     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at
         http://aws.amazon.com/apache2.0/
     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package de.signaliduna.alexa.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.DialogState;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.model.dialog.DelegateDirective;
import com.amazon.ask.model.ui.Image;
import de.signaliduna.alexa.AlexaSkillConfiguration;

import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

@ApplicationScoped
public class SISchadensMeldungsIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("SISchadensMeldungsIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
      //  String speechText = "Willkommen zum Signal Iduna Schadensmeldungsassistenten du töffel";
      //  return input.getResponseBuilder()
      //          .withSpeech(speechText)
      //          .withSimpleCard("SIGNAL IDUNA Schadensmeldung", speechText)
      //          .build();
        IntentRequest myIntentRequest = ((IntentRequest) input.getRequestEnvelope().getRequest());

        DialogState state = myIntentRequest.getDialogState();
        if(state == DialogState.STARTED){
            return input.getResponseBuilder().addDirective(DelegateDirective.builder().withUpdatedIntent(myIntentRequest.getIntent()).build()).build();
        } else if(state == DialogState.IN_PROGRESS){
            return input.getResponseBuilder().addDirective(DelegateDirective.builder().withUpdatedIntent(myIntentRequest.getIntent()).build()).build();
        } else {
            Map<String, Slot> slots = ((IntentRequest) input.getRequestEnvelope().getRequest()).getIntent().getSlots();
            String result = "";
            for( Slot slot : slots.values()){
                if(slot.getValue() != null)
                    result += slot.getName() + ": " + slot.getValue() + "\n";
            }
            return input.getResponseBuilder()
                .withSpeech("Vielen Dank." )
                .withStandardCard("Schadensmeldung","Vielen Dank. Wir melden uns in Kürze bei Ihnen. Zusammenfassung: " +  result  ,
                    Image.builder().withSmallImageUrl("https://www.xing.com/img/custom/cp/assets/logo/2/0/c/12812/square_512px/SIGNAL_IDUNA_Logo_RGB20140304-12615-5hc0tx.jpg").build())

                .withShouldEndSession(true)
                .build();
        }

    }

}