package task.objects;

import java.io.Serializable;
import java.sql.Time;

public class Work_Item implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private User user;
	private String name;
	private Time estimated_effort;
	private String description;
	private int status;
	private Time effort;
	private Time deviation_percentage;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Time getEstimated_effort() {
		return estimated_effort;
	}
	public void setEstimated_effort(Time estimated_effort) {
		this.estimated_effort = estimated_effort;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Time getEffort() {
		return effort;
	}
	public void setEffort(Time effort) {
		this.effort = effort;
	}
	public Time getDeviation_percentage() {
		return deviation_percentage;
	}
	public void setDeviation_percentage(Time deviation_percentage) {
		this.deviation_percentage = deviation_percentage;
	}

}
