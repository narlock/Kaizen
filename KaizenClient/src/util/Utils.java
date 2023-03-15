package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import domain.Habit;

public class Utils {
	
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
	
	public static Date yesterday(Date today) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = Date.from(today.toInstant().minus(1, ChronoUnit.DAYS));
		String formattedDateString = formatter.format(date);
		try {
			return formatter.parse(formattedDateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		throw new RuntimeException("Unexpected error occurred");
	}
	
	public static Date lastWeek(Date today) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = Date.from(today.toInstant().minus(6, ChronoUnit.DAYS));
		String formattedDateString = formatter.format(date);
		try {
			return formatter.parse(formattedDateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		throw new RuntimeException("Unexpected error occurred");
	}
	
	public static String todayAsString() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return formatter.format(date);
	}
	
	public static Date stringToDate(String dateString) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return formatter.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String dateAsString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}
}
