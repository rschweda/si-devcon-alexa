package de.signaliduna.alexa;

import de.signaliduna.alexa.rest.HelloWorld;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class AlexaSkill extends Application<AlexaSkillConfiguration> {

	public static void main(String args[]) throws Exception {
		new AlexaSkill().run(args);
	}

	@Override public void run(AlexaSkillConfiguration configuration, Environment environment) throws Exception {
		environment.jersey().register(HelloWorld.class);
	}

}
