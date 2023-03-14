package test.habit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import domain.Habit;

/**
 * HabitUpdateTests
 * @author narlock
 * 
 * The purpose of this testing class is to test the functionality
 * of the process of calling the update habits method, and making
 * assertions on how our habit json and habit ui should look like
 * after performing updates under specific conditions.
 * 
 * Test cases for each of the tests to ensure everything works properly
 * 
 * 1. Habit with status=1 for today's date, occur everyday
 * 2. Habit with status=0 for today's date, occur everyday
 * 3. Habit with status=1 for today's date, occur once a week (MONDAY)
 * 4. Habit with status=0 for today's date, occur once a week (MONDAY)
 * 5. Habit with status=1 for today's date, occur once a week (TUESDAY)
 * 6. Habit with status=0 for today's date, occur once a week (TUESDAY)
 * 7. Habit with status=1 for today's date, occur once a week (WEDNESDAY)
 * 8. Habit with status=0 for today's date, occur once a week (WEDNESDAY)
 * 9. Habit with status=1 for today's date, occur once a week (THURSDAY)
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
 * 35. Habit with status=0 for last week's date, occur everyday
 * 36. Habit with status=1 for last week's date, occur once a week (MONDAY)
 * 37. Habit with status=0 for last week's date, occur once a week (MONDAY)
 * 38. Habit with status=1 for last week's date, occur once a week (TUESDAY)
 * 39. Habit with status=0 for last week's date, occur once a week (TUESDAY)
 * 40. Habit with status=1 for last week's date, occur once a week (WEDNESDAY)
 * 41. Habit with status=0 for last week's date, occur once a week (WEDNESDAY)
 * 42. Habit with status=1 for last week's date, occur once a week (THURSDAY)
 * 43. Habit with status=0 for last week's date, occur once a week (THURSDAY)
 * 44. Habit with status=1 for last week's date, occur once a week (FRIDAY)
 * 45. Habit with status=0 for last week's date, occur once a week (FRIDAY)
 * 46. Habit with status=1 for last week's date, occur once a week (SATURDAY)
 * 47. Habit with status=0 for last week's date, occur once a week (SATURDAY)
 * 48. Habit with status=1 for last week's date, occur once a week (SUNDAY)
 * 49. Habit with status=0 for last week's date, occur once a week (SUNDAY)
 */
class HabitUpdateTests {
	
	/**
	 * testHabitUtilsOccursToday
	 * 
	 * @brief Tests the occursToday method in HabitUtils on each of
	 * the test cases.
	 */
	void testHabitUtilsOccursToday() {
		List<Habit> habits = createTestHabitsObject();
		
		fail("Not implemented yet");
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
		List<Habit> habits = createTestHabitsObject();
		
		fail("Not implemented yet");
	}
	
	/**
	 * testHabitUtilsUpdateHabits
	 * 
	 * @brief Tests that when specific updates are made to the JSON
	 * on today's date, the Json will update accordingly.
	 */
	@Test
	void testHabitUtilsUpdateHabits() {
		List<Habit> habits = createTestHabitsObject();
		
		fail("Not yet implemented");
	}
	
	private List<Habit> createTestHabitsObject() {
		//TODO Read from resource file to and create object
		return null;
	}
	
}
