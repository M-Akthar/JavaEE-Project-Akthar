package service;

import javax.inject.Inject;

import persistence.repo.TaskRepositoryImpl;

public class TaskService {

	@Inject
	private TaskRepositoryImpl taskRepo;
	
	public String getAllTasks() {
		return this.taskRepo.getAllTasks();
	}
	
	public String getTask(long id) {
		return this.taskRepo.getTask(id);
	}

	public String updateTask(long id, String task) {
		return this.taskRepo.updateTask(id, task);
	}

	public String deleteTask(long id) {
		return this.taskRepo.deleteTask(id);
	}

	public String createTask(String task, long userID) {
		return this.taskRepo.createTask(task, userID);
	}
	
}
