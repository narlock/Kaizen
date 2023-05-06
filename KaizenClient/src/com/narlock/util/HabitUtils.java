package com.narlock.util;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;

import com.narlock.domain.Habit;

public class HabitUtils extends Utils {
	
	private static final Debug debug = new Debug(true);
	
	/**
	 * updateHabits
	 * @brief Will update the habits. This means that if a day has passed,
	 * streaks will be updated, isCompleted will be set back to false, etc.
	 * 
	 * @param todaysHabits
	 * @return List of updated habits
	 */
	public static List<Habit> updateHabits(List<Habit> habits) {
		debug.print("[HabitUtils/updateHabits] Inside of update Habits");
		for(Habit habit : habits) {
			//Only update the habits that occur today
			updateHabit(habit);
		}
		return habits;
	}
	
	/**
	 * updateHabit
	 * @brief Updates an individual habit based on occurrence,
	 * status, and date.
	 * @param habit
	 */
	public static void updateHabit(Habit habit) {
		if(occursToday(habit)) {
			switch(isStreakIncrementable(habit)) {
			case 0:
				debug.print("[HabitUtils/updateHabits] switch case 0");
				habit.setStreak(0);
				habit.setStatus(0);
				habit.setDate(today());
				break;
			case 1:
				debug.print("[HabitUtils/updateHabits] switch case 1");
				habit.setStreak(habit.getStreak() + 1);
				habit.setStatus(0);
				habit.setDate(today());
				break;
			case 2:
				debug.print("[HabitUtils/updateHabits] switch case 2");
				break;
			}
		}
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
			debug.print("[HabitUtils/isStreakIncrementable] inside occurs everyday");
			//If it's the same day, return 2
			if(sameDay(habit)) {
				debug.print("[HabitUtils/isStreakIncrementable] Habit is on the same day, returning");
				return 2;
			}
			//If the date attr is the previous day and was completed, return 1
			if(previousDay(habit) && habit.getStatus() == 1) {
				return 1;
			}
			//Otherwise, return 0, needs a reset.
			return 0;
		} else if(habit.occursOnceAWeek()) {
			//If it's the same day, return 0
			if(sameDay(habit)) {
				return 2;
			}
			//If it's last week and completed, return 1
			if(lastWeek(habit) && habit.getStatus() == 1) {
				return 1;
			}
			//Otherwise return 0, needs a reset.
			return 0;
			
		} else {
			/*
			 * Not a habit that support streaks.
			 * These habits increment if it's not the same day
			 * to simply display that the user completed the
			 * habit again
			 */
			if(sameDay(habit) && habit.getStatus() == 1) {
				return 2;
			}
			else if(sameDay(habit) && habit.getStatus() == 0) {
				return 2;
			} else return 1;
		}
	}
	
	/**
	 * sameDay
	 * @param habit
	 * @return true if the habit's date is the same as today's date in hours
	 */
	public static boolean sameDay(Habit habit) {
		long hoursBetween = ChronoUnit.HOURS.between(habit.getDate().toInstant(), today().toInstant());
		return hoursBetween == 0;
	}
	
	/**
	 * previousDay
	 * @brief Checks if the habit's date is the previous day. Checks for day light savings.
	 * @param habit
	 * @return true if is previous day to today's current date.
	 */
	public static boolean previousDay(Habit habit) {
		long hoursBetween = ChronoUnit.HOURS.between(habit.getDate().toInstant(), today().toInstant());
		return hoursBetween == 23 || hoursBetween == 24 || hoursBetween == 25;
	}
	
	/**
	 * lastWeek
	 * @brief Checks if the habit's date is last week exactly. Checks for day light savings.
	 * @param habit
	 * @return true if is last week day to today's current date.
	 */
	public static boolean lastWeek(Habit habit) {
		long hoursBetween = ChronoUnit.HOURS.between(habit.getDate().toInstant(), today().toInstant());
		return hoursBetween == 167 || hoursBetween == 168 || hoursBetween == 166;
	}
	
	/**
	 * occursToday
	 * @param habit
	 * @return true if the habit occurs today based on occurrence
	 */
	public static boolean occursToday(Habit habit) {
		//Occurs everyday
		if(habit.getOccurrence().equals("1234567")) { return true; }
		
		//Ensure today is inside of the habit's occurrence
		
		//Get today's day of the week value
		Calendar c = Calendar.getInstance();
		c.setTime(today());
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		
		//Compare dayOfWeek
		String dayOfWeekString = String.valueOf(dayOfWeek);
		return habit.getOccurrence().contains(dayOfWeekString);
	}
}
