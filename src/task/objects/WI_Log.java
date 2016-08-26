package task.objects;

import java.io.Serializable;
import java.util.Date;

public class WI_Log implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private Work_Item wi;
	private Date change_date;
	private String formatedDate;
	private int changed_status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Work_Item getWi() {
		return wi;
	}
	public void setWi(Work_Item wi) {
		this.wi = wi;
	}
	public Date getChange_date() {
		return change_date;
	}
	public void setChange_date(Date change_date) {
		this.change_date = change_date;
	}
	public int getChanged_status() {
		return changed_status;
	}
	public void setChanged_status(int changed_status) {
		this.changed_status = changed_status;
	}
	public String getFormatedDate() {
		return formatedDate;
	}
	public void setFormatedDate(String formatedDate) {
		this.formatedDate = formatedDate;
	}
	

}
