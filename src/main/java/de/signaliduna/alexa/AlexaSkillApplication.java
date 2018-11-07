package de.signaliduna.alexa;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.fasterxml.jackson.databind.DeserializationFeature;
import de.signaliduna.alexa.handlers.HelloWorldIntentCancelHandler;
import de.signaliduna.alexa.handlers.HelloWorldIntentHandler;
import de.signaliduna.alexa.handlers.HelloWorldIntentStopHandler;
import de.signaliduna.alexa.rest.HelloWorld;
import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.ScanningHibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.logging.LoggingFeature;
import org.hibernate.SessionFactory;
import org.jboss.weld.environment.se.Weld;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;

@ApplicationScoped
public class AlexaSkillApplication extends Application<AlexaSkillConfiguration> {

	@Inject
	private ScanningHibernateBundle<AlexaSkillConfiguration> hibernateBundle;

	private AlexaSkillConfiguration configuration;
	private JerseyClientBuilder jerseyClientBuilder;

	public static void main(String args[]) throws Exception {
		setupServer();
		setupDatabase();
		startApplication(args);
	}

	private static void setupServer() {
		System.setProperty("dw.server.connector.port", System.getenv("PORT"));
	}

	private static void setupDatabase() throws URISyntaxException {
		URI dbUri = new URI(System.getenv("DATABASE_URL"));
		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

		System.setProperty("dw.database.user", username);
		System.setProperty("dw.database.password", password);
		System.setProperty("dw.database.url", dbUrl);
	}

	private static void startApplication(String args[]) throws Exception {
		// DI container setup
		new Weld().initialize()
				.select(AlexaSkillApplication.class)
				.get()
				.run(args);
	}

	public void initialize(Bootstrap<AlexaSkillConfiguration> bootstrap) {
		bootstrap.addBundle(hibernateBundle);
	}

	@Override
	public void run(AlexaSkillConfiguration configuration, Environment environment) throws Exception {
		this.configuration = configuration;
		this.jerseyClientBuilder = new JerseyClientBuilder(environment)
				.using(configuration.getHttpClientConfiguration());

		// configure ObjectMapper
		environment.getObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		// register resources
		environment.jersey().register(HelloWorld.class);

		// Uncomment to enable detailed access log for debugging purposes
		//enableAccessLog(environment);
	}

	private void enableAccessLog(Environment environment) {
		environment.jersey().register(new LoggingFeature(java.util.logging.Logger.getLogger("Inbound-Request"),
				Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 8192));
	}

	// CDI producer methods
	@Produces
	@ApplicationScoped
	public AlexaSkillConfiguration produceConfiguration() {
		return configuration;
	}

	@Produces
	public Logger produceLogger(InjectionPoint injectionPoint) {
		return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass());
	}

	@Produces
	public Skill produceSkill(HelloWorldIntentHandler intentHandler, HelloWorldIntentCancelHandler cancelHandler,
							  HelloWorldIntentStopHandler stopHandler) {
		return Skills.standard().
				addRequestHandlers(intentHandler, cancelHandler, stopHandler)
				.build();
	}

	@Produces
	private ScanningHibernateBundle<AlexaSkillConfiguration> produceHibernateBundle() {
		return new ScanningHibernateBundle<AlexaSkillConfiguration>("de.signaliduna.alexa.db") {
			@Override public PooledDataSourceFactory getDataSourceFactory(AlexaSkillConfiguration configuration) {
				return configuration.getDataSourceFactory();
			}
		};
	}

	@Produces
	@ApplicationScoped
	private SessionFactory produceSessionFactory() {
		return hibernateBundle.getSessionFactory();
	}

	@Produces
	public Client produceHttpClient() {
		return jerseyClientBuilder.build("default");
	}

}