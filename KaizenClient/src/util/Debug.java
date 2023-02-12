package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Debug {
	private boolean on;
	
	public Debug(boolean on) {
		this.on = on;
	}
	
	public void print(String message) {
		if(on) {
			String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
			System.out.println("[DEBUG] " + timeStamp + " : " + message);
		}
	}
}
