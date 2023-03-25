package json;

import java.io.File;

public class JsonManager {
	public static final String documentsPath = System.getProperty("user.home") + File.separatorChar + "Documents";
	public static final String directoryPath = System.getProperty("user.home") + File.separatorChar + "Documents" + File.separatorChar + "Kaizen";
	public static final String todoPath = System.getProperty("user.home") + File.separatorChar + "Documents" + File.separatorChar + "Kaizen" + File.separatorChar + "todo.json";
	public static final String habitsPath = System.getProperty("user.home") + File.separatorChar + "Documents" + File.separatorChar + "Kaizen" + File.separatorChar + "habits.json";
	public static final String journalPath = System.getProperty("user.home") + File.separatorChar + "Documents" + File.separatorChar + "Kaizen" + File.separatorChar + "journal.json";
	public static final String relationshipsPath = System.getProperty("user.home") + File.separatorChar + "Documents" + File.separatorChar + "Kaizen" + File.separatorChar + "relationships.json";
}
