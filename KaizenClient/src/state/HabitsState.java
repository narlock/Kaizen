package state;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import panel.HabitsPanel;
import util.Constants;

public class HabitsState extends State {
	
	private JScrollPane scrollPane;
	private HabitsPanel habitsPanel;
	
	private JPanel optionsPanel;
	private JButton newHabitButton;
	private JButton updateHabitsButton;
	
	public HabitsState() {
		super();
	}

	@Override
	public void initPanelComponents() {
		//Set up habits panel
		habitsPanel = new HabitsPanel(0);
		scrollPane = new JScrollPane(habitsPanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(Constants.HABIT_SCROLL_PANE_NORMAL);
		scrollPane.setBorder(null);
		
		//Set up options panel
		optionsPanel = new JPanel();
		optionsPanel.setBackground(new Color(20, 20, 20));
		newHabitButton = new JButton();
		setButtonAttributes(newHabitButton, "New Habit");
		updateHabitsButton = new JButton();
		setButtonAttributes(updateHabitsButton, "Update Habits");
		
		optionsPanel.add(newHabitButton);
		optionsPanel.add(updateHabitsButton);
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
        
		this.add(scrollPane, gbc);
		this.add(optionsPanel, gbc);
	}
}
