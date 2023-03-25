package json;

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

import domain.Habit;
import util.Constants;
import util.Debug;
import util.ErrorPane;

public class HabitJsonManager extends JsonManager {
	private static FileWriter file;
	
	public static List<Habit> readHabits() {
		File habitsJsonFile = new File(habitsPath);
		if(habitsJsonFile.exists()) {
			//TODO read the habits from the file and return, try catch and throw error
			JSONParser parser = new JSONParser();
			try {
				Reader reader = new FileReader(habitsPath);
				JSONArray habitsArray = (JSONArray) parser.parse(reader);
				return jsonHabitArrayToHabitList(habitsArray);
			} catch (IOException | ParseException fnfe) {
				// TODO Auto-generated catch block
				fnfe.printStackTrace();
			}
		} else {
			//Check if the documents directory exists, if not, create it
			File documentsDirectory = new File(documentsPath);
			documentsDirectory.mkdir(); //Creates the Documents/ directory if it does not exist.
			
			//Check if the Kaizen directory exists, if not, create it
			File kaizenDirectory = new File(directoryPath);
			kaizenDirectory.mkdir(); //Creates the Documents/Kaizen/ directory if it does not exist.
			
			//TODO Create new habits.json file and return
			try {
				habitsJsonFile.createNewFile();
				writeHabitJsonToFile(new ArrayList<Habit>());
				readHabits();
			} catch (IOException proOsuGamer) {
				// TODO Auto-generated catch block
				proOsuGamer.printStackTrace();
			}
		}

		return new ArrayList<Habit>();
	}
	
	public static boolean writeHabitJsonToFile(List<Habit> habits) {
		debug.print("Inside of writeHabitJsonToFile");
		File habitsJsonFile = new File(habitsPath);
		if(habits.isEmpty()) {
			debug.print("[writeHabitJsonToFile] Creating habits");
			JSONArray emptyArray = new JSONArray();
			try {
				file = new FileWriter(habitsJsonFile);
				file.write(emptyArray.toJSONString());
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			} finally {
	            try {
	                file.flush();
	                file.close();
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }
		} else {
			debug.print("[writeHabitJsonToFile] Updating habits");
			JSONArray array = habitListToJsonHabitArray(habits);
			try {
				file = new FileWriter(habitsJsonFile);
				debug.print("Habits Array: " + array.toJSONString());
				file.write(array.toJSONString());
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			} finally {
	            try {
	                file.flush();
	                file.close();
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }
		}
	}
	
	public static boolean addHabit(Habit habit) {
		debug.print("Inside of addHabit");
		List<Habit> habits = readHabits();
		habits.add(habit);
		writeHabitJsonToFile(habits);
		return true;
	}
	
	public static List<Habit> jsonHabitArrayToHabitList(JSONArray array) {
		debug.print("Inside of jsonHabitArrayToHabitList");
		if(array.isEmpty()) { 
			debug.print("Returning empty list");
			return new ArrayList<Habit>(); 
		}
		
		List<Habit> habits = new ArrayList<Habit>();
		for(int i = 0; i < array.size(); i++) {
			JSONObject obj = (JSONObject) array.get(i);
			Habit habit = jsonHabitObjectToHabitObject(obj);
			habits.add(habit);
		}
		
		for(Habit habit : habits) {
			debug.print(habit.toString());
		}
		
		return habits;
	}
	
	public static Habit jsonHabitObjectToHabitObject(JSONObject obj) {
		Date date;
		try {
			date = Constants.formatter.parse((String) obj.get("date"));
			return new Habit(
					(String) obj.get("title"),
					(Long) obj.get("streak"),
					(String) obj.get("occurrence"),
					(Long) obj.get("status"),
					date
				);
		} catch (java.text.ParseException e) {
			ErrorPane.displayError(null, "A parsing error occurred");
			e.printStackTrace();
		}
		
		ErrorPane.displayError(null, "An unexpected error occurred");
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONArray habitListToJsonHabitArray(List<Habit> habits) {
		JSONArray array = new JSONArray();
		for(Habit habit : habits) {
			debug.print("Adding " + habit.getTitle() + " to JSONArray");
			array.add(habitToJsonHabitObject(habit));
		}
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject habitToJsonHabitObject(Habit habit) {
		JSONObject obj = new JSONObject();
		obj.put("title", habit.getTitle());
		obj.put("streak", habit.getStreak());
		obj.put("occurrence", habit.getOccurrence());
		obj.put("status", habit.getStatus());
		obj.put("date", Constants.formatter.format(habit.getDate()));
		return obj;
	}
}
