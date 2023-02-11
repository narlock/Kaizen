package panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import domain.Habit;
import state.State;
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
	private List<Habit> habits;
	
	public HabitsPanel(boolean withBorder) {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        
		this.habits = JsonReader.readHabits(1);
		createHabitPanels(habits, gbc);

		this.setBackground(new Color(20, 20, 20));
	}
	
	private void createHabitPanels(List<Habit> habits, GridBagConstraints gbc) {
		for(Habit habit : habits) {
			this.add(createHabitPanelFromHabit(habit), gbc);
		}
	} 
	
	private JPanel createHabitPanelFromHabit(Habit habit) {
		System.out.println("DEBUG " + habit.getTitle());
		JPanel habitMainPanel = new JPanel();
		habitMainPanel.setLayout(new BorderLayout());
		habitMainPanel.setPreferredSize(new Dimension(400, 65));
		habitMainPanel.setOpaque(true);
		habitMainPanel.setBackground(new Color(84, 84, 84));
		habitMainPanel.setBorder(new RoundedBorder(new Color(217, 217, 217), 3, 10, 10, true));
		
		JPanel habitCompletedPanel = new JPanel();
		habitCompletedPanel.setBackground(new Color(84, 84, 84));
		JButton completeHabit = new JButton();
		completeHabit.setPreferredSize(new Dimension(20, 20));
		completeHabit.setOpaque(true);
		completeHabit.setBorder(new RoundedBorder(new Color(217, 217, 217), 2, 20, 0, true));
		if(habit.isCompleted()) {
			completeHabit.setBackground(new Color(120, 120, 255));
			completeHabit.setEnabled(false);
		}
		completeHabit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				habit.setStatus(1);
				JsonWriter.updateHabitsJson(habits);
				completeHabit.setBackground(new Color(120, 120, 255));
				completeHabit.setEnabled(false);
			}
		});
		habitCompletedPanel.add(completeHabit);
		
		JPanel habitTitlePanel = new JPanel();
		habitTitlePanel.setBackground(new Color(84, 84, 84));
		JLabel habitTitle = new JLabel(habit.getTitle());
		habitTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		habitTitle.setForeground(new Color(217, 217, 217));
		habitTitlePanel.add(habitTitle);
		
		JPanel streakPanel = new JPanel();
		streakPanel.setBackground(new Color(84, 84, 84));
		JLabel streakLabel = new JLabel("🔥 " + habit.getStreak());
		streakLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		streakLabel.setForeground(new Color(217, 217, 217));
		streakPanel.add(streakLabel);
		
		habitMainPanel.add(habitCompletedPanel, BorderLayout.WEST);
		habitMainPanel.add(habitTitlePanel, BorderLayout.CENTER);
		habitMainPanel.add(streakPanel, BorderLayout.EAST);
		
		return habitMainPanel;
	}
}
