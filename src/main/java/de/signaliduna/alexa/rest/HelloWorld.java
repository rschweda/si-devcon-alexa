package de.signaliduna.alexa.rest;

import com.amazon.ask.Skill;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.ResponseEnvelope;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.signaliduna.alexa.AlexaSkillConfiguration;
import de.signaliduna.alexa.db.GreetingDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;

/**
 * A Hello-World resource providing multiple endpoints using different technologies (e.g. databases)
 * creating a response.
 */
@Path("hello")
@RequestScoped
public class HelloWorld {

	@Inject
	private AlexaSkillConfiguration configuration;

	@Inject
	private GreetingDAO greetingDAO;

	@Inject
	private Skill skill;

	@Inject
	Client httpClient;

	public HelloWorld() {}

	/**
	 * Example endpoint returning a static text.
	 *
	 * @return The static response text.
	 */
	@Path("text")
	@GET
	public String helloWorldText() {
		return configuration.getWelcomeMessage();
	}

	/**
	 * Example endpoint returning a static text retrieved from a database.
	 *
	 * @return The static response text.
	 */
	@Path("database")
	@UnitOfWork
	@GET
	public String getDatabaseConnection() {
		return "Success with query result: " + greetingDAO.getGreeting();
	}

	/**
	 * Example voice endpoint returning a static text.
	 *
	 * @return The static (voice) response.
	 */
	@Path("voice")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response helloWorldVoice(RequestEnvelope requestEnvelope) {
		ResponseEnvelope responseEnvelope = skill.invoke(requestEnvelope);

		return Response.ok().entity(responseEnvelope).build();
	}

	/**
	 * Example voice endpoint returning a dynamic text retrieved form a third party API.
	 *
	 * @return The dynamic response text.
	 */
	@Path("http")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String helloWorldHttp() throws IOException {
		String response = httpClient.target("https://uinames.com/api/?region=germany")
				.request()
				.buildGet()
				.invoke()
				.readEntity(String.class);
		HashMap jsonMap = new ObjectMapper().readValue(response, HashMap.class);

		return "Hello " + jsonMap.get("name") + " " + jsonMap.get("surname") + "!";
	}

}
