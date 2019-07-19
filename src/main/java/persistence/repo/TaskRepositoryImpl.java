package persistence.repo;

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
public class TaskRepositoryImpl implements TaskRepo {
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	@Inject
	private JSONUtil gson;

	public String getAllTasks() {
		// TODO Auto-generated method stub
		
		TypedQuery<Task> query = this.manager.createQuery("SELECT t FROM Task t", Task.class);
		return this.gson.getJSONForObject(query.getResultList());
		
	}

	@Transactional(value = TxType.REQUIRED)
	
	public String updateTask(long id, String task) {
		// TODO Auto-generated method stub
		
		Task current = this.manager.find(Task.class, id);
		Task newTask = this.gson.getObjectForJSON(task, Task.class);
		
		current.setTaskName(newTask.getTaskName());
		
		this.manager.persist(current);
		
		return SUCCESS;
	}

	@Transactional(value = TxType.REQUIRED)
	public String deleteTask(long id) {
		// TODO Auto-generated method stub
		
		this.manager.remove(this.manager.find(Task.class, id));
		return SUCCESS;

	}

	public String createTask(String task, String user) {
		// TODO Auto-generated method stub
		
		User taskOwner = this.gson.getObjectForJSON(user, User.class);
		Task addedTask = this.gson.getObjectForJSON(task, Task.class);
		
		addedTask.setUser(taskOwner);
		
		this.manager.persist(addedTask);

		return SUCCESS;
		
	}
	
}
