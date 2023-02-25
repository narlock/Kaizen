package panel;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.Habit;
import util.Constants;
import util.ErrorPane;
import util.HabitJsonManager;

/**
 * HabitsPanel
 * 
 * @brief Panel containing habits. Allows the user to check
 * when a habit is completed. This class will read the habits
 * from the habits file and update them based off of whether
 * they are checked or not. This panel is used in the 
 * HomeState so the user can see the habits they need to
 * complete today, and also on the HabitsState.
 * @author narlock
 *
 */
public class HabitsPanel extends JPanel {
	private static final long serialVersionUID = 1101759487138037037L;
	private List<Habit> habits;
	private int size;
	private boolean update;
	public JLabel titleLabel; //Label from HabitsState / Widget
	private int completedHabits;
	
	public HabitsPanel(List<Habit> habits, JLabel titleLabel, int size, boolean update) {
		this.update = update;
		this.size = size;
		this.habits = habits;
		for(Habit habit : habits) {
			//TODO Only cycle through the habits for today, not the others!
			if(habit.isCompleted()) { completedHabits++; }
		}
		this.titleLabel = titleLabel;
		titleLabel.setText("Today's Habits | " + completedHabits + "/" + habits.size());
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        
		updateHabitsOnTime(); //Maybe move to HabitsState
		createHabitPanels(habits, gbc);

		this.setBackground(Constants.GUI_BACKGROUND_COLOR);
	}
	
	private void createHabitPanels(List<Habit> habits, GridBagConstraints gbc) {
		if(habits == null) {
			habits = new ArrayList<Habit>();
			this.habits = habits;
		}
		for(Habit habit : habits) {
			//TODO If they are habits that occur today, then add them!
			//Do not add the other ones!
			this.add(createHabitPanelFromHabit(habit), gbc);
		}
	} 
	
	private JPanel createHabitPanelFromHabit(Habit habit) {
		if(this.size == 0) {
			JPanel habitMainPanel = new JPanel();
			habitMainPanel.setLayout(new BorderLayout());
			habitMainPanel.setPreferredSize(Constants.HABIT_MAIN_PANEL_DIMENSION_NORMAL);
			habitMainPanel.setOpaque(true);
			habitMainPanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
			habitMainPanel.setBorder(Constants.COMPONENT_BORDER_NORMAL);
			
			if(!update) {
				//Add Circle Button to check off habit
				JPanel habitCompletedPanel = new JPanel();
				habitCompletedPanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
				JButton completeHabit = new JButton();
				completeHabit.setPreferredSize(Constants.COMPLETE_HABIT_DIMENSION_NORMAL);
				completeHabit.setOpaque(true);
				completeHabit.setBorder(Constants.HABIT_COMPLETE_BORDER_NORMAL);
				if(habit.isCompleted()) {
					completeHabit.setBackground(Constants.BUTTON_DEFAULT_COLOR);
					completeHabit.setEnabled(false);
				}
				completeHabit.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						habit.setStatus(1);
						completedHabits++;
						titleLabel.setText("Today's Habits | " + completedHabits + "/" + habits.size());
						HabitJsonManager.writeHabitJsonToFile(habits);
						completeHabit.setBackground(Constants.BUTTON_DEFAULT_COLOR);
						completeHabit.setEnabled(false);
					}
				});
				habitCompletedPanel.add(completeHabit);
				habitMainPanel.add(habitCompletedPanel, BorderLayout.WEST);
			} else {
				//Add Update Button
				JButton updateHabitButton = new JButton("Update");
				
			}
			JPanel habitTitlePanel = new JPanel();
			habitTitlePanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
			JLabel habitTitle = new JLabel(habit.getTitle());
			habitTitle.setFont(Constants.COMPONENT_FONT_NORMAL);
			habitTitle.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
			habitTitlePanel.add(habitTitle);
			habitMainPanel.add(habitTitlePanel, BorderLayout.CENTER);
			
			if(!this.update) {
				//Create Streak panel
				JPanel streakPanel = new JPanel();
				streakPanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
				JLabel streakLabel = new JLabel(habit.getStreak() + Constants.FIRE_EMOJI_SPACE);
				streakLabel.setFont(Constants.COMPONENT_FONT_NORMAL);
				streakLabel.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
				streakPanel.add(streakLabel);
				habitMainPanel.add(streakPanel, BorderLayout.EAST);
			} else {
				//Add Delete Button
				
			}
			return habitMainPanel;
		} else if (this.size == 1) {
			JPanel habitMainPanel = new JPanel();
			habitMainPanel.setLayout(new BorderLayout());
			habitMainPanel.setPreferredSize(Constants.HABIT_MAIN_PANEL_DIMENSION_SMALL);
			habitMainPanel.setOpaque(true);
			habitMainPanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
			habitMainPanel.setBorder(Constants.COMPONENT_BORDER_SMALL);
			
			JPanel habitCompletedPanel = new JPanel();
			habitCompletedPanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
			JButton completeHabit = new JButton();
			completeHabit.setPreferredSize(Constants.COMPLETE_HABIT_DIMENSION_SMALL);
			completeHabit.setOpaque(true);
			completeHabit.setBorder(Constants.HABIT_COMPLETE_BORDER_SMALL);
			if(habit.isCompleted()) {
				completeHabit.setBackground(Constants.BUTTON_DEFAULT_COLOR);
				completeHabit.setEnabled(false);
			}
			completeHabit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					habit.setStatus(1);
					HabitJsonManager.writeHabitJsonToFile(habits);
					completeHabit.setBackground(Constants.BUTTON_DEFAULT_COLOR);
					completeHabit.setEnabled(false);
				}
			});
			habitCompletedPanel.add(completeHabit);
			
			JPanel habitTitlePanel = new JPanel();
			habitTitlePanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
			JLabel habitTitle = new JLabel(habit.getTitle());
			habitTitle.setFont(Constants.COMPONENT_FONT_SMALL);
			habitTitle.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
			habitTitlePanel.add(habitTitle);
			
			JPanel streakPanel = new JPanel();
			streakPanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
			JLabel streakLabel = new JLabel(habit.getStreak() + Constants.FIRE_EMOJI_SPACE);
			streakLabel.setFont(Constants.COMPONENT_FONT_SMALL);
			streakLabel.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
			streakPanel.add(streakLabel);
			
			habitMainPanel.add(habitCompletedPanel, BorderLayout.WEST);
			habitMainPanel.add(habitTitlePanel, BorderLayout.CENTER);
			habitMainPanel.add(streakPanel, BorderLayout.EAST);
			
			return habitMainPanel;
		} else {
			ErrorPane.displayError(this, "Unexpected error occurred during the creation of the HabitsPanel."
					+ "\nPlease contact narlock (the developer) if you come across this error.");
			System.exit(1);
			throw new RuntimeException("Unexpected error occurred when creating HabitsPanel");
		}
	}
	
	private void updateHabitsOnTime() {
		//TODO This method will update the habits if for example
		// a day has passed, it will reset the status...
		
		//On consecutive day for doing the habit, streak will be incremented
		//If a streak is broken set that here...
		//Etc.
	}
}
