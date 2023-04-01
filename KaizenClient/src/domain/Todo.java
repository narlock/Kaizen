package domain;

import java.util.ArrayList;
import java.util.Collections;
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
	
	public void sortItemsByDate() {
		Collections.sort(items, (o1, o2) -> o1.getDueDate().compareTo(o2.getDueDate()));
	}
	
	public void sortItemsByDateReverse() {
		Collections.sort(items, (o1, o2) -> o1.getDueDate().compareTo(o2.getDueDate()));
		Collections.reverse(items);
	}
	
	public void sortItemsByPriority() {
		items.sort((o1, o2) -> {
			if(o1.getPriority() > o2.getPriority()) {
				return -1;
			}
			else if(o1.getPriority() == o2.getPriority()) {
				return 0;
			}
			else {
				return 1;
			}
		});
	}
	
	public void sortItemsByPriorityReverse() {
		items.sort((o1, o2) -> {
			if(o1.getPriority() < o2.getPriority()) {
				return -1;
			}
			else if(o1.getPriority() == o2.getPriority()) {
				return 0;
			}
			else {
				return 1;
			}
		});
	}

}
