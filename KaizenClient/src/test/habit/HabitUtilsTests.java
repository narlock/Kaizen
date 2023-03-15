package test.habit;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.junit.jupiter.api.Test;

import domain.Habit;
import json.HabitJsonManager;
import util.Utils;

class HabitUtilsTests {
	
	final String testHabitsPath = System.getProperty("user.home") + File.separatorChar + "Documents" + File.separatorChar + "Kaizen" + File.separatorChar + "test" + File.separatorChar + "habits.json";
	final Date testTodaysDate = Utils.stringToDate("2023-03-14");
	final Date testYesterdaysDate = Utils.stringToDate("2023-03-13");
	final Date testLastWeeksDate = Utils.stringToDate("2023-03-07");
	
	/**
	 * testCreateTestHabitsObject
	 * 
	 * @brief Integration test for the createTestHabits, which reads the JSON
	 * from the testing path, this method will then do assertions after the
	 * JSONArray is converted to the habit list.
	 */
	@Test
	void testCreateTestHabitsObject() {
		File file = new File(testHabitsPath);
		assertTrue(file.exists());
		
		JSONArray testHabitArray = HabitTestUtils.createTestHabitsObject();
		assertNotNull(testHabitArray);
		assertEquals(testHabitArray.size(), 48);
		
		List<Habit> testHabits = HabitJsonManager.jsonHabitArrayToHabitList(testHabitArray);
		assertNotNull(testHabits);
		assertEquals(testHabits.size(), 48);
		
		//Test that the date is correct
		for(int i = 0; i < 16; i++) {
			testHabits.get(i).setDate(testTodaysDate);
			assertEquals(Utils.dateAsString(testHabits.get(i).getDate()), "2023-03-14");
		}
		
		for(int i = 16; i < 32; i++) {
			testHabits.get(i).setDate(testYesterdaysDate);
			assertEquals(Utils.dateAsString(testHabits.get(i).getDate()), "2023-03-13");
		}
		
		for(int i = 32; i < 48; i++) {
			testHabits.get(i).setDate(testLastWeeksDate);
			assertEquals(Utils.dateAsString(testHabits.get(i).getDate()), "2023-03-07");
		}
		
		//Test that the status is correct on creation
		for(int i = 0; i < 46; i = i + 2) { 
			assertEquals(testHabits.get(i).getStatus(), 1);
		}
		
		for(int i = 1; i < 48; i = i + 2) {
			assertEquals(testHabits.get(i).getStatus(), 0);
		}
	}

}
