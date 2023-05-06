package com.narlock.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.narlock.domain.Habit;
import com.narlock.json.HabitJsonManager;
import com.narlock.util.Constants;
import com.narlock.util.ErrorPane;
import com.narlock.util.JTextFieldLimit;

public class UpdateHabitsPanel extends JPanel {
	private static final long serialVersionUID = -748462514207050523L;
	private List<Habit> habits;
	GridBagConstraints gbc = new GridBagConstraints();

	public UpdateHabitsPanel(List<Habit> habits) {
		this.habits = habits;
		
		this.setLayout(new GridBagLayout());
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        
        createUpdateHabitPanels();
        
		this.setBackground(Constants.GUI_BACKGROUND_COLOR);
	}
	
	private void createUpdateHabitPanels() {
		if(habits == null) {
			habits = new ArrayList<Habit>();
		}
		for(Habit habit : habits) {
			this.add(createUpdatePanelFromHabit(habit), gbc);
		}
	}
	
	private JPanel createUpdatePanelFromHabit(Habit habit) {
		/**
		 * UPDATE / DELETE HABIT COMPONENT INITIALIZATION
		 */
		
		//Create panel
		JPanel habitMainPanel = new JPanel();
		habitMainPanel.setLayout(new BorderLayout());
		habitMainPanel.setPreferredSize(Constants.HABIT_MAIN_PANEL_DIMENSION_LARGE);
		habitMainPanel.setOpaque(true);
		habitMainPanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		habitMainPanel.setBorder(Constants.COMPONENT_BORDER_NORMAL);
		
		//Update Button
		JPanel updateHabitPanel = new JPanel();
		updateHabitPanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		JButton updateHabitButton = new JButton("Update");
		updateHabitButton.setOpaque(true);
		updateHabitButton.setForeground(Color.WHITE);
		updateHabitButton.setFont(Constants.COMPONENT_FONT_NORMAL_BOLD);
		updateHabitButton.setBackground(Constants.BUTTON_DEFAULT_COLOR);
		updateHabitButton.setBorder(Constants.BUTTON_BORDER);
		updateHabitButton.setPreferredSize(Constants.HABIT_UPDATE_BUTTON_SIZE);
		updateHabitPanel.add(updateHabitButton);
		habitMainPanel.add(updateHabitPanel, BorderLayout.WEST);
		
		//Title of Habit
		JPanel habitTitlePanel = new JPanel();
		habitTitlePanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		JLabel habitTitle = new JLabel(habit.getTitle());
		habitTitle.setFont(Constants.COMPONENT_FONT_NORMAL_BOLD);
		habitTitle.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		habitTitlePanel.add(habitTitle);
		habitMainPanel.add(habitTitlePanel, BorderLayout.CENTER);
		
		//Delete Button
		JPanel deleteHabitPanel = new JPanel();
		deleteHabitPanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		JButton deleteHabitButton = new JButton("Delete");
		deleteHabitButton.setOpaque(true);
		deleteHabitButton.setForeground(Color.WHITE);
		deleteHabitButton.setFont(Constants.COMPONENT_FONT_NORMAL_BOLD);
		deleteHabitButton.setBackground(Constants.BUTTON_ALERT_COLOR);
		deleteHabitButton.setBorder(Constants.BUTTON_BORDER);
		deleteHabitButton.setPreferredSize(Constants.HABIT_UPDATE_BUTTON_SIZE);
		deleteHabitPanel.add(deleteHabitButton);
		habitMainPanel.add(deleteHabitPanel, BorderLayout.EAST);
		
		//Save Button
		JPanel saveHabitPanel = new JPanel();
		saveHabitPanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		JButton saveHabitButton = new JButton("Save");
		saveHabitButton.setOpaque(true);
		saveHabitButton.setForeground(Color.WHITE);
		saveHabitButton.setFont(Constants.COMPONENT_FONT_NORMAL_BOLD);
		saveHabitButton.setBackground(Constants.BUTTON_CONFIRM_COLOR);
		saveHabitButton.setBorder(Constants.BUTTON_BORDER);
		saveHabitButton.setPreferredSize(Constants.HABIT_UPDATE_BUTTON_SIZE);
		saveHabitPanel.add(saveHabitButton);
		
		//Habit Title Field (Update Mode)
		JPanel habitNamePanel = new JPanel();
		habitNamePanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		JTextField habitNameTextField = new JTextField(10);
		habitNameTextField.setDocument(new JTextFieldLimit(25));
		habitNamePanel.add(habitNameTextField);
		
		//Occurrence Boxes
		JPanel occurrencePanel = new JPanel();
		occurrencePanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		JCheckBox mondayCheckBox = new JCheckBox("MON");
		mondayCheckBox.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		mondayCheckBox.setFont(Constants.COMPONENT_FONT_SMALL_BOLD);
		occurrencePanel.add(mondayCheckBox);
		JCheckBox tuesdayCheckBox = new JCheckBox("TUE");
		tuesdayCheckBox.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		tuesdayCheckBox.setFont(Constants.COMPONENT_FONT_SMALL_BOLD);
		occurrencePanel.add(tuesdayCheckBox);
		JCheckBox wednesdayCheckBox = new JCheckBox("WED");
		wednesdayCheckBox.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		wednesdayCheckBox.setFont(Constants.COMPONENT_FONT_SMALL_BOLD);
		occurrencePanel.add(wednesdayCheckBox);
		JCheckBox thursdayCheckBox = new JCheckBox("THU");
		thursdayCheckBox.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		thursdayCheckBox.setFont(Constants.COMPONENT_FONT_SMALL_BOLD);
		occurrencePanel.add(thursdayCheckBox);
		JCheckBox fridayCheckBox = new JCheckBox("FRI");
		fridayCheckBox.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		fridayCheckBox.setFont(Constants.COMPONENT_FONT_SMALL_BOLD);
		occurrencePanel.add(fridayCheckBox);
		JCheckBox saturdayCheckBox = new JCheckBox("SAT");
		saturdayCheckBox.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		saturdayCheckBox.setFont(Constants.COMPONENT_FONT_SMALL_BOLD);
		occurrencePanel.add(saturdayCheckBox);
		JCheckBox sundayCheckBox = new JCheckBox("SUN");
		sundayCheckBox.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		sundayCheckBox.setFont(Constants.COMPONENT_FONT_SMALL_BOLD);
		occurrencePanel.add(sundayCheckBox);
		
		/*
		 * UPDATE / DELETE HABIT ACTIONS
		 */
		
		deleteHabitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//Ask the user if they are sure they want to delete the habit
				int confirm = JOptionPane.showConfirmDialog(
						getThis().getParent().getParent(),
						"Are you sure you want to delete " + habit.getTitle() + "?", 
						"Confirm Delete Habit", 
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						new ImageIcon(ErrorPane.class.getClassLoader().getResource("INFO_ERROR.png"))
					);
				if(confirm == JOptionPane.YES_OPTION) {
					//Remove Habit from list and update
					habits.remove(habit);
					HabitJsonManager.writeHabitJsonToFile(habits);
					
					//Remove the Habit Panel
					getThis().remove(habitMainPanel);
					getThis().revalidate();
				}
			}
			
		});
		
		updateHabitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//Enter Update Habit Mode
				habitMainPanel.remove(deleteHabitPanel);
				habitMainPanel.remove(updateHabitPanel);
				habitMainPanel.remove(habitTitlePanel);
				habitMainPanel.revalidate();
				habitMainPanel.repaint();
				habitMainPanel.add(saveHabitPanel, BorderLayout.WEST);
				habitMainPanel.add(habitNamePanel, BorderLayout.EAST);
				
				//Set the components values
				habitNameTextField.setText(habit.getTitle());
				habitMainPanel.add(occurrencePanel);
				if(habit.getOccurrence().contains("2"))
					mondayCheckBox.setSelected(true);
				if(habit.getOccurrence().contains("3"))
					tuesdayCheckBox.setSelected(true);
				if(habit.getOccurrence().contains("4"))
					wednesdayCheckBox.setSelected(true);
				if(habit.getOccurrence().contains("5"))
					thursdayCheckBox.setSelected(true);
				if(habit.getOccurrence().contains("6"))
					fridayCheckBox.setSelected(true);
				if(habit.getOccurrence().contains("7"))
					saturdayCheckBox.setSelected(true);
				if(habit.getOccurrence().contains("1"))
					sundayCheckBox.setSelected(true);
				
				habitMainPanel.revalidate();
				habitMainPanel.repaint();
			}
			
		});
		
		saveHabitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//Exit Update Habit Mode
				
				//Get Information for Update
				habit.setTitle(habitNameTextField.getText());
				String occurrenceString = "";
				if(sundayCheckBox.isSelected())
					occurrenceString += "1";
				if(mondayCheckBox.isSelected())
					occurrenceString += "2";
				if(tuesdayCheckBox.isSelected())
					occurrenceString += "3";
				if(wednesdayCheckBox.isSelected())
					occurrenceString += "4";
				if(thursdayCheckBox.isSelected())
					occurrenceString += "5";
				if(fridayCheckBox.isSelected())
					occurrenceString += "6";
				if(saturdayCheckBox.isSelected())
					occurrenceString += "7";
				
				//If the occurrence changes, we need to reset habit
				if(habit.getOccurrence().equals(occurrenceString)) {
					habit.setStreak(0);
					habit.setStatus(0);
					habit.setDate(new Date());
				}
				habit.setOccurrence(occurrenceString);
				
				//Rewrite habit information
				habitTitle.setText(habit.getTitle());
				HabitJsonManager.writeHabitJsonToFile(habits);
				
				//Update Interface
				habitMainPanel.remove(saveHabitPanel);
				habitMainPanel.remove(habitNamePanel);
				habitMainPanel.remove(occurrencePanel);
				habitMainPanel.revalidate();
				habitMainPanel.repaint();
				habitMainPanel.add(deleteHabitPanel, BorderLayout.EAST);
				habitMainPanel.add(updateHabitPanel, BorderLayout.WEST);
				habitMainPanel.add(habitTitlePanel, BorderLayout.CENTER);
				habitMainPanel.revalidate();
				habitMainPanel.repaint();
			}
			
		});
		
		return habitMainPanel;
	}
	
	public UpdateHabitsPanel getThis() {
		return this;
	}
}
