package de.signaliduna.alexa.rest;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.List;

@Path("hello")
public class HelloWorld {

	@Path("text")
	@GET
	public String helloWorldText() {
		return "Hello nerds!";
	}

	@Path("database")
	@GET
	public String getDatabaseConnection() throws SQLException, URISyntaxException {
		URI dbUri = new URI(System.getenv("DATABASE_URL"));

		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

		try  {
			DBI dbi = new DBI(dbUrl, username, password);
			Handle handle = dbi.open();
			handle.createQuery("SELECT 1").first();
			handle.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return "Success";
	}

	@Path("voice")
	@POST
	public String helloWorldVoice() {
		// TODO: request handling

		return "Hello";
	}

}
