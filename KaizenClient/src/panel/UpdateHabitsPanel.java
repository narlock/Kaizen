package panel;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.Habit;
import util.Constants;
import util.ErrorPane;
import util.JTextFieldLimit;

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
		updateHabitButton.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		updateHabitButton.setFont(Constants.COMPONENT_FONT_NORMAL);
		updateHabitButton.setBackground(Constants.BUTTON_DEFAULT_COLOR);
		updateHabitButton.setBorder(Constants.COMPONENT_BORDER_NORMAL);
		updateHabitPanel.add(updateHabitButton);
		habitMainPanel.add(updateHabitPanel, BorderLayout.WEST);
		
		//Title of Habit
		JPanel habitTitlePanel = new JPanel();
		habitTitlePanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		JLabel habitTitle = new JLabel(habit.getTitle());
		habitTitle.setFont(Constants.COMPONENT_FONT_NORMAL);
		habitTitle.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		habitTitlePanel.add(habitTitle);
		habitMainPanel.add(habitTitlePanel, BorderLayout.CENTER);
		
		//Delete Button
		JPanel deleteHabitPanel = new JPanel();
		deleteHabitPanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		JButton deleteHabitButton = new JButton("Delete");
		deleteHabitButton.setOpaque(true);
		deleteHabitButton.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		deleteHabitButton.setFont(Constants.COMPONENT_FONT_NORMAL);
		deleteHabitButton.setBackground(Constants.BUTTON_ALERT_COLOR);
		deleteHabitButton.setBorder(Constants.COMPONENT_BORDER_NORMAL);
		deleteHabitPanel.add(deleteHabitButton);
		habitMainPanel.add(deleteHabitPanel, BorderLayout.EAST);
		
		//Save Button
		JPanel saveHabitPanel = new JPanel();
		saveHabitPanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		JButton saveHabitButton = new JButton("Save");
		saveHabitButton.setOpaque(true);
		saveHabitButton.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		saveHabitButton.setFont(Constants.COMPONENT_FONT_NORMAL);
		saveHabitButton.setBackground(Constants.BUTTON_CONFIRM_COLOR);
		saveHabitButton.setBorder(Constants.COMPONENT_BORDER_NORMAL);
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
		mondayCheckBox.setFont(Constants.COMPONENT_FONT_SMALL);
		occurrencePanel.add(mondayCheckBox);
		JCheckBox tuesdayCheckBox = new JCheckBox("TUE");
		tuesdayCheckBox.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		tuesdayCheckBox.setFont(Constants.COMPONENT_FONT_SMALL);
		occurrencePanel.add(tuesdayCheckBox);
		JCheckBox wednesdayCheckBox = new JCheckBox("WED");
		wednesdayCheckBox.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		wednesdayCheckBox.setFont(Constants.COMPONENT_FONT_SMALL);
		occurrencePanel.add(wednesdayCheckBox);
		JCheckBox thursdayCheckBox = new JCheckBox("THU");
		thursdayCheckBox.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		thursdayCheckBox.setFont(Constants.COMPONENT_FONT_SMALL);
		occurrencePanel.add(thursdayCheckBox);
		JCheckBox fridayCheckBox = new JCheckBox("FRI");
		fridayCheckBox.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		fridayCheckBox.setFont(Constants.COMPONENT_FONT_SMALL);
		occurrencePanel.add(fridayCheckBox);
		JCheckBox saturdayCheckBox = new JCheckBox("SAT");
		saturdayCheckBox.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		saturdayCheckBox.setFont(Constants.COMPONENT_FONT_SMALL);
		occurrencePanel.add(saturdayCheckBox);
		JCheckBox sundayCheckBox = new JCheckBox("SUN");
		sundayCheckBox.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		sundayCheckBox.setFont(Constants.COMPONENT_FONT_SMALL);
		occurrencePanel.add(sundayCheckBox);
		
		//Add update button, will open Jtextfield, replaces
		//delete button with save button, save button will update
		//the habit object and then call writeHabitJsonToFile and
		//take in the habits member attribute from here
		
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
				habitMainPanel.add(occurrencePanel);
				habitNameTextField.setText(habit.getTitle());
				habitMainPanel.revalidate();
				habitMainPanel.repaint();
			}
			
		});
		
		saveHabitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//Exit Update Habit Mode
				
				//TODO Update habit object, write habits to JSON
				
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
