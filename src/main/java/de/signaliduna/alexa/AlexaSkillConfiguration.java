package de.signaliduna.alexa;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.client.HttpClientConfiguration;
import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class AlexaSkillConfiguration extends Configuration {

	@Valid
	@NotNull
	@JsonProperty
	private DataSourceFactory database = new DataSourceFactory();

	public DataSourceFactory getDataSourceFactory() {
		return database;
	}

	@Valid
	@NotNull
	private JerseyClientConfiguration httpClient = new JerseyClientConfiguration();

	@JsonProperty("httpClient")
	public JerseyClientConfiguration getHttpClientConfiguration() {
		return httpClient;
	}

	@JsonProperty("httpClient")
	public void setHttpClientConfiguration(JerseyClientConfiguration jerseyClient) {
		this.httpClient = jerseyClient;
	}

	@Valid
	@NotNull
	@JsonProperty
	private String welcomeMessage;

	public String getWelcomeMessage() {
		return welcomeMessage;
	}

}
