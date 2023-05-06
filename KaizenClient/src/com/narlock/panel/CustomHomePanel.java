package com.narlock.panel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.narlock.domain.Home;
import com.narlock.json.HomeJsonManager;

public class CustomHomePanel extends JPanel {
	private JComboBox<String> widget1Box, widget2Box, widget3Box, widget4Box;
	private Home home;
	
	public CustomHomePanel() {
		// Initialize widget boxes
		widget1Box = new JComboBox<String>();
		addWidgetOptionsToBox(widget1Box);
		widget2Box = new JComboBox<String>();
		addWidgetOptionsToBox(widget2Box);	
		widget3Box = new JComboBox<String>();
		addWidgetOptionsToBox(widget3Box);	
		widget4Box = new JComboBox<String>();
		addWidgetOptionsToBox(widget4Box);
		
		// Read Home information, then enable/disable fields
		home = HomeJsonManager.readJson();
		setSelectedItem(widget1Box, home.getWidget1());
		setSelectedItem(widget2Box, home.getWidget2());
		setSelectedItem(widget3Box, home.getWidget3());
		setSelectedItem(widget4Box, home.getWidget4());
		
		addActionToBox(widget1Box, 1);
		addActionToBox(widget2Box, 2);
		addActionToBox(widget3Box, 3);
		addActionToBox(widget4Box, 4);
		
		this.setLayout(new GridLayout(2, 2));
		this.add(widget1Box);
		this.add(widget2Box);
		this.add(widget3Box);
		this.add(widget4Box);
	}
	
	public void addWidgetOptionsToBox(JComboBox<String> box) {
		box.addItem("");
		box.addItem("Todo");
		box.addItem("Habits");
		box.addItem("AntiHabits");
		box.addItem("Journal Question 1");
		box.addItem("Journal Question 2");
		box.addItem("Journal Question 3");
		box.addItem("Journal Question 4");
	}
	
	public void setSelectedItem(JComboBox<String> box, String widget) {
		switch(widget) {
		case "todo":
			box.setSelectedItem("Todo");
			break;
		case "habits":
			box.setSelectedItem("Habits");
			break;
		case "antiHabits":
			box.setSelectedItem("AntiHabits");
			break;
		case "journal1":
			box.setSelectedItem("Journal Question 1");
			break;
		case "journal2":
			box.setSelectedItem("Journal Question 2");
			break;
		case "journal3":
			box.setSelectedItem("Journal Question 3");
			break;
		case "journal4":
			box.setSelectedItem("Journal Question 4");
			break;
		default:
			box.setSelectedItem("");
			break;
		}
	}
	
	public void addActionToBox(JComboBox<String> box, int widgetBox) {
		box.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("[LOLLOLOL]");
				// Get Widget
				String widgetString = getWidgetString((String) box.getSelectedItem());
				
				// Update Home
				switch(widgetBox) {
				case 1:
					home.setWidget1(widgetString);
					break;
				case 2:
					home.setWidget2(widgetString);
					break;
				case 3:
					home.setWidget3(widgetString);
					break;
				case 4:
					home.setWidget4(widgetString);
					break;
				}
				
				// Update Json
				HomeJsonManager.writeHomeToFile(home);
			}
			
		});
	}
	
	public String getWidgetString(String widgetItem) {
		switch(widgetItem) {
		case "Todo":
			return "todo";
		case "Habits":
			return "habits";
		case "AntiHabits":
			return "antiHabits";
		case "Journal Question 1":
			return "journal1";
		case "Journal Question 2":
			return "journal2";
		case "Journal Question 3":
			return "journal3";
		case "Journal Question 4":
			return "journal4";
		case "":
			return "";
		default:
			return "";
		}
	}
}
