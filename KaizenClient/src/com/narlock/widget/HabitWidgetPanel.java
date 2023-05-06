package com.narlock.widget;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.narlock.domain.Habit;
import com.narlock.json.HabitJsonManager;
import com.narlock.panel.HabitsPanel;
import com.narlock.util.Constants;
import com.narlock.util.Debug;
import com.narlock.util.HabitUtils;

public class HabitWidgetPanel extends JPanel {
	
	private List<Habit> habits;
	
	private static final long serialVersionUID = 2456894465204691767L;
	private final Debug debug = new Debug(true);
	
	private JPanel titlePanel;
	private JLabel titleLabel;
	
	private JPanel habitContainerPanel;
	private JScrollPane scrollPane;
	private HabitsPanel habitsPanel;
	
	public HabitWidgetPanel() {
		this.habits = HabitJsonManager.readHabits();
		HabitUtils.updateHabits(habits);
		HabitJsonManager.writeHabitJsonToFile(habits);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		this.setLayout(new GridBagLayout());
		
		this.setBackground(Constants.GUI_BACKGROUND_COLOR);
		titlePanel = new JPanel();
		titlePanel.setBackground(Constants.GUI_BACKGROUND_COLOR);
		titleLabel = new JLabel();
		titleLabel.setForeground(Constants.COMPONENT_FOREGROUND_COLOR);
		titleLabel.setFont(Constants.COMPONENT_FONT_SMALL_BOLD);
		titlePanel.add(titleLabel);
		
		habitContainerPanel = new JPanel();
		habitContainerPanel.setBackground(Constants.GUI_BACKGROUND_COLOR);
		
		habitsPanel = new HabitsPanel(habits, titleLabel, 1);
		scrollPane = new JScrollPane(habitsPanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.setPreferredSize(Constants.HABIT_SCROLL_PANE_SMALL);
		scrollPane.setBorder(null);
		habitContainerPanel.add(scrollPane);
		
		this.add(titlePanel, gbc);
		this.add(Box.createVerticalStrut(20), gbc);
		this.add(habitContainerPanel, gbc);
	}

}
