package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	public static String todayAsString() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return formatter.format(date);
	}
}
