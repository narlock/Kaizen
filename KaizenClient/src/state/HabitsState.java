package state;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import panel.HabitsPanel;

public class HabitsState extends State {
	
	private HabitsPanel habitsPanel;
	
	public HabitsState() {
		super();
	}

	@Override
	public void initPanelComponents() {
		// TODO Auto-generated method stub
		habitsPanel = new HabitsPanel(false);
		
	}

	@Override
	public void initPanelComponentActions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initPanel() {
		// TODO Auto-generated method stub
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
		this.add(habitsPanel, gbc);
		
		JPanel habitOptionsPanel = new JPanel();
		habitOptionsPanel.setBackground(new Color(20, 20, 20));
		JButton newHabitButton = new JButton("New Habit");
		JButton updateHabitsButton = new JButton("Update Habits");
		habitOptionsPanel.add(newHabitButton);
		habitOptionsPanel.add(updateHabitsButton);
		this.add(habitOptionsPanel, gbc);
	}
}
