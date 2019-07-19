package persistence.repo;

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

	@Transactional(value = TxType.REQUIRED)
	public String updateUser(long id, String user) {
		// TODO Auto-generated method stub
		
		User current = this.manager.find(User.class, id);
		User newUser = this.gson.getObjectForJSON(user, User.class);
		
		current.setUsername(newUser.getUsername());
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

}
