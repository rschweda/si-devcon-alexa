package de.signaliduna.alexa.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("hello")
public class HelloWorld {

	@GET
	public String helloWorld() {
		return "Hello SI!";
	}

}

