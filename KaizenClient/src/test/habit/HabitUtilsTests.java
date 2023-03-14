package test.habit;

import static org.junit.jupiter.api.Assertions.*;

import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.junit.jupiter.api.Test;

import util.Utils;

class HabitUtilsTests {

	@Test
	void habitUtilsTestSameDay() {
		//today
		Date todaysDate = Utils.stringToDate("2023-03-13");
		Date date = Utils.stringToDate("2023-03-13");
		
		long btwn = ChronoUnit.HOURS.between(date.toInstant(), todaysDate.toInstant());
		
		assertEquals(btwn, 0);
	}
	
	@Test
	void habitUtilsTestPreviousDay() {
		//previous day
		Date todaysDate = Utils.stringToDate("2023-03-13");
		Date prevDate = Utils.stringToDate("2023-03-12");
		
		long btwn = ChronoUnit.HOURS.between(prevDate.toInstant(), todaysDate.toInstant());
	
		assertEquals(btwn, 23);
	}
	
	@Test
	void habitUtilsTestLastWeek() {
		//previous day
		Date todaysDate = Utils.stringToDate("2023-03-13");
		Date prevDate = Utils.stringToDate("2023-03-06");
		
		long btwn = ChronoUnit.HOURS.between(prevDate.toInstant(), todaysDate.toInstant());
	
		assertEquals(btwn, 167);
	}

}
