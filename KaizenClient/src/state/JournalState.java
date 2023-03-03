package state;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import domain.Journal;

public class JournalState extends State {

	private static final long serialVersionUID = -7551488559014363160L;
	private GridBagConstraints gbc;
	
	private Journal journal;
	
	private JPanel titlePanel;
	private JLabel titleLabel;
	private JPanel changeEntryPanel;
	private JButton previousEntryButton; //To see past entry
	private JButton followingEntryButton; //A go back entry button, disabled if on today's/new entry
	
	private JPanel journalPanel; //Will be separated into two sections down the middle, like Web
	
	private JPanel leftJournalPanel; //This will have a layout to support howWasDay, text1, and text2
	private JPanel howWasDayButtonPanel;
	private JRadioButton dayBadButton;
	private JRadioButton dayMehButton;
	private JRadioButton dayNeutralButton;
	private JRadioButton dayGoodButton;
	private JRadioButton dayGreatButton;
	
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
		
		this.gbc = new GridBagConstraints();
		this.gbc.gridwidth = GridBagConstraints.REMAINDER;  
		
		//TODO Read in the file, populate attributes
		//journal = JournalJsonManager.readJson();
		
		
		titlePanel = new JPanel();
		titlePanel.setLayout(new GridBagLayout());
		titleLabel = new JLabel("Journal");
		titlePanel.add(titleLabel, gbc);
		changeEntryPanel = new JPanel();
		previousEntryButton = new JButton("Previous Entry");
		followingEntryButton = new JButton("Following Entry");
		changeEntryPanel.add(previousEntryButton);
		changeEntryPanel.add(followingEntryButton);
		titlePanel.add(changeEntryPanel, gbc);
		
		journalPanel = new JPanel();
		journalPanel.setLayout(new GridLayout(2, 1));
		//journalPanel.setPreferredSize();
		
		leftJournalPanel = new JPanel();
		howWasDayButtonPanel = new JPanel();
		dayBadButton = new JRadioButton(new ImageIcon("TODO"));
		dayMehButton = new JRadioButton(new ImageIcon("TODO"));
		dayNeutralButton = new JRadioButton(new ImageIcon("TODO"));
		dayGoodButton = new JRadioButton(new ImageIcon("TODO"));
		dayGreatButton = new JRadioButton(new ImageIcon("TODO"));
	}

	@Override
	public void initPanelComponentActions() {
		
	}

	@Override
	public void initPanel() {
		this.setLayout(new GridBagLayout()); 
        
        this.add(titlePanel, gbc);
        this.add(Box.createVerticalStrut(10), gbc);
		this.add(journalPanel, gbc);
	}

}
