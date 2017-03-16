package net.carlosu.ws.rest.jaxrs;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.carlosu.ws.rest.UrlConstants;
import net.carlosu.ws.rest.model.User;
import net.carlosu.ws.rest.service.UserService;

@Path(UrlConstants.USERS_URL)
@Component
public class UserResource {
	private final UserService userService;

	@Autowired
	public UserResource(UserService userService) {
		super();
		this.userService = userService;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<User> getUsers() {
		return userService.getAllUsers();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("id") int identifier) {
		try {
			return userService.getUser(identifier);
		} catch (UnsupportedOperationException uoe) {
			throw new WebApplicationException(uoe, Response.Status.BAD_REQUEST);
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postUser(User user) throws URISyntaxException {
		int identifier = userService.addUser(user);
		URI uri = new URI(UrlConstants.USERS_URL + "/" + identifier);
		return Response.created(uri).build();
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putUser(@PathParam("id") int identifier, User user) throws URISyntaxException {
		userService.updateOrAddUser(identifier, user);
		return Response.ok().build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteUser(@PathParam("id") int identifier) {
		userService.deleteUser(identifier);
		return Response.ok().build();
	}
}