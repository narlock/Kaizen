package com.narlock.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.narlock.util.Debug;
import com.narlock.util.Utils;

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
		this.showStreak = false;
	}
	
	public Journal(
			List<JournalEntry> entries, 
			Date lastLog, 
			long logStreak,
			boolean showStreak
		) {
		super();
		this.entries = entries;
		Collections.sort(entries); //Sorts the entries by date
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
	
	public boolean isEntryInEntries(Date dateString) {
		if(entries.isEmpty()) {
			return false;
		}
		
		for(JournalEntry entry : entries) {
			final Debug debug = new Debug(true);
			debug.print("JOURNAL - entryDateAsString = " + Utils.dateAsString(entry.getDate()));
			debug.print("JOURNAL - dateString = " + Utils.dateAsString(dateString));
			debug.print("" + Utils.dateAsString(entry.getDate()).equals(Utils.dateAsString(dateString)));
			if(Utils.dateAsString(entry.getDate()).equals(Utils.dateAsString(dateString))) {
				return true;
			}
		}
		return false;
	}
	
	public JournalEntry getEntryByDateString(String dateString) {
		if(entries.isEmpty()) {
			return new JournalEntry();
		}
		
		for(JournalEntry entry : entries) {
			if(Utils.dateAsString(entry.getDate()).equals(dateString)) {
				return entry;
			}
		}
		throw new RuntimeException("Entry does not exist in journal");
	}
	
	public String getEntryDates() {
		String dates = "";
		for(JournalEntry entry : entries) {
			dates += Utils.dateAsString(entry.getDate()) + ", ";
		}
		return dates;
	}

	@Override
	public String toString() {
		return "Journal [entries=" + entries + ", lastLog=" + lastLog + ", logStreak=" + logStreak + ", showStreak="
				+ showStreak + "]";
	}
}
