package test.habit;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import domain.Habit;
import json.HabitJsonManager;
import util.HabitUtils;
import util.Utils;

/**
 * HabitUpdateTests
 * @author narlock
 * 
 * The purpose of this testing class is to test the functionality
 * of the process of calling the update habits method, and making
 * assertions on how our habit JSON and habit UI should look like
 * after performing updates under specific conditions.
 * 
 * Test cases for each of the tests to ensure everything works properly
 * 
 * ============================================================================
 * 
 * HabitJsonManager.jsonHabitArrayToHabitList(createTestHabitsObject()) 
 * yields a list of the following habits:
 * 
 * 1.  Habit with status=1 for today's date, occur everyday
 * 2.  Habit with status=0 for today's date, occur everyday
 * 3.  Habit with status=1 for today's date, occur once a week (MONDAY)
 * 4.  Habit with status=0 for today's date, occur once a week (MONDAY)
 * 5.  Habit with status=1 for today's date, occur once a week (TUESDAY)
 * 6.  Habit with status=0 for today's date, occur once a week (TUESDAY)
 * 7.  Habit with status=1 for today's date, occur once a week (WEDNESDAY)
 * 8.  Habit with status=0 for today's date, occur once a week (WEDNESDAY)
 * 9.  Habit with status=1 for today's date, occur once a week (THURSDAY)
 * 10. Habit with status=0 for today's date, occur once a week (THURSDAY)
 * 11. Habit with status=1 for today's date, occur once a week (FRIDAY)
 * 12. Habit with status=0 for today's date, occur once a week (FRIDAY)
 * 13. Habit with status=1 for today's date, occur once a week (SATURDAY)
 * 14. Habit with status=0 for today's date, occur once a week (SATURDAY)
 * 15. Habit with status=1 for today's date, occur once a week (SUNDAY)
 * 16. Habit with status=0 for today's date, occur once a week (SUNDAY)
 * 
 * 17. Habit with status=1 for yesterday's date, occur everyday
 * 18. Habit with status=0 for yesterday's date, occur everyday
 * 19. Habit with status=1 for yesterday's date, occur once a week (MONDAY)
 * 20. Habit with status=0 for yesterday's date, occur once a week (MONDAY)
 * 21. Habit with status=1 for yesterday's date, occur once a week (TUESDAY)
 * 22. Habit with status=0 for yesterday's date, occur once a week (TUESDAY)
 * 23. Habit with status=1 for yesterday's date, occur once a week (WEDNESDAY)
 * 24. Habit with status=0 for yesterday's date, occur once a week (WEDNESDAY)
 * 25. Habit with status=1 for yesterday's date, occur once a week (THURSDAY)
 * 26. Habit with status=0 for yesterday's date, occur once a week (THURSDAY)
 * 27. Habit with status=1 for yesterday's date, occur once a week (FRIDAY)
 * 28. Habit with status=0 for yesterday's date, occur once a week (FRIDAY)
 * 29. Habit with status=1 for yesterday's date, occur once a week (SATURDAY)
 * 30. Habit with status=0 for yesterday's date, occur once a week (SATURDAY)
 * 31. Habit with status=1 for yesterday's date, occur once a week (SUNDAY)
 * 32. Habit with status=0 for yesterday's date, occur once a week (SUNDAY)
 * 
 * 33. Habit with status=1 for last week's date, occur everyday
 * 34. Habit with status=0 for last week's date, occur everyday
 * 35. Habit with status=1 for last week's date, occur once a week (MONDAY)
 * 36. Habit with status=0 for last week's date, occur once a week (MONDAY)
 * 37. Habit with status=1 for last week's date, occur once a week (TUESDAY)
 * 38. Habit with status=0 for last week's date, occur once a week (TUESDAY)
 * 39. Habit with status=1 for last week's date, occur once a week (WEDNESDAY)
 * 40. Habit with status=0 for last week's date, occur once a week (WEDNESDAY)
 * 41. Habit with status=1 for last week's date, occur once a week (THURSDAY)
 * 42. Habit with status=0 for last week's date, occur once a week (THURSDAY)
 * 43. Habit with status=1 for last week's date, occur once a week (FRIDAY)
 * 44. Habit with status=0 for last week's date, occur once a week (FRIDAY)
 * 45. Habit with status=1 for last week's date, occur once a week (SATURDAY)
 * 46. Habit with status=0 for last week's date, occur once a week (SATURDAY)
 * 47. Habit with status=1 for last week's date, occur once a week (SUNDAY)
 * 48. Habit with status=0 for last week's date, occur once a week (SUNDAY)
 */
class HabitUtilsTests {

	final Date testTodaysDate = Utils.stringToDate("2023-03-14");
	final Date testYesterdaysDate = Utils.stringToDate("2023-03-13");
	final Date testLastWeeksDate = Utils.stringToDate("2023-03-07");
	
	/**
	 * 
	 */
	@Test
	void testDateStringCreation() {
		assertEquals(
				Utils.stringToDate("2023-03-7"),
				Utils.stringToDate("2023-03-07")
			);
	}
	
	/**
	 * habitUtilsTestSameDay
	 * @brief tests HabitUtils.sameDay method
	 */
	@Test
	void habitUtilsTestSameDay() {
		// Test today
		Date todaysDate = Utils.stringToDate("2023-03-13");
		Date date = Utils.stringToDate("2023-03-13");
		assertEquals(ChronoUnit.HOURS.between(date.toInstant(), todaysDate.toInstant()), 0);
		
		//Tests all of the days in January 2023 to ensure they are all equal to 0
		for(int i = 1; i <= 31; i++) {
			todaysDate = Utils.stringToDate("2023-01-" + i);
			date = Utils.stringToDate("2023-01-" + i);
			assertEquals(ChronoUnit.HOURS.between(date.toInstant(), todaysDate.toInstant()), 0);
		}
		
		//Tests all of the days in February 2023 to ensure they are all equal to 0
		for(int i = 1; i <= 28; i++) {
			todaysDate = Utils.stringToDate("2023-02-" + i);
			date = Utils.stringToDate("2023-02-" + i);
			assertEquals(ChronoUnit.HOURS.between(date.toInstant(), todaysDate.toInstant()), 0);
		}
		
		//Tests all of the days in March 2023 to ensure they are all equal to 0
		for(int i = 1; i <= 31; i++) {
			todaysDate = Utils.stringToDate("2023-03-" + i);
			date = Utils.stringToDate("2023-03-" + i);
			assertEquals(ChronoUnit.HOURS.between(date.toInstant(), todaysDate.toInstant()), 0);
		}
		
		//Tests all of the days in April 2023 to ensure they are all equal to 0
		for(int i = 1; i <= 30; i++) {
			todaysDate = Utils.stringToDate("2023-04-" + i);
			date = Utils.stringToDate("2023-04-" + i);
			assertEquals(ChronoUnit.HOURS.between(date.toInstant(), todaysDate.toInstant()), 0);
		}
		
		//Tests all of the days in May 2023 to ensure they are all equal to 0
		for(int i = 1; i <= 31; i++) {
			todaysDate = Utils.stringToDate("2023-05-" + i);
			date = Utils.stringToDate("2023-05-" + i);
			assertEquals(ChronoUnit.HOURS.between(date.toInstant(), todaysDate.toInstant()), 0);
		}
		
		// Test the method for the real today
		Habit testHabit = HabitTestUtils.createTestHabitObject();
		testHabit.setDate(Utils.today());
		assertTrue(HabitUtils.sameDay(testHabit));
	}
	
	/**
	 * habitUtilsTestPreviousDay
	 * @brief tests HabitUtils.previousDay method
	 */
	@Test
	void habitUtilsTestPreviousDay() {
		// DAYLIGHT SAVINGS USA: Previous day where hoursBetween is 23
		Date todaysDate = Utils.stringToDate("2023-03-13");
		Date prevDate = Utils.stringToDate("2023-03-12");
	
		assertEquals(ChronoUnit.HOURS.between(prevDate.toInstant(), todaysDate.toInstant()), 23);
		
		// DAYLIGHT SAVINGS USA: Previous day where hoursBetween is 25
		todaysDate = Utils.stringToDate("2023-11-06");
		prevDate = Utils.stringToDate("2023-11-05");
	
		assertEquals(ChronoUnit.HOURS.between(prevDate.toInstant(), todaysDate.toInstant()), 25);
		
		//Tests all of the days in January 2023 to ensure they are all equal to 0
		Date date;
		for(int i = 2; i <= 31; i++) {
			todaysDate = Utils.stringToDate("2023-01-" + i);
			date = Utils.stringToDate("2023-01-" + (i-1));
			assertEquals(ChronoUnit.HOURS.between(date.toInstant(), todaysDate.toInstant()), 24);
		}
		
		//Tests all of the days in February 2023 to ensure they are all equal to 0
		for(int i = 2; i <= 28; i++) {
			todaysDate = Utils.stringToDate("2023-02-" + i);
			date = Utils.stringToDate("2023-02-" + (i-1));
			assertEquals(ChronoUnit.HOURS.between(date.toInstant(), todaysDate.toInstant()), 24);
		}
		
		//Tests all of the days in March 2023 to ensure they are all equal to 0
		for(int i = 2; i <= 31; i++) {
			if(i == 12) { return; } //Day light savings will be 23
			todaysDate = Utils.stringToDate("2023-03-" + i);
			date = Utils.stringToDate("2023-03-" + (i-1));
			assertEquals(
					ChronoUnit.HOURS.between(date.toInstant(), todaysDate.toInstant()), 24);
		}
		
		//Tests all of the days in April 2023 to ensure they are all equal to 0
		for(int i = 2; i <= 30; i++) {
			todaysDate = Utils.stringToDate("2023-04-" + i);
			date = Utils.stringToDate("2023-04-" + (i-1));
			assertEquals(ChronoUnit.HOURS.between(date.toInstant(), todaysDate.toInstant()), 24);
		}
		
		//Tests all of the days in May 2023 to ensure they are all equal to 0
		for(int i = 2; i <= 31; i++) {
			todaysDate = Utils.stringToDate("2023-05-" + i);
			date = Utils.stringToDate("2023-05-" + (i-1));
			assertEquals(ChronoUnit.HOURS.between(date.toInstant(), todaysDate.toInstant()), 24);
		}
		
		// Test the method for the real today
		Habit testHabit = HabitTestUtils.createTestHabitObject();
		testHabit.setDate(Utils.today());
		assertFalse(HabitUtils.previousDay(testHabit));
	}
	
	/**
	 * habitUtilsTestLastWeek
	 * @brief tests HabitUtils.lastWeek
	 */
	@Test
	void habitUtilsTestLastWeek() {
		// Previous week
		Date todaysDate = Utils.stringToDate("2023-03-13");
		Date prevDate = Utils.stringToDate("2023-03-06");
	
		assertEquals(ChronoUnit.HOURS.between(prevDate.toInstant(), todaysDate.toInstant()), 167);
		
		// Trying another week
		todaysDate = Utils.stringToDate("2023-03-15");
		prevDate = Utils.stringToDate("2023-03-08");
		
		assertEquals(ChronoUnit.HOURS.between(prevDate.toInstant(), todaysDate.toInstant()), 167);
		
		// Test the method for the real today
		Habit testHabit = HabitTestUtils.createTestHabitObject();
		testHabit.setDate(Utils.today());
		assertFalse(HabitUtils.lastWeek(testHabit));
	}
	
	/**
	 * habitUtilsTestOccursToday
	 * @brief Tests HabitUtils.occursToday
	 */
	@Test
	void habitUtilsTestOccursToday() {
		// Test the method with today's actual date
		Habit testHabit = HabitTestUtils.createTestHabitObject();
		testHabit.setOccurrence("1234567");
		
		assertTrue(HabitUtils.occursToday(testHabit));
		assertTrue(testHabit.getOccurrence().contains(String.valueOf(Calendar.MONDAY)));
		assertTrue(testHabit.getOccurrence().contains(String.valueOf(Calendar.TUESDAY)));
		assertTrue(testHabit.getOccurrence().contains(String.valueOf(Calendar.WEDNESDAY)));
		assertTrue(testHabit.getOccurrence().contains(String.valueOf(Calendar.THURSDAY)));
		assertTrue(testHabit.getOccurrence().contains(String.valueOf(Calendar.FRIDAY)));
		assertTrue(testHabit.getOccurrence().contains(String.valueOf(Calendar.SATURDAY)));
		assertTrue(testHabit.getOccurrence().contains(String.valueOf(Calendar.SUNDAY)));
		
		testHabit.setOccurrence("1");
		assertFalse(testHabit.getOccurrence().contains(String.valueOf(Calendar.MONDAY)));
		assertFalse(testHabit.getOccurrence().contains(String.valueOf(Calendar.TUESDAY)));
		assertFalse(testHabit.getOccurrence().contains(String.valueOf(Calendar.WEDNESDAY)));
		assertFalse(testHabit.getOccurrence().contains(String.valueOf(Calendar.THURSDAY)));
		assertFalse(testHabit.getOccurrence().contains(String.valueOf(Calendar.FRIDAY)));
		assertFalse(testHabit.getOccurrence().contains(String.valueOf(Calendar.SATURDAY)));
		assertTrue(testHabit.getOccurrence().contains(String.valueOf(Calendar.SUNDAY)));
	}

	/**
	 * testHabitUtilsIsStreakIncrementable
	 * 
	 * @brief Tests that given a specific list of habits that each of them
	 * returns the correct integer value representing whether their streak
	 * can be incremented.
	 * 
	 * 0: Streak needs to be reset
	 * 1: Streak can be incremented
	 * 2: Streak does not need to be incremented, but does not need reset.
	 */
	@Test
	void testHabitUtilsIsStreakIncrementable() {
		List<Habit> habits = HabitTestUtils.createTestHabitList();
		
		// Get today's date, yesterday's date, and last week's date
		Date today = Utils.today();
		Date yesterday = Utils.yesterday(Utils.today());
		Date lastWeek = Utils.lastWeek(Utils.today());
		
		// Set today's date for the first 16 elements of the test list and assert
		for(int i = 0; i < 16; i++) {
			habits.get(i).setDate(today);
			assertEquals(habits.get(i).getDate(), today);
		}
		
		// Set yesterday's date for the next 16 elements of the test list and assert
		for(int i = 16; i < 32; i++) {
			habits.get(i).setDate(yesterday);
			assertEquals(habits.get(i).getDate(), yesterday);
		}
		
		// Set last week's date for the last 16 elements of the test list and assert
		for(int i = 32; i < 48; i++) {
			habits.get(i).setDate(lastWeek);
			assertEquals(habits.get(i).getDate(), lastWeek);
		}
		
		/*
		 * Cases 1-16 all occur 'today', meaning that no matter if the
		 * habit is completed or not, we expect that the streak will not need
		 * to be incremented and will not need to be reset (2)
		 */
		for(int i = 0; i < 16; i++) {
			assertEquals(HabitUtils.isStreakIncrementable(habits.get(i)), 2);
		}
		
		/*
		 * Cases 16-32 all occur 'yesterday', meaning that if the status is completed (1),
		 * and the habit occurs 'today', the streak can be incremented (1). If the status is 
		 * not completed (0) and the habit occurs 'today', the streak needs to be reset (0).
		 * 
		 * This method is only accessed if the habit occurs today, so we will disregard what
		 * the test object actually has for now in terms of occurrence.
		 */
		for(int i = 16; i < 32; i++) {
			Habit habit = habits.get(i);
			
			if(habit.occursEveryday() && habit.getStatus() == 0)
				assertEquals(HabitUtils.isStreakIncrementable(habits.get(i)), 0);
			else if(habit.occursEveryday() && habit.getStatus() == 1)
				assertEquals(HabitUtils.isStreakIncrementable(habits.get(i)), 1);
			else if(habit.occursOnceAWeek() && habit.getStatus() == 0)
				assertEquals(HabitUtils.isStreakIncrementable(habits.get(i)), 0);
			else if(habit.occursOnceAWeek() && habit.getStatus() == 1)
				assertEquals(HabitUtils.isStreakIncrementable(habits.get(i)), 0);
			else
				fail("Unexpected");
		}
		
		/*
		 * Cases 32-48 all occur 'last week', meaning that if the status is completed (1),
		 * and the habit occurs 'today', the streak will be incremented (1). If the status is
		 * not completed (0) and the habit occurs 'today', the streak needs to be reset (0).
		 * 
		 * This method is only accessed if the habit occurs today, so we will disregard what
		 * the test object actually has for now in terms of occurrence.
		 */
		for(int i = 32; i < 48; i++) {
			Habit habit = habits.get(i);
			
			if(habit.occursEveryday() && habit.getStatus() == 0)
				assertEquals(HabitUtils.isStreakIncrementable(habits.get(i)), 0);
			else if(habit.occursEveryday() && habit.getStatus() == 1)
				assertEquals(HabitUtils.isStreakIncrementable(habits.get(i)), 0);
			else if(habit.occursOnceAWeek() && habit.getStatus() == 0)
				assertEquals(HabitUtils.isStreakIncrementable(habits.get(i)), 0);
			else if(habit.occursOnceAWeek() && habit.getStatus() == 1)
				assertEquals(HabitUtils.isStreakIncrementable(habits.get(i)), 1);
			else
				fail("Unexpected");
		}
	}
	
	/**
	 * testHabitUtilsUpdateHabits
	 * 
	 * @brief Tests that when specific updates are made to the JSON
	 * on today's date, the Json will update accordingly.
	 */
	@Test
	void testHabitUtilsUpdateHabits() {
		// Setup
		List<Habit> referenceHabits = HabitTestUtils.createTestHabitList();
		List<Habit> habits = HabitTestUtils.createTestHabitList();
		
		// Get today's date, yesterday's date, and last week's date
		Date today = Utils.today();
		Date yesterday = Utils.yesterday(Utils.today());
		Date lastWeek = Utils.lastWeek(Utils.today());
		
		// Set today's date for the first 16 elements of the test list and assert
		for(int i = 0; i < 16; i++) {
			referenceHabits.get(i).setDate(today);
			habits.get(i).setDate(today);
			assertEquals(referenceHabits.get(i).getDate(), today);
			assertEquals(habits.get(i).getDate(), today);
		}
		
		// Set yesterday's date for the next 16 elements of the test list and assert
		for(int i = 16; i < 32; i++) {
			referenceHabits.get(i).setDate(yesterday);
			habits.get(i).setDate(yesterday);
			assertEquals(referenceHabits.get(i).getDate(), yesterday);
			assertEquals(habits.get(i).getDate(), yesterday);
		}
		
		// Set last week's date for the last 16 elements of the test list and assert
		for(int i = 32; i < 48; i++) {
			referenceHabits.get(i).setDate(lastWeek);
			habits.get(i).setDate(lastWeek);
			assertEquals(referenceHabits.get(i).getDate(), lastWeek);
			assertEquals(habits.get(i).getDate(), lastWeek);
		}
		
		Habit habit16BeforeChanges = referenceHabits.get(16);
		
		// When
		HabitUtils.updateHabits(habits);
		
		// Then
		assertNotEquals(referenceHabits, habits); //They are not referencing the same objects
		assertEquals(referenceHabits.size(), 48);
		assertEquals(habits.size(), 48);
		
		// TODO Finish assertions and make sure all of the habits are what they should be
		
		/*
		 * Case 1: Habit with status=1 for today's date, occur everyday
		 * Expect: No change, should be equal to referenceHabit
		 */
		assertTrue(habits.get(0).equals(referenceHabits.get(0)));
		
		Habit habit16 = habits.get(16);
		
		assertTrue(1==1);
		
	}
	
	@Test
	void testUpdateHabit() {
		// Setup
		List<Habit> referenceHabits = HabitTestUtils.createTestHabitList();
		List<Habit> habits = HabitTestUtils.createTestHabitList();
		
		// Get today's date, yesterday's date, and last week's date
		Date today = Utils.today();
		Date yesterday = Utils.yesterday(Utils.today());
		Date lastWeek = Utils.lastWeek(Utils.today());
		
		// Set today's date for the first 16 elements of the test list and assert
		for(int i = 0; i < 16; i++) {
			referenceHabits.get(i).setDate(today);
			habits.get(i).setDate(today);
			assertEquals(referenceHabits.get(i).getDate(), today);
			assertEquals(habits.get(i).getDate(), today);
		}
		
		// Set yesterday's date for the next 16 elements of the test list and assert
		for(int i = 16; i < 32; i++) {
			referenceHabits.get(i).setDate(yesterday);
			habits.get(i).setDate(yesterday);
			assertEquals(referenceHabits.get(i).getDate(), yesterday);
			assertEquals(habits.get(i).getDate(), yesterday);
		}
		
		// Set last week's date for the last 16 elements of the test list and assert
		for(int i = 32; i < 48; i++) {
			referenceHabits.get(i).setDate(lastWeek);
			habits.get(i).setDate(lastWeek);
			assertEquals(referenceHabits.get(i).getDate(), lastWeek);
			assertEquals(habits.get(i).getDate(), lastWeek);
		}
		
		//
		Habit testHabit = habits.get(16);
		
		assertEquals(testHabit.getTitle(), "testhabit17");
		assertEquals(testHabit.getStreak(), 0);
		assertEquals(testHabit.getOccurrence(), "1234567");
		assertEquals(testHabit.getStatus(), 1);
		assertEquals(testHabit.getDate(), Utils.yesterday(Utils.today()));
		
		HabitUtils.updateHabit(testHabit);
		
		assertEquals(testHabit.getTitle(), "testhabit17");
		assertEquals(testHabit.getStreak(), 1);
		assertEquals(testHabit.getOccurrence(), "1234567");
		assertEquals(testHabit.getStatus(), 0);
		assertEquals(testHabit.getDate(), Utils.today());
	}
}
