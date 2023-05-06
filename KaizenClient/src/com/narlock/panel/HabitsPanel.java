package com.narlock.panel;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.narlock.domain.Habit;
import com.narlock.json.HabitJsonManager;
import com.narlock.util.Constants;
import com.narlock.util.ErrorPane;
import com.narlock.util.HabitUtils;

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
	public JLabel titleLabel; //Label from HabitsState / Widget
	private int completedHabits;
	private int todaysHabitsSize;
	
	public HabitsPanel(List<Habit> habits, JLabel titleLabel, int size) {
		this.size = size;
		this.habits = habits;
		
		todaysHabitsSize = 0;
		for(Habit habit : habits) {
			if(HabitUtils.occursToday(habit) && HabitUtils.sameDay(habit)) {
				todaysHabitsSize++;
				if(habit.isCompleted()) { completedHabits++; }
			}
		}
		this.titleLabel = titleLabel;
		titleLabel.setText("Today's Habits • " + completedHabits + "/" + todaysHabitsSize);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        
		createHabitPanels(habits, gbc);

		this.setBackground(Constants.GUI_BACKGROUND_COLOR);
	}
	
	private void createHabitPanels(List<Habit> habits, GridBagConstraints gbc) {
		if(habits == null) {
			habits = new ArrayList<Habit>();
			this.habits = habits;
		}
		for(Habit habit : habits) {
			if(HabitUtils.occursToday(habit)) {
				if(habit.occursEveryday() || habit.occursOnceAWeek())
					this.add(createHabitPanelFromHabit(habit, "FIRE.png"), gbc);
				else
					this.add(createHabitPanelFromHabit(habit, "STAR.png"), gbc);
			}
		}
	} 
	
	private JPanel createHabitPanelFromHabit(Habit habit, String streakIconPath) {
		if(this.size == 0) {
			JPanel habitMainPanel = new JPanel();
			habitMainPanel.setLayout(new BorderLayout());
			habitMainPanel.setPreferredSize(Constants.HABIT_MAIN_PANEL_DIMENSION_NORMAL);
			habitMainPanel.setOpaque(true);
			habitMainPanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
			habitMainPanel.setBorder(Constants.COMPONENT_BORDER_NORMAL);
			
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
					titleLabel.setText("Today's Habits • " + completedHabits + "/" + todaysHabitsSize);
					HabitJsonManager.writeHabitJsonToFile(habits);
					completeHabit.setBackground(Constants.BUTTON_DEFAULT_COLOR);
					completeHabit.setEnabled(false);
				}
			});
			habitCompletedPanel.add(completeHabit);
			habitMainPanel.add(habitCompletedPanel, BorderLayout.WEST);
			
			JPanel habitTitlePanel = new JPanel();
			habitTitlePanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
			JLabel habitTitle = new JLabel(habit.getTitle());
			habitTitle.setFont(Constants.COMPONENT_FONT_NORMAL_BOLD);
			habitTitle.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
			habitTitlePanel.add(habitTitle);
			habitMainPanel.add(habitTitlePanel, BorderLayout.CENTER);
			
			//Create Streak panel
			JPanel streakPanel = new JPanel();
			streakPanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
			JLabel streakLabel = new JLabel("" + habit.getStreak(), new ImageIcon(getClass().getClassLoader().getResource(streakIconPath)), SwingConstants.LEADING);
			streakLabel.setHorizontalTextPosition(SwingConstants.LEADING);
			streakLabel.setFont(Constants.COMPONENT_FONT_NORMAL_BOLD);
			setStreakColor(streakLabel, habit.getStreak());
			streakPanel.add(streakLabel);
				habitMainPanel.add(streakPanel, BorderLayout.EAST);
				
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
					completedHabits++;
					titleLabel.setText("Today's Habits • " + completedHabits + "/" + todaysHabitsSize);
					HabitJsonManager.writeHabitJsonToFile(habits);
					completeHabit.setBackground(Constants.BUTTON_DEFAULT_COLOR);
					completeHabit.setEnabled(false);
				}
			});
			habitCompletedPanel.add(completeHabit);
			
			JPanel habitTitlePanel = new JPanel();
			habitTitlePanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
			JLabel habitTitle = new JLabel(habit.getTitle());
			habitTitle.setFont(Constants.COMPONENT_FONT_SMALL_BOLD);
			habitTitle.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
			habitTitlePanel.add(habitTitle);
			
			JPanel streakPanel = new JPanel();
			streakPanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
			JLabel streakLabel = new JLabel("" + habit.getStreak(), new ImageIcon(getClass().getClassLoader().getResource(streakIconPath)), SwingConstants.LEADING);
			streakLabel.setHorizontalTextPosition(SwingConstants.LEADING);
			streakLabel.setFont(Constants.COMPONENT_FONT_SMALL_BOLD);
			setStreakColor(streakLabel, habit.getStreak());
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
	
	/**
	 * The color of the streak will be changed based off of
	 * different milestones that are hit.
	 * @param streakLabel
	 */
	public void setStreakColor(JLabel streakLabel, long streak) {
		if(streak <= 7) {
			streakLabel.setForeground(Constants.KAIZEN_GREEN);
		}
		else if(streak >= 8 && streak <= 30) {
			streakLabel.setForeground(Constants.KAIZEN_GOLD);
		}
		else if(streak >= 31 && streak <= 90) {
			streakLabel.setForeground(Constants.KAIZEN_DIAMOND);
		}
		else if(streak >= 91 && streak <= 300) {
			streakLabel.setForeground(Constants.KAIZEN_PINK);
		}
		else {
			streakLabel.setForeground(Constants.KAIZEN_RED);
		}
	}
}
