package persistence.repo;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import persistence.domain.Task;
import persistence.domain.User;
import util.JSONUtil;

@Transactional(value = TxType.SUPPORTS)
public class UserRepositoryImpl implements UserRepo{

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	@Inject
	private JSONUtil gson;
	
	public String getAllUsers() {
		// TODO Auto-generated method stub
		
		TypedQuery<User> query = this.manager.createQuery("SELECT u FROM User u", User.class);
		return this.gson.getJSONForObject(query.getResultList());
		
	}
	
	public List<User> getListOfAllUsers() {	
		// This was created to be used during the create user method in the service layer.
		
		TypedQuery<User> query = this.manager.createQuery("SELECT u FROM User u", User.class);
		return query.getResultList();
		
	}

	@Transactional(value = TxType.REQUIRED)
	public String updateUser(long id, String user) {
		// TODO Auto-generated method stub
		
		User current = this.manager.find(User.class, id);
		User newUser = this.gson.getObjectForJSON(user, User.class);
		
//		I have commented out the set username as I won't allow username changes for now.
//		usernames should be unique and would require validation checks
//		At this point newUser.getUsername() will be null as I won't have an input field for a username update
		
//		For validation in the future it might be worth creating a method that evaluates to true or false
//		depending on whether a username exists already or not
		
//		current.setUsername(newUser.getUsername());
		current.setFirstName(newUser.getFirstName());
		current.setLastName(newUser.getLastName());
		current.setPassword(newUser.getPassword());
		
		this.manager.persist(current);
		
		return SUCCESS;
		
	}

	@Transactional(value = TxType.REQUIRED)
	public String deleteUser(long id) {
		// TODO Auto-generated method stub
		
		TypedQuery<Task> query = this.manager.createQuery("SELECT t FROM Task t", Task.class);
		
		List<Task> taskDB = query.getResultList();
		
		// The following loops deletes all tasks belonging to the user before deleting the user.
		// This is to prevent orphan tasks and any related errors
		for(Task t: taskDB) {
			if(t.getUser().getUserID() == id) {
				this.manager.remove(this.manager.find(Task.class, t.getTaskID()));
			}
		}
		
		this.manager.remove(this.manager.find(User.class, id));
		return SUCCESS;
	}

	@Transactional(value = TxType.REQUIRED)
	public String createUser(String user) {
		// TODO Auto-generated method stub
		
		this.manager.persist(this.gson.getObjectForJSON(user, User.class));
		return SUCCESS;
	}

	public String findUser(long id) {
		// TODO Auto-generated method stub
		
		User result = this.manager.find(User.class, id);
		return this.gson.getJSONForObject(result);
	}

	public String login(String user) {
		// TODO Auto-generated method stub
		long userID = -1;
		User aUser = this.gson.getObjectForJSON(user, User.class);
		String username = aUser.getUsername();
		String password = aUser.getPassword();
		TypedQuery<User> query = this.manager.createQuery("Select u From User u WHERE username='"+username+
				"' AND password ='"+password+"'", User.class);
		
		List<User> result = query.getResultList();
		
		// The following loop only gets executed if the typed query returns a result.
		for(User u : result) {
			userID = u.getUserID();
		}
		
		if(userID == -1) {
			return "{\"message\": \"Account not found\"}";			
		} else {
			return "{\"USERID\": \""+userID+"\"}";			
		}
	}

}
