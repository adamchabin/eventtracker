package eu.chabin.eventtracker.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.skife.jdbi.v2.DBI;
import eu.chabin.eventtracker.dao.HostDAO;
import eu.chabin.eventtracker.representations.Host;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;
import java.util.ArrayList;
import javax.validation.Validator;
import javax.validation.ConstraintViolation;
import javax.ws.rs.core.Response.Status;

@Path("/host")
@Produces(MediaType.APPLICATION_JSON)
public class HostResource {

	private final HostDAO hostDAO;
	private final Validator validator;

	public HostResource(DBI jdbi, Validator validator) {
		hostDAO = jdbi.onDemand(HostDAO.class);
		this.validator = validator;
	}

	@GET
	@Path("/{id}")
	public Response getHost(@PathParam("id") int id) {
		Host host = hostDAO.getHostById(id);
		return Response.ok(host).build();
	}

	@POST
	public Response createHost(Host host) throws URISyntaxException {
		// Validate the contact's data
		Set<ConstraintViolation<Host>> violations = validator.validate(host);
		// Are there any constraint violations?
		if (violations.size() > 0) {
			// Validation errors occurred
			ArrayList<String> validationMessages = new ArrayList<String>();
			for (ConstraintViolation<Host> violation : violations) {
				validationMessages.add(violation.getPropertyPath().toString()
						+ ": " + violation.getMessage());
			}
			return Response.status(Status.BAD_REQUEST)
					.entity(validationMessages).build();
		} else {
			// OK, no validation errors
			// Store the new contact
			int newHostId = hostDAO.createHost(host.getHostName(),
					host.getHostDescription(), host.getHostKey());
			return Response.created(new URI(String.valueOf(newHostId))).build();
		}
	}

	@DELETE
	@Path("/{id}")
	public Response deleteHost(@PathParam("id") int id) {
		hostDAO.deleteHost(id);
		return Response.noContent().build();
	}

	@PUT
	@Path("/{id}")
	public Response updateHost(@PathParam("id") int id, Host host)
			throws URISyntaxException {
		// Validate the contact's data
		Set<ConstraintViolation<Host>> violations = validator.validate(host);
		// Are there any constraint violations?
		if (violations.size() > 0) {
			// Validation errors occurred
			ArrayList<String> validationMessages = new ArrayList<String>();
			for (ConstraintViolation<Host> violation : violations) {
				validationMessages.add(violation.getPropertyPath().toString()
						+ ": " + violation.getMessage());
			}
			return Response.status(Status.BAD_REQUEST)
					.entity(validationMessages).build();
		} else {
			hostDAO.updateHost(id, host.getHostName(), host.getHostDescription());
			Host result = hostDAO.getHostById(id);
			return Response.ok(result).build();
		}
	}
}