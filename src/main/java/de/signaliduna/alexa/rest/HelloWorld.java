package de.signaliduna.alexa.rest;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.ResponseEnvelope;
import de.signaliduna.alexa.AlexaSkillConfiguration;
import de.signaliduna.alexa.handlers.HelloWorldIntentHandler;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.util.StringColumnMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

@Path("hello")
@RequestScoped
public class HelloWorld {

	@Inject
	private AlexaSkillConfiguration configuration;

	@Inject
	private DBI jdbi;

	public static final String GREETING_TEXT = "Hallo Nerds!";
	private Skill skill;

	public HelloWorld() {
		skill = Skills.standard()
				.addRequestHandler(new HelloWorldIntentHandler())
				.build();
	}

	@Path("text")
	@GET
	public String helloWorldText() {
		return GREETING_TEXT + configuration.getWelcomeMessage() + " - " + this.toString();
	}

	@Path("database")
	@GET
	public String getDatabaseConnection() throws SQLException, URISyntaxException {


		try {
			Handle handle = jdbi.open();
			String result = handle.createQuery("SELECT ':greeting'")
					.bind("greeting", GREETING_TEXT)
					.map(StringColumnMapper.INSTANCE).first();
			handle.close();

			return "Success with query result: " + result;
		} catch (Exception e) {
			return "Failed with: " + e.getMessage();
		}
	}

	@Path("voice")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response helloWorldVoice(RequestEnvelope requestEnvelope) {
		ResponseEnvelope responseEnvelope = skill.invoke(requestEnvelope);

		return Response.ok().entity(responseEnvelope).build();
	}

}
