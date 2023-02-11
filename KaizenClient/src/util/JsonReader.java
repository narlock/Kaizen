package util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import domain.Habit;

public class JsonReader extends JsonManager {
	public static List<Habit> readHabits() {
		//If no habits file, create one and set list<habits> to empty list
		//Otherwise, load the habits
		System.out.println("TODO");
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
	
	public static List<Habit> jsonHabitArrayToHabitList(JSONArray array) {
		return null;
	}
	
	public static Habit jsonHabitObjectToHabitObject(JSONObject object) {
		return null;
	}
}
