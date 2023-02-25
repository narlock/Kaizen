package domain;

import java.util.Date;

public class Habit {
	private String title;
	private long streak;
	private String occurrence;
	private long status;
	private Date date;
	
	public Habit(String title, long streak, String occurrence, long status, Date date) {
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

	public long getStreak() {
		return streak;
	}

	public void setStreak(long streak) {
		this.streak = streak;
	}

	public String getOccurrence() {
		return occurrence;
	}

	public void setOccurrence(String occurrence) {
		this.occurrence = occurrence;
	}

	public long getStatus() {
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

	@Override
	public String toString() {
		return "Habit [title=" + title + ", streak=" + streak + ", occurrence=" + occurrence + ", status=" + status
				+ ", date=" + date + "]";
	}

	public boolean occursEveryday() {
		return occurrence.contains("1") &&
				occurrence.contains("2") &&
				occurrence.contains("3") &&
				occurrence.contains("4") &&
				occurrence.contains("5") &&
				occurrence.contains("6") &&
				occurrence.contains("7");
	}
	
	public boolean occursOnceAWeek() {
		if(occurrence.contains("1") &&
				!occurrence.contains("2") &&
				!occurrence.contains("3") &&
				!occurrence.contains("4") &&
				!occurrence.contains("5") &&
				!occurrence.contains("6") &&
				!occurrence.contains("7")) {
			return true;
		} else if(!occurrence.contains("1") &&
				occurrence.contains("2") &&
				!occurrence.contains("3") &&
				!occurrence.contains("4") &&
				!occurrence.contains("5") &&
				!occurrence.contains("6") &&
				!occurrence.contains("7")) {
			return true;
		} else if(!occurrence.contains("1") &&
				!occurrence.contains("2") &&
				occurrence.contains("3") &&
				!occurrence.contains("4") &&
				!occurrence.contains("5") &&
				!occurrence.contains("6") &&
				!occurrence.contains("7")) {
			return true;
		} else if(!occurrence.contains("1") &&
				!occurrence.contains("2") &&
				!occurrence.contains("3") &&
				occurrence.contains("4") &&
				!occurrence.contains("5") &&
				!occurrence.contains("6") &&
				!occurrence.contains("7")) {
			return true;
		} else if(!occurrence.contains("1") &&
				!occurrence.contains("2") &&
				!occurrence.contains("3") &&
				!occurrence.contains("4") &&
				occurrence.contains("5") &&
				!occurrence.contains("6") &&
				!occurrence.contains("7")) {
			return true;
		} else if(!occurrence.contains("1") &&
				!occurrence.contains("2") &&
				!occurrence.contains("3") &&
				!occurrence.contains("4") &&
				!occurrence.contains("5") &&
				occurrence.contains("6") &&
				!occurrence.contains("7")) {
			return true;
		} else if(!occurrence.contains("1") &&
				!occurrence.contains("2") &&
				!occurrence.contains("3") &&
				!occurrence.contains("4") &&
				!occurrence.contains("5") &&
				!occurrence.contains("6") &&
				occurrence.contains("7")) {
			return true;
		} else return false;
	}
	
}
