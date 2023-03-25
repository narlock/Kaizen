package json;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import domain.AntiHabit;
import util.Utils;

public class AntiHabitJsonManager extends JsonManager {
	private static FileWriter file;
	
	public static List<AntiHabit> readJson() {
		File antiHabitsJsonFile = new File(antiHabitsPath);
		if(antiHabitsJsonFile.exists()) {
			//TODO read the habits from the file and return, try catch and throw error
			JSONParser parser = new JSONParser();
			try {
				Reader reader = new FileReader(antiHabitsPath);
				JSONArray antiHabitsArray = (JSONArray) parser.parse(reader);
				return antiHabitJsonArrayToAntiHabitList(antiHabitsArray);
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
				antiHabitsJsonFile.createNewFile();
				writeAntiHabitsToFile(new ArrayList<AntiHabit>());
				readJson();
			} catch (IOException proOsuGamer) {
				// TODO Auto-generated catch block
				proOsuGamer.printStackTrace();
			}
		}

		return new ArrayList<AntiHabit>();
	}
	
	public static boolean writeAntiHabitsToFile(List<AntiHabit> antiHabits) {
		File antiHabitsJsonFile = new File(antiHabitsPath);
		if(antiHabits.isEmpty()) {
			JSONArray emptyArray = new JSONArray();
			try {
				file = new FileWriter(antiHabitsJsonFile);
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
			JSONArray array = antiHabitListToAntiHabitJsonArray(antiHabits);
			try {
				file = new FileWriter(antiHabitsJsonFile);
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
	
	public static List<AntiHabit> antiHabitJsonArrayToAntiHabitList(JSONArray array) {
		if(array.isEmpty()) { return new ArrayList<AntiHabit>(); }
		
		List<AntiHabit> antiHabits = new ArrayList<AntiHabit>();
		for(int i = 0; i < array.size(); i++) {
			JSONObject obj = (JSONObject) array.get(i);
			AntiHabit antiHabit = antiHabitJsonObjectToAntiHabit(obj);
			antiHabits.add(antiHabit);
		}
		
		return antiHabits;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONArray antiHabitListToAntiHabitJsonArray(List<AntiHabit> antiHabits) {
		JSONArray array = new JSONArray();
		for(AntiHabit antiHabit : antiHabits) {
			array.add(antiHabitToAntiHabitJsonObject(antiHabit));
		}
		return array;
	}
	
	public static AntiHabit antiHabitJsonObjectToAntiHabit(JSONObject antiHabitObject) {
		return new AntiHabit(
					(String) antiHabitObject.get("title"),
					Utils.stringToDate( (String) antiHabitObject.get("date"))
				);
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject antiHabitToAntiHabitJsonObject(AntiHabit antiHabit) {
		JSONObject antiHabitObject = new JSONObject();
		antiHabitObject.put("title", antiHabit.getTitle());
		antiHabitObject.put("date", Utils.dateAsString(antiHabit.getDate()));
		return antiHabitObject;
	}
}
