package util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import domain.Habit;

public class HabitUtils {
	public static List<Habit> getTodaysHabits(List<Habit> allHabits) {
		List<Habit> todaysHabits = new ArrayList<Habit>();
		
		Calendar calendar = Calendar.getInstance();
		Integer day = calendar.get(Calendar.DAY_OF_WEEK);
		String dayString = day.toString();
		
		/*
		 * Calendar days are associated with an integer value.
		 * SUNDAY    : 1
		 * MONDAY    : 2
		 * TUESDAY   : 3
		 * WEDNESDAY : 4
		 * THURSDAY  : 5
		 * FRIDAY    : 6
		 * SATURDAY  : 7
		 * They are stored as a string that can be parsed to an integer
		 * inside of the habits.json file.
		 */
		
		for(Habit habit : allHabits) {
			if(habit.getOccurrence().contains(dayString)) {
				todaysHabits.add(habit);
			}
		}
		return todaysHabits;
	}
	
	/**
	 * updateHabits
	 * @brief Will update the habits. This means that if a day has passed,
	 * streaks will be updated, isCompleted will be set back to false, etc.
	 * @param todaysHabits
	 * @return List of updated habits
	 */
	public static List<Habit> updateHabits(List<Habit> todaysHabits) {
		//TODO
		return todaysHabits;
	}
}
