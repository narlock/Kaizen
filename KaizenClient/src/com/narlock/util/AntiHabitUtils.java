package com.narlock.util;

import java.time.temporal.ChronoUnit;
import java.util.Date;

import com.narlock.domain.AntiHabit;

public class AntiHabitUtils extends Utils {

	public static long getDaysSince(Date antiHabitDate) {
		return Math.abs(ChronoUnit.DAYS.between(Utils.today().toInstant(), antiHabitDate.toInstant()));
	}
}
