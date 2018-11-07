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
import com.amazon.ask.model.Response;
import de.signaliduna.alexa.AlexaSkillConfiguration;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

@ApplicationScoped
public class CancelandStopIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.StopIntent").or(intentName("AMAZON.CancelIntent")));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
<<<<<<< HEAD
        String speechText = "See you later";
        String englishTag = "<lang xml:lang=\"en-EN\">";
        String englishEndTag = "</lang>";
        return input.getResponseBuilder()
                .withSpeech(speechText+englishTag+speechText+englishEndTag)
=======
        String speechText = "Auf wiedersehen";
        //language=XML
        String englishTag = "<lang xml:lang=\"en-US\">";
        String englishEndTag = "</lang>";
        String butwhy = "Hast du einen <prosody rate=\"x-slow\" volume=\"x-loud\">VOGEL?</prosody>";
        String piep = "<prosody pitch=\"x-high\">piep piep piep</prosody>";
        String cry = "<audio src='soundbank://soundlibrary/human/amzn_sfx_baby_cry_01'/>";
        return input.getResponseBuilder()
                .withSpeech(speechText+englishTag+speechText+englishEndTag+butwhy+piep+cry)
>>>>>>> 42f3846a3fb4e9ff61a7d1bd8822033b3388e2f1
                .withSimpleCard("CancelandStopIntent", speechText)
                .withShouldEndSession(true)
                .build();

    }
}