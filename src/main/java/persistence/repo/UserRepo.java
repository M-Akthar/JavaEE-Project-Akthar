package persistence.repo;

public interface UserRepo {

	final String SUCCESS = "Operation succeeded";
	final String FAILURE = "Operation failed";
	
	public String getAllUsers();

	public String updateUser(long id, String user);

	public String deleteUser(long id); 

	public String createUser(String user);
	
}
