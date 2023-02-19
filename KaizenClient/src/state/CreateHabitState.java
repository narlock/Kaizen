package state;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.ErrorPane;

public class CreateHabitState extends State {

	private static final long serialVersionUID = 1823884086458179558L;
	private GridBagConstraints gbc;
	
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
		
		titlePanel = new JPanel();
		titleLabel = new JLabel("What habit would you like to start?");
		titlePanel.add(titleLabel);
		
		formPanel = new JPanel();
		formPanel.setLayout(new GridBagLayout());
		
		habitNamePanel = new JPanel();
		habitNameLabel = new JLabel("Name your habit: ");
		habitNameTextField = new JTextField(10);
		habitNamePanel.add(habitNameLabel);
		habitNamePanel.add(habitNameTextField);
		
		occurrenceLabel = new JLabel("What days will you do this habit?");
		mondayCheckBox = new JCheckBox("Monday");
		tuesdayCheckBox = new JCheckBox("Tuesday");
		wednesdayCheckBox = new JCheckBox("Wednesday");
		thursdayCheckBox = new JCheckBox("Thursday");
		fridayCheckBox = new JCheckBox("Friday");
		saturdayCheckBox = new JCheckBox("Saturday");
		sundayCheckBox = new JCheckBox("Sunday");
		
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
		addHabitButton = new JButton("Add Habit");
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
				boolean noText = titleLabel.getText().equals("");
				boolean atLeastOneDaySelected = habitDateChecked();
				
				if(!noText && atLeastOneDaySelected) {
					//TODO Create the habit, change scene to HabitsState
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
		
        this.add(titlePanel, gbc);
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

}
