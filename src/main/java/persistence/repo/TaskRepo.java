package persistence.repo;

public interface TaskRepo {

	final String SUCCESS = "{\"message\": \"Success\"}";
	final String FAILURE = "{\"message\": \"Failed\"}";
	
	public String getAllTasks();
	
	public String getUserTasks(long id);

	public String updateTask(long id, String task);

	public String deleteTask(long id); 

	public String createTask(String task, long userID);
	
}
