package domain;

import java.util.Date;

public class Habit {
	private int id;
	private String title;
	private int streak;
	private String occurrence;
	private int status;
	private Date date;
	
	public Habit(int id, String title, int streak, String occurrence, int status, Date date) {
		this.id = id;
		this.title = title;
		this.streak = streak;
		this.occurrence = occurrence;
		this.status = status;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
}
