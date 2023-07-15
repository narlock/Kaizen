package com.narlock.state;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.narlock.domain.Home;
import com.narlock.domain.Settings;
import com.narlock.json.HomeJsonManager;
import com.narlock.widget.AntiHabitWidgetPanel;
import com.narlock.widget.HabitWidgetPanel;
import com.narlock.widget.JournalWidgetPanel;
import com.narlock.widget.NoWidgetPanel;
import com.narlock.widget.TodoWidgetPanel;

public class HomeState extends State {
	
	private static final long serialVersionUID = -794674909058043997L;
	private Home home;
	
	private JPanel widget;
	private JPanel widget2;
	private JPanel widget3;
	private JPanel widget4;
	
	public HomeState(Settings settings) {
		super(settings);
	}

	@Override
	public void initPanelComponents() {
		home = HomeJsonManager.readJson();
		
		
		//Set up habits panel
		widget = getWidgetFromString(home.getWidget1());
		widget2 = getWidgetFromString(home.getWidget2());
		widget3 = getWidgetFromString(home.getWidget3());
		widget4 = getWidgetFromString(home.getWidget4());
	}

	@Override
	public void initPanelComponentActions() {}

	@Override
	public void initPanel() {
		// TODO Auto-generated method stub
		this.setLayout(new GridLayout(2,2));
//		this.setLayout(new BorderLayout());
//		JPanel leftPanel = new JPanel(new GridLayout(2, 1));
//		leftPanel.add(widget);
//		leftPanel.add(widget2);
//		JPanel centerPanel = new JPanel();
//		centerPanel.setBackground(Color.BLACK);
//		JPanel rightPanel = new JPanel(new GridLayout(2, 1));
//		rightPanel.add(widget3);
//		rightPanel.add(widget4);
		this.add(widget);
		this.add(widget2);
		this.add(widget3);
		this.add(widget4);
//		this.add(leftPanel, BorderLayout.WEST);
//		this.add(centerPanel, BorderLayout.CENTER);
//		this.add(rightPanel, BorderLayout.EAST);
	}

	public JPanel getWidgetFromString(String widgetString) {
		switch(widgetString) {
		case "todo":
			return new TodoWidgetPanel();
		case "habits":
			return new HabitWidgetPanel();
		case "antiHabits":
			return new AntiHabitWidgetPanel();
		case "journal1":
			return new JournalWidgetPanel(1, settings);
		case "journal2":
			return new JournalWidgetPanel(2, settings);
		case "journal3":
			return new JournalWidgetPanel(3, settings);
		case "journal4":
			return new JournalWidgetPanel(4, settings);
		default:
			return new NoWidgetPanel();
		}
	}
}
