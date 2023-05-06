package com.narlock.widget;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.narlock.domain.Journal;
import com.narlock.domain.JournalEntry;
import com.narlock.domain.Settings;
import com.narlock.json.JournalJsonManager;
import com.narlock.util.Constants;
import com.narlock.util.Utils;

/**
 * JournalWidgetPanel
 * @author narlock
 * 
 * Since developing an entire small version of the journal is not
 * a good idea, the journal widget will allow for a selected text area to
 * be edited from today's entry and added to the journal. For example,
 * the user can only see the "What events occurred today?" question
 * and can answer that one as a widget. The user can choose which
 * question is available.
 * 
 * TODO Make a previous/following button-like thing in the title of
 * this widget that can allow the user to cycle through the different
 * questions.
 *
 */
public class JournalWidgetPanel extends JPanel {

	private static final long serialVersionUID = 3138144527935439794L;
	private GridBagConstraints gbc;

	private Journal journal;
	private JournalEntry todaysEntry;
	private boolean loadToday;
	
	private JPanel textPanel;
	private JLabel textLabel;
	private JTextArea textArea;
	private int index;
	
	private JPanel saveChangesButtonPanel;
	private JButton saveChangesButton;
	
	public JournalWidgetPanel(int index, Settings settings) {
		this.setBackground(Constants.GUI_BACKGROUND_COLOR);
		this.gbc = new GridBagConstraints();
		this.gbc.gridwidth = GridBagConstraints.REMAINDER;  
		this.index = index;
		
		this.journal = JournalJsonManager.readJson();
		if(journal.isEntryInEntries(Utils.today())) {
			todaysEntry = journal.getEntryByDateString(Utils.todayAsString());
			loadToday = true;
		} else {
			todaysEntry = new JournalEntry();
			loadToday = false;
		}
		
		textPanel = new JPanel();
		textPanel.setLayout(new GridBagLayout());
		textPanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		textPanel.setBorder(Constants.COMPONENT_BORDER_SMALL);
		textLabel = new JLabel(getQuestionFromIndex(settings));
		textLabel.setFont(Constants.COMPONENT_FONT_SMALL_BOLD);
		textLabel.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		textArea = new JTextArea(9, 30);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textArea.setLineWrap(true);
		textPanel.add(textLabel, gbc);
		textPanel.add(Box.createVerticalStrut(10), gbc);
		textPanel.add(scrollPane, gbc);
		textPanel.add(Box.createVerticalStrut(10), gbc);
		
		saveChangesButtonPanel = new JPanel();
		saveChangesButtonPanel.setBackground(Constants.GUI_BACKGROUND_COLOR);
		saveChangesButton = new JButton("Save Entry");
		saveChangesButton.setOpaque(true);
		saveChangesButton.setForeground(Color.WHITE);
		saveChangesButton.setBackground(Constants.BUTTON_CONFIRM_COLOR);
		saveChangesButton.setBorder(Constants.BUTTON_BORDER);
		saveChangesButton.setFont(Constants.COMPONENT_FONT_NORMAL_BOLD);
		saveChangesButton.setPreferredSize(new Dimension(120, 40));
		
		saveChangesButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switch(index) {
					case 1:
						todaysEntry.setText1(textArea.getText());
						break;
					case 2:
						todaysEntry.setText2(textArea.getText());
						break;
					case 3:
						todaysEntry.setText3(textArea.getText());
						break;
					case 4:
						todaysEntry.setText4(textArea.getText());
						break;
					default:
						throw new RuntimeException("Invalid textIndex entered: " + index);
				}
				
				if(journal.isEntryInEntries(todaysEntry.getDate())) {
					//Update Entry
					JournalEntry entry = journal.getEntryByDateString(Utils.dateAsString(todaysEntry.getDate()));
					entry.setText1(todaysEntry.getText1());
					entry.setText2(todaysEntry.getText2());
					entry.setText3(todaysEntry.getText3());
					entry.setText4(todaysEntry.getText4());
					JournalJsonManager.writeJournalJsonToFile(journal);
				} else {
					//Write Entry to File
					journal.getEntries().add(todaysEntry);
					JournalJsonManager.writeJournalJsonToFile(journal);
				}
			}
			
		});
		
		saveChangesButtonPanel.add(saveChangesButton);
		
		this.setLayout(new GridBagLayout());
		this.add(textPanel, gbc);
		this.add(saveChangesButtonPanel);
		
		if(loadToday) {
			populateComponentsWithOpenEntryDetails();
		}
	}
	
	private String getQuestionFromIndex(Settings settings) {
		switch(index) {
			case 1:
				return settings.getJournalText1AreaPrompt();
			case 2:
				return settings.getJournalText2AreaPrompt();
			case 3:
				return settings.getJournalText3AreaPrompt();
			case 4:
				return settings.getJournalText4AreaPrompt();
			default:
				throw new RuntimeException("Invalid textIndex entered: " + index);
		}
	}
	
	private void populateComponentsWithOpenEntryDetails() {
		switch(index) {
			case 1:
				textArea.setText(todaysEntry.getText1());
				return;
			case 2:
				textArea.setText(todaysEntry.getText2());
				return;
			case 3:
				textArea.setText(todaysEntry.getText3());
				return;
			case 4:
				textArea.setText(todaysEntry.getText4());
				return;
			default:
				throw new RuntimeException("Invalid textIndex entered: " + index);
		}
	}
}
