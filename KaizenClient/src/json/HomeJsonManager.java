package json;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import domain.Home;

public class HomeJsonManager extends JsonManager {
	private static FileWriter file;
	
	public static Home readJson() {
		File homeJsonFile = new File(homePath);
		if(homeJsonFile.exists()) {
			//TODO read the habits from the file and return, try catch and throw error
			JSONParser parser = new JSONParser();
			try {
				Reader reader = new FileReader(homePath);
				JSONObject homeObject = (JSONObject) parser.parse(reader);
				return homeJsonToHome(homeObject);
			} catch (IOException | ParseException fnfe) {
				// TODO Auto-generated catch block
				fnfe.printStackTrace();
			}
		} else {
			//Check if the documents directory exists, if not, create it
			File documentsDirectory = new File(documentsPath);
			documentsDirectory.mkdir(); //Creates the Documents/ directory if it does not exist.
			
			//Check if the Kaizen directory exists, if not, create it
			File kaizenDirectory = new File(directoryPath);
			kaizenDirectory.mkdir(); //Creates the Documents/Kaizen/ directory if it does not exist.
			
			//TODO Create new habits.json file and return
			try {
				homeJsonFile.createNewFile();
				writeHomeToFile(new Home());
				readJson();
			} catch (IOException proOsuGamer) {
				// TODO Auto-generated catch block
				proOsuGamer.printStackTrace();
			}
		}

		return new Home();
	}
	
	public static boolean writeHomeToFile(Home home) {
		File homeJsonFile = new File(homePath);
		JSONObject homeObject = homeToHomeJson(home);
		try {
			file = new FileWriter(homeJsonFile);
			file.write(homeObject.toJSONString());
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
	}
	
	public static Home homeJsonToHome(JSONObject homeObject) {
		return new Home(
				(String) homeObject.get("widget1"),
				(String) homeObject.get("widget2"),
				(String) homeObject.get("widget3"),
				(String) homeObject.get("widget4")
			);
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject homeToHomeJson(Home home) {
		JSONObject homeObject = new JSONObject();
		homeObject.put("widget1", home.getWidget1());
		homeObject.put("widget2", home.getWidget2());
		homeObject.put("widget3", home.getWidget3());
		homeObject.put("widget4", home.getWidget4());
		return homeObject;
	}
}
