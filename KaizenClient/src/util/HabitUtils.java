package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import domain.Habit;

public class HabitUtils {
//	public static List<Habit> getTodaysHabits(List<Habit> allHabits) {
//		List<Habit> todaysHabits = new ArrayList<Habit>();
//		
//		Calendar calendar = Calendar.getInstance();
//		Integer day = calendar.get(Calendar.DAY_OF_WEEK);
//		String dayString = day.toString();
//		
//		/*
//		 * Calendar days are associated with an integer value.
//		 * SUNDAY    : 1
//		 * MONDAY    : 2
//		 * TUESDAY   : 3
//		 * WEDNESDAY : 4
//		 * THURSDAY  : 5
//		 * FRIDAY    : 6
//		 * SATURDAY  : 7
//		 * They are stored as a string that can be parsed to an integer
//		 * inside of the habits.json file.
//		 */
//		
//		for(Habit habit : allHabits) {
//			if(habit.getOccurrence().contains(dayString)) {
//				todaysHabits.add(habit);
//			}
//		}
//		return todaysHabits;
//	}
	
	/**
	 * updateHabits
	 * @brief Will update the habits. This means that if a day has passed,
	 * streaks will be updated, isCompleted will be set back to false, etc.
	 * 
	 * @param todaysHabits
	 * @return List of updated habits
	 */
	public static List<Habit> updateHabits(List<Habit> habits) {
		for(Habit habit : habits) {
			//Only update the habits that occur today
			if(occursToday(habit)) {
				switch(isStreakIncrementable(habit)) {
				case 0:
					habit.setStreak(0);
					habit.setStatus(0);
					habit.setDate(today());
					break;
				case 1:
					habit.setStreak(habit.getStreak() + 1);
					habit.setStatus(0);
					habit.setDate(today());
					break;
				case 2:
					break;
				}
			}
		}
		return habits;
	}
	
	/**
	 * isStreakIncrementable
	 * @brief Check to see if the streak can be incremented.
	 * 
	 * If the date attribute of the habit is the same as the current date,
	 * this means that either the streak has already been updated, so no
	 * need to increment or set to zero. Or, the habit was created on
	 * the current date (no change to the habit streak if the date attribute
	 * is the same as the current date)
	 * 
	 * If the date attribute is on the previous increment to the current date.
	 * For example, the date attribute is Feb 20th, 2023, the current date is
	 * Feb 21st, 2023, and the streak's occurrence is everyday, this will
	 * indicate that the habit streak should be incremented.
	 * 
	 * Another example will be if the habit occurrence is once a week on Friday.
	 * If the date attribute is Feb 17th, 2023, and the current date is
	 * Feb 24th, 2023. The habit streak will be incremented when the habit
	 * is completed.
	 * 
	 * If the date attribute does not align with the current date given the
	 * examples, this method will return false. This means that the streak
	 * 
	 * @param habit
	 * @return  0: Streak needs to be reset
	 * 			1: Streak can be incremented
	 * 			2: Streak does not need to be incremented, but does not need reset.
	 */
	public static int isStreakIncrementable(Habit habit) {
		if(habit.occursEveryday()) {
			//If it's the same day, return 2
			if(sameDay(habit)) {
				return 2;
			}
			//If the date attr is the previous day, return 1
			if(previousDay(habit)) {
				return 1;
			}
			//Otherwise, return 0, needs a reset.
			return 0;
		} else if(habit.occursOnceAWeek()) {
			//If it's the same day, return 0
			if(sameDay(habit)) {
				return 2;
			}
			//If it's last week, return 1
			if(lastWeek(habit)) {
				return 1;
			}
			//Otherwise return 0, needs a reset.
			return 0;
			
		} else {
			//Not a habit that support streaks.
			//These habits increment if it's not the same day
			//to simply display that the user completed the
			//habit again.
			if(sameDay(habit)) {
				return 2;
			} else return 1;
		}
	}
	
	public static Date today() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String formattedDateString = formatter.format(date);
		try {
			return formatter.parse(formattedDateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		throw new RuntimeException("Unexpected error occurred");
	}
	
	public static boolean sameDay(Habit habit) {
		return ChronoUnit.DAYS.between(habit.getDate().toInstant(), today().toInstant()) 
				== 0;
	}
	
	public static boolean previousDay(Habit habit) {
		return ChronoUnit.DAYS.between(habit.getDate().toInstant(), today().toInstant()) 
				== 1;
	}
	
	public static boolean lastWeek(Habit habit) {
		return ChronoUnit.DAYS.between(habit.getDate().toInstant(), today().toInstant()) 
				== 7;
	}
	
	public static boolean occursToday(Habit habit) {
		Calendar calendar = Calendar.getInstance();
		Integer day = calendar.get(Calendar.DAY_OF_WEEK);
		String dayString = day.toString();
		
		return habit.getOccurrence().contains(dayString);
	}
}
