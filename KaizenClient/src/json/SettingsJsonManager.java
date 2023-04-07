package json;

import domain.Settings;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

public class SettingsJsonManager extends JsonManager {
	
    private static FileWriter file;

    public static Settings readJson() {
        File settingsJsonFile = new File(settingsPath);
        if(settingsJsonFile.exists()) {
            JSONParser parser = new JSONParser();
            try {
                Reader reader = new FileReader(settingsPath);
                JSONObject settingsObject = (JSONObject) parser.parse(reader);
                return jsonSettingsObjectToSettings(settingsObject);
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        } else {
            try {
                settingsJsonFile.createNewFile();
                writeSettingsJsonToFile(new Settings());
                readJson();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new Settings();
    }

    public static boolean writeSettingsJsonToFile(Settings settings) {
		File settingsJsonFile = new File(settingsPath);
		JSONObject settingsObject = settingsToJsonSettingsObject(settings);
		try {
			file = new FileWriter(settingsJsonFile);
			file.write(settingsObject.toJSONString());
			return true;
		} catch (IOException proOsuGamer) {
			proOsuGamer.printStackTrace();
			return false;
		} finally {
			try {
				file.flush();
				file.close();
			} catch (IOException proOsuGamer) {
				proOsuGamer.printStackTrace();
			}
		}
    }

    public static Settings jsonSettingsObjectToSettings(JSONObject settingsObject) {
    	return new Settings(
    		    settingsObject.get("enableDiscordRPC") == null ? true : (Boolean) settingsObject.get("enableDiscordRPC"),
    		    settingsObject.get("showTodoEpicOnClipboard") == null ? true : (Boolean) settingsObject.get("showTodoEpicOnClipboard"),
    		    settingsObject.get("showStreakOnClipboard") == null ? true : (Boolean) settingsObject.get("showStreakOnClipboard"),
    		    settingsObject.get("journalMode") == null ? 0 : (long) settingsObject.get("journalMode"),
    		    settingsObject.get("showHowWasDay") == null ? true : (Boolean) settingsObject.get("showHowWasDay"),
    		    settingsObject.get("journalText1AreaPrompt") == null ? "What events occurred today?" : (String) settingsObject.get("journalText1AreaPrompt"),
    		    settingsObject.get("journalText2AreaPrompt") == null ? "What events occurred today?" : (String) settingsObject.get("journalText2AreaPrompt"),
    		    settingsObject.get("journalText3AreaPrompt") == null ? "What events occurred today?" : (String) settingsObject.get("journalText3AreaPrompt"),
    		    settingsObject.get("journalText4AreaPrompt") == null ? "What events occurred today?" : (String) settingsObject.get("journalText4AreaPrompt")
    		);
    }

    @SuppressWarnings("unchecked")
	public static JSONObject settingsToJsonSettingsObject(Settings settings) {
    	JSONObject jsonObject = new JSONObject();
        jsonObject.put("enableDiscordRPC", settings.isEnableDiscordRPC());
        jsonObject.put("showTodoEpicOnClipboard", settings.isShowTodoEpicOnClipboard());
        jsonObject.put("showStreakOnClipboard", settings.isShowStreakOnClipboard());
        jsonObject.put("journalMode", settings.getJournalMode());
        jsonObject.put("showHowWasDay", settings.isShowHowWasDay());
        jsonObject.put("journalText1AreaPrompt", settings.getJournalText1AreaPrompt());
        jsonObject.put("journalText2AreaPrompt", settings.getJournalText2AreaPrompt());
        jsonObject.put("journalText3AreaPrompt", settings.getJournalText3AreaPrompt());
        jsonObject.put("journalText4AreaPrompt", settings.getJournalText4AreaPrompt());
        return jsonObject;
    }
}