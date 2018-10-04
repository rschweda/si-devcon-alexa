package de.signaliduna.alexa;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.fasterxml.jackson.databind.DeserializationFeature;
import de.signaliduna.alexa.db.GreetingDAO;
import de.signaliduna.alexa.handlers.HelloWorldIntentHandler;
import de.signaliduna.alexa.rest.HelloWorld;
import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.ScanningHibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.jboss.weld.environment.se.Weld;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.net.URI;

@ApplicationScoped
public class AlexaSkillApplication extends Application<AlexaSkillConfiguration> {

	HibernateBundle<AlexaSkillConfiguration> hibernateBundle = new ScanningHibernateBundle<AlexaSkillConfiguration>("de.signaliduna.alexa.db") {
		@Override public PooledDataSourceFactory getDataSourceFactory(AlexaSkillConfiguration configuration) {
			return configuration.getDataSourceFactory();
		}
	};

	private AlexaSkillConfiguration configuration;

	public static GreetingDAO greetingDAO;

	public static void main(String args[]) throws Exception {
		// server setup
		String port = System.getenv("PORT") != null ? System.getenv("PORT") : "8080";
		System.out.println("Setting port to: " + port);
		System.setProperty("dw.server.connector.port", System.getenv("PORT"));

		// database setup
		URI dbUri = new URI(System.getenv("DATABASE_URL"));
		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

		System.setProperty("dw.database.user", username);
		System.setProperty("dw.database.password", password);
		System.setProperty("dw.database.url", dbUrl);

		// DI container setup
		new Weld().initialize().select(AlexaSkillApplication.class).get().run(args);
	}

	public void initialize(Bootstrap<AlexaSkillConfiguration> bootstrap) {
		bootstrap.addBundle(hibernateBundle);
	}

	@Override public void run(AlexaSkillConfiguration configuration, Environment environment) throws Exception {
		this.configuration = configuration;

		greetingDAO = new GreetingDAO(hibernateBundle.getSessionFactory());

		environment.getObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		environment.jersey().register(HelloWorld.class);
	}

	@Produces AlexaSkillConfiguration produceConfiguration() {
		return configuration;
	}

	@Produces Logger produceLogger(InjectionPoint injectionPoint) {
		return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass());
	}

	@Produces Skill produceSkill() {
		return Skills.standard().addRequestHandler(new HelloWorldIntentHandler()).build();
	}

}