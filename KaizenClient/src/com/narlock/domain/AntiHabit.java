package com.narlock.domain;

import java.util.Date;

public class AntiHabit {
	private String title;
	private Date date;
	
	public AntiHabit(String title, Date date) {
		super();
		this.title = title;
		this.date = date;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
}
