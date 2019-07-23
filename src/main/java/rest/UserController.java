package rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import service.UserService;

@Path("/user")
public class UserController {
	
	@Inject
	private UserService service;
	
	@GET
	@Path("/getAll")
	public String getAllUsers() {
		return this.service.getAllUsers();
	}

	@POST
	@Path("/update/{id}")
	public String updateUser(@PathParam("id") long id, String user) {
		return this.service.updateUser(id, user);
	}

	@DELETE
	@Path("/delete/{id}")
	public String deleteUser(@PathParam("id") long id) {
		return this.service.deleteUser(id);
	}

	@POST
	@Path("/create")
	public String createUser(String user) {
		return this.service.createUser(user);
	}
	
	@POST
	@Path("/login")
	public String login(String user) {
		return this.service.login(user);
	}

}
