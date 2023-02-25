package domain;

import java.util.Date;
import java.util.List;

public class Journal {
	private List<JournalEntry> entries;
	private Date lastLog;
	private int logStreak;
	private boolean showStreak;
	
	public Journal(List<JournalEntry> entries, Date lastLog, int logStreak, boolean showStreak) {
		super();
		this.entries = entries;
		this.lastLog = lastLog;
		this.logStreak = logStreak;
		this.showStreak = showStreak;
	}

	public List<JournalEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<JournalEntry> entries) {
		this.entries = entries;
	}

	public Date getLastLog() {
		return lastLog;
	}

	public void setLastLog(Date lastLog) {
		this.lastLog = lastLog;
	}

	public int getLogStreak() {
		return logStreak;
	}

	public void setLogStreak(int logStreak) {
		this.logStreak = logStreak;
	}

	public boolean isShowStreak() {
		return showStreak;
	}

	public void setShowStreak(boolean showStreak) {
		this.showStreak = showStreak;
	}
	
}
