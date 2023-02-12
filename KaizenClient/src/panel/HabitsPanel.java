package panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.Habit;
import util.Constants;
import util.JsonReader;
import util.JsonWriter;
import util.RoundedBorder;

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
	
	public HabitsPanel(int size) {
		this.size = size;
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        
		this.habits = JsonReader.readHabits(1);
		createHabitPanels(habits, gbc);

		this.setBackground(Constants.GUI_BACKGROUND_COLOR);
	}
	
	private void createHabitPanels(List<Habit> habits, GridBagConstraints gbc) {
		for(Habit habit : habits) {
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
					JsonWriter.updateHabitsJson(habits);
					completeHabit.setBackground(Constants.BUTTON_DEFAULT_COLOR);
					completeHabit.setEnabled(false);
				}
			});
			habitCompletedPanel.add(completeHabit);
			
			JPanel habitTitlePanel = new JPanel();
			habitTitlePanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
			JLabel habitTitle = new JLabel(habit.getTitle());
			habitTitle.setFont(Constants.COMPONENT_FONT_NORMAL);
			habitTitle.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
			habitTitlePanel.add(habitTitle);
			
			JPanel streakPanel = new JPanel();
			streakPanel.setBackground(Constants.COMPONENT_BACKGROUND_COLOR);
			JLabel streakLabel = new JLabel(Constants.FIRE_EMOJI_SPACE + habit.getStreak());
			streakLabel.setFont(Constants.COMPONENT_FONT_NORMAL);
			streakLabel.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
			streakPanel.add(streakLabel);
			
			habitMainPanel.add(habitCompletedPanel, BorderLayout.WEST);
			habitMainPanel.add(habitTitlePanel, BorderLayout.CENTER);
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
					JsonWriter.updateHabitsJson(habits);
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
			JLabel streakLabel = new JLabel(Constants.FIRE_EMOJI_SPACE + habit.getStreak());
			streakLabel.setFont(Constants.COMPONENT_FONT_SMALL);
			streakLabel.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
			streakPanel.add(streakLabel);
			
			habitMainPanel.add(habitCompletedPanel, BorderLayout.WEST);
			habitMainPanel.add(habitTitlePanel, BorderLayout.CENTER);
			habitMainPanel.add(streakPanel, BorderLayout.EAST);
			
			return habitMainPanel;
		} else {
			throw new RuntimeException("Unexpected error occurred when creating HabitsPanel");
			//TODO throw error screen
		}
	}
}
