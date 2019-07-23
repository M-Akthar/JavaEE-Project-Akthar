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
		User newUser = gson.getObjectForJSON(user, User.class);
		String newUsername = newUser.getUsername();
		
		if(userDB.size() >= 1 && userDB != null) {
			for(User u : userDB) {
				if(u.getUsername().equals(newUsername)) {
					return "{\"message\": \"username already exists\"}";
				}
			}			
		}
		
		return this.repo.createUser(user);
	}

}
