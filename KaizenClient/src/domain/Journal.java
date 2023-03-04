package domain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.Utils;

public class Journal {
	private List<JournalEntry> entries;
	private Date lastLog;
	private long logStreak;
	private boolean showStreak;
	
	/**
	 * New Journal Constructor
	 */
	public Journal() {
		this.entries = new ArrayList<JournalEntry>();
		this.lastLog = Utils.today(); //There is no "last log" until a user saves their first log.
		this.logStreak = 0;
		this.showStreak = true;
	}
	
	public Journal(List<JournalEntry> entries, Date lastLog, long logStreak, boolean showStreak) {
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

	public long getLogStreak() {
		return logStreak;
	}

	public void setLogStreak(long logStreak) {
		this.logStreak = logStreak;
	}

	public boolean isShowStreak() {
		return showStreak;
	}

	public void setShowStreak(boolean showStreak) {
		this.showStreak = showStreak;
	}
	
	public JournalEntry getEntryByDateString(String dateString) {
		if(entries.isEmpty()) {
			return new JournalEntry();
		}
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		for(JournalEntry entry : entries) {
			if(formatter.format(entry.getDate()).equals(dateString)) {
				return entry;
			}
		}
		throw new RuntimeException("Entry does not exist in journal");
	}

	@Override
	public String toString() {
		return "Journal [entries=" + entries + ", lastLog=" + lastLog + ", logStreak=" + logStreak + ", showStreak="
				+ showStreak + "]";
	}
}
