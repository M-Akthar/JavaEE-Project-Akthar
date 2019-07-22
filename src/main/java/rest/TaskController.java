package rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import service.TaskService;

@Path("/task")
public class TaskController {

	@Inject
	private TaskService service;
	
	@GET
	@Path("/getAll")
	public String getAllTasks() {
		return this.service.getAllTasks();
	}
	
	@GET
	@Path("/getTask/{id}")
	public String getTask(@PathParam("id") long id) {
		return this.service.getTask(id);
	}

	@POST
	@Path("/update/{id}")
	public String updateTask(@PathParam("id") long id, String task) {
		return this.service.updateTask(id, task);
	}

	@DELETE
	@Path("/delete/{id}")
	public String deleteTask(@PathParam("id") long id) {
		return this.service.deleteTask(id);
	}

	@POST
	@Path("/create/{userID}")
	public String createTask(String task, @PathParam("userID") long userID) {
		return this.service.createTask(task, userID);
	}
}
