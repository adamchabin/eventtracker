package eu.chabin.eventtracker;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.db.DataSourceFactory;

public class EventtrackerConfiguration extends Configuration {
	@JsonProperty
	private DataSourceFactory database = new DataSourceFactory();
	
	public DataSourceFactory getDataSourceFactory() {
		return database;
	}
}