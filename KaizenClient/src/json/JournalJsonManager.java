package json;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import domain.Journal;
import domain.JournalEntry;
import util.Debug;
import util.Utils;

public class JournalJsonManager extends JsonManager {
	
	private static final Debug debug = new Debug(true);
	private static FileWriter file;
	
	public static Journal readJson() {
		//Using today's date, we will lookup if there is a journal entry with the date.
		//If there is, return it, otherwise, return a new entry
		File journalJsonFile = new File(journalPath);
		if(journalJsonFile.exists()) {
			JSONParser parser = new JSONParser();
			try {
				Reader reader = new FileReader(journalPath);
				JSONObject journalObject = (JSONObject) parser.parse(reader);
				Journal journal = jsonJournalObjectToJournal(journalObject);
				debug.print(journal.toString());
				return journal;
			} catch (IOException | ParseException almostJustProOsuGamer) {
				almostJustProOsuGamer.printStackTrace();
			}
		} else {
			File documentsDirectory = new File(documentsPath);
			documentsDirectory.mkdir();
			
			File kaizenDirectory = new File(directoryPath);
			kaizenDirectory.mkdir();
			
			try {
				journalJsonFile.createNewFile();
				writeJournalJsonToFile(new Journal());
				readJson();
			} catch (IOException proOsuGamer) {
				proOsuGamer.printStackTrace();
			}
		}
		
		return new Journal();
	}
	
	public static boolean writeJournalJsonToFile(Journal journal) {
		File journalJsonFile = new File(journalPath);
		JSONObject journalObject = journalToJsonJournalObject(journal);
		try {
			file = new FileWriter(journalJsonFile);
			file.write(journalObject.toJSONString());
			debug.print("" + journal.getEntries());
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
	
	public static Journal jsonJournalObjectToJournal(JSONObject journalObject) {
		Date lastLog = Utils.stringToDate((String) journalObject.get("lastLog"));
	    long logStreak = journalObject.get("logStreak") == null ? 0 : (Long) journalObject.get("logStreak");
	    boolean showStreak = journalObject.get("showStreak") == null ? false : (Boolean) journalObject.get("showStreak");
	    
		JSONArray journalEntries = (JSONArray) journalObject.get("entries");
		List<JournalEntry> entries = new ArrayList<JournalEntry>();
		for(int i = 0; i < journalEntries.size(); i++) {
			JSONObject entryObject = (JSONObject) journalEntries.get(i);
			JournalEntry entry = jsonJournalEntryToJournalEntry(entryObject);
			entries.add(entry);
		}
		
		return new Journal(entries, 
				lastLog, 
				logStreak, 
				showStreak
			);
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject journalToJsonJournalObject(Journal journal) {
		JSONObject journalObject = new JSONObject();
		journalObject.put("lastLog", Utils.dateAsString(journal.getLastLog()));
		journalObject.put("logStreak", journal.getLogStreak());
		journalObject.put("showStreak", journal.isShowStreak());
		
		JSONArray entriesArray = new JSONArray();
		for(JournalEntry entry : journal.getEntries()) {
			entriesArray.add(journalEntryToJsonJournalEntry(entry));
		}
		journalObject.put("entries", entriesArray);
		return journalObject;
	}
	
	public static JournalEntry jsonJournalEntryToJournalEntry(JSONObject entryObject) {
		return new JournalEntry(
				Utils.stringToDate((String) entryObject.get("date")),
				(long) entryObject.get("howWasDay"),
				(String) entryObject.get("text1"),
				(String) entryObject.get("text2"),
				(String) entryObject.get("text3"),
				(String) entryObject.get("text4")
			);
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject journalEntryToJsonJournalEntry(JournalEntry entry) {
		JSONObject entryObject = new JSONObject();
		entryObject.put("date", Utils.dateAsString(entry.getDate()));
		entryObject.put("howWasDay", entry.getHowWasDay());
		entryObject.put("text1", entry.getText1());
		entryObject.put("text2", entry.getText2());
		entryObject.put("text3", entry.getText3());
		entryObject.put("text4", entry.getText4());
		return entryObject;
	}
}
