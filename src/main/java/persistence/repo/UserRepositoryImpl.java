package persistence.repo;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

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

}
