package state;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.plaf.basic.BasicScrollBarUI;

import domain.Habit;
import json.HabitJsonManager;
import panel.HabitsPanel;
import util.Constants;
import util.HabitUtils;

public class HabitsState extends State {
	
	private static final long serialVersionUID = 6371695672562881139L;
	
	private List<Habit> habits;
	
	private JPanel titlePanel;
	public JLabel titleLabel;
	
	private JScrollPane scrollPane;
	private HabitsPanel habitsPanel;
	
	public HabitsState() {
		super();
		
	}

	@Override
	public void initPanelComponents() {
		//Get habits
		this.habits = HabitJsonManager.readHabits();
		HabitUtils.updateHabits(habits);
		HabitJsonManager.writeHabitJsonToFile(habits);
		
		//Set up title panel
		titlePanel = new JPanel();
		titlePanel.setBackground(Constants.GUI_BACKGROUND_COLOR);
		titleLabel = new JLabel();
		titleLabel.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		titleLabel.setFont(Constants.COMPONENT_FONT_NORMAL_BOLD);
		titlePanel.add(titleLabel);
		
		habitsPanel = new HabitsPanel(habits, titleLabel, 0);
		scrollPane = new JScrollPane(habitsPanel,
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
