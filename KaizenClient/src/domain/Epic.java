package domain;

import java.awt.Color;
import java.util.List;

public class Epic {
	private String title;
	private Color color;
	private List<TodoItem> items;
	
	public Epic(String title, Color color, List<TodoItem> items) {
		super();
		this.title = title;
		this.color = color;
		this.items = items;
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
	
	public List<TodoItem> getItems() {
		return items;
	}
	
	public void setItems(List<TodoItem> items) {
		this.items = items;
	}
}
