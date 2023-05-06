package com.narlock.domain;

import java.awt.Color;
import java.util.List;

public class Epic {
	private String title;
	private Color color;
	
	public Epic(String title, Color color) {
		super();
		this.title = title;
		this.color = color;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
}
