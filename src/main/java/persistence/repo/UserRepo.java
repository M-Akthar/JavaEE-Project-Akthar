package persistence.repo;

import java.util.List;

import persistence.domain.User;

public interface UserRepo {

	final String SUCCESS = "{\"message\": \"Success\"}";
	final String FAILURE = "{\"message\": \"Failed\"}";
	
	public String getAllUsers();
	
	public List<User> getListOfAllUsers();

	public String updateUser(long id, String user);

	public String deleteUser(long id); 

	public String createUser(String user);
	
	public String findUser(long id);
	
	public String login(String user);
	
}
