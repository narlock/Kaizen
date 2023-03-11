package domain;

import java.util.Date;

public class TodoItem {
	private String title;
	private Date dueDate;
	private Date completedDate;
	private long priority;
	
	public TodoItem(String title, Date dueDate, Date completedDate, long priority) {
		super();
		this.title = title;
		this.dueDate = dueDate;
		this.completedDate = completedDate;
		this.priority = priority;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Date getDueDate() {
		return dueDate;
	}
	
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	public Date getCompletedDate() {
		return completedDate;
	}
	
	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}
	
	public long getPriority() {
		return priority;
	}
	
	public void setPriority(long priority) {
		this.priority = priority;
	}
}
