package test.habit;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import domain.Habit;
import json.HabitJsonManager;
import util.Utils;

public class HabitTestUtils {
	static final String testHabitsPath = System.getProperty("user.home") + File.separatorChar + "Documents" + File.separatorChar + "Kaizen" + File.separatorChar + "test" + File.separatorChar + "habits.json";
	
	public static Habit createTestHabitObject() {
		return new Habit(
					"testTitle",
					0,
					"1234567",
					0,
					Utils.stringToDate("2023-03-13")
				);
	}
	
	public static JSONArray createTestHabitsObject() {
		try {
			JSONParser parser = new JSONParser();
			Reader reader = new FileReader(testHabitsPath);
			return (JSONArray) parser.parse(reader);
		} catch (IOException | ParseException fnfe) {
			fnfe.printStackTrace();
		}
		return null;
	}
	
	public static List<Habit> createTestHabitList() {
		return HabitJsonManager.jsonHabitArrayToHabitList(createTestHabitsObject());
	}
}
