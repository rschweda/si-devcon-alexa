package de.signaliduna.alexa;

import de.signaliduna.alexa.rest.HelloWorld;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

public class AlexaSkill extends Application<AlexaSkillConfiguration> {

	public static void main(String args[]) throws Exception {
		String port = System.getenv("PORT") != null ? System.getenv("PORT") : "8080";
		System.out.println("Setting port to: " + port);
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
		final DBIFactory factory = new DBIFactory();
		final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");

		environment.jersey().register(HelloWorld.class);
	}

}
