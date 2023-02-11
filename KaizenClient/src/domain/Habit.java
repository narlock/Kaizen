package domain;

import java.util.Date;

public class Habit {
	private String title;
	private int streak;
	private String occurrence;
	private int status;
	private Date date;
	
	public Habit(String title, int streak, String occurrence, int status, Date date) {
		this.title = title;
		this.streak = streak;
		this.occurrence = occurrence;
		this.status = status;
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getStreak() {
		return streak;
	}

	public void setStreak(int streak) {
		this.streak = streak;
	}

	public String getOccurrence() {
		return occurrence;
	}

	public void setOccurrence(String occurrence) {
		this.occurrence = occurrence;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public boolean isCompleted() {
		if(status == 0) { return false; }
		else return true;
	}
}
