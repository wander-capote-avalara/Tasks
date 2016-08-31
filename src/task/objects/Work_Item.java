package task.objects;

import java.io.Serializable;
import java.sql.Time;
import java.text.DecimalFormat;

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
	@SuppressWarnings("unused")
	private String deviation_percentage;
	
	
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
	public String getDeviation_percentage() {
		try{
			return new DecimalFormat("##.##").format(Double.parseDouble(this.effort.toString().replace(":", ""))*100/Double.parseDouble(this.estimated_effort.toString().replace(":", ""))).toString()+"%";
		}catch(Exception e){
			return "0%";
		}
	}

}
