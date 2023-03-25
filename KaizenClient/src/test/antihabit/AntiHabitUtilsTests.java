package test.antihabit;

import static org.junit.jupiter.api.Assertions.*;

import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.junit.jupiter.api.Test;

import util.Utils;

class AntiHabitUtilsTests {

	@Test
	void test() {
		Date date1 = Utils.stringToDate("2023-03-25");
		Date date2 = Utils.stringToDate("2023-03-23");
		
		long daysBetween = ChronoUnit.DAYS.between(date2.toInstant(), date1.toInstant());
	
		assertEquals(daysBetween, 2);
	}

}
