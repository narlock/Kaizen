package panel;

import java.util.List;

import javax.swing.JPanel;

import domain.Habit;
import util.JsonReader;

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
	private List<JPanel> habitPanels;
	
	public HabitsPanel() {
		this.habits = JsonReader.readHabits();
		this.habitPanels = createHabitPanels(habits);
	}
	
	private List<JPanel> createHabitPanels(List<Habit> habits) {
		for(Habit habit : habits) {
			JPanel habitPanel = new JPanel();
			
		}
		return null;
	}
}
