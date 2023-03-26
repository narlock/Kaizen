package util;

public class TodoUtils extends Utils {
	public static String getPriorityImage(long indicator) {
		switch((int) indicator) {
		case 0:
			return "PRIORITY_LOW.png";
		case 1:
			return "PRIORITY_MEDIUM.png";
		case 2:
			return "PRIORITY_HIGH.png";
		case 3:
			return "PRIORITY_CRITICAL.png";
		}
		throw new RuntimeException("Error grabbing priority image, invalid indicator: " + indicator);
	}
}
