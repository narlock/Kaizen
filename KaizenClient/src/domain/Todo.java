package domain;

import java.util.ArrayList;
import java.util.List;

public class Todo {
	private String sortMode;
	private List<Epic> epics;
	private List<TodoItem> items;
	
	/**
	 * New Todo Constructor
	 */
	public Todo() {
		this.sortMode = "date";
		this.epics = new ArrayList<Epic>();
		this.items = new ArrayList<TodoItem>();
	}
	
	public Todo(String sortMode, List<Epic> epics, List<TodoItem> items) {
		super();
		this.sortMode = sortMode;
		this.epics = epics;
		this.items = items;
	}

	public String getSortMode() {
		return sortMode;
	}

	public void setSortMode(String sortMode) {
		this.sortMode = sortMode;
	}

	public List<Epic> getEpics() {
		return epics;
	}

	public void setEpics(List<Epic> epics) {
		this.epics = epics;
	}

	public List<TodoItem> getItems() {
		return items;
	}

	public void setItems(List<TodoItem> items) {
		this.items = items;
	}
	
}
