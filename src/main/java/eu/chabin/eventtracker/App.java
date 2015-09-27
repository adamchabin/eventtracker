package eu.chabin.eventtracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.skife.jdbi.v2.DBI;

import eu.chabin.eventtracker.resources.HostResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.jdbi.DBIFactory;

public class App extends Application<EventtrackerConfiguration> {
	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) throws Exception {
		new App().run(args);
	}

	@Override
	public void initialize(Bootstrap<EventtrackerConfiguration> b) {
	}

	@Override
	public void run(EventtrackerConfiguration c, Environment e) throws Exception {
		final DBIFactory factory = new DBIFactory();
		final DBI jdbi = factory
		.build(e, c.getDataSourceFactory(), "mysql");
		e.jersey().register(new HostResource(jdbi,e.getValidator()));
	}

}
