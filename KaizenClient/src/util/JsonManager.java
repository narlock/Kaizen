package util;

import java.io.File;

public class JsonManager {
	private static final String directoryPath = System.getProperty("user.home") + File.separatorChar + "Documents" + File.separatorChar + "Kaizen";
	private static final String habitsPath = System.getProperty("user.home") + File.separatorChar + "Documents" + File.separatorChar + "Kaizen" + File.separatorChar + "habits.json";
	private static final String journalPath = System.getProperty("user.home") + File.separatorChar + "Documents" + File.separatorChar + "Kaizen" + File.separatorChar + "journal.json";
	private static final String relationshipsPath = System.getProperty("user.home") + File.separatorChar + "Documents" + File.separatorChar + "Kaizen" + File.separatorChar + "relationships.json";
	
	public boolean directoryExists() {
		File directory = new File(directoryPath);
		return directory.exists();
	}
	
	public boolean habitsJsonExists() {
		File habitsJsonFile = new File(habitsPath);
		return habitsJsonFile.exists();
	}
	
	public boolean journalJsonExists() {
		File journalJsonFile = new File(journalPath);
		return journalJsonFile.exists();
	}
	
	public boolean relationshipsJsonExists() {
		File relationshipsJsonFile = new File(relationshipsPath);
		return relationshipsJsonFile.exists();
	}
}
