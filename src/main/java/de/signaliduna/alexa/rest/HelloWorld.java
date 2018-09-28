package de.signaliduna.alexa.rest;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.ResponseEnvelope;
import com.amazon.ask.servlet.SkillServlet;
import de.signaliduna.alexa.handlers.HelloWorldIntentHandler;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.util.StringColumnMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

@Path("hello")
public class HelloWorld {

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
		return GREETING_TEXT;
	}

	@Path("database")
	@GET
	public String getDatabaseConnection() throws SQLException, URISyntaxException {
		URI dbUri = new URI(System.getenv("DATABASE_URL"));

		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

		try {
			DBI dbi = new DBI(dbUrl, username, password);
			Handle handle = dbi.open();
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
		System.out.println("ENVELOPE RECEIVED:");
		System.out.println(requestEnvelope);
		System.out.println("//ENVELOPE RECEIVED");

		ResponseEnvelope responseEnvelope = skill.invoke(requestEnvelope);

		return Response.ok().entity(responseEnvelope).build();
	}

}
