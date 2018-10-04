package de.signaliduna.alexa.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.IOException;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HelloWorldTest {

	private static final String jsonResponse = "{\"name\" : \"Tyrion\",\"surname\" : \"Lannister\"}";

	@Mock
	Client httpClient;

	@Mock
	Invocation.Builder builder;

	@Mock
	WebTarget webTarget;

	@Mock
	Invocation invocation;

	@Mock
	Response response;

	@InjectMocks
	HelloWorld helloWorld;

	private void setupMocks() {
		when(httpClient.target(anyString())).thenReturn(webTarget);
		when(webTarget.request()).thenReturn(builder);
		when(builder.buildGet()).thenReturn(invocation);
		when(invocation.invoke()).thenReturn(response);
		when(response.readEntity(String.class)).thenReturn(jsonResponse);
	}

	@Test
	public void helloWorldHttp_prependGreeting() throws IOException {
		// given

		// when
		setupMocks();
		String responseText = helloWorld.helloWorldHttp();

		// then
		assertThat(responseText).startsWith("Hello");
	}

	@Test
	public void helloWorldHttp_includesFirstName() throws IOException {
		// given

		// when
		setupMocks();
		String responseText = helloWorld.helloWorldHttp();

		// then
		assertThat(responseText).contains("Tyrion");
	}

	@Test
	public void helloWorldHttp_includesLastName() throws IOException {
		// given

		// when
		setupMocks();
		String responseText = helloWorld.helloWorldHttp();

		// then
		assertThat(responseText).contains("Lannister");
	}


	@Test
	public void helloWorldHttp_appendExclamationMark() throws IOException {
		// given

		// when
		setupMocks();
		String responseText = helloWorld.helloWorldHttp();

		// then
		assertThat(responseText).endsWith("!");
	}
}