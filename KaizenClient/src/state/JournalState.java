package state;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import domain.Journal;
import util.Constants;
import util.ErrorPane;
import util.Utils;

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
	private JPanel howWasDayPanel;
	private JLabel howWasDayLabel;
	private JPanel howWasDayButtonPanel;
	private JRadioButton dayBadButton;
	private JRadioButton dayMehButton;
	private JRadioButton dayNeutralButton;
	private JRadioButton dayGoodButton;
	private JRadioButton dayGreatButton;
	
	private JPanel text1Panel;
	private JLabel text1Label;
	private JTextArea text1Area;
	private JPanel text2Panel;
	private JLabel text2Label;
	private JTextArea text2Area;
	
	private JPanel rightJournalPanel; //This will have a layout to support text3 and text4
	private JPanel text3Panel;
	private JLabel text3Label;
	private JTextArea text3Area;
	private JPanel text4Panel;
	private JLabel text4Label;
	private JTextArea text4Area;
	
	private JPanel saveChangesButtonPanel;
	private JButton saveChangesButton;

	@Override
	public void initPanelComponents() {
		
		this.gbc = new GridBagConstraints();
		this.gbc.gridwidth = GridBagConstraints.REMAINDER;  
		
		//TODO Read in the file, populate attributes
		//journal = JournalJsonManager.readJson();
		
		//Title Panel
		titlePanel = new JPanel();
		titlePanel.setLayout(new GridBagLayout());
		titlePanel.setBackground(Constants.GUI_BACKGROUND_COLOR);
		titleLabel = new JLabel("Journal • " + Utils.todayAsString());
		titleLabel.setFont(Constants.COMPONENT_FONT_NORMAL);
		titleLabel.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		titlePanel.add(titleLabel, gbc);
		changeEntryPanel = new JPanel();
		changeEntryPanel.setBackground(Constants.GUI_BACKGROUND_COLOR);
		previousEntryButton = new JButton("Previous Entry");
		previousEntryButton.setOpaque(true);
		previousEntryButton.setFont(Constants.COMPONENT_FONT_SMALL);
		previousEntryButton.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		previousEntryButton.setBackground(Constants.BUTTON_DEFAULT_COLOR);
		previousEntryButton.setBorder(Constants.COMPONENT_BORDER_NORMAL);
		followingEntryButton = new JButton("Following Entry");
		followingEntryButton.setOpaque(true);
		followingEntryButton.setFont(Constants.COMPONENT_FONT_SMALL);
		followingEntryButton.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		followingEntryButton.setBackground(Constants.BUTTON_DEFAULT_COLOR);
		followingEntryButton.setBorder(Constants.COMPONENT_BORDER_NORMAL);
		changeEntryPanel.add(previousEntryButton);
		changeEntryPanel.add(followingEntryButton);
		titlePanel.add(changeEntryPanel, gbc);
		
		//Journal Panel
		journalPanel = new JPanel();
		journalPanel.setLayout(new BorderLayout());
//		journalPanel.setLayout(new GridLayout(1, 2));
		journalPanel.setPreferredSize(new Dimension(820, 500));
		journalPanel.setBackground(Constants.GUI_BACKGROUND_COLOR);
		
		//Left Journal Panel
		leftJournalPanel = new JPanel();
		leftJournalPanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		
		howWasDayPanel = new JPanel();
		howWasDayPanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		howWasDayLabel = new JLabel("How was your day?");
		howWasDayLabel.setFont(Constants.COMPONENT_FONT_SMALL);
		howWasDayLabel.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		howWasDayPanel.add(howWasDayLabel);
		
		howWasDayButtonPanel = new JPanel();
		howWasDayButtonPanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		dayBadButton = new JRadioButton(new ImageIcon(getClass().getClassLoader().getResource("JOURNAL_BAD.png")));
		dayMehButton = new JRadioButton(new ImageIcon(getClass().getClassLoader().getResource("JOURNAL_MEH.png")));
		dayNeutralButton = new JRadioButton(new ImageIcon(getClass().getClassLoader().getResource("JOURNAL_NEUTRAL.png")));
		dayGoodButton = new JRadioButton(new ImageIcon(getClass().getClassLoader().getResource("JOURNAL_GOOD.png")));
		dayGreatButton = new JRadioButton(new ImageIcon(getClass().getClassLoader().getResource("JOURNAL_GREAT.png")));
		howWasDayButtonPanel.add(dayBadButton);
		howWasDayButtonPanel.add(dayMehButton);
		howWasDayButtonPanel.add(dayNeutralButton);
		howWasDayButtonPanel.add(dayGoodButton);
		howWasDayButtonPanel.add(dayGreatButton);
		
		leftJournalPanel.add(howWasDayPanel);
		leftJournalPanel.add(howWasDayButtonPanel);
		
		text1Panel = new JPanel();
		text1Panel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		text1Panel.setLayout(new GridBagLayout());
		text1Label = new JLabel("What events occurred today?");
		text1Label.setFont(Constants.COMPONENT_FONT_SMALL);
		text1Label.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		text1Area = new JTextArea(9, 30);
		JScrollPane scroll1Pane = new JScrollPane(text1Area);
		scroll1Pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		text1Area.setLineWrap(true);
		text1Panel.add(text1Label, gbc);
		text1Panel.add(scroll1Pane, gbc);
		
		text2Panel = new JPanel();
		text2Panel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		text2Panel.setLayout(new GridBagLayout());
		text2Label = new JLabel("Any problems or stresses today?");
		text2Label.setFont(Constants.COMPONENT_FONT_SMALL);
		text2Label.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		text2Area = new JTextArea(9, 30);
		JScrollPane scroll2Pane = new JScrollPane(text2Area);
		scroll2Pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		text2Area.setLineWrap(true);
		text2Panel.add(text2Label, gbc);
		text2Panel.add(scroll2Pane, gbc);
		
		leftJournalPanel.setBorder(Constants.COMPONENT_BORDER_NORMAL_RECTANGULAR);
		leftJournalPanel.add(text1Panel);
		leftJournalPanel.add(text2Panel);
		
		//Right Journal Panel
		rightJournalPanel = new JPanel();
		rightJournalPanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		
		text3Panel = new JPanel();
		text3Panel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		text3Panel.setLayout(new GridBagLayout());
		text3Label = new JLabel("What are you grateful for today?");
		text3Label.setFont(Constants.COMPONENT_FONT_SMALL);
		text3Label.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		text3Area = new JTextArea(9, 30);
		JScrollPane scroll3Pane = new JScrollPane(text3Area);
		scroll3Pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		text3Area.setLineWrap(true);
		text3Panel.add(text3Label, gbc);
		text3Panel.add(scroll3Pane, gbc);
		
		text4Panel = new JPanel();
		text4Panel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		text4Panel.setLayout(new GridBagLayout());
		text4Label = new JLabel("What are your goals for tomorrow?");
		text4Label.setFont(Constants.COMPONENT_FONT_SMALL);
		text4Label.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		text4Area = new JTextArea(9, 30);
		JScrollPane scroll4Pane = new JScrollPane(text4Area);
		scroll4Pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		text4Panel.add(text4Label, gbc);
		text4Panel.add(scroll4Pane, gbc);
		
		saveChangesButtonPanel = new JPanel();
		saveChangesButtonPanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		saveChangesButton = new JButton("Save Entry");
		saveChangesButton.setOpaque(true);
		saveChangesButton.setBackground(Constants.BUTTON_CONFIRM_COLOR);
		saveChangesButton.setBorder(Constants.COMPONENT_BORDER_SMALL);
		saveChangesButton.setFont(Constants.COMPONENT_FONT_NORMAL);
		saveChangesButtonPanel.add(saveChangesButton);
		
		rightJournalPanel.setBorder(Constants.COMPONENT_BORDER_NORMAL_RECTANGULAR);
		rightJournalPanel.add(text3Panel);
		rightJournalPanel.add(text4Panel);
		rightJournalPanel.add(saveChangesButtonPanel);
		
		journalPanel.setLayout(new GridLayout(1, 2));
		journalPanel.add(leftJournalPanel);
		journalPanel.add(rightJournalPanel);
	}

	@Override
	public void initPanelComponentActions() {
		dayBadButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(dayBadButton.isSelected()) {
					dayBadButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("JOURNAL_BAD_SELECTED.png")));
					dayMehButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("JOURNAL_MEH.png")));
					dayNeutralButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("JOURNAL_NEUTRAL.png")));
					dayGoodButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("JOURNAL_GOOD.png")));
					dayGreatButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("JOURNAL_GREAT.png")));
				} else {
					dayBadButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("JOURNAL_BAD.png")));
				}
			}
			
		});
		
		dayMehButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(dayMehButton.isSelected()) {
					dayBadButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("JOURNAL_BAD.png")));
					dayMehButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("JOURNAL_MEH_SELECTED.png")));
					dayNeutralButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("JOURNAL_NEUTRAL.png")));
					dayGoodButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("JOURNAL_GOOD.png")));
					dayGreatButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("JOURNAL_GREAT.png")));
				} else {
					dayMehButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("JOURNAL_MEH.png")));
				}
			}
			
		});
		
		//TODO Add remaining actions for the radio button
	}

	@Override
	public void initPanel() {
		this.setLayout(new GridBagLayout()); 
        
        this.add(titlePanel, gbc);
        this.add(Box.createVerticalStrut(10), gbc);
		this.add(journalPanel, gbc);
	}

}
