package state;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.Habit;
import json.HabitJsonManager;
import util.Constants;
import util.Debug;
import util.ErrorPane;
import util.FadingLabel;
import util.JTextFieldLimit;

public class CreateHabitState extends State {

	private static final long serialVersionUID = 1823884086458179558L;
	private final Debug debug = new Debug(true);
	private GridBagConstraints gbc;
	
	private JPanel messagePanel;
	private FadingLabel messagePanelLabel;
	
	private JPanel titlePanel;
	private JLabel titleLabel;
	
	private JPanel formPanel;
	
	private JPanel habitNamePanel;
	private JLabel habitNameLabel;
	private JTextField habitNameTextField;
	
	private JLabel occurrenceLabel;
	private JCheckBox mondayCheckBox;
	private JCheckBox tuesdayCheckBox;
	private JCheckBox wednesdayCheckBox;
	private JCheckBox thursdayCheckBox;
	private JCheckBox fridayCheckBox;
	private JCheckBox saturdayCheckBox;
	private JCheckBox sundayCheckBox;
	
	private JPanel confirmPanel;
	private JButton addHabitButton;

	@Override
	public void initPanelComponents() {
		gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;  
		
		messagePanel = new JPanel();
		messagePanel.setBackground(Constants.GUI_BACKGROUND_COLOR);
		messagePanelLabel = new FadingLabel("Habit saved successfully!", 2000);
		messagePanelLabel.setFont(Constants.COMPONENT_FONT_NORMAL_BOLD);
		messagePanelLabel.setForeground(Constants.GUI_BACKGROUND_COLOR);
		messagePanel.add(messagePanelLabel);
		
		titlePanel = new JPanel();
		titlePanel.setBackground(Constants.GUI_BACKGROUND_COLOR);
		titleLabel = new JLabel("What habit would you like to start?");
		setTextComponentVisual(titleLabel);
		titlePanel.add(titleLabel);
		
		formPanel = new JPanel();
		formPanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		formPanel.setBorder(Constants.COMPONENT_BORDER_NORMAL);
		formPanel.setLayout(new GridBagLayout());
		
		habitNamePanel = new JPanel();
		habitNamePanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		habitNameLabel = new JLabel("Name your habit: ");
		setTextComponentVisual(habitNameLabel);
		habitNameTextField = new JTextField(10);
		habitNameTextField.setDocument(new JTextFieldLimit(25));
		habitNamePanel.add(habitNameLabel);
		habitNamePanel.add(habitNameTextField);
		
		occurrenceLabel = new JLabel("I will perform this habit on:");
		setTextComponentVisual(occurrenceLabel);
		mondayCheckBox = new JCheckBox("Monday");
		setTextComponentVisual(mondayCheckBox);
		tuesdayCheckBox = new JCheckBox("Tuesday");
		setTextComponentVisual(tuesdayCheckBox);
		wednesdayCheckBox = new JCheckBox("Wednesday");
		setTextComponentVisual(wednesdayCheckBox);
		thursdayCheckBox = new JCheckBox("Thursday");
		setTextComponentVisual(thursdayCheckBox);
		fridayCheckBox = new JCheckBox("Friday");
		setTextComponentVisual(fridayCheckBox);
		saturdayCheckBox = new JCheckBox("Saturday");
		setTextComponentVisual(saturdayCheckBox);
		sundayCheckBox = new JCheckBox("Sunday");
		setTextComponentVisual(sundayCheckBox);
		
		formPanel.add(habitNamePanel, gbc);
		formPanel.add(occurrenceLabel, gbc);
		formPanel.add(mondayCheckBox, gbc);
		formPanel.add(tuesdayCheckBox, gbc);
		formPanel.add(wednesdayCheckBox, gbc);
		formPanel.add(thursdayCheckBox, gbc);
		formPanel.add(fridayCheckBox, gbc);
		formPanel.add(saturdayCheckBox, gbc);
		formPanel.add(sundayCheckBox, gbc);
		
		confirmPanel = new JPanel();
		confirmPanel.setBackground(Constants.GUI_BACKGROUND_COLOR);
		addHabitButton = new JButton("Add Habit");
		addHabitButton.setOpaque(true);
		addHabitButton.setForeground(Color.WHITE);
		addHabitButton.setFont(Constants.COMPONENT_FONT_NORMAL_BOLD);
		addHabitButton.setBackground(Constants.BUTTON_CONFIRM_COLOR);
		addHabitButton.setBorder(Constants.BUTTON_BORDER);
		addHabitButton.setPreferredSize(new Dimension(120, 40));
		confirmPanel.add(addHabitButton);
	}

	@Override
	public void initPanelComponentActions() {
		addHabitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * Only allow the user to proceed if:
				 * 1. A title has been given to the habit
				 * 2. At least one day is selected for the habit
				 */
				boolean noText = habitNameTextField.getText().equals("");
				boolean atLeastOneDaySelected = habitDateChecked();
				debug.print("No text is " + noText + ". Since habitNameTextField.getText() is " + habitNameTextField.getText() + ", atLeastOneDaySelected is " + atLeastOneDaySelected);
				
				if(!noText && atLeastOneDaySelected) {
					Habit habit = new Habit(
								habitNameTextField.getText(),
								0,
								getOccurrenceString(),
								0,
								new Date()
							);
					HabitJsonManager.addHabit(habit);
					resetHabitForm();
				} else {
					if(noText && !atLeastOneDaySelected) {
						ErrorPane.displayError(formPanel, "You must give the habit a title and select a day before proceeding!");
					} else if(noText) {
						ErrorPane.displayError(formPanel, "You must give the habit a title before proceeding!");
					} else if(!atLeastOneDaySelected) {
						ErrorPane.displayError(formPanel, "You must select a day before proceeding!");
					}
				}
			}
			
		});	
	}

	@Override
	public void initPanel() {
		this.setLayout(new GridBagLayout()); 
		
		this.add(messagePanel, gbc);
        this.add(titlePanel, gbc);
        this.add(Box.createVerticalStrut(10), gbc);
        this.add(formPanel, gbc);
        this.add(confirmPanel, gbc);
	}
	
	private boolean habitDateChecked() {
		return mondayCheckBox.isSelected() 
				|| tuesdayCheckBox.isSelected() 
				|| wednesdayCheckBox.isSelected() 
				|| thursdayCheckBox.isSelected() 
				|| fridayCheckBox.isSelected() 
				|| saturdayCheckBox.isSelected() 
				|| sundayCheckBox.isSelected();
	}
	
	private void setTextComponentVisual(JComponent component) {
		component.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
		component.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		component.setFont(Constants.COMPONENT_FONT_NORMAL_BOLD);
	}
	
	private String getOccurrenceString() {
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
		return occurrenceString;
	}
	
	private void resetHabitForm() {
		habitNameTextField.setText("");
		mondayCheckBox.setSelected(false);
		tuesdayCheckBox.setSelected(false);
		wednesdayCheckBox.setSelected(false);
		thursdayCheckBox.setSelected(false);
		fridayCheckBox.setSelected(false);
		saturdayCheckBox.setSelected(false);
		sundayCheckBox.setSelected(false);
		
		messagePanelLabel.fade();
	}
}
