package com.narlock.json;

import java.awt.Color;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.narlock.domain.Epic;
import com.narlock.domain.Todo;
import com.narlock.domain.TodoItem;
import com.narlock.util.Debug;
import com.narlock.util.Utils;

public class TodoJsonManager extends JsonManager {
	
	private static final Debug debug = new Debug(true);
	private static FileWriter file;

	public static Todo readJson() {
		File todoJsonFile = new File(todoPath);
		if(todoJsonFile.exists()) {
			JSONParser parser = new JSONParser();
			try {
				Reader reader = new FileReader(todoPath);
				JSONObject todoObject = (JSONObject) parser.parse(reader);
				Todo todo = jsonTodoObjectToTodo(todoObject);
				return todo;
			} catch (IOException | ParseException almostJustProOsuGamer) {
				almostJustProOsuGamer.printStackTrace();
			}
		} else {
			File documentsDirectory = new File(documentsPath);
			documentsDirectory.mkdir();
			
			File kaizenDirectory = new File(directoryPath);
			kaizenDirectory.mkdir();
			
			try {
				todoJsonFile.createNewFile();
				writeTodoJsonToFile(new Todo());
				readJson();
			} catch (IOException proOsuGamer) {
				proOsuGamer.printStackTrace();
			}
		}
		
		return new Todo();
	}
	
	public static boolean writeTodoJsonToFile(Todo todo) {
		File todoJsonFile = new File(todoPath);
		JSONObject todoJsonObject = todoToJsonTodoObject(todo);
		try {
			file = new FileWriter(todoJsonFile);
			file.write(todoJsonObject.toJSONString());
			return true;
		} catch (IOException proOsuGamer) {
			proOsuGamer.printStackTrace();
			return false;
		} finally {
			try {
				file.flush();
				file.close();
			} catch (IOException proOsuGamer) {
				proOsuGamer.printStackTrace();
			}
		}
	}
	
	public static Todo jsonTodoObjectToTodo(JSONObject todoObject) {
		String sortMode = (String) todoObject.get("sortMode");
		String viewMode = (String) todoObject.get("viewMode");
		
		JSONArray epicsArray = (JSONArray) todoObject.get("epics");
		List<Epic> epics = new ArrayList<Epic>();
		for(int i = 0; i < epicsArray.size(); i++) {
			JSONObject epicObject = (JSONObject) epicsArray.get(i);
			Epic epic = jsonEpicObjectToEpic(epicObject);
			epics.add(epic);
		}
		
		JSONArray todoItemsArray = (JSONArray) todoObject.get("items");
		List<TodoItem> todoItems = new ArrayList<TodoItem>();
		for(int i = 0; i < todoItemsArray.size(); i++) {
			JSONObject todoItemObject = (JSONObject) todoItemsArray.get(i);
			TodoItem todoItem = jsonTodoItemObjectToTodoItem(todoItemObject);
			todoItems.add(todoItem);
		}
		
		return new Todo(sortMode, viewMode, epics, todoItems);
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject todoToJsonTodoObject(Todo todo) {
		JSONObject todoObject = new JSONObject();
		todoObject.put("sortMode", todo.getSortMode());
		todoObject.put("viewMode", todo.getViewMode());
		
		JSONArray epicsArray = new JSONArray();
		for(Epic epic : todo.getEpics()) {
			epicsArray.add(epicToJsonEpicObject(epic));
		}
		todoObject.put("epics", epicsArray);
		
		JSONArray todoItemsArray = new JSONArray();
		for(TodoItem todoItem : todo.getItems()) {
			todoItemsArray.add(todoItemToJsonTodoItemObject(todoItem));
		}
		todoObject.put("items", todoItemsArray);
		
		return todoObject;
	}
	
	public static Epic jsonEpicObjectToEpic(JSONObject epicObject) {
		String title = (String) epicObject.get("title");
		
		JSONArray rgbObject = (JSONArray) epicObject.get("color");
		Color color = new Color(
					(int) (long) rgbObject.get(0),
					(int) (long) rgbObject.get(1),
					(int) (long) rgbObject.get(2)
				);
		
		return new Epic(title, color);
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject epicToJsonEpicObject(Epic epic) {
		JSONObject epicObject = new JSONObject();
		epicObject.put("title", epic.getTitle());
		
		JSONArray rgbObject = new JSONArray();
		rgbObject.add(epic.getColor().getRed());
		rgbObject.add(epic.getColor().getGreen());
		rgbObject.add(epic.getColor().getBlue());
		
		epicObject.put("color", rgbObject);
		
		return epicObject;
	}
	
	public static TodoItem jsonTodoItemObjectToTodoItem(JSONObject todoItemObject) {
		String title = (String) todoItemObject.get("title");
		Date dueDate = Utils.stringToDate((String) todoItemObject.get("dueDate"));
		
		Date completedDate;
		String completedDateString = (String) todoItemObject.get("completedDate");
		if(completedDateString.equals("")) {
			completedDate = null;
		} else {
			completedDate = Utils.stringToDate(completedDateString);
		}
		
		long priority = (long) todoItemObject.get("priority");
		String epic = (String) todoItemObject.get("epic");
		
		return new TodoItem(title, dueDate, completedDate, priority, epic);
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject todoItemToJsonTodoItemObject(TodoItem todoItem) {
		JSONObject todoItemObject = new JSONObject();
		todoItemObject.put("title", todoItem.getTitle());
		todoItemObject.put("dueDate", Utils.dateAsString(todoItem.getDueDate()));
		
		if(todoItem.getCompletedDate() == null) {
			todoItemObject.put("completedDate", "");
		} else {
			todoItemObject.put("completedDate", Utils.dateAsString(todoItem.getCompletedDate()));
		}
		
		todoItemObject.put("priority", todoItem.getPriority());
		todoItemObject.put("epic", todoItem.getEpic());
		
		return todoItemObject;
	}
}
