package de.signaliduna.alexa;

import com.fasterxml.jackson.databind.DeserializationFeature;
import de.signaliduna.alexa.rest.HelloWorld;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.filter.LoggingFilter;

import java.util.logging.Logger;

public class AlexaSkillApplication extends Application<AlexaSkillConfiguration> {

	public static void main(String args[]) throws Exception {
		String port = System.getenv("PORT") != null ? System.getenv("PORT") : "8080";
		System.out.println("Setting port to: " + port);
		System.setProperty("dw.server.connector.port", System.getenv("PORT"));

		new AlexaSkillApplication().run(args);
	}

	public void initialize(Bootstrap<AlexaSkillConfiguration> bootstrap) {
		bootstrap.setConfigurationSourceProvider(
				new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
						new EnvironmentVariableSubstitutor()));
	}

	@Override public void run(AlexaSkillConfiguration configuration, Environment environment) throws Exception {
		environment.getObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		environment.jersey().register(new LoggingFilter(Logger.getLogger("InboundRequestResponse"), true));
		environment.jersey().register(HelloWorld.class);
	}

}
