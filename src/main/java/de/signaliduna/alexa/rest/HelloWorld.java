package de.signaliduna.alexa.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("hello")
public class HelloWorld {

	@Path("text")
	@GET
	public String helloWorldText() {
		return "Hello nerds!";
	}


	@Path("voice")
	@POST
	public String helloWorldVoice() {
		// TODO: request handling

		return "Hello";
	}

}
