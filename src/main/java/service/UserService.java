package service;

import javax.inject.Inject;

import persistence.repo.UserRepositoryImpl;

public class UserService {
	
	@Inject
	private UserRepositoryImpl repo;
	
	public String getAllUser() {
		return this.repo.getAllUsers();
	}

	public String updateUser(long id, String user) {
		return this.repo.updateUser(id, user);
	}

	public String deleteUser(long id) {
		return this.repo.deleteUser(id);
	}

	public String createUser(String user) {
		return this.repo.createUser(user);
	}

}
