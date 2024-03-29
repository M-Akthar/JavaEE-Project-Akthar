package service;

import java.util.List;

import javax.inject.Inject;

import persistence.domain.User;
import persistence.repo.UserRepositoryImpl;
import util.JSONUtil;

public class UserService {
	
	private JSONUtil gson = new JSONUtil();
	
	@Inject
	private UserRepositoryImpl repo;
	
	public String getAllUsers() {
		return this.repo.getAllUsers();
	}

	public String updateUser(long id, String user) {
		return this.repo.updateUser(id, user);
	}

	public String deleteUser(long id) {
		return this.repo.deleteUser(id);
	}

	public String createUser(String user) {
		List<User> userDB = this.repo.getListOfAllUsers();
		
		String newUsername = gson.getObjectForJSON(user, User.class).getUsername();
		
		if(userDB != null) {
			for(User u : userDB) {
				if(u.getUsername().equals(newUsername)) {
					return "{\"message\": \"username already exists\"}";
				}
			}			
		}
		
		return this.repo.createUser(user);
	}
	
	public String findUser(long id) {
		return this.repo.findUser(id);
	}
	
	public String login(String user) {
		return this.repo.login(user);
	}

}
