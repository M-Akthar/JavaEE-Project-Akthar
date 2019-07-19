package persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long taskID;
	private String taskName;
//	private long userID;
	
	@ManyToOne
	private User user;
	
	public Task() {
		super();
	}
	
	public Task(long taskID, String taskName, long userID) {
		super();
		this.taskID = taskID;
		this.taskName = taskName;
//		this.userID = userID;
	}
	
	public long getTaskID() {
		return taskID;
	}
	public void setTaskID(long taskID) {
		this.taskID = taskID;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
//	public long getUserID() {
//		return userID;
//	}
//	public void setUserID(long userID) {
//		this.userID = userID;
//	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
