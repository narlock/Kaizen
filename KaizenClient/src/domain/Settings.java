package domain;

public class Settings {
	
	// General Settings
	
	private boolean enableDiscordRPC;
	
	// Todo Settings
	
	private boolean showTodoEpicOnClipboard;
	
	// Habit Settings
	
	private boolean showStreakOnClipboard;
	
	// Anti Habit Settings
	
	// Journal Settings
	
	private long journalMode;
	
	private boolean showHowWasDay;
	
	private String journalText1AreaPrompt;
	
	private String journalText2AreaPrompt;
	
	private String journalText3AreaPrompt;
	
	private String journalText4AreaPrompt;
	
	public Settings() {
		this.enableDiscordRPC = true;
		this.showTodoEpicOnClipboard = true;
		this.showStreakOnClipboard = true;
		this.journalMode = 0;
		this.showHowWasDay = true;
		this.journalText1AreaPrompt = "What events occurred today?";
		this.journalText2AreaPrompt = "Any problems or stresses today?";
		this.journalText3AreaPrompt = "What are you grateful for today?";
		this.journalText4AreaPrompt = "What are your goals for tomorrow?";
	}

	public Settings(boolean enableDiscordRPC, boolean showTodoEpicOnClipboard, boolean showStreakOnClipboard, long journalMode,
			boolean showHowWasDay, String journalText1AreaPrompt, String journalText2AreaPrompt, String journalText3AreaPrompt,
			String journalText4AreaPrompt) {
		super();
		this.enableDiscordRPC = enableDiscordRPC;
		this.showTodoEpicOnClipboard = showTodoEpicOnClipboard;
		this.journalMode = journalMode;
		this.showHowWasDay = showHowWasDay;
		this.journalText1AreaPrompt = journalText1AreaPrompt;
		this.journalText2AreaPrompt = journalText2AreaPrompt;
		this.journalText3AreaPrompt = journalText3AreaPrompt;
		this.journalText4AreaPrompt = journalText4AreaPrompt;
	}

	public boolean isEnableDiscordRPC() {
		return enableDiscordRPC;
	}

	public void setEnableDiscordRPC(boolean enableDiscordRPC) {
		this.enableDiscordRPC = enableDiscordRPC;
	}

	public boolean isShowTodoEpicOnClipboard() {
		return showTodoEpicOnClipboard;
	}

	public void setShowTodoEpicOnClipboard(boolean showTodoEpicOnClipboard) {
		this.showTodoEpicOnClipboard = showTodoEpicOnClipboard;
	}

	public long getJournalMode() {
		return journalMode;
	}

	public void setJournalMode(long journalMode) {
		this.journalMode = journalMode;
	}

	public String getJournalText1AreaPrompt() {
		return journalText1AreaPrompt;
	}

	public void setJournalText1AreaPrompt(String journalText1AreaPrompt) {
		this.journalText1AreaPrompt = journalText1AreaPrompt;
	}

	public String getJournalText2AreaPrompt() {
		return journalText2AreaPrompt;
	}

	public void setJournalText2AreaPrompt(String journalText2AreaPrompt) {
		this.journalText2AreaPrompt = journalText2AreaPrompt;
	}

	public String getJournalText3AreaPrompt() {
		return journalText3AreaPrompt;
	}

	public void setJournalText3AreaPrompt(String journalText3AreaPrompt) {
		this.journalText3AreaPrompt = journalText3AreaPrompt;
	}

	public String getJournalText4AreaPrompt() {
		return journalText4AreaPrompt;
	}

	public void setJournalText4AreaPrompt(String journalText4AreaPrompt) {
		this.journalText4AreaPrompt = journalText4AreaPrompt;
	}

	public boolean isShowStreakOnClipboard() {
		return showStreakOnClipboard;
	}

	public void setShowStreakOnClipboard(boolean showStreakOnClipboard) {
		this.showStreakOnClipboard = showStreakOnClipboard;
	}

	public boolean isShowHowWasDay() {
		return showHowWasDay;
	}

	public void setShowHowWasDay(boolean showHowWasDay) {
		this.showHowWasDay = showHowWasDay;
	}
	
	
}
