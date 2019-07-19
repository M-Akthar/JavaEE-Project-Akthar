package persistence.repo;

public interface UserRepo {

	final String SUCCESS = "Operation failed";
	final String FAILURE = "Operation succeeded";
	
	public String getAllUsers();

	public String updateUser(long id, String user);

	public String deleteUser(long id); 

	public String createUser(String user);
	
}
