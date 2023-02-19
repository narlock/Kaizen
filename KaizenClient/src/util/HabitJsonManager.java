package util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import domain.Habit;

public class HabitJsonManager extends JsonManager {
	
	private static final Debug debug = new Debug(true);
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
				writeHabitJsonToFile(habitsJsonFile, new ArrayList<Habit>());
				readHabits();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ErrorPane.displayError(null, "Unexpected error occurred during reading habits.json file.");
		System.exit(1);
		return null;
	}
	
	//DEBUG Mode readHabits, creates sample habit list
	public static List<Habit> readHabits(int debug) { 
		List<Habit> habitList = new ArrayList<Habit>();
		
		Habit habit1 = new Habit("Test Habit 1", 2, "mtwhfsu", 0, new Date());
		Habit habit2 = new Habit("Test Habit 2", 2, "mtwhfsu", 0, new Date());
		Habit habit3 = new Habit("Test Habit 3", 2, "mtwhfsu", 0, new Date());
		Habit habit4 = new Habit("Test Habit 3", 2, "mtwhfsu", 0, new Date());
		Habit habit5 = new Habit("Test Habit 3", 2, "mtwhfsu", 0, new Date());
		Habit habit6 = new Habit("Test Habit 3", 2, "mtwhfsu", 0, new Date());
		Habit habit7 = new Habit("Test Habit 3", 2, "mtwhfsu", 0, new Date());
		
		habitList.add(habit1);
		habitList.add(habit2);
		habitList.add(habit3);
		habitList.add(habit4);
		habitList.add(habit5);
		habitList.add(habit6);
		habitList.add(habit7);
		
		return habitList;
	}
	
	public static boolean writeHabitJsonToFile(File habitsFile, List<Habit> habits) {
		if(habits.isEmpty()) {
			JSONArray emptyArray = new JSONArray();
			try {
				file = new FileWriter(habitsFile);
				file.write(emptyArray.toJSONString());
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		} else {
			//TODO Implement
			return false;
		}
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
		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");
		Date date;
		try {
			date = formatter.parse((String) obj.get("date"));
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
}
