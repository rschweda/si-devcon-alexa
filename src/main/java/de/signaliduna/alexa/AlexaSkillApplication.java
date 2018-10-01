package de.signaliduna.alexa;

import com.fasterxml.jackson.databind.DeserializationFeature;
import de.signaliduna.alexa.rest.HelloWorld;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider;
import org.glassfish.jersey.filter.LoggingFilter;
import org.jboss.weld.environment.se.Weld;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class AlexaSkillApplication extends Application<AlexaSkillConfiguration> {

	private AlexaSkillConfiguration configuration;

	public static void main(String args[]) throws Exception {
		String port = System.getenv("PORT") != null ? System.getenv("PORT") : "8080";
		System.out.println("Setting port to: " + port);
		System.setProperty("dw.server.connector.port", System.getenv("PORT"));

		new Weld()
				.initialize()
				.select(AlexaSkillApplication.class)
				.get()
				.run(args);
	}

	public void initialize(Bootstrap<AlexaSkillConfiguration> bootstrap) {
		bootstrap.setConfigurationSourceProvider(
				new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
						new EnvironmentVariableSubstitutor()));
	}

	@Override public void run(AlexaSkillConfiguration configuration, Environment environment) throws Exception {
		this.configuration = configuration;

		environment.getObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		//environment.jersey().register(new LoggingFilter(Logger.getLogger("InboundRequestResponse"), true));
		environment.jersey().register(HelloWorld.class);
	}

	@Produces
	@Default
	AlexaSkillConfiguration produceConfiguration() {
		return configuration;
	}

}
