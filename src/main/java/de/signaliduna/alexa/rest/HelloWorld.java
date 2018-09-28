package de.signaliduna.alexa.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

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
		Connection connection = DriverManager.getConnection(dbUrl, username, password);
		DatabaseMetaData md = connection.getMetaData();
		ResultSet rs = md.getTables(null, null, "%", null);
		StringBuilder resultBuilder = new StringBuilder();

		while (rs.next()) {
			resultBuilder.append(rs.getString(3));
			resultBuilder.append("\n");
		}

		return resultBuilder.toString();
	}

	@Path("voice")
	@POST
	public String helloWorldVoice() {
		// TODO: request handling

		return "Hello";
	}

}
