package util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * CheckForUpdates
 * @author antho
 * 
 * This class implements a checkForUpdates object
 * which contains a stored version list in which
 * correspond to releases that are on the github repository
 * 
 * the httpurl connection will identify all of the releases
 * that the github contains
 * 
 * if they are not equal, a new update will be signaled.
 *
 */
public class CheckForUpdates {
	
	private ArrayList<String> currentReleases;
	
	public CheckForUpdates() {
		currentReleases = new ArrayList<String>();
		currentReleases.add("\"v1.0.4\"");
		currentReleases.add("\"v1.0.2\"");
	}
	
	public boolean checkForUpdates() throws Exception {
		ArrayList<String> releases = new ArrayList<String>();
		
		//Create HttpURLConnection 
		HttpURLConnection httpcon = (HttpURLConnection) new URL("https://api.github.com/repos/narlock/Kaizen/releases").openConnection();
		httpcon.addRequestProperty("User-Agent", "Mozilla/5.0");
		BufferedReader in = new BufferedReader(new InputStreamReader(httpcon.getInputStream()));
				
		//Read line by line
		StringBuilder responseSB = new StringBuilder();
		String line;
		while ( ( line = in.readLine() ) != null) {
			responseSB.append("\n" + line);
			//System.out.println(line);
		}
		in.close();
		
		Arrays.stream(responseSB.toString().split("\"tag_name\":")).skip(1).map(l -> l.split(",")[0]).forEach(l -> releases.add(l));
		
		if(releases.equals(currentReleases)) {
			return false;
		} else {
			return true;
		}
	}
}
