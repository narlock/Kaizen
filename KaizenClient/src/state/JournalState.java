package state;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import domain.Journal;

public class JournalState extends State {

	private static final long serialVersionUID = -7551488559014363160L;
	
	private Journal journal;
	
	private JPanel titlePanel;
	private JLabel titleLabel;
	private JButton previousEntryButton; //To see past entry
	private JButton forwardEntryButton; //A go back entry button, disabled if on today's/new entry
	
	private JPanel journalPanel; //Will be separated into two sections down the middle, like Web
	
	private JPanel leftJournalPanel; //This will have a layout to support howWasDay, text1, and text2
	private JPanel howWasDayButtonPanel;
	private JButton dayBadButton;
	private JButton dayMehButton;
	private JButton dayNeutralButton;
	private JButton dayGoodButton;
	private JButton dayGreatButton;
	
	private JPanel text1Panel;
	private JTextArea text1Area;
	private JPanel text2Panel;
	private JTextArea text2Area;
	
	private JPanel rightJournalPanel; //This will have a layout to support text3 and text4
	private JPanel text3Panel;
	private JTextArea text3Area;
	private JPanel text4Panel;
	private JTextArea text4Area;

	@Override
	public void initPanelComponents() {
		//Read in the file, populate attributes
		//journal = JournalJsonManager.readJson();
		
	}

	@Override
	public void initPanelComponentActions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initPanel() {
		// TODO Auto-generated method stub
		
	}

}
