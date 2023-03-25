package state;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import domain.AntiHabit;
import json.AntiHabitJsonManager;
import panel.AntiHabitsPanel;
import panel.HabitsPanel;
import util.Constants;

public class AntiHabitsState extends State {
	
	private List<AntiHabit> antiHabits;
	
	private JPanel titlePanel;
	private JLabel titleLabel;
	
	private JScrollPane scrollPane;
	private AntiHabitsPanel antiHabitsPanel;
	
	public AntiHabitsState() {
		super();
	}

	@Override
	public void initPanelComponents() {
		// Get antiHabits
		this.antiHabits = AntiHabitJsonManager.readJson();
		
		//Set up title panel
		titlePanel = new JPanel();
		titlePanel.setBackground(Constants.GUI_BACKGROUND_COLOR);
		titleLabel = new JLabel("Anti Habits");
		titleLabel.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		titleLabel.setFont(Constants.COMPONENT_FONT_NORMAL_BOLD);
		titlePanel.add(titleLabel);
		
		antiHabitsPanel = new AntiHabitsPanel(antiHabits);
		scrollPane = new JScrollPane(antiHabitsPanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.setPreferredSize(Constants.HABIT_SCROLL_PANE_NORMAL);
		scrollPane.setBorder(null);
	}

	@Override
	public void initPanelComponentActions() {}

	@Override
	public void initPanel() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;   
        
        this.add(titlePanel, gbc);
        this.add(Box.createVerticalStrut(20), gbc);
		this.add(scrollPane, gbc);
	}

}
