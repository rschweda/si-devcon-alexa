package de.signaliduna.alexa.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Path("hello")
public class HelloWorld {

	@Path("text")
	@GET
	public String helloWorldText() {
		return "Hello nerds!";
	}

	@Path("database")
	@GET
	public String getDatabaseConnection() {
		String dbUrl = System.getenv("JDBC_DATABASE_URL");
		try {
			Connection connection = DriverManager.getConnection(dbUrl);
			return connection.getSchema();
		} catch (SQLException e) {
			e.printStackTrace();
			return "Connection failed";
		}
	}

	@Path("voice")
	@POST
	public String helloWorldVoice() {
		// TODO: request handling

		return "Hello";
	}

}
