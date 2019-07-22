package persistence.repo;

public interface TaskRepo {

	final String SUCCESS = "Operation succeeded";
	final String FAILURE = "Operation failed";
	
	public String getAllTasks();
	
	public String getTask(long id);

	public String updateTask(long id, String task);

	public String deleteTask(long id); 

	public String createTask(String task, long userID);
	
}
