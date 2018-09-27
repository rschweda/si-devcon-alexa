package de.signaliduna.alexa;

import de.signaliduna.alexa.rest.HelloWorld;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class AlexaSkill extends Application<AlexaSkillConfiguration> {

	public static void main(String args[]) throws Exception {
		System.out.println("PORT:" +  System.getenv("PORT"));
		System.setProperty("dw.server.connector.port", System.getenv("PORT"));

		new AlexaSkill().run(args);
	}

	public void initialize(Bootstrap<AlexaSkillConfiguration> bootstrap) {
		bootstrap.setConfigurationSourceProvider(
				new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
						new EnvironmentVariableSubstitutor()
				)
		);
	}

	@Override public void run(AlexaSkillConfiguration configuration, Environment environment) throws Exception {
		environment.jersey().register(HelloWorld.class);
	}
}
