package panel;

import java.awt.GridBagConstraints;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.AntiHabit;
import util.AntiHabitUtils;
import util.Utils;

public class AntiHabitsPanel extends JPanel {
	
	private List<AntiHabit> antiHabits;
	
	public AntiHabitsPanel(List<AntiHabit> antiHabits) {
		// Initialize variables
		this.antiHabits = antiHabits;
		GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		// Create each individual panel
		for(AntiHabit antiHabit : antiHabits) {
			this.add(createAntiHabitPanel(antiHabit), gbc);
		}
	}
	
	private JPanel createAntiHabitPanel(AntiHabit antiHabit) {
		JPanel mainPanel = new JPanel();
		
		JPanel daysSincePanel = new JPanel();
		JLabel daysSinceLabel = new JLabel("" + AntiHabitUtils.getDaysSince(antiHabit.getDate()));
		
		return mainPanel;
	}
	
}
