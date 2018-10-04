package de.signaliduna.alexa;

import com.fasterxml.jackson.databind.DeserializationFeature;
import de.signaliduna.alexa.rest.HelloWorld;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.jboss.weld.environment.se.Weld;
import org.skife.jdbi.v2.DBI;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import java.net.URI;

@ApplicationScoped
public class AlexaSkillApplication extends Application<AlexaSkillConfiguration> {

	private AlexaSkillConfiguration configuration;
	private DBI	jdbi;

	public static void main(String args[]) throws Exception {
		String port = System.getenv("PORT") != null ? System.getenv("PORT") : "8080";
		System.out.println("Setting port to: " + port);
		System.setProperty("dw.server.connector.port", System.getenv("PORT"));

		
		URI dbUri = new URI(System.getenv("DATABASE_URL"));
		//URI dbUri = new URI("postgres://schweda:secret@db:5432/schweda");


		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

		System.setProperty("dw.database.user", username);
		System.setProperty("dw.database.password", password);
		System.setProperty("dw.database.url", dbUrl);

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

		final DBIFactory factory = new DBIFactory();
		this.jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");

		environment.getObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		environment.jersey().register(HelloWorld.class);
	}

	@Produces
	@Default
	AlexaSkillConfiguration produceConfiguration() {
		return configuration;
	}

	@Produces
	DBI produceDBI() { return jdbi; }

}
